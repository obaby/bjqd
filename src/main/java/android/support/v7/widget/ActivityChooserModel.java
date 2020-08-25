package android.support.v7.widget;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.database.DataSetObservable;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import android.util.Xml;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

class ActivityChooserModel extends DataSetObservable {
    static final String ATTRIBUTE_ACTIVITY = "activity";
    static final String ATTRIBUTE_TIME = "time";
    static final String ATTRIBUTE_WEIGHT = "weight";
    static final boolean DEBUG = false;
    private static final int DEFAULT_ACTIVITY_INFLATION = 5;
    private static final float DEFAULT_HISTORICAL_RECORD_WEIGHT = 1.0f;
    public static final String DEFAULT_HISTORY_FILE_NAME = "activity_choser_model_history.xml";
    public static final int DEFAULT_HISTORY_MAX_LENGTH = 50;
    private static final String HISTORY_FILE_EXTENSION = ".xml";
    private static final int INVALID_INDEX = -1;
    static final String LOG_TAG = "ActivityChooserModel";
    static final String TAG_HISTORICAL_RECORD = "historical-record";
    static final String TAG_HISTORICAL_RECORDS = "historical-records";
    private static final Map<String, ActivityChooserModel> sDataModelRegistry = new HashMap();
    private static final Object sRegistryLock = new Object();
    private final List<ActivityResolveInfo> mActivities = new ArrayList();
    private OnChooseActivityListener mActivityChoserModelPolicy;
    private ActivitySorter mActivitySorter = new DefaultSorter();
    boolean mCanReadHistoricalData = true;
    final Context mContext;
    private final List<HistoricalRecord> mHistoricalRecords = new ArrayList();
    private boolean mHistoricalRecordsChanged = true;
    final String mHistoryFileName;
    private int mHistoryMaxSize = 50;
    private final Object mInstanceLock = new Object();
    private Intent mIntent;
    private boolean mReadShareHistoryCalled = false;
    private boolean mReloadActivities = false;

    public interface ActivityChooserModelClient {
        void setActivityChooserModel(ActivityChooserModel activityChooserModel);
    }

    public interface ActivitySorter {
        void sort(Intent intent, List<ActivityResolveInfo> list, List<HistoricalRecord> list2);
    }

    public interface OnChooseActivityListener {
        boolean onChooseActivity(ActivityChooserModel activityChooserModel, Intent intent);
    }

    public static ActivityChooserModel get(Context context, String str) {
        ActivityChooserModel activityChooserModel;
        synchronized (sRegistryLock) {
            activityChooserModel = sDataModelRegistry.get(str);
            if (activityChooserModel == null) {
                activityChooserModel = new ActivityChooserModel(context, str);
                sDataModelRegistry.put(str, activityChooserModel);
            }
        }
        return activityChooserModel;
    }

    private ActivityChooserModel(Context context, String str) {
        this.mContext = context.getApplicationContext();
        if (TextUtils.isEmpty(str) || str.endsWith(HISTORY_FILE_EXTENSION)) {
            this.mHistoryFileName = str;
            return;
        }
        this.mHistoryFileName = str + HISTORY_FILE_EXTENSION;
    }

    public void setIntent(Intent intent) {
        synchronized (this.mInstanceLock) {
            if (this.mIntent != intent) {
                this.mIntent = intent;
                this.mReloadActivities = true;
                ensureConsistentState();
            }
        }
    }

    public Intent getIntent() {
        Intent intent;
        synchronized (this.mInstanceLock) {
            intent = this.mIntent;
        }
        return intent;
    }

    public int getActivityCount() {
        int size;
        synchronized (this.mInstanceLock) {
            ensureConsistentState();
            size = this.mActivities.size();
        }
        return size;
    }

    public ResolveInfo getActivity(int i) {
        ResolveInfo resolveInfo;
        synchronized (this.mInstanceLock) {
            ensureConsistentState();
            resolveInfo = this.mActivities.get(i).resolveInfo;
        }
        return resolveInfo;
    }

    public int getActivityIndex(ResolveInfo resolveInfo) {
        synchronized (this.mInstanceLock) {
            ensureConsistentState();
            List<ActivityResolveInfo> list = this.mActivities;
            int size = list.size();
            for (int i = 0; i < size; i++) {
                if (list.get(i).resolveInfo == resolveInfo) {
                    return i;
                }
            }
            return -1;
        }
    }

    public Intent chooseActivity(int i) {
        synchronized (this.mInstanceLock) {
            if (this.mIntent == null) {
                return null;
            }
            ensureConsistentState();
            ActivityResolveInfo activityResolveInfo = this.mActivities.get(i);
            ComponentName componentName = new ComponentName(activityResolveInfo.resolveInfo.activityInfo.packageName, activityResolveInfo.resolveInfo.activityInfo.name);
            Intent intent = new Intent(this.mIntent);
            intent.setComponent(componentName);
            if (this.mActivityChoserModelPolicy != null) {
                if (this.mActivityChoserModelPolicy.onChooseActivity(this, new Intent(intent))) {
                    return null;
                }
            }
            addHistoricalRecord(new HistoricalRecord(componentName, System.currentTimeMillis(), (float) DEFAULT_HISTORICAL_RECORD_WEIGHT));
            return intent;
        }
    }

    public void setOnChooseActivityListener(OnChooseActivityListener onChooseActivityListener) {
        synchronized (this.mInstanceLock) {
            this.mActivityChoserModelPolicy = onChooseActivityListener;
        }
    }

    public ResolveInfo getDefaultActivity() {
        synchronized (this.mInstanceLock) {
            ensureConsistentState();
            if (this.mActivities.isEmpty()) {
                return null;
            }
            ResolveInfo resolveInfo = this.mActivities.get(0).resolveInfo;
            return resolveInfo;
        }
    }

    public void setDefaultActivity(int i) {
        synchronized (this.mInstanceLock) {
            ensureConsistentState();
            ActivityResolveInfo activityResolveInfo = this.mActivities.get(i);
            ActivityResolveInfo activityResolveInfo2 = this.mActivities.get(0);
            addHistoricalRecord(new HistoricalRecord(new ComponentName(activityResolveInfo.resolveInfo.activityInfo.packageName, activityResolveInfo.resolveInfo.activityInfo.name), System.currentTimeMillis(), activityResolveInfo2 != null ? (activityResolveInfo2.weight - activityResolveInfo.weight) + 5.0f : DEFAULT_HISTORICAL_RECORD_WEIGHT));
        }
    }

    private void persistHistoricalDataIfNeeded() {
        if (!this.mReadShareHistoryCalled) {
            throw new IllegalStateException("No preceding call to #readHistoricalData");
        } else if (this.mHistoricalRecordsChanged) {
            this.mHistoricalRecordsChanged = false;
            if (!TextUtils.isEmpty(this.mHistoryFileName)) {
                new PersistHistoryAsyncTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Object[]{new ArrayList(this.mHistoricalRecords), this.mHistoryFileName});
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0015, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setActivitySorter(android.support.v7.widget.ActivityChooserModel.ActivitySorter r3) {
        /*
            r2 = this;
            java.lang.Object r0 = r2.mInstanceLock
            monitor-enter(r0)
            android.support.v7.widget.ActivityChooserModel$ActivitySorter r1 = r2.mActivitySorter     // Catch:{ all -> 0x0016 }
            if (r1 != r3) goto L_0x0009
            monitor-exit(r0)     // Catch:{ all -> 0x0016 }
            return
        L_0x0009:
            r2.mActivitySorter = r3     // Catch:{ all -> 0x0016 }
            boolean r3 = r2.sortActivitiesIfNeeded()     // Catch:{ all -> 0x0016 }
            if (r3 == 0) goto L_0x0014
            r2.notifyChanged()     // Catch:{ all -> 0x0016 }
        L_0x0014:
            monitor-exit(r0)     // Catch:{ all -> 0x0016 }
            return
        L_0x0016:
            r3 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0016 }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.ActivityChooserModel.setActivitySorter(android.support.v7.widget.ActivityChooserModel$ActivitySorter):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0018, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setHistoryMaxSize(int r3) {
        /*
            r2 = this;
            java.lang.Object r0 = r2.mInstanceLock
            monitor-enter(r0)
            int r1 = r2.mHistoryMaxSize     // Catch:{ all -> 0x0019 }
            if (r1 != r3) goto L_0x0009
            monitor-exit(r0)     // Catch:{ all -> 0x0019 }
            return
        L_0x0009:
            r2.mHistoryMaxSize = r3     // Catch:{ all -> 0x0019 }
            r2.pruneExcessiveHistoricalRecordsIfNeeded()     // Catch:{ all -> 0x0019 }
            boolean r3 = r2.sortActivitiesIfNeeded()     // Catch:{ all -> 0x0019 }
            if (r3 == 0) goto L_0x0017
            r2.notifyChanged()     // Catch:{ all -> 0x0019 }
        L_0x0017:
            monitor-exit(r0)     // Catch:{ all -> 0x0019 }
            return
        L_0x0019:
            r3 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0019 }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.ActivityChooserModel.setHistoryMaxSize(int):void");
    }

    public int getHistoryMaxSize() {
        int i;
        synchronized (this.mInstanceLock) {
            i = this.mHistoryMaxSize;
        }
        return i;
    }

    public int getHistorySize() {
        int size;
        synchronized (this.mInstanceLock) {
            ensureConsistentState();
            size = this.mHistoricalRecords.size();
        }
        return size;
    }

    private void ensureConsistentState() {
        boolean loadActivitiesIfNeeded = loadActivitiesIfNeeded() | readHistoricalDataIfNeeded();
        pruneExcessiveHistoricalRecordsIfNeeded();
        if (loadActivitiesIfNeeded) {
            sortActivitiesIfNeeded();
            notifyChanged();
        }
    }

    private boolean sortActivitiesIfNeeded() {
        if (this.mActivitySorter == null || this.mIntent == null || this.mActivities.isEmpty() || this.mHistoricalRecords.isEmpty()) {
            return false;
        }
        this.mActivitySorter.sort(this.mIntent, this.mActivities, Collections.unmodifiableList(this.mHistoricalRecords));
        return true;
    }

    private boolean loadActivitiesIfNeeded() {
        if (!this.mReloadActivities || this.mIntent == null) {
            return false;
        }
        this.mReloadActivities = false;
        this.mActivities.clear();
        List<ResolveInfo> queryIntentActivities = this.mContext.getPackageManager().queryIntentActivities(this.mIntent, 0);
        int size = queryIntentActivities.size();
        for (int i = 0; i < size; i++) {
            this.mActivities.add(new ActivityResolveInfo(queryIntentActivities.get(i)));
        }
        return true;
    }

    private boolean readHistoricalDataIfNeeded() {
        if (!this.mCanReadHistoricalData || !this.mHistoricalRecordsChanged || TextUtils.isEmpty(this.mHistoryFileName)) {
            return false;
        }
        this.mCanReadHistoricalData = false;
        this.mReadShareHistoryCalled = true;
        readHistoricalDataImpl();
        return true;
    }

    private boolean addHistoricalRecord(HistoricalRecord historicalRecord) {
        boolean add = this.mHistoricalRecords.add(historicalRecord);
        if (add) {
            this.mHistoricalRecordsChanged = true;
            pruneExcessiveHistoricalRecordsIfNeeded();
            persistHistoricalDataIfNeeded();
            sortActivitiesIfNeeded();
            notifyChanged();
        }
        return add;
    }

    private void pruneExcessiveHistoricalRecordsIfNeeded() {
        int size = this.mHistoricalRecords.size() - this.mHistoryMaxSize;
        if (size > 0) {
            this.mHistoricalRecordsChanged = true;
            for (int i = 0; i < size; i++) {
                HistoricalRecord remove = this.mHistoricalRecords.remove(0);
            }
        }
    }

    public static final class HistoricalRecord {
        public final ComponentName activity;
        public final long time;
        public final float weight;

        public HistoricalRecord(String str, long j, float f) {
            this(ComponentName.unflattenFromString(str), j, f);
        }

        public HistoricalRecord(ComponentName componentName, long j, float f) {
            this.activity = componentName;
            this.time = j;
            this.weight = f;
        }

        public int hashCode() {
            return (((((this.activity == null ? 0 : this.activity.hashCode()) + 31) * 31) + ((int) (this.time ^ (this.time >>> 32)))) * 31) + Float.floatToIntBits(this.weight);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            HistoricalRecord historicalRecord = (HistoricalRecord) obj;
            if (this.activity == null) {
                if (historicalRecord.activity != null) {
                    return false;
                }
            } else if (!this.activity.equals(historicalRecord.activity)) {
                return false;
            }
            return this.time == historicalRecord.time && Float.floatToIntBits(this.weight) == Float.floatToIntBits(historicalRecord.weight);
        }

        public String toString() {
            return "[" + "; activity:" + this.activity + "; time:" + this.time + "; weight:" + new BigDecimal((double) this.weight) + "]";
        }
    }

    public static final class ActivityResolveInfo implements Comparable<ActivityResolveInfo> {
        public final ResolveInfo resolveInfo;
        public float weight;

        public ActivityResolveInfo(ResolveInfo resolveInfo2) {
            this.resolveInfo = resolveInfo2;
        }

        public int hashCode() {
            return Float.floatToIntBits(this.weight) + 31;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && Float.floatToIntBits(this.weight) == Float.floatToIntBits(((ActivityResolveInfo) obj).weight);
        }

        public int compareTo(ActivityResolveInfo activityResolveInfo) {
            return Float.floatToIntBits(activityResolveInfo.weight) - Float.floatToIntBits(this.weight);
        }

        public String toString() {
            return "[" + "resolveInfo:" + this.resolveInfo.toString() + "; weight:" + new BigDecimal((double) this.weight) + "]";
        }
    }

    private static final class DefaultSorter implements ActivitySorter {
        private static final float WEIGHT_DECAY_COEFFICIENT = 0.95f;
        private final Map<ComponentName, ActivityResolveInfo> mPackageNameToActivityMap = new HashMap();

        DefaultSorter() {
        }

        public void sort(Intent intent, List<ActivityResolveInfo> list, List<HistoricalRecord> list2) {
            Map<ComponentName, ActivityResolveInfo> map = this.mPackageNameToActivityMap;
            map.clear();
            int size = list.size();
            for (int i = 0; i < size; i++) {
                ActivityResolveInfo activityResolveInfo = list.get(i);
                activityResolveInfo.weight = 0.0f;
                map.put(new ComponentName(activityResolveInfo.resolveInfo.activityInfo.packageName, activityResolveInfo.resolveInfo.activityInfo.name), activityResolveInfo);
            }
            float f = ActivityChooserModel.DEFAULT_HISTORICAL_RECORD_WEIGHT;
            for (int size2 = list2.size() - 1; size2 >= 0; size2--) {
                HistoricalRecord historicalRecord = list2.get(size2);
                ActivityResolveInfo activityResolveInfo2 = map.get(historicalRecord.activity);
                if (activityResolveInfo2 != null) {
                    activityResolveInfo2.weight += historicalRecord.weight * f;
                    f *= WEIGHT_DECAY_COEFFICIENT;
                }
            }
            Collections.sort(list);
        }
    }

    private void readHistoricalDataImpl() {
        try {
            FileInputStream openFileInput = this.mContext.openFileInput(this.mHistoryFileName);
            try {
                XmlPullParser newPullParser = Xml.newPullParser();
                newPullParser.setInput(openFileInput, "UTF-8");
                int i = 0;
                while (i != 1 && i != 2) {
                    i = newPullParser.next();
                }
                if (TAG_HISTORICAL_RECORDS.equals(newPullParser.getName())) {
                    List<HistoricalRecord> list = this.mHistoricalRecords;
                    list.clear();
                    while (true) {
                        int next = newPullParser.next();
                        if (next == 1) {
                            if (openFileInput == null) {
                                return;
                            }
                        } else if (!(next == 3 || next == 4)) {
                            if (TAG_HISTORICAL_RECORD.equals(newPullParser.getName())) {
                                list.add(new HistoricalRecord(newPullParser.getAttributeValue((String) null, ATTRIBUTE_ACTIVITY), Long.parseLong(newPullParser.getAttributeValue((String) null, ATTRIBUTE_TIME)), Float.parseFloat(newPullParser.getAttributeValue((String) null, ATTRIBUTE_WEIGHT))));
                            } else {
                                throw new XmlPullParserException("Share records file not well-formed.");
                            }
                        }
                    }
                    try {
                        openFileInput.close();
                    } catch (IOException unused) {
                    }
                } else {
                    throw new XmlPullParserException("Share records file does not start with historical-records tag.");
                }
            } catch (XmlPullParserException e) {
                String str = LOG_TAG;
                Log.e(str, "Error reading historical recrod file: " + this.mHistoryFileName, e);
                if (openFileInput == null) {
                }
            } catch (IOException e2) {
                String str2 = LOG_TAG;
                Log.e(str2, "Error reading historical recrod file: " + this.mHistoryFileName, e2);
                if (openFileInput == null) {
                }
            } catch (Throwable th) {
                if (openFileInput != null) {
                    try {
                        openFileInput.close();
                    } catch (IOException unused2) {
                    }
                }
                throw th;
            }
        } catch (FileNotFoundException unused3) {
        }
    }

    private final class PersistHistoryAsyncTask extends AsyncTask<Object, Void, Void> {
        PersistHistoryAsyncTask() {
        }

        /* JADX WARNING: Code restructure failed: missing block: B:10:0x006f, code lost:
            if (r4 != null) goto L_0x0071;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
            r4.close();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x0096, code lost:
            if (r4 == null) goto L_0x00dd;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:23:0x00b8, code lost:
            if (r4 == null) goto L_0x00dd;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:28:0x00da, code lost:
            if (r4 == null) goto L_0x00dd;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Void doInBackground(java.lang.Object... r12) {
            /*
                r11 = this;
                r0 = 0
                r1 = r12[r0]
                java.util.List r1 = (java.util.List) r1
                r2 = 1
                r12 = r12[r2]
                java.lang.String r12 = (java.lang.String) r12
                r3 = 0
                android.support.v7.widget.ActivityChooserModel r4 = android.support.v7.widget.ActivityChooserModel.this     // Catch:{ FileNotFoundException -> 0x00e8 }
                android.content.Context r4 = r4.mContext     // Catch:{ FileNotFoundException -> 0x00e8 }
                java.io.FileOutputStream r4 = r4.openFileOutput(r12, r0)     // Catch:{ FileNotFoundException -> 0x00e8 }
                org.xmlpull.v1.XmlSerializer r12 = android.util.Xml.newSerializer()
                r12.setOutput(r4, r3)     // Catch:{ IllegalArgumentException -> 0x00bb, IllegalStateException -> 0x0099, IOException -> 0x0077 }
                java.lang.String r5 = "UTF-8"
                java.lang.Boolean r6 = java.lang.Boolean.valueOf(r2)     // Catch:{ IllegalArgumentException -> 0x00bb, IllegalStateException -> 0x0099, IOException -> 0x0077 }
                r12.startDocument(r5, r6)     // Catch:{ IllegalArgumentException -> 0x00bb, IllegalStateException -> 0x0099, IOException -> 0x0077 }
                java.lang.String r5 = "historical-records"
                r12.startTag(r3, r5)     // Catch:{ IllegalArgumentException -> 0x00bb, IllegalStateException -> 0x0099, IOException -> 0x0077 }
                int r5 = r1.size()     // Catch:{ IllegalArgumentException -> 0x00bb, IllegalStateException -> 0x0099, IOException -> 0x0077 }
                r6 = 0
            L_0x002d:
                if (r6 >= r5) goto L_0x0063
                java.lang.Object r7 = r1.remove(r0)     // Catch:{ IllegalArgumentException -> 0x00bb, IllegalStateException -> 0x0099, IOException -> 0x0077 }
                android.support.v7.widget.ActivityChooserModel$HistoricalRecord r7 = (android.support.v7.widget.ActivityChooserModel.HistoricalRecord) r7     // Catch:{ IllegalArgumentException -> 0x00bb, IllegalStateException -> 0x0099, IOException -> 0x0077 }
                java.lang.String r8 = "historical-record"
                r12.startTag(r3, r8)     // Catch:{ IllegalArgumentException -> 0x00bb, IllegalStateException -> 0x0099, IOException -> 0x0077 }
                java.lang.String r8 = "activity"
                android.content.ComponentName r9 = r7.activity     // Catch:{ IllegalArgumentException -> 0x00bb, IllegalStateException -> 0x0099, IOException -> 0x0077 }
                java.lang.String r9 = r9.flattenToString()     // Catch:{ IllegalArgumentException -> 0x00bb, IllegalStateException -> 0x0099, IOException -> 0x0077 }
                r12.attribute(r3, r8, r9)     // Catch:{ IllegalArgumentException -> 0x00bb, IllegalStateException -> 0x0099, IOException -> 0x0077 }
                java.lang.String r8 = "time"
                long r9 = r7.time     // Catch:{ IllegalArgumentException -> 0x00bb, IllegalStateException -> 0x0099, IOException -> 0x0077 }
                java.lang.String r9 = java.lang.String.valueOf(r9)     // Catch:{ IllegalArgumentException -> 0x00bb, IllegalStateException -> 0x0099, IOException -> 0x0077 }
                r12.attribute(r3, r8, r9)     // Catch:{ IllegalArgumentException -> 0x00bb, IllegalStateException -> 0x0099, IOException -> 0x0077 }
                java.lang.String r8 = "weight"
                float r7 = r7.weight     // Catch:{ IllegalArgumentException -> 0x00bb, IllegalStateException -> 0x0099, IOException -> 0x0077 }
                java.lang.String r7 = java.lang.String.valueOf(r7)     // Catch:{ IllegalArgumentException -> 0x00bb, IllegalStateException -> 0x0099, IOException -> 0x0077 }
                r12.attribute(r3, r8, r7)     // Catch:{ IllegalArgumentException -> 0x00bb, IllegalStateException -> 0x0099, IOException -> 0x0077 }
                java.lang.String r7 = "historical-record"
                r12.endTag(r3, r7)     // Catch:{ IllegalArgumentException -> 0x00bb, IllegalStateException -> 0x0099, IOException -> 0x0077 }
                int r6 = r6 + 1
                goto L_0x002d
            L_0x0063:
                java.lang.String r0 = "historical-records"
                r12.endTag(r3, r0)     // Catch:{ IllegalArgumentException -> 0x00bb, IllegalStateException -> 0x0099, IOException -> 0x0077 }
                r12.endDocument()     // Catch:{ IllegalArgumentException -> 0x00bb, IllegalStateException -> 0x0099, IOException -> 0x0077 }
                android.support.v7.widget.ActivityChooserModel r12 = android.support.v7.widget.ActivityChooserModel.this
                r12.mCanReadHistoricalData = r2
                if (r4 == 0) goto L_0x00dd
            L_0x0071:
                r4.close()     // Catch:{ IOException -> 0x00dd }
                goto L_0x00dd
            L_0x0075:
                r12 = move-exception
                goto L_0x00de
            L_0x0077:
                r12 = move-exception
                java.lang.String r0 = android.support.v7.widget.ActivityChooserModel.LOG_TAG     // Catch:{ all -> 0x0075 }
                java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0075 }
                r1.<init>()     // Catch:{ all -> 0x0075 }
                java.lang.String r5 = "Error writing historical record file: "
                r1.append(r5)     // Catch:{ all -> 0x0075 }
                android.support.v7.widget.ActivityChooserModel r5 = android.support.v7.widget.ActivityChooserModel.this     // Catch:{ all -> 0x0075 }
                java.lang.String r5 = r5.mHistoryFileName     // Catch:{ all -> 0x0075 }
                r1.append(r5)     // Catch:{ all -> 0x0075 }
                java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0075 }
                android.util.Log.e(r0, r1, r12)     // Catch:{ all -> 0x0075 }
                android.support.v7.widget.ActivityChooserModel r12 = android.support.v7.widget.ActivityChooserModel.this
                r12.mCanReadHistoricalData = r2
                if (r4 == 0) goto L_0x00dd
                goto L_0x0071
            L_0x0099:
                r12 = move-exception
                java.lang.String r0 = android.support.v7.widget.ActivityChooserModel.LOG_TAG     // Catch:{ all -> 0x0075 }
                java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0075 }
                r1.<init>()     // Catch:{ all -> 0x0075 }
                java.lang.String r5 = "Error writing historical record file: "
                r1.append(r5)     // Catch:{ all -> 0x0075 }
                android.support.v7.widget.ActivityChooserModel r5 = android.support.v7.widget.ActivityChooserModel.this     // Catch:{ all -> 0x0075 }
                java.lang.String r5 = r5.mHistoryFileName     // Catch:{ all -> 0x0075 }
                r1.append(r5)     // Catch:{ all -> 0x0075 }
                java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0075 }
                android.util.Log.e(r0, r1, r12)     // Catch:{ all -> 0x0075 }
                android.support.v7.widget.ActivityChooserModel r12 = android.support.v7.widget.ActivityChooserModel.this
                r12.mCanReadHistoricalData = r2
                if (r4 == 0) goto L_0x00dd
                goto L_0x0071
            L_0x00bb:
                r12 = move-exception
                java.lang.String r0 = android.support.v7.widget.ActivityChooserModel.LOG_TAG     // Catch:{ all -> 0x0075 }
                java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0075 }
                r1.<init>()     // Catch:{ all -> 0x0075 }
                java.lang.String r5 = "Error writing historical record file: "
                r1.append(r5)     // Catch:{ all -> 0x0075 }
                android.support.v7.widget.ActivityChooserModel r5 = android.support.v7.widget.ActivityChooserModel.this     // Catch:{ all -> 0x0075 }
                java.lang.String r5 = r5.mHistoryFileName     // Catch:{ all -> 0x0075 }
                r1.append(r5)     // Catch:{ all -> 0x0075 }
                java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0075 }
                android.util.Log.e(r0, r1, r12)     // Catch:{ all -> 0x0075 }
                android.support.v7.widget.ActivityChooserModel r12 = android.support.v7.widget.ActivityChooserModel.this
                r12.mCanReadHistoricalData = r2
                if (r4 == 0) goto L_0x00dd
                goto L_0x0071
            L_0x00dd:
                return r3
            L_0x00de:
                android.support.v7.widget.ActivityChooserModel r0 = android.support.v7.widget.ActivityChooserModel.this
                r0.mCanReadHistoricalData = r2
                if (r4 == 0) goto L_0x00e7
                r4.close()     // Catch:{ IOException -> 0x00e7 }
            L_0x00e7:
                throw r12
            L_0x00e8:
                r0 = move-exception
                java.lang.String r1 = android.support.v7.widget.ActivityChooserModel.LOG_TAG
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                r2.<init>()
                java.lang.String r4 = "Error writing historical record file: "
                r2.append(r4)
                r2.append(r12)
                java.lang.String r12 = r2.toString()
                android.util.Log.e(r1, r12, r0)
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.ActivityChooserModel.PersistHistoryAsyncTask.doInBackground(java.lang.Object[]):java.lang.Void");
        }
    }
}

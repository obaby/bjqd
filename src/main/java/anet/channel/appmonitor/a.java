package anet.channel.appmonitor;

import android.text.TextUtils;
import anet.channel.statist.AlarmObject;
import anet.channel.statist.CountObject;
import anet.channel.statist.Monitor;
import anet.channel.statist.StatObject;
import anet.channel.util.ALog;
import anet.channel.util.StringUtils;
import anetwork.channel.config.NetworkConfigCenter;
import com.alibaba.mtl.appmonitor.AppMonitor;
import com.alibaba.mtl.appmonitor.model.DimensionValueSet;
import com.alibaba.mtl.appmonitor.model.MeasureValueSet;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: Taobao */
public class a implements IAppMonitor {

    /* renamed from: a  reason: collision with root package name */
    private static boolean f143a;

    /* renamed from: b  reason: collision with root package name */
    private static Map<Class<?>, List<Field>> f144b = new ConcurrentHashMap();

    /* renamed from: c  reason: collision with root package name */
    private static Map<Class<?>, List<Field>> f145c = new ConcurrentHashMap();
    private static Map<Field, String> d = new ConcurrentHashMap();
    private static Random e = new Random();
    private static Set<Class<?>> f = Collections.newSetFromMap(new ConcurrentHashMap());

    @Deprecated
    public void register() {
    }

    @Deprecated
    public void register(Class<?> cls) {
    }

    public a() {
        try {
            Class.forName("com.alibaba.mtl.appmonitor.AppMonitor");
            f143a = true;
        } catch (Exception unused) {
            f143a = false;
        }
    }

    public void commitStat(StatObject statObject) {
        if (f143a && statObject != null) {
            Class<?> cls = statObject.getClass();
            Monitor monitor = (Monitor) cls.getAnnotation(Monitor.class);
            if (monitor != null) {
                if (!f.contains(cls)) {
                    a(cls);
                }
                if (statObject.beforeCommit()) {
                    if (monitor.monitorPoint().equals("network")) {
                        int requestStatisticSampleRate = NetworkConfigCenter.getRequestStatisticSampleRate();
                        if (requestStatisticSampleRate > 10000 || requestStatisticSampleRate < 0) {
                            requestStatisticSampleRate = 10000;
                        }
                        if (requestStatisticSampleRate != 10000 && e.nextInt(10000) >= requestStatisticSampleRate) {
                            return;
                        }
                    }
                    try {
                        DimensionValueSet create = DimensionValueSet.create();
                        MeasureValueSet create2 = MeasureValueSet.create();
                        List<Field> list = f144b.get(cls);
                        HashMap hashMap = ALog.isPrintLog(1) ? new HashMap() : null;
                        if (list != null) {
                            for (Field field : list) {
                                Object obj = field.get(statObject);
                                create.setValue(d.get(field), obj == null ? "" : obj.toString());
                            }
                            for (Field field2 : f145c.get(cls)) {
                                Double valueOf = Double.valueOf(field2.getDouble(statObject));
                                create2.setValue(d.get(field2), valueOf.doubleValue());
                                if (hashMap != null) {
                                    hashMap.put(d.get(field2), valueOf);
                                }
                            }
                        }
                        AppMonitor.Stat.commit(monitor.module(), monitor.monitorPoint(), create, create2);
                        if (ALog.isPrintLog(1)) {
                            ALog.d("awcn.DefaultAppMonitor", "commit stat: " + monitor.monitorPoint(), (String) null, "\nDimensions", create.getMap().toString(), "\nMeasures", hashMap.toString());
                        }
                    } catch (Throwable th) {
                        ALog.e("awcn.DefaultAppMonitor", "commit monitor point failed", (String) null, th, new Object[0]);
                    }
                }
            }
        }
    }

    public void commitAlarm(AlarmObject alarmObject) {
        if (f143a && alarmObject != null && !TextUtils.isEmpty(alarmObject.module) && !TextUtils.isEmpty(alarmObject.modulePoint)) {
            if (ALog.isPrintLog(1)) {
                ALog.d("awcn.DefaultAppMonitor", "commit alarm: " + alarmObject, (String) null, new Object[0]);
            }
            if (!alarmObject.isSuccess) {
                AppMonitor.Alarm.commitFail(alarmObject.module, alarmObject.modulePoint, StringUtils.stringNull2Empty(alarmObject.arg), StringUtils.stringNull2Empty(alarmObject.errorCode), StringUtils.stringNull2Empty(alarmObject.errorMsg));
            } else {
                AppMonitor.Alarm.commitSuccess(alarmObject.module, alarmObject.modulePoint, StringUtils.stringNull2Empty(alarmObject.arg));
            }
        }
    }

    public void commitCount(CountObject countObject) {
        if (f143a && countObject != null && !TextUtils.isEmpty(countObject.module) && !TextUtils.isEmpty(countObject.modulePoint)) {
            if (ALog.isPrintLog(2)) {
                ALog.i("awcn.DefaultAppMonitor", "commit count: " + countObject, (String) null, new Object[0]);
            }
            AppMonitor.Counter.commit(countObject.module, countObject.modulePoint, StringUtils.stringNull2Empty(countObject.arg), countObject.value);
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00f8, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a(java.lang.Class<?> r17) {
        /*
            r16 = this;
            r0 = r17
            monitor-enter(r16)
            if (r0 == 0) goto L_0x00f7
            boolean r1 = f143a     // Catch:{ all -> 0x00f4 }
            if (r1 != 0) goto L_0x000b
            goto L_0x00f7
        L_0x000b:
            r1 = 0
            java.util.Set<java.lang.Class<?>> r2 = f     // Catch:{ Exception -> 0x00e7 }
            boolean r2 = r2.contains(r0)     // Catch:{ Exception -> 0x00e7 }
            if (r2 == 0) goto L_0x0016
            monitor-exit(r16)
            return
        L_0x0016:
            java.lang.Class<anet.channel.statist.Monitor> r2 = anet.channel.statist.Monitor.class
            java.lang.annotation.Annotation r2 = r0.getAnnotation(r2)     // Catch:{ Exception -> 0x00e7 }
            anet.channel.statist.Monitor r2 = (anet.channel.statist.Monitor) r2     // Catch:{ Exception -> 0x00e7 }
            if (r2 != 0) goto L_0x0022
            monitor-exit(r16)
            return
        L_0x0022:
            java.lang.reflect.Field[] r3 = r17.getDeclaredFields()     // Catch:{ Exception -> 0x00e7 }
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ Exception -> 0x00e7 }
            r4.<init>()     // Catch:{ Exception -> 0x00e7 }
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch:{ Exception -> 0x00e7 }
            r5.<init>()     // Catch:{ Exception -> 0x00e7 }
            com.alibaba.mtl.appmonitor.model.DimensionSet r6 = com.alibaba.mtl.appmonitor.model.DimensionSet.create()     // Catch:{ Exception -> 0x00e7 }
            com.alibaba.mtl.appmonitor.model.MeasureSet r7 = com.alibaba.mtl.appmonitor.model.MeasureSet.create()     // Catch:{ Exception -> 0x00e7 }
            r8 = 0
        L_0x0039:
            int r9 = r3.length     // Catch:{ Exception -> 0x00e7 }
            if (r8 >= r9) goto L_0x00cc
            r9 = r3[r8]     // Catch:{ Exception -> 0x00e7 }
            java.lang.Class<anet.channel.statist.Dimension> r10 = anet.channel.statist.Dimension.class
            java.lang.annotation.Annotation r10 = r9.getAnnotation(r10)     // Catch:{ Exception -> 0x00e7 }
            anet.channel.statist.Dimension r10 = (anet.channel.statist.Dimension) r10     // Catch:{ Exception -> 0x00e7 }
            r11 = 1
            if (r10 == 0) goto L_0x006d
            r9.setAccessible(r11)     // Catch:{ Exception -> 0x00e7 }
            r4.add(r9)     // Catch:{ Exception -> 0x00e7 }
            java.lang.String r11 = r10.name()     // Catch:{ Exception -> 0x00e7 }
            java.lang.String r12 = ""
            boolean r11 = r11.equals(r12)     // Catch:{ Exception -> 0x00e7 }
            if (r11 == 0) goto L_0x0060
            java.lang.String r10 = r9.getName()     // Catch:{ Exception -> 0x00e7 }
            goto L_0x0064
        L_0x0060:
            java.lang.String r10 = r10.name()     // Catch:{ Exception -> 0x00e7 }
        L_0x0064:
            java.util.Map<java.lang.reflect.Field, java.lang.String> r11 = d     // Catch:{ Exception -> 0x00e7 }
            r11.put(r9, r10)     // Catch:{ Exception -> 0x00e7 }
            r6.addDimension(r10)     // Catch:{ Exception -> 0x00e7 }
            goto L_0x00c8
        L_0x006d:
            java.lang.Class<anet.channel.statist.Measure> r10 = anet.channel.statist.Measure.class
            java.lang.annotation.Annotation r10 = r9.getAnnotation(r10)     // Catch:{ Exception -> 0x00e7 }
            anet.channel.statist.Measure r10 = (anet.channel.statist.Measure) r10     // Catch:{ Exception -> 0x00e7 }
            if (r10 == 0) goto L_0x00c8
            r9.setAccessible(r11)     // Catch:{ Exception -> 0x00e7 }
            r5.add(r9)     // Catch:{ Exception -> 0x00e7 }
            java.lang.String r11 = r10.name()     // Catch:{ Exception -> 0x00e7 }
            java.lang.String r12 = ""
            boolean r11 = r11.equals(r12)     // Catch:{ Exception -> 0x00e7 }
            if (r11 == 0) goto L_0x008e
            java.lang.String r11 = r9.getName()     // Catch:{ Exception -> 0x00e7 }
            goto L_0x0092
        L_0x008e:
            java.lang.String r11 = r10.name()     // Catch:{ Exception -> 0x00e7 }
        L_0x0092:
            java.util.Map<java.lang.reflect.Field, java.lang.String> r12 = d     // Catch:{ Exception -> 0x00e7 }
            r12.put(r9, r11)     // Catch:{ Exception -> 0x00e7 }
            double r12 = r10.max()     // Catch:{ Exception -> 0x00e7 }
            r14 = 9218868437227405311(0x7fefffffffffffff, double:1.7976931348623157E308)
            int r9 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r9 == 0) goto L_0x00c5
            com.alibaba.mtl.appmonitor.model.Measure r9 = new com.alibaba.mtl.appmonitor.model.Measure     // Catch:{ Exception -> 0x00e7 }
            double r12 = r10.constantValue()     // Catch:{ Exception -> 0x00e7 }
            java.lang.Double r12 = java.lang.Double.valueOf(r12)     // Catch:{ Exception -> 0x00e7 }
            double r13 = r10.min()     // Catch:{ Exception -> 0x00e7 }
            java.lang.Double r13 = java.lang.Double.valueOf(r13)     // Catch:{ Exception -> 0x00e7 }
            double r14 = r10.max()     // Catch:{ Exception -> 0x00e7 }
            java.lang.Double r10 = java.lang.Double.valueOf(r14)     // Catch:{ Exception -> 0x00e7 }
            r9.<init>(r11, r12, r13, r10)     // Catch:{ Exception -> 0x00e7 }
            r7.addMeasure(r9)     // Catch:{ Exception -> 0x00e7 }
            goto L_0x00c8
        L_0x00c5:
            r7.addMeasure(r11)     // Catch:{ Exception -> 0x00e7 }
        L_0x00c8:
            int r8 = r8 + 1
            goto L_0x0039
        L_0x00cc:
            java.util.Map<java.lang.Class<?>, java.util.List<java.lang.reflect.Field>> r3 = f144b     // Catch:{ Exception -> 0x00e7 }
            r3.put(r0, r4)     // Catch:{ Exception -> 0x00e7 }
            java.util.Map<java.lang.Class<?>, java.util.List<java.lang.reflect.Field>> r3 = f145c     // Catch:{ Exception -> 0x00e7 }
            r3.put(r0, r5)     // Catch:{ Exception -> 0x00e7 }
            java.lang.String r3 = r2.module()     // Catch:{ Exception -> 0x00e7 }
            java.lang.String r2 = r2.monitorPoint()     // Catch:{ Exception -> 0x00e7 }
            com.alibaba.mtl.appmonitor.AppMonitor.register(r3, r2, r7, r6)     // Catch:{ Exception -> 0x00e7 }
            java.util.Set<java.lang.Class<?>> r2 = f     // Catch:{ Exception -> 0x00e7 }
            r2.add(r0)     // Catch:{ Exception -> 0x00e7 }
            goto L_0x00f2
        L_0x00e7:
            r0 = move-exception
            java.lang.String r2 = "awcn.DefaultAppMonitor"
            java.lang.String r3 = "register fail"
            r4 = 0
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ all -> 0x00f4 }
            anet.channel.util.ALog.e(r2, r3, r4, r0, r1)     // Catch:{ all -> 0x00f4 }
        L_0x00f2:
            monitor-exit(r16)
            return
        L_0x00f4:
            r0 = move-exception
            monitor-exit(r16)
            throw r0
        L_0x00f7:
            monitor-exit(r16)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: anet.channel.appmonitor.a.a(java.lang.Class):void");
    }
}

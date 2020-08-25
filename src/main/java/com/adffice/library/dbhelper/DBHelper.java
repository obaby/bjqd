package com.adffice.library.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.util.Log;
import com.adffice.library.dbhelper.exception.DbException;
import com.adffice.library.dbhelper.sqlite.CursorUtils;
import com.adffice.library.dbhelper.sqlite.SqlBuilder;
import com.adffice.library.dbhelper.sqlite.SqlInfo;
import com.adffice.library.dbhelper.table.TableInfo;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DBHelper {
    private static final String ERROR_INIT_CONFIG_WITH_NULL = "DBHelper config can not be initialized with null";
    private static final String ERROR_NOT_INIT = "DBHelper must be init with config before using";
    private static final String TAG = "com.adffice.library.dbhelper.DBHelper";
    private static volatile DBHelper sInstance;
    private DBConfig mConfig;
    private SQLiteDatabase mDatabase;
    /* access modifiers changed from: private */
    public DbUpgradeListener mUpgradeListener;

    public interface DbUpgradeListener {
        void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2);
    }

    public static DBHelper getInstance() {
        if (sInstance == null) {
            synchronized (DBHelper.class) {
                if (sInstance == null) {
                    sInstance = new DBHelper();
                }
            }
        }
        return sInstance;
    }

    protected DBHelper() {
    }

    public synchronized void init(DBConfig dBConfig) {
        if (dBConfig == null) {
            throw new DbException(ERROR_INIT_CONFIG_WITH_NULL);
        } else if (this.mConfig == null) {
            this.mConfig = dBConfig;
            if (!TextUtils.isEmpty(this.mConfig.dbPath)) {
                this.mDatabase = createDbFileOnSDCard(this.mConfig.dbPath, this.mConfig.dbName);
            } else {
                this.mDatabase = new SQLiteDbHelper(this.mConfig.context, this.mConfig.dbName, (SQLiteDatabase.CursorFactory) null, this.mConfig.dbVersion).getWritableDatabase();
            }
        }
    }

    public boolean isInited() {
        return this.mConfig != null;
    }

    public void setUpgradeListener(DbUpgradeListener dbUpgradeListener) {
        this.mUpgradeListener = dbUpgradeListener;
    }

    private void checkConfiguration() {
        if (this.mConfig == null) {
            throw new DbException(ERROR_NOT_INIT);
        }
    }

    private SQLiteDatabase createDbFileOnSDCard(String str, String str2) {
        File file = new File(str, str2);
        if (file.exists()) {
            return SQLiteDatabase.openOrCreateDatabase(file, (SQLiteDatabase.CursorFactory) null);
        }
        try {
            if (file.createNewFile()) {
                return SQLiteDatabase.openOrCreateDatabase(file, (SQLiteDatabase.CursorFactory) null);
            }
        } catch (IOException e) {
            String str3 = TAG;
            Log.e(str3, "数据库文件创建失败, Error msg:" + e.getMessage());
        }
        return null;
    }

    public SQLiteDatabase getDatabase() {
        return this.mDatabase;
    }

    public long insert(Object obj) {
        return insert(obj, (String) null);
    }

    public long insert(Object obj, String str) {
        checkConfiguration();
        TableInfo tableInfo = TableInfo.get(obj.getClass());
        checkTableExist(tableInfo);
        return this.mDatabase.insert(tableInfo.getTableName(), str, SqlBuilder.buildContentValues(tableInfo, obj));
    }

    public long insert(Class<?> cls, String str, ContentValues contentValues) {
        checkConfiguration();
        TableInfo tableInfo = TableInfo.get(cls);
        checkTableExist(tableInfo);
        return this.mDatabase.insert(tableInfo.getTableName(), str, contentValues);
    }

    public int delete(Class<?> cls, String str, String[] strArr) {
        checkConfiguration();
        TableInfo tableInfo = TableInfo.get(cls);
        checkTableExist(tableInfo);
        return this.mDatabase.delete(tableInfo.getTableName(), str, strArr);
    }

    public int delete(Class<?> cls) {
        return delete(cls, (String) null, (String[]) null);
    }

    public long update(Class<?> cls, ContentValues contentValues, String str, String[] strArr) {
        checkConfiguration();
        TableInfo tableInfo = TableInfo.get(cls);
        checkTableExist(tableInfo);
        return (long) this.mDatabase.update(tableInfo.getTableName(), contentValues, str, strArr);
    }

    public long update(Object obj, String str, String[] strArr) {
        checkConfiguration();
        TableInfo tableInfo = TableInfo.get(obj.getClass());
        checkTableExist(tableInfo);
        return (long) this.mDatabase.update(tableInfo.getTableName(), SqlBuilder.buildContentValues(tableInfo, obj), str, strArr);
    }

    public <T> List<T> query(Class<T> cls) {
        return query(cls, (String) null, (String[]) null);
    }

    public <T> List<T> query(Class<T> cls, String str, String[] strArr) {
        return query(cls, str, strArr, (String) null);
    }

    public <T> List<T> query(Class<T> cls, String str, String[] strArr, String str2) {
        return query(cls, false, str, strArr, (String) null, (String) null, str2, (String) null);
    }

    public <T> List<T> query(Class<T> cls, boolean z, String str, String[] strArr, String str2, String str3, String str4, String str5) {
        checkConfiguration();
        TableInfo tableInfo = TableInfo.get(cls);
        checkTableExist(tableInfo);
        SqlInfo buildQuerySql = SqlBuilder.buildQuerySql(tableInfo, z, (String[]) null, str, strArr, str2, str3, str4, str5);
        Cursor rawQuery = this.mDatabase.rawQuery(buildQuerySql.getSql(), (String[]) buildQuerySql.getBindArgs());
        ArrayList arrayList = new ArrayList();
        if (rawQuery == null) {
            return arrayList;
        }
        while (rawQuery.moveToNext()) {
            try {
                arrayList.add(CursorUtils.getEntity(rawQuery, tableInfo));
            } catch (Exception e) {
                e.printStackTrace();
                return arrayList;
            } finally {
                rawQuery.close();
            }
        }
        return arrayList;
    }

    public void dropTable(Class<?> cls) {
        checkConfiguration();
        exeSqlInfo(SqlBuilder.buildDropTableSql(TableInfo.get(cls)));
    }

    public int dropAllTables() {
        checkConfiguration();
        Cursor rawQuery = this.mDatabase.rawQuery("SELECT name FROM sqlite_master WHERE type ='table' AND name != 'sqlite_sequence'", (String[]) null);
        int i = 0;
        if (rawQuery != null) {
            int i2 = 0;
            while (rawQuery.moveToNext()) {
                SQLiteDatabase sQLiteDatabase = this.mDatabase;
                sQLiteDatabase.execSQL("DROP TABLE " + rawQuery.getString(0));
                i2++;
            }
            i = i2;
        }
        if (rawQuery != null) {
            rawQuery.close();
        }
        return i;
    }

    public void beginTransaction() {
        checkConfiguration();
        this.mDatabase.beginTransaction();
    }

    public void setTransactionSuccessful() {
        checkConfiguration();
        this.mDatabase.setTransactionSuccessful();
    }

    public void endTransaction() {
        checkConfiguration();
        this.mDatabase.endTransaction();
    }

    public void execSQL(String str, Object[] objArr) {
        this.mDatabase.execSQL(str, objArr);
    }

    private void exeSqlInfo(SqlInfo sqlInfo) {
        if (sqlInfo == null) {
            Log.e(TAG, "sqlInfo is null");
            return;
        }
        debugSql(sqlInfo.getSql(), sqlInfo.getBindArgs());
        if (sqlInfo.getBindArgs() != null) {
            this.mDatabase.execSQL(sqlInfo.getSql(), sqlInfo.getBindArgs());
        } else {
            this.mDatabase.execSQL(sqlInfo.getSql());
        }
    }

    private void checkTableExist(TableInfo tableInfo) {
        if (!isTableExist(tableInfo)) {
            exeSqlInfo(SqlBuilder.buildCreateTableSql(tableInfo));
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x0055  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x005b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean isTableExist(com.adffice.library.dbhelper.table.TableInfo r6) {
        /*
            r5 = this;
            boolean r0 = r6.isTableExist()
            r1 = 1
            if (r0 == 0) goto L_0x0008
            return r1
        L_0x0008:
            r0 = 0
            r2 = 0
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x004f }
            java.lang.String r4 = "SELECT COUNT(*) AS c FROM sqlite_master WHERE type ='table' AND name ='"
            r3.<init>(r4)     // Catch:{ Exception -> 0x004f }
            java.lang.String r4 = r6.getTableName()     // Catch:{ Exception -> 0x004f }
            r3.append(r4)     // Catch:{ Exception -> 0x004f }
            java.lang.String r4 = "'"
            r3.append(r4)     // Catch:{ Exception -> 0x004f }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x004f }
            r5.debugSql(r3, r2)     // Catch:{ Exception -> 0x004f }
            android.database.sqlite.SQLiteDatabase r4 = r5.mDatabase     // Catch:{ Exception -> 0x004f }
            android.database.Cursor r3 = r4.rawQuery(r3, r2)     // Catch:{ Exception -> 0x004f }
            if (r3 == 0) goto L_0x0047
            boolean r2 = r3.moveToNext()     // Catch:{ Exception -> 0x0044, all -> 0x0041 }
            if (r2 == 0) goto L_0x0047
            int r2 = r3.getInt(r0)     // Catch:{ Exception -> 0x0044, all -> 0x0041 }
            if (r2 <= 0) goto L_0x0047
            r6.setTableExist(r1)     // Catch:{ Exception -> 0x0044, all -> 0x0041 }
            if (r3 == 0) goto L_0x0040
            r3.close()
        L_0x0040:
            return r1
        L_0x0041:
            r6 = move-exception
            r2 = r3
            goto L_0x0059
        L_0x0044:
            r6 = move-exception
            r2 = r3
            goto L_0x0050
        L_0x0047:
            if (r3 == 0) goto L_0x0058
            r3.close()
            goto L_0x0058
        L_0x004d:
            r6 = move-exception
            goto L_0x0059
        L_0x004f:
            r6 = move-exception
        L_0x0050:
            r6.printStackTrace()     // Catch:{ all -> 0x004d }
            if (r2 == 0) goto L_0x0058
            r2.close()
        L_0x0058:
            return r0
        L_0x0059:
            if (r2 == 0) goto L_0x005e
            r2.close()
        L_0x005e:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adffice.library.dbhelper.DBHelper.isTableExist(com.adffice.library.dbhelper.table.TableInfo):boolean");
    }

    private void debugSql(String str, Object[] objArr) {
        String str2;
        if (this.mConfig.debug) {
            StringBuilder sb = new StringBuilder(">>>>>>  ");
            sb.append(str);
            if (objArr == null) {
                str2 = "";
            } else {
                str2 = " bindArgs:" + Arrays.toString(objArr);
            }
            sb.append(str2);
            Log.d("Debug SQL", sb.toString());
        }
    }

    class SQLiteDbHelper extends SQLiteOpenHelper {
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
        }

        public SQLiteDbHelper(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory, int i) {
            super(context, str, cursorFactory, i);
        }

        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            if (DBHelper.this.mUpgradeListener != null) {
                DBHelper.this.mUpgradeListener.onUpgrade(sQLiteDatabase, i, i2);
                return;
            }
            Cursor rawQuery = sQLiteDatabase.rawQuery("SELECT name FROM sqlite_master WHERE type ='table' AND name != 'sqlite_sequence'", (String[]) null);
            if (rawQuery != null) {
                while (rawQuery.moveToNext()) {
                    sQLiteDatabase.execSQL("DROP TABLE " + rawQuery.getString(0));
                }
            }
            if (rawQuery != null) {
                rawQuery.close();
            }
        }
    }
}

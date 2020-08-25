package com.alipay.sdk.tid;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import com.alipay.sdk.encrypt.b;
import java.lang.ref.WeakReference;

public final class a extends SQLiteOpenHelper {

    /* renamed from: a  reason: collision with root package name */
    private static final String f715a = "msp.db";

    /* renamed from: b  reason: collision with root package name */
    private static final int f716b = 1;

    /* renamed from: c  reason: collision with root package name */
    private WeakReference<Context> f717c;

    public a(Context context) {
        super(context, f715a, (SQLiteDatabase.CursorFactory) null, 1);
        this.f717c = new WeakReference<>(context);
    }

    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("create table if not exists tb_tid (name text primary key, tid text, key_tid text, dt datetime);");
    }

    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("drop table if exists tb_tid");
        onCreate(sQLiteDatabase);
    }

    /* JADX WARNING: Removed duplicated region for block: B:41:0x00a5  */
    /* JADX WARNING: Removed duplicated region for block: B:51:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(java.lang.String r9, java.lang.String r10, java.lang.String r11, java.lang.String r12) {
        /*
            r8 = this;
            r0 = 0
            android.database.sqlite.SQLiteDatabase r7 = r8.getWritableDatabase()     // Catch:{ Exception -> 0x009d, all -> 0x008f }
            boolean r1 = a(r7, r9, r10)     // Catch:{ Exception -> 0x008d, all -> 0x008b }
            if (r1 == 0) goto L_0x0015
            r1 = r8
            r2 = r7
            r3 = r9
            r4 = r10
            r5 = r11
            r6 = r12
            r1.a(r2, r3, r4, r5, r6)     // Catch:{ Exception -> 0x008d, all -> 0x008b }
            goto L_0x007f
        L_0x0015:
            java.lang.String r1 = "insert into tb_tid (name, tid, key_tid, dt) values (?, ?, ?, datetime('now', 'localtime'))"
            java.lang.ref.WeakReference<android.content.Context> r2 = r8.f717c     // Catch:{ Exception -> 0x008d, all -> 0x008b }
            java.lang.Object r2 = r2.get()     // Catch:{ Exception -> 0x008d, all -> 0x008b }
            android.content.Context r2 = (android.content.Context) r2     // Catch:{ Exception -> 0x008d, all -> 0x008b }
            java.lang.String r2 = com.alipay.sdk.util.a.c(r2)     // Catch:{ Exception -> 0x008d, all -> 0x008b }
            r3 = 1
            java.lang.String r11 = com.alipay.sdk.encrypt.b.a(r3, r11, r2)     // Catch:{ Exception -> 0x008d, all -> 0x008b }
            r2 = 3
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ Exception -> 0x008d, all -> 0x008b }
            java.lang.String r9 = c(r9, r10)     // Catch:{ Exception -> 0x008d, all -> 0x008b }
            r10 = 0
            r2[r10] = r9     // Catch:{ Exception -> 0x008d, all -> 0x008b }
            r2[r3] = r11     // Catch:{ Exception -> 0x008d, all -> 0x008b }
            r9 = 2
            r2[r9] = r12     // Catch:{ Exception -> 0x008d, all -> 0x008b }
            r7.execSQL(r1, r2)     // Catch:{ Exception -> 0x008d, all -> 0x008b }
            java.lang.String r9 = "select name from tb_tid where tid!='' order by dt asc"
            android.database.Cursor r9 = r7.rawQuery(r9, r0)     // Catch:{ Exception -> 0x008d, all -> 0x008b }
            int r11 = r9.getCount()     // Catch:{ Exception -> 0x008d, all -> 0x008b }
            r12 = 14
            if (r11 > r12) goto L_0x004c
            r9.close()     // Catch:{ Exception -> 0x008d, all -> 0x008b }
            goto L_0x007f
        L_0x004c:
            int r11 = r9.getCount()     // Catch:{ Exception -> 0x008d, all -> 0x008b }
            int r11 = r11 - r12
            java.lang.String[] r12 = new java.lang.String[r11]     // Catch:{ Exception -> 0x008d, all -> 0x008b }
            boolean r0 = r9.moveToFirst()     // Catch:{ Exception -> 0x008d, all -> 0x008b }
            if (r0 == 0) goto L_0x006a
            r0 = 0
        L_0x005a:
            java.lang.String r1 = r9.getString(r10)     // Catch:{ Exception -> 0x008d, all -> 0x008b }
            r12[r0] = r1     // Catch:{ Exception -> 0x008d, all -> 0x008b }
            int r0 = r0 + 1
            boolean r1 = r9.moveToNext()     // Catch:{ Exception -> 0x008d, all -> 0x008b }
            if (r1 == 0) goto L_0x006a
            if (r11 > r0) goto L_0x005a
        L_0x006a:
            r9.close()     // Catch:{ Exception -> 0x008d, all -> 0x008b }
        L_0x006d:
            if (r10 >= r11) goto L_0x007f
            r9 = r12[r10]     // Catch:{ Exception -> 0x008d, all -> 0x008b }
            boolean r9 = android.text.TextUtils.isEmpty(r9)     // Catch:{ Exception -> 0x008d, all -> 0x008b }
            if (r9 != 0) goto L_0x007c
            r9 = r12[r10]     // Catch:{ Exception -> 0x008d, all -> 0x008b }
            a((android.database.sqlite.SQLiteDatabase) r7, (java.lang.String) r9)     // Catch:{ Exception -> 0x008d, all -> 0x008b }
        L_0x007c:
            int r10 = r10 + 1
            goto L_0x006d
        L_0x007f:
            if (r7 == 0) goto L_0x00a9
            boolean r9 = r7.isOpen()
            if (r9 == 0) goto L_0x00a9
            r7.close()
            return
        L_0x008b:
            r9 = move-exception
            goto L_0x0091
        L_0x008d:
            r0 = r7
            goto L_0x009d
        L_0x008f:
            r9 = move-exception
            r7 = r0
        L_0x0091:
            if (r7 == 0) goto L_0x009c
            boolean r10 = r7.isOpen()
            if (r10 == 0) goto L_0x009c
            r7.close()
        L_0x009c:
            throw r9
        L_0x009d:
            if (r0 == 0) goto L_0x00a9
            boolean r9 = r0.isOpen()
            if (r9 == 0) goto L_0x00a9
            r0.close()
            return
        L_0x00a9:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.tid.a.a(java.lang.String, java.lang.String, java.lang.String, java.lang.String):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x003d  */
    /* JADX WARNING: Removed duplicated region for block: B:30:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void d(java.lang.String r9, java.lang.String r10) {
        /*
            r8 = this;
            r0 = 0
            android.database.sqlite.SQLiteDatabase r7 = r8.getWritableDatabase()     // Catch:{ Exception -> 0x0035, all -> 0x0027 }
            java.lang.String r5 = ""
            java.lang.String r6 = ""
            r1 = r8
            r2 = r7
            r3 = r9
            r4 = r10
            r1.a(r2, r3, r4, r5, r6)     // Catch:{ Exception -> 0x0025, all -> 0x0023 }
            java.lang.String r9 = c(r9, r10)     // Catch:{ Exception -> 0x0025, all -> 0x0023 }
            a((android.database.sqlite.SQLiteDatabase) r7, (java.lang.String) r9)     // Catch:{ Exception -> 0x0025, all -> 0x0023 }
            if (r7 == 0) goto L_0x0041
            boolean r9 = r7.isOpen()
            if (r9 == 0) goto L_0x0041
            r7.close()
            return
        L_0x0023:
            r9 = move-exception
            goto L_0x0029
        L_0x0025:
            r0 = r7
            goto L_0x0035
        L_0x0027:
            r9 = move-exception
            r7 = r0
        L_0x0029:
            if (r7 == 0) goto L_0x0034
            boolean r10 = r7.isOpen()
            if (r10 == 0) goto L_0x0034
            r7.close()
        L_0x0034:
            throw r9
        L_0x0035:
            if (r0 == 0) goto L_0x0041
            boolean r9 = r0.isOpen()
            if (r9 == 0) goto L_0x0041
            r0.close()
            return
        L_0x0041:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.tid.a.d(java.lang.String, java.lang.String):void");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: java.lang.String} */
    /* JADX WARNING: type inference failed for: r1v0 */
    /* JADX WARNING: type inference failed for: r1v3, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r1v4 */
    /* JADX WARNING: type inference failed for: r1v5 */
    /* JADX WARNING: type inference failed for: r1v6 */
    /* JADX WARNING: type inference failed for: r1v7 */
    /* JADX WARNING: type inference failed for: r1v8 */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002b, code lost:
        if (r2.isOpen() != false) goto L_0x002d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002d, code lost:
        r2.close();
        r1 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x005a, code lost:
        if (r2.isOpen() != false) goto L_0x002d;
     */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x003e  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0051  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0056  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0063  */
    /* JADX WARNING: Removed duplicated region for block: B:43:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String a(java.lang.String r5, java.lang.String r6) {
        /*
            r4 = this;
            java.lang.String r0 = "select tid from tb_tid where name=?"
            r1 = 0
            android.database.sqlite.SQLiteDatabase r2 = r4.getReadableDatabase()     // Catch:{ Exception -> 0x004d, all -> 0x003a }
            r3 = 1
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch:{ Exception -> 0x0038, all -> 0x0036 }
            java.lang.String r5 = c(r5, r6)     // Catch:{ Exception -> 0x0038, all -> 0x0036 }
            r6 = 0
            r3[r6] = r5     // Catch:{ Exception -> 0x0038, all -> 0x0036 }
            android.database.Cursor r5 = r2.rawQuery(r0, r3)     // Catch:{ Exception -> 0x0038, all -> 0x0036 }
            boolean r0 = r5.moveToFirst()     // Catch:{ Exception -> 0x0034, all -> 0x0031 }
            if (r0 == 0) goto L_0x0020
            java.lang.String r6 = r5.getString(r6)     // Catch:{ Exception -> 0x0034, all -> 0x0031 }
            r1 = r6
        L_0x0020:
            if (r5 == 0) goto L_0x0025
            r5.close()
        L_0x0025:
            if (r2 == 0) goto L_0x005d
            boolean r5 = r2.isOpen()
            if (r5 == 0) goto L_0x005d
        L_0x002d:
            r2.close()
            goto L_0x005d
        L_0x0031:
            r6 = move-exception
            r1 = r5
            goto L_0x003c
        L_0x0034:
            goto L_0x004f
        L_0x0036:
            r6 = move-exception
            goto L_0x003c
        L_0x0038:
            r5 = r1
            goto L_0x004f
        L_0x003a:
            r6 = move-exception
            r2 = r1
        L_0x003c:
            if (r1 == 0) goto L_0x0041
            r1.close()
        L_0x0041:
            if (r2 == 0) goto L_0x004c
            boolean r5 = r2.isOpen()
            if (r5 == 0) goto L_0x004c
            r2.close()
        L_0x004c:
            throw r6
        L_0x004d:
            r5 = r1
            r2 = r5
        L_0x004f:
            if (r5 == 0) goto L_0x0054
            r5.close()
        L_0x0054:
            if (r2 == 0) goto L_0x005d
            boolean r5 = r2.isOpen()
            if (r5 == 0) goto L_0x005d
            goto L_0x002d
        L_0x005d:
            boolean r5 = android.text.TextUtils.isEmpty(r1)
            if (r5 != 0) goto L_0x0074
            java.lang.ref.WeakReference<android.content.Context> r5 = r4.f717c
            java.lang.Object r5 = r5.get()
            android.content.Context r5 = (android.content.Context) r5
            java.lang.String r5 = com.alipay.sdk.util.a.c(r5)
            r6 = 2
            java.lang.String r1 = com.alipay.sdk.encrypt.b.a(r6, r1, r5)
        L_0x0074:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.tid.a.a(java.lang.String, java.lang.String):java.lang.String");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0041, code lost:
        if (r4.isOpen() != false) goto L_0x0043;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0043, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0070, code lost:
        if (r4.isOpen() != false) goto L_0x0043;
     */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0055  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x006c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private long e(java.lang.String r7, java.lang.String r8) {
        /*
            r6 = this;
            java.lang.String r0 = "select dt from tb_tid where name=?"
            r1 = 0
            r2 = 0
            android.database.sqlite.SQLiteDatabase r4 = r6.getReadableDatabase()     // Catch:{ Exception -> 0x0064, all -> 0x0050 }
            r5 = 1
            java.lang.String[] r5 = new java.lang.String[r5]     // Catch:{ Exception -> 0x004e, all -> 0x004b }
            java.lang.String r7 = c(r7, r8)     // Catch:{ Exception -> 0x004e, all -> 0x004b }
            r8 = 0
            r5[r8] = r7     // Catch:{ Exception -> 0x004e, all -> 0x004b }
            android.database.Cursor r7 = r4.rawQuery(r0, r5)     // Catch:{ Exception -> 0x004e, all -> 0x004b }
            boolean r0 = r7.moveToFirst()     // Catch:{ Exception -> 0x0049, all -> 0x0047 }
            if (r0 == 0) goto L_0x0036
            java.text.SimpleDateFormat r0 = new java.text.SimpleDateFormat     // Catch:{ Exception -> 0x0049, all -> 0x0047 }
            java.lang.String r1 = "yyyy-MM-dd HH:mm:ss"
            java.util.Locale r5 = java.util.Locale.getDefault()     // Catch:{ Exception -> 0x0049, all -> 0x0047 }
            r0.<init>(r1, r5)     // Catch:{ Exception -> 0x0049, all -> 0x0047 }
            java.lang.String r8 = r7.getString(r8)     // Catch:{ Exception -> 0x0049, all -> 0x0047 }
            java.util.Date r8 = r0.parse(r8)     // Catch:{ Exception -> 0x0049, all -> 0x0047 }
            long r0 = r8.getTime()     // Catch:{ Exception -> 0x0049, all -> 0x0047 }
            r2 = r0
        L_0x0036:
            if (r7 == 0) goto L_0x003b
            r7.close()
        L_0x003b:
            if (r4 == 0) goto L_0x0073
            boolean r7 = r4.isOpen()
            if (r7 == 0) goto L_0x0073
        L_0x0043:
            r4.close()
            goto L_0x0073
        L_0x0047:
            r8 = move-exception
            goto L_0x0053
        L_0x0049:
            r1 = r7
            goto L_0x0065
        L_0x004b:
            r8 = move-exception
            r7 = r1
            goto L_0x0053
        L_0x004e:
            goto L_0x0065
        L_0x0050:
            r8 = move-exception
            r7 = r1
            r4 = r7
        L_0x0053:
            if (r7 == 0) goto L_0x0058
            r7.close()
        L_0x0058:
            if (r4 == 0) goto L_0x0063
            boolean r7 = r4.isOpen()
            if (r7 == 0) goto L_0x0063
            r4.close()
        L_0x0063:
            throw r8
        L_0x0064:
            r4 = r1
        L_0x0065:
            if (r1 == 0) goto L_0x006a
            r1.close()
        L_0x006a:
            if (r4 == 0) goto L_0x0073
            boolean r7 = r4.isOpen()
            if (r7 == 0) goto L_0x0073
            goto L_0x0043
        L_0x0073:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.tid.a.e(java.lang.String, java.lang.String):long");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0041, code lost:
        if (r2.isOpen() != false) goto L_0x006f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x006d, code lost:
        if (r2.isOpen() != false) goto L_0x006f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x006f, code lost:
        r2.close();
     */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0052  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0064  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0069  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.List<java.lang.String> a() {
        /*
            r6 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 0
            android.database.sqlite.SQLiteDatabase r2 = r6.getReadableDatabase()     // Catch:{ Exception -> 0x0061, all -> 0x004d }
            java.lang.String r3 = "select tid from tb_tid"
            android.database.Cursor r3 = r2.rawQuery(r3, r1)     // Catch:{ Exception -> 0x004b, all -> 0x0048 }
        L_0x0010:
            boolean r1 = r3.moveToNext()     // Catch:{ Exception -> 0x0046, all -> 0x0044 }
            if (r1 == 0) goto L_0x0036
            r1 = 0
            java.lang.String r1 = r3.getString(r1)     // Catch:{ Exception -> 0x0046, all -> 0x0044 }
            boolean r4 = android.text.TextUtils.isEmpty(r1)     // Catch:{ Exception -> 0x0046, all -> 0x0044 }
            if (r4 != 0) goto L_0x0010
            java.lang.ref.WeakReference<android.content.Context> r4 = r6.f717c     // Catch:{ Exception -> 0x0046, all -> 0x0044 }
            java.lang.Object r4 = r4.get()     // Catch:{ Exception -> 0x0046, all -> 0x0044 }
            android.content.Context r4 = (android.content.Context) r4     // Catch:{ Exception -> 0x0046, all -> 0x0044 }
            java.lang.String r4 = com.alipay.sdk.util.a.c(r4)     // Catch:{ Exception -> 0x0046, all -> 0x0044 }
            r5 = 2
            java.lang.String r1 = com.alipay.sdk.encrypt.b.a(r5, r1, r4)     // Catch:{ Exception -> 0x0046, all -> 0x0044 }
            r0.add(r1)     // Catch:{ Exception -> 0x0046, all -> 0x0044 }
            goto L_0x0010
        L_0x0036:
            if (r3 == 0) goto L_0x003b
            r3.close()
        L_0x003b:
            if (r2 == 0) goto L_0x0072
            boolean r1 = r2.isOpen()
            if (r1 == 0) goto L_0x0072
            goto L_0x006f
        L_0x0044:
            r0 = move-exception
            goto L_0x0050
        L_0x0046:
            r1 = r3
            goto L_0x0062
        L_0x0048:
            r0 = move-exception
            r3 = r1
            goto L_0x0050
        L_0x004b:
            goto L_0x0062
        L_0x004d:
            r0 = move-exception
            r2 = r1
            r3 = r2
        L_0x0050:
            if (r3 == 0) goto L_0x0055
            r3.close()
        L_0x0055:
            if (r2 == 0) goto L_0x0060
            boolean r1 = r2.isOpen()
            if (r1 == 0) goto L_0x0060
            r2.close()
        L_0x0060:
            throw r0
        L_0x0061:
            r2 = r1
        L_0x0062:
            if (r1 == 0) goto L_0x0067
            r1.close()
        L_0x0067:
            if (r2 == 0) goto L_0x0072
            boolean r1 = r2.isOpen()
            if (r1 == 0) goto L_0x0072
        L_0x006f:
            r2.close()
        L_0x0072:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.tid.a.a():java.util.List");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: java.lang.String} */
    /* JADX WARNING: type inference failed for: r1v0 */
    /* JADX WARNING: type inference failed for: r1v2, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r1v3 */
    /* JADX WARNING: type inference failed for: r1v4 */
    /* JADX WARNING: type inference failed for: r1v5 */
    /* JADX WARNING: type inference failed for: r1v6 */
    /* JADX WARNING: type inference failed for: r1v7 */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002b, code lost:
        if (r2.isOpen() != false) goto L_0x002d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002d, code lost:
        r2.close();
        r1 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x005a, code lost:
        if (r2.isOpen() != false) goto L_0x002d;
     */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x003e  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0051  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0056  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String b(java.lang.String r5, java.lang.String r6) {
        /*
            r4 = this;
            java.lang.String r0 = "select key_tid from tb_tid where name=?"
            r1 = 0
            android.database.sqlite.SQLiteDatabase r2 = r4.getReadableDatabase()     // Catch:{ Exception -> 0x004d, all -> 0x003a }
            r3 = 1
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch:{ Exception -> 0x0038, all -> 0x0036 }
            java.lang.String r5 = c(r5, r6)     // Catch:{ Exception -> 0x0038, all -> 0x0036 }
            r6 = 0
            r3[r6] = r5     // Catch:{ Exception -> 0x0038, all -> 0x0036 }
            android.database.Cursor r5 = r2.rawQuery(r0, r3)     // Catch:{ Exception -> 0x0038, all -> 0x0036 }
            boolean r0 = r5.moveToFirst()     // Catch:{ Exception -> 0x0034, all -> 0x0031 }
            if (r0 == 0) goto L_0x0020
            java.lang.String r6 = r5.getString(r6)     // Catch:{ Exception -> 0x0034, all -> 0x0031 }
            r1 = r6
        L_0x0020:
            if (r5 == 0) goto L_0x0025
            r5.close()
        L_0x0025:
            if (r2 == 0) goto L_0x005d
            boolean r5 = r2.isOpen()
            if (r5 == 0) goto L_0x005d
        L_0x002d:
            r2.close()
            goto L_0x005d
        L_0x0031:
            r6 = move-exception
            r1 = r5
            goto L_0x003c
        L_0x0034:
            goto L_0x004f
        L_0x0036:
            r6 = move-exception
            goto L_0x003c
        L_0x0038:
            r5 = r1
            goto L_0x004f
        L_0x003a:
            r6 = move-exception
            r2 = r1
        L_0x003c:
            if (r1 == 0) goto L_0x0041
            r1.close()
        L_0x0041:
            if (r2 == 0) goto L_0x004c
            boolean r5 = r2.isOpen()
            if (r5 == 0) goto L_0x004c
            r2.close()
        L_0x004c:
            throw r6
        L_0x004d:
            r5 = r1
            r2 = r5
        L_0x004f:
            if (r5 == 0) goto L_0x0054
            r5.close()
        L_0x0054:
            if (r2 == 0) goto L_0x005d
            boolean r5 = r2.isOpen()
            if (r5 == 0) goto L_0x005d
            goto L_0x002d
        L_0x005d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.tid.a.b(java.lang.String, java.lang.String):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0029  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0030  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0036 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0037 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean a(android.database.sqlite.SQLiteDatabase r5, java.lang.String r6, java.lang.String r7) {
        /*
            java.lang.String r0 = "select count(*) from tb_tid where name=?"
            r1 = 1
            r2 = 0
            r3 = 0
            java.lang.String[] r4 = new java.lang.String[r1]     // Catch:{ Exception -> 0x002d, all -> 0x0026 }
            java.lang.String r6 = c(r6, r7)     // Catch:{ Exception -> 0x002d, all -> 0x0026 }
            r4[r2] = r6     // Catch:{ Exception -> 0x002d, all -> 0x0026 }
            android.database.Cursor r5 = r5.rawQuery(r0, r4)     // Catch:{ Exception -> 0x002d, all -> 0x0026 }
            boolean r6 = r5.moveToFirst()     // Catch:{ Exception -> 0x002e, all -> 0x0023 }
            if (r6 == 0) goto L_0x001c
            int r6 = r5.getInt(r2)     // Catch:{ Exception -> 0x002e, all -> 0x0023 }
            goto L_0x001d
        L_0x001c:
            r6 = 0
        L_0x001d:
            if (r5 == 0) goto L_0x0034
            r5.close()
            goto L_0x0034
        L_0x0023:
            r6 = move-exception
            r3 = r5
            goto L_0x0027
        L_0x0026:
            r6 = move-exception
        L_0x0027:
            if (r3 == 0) goto L_0x002c
            r3.close()
        L_0x002c:
            throw r6
        L_0x002d:
            r5 = r3
        L_0x002e:
            if (r5 == 0) goto L_0x0033
            r5.close()
        L_0x0033:
            r6 = 0
        L_0x0034:
            if (r6 <= 0) goto L_0x0037
            return r1
        L_0x0037:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.tid.a.a(android.database.sqlite.SQLiteDatabase, java.lang.String, java.lang.String):boolean");
    }

    static String c(String str, String str2) {
        return str + str2;
    }

    private void b(SQLiteDatabase sQLiteDatabase, String str, String str2, String str3, String str4) {
        String a2 = b.a(1, str3, com.alipay.sdk.util.a.c((Context) this.f717c.get()));
        String c2 = c(str, str2);
        sQLiteDatabase.execSQL("insert into tb_tid (name, tid, key_tid, dt) values (?, ?, ?, datetime('now', 'localtime'))", new Object[]{c2, a2, str4});
        Cursor rawQuery = sQLiteDatabase.rawQuery("select name from tb_tid where tid!='' order by dt asc", (String[]) null);
        if (rawQuery.getCount() <= 14) {
            rawQuery.close();
            return;
        }
        int count = rawQuery.getCount() - 14;
        String[] strArr = new String[count];
        if (rawQuery.moveToFirst()) {
            int i = 0;
            do {
                strArr[i] = rawQuery.getString(0);
                i++;
                if (!rawQuery.moveToNext()) {
                    break;
                }
            } while (count > i);
        }
        rawQuery.close();
        for (int i2 = 0; i2 < count; i2++) {
            if (!TextUtils.isEmpty(strArr[i2])) {
                a(sQLiteDatabase, strArr[i2]);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void a(SQLiteDatabase sQLiteDatabase, String str, String str2, String str3, String str4) {
        sQLiteDatabase.execSQL("update tb_tid set tid=?, key_tid=?, dt=datetime('now', 'localtime') where name=?", new Object[]{b.a(1, str3, com.alipay.sdk.util.a.c((Context) this.f717c.get())), str4, c(str, str2)});
    }

    static void a(SQLiteDatabase sQLiteDatabase, String str) {
        try {
            sQLiteDatabase.delete("tb_tid", "name=?", new String[]{str});
        } catch (Exception unused) {
        }
    }

    private static void a(SQLiteDatabase sQLiteDatabase) {
        Cursor rawQuery = sQLiteDatabase.rawQuery("select name from tb_tid where tid!='' order by dt asc", (String[]) null);
        if (rawQuery.getCount() <= 14) {
            rawQuery.close();
            return;
        }
        int count = rawQuery.getCount() - 14;
        String[] strArr = new String[count];
        if (rawQuery.moveToFirst()) {
            int i = 0;
            do {
                strArr[i] = rawQuery.getString(0);
                i++;
                if (!rawQuery.moveToNext()) {
                    break;
                }
            } while (count > i);
        }
        rawQuery.close();
        for (int i2 = 0; i2 < count; i2++) {
            if (!TextUtils.isEmpty(strArr[i2])) {
                a(sQLiteDatabase, strArr[i2]);
            }
        }
    }
}

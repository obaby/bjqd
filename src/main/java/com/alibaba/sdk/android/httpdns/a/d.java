package com.alibaba.sdk.android.httpdns.a;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.Iterator;
import java.util.List;

class d extends SQLiteOpenHelper {

    /* renamed from: a  reason: collision with root package name */
    private static final Object f459a = new Object();

    d(Context context) {
        super(context, "aliclound_httpdns.db", (SQLiteDatabase.CursorFactory) null, 1);
    }

    private long a(SQLiteDatabase sQLiteDatabase, g gVar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("host_id", Long.valueOf(gVar.g));
        contentValues.put("ip", gVar.m);
        contentValues.put("ttl", gVar.n);
        try {
            return sQLiteDatabase.insert("ip", (String) null, contentValues);
        } catch (Exception unused) {
            return 0;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x008f, code lost:
        if (r3 != null) goto L_0x00ae;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00ac, code lost:
        if (r3 != null) goto L_0x00ae;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00ae, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00b1, code lost:
        return r0;
     */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x009c  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00a1  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00a9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.List<com.alibaba.sdk.android.httpdns.a.g> a(long r7) {
        /*
            r6 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "SELECT * FROM "
            r1.append(r2)
            java.lang.String r2 = "ip"
            r1.append(r2)
            java.lang.String r2 = " WHERE "
            r1.append(r2)
            java.lang.String r2 = "host_id"
            r1.append(r2)
            java.lang.String r2 = " =? ;"
            r1.append(r2)
            r2 = 0
            android.database.sqlite.SQLiteDatabase r3 = r6.getWritableDatabase()     // Catch:{ Exception -> 0x00a5, all -> 0x0097 }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x0095, all -> 0x0092 }
            r4 = 1
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch:{ Exception -> 0x0095, all -> 0x0092 }
            r5 = 0
            java.lang.String r7 = java.lang.String.valueOf(r7)     // Catch:{ Exception -> 0x0095, all -> 0x0092 }
            r4[r5] = r7     // Catch:{ Exception -> 0x0095, all -> 0x0092 }
            android.database.Cursor r7 = r3.rawQuery(r1, r4)     // Catch:{ Exception -> 0x0095, all -> 0x0092 }
            if (r7 == 0) goto L_0x008a
            int r8 = r7.getCount()     // Catch:{ Exception -> 0x0088, all -> 0x0086 }
            if (r8 <= 0) goto L_0x008a
            r7.moveToFirst()     // Catch:{ Exception -> 0x0088, all -> 0x0086 }
        L_0x0045:
            com.alibaba.sdk.android.httpdns.a.g r8 = new com.alibaba.sdk.android.httpdns.a.g     // Catch:{ Exception -> 0x0088, all -> 0x0086 }
            r8.<init>()     // Catch:{ Exception -> 0x0088, all -> 0x0086 }
            java.lang.String r1 = "id"
            int r1 = r7.getColumnIndex(r1)     // Catch:{ Exception -> 0x0088, all -> 0x0086 }
            int r1 = r7.getInt(r1)     // Catch:{ Exception -> 0x0088, all -> 0x0086 }
            long r1 = (long) r1     // Catch:{ Exception -> 0x0088, all -> 0x0086 }
            r8.id = r1     // Catch:{ Exception -> 0x0088, all -> 0x0086 }
            java.lang.String r1 = "host_id"
            int r1 = r7.getColumnIndex(r1)     // Catch:{ Exception -> 0x0088, all -> 0x0086 }
            int r1 = r7.getInt(r1)     // Catch:{ Exception -> 0x0088, all -> 0x0086 }
            long r1 = (long) r1     // Catch:{ Exception -> 0x0088, all -> 0x0086 }
            r8.g = r1     // Catch:{ Exception -> 0x0088, all -> 0x0086 }
            java.lang.String r1 = "ip"
            int r1 = r7.getColumnIndex(r1)     // Catch:{ Exception -> 0x0088, all -> 0x0086 }
            java.lang.String r1 = r7.getString(r1)     // Catch:{ Exception -> 0x0088, all -> 0x0086 }
            r8.m = r1     // Catch:{ Exception -> 0x0088, all -> 0x0086 }
            java.lang.String r1 = "ttl"
            int r1 = r7.getColumnIndex(r1)     // Catch:{ Exception -> 0x0088, all -> 0x0086 }
            java.lang.String r1 = r7.getString(r1)     // Catch:{ Exception -> 0x0088, all -> 0x0086 }
            r8.n = r1     // Catch:{ Exception -> 0x0088, all -> 0x0086 }
            r0.add(r8)     // Catch:{ Exception -> 0x0088, all -> 0x0086 }
            boolean r8 = r7.moveToNext()     // Catch:{ Exception -> 0x0088, all -> 0x0086 }
            if (r8 != 0) goto L_0x0045
            goto L_0x008a
        L_0x0086:
            r8 = move-exception
            goto L_0x009a
        L_0x0088:
            goto L_0x00a7
        L_0x008a:
            if (r7 == 0) goto L_0x008f
            r7.close()
        L_0x008f:
            if (r3 == 0) goto L_0x00b1
            goto L_0x00ae
        L_0x0092:
            r8 = move-exception
            r7 = r2
            goto L_0x009a
        L_0x0095:
            r7 = r2
            goto L_0x00a7
        L_0x0097:
            r8 = move-exception
            r7 = r2
            r3 = r7
        L_0x009a:
            if (r7 == 0) goto L_0x009f
            r7.close()
        L_0x009f:
            if (r3 == 0) goto L_0x00a4
            r3.close()
        L_0x00a4:
            throw r8
        L_0x00a5:
            r7 = r2
            r3 = r7
        L_0x00a7:
            if (r7 == 0) goto L_0x00ac
            r7.close()
        L_0x00ac:
            if (r3 == 0) goto L_0x00b1
        L_0x00ae:
            r3.close()
        L_0x00b1:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.sdk.android.httpdns.a.d.a(long):java.util.List");
    }

    private List<g> a(e eVar) {
        return a(eVar.id);
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0021  */
    /* JADX WARNING: Removed duplicated region for block: B:20:? A[RETURN, SYNTHETIC] */
    /* renamed from: a  reason: collision with other method in class */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m1a(long r6) {
        /*
            r5 = this;
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r5.getWritableDatabase()     // Catch:{ Exception -> 0x0025, all -> 0x001d }
            java.lang.String r0 = "host"
            java.lang.String r2 = "id = ?"
            r3 = 1
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch:{ Exception -> 0x001b, all -> 0x0019 }
            r4 = 0
            java.lang.String r6 = java.lang.String.valueOf(r6)     // Catch:{ Exception -> 0x001b, all -> 0x0019 }
            r3[r4] = r6     // Catch:{ Exception -> 0x001b, all -> 0x0019 }
            r1.delete(r0, r2, r3)     // Catch:{ Exception -> 0x001b, all -> 0x0019 }
            if (r1 == 0) goto L_0x002b
            goto L_0x0028
        L_0x0019:
            r6 = move-exception
            goto L_0x001f
        L_0x001b:
            goto L_0x0026
        L_0x001d:
            r6 = move-exception
            r1 = r0
        L_0x001f:
            if (r1 == 0) goto L_0x0024
            r1.close()
        L_0x0024:
            throw r6
        L_0x0025:
            r1 = r0
        L_0x0026:
            if (r1 == 0) goto L_0x002b
        L_0x0028:
            r1.close()
        L_0x002b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.sdk.android.httpdns.a.d.m1a(long):void");
    }

    private void a(g gVar) {
        b(gVar.id);
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0021  */
    /* JADX WARNING: Removed duplicated region for block: B:20:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void b(long r6) {
        /*
            r5 = this;
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r5.getWritableDatabase()     // Catch:{ Exception -> 0x0025, all -> 0x001d }
            java.lang.String r0 = "ip"
            java.lang.String r2 = "id = ?"
            r3 = 1
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch:{ Exception -> 0x001b, all -> 0x0019 }
            r4 = 0
            java.lang.String r6 = java.lang.String.valueOf(r6)     // Catch:{ Exception -> 0x001b, all -> 0x0019 }
            r3[r4] = r6     // Catch:{ Exception -> 0x001b, all -> 0x0019 }
            r1.delete(r0, r2, r3)     // Catch:{ Exception -> 0x001b, all -> 0x0019 }
            if (r1 == 0) goto L_0x002b
            goto L_0x0028
        L_0x0019:
            r6 = move-exception
            goto L_0x001f
        L_0x001b:
            goto L_0x0026
        L_0x001d:
            r6 = move-exception
            r1 = r0
        L_0x001f:
            if (r1 == 0) goto L_0x0024
            r1.close()
        L_0x0024:
            throw r6
        L_0x0025:
            r1 = r0
        L_0x0026:
            if (r1 == 0) goto L_0x002b
        L_0x0028:
            r1.close()
        L_0x002b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.sdk.android.httpdns.a.d.b(long):void");
    }

    private void c(e eVar) {
        a(eVar.id);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0063, code lost:
        return r1;
     */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x006c A[Catch:{ Exception -> 0x0066, all -> 0x0064 }] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0076 A[Catch:{ Exception -> 0x0066, all -> 0x0064 }] */
    /* renamed from: a  reason: collision with other method in class */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long m2a(com.alibaba.sdk.android.httpdns.a.e r8) {
        /*
            r7 = this;
            java.lang.Object r0 = f459a
            monitor-enter(r0)
            java.lang.String r1 = r8.k     // Catch:{ all -> 0x0080 }
            java.lang.String r2 = r8.j     // Catch:{ all -> 0x0080 }
            r7.b(r1, r2)     // Catch:{ all -> 0x0080 }
            android.content.ContentValues r1 = new android.content.ContentValues     // Catch:{ all -> 0x0080 }
            r1.<init>()     // Catch:{ all -> 0x0080 }
            r2 = 0
            android.database.sqlite.SQLiteDatabase r3 = r7.getWritableDatabase()     // Catch:{ Exception -> 0x0073, all -> 0x0068 }
            r3.beginTransaction()     // Catch:{ Exception -> 0x0066, all -> 0x0064 }
            java.lang.String r4 = "host"
            java.lang.String r5 = r8.j     // Catch:{ Exception -> 0x0066, all -> 0x0064 }
            r1.put(r4, r5)     // Catch:{ Exception -> 0x0066, all -> 0x0064 }
            java.lang.String r4 = "sp"
            java.lang.String r5 = r8.k     // Catch:{ Exception -> 0x0066, all -> 0x0064 }
            r1.put(r4, r5)     // Catch:{ Exception -> 0x0066, all -> 0x0064 }
            java.lang.String r4 = "time"
            java.lang.String r5 = r8.l     // Catch:{ Exception -> 0x0066, all -> 0x0064 }
            java.lang.String r5 = com.alibaba.sdk.android.httpdns.a.c.c(r5)     // Catch:{ Exception -> 0x0066, all -> 0x0064 }
            r1.put(r4, r5)     // Catch:{ Exception -> 0x0066, all -> 0x0064 }
            java.lang.String r4 = "host"
            long r1 = r3.insert(r4, r2, r1)     // Catch:{ Exception -> 0x0066, all -> 0x0064 }
            r8.id = r1     // Catch:{ Exception -> 0x0066, all -> 0x0064 }
            java.util.ArrayList<com.alibaba.sdk.android.httpdns.a.g> r4 = r8.f460a     // Catch:{ Exception -> 0x0066, all -> 0x0064 }
            if (r4 == 0) goto L_0x0057
            java.util.ArrayList<com.alibaba.sdk.android.httpdns.a.g> r8 = r8.f460a     // Catch:{ Exception -> 0x0066, all -> 0x0064 }
            java.util.Iterator r8 = r8.iterator()     // Catch:{ Exception -> 0x0066, all -> 0x0064 }
        L_0x0042:
            boolean r4 = r8.hasNext()     // Catch:{ Exception -> 0x0066, all -> 0x0064 }
            if (r4 == 0) goto L_0x0057
            java.lang.Object r4 = r8.next()     // Catch:{ Exception -> 0x0066, all -> 0x0064 }
            com.alibaba.sdk.android.httpdns.a.g r4 = (com.alibaba.sdk.android.httpdns.a.g) r4     // Catch:{ Exception -> 0x0066, all -> 0x0064 }
            r4.g = r1     // Catch:{ Exception -> 0x0066, all -> 0x0064 }
            long r5 = r7.a((android.database.sqlite.SQLiteDatabase) r3, (com.alibaba.sdk.android.httpdns.a.g) r4)     // Catch:{ Exception -> 0x0066, all -> 0x0064 }
            r4.id = r5     // Catch:{ Exception -> 0x0066, all -> 0x0064 }
            goto L_0x0042
        L_0x0057:
            r3.setTransactionSuccessful()     // Catch:{ Exception -> 0x0066, all -> 0x0064 }
            if (r3 == 0) goto L_0x0062
            r3.endTransaction()     // Catch:{ all -> 0x0080 }
            r3.close()     // Catch:{ all -> 0x0080 }
        L_0x0062:
            monitor-exit(r0)     // Catch:{ all -> 0x0080 }
            return r1
        L_0x0064:
            r8 = move-exception
            goto L_0x006a
        L_0x0066:
            goto L_0x0074
        L_0x0068:
            r8 = move-exception
            r3 = r2
        L_0x006a:
            if (r3 == 0) goto L_0x0072
            r3.endTransaction()     // Catch:{ all -> 0x0080 }
            r3.close()     // Catch:{ all -> 0x0080 }
        L_0x0072:
            throw r8     // Catch:{ all -> 0x0080 }
        L_0x0073:
            r3 = r2
        L_0x0074:
            if (r3 == 0) goto L_0x007c
            r3.endTransaction()     // Catch:{ all -> 0x0080 }
            r3.close()     // Catch:{ all -> 0x0080 }
        L_0x007c:
            r1 = 0
            monitor-exit(r0)     // Catch:{ all -> 0x0080 }
            return r1
        L_0x0080:
            r8 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0080 }
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.sdk.android.httpdns.a.d.m2a(com.alibaba.sdk.android.httpdns.a.e):long");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0096, code lost:
        r8 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0098, code lost:
        r8 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00a0, code lost:
        if (r3 == null) goto L_0x00c5;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0096 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:10:0x0048] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x009d A[SYNTHETIC, Splitter:B:23:0x009d] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00b1 A[Catch:{ Exception -> 0x00a9, all -> 0x00a6 }] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00b6 A[Catch:{ Exception -> 0x00a9, all -> 0x00a6 }] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00bf A[Catch:{ Exception -> 0x00a9, all -> 0x00a6 }] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00c4 A[Catch:{ Exception -> 0x00a9, all -> 0x00a6 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.alibaba.sdk.android.httpdns.a.e a(java.lang.String r7, java.lang.String r8) {
        /*
            r6 = this;
            java.lang.Object r0 = f459a
            monitor-enter(r0)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x00c7 }
            r1.<init>()     // Catch:{ all -> 0x00c7 }
            java.lang.String r2 = "SELECT * FROM "
            r1.append(r2)     // Catch:{ all -> 0x00c7 }
            java.lang.String r2 = "host"
            r1.append(r2)     // Catch:{ all -> 0x00c7 }
            java.lang.String r2 = " WHERE "
            r1.append(r2)     // Catch:{ all -> 0x00c7 }
            java.lang.String r2 = "sp"
            r1.append(r2)     // Catch:{ all -> 0x00c7 }
            java.lang.String r2 = " =? "
            r1.append(r2)     // Catch:{ all -> 0x00c7 }
            java.lang.String r2 = " AND "
            r1.append(r2)     // Catch:{ all -> 0x00c7 }
            java.lang.String r2 = "host"
            r1.append(r2)     // Catch:{ all -> 0x00c7 }
            java.lang.String r2 = " =? ;"
            r1.append(r2)     // Catch:{ all -> 0x00c7 }
            r2 = 0
            android.database.sqlite.SQLiteDatabase r3 = r6.getReadableDatabase()     // Catch:{ Exception -> 0x00ba, all -> 0x00ac }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x00a9, all -> 0x00a6 }
            r4 = 2
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch:{ Exception -> 0x00a9, all -> 0x00a6 }
            r5 = 0
            r4[r5] = r7     // Catch:{ Exception -> 0x00a9, all -> 0x00a6 }
            r7 = 1
            r4[r7] = r8     // Catch:{ Exception -> 0x00a9, all -> 0x00a6 }
            android.database.Cursor r7 = r3.rawQuery(r1, r4)     // Catch:{ Exception -> 0x00a9, all -> 0x00a6 }
            if (r7 == 0) goto L_0x009a
            int r8 = r7.getCount()     // Catch:{ Exception -> 0x0098, all -> 0x0096 }
            if (r8 <= 0) goto L_0x009a
            r7.moveToFirst()     // Catch:{ Exception -> 0x0098, all -> 0x0096 }
            com.alibaba.sdk.android.httpdns.a.e r8 = new com.alibaba.sdk.android.httpdns.a.e     // Catch:{ Exception -> 0x0098, all -> 0x0096 }
            r8.<init>()     // Catch:{ Exception -> 0x0098, all -> 0x0096 }
            java.lang.String r1 = "id"
            int r1 = r7.getColumnIndex(r1)     // Catch:{ Exception -> 0x0094, all -> 0x0096 }
            int r1 = r7.getInt(r1)     // Catch:{ Exception -> 0x0094, all -> 0x0096 }
            long r1 = (long) r1     // Catch:{ Exception -> 0x0094, all -> 0x0096 }
            r8.id = r1     // Catch:{ Exception -> 0x0094, all -> 0x0096 }
            java.lang.String r1 = "host"
            int r1 = r7.getColumnIndex(r1)     // Catch:{ Exception -> 0x0094, all -> 0x0096 }
            java.lang.String r1 = r7.getString(r1)     // Catch:{ Exception -> 0x0094, all -> 0x0096 }
            r8.j = r1     // Catch:{ Exception -> 0x0094, all -> 0x0096 }
            java.lang.String r1 = "sp"
            int r1 = r7.getColumnIndex(r1)     // Catch:{ Exception -> 0x0094, all -> 0x0096 }
            java.lang.String r1 = r7.getString(r1)     // Catch:{ Exception -> 0x0094, all -> 0x0096 }
            r8.k = r1     // Catch:{ Exception -> 0x0094, all -> 0x0096 }
            java.lang.String r1 = "time"
            int r1 = r7.getColumnIndex(r1)     // Catch:{ Exception -> 0x0094, all -> 0x0096 }
            java.lang.String r1 = r7.getString(r1)     // Catch:{ Exception -> 0x0094, all -> 0x0096 }
            java.lang.String r1 = com.alibaba.sdk.android.httpdns.a.c.d(r1)     // Catch:{ Exception -> 0x0094, all -> 0x0096 }
            r8.l = r1     // Catch:{ Exception -> 0x0094, all -> 0x0096 }
            java.util.List r1 = r6.a((com.alibaba.sdk.android.httpdns.a.e) r8)     // Catch:{ Exception -> 0x0094, all -> 0x0096 }
            java.util.ArrayList r1 = (java.util.ArrayList) r1     // Catch:{ Exception -> 0x0094, all -> 0x0096 }
            r8.f460a = r1     // Catch:{ Exception -> 0x0094, all -> 0x0096 }
            goto L_0x009b
        L_0x0094:
            goto L_0x00bd
        L_0x0096:
            r8 = move-exception
            goto L_0x00af
        L_0x0098:
            r8 = r2
            goto L_0x00bd
        L_0x009a:
            r8 = r2
        L_0x009b:
            if (r7 == 0) goto L_0x00a0
            r7.close()     // Catch:{ all -> 0x00c7 }
        L_0x00a0:
            if (r3 == 0) goto L_0x00c5
        L_0x00a2:
            r3.close()     // Catch:{ all -> 0x00c7 }
            goto L_0x00c5
        L_0x00a6:
            r8 = move-exception
            r7 = r2
            goto L_0x00af
        L_0x00a9:
            r7 = r2
            r8 = r7
            goto L_0x00bd
        L_0x00ac:
            r8 = move-exception
            r7 = r2
            r3 = r7
        L_0x00af:
            if (r7 == 0) goto L_0x00b4
            r7.close()     // Catch:{ all -> 0x00c7 }
        L_0x00b4:
            if (r3 == 0) goto L_0x00b9
            r3.close()     // Catch:{ all -> 0x00c7 }
        L_0x00b9:
            throw r8     // Catch:{ all -> 0x00c7 }
        L_0x00ba:
            r7 = r2
            r8 = r7
            r3 = r8
        L_0x00bd:
            if (r7 == 0) goto L_0x00c2
            r7.close()     // Catch:{ all -> 0x00c7 }
        L_0x00c2:
            if (r3 == 0) goto L_0x00c5
            goto L_0x00a2
        L_0x00c5:
            monitor-exit(r0)     // Catch:{ all -> 0x00c7 }
            return r8
        L_0x00c7:
            r7 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00c7 }
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.sdk.android.httpdns.a.d.a(java.lang.String, java.lang.String):com.alibaba.sdk.android.httpdns.a.e");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0089, code lost:
        if (r4 != null) goto L_0x008b;
     */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0099 A[Catch:{ Exception -> 0x0092, all -> 0x008f }] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x009e A[Catch:{ Exception -> 0x0092, all -> 0x008f }] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00a6 A[Catch:{ Exception -> 0x0092, all -> 0x008f }] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00ab A[Catch:{ Exception -> 0x0092, all -> 0x008f }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<com.alibaba.sdk.android.httpdns.a.e> b() {
        /*
            r7 = this;
            java.lang.Object r0 = f459a
            monitor-enter(r0)
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ all -> 0x00ae }
            r1.<init>()     // Catch:{ all -> 0x00ae }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x00ae }
            r2.<init>()     // Catch:{ all -> 0x00ae }
            java.lang.String r3 = "SELECT * FROM "
            r2.append(r3)     // Catch:{ all -> 0x00ae }
            java.lang.String r3 = "host"
            r2.append(r3)     // Catch:{ all -> 0x00ae }
            java.lang.String r3 = " ; "
            r2.append(r3)     // Catch:{ all -> 0x00ae }
            r3 = 0
            android.database.sqlite.SQLiteDatabase r4 = r7.getReadableDatabase()     // Catch:{ Exception -> 0x00a2, all -> 0x0094 }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x0092, all -> 0x008f }
            android.database.Cursor r2 = r4.rawQuery(r2, r3)     // Catch:{ Exception -> 0x0092, all -> 0x008f }
            if (r2 == 0) goto L_0x0084
            int r3 = r2.getCount()     // Catch:{ Exception -> 0x0082, all -> 0x0080 }
            if (r3 <= 0) goto L_0x0084
            r2.moveToFirst()     // Catch:{ Exception -> 0x0082, all -> 0x0080 }
        L_0x0034:
            com.alibaba.sdk.android.httpdns.a.e r3 = new com.alibaba.sdk.android.httpdns.a.e     // Catch:{ Exception -> 0x0082, all -> 0x0080 }
            r3.<init>()     // Catch:{ Exception -> 0x0082, all -> 0x0080 }
            java.lang.String r5 = "id"
            int r5 = r2.getColumnIndex(r5)     // Catch:{ Exception -> 0x0082, all -> 0x0080 }
            int r5 = r2.getInt(r5)     // Catch:{ Exception -> 0x0082, all -> 0x0080 }
            long r5 = (long) r5     // Catch:{ Exception -> 0x0082, all -> 0x0080 }
            r3.id = r5     // Catch:{ Exception -> 0x0082, all -> 0x0080 }
            java.lang.String r5 = "host"
            int r5 = r2.getColumnIndex(r5)     // Catch:{ Exception -> 0x0082, all -> 0x0080 }
            java.lang.String r5 = r2.getString(r5)     // Catch:{ Exception -> 0x0082, all -> 0x0080 }
            r3.j = r5     // Catch:{ Exception -> 0x0082, all -> 0x0080 }
            java.lang.String r5 = "sp"
            int r5 = r2.getColumnIndex(r5)     // Catch:{ Exception -> 0x0082, all -> 0x0080 }
            java.lang.String r5 = r2.getString(r5)     // Catch:{ Exception -> 0x0082, all -> 0x0080 }
            r3.k = r5     // Catch:{ Exception -> 0x0082, all -> 0x0080 }
            java.lang.String r5 = "time"
            int r5 = r2.getColumnIndex(r5)     // Catch:{ Exception -> 0x0082, all -> 0x0080 }
            java.lang.String r5 = r2.getString(r5)     // Catch:{ Exception -> 0x0082, all -> 0x0080 }
            java.lang.String r5 = com.alibaba.sdk.android.httpdns.a.c.d(r5)     // Catch:{ Exception -> 0x0082, all -> 0x0080 }
            r3.l = r5     // Catch:{ Exception -> 0x0082, all -> 0x0080 }
            java.util.List r5 = r7.a((com.alibaba.sdk.android.httpdns.a.e) r3)     // Catch:{ Exception -> 0x0082, all -> 0x0080 }
            java.util.ArrayList r5 = (java.util.ArrayList) r5     // Catch:{ Exception -> 0x0082, all -> 0x0080 }
            r3.f460a = r5     // Catch:{ Exception -> 0x0082, all -> 0x0080 }
            r1.add(r3)     // Catch:{ Exception -> 0x0082, all -> 0x0080 }
            boolean r3 = r2.moveToNext()     // Catch:{ Exception -> 0x0082, all -> 0x0080 }
            if (r3 != 0) goto L_0x0034
            goto L_0x0084
        L_0x0080:
            r1 = move-exception
            goto L_0x0097
        L_0x0082:
            goto L_0x00a4
        L_0x0084:
            if (r2 == 0) goto L_0x0089
            r2.close()     // Catch:{ all -> 0x00ae }
        L_0x0089:
            if (r4 == 0) goto L_0x00ac
        L_0x008b:
            r4.close()     // Catch:{ all -> 0x00ae }
            goto L_0x00ac
        L_0x008f:
            r1 = move-exception
            r2 = r3
            goto L_0x0097
        L_0x0092:
            r2 = r3
            goto L_0x00a4
        L_0x0094:
            r1 = move-exception
            r2 = r3
            r4 = r2
        L_0x0097:
            if (r2 == 0) goto L_0x009c
            r2.close()     // Catch:{ all -> 0x00ae }
        L_0x009c:
            if (r4 == 0) goto L_0x00a1
            r4.close()     // Catch:{ all -> 0x00ae }
        L_0x00a1:
            throw r1     // Catch:{ all -> 0x00ae }
        L_0x00a2:
            r2 = r3
            r4 = r2
        L_0x00a4:
            if (r2 == 0) goto L_0x00a9
            r2.close()     // Catch:{ all -> 0x00ae }
        L_0x00a9:
            if (r4 == 0) goto L_0x00ac
            goto L_0x008b
        L_0x00ac:
            monitor-exit(r0)     // Catch:{ all -> 0x00ae }
            return r1
        L_0x00ae:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00ae }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.sdk.android.httpdns.a.d.b():java.util.List");
    }

    /* access modifiers changed from: package-private */
    public void b(String str, String str2) {
        synchronized (f459a) {
            e a2 = a(str, str2);
            if (a2 != null) {
                c(a2);
                if (a2.f460a != null) {
                    Iterator<g> it = a2.f460a.iterator();
                    while (it.hasNext()) {
                        a(it.next());
                    }
                }
            }
        }
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("CREATE TABLE host (id INTEGER PRIMARY KEY,host TEXT,sp TEXT,time TEXT);");
            sQLiteDatabase.execSQL("CREATE TABLE ip (id INTEGER PRIMARY KEY,host_id INTEGER,ip TEXT,ttl TEXT);");
        } catch (Exception unused) {
        }
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i != i2) {
            try {
                sQLiteDatabase.beginTransaction();
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS host;");
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS ip;");
                sQLiteDatabase.setTransactionSuccessful();
                sQLiteDatabase.endTransaction();
                onCreate(sQLiteDatabase);
            } catch (Exception unused) {
            }
        }
    }
}

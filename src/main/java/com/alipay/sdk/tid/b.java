package com.alipay.sdk.tid;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.alipay.sdk.util.a;

public final class b {

    /* renamed from: c  reason: collision with root package name */
    private static b f718c;

    /* renamed from: a  reason: collision with root package name */
    public String f719a;

    /* renamed from: b  reason: collision with root package name */
    public String f720b;

    private b() {
    }

    private String b() {
        return this.f719a;
    }

    private void a(String str) {
        this.f719a = str;
    }

    private String c() {
        return this.f720b;
    }

    private void b(String str) {
        this.f720b = str;
    }

    private void a(Context context) {
        a aVar = new a(context);
        try {
            aVar.a(a.a(context).a(), a.a(context).b(), this.f719a, this.f720b);
        } catch (Exception unused) {
        } finally {
            aVar.close();
        }
    }

    private boolean d() {
        return TextUtils.isEmpty(this.f719a);
    }

    public static synchronized b a() {
        b bVar;
        synchronized (b.class) {
            if (f718c == null) {
                f718c = new b();
                Context context = com.alipay.sdk.sys.b.a().f714a;
                a aVar = new a(context);
                String a2 = a.a(context).a();
                String b2 = a.a(context).b();
                f718c.f719a = aVar.a(a2, b2);
                f718c.f720b = aVar.b(a2, b2);
                if (TextUtils.isEmpty(f718c.f720b)) {
                    b bVar2 = f718c;
                    String hexString = Long.toHexString(System.currentTimeMillis());
                    if (hexString.length() > 10) {
                        hexString = hexString.substring(hexString.length() - 10);
                    }
                    bVar2.f720b = hexString;
                }
                aVar.a(a2, b2, f718c.f719a, f718c.f720b);
            }
            bVar = f718c;
        }
        return bVar;
    }

    private static void e() {
        SQLiteDatabase sQLiteDatabase;
        Throwable th;
        Context context = com.alipay.sdk.sys.b.a().f714a;
        String a2 = a.a(context).a();
        String b2 = a.a(context).b();
        a aVar = new a(context);
        SQLiteDatabase sQLiteDatabase2 = null;
        try {
            sQLiteDatabase = aVar.getWritableDatabase();
            try {
                aVar.a(sQLiteDatabase, a2, b2, "", "");
                a.a(sQLiteDatabase, a.c(a2, b2));
                if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
                    sQLiteDatabase.close();
                }
            } catch (Exception unused) {
                sQLiteDatabase2 = sQLiteDatabase;
                if (sQLiteDatabase2 != null && sQLiteDatabase2.isOpen()) {
                    sQLiteDatabase2.close();
                }
                aVar.close();
            } catch (Throwable th2) {
                th = th2;
                if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
                    sQLiteDatabase.close();
                }
                throw th;
            }
        } catch (Exception unused2) {
            sQLiteDatabase2.close();
            aVar.close();
        } catch (Throwable th3) {
            sQLiteDatabase = null;
            th = th3;
            sQLiteDatabase.close();
            throw th;
        }
        aVar.close();
    }

    private static String f() {
        String hexString = Long.toHexString(System.currentTimeMillis());
        return hexString.length() > 10 ? hexString.substring(hexString.length() - 10) : hexString;
    }
}

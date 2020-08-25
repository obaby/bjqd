package com.alipay.sdk.app.statistic;

import android.content.Context;
import android.text.TextUtils;

public final class a {

    /* renamed from: a  reason: collision with root package name */
    public static final String f621a = "alipay_cashier_statistic_record";

    /* renamed from: b  reason: collision with root package name */
    private static c f622b;

    public static void a(Context context) {
        if (f622b == null) {
            f622b = new c(context);
        }
    }

    private static void b(Context context, String str) {
        new Thread(new b(context, str)).start();
    }

    public static synchronized void a(Context context, String str) {
        String str2;
        String str3;
        String str4;
        synchronized (a.class) {
            if (f622b != null) {
                c cVar = f622b;
                if (TextUtils.isEmpty(cVar.Q)) {
                    str2 = "";
                } else {
                    String[] split = str.split("&");
                    if (split != null) {
                        str4 = null;
                        str3 = null;
                        for (String split2 : split) {
                            String[] split3 = split2.split("=");
                            if (split3 != null && split3.length == 2) {
                                if (split3[0].equalsIgnoreCase(c.F)) {
                                    split3[1].replace("\"", "");
                                } else if (split3[0].equalsIgnoreCase(c.G)) {
                                    str4 = split3[1].replace("\"", "");
                                } else if (split3[0].equalsIgnoreCase(c.H)) {
                                    str3 = split3[1].replace("\"", "");
                                }
                            }
                        }
                    } else {
                        str4 = null;
                        str3 = null;
                    }
                    String a2 = c.a(str3);
                    String a3 = c.a(str4);
                    cVar.J = String.format("%s,%s,-,%s,-,-,-", new Object[]{a2, a3, c.a(a3)});
                    str2 = String.format("[(%s),(%s),(%s),(%s),(%s),(%s),(%s),(%s),(%s),(%s)]", new Object[]{cVar.I, cVar.J, cVar.K, cVar.L, cVar.M, cVar.N, cVar.O, cVar.P, cVar.Q, cVar.R});
                }
                new Thread(new b(context, str2)).start();
                f622b = null;
            }
        }
    }

    public static void a(String str, Throwable th) {
        if (f622b != null && th.getClass() != null) {
            f622b.a(str, th.getClass().getSimpleName(), th);
        }
    }

    private static void a(String str, String str2, Throwable th, String str3) {
        if (f622b != null) {
            f622b.a(str, str2, c.a(th), str3);
        }
    }

    public static void a(String str, String str2, Throwable th) {
        if (f622b != null) {
            f622b.a(str, str2, th);
        }
    }

    public static void a(String str, String str2, String str3) {
        if (f622b != null) {
            f622b.a(str, str2, str3);
        }
    }
}

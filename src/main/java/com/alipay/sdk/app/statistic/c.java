package com.alipay.sdk.app.statistic;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import anet.channel.strategy.dispatch.DispatchConstants;
import com.alipay.sdk.cons.a;
import com.alipay.sdk.tid.b;
import com.stub.StubApp;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class c {
    public static final String A = "BindWaitTimeoutEx";
    public static final String B = "CheckClientExistEx";
    public static final String C = "CheckClientSignEx";
    public static final String D = "GetInstalledAppEx";
    public static final String E = "GetInstalledAppEx";
    public static final String F = "partner";
    public static final String G = "out_trade_no";
    public static final String H = "trade_no";

    /* renamed from: a  reason: collision with root package name */
    public static final String f625a = "net";

    /* renamed from: b  reason: collision with root package name */
    public static final String f626b = "biz";

    /* renamed from: c  reason: collision with root package name */
    public static final String f627c = "cp";
    public static final String d = "auth";
    public static final String e = "third";
    public static final String f = "FormatResultEx";
    public static final String g = "GetApdidEx";
    public static final String h = "GetApdidNull";
    public static final String i = "GetApdidTimeout";
    public static final String j = "GetUtdidEx";
    public static final String k = "GetPackageInfoEx";
    public static final String l = "NotIncludeSignatures";
    public static final String m = "GetInstalledPackagesEx";
    public static final String n = "GetPublicKeyFromSignEx";
    public static final String o = "H5PayNetworkError";
    public static final String p = "H5AuthNetworkError";
    public static final String q = "SSLError";
    public static final String r = "H5PayDataAnalysisError";
    public static final String s = "H5AuthDataAnalysisError";
    public static final String t = "PublicKeyUnmatch";
    public static final String u = "ClientBindFailed";
    public static final String v = "TriDesEncryptError";
    public static final String w = "TriDesDecryptError";
    public static final String x = "ClientBindException";
    public static final String y = "SaveTradeTokenError";
    public static final String z = "ClientBindServiceFailed";
    String I;
    String J;
    String K;
    String L;
    String M;
    String N;
    String O;
    String P;
    String Q = "";
    String R;

    public c(Context context) {
        context = context != null ? StubApp.getOrigApplicationContext(context.getApplicationContext()) : context;
        this.I = String.format("123456789,%s", new Object[]{new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").format(new Date())});
        this.K = a(context);
        this.L = String.format("android,3,%s,%s,com.alipay.mcpay,5.0,-,-,-", new Object[]{a(a.f), a(a.g)});
        this.M = String.format("%s,%s,-,-,-", new Object[]{a(b.a().f719a), a(com.alipay.sdk.sys.b.a().c())});
        this.N = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,-", new Object[]{a(com.alipay.sdk.util.a.d(context)), DispatchConstants.ANDROID, a(Build.VERSION.RELEASE), a(Build.MODEL), "-", a(com.alipay.sdk.util.a.a(context).a()), a(com.alipay.sdk.util.a.b(context).p), "gw", a(com.alipay.sdk.util.a.a(context).b())});
        this.O = "-";
        this.P = "-";
        this.R = "-";
    }

    private boolean a() {
        return TextUtils.isEmpty(this.Q);
    }

    public final void a(String str, String str2, Throwable th) {
        a(str, str2, a(th));
    }

    private void a(String str, String str2, Throwable th, String str3) {
        a(str, str2, a(th), str3);
    }

    public final void a(String str, String str2, String str3, String str4) {
        String str5 = "";
        if (!TextUtils.isEmpty(this.Q)) {
            str5 = str5 + "^";
        }
        this.Q += (str5 + String.format("%s,%s,%s,%s", new Object[]{str, str2, a(str3), str4}));
    }

    public final void a(String str, String str2, String str3) {
        a(str, str2, str3, "-");
    }

    static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        return str.replace("[", "【").replace("]", "】").replace("(", "（").replace(")", "）").replace(",", "，").replace("-", "=").replace("^", "~");
    }

    static String a(Throwable th) {
        if (th == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        try {
            stringBuffer.append(th.getClass().getName());
            stringBuffer.append(":");
            stringBuffer.append(th.getMessage());
            stringBuffer.append(" 》 ");
            StackTraceElement[] stackTrace = th.getStackTrace();
            if (stackTrace != null) {
                for (int i2 = 0; i2 < stackTrace.length; i2++) {
                    stringBuffer.append(stackTrace[i2].toString() + " 》 ");
                }
            }
        } catch (Throwable unused) {
        }
        return stringBuffer.toString();
    }

    @SuppressLint({"SimpleDateFormat"})
    private static String b() {
        return String.format("123456789,%s", new Object[]{new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").format(new Date())});
    }

    private static String c(String str) {
        String str2;
        String[] split = str.split("&");
        String str3 = null;
        if (split != null) {
            str2 = null;
            String str4 = null;
            for (String split2 : split) {
                String[] split3 = split2.split("=");
                if (split3 != null && split3.length == 2) {
                    if (split3[0].equalsIgnoreCase(F)) {
                        split3[1].replace("\"", "");
                    } else if (split3[0].equalsIgnoreCase(G)) {
                        str2 = split3[1].replace("\"", "");
                    } else if (split3[0].equalsIgnoreCase(H)) {
                        str4 = split3[1].replace("\"", "");
                    }
                }
            }
            str3 = str4;
        } else {
            str2 = null;
        }
        String a2 = a(str3);
        String a3 = a(str2);
        return String.format("%s,%s,-,%s,-,-,-", new Object[]{a2, a3, a(a3)});
    }

    private static String a(Context context) {
        String str = "-";
        String str2 = "-";
        if (context != null) {
            try {
                Context origApplicationContext = StubApp.getOrigApplicationContext(context.getApplicationContext());
                String packageName = origApplicationContext.getPackageName();
                try {
                    str2 = origApplicationContext.getPackageManager().getPackageInfo(packageName, 0).versionName;
                } catch (Throwable unused) {
                }
                str = packageName;
            } catch (Throwable unused2) {
            }
        }
        return String.format("%s,%s,-,-,-", new Object[]{str, str2});
    }

    private static String c() {
        return String.format("android,3,%s,%s,com.alipay.mcpay,5.0,-,-,-", new Object[]{a(a.f), a(a.g)});
    }

    private static String d() {
        return String.format("%s,%s,-,-,-", new Object[]{a(b.a().f719a), a(com.alipay.sdk.sys.b.a().c())});
    }

    private static String b(Context context) {
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,-", new Object[]{a(com.alipay.sdk.util.a.d(context)), DispatchConstants.ANDROID, a(Build.VERSION.RELEASE), a(Build.MODEL), "-", a(com.alipay.sdk.util.a.a(context).a()), a(com.alipay.sdk.util.a.b(context).p), "gw", a(com.alipay.sdk.util.a.a(context).b())});
    }

    private String b(String str) {
        String str2;
        if (TextUtils.isEmpty(this.Q)) {
            return "";
        }
        String[] split = str.split("&");
        String str3 = null;
        if (split != null) {
            str2 = null;
            String str4 = null;
            for (String split2 : split) {
                String[] split3 = split2.split("=");
                if (split3 != null && split3.length == 2) {
                    if (split3[0].equalsIgnoreCase(F)) {
                        split3[1].replace("\"", "");
                    } else if (split3[0].equalsIgnoreCase(G)) {
                        str2 = split3[1].replace("\"", "");
                    } else if (split3[0].equalsIgnoreCase(H)) {
                        str4 = split3[1].replace("\"", "");
                    }
                }
            }
            str3 = str4;
        } else {
            str2 = null;
        }
        String a2 = a(str3);
        String a3 = a(str2);
        this.J = String.format("%s,%s,-,%s,-,-,-", new Object[]{a2, a3, a(a3)});
        return String.format("[(%s),(%s),(%s),(%s),(%s),(%s),(%s),(%s),(%s),(%s)]", new Object[]{this.I, this.J, this.K, this.L, this.M, this.N, this.O, this.P, this.Q, this.R});
    }
}

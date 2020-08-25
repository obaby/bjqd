package com.alipay.sdk.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.webkit.WebView;
import anet.channel.util.HttpConstant;
import com.alibaba.fastjson.asm.Opcodes;
import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.i;
import com.alipay.sdk.app.j;
import com.alipay.sdk.app.statistic.c;
import com.stub.StubApp;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressLint({"SetJavaScriptEnabled", "DefaultLocale"})
public final class l {

    /* renamed from: a  reason: collision with root package name */
    static final String f742a = "com.alipay.android.app";

    /* renamed from: b  reason: collision with root package name */
    public static final int f743b = 99;

    /* renamed from: c  reason: collision with root package name */
    public static final int f744c = 73;
    public static final String[] d = {"10.1.5.1013151", "10.1.5.1013148"};
    private static final String e = "com.eg.android.AlipayGphone";
    private static final String f = "com.eg.android.AlipayGphoneRC";

    public static String d() {
        return "-1;-1";
    }

    public static String a() {
        return EnvUtils.isSandBox() ? f : "com.eg.android.AlipayGphone";
    }

    public static Map<String, String> a(String str) {
        HashMap hashMap = new HashMap();
        for (String str2 : str.split("&")) {
            int indexOf = str2.indexOf("=", 1);
            hashMap.put(str2.substring(0, indexOf), URLDecoder.decode(str2.substring(indexOf + 1)));
        }
        return hashMap;
    }

    public static String a(String str, String str2, String str3) {
        try {
            int indexOf = str3.indexOf(str) + str.length();
            if (indexOf <= str.length()) {
                return "";
            }
            int i = 0;
            if (!TextUtils.isEmpty(str2)) {
                i = str3.indexOf(str2, indexOf);
            }
            if (i <= 0) {
                return str3.substring(indexOf);
            }
            return str3.substring(indexOf, i);
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String b(String str, String str2, String str3) {
        try {
            int indexOf = str3.indexOf(str) + str.length();
            int i = 0;
            if (!TextUtils.isEmpty(str2)) {
                i = str3.indexOf(str2, indexOf);
            }
            if (i <= 0) {
                return str3.substring(indexOf);
            }
            return str3.substring(indexOf, i);
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String a(byte[] bArr) {
        try {
            String obj = ((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(bArr))).getPublicKey().toString();
            if (obj.indexOf("modulus") != -1) {
                return obj.substring(obj.indexOf("modulus") + 8, obj.lastIndexOf(",")).trim();
            }
            return null;
        } catch (Exception e2) {
            com.alipay.sdk.app.statistic.a.a(c.d, c.n, (Throwable) e2);
            return null;
        }
    }

    public static a a(Context context) {
        return a(context, a());
    }

    private static boolean a(PackageInfo packageInfo) {
        String str = "";
        boolean z = false;
        if (packageInfo == null) {
            str = str + "info == null";
        } else if (packageInfo.signatures == null) {
            str = str + "info.signatures == null";
        } else if (packageInfo.signatures.length <= 0) {
            str = str + "info.signatures.length <= 0";
        } else {
            z = true;
        }
        if (!z) {
            com.alipay.sdk.app.statistic.a.a(c.d, c.l, str);
        }
        return z;
    }

    private static PackageInfo b(Context context, String str) throws PackageManager.NameNotFoundException {
        return context.getPackageManager().getPackageInfo(str, Opcodes.CHECKCAST);
    }

    private static PackageInfo c(Context context, String str) {
        for (PackageInfo next : context.getPackageManager().getInstalledPackages(Opcodes.CHECKCAST)) {
            if (next.packageName.equals(str)) {
                return next;
            }
        }
        return null;
    }

    private static a b(PackageInfo packageInfo) {
        if (packageInfo == null) {
            return null;
        }
        a aVar = new a();
        aVar.f745a = packageInfo.signatures;
        aVar.f746b = packageInfo.versionCode;
        return aVar;
    }

    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public Signature[] f745a;

        /* renamed from: b  reason: collision with root package name */
        public int f746b;

        public final boolean a() {
            if (this.f745a == null || this.f745a.length <= 0) {
                return false;
            }
            int i = 0;
            while (i < this.f745a.length) {
                String a2 = l.a(this.f745a[i].toByteArray());
                if (a2 == null || TextUtils.equals(a2, com.alipay.sdk.cons.a.h)) {
                    i++;
                } else {
                    com.alipay.sdk.app.statistic.a.a(c.f626b, c.t, a2);
                    return true;
                }
            }
            return false;
        }
    }

    public static boolean b(Context context) {
        try {
            if (context.getPackageManager().getPackageInfo(f742a, 128) == null) {
                return false;
            }
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public static boolean c(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(a(), 128);
            if (packageInfo != null && packageInfo.versionCode > 73) {
                return true;
            }
            return false;
        } catch (Throwable th) {
            com.alipay.sdk.app.statistic.a.a(c.f626b, c.B, th);
            return false;
        }
    }

    public static boolean d(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(a(), 128);
            if (packageInfo == null) {
                return false;
            }
            String str = packageInfo.versionName;
            if (TextUtils.equals(str, d[0]) || TextUtils.equals(str, d[1])) {
                return true;
            }
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static boolean e(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(a(), 128);
            if (packageInfo != null && packageInfo.versionCode < 99) {
                return true;
            }
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static String f(Context context) {
        String b2 = b();
        String c2 = c();
        String g = g(context);
        String h = h(context);
        return " (" + b2 + h.f735b + c2 + h.f735b + g + ";;" + h + ")(sdk android)";
    }

    public static String b() {
        return "Android " + Build.VERSION.RELEASE;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(14:0|(1:2)|3|(3:5|6|(1:8))|9|11|12|13|14|(1:16)|18|(1:20)|21|22) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x00ca */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x00d8 A[Catch:{ Throwable -> 0x00f4 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.webkit.WebView a(android.app.Activity r5, java.lang.String r6, java.lang.String r7) {
        /*
            android.content.Context r0 = r5.getApplicationContext()
            android.content.Context r0 = com.stub.StubApp.getOrigApplicationContext(r0)
            boolean r1 = android.text.TextUtils.isEmpty(r7)
            if (r1 != 0) goto L_0x0023
            android.webkit.CookieSyncManager r1 = android.webkit.CookieSyncManager.createInstance(r0)
            r1.sync()
            android.webkit.CookieManager r1 = android.webkit.CookieManager.getInstance()
            r1.setCookie(r6, r7)
            android.webkit.CookieSyncManager r7 = android.webkit.CookieSyncManager.getInstance()
            r7.sync()
        L_0x0023:
            android.widget.LinearLayout r7 = new android.widget.LinearLayout
            r7.<init>(r0)
            android.widget.LinearLayout$LayoutParams r1 = new android.widget.LinearLayout$LayoutParams
            r2 = -1
            r1.<init>(r2, r2)
            r2 = 1
            r7.setOrientation(r2)
            r5.setContentView(r7, r1)
            android.webkit.WebView r5 = new android.webkit.WebView
            r5.<init>(r0)
            r3 = 1065353216(0x3f800000, float:1.0)
            r1.weight = r3
            r3 = 0
            r5.setVisibility(r3)
            r7.addView(r5, r1)
            android.webkit.WebSettings r7 = r5.getSettings()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r4 = r7.getUserAgentString()
            r1.append(r4)
            java.lang.String r4 = f(r0)
            r1.append(r4)
            java.lang.String r1 = r1.toString()
            r7.setUserAgentString(r1)
            android.webkit.WebSettings$RenderPriority r1 = android.webkit.WebSettings.RenderPriority.HIGH
            r7.setRenderPriority(r1)
            r7.setSupportMultipleWindows(r2)
            r7.setJavaScriptEnabled(r2)
            r7.setSavePassword(r3)
            r7.setJavaScriptCanOpenWindowsAutomatically(r2)
            int r1 = r7.getMinimumFontSize()
            int r1 = r1 + 8
            r7.setMinimumFontSize(r1)
            r7.setAllowFileAccess(r3)
            android.webkit.WebSettings$TextSize r1 = android.webkit.WebSettings.TextSize.NORMAL
            r7.setTextSize(r1)
            r5.setVerticalScrollbarOverlay(r2)
            com.alipay.sdk.util.m r7 = new com.alipay.sdk.util.m
            r7.<init>(r0)
            r5.setDownloadListener(r7)
            int r7 = android.os.Build.VERSION.SDK_INT
            r0 = 7
            if (r7 < r0) goto L_0x00ba
            android.webkit.WebSettings r7 = r5.getSettings()     // Catch:{ Exception -> 0x00ba }
            java.lang.Class r7 = r7.getClass()     // Catch:{ Exception -> 0x00ba }
            java.lang.String r0 = "setDomStorageEnabled"
            java.lang.Class[] r1 = new java.lang.Class[r2]     // Catch:{ Exception -> 0x00ba }
            java.lang.Class r4 = java.lang.Boolean.TYPE     // Catch:{ Exception -> 0x00ba }
            r1[r3] = r4     // Catch:{ Exception -> 0x00ba }
            java.lang.reflect.Method r7 = r7.getMethod(r0, r1)     // Catch:{ Exception -> 0x00ba }
            if (r7 == 0) goto L_0x00ba
            android.webkit.WebSettings r0 = r5.getSettings()     // Catch:{ Exception -> 0x00ba }
            java.lang.Object[] r1 = new java.lang.Object[r2]     // Catch:{ Exception -> 0x00ba }
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r2)     // Catch:{ Exception -> 0x00ba }
            r1[r3] = r4     // Catch:{ Exception -> 0x00ba }
            r7.invoke(r0, r1)     // Catch:{ Exception -> 0x00ba }
        L_0x00ba:
            java.lang.String r7 = "searchBoxJavaBridge_"
            r5.removeJavascriptInterface(r7)     // Catch:{ Throwable -> 0x00ca }
            java.lang.String r7 = "accessibility"
            r5.removeJavascriptInterface(r7)     // Catch:{ Throwable -> 0x00ca }
            java.lang.String r7 = "accessibilityTraversal"
            r5.removeJavascriptInterface(r7)     // Catch:{ Throwable -> 0x00ca }
            goto L_0x00f5
        L_0x00ca:
            java.lang.Class r7 = r5.getClass()     // Catch:{ Throwable -> 0x00f4 }
            java.lang.String r0 = "removeJavascriptInterface"
            java.lang.Class[] r1 = new java.lang.Class[r3]     // Catch:{ Throwable -> 0x00f4 }
            java.lang.reflect.Method r7 = r7.getMethod(r0, r1)     // Catch:{ Throwable -> 0x00f4 }
            if (r7 == 0) goto L_0x00f5
            java.lang.Object[] r0 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x00f4 }
            java.lang.String r1 = "searchBoxJavaBridge_"
            r0[r3] = r1     // Catch:{ Throwable -> 0x00f4 }
            r7.invoke(r5, r0)     // Catch:{ Throwable -> 0x00f4 }
            java.lang.Object[] r0 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x00f4 }
            java.lang.String r1 = "accessibility"
            r0[r3] = r1     // Catch:{ Throwable -> 0x00f4 }
            r7.invoke(r5, r0)     // Catch:{ Throwable -> 0x00f4 }
            java.lang.Object[] r0 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x00f4 }
            java.lang.String r1 = "accessibilityTraversal"
            r0[r3] = r1     // Catch:{ Throwable -> 0x00f4 }
            r7.invoke(r5, r0)     // Catch:{ Throwable -> 0x00f4 }
            goto L_0x00f5
        L_0x00f4:
        L_0x00f5:
            int r7 = android.os.Build.VERSION.SDK_INT
            r0 = 19
            if (r7 < r0) goto L_0x0103
            android.webkit.WebSettings r7 = r5.getSettings()
            r0 = 2
            r7.setCacheMode(r0)
        L_0x0103:
            r5.loadUrl(r6)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.util.l.a(android.app.Activity, java.lang.String, java.lang.String):android.webkit.WebView");
    }

    public static String c() {
        String f2 = f();
        int indexOf = f2.indexOf("-");
        if (indexOf != -1) {
            f2 = f2.substring(0, indexOf);
        }
        int indexOf2 = f2.indexOf("\n");
        if (indexOf2 != -1) {
            f2 = f2.substring(0, indexOf2);
        }
        return "Linux " + f2;
    }

    private static String f() {
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader("/proc/version"), 256);
            String readLine = bufferedReader.readLine();
            bufferedReader.close();
            Matcher matcher = Pattern.compile("\\w+\\s+\\w+\\s+([^\\s]+)\\s+\\(([^\\s@]+(?:@[^\\s.]+)?)[^)]*\\)\\s+\\((?:[^(]*\\([^)]*\\))?[^)]*\\)\\s+([^\\s]+)\\s+(?:PREEMPT\\s+)?(.+)").matcher(readLine);
            if (!matcher.matches() || matcher.groupCount() < 4) {
                return "Unavailable";
            }
            return matcher.group(1) + "\n" + matcher.group(2) + " " + matcher.group(3) + "\n" + matcher.group(4);
        } catch (IOException unused) {
            return "Unavailable";
        } catch (Throwable th) {
            bufferedReader.close();
            throw th;
        }
    }

    public static String g(Context context) {
        return context.getResources().getConfiguration().locale.toString();
    }

    private static DisplayMetrics l(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) StubApp.getOrigApplicationContext(context.getApplicationContext()).getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    public static String i(Context context) {
        String a2 = k.a(context);
        return a2.substring(0, a2.indexOf(HttpConstant.SCHEME_SPLIT));
    }

    public static String e() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 24; i++) {
            switch (random.nextInt(3)) {
                case 0:
                    sb.append(String.valueOf((char) ((int) Math.round((Math.random() * 25.0d) + 65.0d))));
                    break;
                case 1:
                    sb.append(String.valueOf((char) ((int) Math.round((Math.random() * 25.0d) + 97.0d))));
                    break;
                case 2:
                    sb.append(String.valueOf(new Random().nextInt(10)));
                    break;
            }
        }
        return sb.toString();
    }

    public static boolean b(String str) {
        return Pattern.compile("^http(s)?://([a-z0-9_\\-]+\\.)*(alipaydev|alipay|taobao)\\.(com|net)(:\\d+)?(/.*)?$").matcher(str).matches();
    }

    public static String j(Context context) {
        String str = "";
        try {
            for (ActivityManager.RunningAppProcessInfo next : ((ActivityManager) StubApp.getOrigApplicationContext(context.getApplicationContext()).getSystemService("activity")).getRunningAppProcesses()) {
                if (next.processName.equals(a())) {
                    str = str + "#M";
                } else {
                    if (next.processName.startsWith(a() + ":")) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(str);
                        sb.append("#");
                        sb.append(next.processName.replace(a() + ":", ""));
                        str = sb.toString();
                    }
                }
            }
        } catch (Throwable unused) {
            str = "";
        }
        if (str.length() > 0) {
            str = str.substring(1);
        }
        return str.length() == 0 ? "N" : str;
    }

    public static String k(Context context) {
        try {
            List<PackageInfo> installedPackages = context.getPackageManager().getInstalledPackages(0);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < installedPackages.size(); i++) {
                PackageInfo packageInfo = installedPackages.get(i);
                int i2 = packageInfo.applicationInfo.flags;
                if ((i2 & 1) == 0 && (i2 & 128) == 0) {
                    if (packageInfo.packageName.equals(a())) {
                        sb.append(packageInfo.packageName);
                        sb.append(packageInfo.versionCode);
                        sb.append("-");
                    } else if (!packageInfo.packageName.contains("theme") && !packageInfo.packageName.startsWith("com.google.") && !packageInfo.packageName.startsWith("com.android.")) {
                        sb.append(packageInfo.packageName);
                        sb.append("-");
                    }
                }
            }
            return sb.toString();
        } catch (Throwable th) {
            com.alipay.sdk.app.statistic.a.a(c.f626b, "GetInstalledAppEx", th);
            return "";
        }
    }

    @SuppressLint({"InlinedApi"})
    private static boolean c(PackageInfo packageInfo) {
        int i = packageInfo.applicationInfo.flags;
        return (i & 1) == 0 && (i & 128) == 0;
    }

    public static boolean a(WebView webView, String str, Activity activity) {
        String str2;
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        if (str.toLowerCase().startsWith(com.alipay.sdk.cons.a.i.toLowerCase()) || str.toLowerCase().startsWith(com.alipay.sdk.cons.a.j.toLowerCase())) {
            try {
                a a2 = a((Context) activity);
                if (a2 != null) {
                    if (!a2.a()) {
                        if (str.startsWith("intent://platformapi/startapp")) {
                            str = str.replaceFirst("intent://platformapi/startapp\\?", com.alipay.sdk.cons.a.i);
                        }
                        activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                        return true;
                    }
                }
                return true;
            } catch (Throwable unused) {
            }
        } else if (TextUtils.equals(str, com.alipay.sdk.cons.a.l) || TextUtils.equals(str, com.alipay.sdk.cons.a.m)) {
            i.f617a = i.a();
            activity.finish();
            return true;
        } else if (str.startsWith(com.alipay.sdk.cons.a.k)) {
            try {
                String substring = str.substring(str.indexOf(com.alipay.sdk.cons.a.k) + 24);
                int parseInt = Integer.parseInt(substring.substring(substring.lastIndexOf(com.alipay.sdk.cons.a.n) + 10));
                if (parseInt != j.SUCCEEDED.h) {
                    if (parseInt != j.PAY_WAITTING.h) {
                        j a3 = j.a(j.FAILED.h);
                        i.f617a = i.a(a3.h, a3.i, "");
                        activity.runOnUiThread(new n(activity));
                        return true;
                    }
                }
                if (com.alipay.sdk.cons.a.r) {
                    StringBuilder sb = new StringBuilder();
                    String decode = URLDecoder.decode(str);
                    String decode2 = URLDecoder.decode(decode);
                    String str3 = decode2.substring(decode2.indexOf(com.alipay.sdk.cons.a.k) + 24, decode2.lastIndexOf(com.alipay.sdk.cons.a.n)).split(com.alipay.sdk.cons.a.p)[0];
                    int indexOf = decode.indexOf(com.alipay.sdk.cons.a.p) + 12;
                    sb.append(str3);
                    sb.append(com.alipay.sdk.cons.a.p);
                    sb.append(decode.substring(indexOf, decode.indexOf("&", indexOf)));
                    sb.append(decode.substring(decode.indexOf("&", indexOf)));
                    str2 = sb.toString();
                } else {
                    String decode3 = URLDecoder.decode(str);
                    str2 = decode3.substring(decode3.indexOf(com.alipay.sdk.cons.a.k) + 24, decode3.lastIndexOf(com.alipay.sdk.cons.a.n));
                }
                j a4 = j.a(parseInt);
                i.f617a = i.a(a4.h, a4.i, str2);
            } catch (Exception unused2) {
                j a5 = j.a(j.PARAMS_ERROR.h);
                i.f617a = i.a(a5.h, a5.i, "");
            }
            activity.runOnUiThread(new n(activity));
            return true;
        } else {
            webView.loadUrl(str);
            return true;
        }
    }

    private static a a(Context context, String str) {
        PackageInfo packageInfo;
        try {
            PackageInfo packageInfo2 = context.getPackageManager().getPackageInfo(str, Opcodes.CHECKCAST);
            if (!a(packageInfo2)) {
                try {
                    packageInfo = c(context, str);
                } catch (Throwable th) {
                    com.alipay.sdk.app.statistic.a.a(c.d, c.m, th);
                }
                if (a(packageInfo) || packageInfo == null) {
                    return null;
                }
                a aVar = new a();
                aVar.f745a = packageInfo.signatures;
                aVar.f746b = packageInfo.versionCode;
                return aVar;
            }
            packageInfo = packageInfo2;
        } catch (Throwable th2) {
            com.alipay.sdk.app.statistic.a.a(c.d, c.m, th2);
            packageInfo = null;
        }
        if (a(packageInfo)) {
            return null;
        }
        a aVar2 = new a();
        aVar2.f745a = packageInfo.signatures;
        aVar2.f746b = packageInfo.versionCode;
        return aVar2;
        throw th;
    }

    public static String h(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) StubApp.getOrigApplicationContext(context.getApplicationContext()).getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels + "*" + displayMetrics.heightPixels;
    }
}

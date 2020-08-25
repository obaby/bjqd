package anet.channel.util;

import android.content.Context;
import android.content.pm.PackageManager;
import anet.channel.monitor.NetworkSpeed;
import anet.channel.monitor.b;
import anet.channel.status.NetworkStatusHelper;
import com.ta.utdid2.device.UTDevice;
import java.lang.reflect.Method;

/* compiled from: Taobao */
public class Utils {
    private static final String TAG = "awcn.Utils";
    public static Context context;

    public static String getDeviceId(Context context2) {
        return UTDevice.getUtdid(context2);
    }

    public static String getMainProcessName(Context context2) {
        if (context2 == null) {
            return "";
        }
        try {
            return context2.getPackageManager().getPackageInfo(context2.getPackageName(), 0).applicationInfo.processName;
        } catch (PackageManager.NameNotFoundException unused) {
            return "";
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0075  */
    /* JADX WARNING: Removed duplicated region for block: B:21:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getProcessName(android.content.Context r5, int r6) {
        /*
            java.lang.String r0 = ""
            r1 = -108(0xffffffffffffff94, float:NaN)
            java.lang.String r2 = "activity"
            java.lang.Object r5 = r5.getSystemService(r2)     // Catch:{ Exception -> 0x005c }
            android.app.ActivityManager r5 = (android.app.ActivityManager) r5     // Catch:{ Exception -> 0x005c }
            java.util.List r5 = r5.getRunningAppProcesses()     // Catch:{ Exception -> 0x005c }
            if (r5 == 0) goto L_0x0032
            int r2 = r5.size()     // Catch:{ Exception -> 0x005c }
            if (r2 <= 0) goto L_0x0032
            java.util.Iterator r5 = r5.iterator()     // Catch:{ Exception -> 0x005c }
        L_0x001c:
            boolean r2 = r5.hasNext()     // Catch:{ Exception -> 0x005c }
            if (r2 == 0) goto L_0x006f
            java.lang.Object r2 = r5.next()     // Catch:{ Exception -> 0x005c }
            android.app.ActivityManager$RunningAppProcessInfo r2 = (android.app.ActivityManager.RunningAppProcessInfo) r2     // Catch:{ Exception -> 0x005c }
            android.app.ActivityManager$RunningAppProcessInfo r2 = (android.app.ActivityManager.RunningAppProcessInfo) r2     // Catch:{ Exception -> 0x005c }
            int r3 = r2.pid     // Catch:{ Exception -> 0x005c }
            if (r3 != r6) goto L_0x001c
            java.lang.String r5 = r2.processName     // Catch:{ Exception -> 0x005c }
            r0 = r5
            goto L_0x006f
        L_0x0032:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x005c }
            r5.<init>()     // Catch:{ Exception -> 0x005c }
            java.lang.String r2 = "BuildVersion="
            r5.append(r2)     // Catch:{ Exception -> 0x005c }
            int r2 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x005c }
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ Exception -> 0x005c }
            r5.append(r2)     // Catch:{ Exception -> 0x005c }
            java.lang.String r5 = r5.toString()     // Catch:{ Exception -> 0x005c }
            java.lang.String r5 = anet.channel.util.ErrorConstant.formatMsg(r1, r5)     // Catch:{ Exception -> 0x005c }
            anet.channel.appmonitor.IAppMonitor r2 = anet.channel.appmonitor.AppMonitor.getInstance()     // Catch:{ Exception -> 0x005c }
            anet.channel.statist.ExceptionStatistic r3 = new anet.channel.statist.ExceptionStatistic     // Catch:{ Exception -> 0x005c }
            java.lang.String r4 = "rt"
            r3.<init>(r1, r5, r4)     // Catch:{ Exception -> 0x005c }
            r2.commitStat(r3)     // Catch:{ Exception -> 0x005c }
            goto L_0x006f
        L_0x005c:
            r5 = move-exception
            anet.channel.appmonitor.IAppMonitor r2 = anet.channel.appmonitor.AppMonitor.getInstance()
            anet.channel.statist.ExceptionStatistic r3 = new anet.channel.statist.ExceptionStatistic
            java.lang.String r5 = r5.toString()
            java.lang.String r4 = "rt"
            r3.<init>(r1, r5, r4)
            r2.commitStat(r3)
        L_0x006f:
            boolean r5 = android.text.TextUtils.isEmpty(r0)
            if (r5 == 0) goto L_0x0079
            java.lang.String r0 = getProcessNameNew(r6)
        L_0x0079:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: anet.channel.util.Utils.getProcessName(android.content.Context, int):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:36:0x00bc A[SYNTHETIC, Splitter:B:36:0x00bc] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00c4 A[Catch:{ IOException -> 0x00c0 }] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00d7 A[SYNTHETIC, Splitter:B:45:0x00d7] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00df A[Catch:{ IOException -> 0x00db }] */
    /* JADX WARNING: Removed duplicated region for block: B:59:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String getProcessNameNew(int r7) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "ps  |  grep  "
            r0.append(r1)
            r0.append(r7)
            java.lang.String r0 = r0.toString()
            r1 = 0
            r2 = 0
            java.lang.Runtime r3 = java.lang.Runtime.getRuntime()     // Catch:{ Exception -> 0x00ae, all -> 0x00aa }
            java.lang.String r4 = "sh"
            java.lang.Process r3 = r3.exec(r4)     // Catch:{ Exception -> 0x00ae, all -> 0x00aa }
            java.io.BufferedReader r4 = new java.io.BufferedReader     // Catch:{ Exception -> 0x00ae, all -> 0x00aa }
            java.io.InputStreamReader r5 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x00ae, all -> 0x00aa }
            java.io.InputStream r6 = r3.getInputStream()     // Catch:{ Exception -> 0x00ae, all -> 0x00aa }
            r5.<init>(r6)     // Catch:{ Exception -> 0x00ae, all -> 0x00aa }
            r4.<init>(r5)     // Catch:{ Exception -> 0x00ae, all -> 0x00aa }
            java.io.DataOutputStream r5 = new java.io.DataOutputStream     // Catch:{ Exception -> 0x00a7, all -> 0x00a4 }
            java.io.OutputStream r6 = r3.getOutputStream()     // Catch:{ Exception -> 0x00a7, all -> 0x00a4 }
            r5.<init>(r6)     // Catch:{ Exception -> 0x00a7, all -> 0x00a4 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00a2 }
            r6.<init>()     // Catch:{ Exception -> 0x00a2 }
            r6.append(r0)     // Catch:{ Exception -> 0x00a2 }
            java.lang.String r0 = "  &\n"
            r6.append(r0)     // Catch:{ Exception -> 0x00a2 }
            java.lang.String r0 = r6.toString()     // Catch:{ Exception -> 0x00a2 }
            r5.writeBytes(r0)     // Catch:{ Exception -> 0x00a2 }
            r5.flush()     // Catch:{ Exception -> 0x00a2 }
            java.lang.String r0 = "exit\n"
            r5.writeBytes(r0)     // Catch:{ Exception -> 0x00a2 }
            r3.waitFor()     // Catch:{ Exception -> 0x00a2 }
        L_0x0053:
            java.lang.String r0 = r4.readLine()     // Catch:{ Exception -> 0x00a2 }
            if (r0 == 0) goto L_0x009b
            java.lang.String r3 = "\\s+"
            java.lang.String r6 = "  "
            java.lang.String r0 = r0.replaceAll(r3, r6)     // Catch:{ Exception -> 0x00a2 }
            java.lang.String r3 = "  "
            java.lang.String[] r0 = r0.split(r3)     // Catch:{ Exception -> 0x00a2 }
            int r3 = r0.length     // Catch:{ Exception -> 0x00a2 }
            r6 = 9
            if (r3 < r6) goto L_0x0053
            r3 = 1
            r6 = r0[r3]     // Catch:{ Exception -> 0x00a2 }
            boolean r6 = android.text.TextUtils.isEmpty(r6)     // Catch:{ Exception -> 0x00a2 }
            if (r6 != 0) goto L_0x0053
            r3 = r0[r3]     // Catch:{ Exception -> 0x00a2 }
            java.lang.String r3 = r3.trim()     // Catch:{ Exception -> 0x00a2 }
            java.lang.String r6 = java.lang.String.valueOf(r7)     // Catch:{ Exception -> 0x00a2 }
            boolean r3 = r3.equals(r6)     // Catch:{ Exception -> 0x00a2 }
            if (r3 == 0) goto L_0x0053
            r7 = 8
            r7 = r0[r7]     // Catch:{ Exception -> 0x00a2 }
            r4.close()     // Catch:{ IOException -> 0x0090 }
            r5.close()     // Catch:{ IOException -> 0x0090 }
            goto L_0x009a
        L_0x0090:
            r0 = move-exception
            java.lang.String r3 = "awcn.Utils"
            java.lang.String r4 = "getProcessNameNew "
            java.lang.Object[] r1 = new java.lang.Object[r1]
            anet.channel.util.ALog.e(r3, r4, r2, r0, r1)
        L_0x009a:
            return r7
        L_0x009b:
            r4.close()     // Catch:{ IOException -> 0x00c0 }
            r5.close()     // Catch:{ IOException -> 0x00c0 }
            goto L_0x00d1
        L_0x00a2:
            r7 = move-exception
            goto L_0x00b1
        L_0x00a4:
            r7 = move-exception
            r5 = r2
            goto L_0x00d5
        L_0x00a7:
            r7 = move-exception
            r5 = r2
            goto L_0x00b1
        L_0x00aa:
            r7 = move-exception
            r4 = r2
            r5 = r4
            goto L_0x00d5
        L_0x00ae:
            r7 = move-exception
            r4 = r2
            r5 = r4
        L_0x00b1:
            java.lang.String r0 = "awcn.Utils"
            java.lang.String r3 = "getProcessNameNew "
            java.lang.Object[] r6 = new java.lang.Object[r1]     // Catch:{ all -> 0x00d4 }
            anet.channel.util.ALog.e(r0, r3, r2, r7, r6)     // Catch:{ all -> 0x00d4 }
            if (r4 == 0) goto L_0x00c2
            r4.close()     // Catch:{ IOException -> 0x00c0 }
            goto L_0x00c2
        L_0x00c0:
            r7 = move-exception
            goto L_0x00c8
        L_0x00c2:
            if (r5 == 0) goto L_0x00d1
            r5.close()     // Catch:{ IOException -> 0x00c0 }
            goto L_0x00d1
        L_0x00c8:
            java.lang.String r0 = "awcn.Utils"
            java.lang.String r3 = "getProcessNameNew "
            java.lang.Object[] r1 = new java.lang.Object[r1]
            anet.channel.util.ALog.e(r0, r3, r2, r7, r1)
        L_0x00d1:
            java.lang.String r7 = ""
            return r7
        L_0x00d4:
            r7 = move-exception
        L_0x00d5:
            if (r4 == 0) goto L_0x00dd
            r4.close()     // Catch:{ IOException -> 0x00db }
            goto L_0x00dd
        L_0x00db:
            r0 = move-exception
            goto L_0x00e3
        L_0x00dd:
            if (r5 == 0) goto L_0x00ec
            r5.close()     // Catch:{ IOException -> 0x00db }
            goto L_0x00ec
        L_0x00e3:
            java.lang.Object[] r1 = new java.lang.Object[r1]
            java.lang.String r3 = "awcn.Utils"
            java.lang.String r4 = "getProcessNameNew "
            anet.channel.util.ALog.e(r3, r4, r2, r0, r1)
        L_0x00ec:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: anet.channel.util.Utils.getProcessNameNew(int):java.lang.String");
    }

    public static Context getAppContext() {
        if (context != null) {
            return context;
        }
        synchronized (Utils.class) {
            if (context != null) {
                Context context2 = context;
                return context2;
            }
            try {
                Class<?> cls = Class.forName("android.app.ActivityThread");
                Object invoke = cls.getMethod("currentActivityThread", new Class[0]).invoke(cls, new Object[0]);
                context = (Context) invoke.getClass().getMethod("getApplication", new Class[0]).invoke(invoke, new Object[0]);
            } catch (Exception e) {
                ALog.w(TAG, "getAppContext", (String) null, e, new Object[0]);
            }
            Context context3 = context;
            return context3;
        }
    }

    public static String getStackMsg(Throwable th) {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            StackTraceElement[] stackTrace = th.getStackTrace();
            if (stackTrace != null && stackTrace.length > 0) {
                for (StackTraceElement stackTraceElement : stackTrace) {
                    stringBuffer.append(stackTraceElement.toString() + "\n");
                }
            }
        } catch (Exception e) {
            ALog.e(TAG, "getStackMsg", (String) null, e, new Object[0]);
        }
        return stringBuffer.toString();
    }

    public static Object invokeStaticMethodThrowException(String str, String str2, Class<?>[] clsArr, Object... objArr) throws Exception {
        Method method;
        if (str == null || str2 == null) {
            return null;
        }
        Class<?> cls = Class.forName(str);
        if (clsArr != null) {
            method = cls.getDeclaredMethod(str2, clsArr);
        } else {
            method = cls.getDeclaredMethod(str2, new Class[0]);
        }
        if (method == null) {
            return null;
        }
        method.setAccessible(true);
        if (objArr != null) {
            return method.invoke(cls, objArr);
        }
        return method.invoke(cls, new Object[0]);
    }

    public static float getNetworkTimeFactor() {
        NetworkStatusHelper.NetworkStatus status = NetworkStatusHelper.getStatus();
        float f = (status == NetworkStatusHelper.NetworkStatus.G4 || status == NetworkStatusHelper.NetworkStatus.WIFI) ? 0.8f : 1.0f;
        return b.a().b() == NetworkSpeed.Fast.getCode() ? f * 0.75f : f;
    }
}

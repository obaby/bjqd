package anet.channel.strategy;

import android.content.Context;
import anet.channel.GlobalAppRuntimeInfo;
import anet.channel.statist.StrategyStatObject;
import anet.channel.util.ALog;
import anet.channel.util.SerializeHelper;
import java.io.File;
import java.io.Serializable;
import java.util.Comparator;

/* compiled from: Taobao */
class l {

    /* renamed from: a  reason: collision with root package name */
    private static File f321a;

    /* renamed from: b  reason: collision with root package name */
    private static volatile boolean f322b;

    /* renamed from: c  reason: collision with root package name */
    private static Comparator<File> f323c = new m();

    l() {
    }

    public static void a(Context context) {
        if (context != null) {
            try {
                f321a = new File(context.getFilesDir(), "awcn_strategy");
                if (!a(f321a)) {
                    ALog.e("awcn.StrategySerializeHelper", "create directory failed!!!", (String) null, "dir", f321a.getAbsolutePath());
                }
                if (!GlobalAppRuntimeInfo.isTargetProcess()) {
                    String currentProcess = GlobalAppRuntimeInfo.getCurrentProcess();
                    f321a = new File(f321a, currentProcess.substring(currentProcess.indexOf(58) + 1));
                    if (!a(f321a)) {
                        ALog.e("awcn.StrategySerializeHelper", "create directory failed!!!", (String) null, "dir", f321a.getAbsolutePath());
                    }
                }
                ALog.i("awcn.StrategySerializeHelper", "StrateyFolder", (String) null, "path", f321a.getAbsolutePath());
                if (f322b) {
                    a();
                    f322b = false;
                    return;
                }
                c();
            } catch (Throwable th) {
                ALog.e("awcn.StrategySerializeHelper", "StrategySerializeHelper initialize failed!!!", (String) null, th, new Object[0]);
            }
        }
    }

    private static boolean a(File file) {
        if (file == null || file.exists()) {
            return true;
        }
        return file.mkdir();
    }

    public static File a(String str) {
        a(f321a);
        return new File(f321a, str);
    }

    static synchronized void a() {
        synchronized (l.class) {
            ALog.i("awcn.StrategySerializeHelper", "clear start.", (String) null, new Object[0]);
            if (f321a == null) {
                ALog.w("awcn.StrategySerializeHelper", "folder path not initialized, wait to clear", (String) null, new Object[0]);
                f322b = true;
                return;
            }
            File[] listFiles = f321a.listFiles();
            if (listFiles != null) {
                for (File file : listFiles) {
                    if (file.isFile()) {
                        file.delete();
                    }
                }
                ALog.i("awcn.StrategySerializeHelper", "clear end.", (String) null, new Object[0]);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0018, code lost:
        return r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static synchronized java.io.File[] b() {
        /*
            java.lang.Class<anet.channel.strategy.l> r0 = anet.channel.strategy.l.class
            monitor-enter(r0)
            java.io.File r1 = f321a     // Catch:{ all -> 0x0019 }
            if (r1 != 0) goto L_0x000a
            r1 = 0
            monitor-exit(r0)
            return r1
        L_0x000a:
            java.io.File r1 = f321a     // Catch:{ all -> 0x0019 }
            java.io.File[] r1 = r1.listFiles()     // Catch:{ all -> 0x0019 }
            if (r1 == 0) goto L_0x0017
            java.util.Comparator<java.io.File> r2 = f323c     // Catch:{ all -> 0x0019 }
            java.util.Arrays.sort(r1, r2)     // Catch:{ all -> 0x0019 }
        L_0x0017:
            monitor-exit(r0)
            return r1
        L_0x0019:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: anet.channel.strategy.l.b():java.io.File[]");
    }

    static synchronized void c() {
        synchronized (l.class) {
            File[] b2 = b();
            if (b2 != null) {
                int i = 0;
                for (File file : b2) {
                    if (!file.isDirectory()) {
                        if (System.currentTimeMillis() - file.lastModified() > 259200000) {
                            file.delete();
                        } else if (file.getName().startsWith("WIFI")) {
                            int i2 = i + 1;
                            if (((long) i) > 10) {
                                file.delete();
                            }
                            i = i2;
                        }
                    }
                }
            }
        }
    }

    static synchronized void a(Serializable serializable, String str, StrategyStatObject strategyStatObject) {
        synchronized (l.class) {
            SerializeHelper.persist(serializable, a(str), strategyStatObject);
        }
    }

    static synchronized <T> T a(String str, StrategyStatObject strategyStatObject) {
        T restore;
        synchronized (l.class) {
            restore = SerializeHelper.restore(a(str), strategyStatObject);
        }
        return restore;
    }
}

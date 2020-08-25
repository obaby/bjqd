package com.alibaba.android.arouter.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.android.arouter.thread.DefaultPoolExecutor;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

public class ClassUtils {
    private static final String EXTRACTED_NAME_EXT = ".classes";
    private static final String EXTRACTED_SUFFIX = ".zip";
    private static final String KEY_DEX_NUMBER = "dex.number";
    private static final String PREFS_FILE = "multidex.version";
    private static final String SECONDARY_FOLDER_NAME = ("code_cache" + File.separator + "secondary-dexes");
    private static final int VM_WITH_MULTIDEX_VERSION_MAJOR = 2;
    private static final int VM_WITH_MULTIDEX_VERSION_MINOR = 1;

    private static SharedPreferences getMultiDexPreferences(Context context) {
        return context.getSharedPreferences(PREFS_FILE, Build.VERSION.SDK_INT < 11 ? 0 : 4);
    }

    public static Set<String> getFileNameByPackageName(Context context, final String str) throws PackageManager.NameNotFoundException, IOException, InterruptedException {
        final HashSet hashSet = new HashSet();
        List<String> sourcePaths = getSourcePaths(context);
        final CountDownLatch countDownLatch = new CountDownLatch(sourcePaths.size());
        for (final String next : sourcePaths) {
            DefaultPoolExecutor.getInstance().execute(new Runnable() {
                /* JADX WARNING: Code restructure failed: missing block: B:12:0x004c, code lost:
                    if (r0 != null) goto L_0x005b;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:18:0x0059, code lost:
                    if (r0 == null) goto L_0x005e;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
                    r0.close();
                 */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void run() {
                    /*
                        r4 = this;
                        r0 = 0
                        java.lang.String r1 = r2     // Catch:{ Throwable -> 0x0051 }
                        java.lang.String r2 = ".zip"
                        boolean r1 = r1.endsWith(r2)     // Catch:{ Throwable -> 0x0051 }
                        if (r1 == 0) goto L_0x0026
                        java.lang.String r1 = r2     // Catch:{ Throwable -> 0x0051 }
                        java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0051 }
                        r2.<init>()     // Catch:{ Throwable -> 0x0051 }
                        java.lang.String r3 = r2     // Catch:{ Throwable -> 0x0051 }
                        r2.append(r3)     // Catch:{ Throwable -> 0x0051 }
                        java.lang.String r3 = ".tmp"
                        r2.append(r3)     // Catch:{ Throwable -> 0x0051 }
                        java.lang.String r2 = r2.toString()     // Catch:{ Throwable -> 0x0051 }
                        r3 = 0
                        dalvik.system.DexFile r1 = dalvik.system.DexFile.loadDex(r1, r2, r3)     // Catch:{ Throwable -> 0x0051 }
                        goto L_0x002d
                    L_0x0026:
                        dalvik.system.DexFile r1 = new dalvik.system.DexFile     // Catch:{ Throwable -> 0x0051 }
                        java.lang.String r2 = r2     // Catch:{ Throwable -> 0x0051 }
                        r1.<init>(r2)     // Catch:{ Throwable -> 0x0051 }
                    L_0x002d:
                        r0 = r1
                        java.util.Enumeration r1 = com.stub.StubApp.interface12(r0)     // Catch:{ Throwable -> 0x0051 }
                    L_0x0032:
                        boolean r2 = r1.hasMoreElements()     // Catch:{ Throwable -> 0x0051 }
                        if (r2 == 0) goto L_0x004c
                        java.lang.Object r2 = r1.nextElement()     // Catch:{ Throwable -> 0x0051 }
                        java.lang.String r2 = (java.lang.String) r2     // Catch:{ Throwable -> 0x0051 }
                        java.lang.String r3 = r6     // Catch:{ Throwable -> 0x0051 }
                        boolean r3 = r2.startsWith(r3)     // Catch:{ Throwable -> 0x0051 }
                        if (r3 == 0) goto L_0x0032
                        java.util.Set r3 = r0     // Catch:{ Throwable -> 0x0051 }
                        r3.add(r2)     // Catch:{ Throwable -> 0x0051 }
                        goto L_0x0032
                    L_0x004c:
                        if (r0 == 0) goto L_0x005e
                        goto L_0x005b
                    L_0x004f:
                        r1 = move-exception
                        goto L_0x0064
                    L_0x0051:
                        r1 = move-exception
                        java.lang.String r2 = "ARouter"
                        java.lang.String r3 = "Scan map file in dex files made error."
                        android.util.Log.e(r2, r3, r1)     // Catch:{ all -> 0x004f }
                        if (r0 == 0) goto L_0x005e
                    L_0x005b:
                        r0.close()     // Catch:{ Throwable -> 0x005e }
                    L_0x005e:
                        java.util.concurrent.CountDownLatch r0 = r1
                        r0.countDown()
                        return
                    L_0x0064:
                        if (r0 == 0) goto L_0x0069
                        r0.close()     // Catch:{ Throwable -> 0x0069 }
                    L_0x0069:
                        java.util.concurrent.CountDownLatch r0 = r1
                        r0.countDown()
                        throw r1
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.alibaba.android.arouter.utils.ClassUtils.AnonymousClass1.run():void");
                }
            });
        }
        countDownLatch.await();
        Log.d("ARouter::", "Filter " + hashSet.size() + " classes by packageName <" + str + ">");
        return hashSet;
    }

    public static List<String> getSourcePaths(Context context) throws PackageManager.NameNotFoundException, IOException {
        ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0);
        File file = new File(applicationInfo.sourceDir);
        ArrayList arrayList = new ArrayList();
        arrayList.add(applicationInfo.sourceDir);
        String str = file.getName() + EXTRACTED_NAME_EXT;
        if (!isVMMultidexCapable()) {
            int i = getMultiDexPreferences(context).getInt(KEY_DEX_NUMBER, 1);
            File file2 = new File(applicationInfo.dataDir, SECONDARY_FOLDER_NAME);
            int i2 = 2;
            while (i2 <= i) {
                File file3 = new File(file2, str + i2 + EXTRACTED_SUFFIX);
                if (file3.isFile()) {
                    arrayList.add(file3.getAbsolutePath());
                    i2++;
                } else {
                    throw new IOException("Missing extracted secondary dex file '" + file3.getPath() + "'");
                }
            }
        }
        if (ARouter.debuggable()) {
            arrayList.addAll(tryLoadInstantRunDexFile(applicationInfo));
        }
        return arrayList;
    }

    private static List<String> tryLoadInstantRunDexFile(ApplicationInfo applicationInfo) {
        ArrayList arrayList = new ArrayList();
        if (Build.VERSION.SDK_INT < 21 || applicationInfo.splitSourceDirs == null) {
            try {
                File file = new File((String) Class.forName("com.android.tools.fd.runtime.Paths").getMethod("getDexFileDirectory", new Class[]{String.class}).invoke((Object) null, new Object[]{applicationInfo.packageName}));
                if (file.exists() && file.isDirectory()) {
                    for (File file2 : file.listFiles()) {
                        if (file2 != null && file2.exists() && file2.isFile() && file2.getName().endsWith(".dex")) {
                            arrayList.add(file2.getAbsolutePath());
                        }
                    }
                    Log.d("ARouter::", "Found InstantRun support");
                }
            } catch (Exception e) {
                Log.e("ARouter::", "InstantRun support error, " + e.getMessage());
            }
        } else {
            arrayList.addAll(Arrays.asList(applicationInfo.splitSourceDirs));
            Log.d("ARouter::", "Found InstantRun support");
        }
        return arrayList;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x004e, code lost:
        if (r1 < 1) goto L_0x0052;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001b, code lost:
        if (java.lang.Integer.valueOf(java.lang.System.getProperty("ro.build.version.sdk")).intValue() >= 21) goto L_0x001d;
     */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0063  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0066  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean isVMMultidexCapable() {
        /*
            r0 = 0
            r1 = 0
            boolean r2 = isYunOS()     // Catch:{ Exception -> 0x0051 }
            r3 = 1
            if (r2 == 0) goto L_0x001f
            java.lang.String r2 = "'YunOS'"
            java.lang.String r1 = "ro.build.version.sdk"
            java.lang.String r1 = java.lang.System.getProperty(r1)     // Catch:{  }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{  }
            int r1 = r1.intValue()     // Catch:{  }
            r4 = 21
            if (r1 < r4) goto L_0x0052
        L_0x001d:
            r0 = 1
            goto L_0x0052
        L_0x001f:
            java.lang.String r2 = "'Android'"
            java.lang.String r1 = "java.vm.version"
            java.lang.String r1 = java.lang.System.getProperty(r1)     // Catch:{  }
            if (r1 == 0) goto L_0x0052
            java.lang.String r4 = "(\\d+)\\.(\\d+)(\\.\\d+)?"
            java.util.regex.Pattern r4 = java.util.regex.Pattern.compile(r4)     // Catch:{  }
            java.util.regex.Matcher r1 = r4.matcher(r1)     // Catch:{  }
            boolean r4 = r1.matches()     // Catch:{  }
            if (r4 == 0) goto L_0x0052
            java.lang.String r4 = r1.group(r3)     // Catch:{ Exception -> 0x0052 }
            int r4 = java.lang.Integer.parseInt(r4)     // Catch:{ Exception -> 0x0052 }
            r5 = 2
            java.lang.String r1 = r1.group(r5)     // Catch:{ Exception -> 0x0052 }
            int r1 = java.lang.Integer.parseInt(r1)     // Catch:{ Exception -> 0x0052 }
            if (r4 > r5) goto L_0x001d
            if (r4 != r5) goto L_0x0052
            if (r1 < r3) goto L_0x0052
            goto L_0x001d
        L_0x0051:
            r2 = r1
        L_0x0052:
            java.lang.String r1 = "ARouter::"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "VM with name "
            r3.append(r4)
            r3.append(r2)
            if (r0 == 0) goto L_0x0066
            java.lang.String r2 = " has multidex support"
            goto L_0x0068
        L_0x0066:
            java.lang.String r2 = " does not have multidex support"
        L_0x0068:
            r3.append(r2)
            java.lang.String r2 = r3.toString()
            android.util.Log.i(r1, r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.android.arouter.utils.ClassUtils.isVMMultidexCapable():boolean");
    }

    private static boolean isYunOS() {
        try {
            String property = System.getProperty("ro.yunos.version");
            String property2 = System.getProperty("java.vm.name");
            if ((property2 == null || !property2.toLowerCase().contains("lemur")) && (property == null || property.trim().length() <= 0)) {
                return false;
            }
            return true;
        } catch (Exception unused) {
            return false;
        }
    }
}

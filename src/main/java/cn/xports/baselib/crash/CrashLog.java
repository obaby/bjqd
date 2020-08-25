package cn.xports.baselib.crash;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class CrashLog {
    public static final String TAG = "CrashLog";

    public static void saveCrashLog(Context context, Throwable th) {
        saveCrashInfo2File(context, th, collectDeviceInfo(context));
    }

    private static Map<String, String> collectDeviceInfo(Context context) {
        TreeMap treeMap = new TreeMap();
        try {
            treeMap.put("systemVersion", Build.VERSION.RELEASE);
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 1);
            if (packageInfo != null) {
                treeMap.put("versionName", packageInfo.versionName == null ? "null" : packageInfo.versionName);
                treeMap.put("versionCode", packageInfo.versionCode + "");
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        for (Field field : Build.class.getDeclaredFields()) {
            try {
                field.setAccessible(true);
                treeMap.put(field.getName(), field.get((Object) null).toString());
            } catch (Exception unused2) {
            }
        }
        return treeMap;
    }

    private static void saveCrashInfo2File(Context context, Throwable th, Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry next : map.entrySet()) {
            sb.append((String) next.getKey());
            sb.append("=");
            sb.append((String) next.getValue());
            sb.append("\n");
        }
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace(printWriter);
        for (Throwable cause = th.getCause(); cause != null; cause = cause.getCause()) {
            cause.printStackTrace(printWriter);
        }
        printWriter.close();
        sb.append(stringWriter.toString());
        try {
            long currentTimeMillis = System.currentTimeMillis();
            String crashLogDir = crashLogDir(context);
            new File(crashLogDir).mkdirs();
            FileOutputStream fileOutputStream = new FileOutputStream(crashLogDir + ("crash-" + new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date()) + "-" + currentTimeMillis + ".log"));
            fileOutputStream.write(sb.toString().getBytes());
            fileOutputStream.close();
        } catch (Exception unused) {
        }
    }

    public static String crashLogDir(Context context) {
        return context.getCacheDir().getPath() + File.separator + "crash" + File.separator;
    }
}

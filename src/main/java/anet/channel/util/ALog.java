package anet.channel.util;

import android.text.TextUtils;
import android.util.Log;

/* compiled from: Taobao */
public class ALog {
    private static Object LOG_BREAK = "|";
    private static boolean canUseTlog = true;
    private static boolean isPrintLog = true;
    private static volatile ILog log = logcat;
    public static Logcat logcat = new Logcat();

    /* compiled from: Taobao */
    public interface ILog {
        void d(String str, String str2);

        void e(String str, String str2);

        void e(String str, String str2, Throwable th);

        void i(String str, String str2);

        boolean isPrintLog(int i);

        boolean isValid();

        void setLogLevel(int i);

        void w(String str, String str2);

        void w(String str, String str2, Throwable th);
    }

    /* compiled from: Taobao */
    public static class Level {
        public static final int D = 1;
        public static final int E = 4;
        public static final int I = 2;
        public static final int N = 5;
        public static final int V = 0;
        public static final int W = 3;
    }

    private static String buildLogTag(String str) {
        return str;
    }

    /* compiled from: Taobao */
    public static class Logcat implements ILog {
        int defaultLevel = 1;

        public boolean isValid() {
            return true;
        }

        public void d(String str, String str2) {
            Log.d(str, str2);
        }

        public void i(String str, String str2) {
            Log.i(str, str2);
        }

        public void w(String str, String str2) {
            Log.w(str, str2);
        }

        public void w(String str, String str2, Throwable th) {
            Log.w(str, str2, th);
        }

        public void e(String str, String str2) {
            Log.e(str, str2);
        }

        public void e(String str, String str2, Throwable th) {
            Log.e(str, str2, th);
        }

        public boolean isPrintLog(int i) {
            return i >= this.defaultLevel;
        }

        public void setLogLevel(int i) {
            if (i < 0 || i > 5) {
                this.defaultLevel = 5;
            } else {
                this.defaultLevel = i;
            }
        }
    }

    public static void setLog(ILog iLog) {
        if (iLog != null) {
            if ((canUseTlog || !iLog.getClass().getSimpleName().toLowerCase().contains("tlog")) && iLog.isValid()) {
                log = iLog;
            }
        }
    }

    public static ILog getLog() {
        return log;
    }

    public static void setPrintLog(boolean z) {
        isPrintLog = z;
    }

    public static void setLevel(int i) {
        if (log != null) {
            log.setLogLevel(i);
        }
    }

    public static boolean isPrintLog(int i) {
        if (isPrintLog && log != null) {
            return log.isPrintLog(i);
        }
        return false;
    }

    public static void d(String str, String str2, String str3, Object... objArr) {
        if (isPrintLog(1) && log != null) {
            log.d(buildLogTag(str), buildLogMsg(str2, str3, objArr));
        }
    }

    public static void i(String str, String str2, String str3, Object... objArr) {
        if (isPrintLog(2) && log != null) {
            log.i(buildLogTag(str), buildLogMsg(str2, str3, objArr));
        }
    }

    public static void w(String str, String str2, String str3, Object... objArr) {
        if (isPrintLog(3) && log != null) {
            log.w(buildLogTag(str), buildLogMsg(str2, str3, objArr));
        }
    }

    public static void w(String str, String str2, String str3, Throwable th, Object... objArr) {
        if (isPrintLog(3) && log != null) {
            log.w(buildLogTag(str), buildLogMsg(str2, str3, objArr), th);
        }
    }

    public static void e(String str, String str2, String str3, Object... objArr) {
        if (isPrintLog(4) && log != null) {
            log.e(buildLogTag(str), buildLogMsg(str2, str3, objArr));
        }
    }

    public static void e(String str, String str2, String str3, Throwable th, Object... objArr) {
        if (isPrintLog(4) && log != null) {
            log.e(buildLogTag(str), buildLogMsg(str2, str3, objArr), th);
        }
    }

    private static String buildLogMsg(String str, String str2, Object... objArr) {
        if (str == null && str2 == null && objArr == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder(64);
        if (!TextUtils.isEmpty(str2)) {
            sb.append(LOG_BREAK);
            sb.append("[seq:");
            sb.append(str2);
            sb.append("]");
        }
        if (str != null) {
            sb.append(" ");
            sb.append(str);
        }
        if (objArr != null) {
            int i = 0;
            while (true) {
                int i2 = i + 1;
                if (i2 >= objArr.length) {
                    break;
                }
                sb.append(" ");
                sb.append(objArr[i] != null ? objArr[i] : "");
                sb.append(":");
                sb.append(objArr[i2] != null ? objArr[i2] : "");
                i += 2;
            }
            if (i < objArr.length) {
                sb.append(" ");
                sb.append(objArr[i]);
            }
        }
        return sb.toString();
    }

    @Deprecated
    public static void setUseTlog(boolean z) {
        if (!z) {
            canUseTlog = false;
            log = logcat;
            return;
        }
        canUseTlog = true;
    }
}

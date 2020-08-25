package cn.qqtheme.framework.util;

import android.os.Debug;
import android.os.Environment;
import android.util.Log;
import cn.qqtheme.framework.AppConfig;
import com.alibaba.android.arouter.utils.Consts;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;

public final class LogUtils {
    private static final int MAX_STACK_TRACE_SIZE = 131071;
    private static final int METHOD_COUNT = 2;
    private static final int MIN_STACK_OFFSET = 3;
    private static String debugTag = AppConfig.DEBUG_TAG;
    private static boolean isDebug = AppConfig.DEBUG_ENABLE;

    public static void verbose(String str) {
        verbose("", str);
    }

    public static void verbose(Object obj, String str) {
        verbose(obj.getClass().getSimpleName(), str);
    }

    public static void verbose(String str, String str2) {
        if (isDebug) {
            StringBuilder sb = new StringBuilder();
            sb.append(debugTag);
            sb.append((str == null || str.trim().length() == 0) ? "" : "-");
            sb.append(str);
            String sb2 = sb.toString();
            String str3 = str2 + getTraceElement();
            try {
                Log.v(sb2, str3);
            } catch (Exception unused) {
                System.out.println(sb2 + ">>>" + str3);
            }
        }
    }

    public static void debug(String str) {
        debug("", str);
    }

    public static void debug(Object obj, String str) {
        debug(obj.getClass().getSimpleName(), str);
    }

    public static void debug(String str, String str2) {
        if (isDebug) {
            StringBuilder sb = new StringBuilder();
            sb.append(debugTag);
            sb.append((str == null || str.trim().length() == 0) ? "" : "-");
            sb.append(str);
            String sb2 = sb.toString();
            String str3 = str2 + getTraceElement();
            try {
                Log.d(sb2, str3);
            } catch (Exception unused) {
                System.out.println(sb2 + ">>>" + str3);
            }
        }
    }

    public static void warn(Throwable th) {
        warn(toStackTraceString(th));
    }

    public static void warn(String str) {
        warn("", str);
    }

    public static void warn(Object obj, String str) {
        warn(obj.getClass().getSimpleName(), str);
    }

    public static void warn(Object obj, Throwable th) {
        warn(obj.getClass().getSimpleName(), toStackTraceString(th));
    }

    public static void warn(String str, String str2) {
        if (isDebug) {
            StringBuilder sb = new StringBuilder();
            sb.append(debugTag);
            sb.append((str == null || str.trim().length() == 0) ? "" : "-");
            sb.append(str);
            String sb2 = sb.toString();
            String str3 = str2 + getTraceElement();
            try {
                Log.w(sb2, str3);
            } catch (Exception unused) {
                System.out.println(sb2 + ">>>" + str3);
            }
        }
    }

    public static void error(Throwable th) {
        error(toStackTraceString(th));
    }

    public static void error(String str) {
        error("", str);
    }

    public static void error(Object obj, String str) {
        error(obj.getClass().getSimpleName(), str);
    }

    public static void error(Object obj, Throwable th) {
        error(obj.getClass().getSimpleName(), toStackTraceString(th));
    }

    public static void error(String str, String str2) {
        if (isDebug) {
            StringBuilder sb = new StringBuilder();
            sb.append(debugTag);
            sb.append((str == null || str.trim().length() == 0) ? "" : "-");
            sb.append(str);
            String sb2 = sb.toString();
            String str3 = str2 + getTraceElement();
            try {
                Log.e(sb2, str3);
            } catch (Exception unused) {
                System.err.println(sb2 + ">>>" + str3);
            }
        }
    }

    public static void startMethodTracing() {
        if (isDebug) {
            Debug.startMethodTracing(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + debugTag + ".trace");
        }
    }

    public static void stopMethodTracing() {
        if (isDebug) {
            Debug.stopMethodTracing();
        }
    }

    public static String toStackTraceString(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        String stringWriter2 = stringWriter.toString();
        if (stringWriter2.length() <= MAX_STACK_TRACE_SIZE) {
            return stringWriter2;
        }
        return stringWriter2.substring(0, MAX_STACK_TRACE_SIZE - " [stack trace too large]".length()) + " [stack trace too large]";
    }

    private static String getTraceElement() {
        int i = 2;
        try {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            int _getStackOffset = _getStackOffset(stackTrace);
            if (_getStackOffset + 2 > stackTrace.length) {
                i = (stackTrace.length - _getStackOffset) - 1;
            }
            String str = "    ";
            StringBuilder sb = new StringBuilder();
            while (i > 0) {
                int i2 = i + _getStackOffset;
                if (i2 < stackTrace.length) {
                    sb.append("\n");
                    sb.append(str);
                    sb.append(_getSimpleClassName(stackTrace[i2].getClassName()));
                    sb.append(Consts.DOT);
                    sb.append(stackTrace[i2].getMethodName());
                    sb.append(" ");
                    sb.append("(");
                    sb.append(stackTrace[i2].getFileName());
                    sb.append(":");
                    sb.append(stackTrace[i2].getLineNumber());
                    sb.append(")");
                    str = str + "    ";
                }
                i--;
            }
            return sb.toString();
        } catch (Exception unused) {
            return "";
        }
    }

    private static int _getStackOffset(StackTraceElement[] stackTraceElementArr) {
        for (int i = 3; i < stackTraceElementArr.length; i++) {
            if (!stackTraceElementArr[i].getClassName().equals(LogUtils.class.getName())) {
                return i - 1;
            }
        }
        return -1;
    }

    private static String _getSimpleClassName(String str) {
        return str.substring(str.lastIndexOf(Consts.DOT) + 1);
    }
}

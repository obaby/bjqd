package cn.xports.baselib.crash;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import cn.xports.baselib.crash.compat.ActivityKillerV15_V20;
import cn.xports.baselib.crash.compat.ActivityKillerV21_V23;
import cn.xports.baselib.crash.compat.ActivityKillerV24_V25;
import cn.xports.baselib.crash.compat.ActivityKillerV26;
import cn.xports.baselib.crash.compat.ActivityKillerV28;
import cn.xports.baselib.crash.compat.IActivityKiller;
import java.lang.Thread;
import java.lang.reflect.Field;
import me.weishu.reflection.Reflection;

public final class Cockroach {
    /* access modifiers changed from: private */
    public static IActivityKiller sActivityKiller;
    /* access modifiers changed from: private */
    public static ExceptionHandler sExceptionHandler;
    private static boolean sInstalled;
    private static boolean sIsSafeMode;

    private Cockroach() {
    }

    public static void install(Context context, ExceptionHandler exceptionHandler) {
        if (!sInstalled) {
            try {
                Reflection.unseal(context);
            } catch (Throwable th) {
                th.printStackTrace();
            }
            sInstalled = true;
            sExceptionHandler = exceptionHandler;
            initActivityKiller();
            Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                public void uncaughtException(Thread thread, Throwable th) {
                    if (Cockroach.sExceptionHandler != null) {
                        Cockroach.sExceptionHandler.uncaughtExceptionHappened(thread, th);
                    }
                    if (thread == Looper.getMainLooper().getThread()) {
                        Cockroach.isChoreographerException(th);
                        Cockroach.safeMode();
                    }
                }
            });
        }
    }

    private static void initActivityKiller() {
        if (Build.VERSION.SDK_INT >= 28) {
            sActivityKiller = new ActivityKillerV28();
        } else if (Build.VERSION.SDK_INT >= 26) {
            sActivityKiller = new ActivityKillerV26();
        } else if (Build.VERSION.SDK_INT == 25 || Build.VERSION.SDK_INT == 24) {
            sActivityKiller = new ActivityKillerV24_V25();
        } else if (Build.VERSION.SDK_INT >= 21 && Build.VERSION.SDK_INT <= 23) {
            sActivityKiller = new ActivityKillerV21_V23();
        } else if (Build.VERSION.SDK_INT >= 15 && Build.VERSION.SDK_INT <= 20) {
            sActivityKiller = new ActivityKillerV15_V20();
        } else if (Build.VERSION.SDK_INT < 15) {
            sActivityKiller = new ActivityKillerV15_V20();
        }
        try {
            hookmH();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private static void hookmH() throws Exception {
        Class<?> cls = Class.forName("android.app.ActivityThread");
        Object invoke = cls.getDeclaredMethod("currentActivityThread", new Class[0]).invoke((Object) null, new Object[0]);
        Field declaredField = cls.getDeclaredField("mH");
        declaredField.setAccessible(true);
        final Handler handler = (Handler) declaredField.get(invoke);
        Field declaredField2 = Handler.class.getDeclaredField("mCallback");
        declaredField2.setAccessible(true);
        declaredField2.set(handler, new Handler.Callback() {
            public boolean handleMessage(Message message) {
                if (Build.VERSION.SDK_INT < 28) {
                    int i = message.what;
                    if (i == 104) {
                        try {
                            handler.handleMessage(message);
                        } catch (Throwable th) {
                            Cockroach.sActivityKiller.finishStopActivity(message);
                            Cockroach.notifyException(th);
                        }
                        return true;
                    } else if (i == 107) {
                        try {
                            handler.handleMessage(message);
                        } catch (Throwable th2) {
                            Cockroach.sActivityKiller.finishResumeActivity(message);
                            Cockroach.notifyException(th2);
                        }
                        return true;
                    } else if (i != 109) {
                        switch (i) {
                            case 100:
                                try {
                                    handler.handleMessage(message);
                                } catch (Throwable th3) {
                                    Cockroach.sActivityKiller.finishLaunchActivity(message);
                                    Cockroach.notifyException(th3);
                                }
                                return true;
                            case 101:
                                try {
                                    handler.handleMessage(message);
                                } catch (Throwable th4) {
                                    Cockroach.sActivityKiller.finishPauseActivity(message);
                                    Cockroach.notifyException(th4);
                                }
                                return true;
                            case 102:
                                try {
                                    handler.handleMessage(message);
                                } catch (Throwable th5) {
                                    Cockroach.sActivityKiller.finishPauseActivity(message);
                                    Cockroach.notifyException(th5);
                                }
                                return true;
                            default:
                                return false;
                        }
                    } else {
                        try {
                            handler.handleMessage(message);
                        } catch (Throwable th6) {
                            Cockroach.notifyException(th6);
                        }
                        return true;
                    }
                } else if (message.what != 159) {
                    return false;
                } else {
                    try {
                        handler.handleMessage(message);
                    } catch (Throwable th7) {
                        Cockroach.sActivityKiller.finishLaunchActivity(message);
                        Cockroach.notifyException(th7);
                    }
                    return true;
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public static void notifyException(Throwable th) {
        if (sExceptionHandler != null) {
            if (isSafeMode()) {
                sExceptionHandler.bandageExceptionHappened(th);
                return;
            }
            sExceptionHandler.uncaughtExceptionHappened(Looper.getMainLooper().getThread(), th);
            safeMode();
        }
    }

    public static boolean isSafeMode() {
        return sIsSafeMode;
    }

    /* access modifiers changed from: private */
    public static void safeMode() {
        sIsSafeMode = true;
        if (sExceptionHandler != null) {
            sExceptionHandler.enterSafeMode();
        }
        while (true) {
            try {
                Looper.loop();
            } catch (Throwable th) {
                isChoreographerException(th);
                if (sExceptionHandler != null) {
                    sExceptionHandler.bandageExceptionHappened(th);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public static void isChoreographerException(Throwable th) {
        StackTraceElement[] stackTrace;
        if (th != null && sExceptionHandler != null && (stackTrace = th.getStackTrace()) != null) {
            int length = stackTrace.length;
            while (true) {
                length--;
                if (length > -1 && stackTrace.length - length <= 20) {
                    StackTraceElement stackTraceElement = stackTrace[length];
                    if ("android.view.Choreographer".equals(stackTraceElement.getClassName()) && "Choreographer.java".equals(stackTraceElement.getFileName()) && "doFrame".equals(stackTraceElement.getMethodName())) {
                        sExceptionHandler.mayBeBlackScreen(th);
                        return;
                    }
                } else {
                    return;
                }
            }
        }
    }
}

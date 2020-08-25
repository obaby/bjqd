package cn.xports.baselib.crash;

public abstract class ExceptionHandler {
    /* access modifiers changed from: protected */
    public abstract void onBandageExceptionHappened(Throwable th);

    /* access modifiers changed from: protected */
    public abstract void onEnterSafeMode();

    /* access modifiers changed from: protected */
    public void onMayBeBlackScreen(Throwable th) {
    }

    /* access modifiers changed from: protected */
    public abstract void onUncaughtExceptionHappened(Thread thread, Throwable th);

    /* access modifiers changed from: package-private */
    public final void uncaughtExceptionHappened(Thread thread, Throwable th) {
        try {
            onUncaughtExceptionHappened(thread, th);
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
    }

    /* access modifiers changed from: package-private */
    public final void bandageExceptionHappened(Throwable th) {
        try {
            onBandageExceptionHappened(th);
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
    }

    /* access modifiers changed from: package-private */
    public final void enterSafeMode() {
        try {
            onEnterSafeMode();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* access modifiers changed from: package-private */
    public final void mayBeBlackScreen(Throwable th) {
        try {
            onMayBeBlackScreen(th);
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
    }
}

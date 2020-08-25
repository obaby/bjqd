package cn.xports.baselib.crash.compat;

import android.app.ActivityManager;
import android.app.servertransaction.ClientTransaction;
import android.content.Intent;
import android.os.IBinder;
import android.os.Message;
import java.lang.reflect.Method;

public class ActivityKillerV28 implements IActivityKiller {
    public void finishPauseActivity(Message message) {
    }

    public void finishResumeActivity(Message message) {
    }

    public void finishStopActivity(Message message) {
    }

    public void finishLaunchActivity(Message message) {
        try {
            tryFinish1(message);
        } catch (Throwable th) {
            th.printStackTrace();
            try {
                tryFinish3(message);
            } catch (Throwable th2) {
                th2.printStackTrace();
            }
        }
    }

    private void tryFinish1(Message message) throws Throwable {
        finish(((ClientTransaction) message.obj).getActivityToken());
    }

    private void tryFinish3(Message message) throws Throwable {
        Object obj = message.obj;
        finish((IBinder) obj.getClass().getDeclaredField("mActivityToken").get(obj));
    }

    private void tryFinish2(Message message) throws Throwable {
        Object obj = message.obj;
        finish((IBinder) obj.getClass().getDeclaredMethod("getActivityToken", new Class[0]).invoke(obj, new Object[0]));
    }

    private void finish(IBinder iBinder) throws Exception {
        Object invoke = ActivityManager.class.getDeclaredMethod("getService", new Class[0]).invoke((Object) null, new Object[0]);
        Method declaredMethod = invoke.getClass().getDeclaredMethod("finishActivity", new Class[]{IBinder.class, Integer.TYPE, Intent.class, Integer.TYPE});
        declaredMethod.setAccessible(true);
        declaredMethod.invoke(invoke, new Object[]{iBinder, 0, null, 0});
    }
}

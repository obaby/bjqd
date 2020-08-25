package cn.xports.baselib.crash.compat;

import android.app.ActivityManager;
import android.content.Intent;
import android.os.IBinder;
import android.os.Message;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ActivityKillerV26 implements IActivityKiller {
    public void finishLaunchActivity(Message message) {
        try {
            Object obj = message.obj;
            Field declaredField = obj.getClass().getDeclaredField("token");
            declaredField.setAccessible(true);
            finish((IBinder) declaredField.get(obj));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void finishResumeActivity(Message message) {
        finishSomeArgs(message);
    }

    public void finishPauseActivity(Message message) {
        finishSomeArgs(message);
    }

    public void finishStopActivity(Message message) {
        finishSomeArgs(message);
    }

    private void finishSomeArgs(Message message) {
        try {
            Object obj = message.obj;
            Field declaredField = obj.getClass().getDeclaredField("arg1");
            declaredField.setAccessible(true);
            finish((IBinder) declaredField.get(obj));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void finish(IBinder iBinder) throws Exception {
        Object invoke = ActivityManager.class.getDeclaredMethod("getService", new Class[0]).invoke((Object) null, new Object[0]);
        Method declaredMethod = invoke.getClass().getDeclaredMethod("finishActivity", new Class[]{IBinder.class, Integer.TYPE, Intent.class, Integer.TYPE});
        declaredMethod.setAccessible(true);
        declaredMethod.invoke(invoke, new Object[]{iBinder, 0, null, 0});
    }
}

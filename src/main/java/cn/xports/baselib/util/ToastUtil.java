package cn.xports.baselib.util;

import android.app.Activity;
import android.app.AppOpsManager;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.view.WindowManager;
import android.widget.Toast;
import cn.xports.baselib.App;
import com.stub.StubApp;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class ToastUtil extends Handler {
    private Activity activity;
    private boolean isShow = false;
    private ArrayList<String> msgList = new ArrayList<>();
    private Toast toast;

    public ToastUtil(Activity activity2) {
        this.activity = activity2;
    }

    public static void showMsg(String str) {
        Toast.makeText(App.getInstance(), str, 0).show();
    }

    private static boolean isNotificationEnabled(Context context) {
        if (Build.VERSION.SDK_INT >= 24) {
            return ((NotificationManager) context.getSystemService("notification")).areNotificationsEnabled();
        }
        if (Build.VERSION.SDK_INT < 19) {
            return true;
        }
        AppOpsManager appOpsManager = (AppOpsManager) context.getSystemService("appops");
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        String packageName = StubApp.getOrigApplicationContext(context.getApplicationContext()).getPackageName();
        int i = applicationInfo.uid;
        try {
            Class<?> cls = Class.forName(AppOpsManager.class.getName());
            if (((Integer) cls.getMethod("checkOpNoThrow", new Class[]{Integer.TYPE, Integer.TYPE, String.class}).invoke(appOpsManager, new Object[]{Integer.valueOf(((Integer) cls.getDeclaredField("OP_POST_NOTIFICATION").get(Integer.class)).intValue()), Integer.valueOf(i), packageName})).intValue() == 0) {
                return true;
            }
            return false;
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException | NoSuchMethodException | RuntimeException | InvocationTargetException unused) {
            return true;
        }
    }

    public void show(String str) {
        if (isNotificationEnabled(this.activity)) {
            Toast.makeText(this.activity, str, 0).show();
            return;
        }
        if (this.toast == null) {
            this.toast = Toast.makeText(this.activity, "", 0);
        }
        this.msgList.add(str);
        showMyTOat();
    }

    private void showMyTOat() {
        if (!this.msgList.isEmpty() && this.toast != null) {
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            layoutParams.height = -2;
            layoutParams.width = -2;
            layoutParams.format = -3;
            layoutParams.windowAnimations = 16973828;
            layoutParams.flags = 152;
            layoutParams.packageName = this.activity.getPackageName();
            layoutParams.gravity = this.toast.getGravity();
            layoutParams.x = this.toast.getXOffset();
            layoutParams.y = this.toast.getYOffset();
            if (this.isShow) {
                removeMessages(20202);
            } else {
                try {
                    this.activity.getWindowManager().addView(this.toast.getView(), layoutParams);
                    this.isShow = true;
                } catch (Exception unused) {
                }
            }
            if (!this.msgList.isEmpty()) {
                this.toast.setText(this.msgList.get(this.msgList.size() - 1));
                this.msgList.remove(0);
            }
            if (this.isShow) {
                sendEmptyMessageDelayed(20202, this.toast.getDuration() == 0 ? 2500 : 3000);
            }
        }
    }

    public void handleMessage(Message message) {
        if (message.what == 20202) {
            cancelMyTOst();
        }
    }

    private void cancelMyTOst() {
        removeMessages(20202);
        if (this.isShow) {
            if (this.toast != null) {
                try {
                    this.activity.getWindowManager().removeViewImmediate(this.toast.getView());
                } catch (Exception unused) {
                }
            }
            this.isShow = false;
        }
    }
}

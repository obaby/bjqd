package cn.xports.stepCountUtils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class StepShutdownReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() != null && intent.getAction().equals("android.intent.action.ACTION_SHUTDOWN")) {
            StepSPHelper.setShutdown(context, true);
        }
    }
}

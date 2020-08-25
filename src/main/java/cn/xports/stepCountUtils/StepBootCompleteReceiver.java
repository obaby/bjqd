package cn.xports.stepCountUtils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class StepBootCompleteReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() != null && intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            StepLiveService.startLive(true, false);
        }
    }
}

package cn.xports.stepCountUtils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class StepZeroAlarmReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() != null && intent.getAction().equals("alarm_0_separate")) {
            StepLiveService.startLive(false, true);
        }
    }
}

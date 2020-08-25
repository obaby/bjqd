package cn.xports.stepCountUtils;

import android.content.Context;

public class StepUtil {
    public static boolean isSupportStep(Context context) {
        return StepSPHelper.getSupportStep(context);
    }

    public static int getTodayStep(Context context) {
        return (int) StepSPHelper.getCurrentStep(context);
    }
}

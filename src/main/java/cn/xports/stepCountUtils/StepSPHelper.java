package cn.xports.stepCountUtils;

import android.content.Context;

public class StepSPHelper {
    private static final String CLEAN_STEP = "clean_step";
    private static final String CURR_STEP = "curr_step";
    private static final String ELAPSED_REAL_TIME = "elapsed_real_time";
    private static final String IS_SUPPORT_STEP = "is_support_step";
    private static final String LAST_SENSOR_TIME = "last_sensor_time";
    private static final String SHUTDOWN = "shutdown";
    private static final String STEP_OFFSET = "step_offset";
    private static final String STEP_TODAY = "step_today";

    protected static void setLastSensorStep(Context context, float f) {
        StepSharedPreferencesUtil.setParam(context, LAST_SENSOR_TIME, Float.valueOf(f));
    }

    protected static float getLastSensorStep(Context context) {
        return ((Float) StepSharedPreferencesUtil.getParam(context, LAST_SENSOR_TIME, Float.valueOf(0.0f))).floatValue();
    }

    protected static void setStepOffset(Context context, float f) {
        StepSharedPreferencesUtil.setParam(context, STEP_OFFSET, Float.valueOf(f));
    }

    protected static float getStepOffset(Context context) {
        return ((Float) StepSharedPreferencesUtil.getParam(context, STEP_OFFSET, Float.valueOf(0.0f))).floatValue();
    }

    protected static void setStepToday(Context context, String str) {
        StepSharedPreferencesUtil.setParam(context, STEP_TODAY, str);
    }

    protected static String getStepToday(Context context) {
        return (String) StepSharedPreferencesUtil.getParam(context, STEP_TODAY, "");
    }

    protected static void setCleanStep(Context context, boolean z) {
        StepSharedPreferencesUtil.setParam(context, CLEAN_STEP, Boolean.valueOf(z));
    }

    protected static boolean getCleanStep(Context context) {
        return ((Boolean) StepSharedPreferencesUtil.getParam(context, CLEAN_STEP, true)).booleanValue();
    }

    protected static void setCurrentStep(Context context, float f) {
        StepSharedPreferencesUtil.setParam(context, CURR_STEP, Float.valueOf(f));
    }

    protected static float getCurrentStep(Context context) {
        return ((Float) StepSharedPreferencesUtil.getParam(context, CURR_STEP, Float.valueOf(0.0f))).floatValue();
    }

    protected static void setShutdown(Context context, boolean z) {
        StepSharedPreferencesUtil.setParam(context, SHUTDOWN, Boolean.valueOf(z));
    }

    protected static boolean getShutdown(Context context) {
        return ((Boolean) StepSharedPreferencesUtil.getParam(context, SHUTDOWN, false)).booleanValue();
    }

    protected static void setElapsedRealTime(Context context, long j) {
        StepSharedPreferencesUtil.setParam(context, ELAPSED_REAL_TIME, Long.valueOf(j));
    }

    protected static long getElapsedRealTime(Context context) {
        return ((Long) StepSharedPreferencesUtil.getParam(context, ELAPSED_REAL_TIME, 0L)).longValue();
    }

    protected static void setSupportStep(Context context, boolean z) {
        StepSharedPreferencesUtil.setParam(context, IS_SUPPORT_STEP, Boolean.valueOf(z));
    }

    protected static boolean getSupportStep(Context context) {
        return ((Boolean) StepSharedPreferencesUtil.getParam(context, IS_SUPPORT_STEP, false)).booleanValue();
    }
}

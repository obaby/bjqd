package cn.xports.stepCountUtils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.SystemClock;

public class StepCounter implements SensorEventListener {
    private Context mContext;
    private boolean mIsBoot;
    private boolean mIsCleanStep;
    private boolean mIsCounterStepReset = true;
    private boolean mIsSeparate;
    private boolean mIsShutdown;
    private String mTodayDate;
    private int sCurrStep;
    private int sOffsetStep;

    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public StepCounter(Context context, boolean z, boolean z2) {
        this.mContext = context;
        this.mIsSeparate = z;
        this.mIsBoot = z2;
        this.sCurrStep = (int) StepSPHelper.getCurrentStep(this.mContext);
        this.mIsCleanStep = StepSPHelper.getCleanStep(this.mContext);
        this.mTodayDate = StepSPHelper.getStepToday(this.mContext);
        this.sOffsetStep = (int) StepSPHelper.getStepOffset(this.mContext);
        this.mIsShutdown = StepSPHelper.getShutdown(this.mContext);
        boolean shutdownBySystemRunningTime = shutdownBySystemRunningTime();
        if (this.mIsBoot || shutdownBySystemRunningTime || this.mIsShutdown) {
            this.mIsShutdown = true;
            StepSPHelper.setShutdown(this.mContext, true);
        }
        initBroadcastReceiver();
        dateChangeCleanStep();
    }

    private void initBroadcastReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.TIME_TICK");
        intentFilter.addAction("android.intent.action.TIME_SET");
        intentFilter.addAction("android.intent.action.DATE_CHANGED");
        this.mContext.registerReceiver(new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                if ("android.intent.action.TIME_TICK".equals(intent.getAction()) || "android.intent.action.TIME_SET".equals(intent.getAction()) || "android.intent.action.DATE_CHANGED".equals(intent.getAction())) {
                    StepCounter.this.dateChangeCleanStep();
                }
            }
        }, intentFilter);
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == 19) {
            int i = (int) sensorEvent.values[0];
            if (this.mIsCleanStep) {
                cleanStep(i);
            } else if (this.mIsShutdown || shutdownByCounterStep(i)) {
                shutdown(i);
            }
            this.sCurrStep = i - this.sOffsetStep;
            if (this.sCurrStep < 0) {
                cleanStep(i);
            }
            StepSPHelper.setCurrentStep(this.mContext, (float) this.sCurrStep);
            StepSPHelper.setElapsedRealTime(this.mContext, SystemClock.elapsedRealtime());
            StepSPHelper.setLastSensorStep(this.mContext, (float) i);
            dateChangeCleanStep();
        }
    }

    private void cleanStep(int i) {
        this.sCurrStep = 0;
        this.sOffsetStep = i;
        StepSPHelper.setStepOffset(this.mContext, (float) this.sOffsetStep);
        this.mIsCleanStep = false;
        StepSPHelper.setCleanStep(this.mContext, false);
    }

    private void shutdown(int i) {
        this.sOffsetStep = i - ((int) StepSPHelper.getCurrentStep(this.mContext));
        StepSPHelper.setStepOffset(this.mContext, (float) this.sOffsetStep);
        this.mIsShutdown = false;
        StepSPHelper.setShutdown(this.mContext, false);
    }

    private boolean shutdownByCounterStep(int i) {
        if (this.mIsCounterStepReset) {
            this.mIsCounterStepReset = false;
            if (((float) i) < StepSPHelper.getLastSensorStep(this.mContext)) {
                return true;
            }
        }
        return false;
    }

    private boolean shutdownBySystemRunningTime() {
        return StepSPHelper.getElapsedRealTime(this.mContext) > SystemClock.elapsedRealtime();
    }

    /* access modifiers changed from: private */
    public synchronized void dateChangeCleanStep() {
        if (!getTodayDate().equals(this.mTodayDate) || this.mIsSeparate) {
            this.mIsCleanStep = true;
            StepSPHelper.setCleanStep(this.mContext, true);
            this.mTodayDate = getTodayDate();
            StepSPHelper.setStepToday(this.mContext, this.mTodayDate);
            this.mIsShutdown = false;
            StepSPHelper.setShutdown(this.mContext, false);
            this.mIsBoot = false;
            this.mIsSeparate = false;
            this.sCurrStep = 0;
            StepSPHelper.setCurrentStep(this.mContext, (float) this.sCurrStep);
        }
    }

    private String getTodayDate() {
        return StepDateUtils.getCurrentDate("yyyy-MM-dd");
    }

    public void setZeroAndBoot(boolean z, boolean z2) {
        this.mIsSeparate = z;
        this.mIsBoot = z2;
    }
}

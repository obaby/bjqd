package cn.xports.stepCountUtils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v4.app.JobIntentService;
import android.support.v4.app.NotificationCompat;
import cn.xports.qdplugin.R;
import com.blankj.utilcode.util.Utils;
import com.stub.StubApp;
import java.util.Calendar;
import java.util.TimeZone;

public class StepLiveService extends JobIntentService {
    private static final String CHANNEL_ID = "xprchannelId";
    private static final String CHANNEL_NAME = "xprchannelName";
    public static final String INTENT_ALARM_0_SEPARATE = "intent_alarm_0_separate";
    public static final String INTENT_BOOT_COMPLETED = "intent_boot_completed";
    private static final int SAMPLING_PERIOD_US = 0;
    private int alarmCount;
    private boolean mIsBoot = false;
    private boolean mIsSeparate = false;
    private MediaPlayer mMediaPlayer;
    private SensorManager mSensorManager;
    private StepCounter mStepCounter;

    public void onCreate() {
        super.onCreate();
        this.mSensorManager = (SensorManager) getSystemService("sensor");
        initAlarm();
    }

    public static void startLive() {
        enqueueWork((Context) Utils.getApp(), StepLiveService.class, 10101, new Intent());
    }

    public static void startLive(boolean z, boolean z2) {
        Intent intent = new Intent();
        intent.putExtra("intent_boot_completed", z).putExtra("intent_alarm_0_separate", z2);
        enqueueWork((Context) Utils.getApp(), StepLiveService.class, 10101, intent);
    }

    public void onStart(Intent intent, int i) {
        if (intent != null) {
            this.mIsSeparate = intent.getBooleanExtra("intent_alarm_0_separate", false);
            this.mIsBoot = intent.getBooleanExtra("intent_boot_completed", false);
        }
        super.onStart(intent, i);
    }

    /* access modifiers changed from: protected */
    public void onHandleWork(@NonNull Intent intent) {
        this.mIsSeparate = intent.getBooleanExtra("intent_alarm_0_separate", false);
        this.mIsBoot = intent.getBooleanExtra("intent_boot_completed", false);
        startStepDetector();
        if (this.mMediaPlayer == null || !this.mMediaPlayer.isPlaying()) {
            this.mMediaPlayer = MediaPlayer.create(StubApp.getOrigApplicationContext(getApplicationContext()), R.raw.no_notice);
            this.mMediaPlayer.setLooping(true);
            this.mMediaPlayer.start();
        }
    }

    private void startStepDetector() {
        if (Build.VERSION.SDK_INT < 19 || !getStepCounter()) {
            StepSPHelper.setSupportStep(this, false);
        } else {
            addStepCounterListener();
        }
    }

    private boolean getStepCounter() {
        return this.mSensorManager.getDefaultSensor(19) != null && getPackageManager().hasSystemFeature("android.hardware.sensor.stepcounter");
    }

    private void addStepCounterListener() {
        StepSPHelper.setSupportStep(this, true);
        if (this.mStepCounter != null) {
            this.mStepCounter.setZeroAndBoot(this.mIsSeparate, this.mIsBoot);
            return;
        }
        Sensor defaultSensor = this.mSensorManager.getDefaultSensor(19);
        this.mStepCounter = new StepCounter(StubApp.getOrigApplicationContext(getApplicationContext()), this.mIsSeparate, this.mIsBoot);
        this.mSensorManager.registerListener(this.mStepCounter, defaultSensor, 0);
    }

    public void initAlarm() {
        Intent intent = new Intent(this, StepZeroAlarmReceiver.class);
        intent.setAction("alarm_0_separate");
        int i = this.alarmCount;
        this.alarmCount = i + 1;
        PendingIntent broadcast = PendingIntent.getBroadcast(this, i, intent, 134217728);
        long elapsedRealtime = SystemClock.elapsedRealtime();
        long currentTimeMillis = System.currentTimeMillis();
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(System.currentTimeMillis());
        instance.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        instance.set(11, 0);
        instance.set(12, 0);
        instance.set(13, 0);
        instance.set(14, 0);
        long timeInMillis = instance.getTimeInMillis();
        if (currentTimeMillis > timeInMillis) {
            instance.add(5, 1);
            timeInMillis = instance.getTimeInMillis();
        }
        long j = (timeInMillis - currentTimeMillis) + elapsedRealtime;
        AlarmManager alarmManager = (AlarmManager) getSystemService(NotificationCompat.CATEGORY_ALARM);
        if (alarmManager != null) {
            alarmManager.setRepeating(2, j, 86400000, broadcast);
        }
    }
}

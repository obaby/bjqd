package cn.xports.stepCountUtils;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import cn.xports.qdplugin.R;
import com.stub.StubApp;
import java.util.Calendar;
import java.util.TimeZone;

public class StepService extends Service {
    private static final String CHANNEL_ID = "xprchannelId";
    private static final String CHANNEL_NAME = "xprchannelName";
    public static final String INTENT_ALARM_0_SEPARATE = "intent_alarm_0_separate";
    public static final String INTENT_BOOT_COMPLETED = "intent_boot_completed";
    private static final int SAMPLING_PERIOD_US = 0;
    private int alarmCount;
    private boolean mIsBoot = false;
    private boolean mIsSeparate = false;
    private SensorManager mSensorManager;
    private StepCounter mStepCounter;
    private NotificationManager notificationManager;

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT >= 26) {
            this.notificationManager = (NotificationManager) getSystemService("notification");
            this.notificationManager.createNotificationChannel(new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, 4));
            startForeground(1, getNotification());
        }
        this.mSensorManager = (SensorManager) getSystemService("sensor");
        initAlarm();
    }

    private Notification getNotification() {
        Notification.Builder contentText = new Notification.Builder(this).setSmallIcon(R.drawable.ic_step).setContentTitle("").setContentText("计步中...");
        if (Build.VERSION.SDK_INT >= 26) {
            contentText.setChannelId(CHANNEL_ID);
        }
        return contentText.build();
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        if (intent != null) {
            this.mIsSeparate = intent.getBooleanExtra("intent_alarm_0_separate", false);
            this.mIsBoot = intent.getBooleanExtra("intent_boot_completed", false);
        }
        startStepDetector();
        return 1;
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

    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    public void onDestroy() {
        super.onDestroy();
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

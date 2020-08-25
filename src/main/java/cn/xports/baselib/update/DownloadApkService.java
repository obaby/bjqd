package cn.xports.baselib.update;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import cn.xports.baselib.event.DownloadProgressEvent;
import cn.xports.baselib.http.CommonService;
import cn.xports.baselib.http.RetrofitUtil;
import cn.xports.baselib.util.FileUtil;
import cn.xports.baselib.util.RxBus;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import java.io.File;
import okhttp3.ResponseBody;

public class DownloadApkService extends IntentService {
    /* access modifiers changed from: private */
    public static int notifyId = 23456;
    /* access modifiers changed from: private */
    public NotificationCompat.Builder builder;
    /* access modifiers changed from: private */
    public NotificationManager notificationManager;

    @Nullable
    public IBinder onBind(Intent intent) {
        return null;
    }

    public DownloadApkService() {
        super("DownloadApkService");
    }

    public static void start(Context context, String str) {
        Intent intent = new Intent(context, DownloadApkService.class);
        intent.putExtra("url", str);
        context.startService(intent);
    }

    public void onCreate() {
        super.onCreate();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    public void onHandleIntent(@Nullable Intent intent) {
        String stringExtra = intent.getStringExtra("url");
        String str = getExternalCacheDir() + File.separator + stringExtra.substring(stringExtra.lastIndexOf("/") + 1, stringExtra.length());
        if (new File(str).exists()) {
            setInstallNotification(str);
            return;
        }
        initNotification();
        setOnDownloadListener();
        downloadApk(stringExtra, str);
    }

    private void initNotification() {
        this.notificationManager = (NotificationManager) getSystemService("notification");
        this.builder = new NotificationCompat.Builder(this).setContentTitle("开始下载").setAutoCancel(true).setContentText("版本更新");
        this.notificationManager.notify(notifyId, this.builder.build());
    }

    private void setInstallNotification(String str) {
        TaskStackBuilder create = TaskStackBuilder.create(this);
        create.addNextIntent(UpdateUtil.getInstallApkIntent(str, this));
        PendingIntent pendingIntent = create.getPendingIntent(notifyId, 134217728);
        this.notificationManager = (NotificationManager) getSystemService("notification");
        this.builder = new NotificationCompat.Builder(this).setContentTitle("下载完成").setAutoCancel(true).setContentIntent(pendingIntent).setContentText("请点击跳转安装");
        this.notificationManager.notify(notifyId, this.builder.build());
    }

    private void setOnDownloadListener() {
        RxBus.get().toFlowable(DownloadProgressEvent.class).subscribe(new Consumer<DownloadProgressEvent>() {
            public void accept(DownloadProgressEvent downloadProgressEvent) throws Exception {
                double readBytes = (double) downloadProgressEvent.getReadBytes();
                double total = (double) downloadProgressEvent.getTotal();
                Double.isNaN(readBytes);
                Double.isNaN(total);
                DownloadApkService.this.builder.setProgress(100, (int) Math.round((readBytes / total) * 100.0d), false);
                DownloadApkService.this.notificationManager.notify(DownloadApkService.notifyId, DownloadApkService.this.builder.build());
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
            }
        }, new Action() {
            public void run() throws Exception {
            }
        });
    }

    private void downloadApk(String str, final String str2) {
        ((CommonService) RetrofitUtil.getInstance().create(CommonService.class)).downloadApk(str).map(new Function<ResponseBody, Boolean>() {
            public Boolean apply(ResponseBody responseBody) throws Exception {
                return Boolean.valueOf(FileUtil.writeFileToSDCard(responseBody, str2));
            }
        }).subscribe(new Observer<Boolean>() {
            public void onError(Throwable th) {
            }

            public void onNext(Boolean bool) {
            }

            public void onSubscribe(Disposable disposable) {
            }

            public void onComplete() {
                TaskStackBuilder create = TaskStackBuilder.create(DownloadApkService.this);
                create.addNextIntent(UpdateUtil.getInstallApkIntent(str2, DownloadApkService.this));
                DownloadApkService.this.builder.setContentIntent(create.getPendingIntent(DownloadApkService.notifyId, 134217728)).setProgress(100, 100, false).setContentTitle("下载完成").setContentText("请点击跳转安装");
                DownloadApkService.this.notificationManager.notify(DownloadApkService.notifyId, DownloadApkService.this.builder.build());
                DownloadApkService.this.startActivity(UpdateUtil.getInstallApkIntent(str2, DownloadApkService.this));
            }
        });
    }
}

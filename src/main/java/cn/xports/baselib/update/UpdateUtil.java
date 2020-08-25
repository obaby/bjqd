package cn.xports.baselib.update;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;
import cn.xports.baselib.App;
import java.io.File;

public class UpdateUtil {
    public static Intent getInstallApkIntent(String str, Context context) {
        File file = new File(str);
        if (Build.VERSION.SDK_INT >= 24) {
            Uri uriForFile = FileProvider.getUriForFile(context, App.getInstance().getPackageName() + ".fileProvider", file);
            Intent intent = new Intent("android.intent.action.INSTALL_PACKAGE");
            intent.setData(uriForFile);
            intent.setFlags(1);
            return intent;
        }
        Intent intent2 = new Intent();
        intent2.setAction("android.intent.action.VIEW");
        intent2.setFlags(268435456);
        intent2.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        return intent2;
    }

    public static void startUpdate(Activity activity, String str) {
        DownloadApkService.start(activity, str);
    }
}

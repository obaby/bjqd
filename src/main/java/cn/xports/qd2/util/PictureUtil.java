package cn.xports.qd2.util;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import cn.xports.baselib.App;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.Utils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class PictureUtil {
    public static final int CHOOSE_PHOTO = 2222;
    public static final int CROP_PHOTO = 3333;
    public static final int TAKE_PHOTO = 1111;
    private String path;
    private File tmpFile;

    public PictureUtil(String str) {
        this.path = str;
    }

    public static String getFilePath(Uri uri) {
        try {
            if (uri.getScheme().equals("file")) {
                return uri.getPath();
            }
            return getFilePathByUri(uri);
        } catch (FileNotFoundException unused) {
            return null;
        }
    }

    private static String getFilePathByUri(Uri uri) throws FileNotFoundException {
        Cursor query = Utils.getApp().getContentResolver().query(uri, (String[]) null, (String) null, (String[]) null, (String) null);
        query.moveToFirst();
        String string = query.getString(1);
        query.close();
        return string;
    }

    public static String getUriImgPath(Context context, Uri uri) {
        String[] strArr = {"_data"};
        Cursor query = context.getContentResolver().query(uri, strArr, (String) null, (String[]) null, (String) null);
        query.moveToFirst();
        String string = query.getString(query.getColumnIndex(strArr[0]));
        query.close();
        return string;
    }

    public void setPath(String str) {
        this.path = str;
    }

    private void openCameraUnderNougat() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        this.tmpFile = new File(Utils.getApp().getExternalFilesDir(""), this.path);
        if (this.tmpFile.exists()) {
            this.tmpFile.delete();
        }
        try {
            this.tmpFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        intent.putExtra("output", Uri.fromFile(this.tmpFile));
        ActivityUtils.getTopActivity().startActivityForResult(intent, TAKE_PHOTO);
    }

    private void openCameraOnNougat(String str) {
        Intent intent = new Intent();
        this.tmpFile = new File(App.getInstance().getExternalFilesDir(""), this.path);
        if (this.tmpFile.exists()) {
            this.tmpFile.delete();
        }
        try {
            this.tmpFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Application app = Utils.getApp();
        Uri uriForFile = FileProvider.getUriForFile(app, str + ".fileProvider", this.tmpFile);
        intent.addFlags(1);
        intent.setAction("android.media.action.IMAGE_CAPTURE");
        intent.putExtra("output", uriForFile);
        ActivityUtils.getTopActivity().startActivityForResult(intent, TAKE_PHOTO);
    }

    public void openGallery(String str) {
        if (Build.VERSION.SDK_INT < 19) {
            Intent intent = new Intent("android.intent.action.GET_CONTENT", (Uri) null);
            intent.setType("image/*");
            ActivityUtils.getTopActivity().startActivityForResult(intent, CHOOSE_PHOTO);
            return;
        }
        Intent intent2 = new Intent("android.intent.action.PICK", (Uri) null);
        intent2.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        ActivityUtils.getTopActivity().startActivityForResult(intent2, CHOOSE_PHOTO);
    }

    public void openCamera(String str) {
        if (Build.VERSION.SDK_INT >= 24) {
            openCameraOnNougat(str);
        } else {
            openCameraUnderNougat();
        }
    }
}

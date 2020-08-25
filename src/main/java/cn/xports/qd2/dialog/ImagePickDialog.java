package cn.xports.qd2.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import cn.xports.base.BaseBottomDialog;
import cn.xports.qd2.R;
import cn.xports.qd2.util.PictureUtil;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.SPUtils;

public class ImagePickDialog extends BaseBottomDialog {
    /* access modifiers changed from: private */
    public PictureUtil pictureUtil;

    public ImagePickDialog(@NonNull Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public void onCreate() {
        setContentView(R.layout.dialog_image_pick);
        findViewById(R.id.tv_take_picture).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String str = System.currentTimeMillis() + ".png";
                SPUtils.getInstance().put("_path_", str);
                PictureUtil unused = ImagePickDialog.this.pictureUtil = new PictureUtil(str);
                ImagePickDialog.this.pictureUtil.openCamera(AppUtils.getAppPackageName());
                ImagePickDialog.this.dismiss();
            }
        });
        findViewById(R.id.tv_select_photo).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                PictureUtil unused = ImagePickDialog.this.pictureUtil = new PictureUtil((String) null);
                ImagePickDialog.this.pictureUtil.openGallery(AppUtils.getAppPackageName());
                ImagePickDialog.this.dismiss();
            }
        });
        findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ImagePickDialog.this.dismiss();
            }
        });
    }
}

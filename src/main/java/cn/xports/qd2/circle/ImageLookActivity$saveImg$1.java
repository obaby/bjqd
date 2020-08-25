package cn.xports.qd2.circle;

import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import cn.xports.qd2.circle.videoPlayer.PicUrlUtils;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.previewlibrary.enitity.ThumbViewInfo;
import com.previewlibrary.wight.PhotoViewPager;
import java.io.File;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 1, 15})
/* compiled from: ImageLookActivity.kt */
final class ImageLookActivity$saveImg$1 implements Runnable {
    final /* synthetic */ List $imgUrls;
    final /* synthetic */ ImageLookActivity this$0;

    ImageLookActivity$saveImg$1(ImageLookActivity imageLookActivity, List list) {
        this.this$0 = imageLookActivity;
        this.$imgUrls = list;
    }

    public final void run() {
        RequestManager with = Glide.with((FragmentActivity) this.this$0);
        List list = this.$imgUrls;
        PhotoViewPager viewPager = this.this$0.getViewPager();
        Intrinsics.checkExpressionValueIsNotNull(viewPager, "viewPager");
        File file = (File) with.load(PicUrlUtils.getPath(((ThumbViewInfo) list.get(viewPager.getCurrentItem())).getUrl())).downloadOnly(Integer.MIN_VALUE, Integer.MIN_VALUE).get();
        try {
            StringBuilder sb = new StringBuilder();
            File externalStorageDirectory = Environment.getExternalStorageDirectory();
            Intrinsics.checkExpressionValueIsNotNull(externalStorageDirectory, "Environment.getExternalStorageDirectory()");
            sb.append(externalStorageDirectory.getAbsolutePath());
            sb.append("/Pictures/");
            File file2 = new File(sb.toString());
            if (!file2.exists()) {
                file2.mkdirs();
            }
            FileUtils.copyFile(file, new File(file2.getAbsolutePath() + "/" + System.currentTimeMillis() + ".jpg"));
            ToastUtils.showShort("保存成功", new Object[0]);
        } catch (Exception e) {
            ToastUtils.showShort("保存失败", new Object[0]);
            e.printStackTrace();
        }
    }
}

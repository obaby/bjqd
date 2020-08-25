package cn.xports.venue;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import cn.bingoogolapple.bgabanner.BGABanner;
import cn.xports.baselib.util.GlideUtil;
import cn.xports.qdplugin.R;

public class ImagePagerAdapter implements BGABanner.Adapter<ImageView, String> {
    public void fillBannerItem(BGABanner bGABanner, ImageView imageView, @Nullable String str, int i) {
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        GlideUtil.loadImage(imageView.getContext(), str, R.drawable.bg_default).into(imageView);
    }
}

package cn.xports.qd2.circle.imgSelect;

import android.widget.ImageView;
import cn.xports.qd2.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.lcw.library.imagepicker.utils.ImageLoader;

public class GlideLoader implements ImageLoader {
    private RequestOptions mOptions = new RequestOptions().centerCrop().format(DecodeFormat.PREFER_RGB_565).placeholder(R.drawable.bg_default).error(R.drawable.bg_default);
    private RequestOptions mPreOptions = new RequestOptions().skipMemoryCache(true).error(R.drawable.bg_default);

    public void clearMemoryCache() {
    }

    public void loadImage(ImageView imageView, String str) {
        Glide.with(imageView.getContext()).load(str).apply((BaseRequestOptions<?>) this.mOptions).into(imageView);
    }

    public void loadPreImage(ImageView imageView, String str) {
        Glide.with(imageView.getContext()).load(str).apply((BaseRequestOptions<?>) this.mPreOptions).into(imageView);
    }
}

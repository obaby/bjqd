package cn.xports.qd2.circle;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import cn.xports.qd2.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.previewlibrary.loader.IZoomMediaLoader;
import com.previewlibrary.loader.MySimpleTarget;

public class ImageLoader implements IZoomMediaLoader {
    RequestOptions options = new RequestOptions().centerCrop().placeholder(R.drawable.bg_default).error(R.drawable.bg_default).priority(Priority.HIGH);

    public void displayImage(Fragment fragment, String str, final MySimpleTarget<Bitmap> mySimpleTarget) {
        Glide.with(fragment).asBitmap().load(str).apply((BaseRequestOptions<?>) this.options).into(new SimpleTarget<Bitmap>() {
            public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
                mySimpleTarget.onResourceReady(bitmap);
            }

            public void onLoadStarted(Drawable drawable) {
                ImageLoader.super.onLoadStarted(drawable);
                mySimpleTarget.onLoadStarted();
            }

            public void onLoadFailed(Drawable drawable) {
                ImageLoader.super.onLoadFailed(drawable);
                mySimpleTarget.onLoadFailed(drawable);
            }
        });
    }

    public void onStop(@NonNull Fragment fragment) {
        Glide.with(fragment).onStop();
    }

    public void clearMemory(@NonNull Context context) {
        Glide.get(context).clearMemory();
    }
}

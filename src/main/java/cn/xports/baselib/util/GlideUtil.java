package cn.xports.baselib.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.text.TextUtils;
import anet.channel.util.HttpConstant;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;

public class GlideUtil {
    public static RequestBuilder loadImage(Context context, String str) {
        return loadImage(context, getPath(str), 0);
    }

    public static RequestBuilder loadImage(Context context, String str, int i) {
        return Glide.with(context).load(getPath(str)).apply((BaseRequestOptions<?>) new RequestOptions().error(i).placeholder(i));
    }

    public static RequestBuilder loadImage(Context context, Uri uri) {
        return Glide.with(context).load(uri).apply((BaseRequestOptions<?>) new RequestOptions().error((Drawable) null).placeholder((Drawable) null));
    }

    public static RequestBuilder loadRoundImage(Context context, String str) {
        return Glide.with(context).load(getPath(str)).apply((BaseRequestOptions<?>) new RequestOptions().placeholder((Drawable) null).error((Drawable) null)).apply((BaseRequestOptions<?>) RequestOptions.circleCropTransform());
    }

    public static RequestBuilder loadCornerImage(Context context, String str, int i) {
        RequestBuilder<Drawable> apply = Glide.with(context).load(getPath(str)).apply((BaseRequestOptions<?>) new RequestOptions().placeholder((Drawable) null).error((Drawable) null));
        return i > 0 ? apply.apply((BaseRequestOptions<?>) RequestOptions.bitmapTransform(new RoundedCorners(i))) : apply;
    }

    public static RequestBuilder loadRoundImage(Context context, Uri uri) {
        return Glide.with(context).load(uri).apply((BaseRequestOptions<?>) new RequestOptions().placeholder((Drawable) null).error((Drawable) null)).apply((BaseRequestOptions<?>) RequestOptions.circleCropTransform());
    }

    public static RequestBuilder loadRoundImage(Context context, String str, int i) {
        String path = getPath(str);
        Bitmap decodeResource = BitmapFactory.decodeResource(context.getResources(), i);
        RoundedBitmapDrawable create = RoundedBitmapDrawableFactory.create(context.getResources(), decodeResource);
        create.setCornerRadius(((float) Math.max(decodeResource.getWidth(), decodeResource.getHeight())) / 2.0f);
        return Glide.with(context).load(path).apply((BaseRequestOptions<?>) new RequestOptions().placeholder(create).error(create)).apply((BaseRequestOptions<?>) RequestOptions.circleCropTransform());
    }

    private static String getPath(String str) {
        if (TextUtils.isEmpty(str) || str.startsWith(HttpConstant.HTTP)) {
            return str;
        }
        String stringValue = SPUtil.Companion.getINSTANCE().getStringValue("ossUrl");
        if (TextUtils.isEmpty(stringValue)) {
            stringValue = "http://aisports.oss-cn-hangzhou.aliyuncs.com/";
        }
        return stringValue + str;
    }
}

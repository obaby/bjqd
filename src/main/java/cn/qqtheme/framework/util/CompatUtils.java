package cn.qqtheme.framework.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.view.View;
import android.widget.TextView;

public class CompatUtils {
    @TargetApi(16)
    public static void setBackground(View view, Drawable drawable) {
        if (Build.VERSION.SDK_INT < 16) {
            view.setBackgroundDrawable(drawable);
        } else {
            view.setBackground(drawable);
        }
    }

    @TargetApi(23)
    public static void setTextAppearance(TextView textView, @StyleRes int i) {
        if (Build.VERSION.SDK_INT < 23) {
            textView.setTextAppearance(textView.getContext(), i);
        } else {
            textView.setTextAppearance(i);
        }
    }

    @TargetApi(21)
    public static Drawable getDrawable(Context context, @DrawableRes int i) {
        if (Build.VERSION.SDK_INT < 21) {
            return context.getResources().getDrawable(i);
        }
        return context.getDrawable(i);
    }

    @TargetApi(21)
    public static String getString(Context context, @StringRes int i) {
        if (Build.VERSION.SDK_INT < 21) {
            return context.getResources().getString(i);
        }
        return context.getString(i);
    }

    @ColorInt
    public static int getColor(Context context, @ColorRes int i) {
        if (Build.VERSION.SDK_INT < 21) {
            return context.getResources().getColor(i);
        }
        return context.getResources().getColor(i, (Resources.Theme) null);
    }
}

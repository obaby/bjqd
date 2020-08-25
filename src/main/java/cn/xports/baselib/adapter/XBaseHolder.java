package cn.xports.baselib.adapter;

import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.util.Linkify;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

public class XBaseHolder extends RecyclerView.ViewHolder {
    private View baseView;

    public XBaseHolder(View view) {
        super(view);
        this.baseView = view;
    }

    public <T extends View> T getView(int i) {
        return this.baseView.findViewById(i);
    }

    public XBaseHolder setText(int i, String str) {
        ((TextView) getView(i)).setText(str);
        return this;
    }

    public XBaseHolder setImageResource(int i, int i2) {
        ((ImageView) getView(i)).setImageResource(i2);
        return this;
    }

    public XBaseHolder setImageBitmap(int i, Bitmap bitmap) {
        ((ImageView) getView(i)).setImageBitmap(bitmap);
        return this;
    }

    public XBaseHolder setImageDrawable(int i, Drawable drawable) {
        ((ImageView) getView(i)).setImageDrawable(drawable);
        return this;
    }

    public XBaseHolder setBackgroundColor(int i, int i2) {
        getView(i).setBackgroundColor(i2);
        return this;
    }

    public XBaseHolder setBackgroundRes(int i, int i2) {
        getView(i).setBackgroundResource(i2);
        return this;
    }

    public XBaseHolder setTextColor(int i, int i2) {
        ((TextView) getView(i)).setTextColor(i2);
        return this;
    }

    public XBaseHolder setAlpha(int i, float f) {
        if (Build.VERSION.SDK_INT >= 11) {
            getView(i).setAlpha(f);
        } else {
            AlphaAnimation alphaAnimation = new AlphaAnimation(f, f);
            alphaAnimation.setDuration(0);
            alphaAnimation.setFillAfter(true);
            getView(i).startAnimation(alphaAnimation);
        }
        return this;
    }

    public XBaseHolder setVisible(int i, boolean z) {
        getView(i).setVisibility(z ? 0 : 8);
        return this;
    }

    public XBaseHolder linkify(int i) {
        Linkify.addLinks((TextView) getView(i), 15);
        return this;
    }

    public XBaseHolder setTypeface(Typeface typeface, int... iArr) {
        for (int view : iArr) {
            TextView textView = (TextView) getView(view);
            textView.setTypeface(typeface);
            textView.setPaintFlags(textView.getPaintFlags() | 128);
        }
        return this;
    }

    public XBaseHolder setProgress(int i, int i2) {
        ((ProgressBar) getView(i)).setProgress(i2);
        return this;
    }

    public XBaseHolder setProgress(int i, int i2, int i3) {
        ProgressBar progressBar = (ProgressBar) getView(i);
        progressBar.setMax(i3);
        progressBar.setProgress(i2);
        return this;
    }

    public XBaseHolder setMax(int i, int i2) {
        ((ProgressBar) getView(i)).setMax(i2);
        return this;
    }

    public XBaseHolder setRating(int i, float f) {
        ((RatingBar) getView(i)).setRating(f);
        return this;
    }

    public XBaseHolder setRating(int i, float f, int i2) {
        RatingBar ratingBar = (RatingBar) getView(i);
        ratingBar.setMax(i2);
        ratingBar.setRating(f);
        return this;
    }

    public XBaseHolder setTag(int i, Object obj) {
        getView(i).setTag(obj);
        return this;
    }

    public XBaseHolder setTag(int i, int i2, Object obj) {
        getView(i).setTag(i2, obj);
        return this;
    }

    public XBaseHolder setChecked(int i, boolean z) {
        ((Checkable) getView(i)).setChecked(z);
        return this;
    }

    public XBaseHolder setOnClickListener(int i, View.OnClickListener onClickListener) {
        getView(i).setOnClickListener(onClickListener);
        return this;
    }

    public XBaseHolder setOnTouchListener(int i, View.OnTouchListener onTouchListener) {
        getView(i).setOnTouchListener(onTouchListener);
        return this;
    }

    public XBaseHolder setOnLongClickListener(int i, View.OnLongClickListener onLongClickListener) {
        getView(i).setOnLongClickListener(onLongClickListener);
        return this;
    }
}

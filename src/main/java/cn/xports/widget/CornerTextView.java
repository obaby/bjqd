package cn.xports.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import cn.xports.qdplugin.R;

public class CornerTextView extends AppCompatTextView {
    private GradientDrawable gd;

    public CornerTextView(Context context) {
        this(context, (AttributeSet) null);
    }

    public CornerTextView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CornerTextView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CornerTextView, i, 0);
        if (obtainStyledAttributes != null) {
            int color = obtainStyledAttributes.getColor(R.styleable.CornerTextView_ct_background_color, -1);
            int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.CornerTextView_ct_border_width, 0);
            int color2 = obtainStyledAttributes.getColor(R.styleable.CornerTextView_ct_border_color, color);
            float dimension = obtainStyledAttributes.getDimension(R.styleable.CornerTextView_ct_radius, 4.0f);
            obtainStyledAttributes.recycle();
            this.gd = new GradientDrawable();
            this.gd.setColor(color);
            this.gd.setCornerRadius(dimension);
            if (dimensionPixelSize > 0) {
                this.gd.setStroke(dimensionPixelSize, color2);
            }
            setBackground(this.gd);
        }
    }

    public CornerTextView setBgColor(@ColorInt int i) {
        if (this.gd == null) {
            this.gd = new GradientDrawable();
        }
        this.gd.setColor(i);
        setBackground(this.gd);
        return this;
    }

    public CornerTextView setBorderWidthColor(int i, @ColorInt int i2) {
        if (this.gd == null) {
            this.gd = new GradientDrawable();
        }
        this.gd.setStroke(i, i2);
        setBackground(this.gd);
        return this;
    }

    public CornerTextView setRadius(int i) {
        if (this.gd == null) {
            this.gd = new GradientDrawable();
        }
        this.gd.setCornerRadius((float) i);
        setBackground(this.gd);
        return this;
    }

    public void build() {
        invalidate();
    }
}

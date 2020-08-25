package cn.xports.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import cn.xports.qdplugin.R;
import java.util.ArrayList;
import java.util.List;

public class RatingBar extends LinearLayout {
    public OnStarChangeListener changeListener;
    private Drawable divide;
    private LinearLayout layout;
    /* access modifiers changed from: private */
    public int numStars;
    /* access modifiers changed from: private */
    public int star;
    private int starHeight;
    /* access modifiers changed from: private */
    public List<ImageView> starViews;
    private int vHeight;
    private int vWidth;

    public interface OnStarChangeListener {
        void onChange(int i);
    }

    public RatingBar(Context context) {
        this(context, (AttributeSet) null);
    }

    public RatingBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RatingBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.numStars = 5;
        this.starHeight = 5;
        this.vWidth = 0;
        this.vHeight = 0;
        this.star = 0;
        this.starViews = new ArrayList();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RatingBar, i, 0);
        if (obtainStyledAttributes != null) {
            this.starHeight = obtainStyledAttributes.getDimensionPixelSize(R.styleable.RatingBar_xStarHeight, this.starHeight);
            this.numStars = obtainStyledAttributes.getInt(R.styleable.RatingBar_xNumStars, this.numStars);
            obtainStyledAttributes.recycle();
        }
        setOrientation(0);
        setGravity(16);
        inflate(context, R.layout.rating_bar, this);
        this.layout = (LinearLayout) findViewById(R.id.ll_rating_bar);
    }

    public RatingBar showStars(int i) {
        int height = ((int) (((float) this.layout.getHeight()) - (((float) this.starHeight) * 1.0f))) / 2;
        if (height < 0) {
            height = 0;
        }
        for (int i2 = 0; i2 < this.numStars; i2++) {
            final ImageView starView = getStarView(this.starHeight + (i * 2));
            starView.setPadding(i, height, i, height);
            if (i2 == this.numStars - 1) {
                starView = getStarView(this.starHeight + i);
                starView.setPadding(i, height, 0, height);
            } else if (i2 == 0) {
                starView = getStarView(this.starHeight + i);
                starView.setPadding(0, height, i, height);
            }
            starView.setTag(Integer.valueOf(i2));
            this.layout.addView(starView);
            this.starViews.add(starView);
            starView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    int unused = RatingBar.this.star = ((Integer) starView.getTag()).intValue();
                    for (int i = 0; i < RatingBar.this.numStars; i++) {
                        if (i <= RatingBar.this.star) {
                            ((ImageView) RatingBar.this.starViews.get(i)).setImageResource(R.drawable.ic_rating_on);
                        } else {
                            ((ImageView) RatingBar.this.starViews.get(i)).setImageResource(R.drawable.ic_rating_off);
                        }
                    }
                    if (RatingBar.this.changeListener != null) {
                        RatingBar.this.changeListener.onChange(RatingBar.this.star + 1);
                    }
                }
            });
        }
        postInvalidate();
        requestLayout();
        return this;
    }

    private ImageView getStarView(int i) {
        ImageView imageView = new ImageView(getContext());
        imageView.setLayoutParams(new LinearLayout.LayoutParams(i, -1));
        imageView.setImageResource(R.drawable.ic_rating_off);
        return imageView;
    }

    public void setChangeListener(OnStarChangeListener onStarChangeListener) {
        this.changeListener = onStarChangeListener;
    }

    public int getStars() {
        return this.star + 1;
    }
}

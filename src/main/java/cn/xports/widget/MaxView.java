package cn.xports.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import cn.xports.qdplugin.R;

public class MaxView extends RelativeLayout {
    private int maxHeight;
    private int maxWidth;

    public MaxView(Context context) {
        this(context, (AttributeSet) null);
    }

    public MaxView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MaxView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.MaxView);
        this.maxWidth = obtainStyledAttributes.getLayoutDimension(R.styleable.MaxView_xMaxWidth, 0);
        this.maxHeight = obtainStyledAttributes.getLayoutDimension(R.styleable.MaxView_xMaxHeight, 0);
        obtainStyledAttributes.recycle();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        if (this.maxHeight > 0) {
            int mode = View.MeasureSpec.getMode(i2);
            int size = View.MeasureSpec.getSize(i2);
            if (size > this.maxHeight) {
                size = this.maxHeight;
            }
            i2 = View.MeasureSpec.makeMeasureSpec(size, mode);
        }
        if (this.maxWidth > 0) {
            int mode2 = View.MeasureSpec.getMode(i);
            int size2 = View.MeasureSpec.getSize(i);
            if (size2 > this.maxWidth) {
                size2 = this.maxWidth;
            }
            i = View.MeasureSpec.makeMeasureSpec(size2, mode2);
        }
        super.onMeasure(i, i2);
    }
}

package cn.xports.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import cn.xports.qdplugin.R;

public class PointIndexView extends View {
    public static final int HORIZONTAL = 1;
    public static final int VERTICAL = 0;
    private int allLength;
    private int count;
    private int direction;
    private int gapWidth;
    private int height;
    private int index;
    private int indexLength;
    private Paint indexPaint;
    private int length;
    private Paint paint;
    private int strokeWidth;
    private int width;

    public PointIndexView(Context context) {
        this(context, (AttributeSet) null);
    }

    public PointIndexView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PointIndexView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.paint = new Paint(1);
        this.indexPaint = new Paint(1);
        this.gapWidth = 10;
        this.strokeWidth = 6;
        this.indexLength = 16;
        this.length = 6;
        this.count = 2;
        this.index = 0;
        this.direction = 0;
        this.allLength = 0;
        init();
        setLayerType(2, (Paint) null);
    }

    private void init() {
        this.allLength = ((this.count - 1) * (this.gapWidth + this.length)) + this.indexLength;
        this.paint.setColor(getContext().getResources().getColor(R.color.gray_d1d));
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setStrokeWidth((float) this.strokeWidth);
        this.paint.setStrokeCap(Paint.Cap.ROUND);
        this.indexPaint.setColor(Color.parseColor("#2e6ffc"));
        this.indexPaint.setStyle(Paint.Style.STROKE);
        this.indexPaint.setStrokeWidth((float) this.strokeWidth);
        this.indexPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        int i;
        int i2;
        int i3;
        int i4;
        super.onDraw(canvas);
        int i5 = 0;
        canvas.drawColor(0, PorterDuff.Mode.CLEAR);
        if (this.direction == 1) {
            int i6 = (this.width - this.allLength) / 2;
            while (i5 < this.count) {
                if (this.index == i5) {
                    canvas.drawLine((float) i6, (float) (this.height / 2), (float) (this.indexLength + i6), (float) (this.height / 2), this.indexPaint);
                    i4 = i6 + this.indexLength;
                    i3 = this.gapWidth;
                } else {
                    canvas.drawLine((float) i6, (float) (this.height / 2), (float) (this.length + i6), (float) (this.height / 2), this.paint);
                    i4 = i6 + this.length;
                    i3 = this.gapWidth;
                }
                i6 = i4 + i3;
                i5++;
            }
            return;
        }
        int i7 = (this.height - this.allLength) / 2;
        while (i5 < this.count) {
            if (this.index == i5) {
                canvas.drawLine((float) (this.width / 2), (float) i7, (float) (this.width / 2), (float) (this.indexLength + i7), this.indexPaint);
                i2 = i7 + this.indexLength;
                i = this.gapWidth;
            } else {
                canvas.drawLine((float) (this.width / 2), (float) i7, (float) (this.width / 2), (float) (this.length + i7), this.paint);
                i2 = i7 + this.length;
                i = this.gapWidth;
            }
            i7 = i2 + i;
            i5++;
        }
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.width = i;
        this.height = i2;
    }

    public void setCount(int i, int i2) {
        this.count = i;
        this.direction = i2;
        invalidate();
    }

    public void setIndex(int i) {
        this.index = i;
        invalidate();
    }
}

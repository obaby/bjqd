package cn.xports.qd2.widget;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.style.ReplacementSpan;

public class RoundBackgroundColorSpan extends ReplacementSpan {
    private int bgColor;
    private int textColor;

    public RoundBackgroundColorSpan(int i, int i2) {
        this.bgColor = i;
        this.textColor = i2;
    }

    public int getSize(Paint paint, CharSequence charSequence, int i, int i2, Paint.FontMetricsInt fontMetricsInt) {
        return ((int) paint.measureText(charSequence, i, i2)) + 60;
    }

    public void draw(Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, Paint paint) {
        float f2 = f;
        Paint paint2 = paint;
        int color = paint.getColor();
        paint2.setColor(this.bgColor);
        CharSequence charSequence2 = charSequence;
        int i6 = i;
        Canvas canvas2 = canvas;
        canvas.drawRoundRect(new RectF(f2, (float) i3, ((float) (((int) paint2.measureText(charSequence, i, i2)) + 40)) + f2, (float) i5), 15.0f, 15.0f, paint2);
        paint2.setColor(this.textColor);
        canvas.drawText(charSequence, i, i2, f2 + 20.0f, (float) (i4 - 5), paint);
        paint2.setColor(color);
    }
}

package coband.bsit.com.integral.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import coband.bsit.com.integral.R;
import coband.bsit.com.integral.utils.CalendarUtil;

public class WeekView extends View {
    private Context context;
    private Paint mPaint;
    private String[] weekArray;
    private int weekColor;
    private int weekSize;

    public WeekView(Context context2) {
        this(context2, (AttributeSet) null);
    }

    public WeekView(Context context2, AttributeSet attributeSet) {
        this(context2, attributeSet, 0);
    }

    public WeekView(Context context2, AttributeSet attributeSet, int i) {
        super(context2, attributeSet, i);
        this.weekArray = new String[]{"日", "一", "二", "三", "四", "五", "六"};
        this.weekSize = 14;
        this.weekColor = Color.parseColor("#282828");
        this.context = context2;
        initAttrs(attributeSet);
        initPaint();
        setBackgroundColor(-1);
    }

    private void initAttrs(AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = this.context.obtainStyledAttributes(attributeSet, R.styleable.WeekView);
        String str = null;
        for (int i = 0; i < obtainStyledAttributes.getIndexCount(); i++) {
            int index = obtainStyledAttributes.getIndex(i);
            if (index == R.styleable.WeekView_week_color) {
                this.weekColor = obtainStyledAttributes.getColor(R.styleable.WeekView_week_color, this.weekColor);
            } else if (index == R.styleable.WeekView_week_size) {
                this.weekSize = obtainStyledAttributes.getInteger(R.styleable.WeekView_week_size, this.weekSize);
            } else if (index == R.styleable.WeekView_week_str) {
                str = obtainStyledAttributes.getString(R.styleable.WeekView_week_str);
            }
        }
        obtainStyledAttributes.recycle();
        if (!TextUtils.isEmpty(str)) {
            String[] split = str.split("\\.");
            if (split.length == 7) {
                System.arraycopy(split, 0, this.weekArray, 0, 7);
            } else {
                return;
            }
        }
        this.weekSize = CalendarUtil.getTextSize1(this.context, this.weekSize);
    }

    private void initPaint() {
        this.mPaint = new Paint();
        this.mPaint.setColor(this.weekColor);
        this.mPaint.setAntiAlias(true);
        this.mPaint.setTextSize((float) this.weekSize);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int size = View.MeasureSpec.getSize(i);
        int mode = View.MeasureSpec.getMode(i);
        int size2 = View.MeasureSpec.getSize(i2);
        if (View.MeasureSpec.getMode(i2) == Integer.MIN_VALUE) {
            size2 = CalendarUtil.getPxSize(this.context, 35);
        }
        if (mode == Integer.MIN_VALUE) {
            size = CalendarUtil.getPxSize(this.context, 300);
        }
        setMeasuredDimension(size, size2);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        int i = width / 7;
        for (int i2 = 0; i2 < this.weekArray.length; i2++) {
            String str = this.weekArray[i2];
            canvas.drawText(str, (float) ((i * i2) + ((i - ((int) this.mPaint.measureText(str))) / 2)), (float) ((int) (((float) (height / 2)) - ((this.mPaint.ascent() + this.mPaint.descent()) / 2.0f))), this.mPaint);
        }
    }
}

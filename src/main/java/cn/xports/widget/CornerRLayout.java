package cn.xports.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import cn.xports.qdplugin.R;

public class CornerRLayout extends RelativeLayout {
    private static final int BOTTOM = 1;
    private static final int LEFT = 3;
    private static final int RIGHT = 2;
    private static final int TOP = 0;
    private float cornerRadius;
    private int direction;
    private int height;
    private Path path;
    private RectF rectF;
    private float shapHeight;
    private int width;

    public CornerRLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    public CornerRLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CornerRLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.shapHeight = 0.0f;
        this.path = new Path();
        this.cornerRadius = 15.0f;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CornerRLayout, i, 0);
        if (obtainStyledAttributes != null) {
            this.cornerRadius = obtainStyledAttributes.getDimension(R.styleable.CornerRLayout_cg_radius, this.cornerRadius);
            this.direction = obtainStyledAttributes.getInt(R.styleable.CornerRLayout_shape_direction, 2);
            this.shapHeight = obtainStyledAttributes.getDimension(R.styleable.CornerRLayout_shape_height, 0.0f);
            obtainStyledAttributes.recycle();
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.shapHeight == 0.0f) {
            return;
        }
        if (this.direction == 0 || this.direction == 1) {
            setMeasuredDimension(getMeasuredWidth(), (int) (((float) getMeasuredHeight()) + this.shapHeight));
        } else {
            setMeasuredDimension((int) (((float) getMeasuredWidth()) + this.shapHeight), getMeasuredHeight());
        }
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.rectF = new RectF(0.0f, 0.0f, (float) i, (float) i2);
        this.width = i;
        this.height = i2;
        float min = ((float) Math.min(i, i2)) / 2.0f;
        if (this.cornerRadius > min) {
            this.cornerRadius = min;
        }
        resetPath();
    }

    public void draw(Canvas canvas) {
        int save = canvas.save();
        canvas.clipPath(this.path);
        super.draw(canvas);
        canvas.restoreToCount(save);
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        int save = canvas.save();
        canvas.clipPath(this.path);
        super.dispatchDraw(canvas);
        canvas.restoreToCount(save);
    }

    private void resetPath() {
        this.path.reset();
        if (this.shapHeight != 0.0f) {
            float f = this.shapHeight / 1.732f;
            switch (this.direction) {
                case 0:
                    setPadding(getPaddingLeft(), (int) (((float) getPaddingTop()) + this.shapHeight), getPaddingRight(), getPaddingBottom());
                    this.rectF = new RectF(0.0f, this.shapHeight, (float) this.width, (float) this.height);
                    this.path.addRoundRect(this.rectF, this.cornerRadius, this.cornerRadius, Path.Direction.CW);
                    this.path.moveTo(((((float) this.width) * 1.0f) / 2.0f) - f, this.shapHeight);
                    this.path.lineTo((((float) this.width) * 1.0f) / 2.0f, 0.0f);
                    this.path.lineTo(((((float) this.width) * 1.0f) / 2.0f) + f, this.shapHeight);
                    break;
                case 1:
                    setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), (int) (((float) getPaddingBottom()) + this.shapHeight));
                    this.rectF = new RectF(0.0f, 0.0f, (float) this.width, ((float) this.height) - this.shapHeight);
                    this.path.addRoundRect(this.rectF, this.cornerRadius, this.cornerRadius, Path.Direction.CW);
                    this.path.moveTo(((((float) this.width) * 1.0f) / 2.0f) - f, ((float) this.height) - this.shapHeight);
                    this.path.lineTo((((float) this.width) * 1.0f) / 2.0f, (float) this.height);
                    this.path.lineTo(((((float) this.width) * 1.0f) / 2.0f) + f, ((float) this.height) - this.shapHeight);
                    break;
                case 2:
                    setPadding(getPaddingLeft(), getPaddingTop(), (int) (((float) getPaddingRight()) + this.shapHeight), getPaddingBottom());
                    this.rectF = new RectF(0.0f, 0.0f, ((float) this.width) - this.shapHeight, (float) this.height);
                    this.path.addRoundRect(this.rectF, this.cornerRadius, this.cornerRadius, Path.Direction.CW);
                    this.path.moveTo(((float) this.width) - this.shapHeight, ((((float) this.height) * 1.0f) / 2.0f) - f);
                    this.path.lineTo((float) this.width, (((float) this.height) * 1.0f) / 2.0f);
                    this.path.lineTo(((float) this.width) - this.shapHeight, ((((float) this.height) * 1.0f) / 2.0f) + f);
                    break;
                case 3:
                    setPadding((int) (((float) getPaddingLeft()) + this.shapHeight), getPaddingTop(), getPaddingRight(), getPaddingBottom());
                    this.rectF = new RectF(this.shapHeight, 0.0f, (float) this.width, (float) this.height);
                    this.path.addRoundRect(this.rectF, this.cornerRadius, this.cornerRadius, Path.Direction.CW);
                    this.path.moveTo(this.shapHeight, ((((float) this.height) * 1.0f) / 2.0f) - f);
                    this.path.lineTo(0.0f, (((float) this.height) * 1.0f) / 2.0f);
                    this.path.lineTo(this.shapHeight, ((((float) this.height) * 1.0f) / 2.0f) + f);
                    break;
                default:
                    this.path.addRoundRect(this.rectF, this.cornerRadius, this.cornerRadius, Path.Direction.CW);
                    break;
            }
        } else {
            this.path.addRoundRect(this.rectF, this.cornerRadius, this.cornerRadius, Path.Direction.CW);
        }
        this.path.close();
    }
}

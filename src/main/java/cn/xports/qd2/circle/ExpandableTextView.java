package cn.xports.qd2.circle;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.ActivityChooserView;
import android.support.v7.widget.AppCompatTextView;
import android.text.Layout;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.AlignmentSpan;
import android.text.style.ClickableSpan;
import android.text.style.StyleSpan;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import java.lang.reflect.Field;

public class ExpandableTextView extends AppCompatTextView {
    private static final String DEFAULT_CLOSE_SUFFIX = "";
    private static final int DEFAULT_MAX_LINE = 3;
    private static final String DEFAULT_OPEN_SUFFIX = " 展开";
    public static final String ELLIPSIS_STRING = "&";
    private static final String TAG = "ExpandableTextView";
    volatile boolean animating = false;
    private Context context;
    private boolean hasAnimation = false;
    private boolean hasOpened = false;
    private int initWidth = 0;
    boolean isClosed = false;
    /* access modifiers changed from: private */
    public int mCLoseHeight;
    private CharSequenceToSpannableHandler mCharSequenceToSpannableHandler;
    private Animation mCloseAnim;
    private boolean mCloseInNewLine;
    /* access modifiers changed from: private */
    public SpannableStringBuilder mCloseSpannableStr;
    /* access modifiers changed from: private */
    public int mCloseSuffixColor;
    @Nullable
    private SpannableString mCloseSuffixSpan;
    private String mCloseSuffixStr = "";
    private boolean mExpandable;
    /* access modifiers changed from: private */
    public int mMaxLines = 3;
    private View.OnClickListener mOnClickListener;
    private Animation mOpenAnim;
    public OpenAndCloseCallback mOpenCloseCallback;
    /* access modifiers changed from: private */
    public int mOpenHeight;
    /* access modifiers changed from: private */
    public SpannableStringBuilder mOpenSpannableStr;
    /* access modifiers changed from: private */
    public int mOpenSuffixColor;
    @Nullable
    private SpannableString mOpenSuffixSpan;
    private String mOpenSuffixStr = DEFAULT_OPEN_SUFFIX;
    private int margerdp;
    private CharSequence originalText;

    public interface CharSequenceToSpannableHandler {
        @NonNull
        SpannableStringBuilder charSequenceToSpannable(CharSequence charSequence);
    }

    public interface OpenAndCloseCallback {
        void onClose();

        void onOpen();
    }

    public boolean hasOverlappingRendering() {
        return false;
    }

    public boolean isHasOpened() {
        return this.hasOpened;
    }

    public void setHasOpened(boolean z) {
        this.hasOpened = z;
    }

    public ExpandableTextView(Context context2) {
        super(context2);
        this.context = context2;
        initialize();
    }

    public ExpandableTextView(Context context2, AttributeSet attributeSet) {
        super(context2, attributeSet);
        this.context = context2;
        initialize();
    }

    public ExpandableTextView(Context context2, AttributeSet attributeSet, int i) {
        super(context2, attributeSet, i);
        this.context = context2;
        initialize();
    }

    private void initialize() {
        int parseColor = Color.parseColor("#F23030");
        this.mCloseSuffixColor = parseColor;
        this.mOpenSuffixColor = parseColor;
        setMovementMethod(OverLinkMovementMethod.getInstance());
        setIncludeFontPadding(false);
        updateOpenSuffixSpan();
        updateCloseSuffixSpan();
    }

    public void setOriginalText(CharSequence charSequence) {
        int length;
        this.originalText = charSequence;
        this.mExpandable = false;
        this.mCloseSpannableStr = new SpannableStringBuilder();
        int i = this.mMaxLines;
        SpannableStringBuilder charSequenceToSpannable = charSequenceToSpannable(charSequence);
        this.mOpenSpannableStr = charSequenceToSpannable(charSequence);
        if (i != -1) {
            Layout createStaticLayout = createStaticLayout(charSequenceToSpannable);
            this.mExpandable = createStaticLayout.getLineCount() > i;
            if (this.mExpandable) {
                if (this.mCloseInNewLine) {
                    this.mOpenSpannableStr.append("\n");
                }
                if (this.mCloseSuffixSpan != null) {
                    this.mOpenSpannableStr.append(this.mCloseSuffixSpan);
                }
                int lineEnd = createStaticLayout.getLineEnd(i - 1);
                if (charSequence.length() <= lineEnd) {
                    this.mCloseSpannableStr = charSequenceToSpannable(charSequence);
                } else {
                    this.mCloseSpannableStr = charSequenceToSpannable(charSequence.subSequence(0, lineEnd));
                }
                SpannableStringBuilder append = charSequenceToSpannable(this.mCloseSpannableStr).append(ELLIPSIS_STRING);
                if (this.mOpenSuffixSpan != null) {
                    append.append(this.mOpenSuffixSpan);
                }
                Layout createStaticLayout2 = createStaticLayout(append);
                while (createStaticLayout2.getLineCount() > i && (length = this.mCloseSpannableStr.length() - 1) != -1) {
                    if (charSequence.length() <= length) {
                        this.mCloseSpannableStr = charSequenceToSpannable(charSequence);
                    } else {
                        this.mCloseSpannableStr = charSequenceToSpannable(charSequence.subSequence(0, length));
                    }
                    SpannableStringBuilder append2 = charSequenceToSpannable(this.mCloseSpannableStr).append(ELLIPSIS_STRING);
                    if (this.mOpenSuffixSpan != null) {
                        append2.append(this.mOpenSuffixSpan);
                    }
                    createStaticLayout2 = createStaticLayout(append2);
                }
                int length2 = this.mCloseSpannableStr.length() - this.mOpenSuffixSpan.length();
                if (length2 >= 0 && charSequence.length() > length2) {
                    int hasEnCharCount = (hasEnCharCount(charSequence.subSequence(length2, this.mOpenSuffixSpan.length() + length2)) - hasEnCharCount(this.mOpenSuffixSpan)) + 1;
                    if (hasEnCharCount > 0) {
                        length2 -= hasEnCharCount;
                    }
                    this.mCloseSpannableStr = charSequenceToSpannable(charSequence.subSequence(0, length2));
                }
                this.mCLoseHeight = createStaticLayout2.getHeight() + getPaddingTop() + getPaddingBottom();
                this.mCloseSpannableStr.append(ELLIPSIS_STRING);
                if (this.mOpenSuffixSpan != null) {
                    this.mCloseSpannableStr.append(this.mOpenSuffixSpan);
                }
            }
        }
        this.isClosed = this.mExpandable;
        if (this.mExpandable) {
            setText(this.mCloseSpannableStr);
        } else {
            setText(this.mOpenSpannableStr);
        }
    }

    private int hasEnCharCount(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            return 0;
        }
        int i = 0;
        for (int i2 = 0; i2 < charSequence.length(); i2++) {
            char charAt = charSequence.charAt(i2);
            if (charAt >= ' ' && charAt <= '~') {
                i++;
            }
        }
        return i;
    }

    /* access modifiers changed from: private */
    public void switchOpenClose() {
        if (this.mExpandable) {
            this.isClosed = !this.isClosed;
            if (this.isClosed) {
                close();
            } else {
                open();
            }
        }
    }

    public void setHasAnimation(boolean z) {
        this.hasAnimation = z;
    }

    private void open() {
        this.hasOpened = true;
        if (this.hasAnimation) {
            this.mOpenHeight = createStaticLayout(this.mOpenSpannableStr).getHeight() + getPaddingTop() + getPaddingBottom();
            executeOpenAnim();
            return;
        }
        super.setMaxLines(ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
        setText(this.mOpenSpannableStr);
        if (this.mOpenCloseCallback != null) {
            this.mOpenCloseCallback.onOpen();
        }
    }

    private void close() {
        if (this.hasAnimation) {
            executeCloseAnim();
            return;
        }
        super.setMaxLines(this.mMaxLines);
        setText(this.mCloseSpannableStr);
        if (this.mOpenCloseCallback != null) {
            this.mOpenCloseCallback.onClose();
        }
    }

    private void executeOpenAnim() {
        if (this.mOpenAnim == null) {
            this.mOpenAnim = new ExpandCollapseAnimation(this, this.mCLoseHeight, this.mOpenHeight);
            this.mOpenAnim.setFillAfter(true);
            this.mOpenAnim.setAnimationListener(new Animation.AnimationListener() {
                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationStart(Animation animation) {
                    ExpandableTextView.super.setMaxLines(ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
                    ExpandableTextView.this.setText(ExpandableTextView.this.mOpenSpannableStr);
                }

                public void onAnimationEnd(Animation animation) {
                    ExpandableTextView.this.getLayoutParams().height = ExpandableTextView.this.mOpenHeight;
                    ExpandableTextView.this.requestLayout();
                    ExpandableTextView.this.animating = false;
                }
            });
        }
        if (!this.animating) {
            this.animating = true;
            clearAnimation();
            startAnimation(this.mOpenAnim);
        }
    }

    private void executeCloseAnim() {
        if (this.mCloseAnim == null) {
            this.mCloseAnim = new ExpandCollapseAnimation(this, this.mOpenHeight, this.mCLoseHeight);
            this.mCloseAnim.setFillAfter(true);
            this.mCloseAnim.setAnimationListener(new Animation.AnimationListener() {
                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationStart(Animation animation) {
                }

                public void onAnimationEnd(Animation animation) {
                    ExpandableTextView.this.animating = false;
                    ExpandableTextView.super.setMaxLines(ExpandableTextView.this.mMaxLines);
                    ExpandableTextView.this.setText(ExpandableTextView.this.mCloseSpannableStr);
                    ExpandableTextView.this.getLayoutParams().height = ExpandableTextView.this.mCLoseHeight;
                    ExpandableTextView.this.requestLayout();
                }
            });
        }
        if (!this.animating) {
            this.animating = true;
            clearAnimation();
            startAnimation(this.mCloseAnim);
        }
    }

    private Layout createStaticLayout(SpannableStringBuilder spannableStringBuilder) {
        int paddingLeft = (this.initWidth - getPaddingLeft()) - getPaddingRight();
        if (Build.VERSION.SDK_INT >= 23) {
            StaticLayout.Builder obtain = StaticLayout.Builder.obtain(spannableStringBuilder, 0, spannableStringBuilder.length(), getPaint(), paddingLeft);
            obtain.setAlignment(Layout.Alignment.ALIGN_NORMAL);
            obtain.setIncludePad(getIncludeFontPadding());
            obtain.setLineSpacing(getLineSpacingExtra(), getLineSpacingMultiplier());
            return obtain.build();
        } else if (Build.VERSION.SDK_INT >= 16) {
            return new StaticLayout(spannableStringBuilder, getPaint(), paddingLeft, Layout.Alignment.ALIGN_NORMAL, getLineSpacingMultiplier(), getLineSpacingExtra(), getIncludeFontPadding());
        } else {
            return new StaticLayout(spannableStringBuilder, getPaint(), paddingLeft, Layout.Alignment.ALIGN_NORMAL, getFloatField("mSpacingMult", 1.0f), getFloatField("mSpacingAdd", 0.0f), getIncludeFontPadding());
        }
    }

    private float getFloatField(String str, float f) {
        if (TextUtils.isEmpty(str)) {
            return f;
        }
        try {
            for (Field field : getClass().getDeclaredFields()) {
                if (TextUtils.equals(str, field.getName())) {
                    return field.getFloat(this);
                }
            }
            return f;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return f;
        }
    }

    private SpannableStringBuilder charSequenceToSpannable(@NonNull CharSequence charSequence) {
        SpannableStringBuilder charSequenceToSpannable = this.mCharSequenceToSpannableHandler != null ? this.mCharSequenceToSpannableHandler.charSequenceToSpannable(charSequence) : null;
        return charSequenceToSpannable == null ? new SpannableStringBuilder(charSequence) : charSequenceToSpannable;
    }

    public void initWidth(int i) {
        this.initWidth = i;
    }

    public void setMaxLines(int i) {
        this.mMaxLines = i;
        super.setMaxLines(i);
    }

    public void setOpenSuffix(String str) {
        this.mOpenSuffixStr = str;
        updateOpenSuffixSpan();
    }

    public void setOpenSuffixColor(@ColorInt int i) {
        this.mOpenSuffixColor = i;
        updateOpenSuffixSpan();
    }

    public void setCloseSuffix(String str) {
        this.mCloseSuffixStr = str;
        updateCloseSuffixSpan();
    }

    public void setCloseSuffixColor(@ColorInt int i) {
        this.mCloseSuffixColor = i;
        updateCloseSuffixSpan();
    }

    public void setCloseInNewLine(boolean z) {
        this.mCloseInNewLine = z;
        updateCloseSuffixSpan();
    }

    private void updateOpenSuffixSpan() {
        if (TextUtils.isEmpty(this.mOpenSuffixStr)) {
            this.mOpenSuffixSpan = null;
            return;
        }
        this.mOpenSuffixSpan = new SpannableString(this.mOpenSuffixStr);
        this.mOpenSuffixSpan.setSpan(new StyleSpan(1), 0, this.mOpenSuffixStr.length(), 33);
        this.mOpenSuffixSpan.setSpan(new ClickableSpan() {
            public void onClick(@NonNull View view) {
                ExpandableTextView.this.switchOpenClose();
            }

            public void updateDrawState(@NonNull TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setColor(ExpandableTextView.this.mOpenSuffixColor);
                textPaint.setUnderlineText(false);
            }
        }, 0, this.mOpenSuffixStr.length(), 34);
    }

    private void updateCloseSuffixSpan() {
        if (TextUtils.isEmpty(this.mCloseSuffixStr)) {
            this.mCloseSuffixSpan = null;
            return;
        }
        this.mCloseSuffixSpan = new SpannableString(this.mCloseSuffixStr);
        this.mCloseSuffixSpan.setSpan(new StyleSpan(1), 0, this.mCloseSuffixStr.length(), 33);
        if (this.mCloseInNewLine) {
            this.mCloseSuffixSpan.setSpan(new AlignmentSpan.Standard(Layout.Alignment.ALIGN_OPPOSITE), 0, 1, 33);
        }
        this.mCloseSuffixSpan.setSpan(new ClickableSpan() {
            public void onClick(@NonNull View view) {
                ExpandableTextView.this.switchOpenClose();
            }

            public void updateDrawState(@NonNull TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setColor(ExpandableTextView.this.mCloseSuffixColor);
                textPaint.setUnderlineText(false);
            }
        }, 1, this.mCloseSuffixStr.length(), 33);
    }

    public void setOpenAndCloseCallback(OpenAndCloseCallback openAndCloseCallback) {
        this.mOpenCloseCallback = openAndCloseCallback;
    }

    public void setCharSequenceToSpannableHandler(CharSequenceToSpannableHandler charSequenceToSpannableHandler) {
        this.mCharSequenceToSpannableHandler = charSequenceToSpannableHandler;
    }

    class ExpandCollapseAnimation extends Animation {
        private final int mEndHeight;
        private final int mStartHeight;
        private final View mTargetView;

        ExpandCollapseAnimation(View view, int i, int i2) {
            this.mTargetView = view;
            this.mStartHeight = i;
            this.mEndHeight = i2;
            setDuration(400);
        }

        /* access modifiers changed from: protected */
        public void applyTransformation(float f, Transformation transformation) {
            this.mTargetView.setScrollY(0);
            this.mTargetView.getLayoutParams().height = (int) ((((float) (this.mEndHeight - this.mStartHeight)) * f) + ((float) this.mStartHeight));
            this.mTargetView.requestLayout();
        }
    }

    public void setMargerdp(int i) {
        this.margerdp = i;
    }

    public int dp2px(Context context2, float f) {
        float f2 = context2.getResources().getDisplayMetrics().density;
        return f < 0.0f ? -((int) (((-f) * f2) + 0.5f)) : (int) ((f * f2) + 0.5f);
    }
}

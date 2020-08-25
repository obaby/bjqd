package cn.bingoogolapple.bgabanner;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.graphics.drawable.PathInterpolatorCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.ActivityChooserView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.bingoogolapple.bgabanner.BGAViewPager;
import cn.bingoogolapple.bgabanner.transformer.BGAPageTransformer;
import cn.bingoogolapple.bgabanner.transformer.TransitionEffect;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BGABanner extends RelativeLayout implements BGAViewPager.AutoPlayDelegate, ViewPager.OnPageChangeListener {
    private static final int LWC = -2;
    private static final int NO_PLACEHOLDER_DRAWABLE = -1;
    private static final int RMP = -1;
    private static final int RWC = -2;
    private static final int VEL_THRESHOLD = 400;
    private static final ImageView.ScaleType[] sScaleTypeArray = {ImageView.ScaleType.MATRIX, ImageView.ScaleType.FIT_XY, ImageView.ScaleType.FIT_START, ImageView.ScaleType.FIT_CENTER, ImageView.ScaleType.FIT_END, ImageView.ScaleType.CENTER, ImageView.ScaleType.CENTER_CROP, ImageView.ScaleType.CENTER_INSIDE};
    /* access modifiers changed from: private */
    public Adapter mAdapter;
    private boolean mAllowUserScrollable;
    private float mAspectRatio;
    /* access modifiers changed from: private */
    public boolean mAutoPlayAble;
    private int mAutoPlayInterval;
    private AutoPlayTask mAutoPlayTask;
    private int mContentBottomMargin;
    /* access modifiers changed from: private */
    public Delegate mDelegate;
    private View mEnterView;
    /* access modifiers changed from: private */
    public GuideDelegate mGuideDelegate;
    private BGAOnNoDoubleClickListener mGuideOnNoDoubleClickListener;
    /* access modifiers changed from: private */
    public List<View> mHackyViews;
    private boolean mIsFirstInvisible;
    private boolean mIsNeedShowIndicatorOnOnlyOnePage;
    private boolean mIsNumberIndicator;
    /* access modifiers changed from: private */
    public List<? extends Object> mModels;
    private Drawable mNumberIndicatorBackground;
    private int mNumberIndicatorTextColor;
    private int mNumberIndicatorTextSize;
    private TextView mNumberIndicatorTv;
    private ViewPager.OnPageChangeListener mOnPageChangeListener;
    private int mOverScrollMode;
    private int mPageChangeDuration;
    private int mPageScrollPosition;
    private float mPageScrollPositionOffset;
    private int mPlaceholderDrawableResId;
    private ImageView mPlaceholderIv;
    private Drawable mPointContainerBackgroundDrawable;
    private int mPointContainerLeftRightPadding;
    private int mPointDrawableResId;
    private int mPointGravity;
    private int mPointLeftRightMargin;
    private LinearLayout mPointRealContainerLl;
    private int mPointTopBottomMargin;
    private ImageView.ScaleType mScaleType;
    private View mSkipView;
    private int mTipTextColor;
    private int mTipTextSize;
    private TextView mTipTv;
    private List<String> mTips;
    private TransitionEffect mTransitionEffect;
    /* access modifiers changed from: private */
    public BGAViewPager mViewPager;
    /* access modifiers changed from: private */
    public List<View> mViews;

    public interface Adapter<V extends View, M> {
        void fillBannerItem(BGABanner bGABanner, V v, @Nullable M m, int i);
    }

    public interface Delegate<V extends View, M> {
        void onBannerItemClick(BGABanner bGABanner, V v, @Nullable M m, int i);
    }

    public interface GuideDelegate {
        void onClickEnterOrSkip();
    }

    public BGABanner(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BGABanner(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mAutoPlayAble = true;
        this.mAutoPlayInterval = PathInterpolatorCompat.MAX_NUM_POINTS;
        this.mPageChangeDuration = 800;
        this.mPointGravity = 81;
        this.mTipTextColor = -1;
        this.mPointDrawableResId = R.drawable.bga_banner_selector_point_solid;
        this.mScaleType = ImageView.ScaleType.CENTER_CROP;
        this.mPlaceholderDrawableResId = -1;
        this.mOverScrollMode = 2;
        this.mIsNumberIndicator = false;
        this.mNumberIndicatorTextColor = -1;
        this.mAllowUserScrollable = true;
        this.mIsFirstInvisible = true;
        this.mGuideOnNoDoubleClickListener = new BGAOnNoDoubleClickListener() {
            public void onNoDoubleClick(View view) {
                if (BGABanner.this.mGuideDelegate != null) {
                    BGABanner.this.mGuideDelegate.onClickEnterOrSkip();
                }
            }
        };
        initDefaultAttrs(context);
        initCustomAttrs(context, attributeSet);
        initView(context);
    }

    private void initDefaultAttrs(Context context) {
        this.mAutoPlayTask = new AutoPlayTask();
        this.mPointLeftRightMargin = BGABannerUtil.dp2px(context, 3.0f);
        this.mPointTopBottomMargin = BGABannerUtil.dp2px(context, 6.0f);
        this.mPointContainerLeftRightPadding = BGABannerUtil.dp2px(context, 10.0f);
        this.mTipTextSize = BGABannerUtil.sp2px(context, 10.0f);
        this.mPointContainerBackgroundDrawable = new ColorDrawable(Color.parseColor("#44aaaaaa"));
        this.mTransitionEffect = TransitionEffect.Default;
        this.mNumberIndicatorTextSize = BGABannerUtil.sp2px(context, 10.0f);
        this.mContentBottomMargin = 0;
        this.mAspectRatio = 0.0f;
    }

    private void initCustomAttrs(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.BGABanner);
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            initCustomAttr(obtainStyledAttributes.getIndex(i), obtainStyledAttributes);
        }
        obtainStyledAttributes.recycle();
    }

    private void initCustomAttr(int i, TypedArray typedArray) {
        int i2;
        if (i == R.styleable.BGABanner_banner_pointDrawable) {
            this.mPointDrawableResId = typedArray.getResourceId(i, R.drawable.bga_banner_selector_point_solid);
        } else if (i == R.styleable.BGABanner_banner_pointContainerBackground) {
            this.mPointContainerBackgroundDrawable = typedArray.getDrawable(i);
        } else if (i == R.styleable.BGABanner_banner_pointLeftRightMargin) {
            this.mPointLeftRightMargin = typedArray.getDimensionPixelSize(i, this.mPointLeftRightMargin);
        } else if (i == R.styleable.BGABanner_banner_pointContainerLeftRightPadding) {
            this.mPointContainerLeftRightPadding = typedArray.getDimensionPixelSize(i, this.mPointContainerLeftRightPadding);
        } else if (i == R.styleable.BGABanner_banner_pointTopBottomMargin) {
            this.mPointTopBottomMargin = typedArray.getDimensionPixelSize(i, this.mPointTopBottomMargin);
        } else if (i == R.styleable.BGABanner_banner_indicatorGravity) {
            this.mPointGravity = typedArray.getInt(i, this.mPointGravity);
        } else if (i == R.styleable.BGABanner_banner_pointAutoPlayAble) {
            this.mAutoPlayAble = typedArray.getBoolean(i, this.mAutoPlayAble);
        } else if (i == R.styleable.BGABanner_banner_pointAutoPlayInterval) {
            this.mAutoPlayInterval = typedArray.getInteger(i, this.mAutoPlayInterval);
        } else if (i == R.styleable.BGABanner_banner_pageChangeDuration) {
            this.mPageChangeDuration = typedArray.getInteger(i, this.mPageChangeDuration);
        } else if (i == R.styleable.BGABanner_banner_transitionEffect) {
            this.mTransitionEffect = TransitionEffect.values()[typedArray.getInt(i, TransitionEffect.Accordion.ordinal())];
        } else if (i == R.styleable.BGABanner_banner_tipTextColor) {
            this.mTipTextColor = typedArray.getColor(i, this.mTipTextColor);
        } else if (i == R.styleable.BGABanner_banner_tipTextSize) {
            this.mTipTextSize = typedArray.getDimensionPixelSize(i, this.mTipTextSize);
        } else if (i == R.styleable.BGABanner_banner_placeholderDrawable) {
            this.mPlaceholderDrawableResId = typedArray.getResourceId(i, this.mPlaceholderDrawableResId);
        } else if (i == R.styleable.BGABanner_banner_isNumberIndicator) {
            this.mIsNumberIndicator = typedArray.getBoolean(i, this.mIsNumberIndicator);
        } else if (i == R.styleable.BGABanner_banner_numberIndicatorTextColor) {
            this.mNumberIndicatorTextColor = typedArray.getColor(i, this.mNumberIndicatorTextColor);
        } else if (i == R.styleable.BGABanner_banner_numberIndicatorTextSize) {
            this.mNumberIndicatorTextSize = typedArray.getDimensionPixelSize(i, this.mNumberIndicatorTextSize);
        } else if (i == R.styleable.BGABanner_banner_numberIndicatorBackground) {
            this.mNumberIndicatorBackground = typedArray.getDrawable(i);
        } else if (i == R.styleable.BGABanner_banner_isNeedShowIndicatorOnOnlyOnePage) {
            this.mIsNeedShowIndicatorOnOnlyOnePage = typedArray.getBoolean(i, this.mIsNeedShowIndicatorOnOnlyOnePage);
        } else if (i == R.styleable.BGABanner_banner_contentBottomMargin) {
            this.mContentBottomMargin = typedArray.getDimensionPixelSize(i, this.mContentBottomMargin);
        } else if (i == R.styleable.BGABanner_banner_aspectRatio) {
            this.mAspectRatio = typedArray.getFloat(i, this.mAspectRatio);
        } else if (i == R.styleable.BGABanner_android_scaleType && (i2 = typedArray.getInt(i, -1)) >= 0 && i2 < sScaleTypeArray.length) {
            this.mScaleType = sScaleTypeArray[i2];
        }
    }

    private void initView(Context context) {
        RelativeLayout relativeLayout = new RelativeLayout(context);
        if (Build.VERSION.SDK_INT >= 16) {
            relativeLayout.setBackground(this.mPointContainerBackgroundDrawable);
        } else {
            relativeLayout.setBackgroundDrawable(this.mPointContainerBackgroundDrawable);
        }
        relativeLayout.setPadding(this.mPointContainerLeftRightPadding, this.mPointTopBottomMargin, this.mPointContainerLeftRightPadding, this.mPointTopBottomMargin);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        if ((this.mPointGravity & 112) == 48) {
            layoutParams.addRule(10);
        } else {
            layoutParams.addRule(12);
        }
        addView(relativeLayout, layoutParams);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(15);
        if (this.mIsNumberIndicator) {
            this.mNumberIndicatorTv = new TextView(context);
            this.mNumberIndicatorTv.setId(R.id.banner_indicatorId);
            this.mNumberIndicatorTv.setGravity(16);
            this.mNumberIndicatorTv.setSingleLine(true);
            this.mNumberIndicatorTv.setEllipsize(TextUtils.TruncateAt.END);
            this.mNumberIndicatorTv.setTextColor(this.mNumberIndicatorTextColor);
            this.mNumberIndicatorTv.setTextSize(0, (float) this.mNumberIndicatorTextSize);
            this.mNumberIndicatorTv.setVisibility(4);
            if (this.mNumberIndicatorBackground != null) {
                if (Build.VERSION.SDK_INT >= 16) {
                    this.mNumberIndicatorTv.setBackground(this.mNumberIndicatorBackground);
                } else {
                    this.mNumberIndicatorTv.setBackgroundDrawable(this.mNumberIndicatorBackground);
                }
            }
            relativeLayout.addView(this.mNumberIndicatorTv, layoutParams2);
        } else {
            this.mPointRealContainerLl = new LinearLayout(context);
            this.mPointRealContainerLl.setId(R.id.banner_indicatorId);
            this.mPointRealContainerLl.setOrientation(0);
            this.mPointRealContainerLl.setGravity(16);
            relativeLayout.addView(this.mPointRealContainerLl, layoutParams2);
        }
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams3.addRule(15);
        this.mTipTv = new TextView(context);
        this.mTipTv.setGravity(16);
        this.mTipTv.setSingleLine(true);
        this.mTipTv.setEllipsize(TextUtils.TruncateAt.END);
        this.mTipTv.setTextColor(this.mTipTextColor);
        this.mTipTv.setTextSize(0, (float) this.mTipTextSize);
        relativeLayout.addView(this.mTipTv, layoutParams3);
        int i = this.mPointGravity & 7;
        if (i == 3) {
            layoutParams2.addRule(9);
            layoutParams3.addRule(1, R.id.banner_indicatorId);
            this.mTipTv.setGravity(21);
        } else if (i == 5) {
            layoutParams2.addRule(11);
            layoutParams3.addRule(0, R.id.banner_indicatorId);
        } else {
            layoutParams2.addRule(14);
            layoutParams3.addRule(0, R.id.banner_indicatorId);
        }
        showPlaceholder();
    }

    public void showPlaceholder() {
        if (this.mPlaceholderIv == null && this.mPlaceholderDrawableResId != -1) {
            this.mPlaceholderIv = BGABannerUtil.getItemImageView(getContext(), this.mPlaceholderDrawableResId, new BGALocalImageSize(720, 360, 640.0f, 320.0f), this.mScaleType);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
            layoutParams.setMargins(0, 0, 0, this.mContentBottomMargin);
            addView(this.mPlaceholderIv, layoutParams);
        }
    }

    public void setPageChangeDuration(int i) {
        if (i >= 0 && i <= 2000) {
            this.mPageChangeDuration = i;
            if (this.mViewPager != null) {
                this.mViewPager.setPageChangeDuration(i);
            }
        }
    }

    public void setAutoPlayAble(boolean z) {
        this.mAutoPlayAble = z;
        stopAutoPlay();
        if (this.mViewPager != null && this.mViewPager.getAdapter() != null) {
            this.mViewPager.getAdapter().notifyDataSetChanged();
        }
    }

    public void setAutoPlayInterval(int i) {
        this.mAutoPlayInterval = i;
    }

    public void setData(List<View> list, List<? extends Object> list2, List<String> list3) {
        if (BGABannerUtil.isCollectionEmpty(list, new Collection[0])) {
            this.mAutoPlayAble = false;
            list = new ArrayList<>();
            list2 = new ArrayList<>();
            list3 = new ArrayList<>();
        }
        if (this.mAutoPlayAble && list.size() < 3 && this.mHackyViews == null) {
            this.mAutoPlayAble = false;
        }
        this.mModels = list2;
        this.mViews = list;
        this.mTips = list3;
        initIndicator();
        initViewPager();
        removePlaceholder();
    }

    public void setData(@LayoutRes int i, List<? extends Object> list, List<String> list2) {
        this.mViews = new ArrayList();
        if (list == null) {
            list = new ArrayList<>();
            list2 = new ArrayList<>();
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            this.mViews.add(View.inflate(getContext(), i, (ViewGroup) null));
        }
        if (this.mAutoPlayAble && this.mViews.size() < 3) {
            this.mHackyViews = new ArrayList(this.mViews);
            this.mHackyViews.add(View.inflate(getContext(), i, (ViewGroup) null));
            if (this.mHackyViews.size() == 2) {
                this.mHackyViews.add(View.inflate(getContext(), i, (ViewGroup) null));
            }
        }
        setData(this.mViews, list, list2);
    }

    public void setData(List<? extends Object> list, List<String> list2) {
        setData(R.layout.bga_banner_item_image, list, list2);
    }

    public void setData(List<View> list) {
        setData(list, (List<? extends Object>) null, (List<String>) null);
    }

    public void setData(@Nullable BGALocalImageSize bGALocalImageSize, @Nullable ImageView.ScaleType scaleType, @DrawableRes int... iArr) {
        if (bGALocalImageSize == null) {
            bGALocalImageSize = new BGALocalImageSize(720, 1280, 320.0f, 640.0f);
        }
        if (scaleType != null) {
            this.mScaleType = scaleType;
        }
        ArrayList arrayList = new ArrayList();
        for (int itemImageView : iArr) {
            arrayList.add(BGABannerUtil.getItemImageView(getContext(), itemImageView, bGALocalImageSize, this.mScaleType));
        }
        setData(arrayList);
    }

    public void setAllowUserScrollable(boolean z) {
        this.mAllowUserScrollable = z;
        if (this.mViewPager != null) {
            this.mViewPager.setAllowUserScrollable(this.mAllowUserScrollable);
        }
    }

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.mOnPageChangeListener = onPageChangeListener;
    }

    public void setEnterSkipViewId(int i, int i2) {
        if (i != 0) {
            this.mEnterView = ((Activity) getContext()).findViewById(i);
        }
        if (i2 != 0) {
            this.mSkipView = ((Activity) getContext()).findViewById(i2);
        }
    }

    public void setEnterSkipViewIdAndDelegate(int i, int i2, GuideDelegate guideDelegate) {
        if (guideDelegate != null) {
            this.mGuideDelegate = guideDelegate;
            if (i != 0) {
                this.mEnterView = ((Activity) getContext()).findViewById(i);
                this.mEnterView.setOnClickListener(this.mGuideOnNoDoubleClickListener);
            }
            if (i2 != 0) {
                this.mSkipView = ((Activity) getContext()).findViewById(i2);
                this.mSkipView.setOnClickListener(this.mGuideOnNoDoubleClickListener);
            }
        }
    }

    public int getCurrentItem() {
        if (this.mViewPager == null || BGABannerUtil.isCollectionEmpty(this.mViews, new Collection[0])) {
            return -1;
        }
        return this.mViewPager.getCurrentItem() % this.mViews.size();
    }

    public int getItemCount() {
        if (this.mViews == null) {
            return 0;
        }
        return this.mViews.size();
    }

    public List<? extends View> getViews() {
        return this.mViews;
    }

    public <VT extends View> VT getItemView(int i) {
        if (this.mViews == null) {
            return null;
        }
        return (View) this.mViews.get(i);
    }

    public ImageView getItemImageView(int i) {
        return (ImageView) getItemView(i);
    }

    public List<String> getTips() {
        return this.mTips;
    }

    public BGAViewPager getViewPager() {
        return this.mViewPager;
    }

    public void setOverScrollMode(int i) {
        this.mOverScrollMode = i;
        if (this.mViewPager != null) {
            this.mViewPager.setOverScrollMode(this.mOverScrollMode);
        }
    }

    private void initIndicator() {
        if (this.mPointRealContainerLl != null) {
            this.mPointRealContainerLl.removeAllViews();
            if (this.mIsNeedShowIndicatorOnOnlyOnePage || (!this.mIsNeedShowIndicatorOnOnlyOnePage && this.mViews.size() > 1)) {
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
                layoutParams.setMargins(this.mPointLeftRightMargin, 0, this.mPointLeftRightMargin, 0);
                for (int i = 0; i < this.mViews.size(); i++) {
                    ImageView imageView = new ImageView(getContext());
                    imageView.setLayoutParams(layoutParams);
                    imageView.setImageResource(this.mPointDrawableResId);
                    this.mPointRealContainerLl.addView(imageView);
                }
            }
        }
        if (this.mNumberIndicatorTv == null) {
            return;
        }
        if (this.mIsNeedShowIndicatorOnOnlyOnePage || (!this.mIsNeedShowIndicatorOnOnlyOnePage && this.mViews.size() > 1)) {
            this.mNumberIndicatorTv.setVisibility(0);
        } else {
            this.mNumberIndicatorTv.setVisibility(4);
        }
    }

    private void initViewPager() {
        if (this.mViewPager != null && equals(this.mViewPager.getParent())) {
            removeView(this.mViewPager);
            this.mViewPager = null;
        }
        this.mViewPager = new BGAViewPager(getContext());
        this.mViewPager.setOffscreenPageLimit(1);
        this.mViewPager.setAdapter(new PageAdapter());
        this.mViewPager.addOnPageChangeListener(this);
        this.mViewPager.setOverScrollMode(this.mOverScrollMode);
        this.mViewPager.setAllowUserScrollable(this.mAllowUserScrollable);
        this.mViewPager.setPageTransformer(true, BGAPageTransformer.getPageTransformer(this.mTransitionEffect));
        setPageChangeDuration(this.mPageChangeDuration);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.setMargins(0, 0, 0, this.mContentBottomMargin);
        addView(this.mViewPager, 0, layoutParams);
        if (this.mAutoPlayAble) {
            this.mViewPager.setAutoPlayDelegate(this);
            this.mViewPager.setCurrentItem(1073741823 - (1073741823 % this.mViews.size()));
            startAutoPlay();
            return;
        }
        switchToPoint(0);
    }

    public void removePlaceholder() {
        if (this.mPlaceholderIv != null && equals(this.mPlaceholderIv.getParent())) {
            removeView(this.mPlaceholderIv);
            this.mPlaceholderIv = null;
        }
    }

    public void setAspectRatio(float f) {
        this.mAspectRatio = f;
        requestLayout();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        if (this.mAspectRatio > 0.0f) {
            i2 = View.MeasureSpec.makeMeasureSpec((int) (((float) View.MeasureSpec.getSize(i)) / this.mAspectRatio), 1073741824);
        }
        super.onMeasure(i, i2);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.mAutoPlayAble) {
            int action = motionEvent.getAction();
            if (action != 3) {
                switch (action) {
                    case 0:
                        stopAutoPlay();
                        break;
                    case 1:
                        break;
                }
            }
            startAutoPlay();
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public void setIsNeedShowIndicatorOnOnlyOnePage(boolean z) {
        this.mIsNeedShowIndicatorOnOnlyOnePage = z;
    }

    public void setCurrentItem(int i) {
        if (this.mViewPager != null && this.mViews != null) {
            if (i <= getItemCount() - 1) {
                if (this.mAutoPlayAble) {
                    int currentItem = this.mViewPager.getCurrentItem();
                    int size = i - (currentItem % this.mViews.size());
                    if (size < 0) {
                        for (int i2 = -1; i2 >= size; i2--) {
                            this.mViewPager.setCurrentItem(currentItem + i2, false);
                        }
                    } else if (size > 0) {
                        for (int i3 = 1; i3 <= size; i3++) {
                            this.mViewPager.setCurrentItem(currentItem + i3, false);
                        }
                    }
                    startAutoPlay();
                    return;
                }
                this.mViewPager.setCurrentItem(i, false);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        if (i == 0) {
            startAutoPlay();
        } else if (i == 4 || i == 8) {
            onInvisibleToUser();
        }
    }

    private void onInvisibleToUser() {
        stopAutoPlay();
        if (!this.mIsFirstInvisible && this.mAutoPlayAble && this.mViewPager != null && getItemCount() > 0 && this.mPageScrollPositionOffset != 0.0f) {
            this.mViewPager.setCurrentItem(this.mViewPager.getCurrentItem() - 1);
            this.mViewPager.setCurrentItem(this.mViewPager.getCurrentItem() + 1);
        }
        this.mIsFirstInvisible = false;
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        onInvisibleToUser();
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        startAutoPlay();
    }

    public void startAutoPlay() {
        stopAutoPlay();
        if (this.mAutoPlayAble) {
            postDelayed(this.mAutoPlayTask, (long) this.mAutoPlayInterval);
        }
    }

    public void stopAutoPlay() {
        if (this.mAutoPlayTask != null) {
            removeCallbacks(this.mAutoPlayTask);
        }
    }

    private void switchToPoint(int i) {
        if (this.mTipTv != null) {
            if (this.mTips == null || this.mTips.size() < 1 || i >= this.mTips.size()) {
                this.mTipTv.setVisibility(8);
            } else {
                this.mTipTv.setVisibility(0);
                this.mTipTv.setText(this.mTips.get(i));
            }
        }
        if (this.mPointRealContainerLl != null) {
            if (this.mViews == null || this.mViews.size() <= 0 || i >= this.mViews.size() || (!this.mIsNeedShowIndicatorOnOnlyOnePage && (this.mIsNeedShowIndicatorOnOnlyOnePage || this.mViews.size() <= 1))) {
                this.mPointRealContainerLl.setVisibility(8);
            } else {
                this.mPointRealContainerLl.setVisibility(0);
                int i2 = 0;
                while (i2 < this.mPointRealContainerLl.getChildCount()) {
                    this.mPointRealContainerLl.getChildAt(i2).setEnabled(i2 == i);
                    this.mPointRealContainerLl.getChildAt(i2).requestLayout();
                    i2++;
                }
            }
        }
        if (this.mNumberIndicatorTv == null) {
            return;
        }
        if (this.mViews == null || this.mViews.size() <= 0 || i >= this.mViews.size() || (!this.mIsNeedShowIndicatorOnOnlyOnePage && (this.mIsNeedShowIndicatorOnOnlyOnePage || this.mViews.size() <= 1))) {
            this.mNumberIndicatorTv.setVisibility(8);
            return;
        }
        this.mNumberIndicatorTv.setVisibility(0);
        TextView textView = this.mNumberIndicatorTv;
        textView.setText((i + 1) + "/" + this.mViews.size());
    }

    public void setTransitionEffect(TransitionEffect transitionEffect) {
        this.mTransitionEffect = transitionEffect;
        if (this.mViewPager != null) {
            initViewPager();
            if (this.mHackyViews == null) {
                BGABannerUtil.resetPageTransformer(this.mViews);
            } else {
                BGABannerUtil.resetPageTransformer(this.mHackyViews);
            }
        }
    }

    public void setPageTransformer(ViewPager.PageTransformer pageTransformer) {
        if (pageTransformer != null && this.mViewPager != null) {
            this.mViewPager.setPageTransformer(true, pageTransformer);
        }
    }

    /* access modifiers changed from: private */
    public void switchToNextPage() {
        if (this.mViewPager != null) {
            this.mViewPager.setCurrentItem(this.mViewPager.getCurrentItem() + 1);
        }
    }

    public void handleAutoPlayActionUpOrCancel(float f) {
        if (this.mViewPager == null) {
            return;
        }
        if (this.mPageScrollPosition < this.mViewPager.getCurrentItem()) {
            if (f > 400.0f || (this.mPageScrollPositionOffset < 0.7f && f > -400.0f)) {
                this.mViewPager.setBannerCurrentItemInternal(this.mPageScrollPosition, true);
            } else {
                this.mViewPager.setBannerCurrentItemInternal(this.mPageScrollPosition + 1, true);
            }
        } else if (f < -400.0f || (this.mPageScrollPositionOffset > 0.3f && f < 400.0f)) {
            this.mViewPager.setBannerCurrentItemInternal(this.mPageScrollPosition + 1, true);
        } else {
            this.mViewPager.setBannerCurrentItemInternal(this.mPageScrollPosition, true);
        }
    }

    public void onPageSelected(int i) {
        int size = i % this.mViews.size();
        switchToPoint(size);
        if (this.mOnPageChangeListener != null) {
            this.mOnPageChangeListener.onPageSelected(size);
        }
    }

    public void onPageScrolled(int i, float f, int i2) {
        handleGuideViewVisibility(i, f);
        this.mPageScrollPosition = i;
        this.mPageScrollPositionOffset = f;
        if (this.mTipTv != null) {
            if (BGABannerUtil.isCollectionNotEmpty(this.mTips, new Collection[0])) {
                this.mTipTv.setVisibility(0);
                int size = i % this.mTips.size();
                int size2 = (i + 1) % this.mTips.size();
                if (size2 < this.mTips.size() && size < this.mTips.size()) {
                    if (((double) f) > 0.5d) {
                        this.mTipTv.setText(this.mTips.get(size2));
                        ViewCompat.setAlpha(this.mTipTv, f);
                    } else {
                        ViewCompat.setAlpha(this.mTipTv, 1.0f - f);
                        this.mTipTv.setText(this.mTips.get(size));
                    }
                }
            } else {
                this.mTipTv.setVisibility(8);
            }
        }
        if (this.mOnPageChangeListener != null) {
            this.mOnPageChangeListener.onPageScrolled(i % this.mViews.size(), f, i2);
        }
    }

    public void onPageScrollStateChanged(int i) {
        if (this.mOnPageChangeListener != null) {
            this.mOnPageChangeListener.onPageScrollStateChanged(i);
        }
    }

    private void handleGuideViewVisibility(int i, float f) {
        if (this.mEnterView != null || this.mSkipView != null) {
            if (i == getItemCount() - 2) {
                if (this.mEnterView != null) {
                    ViewCompat.setAlpha(this.mEnterView, f);
                }
                if (this.mSkipView != null) {
                    ViewCompat.setAlpha(this.mSkipView, 1.0f - f);
                }
                if (f > 0.5f) {
                    if (this.mEnterView != null) {
                        this.mEnterView.setVisibility(0);
                    }
                    if (this.mSkipView != null) {
                        this.mSkipView.setVisibility(8);
                        return;
                    }
                    return;
                }
                if (this.mEnterView != null) {
                    this.mEnterView.setVisibility(8);
                }
                if (this.mSkipView != null) {
                    this.mSkipView.setVisibility(0);
                }
            } else if (i == getItemCount() - 1) {
                if (this.mSkipView != null) {
                    this.mSkipView.setVisibility(8);
                }
                if (this.mEnterView != null) {
                    this.mEnterView.setVisibility(0);
                    ViewCompat.setAlpha(this.mEnterView, 1.0f);
                }
            } else {
                if (this.mSkipView != null) {
                    this.mSkipView.setVisibility(0);
                    ViewCompat.setAlpha(this.mSkipView, 1.0f);
                }
                if (this.mEnterView != null) {
                    this.mEnterView.setVisibility(8);
                }
            }
        }
    }

    public void setDelegate(Delegate delegate) {
        this.mDelegate = delegate;
    }

    public void setAdapter(Adapter adapter) {
        this.mAdapter = adapter;
    }

    private class PageAdapter extends PagerAdapter {
        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        }

        public int getItemPosition(Object obj) {
            return -2;
        }

        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        private PageAdapter() {
        }

        public int getCount() {
            if (BGABanner.this.mViews == null) {
                return 0;
            }
            return BGABanner.this.mAutoPlayAble ? ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED : BGABanner.this.mViews.size();
        }

        public Object instantiateItem(ViewGroup viewGroup, int i) {
            View view;
            if (BGABannerUtil.isCollectionEmpty(BGABanner.this.mViews, new Collection[0])) {
                return null;
            }
            int size = i % BGABanner.this.mViews.size();
            if (BGABanner.this.mHackyViews == null) {
                view = (View) BGABanner.this.mViews.get(size);
            } else {
                view = (View) BGABanner.this.mHackyViews.get(i % BGABanner.this.mHackyViews.size());
            }
            if (BGABanner.this.mDelegate != null) {
                view.setOnClickListener(new BGAOnNoDoubleClickListener() {
                    public void onNoDoubleClick(View view) {
                        int currentItem = BGABanner.this.mViewPager.getCurrentItem() % BGABanner.this.mViews.size();
                        if (BGABannerUtil.isIndexNotOutOfBounds(currentItem, BGABanner.this.mModels)) {
                            BGABanner.this.mDelegate.onBannerItemClick(BGABanner.this, view, BGABanner.this.mModels.get(currentItem), currentItem);
                        } else if (BGABannerUtil.isCollectionEmpty(BGABanner.this.mModels, new Collection[0])) {
                            BGABanner.this.mDelegate.onBannerItemClick(BGABanner.this, view, null, currentItem);
                        }
                    }
                });
            }
            if (BGABanner.this.mAdapter != null) {
                if (BGABannerUtil.isIndexNotOutOfBounds(size, BGABanner.this.mModels)) {
                    BGABanner.this.mAdapter.fillBannerItem(BGABanner.this, view, BGABanner.this.mModels.get(size), size);
                } else if (BGABannerUtil.isCollectionEmpty(BGABanner.this.mModels, new Collection[0])) {
                    BGABanner.this.mAdapter.fillBannerItem(BGABanner.this, view, null, size);
                }
            }
            ViewParent parent = view.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(view);
            }
            viewGroup.addView(view);
            return view;
        }
    }

    private static class AutoPlayTask implements Runnable {
        private final WeakReference<BGABanner> mBanner;

        private AutoPlayTask(BGABanner bGABanner) {
            this.mBanner = new WeakReference<>(bGABanner);
        }

        public void run() {
            BGABanner bGABanner = (BGABanner) this.mBanner.get();
            if (bGABanner != null) {
                bGABanner.switchToNextPage();
                bGABanner.startAutoPlay();
            }
        }
    }
}

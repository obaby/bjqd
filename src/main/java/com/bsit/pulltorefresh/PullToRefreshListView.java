package com.bsit.pulltorefresh;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.bsit.pulltorefresh.PullToRefreshBase;
import com.bsit.pulltorefresh.internal.EmptyViewMethodAccessor;
import com.bsit.pulltorefresh.internal.LoadingLayout;

public class PullToRefreshListView extends PullToRefreshAdapterViewBase<ListView> {
    private LoadingLayout mFooterLoadingView;
    private LoadingLayout mHeaderLoadingView;
    private boolean mListViewExtrasEnabled;
    /* access modifiers changed from: private */
    public FrameLayout mLvFooterLoadingFrame;

    public PullToRefreshListView(Context context) {
        super(context);
    }

    public PullToRefreshListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public PullToRefreshListView(Context context, PullToRefreshBase.Mode mode) {
        super(context, mode);
    }

    public PullToRefreshListView(Context context, PullToRefreshBase.Mode mode, PullToRefreshBase.AnimationStyle animationStyle) {
        super(context, mode, animationStyle);
    }

    public final PullToRefreshBase.Orientation getPullToRefreshScrollDirection() {
        return PullToRefreshBase.Orientation.VERTICAL;
    }

    /* access modifiers changed from: protected */
    public void onRefreshing(boolean z) {
        int i;
        int i2;
        LoadingLayout loadingLayout;
        LoadingLayout loadingLayout2;
        LoadingLayout loadingLayout3;
        ListAdapter adapter = ((ListView) this.mRefreshableView).getAdapter();
        if (!this.mListViewExtrasEnabled || !getShowViewWhileRefreshing() || adapter == null || adapter.isEmpty()) {
            super.onRefreshing(z);
            return;
        }
        super.onRefreshing(false);
        switch (getCurrentMode()) {
            case MANUAL_REFRESH_ONLY:
            case PULL_FROM_END:
                loadingLayout3 = getFooterLayout();
                loadingLayout2 = this.mFooterLoadingView;
                loadingLayout = this.mHeaderLoadingView;
                i2 = ((ListView) this.mRefreshableView).getCount() - 1;
                i = getScrollY() - getFooterSize();
                break;
            default:
                loadingLayout3 = getHeaderLayout();
                loadingLayout2 = this.mHeaderLoadingView;
                loadingLayout = this.mFooterLoadingView;
                i = getHeaderSize() + getScrollY();
                i2 = 0;
                break;
        }
        loadingLayout3.reset();
        loadingLayout3.hideAllViews();
        loadingLayout.setVisibility(8);
        loadingLayout2.setVisibility(0);
        loadingLayout2.refreshing();
        if (z) {
            disableLoadingLayoutVisibilityChanges();
            setHeaderScroll(i);
            ((ListView) this.mRefreshableView).setSelection(i2);
            smoothScrollTo(0);
        }
    }

    /* access modifiers changed from: protected */
    public void onReset() {
        int i;
        int i2;
        LoadingLayout loadingLayout;
        LoadingLayout loadingLayout2;
        if (!this.mListViewExtrasEnabled) {
            super.onReset();
            return;
        }
        boolean z = false;
        boolean z2 = true;
        switch (getCurrentMode()) {
            case MANUAL_REFRESH_ONLY:
            case PULL_FROM_END:
                loadingLayout2 = getFooterLayout();
                loadingLayout = this.mFooterLoadingView;
                i2 = ((ListView) this.mRefreshableView).getCount() - 1;
                i = getFooterSize();
                if (Math.abs(((ListView) this.mRefreshableView).getLastVisiblePosition() - i2) <= 1) {
                    z = true;
                    break;
                }
                break;
            default:
                loadingLayout2 = getHeaderLayout();
                loadingLayout = this.mHeaderLoadingView;
                i = -getHeaderSize();
                if (Math.abs(((ListView) this.mRefreshableView).getFirstVisiblePosition() - 0) > 1) {
                    z2 = false;
                }
                z = z2;
                i2 = 0;
                break;
        }
        if (loadingLayout.getVisibility() == 0) {
            loadingLayout2.showInvisibleViews();
            loadingLayout.setVisibility(8);
            if (z && getState() != PullToRefreshBase.State.MANUAL_REFRESHING) {
                ((ListView) this.mRefreshableView).setSelection(i2);
                setHeaderScroll(i);
            }
        }
        super.onReset();
    }

    /* access modifiers changed from: protected */
    public LoadingLayoutProxy createLoadingLayoutProxy(boolean z, boolean z2) {
        LoadingLayoutProxy createLoadingLayoutProxy = super.createLoadingLayoutProxy(z, z2);
        if (this.mListViewExtrasEnabled) {
            PullToRefreshBase.Mode mode = getMode();
            if (z && mode.showHeaderLoadingLayout()) {
                createLoadingLayoutProxy.addLayout(this.mHeaderLoadingView);
            }
            if (z2 && mode.showFooterLoadingLayout()) {
                createLoadingLayoutProxy.addLayout(this.mFooterLoadingView);
            }
        }
        return createLoadingLayoutProxy;
    }

    /* access modifiers changed from: protected */
    public ListView createListView(Context context, AttributeSet attributeSet) {
        if (Build.VERSION.SDK_INT >= 9) {
            return new InternalListViewSDK9(context, attributeSet);
        }
        return new InternalListView(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public ListView createRefreshableView(Context context, AttributeSet attributeSet) {
        ListView createListView = createListView(context, attributeSet);
        createListView.setId(16908298);
        return createListView;
    }

    /* access modifiers changed from: protected */
    public void handleStyledAttributes(TypedArray typedArray) {
        super.handleStyledAttributes(typedArray);
        this.mListViewExtrasEnabled = typedArray.getBoolean(R.styleable.PullToRefresh_ptrListViewExtrasEnabled, true);
        if (this.mListViewExtrasEnabled) {
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2, 1);
            FrameLayout frameLayout = new FrameLayout(getContext());
            this.mHeaderLoadingView = createLoadingLayout(getContext(), PullToRefreshBase.Mode.PULL_FROM_START, typedArray);
            this.mHeaderLoadingView.setVisibility(8);
            frameLayout.addView(this.mHeaderLoadingView, layoutParams);
            ((ListView) this.mRefreshableView).addHeaderView(frameLayout, (Object) null, false);
            this.mLvFooterLoadingFrame = new FrameLayout(getContext());
            this.mFooterLoadingView = createLoadingLayout(getContext(), PullToRefreshBase.Mode.PULL_FROM_END, typedArray);
            this.mFooterLoadingView.setVisibility(8);
            this.mLvFooterLoadingFrame.addView(this.mFooterLoadingView, layoutParams);
            if (!typedArray.hasValue(R.styleable.PullToRefresh_ptrScrollingWhileRefreshingEnabled)) {
                setScrollingWhileRefreshingEnabled(true);
            }
        }
    }

    @TargetApi(9)
    final class InternalListViewSDK9 extends InternalListView {
        public InternalListViewSDK9(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        /* access modifiers changed from: protected */
        public boolean overScrollBy(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z) {
            boolean overScrollBy = super.overScrollBy(i, i2, i3, i4, i5, i6, i7, i8, z);
            OverscrollHelper.overScrollBy(PullToRefreshListView.this, i, i3, i2, i4, z);
            return overScrollBy;
        }
    }

    protected class InternalListView extends ListView implements EmptyViewMethodAccessor {
        private boolean mAddedLvFooter = false;

        public InternalListView(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        /* access modifiers changed from: protected */
        public void dispatchDraw(Canvas canvas) {
            try {
                super.dispatchDraw(canvas);
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        }

        public boolean dispatchTouchEvent(MotionEvent motionEvent) {
            try {
                return super.dispatchTouchEvent(motionEvent);
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
                return false;
            }
        }

        public void setAdapter(ListAdapter listAdapter) {
            if (PullToRefreshListView.this.mLvFooterLoadingFrame != null && !this.mAddedLvFooter) {
                addFooterView(PullToRefreshListView.this.mLvFooterLoadingFrame, (Object) null, false);
                this.mAddedLvFooter = true;
            }
            super.setAdapter(listAdapter);
        }

        public void setEmptyView(View view) {
            PullToRefreshListView.this.setEmptyView(view);
        }

        public void setEmptyViewInternal(View view) {
            super.setEmptyView(view);
        }
    }
}

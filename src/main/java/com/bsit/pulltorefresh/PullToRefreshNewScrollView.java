package com.bsit.pulltorefresh;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;
import com.bsit.pulltorefresh.PullToRefreshBase;

public class PullToRefreshNewScrollView extends PullToRefreshBase<ScrollView> {
    public PullToRefreshNewScrollView(Context context) {
        super(context);
    }

    public PullToRefreshNewScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public PullToRefreshNewScrollView(Context context, PullToRefreshBase.Mode mode) {
        super(context, mode);
    }

    public PullToRefreshNewScrollView(Context context, PullToRefreshBase.Mode mode, PullToRefreshBase.AnimationStyle animationStyle) {
        super(context, mode, animationStyle);
    }

    public final PullToRefreshBase.Orientation getPullToRefreshScrollDirection() {
        return PullToRefreshBase.Orientation.VERTICAL;
    }

    /* access modifiers changed from: protected */
    public ScrollView createRefreshableView(Context context, AttributeSet attributeSet) {
        ScrollView scrollView = new ScrollView(context, attributeSet);
        scrollView.setId(R.id.pull_to_refresh_scrollview);
        return scrollView;
    }

    /* access modifiers changed from: protected */
    public boolean isReadyForPullStart() {
        return ((ScrollView) this.mRefreshableView).getScrollY() == 0;
    }

    /* access modifiers changed from: protected */
    public boolean isReadyForPullEnd() {
        View childAt = ((ScrollView) this.mRefreshableView).getChildAt(0);
        if (childAt == null || ((ScrollView) this.mRefreshableView).getScrollY() < childAt.getHeight() - getHeight()) {
            return false;
        }
        return true;
    }

    @TargetApi(9)
    final class InternalScrollViewSDK9 extends ScrollView {
        public InternalScrollViewSDK9(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        /* access modifiers changed from: protected */
        public boolean overScrollBy(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z) {
            boolean overScrollBy = super.overScrollBy(i, i2, i3, i4, i5, i6, i7, i8, z);
            OverscrollHelper.overScrollBy(PullToRefreshNewScrollView.this, i, i3, i2, i4, getScrollRange(), z);
            return overScrollBy;
        }

        private int getScrollRange() {
            if (getChildCount() > 0) {
                return Math.max(0, getChildAt(0).getHeight() - ((getHeight() - getPaddingBottom()) - getPaddingTop()));
            }
            return 0;
        }
    }
}

package cn.xports.widget.pagelayout;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.View;

public class PagerGridSnapHelper extends SnapHelper {
    private RecyclerView mRecyclerView;

    public void attachToRecyclerView(@Nullable RecyclerView recyclerView) throws IllegalStateException {
        super.attachToRecyclerView(recyclerView);
        this.mRecyclerView = recyclerView;
    }

    @Nullable
    public int[] calculateDistanceToFinalSnap(@NonNull RecyclerView.LayoutManager layoutManager, @NonNull View view) {
        int position = layoutManager.getPosition(view);
        PagerConfig.Loge("findTargetSnapPosition, pos = " + position);
        return layoutManager instanceof PagerGridLayoutManager ? ((PagerGridLayoutManager) layoutManager).getSnapOffset(position) : new int[2];
    }

    @Nullable
    public View findSnapView(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager instanceof PagerGridLayoutManager) {
            return ((PagerGridLayoutManager) layoutManager).findSnapView();
        }
        return null;
    }

    public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int i, int i2) {
        int i3;
        PagerConfig.Loge("findTargetSnapPosition, velocityX = " + i + ", velocityY" + i2);
        if (layoutManager != null && (layoutManager instanceof PagerGridLayoutManager)) {
            PagerGridLayoutManager pagerGridLayoutManager = (PagerGridLayoutManager) layoutManager;
            if (pagerGridLayoutManager.canScrollHorizontally()) {
                if (i > PagerConfig.getFlingThreshold()) {
                    i3 = pagerGridLayoutManager.findNextPageFirstPos();
                } else if (i < (-PagerConfig.getFlingThreshold())) {
                    i3 = pagerGridLayoutManager.findPrePageFirstPos();
                }
                PagerConfig.Loge("findTargetSnapPosition, target = " + i3);
                return i3;
            } else if (pagerGridLayoutManager.canScrollVertically()) {
                if (i2 > PagerConfig.getFlingThreshold()) {
                    i3 = pagerGridLayoutManager.findNextPageFirstPos();
                } else if (i2 < (-PagerConfig.getFlingThreshold())) {
                    i3 = pagerGridLayoutManager.findPrePageFirstPos();
                }
                PagerConfig.Loge("findTargetSnapPosition, target = " + i3);
                return i3;
            }
        }
        i3 = -1;
        PagerConfig.Loge("findTargetSnapPosition, target = " + i3);
        return i3;
    }

    public boolean onFling(int i, int i2) {
        RecyclerView.LayoutManager layoutManager = this.mRecyclerView.getLayoutManager();
        if (layoutManager == null || this.mRecyclerView.getAdapter() == null) {
            return false;
        }
        int flingThreshold = PagerConfig.getFlingThreshold();
        PagerConfig.Loge("minFlingVelocity = " + flingThreshold);
        if ((Math.abs(i2) > flingThreshold || Math.abs(i) > flingThreshold) && snapFromFling(layoutManager, i, i2)) {
            return true;
        }
        return false;
    }

    private boolean snapFromFling(@NonNull RecyclerView.LayoutManager layoutManager, int i, int i2) {
        LinearSmoothScroller createSnapScroller;
        int findTargetSnapPosition;
        if (!(layoutManager instanceof RecyclerView.SmoothScroller.ScrollVectorProvider) || (createSnapScroller = createSnapScroller(layoutManager)) == null || (findTargetSnapPosition = findTargetSnapPosition(layoutManager, i, i2)) == -1) {
            return false;
        }
        createSnapScroller.setTargetPosition(findTargetSnapPosition);
        layoutManager.startSmoothScroll(createSnapScroller);
        return true;
    }

    /* access modifiers changed from: protected */
    public LinearSmoothScroller createSnapScroller(RecyclerView.LayoutManager layoutManager) {
        if (!(layoutManager instanceof RecyclerView.SmoothScroller.ScrollVectorProvider)) {
            return null;
        }
        return new PagerGridSmoothScroller(this.mRecyclerView);
    }

    public void setFlingThreshold(int i) {
        PagerConfig.setFlingThreshold(i);
    }
}

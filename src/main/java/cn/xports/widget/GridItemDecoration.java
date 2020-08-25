package cn.xports.widget;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import com.blankj.utilcode.util.ScreenUtils;

public class GridItemDecoration extends RecyclerView.ItemDecoration {
    private int spanCount = 0;

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        super.getItemOffsets(rect, view, recyclerView, state);
        int viewLayoutPosition = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
        this.spanCount = getSpanCount(recyclerView);
        recyclerView.getAdapter().getItemCount();
        int maxDividerWidth = getMaxDividerWidth(view) / (this.spanCount - 1);
        if (isFirstColumn(viewLayoutPosition, this.spanCount)) {
            rect.set(0, 0, 0, 0);
        } else {
            rect.set(0, 0, 0, 0);
        }
    }

    private boolean isFirstColumn(int i, int i2) {
        return i % i2 == 0;
    }

    private boolean isLastColumn(RecyclerView recyclerView, int i, int i2, int i3) {
        return (i + 1) % i2 == 0;
    }

    private int getSpanCount(RecyclerView recyclerView) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            return ((GridLayoutManager) layoutManager).getSpanCount();
        }
        if (layoutManager instanceof StaggeredGridLayoutManager) {
            return ((StaggeredGridLayoutManager) layoutManager).getSpanCount();
        }
        return -1;
    }

    private int getMaxDividerWidth(View view) {
        return ScreenUtils.getScreenWidth() - (view.getLayoutParams().width * this.spanCount);
    }
}

package cn.xports.widget.pagelayout;

import android.annotation.SuppressLint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.support.annotation.IntRange;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;

public class PagerGridLayoutManager extends RecyclerView.LayoutManager implements RecyclerView.SmoothScroller.ScrollVectorProvider {
    public static final int HORIZONTAL = 1;
    private static final String TAG = "PagerGridLayoutManager";
    public static final int VERTICAL = 0;
    private boolean mAllowContinuousScroll = true;
    private boolean mChangeSelectInScrolling = true;
    private int mColumns;
    private int mHeightUsed = 0;
    private SparseArray<Rect> mItemFrames = new SparseArray<>();
    private int mItemHeight = 0;
    private int mItemWidth = 0;
    private int mLastPageCount = -1;
    private int mLastPageIndex = -1;
    private int mMaxScrollX;
    private int mMaxScrollY;
    private int mOffsetX = 0;
    private int mOffsetY = 0;
    private int mOnePageSize;
    @OrientationType
    private int mOrientation;
    private PageListener mPageListener = null;
    private RecyclerView mRecyclerView;
    private int mRows;
    private int mScrollState = 0;
    private int mWidthUsed = 0;

    public @interface OrientationType {
    }

    public interface PageListener {
        void onPageSelect(int i);

        void onPageSizeChanged(int i);
    }

    public PagerGridLayoutManager(@IntRange(from = 1, to = 100) int i, @IntRange(from = 1, to = 100) int i2, @OrientationType int i3) {
        this.mOrientation = i3;
        this.mRows = i;
        this.mColumns = i2;
        this.mOnePageSize = this.mRows * this.mColumns;
    }

    public void onAttachedToWindow(RecyclerView recyclerView) {
        super.onAttachedToWindow(recyclerView);
        this.mRecyclerView = recyclerView;
    }

    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        PagerConfig.Logi("Item onLayoutChildren");
        PagerConfig.Logi("Item onLayoutChildren isPreLayout = " + state.isPreLayout());
        PagerConfig.Logi("Item onLayoutChildren isMeasuring = " + state.isMeasuring());
        PagerConfig.Loge("Item onLayoutChildren state = " + state);
        if (!state.isPreLayout() && state.didStructureChange()) {
            int i = 0;
            if (getItemCount() == 0) {
                removeAndRecycleAllViews(recycler);
                setPageCount(0);
                setPageIndex(0, false);
                return;
            }
            setPageCount(getTotalPageCount());
            setPageIndex(getPageIndexByOffset(), false);
            int itemCount = getItemCount() / this.mOnePageSize;
            if (getItemCount() % this.mOnePageSize != 0) {
                itemCount++;
            }
            if (canScrollHorizontally()) {
                this.mMaxScrollX = (itemCount - 1) * getUsableWidth();
                this.mMaxScrollY = 0;
                if (this.mOffsetX > this.mMaxScrollX) {
                    this.mOffsetX = this.mMaxScrollX;
                }
            } else {
                this.mMaxScrollX = 0;
                this.mMaxScrollY = (itemCount - 1) * getUsableHeight();
                if (this.mOffsetY > this.mMaxScrollY) {
                    this.mOffsetY = this.mMaxScrollY;
                }
            }
            PagerConfig.Logi("count = " + getItemCount());
            if (this.mItemWidth <= 0) {
                this.mItemWidth = getUsableWidth() / this.mColumns;
            }
            if (this.mItemHeight <= 0) {
                this.mItemHeight = getUsableHeight() / this.mRows;
            }
            this.mWidthUsed = getUsableWidth() - this.mItemWidth;
            this.mHeightUsed = getUsableHeight() - this.mItemHeight;
            for (int i2 = 0; i2 < this.mOnePageSize * 2; i2++) {
                getItemFrameByPosition(i2);
            }
            if (this.mOffsetX == 0 && this.mOffsetY == 0) {
                while (i < this.mOnePageSize && i < getItemCount()) {
                    View viewForPosition = recycler.getViewForPosition(i);
                    addView(viewForPosition);
                    measureChildWithMargins(viewForPosition, this.mWidthUsed, this.mHeightUsed);
                    i++;
                }
            }
            recycleAndFillItems(recycler, state, true);
        }
    }

    public void onLayoutCompleted(RecyclerView.State state) {
        super.onLayoutCompleted(state);
        if (!state.isPreLayout()) {
            setPageCount(getTotalPageCount());
            setPageIndex(getPageIndexByOffset(), false);
        }
    }

    @SuppressLint({"CheckResult"})
    private void recycleAndFillItems(RecyclerView.Recycler recycler, RecyclerView.State state, boolean z) {
        if (!state.isPreLayout()) {
            PagerConfig.Logi("mOffsetX = " + this.mOffsetX);
            PagerConfig.Logi("mOffsetY = " + this.mOffsetY);
            Rect rect = new Rect(this.mOffsetX - this.mItemWidth, this.mOffsetY - this.mItemHeight, getUsableWidth() + this.mOffsetX + this.mItemWidth, getUsableHeight() + this.mOffsetY + this.mItemHeight);
            rect.intersect(0, 0, this.mMaxScrollX + getUsableWidth(), this.mMaxScrollY + getUsableHeight());
            PagerConfig.Loge("displayRect = " + rect.toString());
            int pageIndexByOffset = getPageIndexByOffset() * this.mOnePageSize;
            PagerConfig.Logi("startPos = " + pageIndexByOffset);
            int i = pageIndexByOffset - (this.mOnePageSize * 2);
            if (i < 0) {
                i = 0;
            }
            int i2 = (this.mOnePageSize * 4) + i;
            if (i2 > getItemCount()) {
                i2 = getItemCount();
            }
            PagerConfig.Loge("startPos = " + i);
            PagerConfig.Loge("stopPos = " + i2);
            detachAndScrapAttachedViews(recycler);
            if (z) {
                while (i < i2) {
                    addOrRemove(recycler, rect, i);
                    i++;
                }
            } else {
                for (int i3 = i2 - 1; i3 >= i; i3--) {
                    addOrRemove(recycler, rect, i3);
                }
            }
            PagerConfig.Loge("child count = " + getChildCount());
        }
    }

    private void addOrRemove(RecyclerView.Recycler recycler, Rect rect, int i) {
        View viewForPosition = recycler.getViewForPosition(i);
        Rect itemFrameByPosition = getItemFrameByPosition(i);
        if (!Rect.intersects(rect, itemFrameByPosition)) {
            removeAndRecycleView(viewForPosition, recycler);
            return;
        }
        addView(viewForPosition);
        measureChildWithMargins(viewForPosition, this.mWidthUsed, this.mHeightUsed);
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) viewForPosition.getLayoutParams();
        layoutDecorated(viewForPosition, (itemFrameByPosition.left - this.mOffsetX) + layoutParams.leftMargin + getPaddingLeft(), (itemFrameByPosition.top - this.mOffsetY) + layoutParams.topMargin + getPaddingTop(), ((itemFrameByPosition.right - this.mOffsetX) - layoutParams.rightMargin) + getPaddingLeft(), ((itemFrameByPosition.bottom - this.mOffsetY) - layoutParams.bottomMargin) + getPaddingTop());
    }

    public int scrollHorizontallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int i2 = this.mOffsetX + i;
        if (i2 > this.mMaxScrollX) {
            i = this.mMaxScrollX - this.mOffsetX;
        } else if (i2 < 0) {
            i = 0 - this.mOffsetX;
        }
        this.mOffsetX += i;
        setPageIndex(getPageIndexByOffset(), true);
        offsetChildrenHorizontal(-i);
        if (i > 0) {
            recycleAndFillItems(recycler, state, true);
        } else {
            recycleAndFillItems(recycler, state, false);
        }
        return i;
    }

    public int scrollVerticallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int i2 = this.mOffsetY + i;
        if (i2 > this.mMaxScrollY) {
            i = this.mMaxScrollY - this.mOffsetY;
        } else if (i2 < 0) {
            i = 0 - this.mOffsetY;
        }
        this.mOffsetY += i;
        setPageIndex(getPageIndexByOffset(), true);
        offsetChildrenVertical(-i);
        if (i > 0) {
            recycleAndFillItems(recycler, state, true);
        } else {
            recycleAndFillItems(recycler, state, false);
        }
        return i;
    }

    public void onScrollStateChanged(int i) {
        PagerConfig.Logi("onScrollStateChanged = " + i);
        this.mScrollState = i;
        super.onScrollStateChanged(i);
        if (i == 0) {
            setPageIndex(getPageIndexByOffset(), false);
        }
    }

    private Rect getItemFrameByPosition(int i) {
        int i2;
        Rect rect = this.mItemFrames.get(i);
        if (rect == null) {
            rect = new Rect();
            int i3 = i / this.mOnePageSize;
            int i4 = 0;
            if (canScrollHorizontally()) {
                i2 = (getUsableWidth() * i3) + 0;
            } else {
                i4 = (getUsableHeight() * i3) + 0;
                i2 = 0;
            }
            int i5 = i % this.mOnePageSize;
            int i6 = i5 / this.mColumns;
            int i7 = i5 - (this.mColumns * i6);
            int i8 = i2 + (this.mItemWidth * i7);
            int i9 = i4 + (this.mItemHeight * i6);
            PagerConfig.Logi("pagePos = " + i5);
            PagerConfig.Logi("行 = " + i6);
            PagerConfig.Logi("列 = " + i7);
            PagerConfig.Logi("offsetX = " + i8);
            PagerConfig.Logi("offsetY = " + i9);
            rect.left = i8;
            rect.top = i9;
            rect.right = i8 + this.mItemWidth;
            rect.bottom = i9 + this.mItemHeight;
            this.mItemFrames.put(i, rect);
        }
        return rect;
    }

    private int getUsableWidth() {
        return (getWidth() - getPaddingLeft()) - getPaddingRight();
    }

    private int getUsableHeight() {
        return (getHeight() - getPaddingTop()) - getPaddingBottom();
    }

    public int getTotalPageCount() {
        if (getItemCount() <= 0) {
            return 0;
        }
        int itemCount = getItemCount() / this.mOnePageSize;
        return getItemCount() % this.mOnePageSize != 0 ? itemCount + 1 : itemCount;
    }

    private int getPageIndexByPos(int i) {
        return i / this.mOnePageSize;
    }

    private int getPageIndexByOffset() {
        int i = 0;
        if (canScrollVertically()) {
            int usableHeight = getUsableHeight();
            if (this.mOffsetY > 0 && usableHeight > 0) {
                i = this.mOffsetY / usableHeight;
                if (this.mOffsetY % usableHeight > usableHeight / 2) {
                    i++;
                }
            }
        } else {
            int usableWidth = getUsableWidth();
            if (this.mOffsetX > 0 && usableWidth > 0) {
                i = this.mOffsetX / usableWidth;
                if (this.mOffsetX % usableWidth > usableWidth / 2) {
                    i++;
                }
            }
        }
        PagerConfig.Logi("getPageIndexByOffset pageIndex = " + i);
        return i;
    }

    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(-2, -2);
    }

    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int i, int i2) {
        super.onMeasure(recycler, state, i, i2);
        int size = View.MeasureSpec.getSize(i);
        int mode = View.MeasureSpec.getMode(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int mode2 = View.MeasureSpec.getMode(i2);
        if (mode != 1073741824 && size > 0) {
            mode = 1073741824;
        }
        if (mode2 != 1073741824 && size2 > 0) {
            mode2 = 1073741824;
        }
        setMeasuredDimension(View.MeasureSpec.makeMeasureSpec(size, mode), View.MeasureSpec.makeMeasureSpec(size2, mode2));
    }

    public boolean canScrollHorizontally() {
        return this.mOrientation == 1;
    }

    public boolean canScrollVertically() {
        return this.mOrientation == 0;
    }

    /* access modifiers changed from: package-private */
    public int findNextPageFirstPos() {
        int i = this.mLastPageIndex + 1;
        if (i >= getTotalPageCount()) {
            i = getTotalPageCount() - 1;
        }
        PagerConfig.Loge("computeScrollVectorForPosition next = " + i);
        return i * this.mOnePageSize;
    }

    /* access modifiers changed from: package-private */
    public int findPrePageFirstPos() {
        int i = this.mLastPageIndex - 1;
        PagerConfig.Loge("computeScrollVectorForPosition pre = " + i);
        if (i < 0) {
            i = 0;
        }
        PagerConfig.Loge("computeScrollVectorForPosition pre = " + i);
        return i * this.mOnePageSize;
    }

    public int getOffsetX() {
        return this.mOffsetX;
    }

    public int getOffsetY() {
        return this.mOffsetY;
    }

    public PointF computeScrollVectorForPosition(int i) {
        PointF pointF = new PointF();
        int[] snapOffset = getSnapOffset(i);
        pointF.x = (float) snapOffset[0];
        pointF.y = (float) snapOffset[1];
        return pointF;
    }

    /* access modifiers changed from: package-private */
    public int[] getSnapOffset(int i) {
        int[] pageLeftTopByPosition = getPageLeftTopByPosition(i);
        return new int[]{pageLeftTopByPosition[0] - this.mOffsetX, pageLeftTopByPosition[1] - this.mOffsetY};
    }

    private int[] getPageLeftTopByPosition(int i) {
        int[] iArr = new int[2];
        int pageIndexByPos = getPageIndexByPos(i);
        if (canScrollHorizontally()) {
            iArr[0] = pageIndexByPos * getUsableWidth();
            iArr[1] = 0;
        } else {
            iArr[0] = 0;
            iArr[1] = pageIndexByPos * getUsableHeight();
        }
        return iArr;
    }

    public View findSnapView() {
        if (getFocusedChild() != null) {
            return getFocusedChild();
        }
        if (getChildCount() <= 0) {
            return null;
        }
        int pageIndexByOffset = getPageIndexByOffset() * this.mOnePageSize;
        for (int i = 0; i < getChildCount(); i++) {
            if (getPosition(getChildAt(i)) == pageIndexByOffset) {
                return getChildAt(i);
            }
        }
        return getChildAt(0);
    }

    private void setPageCount(int i) {
        if (i >= 0) {
            if (!(this.mPageListener == null || i == this.mLastPageCount)) {
                this.mPageListener.onPageSizeChanged(i);
            }
            this.mLastPageCount = i;
        }
    }

    private void setPageIndex(int i, boolean z) {
        PagerConfig.Loge("setPageIndex = " + i + ":" + z);
        if (i != this.mLastPageIndex) {
            if (isAllowContinuousScroll()) {
                this.mLastPageIndex = i;
            } else if (!z) {
                this.mLastPageIndex = i;
            }
            if ((!z || this.mChangeSelectInScrolling) && i >= 0 && this.mPageListener != null) {
                this.mPageListener.onPageSelect(i);
            }
        }
    }

    public void setChangeSelectInScrolling(boolean z) {
        this.mChangeSelectInScrolling = z;
    }

    @OrientationType
    public int setOrientationType(@OrientationType int i) {
        if (this.mOrientation == i || this.mScrollState != 0) {
            return this.mOrientation;
        }
        this.mOrientation = i;
        this.mItemFrames.clear();
        int i2 = this.mOffsetX;
        this.mOffsetX = (this.mOffsetY / getUsableHeight()) * getUsableWidth();
        this.mOffsetY = (i2 / getUsableWidth()) * getUsableHeight();
        int i3 = this.mMaxScrollX;
        this.mMaxScrollX = (this.mMaxScrollY / getUsableHeight()) * getUsableWidth();
        this.mMaxScrollY = (i3 / getUsableWidth()) * getUsableHeight();
        return this.mOrientation;
    }

    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int i) {
        smoothScrollToPage(getPageIndexByPos(i));
    }

    public void smoothPrePage() {
        smoothScrollToPage(getPageIndexByOffset() - 1);
    }

    public void smoothNextPage() {
        smoothScrollToPage(getPageIndexByOffset() + 1);
    }

    public void smoothNextPageWithRevert() {
        int pageIndexByOffset = getPageIndexByOffset() + 1;
        if (pageIndexByOffset >= this.mLastPageCount) {
            smoothScrollToPage(0);
        } else {
            smoothScrollToPage(pageIndexByOffset);
        }
    }

    public void smoothScrollToPage(int i) {
        if (i < 0 || i >= this.mLastPageCount) {
            String str = TAG;
            Log.e(str, "pageIndex is outOfIndex, must in [0, " + this.mLastPageCount + ").");
        } else if (this.mRecyclerView == null) {
            Log.e(TAG, "RecyclerView Not Found!");
        } else {
            int pageIndexByOffset = getPageIndexByOffset();
            if (Math.abs(i - pageIndexByOffset) > 3) {
                if (i > pageIndexByOffset) {
                    scrollToPage(i - 3);
                } else if (i < pageIndexByOffset) {
                    scrollToPage(i + 3);
                }
            }
            PagerGridSmoothScroller pagerGridSmoothScroller = new PagerGridSmoothScroller(this.mRecyclerView);
            pagerGridSmoothScroller.setTargetPosition(i * this.mOnePageSize);
            startSmoothScroll(pagerGridSmoothScroller);
        }
    }

    public void scrollToPosition(int i) {
        scrollToPage(getPageIndexByPos(i));
    }

    public void prePage() {
        scrollToPage(getPageIndexByOffset() - 1);
    }

    public void nextPage() {
        scrollToPage(getPageIndexByOffset() + 1);
    }

    public void nextPageWithRevert() {
        if (getPageIndexByOffset() + 1 == this.mLastPageCount) {
            scrollToPage(0);
        } else {
            scrollToPage(getPageIndexByOffset() + 1);
        }
    }

    public void scrollToPage(int i) {
        int i2;
        int i3;
        if (i < 0 || i >= this.mLastPageCount) {
            String str = TAG;
            Log.e(str, "pageIndex = " + i + " is out of bounds, mast in [0, " + this.mLastPageCount + ")");
        } else if (this.mRecyclerView == null) {
            Log.e(TAG, "RecyclerView Not Found!");
        } else {
            if (canScrollVertically()) {
                i2 = (getUsableHeight() * i) - this.mOffsetY;
                i3 = 0;
            } else {
                i3 = (getUsableWidth() * i) - this.mOffsetX;
                i2 = 0;
            }
            PagerConfig.Loge("mTargetOffsetXBy = " + i3);
            PagerConfig.Loge("mTargetOffsetYBy = " + i2);
            this.mRecyclerView.scrollBy(i3, i2);
            setPageIndex(i, false);
        }
    }

    public boolean isAllowContinuousScroll() {
        return this.mAllowContinuousScroll;
    }

    public void setAllowContinuousScroll(boolean z) {
        this.mAllowContinuousScroll = z;
    }

    public void setPageListener(PageListener pageListener) {
        this.mPageListener = pageListener;
    }
}

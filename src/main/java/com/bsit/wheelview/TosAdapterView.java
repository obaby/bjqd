package com.bsit.wheelview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.DataSetObserver;
import android.os.Handler;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.ContextMenu;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Adapter;

public abstract class TosAdapterView<T extends Adapter> extends ViewGroup {
    public static final int INVALID_POSITION = -1;
    public static final long INVALID_ROW_ID = Long.MIN_VALUE;
    public static final int ITEM_VIEW_TYPE_HEADER_OR_FOOTER = -2;
    public static final int ITEM_VIEW_TYPE_IGNORE = -1;
    static final int SYNC_FIRST_POSITION = 1;
    static final int SYNC_MAX_DURATION_MILLIS = 100;
    static final int SYNC_SELECTED_POSITION = 0;
    boolean mBlockLayoutRequests = false;
    boolean mDataChanged;
    private boolean mDesiredFocusableInTouchModeState;
    private boolean mDesiredFocusableState;
    private View mEmptyView;
    @ViewDebug.ExportedProperty
    int mFirstPosition = 0;
    boolean mInLayout = false;
    @ViewDebug.ExportedProperty
    int mItemCount;
    private int mLayoutHeight;
    boolean mNeedSync = false;
    @ViewDebug.ExportedProperty
    int mNextSelectedPosition = -1;
    long mNextSelectedRowId = Long.MIN_VALUE;
    int mOldItemCount;
    int mOldSelectedPosition = -1;
    long mOldSelectedRowId = Long.MIN_VALUE;
    OnItemClickListener mOnItemClickListener;
    OnItemDoubleClickListener mOnItemDoubleClickListener;
    OnItemLongClickListener mOnItemLongClickListener;
    OnItemSelectedListener mOnItemSelectedListener;
    @ViewDebug.ExportedProperty
    int mSelectedPosition = -1;
    long mSelectedRowId = Long.MIN_VALUE;
    private TosAdapterView<T>.SelectionNotifier mSelectionNotifier;
    int mSpecificTop;
    long mSyncHeight;
    int mSyncMode;
    int mSyncPosition;
    long mSyncRowId = Long.MIN_VALUE;

    public interface OnItemClickListener {
        void onItemClick(TosAdapterView<?> tosAdapterView, View view, int i, long j);
    }

    public interface OnItemDoubleClickListener {
        boolean onItemDoubleClick(TosAdapterView<?> tosAdapterView, View view, int i, long j);
    }

    public interface OnItemLongClickListener {
        boolean onItemLongClick(TosAdapterView<?> tosAdapterView, View view, int i, long j);
    }

    public interface OnItemSelectedListener {
        void onItemSelected(TosAdapterView<?> tosAdapterView, View view, int i, long j);

        void onNothingSelected(TosAdapterView<?> tosAdapterView);
    }

    public abstract T getAdapter();

    public abstract View getSelectedView();

    /* access modifiers changed from: package-private */
    public boolean isInFilterMode() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public int lookForSelectablePosition(int i, boolean z) {
        return i;
    }

    public abstract void setAdapter(T t);

    public abstract void setSelection(int i);

    public TosAdapterView(Context context) {
        super(context);
    }

    public TosAdapterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public TosAdapterView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public final OnItemClickListener getOnItemClickListener() {
        return this.mOnItemClickListener;
    }

    public boolean performItemClick(View view, int i, long j) {
        if (this.mOnItemClickListener == null) {
            return false;
        }
        playSoundEffect(0);
        this.mOnItemClickListener.onItemClick(this, view, i, j);
        return true;
    }

    public void setOnItemDoubleClickListener(OnItemDoubleClickListener onItemDoubleClickListener) {
        this.mOnItemDoubleClickListener = onItemDoubleClickListener;
    }

    public boolean performItemDoubleClick(View view, int i, long j) {
        if (this.mOnItemDoubleClickListener == null) {
            return false;
        }
        this.mOnItemDoubleClickListener.onItemDoubleClick(this, view, i, j);
        return true;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        if (!isLongClickable()) {
            setLongClickable(true);
        }
        this.mOnItemLongClickListener = onItemLongClickListener;
    }

    public final OnItemLongClickListener getOnItemLongClickListener() {
        return this.mOnItemLongClickListener;
    }

    public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener) {
        this.mOnItemSelectedListener = onItemSelectedListener;
    }

    public final OnItemSelectedListener getOnItemSelectedListener() {
        return this.mOnItemSelectedListener;
    }

    public static class AdapterContextMenuInfo implements ContextMenu.ContextMenuInfo {
        public long id;
        public int position;
        public View targetView;

        public AdapterContextMenuInfo(View view, int i, long j) {
            this.targetView = view;
            this.position = i;
            this.id = j;
        }
    }

    public void addView(View view) {
        throw new UnsupportedOperationException("addView(View) is not supported in AdapterView");
    }

    public void addView(View view, int i) {
        throw new UnsupportedOperationException("addView(View, int) is not supported in AdapterView");
    }

    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
        throw new UnsupportedOperationException("addView(View, LayoutParams) is not supported in AdapterView");
    }

    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        throw new UnsupportedOperationException("addView(View, int, LayoutParams) is not supported in AdapterView");
    }

    public void removeView(View view) {
        throw new UnsupportedOperationException("removeView(View) is not supported in AdapterView");
    }

    public void removeViewAt(int i) {
        throw new UnsupportedOperationException("removeViewAt(int) is not supported in AdapterView");
    }

    public void removeAllViews() {
        throw new UnsupportedOperationException("removeAllViews() is not supported in AdapterView");
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        this.mLayoutHeight = getHeight();
    }

    @ViewDebug.CapturedViewProperty
    public int getSelectedItemPosition() {
        return this.mNextSelectedPosition;
    }

    @ViewDebug.CapturedViewProperty
    public long getSelectedItemId() {
        return this.mNextSelectedRowId;
    }

    public Object getSelectedItem() {
        Adapter adapter = getAdapter();
        int selectedItemPosition = getSelectedItemPosition();
        if (adapter == null || adapter.getCount() <= 0 || selectedItemPosition < 0) {
            return null;
        }
        return adapter.getItem(selectedItemPosition);
    }

    @ViewDebug.CapturedViewProperty
    public int getCount() {
        return this.mItemCount;
    }

    public int getPositionForView(View view) {
        while (true) {
            try {
                View view2 = (View) view.getParent();
                if (view2.equals(this)) {
                    break;
                }
                view = view2;
            } catch (ClassCastException unused) {
                return -1;
            }
        }
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (getChildAt(i).equals(view)) {
                return this.mFirstPosition + i;
            }
        }
        return -1;
    }

    public int getFirstVisiblePosition() {
        return this.mFirstPosition;
    }

    public int getLastVisiblePosition() {
        return (this.mFirstPosition + getChildCount()) - 1;
    }

    public void setEmptyView(View view) {
        this.mEmptyView = view;
        Adapter adapter = getAdapter();
        updateEmptyStatus(adapter == null || adapter.isEmpty());
    }

    public View getEmptyView() {
        return this.mEmptyView;
    }

    public void setFocusable(boolean z) {
        Adapter adapter = getAdapter();
        boolean z2 = true;
        boolean z3 = adapter == null || adapter.getCount() == 0;
        this.mDesiredFocusableState = z;
        if (!z) {
            this.mDesiredFocusableInTouchModeState = false;
        }
        if (!z || (z3 && !isInFilterMode())) {
            z2 = false;
        }
        super.setFocusable(z2);
    }

    public void setFocusableInTouchMode(boolean z) {
        Adapter adapter = getAdapter();
        boolean z2 = false;
        boolean z3 = adapter == null || adapter.getCount() == 0;
        this.mDesiredFocusableInTouchModeState = z;
        if (z) {
            this.mDesiredFocusableState = true;
        }
        if (z && (!z3 || isInFilterMode())) {
            z2 = true;
        }
        super.setFocusableInTouchMode(z2);
    }

    /* access modifiers changed from: package-private */
    public void checkFocus() {
        Adapter adapter = getAdapter();
        boolean z = false;
        boolean z2 = !(adapter == null || adapter.getCount() == 0) || isInFilterMode();
        super.setFocusableInTouchMode(z2 && this.mDesiredFocusableInTouchModeState);
        super.setFocusable(z2 && this.mDesiredFocusableState);
        if (this.mEmptyView != null) {
            if (adapter == null || adapter.isEmpty()) {
                z = true;
            }
            updateEmptyStatus(z);
        }
    }

    @SuppressLint({"WrongCall"})
    private void updateEmptyStatus(boolean z) {
        if (isInFilterMode()) {
            z = false;
        }
        if (z) {
            if (this.mEmptyView != null) {
                this.mEmptyView.setVisibility(0);
                setVisibility(8);
            } else {
                setVisibility(0);
            }
            if (this.mDataChanged) {
                onLayout(false, getLeft(), getTop(), getRight(), getBottom());
                return;
            }
            return;
        }
        if (this.mEmptyView != null) {
            this.mEmptyView.setVisibility(8);
        }
        setVisibility(0);
    }

    public Object getItemAtPosition(int i) {
        Adapter adapter = getAdapter();
        if (adapter == null || i < 0) {
            return null;
        }
        return adapter.getItem(i);
    }

    public long getItemIdAtPosition(int i) {
        Adapter adapter = getAdapter();
        if (adapter == null || i < 0) {
            return Long.MIN_VALUE;
        }
        return adapter.getItemId(i);
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        throw new RuntimeException("Don't call setOnClickListener for an AdapterView. You probably want setOnItemClickListener instead");
    }

    /* access modifiers changed from: protected */
    public void dispatchSaveInstanceState(SparseArray<Parcelable> sparseArray) {
        dispatchFreezeSelfOnly(sparseArray);
    }

    /* access modifiers changed from: protected */
    public void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
        dispatchThawSelfOnly(sparseArray);
    }

    class AdapterDataSetObserver extends DataSetObserver {
        private Parcelable mInstanceState = null;

        AdapterDataSetObserver() {
        }

        public void onChanged() {
            TosAdapterView.this.mDataChanged = true;
            TosAdapterView.this.mOldItemCount = TosAdapterView.this.mItemCount;
            TosAdapterView.this.mItemCount = TosAdapterView.this.getAdapter().getCount();
            if (!TosAdapterView.this.getAdapter().hasStableIds() || this.mInstanceState == null || TosAdapterView.this.mOldItemCount != 0 || TosAdapterView.this.mItemCount <= 0) {
                TosAdapterView.this.rememberSyncState();
            } else {
                TosAdapterView.this.onRestoreInstanceState(this.mInstanceState);
                this.mInstanceState = null;
            }
            TosAdapterView.this.checkFocus();
            TosAdapterView.this.requestLayout();
        }

        public void onInvalidated() {
            TosAdapterView.this.mDataChanged = true;
            if (TosAdapterView.this.getAdapter().hasStableIds()) {
                this.mInstanceState = TosAdapterView.this.onSaveInstanceState();
            }
            TosAdapterView.this.mOldItemCount = TosAdapterView.this.mItemCount;
            TosAdapterView.this.mItemCount = 0;
            TosAdapterView.this.mSelectedPosition = -1;
            TosAdapterView.this.mSelectedRowId = Long.MIN_VALUE;
            TosAdapterView.this.mNextSelectedPosition = -1;
            TosAdapterView.this.mNextSelectedRowId = Long.MIN_VALUE;
            TosAdapterView.this.mNeedSync = false;
            TosAdapterView.this.checkSelectionChanged();
            TosAdapterView.this.checkFocus();
            TosAdapterView.this.requestLayout();
        }

        public void clearSavedState() {
            this.mInstanceState = null;
        }
    }

    private class SelectionNotifier extends Handler implements Runnable {
        private SelectionNotifier() {
        }

        public void run() {
            if (TosAdapterView.this.mDataChanged) {
                post(this);
            } else {
                TosAdapterView.this.fireOnSelected();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void selectionChanged() {
        if (this.mOnItemSelectedListener != null) {
            if (this.mInLayout || this.mBlockLayoutRequests) {
                if (this.mSelectionNotifier == null) {
                    this.mSelectionNotifier = new SelectionNotifier();
                }
                this.mSelectionNotifier.post(this.mSelectionNotifier);
            } else {
                fireOnSelected();
            }
        }
        if (this.mSelectedPosition != -1 && isShown() && !isInTouchMode()) {
            sendAccessibilityEvent(4);
        }
    }

    /* access modifiers changed from: private */
    public void fireOnSelected() {
        if (this.mOnItemSelectedListener != null) {
            int selectedItemPosition = getSelectedItemPosition();
            if (selectedItemPosition >= 0) {
                this.mOnItemSelectedListener.onItemSelected(this, getSelectedView(), selectedItemPosition, getAdapter().getItemId(selectedItemPosition));
                return;
            }
            this.mOnItemSelectedListener.onNothingSelected(this);
        }
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        if (accessibilityEvent.getEventType() == 8) {
            accessibilityEvent.setEventType(4);
        }
        View selectedView = getSelectedView();
        boolean dispatchPopulateAccessibilityEvent = selectedView != null ? selectedView.dispatchPopulateAccessibilityEvent(accessibilityEvent) : false;
        if (!dispatchPopulateAccessibilityEvent) {
            if (selectedView != null) {
                accessibilityEvent.setEnabled(selectedView.isEnabled());
            }
            accessibilityEvent.setItemCount(getCount());
            accessibilityEvent.setCurrentItemIndex(getSelectedItemPosition());
        }
        return dispatchPopulateAccessibilityEvent;
    }

    /* access modifiers changed from: protected */
    public boolean canAnimate() {
        return super.canAnimate() && this.mItemCount > 0;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0020  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void handleDataChanged() {
        /*
            r5 = this;
            int r0 = r5.mItemCount
            r1 = 1
            r2 = 0
            if (r0 <= 0) goto L_0x0041
            boolean r3 = r5.mNeedSync
            if (r3 == 0) goto L_0x001d
            r5.mNeedSync = r2
            int r3 = r5.findSyncPosition()
            if (r3 < 0) goto L_0x001d
            int r4 = r5.lookForSelectablePosition(r3, r1)
            if (r4 != r3) goto L_0x001d
            r5.setNextSelectedPositionInt(r3)
            r3 = 1
            goto L_0x001e
        L_0x001d:
            r3 = 0
        L_0x001e:
            if (r3 != 0) goto L_0x003f
            int r4 = r5.getSelectedItemPosition()
            if (r4 < r0) goto L_0x0028
            int r0 = r0 - r1
            goto L_0x0029
        L_0x0028:
            r0 = r4
        L_0x0029:
            if (r0 >= 0) goto L_0x002c
            r0 = 0
        L_0x002c:
            int r4 = r5.lookForSelectablePosition(r0, r1)
            if (r4 >= 0) goto L_0x0036
            int r4 = r5.lookForSelectablePosition(r0, r2)
        L_0x0036:
            if (r4 < 0) goto L_0x003f
            r5.setNextSelectedPositionInt(r4)
            r5.checkSelectionChanged()
            goto L_0x0042
        L_0x003f:
            r1 = r3
            goto L_0x0042
        L_0x0041:
            r1 = 0
        L_0x0042:
            if (r1 != 0) goto L_0x0054
            r0 = -1
            r5.mSelectedPosition = r0
            r3 = -9223372036854775808
            r5.mSelectedRowId = r3
            r5.mNextSelectedPosition = r0
            r5.mNextSelectedRowId = r3
            r5.mNeedSync = r2
            r5.checkSelectionChanged()
        L_0x0054:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bsit.wheelview.TosAdapterView.handleDataChanged():void");
    }

    /* access modifiers changed from: package-private */
    public void checkSelectionChanged() {
        if (this.mSelectedPosition != this.mOldSelectedPosition || this.mSelectedRowId != this.mOldSelectedRowId) {
            selectionChanged();
            this.mOldSelectedPosition = this.mSelectedPosition;
            this.mOldSelectedRowId = this.mSelectedRowId;
        }
    }

    /* access modifiers changed from: package-private */
    public int findSyncPosition() {
        int i = this.mItemCount;
        if (i == 0) {
            return -1;
        }
        long j = this.mSyncRowId;
        int i2 = this.mSyncPosition;
        if (j == Long.MIN_VALUE) {
            return -1;
        }
        int i3 = i - 1;
        int min = Math.min(i3, Math.max(0, i2));
        long uptimeMillis = SystemClock.uptimeMillis() + 100;
        Adapter adapter = getAdapter();
        if (adapter == null) {
            return -1;
        }
        int i4 = min;
        int i5 = i4;
        loop0:
        while (true) {
            boolean z = false;
            while (SystemClock.uptimeMillis() <= uptimeMillis) {
                if (adapter.getItemId(min) != j) {
                    boolean z2 = i4 == i3;
                    boolean z3 = i5 == 0;
                    if (z2 && z3) {
                        break loop0;
                    } else if (z3 || (z && !z2)) {
                        i4++;
                        min = i4;
                    } else {
                        i5--;
                        min = i5;
                        z = true;
                    }
                } else {
                    return min;
                }
            }
            break loop0;
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    public void setSelectedPositionInt(int i) {
        this.mSelectedPosition = i;
        this.mSelectedRowId = getItemIdAtPosition(i);
    }

    /* access modifiers changed from: package-private */
    public void setNextSelectedPositionInt(int i) {
        this.mNextSelectedPosition = i;
        this.mNextSelectedRowId = getItemIdAtPosition(i);
        if (this.mNeedSync && this.mSyncMode == 0 && i >= 0) {
            this.mSyncPosition = i;
            this.mSyncRowId = this.mNextSelectedRowId;
        }
    }

    /* access modifiers changed from: package-private */
    public void rememberSyncState() {
        if (getChildCount() > 0) {
            this.mNeedSync = true;
            this.mSyncHeight = (long) this.mLayoutHeight;
            if (this.mSelectedPosition >= 0) {
                View childAt = getChildAt(this.mSelectedPosition - this.mFirstPosition);
                this.mSyncRowId = this.mNextSelectedRowId;
                this.mSyncPosition = this.mNextSelectedPosition;
                if (childAt != null) {
                    this.mSpecificTop = childAt.getTop();
                }
                this.mSyncMode = 0;
                return;
            }
            View childAt2 = getChildAt(0);
            Adapter adapter = getAdapter();
            if (this.mFirstPosition < 0 || this.mFirstPosition >= adapter.getCount()) {
                this.mSyncRowId = -1;
            } else {
                this.mSyncRowId = adapter.getItemId(this.mFirstPosition);
            }
            this.mSyncPosition = this.mFirstPosition;
            if (childAt2 != null) {
                this.mSpecificTop = childAt2.getTop();
            }
            this.mSyncMode = 1;
        }
    }
}

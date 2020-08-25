package com.bsit.wheelview;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import com.alipay.sdk.util.h;
import com.bsit.wheelview.TosAdapterView;

public abstract class TosAbsSpinner extends TosAdapterView<SpinnerAdapter> {
    SpinnerAdapter mAdapter;
    boolean mBlockLayoutRequests;
    private DataSetObserver mDataSetObserver;
    int mHeightMeasureSpec;
    Interpolator mInterpolator;
    RecycleBin mRecycler;
    View mSelectedView;
    int mSelectionBottomPadding;
    int mSelectionLeftPadding;
    int mSelectionRightPadding;
    int mSelectionTopPadding;
    Rect mSpinnerPadding;
    private Rect mTouchFrame;
    int mWidthMeasureSpec;

    /* access modifiers changed from: package-private */
    public abstract void layout(int i, boolean z);

    /* access modifiers changed from: protected */
    public DataSetObserver getDataSetObserver() {
        return this.mDataSetObserver;
    }

    public TosAbsSpinner(Context context) {
        super(context);
        this.mSelectionLeftPadding = 0;
        this.mSelectionTopPadding = 0;
        this.mSelectionRightPadding = 0;
        this.mSelectionBottomPadding = 0;
        this.mSpinnerPadding = new Rect();
        this.mSelectedView = null;
        this.mRecycler = new RecycleBin();
        initAbsSpinner();
    }

    public TosAbsSpinner(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TosAbsSpinner(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mSelectionLeftPadding = 0;
        this.mSelectionTopPadding = 0;
        this.mSelectionRightPadding = 0;
        this.mSelectionBottomPadding = 0;
        this.mSpinnerPadding = new Rect();
        this.mSelectedView = null;
        this.mRecycler = new RecycleBin();
        initAbsSpinner();
        ArrayAdapter arrayAdapter = new ArrayAdapter(context, 17367048);
        arrayAdapter.setDropDownViewResource(17367049);
        setAdapter((SpinnerAdapter) arrayAdapter);
    }

    private void initAbsSpinner() {
        setFocusable(true);
        setWillNotDraw(false);
    }

    public void setAdapter(SpinnerAdapter spinnerAdapter) {
        if (this.mAdapter != null) {
            this.mAdapter.unregisterDataSetObserver(this.mDataSetObserver);
            resetList();
        }
        this.mAdapter = spinnerAdapter;
        int i = -1;
        this.mOldSelectedPosition = -1;
        this.mOldSelectedRowId = Long.MIN_VALUE;
        if (this.mAdapter != null) {
            this.mOldItemCount = this.mItemCount;
            this.mItemCount = this.mAdapter.getCount();
            checkFocus();
            this.mDataSetObserver = new TosAdapterView.AdapterDataSetObserver();
            this.mAdapter.registerDataSetObserver(this.mDataSetObserver);
            if (this.mItemCount > 0) {
                i = 0;
            }
            setSelectedPositionInt(i);
            setNextSelectedPositionInt(i);
            if (this.mItemCount == 0) {
                checkSelectionChanged();
            }
        } else {
            checkFocus();
            resetList();
            checkSelectionChanged();
        }
        requestLayout();
    }

    /* access modifiers changed from: package-private */
    public void resetList() {
        this.mDataChanged = false;
        this.mNeedSync = false;
        removeAllViewsInLayout();
        this.mOldSelectedPosition = -1;
        this.mOldSelectedRowId = Long.MIN_VALUE;
        setSelectedPositionInt(-1);
        setNextSelectedPositionInt(-1);
        invalidate();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00b1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onMeasure(int r7, int r8) {
        /*
            r6 = this;
            int r0 = android.view.View.MeasureSpec.getMode(r7)
            android.graphics.Rect r1 = r6.mSpinnerPadding
            int r2 = r6.getPaddingLeft()
            int r3 = r6.mSelectionLeftPadding
            if (r2 <= r3) goto L_0x0013
            int r2 = r6.getPaddingLeft()
            goto L_0x0015
        L_0x0013:
            int r2 = r6.mSelectionLeftPadding
        L_0x0015:
            r1.left = r2
            android.graphics.Rect r1 = r6.mSpinnerPadding
            int r2 = r6.getPaddingTop()
            int r3 = r6.mSelectionTopPadding
            if (r2 <= r3) goto L_0x0026
            int r2 = r6.getPaddingTop()
            goto L_0x0028
        L_0x0026:
            int r2 = r6.mSelectionTopPadding
        L_0x0028:
            r1.top = r2
            android.graphics.Rect r1 = r6.mSpinnerPadding
            int r2 = r6.getPaddingRight()
            int r3 = r6.mSelectionRightPadding
            if (r2 <= r3) goto L_0x0039
            int r2 = r6.getPaddingRight()
            goto L_0x003b
        L_0x0039:
            int r2 = r6.mSelectionRightPadding
        L_0x003b:
            r1.right = r2
            android.graphics.Rect r1 = r6.mSpinnerPadding
            int r2 = r6.getPaddingBottom()
            int r3 = r6.mSelectionBottomPadding
            if (r2 <= r3) goto L_0x004c
            int r2 = r6.getPaddingBottom()
            goto L_0x004e
        L_0x004c:
            int r2 = r6.mSelectionBottomPadding
        L_0x004e:
            r1.bottom = r2
            boolean r1 = r6.mDataChanged
            if (r1 == 0) goto L_0x0057
            r6.handleDataChanged()
        L_0x0057:
            int r1 = r6.getSelectedItemPosition()
            r2 = 1
            r3 = 0
            if (r1 < 0) goto L_0x00ae
            android.widget.SpinnerAdapter r4 = r6.mAdapter
            if (r4 == 0) goto L_0x00ae
            com.bsit.wheelview.TosAbsSpinner$RecycleBin r4 = r6.mRecycler
            android.view.View r4 = r4.get(r1)
            if (r4 != 0) goto L_0x0072
            android.widget.SpinnerAdapter r4 = r6.mAdapter
            r5 = 0
            android.view.View r4 = r4.getView(r1, r5, r6)
        L_0x0072:
            if (r4 == 0) goto L_0x0079
            com.bsit.wheelview.TosAbsSpinner$RecycleBin r5 = r6.mRecycler
            r5.put(r1, r4)
        L_0x0079:
            if (r4 == 0) goto L_0x00ae
            android.view.ViewGroup$LayoutParams r1 = r4.getLayoutParams()
            if (r1 != 0) goto L_0x008c
            r6.mBlockLayoutRequests = r2
            android.view.ViewGroup$LayoutParams r1 = r6.generateDefaultLayoutParams()
            r4.setLayoutParams(r1)
            r6.mBlockLayoutRequests = r3
        L_0x008c:
            r6.measureChild(r4, r7, r8)
            int r1 = r6.getChildHeight(r4)
            android.graphics.Rect r2 = r6.mSpinnerPadding
            int r2 = r2.top
            int r1 = r1 + r2
            android.graphics.Rect r2 = r6.mSpinnerPadding
            int r2 = r2.bottom
            int r1 = r1 + r2
            int r2 = r6.getChildWidth(r4)
            android.graphics.Rect r4 = r6.mSpinnerPadding
            int r4 = r4.left
            int r2 = r2 + r4
            android.graphics.Rect r4 = r6.mSpinnerPadding
            int r4 = r4.right
            int r2 = r2 + r4
            r3 = r2
            r2 = 0
            goto L_0x00af
        L_0x00ae:
            r1 = 0
        L_0x00af:
            if (r2 == 0) goto L_0x00c6
            android.graphics.Rect r1 = r6.mSpinnerPadding
            int r1 = r1.top
            android.graphics.Rect r2 = r6.mSpinnerPadding
            int r2 = r2.bottom
            int r1 = r1 + r2
            if (r0 != 0) goto L_0x00c6
            android.graphics.Rect r0 = r6.mSpinnerPadding
            int r0 = r0.left
            android.graphics.Rect r2 = r6.mSpinnerPadding
            int r2 = r2.right
            int r3 = r0 + r2
        L_0x00c6:
            int r0 = r6.getSuggestedMinimumHeight()
            int r0 = java.lang.Math.max(r1, r0)
            int r1 = r6.getSuggestedMinimumWidth()
            int r1 = java.lang.Math.max(r3, r1)
            int r0 = resolveSize(r0, r8)
            int r1 = resolveSize(r1, r7)
            r6.setMeasuredDimension(r1, r0)
            r6.mHeightMeasureSpec = r8
            r6.mWidthMeasureSpec = r7
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bsit.wheelview.TosAbsSpinner.onMeasure(int, int):void");
    }

    /* access modifiers changed from: package-private */
    public int getChildHeight(View view) {
        return view.getMeasuredHeight();
    }

    /* access modifiers changed from: package-private */
    public int getChildWidth(View view) {
        return view.getMeasuredWidth();
    }

    /* access modifiers changed from: protected */
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new ViewGroup.LayoutParams(-1, -2);
    }

    /* access modifiers changed from: package-private */
    public void recycleAllViews() {
        int childCount = getChildCount();
        RecycleBin recycleBin = this.mRecycler;
        for (int i = 0; i < childCount; i++) {
            recycleBin.put(this.mFirstPosition + i, getChildAt(i));
        }
    }

    /* access modifiers changed from: package-private */
    public void handleDataChanged() {
        super.handleDataChanged();
    }

    public void setSelection(int i, boolean z) {
        boolean z2 = true;
        if (!z || this.mFirstPosition > i || i > (this.mFirstPosition + getChildCount()) - 1) {
            z2 = false;
        }
        setSelectionInt(i, z2);
        checkSelectionChanged();
    }

    public void setSelection(int i) {
        this.mSelectedPosition = i;
        setNextSelectedPositionInt(i);
        requestLayout();
        invalidate();
        checkSelectionChanged();
    }

    /* access modifiers changed from: package-private */
    public void setSelectionInt(int i, boolean z) {
        if (i != this.mOldSelectedPosition) {
            this.mBlockLayoutRequests = true;
            setNextSelectedPositionInt(i);
            layout(i - this.mSelectedPosition, z);
            this.mBlockLayoutRequests = false;
        }
    }

    public View getSelectedView() {
        if (this.mItemCount <= 0 || this.mSelectedPosition < 0) {
            return null;
        }
        return getChildAt(this.mSelectedPosition - this.mFirstPosition);
    }

    public void requestLayout() {
        if (!this.mBlockLayoutRequests) {
            super.requestLayout();
        }
    }

    public SpinnerAdapter getAdapter() {
        return this.mAdapter;
    }

    public int getCount() {
        return this.mItemCount;
    }

    public int pointToPosition(int i, int i2) {
        Rect rect = this.mTouchFrame;
        if (rect == null) {
            this.mTouchFrame = new Rect();
            rect = this.mTouchFrame;
        }
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            if (childAt.getVisibility() == 0) {
                childAt.getHitRect(rect);
                if (rect.contains(i, i2)) {
                    return this.mFirstPosition + childCount;
                }
            }
        }
        return -1;
    }

    static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        int position;
        long selectedId;

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.selectedId = parcel.readLong();
            this.position = parcel.readInt();
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeLong(this.selectedId);
            parcel.writeInt(this.position);
        }

        public String toString() {
            return "AbsSpinner.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " selectedId=" + this.selectedId + " position=" + this.position + h.d;
        }
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.selectedId = getSelectedItemId();
        if (savedState.selectedId >= 0) {
            savedState.position = getSelectedItemPosition();
        } else {
            savedState.position = -1;
        }
        return savedState;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        if (savedState.selectedId >= 0) {
            this.mDataChanged = true;
            this.mNeedSync = true;
            this.mSyncRowId = savedState.selectedId;
            this.mSyncPosition = savedState.position;
            this.mSyncMode = 0;
            requestLayout();
        }
    }

    class RecycleBin {
        private SparseArray<View> mScrapHeap = new SparseArray<>();

        RecycleBin() {
        }

        public void put(int i, View view) {
            this.mScrapHeap.put(i, view);
        }

        /* access modifiers changed from: package-private */
        public View get(int i) {
            View view = this.mScrapHeap.get(i);
            if (view != null) {
                this.mScrapHeap.delete(i);
            }
            return view;
        }

        /* access modifiers changed from: package-private */
        public View peek(int i) {
            return this.mScrapHeap.get(i);
        }

        /* access modifiers changed from: package-private */
        public void clear() {
            SparseArray<View> sparseArray = this.mScrapHeap;
            int size = sparseArray.size();
            for (int i = 0; i < size; i++) {
                View valueAt = sparseArray.valueAt(i);
                if (valueAt != null) {
                    TosAbsSpinner.this.removeDetachedView(valueAt, true);
                }
            }
            sparseArray.clear();
        }
    }
}

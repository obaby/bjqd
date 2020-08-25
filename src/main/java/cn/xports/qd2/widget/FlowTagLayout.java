package cn.xports.qd2.widget;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FlowTagLayout extends ViewGroup {
    public static final int FLOW_TAG_CHECKED_MULTI = 2;
    public static final int FLOW_TAG_CHECKED_NONE = 0;
    public static final int FLOW_TAG_CHECKED_SINGLE = 1;
    private static final String TAG = "FlowTagLayout";
    ListAdapter mAdapter;
    /* access modifiers changed from: private */
    public SparseBooleanArray mCheckedTagArray = new SparseBooleanArray();
    AdapterDataSetObserver mDataSetObserver;
    OnTagClickListener mOnTagClickListener;
    OnTagSelectListener mOnTagSelectListener;
    /* access modifiers changed from: private */
    public int mTagCheckMode = 0;

    public interface OnInitSelectedPosition {
        boolean isSelectedPosition(int i);
    }

    public interface OnTagClickListener {
        void onItemClick(FlowTagLayout flowTagLayout, View view, int i);
    }

    public interface OnTagSelectListener {
        void onItemSelect(FlowTagLayout flowTagLayout, List<Integer> list);
    }

    public FlowTagLayout(Context context) {
        super(context);
    }

    public FlowTagLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public FlowTagLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int childCount = getChildCount();
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (i3 < childCount) {
            View childAt = getChildAt(i3);
            measureChild(childAt, i, i2);
            int measuredWidth = childAt.getMeasuredWidth();
            int measuredHeight = childAt.getMeasuredHeight();
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) childAt.getLayoutParams();
            int i8 = size2;
            int i9 = measuredWidth + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin;
            int i10 = measuredHeight + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
            int i11 = i4 + i9;
            if (i11 > size) {
                i7 = Math.max(i4, i9);
                i5 += i10;
                i4 = i9;
                i6 = i10;
            } else {
                i6 = Math.max(i6, i10);
                i4 = i11;
            }
            if (i3 == childCount - 1) {
                i5 += i6;
                i7 = Math.max(i4, i7);
            }
            setMeasuredDimension(mode == 1073741824 ? size : i7, mode2 == 1073741824 ? i8 : i5);
            i3++;
            size2 = i8;
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int width = getWidth();
        int childCount = getChildCount();
        int i5 = 0;
        int i6 = 0;
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            if (childAt.getVisibility() != 8) {
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) childAt.getLayoutParams();
                if (marginLayoutParams.leftMargin + i5 + measuredWidth + marginLayoutParams.rightMargin > width) {
                    i6 += marginLayoutParams.topMargin + measuredHeight + marginLayoutParams.bottomMargin;
                    i5 = 0;
                }
                childAt.layout(marginLayoutParams.leftMargin + i5, marginLayoutParams.topMargin + i6, marginLayoutParams.leftMargin + i5 + measuredWidth, marginLayoutParams.topMargin + i6 + measuredHeight);
                i5 += marginLayoutParams.leftMargin + measuredWidth + marginLayoutParams.rightMargin;
            }
        }
    }

    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new ViewGroup.MarginLayoutParams(getContext(), attributeSet);
    }

    public ListAdapter getAdapter() {
        return this.mAdapter;
    }

    class AdapterDataSetObserver extends DataSetObserver {
        AdapterDataSetObserver() {
        }

        public void onChanged() {
            super.onChanged();
            FlowTagLayout.this.reloadData();
        }

        public void onInvalidated() {
            super.onInvalidated();
        }
    }

    /* access modifiers changed from: private */
    public void reloadData() {
        removeAllViews();
        boolean z = false;
        for (final int i = 0; i < this.mAdapter.getCount(); i++) {
            this.mCheckedTagArray.put(i, false);
            final View view = this.mAdapter.getView(i, (View) null, this);
            addView(view, new ViewGroup.MarginLayoutParams(new ViewGroup.LayoutParams(-2, -2)));
            if (this.mAdapter instanceof OnInitSelectedPosition) {
                boolean isSelectedPosition = ((OnInitSelectedPosition) this.mAdapter).isSelectedPosition(i);
                if (this.mTagCheckMode == 1) {
                    if (isSelectedPosition && !z) {
                        this.mCheckedTagArray.put(i, true);
                        view.setSelected(true);
                        z = true;
                    }
                } else if (this.mTagCheckMode == 2 && isSelectedPosition) {
                    this.mCheckedTagArray.put(i, true);
                    view.setSelected(true);
                }
            }
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (FlowTagLayout.this.mTagCheckMode != 0) {
                        if (FlowTagLayout.this.mTagCheckMode == 1) {
                            if (FlowTagLayout.this.mCheckedTagArray.get(i)) {
                                FlowTagLayout.this.mCheckedTagArray.put(i, false);
                                view.setSelected(false);
                                if (FlowTagLayout.this.mOnTagSelectListener != null) {
                                    FlowTagLayout.this.mOnTagSelectListener.onItemSelect(FlowTagLayout.this, new ArrayList());
                                    return;
                                }
                                return;
                            }
                            for (int i = 0; i < FlowTagLayout.this.mAdapter.getCount(); i++) {
                                FlowTagLayout.this.mCheckedTagArray.put(i, false);
                                FlowTagLayout.this.getChildAt(i).setSelected(false);
                            }
                            FlowTagLayout.this.mCheckedTagArray.put(i, true);
                            view.setSelected(true);
                            if (FlowTagLayout.this.mOnTagSelectListener != null) {
                                FlowTagLayout.this.mOnTagSelectListener.onItemSelect(FlowTagLayout.this, Arrays.asList(new Integer[]{Integer.valueOf(i)}));
                            }
                        } else if (FlowTagLayout.this.mTagCheckMode == 2) {
                            if (FlowTagLayout.this.mCheckedTagArray.get(i)) {
                                FlowTagLayout.this.mCheckedTagArray.put(i, false);
                                view.setSelected(false);
                            } else {
                                FlowTagLayout.this.mCheckedTagArray.put(i, true);
                                view.setSelected(true);
                            }
                            if (FlowTagLayout.this.mOnTagSelectListener != null) {
                                ArrayList arrayList = new ArrayList();
                                for (int i2 = 0; i2 < FlowTagLayout.this.mAdapter.getCount(); i2++) {
                                    if (FlowTagLayout.this.mCheckedTagArray.get(i2)) {
                                        arrayList.add(Integer.valueOf(i2));
                                    }
                                }
                                FlowTagLayout.this.mOnTagSelectListener.onItemSelect(FlowTagLayout.this, arrayList);
                            }
                        }
                    } else if (FlowTagLayout.this.mOnTagClickListener != null) {
                        FlowTagLayout.this.mOnTagClickListener.onItemClick(FlowTagLayout.this, view, i);
                    }
                }
            });
        }
    }

    public void clearAllOption() {
        for (int i = 0; i < this.mAdapter.getCount(); i++) {
            if (this.mCheckedTagArray.get(i)) {
                getChildAt(i).setSelected(false);
            }
        }
    }

    public void setOnTagClickListener(OnTagClickListener onTagClickListener) {
        this.mOnTagClickListener = onTagClickListener;
    }

    public void setOnTagSelectListener(OnTagSelectListener onTagSelectListener) {
        this.mOnTagSelectListener = onTagSelectListener;
    }

    public void setAdapter(ListAdapter listAdapter) {
        if (!(this.mAdapter == null || this.mDataSetObserver == null)) {
            this.mAdapter.unregisterDataSetObserver(this.mDataSetObserver);
        }
        removeAllViews();
        this.mAdapter = listAdapter;
        if (this.mAdapter != null) {
            this.mDataSetObserver = new AdapterDataSetObserver();
            this.mAdapter.registerDataSetObserver(this.mDataSetObserver);
        }
    }

    public int getmTagCheckMode() {
        return this.mTagCheckMode;
    }

    public void setTagCheckedMode(int i) {
        this.mTagCheckMode = i;
    }
}

package cn.xports.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class EmptyRecyclerView extends RecyclerView {
    private View mEmptyView;
    private RecyclerView.AdapterDataObserver observer = new RecyclerView.AdapterDataObserver() {
        public void onChanged() {
            super.onChanged();
            EmptyRecyclerView.this.checkIfEmpty();
        }

        public void onItemRangeChanged(int i, int i2) {
            super.onItemRangeChanged(i, i2);
            EmptyRecyclerView.this.checkIfEmpty();
        }

        public void onItemRangeMoved(int i, int i2, int i3) {
            super.onItemRangeMoved(i, i2, i3);
            EmptyRecyclerView.this.checkIfEmpty();
        }

        public void onItemRangeRemoved(int i, int i2) {
            super.onItemRangeRemoved(i, i2);
            EmptyRecyclerView.this.checkIfEmpty();
        }

        public void onItemRangeInserted(int i, int i2) {
            super.onItemRangeInserted(i, i2);
            EmptyRecyclerView.this.checkIfEmpty();
        }
    };

    public EmptyRecyclerView(Context context) {
        super(context);
    }

    public EmptyRecyclerView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public EmptyRecyclerView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        RecyclerView.Adapter adapter2 = getAdapter();
        if (adapter2 != null) {
            adapter2.unregisterAdapterDataObserver(this.observer);
        }
        super.setAdapter(adapter);
        if (adapter != null) {
            adapter.registerAdapterDataObserver(this.observer);
        }
        checkIfEmpty();
    }

    public void setEmptyView(View view) {
        this.mEmptyView = view;
        if (view != null) {
            this.mEmptyView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
            ((ViewGroup) getParent()).addView(this.mEmptyView);
        }
    }

    /* access modifiers changed from: package-private */
    public void checkIfEmpty() {
        if (this.mEmptyView != null && getAdapter() != null) {
            int i = 0;
            boolean z = getAdapter().getItemCount() == 0;
            this.mEmptyView.setVisibility(z ? 0 : 8);
            if (z) {
                i = 8;
            }
            setVisibility(i);
        }
    }
}

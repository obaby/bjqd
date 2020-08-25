package cn.xports.baselib.adapter;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import me.drakeet.multitype.ItemViewBinder;

public abstract class XViewBinder<T> extends ItemViewBinder<T, XBaseHolder> {
    protected OnItemClickListener clickListener;

    public interface OnItemClickListener<T> {
        void onItemClick(T t, int i);
    }

    /* access modifiers changed from: protected */
    public abstract int getLayoutId();

    /* access modifiers changed from: protected */
    @NonNull
    public XBaseHolder onCreateViewHolder(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
        return new XBaseHolder(layoutInflater.inflate(getLayoutId(), viewGroup, false));
    }

    /* access modifiers changed from: protected */
    public void performItemClick(T t, int i) {
        if (this.clickListener != null) {
            this.clickListener.onItemClick(t, i);
        }
    }

    /* access modifiers changed from: protected */
    public void performItemClick(T t) {
        if (this.clickListener != null) {
            this.clickListener.onItemClick(t, 0);
        }
    }

    public XViewBinder setOnItemClickListener(OnItemClickListener<T> onItemClickListener) {
        this.clickListener = onItemClickListener;
        return this;
    }
}

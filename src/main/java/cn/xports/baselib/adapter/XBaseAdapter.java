package cn.xports.baselib.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.List;

public abstract class XBaseAdapter<T> extends RecyclerView.Adapter<XBaseHolder> {
    protected OnItemClickListener clickListener;
    protected List<T> list;

    public interface OnItemClickListener<T> {
        void onItemClick(T t, int i, int i2);
    }

    public abstract int getLayoutId();

    public XBaseAdapter(List<T> list2) {
        this.list = list2;
    }

    @NonNull
    public XBaseHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new XBaseHolder(LayoutInflater.from(viewGroup.getContext()).inflate(getLayoutId(), viewGroup, false));
    }

    public int getItemCount() {
        if (this.list == null) {
            return 0;
        }
        return this.list.size();
    }

    public XBaseAdapter setOnItemClickListener(OnItemClickListener<T> onItemClickListener) {
        this.clickListener = onItemClickListener;
        return this;
    }

    /* access modifiers changed from: protected */
    public void performItemClick(T t, int i, int i2) {
        if (this.clickListener != null) {
            this.clickListener.onItemClick(t, i, i2);
        }
    }

    /* access modifiers changed from: protected */
    public void performItemClick(T t, int i) {
        if (this.clickListener != null) {
            this.clickListener.onItemClick(t, i, 0);
        }
    }
}

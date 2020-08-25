package cn.xports.baselib.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.List;

public abstract class XListAdapter<T> extends BaseAdapter {
    protected OnItemClickListener clickListener;
    protected List<T> list;

    public interface OnItemClickListener<T> {
        void onItemClick(T t, int i, int i2);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public abstract int getLayoutId();

    public abstract void onBindHolder(XBaseHolder xBaseHolder, T t, int i);

    public XListAdapter(List<T> list2) {
        this.list = list2;
    }

    public int getCount() {
        if (this.list == null) {
            return 0;
        }
        return this.list.size();
    }

    public T getItem(int i) {
        return this.list.get(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        XBaseHolder xBaseHolder;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(getLayoutId(), viewGroup, false);
            xBaseHolder = new XBaseHolder(view);
        } else {
            xBaseHolder = (XBaseHolder) view.getTag();
        }
        onBindHolder(xBaseHolder, getItem(i), i);
        view.setTag(xBaseHolder);
        return view;
    }

    public XListAdapter setOnItemClickListener(OnItemClickListener<T> onItemClickListener) {
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

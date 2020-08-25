package a;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;
import java.util.List;

public abstract class a<T> extends BaseAdapter {

    /* renamed from: a  reason: collision with root package name */
    public Context f17a;

    /* renamed from: b  reason: collision with root package name */
    public int f18b;

    /* renamed from: c  reason: collision with root package name */
    private List<T> f19c = new ArrayList();

    /* access modifiers changed from: protected */
    public abstract View a(int i, View view, ViewGroup viewGroup);

    public long getItemId(int i) {
        return (long) i;
    }

    public a(Context context, int i) {
        this.f17a = context;
        this.f18b = i;
    }

    public int getCount() {
        return this.f19c.size();
    }

    public Object getItem(int i) {
        return Integer.valueOf(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        return a(i, view, viewGroup);
    }

    public void a(List<T> list) {
        if (list != null && list.size() > 0) {
            this.f19c.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void b(List<T> list) {
        if (list != null && list.size() > 0) {
            this.f19c.clear();
            a(list);
        }
    }

    public void a(int i) {
        if (i < this.f19c.size()) {
            this.f19c.remove(i);
        }
    }

    public List<T> a() {
        return this.f19c;
    }
}

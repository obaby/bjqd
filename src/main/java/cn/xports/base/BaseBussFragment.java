package cn.xports.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cn.xports.baselib.adapter.XBaseHolder;
import cn.xports.baselib.mvp.BaseFragment;

public abstract class BaseBussFragment extends BaseFragment {
    /* access modifiers changed from: protected */
    public abstract int getLayoutId();

    /* access modifiers changed from: protected */
    public abstract void initView(XBaseHolder xBaseHolder);

    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        if (getLayoutId() == 0) {
            return null;
        }
        View inflate = layoutInflater.inflate(getLayoutId(), viewGroup, false);
        initView(new XBaseHolder(inflate));
        return inflate;
    }
}

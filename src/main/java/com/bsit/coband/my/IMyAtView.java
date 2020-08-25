package com.bsit.coband.my;

import android.support.v7.widget.RecyclerView;
import com.convenient.qd.core.base.mvp.BaseView;

public interface IMyAtView extends BaseView {
    RecyclerView getItemListView();

    void onPainSign(String str, String str2, String str3);
}

package cn.xports.qd2.adapter;

import android.support.annotation.NonNull;
import cn.xports.baselib.adapter.XBaseHolder;
import cn.xports.baselib.adapter.XViewBinder;
import cn.xports.qd2.R;

public class TableTimeBinder extends XViewBinder<String> {
    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.item_table_time;
    }

    /* access modifiers changed from: protected */
    public void onBindViewHolder(@NonNull XBaseHolder xBaseHolder, @NonNull String str) {
        xBaseHolder.setText(R.id.tv_time, new StringBuilder(str).insert(2, ":").insert(8, ":").toString());
    }
}

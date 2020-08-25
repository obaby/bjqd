package cn.xports.qd2.adapter;

import android.view.View;
import cn.xports.baselib.adapter.XBaseAdapter;
import cn.xports.baselib.adapter.XBaseHolder;
import cn.xports.baselib.bean.DataMap;
import cn.xports.qd2.R;
import cn.xports.qd2.entity.K;
import java.util.List;

public class CreditPagerAdapter extends NoLimitPagerAdapter<DataMap> {
    /* access modifiers changed from: private */
    public XBaseAdapter.OnItemClickListener<DataMap> onItemClickListener;

    public CreditPagerAdapter(List<DataMap> list) {
        super(list);
    }

    public CreditPagerAdapter(List<DataMap> list, boolean z) {
        super(list, z);
    }

    private void fillData(View view, final DataMap dataMap) {
        XBaseHolder xBaseHolder = new XBaseHolder(view);
        if (dataMap != null) {
            int i = R.id.tv_card_no;
            xBaseHolder.setText(i, "NO." + dataMap.getString(K.ecardNo));
            xBaseHolder.setText(R.id.tv_point_balance, dataMap.getString("pointsBalance", K.k0));
        }
        xBaseHolder.setOnClickListener(R.id.v_rule_click, new View.OnClickListener() {
            public void onClick(View view) {
                if (CreditPagerAdapter.this.onItemClickListener != null) {
                    CreditPagerAdapter.this.onItemClickListener.onItemClick(dataMap, -1, 0);
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void fillItem(View view, DataMap dataMap, int i) {
        fillData(view, dataMap);
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.item_pager_credit;
    }

    public CreditPagerAdapter setOnItemClickListener(XBaseAdapter.OnItemClickListener<DataMap> onItemClickListener2) {
        this.onItemClickListener = onItemClickListener2;
        return this;
    }
}

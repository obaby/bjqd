package cn.xports.qd2.adapter;

import android.support.annotation.NonNull;
import cn.xports.baselib.adapter.XBaseAdapter;
import cn.xports.baselib.adapter.XBaseHolder;
import cn.xports.baselib.bean.DataMap;
import cn.xports.qd2.R;
import java.util.List;

public class CreditRuleAdapter extends XBaseAdapter<DataMap> {
    public CreditRuleAdapter(List<DataMap> list) {
        super(list);
    }

    public int getLayoutId() {
        return R.layout.item_credit_rule;
    }

    public void onBindViewHolder(@NonNull XBaseHolder xBaseHolder, int i) {
        DataMap dataMap = (DataMap) this.list.get(i);
        xBaseHolder.setText(R.id.tv_question, dataMap.getString("question"));
        xBaseHolder.setText(R.id.tv_answer, dataMap.getString("answer"));
    }
}

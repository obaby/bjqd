package cn.xports.qd2.adapter;

import android.support.annotation.NonNull;
import cn.xports.baselib.adapter.XBaseAdapter;
import cn.xports.baselib.adapter.XBaseHolder;
import cn.xports.baselib.bean.DataMap;
import cn.xports.qd2.R;
import cn.xports.qd2.entity.K;
import com.blankj.utilcode.util.ColorUtils;
import java.util.List;

public class CreditDetailAdapter extends XBaseAdapter<DataMap> {
    public CreditDetailAdapter(List<DataMap> list) {
        super(list);
    }

    public int getLayoutId() {
        return R.layout.item_credit_detail;
    }

    public void onBindViewHolder(@NonNull XBaseHolder xBaseHolder, int i) {
        DataMap dataMap = (DataMap) this.list.get(i);
        dataMap.getString("changeType");
        String string = dataMap.getString("changePoints");
        int i2 = R.drawable.ic_money_use;
        int color = ColorUtils.getColor(R.color.blue_2e6);
        String str = "+" + dataMap.getString("changePoints");
        if (string.startsWith("-")) {
            i2 = R.drawable.ic_credit_gift;
            color = ColorUtils.getColor(R.color.main_text_color);
            str = dataMap.getString("changePoints");
        }
        xBaseHolder.setImageResource(R.id.iv_tag, i2);
        xBaseHolder.setText(R.id.tv_name, dataMap.getString("changeDesc"));
        xBaseHolder.setText(R.id.tv_change_point, str);
        xBaseHolder.setTextColor(R.id.tv_change_point, color);
        xBaseHolder.setText(R.id.tv_date, dataMap.getString(K.createTime));
    }
}

package cn.xports.qd2.adapter;

import android.support.annotation.NonNull;
import cn.xports.baselib.adapter.XBaseHolder;
import cn.xports.baselib.adapter.XViewBinder;
import cn.xports.baselib.bean.DataMap;
import cn.xports.qd2.R;
import cn.xports.qd2.entity.K;
import com.blankj.utilcode.util.ColorUtils;

public class CreditUseHistBinder extends XViewBinder<DataMap> {
    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.item_credit_detail;
    }

    /* access modifiers changed from: protected */
    public void onBindViewHolder(@NonNull XBaseHolder xBaseHolder, @NonNull DataMap dataMap) {
        xBaseHolder.setVisible(R.id.iv_tag, false);
        xBaseHolder.setText(R.id.tv_name, dataMap.getString(K.couponName));
        xBaseHolder.setText(R.id.tv_change_point, dataMap.getString("changePoints"));
        xBaseHolder.setTextColor(R.id.tv_change_point, ColorUtils.getColor(R.color.main_text_color));
        xBaseHolder.setText(R.id.tv_date, dataMap.getString(K.createTime));
        int indexOf = getAdapter().getItems().indexOf(dataMap);
        if (indexOf <= 0 || !(getAdapter().getItems().get(indexOf - 1) instanceof String)) {
            xBaseHolder.setVisible(R.id.v_line, true);
        } else {
            xBaseHolder.setVisible(R.id.v_line, false);
        }
    }
}

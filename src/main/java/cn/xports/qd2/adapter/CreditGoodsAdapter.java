package cn.xports.qd2.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import cn.xports.baselib.adapter.XBaseAdapter;
import cn.xports.baselib.adapter.XBaseHolder;
import cn.xports.baselib.bean.DataMap;
import cn.xports.baselib.util.GlideUtil;
import cn.xports.baselib.util.MoneyUtil;
import cn.xports.qd2.R;
import cn.xports.qd2.entity.K;
import java.util.List;

public class CreditGoodsAdapter extends XBaseAdapter<DataMap> {
    public CreditGoodsAdapter(List<DataMap> list) {
        super(list);
    }

    public int getItemCount() {
        if (super.getItemCount() > 3) {
            return 3;
        }
        return super.getItemCount();
    }

    public int getLayoutId() {
        return R.layout.item_credit_goods;
    }

    public void onBindViewHolder(@NonNull XBaseHolder xBaseHolder, final int i) {
        final DataMap dataMap = (DataMap) this.list.get(i);
        String string = dataMap.getString("objectType");
        xBaseHolder.setVisible(R.id.rl_coupon, "1".equals(string));
        xBaseHolder.setVisible(R.id.rl_gift, "2,3,4".contains(string));
        if ("1".equals(string)) {
            xBaseHolder.setText(R.id.tv_money, MoneyUtil.simpleYuan(dataMap.getIntValue("value").intValue()));
        } else {
            ImageView imageView = (ImageView) xBaseHolder.getView(R.id.iv_gift);
            GlideUtil.loadImage(imageView.getContext(), dataMap.getString("photo"), R.drawable.bg_default_square).into(imageView);
        }
        xBaseHolder.setText(R.id.tv_name, dataMap.getString(K.couponName));
        int i2 = R.id.tv_charge_value;
        xBaseHolder.setText(i2, dataMap.getIntValue("exchangeNum") + "积分");
        xBaseHolder.setVisible(R.id.v_line, 3 != i + 1);
        xBaseHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CreditGoodsAdapter.this.performItemClick(dataMap, i);
            }
        });
    }
}

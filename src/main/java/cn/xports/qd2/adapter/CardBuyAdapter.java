package cn.xports.qd2.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;
import cn.xports.baselib.adapter.XBaseAdapter;
import cn.xports.baselib.adapter.XBaseHolder;
import cn.xports.baselib.util.MoneyUtil;
import cn.xports.entity.ValidProduct;
import cn.xports.qd2.CardBuyDetailActivity;
import cn.xports.qd2.R;
import cn.xports.qd2.entity.K;
import java.util.List;

public class CardBuyAdapter extends XBaseAdapter<ValidProduct> {
    public CardBuyAdapter(List<ValidProduct> list) {
        super(list);
    }

    public int getLayoutId() {
        return R.layout.item_card_to_buy;
    }

    public void onBindViewHolder(@NonNull final XBaseHolder xBaseHolder, int i) {
        final ValidProduct validProduct = (ValidProduct) this.list.get(i);
        TextView textView = (TextView) xBaseHolder.getView(R.id.tvProductMoney);
        int discountMoney = validProduct.getDiscountMoney();
        if (discountMoney == 0) {
            textView.setVisibility(8);
            int i2 = R.id.tv_card_price;
            xBaseHolder.setText(i2, "¥" + MoneyUtil.cent2Yuan((int) validProduct.getPrice()));
        } else {
            textView.getPaint().setFlags(16);
            textView.setVisibility(0);
            int i3 = R.id.tv_card_price;
            xBaseHolder.setText(i3, "¥" + MoneyUtil.cent2Yuan(discountMoney));
            textView.setText("¥" + MoneyUtil.cent2Yuan((int) validProduct.getPrice()));
        }
        int i4 = R.id.tv_card_has_buy;
        xBaseHolder.setText(i4, validProduct.getBuyNum() + "人已购");
        xBaseHolder.setText(R.id.tv_card_name, validProduct.getProductName());
        xBaseHolder.setOnClickListener(R.id.ll_go_buy, new View.OnClickListener() {
            public void onClick(View view) {
                xBaseHolder.itemView.getContext().startActivity(new Intent(xBaseHolder.itemView.getContext(), CardBuyDetailActivity.class).putExtra("productId", validProduct.getProductId()).putExtra(K.serviceId, validProduct.getServiceId()).putExtra("goodsId", validProduct.getGoodsId()));
            }
        });
    }
}

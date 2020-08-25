package cn.xports.venue;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import cn.xports.base.Router;
import cn.xports.baselib.adapter.XBaseAdapter;
import cn.xports.baselib.adapter.XBaseHolder;
import cn.xports.baselib.util.MoneyUtil;
import cn.xports.entity.ValidProduct;
import cn.xports.qd2.entity.K;
import cn.xports.qdplugin.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class VenueCardBuyAdapter extends XBaseAdapter<ValidProduct> {
    private List<ValidProduct> products = new ArrayList();

    public VenueCardBuyAdapter(List<ValidProduct> list) {
        super(list);
    }

    public int getLayoutId() {
        return R.layout.item_card_2buy;
    }

    public void onBindViewHolder(@NonNull final XBaseHolder xBaseHolder, int i) {
        TextView textView = (TextView) xBaseHolder.getView(R.id.tv_origin_price);
        textView.getPaint().setFlags(16);
        final ValidProduct validProduct = this.products.get(i);
        xBaseHolder.setText(R.id.tv_ticket_name, validProduct.getProductName());
        int i2 = R.id.tv_ticket_left;
        xBaseHolder.setText(i2, "已购：" + validProduct.getBuyNum() + "人");
        if (validProduct.getDiscountMoney() > 0) {
            textView.setVisibility(0);
            textView.setText("¥" + MoneyUtil.cent2Yuan((int) validProduct.getPrice()));
            xBaseHolder.setText(R.id.tv_ticket_money, MoneyUtil.cent2Yuan(validProduct.getDiscountMoney()));
        } else {
            textView.setVisibility(8);
            xBaseHolder.setText(R.id.tv_ticket_money, MoneyUtil.cent2Yuan((int) validProduct.getPrice()));
        }
        xBaseHolder.setOnClickListener(R.id.tv_buy_ticket, new View.OnClickListener() {
            public void onClick(View view) {
                HashMap hashMap = new HashMap();
                hashMap.put("productId", validProduct.getProductId());
                hashMap.put(K.serviceId, validProduct.getServiceId());
                hashMap.put("goodsId", validProduct.getGoodsId());
                xBaseHolder.itemView.getContext().startActivity(Router.getIntent(Router.CARD_BUY_DETAIL, hashMap));
            }
        });
        xBaseHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                HashMap hashMap = new HashMap();
                hashMap.put("productId", validProduct.getProductId());
                hashMap.put(K.serviceId, validProduct.getServiceId());
                hashMap.put("goodsId", validProduct.getGoodsId());
                xBaseHolder.itemView.getContext().startActivity(Router.getIntent(Router.CARD_BUY_DETAIL, hashMap));
            }
        });
        int i3 = R.id.v_line;
        boolean z = true;
        if (i <= 5 && i == getItemCount() - 1) {
            z = false;
        }
        xBaseHolder.setVisible(i3, z);
    }

    public int getItemCount() {
        return this.products.size();
    }

    public void expand() {
        this.products.clear();
        this.products.addAll(this.list);
        notifyDataSetChanged();
    }

    public void up() {
        this.products.clear();
        this.products.addAll(this.list.subList(0, 5));
        notifyDataSetChanged();
    }

    public void notifyData() {
        this.products.clear();
        if (this.list.size() > 5) {
            this.products.addAll(this.list.subList(0, 5));
        } else {
            this.products.addAll(this.list);
        }
        notifyDataSetChanged();
    }

    public class Holder extends RecyclerView.ViewHolder {
        View line;
        TextView tvBuy;
        TextView tvTicketLeft;
        TextView tvTicketMoney;
        TextView tvTicketName;

        public Holder(@NonNull View view) {
            super(view);
            this.tvTicketName = (TextView) view.findViewById(R.id.tv_ticket_name);
            this.tvTicketLeft = (TextView) view.findViewById(R.id.tv_ticket_left);
            this.tvTicketMoney = (TextView) view.findViewById(R.id.tv_ticket_money);
            this.tvBuy = (TextView) view.findViewById(R.id.tv_buy_ticket);
            this.line = view.findViewById(R.id.v_line);
        }
    }
}

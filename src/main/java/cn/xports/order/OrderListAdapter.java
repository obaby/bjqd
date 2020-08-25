package cn.xports.order;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.xports.baselib.util.MoneyUtil;
import cn.xports.entity.OrderInfo;
import cn.xports.qdplugin.R;
import java.util.List;

public class OrderListAdapter extends RecyclerView.Adapter<OrderHolder> {
    private List<OrderInfo> orderInfos;
    /* access modifiers changed from: private */
    public OnPayOption payOption;

    public interface OnPayOption {
        void onCancelPay(String str);

        void onPayClick(String str);
    }

    public OrderListAdapter(List<OrderInfo> list) {
        this.orderInfos = list;
    }

    @NonNull
    public OrderHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new OrderHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_order_list, viewGroup, false));
    }

    public void onBindViewHolder(@NonNull final OrderHolder orderHolder, int i) {
        String str;
        final OrderInfo orderInfo = this.orderInfos.get(i);
        String orderState = orderInfo.getOrderState();
        orderHolder.vShowPay.setVisibility(8);
        orderHolder.tvTradeState.setTextColor(Color.parseColor("#353535"));
        if ("1".equals(orderState)) {
            str = "已取消";
        } else if (!"2".equals(orderState)) {
            str = "3".equals(orderState) ? "已完成" : "未知";
        } else if (orderInfo.isExpired()) {
            str = "已过期";
        } else {
            str = "待支付";
            orderHolder.vShowPay.setVisibility(0);
            orderHolder.tvTradeState.setTextColor(Color.parseColor("#fd4772"));
        }
        orderHolder.tvTradeState.setText(str);
        orderHolder.tvTradeName.setText(orderInfo.getTradeTypeCodeName());
        TextView textView = orderHolder.tvPrice;
        textView.setText("¥" + MoneyUtil.cent2Yuan(orderInfo.getPayTfee()));
        TextView textView2 = orderHolder.tvTotalMoney;
        textView2.setText("¥" + MoneyUtil.cent2Yuan(orderInfo.getPayTfee()));
        if (orderInfo.getTotalCashPledge() > 0) {
            TextView textView3 = orderHolder.tvCash;
            textView3.setText("押金：¥" + MoneyUtil.cent2Yuan(orderInfo.getTotalCashPledge()));
        }
        orderHolder.tvTicketName.setText(orderInfo.getTitle());
        orderHolder.tvDate.setText(orderInfo.getAcceptDate());
        orderHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Context context = orderHolder.itemView.getContext();
                context.startActivity(new Intent(context, OrderDetailActivity.class).putExtra("tradeId", orderInfo.getTradeId()));
            }
        });
        orderHolder.tvPay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (OrderListAdapter.this.payOption != null) {
                    OrderListAdapter.this.payOption.onPayClick(orderInfo.getTradeId());
                }
            }
        });
        orderHolder.tvCancelPay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (OrderListAdapter.this.payOption != null) {
                    OrderListAdapter.this.payOption.onCancelPay(orderInfo.getTradeId());
                }
            }
        });
    }

    public int getItemCount() {
        if (this.orderInfos == null) {
            return 0;
        }
        return this.orderInfos.size();
    }

    public OrderListAdapter setPayOption(OnPayOption onPayOption) {
        this.payOption = onPayOption;
        return this;
    }

    public class OrderHolder extends RecyclerView.ViewHolder {
        TextView tvCancelPay;
        TextView tvCash;
        TextView tvDate;
        TextView tvPay;
        TextView tvPrice;
        TextView tvTicketName;
        TextView tvTotalMoney;
        TextView tvTradeName;
        TextView tvTradeState;
        View vShowPay;

        public OrderHolder(@NonNull View view) {
            super(view);
            this.tvCancelPay = (TextView) view.findViewById(R.id.tv_cancel_pay);
            this.tvTradeName = (TextView) view.findViewById(R.id.tv_trade_name);
            this.tvTradeState = (TextView) view.findViewById(R.id.tv_trade_state);
            this.tvTicketName = (TextView) view.findViewById(R.id.tv_ticket_name);
            this.tvPrice = (TextView) view.findViewById(R.id.tv_ticket_price);
            this.tvDate = (TextView) view.findViewById(R.id.tv_ticket_time);
            this.tvCash = (TextView) view.findViewById(R.id.tv_ticket_cash);
            this.tvTotalMoney = (TextView) view.findViewById(R.id.tv_ticket_total_money);
            this.tvPay = (TextView) view.findViewById(R.id.tv_pay);
            this.vShowPay = view.findViewById(R.id.v_pay);
        }
    }
}

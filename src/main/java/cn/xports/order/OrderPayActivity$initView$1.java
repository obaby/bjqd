package cn.xports.order;

import android.view.View;
import android.widget.TextView;
import cn.xports.entity.BalanceInfo;
import cn.xports.qdplugin.R;
import cn.xports.widget.PaySelectView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 15})
/* compiled from: OrderPayActivity.kt */
final class OrderPayActivity$initView$1 implements View.OnClickListener {
    final /* synthetic */ OrderPayActivity this$0;

    OrderPayActivity$initView$1(OrderPayActivity orderPayActivity) {
        this.this$0 = orderPayActivity;
    }

    public final void onClick(View view) {
        TextView textView = (TextView) this.this$0._$_findCachedViewById(R.id.tvCountDown);
        Intrinsics.checkExpressionValueIsNotNull(textView, "tvCountDown");
        if (Intrinsics.areEqual(textView.getText().toString(), "订单已过期")) {
            this.this$0.showMsg("订单已过期！");
            return;
        }
        PaySelectView paySelectView = (PaySelectView) this.this$0._$_findCachedViewById(R.id.paySelectView);
        Intrinsics.checkExpressionValueIsNotNull(paySelectView, "paySelectView");
        if (paySelectView.isHasSelect()) {
            String str = "场地票/次票";
            if (this.this$0.getProductType() == 1) {
                str = "购卡";
            } else if (this.this$0.getProductType() == 2) {
                str = "赛事/活动报名费";
            } else if (this.this$0.getProductType() == 3) {
                str = "私教/培训报名费";
            }
            String str2 = str;
            PaySelectView paySelectView2 = (PaySelectView) this.this$0._$_findCachedViewById(R.id.paySelectView);
            Intrinsics.checkExpressionValueIsNotNull(paySelectView2, "paySelectView");
            if (paySelectView2.isWechatSelect()) {
                this.this$0.getPayPresenter().callWexin(this.this$0.getTradeId(), this.this$0.getTotal(), this.this$0.getMCash(), str2, this.this$0.couponNo, this.this$0.couponMoney);
            } else {
                this.this$0.getPayPresenter().callAli(this.this$0.getTradeId(), this.this$0.getTotal(), this.this$0.getMCash(), str2, this.this$0.couponNo, this.this$0.couponMoney);
            }
        } else if (this.this$0.getSelectCard() != null) {
            BalanceInfo selectCard = this.this$0.getSelectCard();
            if (selectCard == null) {
                return;
            }
            if (this.this$0.couponMoney + selectCard.getBalance() < this.this$0.getTotal()) {
                this.this$0.showMsg("您选择的支付方式余额不足");
            } else {
                this.this$0.getPayPresenter().ecardFuckPay(this.this$0.getTradeId(), selectCard, this.this$0.getTotal(), this.this$0.couponNo, this.this$0.couponMoney);
            }
        } else if (!(!Intrinsics.areEqual(this.this$0.couponNo, ""))) {
            this.this$0.showMsg("请选择支付方式");
        } else if (this.this$0.couponMoney < this.this$0.getTotal()) {
            this.this$0.showMsg("您选择的支付方式余额不足");
        } else {
            this.this$0.getPayPresenter().ecardFuckPay(this.this$0.getTradeId(), (BalanceInfo) null, this.this$0.getTotal(), this.this$0.couponNo, this.this$0.couponMoney);
        }
    }
}

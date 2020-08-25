package cn.xports.order;

import android.widget.LinearLayout;
import android.widget.TextView;
import cn.xports.baselib.util.MoneyUtil;
import cn.xports.entity.BalanceInfo;
import cn.xports.entity.PairEvent;
import cn.xports.qdplugin.R;
import cn.xports.widget.PaySelectView;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lcn/xports/entity/PairEvent;", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 15})
/* compiled from: OrderPayActivity.kt */
final class OrderPayActivity$initData$1<T> implements Consumer<PairEvent> {
    final /* synthetic */ OrderPayActivity this$0;

    OrderPayActivity$initData$1(OrderPayActivity orderPayActivity) {
        this.this$0 = orderPayActivity;
    }

    public final void accept(PairEvent pairEvent) {
        if (pairEvent != null && Intrinsics.areEqual(pairEvent.getKey1(), this.this$0.getTAG())) {
            if (pairEvent.getKey2() != null) {
                ((PaySelectView) this.this$0._$_findCachedViewById(R.id.paySelectView)).setNoSelect();
                Object key2 = pairEvent.getKey2();
                if (key2 != null) {
                    BalanceInfo balanceInfo = (BalanceInfo) key2;
                    this.this$0.setSelectCard(balanceInfo);
                    OrderPayActivity orderPayActivity = this.this$0;
                    String payMode = balanceInfo.getPayMode();
                    Intrinsics.checkExpressionValueIsNotNull(payMode, "balanceInfo.payMode");
                    orderPayActivity.setPayMode(payMode);
                    BalanceInfo selectCard = this.this$0.getSelectCard();
                    int balance = selectCard != null ? selectCard.getBalance() : 0;
                    if (Intrinsics.areEqual(this.this$0.couponNo, "")) {
                        this.this$0.setTotal(balanceInfo.shouldPayLeft());
                        this.this$0.cardPayMoney = Math.min(this.this$0.getTotal(), balance);
                    } else {
                        this.this$0.setTotal(BalanceInfo.shouldPayParse(this.this$0.getCommonPay()));
                        OrderPayActivity orderPayActivity2 = this.this$0;
                        Integer intValue = this.this$0.coupon.getIntValue("balance");
                        Intrinsics.checkExpressionValueIsNotNull(intValue, "coupon.getIntValue(\"balance\")");
                        orderPayActivity2.couponMoney = intValue.intValue();
                        if (this.this$0.couponMoney >= this.this$0.getTotal()) {
                            this.this$0.couponMoney = this.this$0.getTotal();
                        }
                        this.this$0.cardPayMoney = Math.max(0, (this.this$0.getTotal() - this.this$0.couponMoney) - balanceInfo.getOrderDiscount());
                    }
                    this.this$0.cardPayMoney = Math.min(this.this$0.cardPayMoney, balance);
                    TextView textView = (TextView) this.this$0._$_findCachedViewById(R.id.tvCardPayMoney);
                    Intrinsics.checkExpressionValueIsNotNull(textView, "tvCardPayMoney");
                    textView.setText(this.this$0.getResources().getString(R.string.yuan) + MoneyUtil.cent2Yuan(this.this$0.cardPayMoney));
                    this.this$0.setShouldPayText();
                    int totalPayMoney = balanceInfo.getTotalPayMoney() - this.this$0.getTotal();
                    if (this.this$0.getTotal() - this.this$0.couponMoney <= 0 || totalPayMoney <= 0) {
                        LinearLayout linearLayout = (LinearLayout) this.this$0._$_findCachedViewById(R.id.crCardDiscount);
                        Intrinsics.checkExpressionValueIsNotNull(linearLayout, "crCardDiscount");
                        linearLayout.setVisibility(8);
                        TextView textView2 = (TextView) this.this$0._$_findCachedViewById(R.id.tvCardDiscount);
                        Intrinsics.checkExpressionValueIsNotNull(textView2, "tvCardDiscount");
                        textView2.setText("");
                        return;
                    }
                    LinearLayout linearLayout2 = (LinearLayout) this.this$0._$_findCachedViewById(R.id.crCardDiscount);
                    Intrinsics.checkExpressionValueIsNotNull(linearLayout2, "crCardDiscount");
                    linearLayout2.setVisibility(0);
                    TextView textView3 = (TextView) this.this$0._$_findCachedViewById(R.id.tvCardDiscount);
                    Intrinsics.checkExpressionValueIsNotNull(textView3, "tvCardDiscount");
                    textView3.setText("折扣-" + MoneyUtil.cent2Yuan(totalPayMoney) + "元");
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type cn.xports.entity.BalanceInfo");
            }
            this.this$0.setSelectCard((BalanceInfo) null);
            this.this$0.setPayMode("");
            this.this$0.cardPayMoney = 0;
            TextView textView4 = (TextView) this.this$0._$_findCachedViewById(R.id.tvCardPayMoney);
            Intrinsics.checkExpressionValueIsNotNull(textView4, "tvCardPayMoney");
            textView4.setText("");
            TextView textView5 = (TextView) this.this$0._$_findCachedViewById(R.id.tvCardDiscount);
            Intrinsics.checkExpressionValueIsNotNull(textView5, "tvCardDiscount");
            textView5.setVisibility(8);
            this.this$0.setTotal(BalanceInfo.shouldPayParse(this.this$0.getCommonPay()));
            if (!Intrinsics.areEqual(this.this$0.couponNo, "")) {
                OrderPayActivity orderPayActivity3 = this.this$0;
                Integer intValue2 = this.this$0.coupon.getIntValue("balance");
                Intrinsics.checkExpressionValueIsNotNull(intValue2, "coupon.getIntValue(\"balance\")");
                orderPayActivity3.couponMoney = intValue2.intValue();
                if (this.this$0.couponMoney >= this.this$0.getTotal()) {
                    this.this$0.couponMoney = this.this$0.getTotal();
                }
            }
            this.this$0.setShouldPayText();
        }
    }
}

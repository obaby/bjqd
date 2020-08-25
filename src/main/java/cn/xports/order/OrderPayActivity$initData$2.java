package cn.xports.order;

import android.widget.LinearLayout;
import android.widget.TextView;
import cn.xports.baselib.bean.DataMap;
import cn.xports.baselib.util.MoneyUtil;
import cn.xports.entity.BalanceInfo;
import cn.xports.qd2.entity.K;
import cn.xports.qdplugin.R;
import com.blankj.utilcode.util.StringUtils;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "dataMap", "Lcn/xports/baselib/bean/DataMap;", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 15})
/* compiled from: OrderPayActivity.kt */
final class OrderPayActivity$initData$2<T> implements Consumer<DataMap> {
    final /* synthetic */ OrderPayActivity this$0;

    OrderPayActivity$initData$2(OrderPayActivity orderPayActivity) {
        this.this$0 = orderPayActivity;
    }

    public final void accept(DataMap dataMap) {
        int i;
        if (dataMap.getBooleanValue("select")) {
            OrderPayActivity orderPayActivity = this.this$0;
            Intrinsics.checkExpressionValueIsNotNull(dataMap, "dataMap");
            orderPayActivity.coupon = dataMap;
            OrderPayActivity orderPayActivity2 = this.this$0;
            Integer intValue = dataMap.getIntValue("balance");
            Intrinsics.checkExpressionValueIsNotNull(intValue, "dataMap.getIntValue(\"balance\")");
            orderPayActivity2.couponMoney = intValue.intValue();
            OrderPayActivity orderPayActivity3 = this.this$0;
            String string = dataMap.getString(K.couponNo);
            Intrinsics.checkExpressionValueIsNotNull(string, "dataMap.getString(\"couponNo\")");
            orderPayActivity3.couponNo = string;
            this.this$0.setTotal(BalanceInfo.shouldPayParse(this.this$0.getCommonPay()));
            if (this.this$0.getSelectCard() != null) {
                BalanceInfo selectCard = this.this$0.getSelectCard();
                if (selectCard != null) {
                    selectCard.shouldCouponPay();
                    i = selectCard.getOrderDiscount();
                } else {
                    i = 0;
                }
                if (this.this$0.couponMoney >= this.this$0.getTotal()) {
                    this.this$0.couponMoney = this.this$0.getTotal();
                    this.this$0.cardPayMoney = 0;
                } else {
                    this.this$0.cardPayMoney = Math.max((this.this$0.getTotal() - this.this$0.couponMoney) - i, 0);
                }
                if (this.this$0.cardPayMoney <= 0 || i <= 0) {
                    LinearLayout linearLayout = (LinearLayout) this.this$0._$_findCachedViewById(R.id.crCardDiscount);
                    Intrinsics.checkExpressionValueIsNotNull(linearLayout, "crCardDiscount");
                    linearLayout.setVisibility(8);
                } else {
                    LinearLayout linearLayout2 = (LinearLayout) this.this$0._$_findCachedViewById(R.id.crCardDiscount);
                    Intrinsics.checkExpressionValueIsNotNull(linearLayout2, "crCardDiscount");
                    linearLayout2.setVisibility(0);
                    TextView textView = (TextView) this.this$0._$_findCachedViewById(R.id.tvCardDiscount);
                    Intrinsics.checkExpressionValueIsNotNull(textView, "tvCardDiscount");
                    textView.setText("折扣-" + MoneyUtil.cent2Yuan(i) + "元");
                }
                TextView textView2 = (TextView) this.this$0._$_findCachedViewById(R.id.tvCardPayMoney);
                Intrinsics.checkExpressionValueIsNotNull(textView2, "tvCardPayMoney");
                textView2.setText(this.this$0.getResources().getString(R.string.yuan) + MoneyUtil.cent2Yuan(this.this$0.cardPayMoney));
            } else if (this.this$0.couponMoney >= this.this$0.getTotal()) {
                this.this$0.couponMoney = this.this$0.getTotal();
            }
            TextView textView3 = (TextView) this.this$0._$_findCachedViewById(R.id.tvCouponMoney);
            Intrinsics.checkExpressionValueIsNotNull(textView3, "tvCouponMoney");
            textView3.setText(StringUtils.getString(R.string.yuan) + MoneyUtil.cent2Yuan(this.this$0.couponMoney));
        } else {
            TextView textView4 = (TextView) this.this$0._$_findCachedViewById(R.id.tvCouponMoney);
            Intrinsics.checkExpressionValueIsNotNull(textView4, "tvCouponMoney");
            textView4.setText("");
            this.this$0.couponNo = "";
            this.this$0.couponMoney = 0;
            this.this$0.setTotal(BalanceInfo.shouldPayParse(this.this$0.getCommonPay()));
        }
        this.this$0.setShouldPayText();
    }
}

package cn.xports.qd2;

import android.widget.TextView;
import cn.xports.baselib.bean.DataMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "dataMap", "Lcn/xports/baselib/bean/DataMap;", "invoke"}, k = 3, mv = {1, 1, 15})
/* compiled from: MyCenterActivity.kt */
final class MyCenterActivity$getUserCouponAndInfo$1 extends Lambda implements Function1<DataMap, Unit> {
    final /* synthetic */ MyCenterActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MyCenterActivity$getUserCouponAndInfo$1(MyCenterActivity myCenterActivity) {
        super(1);
        this.this$0 = myCenterActivity;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((DataMap) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(@NotNull DataMap dataMap) {
        Intrinsics.checkParameterIsNotNull(dataMap, "dataMap");
        MyCenterActivity myCenterActivity = this.this$0;
        Integer intValue = dataMap.getIntValue("cardNum");
        Intrinsics.checkExpressionValueIsNotNull(intValue, "dataMap.getIntValue(\"cardNum\")");
        myCenterActivity.cardNum = intValue.intValue();
        TextView textView = (TextView) this.this$0._$_findCachedViewById(R.id.tv_card_count);
        Intrinsics.checkExpressionValueIsNotNull(textView, "tv_card_count");
        textView.setText(dataMap.getString("cardNum"));
        TextView textView2 = (TextView) this.this$0._$_findCachedViewById(R.id.tv_coupon_count);
        Intrinsics.checkExpressionValueIsNotNull(textView2, "tv_coupon_count");
        textView2.setText(dataMap.getString("couponNum"));
        TextView textView3 = (TextView) this.this$0._$_findCachedViewById(R.id.tv_ticket_count);
        Intrinsics.checkExpressionValueIsNotNull(textView3, "tv_ticket_count");
        textView3.setText(dataMap.getString("ticketNum"));
    }
}

package cn.xports.qd2.credit;

import android.widget.LinearLayout;
import android.widget.TextView;
import cn.xports.baselib.bean.DataMap;
import cn.xports.qd2.R;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lcn/xports/baselib/bean/DataMap;", "invoke"}, k = 3, mv = {1, 1, 15})
/* compiled from: CreditStoreActivity.kt */
final class CreditStoreActivity$getCouponsList$1 extends Lambda implements Function1<DataMap, Unit> {
    final /* synthetic */ CreditStoreActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CreditStoreActivity$getCouponsList$1(CreditStoreActivity creditStoreActivity) {
        super(1);
        this.this$0 = creditStoreActivity;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((DataMap) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(@NotNull DataMap dataMap) {
        Intrinsics.checkParameterIsNotNull(dataMap, "it");
        ArrayList<DataMap> dataList = dataMap.getDataList("exchangeCoupons");
        if (dataList.isEmpty()) {
            TextView textView = (TextView) this.this$0._$_findCachedViewById(R.id.tvCouponTip);
            Intrinsics.checkExpressionValueIsNotNull(textView, "tvCouponTip");
            textView.setVisibility(0);
            return;
        }
        this.this$0.coupons.addAll(dataList);
        this.this$0.couponAdapter.notifyDataSetChanged();
        if (this.this$0.coupons.size() > 3) {
            LinearLayout linearLayout = (LinearLayout) this.this$0._$_findCachedViewById(R.id.llCouponShowMore);
            Intrinsics.checkExpressionValueIsNotNull(linearLayout, "llCouponShowMore");
            linearLayout.setVisibility(0);
        }
    }
}

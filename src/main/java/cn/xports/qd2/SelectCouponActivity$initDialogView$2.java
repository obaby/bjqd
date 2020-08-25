package cn.xports.qd2;

import cn.xports.baselib.adapter.XBaseAdapter;
import cn.xports.baselib.bean.DataMap;
import cn.xports.baselib.util.RxBus;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\n¢\u0006\u0002\b\b"}, d2 = {"<anonymous>", "", "item", "Lcn/xports/baselib/bean/DataMap;", "kotlin.jvm.PlatformType", "position", "", "viewId", "onItemClick"}, k = 3, mv = {1, 1, 15})
/* compiled from: SelectCouponActivity.kt */
final class SelectCouponActivity$initDialogView$2<T> implements XBaseAdapter.OnItemClickListener<DataMap> {
    final /* synthetic */ SelectCouponActivity this$0;

    SelectCouponActivity$initDialogView$2(SelectCouponActivity selectCouponActivity) {
        this.this$0 = selectCouponActivity;
    }

    public final void onItemClick(DataMap dataMap, int i, int i2) {
        SelectCouponActivity selectCouponActivity = this.this$0;
        Intrinsics.checkExpressionValueIsNotNull(dataMap, "item");
        selectCouponActivity.clickCoupon = dataMap;
        RxBus.get().post(this.this$0.clickCoupon);
        this.this$0.finish();
    }
}

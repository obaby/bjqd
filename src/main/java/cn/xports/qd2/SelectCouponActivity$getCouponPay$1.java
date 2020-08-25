package cn.xports.qd2;

import android.support.design.widget.TabLayout;
import cn.xports.baselib.bean.DataMap;
import cn.xports.baselib.http.ResponseDataMap;
import cn.xports.baselib.mvp.IView;
import cn.xports.qd2.entity.K;
import cn.xports.qd2.util.ViewExt;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"cn/xports/qd2/SelectCouponActivity$getCouponPay$1", "Lcn/xports/baselib/http/ResponseDataMap;", "onSuccess", "", "dataMap", "Lcn/xports/baselib/bean/DataMap;", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: SelectCouponActivity.kt */
public final class SelectCouponActivity$getCouponPay$1 extends ResponseDataMap {
    final /* synthetic */ SelectCouponActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SelectCouponActivity$getCouponPay$1(SelectCouponActivity selectCouponActivity, IView iView) {
        super(iView);
        this.this$0 = selectCouponActivity;
    }

    public void onSuccess(@NotNull DataMap dataMap) {
        Intrinsics.checkParameterIsNotNull(dataMap, "dataMap");
        ArrayList<DataMap> dataList = dataMap.getDataList(K.couponList);
        Intrinsics.checkExpressionValueIsNotNull(dataList, "dataMap.getDataList(\"couponList\")");
        for (DataMap dataMap2 : dataList) {
            this.this$0.canUseCoupons.add(dataMap2.set(K.couponState, 1));
        }
        ArrayList<DataMap> dataList2 = dataMap.getDataList("unUseCouponList");
        Intrinsics.checkExpressionValueIsNotNull(dataList2, "dataMap.getDataList(\"unUseCouponList\")");
        for (DataMap dataMap3 : dataList2) {
            this.this$0.cannotUseCoupons.add(dataMap3.set(K.couponState, 0));
        }
        ArrayList access$getTitles$p = this.this$0.titles;
        access$getTitles$p.add("可用优惠券(" + this.this$0.canUseCoupons.size() + ')');
        ArrayList access$getTitles$p2 = this.this$0.titles;
        access$getTitles$p2.add("不可用优惠券(" + this.this$0.cannotUseCoupons.size() + ')');
        TabLayout tabLayout = (TabLayout) this.this$0._$_findCachedViewById(R.id.tabLayout);
        Intrinsics.checkExpressionValueIsNotNull(tabLayout, "tabLayout");
        ViewExt.setTextColorSize(ViewExt.setUpTabLayout$default(ViewExt.setOnTabListener(tabLayout, this.this$0), this.this$0.titles, R.layout.item_tab_text, 0, 4, (Object) null), R.color.blue_2e6, 17.0f, R.color.gray_888, 14.0f);
    }
}

package cn.xports.qd2.coupons;

import cn.xports.baselib.bean.DataMap;
import cn.xports.baselib.http.ResponseDataMap;
import cn.xports.baselib.mvp.IView;
import cn.xports.qd2.entity.K;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0007"}, d2 = {"cn/xports/qd2/coupons/MyCouponsActivity$getMyCoupons$1", "Lcn/xports/baselib/http/ResponseDataMap;", "onError", "", "dataMap", "Lcn/xports/baselib/bean/DataMap;", "onSuccess", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: MyCouponsActivity.kt */
public final class MyCouponsActivity$getMyCoupons$1 extends ResponseDataMap {
    final /* synthetic */ int $pageNO;
    final /* synthetic */ MyCouponsActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MyCouponsActivity$getMyCoupons$1(MyCouponsActivity myCouponsActivity, int i, IView iView) {
        super(iView);
        this.this$0 = myCouponsActivity;
        this.$pageNO = i;
    }

    public void onSuccess(@NotNull DataMap dataMap) {
        Intrinsics.checkParameterIsNotNull(dataMap, "dataMap");
        this.this$0.pageNo = this.$pageNO;
        MyCouponsActivity myCouponsActivity = this.this$0;
        Integer intValue = dataMap.getIntValue("totalPage");
        Intrinsics.checkExpressionValueIsNotNull(intValue, "dataMap.getIntValue(\"totalPage\")");
        myCouponsActivity.totalPage = intValue.intValue();
        ArrayList<DataMap> dataList = dataMap.getDataList("couponEntityList");
        Intrinsics.checkExpressionValueIsNotNull(dataList, "dataMap.getDataList(\"couponEntityList\")");
        for (DataMap dataMap2 : dataList) {
            dataMap2.put(K.couponState, this.this$0.couponState);
            this.this$0.couponList.add(dataMap2);
        }
        this.this$0.adapter.notifyDataSetChanged();
    }

    public void onError(@NotNull DataMap dataMap) {
        Intrinsics.checkParameterIsNotNull(dataMap, "dataMap");
        this.this$0.adapter.notifyDataSetChanged();
    }
}

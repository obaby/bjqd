package cn.xports.qd2.coupons;

import android.content.Intent;
import cn.xports.baselib.adapter.XViewBinder;
import cn.xports.baselib.bean.DataMap;
import cn.xports.qd2.entity.K;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "item", "Lcn/xports/baselib/bean/DataMap;", "kotlin.jvm.PlatformType", "viewId", "", "onItemClick"}, k = 3, mv = {1, 1, 15})
/* compiled from: CouponListActivity.kt */
final class CouponListActivity$initView$1<T> implements XViewBinder.OnItemClickListener<DataMap> {
    final /* synthetic */ CouponListActivity this$0;

    CouponListActivity$initView$1(CouponListActivity couponListActivity) {
        this.this$0 = couponListActivity;
    }

    public final void onItemClick(DataMap dataMap, int i) {
        this.this$0.startActivity(new Intent(this.this$0, CouponGetActivity.class).putExtra(K.couponInfo, dataMap.toJson()).putExtra(K.campId, dataMap.getString(K.campaignId)).putExtra(K.venueId, dataMap.getString(K.venueId)).putExtra(K.couponId, dataMap.getString(K.couponId)));
    }
}

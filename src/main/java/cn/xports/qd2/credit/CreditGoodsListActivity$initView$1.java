package cn.xports.qd2.credit;

import android.content.Intent;
import android.os.Bundle;
import cn.xports.baselib.adapter.XBaseAdapter;
import cn.xports.baselib.bean.DataMap;
import cn.xports.qd2.util.ViewExt;
import com.alipay.sdk.packet.d;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\n¢\u0006\u0002\b\b"}, d2 = {"<anonymous>", "", "dataMap", "Lcn/xports/baselib/bean/DataMap;", "kotlin.jvm.PlatformType", "i", "", "i2", "onItemClick"}, k = 3, mv = {1, 1, 15})
/* compiled from: CreditGoodsListActivity.kt */
final class CreditGoodsListActivity$initView$1<T> implements XBaseAdapter.OnItemClickListener<DataMap> {
    final /* synthetic */ CreditGoodsListActivity this$0;

    CreditGoodsListActivity$initView$1(CreditGoodsListActivity creditGoodsListActivity) {
        this.this$0 = creditGoodsListActivity;
    }

    public final void onItemClick(DataMap dataMap, int i, int i2) {
        String string = dataMap.getString("id");
        Integer intValue = dataMap.getIntValue("objectType");
        CreditGoodsListActivity creditGoodsListActivity = this.this$0;
        Bundle bundle = new Bundle();
        Intrinsics.checkExpressionValueIsNotNull(intValue, d.p);
        Bundle bundle2 = ViewExt.set(bundle, "exchange_type", intValue);
        Intrinsics.checkExpressionValueIsNotNull(string, "exchangeId");
        creditGoodsListActivity.startActivity(new Intent(creditGoodsListActivity, CouponExchangeDetailActivity.class).putExtras(ViewExt.set(bundle2, "exchange_id", string)));
    }
}

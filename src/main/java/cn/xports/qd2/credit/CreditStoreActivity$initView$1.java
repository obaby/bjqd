package cn.xports.qd2.credit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import cn.xports.qd2.util.ViewExt;
import com.alipay.sdk.packet.d;
import com.blankj.utilcode.util.GsonUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 15})
/* compiled from: CreditStoreActivity.kt */
final class CreditStoreActivity$initView$1 implements View.OnClickListener {
    final /* synthetic */ CreditStoreActivity this$0;

    CreditStoreActivity$initView$1(CreditStoreActivity creditStoreActivity) {
        this.this$0 = creditStoreActivity;
    }

    public final void onClick(View view) {
        CreditStoreActivity creditStoreActivity = this.this$0;
        Bundle bundle = ViewExt.set(new Bundle(), d.p, 2);
        String json = GsonUtils.toJson(this.this$0.goods);
        Intrinsics.checkExpressionValueIsNotNull(json, "GsonUtils.toJson(goods)");
        creditStoreActivity.startActivity(new Intent(creditStoreActivity, CreditGoodsListActivity.class).putExtras(ViewExt.set(bundle, d.k, json)));
    }
}

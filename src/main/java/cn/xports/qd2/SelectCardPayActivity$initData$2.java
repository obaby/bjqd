package cn.xports.qd2;

import cn.xports.entity.BalanceInfo;
import cn.xports.qd2.adapter.CardSelectDetailBinder;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lcn/xports/entity/BalanceInfo;", "kotlin.jvm.PlatformType", "onItemSelect"}, k = 3, mv = {1, 1, 15})
/* compiled from: SelectCardPayActivity.kt */
final class SelectCardPayActivity$initData$2 implements CardSelectDetailBinder.OnSelectItem {
    final /* synthetic */ SelectCardPayActivity this$0;

    SelectCardPayActivity$initData$2(SelectCardPayActivity selectCardPayActivity) {
        this.this$0 = selectCardPayActivity;
    }

    public final void onItemSelect(BalanceInfo balanceInfo) {
        this.this$0.curBalanceInfo = balanceInfo;
    }
}

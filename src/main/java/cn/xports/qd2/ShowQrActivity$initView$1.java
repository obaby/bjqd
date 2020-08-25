package cn.xports.qd2;

import cn.xports.entity.CardInfo;
import cn.xports.qd2.adapter.CardQrSwitchAdapter;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "info", "Lcn/xports/entity/CardInfo;", "kotlin.jvm.PlatformType", "onItemClick"}, k = 3, mv = {1, 1, 15})
/* compiled from: ShowQrActivity.kt */
final class ShowQrActivity$initView$1 implements CardQrSwitchAdapter.OnItemClick {
    final /* synthetic */ ShowQrActivity this$0;

    ShowQrActivity$initView$1(ShowQrActivity showQrActivity) {
        this.this$0 = showQrActivity;
    }

    public final void onItemClick(CardInfo cardInfo) {
        this.this$0.mCard = cardInfo;
        this.this$0.setCard();
        this.this$0.countDown();
    }
}

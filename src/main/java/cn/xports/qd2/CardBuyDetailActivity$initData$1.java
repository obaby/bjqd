package cn.xports.qd2;

import cn.xports.entity.CardInfo;
import cn.xports.entity.PairEvent;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "t", "Lcn/xports/entity/PairEvent;", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 15})
/* compiled from: CardBuyDetailActivity.kt */
final class CardBuyDetailActivity$initData$1<T> implements Consumer<PairEvent> {
    final /* synthetic */ CardBuyDetailActivity this$0;

    CardBuyDetailActivity$initData$1(CardBuyDetailActivity cardBuyDetailActivity) {
        this.this$0 = cardBuyDetailActivity;
    }

    public final void accept(PairEvent pairEvent) {
        this.this$0.fromPayBottom = true;
        if (pairEvent != null && Intrinsics.areEqual(pairEvent.getKey1(), this.this$0.getTAG())) {
            CardBuyDetailActivity cardBuyDetailActivity = this.this$0;
            Object key2 = pairEvent.getKey2();
            if (key2 != null) {
                cardBuyDetailActivity.selectCard = (CardInfo) key2;
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type cn.xports.entity.CardInfo");
        }
    }
}

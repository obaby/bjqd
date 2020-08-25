package cn.xports.qd2;

import android.view.View;
import cn.xports.entity.CardInfo;
import cn.xports.qd2.SelectBottomActivity;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 15})
/* compiled from: CardBuyDetailActivity.kt */
final class CardBuyDetailActivity$initView$2 implements View.OnClickListener {
    final /* synthetic */ CardBuyDetailActivity this$0;

    CardBuyDetailActivity$initView$2(CardBuyDetailActivity cardBuyDetailActivity) {
        this.this$0 = cardBuyDetailActivity;
    }

    public final void onClick(View view) {
        if (this.this$0.selectCard == null) {
            List access$getCardList$p = this.this$0.cardList;
            if (access$getCardList$p == null || !(!access$getCardList$p.isEmpty())) {
                this.this$0.createOrder();
                return;
            }
            List access$getCardList$p2 = this.this$0.cardList;
            if (access$getCardList$p2 == null) {
                return;
            }
            if (access$getCardList$p2.size() == 1) {
                this.this$0.selectCard = (CardInfo) access$getCardList$p2.get(0);
                this.this$0.createOrder();
                return;
            }
            SelectBottomActivity.Companion companion = SelectBottomActivity.Companion;
            String tag = this.this$0.getTAG();
            Intrinsics.checkExpressionValueIsNotNull(tag, "TAG");
            companion.start(tag, access$getCardList$p2);
            return;
        }
        this.this$0.createOrder();
    }
}

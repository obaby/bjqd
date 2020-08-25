package cn.xports.qd2;

import android.view.View;
import android.widget.TextView;
import cn.xports.qd2.entity.CardProductResult;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005¨\u0006\u0006"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick", "cn/xports/qd2/CardBuyDetailActivity$getCardDetail$1$next$1$3"}, k = 3, mv = {1, 1, 15})
/* compiled from: CardBuyDetailActivity.kt */
final class CardBuyDetailActivity$getCardDetail$1$next$$inlined$apply$lambda$1 implements View.OnClickListener {
    final /* synthetic */ CardProductResult $this_apply;
    final /* synthetic */ CardBuyDetailActivity$getCardDetail$1 this$0;

    CardBuyDetailActivity$getCardDetail$1$next$$inlined$apply$lambda$1(CardProductResult cardProductResult, CardBuyDetailActivity$getCardDetail$1 cardBuyDetailActivity$getCardDetail$1) {
        this.$this_apply = cardProductResult;
        this.this$0 = cardBuyDetailActivity$getCardDetail$1;
    }

    public final void onClick(View view) {
        TextView textView = (TextView) this.this$0.this$0._$_findCachedViewById(R.id.tvCampOperation);
        Intrinsics.checkExpressionValueIsNotNull(textView, "tvCampOperation");
        if (Intrinsics.areEqual(textView.getText().toString(), "查看更多")) {
            TextView textView2 = (TextView) this.this$0.this$0._$_findCachedViewById(R.id.tvCampOperation);
            Intrinsics.checkExpressionValueIsNotNull(textView2, "tvCampOperation");
            textView2.setText("收起");
            this.this$0.this$0.promotionList.clear();
            this.this$0.this$0.promotionList.addAll(this.$this_apply.getSpecialCardPromotionList());
        } else {
            TextView textView3 = (TextView) this.this$0.this$0._$_findCachedViewById(R.id.tvCampOperation);
            Intrinsics.checkExpressionValueIsNotNull(textView3, "tvCampOperation");
            textView3.setText("查看更多");
            this.this$0.this$0.promotionList.clear();
            this.this$0.this$0.promotionList.addAll(this.$this_apply.getSpecialCardPromotionList().subList(0, 3));
        }
        this.this$0.this$0.adapter.notifyDataSetChanged();
    }
}

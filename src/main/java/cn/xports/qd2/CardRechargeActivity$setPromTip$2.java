package cn.xports.qd2;

import android.view.View;
import cn.xports.base.GlobalDialog;
import kotlin.Metadata;
import kotlin.jvm.internal.Ref;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 15})
/* compiled from: CardRechargeActivity.kt */
final class CardRechargeActivity$setPromTip$2 implements View.OnClickListener {
    final /* synthetic */ Ref.ObjectRef $tip;
    final /* synthetic */ CardRechargeActivity this$0;

    CardRechargeActivity$setPromTip$2(CardRechargeActivity cardRechargeActivity, Ref.ObjectRef objectRef) {
        this.this$0 = cardRechargeActivity;
        this.$tip = objectRef;
    }

    public final void onClick(View view) {
        if (!(((String) this.$tip.element).length() == 0)) {
            new GlobalDialog(this.this$0, (String) this.$tip.element, "优惠信息").setCancelVisible(false);
        }
    }
}

package cn.xports.qd2;

import android.view.View;
import cn.xports.base.GlobalDialog;
import cn.xports.qd2.entity.K;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 15})
/* compiled from: CardInfoActivity.kt */
final class CardInfoActivity$initView$1 implements View.OnClickListener {
    final /* synthetic */ CardInfoActivity this$0;

    CardInfoActivity$initView$1(CardInfoActivity cardInfoActivity) {
        this.this$0 = cardInfoActivity;
    }

    public final void onClick(View view) {
        new GlobalDialog(this.this$0, "是否确定解除卡绑定？").setButtonClick(new GlobalDialog.OnButtonClick(this) {
            final /* synthetic */ CardInfoActivity$initView$1 this$0;

            {
                this.this$0 = r1;
            }

            public final void onClick(int i) {
                if (i == 1) {
                    CardInfoActivity cardInfoActivity = this.this$0.this$0;
                    String stringExtra = this.this$0.this$0.getIntent().getStringExtra(K.ecardNo);
                    Intrinsics.checkExpressionValueIsNotNull(stringExtra, "intent.getStringExtra(\"ecardNo\")");
                    cardInfoActivity.ubind(stringExtra);
                }
            }
        });
    }
}

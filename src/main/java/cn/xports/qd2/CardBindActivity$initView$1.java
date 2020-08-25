package cn.xports.qd2;

import android.view.View;
import android.widget.EditText;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 15})
/* compiled from: CardBindActivity.kt */
final class CardBindActivity$initView$1 implements View.OnClickListener {
    final /* synthetic */ CardBindActivity this$0;

    CardBindActivity$initView$1(CardBindActivity cardBindActivity) {
        this.this$0 = cardBindActivity;
    }

    public final void onClick(View view) {
        EditText editText = (EditText) this.this$0._$_findCachedViewById(R.id.etCardNo);
        Intrinsics.checkExpressionValueIsNotNull(editText, "etCardNo");
        CharSequence text = editText.getText();
        if (!(text == null || text.length() == 0)) {
            CardBindActivity cardBindActivity = this.this$0;
            EditText editText2 = (EditText) this.this$0._$_findCachedViewById(R.id.etCardNo);
            Intrinsics.checkExpressionValueIsNotNull(editText2, "etCardNo");
            cardBindActivity.getCode(editText2.getText().toString());
        }
    }
}

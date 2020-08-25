package cn.xports.qd2;

import android.widget.Button;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 15})
/* compiled from: CardBindActivity.kt */
final class CardBindActivity$initView$4<T> implements Consumer<CharSequence> {
    final /* synthetic */ CardBindActivity this$0;

    CardBindActivity$initView$4(CardBindActivity cardBindActivity) {
        this.this$0 = cardBindActivity;
    }

    public final void accept(CharSequence charSequence) {
        Button button = (Button) this.this$0._$_findCachedViewById(R.id.btnBind);
        Intrinsics.checkExpressionValueIsNotNull(button, "btnBind");
        button.setEnabled(this.this$0.btnEnable());
    }
}

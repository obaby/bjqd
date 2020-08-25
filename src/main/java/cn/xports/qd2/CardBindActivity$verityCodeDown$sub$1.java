package cn.xports.qd2;

import android.widget.TextView;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "longTime", "", "kotlin.jvm.PlatformType", "accept", "(Ljava/lang/Long;)V"}, k = 3, mv = {1, 1, 15})
/* compiled from: CardBindActivity.kt */
final class CardBindActivity$verityCodeDown$sub$1<T> implements Consumer<Long> {
    final /* synthetic */ CardBindActivity this$0;

    CardBindActivity$verityCodeDown$sub$1(CardBindActivity cardBindActivity) {
        this.this$0 = cardBindActivity;
    }

    public final void accept(Long l) {
        Intrinsics.checkExpressionValueIsNotNull(l, "longTime");
        int longValue = (int) (((long) 60) - l.longValue());
        if (longValue == 0) {
            TextView textView = (TextView) this.this$0._$_findCachedViewById(R.id.tvGetCode);
            Intrinsics.checkExpressionValueIsNotNull(textView, "tvGetCode");
            textView.setText("重新获取");
            TextView textView2 = (TextView) this.this$0._$_findCachedViewById(R.id.tvGetCode);
            Intrinsics.checkExpressionValueIsNotNull(textView2, "tvGetCode");
            textView2.setEnabled(true);
            return;
        }
        TextView textView3 = (TextView) this.this$0._$_findCachedViewById(R.id.tvGetCode);
        Intrinsics.checkExpressionValueIsNotNull(textView3, "tvGetCode");
        StringBuilder sb = new StringBuilder();
        sb.append(longValue);
        sb.append('s');
        textView3.setText(sb.toString());
        TextView textView4 = (TextView) this.this$0._$_findCachedViewById(R.id.tvGetCode);
        Intrinsics.checkExpressionValueIsNotNull(textView4, "tvGetCode");
        textView4.setEnabled(false);
    }
}

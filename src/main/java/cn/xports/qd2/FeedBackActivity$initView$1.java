package cn.xports.qd2;

import android.widget.TextView;
import cn.xports.baselib.rxbind.widget.TextViewTextChangeEvent;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lcn/xports/baselib/rxbind/widget/TextViewTextChangeEvent;", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 15})
/* compiled from: FeedBackActivity.kt */
final class FeedBackActivity$initView$1<T> implements Consumer<TextViewTextChangeEvent> {
    final /* synthetic */ FeedBackActivity this$0;

    FeedBackActivity$initView$1(FeedBackActivity feedBackActivity) {
        this.this$0 = feedBackActivity;
    }

    public final void accept(TextViewTextChangeEvent textViewTextChangeEvent) {
        if (StringsKt.trim(textViewTextChangeEvent.getText()).length() > 0) {
            TextView textView = (TextView) this.this$0._$_findCachedViewById(R.id.tvSubmitComment);
            Intrinsics.checkExpressionValueIsNotNull(textView, "tvSubmitComment");
            textView.setEnabled(true);
            ((TextView) this.this$0._$_findCachedViewById(R.id.tvSubmitComment)).setBackgroundResource(R.drawable.blue_round);
            return;
        }
        TextView textView2 = (TextView) this.this$0._$_findCachedViewById(R.id.tvSubmitComment);
        Intrinsics.checkExpressionValueIsNotNull(textView2, "tvSubmitComment");
        textView2.setEnabled(false);
        ((TextView) this.this$0._$_findCachedViewById(R.id.tvSubmitComment)).setBackgroundResource(R.drawable.gray_round_f4f);
    }
}

package cn.xports.ticket;

import android.text.TextUtils;
import android.widget.CompoundButton;
import android.widget.TextView;
import cn.xports.base.Constant;
import cn.xports.qdplugin.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "buttonView", "Landroid/widget/CompoundButton;", "kotlin.jvm.PlatformType", "isChecked", "", "onCheckedChanged"}, k = 3, mv = {1, 1, 15})
/* compiled from: TicketDetailActivity.kt */
final class TicketDetailActivity$initView$1 implements CompoundButton.OnCheckedChangeListener {
    final /* synthetic */ TicketDetailActivity this$0;

    TicketDetailActivity$initView$1(TicketDetailActivity ticketDetailActivity) {
        this.this$0 = ticketDetailActivity;
    }

    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (z) {
            TextView textView = (TextView) this.this$0._$_findCachedViewById(R.id.tvSubmit);
            Intrinsics.checkExpressionValueIsNotNull(textView, "tvSubmit");
            textView.setEnabled(!TextUtils.isEmpty(Constant.PAYMODES));
            return;
        }
        TextView textView2 = (TextView) this.this$0._$_findCachedViewById(R.id.tvSubmit);
        Intrinsics.checkExpressionValueIsNotNull(textView2, "tvSubmit");
        textView2.setEnabled(false);
    }
}

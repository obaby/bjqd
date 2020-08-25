package cn.xports.ticket;

import android.widget.TextView;
import cn.xports.baselib.util.MoneyUtil;
import cn.xports.entity.TicketType;
import cn.xports.parser.TicketDetailParser;
import cn.xports.qdplugin.R;
import cn.xports.widget.AddLessView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006¸\u0006\u0000"}, d2 = {"cn/xports/ticket/TicketDetailActivity$onGetTicketInfo$1$1", "Lcn/xports/widget/AddLessView$OnValueListener;", "onValueChange", "", "value", "", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: TicketDetailActivity.kt */
public final class TicketDetailActivity$onGetTicketInfo$$inlined$apply$lambda$1 implements AddLessView.OnValueListener {
    final /* synthetic */ TicketDetailParser $this_apply;
    final /* synthetic */ TicketDetailActivity this$0;

    TicketDetailActivity$onGetTicketInfo$$inlined$apply$lambda$1(TicketDetailParser ticketDetailParser, TicketDetailActivity ticketDetailActivity) {
        this.$this_apply = ticketDetailParser;
        this.this$0 = ticketDetailActivity;
    }

    public void onValueChange(int i) {
        TextView textView = (TextView) this.this$0._$_findCachedViewById(R.id.tvTotalMoney);
        Intrinsics.checkExpressionValueIsNotNull(textView, "tvTotalMoney");
        StringBuilder sb = new StringBuilder();
        sb.append("¥");
        TicketType ticketDetails = this.$this_apply.getTicketDetails();
        Intrinsics.checkExpressionValueIsNotNull(ticketDetails, "ticketDetails");
        sb.append(MoneyUtil.cent2Yuan(i * ticketDetails.getPrice()));
        textView.setText(sb.toString());
    }
}

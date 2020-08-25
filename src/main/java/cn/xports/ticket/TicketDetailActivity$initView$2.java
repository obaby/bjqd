package cn.xports.ticket;

import android.view.View;
import cn.xports.baselib.util.DateUtil;
import cn.xports.qdplugin.R;
import cn.xports.widget.AddLessView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 15})
/* compiled from: TicketDetailActivity.kt */
final class TicketDetailActivity$initView$2 implements View.OnClickListener {
    final /* synthetic */ TicketDetailActivity this$0;

    TicketDetailActivity$initView$2(TicketDetailActivity ticketDetailActivity) {
        this.this$0 = ticketDetailActivity;
    }

    public final void onClick(View view) {
        if (((AddLessView) this.this$0._$_findCachedViewById(R.id.addLessView)).getValue() == 0) {
            this.this$0.showMsg("请选择数量");
            return;
        }
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("ticketTypeId", this.this$0.getTicketTypeId());
        jSONObject.put("ticketTimeId", this.this$0.getTimeId());
        jSONObject.put("num", ((AddLessView) this.this$0._$_findCachedViewById(R.id.addLessView)).getValue());
        jSONObject.put("ticketPrice", this.this$0.getTicket().getPrice());
        jSONObject.put("cashPledge", this.this$0.getTicket().getCashPledge());
        jSONArray.put(jSONObject);
        TicketDetailPresenter ticketDetailPresenter = (TicketDetailPresenter) this.this$0.getPresenter();
        if (ticketDetailPresenter != null) {
            String jSONArray2 = jSONArray.toString();
            Intrinsics.checkExpressionValueIsNotNull(jSONArray2, "array.toString()");
            String dateFormat = DateUtil.dateFormat("yyyyMMdd");
            String venueId = this.this$0.getTicket().getVenueId();
            Intrinsics.checkExpressionValueIsNotNull(venueId, "ticket.venueId");
            String serviceId = this.this$0.getTicket().getServiceId();
            Intrinsics.checkExpressionValueIsNotNull(serviceId, "ticket.serviceId");
            ticketDetailPresenter.ticketOrder(jSONArray2, dateFormat, venueId, serviceId);
        }
    }
}

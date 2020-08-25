package cn.xports.order;

import cn.xports.baselib.mvp.BasePresenter;
import cn.xports.entity.TradeTicket;
import cn.xports.order.OrderContract;
import cn.xports.qd2.entity.K;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0014\u0010\u000b\u001a\u00020\b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rJ\u000e\u0010\u000f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0016\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\n¨\u0006\u0014"}, d2 = {"Lcn/xports/order/OrderPresenter;", "Lcn/xports/baselib/mvp/BasePresenter;", "Lcn/xports/order/OrderContract$Model;", "Lcn/xports/order/OrderContract$View;", "model", "view", "(Lcn/xports/order/OrderContract$Model;Lcn/xports/order/OrderContract$View;)V", "cancelPay", "", "tradeId", "", "cancelTickets", "tickets", "", "Lcn/xports/entity/TradeTicket;", "getOrderDetail", "getOrderList", "page", "", "state", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: OrderPresenter.kt */
public final class OrderPresenter extends BasePresenter<OrderContract.Model, OrderContract.View> {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public OrderPresenter(@NotNull OrderContract.Model model, @NotNull OrderContract.View view) {
        super(model, view);
        Intrinsics.checkParameterIsNotNull(model, "model");
        Intrinsics.checkParameterIsNotNull(view, "view");
    }

    public final void getOrderList(int i, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, K.state);
        OrderContract.View view = (OrderContract.View) getRootView();
        if (view != null) {
            view.hideNoData();
        }
        OrderContract.Model model = (OrderContract.Model) getModel();
        if (model != null) {
            OrderContract.View view2 = (OrderContract.View) getRootView();
            if (view2 != null) {
                view2.showLoading();
            }
            model.getOrderList(i, str).subscribe(new OrderPresenter$getOrderList$$inlined$apply$lambda$1(getTAG(), this, i, str));
        }
    }

    public final void getOrderDetail(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "tradeId");
        OrderContract.View view = (OrderContract.View) getRootView();
        if (view != null) {
            view.hideNoData();
        }
        OrderContract.Model model = (OrderContract.Model) getModel();
        if (model != null) {
            OrderContract.View view2 = (OrderContract.View) getRootView();
            if (view2 != null) {
                view2.showLoading();
            }
            model.getOrderDetail(str).subscribe(new OrderPresenter$getOrderDetail$$inlined$apply$lambda$1(getTAG(), this, str));
        }
    }

    public final void cancelPay(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "tradeId");
        OrderContract.Model model = (OrderContract.Model) getModel();
        if (model != null) {
            OrderContract.View view = (OrderContract.View) getRootView();
            if (view != null) {
                view.showLoading();
            }
            model.cancelPay(str).subscribe(new OrderPresenter$cancelPay$$inlined$apply$lambda$1(getTAG(), this, str));
        }
    }

    public final void cancelTickets(@NotNull List<? extends TradeTicket> list) {
        Intrinsics.checkParameterIsNotNull(list, "tickets");
        if (!list.isEmpty()) {
            JSONArray jSONArray = new JSONArray();
            for (TradeTicket tradeTicket : list) {
                JSONObject jSONObject = new JSONObject();
                Long ticketId = tradeTicket.getTicketId();
                Intrinsics.checkExpressionValueIsNotNull(ticketId, "ticket.ticketId");
                jSONObject.put("ticketId", ticketId.longValue());
                jSONObject.put("backFee", tradeTicket.getBackFee());
                jSONArray.put(jSONObject);
            }
            OrderContract.Model model = (OrderContract.Model) getModel();
            if (model != null) {
                OrderContract.View view = (OrderContract.View) getRootView();
                if (view != null) {
                    view.showLoading();
                }
                String jSONArray2 = jSONArray.toString();
                Intrinsics.checkExpressionValueIsNotNull(jSONArray2, "array.toString()");
                model.cancelTickets(jSONArray2).subscribe(new OrderPresenter$cancelTickets$$inlined$apply$lambda$1(getTAG(), this, jSONArray));
            }
        }
    }
}

package cn.xports.ticket;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import cn.xports.base.BaseBussActivity;
import cn.xports.entity.TradeTicket;
import cn.xports.qdplugin.R;
import cn.xports.ticket.VenueTicketContract;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u000f\u001a\u00020\u0010H\u0014J\b\u0010\u0011\u001a\u00020\u0012H\u0014J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0014H\u0014J\u0018\u0010\u0016\u001a\u00020\u00142\u000e\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u0018H\u0016R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR!\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\nj\b\u0012\u0004\u0012\u00020\u000b`\f¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0019"}, d2 = {"Lcn/xports/ticket/VenueTicketActivity;", "Lcn/xports/base/BaseBussActivity;", "Lcn/xports/ticket/VenueTicketPresenter;", "Lcn/xports/ticket/VenueTicketContract$View;", "()V", "adapter", "Lcn/xports/ticket/OrderTicketAdapter;", "getAdapter", "()Lcn/xports/ticket/OrderTicketAdapter;", "tickets", "Ljava/util/ArrayList;", "Lcn/xports/entity/TradeTicket;", "Lkotlin/collections/ArrayList;", "getTickets", "()Ljava/util/ArrayList;", "getChildTitle", "", "getLayoutId", "", "initData", "", "initView", "showTickets", "ticketList", "", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: VenueTicketActivity.kt */
public final class VenueTicketActivity extends BaseBussActivity<VenueTicketPresenter> implements VenueTicketContract.View {
    private HashMap _$_findViewCache;
    @NotNull
    private final OrderTicketAdapter adapter = new OrderTicketAdapter(this.tickets);
    @NotNull
    private final ArrayList<TradeTicket> tickets = new ArrayList<>();

    public void _$_clearFindViewByIdCache() {
        if (this._$_findViewCache != null) {
            this._$_findViewCache.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    /* access modifiers changed from: protected */
    @NotNull
    public String getChildTitle() {
        return "入馆票";
    }

    public void showTickets(@Nullable List<? extends TradeTicket> list) {
        this.tickets.clear();
        if (list != null) {
            this.tickets.addAll(list);
            this.adapter.notifyDataSetChanged();
        }
        if (this.tickets.size() == 0) {
            showNoData("暂无入馆票");
        }
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.activity_venue_ticket;
    }

    /* access modifiers changed from: protected */
    public void initView() {
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.rvVenueTickets);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "rvVenueTickets");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(R.id.rvVenueTickets);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView2, "rvVenueTickets");
        recyclerView2.setAdapter(this.adapter);
        VenueTicketPresenter venueTicketPresenter = (VenueTicketPresenter) getPresenter();
        if (venueTicketPresenter != null) {
            venueTicketPresenter.getTickets();
        }
    }

    public void initData() {
        setPresenter(new VenueTicketPresenter(new VenueTicketModel(), this));
    }

    @NotNull
    public final ArrayList<TradeTicket> getTickets() {
        return this.tickets;
    }

    @NotNull
    public final OrderTicketAdapter getAdapter() {
        return this.adapter;
    }
}

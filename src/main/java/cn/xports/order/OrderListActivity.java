package cn.xports.order;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import cn.xports.base.BaseBussActivity;
import cn.xports.baselib.util.RxBus;
import cn.xports.entity.OrderInfo;
import cn.xports.entity.OrderPageInfo;
import cn.xports.order.OrderContract;
import cn.xports.parser.OrderInfoParser;
import cn.xports.parser.OrderListParser;
import cn.xports.qd2.entity.K;
import cn.xports.qdplugin.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0012\u001a\u00020\fH\u0014J\b\u0010\u0013\u001a\u00020\nH\u0014J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0015H\u0014J\u0010\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\fH\u0016J\b\u0010\u0019\u001a\u00020\u0015H\u0002J\u0012\u0010\u001a\u001a\u00020\u00152\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016J\u0012\u0010\u001d\u001a\u00020\u00152\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u000f0\u000ej\b\u0012\u0004\u0012\u00020\u000f`\u0010X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcn/xports/order/OrderListActivity;", "Lcn/xports/base/BaseBussActivity;", "Lcn/xports/order/OrderPresenter;", "Lcn/xports/order/OrderContract$View;", "()V", "adapter", "Lcn/xports/order/OrderListAdapter;", "isRefresh", "", "mNextPage", "", "orderState", "", "orders", "Ljava/util/ArrayList;", "Lcn/xports/entity/OrderInfo;", "Lkotlin/collections/ArrayList;", "pageNo", "getChildTitle", "getLayoutId", "initData", "", "initView", "onCancelOrder", "msg", "refresh", "showOrderDetail", "infoParser", "Lcn/xports/parser/OrderInfoParser;", "showOrderList", "listParser", "Lcn/xports/parser/OrderListParser;", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: OrderListActivity.kt */
public final class OrderListActivity extends BaseBussActivity<OrderPresenter> implements OrderContract.View {
    private HashMap _$_findViewCache;
    private final OrderListAdapter adapter = new OrderListAdapter(this.orders);
    private boolean isRefresh;
    /* access modifiers changed from: private */
    public int mNextPage = 1;
    /* access modifiers changed from: private */
    public String orderState = K.k0;
    private ArrayList<OrderInfo> orders = new ArrayList<>();
    /* access modifiers changed from: private */
    public int pageNo = 1;

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
        return "我的订单";
    }

    public void showOrderDetail(@Nullable OrderInfoParser orderInfoParser) {
    }

    public void onCancelOrder(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "msg");
        if (StringsKt.contains$default(str, "成功", false, 2, (Object) null)) {
            refresh();
        }
        showMsg(str);
    }

    public void showOrderList(@Nullable OrderListParser orderListParser) {
        OrderPageInfo pageInfo;
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) _$_findCachedViewById(R.id.refreshLayout);
        Intrinsics.checkExpressionValueIsNotNull(swipeRefreshLayout, "refreshLayout");
        if (swipeRefreshLayout.isRefreshing()) {
            SwipeRefreshLayout swipeRefreshLayout2 = (SwipeRefreshLayout) _$_findCachedViewById(R.id.refreshLayout);
            Intrinsics.checkExpressionValueIsNotNull(swipeRefreshLayout2, "refreshLayout");
            swipeRefreshLayout2.setRefreshing(false);
        }
        if (this.isRefresh) {
            this.isRefresh = false;
            this.orders.clear();
        }
        if (!(orderListParser == null || (pageInfo = orderListParser.getPageInfo()) == null)) {
            List<OrderInfo> list = pageInfo.getList();
            Intrinsics.checkExpressionValueIsNotNull(list, "list");
            for (OrderInfo orderInfo : list) {
                Intrinsics.checkExpressionValueIsNotNull(orderInfo, "it");
                orderInfo.setSysdate(orderListParser.getSysdate());
            }
            this.mNextPage = pageInfo.getNextPage();
            this.orders.addAll(pageInfo.getList());
            this.adapter.notifyDataSetChanged();
            if (this.orders.size() == 0) {
                showNoData("暂无订单信息");
            }
            if (pageInfo != null) {
                return;
            }
        }
        showNoData("暂无订单信息");
        Unit unit = Unit.INSTANCE;
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.activity_order_list;
    }

    /* access modifiers changed from: protected */
    public void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.rvMyOrder);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "rvMyOrder");
        recyclerView.setLayoutManager(linearLayoutManager);
        RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(R.id.rvMyOrder);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView2, "rvMyOrder");
        recyclerView2.setAdapter(this.adapter);
        ((RecyclerView) _$_findCachedViewById(R.id.rvMyOrder)).addOnScrollListener(new OrderListActivity$initView$1(this, linearLayoutManager));
        this.adapter.setPayOption(new OrderListActivity$initView$2(this));
        ((TextView) _$_findCachedViewById(R.id.tvAll)).setOnClickListener(new OrderListActivity$initView$3(this));
        ((TextView) _$_findCachedViewById(R.id.tvToPay)).setOnClickListener(new OrderListActivity$initView$4(this));
        OrderPresenter orderPresenter = (OrderPresenter) getPresenter();
        if (orderPresenter != null) {
            orderPresenter.getOrderList(this.pageNo, this.orderState);
        }
        RxBus.get().toFlowable(String.class).subscribe(new OrderListActivity$initView$5(this));
        ((SwipeRefreshLayout) _$_findCachedViewById(R.id.refreshLayout)).setOnRefreshListener(new OrderListActivity$initView$6(this));
    }

    /* access modifiers changed from: private */
    public final void refresh() {
        this.isRefresh = true;
        this.pageNo = 1;
        OrderPresenter orderPresenter = (OrderPresenter) getPresenter();
        if (orderPresenter != null) {
            orderPresenter.getOrderList(this.pageNo, this.orderState);
        }
    }

    public void initData() {
        setPresenter(new OrderPresenter(new OrderModel(), this));
    }
}

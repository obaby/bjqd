package cn.xports.order;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import cn.xports.base.BaseBussActivity;
import cn.xports.order.OrderContract;
import cn.xports.parser.OrderListParser;
import cn.xports.qd2.entity.K;
import cn.xports.qdplugin.R;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u000b\u001a\u00020\nH\u0014J\b\u0010\f\u001a\u00020\rH\u0014J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u000fH\u0014J\u0010\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\nH\u0016J\b\u0010\u0013\u001a\u00020\u000fH\u0014J\u0012\u0010\u0014\u001a\u00020\u000f2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J\u0012\u0010\u0017\u001a\u00020\u000f2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016R\u001e\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcn/xports/order/OrderDetailActivity;", "Lcn/xports/base/BaseBussActivity;", "Lcn/xports/order/OrderPresenter;", "Lcn/xports/order/OrderContract$View;", "()V", "orderItems", "Ljava/util/ArrayList;", "Lcn/xports/order/ItemOrderWrap;", "Lkotlin/collections/ArrayList;", "tradeId", "", "getChildTitle", "getLayoutId", "", "initData", "", "initView", "onCancelOrder", "msg", "onResume", "showOrderDetail", "infoParser", "Lcn/xports/parser/OrderInfoParser;", "showOrderList", "listParser", "Lcn/xports/parser/OrderListParser;", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: OrderDetailActivity.kt */
public final class OrderDetailActivity extends BaseBussActivity<OrderPresenter> implements OrderContract.View {
    private HashMap _$_findViewCache;
    /* access modifiers changed from: private */
    public final ArrayList<ItemOrderWrap> orderItems = new ArrayList<>();
    /* access modifiers changed from: private */
    public String tradeId = K.k0;

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
        return "订单详情";
    }

    public void showOrderList(@Nullable OrderListParser orderListParser) {
    }

    public void onCancelOrder(@NotNull String str) {
        OrderPresenter orderPresenter;
        Intrinsics.checkParameterIsNotNull(str, "msg");
        if (StringsKt.contains$default(str, "成功", false, 2, (Object) null) && (orderPresenter = (OrderPresenter) getPresenter()) != null) {
            orderPresenter.getOrderDetail(this.tradeId);
        }
        showMsg(str);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        OrderPresenter orderPresenter = (OrderPresenter) getPresenter();
        if (orderPresenter != null) {
            orderPresenter.getOrderDetail(this.tradeId);
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void showOrderDetail(@org.jetbrains.annotations.Nullable cn.xports.parser.OrderInfoParser r8) {
        /*
            r7 = this;
            if (r8 == 0) goto L_0x033c
            cn.xports.entity.OrderInfo r0 = r8.getTrade()
            java.lang.String r1 = "trade"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            java.lang.String r1 = r8.getSysdate()
            r0.setSysdate(r1)
            cn.xports.entity.OrderInfo r0 = r8.getTrade()
            java.lang.String r1 = "trade"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            java.lang.String r0 = r0.getTradeId()
            java.lang.String r1 = "trade.tradeId"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            r7.tradeId = r0
            int r0 = cn.xports.qdplugin.R.id.tvOrderTitle
            android.view.View r0 = r7._$_findCachedViewById(r0)
            android.widget.TextView r0 = (android.widget.TextView) r0
            java.lang.String r1 = "tvOrderTitle"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            java.lang.String r1 = r8.getTitle()
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            r0.setText(r1)
            int r0 = cn.xports.qdplugin.R.id.tvOrderMoney
            android.view.View r0 = r7._$_findCachedViewById(r0)
            android.widget.TextView r0 = (android.widget.TextView) r0
            java.lang.String r1 = "tvOrderMoney"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "¥"
            r1.append(r2)
            int r2 = r8.getTotalPayMoney()
            java.lang.String r2 = cn.xports.baselib.util.MoneyUtil.cent2Yuan((int) r2)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            r0.setText(r1)
            int r0 = cn.xports.qdplugin.R.id.tvPayMoney
            android.view.View r0 = r7._$_findCachedViewById(r0)
            android.widget.TextView r0 = (android.widget.TextView) r0
            java.lang.String r1 = "tvPayMoney"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "¥"
            r1.append(r2)
            int r2 = r8.getTotalPayMoney()
            java.lang.String r2 = cn.xports.baselib.util.MoneyUtil.cent2Yuan((int) r2)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            r0.setText(r1)
            cn.xports.entity.OrderInfo r0 = r8.getTrade()
            java.lang.String r1 = "trade"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            java.lang.String r0 = r0.getVenueName()
            cn.xports.entity.ProductRes r1 = r8.getProductRes()
            if (r1 == 0) goto L_0x00c6
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "有效期："
            r0.append(r1)
            cn.xports.entity.ProductRes r1 = r8.getProductRes()
            java.lang.String r2 = "productRes"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r2)
            java.lang.String r1 = r1.getLimitInfo()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
        L_0x00c6:
            int r1 = cn.xports.qdplugin.R.id.tvVenueName
            android.view.View r1 = r7._$_findCachedViewById(r1)
            android.widget.TextView r1 = (android.widget.TextView) r1
            java.lang.String r2 = "tvVenueName"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r2)
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            r1.setText(r0)
            cn.xports.entity.OrderInfo r0 = r8.getTrade()
            r1 = 0
            if (r0 == 0) goto L_0x0187
            java.util.List r0 = r0.getTradePayLogList()
            if (r0 == 0) goto L_0x0187
            int r2 = r0.size()
            if (r2 <= 0) goto L_0x0172
            java.lang.String r2 = ""
            r3 = r0
            java.lang.Iterable r3 = (java.lang.Iterable) r3
            java.util.Iterator r3 = r3.iterator()
        L_0x00f4:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x011e
            java.lang.Object r4 = r3.next()
            cn.xports.entity.TradePayLog r4 = (cn.xports.entity.TradePayLog) r4
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r2)
            java.lang.String r2 = "codeName"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r4, r2)
            java.lang.String r2 = r4.getPayModeCodeName()
            r5.append(r2)
            r2 = 44
            r5.append(r2)
            java.lang.String r2 = r5.toString()
            goto L_0x00f4
        L_0x011e:
            java.lang.String r3 = ","
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r2, r3)
            r3 = r3 ^ 1
            if (r3 == 0) goto L_0x0142
            int r3 = r2.length()
            int r3 = r3 + -1
            if (r2 == 0) goto L_0x013a
            java.lang.String r2 = r2.substring(r1, r3)
            java.lang.String r3 = "(this as java.lang.Strin…ing(startIndex, endIndex)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r3)
            goto L_0x0142
        L_0x013a:
            kotlin.TypeCastException r8 = new kotlin.TypeCastException
            java.lang.String r0 = "null cannot be cast to non-null type java.lang.String"
            r8.<init>(r0)
            throw r8
        L_0x0142:
            int r3 = cn.xports.qdplugin.R.id.tvPayMode
            android.view.View r3 = r7._$_findCachedViewById(r3)
            android.widget.TextView r3 = (android.widget.TextView) r3
            java.lang.String r4 = "tvPayMode"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r4)
            java.lang.Object r0 = r0.get(r1)
            java.lang.String r4 = "it[0]"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r4)
            cn.xports.entity.TradePayLog r0 = (cn.xports.entity.TradePayLog) r0
            java.lang.String r0 = r0.getPayModeCodeName()
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L_0x016b
            java.lang.String r0 = "--"
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            goto L_0x016e
        L_0x016b:
            r0 = r2
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
        L_0x016e:
            r3.setText(r0)
            goto L_0x019b
        L_0x0172:
            int r0 = cn.xports.qdplugin.R.id.tvPayMode
            android.view.View r0 = r7._$_findCachedViewById(r0)
            android.widget.TextView r0 = (android.widget.TextView) r0
            java.lang.String r2 = "tvPayMode"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r2)
            java.lang.String r2 = "--"
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            r0.setText(r2)
            goto L_0x019b
        L_0x0187:
            int r0 = cn.xports.qdplugin.R.id.tvPayMode
            android.view.View r0 = r7._$_findCachedViewById(r0)
            android.widget.TextView r0 = (android.widget.TextView) r0
            java.lang.String r2 = "tvPayMode"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r2)
            java.lang.String r2 = "--"
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            r0.setText(r2)
        L_0x019b:
            int r0 = cn.xports.qdplugin.R.id.tvOrderTime
            android.view.View r0 = r7._$_findCachedViewById(r0)
            android.widget.TextView r0 = (android.widget.TextView) r0
            java.lang.String r2 = "tvOrderTime"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r2)
            cn.xports.entity.OrderInfo r2 = r8.getTrade()
            java.lang.String r3 = "trade"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r3)
            java.lang.String r2 = r2.getAcceptDate()
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            r0.setText(r2)
            int r0 = cn.xports.qdplugin.R.id.tvTradeId
            android.view.View r0 = r7._$_findCachedViewById(r0)
            android.widget.TextView r0 = (android.widget.TextView) r0
            java.lang.String r2 = "tvTradeId"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r2)
            cn.xports.entity.OrderInfo r2 = r8.getTrade()
            java.lang.String r3 = "trade"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r3)
            java.lang.String r2 = r2.getTradeId()
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            r0.setText(r2)
            cn.xports.entity.OrderInfo r0 = r8.getTrade()
            java.lang.String r2 = "trade"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r2)
            java.lang.String r0 = r0.getOrderState()
            if (r0 != 0) goto L_0x01e9
            goto L_0x0228
        L_0x01e9:
            int r2 = r0.hashCode()
            switch(r2) {
                case 49: goto L_0x021c;
                case 50: goto L_0x01fd;
                case 51: goto L_0x01f1;
                default: goto L_0x01f0;
            }
        L_0x01f0:
            goto L_0x0228
        L_0x01f1:
            java.lang.String r2 = "3"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x0228
            java.lang.String r0 = "已完成"
            goto L_0x022b
        L_0x01fd:
            java.lang.String r2 = "2"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x0228
            cn.xports.entity.OrderInfo r0 = r8.getTrade()
            java.lang.String r2 = "trade"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r2)
            boolean r0 = r0.isExpired()
            if (r0 == 0) goto L_0x0218
            java.lang.String r0 = "已过期"
            goto L_0x022b
        L_0x0218:
            java.lang.String r0 = "待支付"
            goto L_0x022b
        L_0x021c:
            java.lang.String r2 = "1"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x0228
            java.lang.String r0 = "已取消"
            goto L_0x022b
        L_0x0228:
            java.lang.String r0 = "未知"
        L_0x022b:
            int r2 = cn.xports.qdplugin.R.id.tvOrderState
            android.view.View r2 = r7._$_findCachedViewById(r2)
            android.widget.TextView r2 = (android.widget.TextView) r2
            java.lang.String r3 = "tvOrderState"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r3)
            r3 = r0
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
            r2.setText(r3)
            int r2 = cn.xports.qdplugin.R.id.llOrderList
            android.view.View r2 = r7._$_findCachedViewById(r2)
            android.widget.LinearLayout r2 = (android.widget.LinearLayout) r2
            r2.removeAllViews()
            java.util.ArrayList<cn.xports.order.ItemOrderWrap> r2 = r7.orderItems
            r2.clear()
            java.util.List r2 = r8.getTradeTickets()
            if (r2 == 0) goto L_0x029a
            java.lang.Iterable r2 = (java.lang.Iterable) r2
            java.util.Iterator r2 = r2.iterator()
            r3 = 0
        L_0x025b:
            boolean r4 = r2.hasNext()
            if (r4 == 0) goto L_0x029a
            java.lang.Object r4 = r2.next()
            int r5 = r3 + 1
            if (r3 >= 0) goto L_0x026c
            kotlin.collections.CollectionsKt.throwIndexOverflow()
        L_0x026c:
            cn.xports.entity.TradeTicket r4 = (cn.xports.entity.TradeTicket) r4
            cn.xports.order.ItemOrderWrap r3 = new cn.xports.order.ItemOrderWrap
            int r6 = cn.xports.qdplugin.R.id.llOrderList
            android.view.View r6 = r7._$_findCachedViewById(r6)
            android.widget.LinearLayout r6 = (android.widget.LinearLayout) r6
            r3.<init>(r4, r6, r0)
            cn.xports.order.ItemOrderWrap r3 = r3.setNo(r5)
            java.util.ArrayList<cn.xports.order.ItemOrderWrap> r4 = r7.orderItems
            r4.add(r3)
            int r4 = cn.xports.qdplugin.R.id.llOrderList
            android.view.View r4 = r7._$_findCachedViewById(r4)
            android.widget.LinearLayout r4 = (android.widget.LinearLayout) r4
            java.lang.String r6 = "item"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r6)
            android.view.View r3 = r3.getView()
            r4.addView(r3)
            r3 = r5
            goto L_0x025b
        L_0x029a:
            int r2 = cn.xports.qdplugin.R.id.rlPayOption
            android.view.View r2 = r7._$_findCachedViewById(r2)
            android.widget.RelativeLayout r2 = (android.widget.RelativeLayout) r2
            java.lang.String r3 = "rlPayOption"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r3)
            r3 = 8
            r2.setVisibility(r3)
            int r2 = cn.xports.qdplugin.R.id.tvOption
            android.view.View r2 = r7._$_findCachedViewById(r2)
            android.widget.TextView r2 = (android.widget.TextView) r2
            java.lang.String r4 = "tvOption"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r4)
            r2.setVisibility(r3)
            java.lang.String r2 = "待支付"
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r2)
            if (r2 == 0) goto L_0x02ea
            int r2 = cn.xports.qdplugin.R.id.rlPayOption
            android.view.View r2 = r7._$_findCachedViewById(r2)
            android.widget.RelativeLayout r2 = (android.widget.RelativeLayout) r2
            java.lang.String r4 = "rlPayOption"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r4)
            r2.setVisibility(r1)
            int r2 = cn.xports.qdplugin.R.id.btnPayOption
            android.view.View r2 = r7._$_findCachedViewById(r2)
            android.widget.Button r2 = (android.widget.Button) r2
            java.lang.String r4 = "btnPayOption"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r4)
            java.lang.String r4 = "去支付"
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4
            r2.setText(r4)
        L_0x02ea:
            boolean r8 = r8.isCancelTicketTag()
            if (r8 == 0) goto L_0x032c
            java.lang.String r8 = "已完成"
            boolean r8 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r8)
            if (r8 == 0) goto L_0x032c
            int r8 = cn.xports.qdplugin.R.id.tvOption
            android.view.View r8 = r7._$_findCachedViewById(r8)
            android.widget.TextView r8 = (android.widget.TextView) r8
            java.lang.String r0 = "tvOption"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r8, r0)
            r8.setVisibility(r1)
            int r8 = cn.xports.qdplugin.R.id.tvOption
            android.view.View r8 = r7._$_findCachedViewById(r8)
            android.widget.TextView r8 = (android.widget.TextView) r8
            int r0 = cn.xports.qdplugin.R.drawable.bg_blue_stroke_round
            r8.setBackgroundResource(r0)
            int r8 = cn.xports.qdplugin.R.id.tvOption
            android.view.View r8 = r7._$_findCachedViewById(r8)
            android.widget.TextView r8 = (android.widget.TextView) r8
            java.lang.String r0 = "tvOption"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r8, r0)
            java.lang.String r0 = "退票"
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            r8.setText(r0)
            goto L_0x033c
        L_0x032c:
            int r8 = cn.xports.qdplugin.R.id.tvOption
            android.view.View r8 = r7._$_findCachedViewById(r8)
            android.widget.TextView r8 = (android.widget.TextView) r8
            java.lang.String r0 = "tvOption"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r8, r0)
            r8.setVisibility(r3)
        L_0x033c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.xports.order.OrderDetailActivity.showOrderDetail(cn.xports.parser.OrderInfoParser):void");
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.activity_order_detail;
    }

    /* access modifiers changed from: protected */
    public void initView() {
        ((TextView) _$_findCachedViewById(R.id.tvOption)).setOnClickListener(new OrderDetailActivity$initView$1(this));
        ((Button) _$_findCachedViewById(R.id.btnPayOption)).setOnClickListener(new OrderDetailActivity$initView$2(this));
        String stringExtra = getIntent().getStringExtra("tradeId");
        Intrinsics.checkExpressionValueIsNotNull(stringExtra, "intent.getStringExtra(\"tradeId\")");
        this.tradeId = stringExtra;
        OrderPresenter orderPresenter = (OrderPresenter) getPresenter();
        if (orderPresenter != null) {
            orderPresenter.getOrderDetail(this.tradeId);
        }
    }

    public void initData() {
        setPresenter(new OrderPresenter(new OrderModel(), this));
    }
}

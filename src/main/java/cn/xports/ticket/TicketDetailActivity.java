package cn.xports.ticket;

import android.app.Activity;
import android.content.Intent;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.xports.base.BaseBussActivity;
import cn.xports.base.Constant;
import cn.xports.baselib.util.BarUtils;
import cn.xports.baselib.util.MoneyUtil;
import cn.xports.baselib.util.SegmentUtils;
import cn.xports.entity.AgreementInfo;
import cn.xports.entity.TicketType;
import cn.xports.order.OrderPayActivity;
import cn.xports.parser.TicketDetailParser;
import cn.xports.qdplugin.R;
import cn.xports.ticket.TicketDetailContract;
import cn.xports.widget.AddLessView;
import com.stub.StubApp;
import java.io.Serializable;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.Delegates;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0019\u001a\u00020\u000eH\u0014J\b\u0010\u001a\u001a\u00020\u001bH\u0014J\b\u0010\u001c\u001a\u00020\u001dH\u0016J\b\u0010\u001e\u001a\u00020\u001dH\u0014J\u0012\u0010\u001f\u001a\u00020\u001d2\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J\u0010\u0010\"\u001a\u00020\u001d2\u0006\u0010#\u001a\u00020\u000eH\u0016R+\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068B@BX\u0002¢\u0006\u0012\n\u0004\b\f\u0010\r\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR+\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u000e8B@BX\u0002¢\u0006\u0012\n\u0004\b\u0014\u0010\r\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R+\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u000e8B@BX\u0002¢\u0006\u0012\n\u0004\b\u0018\u0010\r\u001a\u0004\b\u0016\u0010\u0011\"\u0004\b\u0017\u0010\u0013¨\u0006$"}, d2 = {"Lcn/xports/ticket/TicketDetailActivity;", "Lcn/xports/base/BaseBussActivity;", "Lcn/xports/ticket/TicketDetailPresenter;", "Lcn/xports/ticket/TicketDetailContract$View;", "()V", "<set-?>", "Lcn/xports/entity/TicketType;", "ticket", "getTicket", "()Lcn/xports/entity/TicketType;", "setTicket", "(Lcn/xports/entity/TicketType;)V", "ticket$delegate", "Lkotlin/properties/ReadWriteProperty;", "", "ticketTypeId", "getTicketTypeId", "()Ljava/lang/String;", "setTicketTypeId", "(Ljava/lang/String;)V", "ticketTypeId$delegate", "timeId", "getTimeId", "setTimeId", "timeId$delegate", "getChildTitle", "getLayoutId", "", "initData", "", "initView", "onGetTicketInfo", "value", "Lcn/xports/parser/TicketDetailParser;", "onGetTradeId", "tradeId", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: TicketDetailActivity.kt */
public final class TicketDetailActivity extends BaseBussActivity<TicketDetailPresenter> implements TicketDetailContract.View {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {(KProperty) Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(TicketDetailActivity.class), "timeId", "getTimeId()Ljava/lang/String;")), (KProperty) Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(TicketDetailActivity.class), "ticketTypeId", "getTicketTypeId()Ljava/lang/String;")), (KProperty) Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(TicketDetailActivity.class), "ticket", "getTicket()Lcn/xports/entity/TicketType;"))};
    private HashMap _$_findViewCache;
    private final ReadWriteProperty ticket$delegate = Delegates.INSTANCE.notNull();
    private final ReadWriteProperty ticketTypeId$delegate = Delegates.INSTANCE.notNull();
    private final ReadWriteProperty timeId$delegate = Delegates.INSTANCE.notNull();

    static {
        StubApp.interface11(4355);
    }

    /* access modifiers changed from: private */
    public final TicketType getTicket() {
        return (TicketType) this.ticket$delegate.getValue(this, $$delegatedProperties[2]);
    }

    /* access modifiers changed from: private */
    public final String getTicketTypeId() {
        return (String) this.ticketTypeId$delegate.getValue(this, $$delegatedProperties[1]);
    }

    /* access modifiers changed from: private */
    public final String getTimeId() {
        return (String) this.timeId$delegate.getValue(this, $$delegatedProperties[0]);
    }

    /* access modifiers changed from: private */
    public final void setTicket(TicketType ticketType) {
        this.ticket$delegate.setValue(this, $$delegatedProperties[2], ticketType);
    }

    /* access modifiers changed from: private */
    public final void setTicketTypeId(String str) {
        this.ticketTypeId$delegate.setValue(this, $$delegatedProperties[1], str);
    }

    /* access modifiers changed from: private */
    public final void setTimeId(String str) {
        this.timeId$delegate.setValue(this, $$delegatedProperties[0], str);
    }

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
        return "购票";
    }

    public void onGetTradeId(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "tradeId");
        startActivity(new Intent(this, OrderPayActivity.class).putExtra("tradeId", str));
    }

    public void onGetTicketInfo(@Nullable TicketDetailParser ticketDetailParser) {
        if (ticketDetailParser != null) {
            TicketType ticket = getTicket();
            TicketType ticketDetails = ticketDetailParser.getTicketDetails();
            Intrinsics.checkExpressionValueIsNotNull(ticketDetails, "ticketDetails");
            ticket.setPrice(ticketDetails.getPrice());
            TextView textView = (TextView) _$_findCachedViewById(R.id.tvTicketName);
            Intrinsics.checkExpressionValueIsNotNull(textView, "tvTicketName");
            TicketType ticketDetails2 = ticketDetailParser.getTicketDetails();
            Intrinsics.checkExpressionValueIsNotNull(ticketDetails2, "ticketDetails");
            textView.setText(ticketDetails2.getTicketTypeName());
            TextView textView2 = (TextView) _$_findCachedViewById(R.id.tvTicketMoney);
            Intrinsics.checkExpressionValueIsNotNull(textView2, "tvTicketMoney");
            StringBuilder sb = new StringBuilder();
            sb.append("¥");
            TicketType ticketDetails3 = ticketDetailParser.getTicketDetails();
            Intrinsics.checkExpressionValueIsNotNull(ticketDetails3, "ticketDetails");
            sb.append(MoneyUtil.cent2Yuan(ticketDetails3.getPrice()));
            textView2.setText(sb.toString());
            TextView textView3 = (TextView) _$_findCachedViewById(R.id.tvTicketTime);
            Intrinsics.checkExpressionValueIsNotNull(textView3, "tvTicketTime");
            StringBuilder sb2 = new StringBuilder();
            TicketType ticketDetails4 = ticketDetailParser.getTicketDetails();
            Intrinsics.checkExpressionValueIsNotNull(ticketDetails4, "ticketDetails");
            sb2.append(SegmentUtils.getStartTime(ticketDetails4.getStartSegment()));
            sb2.append("-");
            TicketType ticketDetails5 = ticketDetailParser.getTicketDetails();
            Intrinsics.checkExpressionValueIsNotNull(ticketDetails5, "ticketDetails");
            sb2.append(SegmentUtils.getEndTime(ticketDetails5.getEndSegment() - 1));
            textView3.setText(sb2.toString());
            TextView textView4 = (TextView) _$_findCachedViewById(R.id.tvEffectDate);
            Intrinsics.checkExpressionValueIsNotNull(textView4, "tvEffectDate");
            StringBuilder sb3 = new StringBuilder();
            TicketType ticketDetails6 = ticketDetailParser.getTicketDetails();
            Intrinsics.checkExpressionValueIsNotNull(ticketDetails6, "ticketDetails");
            sb3.append(ticketDetails6.getStartDate());
            sb3.append(" ~ ");
            TicketType ticketDetails7 = ticketDetailParser.getTicketDetails();
            Intrinsics.checkExpressionValueIsNotNull(ticketDetails7, "ticketDetails");
            sb3.append(ticketDetails7.getEndDate());
            textView4.setText(sb3.toString());
            TextView textView5 = (TextView) _$_findCachedViewById(R.id.tvLeftTip);
            Intrinsics.checkExpressionValueIsNotNull(textView5, "tvLeftTip");
            StringBuilder sb4 = new StringBuilder();
            sb4.append("余");
            TicketType ticketDetails8 = ticketDetailParser.getTicketDetails();
            Intrinsics.checkExpressionValueIsNotNull(ticketDetails8, "ticketDetails");
            sb4.append(ticketDetails8.getDayRemain());
            sb4.append("张");
            textView5.setText(sb4.toString());
            TextView textView6 = (TextView) _$_findCachedViewById(R.id.tvLimitBuy);
            Intrinsics.checkExpressionValueIsNotNull(textView6, "tvLimitBuy");
            TicketType ticketDetails9 = ticketDetailParser.getTicketDetails();
            Intrinsics.checkExpressionValueIsNotNull(ticketDetails9, "ticketDetails");
            textView6.setText(String.valueOf(ticketDetails9.getBuyLimit()));
            TicketType ticketDetails10 = ticketDetailParser.getTicketDetails();
            Intrinsics.checkExpressionValueIsNotNull(ticketDetails10, "ticketDetails");
            if (ticketDetails10.getRemark() != null) {
                TextView textView7 = (TextView) _$_findCachedViewById(R.id.tvRemark);
                Intrinsics.checkExpressionValueIsNotNull(textView7, "tvRemark");
                TicketType ticketDetails11 = ticketDetailParser.getTicketDetails();
                Intrinsics.checkExpressionValueIsNotNull(ticketDetails11, "ticketDetails");
                textView7.setText(Html.fromHtml(ticketDetails11.getRemark()));
            }
            TicketType ticketDetails12 = ticketDetailParser.getTicketDetails();
            Intrinsics.checkExpressionValueIsNotNull(ticketDetails12, "ticketDetails");
            int buyLimit = ticketDetails12.getBuyLimit();
            TicketType ticketDetails13 = ticketDetailParser.getTicketDetails();
            Intrinsics.checkExpressionValueIsNotNull(ticketDetails13, "ticketDetails");
            ((AddLessView) _$_findCachedViewById(R.id.addLessView)).setMax(Math.min(buyLimit, ticketDetails13.getDayRemain()));
            ((AddLessView) _$_findCachedViewById(R.id.addLessView)).setOnValueListener(new TicketDetailActivity$onGetTicketInfo$$inlined$apply$lambda$1(ticketDetailParser, this));
            if (((AddLessView) _$_findCachedViewById(R.id.addLessView)).getMax() > 0) {
                ((AddLessView) _$_findCachedViewById(R.id.addLessView)).setValue(1);
                TextView textView8 = (TextView) _$_findCachedViewById(R.id.tvTotalMoney);
                Intrinsics.checkExpressionValueIsNotNull(textView8, "tvTotalMoney");
                StringBuilder sb5 = new StringBuilder();
                sb5.append("¥");
                TicketType ticketDetails14 = ticketDetailParser.getTicketDetails();
                Intrinsics.checkExpressionValueIsNotNull(ticketDetails14, "ticketDetails");
                sb5.append(MoneyUtil.cent2Yuan(ticketDetails14.getPrice()));
                textView8.setText(sb5.toString());
            }
            if (ticketDetailParser.getAgreementInfo() == null) {
                LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(R.id.llAgreement);
                Intrinsics.checkExpressionValueIsNotNull(linearLayout, "llAgreement");
                linearLayout.setVisibility(8);
                return;
            }
            LinearLayout linearLayout2 = (LinearLayout) _$_findCachedViewById(R.id.llAgreement);
            Intrinsics.checkExpressionValueIsNotNull(linearLayout2, "llAgreement");
            linearLayout2.setVisibility(0);
            TextView textView9 = (TextView) _$_findCachedViewById(R.id.tvAgreement);
            Intrinsics.checkExpressionValueIsNotNull(textView9, "tvAgreement");
            StringBuilder sb6 = new StringBuilder();
            sb6.append("《");
            AgreementInfo agreementInfo = ticketDetailParser.getAgreementInfo();
            Intrinsics.checkExpressionValueIsNotNull(agreementInfo, "agreementInfo");
            sb6.append(agreementInfo.getAgreementName());
            sb6.append("》");
            textView9.setText(sb6.toString());
            ((TextView) _$_findCachedViewById(R.id.tvAgreement)).setOnClickListener(new TicketDetailActivity$onGetTicketInfo$$inlined$apply$lambda$2(ticketDetailParser, this));
        }
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.activity_ticket_detail;
    }

    /* access modifiers changed from: protected */
    public void initView() {
        useBlackTitle(false);
        setTitleBarColor(getResources().getColor(R.color.blue_4f7));
        Activity activity = this;
        BarUtils.setStatusBarLightMode(activity, false);
        BarUtils.setStatusBarColor(activity, getResources().getColor(R.color.blue_4f7));
        ((CheckBox) _$_findCachedViewById(R.id.checkbox)).setOnCheckedChangeListener(new TicketDetailActivity$initView$1(this));
        CheckBox checkBox = (CheckBox) _$_findCachedViewById(R.id.checkbox);
        Intrinsics.checkExpressionValueIsNotNull(checkBox, "checkbox");
        checkBox.setChecked(true);
        TextView textView = (TextView) _$_findCachedViewById(R.id.tvSubmit);
        Intrinsics.checkExpressionValueIsNotNull(textView, "tvSubmit");
        textView.setEnabled(true ^ TextUtils.isEmpty(Constant.PAYMODES));
        ((TextView) _$_findCachedViewById(R.id.tvSubmit)).setOnClickListener(new TicketDetailActivity$initView$2(this));
        TicketDetailPresenter ticketDetailPresenter = (TicketDetailPresenter) getPresenter();
        if (ticketDetailPresenter != null) {
            ticketDetailPresenter.getTicketInfo(getTimeId(), getTicketTypeId());
        }
    }

    public void initData() {
        setPresenter(new TicketDetailPresenter(new TicketDetailModel(), this));
        String stringExtra = getIntent().getStringExtra("timeId");
        Intrinsics.checkExpressionValueIsNotNull(stringExtra, "intent.getStringExtra(\"timeId\")");
        setTimeId(stringExtra);
        String stringExtra2 = getIntent().getStringExtra("ticketTypeId");
        Intrinsics.checkExpressionValueIsNotNull(stringExtra2, "intent.getStringExtra(\"ticketTypeId\")");
        setTicketTypeId(stringExtra2);
        Serializable serializableExtra = getIntent().getSerializableExtra("ticket");
        if (serializableExtra != null) {
            setTicket((TicketType) serializableExtra);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type cn.xports.entity.TicketType");
    }
}

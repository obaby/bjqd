package cn.xports.order;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.xports.base.BaseBussActivity;
import cn.xports.base.Constant;
import cn.xports.baselib.bean.DataMap;
import cn.xports.baselib.http.RxUtil;
import cn.xports.baselib.util.MoneyUtil;
import cn.xports.baselib.util.RxBus;
import cn.xports.entity.BalanceInfo;
import cn.xports.entity.CampaignInfo;
import cn.xports.entity.CardInfo;
import cn.xports.entity.OrderInfo;
import cn.xports.entity.PairEvent;
import cn.xports.entity.PayCardResult;
import cn.xports.entity.TradeTicket;
import cn.xports.entity.TrainCourse;
import cn.xports.entity.ValidProduct;
import cn.xports.entity.WechatPayInfo;
import cn.xports.export.EventHandler;
import cn.xports.export.OnPayListener;
import cn.xports.order.OrderPayContract;
import cn.xports.parser.OrderPayParser;
import cn.xports.pay.PayPresenter;
import cn.xports.qd2.entity.K;
import cn.xports.qdplugin.R;
import cn.xports.venue.VenueMainActivity;
import cn.xports.widget.PaySelectView;
import com.alipay.sdk.util.j;
import com.blankj.utilcode.util.ActivityUtils;
import com.stub.StubApp;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u0004B\u0005¢\u0006\u0002\u0010\u0005J\u0010\u0010<\u001a\u00020=2\u0006\u0010>\u001a\u00020\u0018H\u0016J\u0010\u0010?\u001a\u00020=2\u0006\u0010@\u001a\u00020AH\u0016J\b\u0010B\u001a\u00020\u001aH\u0002J\b\u0010C\u001a\u00020\u001aH\u0002J\b\u0010D\u001a\u00020\u0018H\u0014J\b\u0010E\u001a\u00020\rH\u0014J\b\u0010F\u001a\u00020=H\u0016J\b\u0010G\u001a\u00020=H\u0014J\u0010\u0010H\u001a\u00020=2\u0006\u0010I\u001a\u00020JH\u0016J\u0010\u0010K\u001a\u00020=2\u0006\u0010L\u001a\u00020\rH\u0016J\u0010\u0010M\u001a\u00020=2\u0006\u0010L\u001a\u00020\u0018H\u0016J\b\u0010N\u001a\u00020=H\u0002J\u0018\u0010O\u001a\u00020=2\u0006\u0010P\u001a\u00020\r2\u0006\u0010Q\u001a\u00020\rH\u0016J\u0012\u0010R\u001a\u00020=2\b\u0010S\u001a\u0004\u0018\u00010\u000fH\u0016R!\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u0015X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\rX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0019\u001a\u00020\u001aX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001a\u0010\u001f\u001a\u00020\rX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001a\u0010$\u001a\u00020\u0018X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u0011\u0010)\u001a\u00020*¢\u0006\b\n\u0000\u001a\u0004\b+\u0010,R\u001a\u0010-\u001a\u00020\rX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010!\"\u0004\b/\u0010#R\u001c\u00100\u001a\u0004\u0018\u000101X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u00103\"\u0004\b4\u00105R\u001a\u00106\u001a\u00020\rX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u0010!\"\u0004\b8\u0010#R\u001a\u00109\u001a\u00020\u0018X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010&\"\u0004\b;\u0010(¨\u0006T"}, d2 = {"Lcn/xports/order/OrderPayActivity;", "Lcn/xports/base/BaseBussActivity;", "Lcn/xports/order/OrderPayPresenter;", "Lcn/xports/order/OrderPayContract$View;", "Lcn/xports/export/OnPayListener;", "()V", "cardList", "Ljava/util/ArrayList;", "Lcn/xports/entity/CardInfo;", "Lkotlin/collections/ArrayList;", "getCardList", "()Ljava/util/ArrayList;", "cardPayMoney", "", "commonPay", "Lcn/xports/parser/OrderPayParser;", "getCommonPay", "()Lcn/xports/parser/OrderPayParser;", "setCommonPay", "(Lcn/xports/parser/OrderPayParser;)V", "coupon", "Lcn/xports/baselib/bean/DataMap;", "couponMoney", "couponNo", "", "hasCards", "", "getHasCards", "()Z", "setHasCards", "(Z)V", "mCash", "getMCash", "()I", "setMCash", "(I)V", "payMode", "getPayMode", "()Ljava/lang/String;", "setPayMode", "(Ljava/lang/String;)V", "payPresenter", "Lcn/xports/pay/PayPresenter;", "getPayPresenter", "()Lcn/xports/pay/PayPresenter;", "productType", "getProductType", "setProductType", "selectCard", "Lcn/xports/entity/BalanceInfo;", "getSelectCard", "()Lcn/xports/entity/BalanceInfo;", "setSelectCard", "(Lcn/xports/entity/BalanceInfo;)V", "total", "getTotal", "setTotal", "tradeId", "getTradeId", "setTradeId", "callAlipay", "", "alipaySign", "callWechat", "wechatPayInfo", "Lcn/xports/entity/WechatPayInfo;", "checkIsAlipayInstall", "checkIsWechatInstall", "getChildTitle", "getLayoutId", "initData", "initView", "onCardList", "cardResult", "Lcn/xports/entity/PayCardResult;", "onPayResult", "result", "onThirdResult", "setShouldPayText", "showCountDownTime", "minute", "second", "showOrderPayInfo", "payParser", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: OrderPayActivity.kt */
public final class OrderPayActivity extends BaseBussActivity<OrderPayPresenter> implements OrderPayContract.View, OnPayListener {
    private HashMap _$_findViewCache;
    @NotNull
    private final ArrayList<CardInfo> cardList = new ArrayList<>();
    /* access modifiers changed from: private */
    public int cardPayMoney;
    @Nullable
    private OrderPayParser commonPay;
    /* access modifiers changed from: private */
    public DataMap coupon = new DataMap();
    /* access modifiers changed from: private */
    public int couponMoney;
    /* access modifiers changed from: private */
    public String couponNo = "";
    private boolean hasCards;
    private int mCash;
    @NotNull
    private String payMode = K.k0;
    @NotNull
    private final PayPresenter payPresenter = new PayPresenter(this);
    private int productType;
    @Nullable
    private BalanceInfo selectCard;
    private int total;
    @NotNull
    private String tradeId = K.k0;

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
        return "订单支付";
    }

    public void onThirdResult(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, j.f740c);
        if (Intrinsics.areEqual(str, "1")) {
            RxBus.get().post("支付成功");
            if (Intrinsics.areEqual(this.payMode, Constant.ALI_PAY)) {
                showMsg("支付成功");
            }
            startActivity(new Intent(this, OrderDetailActivity.class).putExtra("tradeId", this.tradeId));
            if (this.productType == 1) {
                ActivityUtils.finishToActivity(VenueMainActivity.class, false);
            }
            finish();
        } else if (!Intrinsics.areEqual(this.payMode, Constant.ALI_PAY)) {
        } else {
            if (TextUtils.isEmpty(str)) {
                showMsg("支付失败");
                return;
            }
            showMsg("支付失败：" + str);
        }
    }

    public void onPayResult(int i) {
        RxBus.get().post("支付成功");
        startActivity(new Intent(this, OrderDetailActivity.class).putExtra("tradeId", this.tradeId));
        if (this.productType == 1) {
            ActivityUtils.finishToActivity(VenueMainActivity.class, false);
        }
        finish();
    }

    public void showCountDownTime(int i, int i2) {
        TextView textView = (TextView) _$_findCachedViewById(R.id.tvCountDown);
        Intrinsics.checkExpressionValueIsNotNull(textView, "tvCountDown");
        StringBuilder sb = new StringBuilder();
        sb.append("请在");
        sb.append(i);
        sb.append(":");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Object[] objArr = {Integer.valueOf(i2)};
        String format = String.format("%02d", Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
        sb.append(format);
        sb.append("内完成支付，超时将取消订单");
        textView.setText(sb.toString());
        if (i <= 0 && i2 <= 0) {
            TextView textView2 = (TextView) _$_findCachedViewById(R.id.tvCountDown);
            Intrinsics.checkExpressionValueIsNotNull(textView2, "tvCountDown");
            textView2.setText("订单已过期");
        }
    }

    public void onCardList(@NotNull PayCardResult payCardResult) {
        Intrinsics.checkParameterIsNotNull(payCardResult, "cardResult");
        this.hasCards = true;
        if (Intrinsics.areEqual("1", payCardResult.getChooseCard())) {
            List<CardInfo> cardInfoList = payCardResult.getCardInfoList();
            if (cardInfoList != null) {
                this.cardList.addAll(cardInfoList);
                return;
            }
            return;
        }
        CardInfo primaryCard = payCardResult.getPrimaryCard();
        if (primaryCard != null) {
            this.cardList.add(primaryCard);
        }
    }

    public void showOrderPayInfo(@Nullable OrderPayParser orderPayParser) {
        int i;
        String sb;
        String str;
        if (orderPayParser != null) {
            LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(R.id.llCardPay);
            Intrinsics.checkExpressionValueIsNotNull(linearLayout, "llCardPay");
            linearLayout.setVisibility(0);
            PaySelectView paySelectView = (PaySelectView) _$_findCachedViewById(R.id.paySelectView);
            Intrinsics.checkExpressionValueIsNotNull(paySelectView, "paySelectView");
            paySelectView.setVisibility(0);
            this.commonPay = orderPayParser;
            OrderPayPresenter orderPayPresenter = (OrderPayPresenter) getPresenter();
            if (orderPayPresenter != null) {
                String sysdate = orderPayParser.getSysdate();
                Intrinsics.checkExpressionValueIsNotNull(sysdate, "sysdate");
                OrderInfo trade = orderPayParser.getTrade();
                Intrinsics.checkExpressionValueIsNotNull(trade, "trade");
                String expireTime = trade.getExpireTime();
                Intrinsics.checkExpressionValueIsNotNull(expireTime, "trade.expireTime");
                orderPayPresenter.countDownTime(sysdate, expireTime);
            }
            if (orderPayParser.getMargin() != null) {
                String margin = orderPayParser.getMargin();
                Intrinsics.checkExpressionValueIsNotNull(margin, "margin");
                i = Integer.parseInt(margin);
            } else {
                i = 0;
            }
            if (i == 0) {
                LinearLayout linearLayout2 = (LinearLayout) _$_findCachedViewById(R.id.llClash);
                Intrinsics.checkExpressionValueIsNotNull(linearLayout2, "llClash");
                linearLayout2.setVisibility(8);
            } else {
                LinearLayout linearLayout3 = (LinearLayout) _$_findCachedViewById(R.id.llClash);
                Intrinsics.checkExpressionValueIsNotNull(linearLayout3, "llClash");
                linearLayout3.setVisibility(0);
                TextView textView = (TextView) _$_findCachedViewById(R.id.tvMargin);
                Intrinsics.checkExpressionValueIsNotNull(textView, "tvMargin");
                if (i == 0) {
                    str = "--";
                } else {
                    str = "¥" + MoneyUtil.cent2Yuan(i);
                }
                textView.setText(str);
                this.mCash = i;
            }
            int shouldPayParse = BalanceInfo.shouldPayParse(orderPayParser);
            TextView textView2 = (TextView) _$_findCachedViewById(R.id.tvTotalMoney);
            Intrinsics.checkExpressionValueIsNotNull(textView2, "tvTotalMoney");
            textView2.setText("¥" + MoneyUtil.cent2Yuan(orderPayParser.getTotalPayMoney() + i));
            this.total = shouldPayParse;
            setShouldPayText();
            ((LinearLayout) _$_findCachedViewById(R.id.llPayOrderList)).removeAllViews();
            if (orderPayParser.getProduct() != null && orderPayParser.getProductRes() != null) {
                this.productType = 1;
                LinearLayout linearLayout4 = (LinearLayout) _$_findCachedViewById(R.id.llClash);
                Intrinsics.checkExpressionValueIsNotNull(linearLayout4, "llClash");
                linearLayout4.setVisibility(8);
                ValidProduct product = orderPayParser.getProduct();
                Intrinsics.checkExpressionValueIsNotNull(product, "product");
                String productName = product.getProductName();
                ValidProduct product2 = orderPayParser.getProduct();
                Intrinsics.checkExpressionValueIsNotNull(product2, "product");
                ((LinearLayout) _$_findCachedViewById(R.id.llPayOrderList)).addView(new ItemPayInfoWrap(productName, product2.getPrice(), orderPayParser.getProductRes(), (LinearLayout) _$_findCachedViewById(R.id.llPayOrderList)).getView());
            } else if (orderPayParser.getTicketInfo() != null && orderPayParser.getTicketInfo().size() > 0) {
                ((LinearLayout) _$_findCachedViewById(R.id.llPayOrderList)).addView(new ItemPayInfoWrap(orderPayParser.getTicketInfo().get(0), (LinearLayout) _$_findCachedViewById(R.id.llPayOrderList), orderPayParser.getTicketInfo().size()).getView());
            } else if (orderPayParser.getPublicCampaign() != null) {
                this.productType = 2;
                CampaignInfo publicCampaign = orderPayParser.getPublicCampaign();
                String campStartDate = publicCampaign.getCampStartDate();
                Intrinsics.checkExpressionValueIsNotNull(campStartDate, K.campStartDate);
                if (campStartDate != null) {
                    String substring = campStartDate.substring(0, 10);
                    Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                    String campEndDate = publicCampaign.getCampEndDate();
                    Intrinsics.checkExpressionValueIsNotNull(campEndDate, K.campEndDate);
                    if (campEndDate != null) {
                        String substring2 = campEndDate.substring(0, 10);
                        Intrinsics.checkExpressionValueIsNotNull(substring2, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                        if (Intrinsics.areEqual(substring, substring2)) {
                            StringBuilder sb2 = new StringBuilder();
                            String campStartDate2 = publicCampaign.getCampStartDate();
                            Intrinsics.checkExpressionValueIsNotNull(campStartDate2, K.campStartDate);
                            if (campStartDate2 != null) {
                                String substring3 = campStartDate2.substring(0, 15);
                                Intrinsics.checkExpressionValueIsNotNull(substring3, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                                sb2.append(StringsKt.replace$default(substring3, "-", "/", false, 4, (Object) null));
                                sb2.append(" - ");
                                String campEndDate2 = publicCampaign.getCampEndDate();
                                Intrinsics.checkExpressionValueIsNotNull(campEndDate2, K.campEndDate);
                                if (campEndDate2 != null) {
                                    String substring4 = campEndDate2.substring(11, 16);
                                    Intrinsics.checkExpressionValueIsNotNull(substring4, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                                    sb2.append(substring4);
                                    sb = sb2.toString();
                                } else {
                                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                                }
                            } else {
                                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                            }
                        } else {
                            StringBuilder sb3 = new StringBuilder();
                            String campStartDate3 = publicCampaign.getCampStartDate();
                            Intrinsics.checkExpressionValueIsNotNull(campStartDate3, K.campStartDate);
                            if (campStartDate3 != null) {
                                String substring5 = campStartDate3.substring(0, 16);
                                Intrinsics.checkExpressionValueIsNotNull(substring5, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                                sb3.append(StringsKt.replace$default(substring5, "-", "/", false, 4, (Object) null));
                                sb3.append(" - ");
                                String campEndDate3 = publicCampaign.getCampEndDate();
                                Intrinsics.checkExpressionValueIsNotNull(campEndDate3, K.campEndDate);
                                if (campEndDate3 != null) {
                                    String substring6 = campEndDate3.substring(0, 16);
                                    Intrinsics.checkExpressionValueIsNotNull(substring6, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                                    sb3.append(StringsKt.replace$default(substring6, "-", "/", false, 4, (Object) null));
                                    sb = sb3.toString();
                                } else {
                                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                                }
                            } else {
                                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                            }
                        }
                        String str2 = sb;
                        CampaignInfo publicCampaign2 = orderPayParser.getPublicCampaign();
                        Intrinsics.checkExpressionValueIsNotNull(publicCampaign2, "publicCampaign");
                        ((LinearLayout) _$_findCachedViewById(R.id.llPayOrderList)).addView(new ItemPayInfoWrap(publicCampaign2.getTitle(), this.total, "活动时间", str2, (LinearLayout) _$_findCachedViewById(R.id.llPayOrderList)).getView());
                        return;
                    }
                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                }
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            } else if (orderPayParser.getCourse() != null) {
                this.productType = 3;
                TrainCourse course = orderPayParser.getCourse();
                String startDate = course.getStartDate();
                Intrinsics.checkExpressionValueIsNotNull(startDate, K.startDate);
                if (startDate != null) {
                    String substring7 = startDate.substring(0, 10);
                    Intrinsics.checkExpressionValueIsNotNull(substring7, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                    String replace$default = StringsKt.replace$default(substring7, "-", "/", false, 4, (Object) null);
                    String endDate = course.getEndDate();
                    Intrinsics.checkExpressionValueIsNotNull(endDate, K.endDate);
                    if (endDate != null) {
                        String substring8 = endDate.substring(0, 10);
                        Intrinsics.checkExpressionValueIsNotNull(substring8, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                        TrainCourse course2 = orderPayParser.getCourse();
                        Intrinsics.checkExpressionValueIsNotNull(course2, "course");
                        ((LinearLayout) _$_findCachedViewById(R.id.llPayOrderList)).addView(new ItemPayInfoWrap(course2.getCourseName(), this.total, "上课时间", replace$default + " - " + StringsKt.replace$default(substring8, "-", "/", false, 4, (Object) null), (LinearLayout) _$_findCachedViewById(R.id.llPayOrderList)).getView());
                        return;
                    }
                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                }
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            } else {
                List<TradeTicket> orderInfo = orderPayParser.getOrderInfo();
                if (orderInfo != null) {
                    for (TradeTicket itemPayInfoWrap : orderInfo) {
                        ((LinearLayout) _$_findCachedViewById(R.id.llPayOrderList)).addView(new ItemPayInfoWrap(itemPayInfoWrap, (LinearLayout) _$_findCachedViewById(R.id.llPayOrderList), 0).getView());
                    }
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public final void setShouldPayText() {
        TextView textView = (TextView) _$_findCachedViewById(R.id.tvShouldMoney);
        Intrinsics.checkExpressionValueIsNotNull(textView, "tvShouldMoney");
        textView.setText("¥" + MoneyUtil.cent2Yuan(((this.total - this.couponMoney) - this.cardPayMoney) + this.mCash));
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.activity_order_pay;
    }

    public final int getTotal() {
        return this.total;
    }

    public final void setTotal(int i) {
        this.total = i;
    }

    public final int getMCash() {
        return this.mCash;
    }

    public final void setMCash(int i) {
        this.mCash = i;
    }

    @NotNull
    public final String getTradeId() {
        return this.tradeId;
    }

    public final void setTradeId(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.tradeId = str;
    }

    @NotNull
    public final String getPayMode() {
        return this.payMode;
    }

    public final void setPayMode(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.payMode = str;
    }

    public final boolean getHasCards() {
        return this.hasCards;
    }

    public final void setHasCards(boolean z) {
        this.hasCards = z;
    }

    @Nullable
    public final BalanceInfo getSelectCard() {
        return this.selectCard;
    }

    public final void setSelectCard(@Nullable BalanceInfo balanceInfo) {
        this.selectCard = balanceInfo;
    }

    @NotNull
    public final ArrayList<CardInfo> getCardList() {
        return this.cardList;
    }

    @NotNull
    public final PayPresenter getPayPresenter() {
        return this.payPresenter;
    }

    public final int getProductType() {
        return this.productType;
    }

    public final void setProductType(int i) {
        this.productType = i;
    }

    @Nullable
    public final OrderPayParser getCommonPay() {
        return this.commonPay;
    }

    public final void setCommonPay(@Nullable OrderPayParser orderPayParser) {
        this.commonPay = orderPayParser;
    }

    /* access modifiers changed from: protected */
    public void initView() {
        setPresenter(new OrderPayPresenter(new OrderPayModel(), this));
        this.tradeId = getStringExtra("tradeId");
        OrderPayPresenter orderPayPresenter = (OrderPayPresenter) getPresenter();
        if (orderPayPresenter != null) {
            orderPayPresenter.getPayInfo(this.tradeId, "");
        }
        OrderPayPresenter orderPayPresenter2 = (OrderPayPresenter) getPresenter();
        if (orderPayPresenter2 != null) {
            orderPayPresenter2.getPayCards(this.tradeId);
        }
        ((TextView) _$_findCachedViewById(R.id.tvSubmit)).setOnClickListener(new OrderPayActivity$initView$1(this));
        ((LinearLayout) _$_findCachedViewById(R.id.llCardPay)).setOnClickListener(new OrderPayActivity$initView$2(this));
        ((PaySelectView) _$_findCachedViewById(R.id.paySelectView)).setSelectListener(new OrderPayActivity$initView$3(this));
        ((LinearLayout) _$_findCachedViewById(R.id.llCouponPay)).setOnClickListener(new OrderPayActivity$initView$4(this));
    }

    public void initData() {
        EventHandler.getInstance().setPayListener(this);
        RxBus.get().toFlowable(PairEvent.class).subscribe(new OrderPayActivity$initData$1(this));
        RxBus.get().toFlowable(DataMap.class).subscribe(new OrderPayActivity$initData$2(this));
    }

    public void callWechat(@NotNull WechatPayInfo wechatPayInfo) {
        Intrinsics.checkParameterIsNotNull(wechatPayInfo, "wechatPayInfo");
        Constant.APPID = wechatPayInfo.getAppid();
        IWXAPI createWXAPI = WXAPIFactory.createWXAPI(StubApp.getOrigApplicationContext(getApplicationContext()), wechatPayInfo.getAppid());
        createWXAPI.registerApp(wechatPayInfo.getAppid());
        BaseReq payReq = new PayReq();
        payReq.appId = wechatPayInfo.getAppid();
        payReq.nonceStr = wechatPayInfo.getNoncestr();
        payReq.packageValue = wechatPayInfo.getPackageX();
        payReq.partnerId = wechatPayInfo.getPartnerid();
        payReq.prepayId = wechatPayInfo.getPrepayid();
        payReq.timeStamp = wechatPayInfo.getTimestamp();
        payReq.sign = wechatPayInfo.getSign();
        createWXAPI.sendReq(payReq);
    }

    public void callAlipay(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "alipaySign");
        Observable.just(str).map(new OrderPayActivity$callAlipay$subscription$1(this)).compose(RxUtil.applyIO()).map(OrderPayActivity$callAlipay$subscription$2.INSTANCE).subscribe(new OrderPayActivity$callAlipay$subscription$3(this, getTAG()));
        Unit unit = Unit.INSTANCE;
    }

    private final boolean checkIsWechatInstall() {
        IWXAPI createWXAPI = WXAPIFactory.createWXAPI(this, (String) null);
        Intrinsics.checkExpressionValueIsNotNull(createWXAPI, "msgApi");
        return createWXAPI.isWXAppInstalled();
    }

    private final boolean checkIsAlipayInstall() {
        PackageManager packageManager = getPackageManager();
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("alipays://"));
        List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, 64);
        return queryIntentActivities != null && !queryIntentActivities.isEmpty();
    }
}

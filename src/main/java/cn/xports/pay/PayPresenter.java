package cn.xports.pay;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;
import cn.xports.base.Constant;
import cn.xports.baselib.bean.DataMap;
import cn.xports.baselib.bean.Response;
import cn.xports.baselib.http.ApiResponseFunc;
import cn.xports.baselib.http.RetrofitUtil;
import cn.xports.baselib.http.RxUtil;
import cn.xports.baselib.mvp.IView;
import cn.xports.baselib.util.RxBus;
import cn.xports.entity.BalanceInfo;
import cn.xports.entity.PayResult;
import cn.xports.entity.PayResultEvent;
import cn.xports.entity.WechatPayInfo;
import cn.xports.export.EventHandler;
import cn.xports.http.SodaService;
import cn.xports.qd2.entity.K;
import cn.xports.sportCoaching.WebViewDetailActivity;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.stub.StubApp;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J:\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u00062\b\b\u0002\u0010\u0014\u001a\u00020\u00062\b\b\u0002\u0010\u0015\u001a\u00020\u000bJ\u0016\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u0006J6\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\u0006\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u000b2\u0016\u0010\u001b\u001a\u0012\u0012\u0004\u0012\u00020\u001d0\u001cj\b\u0012\u0004\u0012\u00020\u001d`\u001eH\u0002J.\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\u0006\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u000b2\u0006\u0010 \u001a\u00020\u0006H\u0002J\u000e\u0010!\u001a\u00020\u000f2\u0006\u0010\"\u001a\u00020#J:\u0010$\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u00062\b\b\u0002\u0010\u0014\u001a\u00020\u00062\b\b\u0002\u0010\u0015\u001a\u00020\u000bJ\u0016\u0010%\u001a\u00020\u000f2\u0006\u0010&\u001a\u00020'2\u0006\u0010\u0010\u001a\u00020\u0006J\u0006\u0010(\u001a\u00020)J\u0006\u0010*\u001a\u00020)J4\u0010+\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00062\b\u0010,\u001a\u0004\u0018\u00010-2\u0006\u0010\u0011\u001a\u00020\u000b2\b\b\u0002\u0010\u0014\u001a\u00020\u00062\b\b\u0002\u0010\u0015\u001a\u00020\u000bJ*\u0010.\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00062\u0006\u0010,\u001a\u00020-2\b\b\u0002\u0010\u0014\u001a\u00020\u00062\b\b\u0002\u0010\u0015\u001a\u00020\u000bJ\u0018\u0010/\u001a\u00020\u001d2\u0006\u00100\u001a\u00020\u000b2\u0006\u0010 \u001a\u00020\u0006H\u0002J.\u00101\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u00062\n\b\u0002\u00102\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u00103\u001a\u0004\u0018\u00010\u0006R\u000e\u0010\u0005\u001a\u00020\u0006XD¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006XD¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006XD¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006XD¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u00064"}, d2 = {"Lcn/xports/pay/PayPresenter;", "", "rootView", "Lcn/xports/baselib/mvp/IView;", "(Lcn/xports/baselib/mvp/IView;)V", "CASH", "", "PAY_MODE", "PAY_MONEY", "REAL_PAY", "acountId", "", "getRootView", "()Lcn/xports/baselib/mvp/IView;", "callAli", "", "tradeId", "totalFee", "cash", "title", "couponNo", "couponMoney", "callAlipay", "alipaySign", "callMultiPay", "Lio/reactivex/Observable;", "Lcn/xports/baselib/bean/Response;", "payGroup", "Ljava/util/ArrayList;", "Lcn/xports/baselib/bean/DataMap;", "Lkotlin/collections/ArrayList;", "callPay", "payMode", "callWechat", "wechatPayInfo", "Lcn/xports/entity/WechatPayInfo;", "callWexin", "checkAliPayResult", "payResult", "Lcn/xports/entity/PayResult;", "checkIsAlipayInstall", "", "checkIsWechatInstall", "ecardFuckPay", "balanceInfo", "Lcn/xports/entity/BalanceInfo;", "ecardPay", "getThridPayData", "money", "payOrderF", "ecardNo", "orderDiscount", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: PayPresenter.kt */
public final class PayPresenter {
    /* access modifiers changed from: private */
    public final String CASH = "cashPledge";
    /* access modifiers changed from: private */
    public final String PAY_MODE = "payMode";
    /* access modifiers changed from: private */
    public final String PAY_MONEY = "payMoney";
    /* access modifiers changed from: private */
    public final String REAL_PAY = "realPay";
    /* access modifiers changed from: private */
    public int acountId;
    @NotNull
    private final IView rootView;

    public PayPresenter(@NotNull IView iView) {
        Intrinsics.checkParameterIsNotNull(iView, "rootView");
        this.rootView = iView;
    }

    @NotNull
    public final IView getRootView() {
        return this.rootView;
    }

    private final Observable<Response> callPay(String str, int i, int i2, String str2) {
        SodaService sodaService = (SodaService) RetrofitUtil.getInstance().create(SodaService.class);
        Observable<Response> map = sodaService.getThirdAccountId(str).map(new ApiResponseFunc()).flatMap(new PayPresenter$callPay$$inlined$apply$lambda$1(sodaService, this, str, str2, i, i2)).map(new ApiResponseFunc());
        Intrinsics.checkExpressionValueIsNotNull(map, "getThirdAccountId(tradeI…ResponseFunc<Response>())");
        return map;
    }

    private final DataMap getThridPayData(int i, String str) {
        DataMap dataMap = new DataMap();
        dataMap.set(this.PAY_MODE, str).set(this.PAY_MONEY, Integer.valueOf(i)).set(this.REAL_PAY, Integer.valueOf(i));
        return dataMap;
    }

    private final Observable<Response> callMultiPay(String str, int i, ArrayList<DataMap> arrayList) {
        SodaService sodaService = (SodaService) RetrofitUtil.getInstance().create(SodaService.class);
        Observable<Response> map = sodaService.getThirdAccountId(str).map(new ApiResponseFunc()).flatMap(new PayPresenter$callMultiPay$$inlined$apply$lambda$1(sodaService, this, str, arrayList, i)).map(new ApiResponseFunc());
        Intrinsics.checkExpressionValueIsNotNull(map, "getThirdAccountId(tradeI…ResponseFunc<Response>())");
        return map;
    }

    public static /* synthetic */ void ecardFuckPay$default(PayPresenter payPresenter, String str, BalanceInfo balanceInfo, int i, String str2, int i2, int i3, Object obj) {
        if ((i3 & 8) != 0) {
            str2 = "";
        }
        payPresenter.ecardFuckPay(str, balanceInfo, i, str2, (i3 & 16) != 0 ? 0 : i2);
    }

    public final void ecardFuckPay(@NotNull String str, @Nullable BalanceInfo balanceInfo, int i, @NotNull String str2, int i2) {
        String str3;
        String str4 = str;
        int i3 = i;
        String str5 = str2;
        int i4 = i2;
        Intrinsics.checkParameterIsNotNull(str, "tradeId");
        Intrinsics.checkParameterIsNotNull(str2, K.couponNo);
        if (i4 >= i3 || balanceInfo != null) {
            ArrayList arrayList = new ArrayList();
            DataMap dataMap = new DataMap();
            Integer num = null;
            dataMap.put(this.REAL_PAY, balanceInfo != null ? Integer.valueOf(balanceInfo.shouldPayLeft()) : null);
            dataMap.put(this.PAY_MONEY, balanceInfo != null ? Integer.valueOf(balanceInfo.getPayMoney()) : null);
            dataMap.put(this.PAY_MODE, balanceInfo != null ? balanceInfo.getPayMode() : null);
            if (!Intrinsics.areEqual(str2, "")) {
                arrayList.add(getThridPayData(i4 > i3 ? i3 : i4, Constant.COUPON_PAY).set(K.couponNo, str2));
                if (i4 >= i3) {
                    String json = GsonUtils.toJson(arrayList);
                    Intrinsics.checkExpressionValueIsNotNull(json, "GsonUtils.toJson(payGroup)");
                    payOrderF$default(this, str, json, (String) null, (String) null, 12, (Object) null);
                    return;
                }
                int i5 = i3 - i4;
                if (balanceInfo == null || (str3 = balanceInfo.getPayMode()) == null) {
                    str3 = "";
                }
                dataMap = getThridPayData(i5, str3);
            }
            if (balanceInfo != null) {
                String payMode = balanceInfo.getPayMode();
                if (payMode != null) {
                    int hashCode = payMode.hashCode();
                    if (hashCode != 1569) {
                        if (hashCode == 53666678 ? payMode.equals(Constant.TIMES_PAY) : !(hashCode != 383818865 || !payMode.equals(Constant.PERIOD_PAY))) {
                            dataMap.put("depositId", balanceInfo.getId());
                        }
                    } else if (payMode.equals(Constant.DEPOSIT_PAY)) {
                        dataMap.put("productId", balanceInfo.getId());
                        dataMap.put("validTag", balanceInfo.getValidTag());
                    }
                }
                arrayList.add(dataMap);
            }
            String json2 = GsonUtils.toJson(arrayList);
            Intrinsics.checkExpressionValueIsNotNull(json2, "GsonUtils.toJson(payGroup)");
            String ecardNo = balanceInfo != null ? balanceInfo.getEcardNo() : null;
            if (balanceInfo != null) {
                num = Integer.valueOf(balanceInfo.getOrderDiscount());
            }
            payOrderF(str, json2, ecardNo, String.valueOf(num));
            return;
        }
        ToastUtils.showShort("您选择的支付方式余额不足", new Object[0]);
    }

    public static /* synthetic */ void payOrderF$default(PayPresenter payPresenter, String str, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 4) != 0) {
            str3 = "";
        }
        if ((i & 8) != 0) {
            str4 = "";
        }
        payPresenter.payOrderF(str, str2, str3, str4);
    }

    public final void payOrderF(@NotNull String str, @NotNull String str2, @Nullable String str3, @Nullable String str4) {
        Intrinsics.checkParameterIsNotNull(str, "tradeId");
        Intrinsics.checkParameterIsNotNull(str2, "payGroup");
        SodaService sodaService = (SodaService) RetrofitUtil.getInstance().create(SodaService.class);
        if (Intrinsics.areEqual(str3, "")) {
            str3 = null;
        }
        if (Intrinsics.areEqual(str4, "")) {
            str4 = null;
        }
        sodaService.payOrder(str3, str2, str, str4).compose(RxUtil.applyIO()).subscribe(new PayPresenter$payOrderF$1(this, this.rootView));
    }

    public static /* synthetic */ void ecardPay$default(PayPresenter payPresenter, String str, BalanceInfo balanceInfo, String str2, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            str2 = "";
        }
        if ((i2 & 8) != 0) {
            i = 0;
        }
        payPresenter.ecardPay(str, balanceInfo, str2, i);
    }

    public final void ecardPay(@NotNull String str, @NotNull BalanceInfo balanceInfo, @NotNull String str2, int i) {
        Intrinsics.checkParameterIsNotNull(str, "tradeId");
        Intrinsics.checkParameterIsNotNull(balanceInfo, "balanceInfo");
        Intrinsics.checkParameterIsNotNull(str2, K.couponNo);
        if (!Intrinsics.areEqual(str2, "")) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(getThridPayData(i, Constant.COUPON_PAY).set(K.couponNo, str2));
            int shouldCouponPay = balanceInfo.shouldCouponPay();
            String payMode = balanceInfo.getPayMode();
            Intrinsics.checkExpressionValueIsNotNull(payMode, "balanceInfo.payMode");
            DataMap thridPayData = getThridPayData(shouldCouponPay, payMode);
            String payMode2 = balanceInfo.getPayMode();
            if (payMode2 != null) {
                int hashCode = payMode2.hashCode();
                if (hashCode != 1569) {
                    if (hashCode != 53666678) {
                    }
                    thridPayData.put("depositId", balanceInfo.getId());
                } else if (payMode2.equals(Constant.DEPOSIT_PAY)) {
                    thridPayData.put("productId", balanceInfo.getId());
                    thridPayData.put("validTag", balanceInfo.getValidTag());
                }
            }
            arrayList.add(thridPayData);
            ((SodaService) RetrofitUtil.getInstance().create(SodaService.class)).payOrder(balanceInfo.getEcardNo(), GsonUtils.toJson(arrayList), str, "").compose(RxUtil.applyIO()).subscribe(new PayPresenter$ecardPay$2(this, this.rootView));
            return;
        }
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(this.REAL_PAY, balanceInfo.shouldPayLeft());
        jSONObject.put(this.PAY_MONEY, balanceInfo.getPayMoney());
        jSONObject.put(this.PAY_MODE, balanceInfo.getPayMode());
        String valueOf = balanceInfo.getOrderDiscount() <= 0 ? "" : String.valueOf(balanceInfo.getOrderDiscount());
        String payMode3 = balanceInfo.getPayMode();
        if (payMode3 != null) {
            int hashCode2 = payMode3.hashCode();
            if (hashCode2 != 1569) {
                if (hashCode2 != 53666678) {
                }
                jSONObject.put("depositId", balanceInfo.getId());
            } else if (payMode3.equals(Constant.DEPOSIT_PAY)) {
                jSONObject.put("productId", balanceInfo.getId());
                jSONObject.put("validTag", balanceInfo.getValidTag());
            }
        }
        jSONArray.put(jSONObject);
        ((SodaService) RetrofitUtil.getInstance().create(SodaService.class)).payOrder(balanceInfo.getEcardNo(), jSONArray.toString(), str, valueOf).compose(RxUtil.applyIO()).subscribe(new PayPresenter$ecardPay$$inlined$apply$lambda$1(this.rootView, this, jSONObject, balanceInfo, jSONArray, str));
    }

    public static /* synthetic */ void callAli$default(PayPresenter payPresenter, String str, int i, int i2, String str2, String str3, int i3, int i4, Object obj) {
        if ((i4 & 16) != 0) {
            str3 = "";
        }
        payPresenter.callAli(str, i, i2, str2, str3, (i4 & 32) != 0 ? 0 : i3);
    }

    public final void callAli(@NotNull String str, int i, int i2, @NotNull String str2, @NotNull String str3, int i3) {
        Intrinsics.checkParameterIsNotNull(str, "tradeId");
        Intrinsics.checkParameterIsNotNull(str2, WebViewDetailActivity.TITLE);
        Intrinsics.checkParameterIsNotNull(str3, K.couponNo);
        if (!checkIsAlipayInstall()) {
            this.rootView.showMsg("请先安装支付宝");
            return;
        }
        ArrayList arrayList = new ArrayList();
        if (!Intrinsics.areEqual(str3, "")) {
            arrayList.add(getThridPayData(i3 > i ? i : i3, Constant.COUPON_PAY).set(K.couponNo, str3));
            if (i3 >= i) {
                String json = GsonUtils.toJson(arrayList);
                Intrinsics.checkExpressionValueIsNotNull(json, "GsonUtils.toJson(payGroup)");
                payOrderF$default(this, str, json, (String) null, (String) null, 12, (Object) null);
                return;
            }
        }
        arrayList.add(getThridPayData(i - i3, Constant.ALI_PAY));
        callMultiPay(str, i2, arrayList).flatMap(new PayPresenter$callAli$$inlined$apply$lambda$1(this, str2, i, i3, i2, str)).compose(RxUtil.applyErrorsWithIO()).subscribe(new PayPresenter$callAli$$inlined$apply$lambda$2(this.rootView, this, str2, i, i3, i2, str));
    }

    public final void callAlipay(@NotNull String str, @NotNull String str2) {
        Intrinsics.checkParameterIsNotNull(str, "alipaySign");
        Intrinsics.checkParameterIsNotNull(str2, "tradeId");
        Observable.just(str).map(new PayPresenter$callAlipay$1(this)).compose(RxUtil.applyIO()).map(PayPresenter$callAlipay$2.INSTANCE).subscribe(new PayPresenter$callAlipay$3(this, str2, this.rootView));
    }

    public final void checkAliPayResult(@NotNull PayResult payResult, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(payResult, "payResult");
        Intrinsics.checkParameterIsNotNull(str, "tradeId");
        payResult.getResult();
        String resultStatus = payResult.getResultStatus();
        PayResultEvent payResultEvent = new PayResultEvent();
        payResultEvent.setPayMode("支付宝支付");
        CharSequence charSequence = resultStatus;
        if (TextUtils.equals(charSequence, "9000")) {
            payResultEvent.setSuccess(true);
            payResultEvent.setMessage("支付成功");
            payResultEvent.setTradeId(str);
            EventHandler.getInstance().setPayResult("1");
        } else if (TextUtils.equals(charSequence, "8000")) {
            payResultEvent.setMessage("支付宝结果确认中");
            payResultEvent.setTradeId(str);
            payResultEvent.setSuccess(false);
        } else {
            payResultEvent.setMessage("支付失败");
            payResultEvent.setTradeId(str);
            payResultEvent.setSuccess(false);
            EventHandler.getInstance().setPayResult("");
        }
        RxBus.get().post(payResultEvent);
    }

    public static /* synthetic */ void callWexin$default(PayPresenter payPresenter, String str, int i, int i2, String str2, String str3, int i3, int i4, Object obj) {
        if ((i4 & 16) != 0) {
            str3 = "";
        }
        payPresenter.callWexin(str, i, i2, str2, str3, (i4 & 32) != 0 ? 0 : i3);
    }

    public final void callWexin(@NotNull String str, int i, int i2, @NotNull String str2, @NotNull String str3, int i3) {
        Intrinsics.checkParameterIsNotNull(str, "tradeId");
        Intrinsics.checkParameterIsNotNull(str2, WebViewDetailActivity.TITLE);
        Intrinsics.checkParameterIsNotNull(str3, K.couponNo);
        if (!checkIsWechatInstall()) {
            this.rootView.showMsg("请先安装微信");
            return;
        }
        ArrayList arrayList = new ArrayList();
        if (!Intrinsics.areEqual(str3, "")) {
            arrayList.add(getThridPayData(i3 > i ? i : i3, Constant.COUPON_PAY).set(K.couponNo, str3));
            if (i3 >= i) {
                String json = GsonUtils.toJson(arrayList);
                Intrinsics.checkExpressionValueIsNotNull(json, "GsonUtils.toJson(payGroup)");
                payOrderF$default(this, str, json, (String) null, (String) null, 12, (Object) null);
                return;
            }
        }
        arrayList.add(getThridPayData(i - i3, Constant.WECHAT_PAY));
        callMultiPay(str, i2, arrayList).flatMap(new PayPresenter$callWexin$$inlined$apply$lambda$1(this, str2, i, i3, i2, str)).compose(RxUtil.applyErrorsWithIO()).subscribe(new PayPresenter$callWexin$$inlined$apply$lambda$2(this.rootView, this, str2, i, i3, i2, str));
    }

    public final void callWechat(@NotNull WechatPayInfo wechatPayInfo) {
        Intrinsics.checkParameterIsNotNull(wechatPayInfo, "wechatPayInfo");
        Constant.APPID = wechatPayInfo.getAppid();
        IView iView = this.rootView;
        if (iView != null) {
            IWXAPI createWXAPI = WXAPIFactory.createWXAPI(StubApp.getOrigApplicationContext(((Activity) iView).getApplicationContext()), wechatPayInfo.getAppid());
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
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.app.Activity");
    }

    public final boolean checkIsWechatInstall() {
        IView iView = this.rootView;
        if (iView != null) {
            IWXAPI createWXAPI = WXAPIFactory.createWXAPI((Activity) iView, (String) null);
            Intrinsics.checkExpressionValueIsNotNull(createWXAPI, "msgApi");
            return createWXAPI.isWXAppInstalled();
        }
        throw new TypeCastException("null cannot be cast to non-null type android.app.Activity");
    }

    public final boolean checkIsAlipayInstall() {
        IView iView = this.rootView;
        if (iView != null) {
            PackageManager packageManager = ((Activity) iView).getPackageManager();
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse("alipays://"));
            List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, 64);
            return queryIntentActivities != null && !queryIntentActivities.isEmpty();
        }
        throw new TypeCastException("null cannot be cast to non-null type android.app.Activity");
    }
}

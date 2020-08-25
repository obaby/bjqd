package cn.xports.order;

import android.text.TextUtils;
import cn.xports.base.Constant;
import cn.xports.baselib.bean.Response;
import cn.xports.baselib.http.ApiResponseFunc;
import cn.xports.baselib.http.RxDisposableManager;
import cn.xports.baselib.http.RxUtil;
import cn.xports.baselib.mvp.BasePresenter;
import cn.xports.baselib.util.DateUtil;
import cn.xports.baselib.util.RxBus;
import cn.xports.entity.PayResult;
import cn.xports.entity.PayResultEvent;
import cn.xports.export.EventHandler;
import cn.xports.order.OrderPayContract;
import cn.xports.qd2.entity.K;
import io.reactivex.Observable;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\u001e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\rJ0\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u00142\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\bH\u0002J\u001e\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\rJ\u0016\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0010\u001a\u00020\bJ\u0016\u0010\u001b\u001a\u00020\u000f2\u0006\u0010\u001c\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\bJ\u000e\u0010\u001e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\bJ\u0016\u0010\u001f\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010 \u001a\u00020\bR\u000e\u0010\u0007\u001a\u00020\bXD¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bXD¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bXD¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\bXD¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u000e¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcn/xports/order/OrderPayPresenter;", "Lcn/xports/baselib/mvp/BasePresenter;", "Lcn/xports/order/OrderPayContract$Model;", "Lcn/xports/order/OrderPayContract$View;", "model", "view", "(Lcn/xports/order/OrderPayContract$Model;Lcn/xports/order/OrderPayContract$View;)V", "CASH", "", "PAY_MODE", "PAY_MONEY", "REAL_PAY", "acountId", "", "callAli", "", "tradeId", "totalFee", "cash", "callPay", "Lio/reactivex/Observable;", "Lcn/xports/baselib/bean/Response;", "payMode", "callWechat", "checkAliPayResult", "payResult", "Lcn/xports/entity/PayResult;", "countDownTime", "systemDate", "expireTime", "getPayCards", "getPayInfo", "ecardNo", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: OrderPayPresenter.kt */
public final class OrderPayPresenter extends BasePresenter<OrderPayContract.Model, OrderPayContract.View> {
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

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public OrderPayPresenter(@NotNull OrderPayContract.Model model, @NotNull OrderPayContract.View view) {
        super(model, view);
        Intrinsics.checkParameterIsNotNull(model, "model");
        Intrinsics.checkParameterIsNotNull(view, "view");
    }

    public final void getPayInfo(@NotNull String str, @NotNull String str2) {
        Intrinsics.checkParameterIsNotNull(str, "tradeId");
        Intrinsics.checkParameterIsNotNull(str2, K.ecardNo);
        OrderPayContract.Model model = (OrderPayContract.Model) getModel();
        if (model != null) {
            model.getPayOrderInfo(str, str2).subscribe(new OrderPayPresenter$getPayInfo$$inlined$apply$lambda$1(getRootView(), this, str, str2));
        }
    }

    public final void getPayCards(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "tradeId");
        OrderPayContract.Model model = (OrderPayContract.Model) getModel();
        if (model != null) {
            model.getPayCardList(str).subscribe(new OrderPayPresenter$getPayCards$$inlined$apply$lambda$1(getRootView(), this, str));
        }
    }

    public final void countDownTime(@NotNull String str, @NotNull String str2) {
        Intrinsics.checkParameterIsNotNull(str, "systemDate");
        Intrinsics.checkParameterIsNotNull(str2, "expireTime");
        Ref.LongRef longRef = new Ref.LongRef();
        longRef.element = (DateUtil.parse2Time(str2, "yyyy-MM-dd HH:mm:ss") - DateUtil.parse2Time(str, "yyyy-MM-dd HH:mm:ss")) / ((long) 1000);
        if (longRef.element <= 0) {
            longRef.element = 0;
        }
        RxDisposableManager.getInstance().add("time", Observable.interval(0, 1, TimeUnit.SECONDS).take(longRef.element + 1).compose(RxUtil.applyIO()).subscribe(new OrderPayPresenter$countDownTime$sub$1(this, longRef), OrderPayPresenter$countDownTime$sub$2.INSTANCE, OrderPayPresenter$countDownTime$sub$3.INSTANCE));
    }

    private final Observable<Response> callPay(String str, int i, int i2, String str2) {
        OrderPayContract.Model model = (OrderPayContract.Model) getModel();
        if (model != null) {
            return model.getThirdAccountId(str).map(new ApiResponseFunc()).flatMap(new OrderPayPresenter$callPay$$inlined$apply$lambda$1(model, this, str, str2, i, i2)).map(new ApiResponseFunc());
        }
        return null;
    }

    public final void callAli(@NotNull String str, int i, int i2) {
        Observable<Response> callPay;
        Intrinsics.checkParameterIsNotNull(str, "tradeId");
        OrderPayContract.Model model = (OrderPayContract.Model) getModel();
        if (model != null && (callPay = callPay(str, i, i2, Constant.ALI_PAY)) != null) {
            callPay.flatMap(new OrderPayPresenter$callAli$$inlined$apply$lambda$1(model, this, str, i, i2)).compose(RxUtil.applyErrorsWithIO()).subscribe(new OrderPayPresenter$callAli$$inlined$apply$lambda$2(getTAG(), model, this, str, i, i2));
        }
    }

    public final void callWechat(@NotNull String str, int i, int i2) {
        Observable<Response> callPay;
        Intrinsics.checkParameterIsNotNull(str, "tradeId");
        OrderPayContract.Model model = (OrderPayContract.Model) getModel();
        if (model != null && (callPay = callPay(str, i, i2, Constant.WECHAT_PAY)) != null) {
            callPay.flatMap(new OrderPayPresenter$callWechat$$inlined$apply$lambda$1(model, this, str, i, i2)).compose(RxUtil.applyErrorsWithIO()).subscribe(new OrderPayPresenter$callWechat$$inlined$apply$lambda$2(getTAG(), model, this, str, i, i2));
        }
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
}

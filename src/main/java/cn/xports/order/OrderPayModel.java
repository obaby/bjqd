package cn.xports.order;

import cn.xports.base.BaseBusModel;
import cn.xports.baselib.bean.Response;
import cn.xports.baselib.http.RxUtil;
import cn.xports.entity.PayCardResult;
import cn.xports.order.OrderPayContract;
import cn.xports.parser.AliPaySignParser;
import cn.xports.parser.OrderPayParser;
import cn.xports.parser.ThirdPayAccountParser;
import cn.xports.parser.ThirdPayBodyParser;
import cn.xports.parser.WeChatPayParser;
import cn.xports.qd2.entity.K;
import io.reactivex.Observable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J.\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\bH\u0016J\u0016\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u00052\u0006\u0010\u000b\u001a\u00020\bH\u0016J\u001e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00052\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\bH\u0016J\u0016\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u00052\u0006\u0010\u000b\u001a\u00020\bH\u0016J\u0016\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u00052\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J.\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u00162\u0006\u0010\u000b\u001a\u00020\bH\u0016J&\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u00052\u0006\u0010\u001c\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\bH\u0016¨\u0006\u001e"}, d2 = {"Lcn/xports/order/OrderPayModel;", "Lcn/xports/base/BaseBusModel;", "Lcn/xports/order/OrderPayContract$Model;", "()V", "getAliPayOrder", "Lio/reactivex/Observable;", "Lcn/xports/parser/AliPaySignParser;", "body", "", "subject", "totalFee", "tradeId", "getPayCardList", "Lcn/xports/entity/PayCardResult;", "getPayOrderInfo", "Lcn/xports/parser/OrderPayParser;", "ecardNo", "getThirdAccountId", "Lcn/xports/parser/ThirdPayAccountParser;", "getThirdPayBody", "Lcn/xports/parser/ThirdPayBodyParser;", "tradeType", "", "getWeChatSign", "Lcn/xports/parser/WeChatPayParser;", "attach", "saveTradeAttach", "Lcn/xports/baselib/bean/Response;", "payGroup", "cashInfo", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: OrderPayModel.kt */
public final class OrderPayModel extends BaseBusModel implements OrderPayContract.Model {
    @NotNull
    public Observable<PayCardResult> getPayCardList(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "tradeId");
        Observable<PayCardResult> compose = this.service.getPayCardList(str).compose(RxUtil.applyErrorsWithIO());
        Intrinsics.checkExpressionValueIsNotNull(compose, "service.getPayCardList(t…sWithIO<PayCardResult>())");
        return compose;
    }

    @NotNull
    public Observable<ThirdPayBodyParser> getThirdPayBody(int i) {
        Observable<ThirdPayBodyParser> thirdPayBody = this.service.getThirdPayBody("third_pay_trade_type", i);
        Intrinsics.checkExpressionValueIsNotNull(thirdPayBody, "service.getThirdPayBody(…ay_trade_type\",tradeType)");
        return thirdPayBody;
    }

    @NotNull
    public Observable<ThirdPayAccountParser> getThirdAccountId(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "tradeId");
        Observable<ThirdPayAccountParser> thirdAccountId = this.service.getThirdAccountId(str);
        Intrinsics.checkExpressionValueIsNotNull(thirdAccountId, "service.getThirdAccountId(tradeId)");
        return thirdAccountId;
    }

    @NotNull
    public Observable<AliPaySignParser> getAliPayOrder(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4) {
        Intrinsics.checkParameterIsNotNull(str, "body");
        Intrinsics.checkParameterIsNotNull(str2, "subject");
        Intrinsics.checkParameterIsNotNull(str3, "totalFee");
        Intrinsics.checkParameterIsNotNull(str4, "tradeId");
        Observable<AliPaySignParser> aliPayOrder = this.service.getAliPayOrder(str, str2, str3, str4);
        Intrinsics.checkExpressionValueIsNotNull(aliPayOrder, "service.getAliPayOrder(b…bject, totalFee, tradeId)");
        return aliPayOrder;
    }

    @NotNull
    public Observable<WeChatPayParser> getWeChatSign(@NotNull String str, @NotNull String str2, int i, @NotNull String str3) {
        Intrinsics.checkParameterIsNotNull(str, "body");
        Intrinsics.checkParameterIsNotNull(str2, "attach");
        Intrinsics.checkParameterIsNotNull(str3, "tradeId");
        Observable<WeChatPayParser> weChatSign = this.service.getWeChatSign(str, str2, i, str3);
        Intrinsics.checkExpressionValueIsNotNull(weChatSign, "service.getWeChatSign(bo…ttach, totalFee, tradeId)");
        return weChatSign;
    }

    @NotNull
    public Observable<Response> saveTradeAttach(@NotNull String str, @NotNull String str2, @NotNull String str3) {
        Intrinsics.checkParameterIsNotNull(str, "payGroup");
        Intrinsics.checkParameterIsNotNull(str2, "cashInfo");
        Intrinsics.checkParameterIsNotNull(str3, "tradeId");
        Observable<Response> saveTradeAttach = this.service.saveTradeAttach(str, str2, str3);
        Intrinsics.checkExpressionValueIsNotNull(saveTradeAttach, "service.saveTradeAttach(…yGroup,cashInfo, tradeId)");
        return saveTradeAttach;
    }

    @NotNull
    public Observable<OrderPayParser> getPayOrderInfo(@NotNull String str, @NotNull String str2) {
        Intrinsics.checkParameterIsNotNull(str, "tradeId");
        Intrinsics.checkParameterIsNotNull(str2, K.ecardNo);
        Observable<OrderPayParser> compose = this.service.getPayOrderInfo(str, str2).compose(RxUtil.applyErrorsWithIO());
        Intrinsics.checkExpressionValueIsNotNull(compose, "service.getPayOrderInfo(…WithIO<OrderPayParser>())");
        return compose;
    }
}

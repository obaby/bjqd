package cn.xports.order;

import cn.xports.baselib.bean.Response;
import cn.xports.baselib.mvp.IModel;
import cn.xports.baselib.mvp.IView;
import cn.xports.entity.PayCardResult;
import cn.xports.entity.WechatPayInfo;
import cn.xports.parser.AliPaySignParser;
import cn.xports.parser.OrderPayParser;
import cn.xports.parser.ThirdPayAccountParser;
import cn.xports.parser.ThirdPayBodyParser;
import cn.xports.parser.WeChatPayParser;
import io.reactivex.Observable;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0005"}, d2 = {"Lcn/xports/order/OrderPayContract;", "", "()V", "Model", "View", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: OrderPayContract.kt */
public final class OrderPayContract {

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J.\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006H&J\u0016\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00032\u0006\u0010\t\u001a\u00020\u0006H&J\u001e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u00032\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u0006H&J\u0016\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u00032\u0006\u0010\t\u001a\u00020\u0006H&J\u0016\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u00032\u0006\u0010\u0013\u001a\u00020\u0014H&J.\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\u0006H&J&\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u00032\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006H&¨\u0006\u001c"}, d2 = {"Lcn/xports/order/OrderPayContract$Model;", "Lcn/xports/baselib/mvp/IModel;", "getAliPayOrder", "Lio/reactivex/Observable;", "Lcn/xports/parser/AliPaySignParser;", "body", "", "subject", "totalFee", "tradeId", "getPayCardList", "Lcn/xports/entity/PayCardResult;", "getPayOrderInfo", "Lcn/xports/parser/OrderPayParser;", "ecardNo", "getThirdAccountId", "Lcn/xports/parser/ThirdPayAccountParser;", "getThirdPayBody", "Lcn/xports/parser/ThirdPayBodyParser;", "tradeType", "", "getWeChatSign", "Lcn/xports/parser/WeChatPayParser;", "attach", "saveTradeAttach", "Lcn/xports/baselib/bean/Response;", "payGroup", "cashInfo", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
    /* compiled from: OrderPayContract.kt */
    public interface Model extends IModel {
        @NotNull
        Observable<AliPaySignParser> getAliPayOrder(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4);

        @NotNull
        Observable<PayCardResult> getPayCardList(@NotNull String str);

        @NotNull
        Observable<OrderPayParser> getPayOrderInfo(@NotNull String str, @NotNull String str2);

        @NotNull
        Observable<ThirdPayAccountParser> getThirdAccountId(@NotNull String str);

        @NotNull
        Observable<ThirdPayBodyParser> getThirdPayBody(int i);

        @NotNull
        Observable<WeChatPayParser> getWeChatSign(@NotNull String str, @NotNull String str2, int i, @NotNull String str3);

        @NotNull
        Observable<Response> saveTradeAttach(@NotNull String str, @NotNull String str2, @NotNull String str3);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH&J\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000eH&J\u0018\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u000eH&J\u0012\u0010\u0012\u001a\u00020\u00032\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H&¨\u0006\u0015"}, d2 = {"Lcn/xports/order/OrderPayContract$View;", "Lcn/xports/baselib/mvp/IView;", "callAlipay", "", "alipaySign", "", "callWechat", "wechatPayInfo", "Lcn/xports/entity/WechatPayInfo;", "onCardList", "cardResult", "Lcn/xports/entity/PayCardResult;", "onPayResult", "result", "", "showCountDownTime", "minute", "second", "showOrderPayInfo", "payParser", "Lcn/xports/parser/OrderPayParser;", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
    /* compiled from: OrderPayContract.kt */
    public interface View extends IView {
        void callAlipay(@NotNull String str);

        void callWechat(@NotNull WechatPayInfo wechatPayInfo);

        void onCardList(@NotNull PayCardResult payCardResult);

        void onPayResult(int i);

        void showCountDownTime(int i, int i2);

        void showOrderPayInfo(@Nullable OrderPayParser orderPayParser);
    }
}

package cn.xports.order;

import cn.xports.baselib.http.ProcessObserver;
import cn.xports.baselib.http.ResponseThrowable;
import cn.xports.entity.WechatPayInfo;
import cn.xports.order.OrderPayContract;
import cn.xports.parser.WeChatPayParser;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000?\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002H\u0016J\u0012\u0010\u0006\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\t¸\u0006\n"}, d2 = {"cn/xports/order/OrderPayPresenter$callWechat$1$1$2", "Lcn/xports/baselib/http/ProcessObserver;", "Lcn/xports/parser/WeChatPayParser;", "next", "", "value", "onError", "responseThrowable", "Lcn/xports/baselib/http/ResponseThrowable;", "xports_productRelease", "cn/xports/order/OrderPayPresenter$$special$$inlined$apply$lambda$4"}, k = 1, mv = {1, 1, 15})
/* compiled from: OrderPayPresenter.kt */
public final class OrderPayPresenter$callWechat$$inlined$apply$lambda$2 extends ProcessObserver<WeChatPayParser> {
    final /* synthetic */ int $cash$inlined;
    final /* synthetic */ OrderPayContract.Model $this_apply$inlined;
    final /* synthetic */ int $totalFee$inlined;
    final /* synthetic */ String $tradeId$inlined;
    final /* synthetic */ OrderPayPresenter this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    OrderPayPresenter$callWechat$$inlined$apply$lambda$2(String str, OrderPayContract.Model model, OrderPayPresenter orderPayPresenter, String str2, int i, int i2) {
        super(str);
        this.$this_apply$inlined = model;
        this.this$0 = orderPayPresenter;
        this.$tradeId$inlined = str2;
        this.$totalFee$inlined = i;
        this.$cash$inlined = i2;
    }

    public void next(@Nullable WeChatPayParser weChatPayParser) {
        OrderPayContract.View view;
        if (weChatPayParser != null && (view = (OrderPayContract.View) this.this$0.getRootView()) != null) {
            WechatPayInfo wechatPayInfo = weChatPayParser.getWechatPayInfo();
            Intrinsics.checkExpressionValueIsNotNull(wechatPayInfo, "wechatPayInfo");
            view.callWechat(wechatPayInfo);
        }
    }

    public void onError(@Nullable ResponseThrowable responseThrowable) {
        super.onError(responseThrowable);
        OrderPayContract.View view = (OrderPayContract.View) this.this$0.getRootView();
        if (view != null) {
            if (responseThrowable == null) {
                Intrinsics.throwNpe();
            }
            String message = responseThrowable.getMessage();
            if (message == null) {
                message = "";
            }
            view.showMsg(message);
        }
    }
}

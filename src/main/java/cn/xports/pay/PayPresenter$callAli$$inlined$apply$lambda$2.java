package cn.xports.pay;

import cn.xports.baselib.http.ProcessObserver;
import cn.xports.baselib.http.ResponseThrowable;
import cn.xports.baselib.mvp.IView;
import cn.xports.parser.AliPaySignParser;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002H\u0016J\u0012\u0010\u0006\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\t¸\u0006\u0000"}, d2 = {"cn/xports/pay/PayPresenter$callAli$1$2", "Lcn/xports/baselib/http/ProcessObserver;", "Lcn/xports/parser/AliPaySignParser;", "next", "", "value", "onError", "responseThrowable", "Lcn/xports/baselib/http/ResponseThrowable;", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: PayPresenter.kt */
public final class PayPresenter$callAli$$inlined$apply$lambda$2 extends ProcessObserver<AliPaySignParser> {
    final /* synthetic */ int $cash$inlined;
    final /* synthetic */ int $couponMoney$inlined;
    final /* synthetic */ String $title$inlined;
    final /* synthetic */ int $totalFee$inlined;
    final /* synthetic */ String $tradeId$inlined;
    final /* synthetic */ PayPresenter this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PayPresenter$callAli$$inlined$apply$lambda$2(IView iView, PayPresenter payPresenter, String str, int i, int i2, int i3, String str2) {
        super(iView);
        this.this$0 = payPresenter;
        this.$title$inlined = str;
        this.$totalFee$inlined = i;
        this.$couponMoney$inlined = i2;
        this.$cash$inlined = i3;
        this.$tradeId$inlined = str2;
    }

    public void next(@Nullable AliPaySignParser aliPaySignParser) {
        if (aliPaySignParser != null) {
            PayPresenter payPresenter = this.this$0;
            String signString = aliPaySignParser.getSignString();
            Intrinsics.checkExpressionValueIsNotNull(signString, "signString");
            payPresenter.callAlipay(signString, this.$tradeId$inlined);
        }
    }

    public void onError(@Nullable ResponseThrowable responseThrowable) {
        super.onError(responseThrowable);
        IView rootView = this.this$0.getRootView();
        if (responseThrowable == null) {
            Intrinsics.throwNpe();
        }
        String message = responseThrowable.getMessage();
        if (message == null) {
            message = "";
        }
        rootView.showMsg(message);
    }
}

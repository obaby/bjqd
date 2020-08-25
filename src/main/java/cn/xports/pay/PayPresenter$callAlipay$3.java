package cn.xports.pay;

import cn.xports.baselib.http.ProcessObserver;
import cn.xports.baselib.mvp.IView;
import cn.xports.entity.PayResult;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002H\u0016¨\u0006\u0006"}, d2 = {"cn/xports/pay/PayPresenter$callAlipay$3", "Lcn/xports/baselib/http/ProcessObserver;", "Lcn/xports/entity/PayResult;", "next", "", "value", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: PayPresenter.kt */
public final class PayPresenter$callAlipay$3 extends ProcessObserver<PayResult> {
    final /* synthetic */ String $tradeId;
    final /* synthetic */ PayPresenter this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PayPresenter$callAlipay$3(PayPresenter payPresenter, String str, IView iView) {
        super(iView);
        this.this$0 = payPresenter;
        this.$tradeId = str;
    }

    public void next(@Nullable PayResult payResult) {
        if (payResult != null) {
            this.this$0.checkAliPayResult(payResult, this.$tradeId);
        }
    }
}

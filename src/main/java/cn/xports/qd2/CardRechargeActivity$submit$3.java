package cn.xports.qd2;

import cn.xports.base.Constant;
import cn.xports.baselib.http.ResponseJsonWrap;
import cn.xports.baselib.mvp.IView;
import cn.xports.pay.PayPresenter;
import cn.xports.widget.PaySelectView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016¨\u0006\u0006"}, d2 = {"cn/xports/qd2/CardRechargeActivity$submit$3", "Lcn/xports/baselib/http/ResponseJsonWrap;", "onSuccess", "", "result", "Lorg/json/JSONObject;", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: CardRechargeActivity.kt */
public final class CardRechargeActivity$submit$3 extends ResponseJsonWrap {
    final /* synthetic */ CardRechargeActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CardRechargeActivity$submit$3(CardRechargeActivity cardRechargeActivity, IView iView) {
        super(iView);
        this.this$0 = cardRechargeActivity;
    }

    public void onSuccess(@Nullable JSONObject jSONObject) {
        if (jSONObject != null) {
            String string = jSONObject.getString("tradeId");
            PaySelectView paySelectView = (PaySelectView) this.this$0._$_findCachedViewById(R.id.payModeView);
            Intrinsics.checkExpressionValueIsNotNull(paySelectView, "payModeView");
            CharSequence payMode = paySelectView.getPayMode();
            if (payMode == null || payMode.length() == 0) {
                this.this$0.showMsg("请选择支付方式");
                return;
            }
            PaySelectView paySelectView2 = (PaySelectView) this.this$0._$_findCachedViewById(R.id.payModeView);
            Intrinsics.checkExpressionValueIsNotNull(paySelectView2, "payModeView");
            if (Intrinsics.areEqual(paySelectView2.getPayMode(), Constant.ALI_PAY)) {
                PayPresenter access$getPayPresenter$p = this.this$0.payPresenter;
                Intrinsics.checkExpressionValueIsNotNull(string, "tradeId");
                PayPresenter.callAli$default(access$getPayPresenter$p, string, this.this$0.subMoney, 0, "一卡通充值", (String) null, 0, 48, (Object) null);
                return;
            }
            PaySelectView paySelectView3 = (PaySelectView) this.this$0._$_findCachedViewById(R.id.payModeView);
            Intrinsics.checkExpressionValueIsNotNull(paySelectView3, "payModeView");
            if (Intrinsics.areEqual(paySelectView3.getPayMode(), Constant.WECHAT_PAY)) {
                PayPresenter access$getPayPresenter$p2 = this.this$0.payPresenter;
                Intrinsics.checkExpressionValueIsNotNull(string, "tradeId");
                PayPresenter.callWexin$default(access$getPayPresenter$p2, string, this.this$0.subMoney, 0, "一卡通充值", (String) null, 0, 48, (Object) null);
            }
        }
    }
}

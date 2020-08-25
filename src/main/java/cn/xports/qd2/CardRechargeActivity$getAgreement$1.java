package cn.xports.qd2;

import cn.xports.baselib.http.ProcessObserver;
import cn.xports.baselib.mvp.IView;
import cn.xports.entity.AgreementInfo;
import cn.xports.entity.AgreementResult;
import cn.xports.widget.AgreementBar;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002H\u0016¨\u0006\u0006"}, d2 = {"cn/xports/qd2/CardRechargeActivity$getAgreement$1", "Lcn/xports/baselib/http/ProcessObserver;", "Lcn/xports/entity/AgreementResult;", "next", "", "value", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: CardRechargeActivity.kt */
public final class CardRechargeActivity$getAgreement$1 extends ProcessObserver<AgreementResult> {
    final /* synthetic */ CardRechargeActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CardRechargeActivity$getAgreement$1(CardRechargeActivity cardRechargeActivity, IView iView) {
        super(iView);
        this.this$0 = cardRechargeActivity;
    }

    public void next(@Nullable AgreementResult agreementResult) {
        String str;
        if (agreementResult != null) {
            AgreementBar agreementBar = (AgreementBar) this.this$0._$_findCachedViewById(R.id.agreementBar);
            StringBuilder sb = new StringBuilder();
            sb.append("《");
            AgreementInfo agreement = agreementResult.getAgreement();
            if (agreement == null || (str = agreement.getAgreementName()) == null) {
                str = "充值协议";
            }
            sb.append(str);
            sb.append("》");
            agreementBar.setAgreementName(sb.toString());
            ((AgreementBar) this.this$0._$_findCachedViewById(R.id.agreementBar)).setAgreementClick(new CardRechargeActivity$getAgreement$1$next$$inlined$apply$lambda$1(agreementResult, this));
            if (agreementResult != null) {
                return;
            }
        }
        CardRechargeActivity$getAgreement$1 cardRechargeActivity$getAgreement$1 = this;
        AgreementBar agreementBar2 = (AgreementBar) this.this$0._$_findCachedViewById(R.id.agreementBar);
        Intrinsics.checkExpressionValueIsNotNull(agreementBar2, "agreementBar");
        agreementBar2.setVisibility(8);
        Unit unit = Unit.INSTANCE;
    }
}

package cn.xports.qd2;

import android.view.View;
import cn.xports.base.Router;
import cn.xports.baselib.util.SPUtil;
import cn.xports.entity.AgreementInfo;
import cn.xports.entity.AgreementResult;
import com.alipay.sdk.cons.c;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005¨\u0006\u0006"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick", "cn/xports/qd2/CardRechargeActivity$getAgreement$1$next$1$1"}, k = 3, mv = {1, 1, 15})
/* compiled from: CardRechargeActivity.kt */
final class CardRechargeActivity$getAgreement$1$next$$inlined$apply$lambda$1 implements View.OnClickListener {
    final /* synthetic */ AgreementResult $this_apply;
    final /* synthetic */ CardRechargeActivity$getAgreement$1 this$0;

    CardRechargeActivity$getAgreement$1$next$$inlined$apply$lambda$1(AgreementResult agreementResult, CardRechargeActivity$getAgreement$1 cardRechargeActivity$getAgreement$1) {
        this.$this_apply = agreementResult;
        this.this$0 = cardRechargeActivity$getAgreement$1;
    }

    public final void onClick(View view) {
        String str;
        String str2;
        Map hashMap = new HashMap();
        AgreementInfo agreement = this.$this_apply.getAgreement();
        if (agreement == null || (str = agreement.getAgreementName()) == null) {
            str = "充值协议";
        }
        hashMap.put(c.e, str);
        SPUtil instance = SPUtil.Companion.getINSTANCE();
        AgreementInfo agreement2 = this.$this_apply.getAgreement();
        if (agreement2 == null || (str2 = agreement2.getAgreementCont()) == null) {
            str2 = "";
        }
        instance.save("agreement", str2).apply();
        this.this$0.this$0.startActivity(Router.getIntent(Router.AGREEMENT, hashMap));
    }
}

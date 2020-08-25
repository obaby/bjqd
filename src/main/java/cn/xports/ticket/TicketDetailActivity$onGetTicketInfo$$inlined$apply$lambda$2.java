package cn.xports.ticket;

import android.content.Intent;
import android.view.View;
import cn.xports.base.AgreementActivity;
import cn.xports.baselib.util.SPUtil;
import cn.xports.entity.AgreementInfo;
import cn.xports.parser.TicketDetailParser;
import com.alipay.sdk.cons.c;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005¨\u0006\u0006"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick", "cn/xports/ticket/TicketDetailActivity$onGetTicketInfo$1$2"}, k = 3, mv = {1, 1, 15})
/* compiled from: TicketDetailActivity.kt */
final class TicketDetailActivity$onGetTicketInfo$$inlined$apply$lambda$2 implements View.OnClickListener {
    final /* synthetic */ TicketDetailParser $this_apply;
    final /* synthetic */ TicketDetailActivity this$0;

    TicketDetailActivity$onGetTicketInfo$$inlined$apply$lambda$2(TicketDetailParser ticketDetailParser, TicketDetailActivity ticketDetailActivity) {
        this.$this_apply = ticketDetailParser;
        this.this$0 = ticketDetailActivity;
    }

    public final void onClick(View view) {
        SPUtil instance = SPUtil.Companion.getINSTANCE();
        AgreementInfo agreementInfo = this.$this_apply.getAgreementInfo();
        Intrinsics.checkExpressionValueIsNotNull(agreementInfo, "agreementInfo");
        String agreementCont = agreementInfo.getAgreementCont();
        Intrinsics.checkExpressionValueIsNotNull(agreementCont, "agreementInfo.agreementCont");
        instance.save("agreement", agreementCont).apply();
        TicketDetailActivity ticketDetailActivity = this.this$0;
        Intent intent = new Intent(this.this$0, AgreementActivity.class);
        AgreementInfo agreementInfo2 = this.$this_apply.getAgreementInfo();
        Intrinsics.checkExpressionValueIsNotNull(agreementInfo2, "agreementInfo");
        ticketDetailActivity.startActivity(intent.putExtra(c.e, agreementInfo2.getAgreementName()));
    }
}

package cn.xports.qd2.campaign;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 1, 15})
/* compiled from: CampaignListActivity.kt */
final class CampaignListActivity$initView$3 extends Lambda implements Function0<Unit> {
    final /* synthetic */ CampaignListActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CampaignListActivity$initView$3(CampaignListActivity campaignListActivity) {
        super(0);
        this.this$0 = campaignListActivity;
    }

    public final void invoke() {
        this.this$0.getCampList(this.this$0.curPage + 1, this.this$0.curState);
    }
}

package cn.xports.qd2.campaign;

import cn.xports.qd2.shareCampaign.PopuSelectShare;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0003H\u0016¨\u0006\u0005"}, d2 = {"cn/xports/qd2/campaign/TeamInfoActivity$addNewMember$1", "Lcn/xports/qd2/shareCampaign/PopuSelectShare$SelectLisenter;", "add", "", "invite", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: TeamInfoActivity.kt */
public final class TeamInfoActivity$addNewMember$1 implements PopuSelectShare.SelectLisenter {
    final /* synthetic */ TeamInfoActivity this$0;

    TeamInfoActivity$addNewMember$1(TeamInfoActivity teamInfoActivity) {
        this.this$0 = teamInfoActivity;
    }

    public void add() {
        this.this$0.opType = 22;
        AddMemberActivityKt.EditMemberForResult(this.this$0, this.this$0.campId, this.this$0.memberList, this.this$0.maxCount, 22, true);
    }

    public void invite() {
        this.this$0.showShareDialog();
    }
}

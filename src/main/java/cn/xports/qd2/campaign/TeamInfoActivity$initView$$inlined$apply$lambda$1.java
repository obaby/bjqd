package cn.xports.qd2.campaign;

import cn.xports.qd2.adapter.TeamInfoAdapter;
import cn.xports.qd2.entity.MemberInfo;
import com.blankj.utilcode.util.SPUtils;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007¨\u0006\b"}, d2 = {"<anonymous>", "", "info", "Lcn/xports/qd2/entity/MemberInfo;", "kotlin.jvm.PlatformType", "opType", "", "onItemOp", "cn/xports/qd2/campaign/TeamInfoActivity$initView$1$2"}, k = 3, mv = {1, 1, 15})
/* compiled from: TeamInfoActivity.kt */
final class TeamInfoActivity$initView$$inlined$apply$lambda$1 implements TeamInfoAdapter.OnMemberOperationListener {
    final /* synthetic */ TeamInfoActivity this$0;

    TeamInfoActivity$initView$$inlined$apply$lambda$1(TeamInfoActivity teamInfoActivity) {
        this.this$0 = teamInfoActivity;
    }

    public final void onItemOp(MemberInfo memberInfo, int i) {
        this.this$0.curMember = memberInfo;
        this.this$0.editPos = this.this$0.memberList.indexOf(memberInfo);
        if (i == 20) {
            TeamInfoActivity teamInfoActivity = this.this$0;
            Intrinsics.checkExpressionValueIsNotNull(memberInfo, "info");
            String id = memberInfo.getId();
            Intrinsics.checkExpressionValueIsNotNull(id, "info.id");
            teamInfoActivity.removePlayer(id);
        } else if (i == 10) {
            SPUtils instance = SPUtils.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(memberInfo, "info");
            instance.put("playerId", memberInfo.getId());
            this.this$0.opType = 11;
            AddMemberActivityKt.EditMemberForResult(this.this$0, this.this$0.campId, CollectionsKt.arrayListOf(new MemberInfo[]{memberInfo}), 0, 11, true);
        } else if (i == 30) {
            SPUtils instance2 = SPUtils.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(memberInfo, "info");
            instance2.put("playerId", memberInfo.getId());
            SPUtils.getInstance().put("editable", this.this$0.editable);
            SPUtils.getInstance().put("teamEditable", this.this$0.teamEditable);
            AddMemberActivityKt.EditMemberForResult(this.this$0, this.this$0.campId, CollectionsKt.arrayListOf(new MemberInfo[]{memberInfo}), 0, 33, true);
        }
    }
}

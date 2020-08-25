package cn.xports.qd2.campaign;

import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.xports.baselib.http.ProcessObserver;
import cn.xports.baselib.mvp.IView;
import cn.xports.qd2.R;
import cn.xports.qd2.adapter.MemberRoundAdapter;
import cn.xports.qd2.entity.CampTeam;
import cn.xports.qd2.entity.MemberInfo;
import cn.xports.qd2.entity.TeamInfoResult;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002H\u0016¨\u0006\u0006"}, d2 = {"cn/xports/qd2/campaign/JoinedSportsDetailActivity$getDetail$2", "Lcn/xports/baselib/http/ProcessObserver;", "Lcn/xports/qd2/entity/TeamInfoResult;", "next", "", "value", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: JoinedSportsDetailActivity.kt */
public final class JoinedSportsDetailActivity$getDetail$2 extends ProcessObserver<TeamInfoResult> {
    final /* synthetic */ JoinedSportsDetailActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    JoinedSportsDetailActivity$getDetail$2(JoinedSportsDetailActivity joinedSportsDetailActivity, IView iView) {
        super(iView);
        this.this$0 = joinedSportsDetailActivity;
    }

    public void next(@Nullable TeamInfoResult teamInfoResult) {
        if (teamInfoResult != null) {
            this.this$0.members.clear();
            CampTeam publicCampTeam = teamInfoResult.getPublicCampTeam();
            if (publicCampTeam != null) {
                TextView textView = (TextView) this.this$0._$_findCachedViewById(R.id.tvTeamName);
                Intrinsics.checkExpressionValueIsNotNull(textView, "tvTeamName");
                String name = publicCampTeam.getName();
                if (name == null) {
                    name = "--";
                }
                textView.setText(name);
                TextView textView2 = (TextView) this.this$0._$_findCachedViewById(R.id.tvLeaderName);
                Intrinsics.checkExpressionValueIsNotNull(textView2, "tvLeaderName");
                String leaderName = publicCampTeam.getLeaderName();
                if (leaderName == null) {
                    leaderName = "--";
                }
                textView2.setText(leaderName);
                TextView textView3 = (TextView) this.this$0._$_findCachedViewById(R.id.tvPsptId);
                Intrinsics.checkExpressionValueIsNotNull(textView3, "tvPsptId");
                String psptId = publicCampTeam.getPsptId();
                if (psptId == null) {
                    psptId = "--";
                }
                textView3.setText(psptId);
                TextView textView4 = (TextView) this.this$0._$_findCachedViewById(R.id.tvPhone);
                Intrinsics.checkExpressionValueIsNotNull(textView4, "tvPhone");
                String contactPhone = publicCampTeam.getContactPhone();
                if (contactPhone == null) {
                    contactPhone = "--";
                }
                textView4.setText(contactPhone);
            }
            List<MemberInfo> normalPlayerList = teamInfoResult.getNormalPlayerList();
            if (normalPlayerList != null) {
                this.this$0.members.addAll(normalPlayerList);
            }
            List<MemberInfo> confirmPlayerList = teamInfoResult.getConfirmPlayerList();
            if (confirmPlayerList != null) {
                this.this$0.members.addAll(confirmPlayerList);
            }
            if (this.this$0.members.size() == 0) {
                TextView textView5 = (TextView) this.this$0._$_findCachedViewById(R.id.tvNoMemberTip);
                Intrinsics.checkExpressionValueIsNotNull(textView5, "tvNoMemberTip");
                textView5.setVisibility(0);
                RecyclerView recyclerView = (RecyclerView) this.this$0._$_findCachedViewById(R.id.rvMemberList);
                Intrinsics.checkExpressionValueIsNotNull(recyclerView, "rvMemberList");
                recyclerView.setVisibility(8);
            } else {
                TextView textView6 = (TextView) this.this$0._$_findCachedViewById(R.id.tvNoMemberTip);
                Intrinsics.checkExpressionValueIsNotNull(textView6, "tvNoMemberTip");
                textView6.setVisibility(8);
                RecyclerView recyclerView2 = (RecyclerView) this.this$0._$_findCachedViewById(R.id.rvMemberList);
                Intrinsics.checkExpressionValueIsNotNull(recyclerView2, "rvMemberList");
                recyclerView2.setVisibility(0);
                RecyclerView recyclerView3 = (RecyclerView) this.this$0._$_findCachedViewById(R.id.rvMemberList);
                Intrinsics.checkExpressionValueIsNotNull(recyclerView3, "rvMemberList");
                recyclerView3.getAdapter().notifyDataSetChanged();
            }
            ((RelativeLayout) this.this$0._$_findCachedViewById(R.id.rlAddMember)).setOnClickListener(new JoinedSportsDetailActivity$getDetail$2$next$$inlined$apply$lambda$1(teamInfoResult, this));
            MemberRoundAdapter access$getAdapter$p = this.this$0.adapter;
            if (access$getAdapter$p != null) {
                access$getAdapter$p.setListener(new JoinedSportsDetailActivity$getDetail$2$next$$inlined$apply$lambda$2(teamInfoResult, this));
            }
        }
    }
}

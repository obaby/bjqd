package cn.xports.qd2.campaign;

import android.support.v7.widget.RecyclerView;
import cn.xports.baselib.bean.Response;
import cn.xports.baselib.http.ProcessObserver;
import cn.xports.baselib.mvp.IView;
import cn.xports.baselib.util.RxBus;
import cn.xports.qd2.R;
import cn.xports.qd2.entity.MemberInfo;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002H\u0016¨\u0006\u0006"}, d2 = {"cn/xports/qd2/campaign/TeamInfoActivity$removePlayer$1", "Lcn/xports/baselib/http/ProcessObserver;", "Lcn/xports/baselib/bean/Response;", "next", "", "value", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: TeamInfoActivity.kt */
public final class TeamInfoActivity$removePlayer$1 extends ProcessObserver<Response> {
    final /* synthetic */ TeamInfoActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    TeamInfoActivity$removePlayer$1(TeamInfoActivity teamInfoActivity, IView iView) {
        super(iView);
        this.this$0 = teamInfoActivity;
    }

    public void next(@Nullable Response response) {
        Collection access$getMemberList$p = this.this$0.memberList;
        MemberInfo access$getCurMember$p = this.this$0.curMember;
        if (access$getMemberList$p != null) {
            TypeIntrinsics.asMutableCollection(access$getMemberList$p).remove(access$getCurMember$p);
            RecyclerView recyclerView = (RecyclerView) this.this$0._$_findCachedViewById(R.id.rvMemberList);
            Intrinsics.checkExpressionValueIsNotNull(recyclerView, "rvMemberList");
            recyclerView.getAdapter().notifyDataSetChanged();
            RxBus.get().post("update_success");
            this.this$0.updateTeamNum();
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableCollection<T>");
    }
}

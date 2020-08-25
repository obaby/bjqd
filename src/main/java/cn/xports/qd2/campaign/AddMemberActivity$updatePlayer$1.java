package cn.xports.qd2.campaign;

import cn.xports.baselib.bean.Response;
import cn.xports.baselib.http.ProcessObserver;
import cn.xports.baselib.mvp.IView;
import cn.xports.qd2.entity.MemberInfo;
import java.util.ArrayList;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002H\u0016¨\u0006\u0006"}, d2 = {"cn/xports/qd2/campaign/AddMemberActivity$updatePlayer$1", "Lcn/xports/baselib/http/ProcessObserver;", "Lcn/xports/baselib/bean/Response;", "next", "", "value", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: AddMemberActivity.kt */
public final class AddMemberActivity$updatePlayer$1 extends ProcessObserver<Response> {
    final /* synthetic */ MemberInfo $member;
    final /* synthetic */ AddMemberActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AddMemberActivity$updatePlayer$1(AddMemberActivity addMemberActivity, MemberInfo memberInfo, IView iView) {
        super(iView);
        this.this$0 = addMemberActivity;
        this.$member = memberInfo;
    }

    public void next(@Nullable Response response) {
        this.this$0.opType = 11;
        ArrayList access$getMemberList$p = this.this$0.memberList;
        if (access$getMemberList$p != null) {
            access$getMemberList$p.clear();
        }
        ArrayList access$getMemberList$p2 = this.this$0.memberList;
        if (access$getMemberList$p2 != null) {
            access$getMemberList$p2.add(this.$member);
        }
        this.this$0.finish();
    }
}

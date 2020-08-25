package cn.xports.qd2.campaign;

import cn.xports.qd2.adapter.MemberAddAdapter;
import cn.xports.qd2.entity.MemberInfo;
import java.util.ArrayList;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "memberInfo", "Lcn/xports/qd2/entity/MemberInfo;", "kotlin.jvm.PlatformType", "OnEdit"}, k = 3, mv = {1, 1, 15})
/* compiled from: SignInfoActivity.kt */
final class SignInfoActivity$initView$3 implements MemberAddAdapter.OnEditMemberListener {
    final /* synthetic */ SignInfoActivity this$0;

    SignInfoActivity$initView$3(SignInfoActivity signInfoActivity) {
        this.this$0 = signInfoActivity;
    }

    public final void OnEdit(MemberInfo memberInfo) {
        this.this$0.editPos = this.this$0.members.indexOf(memberInfo);
        ArrayList arrayList = new ArrayList();
        arrayList.add(memberInfo);
        this.this$0.opType = 11;
        AddMemberActivityKt.EditMemberForResult(this.this$0, this.this$0.campId, arrayList, 0, 11);
    }
}

package cn.xports.qd2.campaign;

import android.view.View;
import android.widget.LinearLayout;
import cn.xports.qd2.R;
import cn.xports.qd2.entity.MemberInfo;
import cn.xports.qd2.widget.ElementView;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 15})
/* compiled from: AddMemberActivity.kt */
final class AddMemberActivity$initView$3 implements View.OnClickListener {
    final /* synthetic */ AddMemberActivity this$0;

    AddMemberActivity$initView$3(AddMemberActivity addMemberActivity) {
        this.this$0 = addMemberActivity;
    }

    public final void onClick(View view) {
        ArrayList access$getMemberList$p;
        if (this.this$0.opType != 22 || (access$getMemberList$p = this.this$0.memberList) == null || this.this$0.maxCount == 0 || access$getMemberList$p.size() < this.this$0.maxCount) {
            MemberInfo createMemberInfo = ElementView.createMemberInfo((LinearLayout) this.this$0._$_findCachedViewById(R.id.llSignElements), (MemberInfo) null);
            if (createMemberInfo != null) {
                if (this.this$0.memberList == null) {
                    this.this$0.memberList = new ArrayList();
                }
                if (this.this$0.update) {
                    this.this$0.addPlayer(createMemberInfo, false);
                    return;
                }
                ArrayList access$getMemberList$p2 = this.this$0.memberList;
                if (access$getMemberList$p2 != null) {
                    access$getMemberList$p2.add(createMemberInfo);
                }
                LinearLayout linearLayout = (LinearLayout) this.this$0._$_findCachedViewById(R.id.llSignElements);
                Intrinsics.checkExpressionValueIsNotNull(linearLayout, "llSignElements");
                for (ElementView clear : CollectionsKt.reversed(SignInfoActivityKt.getElementViews(linearLayout))) {
                    clear.clear();
                }
                return;
            }
            return;
        }
        this.this$0.showMsg("团队人数已达上限！");
    }
}

package cn.xports.qd2.campaign;

import android.view.View;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 15})
/* compiled from: AddMemberActivity.kt */
final class AddMemberActivity$initView$2 implements View.OnClickListener {
    final /* synthetic */ AddMemberActivity this$0;

    AddMemberActivity$initView$2(AddMemberActivity addMemberActivity) {
        this.this$0 = addMemberActivity;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v19, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v14, resolved type: cn.xports.qd2.entity.MemberInfo} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onClick(android.view.View r5) {
        /*
            r4 = this;
            cn.xports.qd2.campaign.AddMemberActivity r5 = r4.this$0
            int r5 = r5.opType
            r0 = 22
            if (r5 != r0) goto L_0x002f
            cn.xports.qd2.campaign.AddMemberActivity r5 = r4.this$0
            java.util.ArrayList r5 = r5.memberList
            if (r5 == 0) goto L_0x002f
            cn.xports.qd2.campaign.AddMemberActivity r1 = r4.this$0
            int r1 = r1.maxCount
            if (r1 == 0) goto L_0x002f
            int r5 = r5.size()
            cn.xports.qd2.campaign.AddMemberActivity r1 = r4.this$0
            int r1 = r1.maxCount
            if (r5 < r1) goto L_0x002f
            cn.xports.qd2.campaign.AddMemberActivity r5 = r4.this$0
            java.lang.String r0 = "团队人数已达上限！"
            r5.showMsg(r0)
            return
        L_0x002f:
            cn.xports.qd2.campaign.AddMemberActivity r5 = r4.this$0
            int r1 = cn.xports.qd2.R.id.llSignElements
            android.view.View r5 = r5._$_findCachedViewById(r1)
            android.widget.LinearLayout r5 = (android.widget.LinearLayout) r5
            r1 = 0
            cn.xports.qd2.entity.MemberInfo r5 = cn.xports.qd2.widget.ElementView.createMemberInfo(r5, r1)
            if (r5 == 0) goto L_0x0131
            cn.xports.qd2.campaign.AddMemberActivity r2 = r4.this$0
            java.util.ArrayList r2 = r2.memberList
            if (r2 != 0) goto L_0x0052
            cn.xports.qd2.campaign.AddMemberActivity r2 = r4.this$0
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            r2.memberList = r3
        L_0x0052:
            java.lang.String r2 = "1"
            r5.setState(r2)
            cn.xports.qd2.campaign.AddMemberActivity r2 = r4.this$0
            int r2 = r2.opType
            if (r2 != r0) goto L_0x0081
            cn.xports.qd2.campaign.AddMemberActivity r0 = r4.this$0
            boolean r0 = r0.update
            if (r0 == 0) goto L_0x006f
            cn.xports.qd2.campaign.AddMemberActivity r0 = r4.this$0
            r1 = 1
            r0.addPlayer(r5, r1)
            goto L_0x0130
        L_0x006f:
            cn.xports.qd2.campaign.AddMemberActivity r0 = r4.this$0
            java.util.ArrayList r0 = r0.memberList
            if (r0 == 0) goto L_0x007a
            r0.add(r5)
        L_0x007a:
            cn.xports.qd2.campaign.AddMemberActivity r5 = r4.this$0
            r5.finish()
            goto L_0x0130
        L_0x0081:
            cn.xports.qd2.campaign.AddMemberActivity r0 = r4.this$0
            java.util.ArrayList r0 = r0.memberList
            r2 = 0
            if (r0 == 0) goto L_0x00b1
            int r0 = r0.size()
            if (r0 <= 0) goto L_0x00b1
            cn.xports.qd2.campaign.AddMemberActivity r5 = r4.this$0
            int r0 = cn.xports.qd2.R.id.llSignElements
            android.view.View r5 = r5._$_findCachedViewById(r0)
            android.widget.LinearLayout r5 = (android.widget.LinearLayout) r5
            cn.xports.qd2.campaign.AddMemberActivity r0 = r4.this$0
            java.util.ArrayList r0 = r0.memberList
            if (r0 == 0) goto L_0x00a9
            java.lang.Object r0 = r0.get(r2)
            r1 = r0
            cn.xports.qd2.entity.MemberInfo r1 = (cn.xports.qd2.entity.MemberInfo) r1
        L_0x00a9:
            cn.xports.qd2.entity.MemberInfo r5 = cn.xports.qd2.widget.ElementView.createMemberInfo(r5, r1)
            if (r5 == 0) goto L_0x00b0
            goto L_0x00b1
        L_0x00b0:
            return
        L_0x00b1:
            cn.xports.qd2.campaign.AddMemberActivity r0 = r4.this$0
            boolean r0 = r0.update
            if (r0 == 0) goto L_0x0115
            cn.xports.qd2.campaign.AddMemberActivity r0 = r4.this$0
            java.util.ArrayList r0 = r0.memberList
            if (r0 == 0) goto L_0x010f
            int r1 = r0.size()
            if (r1 <= 0) goto L_0x010f
            java.lang.Object r1 = r0.get(r2)
            java.lang.String r3 = "get(0)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r3)
            cn.xports.qd2.entity.MemberInfo r1 = (cn.xports.qd2.entity.MemberInfo) r1
            java.lang.String r1 = r1.getId()
            r5.setId(r1)
            java.lang.Object r1 = r0.get(r2)
            java.lang.String r3 = "get(0)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r3)
            cn.xports.qd2.entity.MemberInfo r1 = (cn.xports.qd2.entity.MemberInfo) r1
            java.lang.String r1 = r1.getEnrollId()
            r5.setEnrollId(r1)
            java.lang.Object r1 = r0.get(r2)
            java.lang.String r3 = "get(0)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r3)
            cn.xports.qd2.entity.MemberInfo r1 = (cn.xports.qd2.entity.MemberInfo) r1
            java.lang.String r1 = r1.getTeamId()
            r5.setTeamId(r1)
            java.lang.Object r0 = r0.get(r2)
            java.lang.String r1 = "get(0)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            cn.xports.qd2.entity.MemberInfo r0 = (cn.xports.qd2.entity.MemberInfo) r0
            java.lang.String r0 = r0.getAvatar()
            r5.setAvatar(r0)
        L_0x010f:
            cn.xports.qd2.campaign.AddMemberActivity r0 = r4.this$0
            r0.updatePlayer(r5)
            goto L_0x0130
        L_0x0115:
            cn.xports.qd2.campaign.AddMemberActivity r0 = r4.this$0
            java.util.ArrayList r0 = r0.memberList
            if (r0 == 0) goto L_0x0120
            r0.clear()
        L_0x0120:
            cn.xports.qd2.campaign.AddMemberActivity r0 = r4.this$0
            java.util.ArrayList r0 = r0.memberList
            if (r0 == 0) goto L_0x012b
            r0.add(r5)
        L_0x012b:
            cn.xports.qd2.campaign.AddMemberActivity r5 = r4.this$0
            r5.finish()
        L_0x0130:
            return
        L_0x0131:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.xports.qd2.campaign.AddMemberActivity$initView$2.onClick(android.view.View):void");
    }
}

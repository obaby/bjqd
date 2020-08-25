package cn.xports.qd2.campaign;

import cn.xports.baselib.http.ProcessObserver;
import cn.xports.baselib.mvp.IView;
import cn.xports.qd2.entity.AddPlayerResult;
import cn.xports.qd2.entity.MemberInfo;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002H\u0016¨\u0006\u0006"}, d2 = {"cn/xports/qd2/campaign/AddMemberActivity$addPlayer$1", "Lcn/xports/baselib/http/ProcessObserver;", "Lcn/xports/qd2/entity/AddPlayerResult;", "next", "", "value", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: AddMemberActivity.kt */
public final class AddMemberActivity$addPlayer$1 extends ProcessObserver<AddPlayerResult> {
    final /* synthetic */ boolean $finish;
    final /* synthetic */ MemberInfo $member;
    final /* synthetic */ AddMemberActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AddMemberActivity$addPlayer$1(AddMemberActivity addMemberActivity, MemberInfo memberInfo, boolean z, IView iView) {
        super(iView);
        this.this$0 = addMemberActivity;
        this.$member = memberInfo;
        this.$finish = z;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0005, code lost:
        r2 = r4.getPlayerInfo();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void next(@org.jetbrains.annotations.Nullable cn.xports.qd2.entity.AddPlayerResult r4) {
        /*
            r3 = this;
            cn.xports.qd2.entity.MemberInfo r0 = r3.$member
            r1 = 0
            if (r4 == 0) goto L_0x0010
            cn.xports.qd2.entity.MemberInfo r2 = r4.getPlayerInfo()
            if (r2 == 0) goto L_0x0010
            java.lang.String r2 = r2.getId()
            goto L_0x0011
        L_0x0010:
            r2 = r1
        L_0x0011:
            r0.setId(r2)
            cn.xports.qd2.entity.MemberInfo r0 = r3.$member
            if (r4 == 0) goto L_0x0023
            cn.xports.qd2.entity.MemberInfo r2 = r4.getPlayerInfo()
            if (r2 == 0) goto L_0x0023
            java.lang.String r2 = r2.getEnrollId()
            goto L_0x0024
        L_0x0023:
            r2 = r1
        L_0x0024:
            r0.setEnrollId(r2)
            cn.xports.qd2.entity.MemberInfo r0 = r3.$member
            if (r4 == 0) goto L_0x0036
            cn.xports.qd2.entity.MemberInfo r2 = r4.getPlayerInfo()
            if (r2 == 0) goto L_0x0036
            java.lang.String r2 = r2.getState()
            goto L_0x0037
        L_0x0036:
            r2 = r1
        L_0x0037:
            r0.setState(r2)
            cn.xports.qd2.entity.MemberInfo r0 = r3.$member
            if (r4 == 0) goto L_0x0048
            cn.xports.qd2.entity.MemberInfo r4 = r4.getPlayerInfo()
            if (r4 == 0) goto L_0x0048
            java.lang.String r1 = r4.getTeamId()
        L_0x0048:
            r0.setTeamId(r1)
            cn.xports.qd2.campaign.AddMemberActivity r4 = r3.this$0
            java.util.ArrayList r4 = r4.memberList
            if (r4 == 0) goto L_0x0058
            cn.xports.qd2.entity.MemberInfo r0 = r3.$member
            r4.add(r0)
        L_0x0058:
            boolean r4 = r3.$finish
            if (r4 == 0) goto L_0x0062
            cn.xports.qd2.campaign.AddMemberActivity r4 = r3.this$0
            r4.finish()
            goto L_0x0099
        L_0x0062:
            cn.xports.qd2.campaign.AddMemberActivity r4 = r3.this$0
            java.lang.String r0 = "保存成功，请继续添加"
            r4.showMsg(r0)
            cn.xports.qd2.campaign.AddMemberActivity r4 = r3.this$0
            int r0 = cn.xports.qd2.R.id.llSignElements
            android.view.View r4 = r4._$_findCachedViewById(r0)
            android.widget.LinearLayout r4 = (android.widget.LinearLayout) r4
            java.lang.String r0 = "llSignElements"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r4, r0)
            java.util.ArrayList r4 = cn.xports.qd2.campaign.SignInfoActivityKt.getElementViews(r4)
            java.lang.Iterable r4 = (java.lang.Iterable) r4
            java.util.List r4 = kotlin.collections.CollectionsKt.reversed(r4)
            java.lang.Iterable r4 = (java.lang.Iterable) r4
            java.util.Iterator r4 = r4.iterator()
        L_0x0089:
            boolean r0 = r4.hasNext()
            if (r0 == 0) goto L_0x0099
            java.lang.Object r0 = r4.next()
            cn.xports.qd2.widget.ElementView r0 = (cn.xports.qd2.widget.ElementView) r0
            r0.clear()
            goto L_0x0089
        L_0x0099:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.xports.qd2.campaign.AddMemberActivity$addPlayer$1.next(cn.xports.qd2.entity.AddPlayerResult):void");
    }
}

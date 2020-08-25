package cn.xports.qd2.campaign;

import android.content.Context;
import android.widget.LinearLayout;
import cn.xports.baselib.http.ProcessObserver;
import cn.xports.baselib.mvp.IView;
import cn.xports.qd2.R;
import cn.xports.qd2.entity.MemberInfo;
import cn.xports.qd2.entity.SignElementsResult;
import cn.xports.qd2.entity.ViewElementData;
import cn.xports.qd2.widget.ElementView;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002H\u0016¨\u0006\u0006"}, d2 = {"cn/xports/qd2/campaign/AddMemberActivity$getElements$1", "Lcn/xports/baselib/http/ProcessObserver;", "Lcn/xports/qd2/entity/SignElementsResult;", "next", "", "p0", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: AddMemberActivity.kt */
public final class AddMemberActivity$getElements$1 extends ProcessObserver<SignElementsResult> {
    final /* synthetic */ AddMemberActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AddMemberActivity$getElements$1(AddMemberActivity addMemberActivity, IView iView) {
        super(iView);
        this.this$0 = addMemberActivity;
    }

    public void next(@Nullable SignElementsResult signElementsResult) {
        List<ViewElementData> personalElements;
        if (!(signElementsResult == null || (personalElements = signElementsResult.getPersonalElements()) == null)) {
            if (this.this$0.opType == 11 || this.this$0.opType == 33) {
                if (this.this$0.update) {
                    this.this$0.getPlayerInfo(personalElements);
                } else {
                    for (ViewElementData viewElementData : personalElements) {
                        LinearLayout linearLayout = (LinearLayout) this.this$0._$_findCachedViewById(R.id.llSignElements);
                        Context context = this.this$0;
                        ArrayList access$getMemberList$p = this.this$0.memberList;
                        linearLayout.addView(ElementView.createElementView(context, viewElementData, access$getMemberList$p != null ? (MemberInfo) access$getMemberList$p.get(0) : null));
                    }
                }
                if (this.this$0.opType != 11) {
                    LinearLayout linearLayout2 = (LinearLayout) this.this$0._$_findCachedViewById(R.id.llSignElements);
                    Intrinsics.checkExpressionValueIsNotNull(linearLayout2, "llSignElements");
                    for (ElementView enable : SignInfoActivityKt.getElementViews(linearLayout2)) {
                        enable.setEnable(false);
                    }
                }
            } else {
                LinearLayout linearLayout3 = (LinearLayout) this.this$0._$_findCachedViewById(R.id.llSignElements);
                Intrinsics.checkExpressionValueIsNotNull(linearLayout3, "llSignElements");
                SignInfoActivityKt.setElementViews(linearLayout3, personalElements);
            }
        }
        if (this.this$0.opType == 11) {
            LinearLayout linearLayout4 = (LinearLayout) this.this$0._$_findCachedViewById(R.id.llSignElements);
            Intrinsics.checkExpressionValueIsNotNull(linearLayout4, "llSignElements");
            for (ElementView enable2 : SignInfoActivityKt.getElementViews(linearLayout4)) {
                enable2.setEnable(true);
            }
            this.this$0.mTvRight.performClick();
        }
    }
}

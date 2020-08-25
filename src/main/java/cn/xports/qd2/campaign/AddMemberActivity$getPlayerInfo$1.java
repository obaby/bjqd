package cn.xports.qd2.campaign;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.xports.baselib.http.ProcessObserver;
import cn.xports.baselib.http.ResponseThrowable;
import cn.xports.baselib.mvp.IView;
import cn.xports.qd2.R;
import cn.xports.qd2.entity.MemberInfo;
import cn.xports.qd2.entity.PlayerInfoResult;
import cn.xports.qd2.entity.ViewElementData;
import cn.xports.qd2.widget.ElementView;
import com.blankj.utilcode.util.SPUtils;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002H\u0016J\u0012\u0010\u0006\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\t"}, d2 = {"cn/xports/qd2/campaign/AddMemberActivity$getPlayerInfo$1", "Lcn/xports/baselib/http/ProcessObserver;", "Lcn/xports/qd2/entity/PlayerInfoResult;", "next", "", "value", "onError", "responseThrowable", "Lcn/xports/baselib/http/ResponseThrowable;", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: AddMemberActivity.kt */
public final class AddMemberActivity$getPlayerInfo$1 extends ProcessObserver<PlayerInfoResult> {
    final /* synthetic */ List $viewElementDatas;
    final /* synthetic */ AddMemberActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AddMemberActivity$getPlayerInfo$1(AddMemberActivity addMemberActivity, List list, IView iView) {
        super(iView);
        this.this$0 = addMemberActivity;
        this.$viewElementDatas = list;
    }

    public void next(@Nullable PlayerInfoResult playerInfoResult) {
        if (playerInfoResult != null) {
            for (ViewElementData createElementView : this.$viewElementDatas) {
                ((LinearLayout) this.this$0._$_findCachedViewById(R.id.llSignElements)).addView(ElementView.createElementView(this.this$0, createElementView, playerInfoResult.getCampPlayerInfo()));
            }
            if ((Intrinsics.areEqual(playerInfoResult.getSelf(), "1") && SPUtils.getInstance().getBoolean("editable")) || SPUtils.getInstance().getBoolean("teamEditable")) {
                TextView access$getMTvRight$p = this.this$0.mTvRight;
                Intrinsics.checkExpressionValueIsNotNull(access$getMTvRight$p, "mTvRight");
                access$getMTvRight$p.setVisibility(0);
                this.this$0.opType = 11;
            }
            LinearLayout linearLayout = (LinearLayout) this.this$0._$_findCachedViewById(R.id.llSignElements);
            Intrinsics.checkExpressionValueIsNotNull(linearLayout, "llSignElements");
            for (ElementView enable : SignInfoActivityKt.getElementViews(linearLayout)) {
                enable.setEnable(this.this$0.opType == 11);
            }
            LinearLayout linearLayout2 = (LinearLayout) this.this$0._$_findCachedViewById(R.id.llSignElements);
            Intrinsics.checkExpressionValueIsNotNull(linearLayout2, "llSignElements");
            for (ElementView enable2 : SignInfoActivityKt.getElementViews(linearLayout2)) {
                TextView access$getMTvRight$p2 = this.this$0.mTvRight;
                Intrinsics.checkExpressionValueIsNotNull(access$getMTvRight$p2, "mTvRight");
                enable2.setEnable(Intrinsics.areEqual(access$getMTvRight$p2.getText(), "取消编辑"));
            }
        }
    }

    public void onError(@Nullable ResponseThrowable responseThrowable) {
        super.onError(responseThrowable);
        for (ViewElementData viewElementData : this.$viewElementDatas) {
            LinearLayout linearLayout = (LinearLayout) this.this$0._$_findCachedViewById(R.id.llSignElements);
            Context context = this.this$0;
            ArrayList access$getMemberList$p = this.this$0.memberList;
            linearLayout.addView(ElementView.createElementView(context, viewElementData, access$getMemberList$p != null ? (MemberInfo) access$getMemberList$p.get(0) : null));
        }
        LinearLayout linearLayout2 = (LinearLayout) this.this$0._$_findCachedViewById(R.id.llSignElements);
        Intrinsics.checkExpressionValueIsNotNull(linearLayout2, "llSignElements");
        for (ElementView enable : SignInfoActivityKt.getElementViews(linearLayout2)) {
            enable.setEnable(this.this$0.opType == 11);
        }
        LinearLayout linearLayout3 = (LinearLayout) this.this$0._$_findCachedViewById(R.id.llSignElements);
        Intrinsics.checkExpressionValueIsNotNull(linearLayout3, "llSignElements");
        for (ElementView enable2 : SignInfoActivityKt.getElementViews(linearLayout3)) {
            TextView access$getMTvRight$p = this.this$0.mTvRight;
            Intrinsics.checkExpressionValueIsNotNull(access$getMTvRight$p, "mTvRight");
            enable2.setEnable(Intrinsics.areEqual(access$getMTvRight$p.getText(), "取消编辑"));
        }
    }
}

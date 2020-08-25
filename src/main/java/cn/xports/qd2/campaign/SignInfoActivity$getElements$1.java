package cn.xports.qd2.campaign;

import android.widget.LinearLayout;
import android.widget.TextView;
import cn.xports.baselib.http.ProcessObserver;
import cn.xports.baselib.mvp.IView;
import cn.xports.qd2.R;
import cn.xports.qd2.entity.SignElementsResult;
import cn.xports.qd2.entity.ViewElementData;
import cn.xports.qd2.widget.ElementView;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002H\u0016¨\u0006\u0006"}, d2 = {"cn/xports/qd2/campaign/SignInfoActivity$getElements$1", "Lcn/xports/baselib/http/ProcessObserver;", "Lcn/xports/qd2/entity/SignElementsResult;", "next", "", "p0", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: SignInfoActivity.kt */
public final class SignInfoActivity$getElements$1 extends ProcessObserver<SignElementsResult> {
    final /* synthetic */ SignInfoActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SignInfoActivity$getElements$1(SignInfoActivity signInfoActivity, IView iView) {
        super(iView);
        this.this$0 = signInfoActivity;
    }

    public void next(@Nullable SignElementsResult signElementsResult) {
        List<ViewElementData> teamElements;
        List<ViewElementData> personalElements;
        TextView textView = (TextView) this.this$0._$_findCachedViewById(R.id.tvSignNew);
        Intrinsics.checkExpressionValueIsNotNull(textView, "tvSignNew");
        textView.setVisibility(0);
        if (Intrinsics.areEqual(this.this$0.enrollType, "1")) {
            TextView textView2 = (TextView) this.this$0._$_findCachedViewById(R.id.tvSignNew);
            Intrinsics.checkExpressionValueIsNotNull(textView2, "tvSignNew");
            textView2.setText("立即报名");
            if (signElementsResult != null && (personalElements = signElementsResult.getPersonalElements()) != null) {
                for (ViewElementData createElementView : personalElements) {
                    ((LinearLayout) this.this$0._$_findCachedViewById(R.id.llSignElements)).addView(ElementView.createElementView(this.this$0, createElementView));
                }
                return;
            }
            return;
        }
        TextView textView3 = (TextView) this.this$0._$_findCachedViewById(R.id.tvSignNew);
        Intrinsics.checkExpressionValueIsNotNull(textView3, "tvSignNew");
        textView3.setText("立即创建团队");
        LinearLayout linearLayout = (LinearLayout) this.this$0._$_findCachedViewById(R.id.llAddMember);
        Intrinsics.checkExpressionValueIsNotNull(linearLayout, "llAddMember");
        linearLayout.setVisibility(0);
        if (signElementsResult != null && (teamElements = signElementsResult.getTeamElements()) != null) {
            for (ViewElementData createElementView2 : teamElements) {
                ((LinearLayout) this.this$0._$_findCachedViewById(R.id.llSignElements)).addView(ElementView.createElementView(this.this$0, createElementView2));
            }
        }
    }
}

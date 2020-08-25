package cn.xports.qd2.training;

import android.widget.LinearLayout;
import cn.xports.baselib.http.ProcessObserver;
import cn.xports.baselib.mvp.IView;
import cn.xports.qd2.R;
import cn.xports.qd2.entity.CourseElementsReslut;
import cn.xports.qd2.widget.FollowChangeView;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002H\u0016¨\u0006\u0006"}, d2 = {"cn/xports/qd2/training/TrainingSignActivity$getCourseEnrollElement$1", "Lcn/xports/baselib/http/ProcessObserver;", "Lcn/xports/qd2/entity/CourseElementsReslut;", "next", "", "value", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: TrainingSignActivity.kt */
public final class TrainingSignActivity$getCourseEnrollElement$1 extends ProcessObserver<CourseElementsReslut> {
    final /* synthetic */ TrainingSignActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    TrainingSignActivity$getCourseEnrollElement$1(TrainingSignActivity trainingSignActivity, IView iView) {
        super(iView);
        this.this$0 = trainingSignActivity;
    }

    public void next(@Nullable CourseElementsReslut courseElementsReslut) {
        if (courseElementsReslut != null) {
            Collection elementList = courseElementsReslut.getElementList();
            if (!(elementList == null || elementList.isEmpty())) {
                LinearLayout linearLayout = (LinearLayout) this.this$0._$_findCachedViewById(R.id.llAddMember);
                Intrinsics.checkExpressionValueIsNotNull(linearLayout, "llAddMember");
                linearLayout.setVisibility(0);
                ((FollowChangeView) this.this$0._$_findCachedViewById(R.id.followChangeView)).setData(courseElementsReslut.getElementList().get(0));
            }
        }
    }
}

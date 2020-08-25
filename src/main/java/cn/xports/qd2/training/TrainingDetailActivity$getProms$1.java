package cn.xports.qd2.training;

import android.widget.TextView;
import cn.xports.baselib.http.ProcessObserver;
import cn.xports.baselib.mvp.IView;
import cn.xports.qd2.R;
import cn.xports.qd2.entity.CoursePromsResult;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002H\u0016¨\u0006\u0006"}, d2 = {"cn/xports/qd2/training/TrainingDetailActivity$getProms$1", "Lcn/xports/baselib/http/ProcessObserver;", "Lcn/xports/qd2/entity/CoursePromsResult;", "next", "", "value", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: TrainingDetailActivity.kt */
public final class TrainingDetailActivity$getProms$1 extends ProcessObserver<CoursePromsResult> {
    final /* synthetic */ TrainingDetailActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    TrainingDetailActivity$getProms$1(TrainingDetailActivity trainingDetailActivity, IView iView) {
        super(iView);
        this.this$0 = trainingDetailActivity;
    }

    public void next(@Nullable CoursePromsResult coursePromsResult) {
        if (coursePromsResult != null) {
            Collection proms = coursePromsResult.getProms();
            if (!(proms == null || proms.isEmpty())) {
                this.this$0.proms.addAll(coursePromsResult.getProms());
                return;
            }
            this.this$0.notNeedProm = true;
            TextView textView = (TextView) this.this$0._$_findCachedViewById(R.id.tvProms);
            Intrinsics.checkExpressionValueIsNotNull(textView, "tvProms");
            textView.setText("暂无优惠信息");
        }
    }
}

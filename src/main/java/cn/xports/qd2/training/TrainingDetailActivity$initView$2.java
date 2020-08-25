package cn.xports.qd2.training;

import android.view.View;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 15})
/* compiled from: TrainingDetailActivity.kt */
final class TrainingDetailActivity$initView$2 implements View.OnClickListener {
    final /* synthetic */ TrainingDetailActivity this$0;

    TrainingDetailActivity$initView$2(TrainingDetailActivity trainingDetailActivity) {
        this.this$0 = trainingDetailActivity;
    }

    public final void onClick(View view) {
        if (this.this$0.proms.size() == 0) {
            this.this$0.showMsg("暂无优惠信息");
        } else {
            this.this$0.showPromDialog();
        }
    }
}

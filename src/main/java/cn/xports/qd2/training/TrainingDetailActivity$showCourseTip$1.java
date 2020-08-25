package cn.xports.qd2.training;

import cn.xports.qd2.dialog.CourseTipDialog;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "onClick"}, k = 3, mv = {1, 1, 15})
/* compiled from: TrainingDetailActivity.kt */
final class TrainingDetailActivity$showCourseTip$1 implements CourseTipDialog.OnButtonClick {
    final /* synthetic */ TrainingDetailActivity this$0;

    TrainingDetailActivity$showCourseTip$1(TrainingDetailActivity trainingDetailActivity) {
        this.this$0 = trainingDetailActivity;
    }

    public final void onClick(int i) {
        if (i == 1) {
            this.this$0.getStudents();
        }
    }
}

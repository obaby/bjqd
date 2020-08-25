package cn.xports.qd2.training;

import android.view.View;
import cn.xports.qd2.SelectBottomActivity;
import cn.xports.qd2.dialog.ChooseCourseDialog;
import cn.xports.qd2.entity.K;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 15})
/* compiled from: TrainingDetailActivity.kt */
final class TrainingDetailActivity$initView$4 implements View.OnClickListener {
    final /* synthetic */ TrainingDetailActivity this$0;

    TrainingDetailActivity$initView$4(TrainingDetailActivity trainingDetailActivity) {
        this.this$0 = trainingDetailActivity;
    }

    public final void onClick(View view) {
        if (Intrinsics.areEqual(this.this$0.privateTag, K.k0)) {
            if (this.this$0.termAndLesson != null && this.this$0.selectCourseMap.size() == 0) {
                this.this$0.showMsg("请选择课时");
                return;
            } else if (ChooseCourseDialog.showChooseDay(this.this$0.termAndLesson) && !this.this$0.courseJson.isNotEmpty(K.startDate)) {
                this.this$0.showMsg("请选择开始时间");
                return;
            } else if (ChooseCourseDialog.showClassNum(this.this$0.termAndLesson) && !this.this$0.courseJson.isNotEmpty("shouldPay")) {
                this.this$0.showMsg("请选择课时数量");
                return;
            } else if (ChooseCourseDialog.showTerms(this.this$0.termAndLesson) && !this.this$0.courseJson.isNotEmpty("termId")) {
                this.this$0.showMsg("请选择课程期次");
                return;
            }
        }
        if (this.this$0.hasSelectProm()) {
            SelectBottomActivity.Companion companion = SelectBottomActivity.Companion;
            String tag = this.this$0.getTAG();
            Intrinsics.checkExpressionValueIsNotNull(tag, "TAG");
            companion.start(tag, this.this$0.cardList);
        }
    }
}

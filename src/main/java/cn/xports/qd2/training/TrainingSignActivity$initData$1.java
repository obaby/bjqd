package cn.xports.qd2.training;

import cn.xports.qd2.entity.StudentInfo;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lcn/xports/qd2/entity/StudentInfo;", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 15})
/* compiled from: TrainingSignActivity.kt */
final class TrainingSignActivity$initData$1<T> implements Consumer<StudentInfo> {
    final /* synthetic */ TrainingSignActivity this$0;

    TrainingSignActivity$initData$1(TrainingSignActivity trainingSignActivity) {
        this.this$0 = trainingSignActivity;
    }

    public final void accept(StudentInfo studentInfo) {
        TrainingSignActivity trainingSignActivity = this.this$0;
        Intrinsics.checkExpressionValueIsNotNull(studentInfo, "it");
        trainingSignActivity.fillInfo(studentInfo);
        if (Intrinsics.areEqual(this.this$0.opType, AddTrainingInfoActivity.ADD)) {
            this.this$0.studentList.add(studentInfo);
            return;
        }
        this.this$0.studentList.remove(this.this$0.infoPos);
        this.this$0.studentList.add(this.this$0.infoPos, studentInfo);
    }
}

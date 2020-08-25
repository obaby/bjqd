package cn.xports.qd2.training;

import android.view.View;
import cn.xports.base.BaseBottomDialog;
import cn.xports.qd2.dialog.ChooseStudentDialog;
import cn.xports.qd2.entity.StudentInfo;
import kotlin.Metadata;
import kotlin.TypeCastException;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 15})
/* compiled from: TrainingSignActivity.kt */
final class TrainingSignActivity$initView$1 implements View.OnClickListener {
    final /* synthetic */ TrainingSignActivity this$0;

    TrainingSignActivity$initView$1(TrainingSignActivity trainingSignActivity) {
        this.this$0 = trainingSignActivity;
    }

    public final void onClick(View view) {
        new ChooseStudentDialog(this.this$0).setData(this.this$0.studentList).setOnItemClickListener(new BaseBottomDialog.OnItemClickListener(this) {
            final /* synthetic */ TrainingSignActivity$initView$1 this$0;

            {
                this.this$0 = r1;
            }

            public final void onItemClick(int i, Object[] objArr) {
                if (i == 1) {
                    this.this$0.this$0.goAddStudent(AddTrainingInfoActivity.ADD);
                    return;
                }
                TrainingSignActivity trainingSignActivity = this.this$0.this$0;
                StudentInfo studentInfo = objArr[0];
                if (studentInfo != null) {
                    trainingSignActivity.fillInfo(studentInfo);
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type cn.xports.qd2.entity.StudentInfo");
            }
        }).show();
    }
}

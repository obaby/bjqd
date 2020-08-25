package cn.xports.qd2.training;

import cn.xports.baselib.http.ProcessObserver;
import cn.xports.baselib.http.ResponseThrowable;
import cn.xports.baselib.mvp.IView;
import cn.xports.qd2.entity.StudentInfo;
import cn.xports.qd2.entity.StudentListResult;
import com.blankj.utilcode.util.GsonUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002H\u0016J\u0012\u0010\u0006\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\t"}, d2 = {"cn/xports/qd2/training/TrainingDetailActivity$getStudents$1", "Lcn/xports/baselib/http/ProcessObserver;", "Lcn/xports/qd2/entity/StudentListResult;", "next", "", "value", "onError", "responseThrowable", "Lcn/xports/baselib/http/ResponseThrowable;", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: TrainingDetailActivity.kt */
public final class TrainingDetailActivity$getStudents$1 extends ProcessObserver<StudentListResult> {
    final /* synthetic */ ArrayList $students;
    final /* synthetic */ TrainingDetailActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    TrainingDetailActivity$getStudents$1(TrainingDetailActivity trainingDetailActivity, ArrayList arrayList, IView iView) {
        super(iView);
        this.this$0 = trainingDetailActivity;
        this.$students = arrayList;
    }

    public void next(@Nullable StudentListResult studentListResult) {
        if (studentListResult != null) {
            Collection studentList = studentListResult.getStudentList();
            if (!(studentList == null || studentList.isEmpty())) {
                if (Intrinsics.areEqual(this.this$0.privateTag, "1")) {
                    Iterator<StudentInfo> it = studentListResult.getStudentList().iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        StudentInfo next = it.next();
                        Intrinsics.checkExpressionValueIsNotNull(next, "info");
                        if (Intrinsics.areEqual(next.getSelfTag(), "1")) {
                            this.$students.add(next);
                            break;
                        }
                    }
                } else {
                    this.$students.addAll(studentListResult.getStudentList());
                }
            }
        }
        TrainingDetailActivity trainingDetailActivity = this.this$0;
        String json = GsonUtils.toJson(this.$students);
        Intrinsics.checkExpressionValueIsNotNull(json, "GsonUtils.toJson(students)");
        trainingDetailActivity.goSignInfo(json);
    }

    public void onError(@Nullable ResponseThrowable responseThrowable) {
        super.onError(responseThrowable);
        TrainingDetailActivity trainingDetailActivity = this.this$0;
        String json = GsonUtils.toJson(this.$students);
        Intrinsics.checkExpressionValueIsNotNull(json, "GsonUtils.toJson(students)");
        trainingDetailActivity.goSignInfo(json);
    }
}

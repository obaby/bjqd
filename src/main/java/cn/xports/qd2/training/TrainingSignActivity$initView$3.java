package cn.xports.qd2.training;

import android.view.View;
import cn.xports.baselib.bean.DataMap;
import cn.xports.qd2.entity.K;
import cn.xports.qd2.entity.StudentInfo;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.LogUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 15})
/* compiled from: TrainingSignActivity.kt */
final class TrainingSignActivity$initView$3 implements View.OnClickListener {
    final /* synthetic */ TrainingSignActivity this$0;

    TrainingSignActivity$initView$3(TrainingSignActivity trainingSignActivity) {
        this.this$0 = trainingSignActivity;
    }

    public final void onClick(View view) {
        String str;
        DataMap dataMap = (DataMap) GsonUtils.fromJson(this.this$0.courseJson, DataMap.class);
        if (dataMap != null) {
            StudentInfo access$getCurStudent$p = this.this$0.curStudent;
            dataMap.set(K.stuId, access$getCurStudent$p != null ? access$getCurStudent$p.getStuId() : null);
        }
        if (dataMap != null) {
            dataMap.set("newModelTag", "1");
        }
        TrainingSignActivity trainingSignActivity = this.this$0;
        if (dataMap == null || (str = dataMap.toJson()) == null) {
            str = "";
        }
        trainingSignActivity.courseJson = str;
        LogUtils.e(new Object[]{this.this$0.courseJson});
        if (Intrinsics.areEqual(this.this$0.privateTag, "1")) {
            this.this$0.submitPrivateTrain();
        } else {
            this.this$0.submitTrain();
        }
    }
}

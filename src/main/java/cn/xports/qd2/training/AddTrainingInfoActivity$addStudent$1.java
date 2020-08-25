package cn.xports.qd2.training;

import cn.xports.baselib.http.ResponseJsonWrap;
import cn.xports.baselib.mvp.IView;
import cn.xports.baselib.util.RxBus;
import cn.xports.qd2.entity.K;
import cn.xports.qd2.entity.StudentInfo;
import com.blankj.utilcode.util.JsonUtils;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016¨\u0006\u0006"}, d2 = {"cn/xports/qd2/training/AddTrainingInfoActivity$addStudent$1", "Lcn/xports/baselib/http/ResponseJsonWrap;", "onSuccess", "", "result", "Lorg/json/JSONObject;", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: AddTrainingInfoActivity.kt */
public final class AddTrainingInfoActivity$addStudent$1 extends ResponseJsonWrap {
    final /* synthetic */ StudentInfo $student;
    final /* synthetic */ AddTrainingInfoActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AddTrainingInfoActivity$addStudent$1(AddTrainingInfoActivity addTrainingInfoActivity, StudentInfo studentInfo, IView iView) {
        super(iView);
        this.this$0 = addTrainingInfoActivity;
        this.$student = studentInfo;
    }

    public void onSuccess(@Nullable JSONObject jSONObject) {
        if (jSONObject != null) {
            this.this$0.showMsg("保存成功");
            this.$student.setStuId(JsonUtils.getString(jSONObject, K.stuId));
            RxBus.get().post(this.$student);
            this.this$0.finish();
        }
    }
}

package cn.xports.qd2.training;

import cn.xports.base.Router;
import cn.xports.baselib.http.ResponseJsonWrap;
import cn.xports.baselib.mvp.IView;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016¨\u0006\u0006"}, d2 = {"cn/xports/qd2/training/TrainingSignActivity$submitPrivateTrain$1", "Lcn/xports/baselib/http/ResponseJsonWrap;", "onSuccess", "", "result", "Lorg/json/JSONObject;", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: TrainingSignActivity.kt */
public final class TrainingSignActivity$submitPrivateTrain$1 extends ResponseJsonWrap {
    final /* synthetic */ TrainingSignActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    TrainingSignActivity$submitPrivateTrain$1(TrainingSignActivity trainingSignActivity, IView iView) {
        super(iView);
        this.this$0 = trainingSignActivity;
    }

    public void onSuccess(@Nullable JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        if (jSONObject.has("tradeId")) {
            TrainingSignActivity trainingSignActivity = this.this$0;
            trainingSignActivity.startActivity(Router.getIntent("/orderPay?tradeId=" + jSONObject.getString("tradeId")));
            return;
        }
        this.this$0.showMsg("获取订单错误");
    }
}

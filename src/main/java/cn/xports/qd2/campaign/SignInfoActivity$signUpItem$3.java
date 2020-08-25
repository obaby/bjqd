package cn.xports.qd2.campaign;

import cn.xports.baselib.http.ResponseJsonWrap;
import cn.xports.baselib.mvp.IView;
import cn.xports.qd2.entity.K;
import com.blankj.utilcode.util.JsonUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016¨\u0006\u0006"}, d2 = {"cn/xports/qd2/campaign/SignInfoActivity$signUpItem$3", "Lcn/xports/baselib/http/ResponseJsonWrap;", "onSuccess", "", "result", "Lorg/json/JSONObject;", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: SignInfoActivity.kt */
public final class SignInfoActivity$signUpItem$3 extends ResponseJsonWrap {
    final /* synthetic */ SignInfoActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SignInfoActivity$signUpItem$3(SignInfoActivity signInfoActivity, IView iView) {
        super(iView);
        this.this$0 = signInfoActivity;
    }

    public void onSuccess(@Nullable JSONObject jSONObject) {
        if (jSONObject != null) {
            String string = JsonUtils.getString(jSONObject, K.enrollId);
            SignInfoActivity signInfoActivity = this.this$0;
            Intrinsics.checkExpressionValueIsNotNull(string, "id");
            signInfoActivity.getOrder(string);
        }
    }
}

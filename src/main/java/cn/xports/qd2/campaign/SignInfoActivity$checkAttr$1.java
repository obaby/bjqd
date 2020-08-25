package cn.xports.qd2.campaign;

import cn.xports.baselib.http.ResponseJsonWrap;
import cn.xports.baselib.mvp.IView;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\u0012\u0010\u0006\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016¨\u0006\u0007"}, d2 = {"cn/xports/qd2/campaign/SignInfoActivity$checkAttr$1", "Lcn/xports/baselib/http/ResponseJsonWrap;", "onError", "", "result", "Lorg/json/JSONObject;", "onSuccess", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: SignInfoActivity.kt */
public final class SignInfoActivity$checkAttr$1 extends ResponseJsonWrap {
    final /* synthetic */ SignInfoActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SignInfoActivity$checkAttr$1(SignInfoActivity signInfoActivity, IView iView) {
        super(iView);
        this.this$0 = signInfoActivity;
    }

    public void onSuccess(@Nullable JSONObject jSONObject) {
        if (jSONObject != null && jSONObject.has("attrValue")) {
            SignInfoActivity signInfoActivity = this.this$0;
            String string = jSONObject.getString("attrValue");
            if (string == null) {
                string = "2";
            }
            signInfoActivity.signUpItem(string);
        }
        this.this$0.signUpItem("2");
    }

    public void onError(@Nullable JSONObject jSONObject) {
        super.onError(jSONObject);
        this.this$0.signUpItem("2");
    }
}

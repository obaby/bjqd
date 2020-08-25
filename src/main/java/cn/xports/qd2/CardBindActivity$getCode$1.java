package cn.xports.qd2;

import cn.xports.baselib.http.ResponseJsonWrap;
import cn.xports.qd2.entity.K;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\u0012\u0010\u0006\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016¨\u0006\u0007"}, d2 = {"cn/xports/qd2/CardBindActivity$getCode$1", "Lcn/xports/baselib/http/ResponseJsonWrap;", "onError", "", "result", "Lorg/json/JSONObject;", "onSuccess", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: CardBindActivity.kt */
public final class CardBindActivity$getCode$1 extends ResponseJsonWrap {
    final /* synthetic */ CardBindActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CardBindActivity$getCode$1(CardBindActivity cardBindActivity, String str) {
        super(str);
        this.this$0 = cardBindActivity;
    }

    public void onSuccess(@Nullable JSONObject jSONObject) {
        this.this$0.verityCodeDown();
        if (jSONObject != null) {
            CardBindActivity cardBindActivity = this.this$0;
            String string = jSONObject.getString(K.message);
            if (string == null) {
                string = "发送成功";
            }
            cardBindActivity.showDialog(string);
        }
    }

    public void onError(@Nullable JSONObject jSONObject) {
        String str;
        super.onError(jSONObject);
        CardBindActivity cardBindActivity = this.this$0;
        if (jSONObject == null || (str = jSONObject.getString(K.message)) == null) {
            str = "未知错误";
        }
        cardBindActivity.showDialog(str);
    }
}

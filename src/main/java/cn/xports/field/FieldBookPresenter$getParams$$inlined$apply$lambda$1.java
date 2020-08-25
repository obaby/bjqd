package cn.xports.field;

import cn.xports.baselib.http.ResponseJsonWrap;
import cn.xports.field.FieldBookContract;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016¨\u0006\u0006¸\u0006\u0000"}, d2 = {"cn/xports/field/FieldBookPresenter$getParams$1$1", "Lcn/xports/baselib/http/ResponseJsonWrap;", "onSuccess", "", "result", "Lorg/json/JSONObject;", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: FieldBookPresenter.kt */
public final class FieldBookPresenter$getParams$$inlined$apply$lambda$1 extends ResponseJsonWrap {
    final /* synthetic */ String $serviceId$inlined;
    final /* synthetic */ String $venueId$inlined;
    final /* synthetic */ FieldBookPresenter this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FieldBookPresenter$getParams$$inlined$apply$lambda$1(String str, FieldBookPresenter fieldBookPresenter, String str2, String str3) {
        super(str);
        this.this$0 = fieldBookPresenter;
        this.$venueId$inlined = str2;
        this.$serviceId$inlined = str3;
    }

    public void onSuccess(@Nullable JSONObject jSONObject) {
        FieldBookContract.View view;
        if (jSONObject != null && (view = (FieldBookContract.View) this.this$0.getRootView()) != null) {
            String string = jSONObject.getString("wechat_default_field_full_tag");
            Intrinsics.checkExpressionValueIsNotNull(string, "getString(\"wechat_default_field_full_tag\")");
            String string2 = jSONObject.getString("wechat_court_message_" + this.$serviceId$inlined);
            Intrinsics.checkExpressionValueIsNotNull(string2, "getString(\"wechat_court_message_$serviceId\")");
            view.saveParam(string, string2);
        }
    }
}

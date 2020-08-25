package cn.xports.qd2.campaign;

import android.view.View;
import android.widget.LinearLayout;
import cn.xports.baselib.http.ResponseJsonWrap;
import cn.xports.baselib.mvp.IView;
import cn.xports.qd2.R;
import cn.xports.qd2.widget.ImagePickElementView;
import com.blankj.utilcode.util.SPUtils;
import kotlin.Metadata;
import kotlin.TypeCastException;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016¨\u0006\u0006"}, d2 = {"cn/xports/qd2/campaign/AddMemberActivity$uploadImg$1", "Lcn/xports/baselib/http/ResponseJsonWrap;", "onSuccess", "", "result", "Lorg/json/JSONObject;", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: AddMemberActivity.kt */
public final class AddMemberActivity$uploadImg$1 extends ResponseJsonWrap {
    final /* synthetic */ AddMemberActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AddMemberActivity$uploadImg$1(AddMemberActivity addMemberActivity, IView iView) {
        super(iView);
        this.this$0 = addMemberActivity;
    }

    public void onSuccess(@Nullable JSONObject jSONObject) {
        int i;
        if (jSONObject != null && jSONObject.has("url") && (i = SPUtils.getInstance().getInt("image_location", -1)) != -1) {
            View childAt = ((LinearLayout) this.this$0._$_findCachedViewById(R.id.llSignElements)).getChildAt(i);
            if (childAt != null) {
                ((ImagePickElementView) childAt).setMaxCount(1).addUrl(jSONObject.getString("url"));
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type cn.xports.qd2.widget.ImagePickElementView");
        }
    }
}

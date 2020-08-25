package cn.xports.qd2.campaign;

import android.content.Intent;
import cn.xports.baselib.bean.Response;
import cn.xports.baselib.http.ProcessObserver;
import cn.xports.baselib.http.ResponseThrowable;
import cn.xports.baselib.mvp.IView;
import cn.xports.qd2.entity.K;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002H\u0016J\u0012\u0010\u0006\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\t"}, d2 = {"cn/xports/qd2/campaign/ChooseSportActivity$check$1", "Lcn/xports/baselib/http/ProcessObserver;", "Lcn/xports/baselib/bean/Response;", "next", "", "p0", "onError", "responseThrowable", "Lcn/xports/baselib/http/ResponseThrowable;", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: ChooseSportActivity.kt */
public final class ChooseSportActivity$check$1 extends ProcessObserver<Response> {
    final /* synthetic */ String $id;
    final /* synthetic */ String $packageId;
    final /* synthetic */ String $type;
    final /* synthetic */ ChooseSportActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ChooseSportActivity$check$1(ChooseSportActivity chooseSportActivity, String str, String str2, String str3, IView iView) {
        super(iView);
        this.this$0 = chooseSportActivity;
        this.$packageId = str;
        this.$id = str2;
        this.$type = str3;
    }

    public void next(@Nullable Response response) {
        this.this$0.startActivity(new Intent(this.this$0, SignInfoActivity.class).putExtra(K.campId, this.this$0.campId).putExtra("packageId", this.$packageId).putExtra("campItemId", this.$id).putExtra("enrollType", this.$type));
    }

    public void onError(@Nullable ResponseThrowable responseThrowable) {
        String str;
        super.onError(responseThrowable);
        ChooseSportActivity chooseSportActivity = this.this$0;
        if (responseThrowable == null || (str = responseThrowable.getMessage()) == null) {
            str = "暂时无法报名";
        }
        chooseSportActivity.showMsg(str);
    }
}

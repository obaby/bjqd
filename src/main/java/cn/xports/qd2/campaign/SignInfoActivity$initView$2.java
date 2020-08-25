package cn.xports.qd2.campaign;

import android.view.View;
import cn.xports.qd2.R;
import cn.xports.widget.AgreementBar;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 15})
/* compiled from: SignInfoActivity.kt */
final class SignInfoActivity$initView$2 implements View.OnClickListener {
    final /* synthetic */ SignInfoActivity this$0;

    SignInfoActivity$initView$2(SignInfoActivity signInfoActivity) {
        this.this$0 = signInfoActivity;
    }

    public final void onClick(View view) {
        AgreementBar agreementBar = (AgreementBar) this.this$0._$_findCachedViewById(R.id.agreementBar);
        Intrinsics.checkExpressionValueIsNotNull(agreementBar, "agreementBar");
        if (!agreementBar.isCheck()) {
            this.this$0.showMsg("请同意协议");
        } else {
            this.this$0.signUpItem("1");
        }
    }
}

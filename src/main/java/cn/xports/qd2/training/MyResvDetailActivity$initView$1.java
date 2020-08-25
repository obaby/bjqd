package cn.xports.qd2.training;

import android.view.View;
import cn.xports.base.GlobalDialog;
import cn.xports.baselib.widget.FakeBoldText;
import cn.xports.qd2.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 15})
/* compiled from: MyResvDetailActivity.kt */
final class MyResvDetailActivity$initView$1 implements View.OnClickListener {
    final /* synthetic */ MyResvDetailActivity this$0;

    MyResvDetailActivity$initView$1(MyResvDetailActivity myResvDetailActivity) {
        this.this$0 = myResvDetailActivity;
    }

    public final void onClick(View view) {
        StringBuilder sb = new StringBuilder();
        sb.append("是否确定取消「");
        FakeBoldText fakeBoldText = (FakeBoldText) this.this$0._$_findCachedViewById(R.id.tvCourseName);
        Intrinsics.checkExpressionValueIsNotNull(fakeBoldText, "tvCourseName");
        sb.append(fakeBoldText.getText());
        sb.append("」预约");
        new GlobalDialog(this.this$0, sb.toString()).setButtonClick(new GlobalDialog.OnButtonClick(this) {
            final /* synthetic */ MyResvDetailActivity$initView$1 this$0;

            {
                this.this$0 = r1;
            }

            public final void onClick(int i) {
                if (i == 1) {
                    this.this$0.this$0.cancelResv();
                }
            }
        });
    }
}

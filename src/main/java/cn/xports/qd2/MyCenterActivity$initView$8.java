package cn.xports.qd2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import cn.xports.base.GlobalDialog;
import cn.xports.qd2.credit.MyCreditActivity;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 15})
/* compiled from: MyCenterActivity.kt */
final class MyCenterActivity$initView$8 implements View.OnClickListener {
    final /* synthetic */ MyCenterActivity this$0;

    MyCenterActivity$initView$8(MyCenterActivity myCenterActivity) {
        this.this$0 = myCenterActivity;
    }

    public final void onClick(View view) {
        if (this.this$0.cardNum == 0) {
            new GlobalDialog(this.this$0, "暂无会员卡，是否立即绑卡？").setButtonClick(new GlobalDialog.OnButtonClick(this) {
                final /* synthetic */ MyCenterActivity$initView$8 this$0;

                {
                    this.this$0 = r1;
                }

                public final void onClick(int i) {
                    if (i == 1) {
                        MyCenterActivity myCenterActivity = this.this$0.this$0;
                        myCenterActivity.startActivity(new Intent(myCenterActivity, CardBindActivity.class).putExtras(new Bundle()));
                    }
                }
            });
            return;
        }
        MyCenterActivity myCenterActivity = this.this$0;
        myCenterActivity.startActivity(new Intent(myCenterActivity, MyCreditActivity.class).putExtras(new Bundle()));
    }
}

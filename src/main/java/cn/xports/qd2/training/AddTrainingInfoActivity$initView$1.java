package cn.xports.qd2.training;

import android.view.View;
import cn.xports.baselib.util.RxBus;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 15})
/* compiled from: AddTrainingInfoActivity.kt */
final class AddTrainingInfoActivity$initView$1 implements View.OnClickListener {
    final /* synthetic */ AddTrainingInfoActivity this$0;

    AddTrainingInfoActivity$initView$1(AddTrainingInfoActivity addTrainingInfoActivity) {
        this.this$0 = addTrainingInfoActivity;
    }

    public final void onClick(View view) {
        if (Intrinsics.areEqual(this.this$0.getStringExtra("back"), "training_sign_finish")) {
            RxBus.get().post("training_sign_finish");
        }
        this.this$0.finish();
    }
}

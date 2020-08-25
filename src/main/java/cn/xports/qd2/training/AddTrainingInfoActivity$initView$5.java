package cn.xports.qd2.training;

import android.view.View;
import android.widget.TextView;
import cn.xports.qd2.R;
import cn.xports.qd2.dialog.SingleSelectDialog;
import cn.xports.qd2.entity.K;
import cn.xports.qd2.entity.ViewElementData;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 15})
/* compiled from: AddTrainingInfoActivity.kt */
final class AddTrainingInfoActivity$initView$5 implements View.OnClickListener {
    final /* synthetic */ AddTrainingInfoActivity this$0;

    AddTrainingInfoActivity$initView$5(AddTrainingInfoActivity addTrainingInfoActivity) {
        this.this$0 = addTrainingInfoActivity;
    }

    public final void onClick(View view) {
        new SingleSelectDialog(this.this$0).setTitle("性别").setOptions(CollectionsKt.arrayListOf(new ViewElementData.Option[]{new ViewElementData.Option().setName("男").setValue(K.k0), new ViewElementData.Option().setName("女").setValue("1")})).setOnItemSelectListener(new SingleSelectDialog.OnItemSelectListener(this) {
            final /* synthetic */ AddTrainingInfoActivity$initView$5 this$0;

            {
                this.this$0 = r1;
            }

            public final void onItemSelect(ViewElementData.Option option) {
                TextView textView = (TextView) this.this$0.this$0._$_findCachedViewById(R.id.tvGender);
                Intrinsics.checkExpressionValueIsNotNull(textView, "tvGender");
                Intrinsics.checkExpressionValueIsNotNull(option, "it");
                textView.setText(option.getName());
                AddTrainingInfoActivity addTrainingInfoActivity = this.this$0.this$0;
                String value = option.getValue();
                Intrinsics.checkExpressionValueIsNotNull(value, "it.value");
                addTrainingInfoActivity.gender = value;
            }
        }).show();
    }
}

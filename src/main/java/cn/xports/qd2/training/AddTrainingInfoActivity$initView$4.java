package cn.xports.qd2.training;

import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import cn.xports.qd2.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 15})
/* compiled from: AddTrainingInfoActivity.kt */
final class AddTrainingInfoActivity$initView$4 implements View.OnClickListener {
    final /* synthetic */ AddTrainingInfoActivity this$0;

    AddTrainingInfoActivity$initView$4(AddTrainingInfoActivity addTrainingInfoActivity) {
        this.this$0 = addTrainingInfoActivity;
    }

    public final void onClick(View view) {
        EditText editText = (EditText) this.this$0._$_findCachedViewById(R.id.etPhone);
        Intrinsics.checkExpressionValueIsNotNull(editText, "etPhone");
        if (editText.getText().length() != 11) {
            this.this$0.showMsg("请填写正确的手机号码");
            return;
        }
        EditText editText2 = (EditText) this.this$0._$_findCachedViewById(R.id.etHeight);
        Intrinsics.checkExpressionValueIsNotNull(editText2, "etHeight");
        Editable text = editText2.getText();
        Intrinsics.checkExpressionValueIsNotNull(text, "etHeight.text");
        boolean z = false;
        if (!(text.length() == 0)) {
            EditText editText3 = (EditText) this.this$0._$_findCachedViewById(R.id.etStudentName);
            Intrinsics.checkExpressionValueIsNotNull(editText3, "etStudentName");
            Editable text2 = editText3.getText();
            Intrinsics.checkExpressionValueIsNotNull(text2, "etStudentName.text");
            if (!(text2.length() == 0)) {
                TextView textView = (TextView) this.this$0._$_findCachedViewById(R.id.tvGender);
                Intrinsics.checkExpressionValueIsNotNull(textView, "tvGender");
                CharSequence text3 = textView.getText();
                Intrinsics.checkExpressionValueIsNotNull(text3, "tvGender.text");
                if (text3.length() == 0) {
                    z = true;
                }
                if (!z) {
                    if (Intrinsics.areEqual(this.this$0.opType, AddTrainingInfoActivity.ADD)) {
                        this.this$0.addStudent();
                        return;
                    } else {
                        this.this$0.editStudent();
                        return;
                    }
                }
            }
        }
        this.this$0.showMsg("请填写正确的信息");
    }
}

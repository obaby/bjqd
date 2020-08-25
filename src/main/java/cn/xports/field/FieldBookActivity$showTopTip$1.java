package cn.xports.field;

import android.text.Html;
import android.view.View;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 15})
/* compiled from: FieldBookActivity.kt */
final class FieldBookActivity$showTopTip$1 implements View.OnClickListener {
    final /* synthetic */ String $manual;
    final /* synthetic */ FieldBookActivity this$0;

    FieldBookActivity$showTopTip$1(FieldBookActivity fieldBookActivity, String str) {
        this.this$0 = fieldBookActivity;
        this.$manual = str;
    }

    public final void onClick(View view) {
        new FieldDialog(this.this$0, Html.fromHtml(this.$manual).toString());
    }
}

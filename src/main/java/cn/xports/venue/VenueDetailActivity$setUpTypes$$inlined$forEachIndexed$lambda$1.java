package cn.xports.venue;

import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import cn.xports.qdplugin.R;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005¨\u0006\u0006"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick", "cn/xports/venue/VenueDetailActivity$setUpTypes$1$1"}, k = 3, mv = {1, 1, 15})
/* compiled from: VenueDetailActivity.kt */
final class VenueDetailActivity$setUpTypes$$inlined$forEachIndexed$lambda$1 implements View.OnClickListener {
    final /* synthetic */ String $type;
    final /* synthetic */ CheckBox $typeCheckBox;
    final /* synthetic */ VenueDetailActivity this$0;

    VenueDetailActivity$setUpTypes$$inlined$forEachIndexed$lambda$1(CheckBox checkBox, String str, VenueDetailActivity venueDetailActivity) {
        this.$typeCheckBox = checkBox;
        this.$type = str;
        this.this$0 = venueDetailActivity;
    }

    public final void onClick(View view) {
        this.$typeCheckBox.setEnabled(false);
        this.this$0.updateCardTypeList(this.$type);
        LinearLayout linearLayout = (LinearLayout) this.this$0._$_findCachedViewById(R.id.llCardTypeList);
        Intrinsics.checkExpressionValueIsNotNull(linearLayout, "llCardTypeList");
        int childCount = linearLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (!Intrinsics.areEqual(((LinearLayout) this.this$0._$_findCachedViewById(R.id.llCardTypeList)).getChildAt(i), this.$typeCheckBox)) {
                View childAt = ((LinearLayout) this.this$0._$_findCachedViewById(R.id.llCardTypeList)).getChildAt(i);
                if (childAt != null) {
                    ((CheckBox) childAt).setChecked(false);
                    View childAt2 = ((LinearLayout) this.this$0._$_findCachedViewById(R.id.llCardTypeList)).getChildAt(i);
                    Intrinsics.checkExpressionValueIsNotNull(childAt2, "llCardTypeList.getChildAt(i)");
                    childAt2.setEnabled(true);
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type android.widget.CheckBox");
                }
            }
        }
    }
}

package cn.xports.qd2.campaign;

import android.view.View;
import android.widget.LinearLayout;
import cn.xports.qd2.entity.ViewElementData;
import cn.xports.qd2.widget.ElementView;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u001a\u001a\u0010\u0000\u001a\u0012\u0012\u0004\u0012\u00020\u00020\u0001j\b\u0012\u0004\u0012\u00020\u0002`\u0003*\u00020\u0004\u001a\n\u0010\u0005\u001a\u00020\u0006*\u00020\u0004\u001a\u0018\u0010\u0007\u001a\u00020\u0006*\u00020\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¨\u0006\u000b"}, d2 = {"getElementViews", "Ljava/util/ArrayList;", "Lcn/xports/qd2/widget/ElementView;", "Lkotlin/collections/ArrayList;", "Landroid/widget/LinearLayout;", "lastElementNoLine", "", "setElementViews", "elementData", "", "Lcn/xports/qd2/entity/ViewElementData;", "xports2_productRelease"}, k = 2, mv = {1, 1, 15})
/* compiled from: SignInfoActivity.kt */
public final class SignInfoActivityKt {
    @NotNull
    public static final ArrayList<ElementView> getElementViews(@NotNull LinearLayout linearLayout) {
        Intrinsics.checkParameterIsNotNull(linearLayout, "$this$getElementViews");
        ArrayList<ElementView> arrayList = new ArrayList<>();
        int childCount = linearLayout.getChildCount();
        if (childCount >= 0) {
            int i = 0;
            while (true) {
                View childAt = linearLayout.getChildAt(i);
                if (childAt instanceof ElementView) {
                    arrayList.add(childAt);
                    if (i == linearLayout.getChildCount() - 1) {
                        ((ElementView) childAt).showLine(false);
                    }
                }
                if (i == childCount) {
                    break;
                }
                i++;
            }
        }
        return arrayList;
    }

    public static final void setElementViews(@NotNull LinearLayout linearLayout, @NotNull List<? extends ViewElementData> list) {
        Intrinsics.checkParameterIsNotNull(linearLayout, "$this$setElementViews");
        Intrinsics.checkParameterIsNotNull(list, "elementData");
        for (ViewElementData createElementView : list) {
            linearLayout.addView(ElementView.createElementView(linearLayout.getContext(), createElementView));
        }
    }

    public static final void lastElementNoLine(@NotNull LinearLayout linearLayout) {
        Intrinsics.checkParameterIsNotNull(linearLayout, "$this$lastElementNoLine");
        int childCount = linearLayout.getChildCount();
        if (childCount >= 0) {
            int i = 0;
            while (true) {
                View childAt = linearLayout.getChildAt(i);
                if ((childAt instanceof ElementView) && i == linearLayout.getChildCount() - 1) {
                    ((ElementView) childAt).showLine(false);
                }
                if (i != childCount) {
                    i++;
                } else {
                    return;
                }
            }
        }
    }
}

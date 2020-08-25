package cn.xports.qd2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.xports.qd2.R;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eR \u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\u0005¨\u0006\u000f"}, d2 = {"Lcn/xports/qd2/adapter/TabLayoutAdapter;", "", "titleList", "", "", "(Ljava/util/List;)V", "getTitleList", "()Ljava/util/List;", "setTitleList", "getView", "Landroid/view/View;", "context", "Landroid/content/Context;", "position", "", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: TabLayoutAdapter.kt */
public final class TabLayoutAdapter {
    @NotNull
    private List<String> titleList;

    public TabLayoutAdapter(@NotNull List<String> list) {
        Intrinsics.checkParameterIsNotNull(list, "titleList");
        this.titleList = list;
    }

    @NotNull
    public final List<String> getTitleList() {
        return this.titleList;
    }

    public final void setTitleList(@NotNull List<String> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.titleList = list;
    }

    @NotNull
    public final View getView(@NotNull Context context, int i) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_tab_text, (ViewGroup) null);
        View findViewById = inflate.findViewById(R.id.tv_item_tab);
        if (findViewById != null) {
            ((TextView) findViewById).setText(this.titleList.get(i));
            Intrinsics.checkExpressionValueIsNotNull(inflate, "singleTabView");
            return inflate;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
    }
}

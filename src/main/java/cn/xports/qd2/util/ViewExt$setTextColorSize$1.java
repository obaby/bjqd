package cn.xports.qd2.util;

import android.support.design.widget.TabLayout;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\u0012\u0010\u0006\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\u0012\u0010\u0007\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016¨\u0006\b"}, d2 = {"cn/xports/qd2/util/ViewExt$setTextColorSize$1", "Landroid/support/design/widget/TabLayout$OnTabSelectedListener;", "onTabReselected", "", "tab", "Landroid/support/design/widget/TabLayout$Tab;", "onTabSelected", "onTabUnselected", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: ViewExt.kt */
public final class ViewExt$setTextColorSize$1 implements TabLayout.OnTabSelectedListener {
    final /* synthetic */ int $selectTextColorSrc;
    final /* synthetic */ float $selectTextSize;
    final /* synthetic */ int $unSelectedTextColorSrc;
    final /* synthetic */ float $unSelectedTextSize;

    public void onTabReselected(@Nullable TabLayout.Tab tab) {
    }

    ViewExt$setTextColorSize$1(int i, float f, int i2, float f2) {
        this.$unSelectedTextColorSrc = i;
        this.$unSelectedTextSize = f;
        this.$selectTextColorSrc = i2;
        this.$selectTextSize = f2;
    }

    public void onTabUnselected(@Nullable TabLayout.Tab tab) {
        if (tab != null) {
            ViewExt.changeTabText(tab, 8, this.$unSelectedTextColorSrc, this.$unSelectedTextSize);
        }
    }

    public void onTabSelected(@Nullable TabLayout.Tab tab) {
        if (tab != null) {
            ViewExt.changeTabText(tab, 0, this.$selectTextColorSrc, this.$selectTextSize);
        }
    }
}

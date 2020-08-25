package cn.xports.qd2.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0003H\u0016¨\u0006\u0007¸\u0006\b"}, d2 = {"cn/xports/qd2/util/ViewExt$showFragments$1$1$1", "Landroid/support/v4/app/FragmentPagerAdapter;", "getCount", "", "getItem", "Landroid/support/v4/app/Fragment;", "p0", "xports2_productRelease", "cn/xports/qd2/util/ViewExt$$special$$inlined$let$lambda$1"}, k = 1, mv = {1, 1, 15})
/* compiled from: ViewExt.kt */
public final class ViewExt$showFragments$$inlined$let$lambda$1 extends FragmentPagerAdapter {
    final /* synthetic */ List $fragments$inlined;
    final /* synthetic */ FragmentManager $manager$inlined;
    final /* synthetic */ ViewPager $this_showFragments$inlined;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ViewExt$showFragments$$inlined$let$lambda$1(FragmentManager fragmentManager, FragmentManager fragmentManager2, ViewPager viewPager, List list) {
        super(fragmentManager);
        this.$manager$inlined = fragmentManager2;
        this.$this_showFragments$inlined = viewPager;
        this.$fragments$inlined = list;
    }

    @NotNull
    public Fragment getItem(int i) {
        return (Fragment) this.$fragments$inlined.get(i);
    }

    public int getCount() {
        return this.$fragments$inlined.size();
    }
}

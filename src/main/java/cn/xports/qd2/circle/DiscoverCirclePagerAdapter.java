package cn.xports.qd2.circle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.List;

public class DiscoverCirclePagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;
    private List<String> titleList;

    public DiscoverCirclePagerAdapter(FragmentManager fragmentManager, List<String> list, List<Fragment> list2) {
        super(fragmentManager);
        this.titleList = list;
        this.fragmentList = list2;
    }

    public int getCount() {
        return this.titleList.size();
    }

    public Fragment getItem(int i) {
        return this.fragmentList.get(i);
    }

    @Nullable
    public CharSequence getPageTitle(int i) {
        return (this.titleList == null || this.titleList.size() <= i) ? "" : this.titleList.get(i);
    }
}

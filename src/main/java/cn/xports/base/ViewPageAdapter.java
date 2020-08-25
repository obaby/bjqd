package cn.xports.base;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.List;

public class ViewPageAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    private List<String> titles;

    public ViewPageAdapter(FragmentManager fragmentManager, List<Fragment> list, List<String> list2) {
        super(fragmentManager);
        this.fragments = list;
        this.titles = list2;
    }

    public ViewPageAdapter(FragmentManager fragmentManager, List<Fragment> list) {
        this(fragmentManager, list, (List<String>) null);
    }

    public Fragment getItem(int i) {
        return this.fragments.get(i);
    }

    public int getCount() {
        if (this.fragments == null) {
            return 0;
        }
        return this.fragments.size();
    }

    @Nullable
    public CharSequence getPageTitle(int i) {
        if (this.titles == null || this.titles.size() == 0) {
            return null;
        }
        return this.titles.get(i);
    }
}

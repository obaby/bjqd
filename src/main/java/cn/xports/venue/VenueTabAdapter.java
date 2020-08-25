package cn.xports.venue;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.List;

public class VenueTabAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    private List<String> titles;

    public VenueTabAdapter(FragmentManager fragmentManager, List<Fragment> list, List<String> list2) {
        super(fragmentManager);
        this.fragments = list;
        this.titles = list2;
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
        return this.titles.get(i);
    }
}

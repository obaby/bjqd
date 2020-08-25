package com.bsit.coband.home;

import com.bsit.coband.net.bean.HomeResponse;
import com.bsit.coband.net.bean.Module;
import com.convenient.qd.core.base.mvp.BaseView;
import com.convenient.qd.core.bean.MenuTab;
import java.util.List;

public interface IHomeAtView extends BaseView {
    void onBannerData(List<MenuTab> list);

    void onIconList(List<MenuTab> list);

    void onMoreSvList(List<Module> list);

    void onShowHomeData(HomeResponse homeResponse);

    void onSingleAd(Module module);

    void setTextBannerData(String str);
}

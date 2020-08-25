package com.bsit.coband.home.menumg;

import com.bsit.coband.net.bean.SubModule;
import com.convenient.qd.core.base.mvp.BaseView;
import java.util.List;

public interface IMenuAtView extends BaseView {
    void showAllMenuData(List<SubModule> list);
}

package com.alibaba.android.arouter.routes;

import com.alibaba.android.arouter.facade.enums.RouteType;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IRouteGroup;
import com.bsit.coband.home.menumg.MenuActivity;
import java.util.Map;

public class ARouter$$Group$$menu implements IRouteGroup {
    public void loadInto(Map<String, RouteMeta> map) {
        map.put("/menu/MenuActivity", RouteMeta.build(RouteType.ACTIVITY, MenuActivity.class, "/menu/menuactivity", "menu", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
    }
}

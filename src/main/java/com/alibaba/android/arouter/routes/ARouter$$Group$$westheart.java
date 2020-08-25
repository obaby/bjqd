package com.alibaba.android.arouter.routes;

import com.alibaba.android.arouter.facade.enums.RouteType;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IRouteGroup;
import com.bsit.coband.web.westheart.WestHeartActivity;
import java.util.Map;

public class ARouter$$Group$$westheart implements IRouteGroup {
    public void loadInto(Map<String, RouteMeta> map) {
        map.put("/westheart/WestHeartActivity", RouteMeta.build(RouteType.ACTIVITY, WestHeartActivity.class, "/westheart/westheartactivity", "westheart", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
    }
}

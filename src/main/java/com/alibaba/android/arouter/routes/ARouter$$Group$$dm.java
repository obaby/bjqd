package com.alibaba.android.arouter.routes;

import com.alibaba.android.arouter.facade.enums.RouteType;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IRouteGroup;
import com.convenient.module.dm.activity.MainActivity;
import java.util.Map;

public class ARouter$$Group$$dm implements IRouteGroup {
    public void loadInto(Map<String, RouteMeta> map) {
        map.put("/dm/DmHomeActivity", RouteMeta.build(RouteType.ACTIVITY, MainActivity.class, "/dm/dmhomeactivity", "dm", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
    }
}

package com.alibaba.android.arouter.routes;

import com.alibaba.android.arouter.facade.enums.RouteType;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IRouteGroup;
import com.convenient.qd.module.gov.GOVHomeActivity;
import java.util.Map;

public class ARouter$$Group$$gov implements IRouteGroup {
    public void loadInto(Map<String, RouteMeta> map) {
        map.put("/gov/GOVHomeActivity", RouteMeta.build(RouteType.ACTIVITY, GOVHomeActivity.class, "/gov/govhomeactivity", "gov", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
    }
}

package com.alibaba.android.arouter.routes;

import com.alibaba.android.arouter.facade.enums.RouteType;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IRouteGroup;
import com.convenient.qd.module.sea.SeawaterActivity;
import java.util.Map;

public class ARouter$$Group$$seawater implements IRouteGroup {
    public void loadInto(Map<String, RouteMeta> map) {
        map.put("/seawater/SeawaterActivity", RouteMeta.build(RouteType.ACTIVITY, SeawaterActivity.class, "/seawater/seawateractivity", "seawater", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
    }
}

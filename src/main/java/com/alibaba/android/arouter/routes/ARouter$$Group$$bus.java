package com.alibaba.android.arouter.routes;

import com.alibaba.android.arouter.facade.enums.RouteType;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IRouteGroup;
import com.wzh.ssgjcx.activity.SsgjActivity;
import java.util.Map;

public class ARouter$$Group$$bus implements IRouteGroup {
    public void loadInto(Map<String, RouteMeta> map) {
        map.put("/bus/BusHomeActivity", RouteMeta.build(RouteType.ACTIVITY, SsgjActivity.class, "/bus/bushomeactivity", "bus", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
    }
}

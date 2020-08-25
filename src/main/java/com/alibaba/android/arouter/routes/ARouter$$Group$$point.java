package com.alibaba.android.arouter.routes;

import coband.bsit.com.integral.activity.PointCalendarActivity;
import coband.bsit.com.integral.activity.PointExchangeActivity;
import coband.bsit.com.integral.activity.PointHomeActivity;
import com.alibaba.android.arouter.facade.enums.RouteType;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IRouteGroup;
import java.util.Map;

public class ARouter$$Group$$point implements IRouteGroup {
    public void loadInto(Map<String, RouteMeta> map) {
        map.put("/point/PointCalendarActivity", RouteMeta.build(RouteType.ACTIVITY, PointCalendarActivity.class, "/point/pointcalendaractivity", "point", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put("/point/PointExchangeActivity", RouteMeta.build(RouteType.ACTIVITY, PointExchangeActivity.class, "/point/pointexchangeactivity", "point", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put("/point/PointHomeActivity", RouteMeta.build(RouteType.ACTIVITY, PointHomeActivity.class, "/point/pointhomeactivity", "point", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
    }
}

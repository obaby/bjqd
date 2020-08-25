package com.alibaba.android.arouter.routes;

import coband.bsit.com.integral.activity.PointInviteActivity;
import com.alibaba.android.arouter.facade.enums.RouteType;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IRouteGroup;
import java.util.Map;

public class ARouter$$Group$$share implements IRouteGroup {
    public void loadInto(Map<String, RouteMeta> map) {
        map.put("/share/PointInviteActivity", RouteMeta.build(RouteType.ACTIVITY, PointInviteActivity.class, "/share/pointinviteactivity", "share", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
    }
}

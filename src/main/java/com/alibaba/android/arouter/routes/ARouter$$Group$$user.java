package com.alibaba.android.arouter.routes;

import com.alibaba.android.arouter.facade.enums.RouteType;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IRouteGroup;
import com.bsit.coband.msg.MSGActivity;
import java.util.Map;

public class ARouter$$Group$$user implements IRouteGroup {
    public void loadInto(Map<String, RouteMeta> map) {
        map.put("/user/MSGActivity", RouteMeta.build(RouteType.ACTIVITY, MSGActivity.class, "/user/msgactivity", "user", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
    }
}

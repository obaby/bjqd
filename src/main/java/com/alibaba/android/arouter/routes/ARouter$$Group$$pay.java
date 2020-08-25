package com.alibaba.android.arouter.routes;

import com.alibaba.android.arouter.facade.enums.RouteType;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IRouteGroup;
import com.convenient.qd.module.refuse.RefuseSortActivity;
import java.util.Map;

public class ARouter$$Group$$pay implements IRouteGroup {
    public void loadInto(Map<String, RouteMeta> map) {
        map.put("/pay/RefuseSortActivity", RouteMeta.build(RouteType.ACTIVITY, RefuseSortActivity.class, "/pay/refusesortactivity", "pay", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
    }
}

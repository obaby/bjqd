package com.alibaba.android.arouter.routes;

import com.alibaba.android.arouter.facade.enums.RouteType;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IRouteGroup;
import com.bsit.coband.home.gxgroup.EducationPayHomeActivity;
import com.bsit.coband.home.gxgroup.GxGroupActivity;
import java.util.Map;

public class ARouter$$Group$$gxgroup implements IRouteGroup {
    public void loadInto(Map<String, RouteMeta> map) {
        map.put("/gxgroup/EducationPayHomeActivity", RouteMeta.build(RouteType.ACTIVITY, EducationPayHomeActivity.class, "/gxgroup/educationpayhomeactivity", "gxgroup", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put("/gxgroup/GxGroupActivity", RouteMeta.build(RouteType.ACTIVITY, GxGroupActivity.class, "/gxgroup/gxgroupactivity", "gxgroup", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
    }
}

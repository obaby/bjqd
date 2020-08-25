package com.alibaba.android.arouter.routes;

import com.alibaba.android.arouter.facade.enums.RouteType;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IRouteGroup;
import com.convenient.mudule.property.activity.PropertyIndexActivity;
import java.util.Map;

public class ARouter$$Group$$property implements IRouteGroup {
    public void loadInto(Map<String, RouteMeta> map) {
        map.put("/property/PropertyIndexActivity", RouteMeta.build(RouteType.ACTIVITY, PropertyIndexActivity.class, "/property/propertyindexactivity", "property", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
    }
}

package com.alibaba.android.arouter.routes;

import android.support.v4.app.NotificationCompat;
import com.alibaba.android.arouter.facade.enums.RouteType;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IRouteGroup;
import com.convenient.qd.core.base.arouter.PathReplaceServiceImpl;
import java.util.Map;

public class ARouter$$Group$$service implements IRouteGroup {
    public void loadInto(Map<String, RouteMeta> map) {
        map.put("/service/PathReplaceServiceImpl", RouteMeta.build(RouteType.PROVIDER, PathReplaceServiceImpl.class, "/service/pathreplaceserviceimpl", NotificationCompat.CATEGORY_SERVICE, (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
    }
}

package com.alibaba.android.arouter.routes;

import android.support.v4.app.NotificationCompat;
import com.alibaba.android.arouter.facade.enums.RouteType;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IProviderGroup;
import com.convenient.qd.core.base.arouter.PathReplaceServiceImpl;
import java.util.Map;

public class ARouter$$Providers$$core implements IProviderGroup {
    public void loadInto(Map<String, RouteMeta> map) {
        map.put("com.alibaba.android.arouter.facade.service.PathReplaceService", RouteMeta.build(RouteType.PROVIDER, PathReplaceServiceImpl.class, "/service/PathReplaceServiceImpl", NotificationCompat.CATEGORY_SERVICE, (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
    }
}

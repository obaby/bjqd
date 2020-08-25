package com.alibaba.android.arouter.routes;

import android.support.v4.app.NotificationCompat;
import com.alibaba.android.arouter.facade.template.IRouteGroup;
import com.alibaba.android.arouter.facade.template.IRouteRoot;
import java.util.Map;

public class ARouter$$Root$$core implements IRouteRoot {
    public void loadInto(Map<String, Class<? extends IRouteGroup>> map) {
        map.put(NotificationCompat.CATEGORY_SERVICE, ARouter$$Group$$service.class);
    }
}

package com.alibaba.android.arouter.routes;

import android.support.v4.app.NotificationCompat;
import com.alibaba.android.arouter.facade.template.IRouteGroup;
import com.alibaba.android.arouter.facade.template.IRouteRoot;
import java.util.Map;

public class ARouter$$Root$$module_social implements IRouteRoot {
    public void loadInto(Map<String, Class<? extends IRouteGroup>> map) {
        map.put("module_social", ARouter$$Group$$module_social.class);
        map.put(NotificationCompat.CATEGORY_SOCIAL, ARouter$$Group$$social.class);
    }
}

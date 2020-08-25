package com.alibaba.android.arouter.routes;

import com.alibaba.android.arouter.facade.template.IRouteGroup;
import com.alibaba.android.arouter.facade.template.IRouteRoot;
import java.util.Map;

public class ARouter$$Root$$app implements IRouteRoot {
    public void loadInto(Map<String, Class<? extends IRouteGroup>> map) {
        map.put("address", ARouter$$Group$$address.class);
        map.put("app", ARouter$$Group$$app.class);
        map.put("gxgroup", ARouter$$Group$$gxgroup.class);
        map.put("menu", ARouter$$Group$$menu.class);
        map.put("user", ARouter$$Group$$user.class);
        map.put("web", ARouter$$Group$$web.class);
        map.put("westheart", ARouter$$Group$$westheart.class);
        map.put("wt", ARouter$$Group$$wt.class);
    }
}

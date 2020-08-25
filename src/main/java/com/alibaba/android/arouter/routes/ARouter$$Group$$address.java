package com.alibaba.android.arouter.routes;

import com.alibaba.android.arouter.facade.enums.RouteType;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IRouteGroup;
import com.bsit.coband.my.gdaddress.GdAddressActivity;
import java.util.Map;

public class ARouter$$Group$$address implements IRouteGroup {
    public void loadInto(Map<String, RouteMeta> map) {
        map.put("/address/GdAddressActivity", RouteMeta.build(RouteType.ACTIVITY, GdAddressActivity.class, "/address/gdaddressactivity", "address", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
    }
}

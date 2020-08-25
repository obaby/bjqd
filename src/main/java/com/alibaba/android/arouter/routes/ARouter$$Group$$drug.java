package com.alibaba.android.arouter.routes;

import com.alibaba.android.arouter.facade.enums.RouteType;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IRouteGroup;
import com.boruan.qq.zxgylibrary.MainActivity;
import com.boruan.qq.zxgylibrary.ui.activities.AddAddressActivity;
import com.boruan.qq.zxgylibrary.ui.activities.AddQDTAddressActivity;
import java.util.Map;

public class ARouter$$Group$$drug implements IRouteGroup {
    public void loadInto(Map<String, RouteMeta> map) {
        map.put("/drug/AddAddressActivity", RouteMeta.build(RouteType.ACTIVITY, AddAddressActivity.class, "/drug/addaddressactivity", "drug", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put("/drug/DrugHomeActivity", RouteMeta.build(RouteType.ACTIVITY, MainActivity.class, "/drug/drughomeactivity", "drug", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put("/drug/qdt/AddAddressActivity", RouteMeta.build(RouteType.ACTIVITY, AddQDTAddressActivity.class, "/drug/qdt/addaddressactivity", "drug", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
    }
}

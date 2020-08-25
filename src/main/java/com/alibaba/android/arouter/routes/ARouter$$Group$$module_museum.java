package com.alibaba.android.arouter.routes;

import com.alibaba.android.arouter.facade.enums.RouteType;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IRouteGroup;
import com.convenient.qd.module.museum.MuseumMainActivity;
import com.convenient.qd.module.museum.MuseumOrderStateActivity;
import com.convenient.qd.module.museum.mspay.OrderCmActivity;
import com.convenient.qd.module.museum.mspay.paydetail.PayDetailActivity;
import com.convenient.qd.module.museum.mspay.refuse.detail.RefuseDetailActivity;
import java.util.Map;

public class ARouter$$Group$$module_museum implements IRouteGroup {
    public void loadInto(Map<String, RouteMeta> map) {
        map.put("/module_museum/MuseumMainActivity", RouteMeta.build(RouteType.ACTIVITY, MuseumMainActivity.class, "/module_museum/museummainactivity", "module_museum", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put("/module_museum/MuseumOrderStateActivity", RouteMeta.build(RouteType.ACTIVITY, MuseumOrderStateActivity.class, "/module_museum/museumorderstateactivity", "module_museum", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put("/module_museum/mspay/OrderCmActivity", RouteMeta.build(RouteType.ACTIVITY, OrderCmActivity.class, "/module_museum/mspay/ordercmactivity", "module_museum", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put("/module_museum/mspay/paydetail/PayDetailActivity", RouteMeta.build(RouteType.ACTIVITY, PayDetailActivity.class, "/module_museum/mspay/paydetail/paydetailactivity", "module_museum", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put("/module_museum/mspay/refuse/detail/PayDetailActivity", RouteMeta.build(RouteType.ACTIVITY, RefuseDetailActivity.class, "/module_museum/mspay/refuse/detail/paydetailactivity", "module_museum", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
    }
}

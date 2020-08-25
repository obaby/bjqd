package com.alibaba.android.arouter.routes;

import com.alibaba.android.arouter.facade.enums.RouteType;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IRouteGroup;
import com.convenient.qd.module.pay.GxpayActivity;
import com.convenient.qd.module.pay.GxpayDialogActivity;
import java.util.Map;

public class ARouter$$Group$$module_pay implements IRouteGroup {
    public void loadInto(Map<String, RouteMeta> map) {
        map.put("/module_pay/GxpayActivity", RouteMeta.build(RouteType.ACTIVITY, GxpayActivity.class, "/module_pay/gxpayactivity", "module_pay", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put("/module_pay/GxpayDialogActivity", RouteMeta.build(RouteType.ACTIVITY, GxpayDialogActivity.class, "/module_pay/gxpaydialogactivity", "module_pay", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
    }
}

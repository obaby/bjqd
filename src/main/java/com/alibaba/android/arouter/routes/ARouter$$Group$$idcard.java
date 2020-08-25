package com.alibaba.android.arouter.routes;

import com.alibaba.android.arouter.facade.enums.RouteType;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IRouteGroup;
import com.convenient.qd.module.face.idcard.IDCardActivity;
import java.util.Map;

public class ARouter$$Group$$idcard implements IRouteGroup {
    public void loadInto(Map<String, RouteMeta> map) {
        map.put("/idcard/IDCardActivity", RouteMeta.build(RouteType.ACTIVITY, IDCardActivity.class, "/idcard/idcardactivity", "idcard", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
    }
}

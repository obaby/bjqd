package com.alibaba.android.arouter.routes;

import android.support.v4.app.NotificationCompat;
import com.alibaba.android.arouter.facade.enums.RouteType;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IRouteGroup;
import com.convenient.qd.module.social.activity.InsuranceSearchActivity;
import com.convenient.qd.module.social.elecard.EleCardActivity;
import java.util.Map;

public class ARouter$$Group$$social implements IRouteGroup {
    public void loadInto(Map<String, RouteMeta> map) {
        map.put("/social/EleCardActivity", RouteMeta.build(RouteType.ACTIVITY, EleCardActivity.class, "/social/elecardactivity", NotificationCompat.CATEGORY_SOCIAL, (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put("/social/InsuranceSearchActivity", RouteMeta.build(RouteType.ACTIVITY, InsuranceSearchActivity.class, "/social/insurancesearchactivity", NotificationCompat.CATEGORY_SOCIAL, (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
    }
}

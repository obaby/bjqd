package com.alibaba.android.arouter.routes;

import com.alibaba.android.arouter.facade.enums.RouteType;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IRouteGroup;
import com.bsit.coband.wxsport.OnePxActivity;
import com.bsit.coband.wxsport.WtSportCardActivity;
import java.util.Map;

public class ARouter$$Group$$wt implements IRouteGroup {
    public void loadInto(Map<String, RouteMeta> map) {
        map.put("/wt/WTSportCardActivity", RouteMeta.build(RouteType.ACTIVITY, WtSportCardActivity.class, "/wt/wtsportcardactivity", "wt", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put("/wt/WTSportHomeActivity", RouteMeta.build(RouteType.ACTIVITY, OnePxActivity.class, "/wt/wtsporthomeactivity", "wt", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
    }
}

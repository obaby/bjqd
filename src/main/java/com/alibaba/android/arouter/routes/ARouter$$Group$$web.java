package com.alibaba.android.arouter.routes;

import com.alibaba.android.arouter.facade.enums.RouteType;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IRouteGroup;
import com.bsit.coband.web.CommonWebActivity;
import com.bsit.coband.web.WebViewActivity;
import com.bsit.coband.web.dsbridge.DsBridgeWebActivity;
import com.bsit.coband.web.educate.EducateWebActivity;
import com.bsit.coband.web.life.LifePayWebActivity;
import com.bsit.coband.web.smartmedical.SmartMedicalWebViewActivity;
import java.util.Map;

public class ARouter$$Group$$web implements IRouteGroup {
    public void loadInto(Map<String, RouteMeta> map) {
        map.put("/web/CommonWebActivity", RouteMeta.build(RouteType.ACTIVITY, CommonWebActivity.class, "/web/commonwebactivity", "web", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put("/web/DsBridgeWebActivity", RouteMeta.build(RouteType.ACTIVITY, DsBridgeWebActivity.class, "/web/dsbridgewebactivity", "web", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put("/web/EducateWebActivity", RouteMeta.build(RouteType.ACTIVITY, EducateWebActivity.class, "/web/educatewebactivity", "web", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put("/web/LifePayWebActivity", RouteMeta.build(RouteType.ACTIVITY, LifePayWebActivity.class, "/web/lifepaywebactivity", "web", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put("/web/SmartMedicalWebViewActivity", RouteMeta.build(RouteType.ACTIVITY, SmartMedicalWebViewActivity.class, "/web/smartmedicalwebviewactivity", "web", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put("/web/WebViewActivity", RouteMeta.build(RouteType.ACTIVITY, WebViewActivity.class, "/web/webviewactivity", "web", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
    }
}

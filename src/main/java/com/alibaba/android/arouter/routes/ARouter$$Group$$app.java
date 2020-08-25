package com.alibaba.android.arouter.routes;

import com.alibaba.android.arouter.facade.enums.RouteType;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IRouteGroup;
import com.bsit.coband.MainActivity;
import com.bsit.coband.home.govservice.GovServiceActivity;
import com.bsit.coband.qdScore.activity.MyQuanActivity;
import com.bsit.coband.qdScore.activity.QdScoreMainActivity;
import com.bsit.coband.qdScore.activity.QuanListActivity;
import com.bsit.coband.qdScore.activity.ScoreActivity;
import com.bsit.coband.qdScore.activity.ScoreOpenActivity;
import com.bsit.coband.scanqr.ScanQrCodeActivity;
import java.util.Map;

public class ARouter$$Group$$app implements IRouteGroup {
    public void loadInto(Map<String, RouteMeta> map) {
        map.put("/app/GovServiceActivity", RouteMeta.build(RouteType.ACTIVITY, GovServiceActivity.class, "/app/govserviceactivity", "app", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put("/app/MainActivity", RouteMeta.build(RouteType.ACTIVITY, MainActivity.class, "/app/mainactivity", "app", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put("/app/MyQuanActivity", RouteMeta.build(RouteType.ACTIVITY, MyQuanActivity.class, "/app/myquanactivity", "app", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put("/app/QdScoreMainActivity", RouteMeta.build(RouteType.ACTIVITY, QdScoreMainActivity.class, "/app/qdscoremainactivity", "app", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put("/app/QuanListActivity", RouteMeta.build(RouteType.ACTIVITY, QuanListActivity.class, "/app/quanlistactivity", "app", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put("/app/ScanQrCodeActivity", RouteMeta.build(RouteType.ACTIVITY, ScanQrCodeActivity.class, "/app/scanqrcodeactivity", "app", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put("/app/ScoreActivity", RouteMeta.build(RouteType.ACTIVITY, ScoreActivity.class, "/app/scoreactivity", "app", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put("/app/ScoreOpenActivity", RouteMeta.build(RouteType.ACTIVITY, ScoreOpenActivity.class, "/app/scoreopenactivity", "app", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
    }
}

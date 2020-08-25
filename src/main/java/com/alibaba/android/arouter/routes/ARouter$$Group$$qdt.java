package com.alibaba.android.arouter.routes;

import com.alibaba.android.arouter.facade.enums.RouteType;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IRouteGroup;
import com.convenient.qd.module.qdt.activity.authorize.QDAuthorizeActivity;
import com.convenient.qd.module.qdt.activity.balancequrey.QueryBalanceActivity;
import com.convenient.qd.module.qdt.activity.device.SearchDevicesActivity;
import com.convenient.qd.module.qdt.activity.electronicInvoice.BusCardActivity;
import com.convenient.qd.module.qdt.activity.home.QDHomeActivity;
import com.convenient.qd.module.qdt.activity.home.QDTZoneActivity;
import com.convenient.qd.module.qdt.activity.moreService.ServiceDotActivity;
import com.convenient.qd.module.qdt.activity.order.OrderListActivity;
import com.convenient.qd.module.qdt.activity.recharge.MutilRechargeTypeActivity;
import com.convenient.qd.module.qdt.activity.recharge.QDRechargeActivity;
import com.convenient.qd.module.qdt.activity.stucard.SpecialCardGuideActivity;
import com.convenient.qd.module.qdt.activity.stucard.StuCardInfoCollectActivity;
import com.convenient.qd.module.qdt.activity.stucard.StuCardOrderAccountActivity;
import com.convenient.qd.module.qdt.activity.stucard.StuCardOrderDetailActivity;
import com.convenient.qd.module.qdt.activity.stucard.StuCardOrderListActivity;
import com.convenient.qd.module.qdt.activity.stucard.StuCardPayResultActivity;
import com.convenient.qd.module.qdt.activity.stucard.StuCardSplashActivity;
import com.convenient.qd.module.qdt.activity.stucard.StuIdCardImgCollectActivity;
import java.util.Map;

public class ARouter$$Group$$qdt implements IRouteGroup {
    public void loadInto(Map<String, RouteMeta> map) {
        map.put("/qdt/BusCardActivity", RouteMeta.build(RouteType.ACTIVITY, BusCardActivity.class, "/qdt/buscardactivity", "qdt", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put("/qdt/MutilRechargeTypeActivity", RouteMeta.build(RouteType.ACTIVITY, MutilRechargeTypeActivity.class, "/qdt/mutilrechargetypeactivity", "qdt", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put("/qdt/OrderListActivity", RouteMeta.build(RouteType.ACTIVITY, OrderListActivity.class, "/qdt/orderlistactivity", "qdt", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put("/qdt/QDAuthorizeActivity", RouteMeta.build(RouteType.ACTIVITY, QDAuthorizeActivity.class, "/qdt/qdauthorizeactivity", "qdt", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put("/qdt/QDCardBalanceActivity", RouteMeta.build(RouteType.ACTIVITY, QueryBalanceActivity.class, "/qdt/qdcardbalanceactivity", "qdt", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put("/qdt/QDHomeActivity", RouteMeta.build(RouteType.ACTIVITY, QDHomeActivity.class, "/qdt/qdhomeactivity", "qdt", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put("/qdt/QDRechargeActivity", RouteMeta.build(RouteType.ACTIVITY, QDRechargeActivity.class, "/qdt/qdrechargeactivity", "qdt", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put("/qdt/QDServiceActivity", RouteMeta.build(RouteType.ACTIVITY, ServiceDotActivity.class, "/qdt/qdserviceactivity", "qdt", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put("/qdt/QDTZoneActivity", RouteMeta.build(RouteType.ACTIVITY, QDTZoneActivity.class, "/qdt/qdtzoneactivity", "qdt", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put("/qdt/SearchDevicesActivity", RouteMeta.build(RouteType.ACTIVITY, SearchDevicesActivity.class, "/qdt/searchdevicesactivity", "qdt", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put("/qdt/SpecialCardGuideActivity", RouteMeta.build(RouteType.ACTIVITY, SpecialCardGuideActivity.class, "/qdt/specialcardguideactivity", "qdt", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put("/qdt/StuCardInfoCollectActivity", RouteMeta.build(RouteType.ACTIVITY, StuCardInfoCollectActivity.class, "/qdt/stucardinfocollectactivity", "qdt", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put("/qdt/StuCardOrderAccountActivity", RouteMeta.build(RouteType.ACTIVITY, StuCardOrderAccountActivity.class, "/qdt/stucardorderaccountactivity", "qdt", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put("/qdt/StuCardOrderDetailActivity", RouteMeta.build(RouteType.ACTIVITY, StuCardOrderDetailActivity.class, "/qdt/stucardorderdetailactivity", "qdt", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put("/qdt/StuCardOrderListActivity", RouteMeta.build(RouteType.ACTIVITY, StuCardOrderListActivity.class, "/qdt/stucardorderlistactivity", "qdt", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put("/qdt/StuCardPayResultActivity", RouteMeta.build(RouteType.ACTIVITY, StuCardPayResultActivity.class, "/qdt/stucardpayresultactivity", "qdt", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put("/qdt/StuCardSplashActivity", RouteMeta.build(RouteType.ACTIVITY, StuCardSplashActivity.class, "/qdt/stucardsplashactivity", "qdt", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put("/qdt/StuIdCardImgCollectActivity", RouteMeta.build(RouteType.ACTIVITY, StuIdCardImgCollectActivity.class, "/qdt/stuidcardimgcollectactivity", "qdt", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
    }
}

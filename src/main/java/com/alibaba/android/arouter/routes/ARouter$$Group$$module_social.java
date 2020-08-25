package com.alibaba.android.arouter.routes;

import com.alibaba.android.arouter.facade.enums.RouteType;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IRouteGroup;
import com.convenient.qd.module.social.activity.EleCardHomeActivity;
import com.convenient.qd.module.social.activity.EleCardProgressActivity;
import com.convenient.qd.module.social.activity.ElePayQRCodeActivity;
import com.convenient.qd.module.social.activity.InsuranceAccountActivity;
import com.convenient.qd.module.social.activity.JobRegisterActivity;
import com.convenient.qd.module.social.activity.PaymentInfoQueryActivity;
import java.util.Map;

public class ARouter$$Group$$module_social implements IRouteGroup {
    public void loadInto(Map<String, RouteMeta> map) {
        map.put("/module_social/EleCardHomeActivity", RouteMeta.build(RouteType.ACTIVITY, EleCardHomeActivity.class, "/module_social/elecardhomeactivity", "module_social", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put("/module_social/EleCardProgressActivity", RouteMeta.build(RouteType.ACTIVITY, EleCardProgressActivity.class, "/module_social/elecardprogressactivity", "module_social", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put("/module_social/ElePayQRCodeActivity", RouteMeta.build(RouteType.ACTIVITY, ElePayQRCodeActivity.class, "/module_social/elepayqrcodeactivity", "module_social", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put("/module_social/InsuranceAccountActivity", RouteMeta.build(RouteType.ACTIVITY, InsuranceAccountActivity.class, "/module_social/insuranceaccountactivity", "module_social", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put("/module_social/JobRegisterActivity", RouteMeta.build(RouteType.ACTIVITY, JobRegisterActivity.class, "/module_social/jobregisteractivity", "module_social", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put("/module_social/PaymentInfoQueryActivity", RouteMeta.build(RouteType.ACTIVITY, PaymentInfoQueryActivity.class, "/module_social/paymentinfoqueryactivity", "module_social", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
    }
}

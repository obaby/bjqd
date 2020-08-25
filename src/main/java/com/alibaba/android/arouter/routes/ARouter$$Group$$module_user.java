package com.alibaba.android.arouter.routes;

import com.alibaba.android.arouter.facade.enums.RouteType;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IRouteGroup;
import com.convenient.qd.module.user.edit.UserEditActivity;
import com.convenient.qd.module.user.login.UserAgreementActivity;
import com.convenient.qd.module.user.login.UserPwdLoginActivity;
import com.convenient.qd.module.user.login.UserRegisterActivity;
import com.convenient.qd.module.user.order.UserOrderActivity;
import com.convenient.qd.module.user.setting.UserPayPwdChangeActivity;
import com.convenient.qd.module.user.setting.UserPayPwdSetActivity;
import com.convenient.qd.module.user.setting.UserPwdGuideActivity;
import com.convenient.qd.module.user.setting.UserPwdShiRenActivity;
import com.convenient.qd.module.user.setting.UserSafeManagerActivity;
import com.convenient.qd.module.user.setting.UserSettingActivity;
import java.util.Map;

public class ARouter$$Group$$module_user implements IRouteGroup {
    public void loadInto(Map<String, RouteMeta> map) {
        map.put("/module_user/UserAgreementActivity", RouteMeta.build(RouteType.ACTIVITY, UserAgreementActivity.class, "/module_user/useragreementactivity", "module_user", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put("/module_user/UserPayPwdChangeActivity", RouteMeta.build(RouteType.ACTIVITY, UserPayPwdChangeActivity.class, "/module_user/userpaypwdchangeactivity", "module_user", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put("/module_user/UserPayPwdSetActivity", RouteMeta.build(RouteType.ACTIVITY, UserPayPwdSetActivity.class, "/module_user/userpaypwdsetactivity", "module_user", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put("/module_user/UserPwdGuideActivity", RouteMeta.build(RouteType.ACTIVITY, UserPwdGuideActivity.class, "/module_user/userpwdguideactivity", "module_user", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put("/module_user/UserPwdShiRenActivity", RouteMeta.build(RouteType.ACTIVITY, UserPwdShiRenActivity.class, "/module_user/userpwdshirenactivity", "module_user", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put("/module_user/edit/UserEditActivity", RouteMeta.build(RouteType.ACTIVITY, UserEditActivity.class, "/module_user/edit/usereditactivity", "module_user", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put("/module_user/login/LoginPwdActivity", RouteMeta.build(RouteType.ACTIVITY, UserPwdLoginActivity.class, "/module_user/login/loginpwdactivity", "module_user", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put("/module_user/login/UserRegisterActivity", RouteMeta.build(RouteType.ACTIVITY, UserRegisterActivity.class, "/module_user/login/userregisteractivity", "module_user", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put("/module_user/order/UserOrderActivity", RouteMeta.build(RouteType.ACTIVITY, UserOrderActivity.class, "/module_user/order/userorderactivity", "module_user", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put("/module_user/setting/UserSafeManagerActivity", RouteMeta.build(RouteType.ACTIVITY, UserSafeManagerActivity.class, "/module_user/setting/usersafemanageractivity", "module_user", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put("/module_user/setting/UserSettingActivity", RouteMeta.build(RouteType.ACTIVITY, UserSettingActivity.class, "/module_user/setting/usersettingactivity", "module_user", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
    }
}

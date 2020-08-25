package cn.xports.qd2.util;

import cn.xports.baselib.http.RetrofitUtil;
import cn.xports.http.SodaService;
import cn.xports.qd2.http.ApiService2;

public class ApiUtil {
    public static ApiService2 getApi2() {
        return (ApiService2) RetrofitUtil.getInstance().create(ApiService2.class);
    }

    public static SodaService getApi() {
        return (SodaService) RetrofitUtil.getInstance().create(SodaService.class);
    }
}

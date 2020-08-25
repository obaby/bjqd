package com.alibaba.android.arouter.routes;

import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.alibaba.android.arouter.facade.template.IInterceptorGroup;
import com.convenient.qd.module.qdt.activity.home.OpenCodeRideInterceptor;
import java.util.Map;

public class ARouter$$Interceptors$$module_qdt implements IInterceptorGroup {
    public void loadInto(Map<Integer, Class<? extends IInterceptor>> map) {
        map.put(1, OpenCodeRideInterceptor.class);
    }
}

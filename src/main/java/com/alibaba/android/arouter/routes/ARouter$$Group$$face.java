package com.alibaba.android.arouter.routes;

import com.alibaba.android.arouter.facade.enums.RouteType;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IRouteGroup;
import com.convenient.qd.module.face.IdentityActivity;
import com.convenient.qd.module.face.sense.FaceRecognitionActivity;
import java.util.Map;

public class ARouter$$Group$$face implements IRouteGroup {
    public void loadInto(Map<String, RouteMeta> map) {
        map.put("/face/FaceRecognitionActivity", RouteMeta.build(RouteType.ACTIVITY, FaceRecognitionActivity.class, "/face/facerecognitionactivity", "face", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put("/face/IdentityActivity", RouteMeta.build(RouteType.ACTIVITY, IdentityActivity.class, "/face/identityactivity", "face", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
    }
}

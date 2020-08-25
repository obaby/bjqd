package cn.xports.qd2.coupons;

import android.support.annotation.NonNull;
import cn.xports.baselib.bean.DataMap;
import cn.xports.baselib.mvp.IView;

public interface CouponGetView extends IView {
    void needPsptId(boolean z);

    void onCouponDetail(@NonNull DataMap dataMap);

    void onGetFinish(@NonNull DataMap dataMap);
}

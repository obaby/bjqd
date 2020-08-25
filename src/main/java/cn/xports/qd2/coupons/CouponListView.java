package cn.xports.qd2.coupons;

import android.support.annotation.NonNull;
import cn.xports.baselib.bean.DataMap;
import cn.xports.baselib.mvp.IView;
import java.util.ArrayList;

public interface CouponListView extends IView {
    void showCouponList(@NonNull ArrayList<DataMap> arrayList);

    void showTabLayout(@NonNull DataMap dataMap);

    void showVenueList(@NonNull ArrayList<DataMap> arrayList);
}

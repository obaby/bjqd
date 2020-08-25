package cn.xports.qd2.coupons;

import android.support.annotation.NonNull;
import cn.xports.baselib.bean.DataMap;
import cn.xports.baselib.http.ResponseDataMap;
import cn.xports.baselib.http.RetrofitUtil;
import cn.xports.baselib.http.RxUtil;
import cn.xports.baselib.mvp.BasePresenter;
import cn.xports.baselib.mvp.IModel;
import cn.xports.qd2.entity.K;
import cn.xports.qd2.http.ApiService2;
import java.util.ArrayList;
import java.util.Iterator;

public class CouponListPresenter extends BasePresenter<IModel, CouponListView> {
    private ApiService2 service2 = ((ApiService2) RetrofitUtil.getInstance().create(ApiService2.class));

    public CouponListPresenter(CouponListView couponListView) {
        super(couponListView);
    }

    public void getCouponServices() {
        this.service2.getCouponServices().compose(RxUtil.applyDataMapIO()).subscribe(new ResponseDataMap(getRootView()) {
            public void onSuccess(@NonNull DataMap dataMap) {
                ((CouponListView) CouponListPresenter.this.getRootView()).showTabLayout(dataMap);
            }
        });
    }

    public void getCouponList(final String str, String str2, String str3) {
        this.service2.getCouponList("", str2, str3, "").compose(RxUtil.applyDataMapIO()).subscribe(new ResponseDataMap(getRootView()) {
            public void onSuccess(@NonNull DataMap dataMap) {
                ArrayList<DataMap> dataList = dataMap.getDataList("venueList");
                ArrayList<DataMap> arrayList = new ArrayList<>();
                if (!str.equals("")) {
                    Iterator<DataMap> it = dataList.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        DataMap next = it.next();
                        if (next.getString(K.venueId).equals(str)) {
                            arrayList.add(next);
                            break;
                        }
                    }
                    dataList = arrayList;
                } else {
                    ((CouponListView) CouponListPresenter.this.getRootView()).showVenueList(dataList);
                }
                ((CouponListView) CouponListPresenter.this.getRootView()).showCouponList(dataList);
            }
        });
    }

    public void getVenues() {
        this.service2.getVenueList().compose(RxUtil.applyDataMapIO()).subscribe(new ResponseDataMap(getRootView()) {
            public void onSuccess(@NonNull DataMap dataMap) {
                ((CouponListView) CouponListPresenter.this.getRootView()).showVenueList(dataMap.getDataList("venueList"));
            }
        });
    }
}

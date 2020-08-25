package coband.bsit.com.integral.api;

import coband.bsit.com.integral.bean.CouponInfoBean;
import coband.bsit.com.integral.bean.CreditListBean;
import coband.bsit.com.integral.bean.IconBean;
import coband.bsit.com.integral.bean.ShareUrlBean;
import coband.bsit.com.integral.bean.UserSign;
import coband.bsit.com.integral.bean.UserSignInfoToday;
import coband.bsit.com.integral.bean.UserSignListMonth;
import coband.bsit.com.integral.body.CouponExchangeBody;
import coband.bsit.com.integral.body.CreditListBody;
import com.convenient.qd.core.base.BaseRequest;
import com.convenient.qd.core.bean.BaseResBean;
import io.reactivex.Observable;
import java.util.List;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("v3/credit/mobileclient/credit/couponChangeInfoList")
    Observable<BaseResBean<List<CouponInfoBean>>> couponInfo();

    @POST("v3/credit/mobileclient/credit/couponChange")
    Observable<BaseResBean<Object>> exchangeCoupon(@Body CouponExchangeBody couponExchangeBody);

    @POST("v3/credit/mobileclient/credit/creditList")
    Observable<CreditListBean> getCredits(@Body CreditListBody creditListBody);

    @POST("v3/credit/mobileclient/credit/creditList")
    Observable<CreditListBean> getCreditsList(@Body CreditListBody creditListBody);

    @POST("v3/credit/mobileclient/usersign/queryUserSignRecordList")
    Observable<UserSignListMonth> getMonthSignInfo();

    @POST("v3/app/credit")
    Observable<BaseResBean<IconBean>> getPointIcon(@Body BaseRequest baseRequest);

    @POST("v3/share/url")
    Observable<BaseResBean<ShareUrlBean>> getShareUrl(@Body BaseRequest baseRequest);

    @POST("v3/credit/mobileclient/usersign/queryUserSign")
    Observable<UserSignInfoToday> getSignInfo();

    @POST("v3/credit/mobileclient/usersign/addUserSignRecord")
    Observable<UserSign> userSign();
}

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
import com.convenient.qd.core.net.BaseHttpObserver;
import com.convenient.qd.core.net.RetrofitHelper;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;
import java.util.List;

public class DataManager {
    private static DataManager model;
    private ApiService mApiService = ((ApiService) RetrofitHelper.getInstance(Api.SERVER_URL).create(ApiService.class));

    private DataManager() {
    }

    public static DataManager getInstance() {
        if (model == null) {
            model = new DataManager();
        }
        return model;
    }

    public void getCredits(DefaultObserver<CreditListBean> defaultObserver) {
        CreditListBody creditListBody = new CreditListBody();
        creditListBody.setPage(1);
        creditListBody.setListType(1);
        this.mApiService.getCredits(creditListBody).subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(defaultObserver);
    }

    public void getCreditsList(int i, DefaultObserver<CreditListBean> defaultObserver) {
        CreditListBody creditListBody = new CreditListBody();
        creditListBody.setPage(i);
        creditListBody.setListType(1);
        this.mApiService.getCreditsList(creditListBody).subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(defaultObserver);
    }

    public void getSignInfo(DefaultObserver<UserSignInfoToday> defaultObserver) {
        this.mApiService.getSignInfo().subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(defaultObserver);
    }

    public void getSignInfoMonth(DefaultObserver<UserSignListMonth> defaultObserver) {
        this.mApiService.getMonthSignInfo().subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(defaultObserver);
    }

    public void userSign(DefaultObserver<UserSign> defaultObserver) {
        this.mApiService.userSign().subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(defaultObserver);
    }

    public void couponInfo(BaseHttpObserver<BaseResBean<List<CouponInfoBean>>> baseHttpObserver) {
        this.mApiService.couponInfo().subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(baseHttpObserver);
    }

    public void exchangeCoupon(int i, String str, BaseHttpObserver<Object> baseHttpObserver) {
        CouponExchangeBody couponExchangeBody = new CouponExchangeBody();
        couponExchangeBody.setCreditid(i);
        couponExchangeBody.setCouponid(str);
        couponExchangeBody.setCouponcnt("1");
        this.mApiService.exchangeCoupon(couponExchangeBody).subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(baseHttpObserver);
    }

    public void getShareUrl(BaseHttpObserver<BaseResBean<ShareUrlBean>> baseHttpObserver) {
        this.mApiService.getShareUrl(new BaseRequest()).subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(baseHttpObserver);
    }

    public void getPointIcon(BaseHttpObserver<BaseResBean<IconBean>> baseHttpObserver) {
        this.mApiService.getPointIcon(new BaseRequest()).subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(baseHttpObserver);
    }
}

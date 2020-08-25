package cn.xports.qd2.coupons;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import cn.xports.base.Constant;
import cn.xports.baselib.bean.DataMap;
import cn.xports.baselib.http.ResponseDataMap;
import cn.xports.baselib.http.RetrofitUtil;
import cn.xports.baselib.http.RxUtil;
import cn.xports.baselib.mvp.BasePresenter;
import cn.xports.baselib.mvp.IModel;
import cn.xports.qd2.entity.K;
import cn.xports.qd2.http.ApiService2;
import cn.xports.qd2.training.CourseResvStateActivity;
import cn.xports.sportCoaching.WebViewDetailActivity;
import com.blankj.utilcode.util.ActivityUtils;
import org.jetbrains.annotations.Nullable;

public class CouponGetPresenter extends BasePresenter<IModel, CouponGetView> {
    private ApiService2 service2 = ((ApiService2) RetrofitUtil.getInstance().create(ApiService2.class));

    public CouponGetPresenter(@Nullable CouponGetView couponGetView) {
        super(couponGetView);
    }

    public void getCouponDetail(String str, String str2) {
        this.service2.getCouponDetail(str, str2).compose(RxUtil.applyDataMapIO()).subscribe(new ResponseDataMap(getRootView()) {
            public void onSuccess(@NonNull DataMap dataMap) {
                ((CouponGetView) CouponGetPresenter.this.getRootView()).onCouponDetail(dataMap);
            }
        });
    }

    public void getNeedPsptId(String str) {
        ((ApiService2) RetrofitUtil.getInstance().create(ApiService2.class)).getVenueParams("wechat_coupon_need_psptid").compose(RxUtil.applyDataMapIO()).subscribe(new ResponseDataMap(getRootView()) {
            public void onSuccess(@NonNull DataMap dataMap) {
                ((CouponGetView) CouponGetPresenter.this.getRootView()).needPsptId(dataMap.isOK("wechat_coupon_need_psptid"));
            }
        });
    }

    public void sendVerifyCode(String str) {
        this.service2.sendVerifyCode(str, "3", Constant.COUPON_PAY).compose(RxUtil.applyDataMapIO()).subscribe(new ResponseDataMap(getRootView(), false) {
            public void onSuccess(@NonNull DataMap dataMap) {
            }
        });
    }

    public void checkVerify(final DataMap dataMap) {
        this.service2.checkVerifyCode(dataMap.getString(K.phoneNum), "3", dataMap.getString(K.verifyCode)).compose(RxUtil.applyDataMapIO()).subscribe(new ResponseDataMap(getRootView()) {
            public void onSuccess(@NonNull DataMap dataMap) {
                dataMap.remove(K.verifyCode);
                CouponGetPresenter.this.collectCoupon(dataMap);
            }
        });
    }

    public void collectCoupon(DataMap dataMap) {
        this.service2.getCoupon(dataMap).compose(RxUtil.applyDataMapIO()).subscribe(new ResponseDataMap(getRootView()) {
            public void onSuccess(@NonNull DataMap dataMap) {
                ActivityUtils.startActivity(new Intent((Activity) CouponGetPresenter.this.getRootView(), CourseResvStateActivity.class).putExtra(K.state, "1").putExtra(WebViewDetailActivity.TITLE, "领取成功").putExtra("content", "恭喜，领取成功！").putExtra("tip", "优惠券已发放至您的账户！请在「我的」页面查看").putExtra("secondOptionName", "回到首页").putExtra("secondOptionUrl", "/venueMain").putExtra("firstOptionName", "查看优惠券").putExtra("firstOptionUrl", "/myCenter"));
            }

            public void onError(@NonNull DataMap dataMap) {
                ActivityUtils.startActivity(new Intent((Activity) CouponGetPresenter.this.getRootView(), CourseResvStateActivity.class).putExtra(K.state, K.k0).putExtra(WebViewDetailActivity.TITLE, "领取失败").putExtra("content", "领取失败").putExtra("tip", dataMap.getMessage()).putExtra("secondOptionName", "确定").putExtra("secondOptionUrl", ""));
            }
        });
    }
}

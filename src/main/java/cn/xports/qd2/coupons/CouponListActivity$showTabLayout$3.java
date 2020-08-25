package cn.xports.qd2.coupons;

import android.support.design.widget.TabLayout;
import android.widget.ImageView;
import cn.xports.baselib.bean.DataMap;
import cn.xports.qd2.R;
import cn.xports.qd2.dialog.CouponDropWindow;
import cn.xports.qd2.entity.Option;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J8\u0010\u0002\u001a\u00020\u00032\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005H\u0016J\b\u0010\t\u001a\u00020\u0003H\u0016¨\u0006\n"}, d2 = {"cn/xports/qd2/coupons/CouponListActivity$showTabLayout$3", "Lcn/xports/qd2/dialog/CouponDropWindow$OnPopOptListener;", "onConfirm", "", "couponType", "Lcn/xports/qd2/entity/Option;", "Lcn/xports/baselib/bean/DataMap;", "venue", "service", "onReset", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: CouponListActivity.kt */
public final class CouponListActivity$showTabLayout$3 implements CouponDropWindow.OnPopOptListener {
    final /* synthetic */ CouponListActivity this$0;

    CouponListActivity$showTabLayout$3(CouponListActivity couponListActivity) {
        this.this$0 = couponListActivity;
    }

    public void onConfirm(@Nullable Option<DataMap> option, @Nullable Option<DataMap> option2, @Nullable Option<DataMap> option3) {
        String str;
        String str2;
        String str3;
        if (option != null) {
            CouponListActivity couponListActivity = this.this$0;
            if (option.isSelect()) {
                str3 = option.getValue();
                Intrinsics.checkExpressionValueIsNotNull(str3, "it.value");
            } else {
                str3 = "";
            }
            couponListActivity.couponClass = str3;
        }
        if (option2 != null) {
            CouponListActivity couponListActivity2 = this.this$0;
            if (option2.isSelect()) {
                str2 = option2.getValue();
                Intrinsics.checkExpressionValueIsNotNull(str2, "it.value");
            } else {
                str2 = "";
            }
            couponListActivity2.venueId = str2;
        }
        if (option3 != null) {
            CouponListActivity couponListActivity3 = this.this$0;
            if (option3.isSelect()) {
                str = option3.getValue();
                Intrinsics.checkExpressionValueIsNotNull(str, "this.value");
            } else {
                str = "";
            }
            couponListActivity3.serviceId = str;
            TabLayout.Tab tabAt = ((TabLayout) this.this$0._$_findCachedViewById(R.id.tabLayout)).getTabAt(this.this$0.titles.indexOf(option3.getName()));
            if (tabAt != null) {
                tabAt.select();
            } else {
                CouponListPresenter couponListPresenter = (CouponListPresenter) this.this$0.getPresenter();
                if (couponListPresenter != null) {
                    couponListPresenter.getCouponList(this.this$0.venueId, this.this$0.serviceId, this.this$0.couponClass);
                }
            }
        }
        boolean z = false;
        if (this.this$0.couponClass.length() == 0) {
            if (this.this$0.venueId.length() == 0) {
                z = true;
            }
            if (z) {
                ((ImageView) this.this$0._$_findCachedViewById(R.id.ivFilter)).setImageResource(R.drawable.ic_filter_gray);
                return;
            }
        }
        ((ImageView) this.this$0._$_findCachedViewById(R.id.ivFilter)).setImageResource(R.drawable.ic_filter);
    }

    public void onReset() {
        this.this$0.serviceId = "";
        this.this$0.venueId = "";
        this.this$0.couponClass = "";
        ((ImageView) this.this$0._$_findCachedViewById(R.id.ivFilter)).setImageResource(R.drawable.ic_filter_gray);
        TabLayout.Tab tabAt = ((TabLayout) this.this$0._$_findCachedViewById(R.id.tabLayout)).getTabAt(0);
        if (tabAt != null) {
            tabAt.select();
        }
    }
}

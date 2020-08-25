package cn.xports.qd2;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.xports.base.BaseBussActivity;
import cn.xports.baselib.bean.DataMap;
import cn.xports.baselib.http.RxUtil;
import cn.xports.baselib.mvp.IPresenter;
import cn.xports.baselib.util.DataMapUtils;
import cn.xports.baselib.util.GlideUtil;
import cn.xports.baselib.util.SPUtil;
import cn.xports.qd2.http.ApiService2;
import cn.xports.qd2.util.ApiUtil;
import com.blankj.utilcode.util.ColorUtils;
import com.blankj.utilcode.util.SpanUtils;
import io.reactivex.Observable;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0006\u001a\u00020\u0007H\u0002J\b\u0010\b\u001a\u00020\tH\u0014J\b\u0010\n\u001a\u00020\u0005H\u0014J\b\u0010\u000b\u001a\u00020\u0007H\u0002J\b\u0010\f\u001a\u00020\u0007H\u0002J\b\u0010\r\u001a\u00020\u0007H\u0016J\b\u0010\u000e\u001a\u00020\u0007H\u0014J\u0018\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u0005H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcn/xports/qd2/MyCenterActivity;", "Lcn/xports/base/BaseBussActivity;", "Lcn/xports/baselib/mvp/IPresenter;", "()V", "cardNum", "", "getAppoints", "", "getChildTitle", "", "getLayoutId", "getUserCouponAndInfo", "getUserIcon", "initData", "initView", "setAppointView", "size", "today", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: MyCenterActivity.kt */
public final class MyCenterActivity extends BaseBussActivity<IPresenter> {
    private HashMap _$_findViewCache;
    /* access modifiers changed from: private */
    public int cardNum;

    public void _$_clearFindViewByIdCache() {
        if (this._$_findViewCache != null) {
            this._$_findViewCache.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    /* access modifiers changed from: protected */
    @NotNull
    public String getChildTitle() {
        return "";
    }

    public void initData() {
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.activity_my_center_new;
    }

    /* access modifiers changed from: protected */
    public void initView() {
        underStatusBar(true, false);
        ((LinearLayout) _$_findCachedViewById(R.id.llMyActivitys)).setOnClickListener(new MyCenterActivity$initView$1(this));
        ((LinearLayout) _$_findCachedViewById(R.id.llMyCourses)).setOnClickListener(new MyCenterActivity$initView$2(this));
        ((TextView) _$_findCachedViewById(R.id.tvGoAppoints)).setOnClickListener(new MyCenterActivity$initView$3(this));
        ((LinearLayout) _$_findCachedViewById(R.id.llMyOrders)).setOnClickListener(new MyCenterActivity$initView$4(this));
        ((LinearLayout) _$_findCachedViewById(R.id.llVenueTicket)).setOnClickListener(new MyCenterActivity$initView$5(this));
        ((LinearLayout) _$_findCachedViewById(R.id.llMyCardPackage)).setOnClickListener(new MyCenterActivity$initView$6(this));
        ((LinearLayout) _$_findCachedViewById(R.id.llMyProms)).setOnClickListener(new MyCenterActivity$initView$7(this));
        ((TextView) _$_findCachedViewById(R.id.tvMyCredit)).setOnClickListener(new MyCenterActivity$initView$8(this));
        ((LinearLayout) _$_findCachedViewById(R.id.llFeedBack)).setOnClickListener(new MyCenterActivity$initView$9(this));
        ((ImageView) _$_findCachedViewById(R.id.iv_circle)).setOnClickListener(new MyCenterActivity$initView$10(this));
        getUserIcon();
        getUserCouponAndInfo();
        getAppoints();
    }

    private final void getUserIcon() {
        DataMap fromJson = DataMapUtils.fromJson(SPUtil.Companion.getINSTANCE().getStringValue("qdUserInfo"));
        if (fromJson == null) {
            fromJson = new DataMap();
        }
        TextView textView = (TextView) _$_findCachedViewById(R.id.tv_user_name);
        Intrinsics.checkExpressionValueIsNotNull(textView, "tv_user_name");
        textView.setText(fromJson.getString("nickName", SPUtil.Companion.getINSTANCE().getStringValue("phone")));
        GlideUtil.loadImage(this, fromJson.getString("photo"), R.drawable.ic_head_default).into((ImageView) _$_findCachedViewById(R.id.ivHead));
    }

    private final void getUserCouponAndInfo() {
        ApiService2 api2 = ApiUtil.getApi2();
        Intrinsics.checkExpressionValueIsNotNull(api2, "ApiUtil.getApi2()");
        Observable<ResponseBody> userCouponCountInfo = api2.getUserCouponCountInfo();
        Intrinsics.checkExpressionValueIsNotNull(userCouponCountInfo, "ApiUtil.getApi2()\n            .userCouponCountInfo");
        RxUtil.subscribeDataMapIO$default(userCouponCountInfo, this, new MyCenterActivity$getUserCouponAndInfo$1(this), (Function1) null, false, 12, (Object) null);
    }

    private final void getAppoints() {
        ApiService2 api2 = ApiUtil.getApi2();
        Intrinsics.checkExpressionValueIsNotNull(api2, "ApiUtil.getApi2()");
        Observable<ResponseBody> appoints = api2.getAppoints();
        Intrinsics.checkExpressionValueIsNotNull(appoints, "ApiUtil.getApi2()\n            .appoints");
        RxUtil.subscribeDataMapIO$default(appoints, this, new MyCenterActivity$getAppoints$1(this), (Function1) null, false, 12, (Object) null);
    }

    /* access modifiers changed from: private */
    public final void setAppointView(int i, int i2) {
        if (i > 0) {
            LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(R.id.llMyAppoints);
            Intrinsics.checkExpressionValueIsNotNull(linearLayout, "llMyAppoints");
            linearLayout.setVisibility(0);
            SpanUtils append = SpanUtils.with((TextView) _$_findCachedViewById(R.id.tvAppointCount)).append("您有");
            StringBuilder sb = new StringBuilder();
            sb.append(' ');
            sb.append(i);
            sb.append(' ');
            append.append(sb.toString()).setForegroundColor(ColorUtils.getColor(R.color.red_fd4)).setFontSize(17, true).append("节课程预约成功").create();
            if (i2 > 0) {
                TextView textView = (TextView) _$_findCachedViewById(R.id.tvTodayCount);
                Intrinsics.checkExpressionValueIsNotNull(textView, "tvTodayCount");
                textView.setVisibility(0);
                TextView textView2 = (TextView) _$_findCachedViewById(R.id.tvTodayCount);
                Intrinsics.checkExpressionValueIsNotNull(textView2, "tvTodayCount");
                textView2.setText("• 有" + i2 + "节预约课程，今日即将上课");
            }
        }
    }
}

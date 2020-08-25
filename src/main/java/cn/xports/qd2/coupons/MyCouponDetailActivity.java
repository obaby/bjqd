package cn.xports.qd2.coupons;

import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.xports.base.BaseBussActivity;
import cn.xports.baselib.bean.DataMap;
import cn.xports.baselib.http.RxUtil;
import cn.xports.baselib.mvp.IPresenter;
import cn.xports.baselib.util.DataMapUtils;
import cn.xports.baselib.widget.FakeBoldText;
import cn.xports.qd2.R;
import cn.xports.qd2.entity.K;
import cn.xports.qd2.util.ApiUtil;
import cn.xports.qd2.util.ViewExt;
import cn.xports.util.QRUtil;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\b\u0010\f\u001a\u00020\u0005H\u0014J\b\u0010\r\u001a\u00020\tH\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0014J\b\u0010\u0010\u001a\u00020\tH\u0016J\b\u0010\u0011\u001a\u00020\tH\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcn/xports/qd2/coupons/MyCouponDetailActivity;", "Lcn/xports/base/BaseBussActivity;", "Lcn/xports/baselib/mvp/IPresenter;", "()V", "couponNo", "", "first", "", "fillData", "", "couponInfo", "Lcn/xports/baselib/bean/DataMap;", "getChildTitle", "getCouponInfo", "getLayoutId", "", "initData", "initView", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: MyCouponDetailActivity.kt */
public final class MyCouponDetailActivity extends BaseBussActivity<IPresenter> {
    private HashMap _$_findViewCache;
    private String couponNo = "";
    private boolean first = true;

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
        return "优惠券详情";
    }

    public void initData() {
        this.couponNo = getStringExtra(K.couponNo);
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.activity_my_coupon_detail;
    }

    /* access modifiers changed from: protected */
    public void initView() {
        DataMap fromJson = DataMapUtils.fromJson(getStringExtra(K.couponInfo));
        Intrinsics.checkExpressionValueIsNotNull(fromJson, "DataMapUtils.fromJson(ge…tringExtra(K.couponInfo))");
        fillData(fromJson);
    }

    /* access modifiers changed from: private */
    public final void fillData(DataMap dataMap) {
        FakeBoldText fakeBoldText = (FakeBoldText) _$_findCachedViewById(R.id.tvCouponName);
        Intrinsics.checkExpressionValueIsNotNull(fakeBoldText, "tvCouponName");
        fakeBoldText.setText(dataMap.getString(K.couponName));
        TextView textView = (TextView) _$_findCachedViewById(R.id.tvCouponExpire);
        Intrinsics.checkExpressionValueIsNotNull(textView, "tvCouponExpire");
        StringBuilder sb = new StringBuilder();
        sb.append("有效期：");
        String string = dataMap.getString(K.effectiveDate);
        Intrinsics.checkExpressionValueIsNotNull(string, "couponInfo.getString(\"effectiveDate\")");
        sb.append(StringsKt.replace$default(string, "-", "/", false, 4, (Object) null));
        sb.append("-");
        String string2 = dataMap.getString(K.expireDate);
        Intrinsics.checkExpressionValueIsNotNull(string2, "couponInfo.getString(\"expireDate\")");
        sb.append(StringsKt.replace$default(string2, "-", "/", false, 4, (Object) null));
        textView.setText(sb.toString());
        ((ImageView) _$_findCachedViewById(R.id.ivQRCode)).setImageBitmap(QRUtil.createQRCode(dataMap.getString(K.couponNo), 1000));
        TextView textView2 = (TextView) _$_findCachedViewById(R.id.tvCouponNo);
        Intrinsics.checkExpressionValueIsNotNull(textView2, "tvCouponNo");
        textView2.setText("NO." + dataMap.getString(K.couponNo));
        WebView webView = (WebView) _$_findCachedViewById(R.id.webDesc);
        Intrinsics.checkExpressionValueIsNotNull(webView, "webDesc");
        ViewExt.loadHtmlStr(webView, dataMap.getString(K.remark), 10);
        String string3 = dataMap.getString(K.couponState);
        if (Intrinsics.areEqual(string3, "2")) {
            LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(R.id.llUsedMask);
            Intrinsics.checkExpressionValueIsNotNull(linearLayout, "llUsedMask");
            linearLayout.setVisibility(0);
        } else if (Intrinsics.areEqual(string3, "3")) {
            LinearLayout linearLayout2 = (LinearLayout) _$_findCachedViewById(R.id.llUsedMask);
            Intrinsics.checkExpressionValueIsNotNull(linearLayout2, "llUsedMask");
            linearLayout2.setVisibility(0);
            ((ImageView) _$_findCachedViewById(R.id.ivHasUse)).setImageResource(R.drawable.ic_has_expired);
        }
    }

    private final void getCouponInfo() {
        ApiUtil.getApi2().getMyCouponDetail(this.couponNo).compose(RxUtil.applyDataMapIO()).subscribe(new MyCouponDetailActivity$getCouponInfo$1(this, this, this.first));
    }
}

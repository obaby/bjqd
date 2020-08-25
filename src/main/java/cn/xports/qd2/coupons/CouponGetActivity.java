package cn.xports.qd2.coupons;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.xports.base.BaseBussActivity;
import cn.xports.baselib.bean.DataMap;
import cn.xports.baselib.http.RetrofitUtil;
import cn.xports.baselib.http.RxDisposableManager;
import cn.xports.baselib.http.RxUtil;
import cn.xports.baselib.util.DataMapUtils;
import cn.xports.baselib.util.MoneyUtil;
import cn.xports.baselib.widget.FakeBoldText;
import cn.xports.http.SodaService;
import cn.xports.qd2.R;
import cn.xports.qd2.entity.K;
import cn.xports.qd2.util.IDCardUtil;
import cn.xports.qd2.util.ViewExt;
import cn.xports.widget.AgreementBar;
import cn.xports.widget.CornerTextView;
import com.blankj.utilcode.util.ColorUtils;
import com.blankj.utilcode.util.SpanUtils;
import io.reactivex.Observable;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u000b\u001a\u00020\fH\u0002J\b\u0010\r\u001a\u00020\u000eH\u0002J\b\u0010\u000f\u001a\u00020\u000eH\u0002J\b\u0010\u0010\u001a\u00020\u000eH\u0002J\b\u0010\u0011\u001a\u00020\u0006H\u0014J\b\u0010\u0012\u001a\u00020\u0013H\u0014J\u0010\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\bH\u0002J\b\u0010\u0016\u001a\u00020\u000eH\u0016J\b\u0010\u0017\u001a\u00020\u000eH\u0014J\u0010\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\fH\u0016J\u0010\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\bH\u0016J\u0010\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\bH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcn/xports/qd2/coupons/CouponGetActivity;", "Lcn/xports/base/BaseBussActivity;", "Lcn/xports/qd2/coupons/CouponGetPresenter;", "Lcn/xports/qd2/coupons/CouponGetView;", "()V", "campId", "", "coupon", "Lcn/xports/baselib/bean/DataMap;", "couponId", "venueId", "checkPhoneNum", "", "collectInfo", "", "downTime", "getAgreement", "getChildTitle", "getLayoutId", "", "initCouponInfo", "dataMap", "initData", "initView", "needPsptId", "isNeed", "onCouponDetail", "onGetFinish", "map", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: CouponGetActivity.kt */
public final class CouponGetActivity extends BaseBussActivity<CouponGetPresenter> implements CouponGetView {
    private HashMap _$_findViewCache;
    private String campId = "";
    private DataMap coupon = new DataMap();
    private String couponId = "";
    private String venueId = "";

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
        return "领取优惠券";
    }

    public void onGetFinish(@NotNull DataMap dataMap) {
        Intrinsics.checkParameterIsNotNull(dataMap, "map");
        showMsg("领取成功");
        finish();
    }

    public void needPsptId(boolean z) {
        if (z) {
            LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(R.id.llPsptId);
            Intrinsics.checkExpressionValueIsNotNull(linearLayout, "llPsptId");
            linearLayout.setVisibility(0);
            EditText editText = (EditText) _$_findCachedViewById(R.id.etPsptId);
            Intrinsics.checkExpressionValueIsNotNull(editText, "etPsptId");
            ViewExt.setMaxLength$default(editText, 18, (String) null, 2, (Object) null);
            EditText editText2 = (EditText) _$_findCachedViewById(R.id.etPsptId);
            Intrinsics.checkExpressionValueIsNotNull(editText2, "etPsptId");
            ViewExt.filterBlank(editText2);
        }
    }

    public void onCouponDetail(@NotNull DataMap dataMap) {
        Intrinsics.checkParameterIsNotNull(dataMap, "dataMap");
        initCouponInfo(this.coupon);
        if (dataMap.isOK(K.wechatCouponNeedName)) {
            LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(R.id.llName);
            Intrinsics.checkExpressionValueIsNotNull(linearLayout, "llName");
            linearLayout.setVisibility(0);
            EditText editText = (EditText) _$_findCachedViewById(R.id.etName);
            Intrinsics.checkExpressionValueIsNotNull(editText, "etName");
            ViewExt.filterBlank(editText);
        }
    }

    public void initData() {
        this.campId = getStringExtra(K.campId);
        this.couponId = getStringExtra(K.couponId);
        this.venueId = getStringExtra(K.venueId);
        setPresenter(new CouponGetPresenter(this));
        DataMap fromJson = DataMapUtils.fromJson(getStringExtra(K.couponInfo));
        Intrinsics.checkExpressionValueIsNotNull(fromJson, "DataMapUtils.fromJson(ge…tringExtra(K.couponInfo))");
        this.coupon = fromJson;
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.activity_coupon_get;
    }

    /* access modifiers changed from: protected */
    public void initView() {
        CouponGetPresenter couponGetPresenter = (CouponGetPresenter) getPresenter();
        if (couponGetPresenter != null) {
            couponGetPresenter.getNeedPsptId(this.venueId);
        }
        CouponGetPresenter couponGetPresenter2 = (CouponGetPresenter) getPresenter();
        if (couponGetPresenter2 != null) {
            couponGetPresenter2.getCouponDetail(this.couponId, this.campId);
        }
        EditText editText = (EditText) _$_findCachedViewById(R.id.etPhoneNum);
        Intrinsics.checkExpressionValueIsNotNull(editText, "etPhoneNum");
        ViewExt.filterBlank(editText);
        EditText editText2 = (EditText) _$_findCachedViewById(R.id.etPhoneNum);
        Intrinsics.checkExpressionValueIsNotNull(editText2, "etPhoneNum");
        ViewExt.setMaxLength$default(editText2, 11, (String) null, 2, (Object) null);
        EditText editText3 = (EditText) _$_findCachedViewById(R.id.etVerityCode);
        Intrinsics.checkExpressionValueIsNotNull(editText3, "etVerityCode");
        ViewExt.filterBlank(editText3);
        EditText editText4 = (EditText) _$_findCachedViewById(R.id.etName);
        Intrinsics.checkExpressionValueIsNotNull(editText4, "etName");
        ViewExt.addFilter(editText4, CouponGetActivity$initView$1.INSTANCE);
        ((CornerTextView) _$_findCachedViewById(R.id.ctGetCoupon)).setOnClickListener(new CouponGetActivity$initView$2(this));
        ((AgreementBar) _$_findCachedViewById(R.id.agreementBar)).setCheckListener(new CouponGetActivity$initView$3(this));
        ((TextView) _$_findCachedViewById(R.id.tvGetCode)).setOnClickListener(new CouponGetActivity$initView$4(this));
        AgreementBar agreementBar = (AgreementBar) _$_findCachedViewById(R.id.agreementBar);
        Intrinsics.checkExpressionValueIsNotNull(agreementBar, "agreementBar");
        agreementBar.setCheck(true);
        getAgreement();
    }

    private final void initCouponInfo(DataMap dataMap) {
        String str = "";
        String string = dataMap.getString(K.valueType);
        if (string != null) {
            switch (string.hashCode()) {
                case 49:
                    if (string.equals("1")) {
                        str = "代金券";
                        String simpleYuan = MoneyUtil.simpleYuan(dataMap.getString("value"));
                        FakeBoldText fakeBoldText = (FakeBoldText) _$_findCachedViewById(R.id.tv_money);
                        Intrinsics.checkExpressionValueIsNotNull(fakeBoldText, "tv_money");
                        fakeBoldText.setText(simpleYuan);
                        break;
                    }
                    break;
                case 50:
                    if (string.equals("2")) {
                        str = "体验券";
                        break;
                    }
                    break;
                case 51:
                    if (string.equals("3")) {
                        str = "兑换券";
                        break;
                    }
                    break;
                case 52:
                    if (string.equals("4")) {
                        str = "停车券";
                        break;
                    }
                    break;
                case 53:
                    if (string.equals("5")) {
                        str = "折扣券";
                        break;
                    }
                    break;
            }
        }
        if (Intrinsics.areEqual(str, "代金券") || StringsKt.contains$default(str, "总金额", false, 2, (Object) null)) {
            TextView textView = (TextView) _$_findCachedViewById(R.id.tv_coupon_type);
            Intrinsics.checkExpressionValueIsNotNull(textView, "tv_coupon_type");
            textView.setTextSize(12.0f);
            FakeBoldText fakeBoldText2 = (FakeBoldText) _$_findCachedViewById(R.id.tv_money);
            Intrinsics.checkExpressionValueIsNotNull(fakeBoldText2, "tv_money");
            fakeBoldText2.setVisibility(0);
            FakeBoldText fakeBoldText3 = (FakeBoldText) _$_findCachedViewById(R.id.tv_money_tag);
            Intrinsics.checkExpressionValueIsNotNull(fakeBoldText3, "tv_money_tag");
            fakeBoldText3.setVisibility(0);
        } else {
            TextView textView2 = (TextView) _$_findCachedViewById(R.id.tv_coupon_type);
            Intrinsics.checkExpressionValueIsNotNull(textView2, "tv_coupon_type");
            textView2.setTextSize(14.0f);
            FakeBoldText fakeBoldText4 = (FakeBoldText) _$_findCachedViewById(R.id.tv_money);
            Intrinsics.checkExpressionValueIsNotNull(fakeBoldText4, "tv_money");
            fakeBoldText4.setVisibility(8);
            FakeBoldText fakeBoldText5 = (FakeBoldText) _$_findCachedViewById(R.id.tv_money_tag);
            Intrinsics.checkExpressionValueIsNotNull(fakeBoldText5, "tv_money_tag");
            fakeBoldText5.setVisibility(8);
        }
        if (Intrinsics.areEqual(str, "代金券")) {
            ((TextView) _$_findCachedViewById(R.id.tv_coupon_type)).setTextSize(14.0f);
            ((TextView) _$_findCachedViewById(R.id.tv_coupon_type)).getPaint().setFakeBoldText(false);
            ((TextView) _$_findCachedViewById(R.id.tv_coupon_type)).setText(str);
        } else {
            ((TextView) _$_findCachedViewById(R.id.tv_coupon_type)).setTextSize(20.0f);
            ((TextView) _$_findCachedViewById(R.id.tv_coupon_type)).getPaint().setFakeBoldText(true);
            ((TextView) _$_findCachedViewById(R.id.tv_coupon_type)).setText(str);
        }
        String string2 = dataMap.getString(K.expireDate);
        Intrinsics.checkExpressionValueIsNotNull(string2, "dataMap.getString(K.expireDate)");
        String replace$default = StringsKt.replace$default(string2, "-", "/", false, 4, (Object) null);
        String string3 = dataMap.getString(K.effectiveDate);
        Intrinsics.checkExpressionValueIsNotNull(string3, "dataMap.getString(K.effectiveDate)");
        String replace$default2 = StringsKt.replace$default(string3, "-", "/", false, 4, (Object) null);
        String str2 = "";
        if (Intrinsics.areEqual(dataMap.getString("recentExpireTag"), "1")) {
            str2 = "(即将过期)";
        }
        SpanUtils with = SpanUtils.with((TextView) _$_findCachedViewById(R.id.tv_coupon_expire));
        with.append("有效期: " + replace$default2 + '-' + replace$default).append(str2).setForegroundColor(ColorUtils.getColor(R.color.red_fd4)).create();
        TextView textView3 = (TextView) _$_findCachedViewById(R.id.tv_coupon_name);
        Intrinsics.checkExpressionValueIsNotNull(textView3, "tv_coupon_name");
        textView3.setText(dataMap.getString(K.couponName));
        TextView textView4 = (TextView) _$_findCachedViewById(R.id.tvDesc);
        Intrinsics.checkExpressionValueIsNotNull(textView4, "tvDesc");
        textView4.setText(dataMap.getString(K.remark));
        TextView textView5 = (TextView) _$_findCachedViewById(R.id.tv_coupon_desc);
        Intrinsics.checkExpressionValueIsNotNull(textView5, "tv_coupon_desc");
        StringBuilder sb = new StringBuilder();
        sb.append("满");
        Integer intValue = dataMap.getIntValue(K.orderAmount);
        Intrinsics.checkExpressionValueIsNotNull(intValue, "dataMap.getIntValue(K.orderAmount)");
        sb.append(MoneyUtil.simpleYuan(intValue.intValue()));
        sb.append("元可以使用");
        textView5.setText(sb.toString());
    }

    /* access modifiers changed from: private */
    public final void collectInfo() {
        LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(R.id.llName);
        Intrinsics.checkExpressionValueIsNotNull(linearLayout, "llName");
        boolean z = false;
        if (linearLayout.getVisibility() == 0) {
            EditText editText = (EditText) _$_findCachedViewById(R.id.etName);
            Intrinsics.checkExpressionValueIsNotNull(editText, "etName");
            CharSequence text = editText.getText();
            if (text == null || text.length() == 0) {
                showMsg("请输入姓名");
                return;
            }
        }
        LinearLayout linearLayout2 = (LinearLayout) _$_findCachedViewById(R.id.llPsptId);
        Intrinsics.checkExpressionValueIsNotNull(linearLayout2, "llPsptId");
        if (linearLayout2.getVisibility() == 0) {
            EditText editText2 = (EditText) _$_findCachedViewById(R.id.etPsptId);
            Intrinsics.checkExpressionValueIsNotNull(editText2, "etPsptId");
            CharSequence text2 = editText2.getText();
            if (text2 == null || text2.length() == 0) {
                showMsg("请输入身份证号");
                return;
            }
            EditText editText3 = (EditText) _$_findCachedViewById(R.id.etPsptId);
            Intrinsics.checkExpressionValueIsNotNull(editText3, "etPsptId");
            if (!IDCardUtil.checkIDCard(editText3.getText().toString())) {
                showMsg("请输入正确的身份证号");
                return;
            }
        }
        if (checkPhoneNum()) {
            EditText editText4 = (EditText) _$_findCachedViewById(R.id.etVerityCode);
            Intrinsics.checkExpressionValueIsNotNull(editText4, "etVerityCode");
            CharSequence text3 = editText4.getText();
            if (text3 == null || text3.length() == 0) {
                z = true;
            }
            if (z) {
                showMsg("请输入验证码");
                return;
            }
            AgreementBar agreementBar = (AgreementBar) _$_findCachedViewById(R.id.agreementBar);
            Intrinsics.checkExpressionValueIsNotNull(agreementBar, "agreementBar");
            if (!agreementBar.isCheck()) {
                showMsg("请同意协议");
                return;
            }
            DataMap dataMap = new DataMap();
            EditText editText5 = (EditText) _$_findCachedViewById(R.id.etPhoneNum);
            Intrinsics.checkExpressionValueIsNotNull(editText5, "etPhoneNum");
            DataMap dataMap2 = dataMap.set(K.phoneNum, editText5.getText().toString());
            EditText editText6 = (EditText) _$_findCachedViewById(R.id.etVerityCode);
            Intrinsics.checkExpressionValueIsNotNull(editText6, "etVerityCode");
            dataMap2.set(K.verifyCode, editText6.getText().toString()).set(K.couponId, this.couponId).set(K.campId, this.campId);
            LinearLayout linearLayout3 = (LinearLayout) _$_findCachedViewById(R.id.llPsptId);
            Intrinsics.checkExpressionValueIsNotNull(linearLayout3, "llPsptId");
            if (linearLayout3.getVisibility() == 0) {
                EditText editText7 = (EditText) _$_findCachedViewById(R.id.etPsptId);
                Intrinsics.checkExpressionValueIsNotNull(editText7, "etPsptId");
                dataMap.set(K.psptId, editText7.getText().toString());
            }
            LinearLayout linearLayout4 = (LinearLayout) _$_findCachedViewById(R.id.llName);
            Intrinsics.checkExpressionValueIsNotNull(linearLayout4, "llName");
            if (linearLayout4.getVisibility() == 0) {
                EditText editText8 = (EditText) _$_findCachedViewById(R.id.etName);
                Intrinsics.checkExpressionValueIsNotNull(editText8, "etName");
                dataMap.set(K.custName, editText8.getText().toString());
            }
            CouponGetPresenter couponGetPresenter = (CouponGetPresenter) getPresenter();
            if (couponGetPresenter != null) {
                couponGetPresenter.checkVerify(dataMap);
            }
        }
    }

    /* access modifiers changed from: private */
    public final boolean checkPhoneNum() {
        EditText editText = (EditText) _$_findCachedViewById(R.id.etPhoneNum);
        Intrinsics.checkExpressionValueIsNotNull(editText, "etPhoneNum");
        CharSequence text = editText.getText();
        if (text == null || text.length() == 0) {
            showMsg("请输入手机号");
            return false;
        }
        EditText editText2 = (EditText) _$_findCachedViewById(R.id.etPhoneNum);
        Intrinsics.checkExpressionValueIsNotNull(editText2, "etPhoneNum");
        if (editText2.getText().length() == 11) {
            return true;
        }
        showMsg("请输入正确的手机号");
        return false;
    }

    /* access modifiers changed from: private */
    public final void downTime() {
        RxDisposableManager.getInstance().add(getTAG(), Observable.interval(0, 1, TimeUnit.SECONDS).take(61).compose(RxUtil.applyIO()).subscribe(new CouponGetActivity$downTime$sub$1(this)));
    }

    private final void getAgreement() {
        ((SodaService) RetrofitUtil.getInstance().create(SodaService.class)).getAgreement(new DataMap().set("tradeTypeCode", "134").set(K.venueId, this.venueId)).compose(RxUtil.applyErrorsWithIO()).subscribe(new CouponGetActivity$getAgreement$1(this, this));
    }
}

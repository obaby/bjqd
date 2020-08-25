package cn.xports.qd2;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import cn.xports.base.BaseBussActivity;
import cn.xports.base.GlobalDialog;
import cn.xports.baselib.http.RxDisposableManager;
import cn.xports.baselib.http.RxUtil;
import cn.xports.baselib.mvp.IPresenter;
import cn.xports.baselib.rxbind.widget.RxTextView;
import cn.xports.qd2.util.ApiUtil;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0002J\b\u0010\t\u001a\u00020\nH\u0002J\b\u0010\u000b\u001a\u00020\u0007H\u0014J\u0010\u0010\f\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\b\u0010\r\u001a\u00020\u000eH\u0014J\b\u0010\u000f\u001a\u00020\u0005H\u0016J\b\u0010\u0010\u001a\u00020\u0005H\u0014J\u0010\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0007H\u0002J\b\u0010\u0013\u001a\u00020\u0005H\u0002¨\u0006\u0014"}, d2 = {"Lcn/xports/qd2/CardBindActivity;", "Lcn/xports/base/BaseBussActivity;", "Lcn/xports/baselib/mvp/IPresenter;", "()V", "bind", "", "ecardNo", "", "code", "btnEnable", "", "getChildTitle", "getCode", "getLayoutId", "", "initData", "initView", "showDialog", "msg", "verityCodeDown", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: CardBindActivity.kt */
public final class CardBindActivity extends BaseBussActivity<IPresenter> {
    private HashMap _$_findViewCache;

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
        return "卡绑定";
    }

    public void initData() {
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.activity_card_bind;
    }

    /* access modifiers changed from: protected */
    public void initView() {
        ((TextView) _$_findCachedViewById(R.id.tvGetCode)).setOnClickListener(new CardBindActivity$initView$1(this));
        ((ImageView) _$_findCachedViewById(R.id.ivClear)).setOnClickListener(new CardBindActivity$initView$2(this));
        RxDisposableManager instance = RxDisposableManager.getInstance();
        String tag = getTAG();
        EditText editText = (EditText) _$_findCachedViewById(R.id.etCardNo);
        Intrinsics.checkExpressionValueIsNotNull(editText, "etCardNo");
        instance.add(tag, RxTextView.textChanges(editText).subscribe(new CardBindActivity$initView$3(this)));
        RxDisposableManager instance2 = RxDisposableManager.getInstance();
        String tag2 = getTAG();
        EditText editText2 = (EditText) _$_findCachedViewById(R.id.etVerityCode);
        Intrinsics.checkExpressionValueIsNotNull(editText2, "etVerityCode");
        instance2.add(tag2, RxTextView.textChanges(editText2).subscribe(new CardBindActivity$initView$4(this)));
        ((Button) _$_findCachedViewById(R.id.btnBind)).setOnClickListener(new CardBindActivity$initView$5(this));
        ((EditText) _$_findCachedViewById(R.id.etCardNo)).addTextChangedListener(new CardBindActivity$initView$6());
    }

    /* access modifiers changed from: private */
    public final void getCode(String str) {
        ApiUtil.getApi2().getVerifyCode(str, "3").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new CardBindActivity$getCode$1(this, getTAG()));
    }

    /* access modifiers changed from: private */
    public final void bind(String str, String str2) {
        ApiUtil.getApi2().bindCard(str, "3", str2).compose(RxUtil.applyErrorsWithIO()).subscribe(new CardBindActivity$bind$1(this, this));
    }

    /* access modifiers changed from: private */
    public final void showDialog(String str) {
        new GlobalDialog(this, str).setCancelVisible(false);
    }

    /* access modifiers changed from: private */
    public final boolean btnEnable() {
        EditText editText = (EditText) _$_findCachedViewById(R.id.etCardNo);
        Intrinsics.checkExpressionValueIsNotNull(editText, "etCardNo");
        CharSequence text = editText.getText();
        if (text == null || text.length() == 0) {
            return false;
        }
        EditText editText2 = (EditText) _$_findCachedViewById(R.id.etVerityCode);
        Intrinsics.checkExpressionValueIsNotNull(editText2, "etVerityCode");
        CharSequence text2 = editText2.getText();
        return !(text2 == null || text2.length() == 0);
    }

    /* access modifiers changed from: private */
    public final void verityCodeDown() {
        RxDisposableManager.getInstance().add(getTAG(), Observable.interval(0, 1, TimeUnit.SECONDS).take(61).compose(RxUtil.applyIO()).subscribe(new CardBindActivity$verityCodeDown$sub$1(this)));
    }
}

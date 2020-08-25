package cn.xports.qd2;

import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import cn.xports.base.BaseBussActivity;
import cn.xports.baselib.http.RxUtil;
import cn.xports.baselib.mvp.IPresenter;
import cn.xports.baselib.rxbind.widget.RxTextView;
import cn.xports.baselib.util.SPUtil;
import cn.xports.qd2.http.ApiService2;
import cn.xports.qd2.util.ApiUtil;
import cn.xports.qd2.util.ViewExt;
import io.reactivex.Observable;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0014J\b\u0010\u0006\u001a\u00020\u0007H\u0014J\b\u0010\b\u001a\u00020\tH\u0016J\b\u0010\n\u001a\u00020\tH\u0014J\b\u0010\u000b\u001a\u00020\tH\u0002¨\u0006\f"}, d2 = {"Lcn/xports/qd2/FeedBackActivity;", "Lcn/xports/base/BaseBussActivity;", "Lcn/xports/baselib/mvp/IPresenter;", "()V", "getChildTitle", "", "getLayoutId", "", "initData", "", "initView", "submit", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: FeedBackActivity.kt */
public final class FeedBackActivity extends BaseBussActivity<IPresenter> {
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
        return "意见反馈";
    }

    public void initData() {
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.activity_feed_back;
    }

    /* access modifiers changed from: protected */
    public void initView() {
        EditText editText = (EditText) _$_findCachedViewById(R.id.etComment);
        Intrinsics.checkExpressionValueIsNotNull(editText, "etComment");
        ViewExt.filterEmoji(editText);
        EditText editText2 = (EditText) _$_findCachedViewById(R.id.etComment);
        Intrinsics.checkExpressionValueIsNotNull(editText2, "etComment");
        RxTextView.textChangeEvents(editText2).subscribe(new FeedBackActivity$initView$1(this));
        ((TextView) _$_findCachedViewById(R.id.tvSubmitComment)).setOnClickListener(new FeedBackActivity$initView$2(this));
    }

    /* access modifiers changed from: private */
    public final void submit() {
        EditText editText = (EditText) _$_findCachedViewById(R.id.etComment);
        Intrinsics.checkExpressionValueIsNotNull(editText, "etComment");
        Editable text = editText.getText();
        Intrinsics.checkExpressionValueIsNotNull(text, "etComment.text");
        if (!(StringsKt.trim(text).length() == 0)) {
            ApiService2 api2 = ApiUtil.getApi2();
            EditText editText2 = (EditText) _$_findCachedViewById(R.id.etComment);
            Intrinsics.checkExpressionValueIsNotNull(editText2, "etComment");
            Observable<ResponseBody> feedback = api2.feedback(editText2.getText().toString(), SPUtil.Companion.getINSTANCE().getStringValue("phone"));
            Intrinsics.checkExpressionValueIsNotNull(feedback, "ApiUtil.getApi2()\n      ….getStringValue(\"phone\"))");
            RxUtil.subscribeDataMapIO$default(feedback, this, new FeedBackActivity$submit$1(this), (Function1) null, false, 12, (Object) null);
        }
    }
}

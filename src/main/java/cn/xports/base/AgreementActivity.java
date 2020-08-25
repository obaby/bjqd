package cn.xports.base;

import android.text.Html;
import android.view.View;
import android.widget.TextView;
import cn.xports.baselib.mvp.IPresenter;
import cn.xports.baselib.util.SPUtil;
import cn.xports.qdplugin.R;
import com.alipay.sdk.cons.c;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0014J\b\u0010\u0006\u001a\u00020\u0007H\u0014J\b\u0010\b\u001a\u00020\tH\u0016J\b\u0010\n\u001a\u00020\tH\u0014¨\u0006\u000b"}, d2 = {"Lcn/xports/base/AgreementActivity;", "Lcn/xports/base/BaseBussActivity;", "Lcn/xports/baselib/mvp/IPresenter;", "()V", "getChildTitle", "", "getLayoutId", "", "initData", "", "initView", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: AgreementActivity.kt */
public final class AgreementActivity extends BaseBussActivity<IPresenter> {
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
        return "协议";
    }

    public void initData() {
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.activity_agreement;
    }

    /* access modifiers changed from: protected */
    public void initView() {
        String stringExtra = getStringExtra(c.e);
        TextView textView = this.mTvTitle;
        Intrinsics.checkExpressionValueIsNotNull(textView, "mTvTitle");
        textView.setText(stringExtra);
        String stringExtra2 = getStringExtra("agreement");
        if (Intrinsics.areEqual(stringExtra2, "") && (stringExtra2 = SPUtil.Companion.getINSTANCE().getStringValue("agreement")) == null) {
            stringExtra2 = "";
        }
        TextView textView2 = (TextView) _$_findCachedViewById(R.id.tvAgreementContent);
        Intrinsics.checkExpressionValueIsNotNull(textView2, "tvAgreementContent");
        textView2.setText(Html.fromHtml(stringExtra2));
    }
}

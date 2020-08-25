package cn.xports.qd2.training;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import cn.xports.base.BaseBussActivity;
import cn.xports.baselib.mvp.IPresenter;
import cn.xports.qd2.R;
import cn.xports.qd2.entity.K;
import cn.xports.sportCoaching.WebViewDetailActivity;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u000b\u001a\u00020\u0005H\u0014J\b\u0010\f\u001a\u00020\rH\u0014J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u000fH\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcn/xports/qd2/training/CourseResvStateActivity;", "Lcn/xports/base/BaseBussActivity;", "Lcn/xports/baselib/mvp/IPresenter;", "()V", "firstOptionName", "", "firstOptionUrl", "secondOptionName", "secondOptionUrl", "state", "tip", "getChildTitle", "getLayoutId", "", "initData", "", "initView", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: CourseResvStateActivity.kt */
public final class CourseResvStateActivity extends BaseBussActivity<IPresenter> {
    private HashMap _$_findViewCache;
    private String firstOptionName = "";
    /* access modifiers changed from: private */
    public String firstOptionUrl = "";
    private String secondOptionName = "";
    /* access modifiers changed from: private */
    public String secondOptionUrl = "";
    private String state = "1";
    private String tip = "";

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
        this.state = getStringExtra(K.state);
        this.tip = getStringExtra("tip");
        this.firstOptionName = getStringExtra("firstOptionName");
        this.firstOptionUrl = getStringExtra("firstOptionUrl");
        this.secondOptionName = getStringExtra("secondOptionName");
        this.secondOptionUrl = getStringExtra("secondOptionUrl");
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.activity_course_resv_state;
    }

    /* access modifiers changed from: protected */
    public void initView() {
        TextView textView = this.mTvTitle;
        Intrinsics.checkExpressionValueIsNotNull(textView, "mTvTitle");
        textView.setText(getStringExtra(WebViewDetailActivity.TITLE));
        TextView textView2 = (TextView) _$_findCachedViewById(R.id.tvState);
        Intrinsics.checkExpressionValueIsNotNull(textView2, "tvState");
        textView2.setText(getStringExtra("content"));
        TextView textView3 = (TextView) _$_findCachedViewById(R.id.tvShowTip);
        Intrinsics.checkExpressionValueIsNotNull(textView3, "tvShowTip");
        textView3.setText(this.tip);
        TextView textView4 = (TextView) _$_findCachedViewById(R.id.tvContinueResv);
        Intrinsics.checkExpressionValueIsNotNull(textView4, "tvContinueResv");
        textView4.setText(this.firstOptionName);
        TextView textView5 = (TextView) _$_findCachedViewById(R.id.tvMyResv);
        Intrinsics.checkExpressionValueIsNotNull(textView5, "tvMyResv");
        textView5.setText(this.secondOptionName);
        if (Intrinsics.areEqual(this.state, "1")) {
            ((ImageView) _$_findCachedViewById(R.id.ivState)).setImageResource(R.drawable.bg_success);
        } else {
            ((ImageView) _$_findCachedViewById(R.id.ivState)).setImageResource(R.drawable.bg_fail);
            TextView textView6 = (TextView) _$_findCachedViewById(R.id.tvContinueResv);
            Intrinsics.checkExpressionValueIsNotNull(textView6, "tvContinueResv");
            textView6.setVisibility(8);
        }
        ((TextView) _$_findCachedViewById(R.id.tvContinueResv)).setOnClickListener(new CourseResvStateActivity$initView$1(this));
        ((TextView) _$_findCachedViewById(R.id.tvMyResv)).setOnClickListener(new CourseResvStateActivity$initView$2(this));
    }
}

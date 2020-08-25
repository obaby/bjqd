package cn.xports.qd2.credit;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import cn.xports.base.BaseBussActivity;
import cn.xports.baselib.bean.DataMap;
import cn.xports.baselib.http.RxUtil;
import cn.xports.baselib.mvp.IPresenter;
import cn.xports.qd2.R;
import cn.xports.qd2.adapter.CreditRuleAdapter;
import cn.xports.qd2.util.ApiUtil;
import cn.xports.qd2.util.ViewExt;
import cn.xports.widget.EmptyRecyclerView;
import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\n\u001a\u00020\u000bH\u0014J\b\u0010\f\u001a\u00020\rH\u0014J\b\u0010\u000e\u001a\u00020\u000fH\u0002J\b\u0010\u0010\u001a\u00020\u000fH\u0016J\b\u0010\u0011\u001a\u00020\u000fH\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\tX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcn/xports/qd2/credit/CreditRuleActivity;", "Lcn/xports/base/BaseBussActivity;", "Lcn/xports/baselib/mvp/IPresenter;", "()V", "ruleAdapter", "Lcn/xports/qd2/adapter/CreditRuleAdapter;", "rules", "Ljava/util/ArrayList;", "Lcn/xports/baselib/bean/DataMap;", "Lkotlin/collections/ArrayList;", "getChildTitle", "", "getLayoutId", "", "getRule", "", "initData", "initView", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: CreditRuleActivity.kt */
public final class CreditRuleActivity extends BaseBussActivity<IPresenter> {
    private HashMap _$_findViewCache;
    /* access modifiers changed from: private */
    public final CreditRuleAdapter ruleAdapter = new CreditRuleAdapter(this.rules);
    /* access modifiers changed from: private */
    public final ArrayList<DataMap> rules = new ArrayList<>();

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
        return "积分规则";
    }

    public void initData() {
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.activity_credit_rule;
    }

    /* access modifiers changed from: protected */
    public void initView() {
        EmptyRecyclerView emptyRecyclerView = (EmptyRecyclerView) _$_findCachedViewById(R.id.rvCreditRule);
        Intrinsics.checkExpressionValueIsNotNull(emptyRecyclerView, "rvCreditRule");
        emptyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        EmptyRecyclerView emptyRecyclerView2 = (EmptyRecyclerView) _$_findCachedViewById(R.id.rvCreditRule);
        Intrinsics.checkExpressionValueIsNotNull(emptyRecyclerView2, "rvCreditRule");
        emptyRecyclerView2.setAdapter(this.ruleAdapter);
        EmptyRecyclerView emptyRecyclerView3 = (EmptyRecyclerView) _$_findCachedViewById(R.id.rvCreditRule);
        Intrinsics.checkExpressionValueIsNotNull(emptyRecyclerView3, "rvCreditRule");
        ViewExt.setEmptyView$default(emptyRecyclerView3, "暂无数据", (String) null, (Function0) null, 6, (Object) null);
        getRule();
    }

    private final void getRule() {
        Observable<ResponseBody> pointExchangeDesc = ApiUtil.getApi2().getPointExchangeDesc("1");
        Intrinsics.checkExpressionValueIsNotNull(pointExchangeDesc, "ApiUtil.getApi2()\n      …getPointExchangeDesc(\"1\")");
        RxUtil.subscribeDataMapIO$default(pointExchangeDesc, this, new CreditRuleActivity$getRule$1(this), new CreditRuleActivity$getRule$2(this), false, 8, (Object) null);
    }
}

package cn.xports.qd2.campaign;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import cn.xports.base.BaseBussActivity;
import cn.xports.baselib.http.RxUtil;
import cn.xports.baselib.mvp.IPresenter;
import cn.xports.qd2.R;
import cn.xports.qd2.adapter.CampEnrolledAdapter;
import cn.xports.qd2.entity.CampEnrollment;
import cn.xports.qd2.util.ApiUtil;
import cn.xports.qd2.util.ViewExt;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u000b\u001a\u00020\fH\u0014J\b\u0010\r\u001a\u00020\tH\u0014J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\tH\u0002J\b\u0010\u0011\u001a\u00020\u000fH\u0016J\b\u0010\u0012\u001a\u00020\u000fH\u0014R\u001e\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcn/xports/qd2/campaign/MySportsActivity;", "Lcn/xports/base/BaseBussActivity;", "Lcn/xports/baselib/mvp/IPresenter;", "()V", "camps", "Ljava/util/ArrayList;", "Lcn/xports/qd2/entity/CampEnrollment;", "Lkotlin/collections/ArrayList;", "curPage", "", "totalPage", "getChildTitle", "", "getLayoutId", "getMyCamps", "", "pageNo", "initData", "initView", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: MySportsActivity.kt */
public final class MySportsActivity extends BaseBussActivity<IPresenter> {
    private HashMap _$_findViewCache;
    /* access modifiers changed from: private */
    public final ArrayList<CampEnrollment> camps = new ArrayList<>();
    /* access modifiers changed from: private */
    public int curPage = 1;
    /* access modifiers changed from: private */
    public int totalPage = 1;

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
        return "我的活动";
    }

    public void initData() {
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.activity_my_sprots;
    }

    /* access modifiers changed from: protected */
    public void initView() {
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.rvSportList);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "rvSportList");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(R.id.rvSportList);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView2, "rvSportList");
        recyclerView2.setAdapter(new CampEnrolledAdapter(this.camps));
        getMyCamps(1);
        RecyclerView recyclerView3 = (RecyclerView) _$_findCachedViewById(R.id.rvSportList);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView3, "rvSportList");
        ViewExt.loadMore(recyclerView3, new MySportsActivity$initView$1(this), new MySportsActivity$initView$2(this), new MySportsActivity$initView$3(this));
        ((TextView) _$_findCachedViewById(R.id.tvJoin)).setOnClickListener(new MySportsActivity$initView$4(this));
    }

    /* access modifiers changed from: private */
    public final void getMyCamps(int i) {
        ApiUtil.getApi2().getMyCamps(i).compose(RxUtil.applyErrorsWithIO()).subscribe(new MySportsActivity$getMyCamps$1(this, i, this));
    }
}

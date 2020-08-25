package cn.xports.qd2.campaign;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import cn.xports.base.BaseBussActivity;
import cn.xports.baselib.http.RxUtil;
import cn.xports.baselib.mvp.IPresenter;
import cn.xports.qd2.R;
import cn.xports.qd2.adapter.CampItemAdapter;
import cn.xports.qd2.entity.CampItem;
import cn.xports.qd2.entity.K;
import cn.xports.qd2.util.ApiUtil;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J \u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0007H\u0002J\b\u0010\u0011\u001a\u00020\rH\u0002J\b\u0010\u0012\u001a\u00020\u0007H\u0014J\b\u0010\u0013\u001a\u00020\u0014H\u0014J\b\u0010\u0015\u001a\u00020\rH\u0016J\b\u0010\u0016\u001a\u00020\rH\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\n0\tj\b\u0012\u0004\u0012\u00020\n`\u000bX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcn/xports/qd2/campaign/ChooseSportActivity;", "Lcn/xports/base/BaseBussActivity;", "Lcn/xports/baselib/mvp/IPresenter;", "()V", "adapter", "Lcn/xports/qd2/adapter/CampItemAdapter;", "campId", "", "campItems", "Ljava/util/ArrayList;", "Lcn/xports/qd2/entity/CampItem;", "Lkotlin/collections/ArrayList;", "check", "", "id", "type", "packageId", "getCampItems", "getChildTitle", "getLayoutId", "", "initData", "initView", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: ChooseSportActivity.kt */
public final class ChooseSportActivity extends BaseBussActivity<IPresenter> {
    private HashMap _$_findViewCache;
    /* access modifiers changed from: private */
    public final CampItemAdapter adapter = new CampItemAdapter(this.campItems);
    /* access modifiers changed from: private */
    public String campId = "";
    /* access modifiers changed from: private */
    public final ArrayList<CampItem> campItems = new ArrayList<>();

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
        return "选择赛事项目";
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.activity_choose_sport;
    }

    /* access modifiers changed from: protected */
    public void initView() {
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.rvCampItemList);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "rvCampItemList");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(R.id.rvCampItemList);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView2, "rvCampItemList");
        recyclerView2.setAdapter(this.adapter);
        this.adapter.setItemClick(new ChooseSportActivity$initView$1(this));
        getCampItems();
    }

    public void initData() {
        this.campId = getStringExtra(K.campId);
    }

    private final void getCampItems() {
        ApiUtil.getApi2().getCampaignItems(this.campId).compose(RxUtil.applyErrorsWithIO()).subscribe(new ChooseSportActivity$getCampItems$1(this, this));
    }

    /* access modifiers changed from: private */
    public final void check(String str, String str2, String str3) {
        ApiUtil.getApi2().checkQualification(this.campId, str).compose(RxUtil.applyErrorsWithIO()).subscribe(new ChooseSportActivity$check$1(this, str3, str, str2, this));
    }
}

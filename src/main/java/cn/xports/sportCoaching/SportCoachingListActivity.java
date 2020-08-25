package cn.xports.sportCoaching;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import cn.xports.base.BaseBussActivity;
import cn.xports.qdplugin.R;
import cn.xports.sportCoaching.SportCoachingListContract;
import com.alipay.sdk.packet.d;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0014J\b\u0010\u0007\u001a\u00020\bH\u0014J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\nH\u0014J\u0010\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000eH\u0016¨\u0006\u000f"}, d2 = {"Lcn/xports/sportCoaching/SportCoachingListActivity;", "Lcn/xports/base/BaseBussActivity;", "Lcn/xports/sportCoaching/SportCoachingPresenter;", "Lcn/xports/sportCoaching/SportCoachingListContract$View;", "()V", "getChildTitle", "", "getLayoutId", "", "initData", "", "initView", "showListData", "data", "Lcn/xports/sportCoaching/SportCoachingBean;", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: SportCoachingListActivity.kt */
public final class SportCoachingListActivity extends BaseBussActivity<SportCoachingPresenter> implements SportCoachingListContract.View {
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
        return "运动指导";
    }

    public void initData() {
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.activity_sport_coaching_list;
    }

    /* access modifiers changed from: protected */
    public void initView() {
        setPresenter(new SportCoachingPresenter(new SportCoachingListModel(), this));
        SportCoachingPresenter sportCoachingPresenter = (SportCoachingPresenter) getPresenter();
        if (sportCoachingPresenter != null) {
            sportCoachingPresenter.getDataList();
        }
    }

    public void showListData(@NotNull SportCoachingBean sportCoachingBean) {
        List<DateList> list;
        Intrinsics.checkParameterIsNotNull(sportCoachingBean, d.k);
        CollectionsKt.emptyList();
        List emptyList = CollectionsKt.emptyList();
        if (sportCoachingBean.pageInfo.list.size() > 3) {
            List<DateList> list2 = sportCoachingBean.pageInfo.list;
            Intrinsics.checkExpressionValueIsNotNull(list2, "data.pageInfo.list");
            List sortedWith = CollectionsKt.sortedWith(list2, new SportCoachingListActivity$showListData$$inlined$sortedByDescending$1());
            list = sortedWith.subList(0, 3);
            emptyList = sortedWith.subList(3, sportCoachingBean.pageInfo.list.size());
        } else {
            list = sportCoachingBean.pageInfo.list;
            Intrinsics.checkExpressionValueIsNotNull(list, "data.pageInfo.list");
        }
        Context context = this;
        SportCoachHotArtciltAdapter sportCoachHotArtciltAdapter = new SportCoachHotArtciltAdapter(list, context);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, 0, false);
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.rv_hot_artcile);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "rv_hot_artcile");
        recyclerView.setLayoutManager(linearLayoutManager);
        RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(R.id.rv_hot_artcile);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView2, "rv_hot_artcile");
        recyclerView2.setAdapter(sportCoachHotArtciltAdapter);
        SportCoachAdapter sportCoachAdapter = new SportCoachAdapter(emptyList, context);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(context);
        RecyclerView recyclerView3 = (RecyclerView) _$_findCachedViewById(R.id.rv_sport_coach);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView3, "rv_sport_coach");
        recyclerView3.setLayoutManager(linearLayoutManager2);
        RecyclerView recyclerView4 = (RecyclerView) _$_findCachedViewById(R.id.rv_sport_coach);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView4, "rv_sport_coach");
        recyclerView4.setAdapter(sportCoachAdapter);
    }
}

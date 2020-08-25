package cn.xports.qd2.campaign;

import android.app.Activity;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import cn.xports.base.BaseBussActivity;
import cn.xports.baselib.bean.DataMap;
import cn.xports.baselib.http.RxUtil;
import cn.xports.baselib.mvp.IPresenter;
import cn.xports.baselib.util.DataMapUtils;
import cn.xports.qd2.R;
import cn.xports.qd2.adapter.ScoreRankAdapter;
import cn.xports.qd2.entity.K;
import cn.xports.qd2.util.ApiUtil;
import cn.xports.qd2.util.ViewExt;
import cn.xports.widget.EmptyRecyclerView;
import com.alipay.sdk.cons.c;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.ColorUtils;
import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0010\u001a\u00020\u0006H\u0014J\b\u0010\u0011\u001a\u00020\u0012H\u0014J\b\u0010\u0013\u001a\u00020\u0014H\u0002J\b\u0010\u0015\u001a\u00020\u0014H\u0016J\b\u0010\u0016\u001a\u00020\u0014H\u0014J\u0012\u0010\u0017\u001a\u00020\u00142\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J\u0012\u0010\u001a\u001a\u00020\u00142\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J\u0012\u0010\u001b\u001a\u00020\u00142\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J\b\u0010\u001c\u001a\u00020\u0014H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\n0\tj\b\u0012\u0004\u0012\u00020\n`\u000bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u000e\u001a\u0012\u0012\u0004\u0012\u00020\n0\tj\b\u0012\u0004\u0012\u00020\n`\u000bX\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\u00060\tj\b\u0012\u0004\u0012\u00020\u0006`\u000bX\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcn/xports/qd2/campaign/CampaignRankActivity;", "Lcn/xports/base/BaseBussActivity;", "Lcn/xports/baselib/mvp/IPresenter;", "Landroid/support/design/widget/TabLayout$OnTabSelectedListener;", "()V", "campId", "", "campItemId", "campItemList", "Ljava/util/ArrayList;", "Lcn/xports/baselib/bean/DataMap;", "Lkotlin/collections/ArrayList;", "rankAdapter", "Lcn/xports/qd2/adapter/ScoreRankAdapter;", "rankList", "titles", "getChildTitle", "getLayoutId", "", "getScoreRank", "", "initData", "initView", "onTabReselected", "tab", "Landroid/support/design/widget/TabLayout$Tab;", "onTabSelected", "onTabUnselected", "setEmptyView", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: CampaignRankActivity.kt */
public final class CampaignRankActivity extends BaseBussActivity<IPresenter> implements TabLayout.OnTabSelectedListener {
    private HashMap _$_findViewCache;
    private String campId = "";
    private String campItemId = "";
    private ArrayList<DataMap> campItemList = new ArrayList<>();
    /* access modifiers changed from: private */
    public final ScoreRankAdapter rankAdapter = new ScoreRankAdapter(this.rankList);
    /* access modifiers changed from: private */
    public final ArrayList<DataMap> rankList = new ArrayList<>();
    private final ArrayList<String> titles = new ArrayList<>();

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
        return "查看赛果";
    }

    public void onTabReselected(@Nullable TabLayout.Tab tab) {
    }

    public void onTabUnselected(@Nullable TabLayout.Tab tab) {
    }

    public void initData() {
        List<DataMap> fromJsonArray = DataMapUtils.fromJsonArray(getStringExtra("campItemJson"));
        if (fromJsonArray != null) {
            this.campItemList = (ArrayList) fromJsonArray;
        }
        for (DataMap string : this.campItemList) {
            this.titles.add(string.getString(c.e));
        }
        if (!this.campItemList.isEmpty()) {
            String string2 = this.campItemList.get(0).getString(K.campId);
            Intrinsics.checkExpressionValueIsNotNull(string2, "campItemList[0].getString(\"campId\")");
            this.campId = string2;
            String string3 = this.campItemList.get(0).getString("id");
            Intrinsics.checkExpressionValueIsNotNull(string3, "campItemList[0].getString(\"id\")");
            this.campItemId = string3;
        }
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.activity_campaign_result;
    }

    /* access modifiers changed from: protected */
    public void initView() {
        useBlackTitle(false);
        Activity activity = this;
        BarUtils.setStatusBarLightMode(activity, false);
        BarUtils.setStatusBarColor(activity, ColorUtils.getColor(R.color.blue_2e6));
        this.rlTitleBar.setBackgroundColor(ColorUtils.getColor(R.color.blue_2e6));
        if (this.campItemList.size() == 1) {
            TabLayout tabLayout = (TabLayout) _$_findCachedViewById(R.id.tabLayout);
            Intrinsics.checkExpressionValueIsNotNull(tabLayout, "tabLayout");
            tabLayout.setVisibility(8);
        }
        TabLayout tabLayout2 = (TabLayout) _$_findCachedViewById(R.id.tabLayout);
        Intrinsics.checkExpressionValueIsNotNull(tabLayout2, "tabLayout");
        ViewExt.setTextColorSize(ViewExt.setUpTabLayout(ViewExt.setOnTabListener(tabLayout2, this), this.titles, R.layout.item_tab_text, -1), R.color.white, 17.0f, R.color.white, 14.0f).setTabMode(0);
        EmptyRecyclerView emptyRecyclerView = (EmptyRecyclerView) _$_findCachedViewById(R.id.rvRankList);
        Intrinsics.checkExpressionValueIsNotNull(emptyRecyclerView, "rvRankList");
        emptyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        EmptyRecyclerView emptyRecyclerView2 = (EmptyRecyclerView) _$_findCachedViewById(R.id.rvRankList);
        Intrinsics.checkExpressionValueIsNotNull(emptyRecyclerView2, "rvRankList");
        emptyRecyclerView2.setAdapter(this.rankAdapter);
        setEmptyView();
        getScoreRank();
    }

    public void onTabSelected(@Nullable TabLayout.Tab tab) {
        if (tab != null) {
            String string = this.campItemList.get(tab.getPosition()).getString("id");
            Intrinsics.checkExpressionValueIsNotNull(string, "campItemList[position].getString(\"id\")");
            this.campItemId = string;
            CardView cardView = (CardView) _$_findCachedViewById(R.id.cardMyTeam);
            Intrinsics.checkExpressionValueIsNotNull(cardView, "cardMyTeam");
            cardView.setVisibility(8);
            this.rankList.clear();
            getScoreRank();
        }
    }

    private final void getScoreRank() {
        Observable<ResponseBody> scoreRank = ApiUtil.getApi2().getScoreRank(this.campId, this.campItemId, 999, 1);
        Intrinsics.checkExpressionValueIsNotNull(scoreRank, "ApiUtil.getApi2().getSco…campId, campItemId,999,1)");
        RxUtil.subscribeDataMapIO$default(scoreRank, this, new CampaignRankActivity$getScoreRank$1(this), (Function1) null, false, 12, (Object) null);
    }

    private final void setEmptyView() {
        ((EmptyRecyclerView) _$_findCachedViewById(R.id.rvRankList)).setEmptyView(View.inflate(this, R.layout.layout_no_score, (ViewGroup) null));
    }
}

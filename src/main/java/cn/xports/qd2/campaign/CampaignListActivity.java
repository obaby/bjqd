package cn.xports.qd2.campaign;

import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.bingoogolapple.bgabanner.BGABanner;
import cn.xports.base.BaseBussActivity;
import cn.xports.baselib.http.RxUtil;
import cn.xports.baselib.mvp.IPresenter;
import cn.xports.entity.CampaignInfo;
import cn.xports.qd2.R;
import cn.xports.qd2.adapter.CampaignsAdapter;
import cn.xports.qd2.util.ApiUtil;
import cn.xports.qd2.util.ViewExt;
import cn.xports.venue.ImagePagerAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0012\u001a\u00020\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00100\u0015H\u0002J\u0018\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\fH\u0002J\b\u0010\u0019\u001a\u00020\u0010H\u0014J\b\u0010\u001a\u001a\u00020\u0013H\u0002J\b\u0010\u001b\u001a\u00020\fH\u0014J\b\u0010\u001c\u001a\u00020\u0013H\u0016J\b\u0010\u001d\u001a\u00020\u0013H\u0014J\u0012\u0010\u001e\u001a\u00020\u00132\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016J\u0012\u0010!\u001a\u00020\u00132\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016J\u0012\u0010\"\u001a\u00020\u00132\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016R\u001e\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\u00100\u0006j\b\u0012\u0004\u0012\u00020\u0010`\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lcn/xports/qd2/campaign/CampaignListActivity;", "Lcn/xports/base/BaseBussActivity;", "Lcn/xports/baselib/mvp/IPresenter;", "Landroid/support/design/widget/TabLayout$OnTabSelectedListener;", "()V", "campaignList", "Ljava/util/ArrayList;", "Lcn/xports/entity/CampaignInfo;", "Lkotlin/collections/ArrayList;", "campaignsAdpater", "Lcn/xports/qd2/adapter/CampaignsAdapter;", "curPage", "", "curState", "lastPos", "titles", "", "totalPage", "createRound", "", "urls", "", "getCampList", "pageNo", "state", "getChildTitle", "getHot", "getLayoutId", "initData", "initView", "onTabReselected", "tab", "Landroid/support/design/widget/TabLayout$Tab;", "onTabSelected", "onTabUnselected", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: CampaignListActivity.kt */
public final class CampaignListActivity extends BaseBussActivity<IPresenter> implements TabLayout.OnTabSelectedListener {
    private HashMap _$_findViewCache;
    /* access modifiers changed from: private */
    public final ArrayList<CampaignInfo> campaignList = new ArrayList<>();
    /* access modifiers changed from: private */
    public final CampaignsAdapter campaignsAdpater = new CampaignsAdapter(this.campaignList);
    /* access modifiers changed from: private */
    public int curPage = 1;
    /* access modifiers changed from: private */
    public int curState = 1;
    /* access modifiers changed from: private */
    public int lastPos;
    private final ArrayList<String> titles = new ArrayList<>();
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
        return "赛事活动";
    }

    public void initData() {
    }

    public void onTabReselected(@Nullable TabLayout.Tab tab) {
    }

    public void onTabUnselected(@Nullable TabLayout.Tab tab) {
    }

    public void onTabSelected(@Nullable TabLayout.Tab tab) {
        this.campaignList.clear();
        this.curPage = 1;
        this.totalPage = 1;
        if (tab != null) {
            this.curState = tab.getPosition() + 1;
            getCampList(this.curPage, this.curState);
        }
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.activity_campaign_list;
    }

    /* access modifiers changed from: protected */
    public void initView() {
        this.titles.add("报名中");
        this.titles.add("进行中");
        this.titles.add("已结束");
        TextView textView = (TextView) _$_findCachedViewById(R.id.tvJoinTip);
        Intrinsics.checkExpressionValueIsNotNull(textView, "tvJoinTip");
        textView.setText("暂无活动");
        TextView textView2 = (TextView) _$_findCachedViewById(R.id.tvJoin);
        Intrinsics.checkExpressionValueIsNotNull(textView2, "tvJoin");
        textView2.setVisibility(8);
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.rvSportList);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "rvSportList");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(R.id.rvSportList);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView2, "rvSportList");
        recyclerView2.setAdapter(this.campaignsAdpater);
        RecyclerView recyclerView3 = (RecyclerView) _$_findCachedViewById(R.id.rvSportList);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView3, "rvSportList");
        ViewExt.loadMore(recyclerView3, new CampaignListActivity$initView$1(this), new CampaignListActivity$initView$2(this), new CampaignListActivity$initView$3(this));
        ((BGABanner) _$_findCachedViewById(R.id.bgaBanner)).setAdapter(new ImagePagerAdapter());
        ((BGABanner) _$_findCachedViewById(R.id.bgaBanner)).setOnPageChangeListener(new CampaignListActivity$initView$4(this));
        getHot();
        TabLayout tabLayout = (TabLayout) _$_findCachedViewById(R.id.tabLayout);
        Intrinsics.checkExpressionValueIsNotNull(tabLayout, "tabLayout");
        ViewExt.setTextColorSize(ViewExt.setUpTabLayout$default(ViewExt.setOnTabListener(tabLayout, this), this.titles, R.layout.item_tab_text, 0, 4, (Object) null), R.color.blue_2e6, 17.0f, R.color.gray_888, 14.0f);
    }

    /* access modifiers changed from: private */
    public final void getCampList(int i, int i2) {
        ApiUtil.getApi2().getCampaignList(i, String.valueOf(i2), "").compose(RxUtil.applyErrorsWithIO()).subscribe(new CampaignListActivity$getCampList$1(this, i, this));
    }

    private final void getHot() {
        ApiUtil.getApi2().getCampaignList(1, "1", "1").compose(RxUtil.applyErrorsWithIO()).subscribe(new CampaignListActivity$getHot$1(this, this));
    }

    /* access modifiers changed from: private */
    public final void createRound(List<String> list) {
        for (String areEqual : list) {
            View view = new View(this);
            view.setBackgroundResource(R.drawable.indicate_round);
            view.setEnabled(false);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(20, 20);
            if (!Intrinsics.areEqual(list.get(0), areEqual)) {
                layoutParams.leftMargin = 10;
            } else {
                view.setEnabled(true);
            }
            ((LinearLayout) _$_findCachedViewById(R.id.llRowRound)).addView(view, layoutParams);
        }
    }
}

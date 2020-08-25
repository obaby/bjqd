package cn.xports.qd2;

import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;
import cn.xports.base.BaseBussActivity;
import cn.xports.baselib.http.RetrofitUtil;
import cn.xports.baselib.http.RxUtil;
import cn.xports.baselib.mvp.IPresenter;
import cn.xports.entity.ValidProduct;
import cn.xports.http.SodaService;
import cn.xports.qd2.adapter.CardBuyAdapter;
import cn.xports.qd2.adapter.TabLayoutAdapter;
import cn.xports.qd2.entity.K;
import cn.xports.widget.EmptyRecyclerView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\n\n\u0002\u0010 \n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J(\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\b\u0010\u0018\u001a\u00020\u000bH\u0014J\b\u0010\u0019\u001a\u00020\u0014H\u0014J\b\u0010\u001a\u001a\u00020\u0010H\u0002J\b\u0010\u001b\u001a\u00020\u0010H\u0016J\b\u0010\u001c\u001a\u00020\u0010H\u0014J\u0012\u0010\u001d\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\u0012\u0010\u001e\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\u0012\u0010\u001f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\u0016\u0010 \u001a\u00020\u00102\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u000b0\"H\u0002R\u001e\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bX\u0004¢\u0006\u0002\n\u0000RJ\u0010\t\u001a>\u0012\u0004\u0012\u00020\u000b\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\b0\nj\u001e\u0012\u0004\u0012\u00020\u000b\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\b`\fX\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\u0006j\b\u0012\u0004\u0012\u00020\u000b`\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lcn/xports/qd2/Card2BuyActivity;", "Lcn/xports/base/BaseBussActivity;", "Lcn/xports/baselib/mvp/IPresenter;", "Landroid/support/design/widget/TabLayout$OnTabSelectedListener;", "()V", "productList", "Ljava/util/ArrayList;", "Lcn/xports/entity/ValidProduct;", "Lkotlin/collections/ArrayList;", "productMap", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "titles", "venueId", "changeTabText", "", "tab", "Landroid/support/design/widget/TabLayout$Tab;", "visibility", "", "color", "textSize", "", "getChildTitle", "getLayoutId", "getProductList", "initData", "initView", "onTabReselected", "onTabSelected", "onTabUnselected", "setUpTabLayout", "titleList", "", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: Card2BuyActivity.kt */
public final class Card2BuyActivity extends BaseBussActivity<IPresenter> implements TabLayout.OnTabSelectedListener {
    private HashMap _$_findViewCache;
    /* access modifiers changed from: private */
    public final ArrayList<ValidProduct> productList = new ArrayList<>();
    /* access modifiers changed from: private */
    public final HashMap<String, ArrayList<ValidProduct>> productMap = new HashMap<>();
    /* access modifiers changed from: private */
    public final ArrayList<String> titles = new ArrayList<>();
    private String venueId = "";

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
        return "购买会员卡";
    }

    public void onTabReselected(@Nullable TabLayout.Tab tab) {
    }

    public void onTabUnselected(@Nullable TabLayout.Tab tab) {
        if (tab != null) {
            changeTabText(tab, 8, -1, 15.0f);
        }
    }

    public void onTabSelected(@Nullable TabLayout.Tab tab) {
        if (tab != null) {
            changeTabText(tab, 0, -1, 17.0f);
            this.productList.clear();
            ArrayList arrayList = this.productMap.get(this.titles.get(tab.getPosition()));
            if (arrayList != null) {
                this.productList.addAll(arrayList);
            }
            EmptyRecyclerView emptyRecyclerView = (EmptyRecyclerView) _$_findCachedViewById(R.id.rvDepositList);
            Intrinsics.checkExpressionValueIsNotNull(emptyRecyclerView, "rvDepositList");
            emptyRecyclerView.getAdapter().notifyDataSetChanged();
        }
    }

    public void initData() {
        String stringExtra = getIntent().getStringExtra(K.venueId);
        if (stringExtra != null) {
            this.venueId = stringExtra;
        }
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.activity_card2buy;
    }

    /* access modifiers changed from: protected */
    public void initView() {
        EmptyRecyclerView emptyRecyclerView = (EmptyRecyclerView) _$_findCachedViewById(R.id.rvDepositList);
        Intrinsics.checkExpressionValueIsNotNull(emptyRecyclerView, "rvDepositList");
        emptyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        EmptyRecyclerView emptyRecyclerView2 = (EmptyRecyclerView) _$_findCachedViewById(R.id.rvDepositList);
        Intrinsics.checkExpressionValueIsNotNull(emptyRecyclerView2, "rvDepositList");
        emptyRecyclerView2.setAdapter(new CardBuyAdapter(this.productList));
        getProductList();
    }

    /* access modifiers changed from: private */
    public final void setUpTabLayout(List<String> list) {
        TabLayoutAdapter tabLayoutAdapter = new TabLayoutAdapter(list);
        TabLayout tabLayout = (TabLayout) _$_findCachedViewById(R.id.tabLayout);
        Intrinsics.checkExpressionValueIsNotNull(tabLayout, "tabLayout");
        tabLayout.setTabMode(0);
        ((TabLayout) _$_findCachedViewById(R.id.tabLayout)).setOnTabSelectedListener(this);
        int size = list.size();
        for (int i = 0; i < size; i++) {
            TabLayout.Tab newTab = ((TabLayout) _$_findCachedViewById(R.id.tabLayout)).newTab();
            Intrinsics.checkExpressionValueIsNotNull(newTab, "tabLayout.newTab()");
            newTab.setCustomView(tabLayoutAdapter.getView(this, i));
            ((TabLayout) _$_findCachedViewById(R.id.tabLayout)).addTab(newTab);
            if (i == 0) {
                changeTabText(newTab, 0, -1, 17.0f);
            }
        }
    }

    private final void changeTabText(TabLayout.Tab tab, int i, int i2, float f) {
        View customView = tab.getCustomView();
        if (customView == null) {
            Intrinsics.throwNpe();
        }
        View findViewById = customView.findViewById(R.id.tv_item_tab);
        if (findViewById != null) {
            TextView textView = (TextView) findViewById;
            View findViewById2 = customView.findViewById(R.id.item_tab_indicator);
            Intrinsics.checkExpressionValueIsNotNull(findViewById2, "singleTabView.findViewBy…(R.id.item_tab_indicator)");
            textView.setTextSize(2, f);
            if (i2 != -1) {
                textView.setTextColor(ContextCompat.getColor(this, i2));
            }
            findViewById2.setVisibility(i);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
    }

    private final void getProductList() {
        ((SodaService) RetrofitUtil.getInstance().create(SodaService.class)).getProducts(this.venueId).compose(RxUtil.applyErrorsWithIO()).subscribe(new Card2BuyActivity$getProductList$1(this, this));
    }
}

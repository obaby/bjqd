package cn.xports.qd2.coupons;

import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import cn.xports.base.BaseBussActivity;
import cn.xports.baselib.bean.DataMap;
import cn.xports.baselib.http.RetrofitUtil;
import cn.xports.baselib.http.RxUtil;
import cn.xports.baselib.mvp.IPresenter;
import cn.xports.qd2.R;
import cn.xports.qd2.adapter.MyCouponAdapter;
import cn.xports.qd2.http.ApiService2;
import cn.xports.qd2.util.ViewExt;
import cn.xports.widget.EmptyRecyclerView;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0011\u001a\u00020\fH\u0014J\b\u0010\u0012\u001a\u00020\u000eH\u0014J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u000eH\u0002J\b\u0010\u0016\u001a\u00020\u0014H\u0016J\b\u0010\u0017\u001a\u00020\u0014H\u0014J\u0012\u0010\u0018\u001a\u00020\u00142\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\u0012\u0010\u001b\u001a\u00020\u00142\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\u0012\u0010\u001c\u001a\u00020\u00142\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\f0\bj\b\u0012\u0004\u0012\u00020\f`\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000eX\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcn/xports/qd2/coupons/MyCouponsActivity;", "Lcn/xports/base/BaseBussActivity;", "Lcn/xports/baselib/mvp/IPresenter;", "Landroid/support/design/widget/TabLayout$OnTabSelectedListener;", "()V", "adapter", "Lcn/xports/qd2/adapter/MyCouponAdapter;", "couponList", "Ljava/util/ArrayList;", "Lcn/xports/baselib/bean/DataMap;", "Lkotlin/collections/ArrayList;", "couponState", "", "pageNo", "", "titles", "totalPage", "getChildTitle", "getLayoutId", "getMyCoupons", "", "pageNO", "initData", "initView", "onTabReselected", "tab", "Landroid/support/design/widget/TabLayout$Tab;", "onTabSelected", "onTabUnselected", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: MyCouponsActivity.kt */
public final class MyCouponsActivity extends BaseBussActivity<IPresenter> implements TabLayout.OnTabSelectedListener {
    private HashMap _$_findViewCache;
    /* access modifiers changed from: private */
    public final MyCouponAdapter adapter = new MyCouponAdapter(this.couponList);
    /* access modifiers changed from: private */
    public final ArrayList<DataMap> couponList = new ArrayList<>();
    /* access modifiers changed from: private */
    public String couponState = "1";
    /* access modifiers changed from: private */
    public int pageNo = 1;
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
        return "我的优惠券";
    }

    public void onTabReselected(@Nullable TabLayout.Tab tab) {
    }

    public void onTabUnselected(@Nullable TabLayout.Tab tab) {
    }

    public void onTabSelected(@Nullable TabLayout.Tab tab) {
        if (tab != null) {
            this.couponList.clear();
            this.couponState = String.valueOf(tab.getPosition() + 1);
            this.pageNo = 1;
            this.totalPage = 1;
            getMyCoupons(1);
        }
    }

    public void initData() {
        this.titles.add("未使用");
        this.titles.add("已使用");
        this.titles.add("已过期");
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.activity_my_coupons;
    }

    /* access modifiers changed from: protected */
    public void initView() {
        TabLayout tabLayout = (TabLayout) _$_findCachedViewById(R.id.tabLayout);
        Intrinsics.checkExpressionValueIsNotNull(tabLayout, "tabLayout");
        ViewExt.setTextColorSize(ViewExt.setUpTabLayout$default(ViewExt.setOnTabListener(tabLayout, this), this.titles, R.layout.item_tab_text, 0, 4, (Object) null), R.color.blue_2e6, 17.0f, R.color.gray_737, 14.0f);
        EmptyRecyclerView emptyRecyclerView = (EmptyRecyclerView) _$_findCachedViewById(R.id.rvMyCoupons);
        Intrinsics.checkExpressionValueIsNotNull(emptyRecyclerView, "rvMyCoupons");
        emptyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        EmptyRecyclerView emptyRecyclerView2 = (EmptyRecyclerView) _$_findCachedViewById(R.id.rvMyCoupons);
        Intrinsics.checkExpressionValueIsNotNull(emptyRecyclerView2, "rvMyCoupons");
        emptyRecyclerView2.setAdapter(this.adapter);
        this.adapter.setOnItemClickListener(new MyCouponsActivity$initView$1(this));
        EmptyRecyclerView emptyRecyclerView3 = (EmptyRecyclerView) _$_findCachedViewById(R.id.rvMyCoupons);
        Intrinsics.checkExpressionValueIsNotNull(emptyRecyclerView3, "rvMyCoupons");
        ((EmptyRecyclerView) _$_findCachedViewById(R.id.rvMyCoupons)).setEmptyView(ViewExt.getEmptyView(emptyRecyclerView3, "暂无可用优惠券！"));
        EmptyRecyclerView emptyRecyclerView4 = (EmptyRecyclerView) _$_findCachedViewById(R.id.rvMyCoupons);
        Intrinsics.checkExpressionValueIsNotNull(emptyRecyclerView4, "rvMyCoupons");
        ViewExt.loadMore(emptyRecyclerView4, new MyCouponsActivity$initView$2(this), new MyCouponsActivity$initView$3(this), new MyCouponsActivity$initView$4(this));
    }

    /* access modifiers changed from: private */
    public final void getMyCoupons(int i) {
        ((ApiService2) RetrofitUtil.getInstance().create(ApiService2.class)).getMyCouponList(this.couponState, i).compose(RxUtil.applyDataMapIO()).subscribe(new MyCouponsActivity$getMyCoupons$1(this, i, this));
    }
}

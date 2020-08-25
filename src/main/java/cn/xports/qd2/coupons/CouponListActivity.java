package cn.xports.qd2.coupons;

import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import cn.xports.base.BaseBussActivity;
import cn.xports.baselib.bean.DataMap;
import cn.xports.qd2.R;
import cn.xports.qd2.adapter.CouponFinishBinder;
import cn.xports.qd2.adapter.CouponGetBinder;
import cn.xports.qd2.adapter.TextServiceBinder;
import cn.xports.qd2.dialog.CouponDropWindow;
import cn.xports.qd2.entity.K;
import cn.xports.qd2.entity.Option;
import cn.xports.qd2.util.ViewExt;
import cn.xports.widget.EmptyRecyclerView;
import com.blankj.utilcode.util.TimeUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import me.drakeet.multitype.ItemViewBinder;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u0004B\u0005¢\u0006\u0002\u0010\u0005J\b\u0010\u001b\u001a\u00020\tH\u0014J\b\u0010\u001c\u001a\u00020\u001dH\u0014J\b\u0010\u001e\u001a\u00020\u001fH\u0016J\b\u0010 \u001a\u00020\u001fH\u0014J\u0012\u0010!\u001a\u00020\u001f2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016J\u0012\u0010$\u001a\u00020\u001f2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016J\u0012\u0010%\u001a\u00020\u001f2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016J\u0016\u0010&\u001a\u00020\u001f2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0016J\u0010\u0010(\u001a\u00020\u001f2\u0006\u0010)\u001a\u00020\fH\u0016J\u0016\u0010*\u001a\u00020\u001f2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\f0\u000bj\b\u0012\u0004\u0012\u00020\f`\rX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u000e¢\u0006\u0002\n\u0000R*\u0010\u0014\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00150\u000bj\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u0015`\rX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0017\u001a\u0012\u0012\u0004\u0012\u00020\f0\u000bj\b\u0012\u0004\u0012\u00020\f`\rX\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0018\u001a\u0012\u0012\u0004\u0012\u00020\t0\u000bj\b\u0012\u0004\u0012\u00020\t`\rX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Lcn/xports/qd2/coupons/CouponListActivity;", "Lcn/xports/base/BaseBussActivity;", "Lcn/xports/qd2/coupons/CouponListPresenter;", "Landroid/support/design/widget/TabLayout$OnTabSelectedListener;", "Lcn/xports/qd2/coupons/CouponListView;", "()V", "adapter", "Lme/drakeet/multitype/MultiTypeAdapter;", "couponClass", "", "couponClassList", "Ljava/util/ArrayList;", "Lcn/xports/baselib/bean/DataMap;", "Lkotlin/collections/ArrayList;", "couponItems", "Lme/drakeet/multitype/Items;", "dropWindow", "Lcn/xports/qd2/dialog/CouponDropWindow;", "hasVenues", "", "optionService", "Lcn/xports/qd2/entity/Option;", "serviceId", "serviceList", "titles", "valueType", "venueId", "getChildTitle", "getLayoutId", "", "initData", "", "initView", "onTabReselected", "tab", "Landroid/support/design/widget/TabLayout$Tab;", "onTabSelected", "onTabUnselected", "showCouponList", "venueList", "showTabLayout", "dataMap", "showVenueList", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: CouponListActivity.kt */
public final class CouponListActivity extends BaseBussActivity<CouponListPresenter> implements TabLayout.OnTabSelectedListener, CouponListView {
    private HashMap _$_findViewCache;
    private final MultiTypeAdapter adapter = new MultiTypeAdapter(this.couponItems);
    /* access modifiers changed from: private */
    public String couponClass = "";
    private ArrayList<DataMap> couponClassList = new ArrayList<>();
    private final Items couponItems = new Items();
    /* access modifiers changed from: private */
    public final CouponDropWindow dropWindow = new CouponDropWindow();
    private boolean hasVenues;
    private final ArrayList<Option<DataMap>> optionService = new ArrayList<>();
    /* access modifiers changed from: private */
    public String serviceId = "";
    private ArrayList<DataMap> serviceList = new ArrayList<>();
    /* access modifiers changed from: private */
    public final ArrayList<String> titles = new ArrayList<>();
    private String valueType = "";
    /* access modifiers changed from: private */
    public String venueId = "";

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
        return "领取优惠券";
    }

    public void onTabUnselected(@Nullable TabLayout.Tab tab) {
    }

    public void showVenueList(@NotNull ArrayList<DataMap> arrayList) {
        Intrinsics.checkParameterIsNotNull(arrayList, "venueList");
        if (!this.hasVenues) {
            this.hasVenues = true;
            ArrayList arrayList2 = new ArrayList();
            for (DataMap dataMap : arrayList) {
                arrayList2.add(new Option().setValue(dataMap.getString(K.venueId)).setName(dataMap.getString(K.venueName)));
            }
            this.dropWindow.setVenueList(arrayList2);
        }
    }

    public void showCouponList(@NotNull ArrayList<DataMap> arrayList) {
        Intrinsics.checkParameterIsNotNull(arrayList, "venueList");
        this.couponItems.clear();
        ArrayList arrayList2 = new ArrayList();
        for (DataMap dataMap : arrayList) {
            this.couponItems.add(dataMap.getString(K.venueName));
            ArrayList<DataMap> dataList = dataMap.getDataList(K.couponList);
            Intrinsics.checkExpressionValueIsNotNull(dataList, "venue.getDataList(K.couponList)");
            for (DataMap dataMap2 : dataList) {
                if (TimeUtils.getTimeSpanByNow(dataMap2.getString(K.endDate), 1000) < 0) {
                    arrayList2.add(dataMap2);
                } else {
                    this.couponItems.add(dataMap2);
                }
            }
        }
        Collection collection = arrayList2;
        if (!collection.isEmpty()) {
            this.couponItems.add(new Option());
            this.couponItems.addAll(collection);
        }
        this.adapter.notifyDataSetChanged();
    }

    public void showTabLayout(@NotNull DataMap dataMap) {
        Intrinsics.checkParameterIsNotNull(dataMap, "dataMap");
        ArrayList<DataMap> dataList = dataMap.getDataList(K.serviceList);
        Intrinsics.checkExpressionValueIsNotNull(dataList, "dataMap.getDataList(K.serviceList)");
        this.serviceList = dataList;
        for (DataMap dataMap2 : this.serviceList) {
            this.titles.add(dataMap2.getString(K.serviceName));
            this.optionService.add(new Option().setName(dataMap2.getString(K.serviceName)).setValue(dataMap2.getString(K.serviceId)));
        }
        this.optionService.add(0, new Option().setName("全部").setValue(""));
        TabLayout tabLayout = (TabLayout) _$_findCachedViewById(R.id.tabLayout);
        Intrinsics.checkExpressionValueIsNotNull(tabLayout, "tabLayout");
        ViewExt.setTextColorSize(ViewExt.setUpTabLayout$default(ViewExt.setOnTabListener(tabLayout, this), this.titles, R.layout.item_tab_text, 0, 4, (Object) null), R.color.blue_2e6, 14.0f, R.color.gray_333, 14.0f);
        TabLayout tabLayout2 = (TabLayout) _$_findCachedViewById(R.id.tabLayout);
        Intrinsics.checkExpressionValueIsNotNull(tabLayout2, "tabLayout");
        tabLayout2.setTabMode(0);
        ArrayList<DataMap> dataList2 = dataMap.getDataList(K.couponClassList);
        Intrinsics.checkExpressionValueIsNotNull(dataList2, "dataMap.getDataList(K.couponClassList)");
        this.couponClassList = dataList2;
        ArrayList arrayList = new ArrayList();
        for (DataMap dataMap3 : this.couponClassList) {
            arrayList.add(new Option().setName(dataMap3.getString(K.valueName)).setValue(dataMap3.getString(K.paramValue)));
        }
        this.dropWindow.setCouponClassList(arrayList).setServiceList(this.optionService).setOptListener(new CouponListActivity$showTabLayout$3(this));
    }

    public void onTabSelected(@Nullable TabLayout.Tab tab) {
        if (tab != null) {
            if (tab.getPosition() == 0) {
                this.serviceId = "";
            } else {
                int position = tab.getPosition() - 1;
                if (this.serviceList.size() > position) {
                    String string = this.serviceList.get(position).getString(K.serviceId);
                    Intrinsics.checkExpressionValueIsNotNull(string, "serviceList[p].getString(K.serviceId)");
                    this.serviceId = string;
                }
            }
            CouponListPresenter couponListPresenter = (CouponListPresenter) getPresenter();
            if (couponListPresenter != null) {
                couponListPresenter.getCouponList(this.venueId, this.serviceId, this.couponClass);
            }
            for (Option option : this.optionService) {
                option.setSelect(Intrinsics.areEqual(this.serviceId, option.getValue()));
            }
        }
    }

    public void onTabReselected(@Nullable TabLayout.Tab tab) {
        CouponListPresenter couponListPresenter = (CouponListPresenter) getPresenter();
        if (couponListPresenter != null) {
            couponListPresenter.getCouponList(this.venueId, this.serviceId, this.couponClass);
        }
    }

    public void initData() {
        setPresenter(new CouponListPresenter(this));
        this.titles.add("全部");
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.activity_coupon_list;
    }

    /* access modifiers changed from: protected */
    public void initView() {
        EmptyRecyclerView emptyRecyclerView = (EmptyRecyclerView) _$_findCachedViewById(R.id.rvCoupons);
        Intrinsics.checkExpressionValueIsNotNull(emptyRecyclerView, "rvCoupons");
        ((EmptyRecyclerView) _$_findCachedViewById(R.id.rvCoupons)).setEmptyView(ViewExt.getEmptyView(emptyRecyclerView, "暂无优惠券信息"));
        EmptyRecyclerView emptyRecyclerView2 = (EmptyRecyclerView) _$_findCachedViewById(R.id.rvCoupons);
        Intrinsics.checkExpressionValueIsNotNull(emptyRecyclerView2, "rvCoupons");
        emptyRecyclerView2.setLayoutManager(new LinearLayoutManager(this));
        this.adapter.register(String.class, new TextServiceBinder());
        this.adapter.register(Option.class, new CouponFinishBinder());
        ItemViewBinder couponGetBinder = new CouponGetBinder();
        couponGetBinder.setOnItemClickListener(new CouponListActivity$initView$1(this));
        this.adapter.register(DataMap.class, couponGetBinder);
        EmptyRecyclerView emptyRecyclerView3 = (EmptyRecyclerView) _$_findCachedViewById(R.id.rvCoupons);
        Intrinsics.checkExpressionValueIsNotNull(emptyRecyclerView3, "rvCoupons");
        emptyRecyclerView3.setAdapter(this.adapter);
        ((ImageView) _$_findCachedViewById(R.id.ivFilter)).setOnClickListener(new CouponListActivity$initView$2(this));
        CouponListPresenter couponListPresenter = (CouponListPresenter) getPresenter();
        if (couponListPresenter != null) {
            couponListPresenter.getCouponServices();
        }
    }
}

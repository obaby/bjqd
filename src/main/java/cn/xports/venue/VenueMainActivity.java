package cn.xports.venue;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.bingoogolapple.bgabanner.BGABanner;
import cn.xports.base.BaseBussActivity;
import cn.xports.baselib.http.RetrofitUtil;
import cn.xports.baselib.util.GlideUtil;
import cn.xports.baselib.util.SPUtil;
import cn.xports.baselib.util.ToastUtil;
import cn.xports.entity.Service;
import cn.xports.parser.HomeBannerBean;
import cn.xports.qd2.entity.K;
import cn.xports.qdplugin.R;
import cn.xports.venue.VenueMainContract;
import cn.xports.widget.NoScrollViewPager;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.blankj.utilcode.util.BarUtils;
import com.stub.StubApp;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00100\u0014H\u0002J\b\u0010\u0015\u001a\u00020\u0010H\u0014J\b\u0010\u0016\u001a\u00020\nH\u0014J\b\u0010\u0017\u001a\u00020\u0012H\u0003J\u0010\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\b\u0010\u001b\u001a\u00020\u0012H\u0016J\b\u0010\u001c\u001a\u00020\u0012H\u0014J\b\u0010\u001d\u001a\u00020\u0012H\u0014J\u001a\u0010\u001e\u001a\u00020\u00122\u0010\u0010\u001f\u001a\f\u0012\b\u0012\u00060 R\u00020!0\u0014H\u0016J\u0010\u0010\"\u001a\u00020\u00122\u0006\u0010#\u001a\u00020\u0010H\u0016J\u0016\u0010$\u001a\u00020\u00122\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\f0\u0014H\u0016J\u0010\u0010&\u001a\u00020\u00122\u0006\u0010'\u001a\u00020\u0010H\u0016R\u001e\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R!\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\f0\u0006j\b\u0012\u0004\u0012\u00020\f`\b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001e\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\u00100\u0006j\b\u0012\u0004\u0012\u00020\u0010`\bX\u0004¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Lcn/xports/venue/VenueMainActivity;", "Lcn/xports/base/BaseBussActivity;", "Lcn/xports/venue/VenueMainPresenter;", "Lcn/xports/venue/VenueMainContract$View;", "()V", "fragments", "Ljava/util/ArrayList;", "Landroid/support/v4/app/Fragment;", "Lkotlin/collections/ArrayList;", "lastPos", "", "list", "Lcn/xports/entity/Service;", "getList", "()Ljava/util/ArrayList;", "titles", "", "createRound", "", "urls", "", "getChildTitle", "getLayoutId", "getLocation", "getLocationAddress", "location", "Landroid/location/Location;", "initData", "initView", "onResume", "showBanner", "bannerAd", "Lcn/xports/parser/HomeBannerBean$AdList;", "Lcn/xports/parser/HomeBannerBean;", "showCityLocation", "cityName", "showServiceList", "serviceList", "showWeather", "temperature", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: VenueMainActivity.kt */
public final class VenueMainActivity extends BaseBussActivity<VenueMainPresenter> implements VenueMainContract.View {
    private HashMap _$_findViewCache;
    private final ArrayList<Fragment> fragments = new ArrayList<>();
    /* access modifiers changed from: private */
    public int lastPos;
    @NotNull
    private final ArrayList<Service> list = new ArrayList<>();
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
        return "青岛国信体育中心";
    }

    @NotNull
    public final ArrayList<Service> getList() {
        return this.list;
    }

    public void showServiceList(@NotNull List<? extends Service> list2) {
        PagerAdapter adapter;
        Intrinsics.checkParameterIsNotNull(list2, K.serviceList);
        this.list.clear();
        this.list.addAll(list2);
        this.fragments.clear();
        this.titles.clear();
        for (Service service : list2) {
            this.fragments.add(VenueListFragment.newInstance(service.getServiceId()));
            this.titles.add(service.getServiceName());
        }
        this.fragments.add(0, VenueListFragment.newInstance("-111"));
        this.titles.add(0, "全部");
        NoScrollViewPager noScrollViewPager = (NoScrollViewPager) _$_findCachedViewById(R.id.viewPager);
        if (noScrollViewPager != null && (adapter = noScrollViewPager.getAdapter()) != null) {
            adapter.notifyDataSetChanged();
        }
    }

    public void showCityLocation(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "cityName");
        CharSequence charSequence = str;
        if (!TextUtils.isEmpty(charSequence)) {
            TextView textView = (TextView) _$_findCachedViewById(R.id.tv_city_name);
            Intrinsics.checkExpressionValueIsNotNull(textView, "tv_city_name");
            textView.setText(charSequence);
        }
    }

    public void showBanner(@NotNull List<? extends HomeBannerBean.AdList> list2) {
        Intrinsics.checkParameterIsNotNull(list2, "bannerAd");
        ArrayList arrayList = new ArrayList();
        for (HomeBannerBean.AdList adList : list2) {
            arrayList.add(adList.cover);
        }
        List list3 = arrayList;
        createRound(list3);
        ((BGABanner) _$_findCachedViewById(R.id.bgaBanner_home)).setAdapter(new ImagePagerAdapter());
        ((BGABanner) _$_findCachedViewById(R.id.bgaBanner_home)).setData(list3, (List<String>) null);
        ((BGABanner) _$_findCachedViewById(R.id.bgaBanner_home)).setOnPageChangeListener(new VenueMainActivity$showBanner$1(this));
        ((BGABanner) _$_findCachedViewById(R.id.bgaBanner_home)).setDelegate(new VenueMainActivity$showBanner$2(this, list2));
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.activity_venue_main_new;
    }

    /* access modifiers changed from: protected */
    public void initView() {
        setPresenter(new VenueMainPresenter(new VenueMainModel(), this));
        ((NoScrollViewPager) _$_findCachedViewById(R.id.viewPager)).setCanScroll(false);
        NoScrollViewPager noScrollViewPager = (NoScrollViewPager) _$_findCachedViewById(R.id.viewPager);
        Intrinsics.checkExpressionValueIsNotNull(noScrollViewPager, "viewPager");
        noScrollViewPager.setAdapter(new VenueTabAdapter(getSupportFragmentManager(), this.fragments, this.titles));
        ((TabLayout) _$_findCachedViewById(R.id.tabLayout)).setupWithViewPager((NoScrollViewPager) _$_findCachedViewById(R.id.viewPager));
        ((TabLayout) _$_findCachedViewById(R.id.tabLayout)).addOnTabSelectedListener(new VenueMainActivity$initView$1());
        this.mIvBack.setImageResource(R.drawable.ic_back_white);
        Activity activity = this;
        BarUtils.setStatusBarLightMode(activity, false);
        BarUtils.setStatusBarColor(activity, getResources().getColor(R.color.blue_4f7));
        this.rlTitleBar.setBackgroundColor(getResources().getColor(R.color.blue_4f7));
        this.rlRoot.setBackgroundColor(getResources().getColor(R.color.blue_4f7));
        this.mTvTitle.setTextColor(-1);
        ImageView imageView = this.mIvRight;
        Intrinsics.checkExpressionValueIsNotNull(imageView, "mIvRight");
        imageView.setVisibility(0);
        GlideUtil.loadRoundImage(this, SPUtil.Companion.getINSTANCE().getStringValue("qdPhoto"), R.drawable.ic_my_tag).into(this.mIvRight);
        this.mIvRight.setOnClickListener(new VenueMainActivity$initView$2(this));
        ((TextView) _$_findCachedViewById(R.id.tv_integral_exchange)).setOnClickListener(new VenueMainActivity$initView$3(this));
        ((TextView) _$_findCachedViewById(R.id.tvSportActivity)).setOnClickListener(new VenueMainActivity$initView$4(this));
        ((TextView) _$_findCachedViewById(R.id.tvTeacherClass)).setOnClickListener(new VenueMainActivity$initView$5(this));
        ((TextView) _$_findCachedViewById(R.id.tv_sport_coach)).setOnClickListener(new VenueMainActivity$initView$6(this));
        String stringExtra = getIntent().getStringExtra("phone");
        String stringExtra2 = getIntent().getStringExtra("outUid");
        VenueMainPresenter venueMainPresenter = (VenueMainPresenter) getPresenter();
        if (venueMainPresenter != null) {
            venueMainPresenter.getAppInfo(stringExtra, stringExtra2);
        }
        getLocation();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        VenueMainPresenter venueMainPresenter = (VenueMainPresenter) getPresenter();
        if (venueMainPresenter != null) {
            venueMainPresenter.getOrderNum();
        }
    }

    public void initData() {
        SPUtil.Companion.getINSTANCE().save("channelId", "7");
        RetrofitUtil.addHttpParamExtent(VenueMainActivity$initData$1.INSTANCE);
    }

    private final void createRound(List<String> list2) {
        for (String areEqual : list2) {
            View view = new View(this);
            view.setBackgroundResource(R.drawable.indicate_round);
            view.setEnabled(false);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(20, 20);
            if (!Intrinsics.areEqual(list2.get(0), areEqual)) {
                layoutParams.leftMargin = 10;
            } else {
                view.setEnabled(true);
            }
            ((LinearLayout) _$_findCachedViewById(R.id.llRowRound_home)).addView(view, layoutParams);
        }
    }

    @SuppressLint({"MissingPermission"})
    private final void getLocation() {
        Object systemService = getSystemService(RequestParameters.SUBRESOURCE_LOCATION);
        if (systemService == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.location.LocationManager");
        } else if (!((LocationManager) systemService).isProviderEnabled("gps")) {
            VenueMainPresenter venueMainPresenter = (VenueMainPresenter) getPresenter();
            if (venueMainPresenter != null) {
                venueMainPresenter.getWeather("青岛市");
            }
        } else {
            Object systemService2 = getSystemService(RequestParameters.SUBRESOURCE_LOCATION);
            if (systemService2 != null) {
                LocationManager locationManager = (LocationManager) systemService2;
                Criteria criteria = new Criteria();
                criteria.setAccuracy(1);
                criteria.setAltitudeRequired(false);
                criteria.setBearingRequired(false);
                criteria.setCostAllowed(true);
                criteria.setPowerRequirement(1);
                try {
                    Location lastKnownLocation = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, true));
                    Intrinsics.checkExpressionValueIsNotNull(lastKnownLocation, "locationManager.getLastKnownLocation(provider)");
                    String locationAddress = getLocationAddress(lastKnownLocation);
                    if (!TextUtils.isEmpty(locationAddress)) {
                        TextView textView = (TextView) _$_findCachedViewById(R.id.tv_city_name);
                        Intrinsics.checkExpressionValueIsNotNull(textView, "tv_city_name");
                        textView.setText(locationAddress);
                        VenueMainPresenter venueMainPresenter2 = (VenueMainPresenter) getPresenter();
                        if (venueMainPresenter2 != null) {
                            venueMainPresenter2.getWeather(locationAddress);
                        }
                    }
                } catch (Exception unused) {
                    ToastUtil.showMsg("没有定位权限");
                    VenueMainPresenter venueMainPresenter3 = (VenueMainPresenter) getPresenter();
                    if (venueMainPresenter3 != null) {
                        venueMainPresenter3.getWeather("青岛市");
                    }
                }
            } else {
                throw new TypeCastException("null cannot be cast to non-null type android.location.LocationManager");
            }
        }
    }

    private final String getLocationAddress(Location location) {
        try {
            Address address = new Geocoder(StubApp.getOrigApplicationContext(getApplicationContext()), Locale.CHINESE).getFromLocation(location.getLatitude(), location.getLongitude(), 1).get(0);
            StringBuilder sb = new StringBuilder();
            sb.append("getLocationAddress: ");
            Intrinsics.checkExpressionValueIsNotNull(address, "address");
            sb.append(address.getLocality());
            Log.i(">>>", sb.toString());
            String locality = address.getLocality();
            Intrinsics.checkExpressionValueIsNotNull(locality, "address.locality");
            return locality;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public void showWeather(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "temperature");
        TextView textView = (TextView) _$_findCachedViewById(R.id.tv_tempr);
        Intrinsics.checkExpressionValueIsNotNull(textView, "tv_tempr");
        textView.setText(str + 8451);
    }
}

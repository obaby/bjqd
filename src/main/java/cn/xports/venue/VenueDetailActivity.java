package cn.xports.venue;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.bingoogolapple.bgabanner.BGABanner;
import cn.xports.base.BaseBussActivity;
import cn.xports.baselib.http.RetrofitUtil;
import cn.xports.baselib.http.RxUtil;
import cn.xports.baselib.util.BarUtils;
import cn.xports.baselib.util.DensityUtil;
import cn.xports.entity.AdditionalService;
import cn.xports.entity.TicketType;
import cn.xports.entity.ValidProduct;
import cn.xports.entity.Venue;
import cn.xports.http.SodaService;
import cn.xports.parser.FieldSaleParser;
import cn.xports.parser.TicketParser;
import cn.xports.parser.VenueDetailParser;
import cn.xports.qd2.entity.K;
import cn.xports.qdplugin.R;
import cn.xports.venue.VenueDetailContract;
import cn.xports.widget.PointIndexView;
import cn.xports.widget.pagelayout.PagerGridLayoutManager;
import cn.xports.widget.pagelayout.PagerGridSnapHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0016\u0010$\u001a\u00020%2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00120'H\u0002J\b\u0010(\u001a\u00020%H\u0002J\b\u0010)\u001a\u00020\u0012H\u0014J\b\u0010*\u001a\u00020\u0010H\u0014J\b\u0010+\u001a\u00020%H\u0016J\b\u0010,\u001a\u00020%H\u0002J\b\u0010-\u001a\u00020%H\u0014J\u0012\u0010.\u001a\u00020%2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J\u0012\u0010/\u001a\u00020%2\b\u00100\u001a\u0004\u0018\u000101H\u0016J\u0010\u00102\u001a\u00020%2\u0006\u00103\u001a\u000204H\u0016J\u001e\u00105\u001a\u00020%2\u0006\u00106\u001a\u00020\u00102\f\u00107\u001a\b\u0012\u0004\u0012\u00020\u001208H\u0016J\u001e\u00109\u001a\u00020%2\u0006\u00106\u001a\u00020\u00102\f\u00107\u001a\b\u0012\u0004\u0012\u00020\u001208H\u0016J\u0016\u0010:\u001a\u00020%2\f\u0010;\u001a\b\u0012\u0004\u0012\u00020\u00120'H\u0002J\u0010\u0010<\u001a\u00020%2\u0006\u0010=\u001a\u00020\u0012H\u0002J\b\u0010>\u001a\u00020?H\u0014R\u000e\u0010\u0005\u001a\u00020\u0006X.¢\u0006\u0002\n\u0000R\u001e\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\nX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0011\u001a\u0012\u0012\u0004\u0012\u00020\u00120\bj\b\u0012\u0004\u0012\u00020\u0012`\nX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0015\u001a\u0012\u0012\u0004\u0012\u00020\u00160\bj\b\u0012\u0004\u0012\u00020\u0016`\nX\u0004¢\u0006\u0002\n\u0000RJ\u0010\u0017\u001a>\u0012\u0004\u0012\u00020\u0012\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u00160\bj\b\u0012\u0004\u0012\u00020\u0016`\n0\u0018j\u001e\u0012\u0004\u0012\u00020\u0012\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u00160\bj\b\u0012\u0004\u0012\u00020\u0016`\n`\u0019X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0012X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX.¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX.¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X.¢\u0006\u0002\n\u0000R\u001e\u0010!\u001a\u0012\u0012\u0004\u0012\u00020\"0\bj\b\u0012\u0004\u0012\u00020\"`\nX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0012X\u000e¢\u0006\u0002\n\u0000¨\u0006@"}, d2 = {"Lcn/xports/venue/VenueDetailActivity;", "Lcn/xports/base/BaseBussActivity;", "Lcn/xports/venue/VenueDetailPresenter;", "Lcn/xports/venue/VenueDetailContract$View;", "()V", "additionAdapter", "Lcn/xports/venue/VenueServiceTagAdapter;", "additionalServices", "Ljava/util/ArrayList;", "Lcn/xports/entity/AdditionalService;", "Lkotlin/collections/ArrayList;", "fieldAdapter", "Lcn/xports/venue/VenueFieldAdapter;", "fieldSaleParser", "Lcn/xports/parser/FieldSaleParser;", "lastPos", "", "limitTags", "", "mVenue", "Lcn/xports/entity/Venue;", "productList", "Lcn/xports/entity/ValidProduct;", "productMap", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "serviceId", "spacePager", "Lcn/xports/widget/pagelayout/PagerGridLayoutManager;", "test", "Landroid/support/v7/widget/LinearLayoutManager;", "ticketAdapter", "Lcn/xports/venue/VenueTicketAdapter;", "ticketTypes", "Lcn/xports/entity/TicketType;", "venueId", "createRound", "", "urls", "", "getCard2Buy", "getChildTitle", "getLayoutId", "initData", "initRecyclerView", "initView", "onGetFieldSale", "onGetTicketScle", "ticketParser", "Lcn/xports/parser/TicketParser;", "onGetVenueDetail", "venueDetailParser", "Lcn/xports/parser/VenueDetailParser;", "onPermissionsDenied", "requestCode", "perms", "", "onPermissionsGranted", "setUpTypes", "types", "updateCardTypeList", "type", "useBaseLayout", "", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: VenueDetailActivity.kt */
public final class VenueDetailActivity extends BaseBussActivity<VenueDetailPresenter> implements VenueDetailContract.View {
    private HashMap _$_findViewCache;
    private VenueServiceTagAdapter additionAdapter;
    private ArrayList<AdditionalService> additionalServices = new ArrayList<>();
    private VenueFieldAdapter fieldAdapter;
    private FieldSaleParser fieldSaleParser;
    /* access modifiers changed from: private */
    public int lastPos;
    /* access modifiers changed from: private */
    public final ArrayList<String> limitTags = new ArrayList<>();
    private Venue mVenue;
    private final ArrayList<ValidProduct> productList = new ArrayList<>();
    /* access modifiers changed from: private */
    public final HashMap<String, ArrayList<ValidProduct>> productMap = new HashMap<>();
    private String serviceId = "";
    private PagerGridLayoutManager spacePager;
    private LinearLayoutManager test;
    /* access modifiers changed from: private */
    public VenueTicketAdapter ticketAdapter;
    private ArrayList<TicketType> ticketTypes = new ArrayList<>();
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
        return "";
    }

    public void onPermissionsDenied(int i, @NotNull List<String> list) {
        Intrinsics.checkParameterIsNotNull(list, "perms");
    }

    /* access modifiers changed from: protected */
    public boolean useBaseLayout() {
        return false;
    }

    public static final /* synthetic */ VenueTicketAdapter access$getTicketAdapter$p(VenueDetailActivity venueDetailActivity) {
        VenueTicketAdapter venueTicketAdapter = venueDetailActivity.ticketAdapter;
        if (venueTicketAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ticketAdapter");
        }
        return venueTicketAdapter;
    }

    public void onGetFieldSale(@Nullable FieldSaleParser fieldSaleParser2) {
        if (fieldSaleParser2 != null) {
            fieldSaleParser2.setVenueId(this.venueId);
            fieldSaleParser2.setServiceId(this.serviceId);
            int i = 0;
            if ((fieldSaleParser2.getFieldTypeList() != null && fieldSaleParser2.getFieldTypeList().size() != 0) || (fieldSaleParser2.getFieldCal() != null && fieldSaleParser2.getFieldCal().size() != 0)) {
                LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(R.id.llFieldOrder);
                Intrinsics.checkExpressionValueIsNotNull(linearLayout, "llFieldOrder");
                linearLayout.setVisibility(0);
                LinearLayout linearLayout2 = (LinearLayout) _$_findCachedViewById(R.id.llFieldCant);
                Intrinsics.checkExpressionValueIsNotNull(linearLayout2, "llFieldCant");
                linearLayout2.setVisibility(8);
                this.fieldAdapter = new VenueFieldAdapter(fieldSaleParser2);
                RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.rvFieldToBook);
                Intrinsics.checkExpressionValueIsNotNull(recyclerView, "rvFieldToBook");
                PagerGridLayoutManager pagerGridLayoutManager = this.spacePager;
                if (pagerGridLayoutManager == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("spacePager");
                }
                recyclerView.setLayoutManager(pagerGridLayoutManager);
                new PagerGridSnapHelper().attachToRecyclerView((RecyclerView) _$_findCachedViewById(R.id.rvFieldToBook));
                RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(R.id.rvFieldToBook);
                Intrinsics.checkExpressionValueIsNotNull(recyclerView2, "rvFieldToBook");
                recyclerView2.setAdapter(this.fieldAdapter);
                PointIndexView pointIndexView = (PointIndexView) _$_findCachedViewById(R.id.pointIndex);
                PagerGridLayoutManager pagerGridLayoutManager2 = this.spacePager;
                if (pagerGridLayoutManager2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("spacePager");
                }
                pointIndexView.setCount(pagerGridLayoutManager2.getTotalPageCount(), 1);
                PagerGridLayoutManager pagerGridLayoutManager3 = this.spacePager;
                if (pagerGridLayoutManager3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("spacePager");
                }
                pagerGridLayoutManager3.setPageListener(new VenueDetailActivity$onGetFieldSale$$inlined$apply$lambda$1(this));
            } else if (TextUtils.isEmpty(fieldSaleParser2.getWechatFieldOpenTime())) {
                LinearLayout linearLayout3 = (LinearLayout) _$_findCachedViewById(R.id.llFieldOrder);
                Intrinsics.checkExpressionValueIsNotNull(linearLayout3, "llFieldOrder");
                linearLayout3.setVisibility(8);
            } else {
                LinearLayout linearLayout4 = (LinearLayout) _$_findCachedViewById(R.id.llFieldOrder);
                Intrinsics.checkExpressionValueIsNotNull(linearLayout4, "llFieldOrder");
                linearLayout4.setVisibility(0);
                LinearLayout linearLayout5 = (LinearLayout) _$_findCachedViewById(R.id.llFieldCan);
                Intrinsics.checkExpressionValueIsNotNull(linearLayout5, "llFieldCan");
                linearLayout5.setVisibility(8);
                LinearLayout linearLayout6 = (LinearLayout) _$_findCachedViewById(R.id.llFieldCant);
                Intrinsics.checkExpressionValueIsNotNull(linearLayout6, "llFieldCant");
                linearLayout6.setVisibility(0);
                String wechatFieldOpenTime = fieldSaleParser2.getWechatFieldOpenTime();
                Intrinsics.checkExpressionValueIsNotNull(wechatFieldOpenTime, "wechatFieldOpenTime");
                CharSequence charSequence = wechatFieldOpenTime;
                String str = "";
                int i2 = 0;
                while (i < charSequence.length()) {
                    char charAt = charSequence.charAt(i);
                    int i3 = i2 + 1;
                    if (i2 == 2 || i2 == 7) {
                        str = str + ":";
                    }
                    str = str + charAt;
                    i++;
                    i2 = i3;
                }
                TextView textView = (TextView) _$_findCachedViewById(R.id.tvCanTime);
                Intrinsics.checkExpressionValueIsNotNull(textView, "tvCanTime");
                textView.setText("可预约时间: " + str);
            }
            if (fieldSaleParser2 != null) {
                return;
            }
        }
        VenueDetailActivity venueDetailActivity = this;
        LinearLayout linearLayout7 = (LinearLayout) _$_findCachedViewById(R.id.llFieldOrder);
        Intrinsics.checkExpressionValueIsNotNull(linearLayout7, "llFieldOrder");
        linearLayout7.setVisibility(8);
        Unit unit = Unit.INSTANCE;
    }

    public void onGetVenueDetail(@NotNull VenueDetailParser venueDetailParser) {
        Intrinsics.checkParameterIsNotNull(venueDetailParser, "venueDetailParser");
        if (Intrinsics.areEqual("1", venueDetailParser.getShowPromTag())) {
            RelativeLayout relativeLayout = (RelativeLayout) _$_findCachedViewById(R.id.rlCoupon);
            Intrinsics.checkExpressionValueIsNotNull(relativeLayout, "rlCoupon");
            relativeLayout.setVisibility(0);
        }
        this.mVenue = venueDetailParser.getVenue();
        Venue venue = this.mVenue;
        if (venue != null) {
            ((TextView) _$_findCachedViewById(R.id.toolbar_title)).setText(venue.getVenueName());
            TextView textView = (TextView) _$_findCachedViewById(R.id.tvVenueName);
            Intrinsics.checkExpressionValueIsNotNull(textView, "tvVenueName");
            textView.setText(venue.getVenueName());
            TextView textView2 = (TextView) _$_findCachedViewById(R.id.tvAddress);
            Intrinsics.checkExpressionValueIsNotNull(textView2, "tvAddress");
            textView2.setText(venue.getAddress());
            ((TextView) _$_findCachedViewById(R.id.tvAddress)).setOnClickListener(new VenueDetailActivity$onGetVenueDetail$$inlined$apply$lambda$1(venue, this));
            TextView textView3 = (TextView) _$_findCachedViewById(R.id.tvBusinessHour);
            Intrinsics.checkExpressionValueIsNotNull(textView3, "tvBusinessHour");
            textView3.setText("营业时间：" + venue.getBusinessHours());
            if (venue.getVenueImages() == null || venue.getVenueImages().size() == 0) {
                venue.setVenueImages(new ArrayList());
                venue.getVenueImages().add("empty");
            }
            List<String> venueImages = venue.getVenueImages();
            Intrinsics.checkExpressionValueIsNotNull(venueImages, "venueImages");
            createRound(venueImages);
            ((BGABanner) _$_findCachedViewById(R.id.bgaBanner)).setAdapter(new ImagePagerAdapter());
            ((BGABanner) _$_findCachedViewById(R.id.bgaBanner)).setData(venue.getVenueImages(), (List<String>) null);
            ((BGABanner) _$_findCachedViewById(R.id.bgaBanner)).setOnPageChangeListener(new VenueDetailActivity$onGetVenueDetail$$inlined$apply$lambda$2(this));
            this.additionalServices.clear();
            this.additionalServices.addAll(venue.getAdditionalService());
            VenueServiceTagAdapter venueServiceTagAdapter = this.additionAdapter;
            if (venueServiceTagAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("additionAdapter");
            }
            venueServiceTagAdapter.notifyDataSetChanged();
        }
    }

    public void onGetTicketScle(@Nullable TicketParser ticketParser) {
        if (ticketParser != null) {
            List<TicketType> ticketTypeList = ticketParser.getTicketTypeList();
            RelativeLayout relativeLayout = (RelativeLayout) _$_findCachedViewById(R.id.rlMore);
            Intrinsics.checkExpressionValueIsNotNull(relativeLayout, "rlMore");
            relativeLayout.setVisibility(ticketTypeList.size() > 5 ? 0 : 8);
            if (ticketTypeList.size() > 0) {
                LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(R.id.llTicketBuy);
                Intrinsics.checkExpressionValueIsNotNull(linearLayout, "llTicketBuy");
                linearLayout.setVisibility(0);
                this.ticketTypes.clear();
                this.ticketTypes.addAll(ticketTypeList);
                VenueTicketAdapter venueTicketAdapter = this.ticketAdapter;
                if (venueTicketAdapter == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("ticketAdapter");
                }
                venueTicketAdapter.notifyData();
            }
        }
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.activity_venue_detail;
    }

    /* access modifiers changed from: protected */
    public void initView() {
        ((ImageView) _$_findCachedViewById(R.id.iv_back)).setOnClickListener(new VenueDetailActivity$initView$1(this));
        ((ImageView) _$_findCachedViewById(R.id.ivCallPhone)).setOnClickListener(new VenueDetailActivity$initView$2(this));
        ((ImageView) _$_findCachedViewById(R.id.ivGetCoupon)).setOnClickListener(new VenueDetailActivity$initView$3(this));
        ((TextView) _$_findCachedViewById(R.id.toolbar_title)).setText(getTitle());
        Toolbar toolbar = (Toolbar) _$_findCachedViewById(R.id.toolbar);
        Intrinsics.checkExpressionValueIsNotNull(toolbar, "toolbar");
        ViewGroup.LayoutParams layoutParams = toolbar.getLayoutParams();
        if (layoutParams != null) {
            CollapsingToolbarLayout.LayoutParams layoutParams2 = (CollapsingToolbarLayout.LayoutParams) layoutParams;
            layoutParams2.setMargins(0, BarUtils.getStatusBarHeight(), 0, 0);
            Toolbar toolbar2 = (Toolbar) _$_findCachedViewById(R.id.toolbar);
            Intrinsics.checkExpressionValueIsNotNull(toolbar2, "toolbar");
            ViewGroup.LayoutParams layoutParams3 = toolbar2.getLayoutParams();
            if (layoutParams3 != null) {
                CollapsingToolbarLayout.LayoutParams layoutParams4 = (CollapsingToolbarLayout.LayoutParams) layoutParams3;
                layoutParams4.setMargins(0, BarUtils.getStatusBarHeight(), 0, 0);
                Toolbar toolbar3 = (Toolbar) _$_findCachedViewById(R.id.toolbar);
                Intrinsics.checkExpressionValueIsNotNull(toolbar3, "toolbar");
                toolbar3.setLayoutParams(layoutParams2);
                ((TextView) _$_findCachedViewById(R.id.toolbar_title)).setLayoutParams(layoutParams4);
                ((AppBarLayout) _$_findCachedViewById(R.id.appbarLayout)).addOnOffsetChangedListener(new VenueDetailActivity$initView$4(this));
                initRecyclerView();
                VenueDetailPresenter venueDetailPresenter = (VenueDetailPresenter) getPresenter();
                if (venueDetailPresenter != null) {
                    venueDetailPresenter.getVenueDetail(this.venueId);
                }
                VenueDetailPresenter venueDetailPresenter2 = (VenueDetailPresenter) getPresenter();
                if (venueDetailPresenter2 != null) {
                    venueDetailPresenter2.getFieldSaleList(this.venueId, this.serviceId);
                }
                VenueDetailPresenter venueDetailPresenter3 = (VenueDetailPresenter) getPresenter();
                if (venueDetailPresenter3 != null) {
                    venueDetailPresenter3.getTicketSaleList(this.venueId, this.serviceId);
                }
                getCard2Buy();
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type android.support.design.widget.CollapsingToolbarLayout.LayoutParams");
        }
        throw new TypeCastException("null cannot be cast to non-null type android.support.design.widget.CollapsingToolbarLayout.LayoutParams");
    }

    private final void initRecyclerView() {
        this.spacePager = new PagerGridLayoutManager(1, 2, 1);
        Context context = this;
        this.test = new LinearLayoutManager(context, 0, false);
        this.ticketAdapter = new VenueTicketAdapter(this, this.ticketTypes);
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.rvTicket2Buy);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "rvTicket2Buy");
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(R.id.rvTicket2Buy);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView2, "rvTicket2Buy");
        VenueTicketAdapter venueTicketAdapter = this.ticketAdapter;
        if (venueTicketAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ticketAdapter");
        }
        recyclerView2.setAdapter(venueTicketAdapter);
        ((RelativeLayout) _$_findCachedViewById(R.id.rlMore)).setOnClickListener(new VenueDetailActivity$initRecyclerView$1(this));
        this.additionAdapter = new VenueServiceTagAdapter(this.additionalServices);
        RecyclerView recyclerView3 = (RecyclerView) _$_findCachedViewById(R.id.rvServiceList);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView3, "rvServiceList");
        recyclerView3.setLayoutManager(new GridLayoutManager(context, 4));
        ((RecyclerView) _$_findCachedViewById(R.id.rvServiceList)).post(new VenueDetailActivity$initRecyclerView$2(this));
        RecyclerView recyclerView4 = (RecyclerView) _$_findCachedViewById(R.id.rvServiceList);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView4, "rvServiceList");
        VenueServiceTagAdapter venueServiceTagAdapter = this.additionAdapter;
        if (venueServiceTagAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("additionAdapter");
        }
        recyclerView4.setAdapter(venueServiceTagAdapter);
        RecyclerView recyclerView5 = (RecyclerView) _$_findCachedViewById(R.id.rvCard2Buy);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView5, "rvCard2Buy");
        recyclerView5.setLayoutManager(new LinearLayoutManager(context));
        VenueCardBuyAdapter venueCardBuyAdapter = new VenueCardBuyAdapter(this.productList);
        RecyclerView recyclerView6 = (RecyclerView) _$_findCachedViewById(R.id.rvCard2Buy);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView6, "rvCard2Buy");
        recyclerView6.setAdapter(venueCardBuyAdapter);
        ((RelativeLayout) _$_findCachedViewById(R.id.rlCardMore)).setOnClickListener(new VenueDetailActivity$initRecyclerView$3(this, venueCardBuyAdapter));
        ((TextView) _$_findCachedViewById(R.id.tvMyCard)).setOnClickListener(new VenueDetailActivity$initRecyclerView$4(this));
    }

    public void onPermissionsGranted(int i, @NotNull List<String> list) {
        VenueDetailPresenter venueDetailPresenter;
        Intrinsics.checkParameterIsNotNull(list, "perms");
        Venue venue = this.mVenue;
        if (venue != null && (venueDetailPresenter = (VenueDetailPresenter) getPresenter()) != null) {
            String phone = venue.getPhone();
            Intrinsics.checkExpressionValueIsNotNull(phone, "phone");
            venueDetailPresenter.callPhone(phone);
        }
    }

    public void initData() {
        this.serviceId = getStringExtra(K.serviceId);
        String stringExtra = getIntent().getStringExtra(K.venueId);
        Intrinsics.checkExpressionValueIsNotNull(stringExtra, "intent.getStringExtra(\"venueId\")");
        this.venueId = stringExtra;
        setPresenter(new VenueDetailPresenter(new VenueDetailModel(), this));
    }

    private final void createRound(List<String> list) {
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

    private final void getCard2Buy() {
        ((SodaService) RetrofitUtil.getInstance().create(SodaService.class)).getProducts(this.venueId).compose(RxUtil.applyErrorsWithIO()).subscribe(new VenueDetailActivity$getCard2Buy$1(this, this));
    }

    /* access modifiers changed from: private */
    public final void setUpTypes(List<String> list) {
        ((LinearLayout) _$_findCachedViewById(R.id.llCardTypeList)).removeAllViews();
        int i = 0;
        for (Object next : list) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            String str = (String) next;
            View inflate = LayoutInflater.from(this).inflate(R.layout.item_card_type, (LinearLayout) _$_findCachedViewById(R.id.llCard2Buy), false);
            if (inflate != null) {
                CheckBox checkBox = (CheckBox) inflate;
                checkBox.setText(ValidProduct.getCardTypeName(str));
                checkBox.setOnClickListener(new VenueDetailActivity$setUpTypes$$inlined$forEachIndexed$lambda$1(checkBox, str, this));
                if (i == 0) {
                    checkBox.setChecked(true);
                    checkBox.setEnabled(false);
                }
                ViewGroup.LayoutParams layoutParams = checkBox.getLayoutParams();
                if (layoutParams != null) {
                    LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
                    layoutParams2.setMargins(0, 0, DensityUtil.dp2px(10.0f), 0);
                    checkBox.setLayoutParams(layoutParams2);
                    ((LinearLayout) _$_findCachedViewById(R.id.llCardTypeList)).addView(checkBox);
                    i = i2;
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type android.widget.LinearLayout.LayoutParams");
                }
            } else {
                throw new TypeCastException("null cannot be cast to non-null type android.widget.CheckBox");
            }
        }
    }

    /* access modifiers changed from: private */
    public final void updateCardTypeList(String str) {
        this.productList.clear();
        ArrayList arrayList = this.productMap.get(str);
        if (arrayList != null) {
            this.productList.addAll(arrayList);
        }
        RelativeLayout relativeLayout = (RelativeLayout) _$_findCachedViewById(R.id.rlCardMore);
        Intrinsics.checkExpressionValueIsNotNull(relativeLayout, "rlCardMore");
        relativeLayout.setVisibility(this.productList.size() > 5 ? 0 : 8);
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.rvCard2Buy);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "rvCard2Buy");
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter != null) {
            ((VenueCardBuyAdapter) adapter).notifyData();
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type cn.xports.venue.VenueCardBuyAdapter");
    }
}

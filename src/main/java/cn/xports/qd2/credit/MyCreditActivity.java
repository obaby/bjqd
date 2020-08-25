package cn.xports.qd2.credit;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
import cn.xports.base.BaseBussActivity;
import cn.xports.baselib.bean.DataMap;
import cn.xports.baselib.http.RxUtil;
import cn.xports.baselib.mvp.IPresenter;
import cn.xports.qd2.R;
import cn.xports.qd2.adapter.CardTransformer;
import cn.xports.qd2.adapter.CreditDetailAdapter;
import cn.xports.qd2.adapter.CreditPagerAdapter;
import cn.xports.qd2.entity.K;
import cn.xports.qd2.http.ApiService2;
import cn.xports.qd2.util.ApiUtil;
import cn.xports.qd2.util.ViewExt;
import cn.xports.widget.EmptyRecyclerView;
import com.blankj.utilcode.util.SizeUtils;
import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000C\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004*\u0001\f\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u000f\u001a\u00020\u0010H\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0014J\u0010\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0006H\u0002J\b\u0010\u0015\u001a\u00020\u0016H\u0014J\b\u0010\u0017\u001a\u00020\u0010H\u0002J\b\u0010\u0018\u001a\u00020\u0010H\u0016J\b\u0010\u0019\u001a\u00020\u0010H\u0014R\u001e\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0004\n\u0002\u0010\rR\u000e\u0010\u000e\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcn/xports/qd2/credit/MyCreditActivity;", "Lcn/xports/base/BaseBussActivity;", "Lcn/xports/baselib/mvp/IPresenter;", "()V", "cardList", "Ljava/util/ArrayList;", "Lcn/xports/baselib/bean/DataMap;", "Lkotlin/collections/ArrayList;", "creditDetailAdapter", "Lcn/xports/qd2/adapter/CreditDetailAdapter;", "creditDetails", "listener", "cn/xports/qd2/credit/MyCreditActivity$listener$1", "Lcn/xports/qd2/credit/MyCreditActivity$listener$1;", "queryMap", "getCardList", "", "getChildTitle", "", "getCreditDetails", "card", "getLayoutId", "", "initCreditPager", "initData", "initView", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: MyCreditActivity.kt */
public final class MyCreditActivity extends BaseBussActivity<IPresenter> {
    private HashMap _$_findViewCache;
    /* access modifiers changed from: private */
    public final ArrayList<DataMap> cardList = new ArrayList<>();
    /* access modifiers changed from: private */
    public final CreditDetailAdapter creditDetailAdapter = new CreditDetailAdapter(this.creditDetails);
    /* access modifiers changed from: private */
    public final ArrayList<DataMap> creditDetails = new ArrayList<>();
    /* access modifiers changed from: private */
    public final MyCreditActivity$listener$1 listener = new MyCreditActivity$listener$1(this);
    /* access modifiers changed from: private */
    public final DataMap queryMap = new DataMap();

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
        return "我的积分";
    }

    public void initData() {
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.activity_my_credit;
    }

    /* access modifiers changed from: protected */
    public void initView() {
        initCreditPager();
        EmptyRecyclerView emptyRecyclerView = (EmptyRecyclerView) _$_findCachedViewById(R.id.rvCreditDetail);
        Intrinsics.checkExpressionValueIsNotNull(emptyRecyclerView, "rvCreditDetail");
        emptyRecyclerView.setAdapter(this.creditDetailAdapter);
        EmptyRecyclerView emptyRecyclerView2 = (EmptyRecyclerView) _$_findCachedViewById(R.id.rvCreditDetail);
        Intrinsics.checkExpressionValueIsNotNull(emptyRecyclerView2, "rvCreditDetail");
        emptyRecyclerView2.setLayoutManager(new LinearLayoutManager(this));
        EmptyRecyclerView emptyRecyclerView3 = (EmptyRecyclerView) _$_findCachedViewById(R.id.rvCreditDetail);
        Intrinsics.checkExpressionValueIsNotNull(emptyRecyclerView3, "rvCreditDetail");
        ViewExt.setEmptyView$default(emptyRecyclerView3, "暂无积分记录", (String) null, (Function0) null, 6, (Object) null);
        getCardList();
        ((LinearLayout) _$_findCachedViewById(R.id.llCreditHistory)).setOnClickListener(new MyCreditActivity$initView$1(this));
        ((LinearLayout) _$_findCachedViewById(R.id.llCreditStore)).setOnClickListener(new MyCreditActivity$initView$2(this));
    }

    private final void initCreditPager() {
        CreditPagerAdapter creditPagerAdapter = new CreditPagerAdapter(this.cardList);
        creditPagerAdapter.setOnItemClickListener(new MyCreditActivity$initCreditPager$1(this));
        ViewPager viewPager = (ViewPager) _$_findCachedViewById(R.id.viewPager);
        Intrinsics.checkExpressionValueIsNotNull(viewPager, "viewPager");
        viewPager.setPageMargin(SizeUtils.dp2px(20.0f));
        ViewPager viewPager2 = (ViewPager) _$_findCachedViewById(R.id.viewPager);
        Intrinsics.checkExpressionValueIsNotNull(viewPager2, "viewPager");
        viewPager2.setOffscreenPageLimit(3);
        ((ViewPager) _$_findCachedViewById(R.id.viewPager)).setPageTransformer(true, new CardTransformer());
        creditPagerAdapter.attachViewPager((ViewPager) _$_findCachedViewById(R.id.viewPager));
        ViewPager viewPager3 = (ViewPager) _$_findCachedViewById(R.id.viewPager);
        Intrinsics.checkExpressionValueIsNotNull(viewPager3, "viewPager");
        viewPager3.setAdapter(creditPagerAdapter);
    }

    /* access modifiers changed from: private */
    public final void getCreditDetails(DataMap dataMap) {
        this.queryMap.set(K.ecardNo, dataMap.getString(K.ecardNo)).set("pageNo", 1).set("pageSize", 999);
        Observable<ResponseBody> pointDetailList = ApiUtil.getApi2().getPointDetailList(dataMap.getString("venueCustId"), "", 1, 999);
        Intrinsics.checkExpressionValueIsNotNull(pointDetailList, "ApiUtil.getApi2().getPoi…enueCustId\"), \"\", 1, 999)");
        RxUtil.subscribeDataMapIO(pointDetailList, this, new MyCreditActivity$getCreditDetails$1(this), new MyCreditActivity$getCreditDetails$2(this), false);
    }

    private final void getCardList() {
        ApiService2 api2 = ApiUtil.getApi2();
        Intrinsics.checkExpressionValueIsNotNull(api2, "ApiUtil.getApi2()");
        Observable<ResponseBody> pointCardList = api2.getPointCardList();
        Intrinsics.checkExpressionValueIsNotNull(pointCardList, "ApiUtil.getApi2().pointCardList");
        RxUtil.subscribeDataMapIO$default(pointCardList, this, new MyCreditActivity$getCardList$1(this), (Function1) null, false, 12, (Object) null);
    }
}

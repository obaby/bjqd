package cn.xports.qd2.credit;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import cn.xports.base.BaseBussActivity;
import cn.xports.baselib.bean.DataMap;
import cn.xports.baselib.http.RxUtil;
import cn.xports.baselib.mvp.IPresenter;
import cn.xports.qd2.R;
import cn.xports.qd2.adapter.CreditGoodsAdapter;
import cn.xports.qd2.util.ApiUtil;
import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\f\u001a\u00020\rH\u0014J\b\u0010\u000e\u001a\u00020\u000fH\u0002J\b\u0010\u0010\u001a\u00020\u000fH\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0014J\b\u0010\u0013\u001a\u00020\u000fH\u0016J\b\u0010\u0014\u001a\u00020\u000fH\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\tX\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcn/xports/qd2/credit/CreditStoreActivity;", "Lcn/xports/base/BaseBussActivity;", "Lcn/xports/baselib/mvp/IPresenter;", "()V", "couponAdapter", "Lcn/xports/qd2/adapter/CreditGoodsAdapter;", "coupons", "Ljava/util/ArrayList;", "Lcn/xports/baselib/bean/DataMap;", "Lkotlin/collections/ArrayList;", "goods", "goodsAdapter", "getChildTitle", "", "getCouponsList", "", "getGoodsList", "getLayoutId", "", "initData", "initView", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: CreditStoreActivity.kt */
public final class CreditStoreActivity extends BaseBussActivity<IPresenter> {
    private HashMap _$_findViewCache;
    /* access modifiers changed from: private */
    public final CreditGoodsAdapter couponAdapter = new CreditGoodsAdapter(this.coupons);
    /* access modifiers changed from: private */
    public final ArrayList<DataMap> coupons = new ArrayList<>();
    /* access modifiers changed from: private */
    public final ArrayList<DataMap> goods = new ArrayList<>();
    /* access modifiers changed from: private */
    public final CreditGoodsAdapter goodsAdapter = new CreditGoodsAdapter(this.goods);

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
        return "积分商城";
    }

    public void initData() {
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.activity_credit_store;
    }

    /* access modifiers changed from: protected */
    public void initView() {
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.rvCouponList);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "rvCouponList");
        Context context = this;
        recyclerView.setLayoutManager(new LinearLayoutManager(context, 0, false));
        RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(R.id.rvGiftList);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView2, "rvGiftList");
        recyclerView2.setLayoutManager(new LinearLayoutManager(context, 0, false));
        RecyclerView recyclerView3 = (RecyclerView) _$_findCachedViewById(R.id.rvCouponList);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView3, "rvCouponList");
        recyclerView3.setAdapter(this.couponAdapter);
        RecyclerView recyclerView4 = (RecyclerView) _$_findCachedViewById(R.id.rvGiftList);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView4, "rvGiftList");
        recyclerView4.setAdapter(this.goodsAdapter);
        ((LinearLayout) _$_findCachedViewById(R.id.llGiftShowMore)).setOnClickListener(new CreditStoreActivity$initView$1(this));
        ((LinearLayout) _$_findCachedViewById(R.id.llCouponShowMore)).setOnClickListener(new CreditStoreActivity$initView$2(this));
        this.couponAdapter.setOnItemClickListener(new CreditStoreActivity$initView$3(this));
        this.goodsAdapter.setOnItemClickListener(new CreditStoreActivity$initView$4(this));
        getCouponsList();
        getGoodsList();
    }

    private final void getCouponsList() {
        Observable<ResponseBody> exchangeCoupons = ApiUtil.getApi2().getExchangeCoupons("1", "1", 1, 999);
        Intrinsics.checkExpressionValueIsNotNull(exchangeCoupons, "ApiUtil.getApi2().getExc…Coupons(\"1\", \"1\", 1, 999)");
        RxUtil.subscribeDataMapIO$default(exchangeCoupons, this, new CreditStoreActivity$getCouponsList$1(this), (Function1) null, false, 12, (Object) null);
    }

    private final void getGoodsList() {
        Observable<ResponseBody> exchangeCoupons = ApiUtil.getApi2().getExchangeCoupons("2,3,4", "1", 1, 999);
        Intrinsics.checkExpressionValueIsNotNull(exchangeCoupons, "ApiUtil.getApi2().getExc…ons(\"2,3,4\", \"1\", 1, 999)");
        RxUtil.subscribeDataMapIO$default(exchangeCoupons, this, new CreditStoreActivity$getGoodsList$1(this), (Function1) null, false, 12, (Object) null);
    }
}

package cn.xports.qd2;

import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import cn.xports.base.BaseBottomDialogActivity;
import cn.xports.baselib.bean.DataMap;
import cn.xports.baselib.http.RetrofitUtil;
import cn.xports.baselib.http.RxUtil;
import cn.xports.baselib.mvp.IPresenter;
import cn.xports.http.SodaService;
import cn.xports.qd2.adapter.CouponPayAdapter;
import cn.xports.qd2.util.ViewExt;
import cn.xports.widget.EmptyRecyclerView;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0013\u001a\u00020\u0014H\u0002J\b\u0010\u0015\u001a\u00020\u0016H\u0014J\b\u0010\u0017\u001a\u00020\u0014H\u0016J\b\u0010\u0018\u001a\u00020\u0014H\u0014J\u0012\u0010\u0019\u001a\u00020\u00142\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\u0012\u0010\u001c\u001a\u00020\u00142\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\u0012\u0010\u001d\u001a\u00020\u00142\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\nX\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\nX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0010\u001a\u0012\u0012\u0004\u0012\u00020\u00110\bj\b\u0012\u0004\u0012\u00020\u0011`\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0011X\u000e¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcn/xports/qd2/SelectCouponActivity;", "Lcn/xports/base/BaseBottomDialogActivity;", "Lcn/xports/baselib/mvp/IPresenter;", "Landroid/support/design/widget/TabLayout$OnTabSelectedListener;", "()V", "adapter", "Lcn/xports/qd2/adapter/CouponPayAdapter;", "canUseCoupons", "Ljava/util/ArrayList;", "Lcn/xports/baselib/bean/DataMap;", "Lkotlin/collections/ArrayList;", "cannotUseCoupons", "clickCoupon", "couponOptions", "emptyView", "Landroid/view/View;", "titles", "", "tradeId", "getCouponPay", "", "getDialogLayout", "", "initData", "initDialogView", "onTabReselected", "tab", "Landroid/support/design/widget/TabLayout$Tab;", "onTabSelected", "onTabUnselected", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: SelectCouponActivity.kt */
public final class SelectCouponActivity extends BaseBottomDialogActivity<IPresenter> implements TabLayout.OnTabSelectedListener {
    private HashMap _$_findViewCache;
    private final CouponPayAdapter adapter = new CouponPayAdapter(this.couponOptions);
    /* access modifiers changed from: private */
    public final ArrayList<DataMap> canUseCoupons = new ArrayList<>();
    /* access modifiers changed from: private */
    public final ArrayList<DataMap> cannotUseCoupons = new ArrayList<>();
    /* access modifiers changed from: private */
    public DataMap clickCoupon = new DataMap();
    private final ArrayList<DataMap> couponOptions = new ArrayList<>();
    private View emptyView;
    /* access modifiers changed from: private */
    public final ArrayList<String> titles = new ArrayList<>();
    private String tradeId = "";

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

    public void onTabReselected(@Nullable TabLayout.Tab tab) {
    }

    public void onTabUnselected(@Nullable TabLayout.Tab tab) {
    }

    public void onTabSelected(@Nullable TabLayout.Tab tab) {
        TextView textView;
        TextView textView2;
        if (tab != null) {
            this.couponOptions.clear();
            if (tab.getPosition() == 0) {
                this.couponOptions.addAll(this.canUseCoupons);
                View view = this.emptyView;
                if (!(view == null || (textView2 = (TextView) view.findViewById(R.id.tvJoinTip)) == null)) {
                    textView2.setText("暂无可用优惠券");
                }
            } else {
                this.couponOptions.addAll(this.cannotUseCoupons);
                View view2 = this.emptyView;
                if (!(view2 == null || (textView = (TextView) view2.findViewById(R.id.tvJoinTip)) == null)) {
                    textView.setText("暂无不可用优惠券");
                }
            }
            this.adapter.notifyDataSetChanged();
        }
    }

    /* access modifiers changed from: protected */
    public int getDialogLayout() {
        return R.layout.dialog_select_coupon;
    }

    /* access modifiers changed from: protected */
    public void initDialogView() {
        ((ImageView) _$_findCachedViewById(R.id.ivClose)).setOnClickListener(new SelectCouponActivity$initDialogView$1(this));
        EmptyRecyclerView emptyRecyclerView = (EmptyRecyclerView) _$_findCachedViewById(R.id.rvCoupons);
        Intrinsics.checkExpressionValueIsNotNull(emptyRecyclerView, "rvCoupons");
        this.emptyView = ViewExt.getEmptyView(emptyRecyclerView, "暂无可用优惠券");
        EmptyRecyclerView emptyRecyclerView2 = (EmptyRecyclerView) _$_findCachedViewById(R.id.rvCoupons);
        Intrinsics.checkExpressionValueIsNotNull(emptyRecyclerView2, "rvCoupons");
        emptyRecyclerView2.setLayoutManager(new LinearLayoutManager(this));
        EmptyRecyclerView emptyRecyclerView3 = (EmptyRecyclerView) _$_findCachedViewById(R.id.rvCoupons);
        Intrinsics.checkExpressionValueIsNotNull(emptyRecyclerView3, "rvCoupons");
        emptyRecyclerView3.setAdapter(this.adapter);
        ((EmptyRecyclerView) _$_findCachedViewById(R.id.rvCoupons)).setEmptyView(this.emptyView);
        this.adapter.setOnItemClickListener(new SelectCouponActivity$initDialogView$2(this));
        getCouponPay();
    }

    public void initData() {
        this.tradeId = getStringExtra("tradeId");
    }

    private final void getCouponPay() {
        ((SodaService) RetrofitUtil.getInstance().create(SodaService.class)).getUseCouponList(this.tradeId).compose(RxUtil.applyDataMapIO()).subscribe(new SelectCouponActivity$getCouponPay$1(this, this));
    }
}

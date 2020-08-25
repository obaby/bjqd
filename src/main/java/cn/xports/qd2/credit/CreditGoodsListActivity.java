package cn.xports.qd2.credit;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import cn.xports.base.BaseBussActivity;
import cn.xports.baselib.bean.DataMap;
import cn.xports.baselib.mvp.IPresenter;
import cn.xports.baselib.util.DataMapUtils;
import cn.xports.qd2.R;
import cn.xports.qd2.adapter.CreditGoods2Adapter;
import com.alipay.sdk.packet.d;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\n\u001a\u00020\u000bH\u0014J\b\u0010\f\u001a\u00020\tH\u0014J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u000eH\u0014R\u001e\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcn/xports/qd2/credit/CreditGoodsListActivity;", "Lcn/xports/base/BaseBussActivity;", "Lcn/xports/baselib/mvp/IPresenter;", "()V", "data", "Ljava/util/ArrayList;", "Lcn/xports/baselib/bean/DataMap;", "Lkotlin/collections/ArrayList;", "type", "", "getChildTitle", "", "getLayoutId", "initData", "", "initView", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: CreditGoodsListActivity.kt */
public final class CreditGoodsListActivity extends BaseBussActivity<IPresenter> {
    private HashMap _$_findViewCache;
    private ArrayList<DataMap> data = new ArrayList<>();
    private int type = 1;

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

    public void initData() {
        this.type = getIntent().getIntExtra(d.p, 1);
        List<DataMap> fromJsonArray = DataMapUtils.fromJsonArray(getIntent().getStringExtra(d.k));
        if (fromJsonArray != null) {
            this.data = (ArrayList) fromJsonArray;
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.ArrayList<cn.xports.baselib.bean.DataMap> /* = java.util.ArrayList<cn.xports.baselib.bean.DataMap> */");
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.activity_credit_goods_list;
    }

    /* access modifiers changed from: protected */
    public void initView() {
        if (this.type == 1) {
            TextView textView = this.mTvTitle;
            Intrinsics.checkExpressionValueIsNotNull(textView, "mTvTitle");
            textView.setText("优惠券专区");
        } else {
            TextView textView2 = this.mTvTitle;
            Intrinsics.checkExpressionValueIsNotNull(textView2, "mTvTitle");
            textView2.setText("礼品专区");
        }
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.rvGoodsList);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "rvGoodsList");
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(R.id.rvGoodsList);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView2, "rvGoodsList");
        recyclerView2.setAdapter(new CreditGoods2Adapter(this.data).setOnItemClickListener(new CreditGoodsListActivity$initView$1(this)));
    }
}

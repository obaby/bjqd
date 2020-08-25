package cn.xports.qd2;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import cn.xports.base.BaseBussActivity;
import cn.xports.baselib.http.RxUtil;
import cn.xports.baselib.mvp.IPresenter;
import cn.xports.entity.CardInfo;
import cn.xports.qd2.adapter.CardPackageAdapter;
import cn.xports.qd2.entity.K;
import cn.xports.qd2.http.ApiService2;
import cn.xports.qd2.util.ApiUtil;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\f\u001a\u00020\rH\u0002J\b\u0010\u000e\u001a\u00020\u000bH\u0014J\b\u0010\u000f\u001a\u00020\u0010H\u0014J\b\u0010\u0011\u001a\u00020\rH\u0016J\b\u0010\u0012\u001a\u00020\rH\u0014J\b\u0010\u0013\u001a\u00020\rH\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcn/xports/qd2/CardPackageActivity;", "Lcn/xports/base/BaseBussActivity;", "Lcn/xports/baselib/mvp/IPresenter;", "()V", "cardAdapter", "Lcn/xports/qd2/adapter/CardPackageAdapter;", "cardList", "Ljava/util/ArrayList;", "Lcn/xports/entity/CardInfo;", "Lkotlin/collections/ArrayList;", "venueId", "", "getCardList", "", "getChildTitle", "getLayoutId", "", "initData", "initView", "onResume", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: CardPackageActivity.kt */
public final class CardPackageActivity extends BaseBussActivity<IPresenter> {
    private HashMap _$_findViewCache;
    /* access modifiers changed from: private */
    public final CardPackageAdapter cardAdapter = new CardPackageAdapter(this.cardList);
    /* access modifiers changed from: private */
    public final ArrayList<CardInfo> cardList = new ArrayList<>();
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
        return "我的卡包";
    }

    public void initData() {
        this.venueId = getStringExtra(K.venueId);
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.activity_card_package;
    }

    /* access modifiers changed from: protected */
    public void initView() {
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.rvCardList);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "rvCardList");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(R.id.rvCardList);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView2, "rvCardList");
        recyclerView2.setAdapter(this.cardAdapter);
        ((RelativeLayout) _$_findCachedViewById(R.id.rlBindCard)).setOnClickListener(new CardPackageActivity$initView$1(this));
        if (this.venueId.length() == 0) {
            RelativeLayout relativeLayout = (RelativeLayout) _$_findCachedViewById(R.id.rlBuyCard);
            Intrinsics.checkExpressionValueIsNotNull(relativeLayout, "rlBuyCard");
            relativeLayout.setVisibility(8);
        }
        ((RelativeLayout) _$_findCachedViewById(R.id.rlBuyCard)).setOnClickListener(new CardPackageActivity$initView$2(this));
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        getCardList();
    }

    private final void getCardList() {
        this.cardList.clear();
        ApiService2 api2 = ApiUtil.getApi2();
        Intrinsics.checkExpressionValueIsNotNull(api2, "ApiUtil.getApi2()");
        api2.getCardList().compose(RxUtil.applyErrorsWithIO()).subscribe(new CardPackageActivity$getCardList$1(this, this));
    }
}

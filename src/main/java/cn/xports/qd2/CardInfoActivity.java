package cn.xports.qd2;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import cn.xports.base.BaseBussActivity;
import cn.xports.baselib.http.RxUtil;
import cn.xports.baselib.mvp.IPresenter;
import cn.xports.baselib.util.MoneyUtil;
import cn.xports.entity.DepositInfo;
import cn.xports.qd2.adapter.CardDepositAdapter;
import cn.xports.qd2.entity.K;
import cn.xports.qd2.util.ApiUtil;
import cn.xports.widget.EmptyRecyclerView;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\b\u0010\f\u001a\u00020\u000bH\u0014J\b\u0010\r\u001a\u00020\u000eH\u0014J\b\u0010\u000f\u001a\u00020\tH\u0016J\b\u0010\u0010\u001a\u00020\tH\u0014J \u0010\u0011\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u000eH\u0002J\u0010\u0010\u0015\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002R\u001e\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcn/xports/qd2/CardInfoActivity;", "Lcn/xports/base/BaseBussActivity;", "Lcn/xports/baselib/mvp/IPresenter;", "()V", "depositInfos", "Ljava/util/ArrayList;", "Lcn/xports/entity/DepositInfo;", "Lkotlin/collections/ArrayList;", "getCardInfo", "", "ecardNo", "", "getChildTitle", "getLayoutId", "", "initData", "initView", "setCardView", "centerName", "cardNo", "balance", "ubind", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: CardInfoActivity.kt */
public final class CardInfoActivity extends BaseBussActivity<IPresenter> {
    private HashMap _$_findViewCache;
    /* access modifiers changed from: private */
    public final ArrayList<DepositInfo> depositInfos = new ArrayList<>();

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
        return "卡信息";
    }

    public void initData() {
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.activity_card_info;
    }

    /* access modifiers changed from: protected */
    public void initView() {
        EmptyRecyclerView emptyRecyclerView = (EmptyRecyclerView) _$_findCachedViewById(R.id.rvCardInfoList);
        Intrinsics.checkExpressionValueIsNotNull(emptyRecyclerView, "rvCardInfoList");
        emptyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ((EmptyRecyclerView) _$_findCachedViewById(R.id.rvCardInfoList)).setEmptyView(getEmptyView("暂无信息"));
        EmptyRecyclerView emptyRecyclerView2 = (EmptyRecyclerView) _$_findCachedViewById(R.id.rvCardInfoList);
        Intrinsics.checkExpressionValueIsNotNull(emptyRecyclerView2, "rvCardInfoList");
        emptyRecyclerView2.setAdapter(new CardDepositAdapter(this.depositInfos));
        String stringExtra = getIntent().getStringExtra(K.ecardNo);
        Intrinsics.checkExpressionValueIsNotNull(stringExtra, "intent.getStringExtra(\"ecardNo\")");
        getCardInfo(stringExtra);
        ((Button) _$_findCachedViewById(R.id.btnUnbind)).setOnClickListener(new CardInfoActivity$initView$1(this));
        ((ImageView) _$_findCachedViewById(R.id.ivQR)).setOnClickListener(new CardInfoActivity$initView$2(this));
        ((TextView) _$_findCachedViewById(R.id.tvChongz)).setOnClickListener(new CardInfoActivity$initView$3(this));
    }

    private final void getCardInfo(String str) {
        ApiUtil.getApi2().getCardDetail(str).compose(RxUtil.applyErrorsWithIO()).subscribe(new CardInfoActivity$getCardInfo$1(this, this));
    }

    /* access modifiers changed from: private */
    public final void ubind(String str) {
        ApiUtil.getApi2().unbindCard(str).compose(RxUtil.applyErrorsWithIO()).subscribe(new CardInfoActivity$ubind$1(this, this));
    }

    /* access modifiers changed from: private */
    public final void setCardView(String str, String str2, int i) {
        TextView textView = (TextView) _$_findCachedViewById(R.id.tvCardName);
        Intrinsics.checkExpressionValueIsNotNull(textView, "tvCardName");
        textView.setText(str);
        TextView textView2 = (TextView) _$_findCachedViewById(R.id.tvCardNo);
        Intrinsics.checkExpressionValueIsNotNull(textView2, "tvCardNo");
        textView2.setText("NO." + str2);
        TextView textView3 = (TextView) _$_findCachedViewById(R.id.tvLeftMoney);
        Intrinsics.checkExpressionValueIsNotNull(textView3, "tvLeftMoney");
        textView3.setText(MoneyUtil.cent2Yuan(i));
    }
}

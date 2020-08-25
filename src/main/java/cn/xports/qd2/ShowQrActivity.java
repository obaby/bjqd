package cn.xports.qd2;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import cn.xports.base.BaseBussActivity;
import cn.xports.baselib.http.RxDisposableManager;
import cn.xports.baselib.http.RxUtil;
import cn.xports.baselib.mvp.IPresenter;
import cn.xports.entity.CardInfo;
import cn.xports.qd2.adapter.CardQrSwitchAdapter;
import cn.xports.qd2.util.ApiUtil;
import com.blankj.utilcode.util.GsonUtils;
import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u000b\u001a\u00020\fH\u0002J\b\u0010\r\u001a\u00020\u000eH\u0014J\b\u0010\u000f\u001a\u00020\u0010H\u0014J\b\u0010\u0011\u001a\u00020\fH\u0002J\b\u0010\u0012\u001a\u00020\fH\u0016J\b\u0010\u0013\u001a\u00020\fH\u0014J\b\u0010\u0014\u001a\u00020\fH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\tX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcn/xports/qd2/ShowQrActivity;", "Lcn/xports/base/BaseBussActivity;", "Lcn/xports/baselib/mvp/IPresenter;", "()V", "adapter", "Lcn/xports/qd2/adapter/CardQrSwitchAdapter;", "cardInfoList", "Ljava/util/ArrayList;", "Lcn/xports/entity/CardInfo;", "Lkotlin/collections/ArrayList;", "mCard", "countDown", "", "getChildTitle", "", "getLayoutId", "", "getQrCode", "initData", "initView", "setCard", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: ShowQrActivity.kt */
public final class ShowQrActivity extends BaseBussActivity<IPresenter> {
    private HashMap _$_findViewCache;
    private final CardQrSwitchAdapter adapter = new CardQrSwitchAdapter(this.cardInfoList);
    private final ArrayList<CardInfo> cardInfoList = new ArrayList<>();
    /* access modifiers changed from: private */
    public CardInfo mCard;

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
        return "二维码";
    }

    public void initData() {
        String stringExtra = getIntent().getStringExtra("cardInfos");
        if (stringExtra != null) {
            this.cardInfoList.addAll((Collection) GsonUtils.fromJson(stringExtra, new ShowQrActivity$initData$1().getType()));
        }
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.activity_show_qr;
    }

    /* access modifiers changed from: protected */
    public void initView() {
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.rvCardSwitchList);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "rvCardSwitchList");
        recyclerView.setLayoutManager(new LinearLayoutManager(this, 0, false));
        RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(R.id.rvCardSwitchList);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView2, "rvCardSwitchList");
        recyclerView2.setAdapter(this.adapter);
        this.adapter.setOnItemClick(new ShowQrActivity$initView$1(this));
        Iterator<CardInfo> it = this.cardInfoList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            CardInfo next = it.next();
            Intrinsics.checkExpressionValueIsNotNull(next, "card");
            if (next.isSelected()) {
                this.mCard = next;
                break;
            }
        }
        setCard();
        countDown();
    }

    /* access modifiers changed from: private */
    public final void countDown() {
        RxDisposableManager.getInstance().clear(getTAG());
        RxDisposableManager.getInstance().add(getTAG(), Observable.interval(0, 2, TimeUnit.MINUTES).compose(RxUtil.applyIO()).subscribe(new ShowQrActivity$countDown$1(this)));
    }

    /* access modifiers changed from: private */
    public final void getQrCode() {
        CardInfo cardInfo = this.mCard;
        if (cardInfo != null) {
            ApiUtil.getApi2().useQrCode(cardInfo.getEcardNo()).compose(RxUtil.applyIO()).subscribe(new ShowQrActivity$getQrCode$$inlined$apply$lambda$1(this, this));
        }
    }

    /* access modifiers changed from: private */
    public final void setCard() {
        CardInfo cardInfo = this.mCard;
        if (cardInfo != null) {
            TextView textView = (TextView) _$_findCachedViewById(R.id.tvCenterName);
            Intrinsics.checkExpressionValueIsNotNull(textView, "tvCenterName");
            textView.setText(cardInfo.getCenterName());
            TextView textView2 = (TextView) _$_findCachedViewById(R.id.tvCardNo);
            Intrinsics.checkExpressionValueIsNotNull(textView2, "tvCardNo");
            textView2.setText("NO." + cardInfo.getEcardNo());
        }
    }
}

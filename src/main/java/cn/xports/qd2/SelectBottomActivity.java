package cn.xports.qd2;

import android.os.Bundle;
import android.view.View;
import cn.xports.base.Router;
import cn.xports.baselib.http.RxUtil;
import cn.xports.baselib.mvp.BaseActivity;
import cn.xports.baselib.mvp.IPresenter;
import cn.xports.baselib.util.LoadingDialog;
import cn.xports.entity.BalanceInfo;
import cn.xports.entity.CardInfo;
import cn.xports.export.EventHandler;
import cn.xports.qd2.adapter.CardSelectBinder;
import cn.xports.qd2.adapter.CardSelectDetailBinder;
import cn.xports.qd2.util.ApiUtil;
import com.blankj.utilcode.util.GsonUtils;
import com.stub.StubApp;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import me.drakeet.multitype.ItemViewBinder;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u001c2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001cB\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0012H\u0002J\u0010\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0005H\u0002J\b\u0010\u0016\u001a\u00020\u0012H\u0016J\b\u0010\u0017\u001a\u00020\u0012H\u0016J\u0012\u0010\u0018\u001a\u00020\u00122\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0014J\b\u0010\u001b\u001a\u00020\u0012H\u0016R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0007j\b\u0012\u0004\u0012\u00020\u0005`\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX.¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcn/xports/qd2/SelectBottomActivity;", "Lcn/xports/baselib/mvp/BaseActivity;", "Lcn/xports/baselib/mvp/IPresenter;", "()V", "card", "Lcn/xports/entity/CardInfo;", "cardList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "loadingDialog", "Lcn/xports/baselib/util/LoadingDialog;", "mItems", "Lme/drakeet/multitype/Items;", "multiTypeAdapter", "Lme/drakeet/multitype/MultiTypeAdapter;", "sourceName", "", "finish", "", "getAllDetails", "getCardDetail", "cardInfo", "hideLoading", "initData", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "showLoading", "Companion", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: SelectBottomActivity.kt */
public final class SelectBottomActivity extends BaseActivity<IPresenter> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private HashMap _$_findViewCache;
    /* access modifiers changed from: private */
    public CardInfo card;
    private final ArrayList<CardInfo> cardList = new ArrayList<>();
    private LoadingDialog loadingDialog;
    /* access modifiers changed from: private */
    public final Items mItems = new Items();
    /* access modifiers changed from: private */
    public final MultiTypeAdapter multiTypeAdapter = new MultiTypeAdapter();
    /* access modifiers changed from: private */
    public String sourceName = "";

    static {
        StubApp.interface11(3469);
    }

    @JvmStatic
    public static final void start(@NotNull String str, @NotNull List<? extends CardInfo> list) {
        Companion.start(str, list);
    }

    @JvmStatic
    public static final void startWithMap(@NotNull Map<String, String> map) {
        Companion.startWithMap(map);
    }

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
    public native void onCreate(@Nullable Bundle bundle);

    public void showLoading() {
        EventHandler instance = EventHandler.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(instance, "EventHandler.getInstance()");
        if (instance.getLoadingListener() != null) {
            EventHandler instance2 = EventHandler.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(instance2, "EventHandler.getInstance()");
            instance2.getLoadingListener().showLoading();
            return;
        }
        LoadingDialog loadingDialog2 = this.loadingDialog;
        if (loadingDialog2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("loadingDialog");
        }
        if (loadingDialog2 != null) {
            LoadingDialog loadingDialog3 = this.loadingDialog;
            if (loadingDialog3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("loadingDialog");
            }
            loadingDialog3.show();
        }
    }

    public void hideLoading() {
        EventHandler instance = EventHandler.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(instance, "EventHandler.getInstance()");
        if (instance.getLoadingListener() != null) {
            EventHandler instance2 = EventHandler.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(instance2, "EventHandler.getInstance()");
            instance2.getLoadingListener().hideLoading();
            return;
        }
        LoadingDialog loadingDialog2 = this.loadingDialog;
        if (loadingDialog2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("loadingDialog");
        }
        if (loadingDialog2 != null) {
            LoadingDialog loadingDialog3 = this.loadingDialog;
            if (loadingDialog3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("loadingDialog");
            }
            loadingDialog3.dismiss();
        }
    }

    public void initData() {
        this.loadingDialog = new LoadingDialog(this);
        ItemViewBinder cardSelectBinder = new CardSelectBinder(this.cardList);
        this.multiTypeAdapter.setItems(this.mItems);
        this.multiTypeAdapter.register(CardInfo.class, cardSelectBinder);
        this.multiTypeAdapter.register(BalanceInfo.class, new CardSelectDetailBinder());
        this.sourceName = getStringExtra("from");
        List list = (List) GsonUtils.fromJson(getStringExtra("cardList"), new SelectBottomActivity$initData$info$1().getType());
        if (list != null) {
            this.cardList.addAll(list);
            this.mItems.addAll(this.cardList);
        }
        cardSelectBinder.setClick(new SelectBottomActivity$initData$1(this));
    }

    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }

    private final void getAllDetails() {
        ArrayList arrayList = new ArrayList();
        HashMap hashMap = new HashMap();
        for (CardInfo cardInfo : this.cardList) {
            hashMap.put(cardInfo.getEcardNo(), cardInfo);
            arrayList.add(ApiUtil.getApi2().getCardDetail(cardInfo.getEcardNo()));
        }
        Observable.merge(arrayList).compose(RxUtil.applyErrors()).toList().map(new SelectBottomActivity$getAllDetails$2(new HashMap())).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SelectBottomActivity$getAllDetails$3(this, hashMap, this));
    }

    /* access modifiers changed from: private */
    public final void getCardDetail(CardInfo cardInfo) {
        ApiUtil.getApi2().getCardDetail(cardInfo.getEcardNo()).compose(RxUtil.applyErrors()).map(SelectBottomActivity$getCardDetail$1.INSTANCE).compose(RxUtil.applyIO()).subscribe(new SelectBottomActivity$getCardDetail$2(this, cardInfo, this));
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0007J\u001c\u0010\n\u001a\u00020\u00042\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\fH\u0007¨\u0006\r"}, d2 = {"Lcn/xports/qd2/SelectBottomActivity$Companion;", "", "()V", "start", "", "tag", "", "cardList", "", "Lcn/xports/entity/CardInfo;", "startWithMap", "map", "", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
    /* compiled from: SelectBottomActivity.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final void startWithMap(@NotNull Map<String, String> map) {
            Intrinsics.checkParameterIsNotNull(map, "map");
            Router.startWithNoAnim(Router.getIntent(Router.SELECT_BOTTOM, map));
        }

        @JvmStatic
        public final void start(@NotNull String str, @NotNull List<? extends CardInfo> list) {
            Intrinsics.checkParameterIsNotNull(str, "tag");
            Intrinsics.checkParameterIsNotNull(list, "cardList");
            Map hashMap = new HashMap();
            hashMap.put("from", str);
            String json = GsonUtils.toJson(list);
            Intrinsics.checkExpressionValueIsNotNull(json, "GsonUtils.toJson(cardList)");
            hashMap.put("cardList", json);
            startWithMap(hashMap);
        }
    }
}

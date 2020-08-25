package cn.xports.qd2;

import android.os.Bundle;
import android.view.View;
import cn.xports.base.Constant;
import cn.xports.base.Router;
import cn.xports.baselib.http.ResponseThrowable;
import cn.xports.baselib.http.RetrofitUtil;
import cn.xports.baselib.http.RxUtil;
import cn.xports.baselib.mvp.BaseActivity;
import cn.xports.baselib.mvp.IPresenter;
import cn.xports.baselib.util.LoadingDialog;
import cn.xports.entity.BalanceInfo;
import cn.xports.entity.CardInfo;
import cn.xports.entity.DepositInfo;
import cn.xports.export.EventHandler;
import cn.xports.http.SodaService;
import cn.xports.parser.OrderPayParser;
import cn.xports.qd2.adapter.CardSelectBinder;
import cn.xports.qd2.adapter.CardSelectDetailBinder;
import com.blankj.utilcode.util.ActivityUtils;
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

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 %2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001%B\u0005¢\u0006\u0002\u0010\u0003J(\u0010\u0016\u001a\u0012\u0012\u0004\u0012\u00020\n0\u0007j\b\u0012\u0004\u0012\u00020\n`\b2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0014H\u0002J\b\u0010\u001a\u001a\u00020\u001bH\u0016J\b\u0010\u001c\u001a\u00020\u001bH\u0002J\u0010\u0010\u001d\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u0005H\u0002J\b\u0010\u001f\u001a\u00020\u001bH\u0016J\b\u0010 \u001a\u00020\u001bH\u0016J\u0012\u0010!\u001a\u00020\u001b2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0014J\b\u0010$\u001a\u00020\u001bH\u0016R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0007j\b\u0012\u0004\u0012\u00020\u0005`\bX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX.¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0014X\u000e¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lcn/xports/qd2/SelectCardPayActivity;", "Lcn/xports/baselib/mvp/BaseActivity;", "Lcn/xports/baselib/mvp/IPresenter;", "()V", "card", "Lcn/xports/entity/CardInfo;", "cardList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "curBalanceInfo", "Lcn/xports/entity/BalanceInfo;", "isGetData", "", "loadingDialog", "Lcn/xports/baselib/util/LoadingDialog;", "mItems", "Lme/drakeet/multitype/Items;", "multiTypeAdapter", "Lme/drakeet/multitype/MultiTypeAdapter;", "sourceName", "", "tradeId", "createBalances", "orderPayParser", "Lcn/xports/parser/OrderPayParser;", "ecardNo", "finish", "", "getAllDetails", "getCardDetail", "cardInfo", "hideLoading", "initData", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "showLoading", "Companion", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: SelectCardPayActivity.kt */
public final class SelectCardPayActivity extends BaseActivity<IPresenter> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private HashMap _$_findViewCache;
    private CardInfo card;
    private final ArrayList<CardInfo> cardList = new ArrayList<>();
    /* access modifiers changed from: private */
    public BalanceInfo curBalanceInfo;
    /* access modifiers changed from: private */
    public boolean isGetData;
    private LoadingDialog loadingDialog;
    /* access modifiers changed from: private */
    public final Items mItems = new Items();
    /* access modifiers changed from: private */
    public final MultiTypeAdapter multiTypeAdapter = new MultiTypeAdapter();
    /* access modifiers changed from: private */
    public String sourceName = "";
    private String tradeId = "";

    static {
        StubApp.interface11(3480);
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
        ItemViewBinder cardSelectBinder = new CardSelectBinder(this.cardList, false);
        ItemViewBinder cardSelectDetailBinder = new CardSelectDetailBinder();
        this.multiTypeAdapter.setItems(this.mItems);
        this.multiTypeAdapter.register(CardInfo.class, cardSelectBinder);
        this.multiTypeAdapter.register(BalanceInfo.class, cardSelectDetailBinder);
        this.sourceName = getStringExtra("from");
        this.tradeId = getStringExtra("tradeId");
        List list = (List) GsonUtils.fromJson(getStringExtra("cardList"), new SelectCardPayActivity$initData$info$1().getType());
        if (list != null) {
            this.cardList.addAll(list);
            this.mItems.addAll(this.cardList);
        }
        cardSelectBinder.setClick(new SelectCardPayActivity$initData$1(this));
        cardSelectDetailBinder.setOnSelectItem(new SelectCardPayActivity$initData$2(this));
    }

    private final void getAllDetails() {
        ArrayList arrayList = new ArrayList();
        HashMap hashMap = new HashMap();
        for (CardInfo cardInfo : this.cardList) {
            hashMap.put(cardInfo.getEcardNo(), cardInfo);
            arrayList.add(((SodaService) RetrofitUtil.getInstance().create(SodaService.class)).getPayOrderInfo(this.tradeId, cardInfo.getEcardNo()));
        }
        Observable.merge(arrayList).compose(RxUtil.applyErrors()).toList().map(new SelectCardPayActivity$getAllDetails$2(this, new HashMap())).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SelectCardPayActivity$getAllDetails$3(this, hashMap, this));
    }

    /* access modifiers changed from: private */
    public final void getCardDetail(CardInfo cardInfo) {
        this.isGetData = true;
        ((SodaService) RetrofitUtil.getInstance().create(SodaService.class)).getPayOrderInfo(this.tradeId, cardInfo.getEcardNo()).compose(RxUtil.applyErrors()).map(new SelectCardPayActivity$getCardDetail$1(this, cardInfo)).compose(RxUtil.applyIO()).subscribe(new SelectCardPayActivity$getCardDetail$2(this, cardInfo, this));
    }

    /* access modifiers changed from: private */
    public final ArrayList<BalanceInfo> createBalances(OrderPayParser orderPayParser, String str) {
        ArrayList<BalanceInfo> arrayList = new ArrayList<>();
        if (orderPayParser.getBalanceInfo() != null) {
            BalanceInfo name = new BalanceInfo().setName("一卡通账户");
            BalanceInfo balanceInfo = orderPayParser.getBalanceInfo();
            Intrinsics.checkExpressionValueIsNotNull(balanceInfo, "it.balanceInfo");
            BalanceInfo discountedFee = name.setBalance(balanceInfo.getCanPayBalance()).setDiscountedFee(orderPayParser.getDiscountedFee());
            BalanceInfo balanceInfo2 = orderPayParser.getBalanceInfo();
            Intrinsics.checkExpressionValueIsNotNull(balanceInfo2, "it.balanceInfo");
            BalanceInfo orderDiscount = discountedFee.setOrderDiscount(balanceInfo2.getOrderDiscount());
            BalanceInfo balanceInfo3 = orderPayParser.getBalanceInfo();
            Intrinsics.checkExpressionValueIsNotNull(balanceInfo3, "it.balanceInfo");
            BalanceInfo totalPayMoney = orderDiscount.setCanPayBalance(balanceInfo3.getCanPayBalance()).setRealTotalPay(orderPayParser.getRealTotalPay()).setPayMode(Constant.ECARD_PAY).setTotalPayMoney(orderPayParser.getTotalPayMoney());
            BalanceInfo balanceInfo4 = orderPayParser.getBalanceInfo();
            Intrinsics.checkExpressionValueIsNotNull(balanceInfo4, "it.balanceInfo");
            arrayList.add(totalPayMoney.setCardPay(balanceInfo4.getCardPay()).setEcardNo(str));
            List<DepositInfo> depositList = orderPayParser.getDepositList();
            if (depositList != null) {
                for (DepositInfo depositInfo : depositList) {
                    Intrinsics.checkExpressionValueIsNotNull(depositInfo, "deposit");
                    if (Intrinsics.areEqual("1", depositInfo.getValidTag())) {
                        BalanceInfo balanceInfo5 = new BalanceInfo();
                        BalanceInfo balanceInfo6 = orderPayParser.getBalanceInfo();
                        Intrinsics.checkExpressionValueIsNotNull(balanceInfo6, "it.balanceInfo");
                        arrayList.add(balanceInfo5.setOrderDiscount(balanceInfo6.getOrderDiscount()).setName(depositInfo.getProductName()).setId(depositInfo.getProductId()).setDiscountedFee(orderPayParser.getDiscountedFee()).setDiscount(depositInfo.getDiscount()).setRealTotalPay(orderPayParser.getRealTotalPay()).setPayMode(Constant.DEPOSIT_PAY).setTotalPayMoney(orderPayParser.getTotalPayMoney()).setValidTag("1").setBalance(depositInfo.getBalance()).setResType(depositInfo.getResType()).setEcardNo(str));
                    }
                }
            }
            List<DepositInfo> periodDepositList = orderPayParser.getPeriodDepositList();
            if (periodDepositList != null) {
                for (DepositInfo depositInfo2 : periodDepositList) {
                    Intrinsics.checkExpressionValueIsNotNull(depositInfo2, "period");
                    if (Intrinsics.areEqual("1", depositInfo2.getValidTag())) {
                        BalanceInfo balanceInfo7 = new BalanceInfo();
                        BalanceInfo balanceInfo8 = orderPayParser.getBalanceInfo();
                        Intrinsics.checkExpressionValueIsNotNull(balanceInfo8, "it.balanceInfo");
                        arrayList.add(balanceInfo7.setOrderDiscount(balanceInfo8.getOrderDiscount()).setPayMode(Constant.PERIOD_PAY).setName(depositInfo2.getProductName()).setId(depositInfo2.getDepositId()).setDiscountedFee(orderPayParser.getDiscountedFee()).setDiscount(depositInfo2.getDiscount()).setRealTotalPay(orderPayParser.getRealTotalPay()).setTotalPayMoney(orderPayParser.getTotalPayMoney()).setValidTag("1").setBalance(depositInfo2.getBalance()).setResType(depositInfo2.getResType()).setEcardNo(str));
                    }
                }
            }
            List<DepositInfo> timesDepositList = orderPayParser.getTimesDepositList();
            if (timesDepositList != null) {
                for (DepositInfo depositInfo3 : timesDepositList) {
                    Intrinsics.checkExpressionValueIsNotNull(depositInfo3, "period");
                    if (Intrinsics.areEqual("1", depositInfo3.getValidTag())) {
                        arrayList.add(new BalanceInfo().setPayMode(Constant.TIMES_PAY).setName(depositInfo3.getProductName()).setId(depositInfo3.getDepositId()).setDiscountedFee(orderPayParser.getDiscountedFee()).setDiscount(depositInfo3.getDiscount()).setRealTotalPay(orderPayParser.getRealTotalPay()).setTotalPayMoney(orderPayParser.getTotalPayMoney()).setValidTag("1").setBalance(depositInfo3.getBalance()).setResType(depositInfo3.getResType()).setEcardNo(str));
                    }
                }
            }
            return arrayList;
        }
        throw new ResponseThrowable((Throwable) new NullPointerException(), 0, "获取余额失败！");
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0007J\u001c\u0010\n\u001a\u00020\u00042\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\fH\u0007¨\u0006\r"}, d2 = {"Lcn/xports/qd2/SelectCardPayActivity$Companion;", "", "()V", "start", "", "tag", "", "cardList", "", "Lcn/xports/entity/CardInfo;", "startWithMap", "map", "", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
    /* compiled from: SelectCardPayActivity.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final void startWithMap(@NotNull Map<String, String> map) {
            Intrinsics.checkParameterIsNotNull(map, "map");
            ActivityUtils.startActivity(Router.getIntent(Router.SELECT_CARD_PAY, map));
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

    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }
}

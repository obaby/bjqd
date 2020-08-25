package cn.xports.qd2;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.view.View;
import android.widget.TextView;
import cn.xports.base.BaseBussActivity;
import cn.xports.baselib.http.RxDisposableManager;
import cn.xports.baselib.http.RxUtil;
import cn.xports.baselib.mvp.IPresenter;
import cn.xports.baselib.util.RxBus;
import cn.xports.entity.AgreementInfo;
import cn.xports.entity.CardInfo;
import cn.xports.entity.PairEvent;
import cn.xports.qd2.adapter.CardPromAdapter;
import cn.xports.qd2.entity.CardPromotion;
import cn.xports.qd2.entity.K;
import cn.xports.qd2.util.ApiUtil;
import cn.xports.widget.AgreementBar;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0016\u001a\u00020\u0017H\u0002J\b\u0010\u0018\u001a\u00020\u0017H\u0002J\b\u0010\u0019\u001a\u00020\fH\u0014J\b\u0010\u001a\u001a\u00020\u0013H\u0014J\b\u0010\u001b\u001a\u00020\u0017H\u0016J\b\u0010\u001c\u001a\u00020\u0017H\u0014J\b\u0010\u001d\u001a\u00020\u0017H\u0014J\u0010\u0010\u001e\u001a\u00020\u00172\u0006\u0010\u001f\u001a\u00020 H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u000e\u001a\u0012\u0012\u0004\u0012\u00020\u00100\u000fj\b\u0012\u0004\u0012\u00020\u0010`\u0011X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcn/xports/qd2/CardBuyDetailActivity;", "Lcn/xports/base/BaseBussActivity;", "Lcn/xports/baselib/mvp/IPresenter;", "()V", "adapter", "Lcn/xports/qd2/adapter/CardPromAdapter;", "cardList", "", "Lcn/xports/entity/CardInfo;", "fromPayBottom", "", "goodsId", "", "productId", "promotionList", "Ljava/util/ArrayList;", "Lcn/xports/qd2/entity/CardPromotion;", "Lkotlin/collections/ArrayList;", "realMoney", "", "selectCard", "serviceId", "createOrder", "", "getCardDetail", "getChildTitle", "getLayoutId", "initData", "initView", "onResume", "setAgreement", "agreement", "Lcn/xports/entity/AgreementInfo;", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: CardBuyDetailActivity.kt */
public final class CardBuyDetailActivity extends BaseBussActivity<IPresenter> {
    private HashMap _$_findViewCache;
    /* access modifiers changed from: private */
    public final CardPromAdapter adapter = new CardPromAdapter(this.promotionList);
    /* access modifiers changed from: private */
    public List<? extends CardInfo> cardList;
    /* access modifiers changed from: private */
    public boolean fromPayBottom;
    private String goodsId = "";
    private String productId = "";
    /* access modifiers changed from: private */
    public final ArrayList<CardPromotion> promotionList = new ArrayList<>();
    /* access modifiers changed from: private */
    public int realMoney;
    /* access modifiers changed from: private */
    public CardInfo selectCard;
    private String serviceId = "";

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
        return "会员卡办理";
    }

    /* access modifiers changed from: protected */
    public void initView() {
        TextView textView = (TextView) _$_findCachedViewById(R.id.tvProductMoney);
        Intrinsics.checkExpressionValueIsNotNull(textView, "tvProductMoney");
        TextPaint paint = textView.getPaint();
        Intrinsics.checkExpressionValueIsNotNull(paint, "tvProductMoney.paint");
        paint.setFlags(16);
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.rvCamp);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "rvCamp");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(R.id.rvCamp);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView2, "rvCamp");
        recyclerView2.setAdapter(this.adapter);
        ((AgreementBar) _$_findCachedViewById(R.id.agreementBar)).setCheckListener(new CardBuyDetailActivity$initView$1(this));
        AgreementBar agreementBar = (AgreementBar) _$_findCachedViewById(R.id.agreementBar);
        Intrinsics.checkExpressionValueIsNotNull(agreementBar, "agreementBar");
        agreementBar.setCheck(true);
        ((TextView) findViewById(R.id.tvSubmit)).setOnClickListener(new CardBuyDetailActivity$initView$2(this));
        getCardDetail();
    }

    public void initData() {
        this.productId = getStringExtra("productId");
        this.serviceId = getStringExtra(K.serviceId);
        this.goodsId = getStringExtra("goodsId");
        RxDisposableManager.getInstance().add(getTAG(), RxBus.get().toFlowable(PairEvent.class).subscribe(new CardBuyDetailActivity$initData$1(this)));
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.activity_card_buy_detail;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (!this.fromPayBottom) {
            this.selectCard = null;
        }
    }

    private final void getCardDetail() {
        ApiUtil.getApi2().getProductDetail(this.productId, this.serviceId, this.goodsId).compose(RxUtil.applyErrorsWithIO()).subscribe(new CardBuyDetailActivity$getCardDetail$1(this, this));
    }

    /* access modifiers changed from: private */
    public final void setAgreement(AgreementInfo agreementInfo) {
        AgreementBar agreementBar = (AgreementBar) _$_findCachedViewById(R.id.agreementBar);
        String agreementName = agreementInfo.getAgreementName();
        if (agreementName == null) {
            agreementName = "协议";
        }
        agreementBar.initNameAndDetail(agreementName, agreementInfo.getAgreementCont());
    }

    /* access modifiers changed from: private */
    public final void createOrder() {
        String str;
        CardInfo cardInfo = this.selectCard;
        if (cardInfo == null || (str = cardInfo.getEcardNo()) == null) {
            str = "";
        }
        String str2 = str;
        String str3 = "";
        Iterator<CardPromotion> it = this.promotionList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            CardPromotion next = it.next();
            Intrinsics.checkExpressionValueIsNotNull(next, "prom");
            if (next.isSelect()) {
                JSONArray jSONArray = new JSONArray();
                JSONObject jSONObject = new JSONObject();
                JSONArray jSONArray2 = new JSONArray();
                JSONArray jSONArray3 = new JSONArray();
                List<CardPromotion.Gift> gifts = next.getGifts();
                if (gifts != null) {
                    for (CardPromotion.Gift gift : gifts) {
                        JSONObject jSONObject2 = new JSONObject();
                        Intrinsics.checkExpressionValueIsNotNull(gift, "it");
                        jSONObject2.put("productId", gift.getProductId());
                        jSONArray2.put(jSONObject2);
                    }
                }
                jSONObject.put("gifts", jSONArray2);
                List<CardPromotion.Coupon> coupons = next.getCoupons();
                if (coupons != null) {
                    for (CardPromotion.Coupon coupon : coupons) {
                        JSONObject jSONObject3 = new JSONObject();
                        Intrinsics.checkExpressionValueIsNotNull(coupon, "it");
                        jSONObject3.put(K.couponId, coupon.getCouponId());
                        jSONObject3.put(K.couponNo, coupon.getCouponNo());
                        jSONArray3.put(jSONObject3);
                    }
                }
                jSONObject.put("coupons", jSONArray3);
                CardPromotion.BuyGift buyGifts = next.getBuyGifts();
                if (buyGifts != null) {
                    JSONObject jSONObject4 = new JSONObject();
                    jSONObject4.put("promId", buyGifts.getPromId());
                    jSONObject4.put("giftValue", buyGifts.getGiftValue());
                    jSONObject4.put("unit", buyGifts.getUnit());
                    jSONObject.put("buyGifts", jSONObject4);
                }
                jSONObject.put("promId", next.getPromId());
                jSONObject.put("promType", next.getPromType());
                jSONArray.put(jSONObject);
                str3 = jSONArray.toString();
                Intrinsics.checkExpressionValueIsNotNull(str3, "array.toString()");
            }
        }
        ApiUtil.getApi2().createSpCardOrder(str2, this.productId, (long) this.realMoney, str3).compose(RxUtil.applyIO()).subscribe(new CardBuyDetailActivity$createOrder$4(this, this));
    }
}

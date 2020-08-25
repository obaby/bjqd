package cn.xports.qd2;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import cn.xports.base.BaseBussActivity;
import cn.xports.baselib.bean.DataMap;
import cn.xports.baselib.http.RetrofitUtil;
import cn.xports.baselib.http.RxUtil;
import cn.xports.baselib.mvp.IPresenter;
import cn.xports.baselib.util.MoneyUtil;
import cn.xports.export.EventHandler;
import cn.xports.export.OnPayListener;
import cn.xports.http.SodaService;
import cn.xports.pay.PayPresenter;
import cn.xports.qd2.adapter.RechargeGiftBinder;
import cn.xports.qd2.adapter.RechargeValueBinder;
import cn.xports.qd2.entity.K;
import cn.xports.qd2.entity.RechargePromotion;
import cn.xports.qd2.entity.RechargeValue;
import cn.xports.qd2.http.ApiService2;
import cn.xports.qd2.util.ApiUtil;
import cn.xports.widget.AgreementBar;
import cn.xports.widget.PaySelectView;
import com.alipay.sdk.packet.d;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import me.drakeet.multitype.ItemViewBinder;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\b\r\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J \u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\bH\u0002J\b\u0010\u001c\u001a\u00020\u0017H\u0002J\u001e\u0010\u001d\u001a\u00020\u00172\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001a0\u001f2\u0006\u0010\u0018\u001a\u00020\u0006H\u0002J\b\u0010 \u001a\u00020\u001aH\u0014J\b\u0010!\u001a\u00020\u0017H\u0002J\b\u0010\"\u001a\u00020\u0012H\u0014J\u0016\u0010#\u001a\u00020\u00172\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001a0\u001fH\u0002J\b\u0010$\u001a\u00020\u0017H\u0002J\b\u0010%\u001a\u00020\u0017H\u0016J\b\u0010&\u001a\u00020\u0017H\u0014J\u0012\u0010'\u001a\u00020\u00172\b\u0010(\u001a\u0004\u0018\u00010\u001aH\u0016J\u0010\u0010)\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0006H\u0002J\u0010\u0010*\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u0012H\u0002J\b\u0010+\u001a\u00020\u0017H\u0002R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0013\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0014j\b\u0012\u0004\u0012\u00020\b`\u0015X\u0004¢\u0006\u0002\n\u0000¨\u0006,"}, d2 = {"Lcn/xports/qd2/CardRechargeActivity;", "Lcn/xports/base/BaseBussActivity;", "Lcn/xports/baselib/mvp/IPresenter;", "Lcn/xports/export/OnPayListener;", "()V", "curRechargePromotion", "Lcn/xports/qd2/entity/RechargePromotion;", "curSelectValue", "Lcn/xports/qd2/entity/RechargeValue;", "isInputMoney", "", "mItems", "Lme/drakeet/multitype/Items;", "multiTypeAdapter", "Lme/drakeet/multitype/MultiTypeAdapter;", "payPresenter", "Lcn/xports/pay/PayPresenter;", "subMoney", "", "valueList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "generate", "", "value", "price", "", "rechargeValue", "getAgreement", "getChargeValue", "priceList", "", "getChildTitle", "getCustName", "getLayoutId", "getRechargeProm", "getRechargeRule", "initData", "initView", "onThirdResult", "p0", "setPromTip", "setTotalPrice", "submit", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: CardRechargeActivity.kt */
public final class CardRechargeActivity extends BaseBussActivity<IPresenter> implements OnPayListener {
    private HashMap _$_findViewCache;
    /* access modifiers changed from: private */
    public RechargePromotion curRechargePromotion;
    /* access modifiers changed from: private */
    public RechargeValue curSelectValue;
    /* access modifiers changed from: private */
    public boolean isInputMoney;
    /* access modifiers changed from: private */
    public final Items mItems = new Items();
    /* access modifiers changed from: private */
    public final MultiTypeAdapter multiTypeAdapter = new MultiTypeAdapter();
    /* access modifiers changed from: private */
    public final PayPresenter payPresenter = new PayPresenter(this);
    /* access modifiers changed from: private */
    public int subMoney;
    /* access modifiers changed from: private */
    public final ArrayList<RechargeValue> valueList = new ArrayList<>();

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
        return "卡充值";
    }

    public void onThirdResult(@Nullable String str) {
        if ("1".equals(str)) {
            showMsg("充值成功！");
        } else {
            showMsg("充值失败！");
        }
    }

    /* access modifiers changed from: protected */
    public void initView() {
        TextView textView = (TextView) _$_findCachedViewById(R.id.tvCardNo);
        Intrinsics.checkExpressionValueIsNotNull(textView, "tvCardNo");
        textView.setText(getIntent().getStringExtra(K.ecardNo));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.rvRecharge);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "rvRecharge");
        recyclerView.setLayoutManager(gridLayoutManager);
        gridLayoutManager.setSpanSizeLookup(new CardRechargeActivity$initView$1(this));
        RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(R.id.rvRecharge);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView2, "rvRecharge");
        recyclerView2.setAdapter(this.multiTypeAdapter);
        ((AgreementBar) _$_findCachedViewById(R.id.agreementBar)).setCheckListener(new CardRechargeActivity$initView$2(this));
        AgreementBar agreementBar = (AgreementBar) _$_findCachedViewById(R.id.agreementBar);
        Intrinsics.checkExpressionValueIsNotNull(agreementBar, "agreementBar");
        agreementBar.setCheck(true);
        getCustName();
        getRechargeRule();
        getAgreement();
        ((PaySelectView) _$_findCachedViewById(R.id.payModeView)).showDefaultSelect();
        ((TextView) findViewById(R.id.tvSubmit)).setOnClickListener(new CardRechargeActivity$initView$3(this));
    }

    public void initData() {
        EventHandler.getInstance().setPayListener(this);
        this.multiTypeAdapter.setItems(this.mItems);
        ItemViewBinder rechargeValueBinder = new RechargeValueBinder(this.valueList);
        ItemViewBinder rechargeGiftBinder = new RechargeGiftBinder();
        this.multiTypeAdapter.register(RechargeValue.class, rechargeValueBinder);
        this.multiTypeAdapter.register(String.class, rechargeGiftBinder);
        rechargeValueBinder.setClickListener(new CardRechargeActivity$initData$1(this, rechargeGiftBinder));
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.activity_card_recharge;
    }

    private final void getCustName() {
        ApiUtil.getApi2().getCustName(getIntent().getStringExtra(K.ecardNo)).compose(RxUtil.applyIO()).subscribe(new CardRechargeActivity$getCustName$1(this, this));
    }

    private final void getRechargeRule() {
        ApiService2 api2 = ApiUtil.getApi2();
        Intrinsics.checkExpressionValueIsNotNull(api2, "ApiUtil.getApi2()");
        api2.getRechargeRule().compose(RxUtil.applyErrorsWithIO()).subscribe(new CardRechargeActivity$getRechargeRule$1(this, this));
    }

    /* access modifiers changed from: private */
    public final void getRechargeProm(List<String> list) {
        ApiService2 api2 = ApiUtil.getApi2();
        Intrinsics.checkExpressionValueIsNotNull(api2, "ApiUtil.getApi2()");
        api2.getRechargeProm().compose(RxUtil.applyErrorsWithIO()).subscribe(new CardRechargeActivity$getRechargeProm$1(this, list, this));
    }

    private final void getAgreement() {
        ((SodaService) RetrofitUtil.getInstance().create(SodaService.class)).getAgreement(new DataMap().set("tradeTypeCode", "36")).compose(RxUtil.applyErrorsWithIO()).subscribe(new CardRechargeActivity$getAgreement$1(this, this));
    }

    /* access modifiers changed from: private */
    public final void submit() {
        String str;
        RechargePromotion.PromoPresent promoPresent;
        RechargePromotion.RechargeCampaign rechargeCampaign;
        RechargeValue rechargeValue;
        RechargePromotion.RechargeCampRule rechargeCampRule;
        RechargePromotion rechargePromotion;
        AgreementBar agreementBar = (AgreementBar) _$_findCachedViewById(R.id.agreementBar);
        Intrinsics.checkExpressionValueIsNotNull(agreementBar, "agreementBar");
        if (!agreementBar.isCheck()) {
            showMsg("请同意协议");
        } else if (this.subMoney == 0) {
            showMsg("请选择充值金额");
        } else {
            PaySelectView paySelectView = (PaySelectView) _$_findCachedViewById(R.id.payModeView);
            Intrinsics.checkExpressionValueIsNotNull(paySelectView, "payModeView");
            CharSequence payMode = paySelectView.getPayMode();
            if (payMode == null || payMode.length() == 0) {
                showMsg("请选择支付方式");
                return;
            }
            if (this.isInputMoney) {
                this.curSelectValue = new RechargeValue().setValue(String.valueOf(this.subMoney));
                RechargeValue rechargeValue2 = this.curSelectValue;
                if (!(rechargeValue2 == null || (rechargePromotion = this.curRechargePromotion) == null)) {
                    generate(rechargePromotion, String.valueOf(this.subMoney), rechargeValue2);
                }
            }
            JSONArray jSONArray = new JSONArray();
            RechargePromotion rechargePromotion2 = this.curRechargePromotion;
            if (!(rechargePromotion2 == null || (rechargeCampaign = rechargePromotion2.getRechargeCampaign()) == null || (rechargeValue = this.curSelectValue) == null || (rechargeCampRule = rechargeValue.getRechargeCampRule()) == null)) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(K.campId, rechargeCampaign.getCampId());
                jSONObject.put(d.p, rechargeCampRule.getPresentType());
                jSONObject.put("value", rechargeCampRule.getPresentValue());
                jSONObject.put("num", rechargeCampRule.getPresentNum());
                jSONArray.put(jSONObject);
            }
            RechargeValue rechargeValue3 = this.curSelectValue;
            if (rechargeValue3 == null || (promoPresent = rechargeValue3.getPromoPresent()) == null || (str = promoPresent.getPromId()) == null) {
                str = "";
            }
            ApiUtil.getApi2().createRechargeOrder(String.valueOf(this.subMoney), getIntent().getStringExtra(K.ecardNo), str, jSONArray.toString()).compose(RxUtil.applyIO()).subscribe(new CardRechargeActivity$submit$3(this, this));
        }
    }

    /* access modifiers changed from: private */
    public final void setPromTip(RechargePromotion rechargePromotion) {
        String str;
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        RechargePromotion.RechargeCampaign rechargeCampaign = rechargePromotion.getRechargeCampaign();
        if (rechargeCampaign == null || (str = rechargeCampaign.getCampDesc()) == null) {
            str = "";
        }
        objectRef.element = str;
        String str2 = "";
        List<RechargePromotion.Promo> promoList = rechargePromotion.getPromoList();
        boolean z = false;
        if (promoList != null && (!promoList.isEmpty())) {
            RechargePromotion.Promo promo = promoList.get(0);
            Intrinsics.checkExpressionValueIsNotNull(promo, "it[0]");
            String promName = promo.getPromName();
            if (promName == null) {
                promName = "";
            }
            str2 = promName;
        }
        if (((String) objectRef.element).length() > 0) {
            if (str2.length() > 0) {
                objectRef.element = "1." + ((String) objectRef.element) + "；\n2." + str2 + "；\n";
                TextView textView = (TextView) _$_findCachedViewById(R.id.tvChargeTip);
                Intrinsics.checkExpressionValueIsNotNull(textView, "tvChargeTip");
                textView.setText(StringsKt.replace$default((String) objectRef.element, "\n", "", false, 4, (Object) null));
                ((TextView) _$_findCachedViewById(R.id.tvChargeTip)).setOnClickListener(new CardRechargeActivity$setPromTip$2(this, objectRef));
            }
        }
        if (((String) objectRef.element).length() > 0) {
            objectRef.element = "1." + ((String) objectRef.element) + "；\n";
        } else {
            if (str2.length() > 0) {
                z = true;
            }
            if (z) {
                objectRef.element = "1." + str2 + "；\n";
            } else {
                objectRef.element = "暂无优惠信息";
            }
        }
        TextView textView2 = (TextView) _$_findCachedViewById(R.id.tvChargeTip);
        Intrinsics.checkExpressionValueIsNotNull(textView2, "tvChargeTip");
        textView2.setText(StringsKt.replace$default((String) objectRef.element, "\n", "", false, 4, (Object) null));
        ((TextView) _$_findCachedViewById(R.id.tvChargeTip)).setOnClickListener(new CardRechargeActivity$setPromTip$2(this, objectRef));
    }

    /* access modifiers changed from: private */
    public final void getChargeValue(List<String> list, RechargePromotion rechargePromotion) {
        for (String str : list) {
            RechargeValue value = new RechargeValue().setValue(str);
            Intrinsics.checkExpressionValueIsNotNull(value, "rechargeValue");
            generate(rechargePromotion, str, value);
            this.valueList.add(value);
        }
        this.valueList.add(new RechargeValue().setValue("其他金额"));
        this.mItems.addAll(this.valueList);
        this.multiTypeAdapter.notifyDataSetChanged();
    }

    private final void generate(RechargePromotion rechargePromotion, String str, RechargeValue rechargeValue) {
        List<RechargePromotion.RechargeCampRule> rechargeCampRuleList = rechargePromotion.getRechargeCampRuleList();
        if (rechargeCampRuleList != null) {
            int i = 0;
            for (Object next : rechargeCampRuleList) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                RechargePromotion.RechargeCampRule rechargeCampRule = (RechargePromotion.RechargeCampRule) next;
                int parseInt = Integer.parseInt(str);
                Intrinsics.checkExpressionValueIsNotNull(rechargeCampRule, "rechargeCampRule");
                if (parseInt >= rechargeCampRule.getBuyNum()) {
                    if (rechargePromotion.getRechargeCampaign() != null) {
                        RechargePromotion.RechargeCampaign rechargeCampaign = rechargePromotion.getRechargeCampaign();
                        Intrinsics.checkExpressionValueIsNotNull(rechargeCampaign, "rechargeCampaign");
                        if (Intrinsics.areEqual(rechargeCampaign.getPromType(), "2")) {
                            StringBuilder sb = new StringBuilder();
                            sb.append("赠送优惠券(");
                            String valueName = rechargeCampRule.getValueName();
                            if (valueName == null) {
                                valueName = "";
                            }
                            sb.append(valueName);
                            sb.append((Integer.parseInt(str) / rechargeCampRule.getBuyNum()) * rechargeCampRule.getPresentNum());
                            sb.append("张)");
                            rechargeValue.setRechargeCampInfo(sb.toString());
                            rechargeValue.setRechargeCampRule(rechargeCampRule);
                        }
                    }
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("赠送优惠券(");
                    String valueName2 = rechargeCampRule.getValueName();
                    if (valueName2 == null) {
                        valueName2 = "";
                    }
                    sb2.append(valueName2);
                    sb2.append(rechargeCampRule.getPresentNum());
                    sb2.append("张)");
                    rechargeValue.setRechargeCampInfo(sb2.toString());
                    rechargeValue.setRechargeCampRule(rechargeCampRule);
                }
                i = i2;
            }
        }
        List<RechargePromotion.PromoPresent> promoPresentList = rechargePromotion.getPromoPresentList();
        if (promoPresentList != null) {
            for (RechargePromotion.PromoPresent promoPresent : promoPresentList) {
                int parseInt2 = Integer.parseInt(str);
                Intrinsics.checkExpressionValueIsNotNull(promoPresent, "promoPresent");
                if (parseInt2 >= promoPresent.getRechargeLevel()) {
                    if (promoPresent.getPresentType() == null || !Intrinsics.areEqual(promoPresent.getPresentType(), "2")) {
                        rechargeValue.setPromInfo("送" + (promoPresent.getPresentMoney() / 100) + "元");
                    } else {
                        rechargeValue.setPromInfo("送" + ((promoPresent.getPresentMoney() * Integer.parseInt(str)) / 10000) + "元");
                    }
                    rechargeValue.setPromoPresent(promoPresent);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public final void setTotalPrice(int i) {
        this.subMoney = i;
        View findViewById = findViewById(R.id.tvTotalMoney);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById<TextView>(R.id.tvTotalMoney)");
        ((TextView) findViewById).setText(getResources().getString(R.string.yuan) + MoneyUtil.cent2Yuan(new BigDecimal(i)));
    }
}

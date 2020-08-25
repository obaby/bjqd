package cn.xports.qd2;

import cn.xports.baselib.http.ProcessObserver;
import cn.xports.baselib.mvp.IView;
import cn.xports.qd2.entity.CardProductResult;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002H\u0016¨\u0006\u0006"}, d2 = {"cn/xports/qd2/CardBuyDetailActivity$getCardDetail$1", "Lcn/xports/baselib/http/ProcessObserver;", "Lcn/xports/qd2/entity/CardProductResult;", "next", "", "value", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: CardBuyDetailActivity.kt */
public final class CardBuyDetailActivity$getCardDetail$1 extends ProcessObserver<CardProductResult> {
    final /* synthetic */ CardBuyDetailActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CardBuyDetailActivity$getCardDetail$1(CardBuyDetailActivity cardBuyDetailActivity, IView iView) {
        super(iView);
        this.this$0 = cardBuyDetailActivity;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0069, code lost:
        if (r0 != null) goto L_0x0080;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void next(@org.jetbrains.annotations.Nullable cn.xports.qd2.entity.CardProductResult r6) {
        /*
            r5 = this;
            if (r6 == 0) goto L_0x01fc
            cn.xports.qd2.CardBuyDetailActivity r0 = r5.this$0
            java.util.List r1 = r6.getCardInfoList()
            r0.cardList = r1
            cn.xports.qd2.CardBuyDetailActivity r0 = r5.this$0
            int r1 = cn.xports.qd2.R.id.tvProductName
            android.view.View r0 = r0._$_findCachedViewById(r1)
            android.widget.TextView r0 = (android.widget.TextView) r0
            java.lang.String r1 = "tvProductName"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            cn.xports.entity.ValidProduct r1 = r6.getProduct()
            java.lang.String r2 = "product"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r2)
            java.lang.String r1 = r1.getProductName()
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            r0.setText(r1)
            cn.xports.qd2.CardBuyDetailActivity r0 = r5.this$0
            int r1 = cn.xports.qd2.R.id.tvNeedContent
            android.view.View r0 = r0._$_findCachedViewById(r1)
            android.widget.TextView r0 = (android.widget.TextView) r0
            java.lang.String r1 = "tvNeedContent"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            cn.xports.qd2.entity.CardProductResult$Detail r1 = r6.getUseDetails()
            if (r1 == 0) goto L_0x0048
            java.lang.String r1 = r1.getValue()
            if (r1 == 0) goto L_0x0048
            goto L_0x004a
        L_0x0048:
            java.lang.String r1 = ""
        L_0x004a:
            android.text.Spanned r1 = android.text.Html.fromHtml(r1)
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            r0.setText(r1)
            cn.xports.entity.AgreementInfo r0 = r6.getAgreementInfo()
            r1 = 8
            if (r0 == 0) goto L_0x006c
            cn.xports.qd2.CardBuyDetailActivity r2 = r5.this$0
            cn.xports.entity.AgreementInfo r3 = r6.getAgreementInfo()
            java.lang.String r4 = "agreementInfo"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r4)
            r2.setAgreement(r3)
            if (r0 == 0) goto L_0x006c
            goto L_0x0080
        L_0x006c:
            cn.xports.qd2.CardBuyDetailActivity r0 = r5.this$0
            int r2 = cn.xports.qd2.R.id.agreementBar
            android.view.View r0 = r0._$_findCachedViewById(r2)
            cn.xports.widget.AgreementBar r0 = (cn.xports.widget.AgreementBar) r0
            java.lang.String r2 = "agreementBar"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r2)
            r0.setVisibility(r1)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
        L_0x0080:
            java.util.List r0 = r6.getSpecialCardPromotionList()
            r2 = 0
            if (r0 == 0) goto L_0x0113
            java.util.List r0 = r6.getSpecialCardPromotionList()
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L_0x00a4
            cn.xports.qd2.CardBuyDetailActivity r0 = r5.this$0
            int r3 = cn.xports.qd2.R.id.llCamp
            android.view.View r0 = r0._$_findCachedViewById(r3)
            android.widget.LinearLayout r0 = (android.widget.LinearLayout) r0
            java.lang.String r3 = "llCamp"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r3)
            r0.setVisibility(r1)
            goto L_0x0113
        L_0x00a4:
            cn.xports.qd2.CardBuyDetailActivity r0 = r5.this$0
            int r1 = cn.xports.qd2.R.id.llCamp
            android.view.View r0 = r0._$_findCachedViewById(r1)
            android.widget.LinearLayout r0 = (android.widget.LinearLayout) r0
            java.lang.String r1 = "llCamp"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            r0.setVisibility(r2)
            java.util.List r0 = r6.getSpecialCardPromotionList()
            int r0 = r0.size()
            r1 = 3
            if (r0 <= r1) goto L_0x00fb
            cn.xports.qd2.CardBuyDetailActivity r0 = r5.this$0
            java.util.ArrayList r0 = r0.promotionList
            java.util.List r3 = r6.getSpecialCardPromotionList()
            java.util.List r1 = r3.subList(r2, r1)
            java.util.Collection r1 = (java.util.Collection) r1
            r0.addAll(r1)
            cn.xports.qd2.CardBuyDetailActivity r0 = r5.this$0
            int r1 = cn.xports.qd2.R.id.rlCampMore
            android.view.View r0 = r0._$_findCachedViewById(r1)
            android.widget.RelativeLayout r0 = (android.widget.RelativeLayout) r0
            java.lang.String r1 = "rlCampMore"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            r0.setVisibility(r2)
            cn.xports.qd2.CardBuyDetailActivity r0 = r5.this$0
            int r1 = cn.xports.qd2.R.id.rlCampMore
            android.view.View r0 = r0._$_findCachedViewById(r1)
            android.widget.RelativeLayout r0 = (android.widget.RelativeLayout) r0
            cn.xports.qd2.CardBuyDetailActivity$getCardDetail$1$next$$inlined$apply$lambda$1 r1 = new cn.xports.qd2.CardBuyDetailActivity$getCardDetail$1$next$$inlined$apply$lambda$1
            r1.<init>(r6, r5)
            android.view.View$OnClickListener r1 = (android.view.View.OnClickListener) r1
            r0.setOnClickListener(r1)
            goto L_0x010a
        L_0x00fb:
            cn.xports.qd2.CardBuyDetailActivity r0 = r5.this$0
            java.util.ArrayList r0 = r0.promotionList
            java.util.List r1 = r6.getSpecialCardPromotionList()
            java.util.Collection r1 = (java.util.Collection) r1
            r0.addAll(r1)
        L_0x010a:
            cn.xports.qd2.CardBuyDetailActivity r0 = r5.this$0
            cn.xports.qd2.adapter.CardPromAdapter r0 = r0.adapter
            r0.notifyDataSetChanged()
        L_0x0113:
            cn.xports.qd2.CardBuyDetailActivity r0 = r5.this$0
            cn.xports.entity.ValidProduct r1 = r6.getProduct()
            java.lang.String r3 = "product"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r3)
            long r3 = r1.getPrice()
            int r1 = (int) r3
            r0.realMoney = r1
            java.util.List r6 = r6.getLimitedDiscountPriceList()
            if (r6 == 0) goto L_0x018a
            int r0 = r6.size()
            if (r0 <= 0) goto L_0x018a
            java.lang.Object r0 = r6.get(r2)
            java.lang.String r1 = "get(0)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            cn.xports.qd2.entity.CardPromotion r0 = (cn.xports.qd2.entity.CardPromotion) r0
            java.lang.String r0 = r0.getDiscountMoney()
            if (r0 == 0) goto L_0x0148
            int r0 = java.lang.Integer.parseInt(r0)
            goto L_0x0149
        L_0x0148:
            r0 = 0
        L_0x0149:
            if (r0 == 0) goto L_0x0169
            cn.xports.qd2.CardBuyDetailActivity r0 = r5.this$0
            int r1 = cn.xports.qd2.R.id.tvProductMoney
            android.view.View r0 = r0._$_findCachedViewById(r1)
            android.widget.TextView r0 = (android.widget.TextView) r0
            java.lang.String r1 = "tvProductMoney"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            cn.xports.qd2.CardBuyDetailActivity r1 = r5.this$0
            int r1 = r1.realMoney
            java.lang.String r1 = cn.xports.baselib.util.MoneyUtil.cent2Yuan((int) r1)
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            r0.setText(r1)
        L_0x0169:
            cn.xports.qd2.CardBuyDetailActivity r0 = r5.this$0
            java.lang.Object r6 = r6.get(r2)
            java.lang.String r1 = "get(0)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r6, r1)
            cn.xports.qd2.entity.CardPromotion r6 = (cn.xports.qd2.entity.CardPromotion) r6
            java.lang.String r6 = r6.getDiscountMoney()
            if (r6 == 0) goto L_0x0181
            int r6 = java.lang.Integer.parseInt(r6)
            goto L_0x0187
        L_0x0181:
            cn.xports.qd2.CardBuyDetailActivity r6 = r5.this$0
            int r6 = r6.realMoney
        L_0x0187:
            r0.realMoney = r6
        L_0x018a:
            cn.xports.qd2.CardBuyDetailActivity r6 = r5.this$0
            int r0 = cn.xports.qd2.R.id.tvRealMoney
            android.view.View r6 = r6._$_findCachedViewById(r0)
            android.widget.TextView r6 = (android.widget.TextView) r6
            java.lang.String r0 = "tvRealMoney"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r6, r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            cn.xports.qd2.CardBuyDetailActivity r1 = r5.this$0
            android.content.res.Resources r1 = r1.getResources()
            int r2 = cn.xports.qd2.R.string.yuan
            java.lang.String r1 = r1.getString(r2)
            r0.append(r1)
            cn.xports.qd2.CardBuyDetailActivity r1 = r5.this$0
            int r1 = r1.realMoney
            java.lang.String r1 = cn.xports.baselib.util.MoneyUtil.cent2Yuan((int) r1)
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            r6.setText(r0)
            cn.xports.qd2.CardBuyDetailActivity r6 = r5.this$0
            int r0 = cn.xports.qd2.R.id.tvTotalMoney
            android.view.View r6 = r6.findViewById(r0)
            java.lang.String r0 = "findViewById<TextView>(R.id.tvTotalMoney)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r6, r0)
            android.widget.TextView r6 = (android.widget.TextView) r6
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            cn.xports.qd2.CardBuyDetailActivity r1 = r5.this$0
            android.content.res.Resources r1 = r1.getResources()
            int r2 = cn.xports.qd2.R.string.yuan
            java.lang.String r1 = r1.getString(r2)
            r0.append(r1)
            cn.xports.qd2.CardBuyDetailActivity r1 = r5.this$0
            int r1 = r1.realMoney
            java.lang.String r1 = cn.xports.baselib.util.MoneyUtil.cent2Yuan((int) r1)
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            r6.setText(r0)
        L_0x01fc:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.xports.qd2.CardBuyDetailActivity$getCardDetail$1.next(cn.xports.qd2.entity.CardProductResult):void");
    }
}

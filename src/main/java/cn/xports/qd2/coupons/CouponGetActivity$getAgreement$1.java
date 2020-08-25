package cn.xports.qd2.coupons;

import cn.xports.baselib.http.ProcessObserver;
import cn.xports.baselib.mvp.IView;
import cn.xports.entity.AgreementResult;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002H\u0016¨\u0006\u0006"}, d2 = {"cn/xports/qd2/coupons/CouponGetActivity$getAgreement$1", "Lcn/xports/baselib/http/ProcessObserver;", "Lcn/xports/entity/AgreementResult;", "next", "", "value", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: CouponGetActivity.kt */
public final class CouponGetActivity$getAgreement$1 extends ProcessObserver<AgreementResult> {
    final /* synthetic */ CouponGetActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CouponGetActivity$getAgreement$1(CouponGetActivity couponGetActivity, IView iView) {
        super(iView);
        this.this$0 = couponGetActivity;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002a, code lost:
        if (r1 != null) goto L_0x0041;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void next(@org.jetbrains.annotations.Nullable cn.xports.entity.AgreementResult r6) {
        /*
            r5 = this;
            r0 = 8
            if (r6 == 0) goto L_0x0044
            cn.xports.entity.AgreementInfo r1 = r6.getAgreement()
            if (r1 == 0) goto L_0x002d
            cn.xports.qd2.coupons.CouponGetActivity r2 = r5.this$0
            int r3 = cn.xports.qd2.R.id.agreementBar
            android.view.View r2 = r2._$_findCachedViewById(r3)
            cn.xports.widget.AgreementBar r2 = (cn.xports.widget.AgreementBar) r2
            java.lang.String r3 = r1.getAgreementName()
            if (r3 == 0) goto L_0x001b
            goto L_0x001e
        L_0x001b:
            java.lang.String r3 = "协议"
        L_0x001e:
            java.lang.String r4 = r1.getAgreementCont()
            if (r4 == 0) goto L_0x0025
            goto L_0x0027
        L_0x0025:
            java.lang.String r4 = ""
        L_0x0027:
            r2.initNameAndDetail(r3, r4)
            if (r1 == 0) goto L_0x002d
            goto L_0x0041
        L_0x002d:
            cn.xports.qd2.coupons.CouponGetActivity r1 = r5.this$0
            int r2 = cn.xports.qd2.R.id.agreementBar
            android.view.View r1 = r1._$_findCachedViewById(r2)
            cn.xports.widget.AgreementBar r1 = (cn.xports.widget.AgreementBar) r1
            java.lang.String r2 = "agreementBar"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r2)
            r1.setVisibility(r0)
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
        L_0x0041:
            if (r6 == 0) goto L_0x0044
            goto L_0x005b
        L_0x0044:
            r6 = r5
            cn.xports.qd2.coupons.CouponGetActivity$getAgreement$1 r6 = (cn.xports.qd2.coupons.CouponGetActivity$getAgreement$1) r6
            cn.xports.qd2.coupons.CouponGetActivity r6 = r5.this$0
            int r1 = cn.xports.qd2.R.id.agreementBar
            android.view.View r6 = r6._$_findCachedViewById(r1)
            cn.xports.widget.AgreementBar r6 = (cn.xports.widget.AgreementBar) r6
            java.lang.String r1 = "agreementBar"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r6, r1)
            r6.setVisibility(r0)
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
        L_0x005b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.xports.qd2.coupons.CouponGetActivity$getAgreement$1.next(cn.xports.entity.AgreementResult):void");
    }
}

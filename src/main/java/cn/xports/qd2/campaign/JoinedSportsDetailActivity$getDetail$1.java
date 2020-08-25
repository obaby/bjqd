package cn.xports.qd2.campaign;

import cn.xports.baselib.http.ProcessObserver;
import cn.xports.baselib.mvp.IView;
import cn.xports.qd2.entity.ItemEnrollmentDetailResult;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002H\u0016¨\u0006\u0006"}, d2 = {"cn/xports/qd2/campaign/JoinedSportsDetailActivity$getDetail$1", "Lcn/xports/baselib/http/ProcessObserver;", "Lcn/xports/qd2/entity/ItemEnrollmentDetailResult;", "next", "", "value", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: JoinedSportsDetailActivity.kt */
public final class JoinedSportsDetailActivity$getDetail$1 extends ProcessObserver<ItemEnrollmentDetailResult> {
    final /* synthetic */ JoinedSportsDetailActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    JoinedSportsDetailActivity$getDetail$1(JoinedSportsDetailActivity joinedSportsDetailActivity, IView iView) {
        super(iView);
        this.this$0 = joinedSportsDetailActivity;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x004a, code lost:
        if (r0 != null) goto L_0x0061;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void next(@org.jetbrains.annotations.Nullable cn.xports.qd2.entity.ItemEnrollmentDetailResult r8) {
        /*
            r7 = this;
            if (r8 == 0) goto L_0x02bc
            cn.xports.qd2.entity.ItemEnrollmentDetailResult$CampScore r0 = r8.getPublicCampScore()
            r1 = 0
            if (r0 == 0) goto L_0x004d
            cn.xports.qd2.campaign.JoinedSportsDetailActivity r2 = r7.this$0
            int r3 = cn.xports.qd2.R.id.tvRank
            android.view.View r2 = r2._$_findCachedViewById(r3)
            android.widget.TextView r2 = (android.widget.TextView) r2
            java.lang.String r3 = "tvRank"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r3)
            java.lang.String r3 = r0.getRank()
            if (r3 == 0) goto L_0x0021
        L_0x001e:
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
            goto L_0x0024
        L_0x0021:
            java.lang.String r3 = "--"
            goto L_0x001e
        L_0x0024:
            r2.setText(r3)
            cn.xports.qd2.campaign.JoinedSportsDetailActivity r2 = r7.this$0
            int r3 = cn.xports.qd2.R.id.tvScore
            android.view.View r2 = r2._$_findCachedViewById(r3)
            android.widget.TextView r2 = (android.widget.TextView) r2
            java.lang.String r3 = "tvScore"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r3)
            java.lang.String r3 = r0.getScore()
            if (r3 == 0) goto L_0x003f
        L_0x003c:
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
            goto L_0x0042
        L_0x003f:
            java.lang.String r3 = "--"
            goto L_0x003c
        L_0x0042:
            r2.setText(r3)
            cn.xports.qd2.campaign.JoinedSportsDetailActivity r2 = r7.this$0
            r2.campScore = r0
            if (r0 == 0) goto L_0x004d
            goto L_0x0061
        L_0x004d:
            cn.xports.qd2.campaign.JoinedSportsDetailActivity r0 = r7.this$0
            int r2 = cn.xports.qd2.R.id.tvNoScoreTip
            android.view.View r0 = r0._$_findCachedViewById(r2)
            android.widget.TextView r0 = (android.widget.TextView) r0
            java.lang.String r2 = "tvNoScoreTip"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r2)
            r0.setVisibility(r1)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
        L_0x0061:
            cn.xports.qd2.entity.CampItem r0 = r8.getPublicCampItem()
            r2 = 1
            if (r0 == 0) goto L_0x016a
            java.lang.String r3 = r0.getEnrollType()
            java.lang.String r4 = "1"
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r3, r4)
            if (r3 == 0) goto L_0x00a0
            cn.xports.qd2.campaign.JoinedSportsDetailActivity r3 = r7.this$0
            int r4 = cn.xports.qd2.R.id.llAddMember
            android.view.View r3 = r3._$_findCachedViewById(r4)
            android.widget.LinearLayout r3 = (android.widget.LinearLayout) r3
            java.lang.String r4 = "llAddMember"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r4)
            r4 = 8
            r3.setVisibility(r4)
            cn.xports.qd2.campaign.JoinedSportsDetailActivity r3 = r7.this$0
            int r4 = cn.xports.qd2.R.id.tvCampType
            android.view.View r3 = r3._$_findCachedViewById(r4)
            android.widget.TextView r3 = (android.widget.TextView) r3
            java.lang.String r4 = "tvCampType"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r4)
            java.lang.String r4 = "个人赛"
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4
            r3.setText(r4)
            goto L_0x00c9
        L_0x00a0:
            cn.xports.qd2.campaign.JoinedSportsDetailActivity r3 = r7.this$0
            int r4 = cn.xports.qd2.R.id.llAddMember
            android.view.View r3 = r3._$_findCachedViewById(r4)
            android.widget.LinearLayout r3 = (android.widget.LinearLayout) r3
            java.lang.String r4 = "llAddMember"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r4)
            r3.setVisibility(r1)
            cn.xports.qd2.campaign.JoinedSportsDetailActivity r3 = r7.this$0
            int r4 = cn.xports.qd2.R.id.tvCampType
            android.view.View r3 = r3._$_findCachedViewById(r4)
            android.widget.TextView r3 = (android.widget.TextView) r3
            java.lang.String r4 = "tvCampType"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r4)
            java.lang.String r4 = "团体赛"
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4
            r3.setText(r4)
        L_0x00c9:
            cn.xports.qd2.campaign.JoinedSportsDetailActivity r3 = r7.this$0
            int r4 = cn.xports.qd2.R.id.tvItemName
            android.view.View r3 = r3._$_findCachedViewById(r4)
            android.widget.TextView r3 = (android.widget.TextView) r3
            java.lang.String r4 = "tvItemName"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r4)
            java.lang.String r4 = r0.getName()
            if (r4 == 0) goto L_0x00e1
        L_0x00de:
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4
            goto L_0x00e4
        L_0x00e1:
            java.lang.String r4 = "--"
            goto L_0x00de
        L_0x00e4:
            r3.setText(r4)
            cn.xports.qd2.campaign.JoinedSportsDetailActivity r3 = r7.this$0
            int r4 = cn.xports.qd2.R.id.tvFee
            android.view.View r3 = r3._$_findCachedViewById(r4)
            android.widget.TextView r3 = (android.widget.TextView) r3
            java.lang.String r4 = "tvFee"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r4)
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            int r5 = r0.getEnrollFee()
            java.lang.String r5 = cn.xports.baselib.util.MoneyUtil.simpleYuan((int) r5)
            r4.append(r5)
            java.lang.String r5 = "元"
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4
            r3.setText(r4)
            java.util.List r3 = r8.getPlayerList()
            java.util.Collection r3 = (java.util.Collection) r3
            if (r3 == 0) goto L_0x0126
            boolean r3 = r3.isEmpty()
            if (r3 == 0) goto L_0x0124
            goto L_0x0126
        L_0x0124:
            r3 = 0
            goto L_0x0127
        L_0x0126:
            r3 = 1
        L_0x0127:
            if (r3 == 0) goto L_0x012b
            r3 = 0
            goto L_0x0133
        L_0x012b:
            java.util.List r3 = r8.getPlayerList()
            int r3 = r3.size()
        L_0x0133:
            cn.xports.qd2.campaign.JoinedSportsDetailActivity r4 = r7.this$0
            int r5 = cn.xports.qd2.R.id.tv_nums
            android.view.View r4 = r4._$_findCachedViewById(r5)
            cn.xports.baselib.widget.FakeBoldText r4 = (cn.xports.baselib.widget.FakeBoldText) r4
            java.lang.String r5 = "tv_nums"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r4, r5)
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "("
            r5.append(r6)
            r5.append(r3)
            java.lang.String r3 = "/"
            r5.append(r3)
            java.lang.String r0 = r0.getMaxPersonNum()
            r5.append(r0)
            java.lang.String r0 = "人)"
            r5.append(r0)
            java.lang.String r0 = r5.toString()
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            r4.setText(r0)
        L_0x016a:
            cn.xports.entity.CampaignInfo r0 = r8.getPublicCampaign()
            if (r0 == 0) goto L_0x022b
            cn.xports.qd2.campaign.JoinedSportsDetailActivity r3 = r7.this$0
            int r4 = cn.xports.qd2.R.id.tvCampDate
            android.view.View r3 = r3._$_findCachedViewById(r4)
            android.widget.TextView r3 = (android.widget.TextView) r3
            java.lang.String r4 = "tvCampDate"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r4)
            java.lang.String r4 = r0.getCampStartDate()
            java.lang.String r5 = r0.getCampEndDate()
            java.lang.String r4 = cn.xports.qd2.util.DateRangeUtil.dateRange(r4, r5, r1)
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4
            r3.setText(r4)
            cn.xports.qd2.campaign.JoinedSportsDetailActivity r3 = r7.this$0
            int r4 = cn.xports.qd2.R.id.tvName
            android.view.View r3 = r3._$_findCachedViewById(r4)
            cn.xports.baselib.widget.FakeBoldText r3 = (cn.xports.baselib.widget.FakeBoldText) r3
            java.lang.String r4 = "tvName"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r4)
            java.lang.String r4 = r0.getTitle()
            if (r4 == 0) goto L_0x01a8
        L_0x01a5:
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4
            goto L_0x01ab
        L_0x01a8:
            java.lang.String r4 = "--"
            goto L_0x01a5
        L_0x01ab:
            r3.setText(r4)
            cn.xports.qd2.campaign.JoinedSportsDetailActivity r3 = r7.this$0
            java.lang.String r3 = r3.exState
            java.lang.String r4 = ""
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r3, r4)
            r3 = r3 ^ r2
            if (r3 == 0) goto L_0x01f4
            cn.xports.qd2.campaign.JoinedSportsDetailActivity r3 = r7.this$0
            int r4 = cn.xports.qd2.R.id.tvName
            android.view.View r3 = r3._$_findCachedViewById(r4)
            cn.xports.baselib.widget.FakeBoldText r3 = (cn.xports.baselib.widget.FakeBoldText) r3
            com.blankj.utilcode.util.SpanUtils r3 = com.blankj.utilcode.util.SpanUtils.with(r3)
            java.lang.String r4 = r0.getTitle()
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4
            com.blankj.utilcode.util.SpanUtils r3 = r3.append(r4)
            cn.xports.qd2.campaign.JoinedSportsDetailActivity r4 = r7.this$0
            java.lang.String r4 = r4.exState
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4
            com.blankj.utilcode.util.SpanUtils r3 = r3.append(r4)
            r4 = 10
            com.blankj.utilcode.util.SpanUtils r2 = r3.setFontSize(r4, r2)
            cn.xports.qd2.campaign.JoinedSportsDetailActivity r3 = r7.this$0
            int r3 = r3.exBgColor
            com.blankj.utilcode.util.SpanUtils r2 = r2.setForegroundColor(r3)
            r2.create()
        L_0x01f4:
            cn.xports.qd2.campaign.JoinedSportsDetailActivity r2 = r7.this$0
            int r3 = cn.xports.qd2.R.id.tvCampLocation
            android.view.View r2 = r2._$_findCachedViewById(r3)
            android.widget.TextView r2 = (android.widget.TextView) r2
            java.lang.String r3 = "tvCampLocation"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r3)
            java.lang.String r3 = r0.getPlace()
            if (r3 == 0) goto L_0x020c
        L_0x0209:
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
            goto L_0x020f
        L_0x020c:
            java.lang.String r3 = "--"
            goto L_0x0209
        L_0x020f:
            r2.setText(r3)
            cn.xports.qd2.campaign.JoinedSportsDetailActivity r2 = r7.this$0
            android.content.Context r2 = (android.content.Context) r2
            java.lang.String r0 = r0.getCoverImg()
            com.bumptech.glide.RequestBuilder r0 = cn.xports.baselib.util.GlideUtil.loadImage((android.content.Context) r2, (java.lang.String) r0)
            cn.xports.qd2.campaign.JoinedSportsDetailActivity r2 = r7.this$0
            int r3 = cn.xports.qd2.R.id.iv_cover
            android.view.View r2 = r2._$_findCachedViewById(r3)
            android.widget.ImageView r2 = (android.widget.ImageView) r2
            r0.into((android.widget.ImageView) r2)
        L_0x022b:
            cn.xports.entity.OrderInfo r0 = r8.getTrade()
            if (r0 == 0) goto L_0x029e
            cn.xports.qd2.campaign.JoinedSportsDetailActivity r2 = r7.this$0
            int r3 = cn.xports.qd2.R.id.tvTradeNo
            android.view.View r2 = r2._$_findCachedViewById(r3)
            android.widget.TextView r2 = (android.widget.TextView) r2
            java.lang.String r3 = "tvTradeNo"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r3)
            java.lang.String r3 = r0.getTradeId()
            if (r3 == 0) goto L_0x0249
        L_0x0246:
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
            goto L_0x024c
        L_0x0249:
            java.lang.String r3 = "--"
            goto L_0x0246
        L_0x024c:
            r2.setText(r3)
            cn.xports.qd2.campaign.JoinedSportsDetailActivity r2 = r7.this$0
            int r3 = cn.xports.qd2.R.id.tvTradeTime
            android.view.View r2 = r2._$_findCachedViewById(r3)
            android.widget.TextView r2 = (android.widget.TextView) r2
            java.lang.String r3 = "tvTradeTime"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r3)
            java.lang.String r3 = r0.getAcceptDate()
            if (r3 == 0) goto L_0x0267
        L_0x0264:
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
            goto L_0x026a
        L_0x0267:
            java.lang.String r3 = "--"
            goto L_0x0264
        L_0x026a:
            r2.setText(r3)
            cn.xports.qd2.campaign.JoinedSportsDetailActivity r2 = r7.this$0
            int r3 = cn.xports.qd2.R.id.tvRealPay
            android.view.View r2 = r2._$_findCachedViewById(r3)
            android.widget.TextView r2 = (android.widget.TextView) r2
            java.lang.String r3 = "tvRealPay"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r3)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            int r4 = cn.xports.qd2.R.string.yuan
            java.lang.String r4 = com.blankj.utilcode.util.StringUtils.getString(r4)
            r3.append(r4)
            int r0 = r0.getPayTfee()
            java.lang.String r0 = cn.xports.baselib.util.MoneyUtil.simpleYuan((int) r0)
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            r2.setText(r0)
        L_0x029e:
            java.util.List r8 = r8.getNewPlayerList()
            if (r8 == 0) goto L_0x02bc
            int r8 = r8.size()
            if (r8 <= 0) goto L_0x02bc
            cn.xports.qd2.campaign.JoinedSportsDetailActivity r8 = r7.this$0
            int r0 = cn.xports.qd2.R.id.ctNew
            android.view.View r8 = r8._$_findCachedViewById(r0)
            cn.xports.widget.CornerTextView r8 = (cn.xports.widget.CornerTextView) r8
            java.lang.String r0 = "ctNew"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r8, r0)
            r8.setVisibility(r1)
        L_0x02bc:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.xports.qd2.campaign.JoinedSportsDetailActivity$getDetail$1.next(cn.xports.qd2.entity.ItemEnrollmentDetailResult):void");
    }
}

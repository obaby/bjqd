package cn.xports.qd2.adapter;

import cn.xports.baselib.adapter.XViewBinder;
import cn.xports.baselib.bean.DataMap;
import cn.xports.qd2.R;

public class CouponGetBinder extends XViewBinder<DataMap> {
    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.item_get_coupon;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onBindViewHolder(@android.support.annotation.NonNull cn.xports.baselib.adapter.XBaseHolder r11, @android.support.annotation.NonNull final cn.xports.baselib.bean.DataMap r12) {
        /*
            r10 = this;
            int r0 = cn.xports.qd2.R.id.tv_coupon_name
            java.lang.String r1 = "couponName"
            java.lang.String r1 = r12.getString(r1)
            r11.setText(r0, r1)
            int r0 = cn.xports.qd2.R.id.tv_coupon_desc
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "满"
            r1.append(r2)
            java.lang.String r2 = "orderAmount"
            java.lang.String r2 = r12.getString(r2)
            java.lang.String r2 = cn.xports.baselib.util.MoneyUtil.simpleYuan((java.lang.String) r2)
            r1.append(r2)
            java.lang.String r2 = "元可用"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r11.setText(r0, r1)
            java.lang.String r0 = "expireDate"
            java.lang.String r0 = r12.getString(r0)
            java.lang.String r1 = "-"
            java.lang.String r2 = "/"
            java.lang.String r0 = r0.replace(r1, r2)
            java.lang.String r1 = "effectiveDate"
            java.lang.String r1 = r12.getString(r1)
            java.lang.String r2 = "-"
            java.lang.String r3 = "/"
            java.lang.String r1 = r1.replace(r2, r3)
            int r2 = cn.xports.qd2.R.id.tv_coupon_expire
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r1)
            java.lang.String r1 = "-"
            r3.append(r1)
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            r11.setText(r2, r0)
            java.lang.String r0 = ""
            java.lang.String r1 = "valueType"
            java.lang.String r1 = r12.getString(r1)
            int r2 = r1.hashCode()
            r3 = 1
            r4 = 0
            r5 = -1
            switch(r2) {
                case 49: goto L_0x00a2;
                case 50: goto L_0x0098;
                case 51: goto L_0x008e;
                case 52: goto L_0x0084;
                case 53: goto L_0x007a;
                default: goto L_0x0079;
            }
        L_0x0079:
            goto L_0x00ac
        L_0x007a:
            java.lang.String r2 = "5"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x00ac
            r1 = 4
            goto L_0x00ad
        L_0x0084:
            java.lang.String r2 = "4"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x00ac
            r1 = 3
            goto L_0x00ad
        L_0x008e:
            java.lang.String r2 = "3"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x00ac
            r1 = 2
            goto L_0x00ad
        L_0x0098:
            java.lang.String r2 = "2"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x00ac
            r1 = 1
            goto L_0x00ad
        L_0x00a2:
            java.lang.String r2 = "1"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x00ac
            r1 = 0
            goto L_0x00ad
        L_0x00ac:
            r1 = -1
        L_0x00ad:
            switch(r1) {
                case 0: goto L_0x00c1;
                case 1: goto L_0x00bd;
                case 2: goto L_0x00b9;
                case 3: goto L_0x00b5;
                case 4: goto L_0x00b1;
                default: goto L_0x00b0;
            }
        L_0x00b0:
            goto L_0x00d3
        L_0x00b1:
            java.lang.String r0 = "折扣券"
            goto L_0x00d3
        L_0x00b5:
            java.lang.String r0 = "停车券"
            goto L_0x00d3
        L_0x00b9:
            java.lang.String r0 = "兑换券"
            goto L_0x00d3
        L_0x00bd:
            java.lang.String r0 = "体验券"
            goto L_0x00d3
        L_0x00c1:
            java.lang.String r0 = "代金券"
            int r1 = cn.xports.qd2.R.id.tv_money
            java.lang.String r2 = "value"
            java.lang.String r2 = r12.getString(r2)
            java.lang.String r2 = cn.xports.baselib.util.MoneyUtil.simpleYuan((java.lang.String) r2)
            r11.setText(r1, r2)
        L_0x00d3:
            int r1 = cn.xports.qd2.R.id.tv_coupon_type
            android.view.View r1 = r11.getView(r1)
            android.widget.TextView r1 = (android.widget.TextView) r1
            java.lang.String r2 = "代金券"
            boolean r2 = r0.equals(r2)
            if (r2 == 0) goto L_0x00f4
            r2 = 1096810496(0x41600000, float:14.0)
            r1.setTextSize(r2)
            android.text.TextPaint r2 = r1.getPaint()
            r2.setFakeBoldText(r4)
            r1.setText(r0)
            goto L_0x0103
        L_0x00f4:
            r2 = 1101004800(0x41a00000, float:20.0)
            r1.setTextSize(r2)
            android.text.TextPaint r2 = r1.getPaint()
            r2.setFakeBoldText(r3)
            r1.setText(r0)
        L_0x0103:
            int r1 = cn.xports.qd2.R.id.tv_money
            java.lang.String r2 = "代金券"
            boolean r2 = r0.equals(r2)
            r11.setVisible(r1, r2)
            int r1 = cn.xports.qd2.R.id.tv_money_tag
            java.lang.String r2 = "代金券"
            boolean r0 = r0.equals(r2)
            r11.setVisible(r1, r0)
            int r0 = cn.xports.qd2.R.color.red_fd4
            int r0 = com.blankj.utilcode.util.ColorUtils.getColor(r0)
            java.lang.String r1 = "立即领取"
            java.lang.String r2 = "endDate"
            java.lang.String r2 = r12.getString(r2)
            r3 = 1000(0x3e8, float:1.401E-42)
            long r6 = com.blankj.utilcode.util.TimeUtils.getTimeSpanByNow(r2, r3)
            r8 = 0
            int r2 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r2 >= 0) goto L_0x0140
            java.lang.String r1 = "已结束"
            int r0 = cn.xports.qd2.R.color.gray_d9d
            int r0 = com.blankj.utilcode.util.ColorUtils.getColor(r0)
            goto L_0x016f
        L_0x0140:
            java.lang.String r2 = "startDate"
            java.lang.String r2 = r12.getString(r2)
            long r2 = com.blankj.utilcode.util.TimeUtils.getTimeSpanByNow(r2, r3)
            int r4 = (r2 > r8 ? 1 : (r2 == r8 ? 0 : -1))
            if (r4 <= 0) goto L_0x015a
            java.lang.String r1 = "未开始"
            int r0 = cn.xports.qd2.R.color.red_fd4
            int r0 = com.blankj.utilcode.util.ColorUtils.getColor(r0)
            r5 = r0
            r0 = -1
            goto L_0x016f
        L_0x015a:
            java.lang.String r2 = "remainAmount"
            java.lang.Integer r2 = r12.getIntValue(r2)
            int r2 = r2.intValue()
            if (r2 > 0) goto L_0x016f
            java.lang.String r1 = "已领完"
            int r0 = cn.xports.qd2.R.color.gray_d9d
            int r0 = com.blankj.utilcode.util.ColorUtils.getColor(r0)
        L_0x016f:
            int r2 = cn.xports.qd2.R.id.tv_coupon_action
            r11.setBackgroundColor(r2, r0)
            int r0 = cn.xports.qd2.R.id.tv_coupon_action
            r11.setTextColor(r0, r5)
            int r0 = cn.xports.qd2.R.id.tv_coupon_action
            r11.setText(r0, r1)
            int r0 = cn.xports.qd2.R.id.tv_coupon_action
            cn.xports.qd2.adapter.CouponGetBinder$1 r2 = new cn.xports.qd2.adapter.CouponGetBinder$1
            r2.<init>(r1, r12)
            r11.setOnClickListener(r0, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.xports.qd2.adapter.CouponGetBinder.onBindViewHolder(cn.xports.baselib.adapter.XBaseHolder, cn.xports.baselib.bean.DataMap):void");
    }
}

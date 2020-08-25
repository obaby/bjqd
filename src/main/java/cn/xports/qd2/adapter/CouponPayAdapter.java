package cn.xports.qd2.adapter;

import cn.xports.baselib.adapter.XBaseAdapter;
import cn.xports.baselib.bean.DataMap;
import cn.xports.qd2.R;
import java.util.List;

public class CouponPayAdapter extends XBaseAdapter<DataMap> {
    public CouponPayAdapter(List<DataMap> list) {
        super(list);
    }

    public int getLayoutId() {
        return R.layout.item_coupon_pay;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onBindViewHolder(@android.support.annotation.NonNull cn.xports.baselib.adapter.XBaseHolder r10, final int r11) {
        /*
            r9 = this;
            java.util.List r0 = r9.list
            java.lang.Object r0 = r0.get(r11)
            cn.xports.baselib.bean.DataMap r0 = (cn.xports.baselib.bean.DataMap) r0
            int r1 = cn.xports.qd2.R.id.tv_coupon_name
            java.lang.String r2 = "couponName"
            java.lang.String r2 = r0.getString(r2)
            r10.setText(r1, r2)
            java.lang.String r1 = ""
            java.lang.String r2 = "valueType"
            java.lang.String r2 = r0.getString(r2)
            int r3 = r2.hashCode()
            r4 = 1
            r5 = 0
            switch(r3) {
                case 49: goto L_0x004d;
                case 50: goto L_0x0043;
                case 51: goto L_0x0039;
                case 52: goto L_0x002f;
                case 53: goto L_0x0025;
                default: goto L_0x0024;
            }
        L_0x0024:
            goto L_0x0057
        L_0x0025:
            java.lang.String r3 = "5"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x0057
            r2 = 4
            goto L_0x0058
        L_0x002f:
            java.lang.String r3 = "4"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x0057
            r2 = 3
            goto L_0x0058
        L_0x0039:
            java.lang.String r3 = "3"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x0057
            r2 = 2
            goto L_0x0058
        L_0x0043:
            java.lang.String r3 = "2"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x0057
            r2 = 1
            goto L_0x0058
        L_0x004d:
            java.lang.String r3 = "1"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x0057
            r2 = 0
            goto L_0x0058
        L_0x0057:
            r2 = -1
        L_0x0058:
            switch(r2) {
                case 0: goto L_0x0071;
                case 1: goto L_0x006c;
                case 2: goto L_0x0067;
                case 3: goto L_0x0062;
                case 4: goto L_0x005d;
                default: goto L_0x005b;
            }
        L_0x005b:
            goto L_0x00f0
        L_0x005d:
            java.lang.String r1 = "折扣券"
            goto L_0x00f0
        L_0x0062:
            java.lang.String r1 = "停车券"
            goto L_0x00f0
        L_0x0067:
            java.lang.String r1 = "兑换券"
            goto L_0x00f0
        L_0x006c:
            java.lang.String r1 = "体验券"
            goto L_0x00f0
        L_0x0071:
            java.lang.String r1 = "代金券"
            java.lang.String r2 = "balance"
            java.lang.String r2 = r0.getString(r2)
            java.lang.String r2 = cn.xports.baselib.util.MoneyUtil.simpleYuan((java.lang.String) r2)
            int r3 = cn.xports.qd2.R.id.tv_money
            r10.setText(r3, r2)
            java.lang.String r2 = "parValue"
            boolean r2 = r0.hasKey(r2)
            if (r2 == 0) goto L_0x00ba
            java.lang.String r2 = "balance"
            java.lang.String r2 = r0.getString(r2)
            java.lang.String r3 = "parValue"
            java.lang.String r3 = r0.getString(r3)
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x00f0
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "总金额 ¥ "
            r1.append(r2)
            java.lang.String r2 = "parValue"
            java.lang.String r2 = r0.getString(r2)
            java.lang.String r2 = cn.xports.baselib.util.MoneyUtil.simpleYuan((java.lang.String) r2)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            goto L_0x00f0
        L_0x00ba:
            java.lang.String r2 = "value"
            boolean r2 = r0.hasKey(r2)
            if (r2 == 0) goto L_0x00f0
            java.lang.String r2 = "balance"
            java.lang.String r2 = r0.getString(r2)
            java.lang.String r3 = "value"
            java.lang.String r3 = r0.getString(r3)
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x00f0
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "总金额 ¥ "
            r1.append(r2)
            java.lang.String r2 = "value"
            java.lang.String r2 = r0.getString(r2)
            java.lang.String r2 = cn.xports.baselib.util.MoneyUtil.simpleYuan((java.lang.String) r2)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
        L_0x00f0:
            int r2 = cn.xports.qd2.R.id.tv_coupon_type
            android.view.View r2 = r10.getView(r2)
            android.widget.TextView r2 = (android.widget.TextView) r2
            java.lang.String r3 = "代金券"
            boolean r3 = r1.equals(r3)
            if (r3 != 0) goto L_0x011b
            java.lang.String r3 = "总金额"
            boolean r3 = r1.contains(r3)
            if (r3 == 0) goto L_0x010b
            goto L_0x011b
        L_0x010b:
            r3 = 1096810496(0x41600000, float:14.0)
            r2.setTextSize(r3)
            int r3 = cn.xports.qd2.R.id.tv_money
            r10.setVisible(r3, r5)
            int r3 = cn.xports.qd2.R.id.tv_money_tag
            r10.setVisible(r3, r5)
            goto L_0x012a
        L_0x011b:
            r3 = 1094713344(0x41400000, float:12.0)
            r2.setTextSize(r3)
            int r3 = cn.xports.qd2.R.id.tv_money
            r10.setVisible(r3, r4)
            int r3 = cn.xports.qd2.R.id.tv_money_tag
            r10.setVisible(r3, r4)
        L_0x012a:
            r2.setText(r1)
            java.lang.String r1 = "expireDate"
            java.lang.String r1 = r0.getString(r1)
            r2 = 10
            java.lang.String r1 = r1.substring(r5, r2)
            java.lang.String r3 = "-"
            java.lang.String r6 = "/"
            java.lang.String r1 = r1.replace(r3, r6)
            java.lang.String r3 = "effectiveDate"
            java.lang.String r3 = r0.getString(r3)
            java.lang.String r2 = r3.substring(r5, r2)
            java.lang.String r3 = "-"
            java.lang.String r6 = "/"
            java.lang.String r2 = r2.replace(r3, r6)
            java.lang.String r3 = ""
            int r6 = cn.xports.qd2.R.id.tv_coupon_expire
            android.view.View r6 = r10.getView(r6)
            android.widget.TextView r6 = (android.widget.TextView) r6
            java.lang.String r7 = "couponState"
            java.lang.String r7 = r0.getString(r7)
            java.lang.String r8 = "1"
            boolean r7 = r7.equals(r8)
            if (r7 == 0) goto L_0x01ec
            java.lang.String r5 = "recentExpireTag"
            java.lang.String r5 = r0.getString(r5)
            java.lang.String r7 = "1"
            boolean r5 = r5.equals(r7)
            if (r5 == 0) goto L_0x017b
            java.lang.String r3 = "(即将过期)"
        L_0x017b:
            com.blankj.utilcode.util.SpanUtils r5 = com.blankj.utilcode.util.SpanUtils.with(r6)
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r6.append(r2)
            java.lang.String r2 = "-"
            r6.append(r2)
            r6.append(r1)
            java.lang.String r1 = r6.toString()
            com.blankj.utilcode.util.SpanUtils r1 = r5.append(r1)
            com.blankj.utilcode.util.SpanUtils r1 = r1.append(r3)
            int r2 = cn.xports.qd2.R.color.red_fd4
            int r2 = com.blankj.utilcode.util.ColorUtils.getColor(r2)
            com.blankj.utilcode.util.SpanUtils r1 = r1.setForegroundColor(r2)
            r1.create()
            int r1 = cn.xports.qd2.R.id.iv_check
            r10.setVisible(r1, r4)
            int r1 = cn.xports.qd2.R.id.ll_item
            int r2 = cn.xports.qd2.R.drawable.bg_coupon_pay
            r10.setBackgroundRes(r1, r2)
            int r1 = cn.xports.qd2.R.id.tv_money_tag
            java.lang.String r2 = "#fd4772"
            int r2 = android.graphics.Color.parseColor(r2)
            r10.setTextColor(r1, r2)
            int r1 = cn.xports.qd2.R.id.tv_money
            java.lang.String r2 = "#fd4772"
            int r2 = android.graphics.Color.parseColor(r2)
            r10.setTextColor(r1, r2)
            int r1 = cn.xports.qd2.R.id.tv_coupon_type
            java.lang.String r2 = "#fd4772"
            int r2 = android.graphics.Color.parseColor(r2)
            r10.setTextColor(r1, r2)
            int r1 = cn.xports.qd2.R.id.tv_coupon_expire
            int r2 = cn.xports.qd2.R.color.second_text_color
            int r2 = com.blankj.utilcode.util.ColorUtils.getColor(r2)
            r10.setTextColor(r1, r2)
            int r1 = cn.xports.qd2.R.id.tv_coupon_name
            int r2 = cn.xports.qd2.R.color.main_text_color
            int r2 = com.blankj.utilcode.util.ColorUtils.getColor(r2)
            r10.setTextColor(r1, r2)
            goto L_0x0248
        L_0x01ec:
            int r3 = cn.xports.qd2.R.id.iv_check
            r10.setVisible(r3, r5)
            int r3 = cn.xports.qd2.R.id.ll_item
            int r4 = cn.xports.qd2.R.drawable.bg_my_coupon_gray
            r10.setBackgroundRes(r3, r4)
            int r3 = cn.xports.qd2.R.id.tv_money_tag
            java.lang.String r4 = "#cccccc"
            int r4 = android.graphics.Color.parseColor(r4)
            r10.setTextColor(r3, r4)
            int r3 = cn.xports.qd2.R.id.tv_money
            java.lang.String r4 = "#cccccc"
            int r4 = android.graphics.Color.parseColor(r4)
            r10.setTextColor(r3, r4)
            int r3 = cn.xports.qd2.R.id.tv_coupon_type
            java.lang.String r4 = "#cccccc"
            int r4 = android.graphics.Color.parseColor(r4)
            r10.setTextColor(r3, r4)
            int r3 = cn.xports.qd2.R.id.tv_coupon_expire
            java.lang.String r4 = "#cccccc"
            int r4 = android.graphics.Color.parseColor(r4)
            r10.setTextColor(r3, r4)
            int r3 = cn.xports.qd2.R.id.tv_coupon_name
            java.lang.String r4 = "#cccccc"
            int r4 = android.graphics.Color.parseColor(r4)
            r10.setTextColor(r3, r4)
            int r3 = cn.xports.qd2.R.id.tv_coupon_expire
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r2)
            java.lang.String r2 = "-"
            r4.append(r2)
            r4.append(r1)
            java.lang.String r1 = r4.toString()
            r10.setText(r3, r1)
        L_0x0248:
            int r1 = cn.xports.qd2.R.id.iv_check
            java.lang.String r2 = "select"
            boolean r2 = r0.getBooleanValue(r2)
            if (r2 == 0) goto L_0x0255
            int r2 = cn.xports.qd2.R.drawable.ic_checked
            goto L_0x0257
        L_0x0255:
            int r2 = cn.xports.qd2.R.drawable.ic_uncheck
        L_0x0257:
            r10.setImageResource(r1, r2)
            android.view.View r10 = r10.itemView
            cn.xports.qd2.adapter.CouponPayAdapter$1 r1 = new cn.xports.qd2.adapter.CouponPayAdapter$1
            r1.<init>(r0, r11)
            r10.setOnClickListener(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.xports.qd2.adapter.CouponPayAdapter.onBindViewHolder(cn.xports.baselib.adapter.XBaseHolder, int):void");
    }
}

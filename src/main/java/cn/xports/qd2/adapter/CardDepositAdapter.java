package cn.xports.qd2.adapter;

import cn.xports.baselib.adapter.XBaseAdapter;
import cn.xports.entity.DepositInfo;
import cn.xports.qd2.R;
import java.util.List;

public class CardDepositAdapter extends XBaseAdapter<DepositInfo> {
    public CardDepositAdapter(List<DepositInfo> list) {
        super(list);
    }

    public int getLayoutId() {
        return R.layout.item_card_service;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x00b7, code lost:
        if (r0.equals("1") != false) goto L_0x00bb;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onBindViewHolder(@android.support.annotation.NonNull cn.xports.baselib.adapter.XBaseHolder r8, int r9) {
        /*
            r7 = this;
            java.util.List r0 = r7.list
            java.lang.Object r9 = r0.get(r9)
            cn.xports.entity.DepositInfo r9 = (cn.xports.entity.DepositInfo) r9
            int r0 = cn.xports.qd2.R.id.tv_deposit_name
            java.lang.String r1 = r9.getDepositName()
            r8.setText(r0, r1)
            java.lang.String r0 = "1"
            java.lang.String r1 = r9.getActiveTag()
            boolean r0 = r0.equals(r1)
            r1 = 0
            if (r0 == 0) goto L_0x010c
            int r0 = cn.xports.qd2.R.id.tv_left
            java.lang.String r2 = "#353535"
            int r2 = android.graphics.Color.parseColor(r2)
            r8.setTextColor(r0, r2)
            int r0 = cn.xports.qd2.R.id.tv_deposit_name
            java.lang.String r2 = "#353535"
            int r2 = android.graphics.Color.parseColor(r2)
            r8.setTextColor(r0, r2)
            int r0 = cn.xports.qd2.R.id.tv_left_tip
            r2 = 1
            r8.setVisible(r0, r2)
            int r0 = cn.xports.qd2.R.id.tv_effect_date
            r8.setVisible(r0, r2)
            java.lang.String r0 = r9.getStartDate()
            int r0 = r0.length()
            r3 = 10
            if (r0 < r3) goto L_0x005c
            java.lang.String r0 = r9.getStartDate()
            java.lang.String r0 = r0.substring(r1, r3)
            java.lang.String r4 = "-"
            java.lang.String r5 = "/"
            java.lang.String r0 = r0.replace(r4, r5)
            goto L_0x005e
        L_0x005c:
            java.lang.String r0 = ""
        L_0x005e:
            java.lang.String r4 = r9.getEndDate()
            int r4 = r4.length()
            if (r4 < r3) goto L_0x0079
            java.lang.String r4 = r9.getEndDate()
            java.lang.String r3 = r4.substring(r1, r3)
            java.lang.String r4 = "-"
            java.lang.String r5 = "/"
            java.lang.String r3 = r3.replace(r4, r5)
            goto L_0x007b
        L_0x0079:
            java.lang.String r3 = ""
        L_0x007b:
            int r4 = cn.xports.qd2.R.id.tv_effect_date
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "有效期："
            r5.append(r6)
            r5.append(r0)
            java.lang.String r0 = "-"
            r5.append(r0)
            r5.append(r3)
            java.lang.String r0 = r5.toString()
            r8.setText(r4, r0)
            java.lang.String r0 = r9.getLimitType()
            r3 = -1
            int r4 = r0.hashCode()
            switch(r4) {
                case 49: goto L_0x00b1;
                case 50: goto L_0x00a7;
                default: goto L_0x00a6;
            }
        L_0x00a6:
            goto L_0x00ba
        L_0x00a7:
            java.lang.String r1 = "2"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x00ba
            r1 = 1
            goto L_0x00bb
        L_0x00b1:
            java.lang.String r2 = "1"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x00ba
            goto L_0x00bb
        L_0x00ba:
            r1 = -1
        L_0x00bb:
            switch(r1) {
                case 0: goto L_0x00f0;
                case 1: goto L_0x00d5;
                default: goto L_0x00be;
            }
        L_0x00be:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            int r9 = r9.getBalance()
            r0.append(r9)
            java.lang.String r9 = "天"
            r0.append(r9)
            java.lang.String r9 = r0.toString()
            goto L_0x0106
        L_0x00d5:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            int r9 = r9.getBalance()
            java.lang.String r9 = cn.xports.baselib.util.MoneyUtil.cent2Yuan((int) r9)
            r0.append(r9)
            java.lang.String r9 = "元"
            r0.append(r9)
            java.lang.String r9 = r0.toString()
            goto L_0x0106
        L_0x00f0:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            int r9 = r9.getBalance()
            r0.append(r9)
            java.lang.String r9 = "次"
            r0.append(r9)
            java.lang.String r9 = r0.toString()
        L_0x0106:
            int r0 = cn.xports.qd2.R.id.tv_left
            r8.setText(r0, r9)
            goto L_0x0134
        L_0x010c:
            int r9 = cn.xports.qd2.R.id.tv_left_tip
            r8.setVisible(r9, r1)
            int r9 = cn.xports.qd2.R.id.tv_effect_date
            r8.setVisible(r9, r1)
            int r9 = cn.xports.qd2.R.id.tv_left
            java.lang.String r0 = "未激活"
            r8.setText(r9, r0)
            int r9 = cn.xports.qd2.R.id.tv_left
            java.lang.String r0 = "#888888"
            int r0 = android.graphics.Color.parseColor(r0)
            r8.setTextColor(r9, r0)
            int r9 = cn.xports.qd2.R.id.tv_deposit_name
            java.lang.String r0 = "#888888"
            int r0 = android.graphics.Color.parseColor(r0)
            r8.setTextColor(r9, r0)
        L_0x0134:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.xports.qd2.adapter.CardDepositAdapter.onBindViewHolder(cn.xports.baselib.adapter.XBaseHolder, int):void");
    }
}

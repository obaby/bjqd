package cn.xports.order;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.xports.baselib.util.MoneyUtil;
import cn.xports.baselib.util.SegmentUtils;
import cn.xports.entity.TradeTicket;
import cn.xports.qdplugin.R;

public class ItemPayInfoWrap {
    private String date;
    private String money;
    private String name;
    private TradeTicket ticket;
    private View view;

    public ItemPayInfoWrap(TradeTicket tradeTicket, LinearLayout linearLayout, int i) {
        this.ticket = tradeTicket;
        this.view = LayoutInflater.from(linearLayout.getContext()).inflate(R.layout.item_pay_order_info, linearLayout, false);
        TextView textView = (TextView) this.view.findViewById(R.id.tv_name);
        TextView textView2 = (TextView) this.view.findViewById(R.id.tv_date);
        TextView textView3 = (TextView) this.view.findViewById(R.id.tv_price);
        String effectDate = tradeTicket.getEffectDate();
        if (i == 0) {
            if (effectDate != null && effectDate.length() >= 10) {
                effectDate = effectDate.substring(0, 10);
            }
            textView2.setText(effectDate + " " + SegmentUtils.getStartTime(tradeTicket.getStartSegment()) + "-" + SegmentUtils.getEndTime(tradeTicket.getEndSegment()));
            textView.setText(tradeTicket.getFieldName());
            StringBuilder sb = new StringBuilder();
            sb.append("¥");
            sb.append(MoneyUtil.cent2Yuan(tradeTicket.getPayMoney()));
            textView3.setText(sb.toString());
            return;
        }
        textView2.setText(effectDate);
        textView.setText(tradeTicket.getTicketTypeName());
        textView3.setText("¥" + MoneyUtil.cent2Yuan(tradeTicket.getPayMoney() * i) + "/" + i + "张");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ItemPayInfoWrap(java.lang.String r5, long r6, cn.xports.entity.ProductRes r8, android.widget.LinearLayout r9) {
        /*
            r4 = this;
            r4.<init>()
            android.content.Context r0 = r9.getContext()
            android.view.LayoutInflater r0 = android.view.LayoutInflater.from(r0)
            int r1 = cn.xports.qdplugin.R.layout.item_pay_order_info_card
            r2 = 0
            android.view.View r9 = r0.inflate(r1, r9, r2)
            r4.view = r9
            android.view.View r9 = r4.view
            int r0 = cn.xports.qdplugin.R.id.tv_name
            android.view.View r9 = r9.findViewById(r0)
            android.widget.TextView r9 = (android.widget.TextView) r9
            android.view.View r0 = r4.view
            int r1 = cn.xports.qdplugin.R.id.tv_date
            android.view.View r0 = r0.findViewById(r1)
            android.widget.TextView r0 = (android.widget.TextView) r0
            android.view.View r1 = r4.view
            int r3 = cn.xports.qdplugin.R.id.tv_price
            android.view.View r1 = r1.findViewById(r3)
            android.widget.TextView r1 = (android.widget.TextView) r1
            r9.setText(r5)
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r9 = "¥"
            r5.append(r9)
            int r6 = (int) r6
            java.lang.String r6 = cn.xports.baselib.util.MoneyUtil.cent2Yuan((int) r6)
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            r1.setText(r5)
            java.lang.String r5 = ""
            java.lang.String r6 = r8.getOffsetUnit()
            int r7 = r6.hashCode()
            r9 = 2
            switch(r7) {
                case 48: goto L_0x007c;
                case 49: goto L_0x0072;
                case 50: goto L_0x0068;
                case 51: goto L_0x005e;
                default: goto L_0x005d;
            }
        L_0x005d:
            goto L_0x0086
        L_0x005e:
            java.lang.String r7 = "3"
            boolean r6 = r6.equals(r7)
            if (r6 == 0) goto L_0x0086
            r6 = 2
            goto L_0x0087
        L_0x0068:
            java.lang.String r7 = "2"
            boolean r6 = r6.equals(r7)
            if (r6 == 0) goto L_0x0086
            r6 = 3
            goto L_0x0087
        L_0x0072:
            java.lang.String r7 = "1"
            boolean r6 = r6.equals(r7)
            if (r6 == 0) goto L_0x0086
            r6 = 1
            goto L_0x0087
        L_0x007c:
            java.lang.String r7 = "0"
            boolean r6 = r6.equals(r7)
            if (r6 == 0) goto L_0x0086
            r6 = 0
            goto L_0x0087
        L_0x0086:
            r6 = -1
        L_0x0087:
            switch(r6) {
                case 0: goto L_0x0097;
                case 1: goto L_0x0093;
                case 2: goto L_0x008f;
                case 3: goto L_0x008b;
                default: goto L_0x008a;
            }
        L_0x008a:
            goto L_0x009a
        L_0x008b:
            java.lang.String r5 = "季"
            goto L_0x009a
        L_0x008f:
            java.lang.String r5 = "年"
            goto L_0x009a
        L_0x0093:
            java.lang.String r5 = "月"
            goto L_0x009a
        L_0x0097:
            java.lang.String r5 = "天"
        L_0x009a:
            java.lang.String r6 = ""
            boolean r6 = r5.equals(r6)
            if (r6 != 0) goto L_0x00c6
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "自办卡之日起"
            r6.append(r7)
            java.lang.String r7 = r8.getOffsetValue()
            r6.append(r7)
            r6.append(r5)
            java.lang.String r5 = "内"
            r6.append(r5)
            java.lang.String r5 = r6.toString()
            r0.setText(r5)
            goto L_0x014a
        L_0x00c6:
            java.lang.String r5 = r8.getOffsetStartDate()     // Catch:{ Exception -> 0x014a }
            boolean r5 = android.text.TextUtils.isEmpty(r5)     // Catch:{ Exception -> 0x014a }
            if (r5 != 0) goto L_0x0111
            java.lang.String r5 = r8.getOffsetStartDate()     // Catch:{ Exception -> 0x014a }
            java.lang.String r6 = r8.getOffsetEndDate()     // Catch:{ Exception -> 0x014a }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x014a }
            r7.<init>()     // Catch:{ Exception -> 0x014a }
            java.lang.String r8 = r5.substring(r2, r9)     // Catch:{ Exception -> 0x014a }
            r7.append(r8)     // Catch:{ Exception -> 0x014a }
            java.lang.String r8 = "/"
            r7.append(r8)     // Catch:{ Exception -> 0x014a }
            r8 = 4
            java.lang.String r5 = r5.substring(r9, r8)     // Catch:{ Exception -> 0x014a }
            r7.append(r5)     // Catch:{ Exception -> 0x014a }
            java.lang.String r5 = "-"
            r7.append(r5)     // Catch:{ Exception -> 0x014a }
            java.lang.String r5 = r6.substring(r2, r9)     // Catch:{ Exception -> 0x014a }
            r7.append(r5)     // Catch:{ Exception -> 0x014a }
            java.lang.String r5 = "/"
            r7.append(r5)     // Catch:{ Exception -> 0x014a }
            java.lang.String r5 = r6.substring(r9, r8)     // Catch:{ Exception -> 0x014a }
            r7.append(r5)     // Catch:{ Exception -> 0x014a }
            java.lang.String r5 = r7.toString()     // Catch:{ Exception -> 0x014a }
            r0.setText(r5)     // Catch:{ Exception -> 0x014a }
            goto L_0x014a
        L_0x0111:
            java.lang.String r5 = r8.getStartDate()     // Catch:{ Exception -> 0x014a }
            boolean r5 = android.text.TextUtils.isEmpty(r5)     // Catch:{ Exception -> 0x014a }
            if (r5 != 0) goto L_0x014a
            java.lang.String r5 = r8.getStartDate()     // Catch:{ Exception -> 0x014a }
            java.lang.String r6 = r8.getEndDatae()     // Catch:{ Exception -> 0x014a }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x014a }
            r7.<init>()     // Catch:{ Exception -> 0x014a }
            java.lang.String r8 = "-"
            java.lang.String r9 = "/"
            java.lang.String r5 = r5.replace(r8, r9)     // Catch:{ Exception -> 0x014a }
            r7.append(r5)     // Catch:{ Exception -> 0x014a }
            java.lang.String r5 = "-"
            r7.append(r5)     // Catch:{ Exception -> 0x014a }
            java.lang.String r5 = "-"
            java.lang.String r8 = "/"
            java.lang.String r5 = r6.replace(r5, r8)     // Catch:{ Exception -> 0x014a }
            r7.append(r5)     // Catch:{ Exception -> 0x014a }
            java.lang.String r5 = r7.toString()     // Catch:{ Exception -> 0x014a }
            r0.setText(r5)     // Catch:{ Exception -> 0x014a }
        L_0x014a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.xports.order.ItemPayInfoWrap.<init>(java.lang.String, long, cn.xports.entity.ProductRes, android.widget.LinearLayout):void");
    }

    public ItemPayInfoWrap(String str, int i, String str2, String str3, LinearLayout linearLayout) {
        this.view = LayoutInflater.from(linearLayout.getContext()).inflate(R.layout.item_pay_order_info_card, linearLayout, false);
        this.view.findViewById(R.id.v_line).setVisibility(8);
        ((TextView) this.view.findViewById(R.id.tv_name)).setText(str);
        ((TextView) this.view.findViewById(R.id.tv_price)).setText("¥" + MoneyUtil.cent2Yuan(i));
        ((TextView) this.view.findViewById(R.id.tv_time_tip)).setText(str2);
        ((TextView) this.view.findViewById(R.id.tv_date)).setText(str3);
    }

    public View getView() {
        return this.view;
    }
}

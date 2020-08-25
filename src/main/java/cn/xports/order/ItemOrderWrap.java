package cn.xports.order;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.xports.baselib.util.DateUtil;
import cn.xports.baselib.util.DensityUtil;
import cn.xports.baselib.util.SegmentUtils;
import cn.xports.entity.TradeTicket;
import cn.xports.qdplugin.R;
import cn.xports.ticket.TicketQRDialog;
import cn.xports.util.QRUtil;

public class ItemOrderWrap {
    private CheckBox checkBox;
    private boolean enable;
    private String extraState;
    private View mask;
    private TradeTicket ticket;
    private TextView tvNo;
    private TextView tvTicketName = ((TextView) this.view.findViewById(R.id.tv_ticket_name));
    private View view;

    public ItemOrderWrap(final TradeTicket tradeTicket, final LinearLayout linearLayout, String str) {
        String str2;
        this.ticket = tradeTicket;
        this.extraState = str;
        this.view = LayoutInflater.from(linearLayout.getContext()).inflate(R.layout.item_order_detail, linearLayout, false);
        TextView textView = (TextView) this.view.findViewById(R.id.tv_ticket_date);
        ImageView imageView = (ImageView) this.view.findViewById(R.id.iv_qr);
        this.tvNo = (TextView) this.view.findViewById(R.id.tv_no);
        this.checkBox = (CheckBox) this.view.findViewById(R.id.checkbox);
        this.mask = this.view.findViewById(R.id.v_mask);
        int endSegment = tradeTicket.getEndSegment();
        if (tradeTicket.getFieldId() != 0) {
            str2 = tradeTicket.getFieldName();
        } else {
            str2 = tradeTicket.getTicketTypeName();
            endSegment = tradeTicket.getEndSegment() - 1;
        }
        if (tradeTicket.getState() == 1 && "已完成".equals(str)) {
            str2 = str2 + "（已退票）";
        }
        this.tvTicketName.setText(str2);
        String week = DateUtil.getWeek(DateUtil.parse2Date(tradeTicket.getEffectDate(), "yyyy-MM-dd HH:mm:ss"));
        String effectDate = tradeTicket.getEffectDate();
        if (effectDate != null && effectDate.length() >= 10) {
            effectDate = effectDate.substring(5, 10);
        }
        textView.setText((effectDate != null ? effectDate.replace("-", "/") : effectDate) + "（" + week + "）" + (SegmentUtils.getStartTime(tradeTicket.getStartSegment()) + "-" + SegmentUtils.getEndTime(endSegment)));
        if (tradeTicket.getState() == 1 || "已过期".equals(str) || "已取消".equals(str)) {
            imageView.setVisibility(8);
            return;
        }
        imageView.setVisibility(0);
        imageView.setImageBitmap(QRUtil.createQRCode(tradeTicket.getTicketNo(), DensityUtil.dp2px(60.0f)));
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                TicketQRDialog.showDialog(linearLayout.getContext(), tradeTicket);
            }
        });
    }

    public TradeTicket getTicket() {
        return this.ticket;
    }

    public ItemOrderWrap setTicket(TradeTicket tradeTicket) {
        this.ticket = tradeTicket;
        return this;
    }

    public ItemOrderWrap setNo(int i) {
        if (this.tvNo != null) {
            TextView textView = this.tvNo;
            textView.setText("" + i);
        }
        return this;
    }

    public String getExtraState() {
        return this.extraState;
    }

    public ItemOrderWrap setExtraState(String str) {
        this.extraState = str;
        return this;
    }

    public CheckBox getCheckBox() {
        return this.checkBox;
    }

    public ItemOrderWrap setCheckBox(CheckBox checkBox2) {
        this.checkBox = checkBox2;
        return this;
    }

    public boolean isEnable() {
        return this.enable;
    }

    public ItemOrderWrap setEnable(boolean z) {
        this.enable = z;
        if (!z) {
            this.mask.setVisibility(0);
            this.checkBox.setEnabled(false);
        } else {
            this.mask.setVisibility(8);
        }
        return this;
    }

    public boolean isSelected() {
        return this.checkBox.isChecked();
    }

    public void showSelect(boolean z) {
        if (z) {
            this.checkBox.setVisibility(0);
            if (this.ticket.getGroupTag().equals("1") || this.ticket.getBackFee() == 0 || this.ticket.getState() == 9) {
                setEnable(false);
            }
            if (this.ticket.getState() == 1) {
                setEnable(false);
                return;
            }
            return;
        }
        this.checkBox.setVisibility(8);
        if (this.ticket.getGroupTag().equals("1")) {
            setEnable(true);
        }
    }

    public View getView() {
        return this.view;
    }
}

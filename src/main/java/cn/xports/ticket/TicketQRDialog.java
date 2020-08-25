package cn.xports.ticket;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import cn.xports.baselib.util.DateUtil;
import cn.xports.baselib.util.DensityUtil;
import cn.xports.baselib.util.SegmentUtils;
import cn.xports.entity.TradeTicket;
import cn.xports.qdplugin.R;
import cn.xports.util.QRUtil;
import java.util.Date;

public class TicketQRDialog {
    public static void showDialog(Context context, final TradeTicket tradeTicket) {
        Date date;
        View inflate = View.inflate(context, R.layout.dialog_ticket_qr, (ViewGroup) null);
        TextView textView = (TextView) inflate.findViewById(R.id.tv_name);
        TextView textView2 = (TextView) inflate.findViewById(R.id.tv_date);
        TextView textView3 = (TextView) inflate.findViewById(R.id.tv_close);
        final ImageView imageView = (ImageView) inflate.findViewById(R.id.iv_qr);
        textView.setText(tradeTicket.getFieldId() != 0 ? tradeTicket.getFieldName() : tradeTicket.getTicketTypeName());
        new Date();
        String effectDate = tradeTicket.getEffectDate();
        if (effectDate == null || effectDate.length() != 10) {
            date = DateUtil.parse2Date(tradeTicket.getEffectDate(), "yyyy-MM-dd HH:mm:ss");
        } else {
            date = DateUtil.parse2Date(tradeTicket.getEffectDate(), "yyyy-MM-dd");
        }
        String week = DateUtil.getWeek(date);
        if (effectDate != null && effectDate.length() >= 10) {
            effectDate = effectDate.substring(5, 10);
        }
        if (effectDate != null) {
            effectDate = effectDate.replace("-", "/");
        }
        int endSegment = tradeTicket.getEndSegment();
        if (tradeTicket.getFieldId() == 0) {
            endSegment--;
        }
        textView2.setText(effectDate + "（" + week + "）" + (SegmentUtils.getStartTime(tradeTicket.getStartSegment()) + "-" + SegmentUtils.getEndTime(endSegment)));
        imageView.post(new Runnable() {
            public void run() {
                QRUtil.createQRImage(tradeTicket.getTicketNo(), imageView);
            }
        });
        final AlertDialog show = new AlertDialog.Builder(context).setView(inflate).setCancelable(false).show();
        WindowManager.LayoutParams attributes = show.getWindow().getAttributes();
        attributes.width = DensityUtil.dp2px(315.0f);
        attributes.height = DensityUtil.dp2px(400.0f);
        show.getWindow().setAttributes(attributes);
        textView3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                show.dismiss();
            }
        });
    }
}

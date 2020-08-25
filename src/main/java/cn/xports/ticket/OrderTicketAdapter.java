package cn.xports.ticket;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.xports.baselib.util.SegmentUtils;
import cn.xports.entity.TradeTicket;
import cn.xports.qdplugin.R;
import java.util.List;

public class OrderTicketAdapter extends RecyclerView.Adapter<Holder> {
    private List<TradeTicket> tradeTickets;

    public OrderTicketAdapter(List<TradeTicket> list) {
        this.tradeTickets = list;
    }

    @NonNull
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new Holder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_venue_ticket, viewGroup, false));
    }

    public void onBindViewHolder(@NonNull final Holder holder, int i) {
        final TradeTicket tradeTicket = this.tradeTickets.get(i);
        holder.tvTicketName.setText(tradeTicket.getVenueName() + "-" + tradeTicket.getTicketTypeName());
        holder.tvTicketNo.setText("NO." + tradeTicket.getTicketNo());
        int endSegment = tradeTicket.getEndSegment();
        if (tradeTicket.getFieldId() == 0) {
            endSegment--;
        }
        holder.tvTicketTime.setText(tradeTicket.getEffectDate().substring(0, 10) + " " + SegmentUtils.getStartTime(tradeTicket.getStartSegment()) + "-" + SegmentUtils.getEndTime(endSegment));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                TicketQRDialog.showDialog(holder.itemView.getContext(), tradeTicket);
            }
        });
    }

    public int getItemCount() {
        if (this.tradeTickets == null) {
            return 0;
        }
        return this.tradeTickets.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView tvTicketName;
        TextView tvTicketNo;
        TextView tvTicketTime;

        public Holder(@NonNull View view) {
            super(view);
            this.tvTicketName = (TextView) view.findViewById(R.id.tv_ticket_name);
            this.tvTicketNo = (TextView) view.findViewById(R.id.tv_ticket_no);
            this.tvTicketTime = (TextView) view.findViewById(R.id.tv_effect_date);
        }
    }
}

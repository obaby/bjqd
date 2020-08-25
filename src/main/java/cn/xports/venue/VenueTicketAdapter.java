package cn.xports.venue;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.xports.baselib.util.MoneyUtil;
import cn.xports.entity.TicketType;
import cn.xports.qdplugin.R;
import cn.xports.ticket.TicketDetailActivity;
import java.util.ArrayList;
import java.util.List;

public class VenueTicketAdapter extends RecyclerView.Adapter<Holder> {
    /* access modifiers changed from: private */
    public Activity activity;
    private List<TicketType> originTickets;
    private List<TicketType> ticketTypes = new ArrayList();

    public VenueTicketAdapter(Activity activity2, List<TicketType> list) {
        this.originTickets = list;
        if (list.size() > 5) {
            this.ticketTypes.addAll(list.subList(0, 5));
        } else {
            this.ticketTypes.addAll(list);
        }
        this.activity = activity2;
    }

    @NonNull
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new Holder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_ticket_2buy, viewGroup, false));
    }

    public void onBindViewHolder(@NonNull Holder holder, int i) {
        final TicketType ticketType = this.ticketTypes.get(i);
        holder.tvTicketName.setText(ticketType.getTicketTypeName());
        TextView textView = holder.tvTicketLeft;
        textView.setText("剩余：" + ticketType.getDayRemain());
        holder.tvTicketMoney.setText(MoneyUtil.cent2Yuan(ticketType.getPrice()));
        holder.tvBuy.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Activity access$000 = VenueTicketAdapter.this.activity;
                Intent putExtra = new Intent(VenueTicketAdapter.this.activity, TicketDetailActivity.class).putExtra("ticket", ticketType);
                Intent putExtra2 = putExtra.putExtra("ticketTypeId", ticketType.getTicketTypeId() + "");
                access$000.startActivity(putExtra2.putExtra("timeId", ticketType.getTimeId() + ""));
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Activity access$000 = VenueTicketAdapter.this.activity;
                Intent putExtra = new Intent(VenueTicketAdapter.this.activity, TicketDetailActivity.class).putExtra("ticket", ticketType);
                Intent putExtra2 = putExtra.putExtra("ticketTypeId", ticketType.getTicketTypeId() + "");
                access$000.startActivity(putExtra2.putExtra("timeId", ticketType.getTimeId() + ""));
            }
        });
        if (i == getItemCount() - 1) {
            holder.line.setVisibility(8);
        } else {
            holder.line.setVisibility(0);
        }
    }

    public int getItemCount() {
        return this.ticketTypes.size();
    }

    public void expand() {
        this.ticketTypes.clear();
        this.ticketTypes.addAll(this.originTickets);
        notifyDataSetChanged();
    }

    public void up() {
        this.ticketTypes.clear();
        this.ticketTypes.addAll(this.originTickets.subList(0, 5));
        notifyDataSetChanged();
    }

    public void notifyData() {
        this.ticketTypes.clear();
        if (this.originTickets.size() > 5) {
            this.ticketTypes.addAll(this.originTickets.subList(0, 5));
        } else {
            this.ticketTypes.addAll(this.originTickets);
        }
        notifyDataSetChanged();
    }

    public class Holder extends RecyclerView.ViewHolder {
        View line;
        TextView tvBuy;
        TextView tvTicketLeft;
        TextView tvTicketMoney;
        TextView tvTicketName;

        public Holder(@NonNull View view) {
            super(view);
            this.tvTicketName = (TextView) view.findViewById(R.id.tv_ticket_name);
            this.tvTicketLeft = (TextView) view.findViewById(R.id.tv_ticket_left);
            this.tvTicketMoney = (TextView) view.findViewById(R.id.tv_ticket_money);
            this.tvBuy = (TextView) view.findViewById(R.id.tv_buy_ticket);
            this.line = view.findViewById(R.id.v_line);
        }
    }
}

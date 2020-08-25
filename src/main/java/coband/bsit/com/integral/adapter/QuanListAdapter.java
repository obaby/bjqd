package coband.bsit.com.integral.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import coband.bsit.com.integral.R;

public class QuanListAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private Context context;
    /* access modifiers changed from: private */
    public OnExchangeClickListener onExchangeClickListener;

    public interface OnExchangeClickListener {
        void onExchangeClick();
    }

    public int getItemCount() {
        return 0;
    }

    public QuanListAdapter(Context context2) {
        this.context = context2;
    }

    public void setOnExchangeClickListener(OnExchangeClickListener onExchangeClickListener2) {
        this.onExchangeClickListener = onExchangeClickListener2;
    }

    @NonNull
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(this.context).inflate(R.layout.integral_item_quan_list_layout, viewGroup, false));
    }

    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.tv_exchange.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (QuanListAdapter.this.onExchangeClickListener != null) {
                    QuanListAdapter.this.onExchangeClickListener.onExchangeClick();
                }
            }
        });
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        /* access modifiers changed from: private */
        public TextView tv_exchange;
        private TextView tv_money;
        private TextView tv_quanName;
        private TextView tv_quanValue;

        public MyViewHolder(View view) {
            super(view);
            this.tv_money = (TextView) view.findViewById(R.id.tv_money);
            this.tv_quanName = (TextView) view.findViewById(R.id.tv_quanName);
            this.tv_quanValue = (TextView) view.findViewById(R.id.tv_quanValue);
            this.tv_exchange = (TextView) view.findViewById(R.id.tv_exchange);
        }
    }
}

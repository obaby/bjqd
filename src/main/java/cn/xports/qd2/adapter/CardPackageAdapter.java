package cn.xports.qd2.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import cn.xports.entity.CardInfo;
import cn.xports.qd2.CardInfoActivity;
import cn.xports.qd2.R;
import cn.xports.qd2.ShowQrActivity;
import cn.xports.qd2.entity.K;
import com.blankj.utilcode.util.GsonUtils;
import java.util.List;

public class CardPackageAdapter extends RecyclerView.Adapter<ViewHolder> {
    /* access modifiers changed from: private */
    public List<CardInfo> cardInfos;

    public CardPackageAdapter(List<CardInfo> list) {
        this.cardInfos = list;
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_card_little, viewGroup, false));
    }

    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final CardInfo cardInfo = this.cardInfos.get(i);
        TextView textView = viewHolder.tvNo;
        textView.setText("NO." + cardInfo.getEcardNo());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                cardInfo.setSelected(true);
                for (CardInfo cardInfo : CardPackageAdapter.this.cardInfos) {
                    if (!cardInfo.equals(cardInfo)) {
                        cardInfo.setSelected(false);
                    }
                }
                viewHolder.itemView.getContext().startActivity(new Intent(viewHolder.itemView.getContext(), CardInfoActivity.class).putExtra(K.ecardNo, cardInfo.getEcardNo()).putExtra("cardInfos", GsonUtils.toJson(CardPackageAdapter.this.cardInfos)));
            }
        });
        viewHolder.ivQr.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                cardInfo.setSelected(true);
                for (CardInfo cardInfo : CardPackageAdapter.this.cardInfos) {
                    if (!cardInfo.equals(cardInfo)) {
                        cardInfo.setSelected(false);
                    }
                }
                viewHolder.itemView.getContext().startActivity(new Intent(viewHolder.itemView.getContext(), ShowQrActivity.class).putExtra("cardInfos", GsonUtils.toJson(CardPackageAdapter.this.cardInfos)));
            }
        });
    }

    public int getItemCount() {
        if (this.cardInfos == null) {
            return 0;
        }
        return this.cardInfos.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivQr;
        TextView tvNo;

        public ViewHolder(View view) {
            super(view);
            this.tvNo = (TextView) view.findViewById(R.id.tv_card_no);
            this.ivQr = (ImageView) view.findViewById(R.id.iv_qr);
        }
    }
}

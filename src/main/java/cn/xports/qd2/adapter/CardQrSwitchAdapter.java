package cn.xports.qd2.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import cn.xports.baselib.adapter.XBaseAdapter;
import cn.xports.baselib.adapter.XBaseHolder;
import cn.xports.entity.CardInfo;
import cn.xports.qd2.R;
import java.util.List;

public class CardQrSwitchAdapter extends XBaseAdapter<CardInfo> {
    /* access modifiers changed from: private */
    public OnItemClick onItemClick;

    public interface OnItemClick {
        void onItemClick(CardInfo cardInfo);
    }

    public CardQrSwitchAdapter(List<CardInfo> list) {
        super(list);
    }

    public void setOnItemClick(OnItemClick onItemClick2) {
        this.onItemClick = onItemClick2;
    }

    public int getLayoutId() {
        return R.layout.item_card_switch_qr;
    }

    public void onBindViewHolder(@NonNull XBaseHolder xBaseHolder, int i) {
        int i2;
        final CardInfo cardInfo = (CardInfo) this.list.get(i);
        xBaseHolder.setImageResource(R.id.iv_card, cardInfo.isSelected() ? R.drawable.ic_card_switch_selected : R.drawable.ic_card_switch_unselected);
        int i3 = R.id.tv_card_num;
        if (cardInfo.isSelected()) {
            i2 = xBaseHolder.itemView.getResources().getColor(R.color.blue_2e6);
        } else {
            i2 = xBaseHolder.itemView.getResources().getColor(R.color.gray_cbc);
        }
        xBaseHolder.setTextColor(i3, i2);
        String ecardNo = cardInfo.getEcardNo();
        if (ecardNo.length() >= 4) {
            ecardNo = "**" + ecardNo.substring(ecardNo.length() - 4);
        }
        xBaseHolder.setText(R.id.tv_card_num, ecardNo);
        xBaseHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (CardQrSwitchAdapter.this.onItemClick != null) {
                    for (CardInfo selected : CardQrSwitchAdapter.this.list) {
                        selected.setSelected(false);
                    }
                    cardInfo.setSelected(true);
                    CardQrSwitchAdapter.this.notifyDataSetChanged();
                    CardQrSwitchAdapter.this.onItemClick.onItemClick(cardInfo);
                }
            }
        });
    }
}

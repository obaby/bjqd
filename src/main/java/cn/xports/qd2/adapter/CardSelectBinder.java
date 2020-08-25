package cn.xports.qd2.adapter;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cn.xports.baselib.adapter.XBaseHolder;
import cn.xports.entity.CardInfo;
import cn.xports.qd2.R;
import java.util.Iterator;
import java.util.List;
import me.drakeet.multitype.ItemViewBinder;

public class CardSelectBinder extends ItemViewBinder<CardInfo, XBaseHolder> {
    private boolean canSelect = true;
    /* access modifiers changed from: private */
    public OnItemSelectClick click;
    /* access modifiers changed from: private */
    public List<CardInfo> infos;

    public interface OnItemSelectClick {
        void onClick(CardInfo cardInfo);

        void onSelect(CardInfo cardInfo);
    }

    public CardSelectBinder(List<CardInfo> list) {
        this.infos = list;
    }

    public CardSelectBinder(List<CardInfo> list, boolean z) {
        this.infos = list;
        this.canSelect = z;
    }

    /* access modifiers changed from: protected */
    @NonNull
    public XBaseHolder onCreateViewHolder(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
        return new XBaseHolder(layoutInflater.inflate(R.layout.item_select_card, viewGroup, false));
    }

    /* access modifiers changed from: protected */
    public void onBindViewHolder(@NonNull XBaseHolder xBaseHolder, @NonNull final CardInfo cardInfo) {
        String ecardNo = cardInfo.getEcardNo();
        if (this.infos != null) {
            xBaseHolder.setVisible(R.id.v_line, this.infos.indexOf(cardInfo) != 0);
        }
        int i = R.id.tv_card_name;
        xBaseHolder.setText(i, "一卡通尾号" + ecardNo.substring(ecardNo.length() - 4));
        xBaseHolder.setVisible(R.id.iv_check, this.canSelect);
        xBaseHolder.setImageResource(R.id.iv_check, cardInfo.isSelected() ? R.drawable.ic_radio_checked : R.drawable.ic_radio_uncheck);
        xBaseHolder.setImageResource(R.id.iv_arrow, cardInfo.isExpand() ? R.drawable.ic_arrow_up : R.drawable.ic_arrow_down);
        xBaseHolder.setOnClickListener(R.id.iv_check, new View.OnClickListener() {
            public void onClick(View view) {
                if (!cardInfo.isSelected()) {
                    Iterator it = CardSelectBinder.this.infos.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        CardInfo cardInfo = (CardInfo) it.next();
                        if (cardInfo.isSelected()) {
                            cardInfo.setSelected(false);
                            break;
                        }
                    }
                    cardInfo.setSelected(true);
                    CardSelectBinder.this.getAdapter().notifyDataSetChanged();
                }
            }
        });
        xBaseHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (CardSelectBinder.this.click != null) {
                    CardSelectBinder.this.click.onClick(cardInfo);
                }
            }
        });
        if (cardInfo.isSelected() && this.click != null) {
            this.click.onSelect(cardInfo);
        }
    }

    public void setClick(OnItemSelectClick onItemSelectClick) {
        this.click = onItemSelectClick;
    }
}

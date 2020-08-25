package cn.xports.qd2.adapter;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cn.xports.baselib.adapter.XBaseHolder;
import cn.xports.baselib.util.MoneyUtil;
import cn.xports.entity.BalanceInfo;
import cn.xports.qd2.R;
import java.util.Iterator;
import me.drakeet.multitype.ItemViewBinder;

public class CardSelectDetailBinder extends ItemViewBinder<BalanceInfo, XBaseHolder> {
    /* access modifiers changed from: private */
    public OnSelectItem onSelectItem;

    public interface OnSelectItem {
        void onItemSelect(BalanceInfo balanceInfo);
    }

    /* access modifiers changed from: protected */
    @NonNull
    public XBaseHolder onCreateViewHolder(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
        return new XBaseHolder(layoutInflater.inflate(R.layout.item_card_balance, viewGroup, false));
    }

    /* access modifiers changed from: protected */
    public void onBindViewHolder(@NonNull final XBaseHolder xBaseHolder, @NonNull final BalanceInfo balanceInfo) {
        xBaseHolder.setVisible(R.id.iv_select, !TextUtils.isEmpty(balanceInfo.getPayMode()));
        if (balanceInfo.isSelect()) {
            xBaseHolder.setVisible(R.id.tv_card_pay, true);
            int i = R.id.tv_card_pay;
            xBaseHolder.setText(i, "-" + MoneyUtil.cent2Yuan(balanceInfo.shouldPayLeft()) + "元");
        } else {
            xBaseHolder.setVisible(R.id.tv_card_pay, false);
        }
        xBaseHolder.setImageResource(R.id.iv_select, balanceInfo.isSelect() ? R.drawable.ic_checked : R.drawable.ic_uncheck);
        xBaseHolder.setText(R.id.tv_name, balanceInfo.getName());
        xBaseHolder.setText(R.id.tv_balance, "可用余额：" + balanceInfo.getBalanceInfo());
        xBaseHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (xBaseHolder.getView(R.id.iv_select).getVisibility() != 8) {
                    if (balanceInfo.isSelect()) {
                        balanceInfo.setSelect(false);
                        CardSelectDetailBinder.this.getAdapter().notifyDataSetChanged();
                        if (CardSelectDetailBinder.this.onSelectItem != null) {
                            CardSelectDetailBinder.this.onSelectItem.onItemSelect((BalanceInfo) null);
                            return;
                        }
                        return;
                    }
                    Iterator it = CardSelectDetailBinder.this.getAdapter().getItems().iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        Object next = it.next();
                        if (next instanceof BalanceInfo) {
                            BalanceInfo balanceInfo = (BalanceInfo) next;
                            if (balanceInfo.isSelect()) {
                                balanceInfo.setSelect(false);
                                break;
                            }
                        }
                    }
                    balanceInfo.setSelect(true);
                    CardSelectDetailBinder.this.getAdapter().notifyDataSetChanged();
                    if (CardSelectDetailBinder.this.onSelectItem != null) {
                        CardSelectDetailBinder.this.onSelectItem.onItemSelect(balanceInfo);
                    }
                }
            }
        });
    }

    public CardSelectDetailBinder setOnSelectItem(OnSelectItem onSelectItem2) {
        this.onSelectItem = onSelectItem2;
        return this;
    }
}

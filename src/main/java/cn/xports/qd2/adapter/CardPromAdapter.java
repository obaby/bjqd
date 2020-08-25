package cn.xports.qd2.adapter;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import cn.xports.baselib.adapter.XBaseAdapter;
import cn.xports.baselib.adapter.XBaseHolder;
import cn.xports.qd2.R;
import cn.xports.qd2.entity.CardPromotion;
import java.util.Iterator;
import java.util.List;

public class CardPromAdapter extends XBaseAdapter<CardPromotion> {
    public CardPromAdapter(List<CardPromotion> list) {
        super(list);
    }

    public int getLayoutId() {
        return R.layout.item_card_prom;
    }

    public void onBindViewHolder(@NonNull XBaseHolder xBaseHolder, int i) {
        final CardPromotion cardPromotion = (CardPromotion) this.list.get(i);
        String str = "";
        if (cardPromotion.getGifts() != null && cardPromotion.getGifts().size() > 0) {
            for (CardPromotion.Gift productName : cardPromotion.getGifts()) {
                str = str + productName.getProductName() + "；";
            }
        }
        if (cardPromotion.getBuyGifts() != null) {
            CardPromotion.BuyGift buyGifts = cardPromotion.getBuyGifts();
            if ("2".equals(buyGifts.getLimitTag())) {
                try {
                    str = str + (((float) Integer.valueOf(buyGifts.getGiftValue()).intValue()) / 100.0f) + "元";
                } catch (Exception unused) {
                }
            } else if ("d".equals(buyGifts.getUnit())) {
                str = str + Integer.valueOf(buyGifts.getGiftValue()) + "天";
            } else {
                str = str + Integer.valueOf(buyGifts.getGiftValue()) + "月";
            }
        }
        boolean z = true;
        xBaseHolder.setVisible(R.id.tv_give, !TextUtils.isEmpty(str));
        xBaseHolder.setImageResource(R.id.iv_select, cardPromotion.isSelect() ? R.drawable.ic_checked : R.drawable.ic_uncheck);
        xBaseHolder.setText(R.id.tv_give_content, str);
        xBaseHolder.setText(R.id.tv_name, cardPromotion.getPromName());
        String str2 = "";
        if (cardPromotion.getCoupons() != null && cardPromotion.getCoupons().size() > 0) {
            for (CardPromotion.Coupon couponName : cardPromotion.getCoupons()) {
                str2 = str2 + couponName.getCouponName() + "；";
            }
        }
        xBaseHolder.setVisible(R.id.tv_camp, !TextUtils.isEmpty(str2));
        xBaseHolder.setText(R.id.tv_camp_content, str2);
        xBaseHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (cardPromotion.isSelect()) {
                    cardPromotion.setSelect(false);
                } else {
                    Iterator it = CardPromAdapter.this.list.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        CardPromotion cardPromotion = (CardPromotion) it.next();
                        if (cardPromotion.isSelect()) {
                            cardPromotion.setSelect(false);
                            break;
                        }
                    }
                    cardPromotion.setSelect(true);
                }
                CardPromAdapter.this.notifyDataSetChanged();
            }
        });
        int i2 = R.id.v_empty;
        if (i == getItemCount() - 1) {
            z = false;
        }
        xBaseHolder.setVisible(i2, z);
    }
}

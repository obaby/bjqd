package cn.xports.qd2.adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import cn.xports.baselib.adapter.XBaseHolder;
import cn.xports.baselib.util.MoneyUtil;
import cn.xports.qd2.R;
import cn.xports.qd2.entity.K;
import cn.xports.qd2.entity.RechargeValue;
import com.blankj.utilcode.util.KeyboardUtils;
import java.util.ArrayList;
import java.util.Iterator;
import me.drakeet.multitype.ItemViewBinder;

public class RechargeValueBinder extends ItemViewBinder<RechargeValue, XBaseHolder> {
    /* access modifiers changed from: private */
    public OnGiftClickListener clickListener;
    /* access modifiers changed from: private */
    public ArrayList<RechargeValue> values;

    public interface OnGiftClickListener {
        void editInputListener(String str, int i);

        void haveGiftClick(int i);
    }

    public RechargeValueBinder(ArrayList<RechargeValue> arrayList) {
        this.values = arrayList;
    }

    /* access modifiers changed from: protected */
    @NonNull
    public XBaseHolder onCreateViewHolder(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
        return new XBaseHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recharge_value, viewGroup, false));
    }

    /* access modifiers changed from: protected */
    public void onBindViewHolder(@NonNull final XBaseHolder xBaseHolder, @NonNull final RechargeValue rechargeValue) {
        int i;
        int indexOf = this.values.indexOf(rechargeValue);
        if (indexOf == 0 || indexOf == 1 || indexOf == 2) {
            xBaseHolder.setVisible(R.id.v_empty, false);
        } else {
            xBaseHolder.setVisible(R.id.v_empty, true);
        }
        if (rechargeValue.isCanInput()) {
            xBaseHolder.setVisible(R.id.ll_input, true);
            xBaseHolder.setVisible(R.id.ll_value, false);
        } else {
            xBaseHolder.setVisible(R.id.ll_input, false);
            xBaseHolder.setVisible(R.id.ll_value, true);
            if ("其他金额".equals(rechargeValue.getValue())) {
                xBaseHolder.setText(R.id.tv_charge_value, "其他金额");
            } else {
                int i2 = R.id.tv_charge_value;
                xBaseHolder.setText(i2, MoneyUtil.cent2Yuan(Integer.valueOf(rechargeValue.getValue()).intValue()) + "元");
            }
            int i3 = R.id.tv_charge_value;
            if (rechargeValue.isSelected()) {
                i = Color.parseColor("#2e6ffc");
            } else {
                i = Color.parseColor("#353535");
            }
            xBaseHolder.setTextColor(i3, i);
            xBaseHolder.setBackgroundRes(R.id.ll_value, rechargeValue.isSelected() ? R.drawable.bg_recharge_item : R.drawable.bg_stroke_blue2e6_corner_2dp);
            xBaseHolder.setVisible(R.id.tv_promo, !TextUtils.isEmpty(rechargeValue.getPromInfo()));
            xBaseHolder.setText(R.id.tv_promo, rechargeValue.getPromInfo());
            if (!rechargeValue.getPromInfo().isEmpty()) {
                xBaseHolder.setVisible(R.id.iv_gift_tag, true);
                xBaseHolder.setImageResource(R.id.iv_gift_tag, R.drawable.ic_prom);
            } else if (rechargeValue.getRechargeCampInfo() != null) {
                xBaseHolder.setVisible(R.id.iv_gift_tag, true);
                xBaseHolder.setImageResource(R.id.iv_gift_tag, R.drawable.ic_recharge_camp);
            } else {
                xBaseHolder.setVisible(R.id.iv_gift_tag, false);
            }
        }
        xBaseHolder.setOnClickListener(R.id.ll_value, new View.OnClickListener() {
            public void onClick(View view) {
                if (!rechargeValue.isSelected() && !rechargeValue.isCanInput()) {
                    Iterator it = RechargeValueBinder.this.values.iterator();
                    while (it.hasNext()) {
                        RechargeValue rechargeValue = (RechargeValue) it.next();
                        if (rechargeValue.isCanInput()) {
                            rechargeValue.setCanInput(false);
                        } else if (rechargeValue.isSelected()) {
                            rechargeValue.setSelected(false);
                        }
                    }
                    if ("其他金额".equals(rechargeValue.getValue())) {
                        rechargeValue.setCanInput(true);
                        xBaseHolder.getView(R.id.et_input_value).requestFocus();
                        xBaseHolder.getView(R.id.et_input_value).performClick();
                        if (RechargeValueBinder.this.clickListener != null) {
                            RechargeValueBinder.this.clickListener.editInputListener((String) null, 2);
                        }
                    } else {
                        rechargeValue.setSelected(true);
                        KeyboardUtils.hideSoftInput(xBaseHolder.itemView);
                        if (RechargeValueBinder.this.clickListener != null) {
                            RechargeValueBinder.this.clickListener.editInputListener(rechargeValue.getValue(), 1);
                        }
                    }
                    if (RechargeValueBinder.this.clickListener != null) {
                        RechargeValueBinder.this.clickListener.haveGiftClick(RechargeValueBinder.this.values.indexOf(rechargeValue));
                    }
                }
            }
        });
        ((EditText) xBaseHolder.getView(R.id.et_input_value)).addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                if (RechargeValueBinder.this.clickListener != null) {
                    RechargeValueBinder.this.clickListener.editInputListener(editable == null ? K.k0 : editable.toString(), 2);
                }
            }
        });
    }

    public RechargeValueBinder setClickListener(OnGiftClickListener onGiftClickListener) {
        this.clickListener = onGiftClickListener;
        return this;
    }
}

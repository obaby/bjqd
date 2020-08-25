package cn.xports.qd2.adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.xports.baselib.adapter.XBaseHolder;
import cn.xports.qd2.R;
import me.drakeet.multitype.ItemViewBinder;

public class RechargeGiftBinder extends ItemViewBinder<String, XBaseHolder> {
    private Align align;

    public enum Align {
        LEFT,
        MID,
        RIGHT
    }

    public void setAlign(Align align2) {
        this.align = align2;
    }

    /* access modifiers changed from: protected */
    @NonNull
    public XBaseHolder onCreateViewHolder(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
        return new XBaseHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recharge_gift, viewGroup, false));
    }

    /* access modifiers changed from: protected */
    public void onBindViewHolder(@NonNull XBaseHolder xBaseHolder, @NonNull String str) {
        xBaseHolder.setText(R.id.tv_gift, str);
        xBaseHolder.setBackgroundRes(R.id.tv_gift, this.align == Align.RIGHT ? R.drawable.bg_recharge_gift_right2 : R.drawable.bg_recharge_gift2);
        TextView textView = (TextView) xBaseHolder.getView(R.id.tv_gift);
        Align align2 = this.align;
        Align align3 = Align.RIGHT;
        int i = GravityCompat.START;
        textView.setGravity(align2 == align3 ? GravityCompat.END : GravityCompat.START);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) textView.getLayoutParams();
        if (this.align == Align.RIGHT) {
            i = GravityCompat.END;
        }
        layoutParams.gravity = i;
        textView.setLayoutParams(layoutParams);
        xBaseHolder.setVisible(R.id.v_empty, this.align == Align.MID);
    }
}

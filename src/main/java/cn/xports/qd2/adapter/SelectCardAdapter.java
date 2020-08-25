package cn.xports.qd2.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import cn.xports.baselib.adapter.XBaseAdapter;
import cn.xports.baselib.adapter.XBaseHolder;
import cn.xports.entity.CardPointInfo;
import cn.xports.qd2.R;
import java.util.List;

public class SelectCardAdapter extends XBaseAdapter<CardPointInfo> {
    /* access modifiers changed from: private */
    public SelectListener listener;

    public interface SelectListener {
        void selected(int i);
    }

    public SelectCardAdapter(List<CardPointInfo> list) {
        super(list);
    }

    public int getLayoutId() {
        return R.layout.item_select_integral;
    }

    public void onBindViewHolder(@NonNull XBaseHolder xBaseHolder, final int i) {
        CardPointInfo cardPointInfo = (CardPointInfo) this.list.get(i);
        TextView textView = (TextView) xBaseHolder.getView(R.id.tv_card_id);
        TextView textView2 = (TextView) xBaseHolder.getView(R.id.tv_integral);
        RadioButton radioButton = (RadioButton) xBaseHolder.getView(R.id.rb_card);
        View view = xBaseHolder.getView(R.id.view_division);
        if (i == 0) {
            view.setVisibility(8);
        } else {
            view.setVisibility(0);
        }
        radioButton.setChecked(cardPointInfo.isSelect);
        textView.setText("一卡通" + cardPointInfo.ecardNo);
        textView2.setText(cardPointInfo.pointsBalance + "积分");
        xBaseHolder.getView(R.id.rl_item).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (SelectCardAdapter.this.listener != null) {
                    SelectCardAdapter.this.listener.selected(i);
                }
            }
        });
    }

    public void setListener(SelectListener selectListener) {
        this.listener = selectListener;
    }
}

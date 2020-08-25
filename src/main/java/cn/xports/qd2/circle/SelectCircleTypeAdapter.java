package cn.xports.qd2.circle;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;
import cn.xports.baselib.adapter.XBaseAdapter;
import cn.xports.baselib.adapter.XBaseHolder;
import cn.xports.qd2.R;
import java.util.List;

public class SelectCircleTypeAdapter extends XBaseAdapter<CircleTypeBean> {
    private Context context;
    /* access modifiers changed from: private */
    public SelectListener listener;

    public interface SelectListener {
        void selected(int i);
    }

    public SelectCircleTypeAdapter(List<CircleTypeBean> list, Context context2) {
        super(list);
        this.context = context2;
    }

    public int getLayoutId() {
        return R.layout.item_circle_tpye;
    }

    public void onBindViewHolder(@NonNull XBaseHolder xBaseHolder, final int i) {
        CircleTypeBean circleTypeBean = (CircleTypeBean) this.list.get(i);
        TextView textView = (TextView) xBaseHolder.getView(R.id.tv_type);
        textView.setText(circleTypeBean.boardName);
        if (circleTypeBean.isSelected) {
            textView.setTextColor(this.context.getResources().getColor(R.color.white));
            textView.setBackground(this.context.getResources().getDrawable(R.drawable.shape_blue_radius5));
        } else {
            textView.setTextColor(this.context.getResources().getColor(R.color.gray_737));
            textView.setBackground(this.context.getResources().getDrawable(R.drawable.shape_white_radius5_bord1_gray));
        }
        textView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (SelectCircleTypeAdapter.this.listener != null) {
                    SelectCircleTypeAdapter.this.listener.selected(i);
                }
            }
        });
    }

    public void setListener(SelectListener selectListener) {
        this.listener = selectListener;
    }
}

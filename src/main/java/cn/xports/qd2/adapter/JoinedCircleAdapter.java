package cn.xports.qd2.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import cn.xports.baselib.adapter.XBaseAdapter;
import cn.xports.baselib.adapter.XBaseHolder;
import cn.xports.baselib.bean.DataMap;
import cn.xports.baselib.util.GlideUtil;
import cn.xports.qd2.R;
import cn.xports.qd2.util.ViewExt;
import com.alipay.sdk.cons.c;
import java.util.List;

public class JoinedCircleAdapter extends XBaseAdapter<DataMap> {
    public JoinedCircleAdapter(List<DataMap> list) {
        super(list);
    }

    public int getLayoutId() {
        return R.layout.item_joined_circle;
    }

    public void onBindViewHolder(@NonNull XBaseHolder xBaseHolder, final int i) {
        String str;
        final DataMap dataMap = (DataMap) this.list.get(i);
        ViewExt.setTextMax((TextView) xBaseHolder.getView(R.id.tv_circle_name), dataMap.getString(c.e), 5);
        GlideUtil.loadImage(xBaseHolder.itemView.getContext(), dataMap.getString("avatar")).into((ImageView) xBaseHolder.getView(R.id.riv_discover));
        xBaseHolder.setVisible(R.id.tv_new_num, dataMap.getIntValue("postNew").intValue() > 0);
        int intValue = dataMap.getIntValue("postNew").intValue();
        if (intValue > 99) {
            str = "NEW " + "99+";
        } else {
            str = "NEW " + intValue;
        }
        xBaseHolder.setText(R.id.tv_new_num, str);
        xBaseHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                JoinedCircleAdapter.this.performItemClick(dataMap, i);
            }
        });
    }
}

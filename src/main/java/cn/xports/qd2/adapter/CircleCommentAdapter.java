package cn.xports.qd2.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import cn.xports.baselib.adapter.XBaseAdapter;
import cn.xports.baselib.adapter.XBaseHolder;
import cn.xports.baselib.bean.DataMap;
import cn.xports.baselib.util.GlideUtil;
import cn.xports.qd2.R;
import cn.xports.qd2.entity.K;
import cn.xports.sportCoaching.DateShowUtils;
import java.util.List;

public class CircleCommentAdapter extends XBaseAdapter<DataMap> {
    public CircleCommentAdapter(List<DataMap> list) {
        super(list);
    }

    public int getLayoutId() {
        return R.layout.item_circle_comment;
    }

    public void onBindViewHolder(@NonNull XBaseHolder xBaseHolder, final int i) {
        final DataMap dataMap = (DataMap) this.list.get(i);
        xBaseHolder.setText(R.id.tv_time, DateShowUtils.getPostFriendlyTime(dataMap.getString(K.createTime)));
        xBaseHolder.setText(R.id.tv_name, dataMap.getString("accountName"));
        xBaseHolder.setText(R.id.tv_content, dataMap.getString("content"));
        GlideUtil.loadImage(xBaseHolder.itemView.getContext(), dataMap.getString("accountAvatar"), R.drawable.ic_head_default).into((ImageView) xBaseHolder.getView(R.id.iv_head));
        xBaseHolder.setOnClickListener(R.id.iv_head, new View.OnClickListener() {
            public void onClick(View view) {
                CircleCommentAdapter.this.performItemClick(dataMap, i, 1);
            }
        });
        xBaseHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CircleCommentAdapter.this.performItemClick(dataMap, i);
            }
        });
    }
}

package cn.xports.qd2.circle;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import cn.xports.baselib.adapter.XBaseAdapter;
import cn.xports.baselib.adapter.XBaseHolder;
import cn.xports.baselib.bean.DataMap;
import cn.xports.qd2.R;
import cn.xports.qd2.circle.videoPlayer.PicUrlUtils;
import com.alipay.sdk.cons.c;
import com.bumptech.glide.Glide;
import java.util.List;

public class SelectCircleListAdapter extends XBaseAdapter<DataMap> {
    private Context context;
    private List<DataMap> list;

    public SelectCircleListAdapter(List<DataMap> list2, Context context2) {
        super(list2);
        this.list = list2;
        this.context = context2;
    }

    public int getLayoutId() {
        return R.layout.item_select_circle;
    }

    public void onBindViewHolder(@NonNull XBaseHolder xBaseHolder, final int i) {
        final DataMap dataMap = this.list.get(i);
        Glide.with(this.context).load(PicUrlUtils.getPath(dataMap.getString("avatar"))).centerCrop().into((ImageView) xBaseHolder.getView(R.id.riv_recommend));
        ((TextView) xBaseHolder.getView(R.id.tv_circle_name)).setText(dataMap.getString(c.e));
        xBaseHolder.getView(R.id.rl_item).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SelectCircleListAdapter.this.performItemClick(dataMap, i);
            }
        });
    }
}

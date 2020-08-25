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

public class MyCircleListAdapter extends XBaseAdapter<DataMap> {
    private Context context;
    private List<DataMap> list;

    public MyCircleListAdapter(List<DataMap> list2, Context context2) {
        super(list2);
        this.list = list2;
        this.context = context2;
    }

    public int getLayoutId() {
        return R.layout.item_my_circle;
    }

    public void onBindViewHolder(@NonNull XBaseHolder xBaseHolder, final int i) {
        final DataMap dataMap = this.list.get(i);
        ImageView imageView = (ImageView) xBaseHolder.getView(R.id.riv_recommend);
        TextView textView = (TextView) xBaseHolder.getView(R.id.tv_circle_name);
        TextView textView2 = (TextView) xBaseHolder.getView(R.id.tv_circle_posts_count);
        TextView textView3 = (TextView) xBaseHolder.getView(R.id.tv_circle_member_count);
        TextView textView4 = (TextView) xBaseHolder.getView(R.id.tv_circle_tag);
        View view = xBaseHolder.getView(R.id.view_division);
        if (i == 0) {
            view.setVisibility(8);
        } else {
            view.setVisibility(0);
        }
        Glide.with(this.context).load(PicUrlUtils.getPath(dataMap.getString("avatar"))).centerCrop().into(imageView);
        textView.setText(dataMap.getString(c.e));
        textView2.setText(dataMap.getString("postNum") + "帖子");
        textView3.setText(dataMap.getString("memberNum") + "成员");
        int intValue = dataMap.getInteger("postNew").intValue();
        if (intValue > 0) {
            textView4.setVisibility(0);
            textView4.setText(intValue + "条新动态");
        } else {
            textView4.setVisibility(8);
        }
        xBaseHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MyCircleListAdapter.this.performItemClick(dataMap, i);
            }
        });
    }
}

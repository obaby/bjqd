package cn.xports.qd2.circle;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import cn.xports.baselib.adapter.XBaseAdapter;
import cn.xports.baselib.adapter.XBaseHolder;
import cn.xports.baselib.bean.DataMap;
import cn.xports.baselib.util.GlideUtil;
import cn.xports.qd2.R;
import cn.xports.qd2.entity.K;
import cn.xports.sportCoaching.DateShowUtils;
import com.blankj.utilcode.util.ActivityUtils;
import java.util.List;

public class MsgInteractAdapter extends XBaseAdapter<DataMap> {
    private Context context;
    private List<DataMap> list;

    public MsgInteractAdapter(List<DataMap> list2, Context context2) {
        super(list2);
        this.list = list2;
        this.context = context2;
    }

    public int getLayoutId() {
        return R.layout.item_interact_msg;
    }

    public void onBindViewHolder(@NonNull XBaseHolder xBaseHolder, int i) {
        final DataMap dataMap = this.list.get(i);
        ImageView imageView = (ImageView) xBaseHolder.getView(R.id.iv_circle_head);
        ImageView imageView2 = (ImageView) xBaseHolder.getView(R.id.iv_head);
        ImageView imageView3 = (ImageView) xBaseHolder.getView(R.id.iv_like);
        ImageView imageView4 = (ImageView) xBaseHolder.getView(R.id.iv_video);
        TextView textView = (TextView) xBaseHolder.getView(R.id.tv_time);
        TextView textView2 = (TextView) xBaseHolder.getView(R.id.tv_desc1);
        TextView textView3 = (TextView) xBaseHolder.getView(R.id.tv_desc2);
        ((TextView) xBaseHolder.getView(R.id.tv_name)).setText(dataMap.getDataMap("commentContent").getString("accountName"));
        String string = dataMap.getDataMap("commentContent").getString(K.createTime);
        if (!TextUtils.isEmpty(string)) {
            textView.setText(DateShowUtils.getPostFriendlyTime(string));
        }
        GlideUtil.loadRoundImage(this.context, dataMap.getDataMap("commentContent").getString("accountAvatar"), R.drawable.ic_circle_default_head).into(imageView2);
        GlideUtil.loadImage(this.context, dataMap.getString("thumbMediaUrl"), R.drawable.ic_circle_default).into(imageView);
        String string2 = dataMap.getString("contentType");
        if (!TextUtils.isEmpty(string2) || !string2.equals("2")) {
            imageView4.setVisibility(8);
        } else {
            imageView4.setVisibility(0);
        }
        if (TextUtils.isEmpty(dataMap.getDataMap("commentContent").getString("commentId"))) {
            imageView3.setVisibility(0);
            textView2.setVisibility(8);
        } else {
            imageView3.setVisibility(8);
            textView2.setVisibility(0);
            textView2.setText(dataMap.getDataMap("commentContent").getString("content"));
        }
        xBaseHolder.getView(R.id.rl_item).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ActivityUtils.startActivity(new Intent(ActivityUtils.getTopActivity(), PostDetailActivity.class).putExtra("joinState", "2").putExtra("postId", dataMap.getString("postId")));
            }
        });
    }
}

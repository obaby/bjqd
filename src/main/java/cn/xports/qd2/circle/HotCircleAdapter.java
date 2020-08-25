package cn.xports.qd2.circle;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import cn.xports.baselib.adapter.XBaseAdapter;
import cn.xports.baselib.adapter.XBaseHolder;
import cn.xports.qd2.R;
import cn.xports.qd2.circle.videoPlayer.PicUrlUtils;
import com.bumptech.glide.Glide;
import java.util.List;

public class HotCircleAdapter extends XBaseAdapter<DiscoverCircleBean> {
    private Context context;
    private List<DiscoverCircleBean> list;

    public HotCircleAdapter(List<DiscoverCircleBean> list2, Context context2) {
        super(list2);
        this.list = list2;
        this.context = context2;
    }

    public int getLayoutId() {
        return R.layout.item_hot_circle;
    }

    public void onBindViewHolder(@NonNull XBaseHolder xBaseHolder, int i) {
        final DiscoverCircleBean discoverCircleBean = this.list.get(i);
        Glide.with(this.context).load(PicUrlUtils.getPath(discoverCircleBean.imgUrl)).centerCrop().into((ImageView) xBaseHolder.getView(R.id.iv_circle_head));
        ((TextView) xBaseHolder.getView(R.id.tv_circle_name)).setText(discoverCircleBean.circleName);
        ((TextView) xBaseHolder.getView(R.id.tv_circle_posts_count)).setText(discoverCircleBean.postsNum + "动态");
        ((TextView) xBaseHolder.getView(R.id.tv_circle_member_count)).setText(discoverCircleBean.memberNum + "人");
        xBaseHolder.getView(R.id.rl_root).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                HotCircleAdapter.this.startCircleDetailActivity(discoverCircleBean.circleId);
            }
        });
    }

    /* access modifiers changed from: private */
    public void startCircleDetailActivity(String str) {
        Intent intent = new Intent(this.context, CircleDetailActivity.class);
        intent.setFlags(268435456);
        intent.putExtra(CircleDetailActivity.CIRCLE_ID, str);
        this.context.startActivity(intent);
    }
}

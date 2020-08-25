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
import cn.xports.qd2.R;
import cn.xports.qd2.circle.videoPlayer.PicUrlUtils;
import com.bumptech.glide.Glide;
import java.util.List;

public class RecommendCircleAdapter extends XBaseAdapter<DiscoverCircleBean> {
    private Context context;
    private List<DiscoverCircleBean> list;

    public RecommendCircleAdapter(List<DiscoverCircleBean> list2, Context context2) {
        super(list2);
        this.list = list2;
        this.context = context2;
    }

    public int getLayoutId() {
        return R.layout.item_circle_recommend;
    }

    public void onBindViewHolder(@NonNull XBaseHolder xBaseHolder, int i) {
        final DiscoverCircleBean discoverCircleBean = this.list.get(i);
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
        Glide.with(this.context).load(PicUrlUtils.getPath(discoverCircleBean.imgUrl)).centerCrop().into(imageView);
        textView.setText(discoverCircleBean.circleName);
        textView2.setText(discoverCircleBean.postsNum + "帖子");
        textView3.setText(discoverCircleBean.memberNum + "成员");
        if (TextUtils.isEmpty(discoverCircleBean.tag)) {
            textView4.setVisibility(8);
        } else {
            textView4.setVisibility(0);
            textView4.setText(discoverCircleBean.tag);
        }
        xBaseHolder.getView(R.id.rl_item).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                RecommendCircleAdapter.this.startCircleDetailActivity(discoverCircleBean.circleId);
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

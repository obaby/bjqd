package cn.xports.sportCoaching;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import cn.xports.baselib.adapter.XBaseAdapter;
import cn.xports.baselib.adapter.XBaseHolder;
import cn.xports.qdplugin.R;
import cn.xports.util.StringUtil;
import com.bumptech.glide.Glide;
import java.util.List;

public class SportCoachAdapter extends XBaseAdapter<DateList> {
    /* access modifiers changed from: private */
    public Context context;

    public SportCoachAdapter(List<DateList> list, Context context2) {
        super(list);
        this.context = context2;
    }

    public int getLayoutId() {
        return R.layout.item_sport_coach;
    }

    public void onBindViewHolder(@NonNull XBaseHolder xBaseHolder, int i) {
        final DateList dateList = (DateList) this.list.get(i);
        ((TextView) xBaseHolder.getView(R.id.tv_title)).setText(dateList.title);
        ((TextView) xBaseHolder.getView(R.id.tv_content)).setText(StringUtil.removeRange(dateList.content, "<", ">"));
        ((TextView) xBaseHolder.getView(R.id.tv_date)).setText(DateShowUtils.getPostFriendlyTime(dateList.createTime));
        Glide.with(this.context).load(dateList.cover).centerCrop().into((ImageView) xBaseHolder.getView(R.id.iv_des_img));
        xBaseHolder.getView(R.id.rl_item).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(SportCoachAdapter.this.context, WebViewDetailActivity.class);
                intent.putExtra(WebViewDetailActivity.TITLE, dateList.title);
                intent.putExtra(WebViewDetailActivity.COUNT, dateList.showOrder + "");
                intent.putExtra(WebViewDetailActivity.DATE, DateShowUtils.dateToYYYYMMDDHHMM(dateList.createTime));
                if (TextUtils.isEmpty(dateList.contType) || !dateList.contType.equals("1")) {
                    intent.putExtra(WebViewDetailActivity.CONTENT_TYPE, 0);
                    intent.putExtra("content", dateList.outerLink);
                } else {
                    intent.putExtra(WebViewDetailActivity.CONTENT_TYPE, 1);
                    intent.putExtra("content", dateList.content);
                }
                SportCoachAdapter.this.context.startActivity(intent);
            }
        });
    }
}

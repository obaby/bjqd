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
import com.bumptech.glide.Glide;
import java.util.List;

public class SportCoachHotArtciltAdapter extends XBaseAdapter<DateList> {
    /* access modifiers changed from: private */
    public Context context;

    public SportCoachHotArtciltAdapter(List<DateList> list, Context context2) {
        super(list);
        this.context = context2;
    }

    public int getLayoutId() {
        return R.layout.item_hot_artcile;
    }

    public void onBindViewHolder(@NonNull XBaseHolder xBaseHolder, int i) {
        final DateList dateList = (DateList) this.list.get(i);
        ImageView imageView = (ImageView) xBaseHolder.getView(R.id.iv_bg_img);
        TextView textView = (TextView) xBaseHolder.getView(R.id.rl_content);
        TextView textView2 = (TextView) xBaseHolder.getView(R.id.tv_sport_content);
        TextView textView3 = (TextView) xBaseHolder.getView(R.id.tv_mark);
        if (i == 0) {
            textView.setBackground(this.context.getResources().getDrawable(R.drawable.bg_tra_radui7_blue));
            textView3.setTextColor(this.context.getResources().getColor(R.color.blue_2e6));
        } else if (i == 1) {
            textView.setBackground(this.context.getResources().getDrawable(R.drawable.bg_tra_radui7_orage));
            textView3.setTextColor(this.context.getResources().getColor(R.color.orange_fe9));
        } else if (i == 2) {
            textView.setBackground(this.context.getResources().getDrawable(R.drawable.bg_tra_radui7_green));
            textView3.setTextColor(this.context.getResources().getColor(R.color.commonsdk_emerald));
        }
        Glide.with(this.context).load(dateList.cover).centerCrop().into(imageView);
        textView2.setText(dateList.title);
        xBaseHolder.getView(R.id.rl_item).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(SportCoachHotArtciltAdapter.this.context, WebViewDetailActivity.class);
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
                SportCoachHotArtciltAdapter.this.context.startActivity(intent);
            }
        });
    }
}

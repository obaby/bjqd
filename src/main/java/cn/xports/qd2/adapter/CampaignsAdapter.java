package cn.xports.qd2.adapter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import cn.xports.baselib.adapter.XBaseAdapter;
import cn.xports.baselib.adapter.XBaseHolder;
import cn.xports.baselib.util.GlideUtil;
import cn.xports.entity.CampaignInfo;
import cn.xports.qd2.R;
import cn.xports.qd2.campaign.SportDetailActivity;
import cn.xports.qd2.entity.K;
import com.blankj.utilcode.util.ActivityUtils;
import java.util.List;

public class CampaignsAdapter extends XBaseAdapter<CampaignInfo> {
    public CampaignsAdapter(List<CampaignInfo> list) {
        super(list);
    }

    public int getLayoutId() {
        return R.layout.item_campaign;
    }

    public void onBindViewHolder(@NonNull XBaseHolder xBaseHolder, int i) {
        final CampaignInfo campaignInfo = (CampaignInfo) this.list.get(i);
        ImageView imageView = (ImageView) xBaseHolder.getView(R.id.iv_cover);
        xBaseHolder.setText(R.id.tv_name, campaignInfo.getTitle());
        xBaseHolder.setText(R.id.tv_person_num, campaignInfo.getEnrolledNum());
        xBaseHolder.setText(R.id.tv_sport_location, campaignInfo.getPlace());
        String replace = campaignInfo.getCampStartDate().substring(0, 10).replace("-", "/");
        String replace2 = campaignInfo.getCampEndDate().substring(0, 10).replace("-", "/");
        int i2 = R.id.tv_sport_date;
        xBaseHolder.setText(i2, replace + "-" + replace2);
        GlideUtil.loadImage(imageView.getContext(), campaignInfo.getCoverImg()).into(imageView);
        xBaseHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString(K.campId, campaignInfo.getCampId());
                ActivityUtils.startActivity(bundle, SportDetailActivity.class);
            }
        });
    }
}

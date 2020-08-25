package cn.xports.qd2.adapter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import cn.xports.baselib.adapter.XBaseAdapter;
import cn.xports.baselib.adapter.XBaseHolder;
import cn.xports.baselib.util.GlideUtil;
import cn.xports.qd2.R;
import cn.xports.qd2.campaign.JoinedSportsDetailActivity;
import cn.xports.qd2.entity.CampEnrollment;
import cn.xports.qd2.entity.K;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ColorUtils;
import com.blankj.utilcode.util.SpanUtils;
import com.blankj.utilcode.util.TimeUtils;
import java.util.List;
import java.util.Objects;

public class CampEnrolledAdapter extends XBaseAdapter<CampEnrollment> {
    public CampEnrolledAdapter(List<CampEnrollment> list) {
        super(list);
    }

    public int getLayoutId() {
        return R.layout.item_my_sports;
    }

    public void onBindViewHolder(@NonNull XBaseHolder xBaseHolder, int i) {
        final int i2;
        final String str;
        String str2;
        final CampEnrollment campEnrollment = (CampEnrollment) this.list.get(i);
        String title = campEnrollment.getTitle();
        title.length();
        int color = ColorUtils.getColor(R.color.green_34d);
        if (Objects.equals(campEnrollment.getCancelTag(), "1") || Objects.equals(campEnrollment.getStatus(), "2")) {
            str = "    " + "已取消";
            i2 = ColorUtils.getColor(R.color.gray_cbc);
        } else if (!Objects.equals(campEnrollment.getExpireTag(), "1") || !Objects.equals(campEnrollment.getStatus(), K.k0)) {
            if (Objects.equals(campEnrollment.getExpireTag(), K.k0) && Objects.equals(campEnrollment.getStatus(), K.k0)) {
                str2 = "    " + "报名中";
            } else if (Objects.equals(campEnrollment.getStatus(), "1") && Objects.equals(campEnrollment.getCampOngoingTag(), K.k0)) {
                str2 = "    " + "报名中";
            } else if (Objects.equals(campEnrollment.getStatus(), "1") && Objects.equals(campEnrollment.getCampOngoingTag(), "1")) {
                str = "    " + "进行中";
                i2 = ColorUtils.getColor(R.color.blue_2e6);
            } else if (TimeUtils.getTimeSpanByNow(campEnrollment.getCampEndDate(), 1000) < 0) {
                str = "    " + "已结束";
                i2 = ColorUtils.getColor(R.color.gray_cbc);
            } else {
                str2 = "";
            }
            String str3 = str2;
            i2 = color;
            str = str3;
        } else {
            str = "    " + "支付超时";
            i2 = ColorUtils.getColor(R.color.red_fd4);
        }
        new SpannableString(title + str);
        new BackgroundColorSpan(ColorUtils.getColor(R.color.red_fd4));
        SpanUtils.with((TextView) xBaseHolder.getView(R.id.tv_name)).append(title).append(str).setFontSize(10, true).setForegroundColor(i2).create();
        String campStartDate = campEnrollment.getCampStartDate();
        if (campStartDate != null && campStartDate.length() >= 10) {
            campStartDate = campStartDate.substring(0, 10);
        }
        String campEndDate = campEnrollment.getCampEndDate();
        if (campEndDate != null && campEndDate.length() >= 10) {
            campEndDate = campEndDate.substring(0, 10);
        }
        String str4 = "";
        if (!(campStartDate == null || campEndDate == null)) {
            str4 = campStartDate.replace("-", "/") + " - " + campEndDate.replace("-", "/");
        }
        xBaseHolder.setText(R.id.tv_sport_date, str4);
        xBaseHolder.setText(R.id.tv_sport_location, campEnrollment.getPlace());
        ImageView imageView = (ImageView) xBaseHolder.getView(R.id.iv_cover);
        GlideUtil.loadImage(imageView.getContext(), campEnrollment.getCoverImg()).into(imageView);
        xBaseHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString(K.enrollId, campEnrollment.getEnrollId());
                bundle.putString("ex_state", str);
                bundle.putString(K.campId, campEnrollment.getCampId());
                bundle.putInt("bg_state_color", i2);
                bundle.putBoolean("readTag", Objects.equals(campEnrollment.getReadonlyTag(), "1"));
                ActivityUtils.startActivity(bundle, JoinedSportsDetailActivity.class);
            }
        });
    }
}

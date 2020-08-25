package cn.xports.qd2.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import cn.xports.baselib.adapter.XBaseHolder;
import cn.xports.baselib.adapter.XViewBinder;
import cn.xports.baselib.bean.DataMap;
import cn.xports.qd2.R;
import cn.xports.qd2.entity.K;
import com.blankj.utilcode.util.ColorUtils;
import com.blankj.utilcode.util.TimeUtils;
import java.text.SimpleDateFormat;

public class MyAppointBinder extends XViewBinder<DataMap> {
    /* access modifiers changed from: protected */
    public void onBindViewHolder(@NonNull XBaseHolder xBaseHolder, @NonNull final DataMap dataMap) {
        String string = dataMap.getString(K.lessonStart, "0000");
        xBaseHolder.setText(R.id.tv_start_time, new StringBuilder(string).insert(2, ":").toString());
        xBaseHolder.setText(R.id.tv_course_name, dataMap.getString(K.courseName));
        xBaseHolder.setText(R.id.tv_place, dataMap.getString(K.placeName));
        String string2 = dataMap.getString(K.lessonEnd, "0000");
        if (((int) TimeUtils.getTimeSpanByNow(dataMap.getString(K.lessonDate, "0000-00-00").substring(0, 10) + " " + string2, new SimpleDateFormat("yyyy-MM-dd HHmm"), 60000)) <= 0) {
            xBaseHolder.setImageResource(R.id.iv_clock, R.drawable.ic_time_clock);
            xBaseHolder.setText(R.id.tv_time_long, "已结束");
            dataMap.set("hasFinish", true);
            xBaseHolder.setTextColor(R.id.tv_time_long, ColorUtils.getColor(R.color.second_text_color));
        } else {
            int i = R.id.tv_time_long;
            xBaseHolder.setText(i, "时长：" + ((int) TimeUtils.getTimeSpan(string2, string, new SimpleDateFormat("HHmm"), 60000)) + "分钟");
            xBaseHolder.setImageResource(R.id.iv_clock, R.drawable.ic_clock_blue);
            xBaseHolder.setTextColor(R.id.tv_time_long, ColorUtils.getColor(R.color.blue_2e6));
        }
        xBaseHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MyAppointBinder.this.performItemClick(dataMap);
            }
        });
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.item_my_appoint;
    }
}

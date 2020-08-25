package cn.xports.qd2.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import cn.xports.baselib.adapter.XBaseAdapter;
import cn.xports.baselib.adapter.XBaseHolder;
import cn.xports.qd2.R;
import cn.xports.qd2.entity.DateWeekday;
import com.blankj.utilcode.util.ColorUtils;
import java.util.Iterator;
import java.util.List;

public class CourseWeekdayAdapter extends XBaseAdapter<DateWeekday> {
    public CourseWeekdayAdapter(List<DateWeekday> list) {
        super(list);
    }

    public int getLayoutId() {
        return R.layout.item_course_weekday;
    }

    public void onBindViewHolder(@NonNull XBaseHolder xBaseHolder, final int i) {
        final DateWeekday dateWeekday = (DateWeekday) this.list.get(i);
        if (dateWeekday.isSelect()) {
            xBaseHolder.setBackgroundRes(R.id.rl_container, R.drawable.bg_blue_2e6_corner_5dp);
            xBaseHolder.setTextColor(R.id.tv_week, -1);
            xBaseHolder.setTextColor(R.id.tv_day, -1);
        } else {
            xBaseHolder.setBackgroundRes(R.id.rl_container, R.drawable.stroke_1px_e6e_corner_5dp);
            xBaseHolder.setTextColor(R.id.tv_week, ColorUtils.getColor(R.color.main_text_color));
            xBaseHolder.setTextColor(R.id.tv_day, ColorUtils.getColor(R.color.main_text_color));
        }
        xBaseHolder.setText(R.id.tv_week, dateWeekday.getWeekday());
        xBaseHolder.setText(R.id.tv_day, dateWeekday.getDayOfMonth());
        if (dateWeekday.isToday()) {
            xBaseHolder.setText(R.id.tv_week, "今天");
        }
        xBaseHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!dateWeekday.isSelect()) {
                    Iterator it = CourseWeekdayAdapter.this.list.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        DateWeekday dateWeekday = (DateWeekday) it.next();
                        if (dateWeekday.isSelect()) {
                            dateWeekday.setSelect(false);
                            break;
                        }
                    }
                    dateWeekday.setSelect(true);
                    CourseWeekdayAdapter.this.notifyDataSetChanged();
                    CourseWeekdayAdapter.this.performItemClick(dateWeekday, i);
                }
            }
        });
    }
}

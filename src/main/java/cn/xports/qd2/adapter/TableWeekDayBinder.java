package cn.xports.qd2.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import cn.xports.baselib.adapter.XBaseHolder;
import cn.xports.baselib.adapter.XViewBinder;
import cn.xports.qd2.R;
import cn.xports.qd2.entity.DateWeekday;
import com.blankj.utilcode.util.ColorUtils;
import java.util.Iterator;

public class TableWeekDayBinder extends XViewBinder<DateWeekday> {
    /* access modifiers changed from: protected */
    public void onBindViewHolder(@NonNull XBaseHolder xBaseHolder, @NonNull final DateWeekday dateWeekday) {
        if (dateWeekday.isSelect()) {
            xBaseHolder.setBackgroundRes(R.id.rl_container, R.drawable.bg_blue_2e6_corner_5dp);
            xBaseHolder.setTextColor(R.id.tv_week, -1);
            xBaseHolder.setTextColor(R.id.tv_day, -1);
        } else {
            xBaseHolder.setBackgroundColor(R.id.rl_container, -1);
            xBaseHolder.setTextColor(R.id.tv_week, ColorUtils.getColor(R.color.main_text_color));
            xBaseHolder.setTextColor(R.id.tv_day, ColorUtils.getColor(R.color.main_text_color));
        }
        xBaseHolder.setText(R.id.tv_week, dateWeekday.getWeekday());
        xBaseHolder.setText(R.id.tv_day, dateWeekday.getDayOfMonth());
        xBaseHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!dateWeekday.isSelect()) {
                    Iterator it = TableWeekDayBinder.this.getAdapter().getItems().iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        Object next = it.next();
                        if (next instanceof DateWeekday) {
                            DateWeekday dateWeekday = (DateWeekday) next;
                            if (dateWeekday.isSelect()) {
                                dateWeekday.setSelect(false);
                                break;
                            }
                        }
                    }
                    dateWeekday.setSelect(true);
                    TableWeekDayBinder.this.getAdapter().notifyDataSetChanged();
                    TableWeekDayBinder.this.performItemClick(dateWeekday);
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.item_weekday2;
    }
}

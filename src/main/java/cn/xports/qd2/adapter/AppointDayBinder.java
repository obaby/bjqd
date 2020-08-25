package cn.xports.qd2.adapter;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.xports.baselib.adapter.XBaseHolder;
import cn.xports.qd2.R;
import cn.xports.qd2.entity.DateWeekday;
import com.blankj.utilcode.util.ColorUtils;
import me.drakeet.multitype.ItemViewBinder;

public class AppointDayBinder extends ItemViewBinder<DateWeekday, XBaseHolder> {
    /* access modifiers changed from: protected */
    @NonNull
    public XBaseHolder onCreateViewHolder(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
        return new XBaseHolder(layoutInflater.inflate(R.layout.item_my_appoint_date, viewGroup, false));
    }

    /* access modifiers changed from: protected */
    public void onBindViewHolder(@NonNull XBaseHolder xBaseHolder, @NonNull DateWeekday dateWeekday) {
        xBaseHolder.setVisible(R.id.tv_today, dateWeekday.isToday());
        xBaseHolder.setVisible(R.id.iv_table, !dateWeekday.isToday());
        TextView textView = (TextView) xBaseHolder.getView(R.id.tv_date);
        if (dateWeekday.isToday()) {
            textView.setTextSize(17.0f);
            textView.setTextColor(ColorUtils.getColor(R.color.main_text_color));
        } else {
            textView.setTextSize(14.0f);
            textView.setTextColor(ColorUtils.getColor(R.color.second_text_color));
        }
        String date = dateWeekday.getDate();
        if (date != null && date.length() >= 10) {
            date = date.substring(0, 10).replace("-", "/");
        }
        textView.setText(date + " " + dateWeekday.getWeekday());
    }
}

package cn.xports.field;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.xports.entity.BookingDay;
import cn.xports.qdplugin.R;

public class TabAdapter {
    public static int FIELD_TAB_TYPE = 1;
    public static int TICKET_TAB_TYPE;
    private Context mContext;
    private TextView mDate;
    private int mTabTextType;
    private TextView mWeekDay;

    public TabAdapter(Context context, int i) {
        this.mContext = context;
        this.mTabTextType = i;
    }

    public View getView(BookingDay bookingDay) {
        View view = null;
        if (this.mTabTextType == FIELD_TAB_TYPE) {
            view = LayoutInflater.from(this.mContext).inflate(R.layout.item_tab_field, (ViewGroup) null);
        }
        this.mWeekDay = (TextView) view.findViewById(R.id.tab_weekday);
        this.mDate = (TextView) view.findViewById(R.id.tab_date);
        this.mDate.setText(bookingDay.getDay().substring(bookingDay.getDay().indexOf("月") + 1));
        this.mWeekDay.setText(bookingDay.getWeek());
        return view;
    }
}

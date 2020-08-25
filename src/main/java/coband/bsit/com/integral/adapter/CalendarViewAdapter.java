package coband.bsit.com.integral.adapter;

import android.view.View;
import android.widget.TextView;
import coband.bsit.com.integral.bean.DateBean;

public interface CalendarViewAdapter {
    TextView[] convertView(View view, DateBean dateBean);
}

package coband.bsit.com.integral.widget;

import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import coband.bsit.com.integral.adapter.CalendarViewAdapter;
import coband.bsit.com.integral.bean.AttrsBean;
import coband.bsit.com.integral.utils.CalendarUtil;
import coband.bsit.com.integral.utils.SolarUtil;
import java.util.LinkedList;

public class CalendarPagerAdapter extends PagerAdapter {
    private LinkedList<MonthView> cache = new LinkedList<>();
    private CalendarViewAdapter calendarViewAdapter;
    private int count;
    private int item_layout;
    private AttrsBean mAttrsBean;
    private SparseArray<MonthView> mViews = new SparseArray<>();

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public CalendarPagerAdapter(int i) {
        this.count = i;
    }

    public int getCount() {
        return this.count;
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        MonthView monthView;
        if (!this.cache.isEmpty()) {
            monthView = this.cache.removeFirst();
        } else {
            monthView = new MonthView(viewGroup.getContext());
        }
        int[] positionToDate = CalendarUtil.positionToDate(i, this.mAttrsBean.getStartDate()[0], this.mAttrsBean.getStartDate()[1]);
        monthView.setAttrsBean(this.mAttrsBean);
        monthView.setOnCalendarViewAdapter(this.item_layout, this.calendarViewAdapter);
        monthView.setDateList(CalendarUtil.getMonthDate(positionToDate[0], positionToDate[1], this.mAttrsBean.getSpecifyMap()), SolarUtil.getMonthDays(positionToDate[0], positionToDate[1]));
        this.mViews.put(i, monthView);
        viewGroup.addView(monthView);
        return monthView;
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        MonthView monthView = (MonthView) obj;
        viewGroup.removeView(monthView);
        this.cache.addLast(monthView);
        this.mViews.remove(i);
    }

    public SparseArray<MonthView> getViews() {
        return this.mViews;
    }

    public void setAttrsBean(AttrsBean attrsBean) {
        this.mAttrsBean = attrsBean;
    }

    public void setOnCalendarViewAdapter(int i, CalendarViewAdapter calendarViewAdapter2) {
        this.item_layout = i;
        this.calendarViewAdapter = calendarViewAdapter2;
    }
}

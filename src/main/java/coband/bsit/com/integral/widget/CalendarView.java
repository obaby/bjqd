package coband.bsit.com.integral.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import coband.bsit.com.integral.R;
import coband.bsit.com.integral.adapter.CalendarViewAdapter;
import coband.bsit.com.integral.bean.AttrsBean;
import coband.bsit.com.integral.bean.DateBean;
import coband.bsit.com.integral.listener.OnMultiChooseListener;
import coband.bsit.com.integral.listener.OnPagerChangeListener;
import coband.bsit.com.integral.listener.OnSingleChooseListener;
import coband.bsit.com.integral.utils.CalendarUtil;
import coband.bsit.com.integral.utils.SolarUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class CalendarView extends ViewPager {
    private CalendarPagerAdapter calendarPagerAdapter;
    private CalendarViewAdapter calendarViewAdapter;
    private SparseArray<HashSet<Integer>> chooseDate;
    private int count;
    /* access modifiers changed from: private */
    public int currentPosition;
    private int[] endDate;
    private int[] initDate;
    private int item_layout;
    /* access modifiers changed from: private */
    public int[] lastClickDate;
    private AttrsBean mAttrsBean;
    private OnMultiChooseListener multiChooseListener;
    /* access modifiers changed from: private */
    public OnPagerChangeListener pagerChangeListener;
    private Set<Integer> positions;
    private OnSingleChooseListener singleChooseListener;
    /* access modifiers changed from: private */
    public int[] startDate;

    public CalendarView(Context context) {
        this(context, (AttributeSet) null);
    }

    public CalendarView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.lastClickDate = new int[2];
        this.mAttrsBean = new AttrsBean();
        initAttr(context, attributeSet);
    }

    private void initAttr(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CalendarView);
        for (int i = 0; i < obtainStyledAttributes.getIndexCount(); i++) {
            int index = obtainStyledAttributes.getIndex(i);
            if (index == R.styleable.CalendarView_show_last_next) {
                this.mAttrsBean.setShowLastNext(obtainStyledAttributes.getBoolean(index, true));
            } else if (index == R.styleable.CalendarView_show_lunar) {
                this.mAttrsBean.setShowLunar(obtainStyledAttributes.getBoolean(index, true));
            } else if (index == R.styleable.CalendarView_show_holiday) {
                this.mAttrsBean.setShowHoliday(obtainStyledAttributes.getBoolean(index, true));
            } else if (index == R.styleable.CalendarView_show_term) {
                this.mAttrsBean.setShowTerm(obtainStyledAttributes.getBoolean(index, true));
            } else if (index == R.styleable.CalendarView_switch_choose) {
                this.mAttrsBean.setSwitchChoose(obtainStyledAttributes.getBoolean(index, true));
            } else if (index == R.styleable.CalendarView_solar_color) {
                this.mAttrsBean.setColorSolar(obtainStyledAttributes.getColor(index, this.mAttrsBean.getColorSolar()));
            } else if (index == R.styleable.CalendarView_solar_size) {
                this.mAttrsBean.setSizeSolar(CalendarUtil.getTextSize(context, obtainStyledAttributes.getInteger(index, this.mAttrsBean.getSizeSolar())));
            } else if (index == R.styleable.CalendarView_lunar_color) {
                this.mAttrsBean.setColorLunar(obtainStyledAttributes.getColor(index, this.mAttrsBean.getColorLunar()));
            } else if (index == R.styleable.CalendarView_lunar_size) {
                this.mAttrsBean.setSizeLunar(CalendarUtil.getTextSize(context, obtainStyledAttributes.getInt(index, this.mAttrsBean.getSizeLunar())));
            } else if (index == R.styleable.CalendarView_holiday_color) {
                this.mAttrsBean.setColorHoliday(obtainStyledAttributes.getColor(index, this.mAttrsBean.getColorHoliday()));
            } else if (index == R.styleable.CalendarView_choose_color) {
                this.mAttrsBean.setColorChoose(obtainStyledAttributes.getColor(index, this.mAttrsBean.getColorChoose()));
            } else if (index == R.styleable.CalendarView_day_bg) {
                this.mAttrsBean.setDayBg(obtainStyledAttributes.getResourceId(index, this.mAttrsBean.getDayBg()));
            } else if (index == R.styleable.CalendarView_choose_type) {
                this.mAttrsBean.setChooseType(obtainStyledAttributes.getInt(index, 0));
            }
        }
        obtainStyledAttributes.recycle();
        this.startDate = new int[]{1900, 1};
        this.endDate = new int[]{2049, 12};
        this.mAttrsBean.setStartDate(this.startDate);
        this.mAttrsBean.setEndDate(this.endDate);
    }

    public void init() {
        int[] singleDate;
        this.count = ((((this.endDate[0] - this.startDate[0]) * 12) + this.endDate[1]) - this.startDate[1]) + 1;
        this.calendarPagerAdapter = new CalendarPagerAdapter(this.count);
        this.calendarPagerAdapter.setAttrsBean(this.mAttrsBean);
        this.calendarPagerAdapter.setOnCalendarViewAdapter(this.item_layout, this.calendarViewAdapter);
        setAdapter(this.calendarPagerAdapter);
        this.currentPosition = CalendarUtil.dateToPosition(this.initDate[0], this.initDate[1], this.startDate[0], this.startDate[1]);
        if (this.mAttrsBean.getChooseType() == 0 && (singleDate = this.mAttrsBean.getSingleDate()) != null) {
            this.lastClickDate[0] = CalendarUtil.dateToPosition(singleDate[0], singleDate[1], this.startDate[0], this.startDate[1]);
            this.lastClickDate[1] = singleDate[2];
        }
        if (this.mAttrsBean.getChooseType() == 1) {
            this.positions = new HashSet();
            this.chooseDate = new SparseArray<>();
            if (this.mAttrsBean.getMultiDates() != null) {
                for (int[] next : this.mAttrsBean.getMultiDates()) {
                    if (isIllegal(next)) {
                        int dateToPosition = CalendarUtil.dateToPosition(next[0], next[1], this.startDate[0], this.startDate[1]);
                        this.positions.add(Integer.valueOf(dateToPosition));
                        setChooseDate(next[2], true, dateToPosition);
                    }
                }
            }
        }
        setCurrentItem(this.currentPosition, false);
        addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            public void onPageSelected(int i) {
                CalendarView.this.refreshMonthView(i);
                int unused = CalendarView.this.currentPosition = i;
                if (CalendarView.this.pagerChangeListener != null) {
                    int[] positionToDate = CalendarUtil.positionToDate(i, CalendarView.this.startDate[0], CalendarView.this.startDate[1]);
                    CalendarView.this.pagerChangeListener.onPagerChanged(new int[]{positionToDate[0], positionToDate[1], CalendarView.this.lastClickDate[1]});
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        MonthView monthView;
        super.onMeasure(i, i2);
        if (getAdapter() != null && (monthView = (MonthView) getChildAt(0)) != null) {
            setMeasuredDimension(i, View.MeasureSpec.makeMeasureSpec(monthView.getMeasuredHeight(), 1073741824));
        }
    }

    /* access modifiers changed from: private */
    public void refreshMonthView(int i) {
        MonthView monthView = this.calendarPagerAdapter.getViews().get(i);
        if (monthView == null) {
            return;
        }
        if (this.mAttrsBean.getChooseType() != 1) {
            boolean z = false;
            if ((!this.mAttrsBean.isSwitchChoose() && this.lastClickDate[0] == i) || this.mAttrsBean.isSwitchChoose()) {
                z = true;
            }
            monthView.refresh(this.lastClickDate[1], z);
        } else if (this.chooseDate.get(i) != null) {
            monthView.multiChooseRefresh(this.chooseDate.get(i));
        }
    }

    public void setLastClickDay(int i) {
        this.lastClickDate[0] = this.currentPosition;
        this.lastClickDate[1] = i;
    }

    public void setChooseDate(int i, boolean z, int i2) {
        if (i2 == -1) {
            i2 = this.currentPosition;
        }
        HashSet hashSet = this.chooseDate.get(i2);
        if (z) {
            if (hashSet == null) {
                hashSet = new HashSet();
                this.chooseDate.put(i2, hashSet);
            }
            hashSet.add(Integer.valueOf(i));
            this.positions.add(Integer.valueOf(i2));
            return;
        }
        hashSet.remove(Integer.valueOf(i));
    }

    private boolean isIllegal(int[] iArr) {
        if (iArr[1] > 12 || iArr[1] < 1 || CalendarUtil.dateToMillis(iArr) < CalendarUtil.dateToMillis(this.startDate) || CalendarUtil.dateToMillis(iArr) > CalendarUtil.dateToMillis(this.endDate) || iArr[2] > SolarUtil.getMonthDays(iArr[0], iArr[1]) || iArr[2] < 1) {
            return false;
        }
        if (this.mAttrsBean.getDisableStartDate() == null || CalendarUtil.dateToMillis(iArr) >= CalendarUtil.dateToMillis(this.mAttrsBean.getDisableStartDate())) {
            return this.mAttrsBean.getDisableEndDate() == null || CalendarUtil.dateToMillis(iArr) <= CalendarUtil.dateToMillis(this.mAttrsBean.getDisableEndDate());
        }
        return false;
    }

    public void setOnSingleChooseListener(OnSingleChooseListener onSingleChooseListener) {
        this.singleChooseListener = onSingleChooseListener;
    }

    public OnMultiChooseListener getMultiChooseListener() {
        return this.multiChooseListener;
    }

    public void setOnMultiChooseListener(OnMultiChooseListener onMultiChooseListener) {
        this.multiChooseListener = onMultiChooseListener;
    }

    public OnSingleChooseListener getSingleChooseListener() {
        return this.singleChooseListener;
    }

    public void setOnPagerChangeListener(OnPagerChangeListener onPagerChangeListener) {
        this.pagerChangeListener = onPagerChangeListener;
    }

    public CalendarView setOnCalendarViewAdapter(int i, CalendarViewAdapter calendarViewAdapter2) {
        this.item_layout = i;
        this.calendarViewAdapter = calendarViewAdapter2;
        return this;
    }

    public void today() {
        int dateToPosition = CalendarUtil.dateToPosition(CalendarUtil.getCurrentDate()[0], CalendarUtil.getCurrentDate()[1], this.startDate[0], this.startDate[1]);
        this.lastClickDate[0] = dateToPosition;
        this.lastClickDate[1] = CalendarUtil.getCurrentDate()[2];
        if (dateToPosition == this.currentPosition) {
            refreshMonthView(dateToPosition);
        } else {
            setCurrentItem(dateToPosition, false);
        }
    }

    public boolean toSpecifyDate(int i, int i2, int i3) {
        if (!isIllegal(new int[]{i, i2, i3})) {
            return false;
        }
        toDestDate(i, i2, i3);
        return true;
    }

    private void toDestDate(int i, int i2, int i3) {
        int dateToPosition = CalendarUtil.dateToPosition(i, i2, this.startDate[0], this.startDate[1]);
        if (!this.mAttrsBean.isSwitchChoose() && i3 != 0) {
            this.lastClickDate[0] = dateToPosition;
        }
        int[] iArr = this.lastClickDate;
        if (i3 == 0) {
            i3 = this.lastClickDate[1];
        }
        iArr[1] = i3;
        if (dateToPosition == this.currentPosition) {
            refreshMonthView(dateToPosition);
        } else {
            setCurrentItem(dateToPosition, false);
        }
    }

    public void nextMonth() {
        if (this.currentPosition < this.count - 1) {
            int i = this.currentPosition + 1;
            this.currentPosition = i;
            setCurrentItem(i, false);
        }
    }

    public void lastMonth() {
        if (this.currentPosition > 0) {
            int i = this.currentPosition - 1;
            this.currentPosition = i;
            setCurrentItem(i, false);
        }
    }

    public void lastYear() {
        if (this.currentPosition - 12 >= 0) {
            int i = this.currentPosition - 12;
            this.currentPosition = i;
            setCurrentItem(i, false);
        }
    }

    public void nextYear() {
        if (this.currentPosition + 12 <= this.count) {
            int i = this.currentPosition + 12;
            this.currentPosition = i;
            setCurrentItem(i, false);
        }
    }

    public void toStart() {
        toDestDate(this.startDate[0], this.startDate[1], 0);
    }

    public void toEnd() {
        toDestDate(this.endDate[0], this.endDate[1], 0);
    }

    public CalendarView setSpecifyMap(HashMap<String, String> hashMap) {
        this.mAttrsBean.setSpecifyMap(hashMap);
        return this;
    }

    public CalendarView setInitDate(String str) {
        this.initDate = CalendarUtil.strToArray(str);
        return this;
    }

    public CalendarView setStartEndDate(String str, String str2) {
        this.startDate = CalendarUtil.strToArray(str);
        if (str == null) {
            this.startDate = new int[]{1900, 1};
        }
        this.endDate = CalendarUtil.strToArray(str2);
        if (str2 == null) {
            this.endDate = new int[]{2100, 12};
        }
        this.mAttrsBean.setStartDate(this.startDate);
        this.mAttrsBean.setEndDate(this.endDate);
        return this;
    }

    public CalendarView setMultiDate(List<String> list) {
        ArrayList arrayList = new ArrayList();
        for (String strToArray : list) {
            int[] strToArray2 = CalendarUtil.strToArray(strToArray);
            if (isIllegal(strToArray2)) {
                arrayList.add(strToArray2);
            }
        }
        this.mAttrsBean.setMultiDates(arrayList);
        return this;
    }

    public CalendarView setSingleDate(String str) {
        int[] strToArray = CalendarUtil.strToArray(str);
        if (!isIllegal(strToArray)) {
            strToArray = null;
        }
        this.mAttrsBean.setSingleDate(strToArray);
        return this;
    }

    public CalendarView setDisableStartEndDate(String str, String str2) {
        this.mAttrsBean.setDisableStartDate(CalendarUtil.strToArray(str));
        this.mAttrsBean.setDisableEndDate(CalendarUtil.strToArray(str2));
        return this;
    }

    public DateBean getSingleDate() {
        int[] positionToDate = CalendarUtil.positionToDate(this.lastClickDate[0], this.startDate[0], this.startDate[1]);
        return CalendarUtil.getDateBean(positionToDate[0], positionToDate[1], this.lastClickDate[1]);
    }

    public List<DateBean> getMultiDate() {
        ArrayList arrayList = new ArrayList();
        for (Integer next : this.positions) {
            HashSet hashSet = this.chooseDate.get(next.intValue());
            if (hashSet.size() > 0) {
                int[] positionToDate = CalendarUtil.positionToDate(next.intValue(), this.startDate[0], this.startDate[1]);
                Iterator it = hashSet.iterator();
                while (it.hasNext()) {
                    arrayList.add(CalendarUtil.getDateBean(positionToDate[0], positionToDate[1], ((Integer) it.next()).intValue()));
                }
            }
        }
        return arrayList;
    }
}

package coband.bsit.com.integral.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import coband.bsit.com.integral.R;
import coband.bsit.com.integral.adapter.CalendarViewAdapter;
import coband.bsit.com.integral.bean.AttrsBean;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MonthView extends ViewGroup {
    private static final int COLOR_RESET = 0;
    private static final int COLOR_SET = 1;
    private static final int COLUMN = 7;
    private static final int ROW = 6;
    private CalendarViewAdapter calendarViewAdapter;
    private Set<Integer> chooseDays;
    private int currentMonthDays;
    private int item_layout;
    private View lastClickedView;
    private int lastMonthDays;
    private AttrsBean mAttrsBean;
    private Context mContext;
    private int nextMonthDays;

    public MonthView(Context context) {
        this(context, (AttributeSet) null);
    }

    public MonthView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        this.chooseDays = new HashSet();
        this.mContext = context;
        setBackgroundColor(-1);
    }

    /* JADX WARNING: type inference failed for: r6v3, types: [android.view.View] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setDateList(java.util.List<coband.bsit.com.integral.bean.DateBean> r13, int r14) {
        /*
            r12 = this;
            int r0 = r12.getChildCount()
            if (r0 <= 0) goto L_0x0009
            r12.removeAllViews()
        L_0x0009:
            r0 = 0
            r12.lastMonthDays = r0
            r12.nextMonthDays = r0
            java.util.Set<java.lang.Integer> r1 = r12.chooseDays
            r1.clear()
            r12.currentMonthDays = r14
            r14 = 0
            r1 = 0
        L_0x0017:
            int r2 = r13.size()
            if (r14 >= r2) goto L_0x02e0
            java.lang.Object r2 = r13.get(r14)
            coband.bsit.com.integral.bean.DateBean r2 = (coband.bsit.com.integral.bean.DateBean) r2
            int r3 = r2.getType()
            r4 = 1
            if (r3 != 0) goto L_0x0043
            int r3 = r12.lastMonthDays
            int r3 = r3 + r4
            r12.lastMonthDays = r3
            coband.bsit.com.integral.bean.AttrsBean r3 = r12.mAttrsBean
            boolean r3 = r3.isShowLastNext()
            if (r3 != 0) goto L_0x0043
            android.view.View r2 = new android.view.View
            android.content.Context r3 = r12.mContext
            r2.<init>(r3)
            r12.addView(r2, r14)
            goto L_0x02dc
        L_0x0043:
            int r3 = r2.getType()
            r5 = 2
            if (r3 != r5) goto L_0x0063
            int r3 = r12.nextMonthDays
            int r3 = r3 + r4
            r12.nextMonthDays = r3
            coband.bsit.com.integral.bean.AttrsBean r3 = r12.mAttrsBean
            boolean r3 = r3.isShowLastNext()
            if (r3 != 0) goto L_0x0063
            android.view.View r2 = new android.view.View
            android.content.Context r3 = r12.mContext
            r2.<init>(r3)
            r12.addView(r2, r14)
            goto L_0x02dc
        L_0x0063:
            int r3 = r12.item_layout
            r6 = 0
            if (r3 == 0) goto L_0x0083
            coband.bsit.com.integral.adapter.CalendarViewAdapter r3 = r12.calendarViewAdapter
            if (r3 == 0) goto L_0x0083
            android.content.Context r3 = r12.mContext
            android.view.LayoutInflater r3 = android.view.LayoutInflater.from(r3)
            int r7 = r12.item_layout
            android.view.View r3 = r3.inflate(r7, r6)
            coband.bsit.com.integral.adapter.CalendarViewAdapter r6 = r12.calendarViewAdapter
            android.widget.TextView[] r6 = r6.convertView(r3, r2)
            r7 = r6[r0]
            r6 = r6[r4]
            goto L_0x00a0
        L_0x0083:
            android.content.Context r3 = r12.mContext
            android.view.LayoutInflater r3 = android.view.LayoutInflater.from(r3)
            int r7 = coband.bsit.com.integral.R.layout.integral_item_month_layout
            android.view.View r3 = r3.inflate(r7, r6)
            int r6 = coband.bsit.com.integral.R.id.solar_day
            android.view.View r6 = r3.findViewById(r6)
            r7 = r6
            android.widget.TextView r7 = (android.widget.TextView) r7
            int r6 = coband.bsit.com.integral.R.id.lunar_day
            android.view.View r6 = r3.findViewById(r6)
            android.widget.TextView r6 = (android.widget.TextView) r6
        L_0x00a0:
            coband.bsit.com.integral.bean.AttrsBean r8 = r12.mAttrsBean
            int r8 = r8.getColorSolar()
            r7.setTextColor(r8)
            coband.bsit.com.integral.bean.AttrsBean r8 = r12.mAttrsBean
            int r8 = r8.getSizeSolar()
            float r8 = (float) r8
            r7.setTextSize(r8)
            coband.bsit.com.integral.bean.AttrsBean r8 = r12.mAttrsBean
            int r8 = r8.getColorLunar()
            r6.setTextColor(r8)
            coband.bsit.com.integral.bean.AttrsBean r8 = r12.mAttrsBean
            int r8 = r8.getSizeLunar()
            float r8 = (float) r8
            r6.setTextSize(r8)
            int r8 = r2.getType()
            if (r8 == 0) goto L_0x00d2
            int r8 = r2.getType()
            if (r8 != r5) goto L_0x00db
        L_0x00d2:
            coband.bsit.com.integral.bean.AttrsBean r8 = r12.mAttrsBean
            int r8 = r8.getColorLunar()
            r7.setTextColor(r8)
        L_0x00db:
            int[] r8 = r2.getSolar()
            r8 = r8[r5]
            java.lang.String r8 = java.lang.String.valueOf(r8)
            r7.setText(r8)
            coband.bsit.com.integral.bean.AttrsBean r8 = r12.mAttrsBean
            boolean r8 = r8.isShowLunar()
            r9 = 8
            if (r8 == 0) goto L_0x01a6
            java.lang.String r8 = "初一"
            java.lang.String[] r10 = r2.getLunar()
            r10 = r10[r4]
            boolean r8 = r8.equals(r10)
            if (r8 == 0) goto L_0x0132
            java.lang.String[] r8 = r2.getLunar()
            r8 = r8[r0]
            r6.setText(r8)
            java.lang.String r8 = "正月"
            java.lang.String[] r9 = r2.getLunar()
            r9 = r9[r0]
            boolean r8 = r8.equals(r9)
            if (r8 == 0) goto L_0x01a9
            coband.bsit.com.integral.bean.AttrsBean r8 = r12.mAttrsBean
            boolean r8 = r8.isShowHoliday()
            if (r8 == 0) goto L_0x01a9
            coband.bsit.com.integral.bean.AttrsBean r8 = r12.mAttrsBean
            int r8 = r8.getColorHoliday()
            r6.setTextColor(r8)
            java.lang.String r8 = "春节"
            r6.setText(r8)
            goto L_0x01a9
        L_0x0132:
            java.lang.String r8 = r2.getSolarHoliday()
            boolean r8 = android.text.TextUtils.isEmpty(r8)
            if (r8 != 0) goto L_0x0150
            coband.bsit.com.integral.bean.AttrsBean r8 = r12.mAttrsBean
            boolean r8 = r8.isShowHoliday()
            if (r8 == 0) goto L_0x0150
            java.lang.String r8 = r2.getSolarHoliday()
            int r9 = r2.getType()
            r12.setLunarText(r8, r6, r9)
            goto L_0x01a9
        L_0x0150:
            java.lang.String r8 = r2.getLunarHoliday()
            boolean r8 = android.text.TextUtils.isEmpty(r8)
            if (r8 != 0) goto L_0x016e
            coband.bsit.com.integral.bean.AttrsBean r8 = r12.mAttrsBean
            boolean r8 = r8.isShowHoliday()
            if (r8 == 0) goto L_0x016e
            java.lang.String r8 = r2.getLunarHoliday()
            int r9 = r2.getType()
            r12.setLunarText(r8, r6, r9)
            goto L_0x01a9
        L_0x016e:
            java.lang.String r8 = r2.getTerm()
            boolean r8 = android.text.TextUtils.isEmpty(r8)
            if (r8 != 0) goto L_0x018c
            coband.bsit.com.integral.bean.AttrsBean r8 = r12.mAttrsBean
            boolean r8 = r8.isShowTerm()
            if (r8 == 0) goto L_0x018c
            java.lang.String r8 = r2.getTerm()
            int r9 = r2.getType()
            r12.setLunarText(r8, r6, r9)
            goto L_0x01a9
        L_0x018c:
            java.lang.String[] r8 = r2.getLunar()
            r8 = r8[r4]
            boolean r8 = android.text.TextUtils.isEmpty(r8)
            if (r8 == 0) goto L_0x019c
            r6.setVisibility(r9)
            goto L_0x01a9
        L_0x019c:
            java.lang.String[] r8 = r2.getLunar()
            r8 = r8[r4]
            r6.setText(r8)
            goto L_0x01a9
        L_0x01a6:
            r6.setVisibility(r9)
        L_0x01a9:
            coband.bsit.com.integral.bean.AttrsBean r8 = r12.mAttrsBean
            int r8 = r8.getChooseType()
            if (r8 != 0) goto L_0x01f7
            coband.bsit.com.integral.bean.AttrsBean r8 = r12.mAttrsBean
            int[] r8 = r8.getSingleDate()
            if (r8 == 0) goto L_0x01f7
            if (r1 != 0) goto L_0x01f7
            int r8 = r2.getType()
            if (r8 != r4) goto L_0x01f7
            coband.bsit.com.integral.bean.AttrsBean r8 = r12.mAttrsBean
            int[] r8 = r8.getSingleDate()
            r8 = r8[r0]
            int[] r9 = r2.getSolar()
            r9 = r9[r0]
            if (r8 != r9) goto L_0x01f7
            coband.bsit.com.integral.bean.AttrsBean r8 = r12.mAttrsBean
            int[] r8 = r8.getSingleDate()
            r8 = r8[r4]
            int[] r9 = r2.getSolar()
            r9 = r9[r4]
            if (r8 != r9) goto L_0x01f7
            coband.bsit.com.integral.bean.AttrsBean r8 = r12.mAttrsBean
            int[] r8 = r8.getSingleDate()
            r8 = r8[r5]
            int[] r9 = r2.getSolar()
            r9 = r9[r5]
            if (r8 != r9) goto L_0x01f7
            r12.lastClickedView = r3
            r12.setDayColor(r3, r4)
            r1 = 1
        L_0x01f7:
            coband.bsit.com.integral.bean.AttrsBean r8 = r12.mAttrsBean
            int r8 = r8.getChooseType()
            if (r8 != r4) goto L_0x024f
            coband.bsit.com.integral.bean.AttrsBean r8 = r12.mAttrsBean
            java.util.List r8 = r8.getMultiDates()
            if (r8 == 0) goto L_0x024f
            coband.bsit.com.integral.bean.AttrsBean r8 = r12.mAttrsBean
            java.util.List r8 = r8.getMultiDates()
            java.util.Iterator r8 = r8.iterator()
        L_0x0211:
            boolean r9 = r8.hasNext()
            if (r9 == 0) goto L_0x024f
            java.lang.Object r9 = r8.next()
            int[] r9 = (int[]) r9
            int r10 = r2.getType()
            if (r10 != r4) goto L_0x0211
            r10 = r9[r0]
            int[] r11 = r2.getSolar()
            r11 = r11[r0]
            if (r10 != r11) goto L_0x0211
            r10 = r9[r4]
            int[] r11 = r2.getSolar()
            r11 = r11[r4]
            if (r10 != r11) goto L_0x0211
            r10 = r9[r5]
            int[] r11 = r2.getSolar()
            r11 = r11[r5]
            if (r10 != r11) goto L_0x0211
            r12.setDayColor(r3, r4)
            java.util.Set<java.lang.Integer> r8 = r12.chooseDays
            r9 = r9[r5]
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
            r8.add(r9)
        L_0x024f:
            int r8 = r2.getType()
            if (r8 != r4) goto L_0x02d9
            int[] r4 = r2.getSolar()
            r4 = r4[r5]
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r3.setTag(r4)
            coband.bsit.com.integral.bean.AttrsBean r4 = r12.mAttrsBean
            int[] r4 = r4.getDisableStartDate()
            r5 = -1
            if (r4 == 0) goto L_0x029e
            coband.bsit.com.integral.bean.AttrsBean r4 = r12.mAttrsBean
            int[] r4 = r4.getDisableStartDate()
            long r8 = coband.bsit.com.integral.utils.CalendarUtil.dateToMillis(r4)
            int[] r4 = r2.getSolar()
            long r10 = coband.bsit.com.integral.utils.CalendarUtil.dateToMillis(r4)
            int r4 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r4 <= 0) goto L_0x029e
            coband.bsit.com.integral.bean.AttrsBean r2 = r12.mAttrsBean
            int r2 = r2.getColorLunar()
            r7.setTextColor(r2)
            coband.bsit.com.integral.bean.AttrsBean r2 = r12.mAttrsBean
            int r2 = r2.getColorLunar()
            r6.setTextColor(r2)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r5)
            r3.setTag(r2)
            r12.addView(r3, r14)
            goto L_0x02dc
        L_0x029e:
            coband.bsit.com.integral.bean.AttrsBean r4 = r12.mAttrsBean
            int[] r4 = r4.getDisableEndDate()
            if (r4 == 0) goto L_0x02d9
            coband.bsit.com.integral.bean.AttrsBean r4 = r12.mAttrsBean
            int[] r4 = r4.getDisableEndDate()
            long r8 = coband.bsit.com.integral.utils.CalendarUtil.dateToMillis(r4)
            int[] r2 = r2.getSolar()
            long r10 = coband.bsit.com.integral.utils.CalendarUtil.dateToMillis(r2)
            int r2 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r2 >= 0) goto L_0x02d9
            coband.bsit.com.integral.bean.AttrsBean r2 = r12.mAttrsBean
            int r2 = r2.getColorLunar()
            r7.setTextColor(r2)
            coband.bsit.com.integral.bean.AttrsBean r2 = r12.mAttrsBean
            int r2 = r2.getColorLunar()
            r6.setTextColor(r2)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r5)
            r3.setTag(r2)
            r12.addView(r3, r14)
            goto L_0x02dc
        L_0x02d9:
            r12.addView(r3, r14)
        L_0x02dc:
            int r14 = r14 + 1
            goto L_0x0017
        L_0x02e0:
            r12.requestLayout()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: coband.bsit.com.integral.widget.MonthView.setDateList(java.util.List, int):void");
    }

    private void setLunarText(String str, TextView textView, int i) {
        textView.setText(str);
        if (i == 1) {
            textView.setTextColor(this.mAttrsBean.getColorHoliday());
        }
        textView.setTag("holiday");
    }

    private void setDayColor(View view, int i) {
        TextView textView = (TextView) view.findViewById(R.id.solar_day);
        TextView textView2 = (TextView) view.findViewById(R.id.lunar_day);
        textView.setTextSize((float) this.mAttrsBean.getSizeSolar());
        textView2.setTextSize((float) this.mAttrsBean.getSizeLunar());
        if (i == 0) {
            view.setBackgroundResource(0);
            textView.setTextColor(this.mAttrsBean.getColorSolar());
            if ("holiday".equals(textView2.getTag())) {
                textView2.setTextColor(this.mAttrsBean.getColorHoliday());
            } else {
                textView2.setTextColor(this.mAttrsBean.getColorLunar());
            }
        } else if (i == 1) {
            view.setBackgroundResource(this.mAttrsBean.getDayBg());
            textView.setTextColor(this.mAttrsBean.getColorChoose());
            textView2.setTextColor(this.mAttrsBean.getColorChoose());
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int i3 = size / 7;
        int i4 = i3 * 6;
        if (size2 > i4) {
            size2 = i4;
        }
        setMeasuredDimension(size, size2);
        int min = Math.min(i3, size2 / 6);
        for (int i5 = 0; i5 < getChildCount(); i5++) {
            getChildAt(i5).measure(View.MeasureSpec.makeMeasureSpec(min, 1073741824), View.MeasureSpec.makeMeasureSpec(min, 1073741824));
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (getChildCount() != 0) {
            View childAt = getChildAt(0);
            int measuredWidth = childAt.getMeasuredWidth();
            int measuredHeight = childAt.getMeasuredHeight();
            int measuredWidth2 = (getMeasuredWidth() - (measuredWidth * 7)) / 14;
            int i5 = getChildCount() == 35 ? measuredHeight / 5 : 0;
            for (int i6 = 0; i6 < getChildCount(); i6++) {
                int i7 = i6 % 7;
                int i8 = (i7 * measuredWidth) + (((i7 * 2) + 1) * measuredWidth2);
                int i9 = (i6 / 7) * (measuredHeight + i5);
                getChildAt(i6).layout(i8, i9, i8 + measuredWidth, i9 + measuredHeight);
            }
        }
    }

    public void refresh(int i, boolean z) {
        View findDestView;
        if (this.lastClickedView != null) {
            setDayColor(this.lastClickedView, 0);
        }
        if (z && (findDestView = findDestView(i)) != null) {
            setDayColor(findDestView, 1);
            this.lastClickedView = findDestView;
            invalidate();
        }
    }

    public void multiChooseRefresh(HashSet<Integer> hashSet) {
        Iterator<Integer> it = hashSet.iterator();
        while (it.hasNext()) {
            Integer next = it.next();
            setDayColor(findDestView(next.intValue()), 1);
            this.chooseDays.add(next);
        }
        invalidate();
    }

    private View findDestView(int i) {
        View view;
        int i2 = this.lastMonthDays;
        while (true) {
            if (i2 >= getChildCount() - this.nextMonthDays) {
                view = null;
                break;
            } else if (((Integer) getChildAt(i2).getTag()).intValue() == i) {
                view = getChildAt(i2);
                break;
            } else {
                i2++;
            }
        }
        if (view == null) {
            view = getChildAt((this.currentMonthDays + this.lastMonthDays) - 1);
        }
        if (((Integer) view.getTag()).intValue() == -1) {
            return null;
        }
        return view;
    }

    public void setAttrsBean(AttrsBean attrsBean) {
        this.mAttrsBean = attrsBean;
    }

    public void setOnCalendarViewAdapter(int i, CalendarViewAdapter calendarViewAdapter2) {
        this.item_layout = i;
        this.calendarViewAdapter = calendarViewAdapter2;
    }
}

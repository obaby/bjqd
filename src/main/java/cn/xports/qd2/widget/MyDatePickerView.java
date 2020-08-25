package cn.xports.qd2.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import cn.xports.qd2.R;
import com.zyyoona7.picker.DatePickerView;

public class MyDatePickerView extends DatePickerView {
    public MyDatePickerView(Context context) {
        super(context);
    }

    public MyDatePickerView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MyDatePickerView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public int getDatePickerViewLayoutId() {
        return R.layout.layout_date_my_picker_view;
    }
}

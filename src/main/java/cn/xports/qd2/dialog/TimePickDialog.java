package cn.xports.qd2.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import cn.xports.base.BaseBottomDialog;
import cn.xports.qd2.R;
import com.blankj.utilcode.util.LogUtils;
import com.zyyoona7.picker.DatePickerView;
import com.zyyoona7.picker.base.BaseDatePickerView;
import com.zyyoona7.picker.listener.OnDateSelectedListener;
import java.util.Date;

public class TimePickDialog extends BaseBottomDialog {
    /* access modifiers changed from: private */
    public OnDateSelect onDateSelect;
    /* access modifiers changed from: private */
    public DatePickerView pickerView;
    /* access modifiers changed from: private */
    public String time;
    private TextView tvCancel;
    private TextView tvFinish;
    private TextView tvTitle;

    public interface OnDateSelect {
        void onSelect(String str);
    }

    public TimePickDialog(@NonNull Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public void onCreate() {
        setContentView(R.layout.dialog_bottom_time_select);
        this.tvTitle = (TextView) findViewById(R.id.tv_title);
        this.tvCancel = (TextView) findViewById(R.id.tv_cancel);
        this.tvFinish = (TextView) findViewById(R.id.tv_finish);
        this.tvTitle.setText("选择日期");
        this.tvCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                TimePickDialog.this.dismiss();
            }
        });
        this.tvFinish.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (TimePickDialog.this.onDateSelect != null) {
                    TimePickDialog timePickDialog = TimePickDialog.this;
                    String unused = timePickDialog.time = TimePickDialog.this.pickerView.getSelectedYear() + "-" + String.format("%02d", new Object[]{Integer.valueOf(TimePickDialog.this.pickerView.getSelectedMonth())}) + "-" + String.format("%02d", new Object[]{Integer.valueOf(TimePickDialog.this.pickerView.getSelectedDay())});
                    TimePickDialog.this.onDateSelect.onSelect(TimePickDialog.this.time);
                }
                TimePickDialog.this.dismiss();
            }
        });
        this.pickerView = findViewById(R.id.picker_view);
        this.pickerView.setTextSize(17.0f, true);
        this.pickerView.setShowDivider(true);
        this.pickerView.setShowLabel(false);
        this.pickerView.setDividerType(0);
        this.pickerView.setDividerHeight(1.0f, false);
        this.pickerView.setDividerColor(this.context.getResources().getColor(R.color.gray_e0e));
        this.pickerView.setLineSpacing(15.0f, true);
        this.pickerView.setOnDateSelectedListener(new OnDateSelectedListener() {
            public void onDateSelected(BaseDatePickerView baseDatePickerView, int i, int i2, int i3, @Nullable Date date) {
                LogUtils.e(new Object[]{TimePickDialog.this.time});
            }
        });
    }

    public TimePickDialog setOnDateSelect(OnDateSelect onDateSelect2) {
        this.onDateSelect = onDateSelect2;
        return this;
    }
}

package cn.xports.qd2.widget;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import cn.xports.qd2.R;
import cn.xports.qd2.dialog.TimePickDialog;
import cn.xports.qd2.entity.ViewElementData;
import com.blankj.utilcode.util.ColorUtils;
import com.blankj.utilcode.util.ToastUtils;

public class TimePickElementView extends ElementView {
    /* access modifiers changed from: private */
    public TextView etValue;
    private String hint;
    private ImageView ivSelect;
    private TextView tvTitle;

    public void clear() {
    }

    public TimePickElementView(ViewElementData viewElementData, Context context) {
        super(viewElementData, context);
    }

    /* access modifiers changed from: protected */
    public void init() {
        inflate(this.context, R.layout.element_select_view, this);
        this.etValue = (TextView) findViewById(R.id.et_input_value);
        this.tvTitle = (TextView) findViewById(R.id.tv_title_tip);
        this.ivSelect = (ImageView) findViewById(R.id.iv_select);
        this.tvTitle.setText(this.elementData.getName());
        this.hint = "请选择" + this.elementData.getName();
        if ("1".equals(this.elementData.getRequired())) {
            this.hint += "(必选)";
        }
        this.etValue.setText(this.hint);
        this.etValue.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                new TimePickDialog(TimePickElementView.this.context).setOnDateSelect(new TimePickDialog.OnDateSelect() {
                    public void onSelect(String str) {
                        TimePickElementView.this.elementData.setValue(str);
                        TimePickElementView.this.etValue.setText(str);
                        TimePickElementView.this.etValue.setTextColor(ColorUtils.getColor(R.color.black_353));
                    }
                }).show();
            }
        });
    }

    public void setEnable(boolean z) {
        int i;
        this.etValue.setEnabled(z);
        TextView textView = this.etValue;
        if (z) {
            i = ColorUtils.getColor(R.color.black_353);
        } else {
            i = ColorUtils.getColor(R.color.gray_cbc);
        }
        textView.setTextColor(i);
    }

    public String getValue() {
        return this.elementData.getValue();
    }

    public void setShowValue(String str) {
        if (!TextUtils.isEmpty(str) && this.etValue != null) {
            this.etValue.setText(str);
            this.elementData.setValue(str);
        }
    }

    public boolean canSubmit() {
        if (super.canSubmit() || this.etValue.getText() != this.hint) {
            return true;
        }
        ToastUtils.showShort("请完成必选项");
        return false;
    }

    public void showLine(boolean z) {
        findViewById(R.id.v_line).setVisibility(z ? 0 : 8);
    }
}

package cn.xports.qd2.widget;

import android.content.Context;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;
import cn.xports.qd2.R;
import cn.xports.qd2.entity.ViewElementData;
import com.blankj.utilcode.util.ColorUtils;
import com.blankj.utilcode.util.ToastUtils;

public class TextInputView extends ElementView {
    private EditText etValue;
    private String hint = "";
    private TextView tvTitle;

    public TextInputView(ViewElementData viewElementData, Context context) {
        super(viewElementData, context);
    }

    /* access modifiers changed from: protected */
    public void init() {
        inflate(this.context, R.layout.element_text_input_view, this);
        this.etValue = (EditText) findViewById(R.id.et_input_value);
        this.tvTitle = (TextView) findViewById(R.id.tv_title_tip);
        this.tvTitle.setText(this.elementData.getName());
        this.hint = this.elementData.getOptions() == null ? "" : this.elementData.getOptions();
        if ("1".equals(this.elementData.getRequired())) {
            this.hint += "(必填)";
        }
        this.etValue.setHint(this.hint);
        if ("$PHONE".equals(this.elementData.getInputType())) {
            this.etValue.setInputType(3);
        }
        if (this.elementData != null && TextUtils.isEmpty(this.elementData.getValue())) {
            this.etValue.setText(this.elementData.getValue());
        }
        this.etValue.setFilters(new InputFilter[]{new InputFilter() {
            public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
                if (charSequence.toString().contains(" ")) {
                    return charSequence.toString().replace(" ", "");
                }
                return null;
            }
        }});
    }

    public void setEnable(boolean z) {
        int i;
        this.etValue.setEnabled(z);
        EditText editText = this.etValue;
        if (z) {
            i = ColorUtils.getColor(R.color.black_353);
        } else {
            i = ColorUtils.getColor(R.color.gray_cbc);
        }
        editText.setTextColor(i);
    }

    public void clear() {
        this.etValue.setText("");
    }

    public String getValue() {
        if (TextUtils.isEmpty(this.etValue.getText())) {
            return "";
        }
        return this.etValue.getText().toString();
    }

    public void setShowValue(String str) {
        if (!TextUtils.isEmpty(str) && this.etValue != null) {
            this.etValue.setText(str);
        }
    }

    public boolean canSubmit() {
        if (super.canSubmit()) {
            return true;
        }
        if (TextUtils.isEmpty(this.etValue.getText())) {
            if (TextUtils.isEmpty(this.hint.replace("(必填)", ""))) {
                "请输入" + this.elementData.getName();
            }
            ToastUtils.showShort("请完成必填项");
            return false;
        } else if (!"$PHONE".equals(this.elementData.getInputType()) || this.etValue.getText().length() == 11) {
            return true;
        } else {
            ToastUtils.showShort("请填写正确的手机号码");
            return false;
        }
    }

    public void showLine(boolean z) {
        findViewById(R.id.v_line).setVisibility(z ? 0 : 8);
    }
}

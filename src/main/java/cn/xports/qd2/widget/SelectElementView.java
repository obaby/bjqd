package cn.xports.qd2.widget;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.xports.qd2.R;
import cn.xports.qd2.dialog.MultiSelectDialog;
import cn.xports.qd2.dialog.SingleSelectDialog;
import cn.xports.qd2.entity.ViewElementData;
import com.alipay.sdk.util.h;
import com.blankj.utilcode.util.ColorUtils;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;

public class SelectElementView extends ElementView {
    /* access modifiers changed from: private */
    public TextView etValue;
    /* access modifiers changed from: private */
    public String hint;
    /* access modifiers changed from: private */
    public boolean isSingle = true;
    private ImageView ivSelect;
    private LinearLayout llShow;
    /* access modifiers changed from: private */
    public ViewElementData.Option selectOption;
    /* access modifiers changed from: private */
    public List<ViewElementData.Option> selectOptions;
    private TextView tvTitle;

    public SelectElementView(ViewElementData viewElementData, Context context) {
        super(viewElementData, context);
    }

    /* access modifiers changed from: protected */
    public void init() {
        inflate(this.context, R.layout.element_select_view, this);
        this.etValue = (TextView) findViewById(R.id.et_input_value);
        this.tvTitle = (TextView) findViewById(R.id.tv_title_tip);
        this.llShow = (LinearLayout) findViewById(R.id.ll_select_options);
        this.ivSelect = (ImageView) findViewById(R.id.iv_select);
        this.tvTitle.setText(this.elementData.getName());
        this.hint = "请选择" + this.elementData.getName();
        if ("1".equals(this.elementData.getRequired())) {
            this.hint += "(必选)";
        }
        this.etValue.setText(this.hint);
        final List list = (List) GsonUtils.fromJson(this.elementData.getOptions(), new TypeToken<ArrayList<ViewElementData.Option>>() {
        }.getType());
        this.etValue.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (SelectElementView.this.isSingle) {
                    SingleSelectDialog singleSelectDialog = new SingleSelectDialog(SelectElementView.this.context);
                    singleSelectDialog.setTitle("选择" + SelectElementView.this.elementData.getName()).setOptions(list).setOnItemSelectListener(new SingleSelectDialog.OnItemSelectListener() {
                        public void onItemSelect(ViewElementData.Option option) {
                            ViewElementData.Option unused = SelectElementView.this.selectOption = option;
                            SelectElementView.this.etValue.setText(option.getName());
                            SelectElementView.this.etValue.setTextColor(ColorUtils.getColor(R.color.black_353));
                        }
                    }).show();
                    return;
                }
                new MultiSelectDialog(SelectElementView.this.context).setOptions(list).setOnItemSelectListener(new MultiSelectDialog.OnItemSelectListener() {
                    public void onItemsSelect(List<ViewElementData.Option> list) {
                        if (list.size() > 0) {
                            List unused = SelectElementView.this.selectOptions = list;
                            SelectElementView.this.etValue.setTextColor(ColorUtils.getColor(R.color.black_353));
                            String str = "";
                            for (ViewElementData.Option name : list) {
                                str = str + name.getName() + "; ";
                                SelectElementView.this.etValue.setText(str);
                            }
                            return;
                        }
                        List unused2 = SelectElementView.this.selectOptions = null;
                        SelectElementView.this.etValue.setText(SelectElementView.this.hint);
                        SelectElementView.this.etValue.setTextColor(ColorUtils.getColor(R.color.gray_cbc));
                    }
                }).show();
            }
        });
    }

    public void setEnable(boolean z) {
        this.etValue.setEnabled(z);
        if (z) {
            if (this.selectOptions != null && this.selectOptions.size() > 0) {
                this.etValue.setTextColor(ColorUtils.getColor(R.color.black_353));
            }
            if (this.selectOption != null) {
                this.etValue.setTextColor(ColorUtils.getColor(R.color.black_353));
            }
            this.ivSelect.setVisibility(0);
            return;
        }
        this.etValue.setTextColor(ColorUtils.getColor(R.color.gray_cbc));
        this.ivSelect.setVisibility(8);
    }

    public void clear() {
        this.selectOptions = null;
        this.etValue.setText(this.hint);
        this.etValue.setTextColor(ColorUtils.getColor(R.color.gray_cbc));
    }

    public SelectElementView setSingle(boolean z) {
        this.isSingle = z;
        return this;
    }

    public String getValue() {
        if (this.isSingle) {
            if (this.selectOption != null) {
                return this.selectOption.getValue();
            }
            return "";
        } else if (this.selectOptions == null) {
            return "";
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < this.selectOptions.size(); i++) {
                if (i < this.selectOptions.size() - 1) {
                    sb.append(this.selectOptions.get(i).getValue());
                    sb.append(",");
                } else {
                    sb.append(this.selectOptions.get(i).getValue());
                }
            }
            return sb.toString();
        }
    }

    public void setShowValue(String str) {
        if (!TextUtils.isEmpty(str) && this.etValue != null && this.elementData != null) {
            List<ViewElementData.Option> list = (List) GsonUtils.fromJson(this.elementData.getOptions(), new TypeToken<ArrayList<ViewElementData.Option>>() {
            }.getType());
            if (this.isSingle) {
                for (ViewElementData.Option option : list) {
                    if (str.equals(option.getValue())) {
                        this.selectOption = option;
                        this.etValue.setText(option.getName());
                        return;
                    }
                }
                return;
            }
            String[] split = str.split(",");
            StringBuilder sb = new StringBuilder();
            this.selectOptions = new ArrayList();
            for (ViewElementData.Option option2 : list) {
                int length = split.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        break;
                    } else if (split[i].equals(option2.getValue())) {
                        sb.append(option2.getName());
                        sb.append(h.f735b);
                        this.selectOptions.add(option2);
                        break;
                    } else {
                        i++;
                    }
                }
            }
            if (!sb.toString().equals("")) {
                this.etValue.setText(sb.toString());
            }
        }
    }

    public boolean canSubmit() {
        if (super.canSubmit()) {
            return true;
        }
        if (this.etValue.getText() != this.hint) {
            return !TextUtils.isEmpty(getValue());
        }
        ToastUtils.showShort("请完成必选项");
        return false;
    }

    public void showLine(boolean z) {
        findViewById(R.id.v_line).setVisibility(z ? 0 : 8);
    }
}

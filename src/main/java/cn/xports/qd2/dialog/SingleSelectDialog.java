package cn.xports.qd2.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;
import cn.xports.base.BaseBottomDialog;
import cn.xports.qd2.R;
import cn.xports.qd2.entity.ViewElementData;
import com.zyyoona7.wheel.WheelView;
import java.util.List;

public class SingleSelectDialog extends BaseBottomDialog {
    /* access modifiers changed from: private */
    public OnItemSelectListener onItemSelectListener;
    /* access modifiers changed from: private */
    public WheelView<ViewElementData.Option> optionWheelView;
    private List<ViewElementData.Option> options;
    private TextView tvCancel;
    private TextView tvFinish;
    private TextView tvTitle;

    public interface OnItemSelectListener {
        void onItemSelect(ViewElementData.Option option);
    }

    public SingleSelectDialog(@NonNull Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public void onCreate() {
        setContentView(R.layout.dialog_bottom_single_select);
        this.optionWheelView = (WheelView) findViewById(R.id.wheel_view);
        this.optionWheelView.setTextSize(17.0f, true);
        this.optionWheelView.setShowDivider(true);
        this.optionWheelView.setDividerType(0);
        this.optionWheelView.setDividerHeight(1.0f, false);
        this.optionWheelView.setDividerColor(this.context.getResources().getColor(R.color.gray_e0e));
        this.optionWheelView.setLineSpacing(15.0f, true);
        this.tvTitle = (TextView) findViewById(R.id.tv_title);
        this.tvCancel = (TextView) findViewById(R.id.tv_cancel);
        this.tvFinish = (TextView) findViewById(R.id.tv_finish);
        this.tvFinish.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (SingleSelectDialog.this.onItemSelectListener != null) {
                    SingleSelectDialog.this.onItemSelectListener.onItemSelect((ViewElementData.Option) SingleSelectDialog.this.optionWheelView.getSelectedItemData());
                }
                SingleSelectDialog.this.dismiss();
            }
        });
        this.tvCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SingleSelectDialog.this.dismiss();
            }
        });
    }

    public SingleSelectDialog setTitle(String str) {
        if (this.tvTitle != null) {
            this.tvTitle.setText(str);
        }
        return this;
    }

    public SingleSelectDialog setOptions(List<ViewElementData.Option> list) {
        this.options = list;
        this.optionWheelView.setData(list);
        return this;
    }

    public SingleSelectDialog setOnItemSelectListener(OnItemSelectListener onItemSelectListener2) {
        this.onItemSelectListener = onItemSelectListener2;
        return this;
    }
}

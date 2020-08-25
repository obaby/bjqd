package cn.xports.qd2.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import cn.xports.base.BaseBottomDialog;
import cn.xports.qd2.R;
import cn.xports.qd2.adapter.MultiSelectAdapter;
import cn.xports.qd2.entity.ViewElementData;
import java.util.ArrayList;
import java.util.List;

public class MultiSelectDialog extends BaseBottomDialog {
    private MultiSelectAdapter adapter;
    /* access modifiers changed from: private */
    public OnItemSelectListener onItemSelectListener;
    /* access modifiers changed from: private */
    public List<ViewElementData.Option> options;
    private RecyclerView rvOptions;
    private TextView tvCancel;
    private TextView tvFinish;
    private TextView tvTitle;

    public interface OnItemSelectListener {
        void onItemsSelect(List<ViewElementData.Option> list);
    }

    public MultiSelectDialog(@NonNull Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public void onCreate() {
        setContentView(R.layout.dialog_bottom_multi_select);
        this.tvTitle = (TextView) findViewById(R.id.tv_title);
        this.tvCancel = (TextView) findViewById(R.id.tv_cancel);
        this.tvFinish = (TextView) findViewById(R.id.tv_finish);
        this.tvFinish.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (MultiSelectDialog.this.onItemSelectListener != null) {
                    ArrayList arrayList = new ArrayList();
                    for (ViewElementData.Option option : MultiSelectDialog.this.options) {
                        if (option.isSelect()) {
                            arrayList.add(option);
                        }
                    }
                    MultiSelectDialog.this.onItemSelectListener.onItemsSelect(arrayList);
                }
                MultiSelectDialog.this.dismiss();
            }
        });
        this.tvCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MultiSelectDialog.this.dismiss();
            }
        });
        this.options = new ArrayList();
        this.adapter = new MultiSelectAdapter(this.options);
        this.rvOptions = (RecyclerView) findViewById(R.id.rv_options);
        this.rvOptions.setLayoutManager(new GridLayoutManager(this.context, 3));
        this.rvOptions.setAdapter(this.adapter);
    }

    public MultiSelectDialog setOptions(List<ViewElementData.Option> list) {
        this.options.clear();
        this.options.addAll(list);
        this.adapter.notifyDataSetChanged();
        return this;
    }

    public MultiSelectDialog setTitle(String str) {
        if (str != null) {
            this.tvTitle.setText(str);
        }
        return this;
    }

    public OnItemSelectListener getOnItemSelectListener() {
        return this.onItemSelectListener;
    }

    public MultiSelectDialog setOnItemSelectListener(OnItemSelectListener onItemSelectListener2) {
        this.onItemSelectListener = onItemSelectListener2;
        return this;
    }
}

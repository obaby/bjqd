package cn.xports.qd2.adapter;

import android.view.View;
import cn.xports.baselib.adapter.XBaseHolder;
import cn.xports.baselib.adapter.XListAdapter;
import cn.xports.qd2.R;
import cn.xports.qd2.entity.Option;
import cn.xports.widget.CornerTextView;
import com.blankj.utilcode.util.ColorUtils;
import java.util.List;

public class FlowTagAdapter<T> extends XListAdapter<Option<T>> {
    /* access modifiers changed from: private */
    public boolean multiSelect = false;

    public FlowTagAdapter(List<Option<T>> list) {
        super(list);
    }

    public FlowTagAdapter(List<Option<T>> list, boolean z) {
        super(list);
        this.multiSelect = z;
    }

    public int getLayoutId() {
        return R.layout.item_tag_flow;
    }

    public void onBindHolder(XBaseHolder xBaseHolder, final Option<T> option, final int i) {
        int i2;
        int i3;
        CornerTextView cornerTextView = (CornerTextView) xBaseHolder.getView(R.id.ctTag);
        cornerTextView.setText(option.getName());
        if (option.isSelect()) {
            i2 = ColorUtils.getColor(R.color.gray_eff);
        } else {
            i2 = ColorUtils.getColor(R.color.gray_f2f);
        }
        cornerTextView.setBgColor(i2);
        if (option.isSelect()) {
            i3 = ColorUtils.getColor(R.color.blue_2e6);
        } else {
            i3 = ColorUtils.getColor(R.color.main_text_color);
        }
        cornerTextView.setTextColor(i3);
        cornerTextView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!option.isSelect() && !FlowTagAdapter.this.multiSelect) {
                    for (Option select : FlowTagAdapter.this.list) {
                        select.setSelect(false);
                    }
                }
                option.setSelect(!option.isSelect());
                FlowTagAdapter.this.notifyDataSetChanged();
                FlowTagAdapter.this.performItemClick(option, i);
            }
        });
    }
}

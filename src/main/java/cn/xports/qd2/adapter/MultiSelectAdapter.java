package cn.xports.qd2.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import cn.xports.baselib.adapter.XBaseAdapter;
import cn.xports.baselib.adapter.XBaseHolder;
import cn.xports.qd2.R;
import cn.xports.qd2.entity.ViewElementData;
import cn.xports.widget.CornerTextView;
import com.blankj.utilcode.util.ColorUtils;
import java.util.List;

public class MultiSelectAdapter extends XBaseAdapter<ViewElementData.Option> {
    public MultiSelectAdapter(List<ViewElementData.Option> list) {
        super(list);
    }

    public int getLayoutId() {
        return R.layout.item_multi_select;
    }

    public void onBindViewHolder(@NonNull XBaseHolder xBaseHolder, int i) {
        int i2;
        int i3;
        final ViewElementData.Option option = (ViewElementData.Option) this.list.get(i);
        CornerTextView cornerTextView = (CornerTextView) xBaseHolder.getView(R.id.ct_select);
        cornerTextView.setText(option.getName());
        if (option.isSelect()) {
            i2 = ColorUtils.getColor(R.color.blue_2e6);
        } else {
            i2 = ColorUtils.getColor(R.color.black_353);
        }
        cornerTextView.setTextColor(i2);
        if (option.isSelect()) {
            i3 = ColorUtils.getColor(R.color.gray_eff);
        } else {
            i3 = ColorUtils.getColor(R.color.gray_f4f);
        }
        cornerTextView.setBgColor(i3);
        xBaseHolder.setOnClickListener(R.id.ct_select, new View.OnClickListener() {
            public void onClick(View view) {
                option.setSelect(!option.isSelect());
                MultiSelectAdapter.this.notifyDataSetChanged();
            }
        });
    }
}

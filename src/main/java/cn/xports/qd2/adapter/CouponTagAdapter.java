package cn.xports.qd2.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import cn.xports.baselib.adapter.XBaseAdapter;
import cn.xports.baselib.adapter.XBaseHolder;
import cn.xports.baselib.bean.DataMap;
import cn.xports.qd2.R;
import cn.xports.qd2.entity.Option;
import cn.xports.widget.CornerTextView;
import com.blankj.utilcode.util.ColorUtils;
import java.util.List;

public class CouponTagAdapter extends XBaseAdapter<Option<DataMap>> {
    public CouponTagAdapter(List<Option<DataMap>> list) {
        super(list);
    }

    public int getLayoutId() {
        return R.layout.item_tag_flow2;
    }

    public void onBindViewHolder(@NonNull XBaseHolder xBaseHolder, final int i) {
        int i2;
        int i3;
        final Option option = (Option) this.list.get(i);
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
                if (!option.isSelect()) {
                    for (Option select : CouponTagAdapter.this.list) {
                        select.setSelect(false);
                    }
                }
                option.setSelect(!option.isSelect());
                CouponTagAdapter.this.notifyDataSetChanged();
                CouponTagAdapter.this.performItemClick(option, i);
            }
        });
    }
}

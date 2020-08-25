package cn.xports.qd2.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import cn.xports.baselib.adapter.XBaseAdapter;
import cn.xports.baselib.adapter.XBaseHolder;
import cn.xports.qd2.R;
import cn.xports.qd2.entity.Option;
import com.blankj.utilcode.util.ColorUtils;
import java.util.Iterator;
import java.util.List;

public class DropDownAdapter<T> extends XBaseAdapter<Option<T>> {
    public DropDownAdapter(List<Option<T>> list) {
        super(list);
    }

    public int getLayoutId() {
        return R.layout.item_drop_down_dialog;
    }

    public void onBindViewHolder(@NonNull XBaseHolder xBaseHolder, final int i) {
        int i2;
        final Option option = (Option) this.list.get(i);
        xBaseHolder.setText(R.id.tv_name, option.getName());
        int i3 = R.id.tv_name;
        if (option.isSelect()) {
            i2 = ColorUtils.getColor(R.color.blue_2e6);
        } else {
            i2 = ColorUtils.getColor(R.color.main_text_color);
        }
        xBaseHolder.setTextColor(i3, i2);
        xBaseHolder.setVisible(R.id.iv_select, option.isSelect());
        xBaseHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!option.isSelect()) {
                    Iterator it = DropDownAdapter.this.list.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        Option option = (Option) it.next();
                        if (option.isSelect()) {
                            option.setSelect(false);
                            break;
                        }
                    }
                    option.setSelect(true);
                    DropDownAdapter.this.notifyDataSetChanged();
                    DropDownAdapter.this.performItemClick(option, i);
                }
            }
        });
    }
}

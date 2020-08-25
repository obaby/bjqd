package cn.xports.qd2.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import cn.xports.baselib.adapter.XBaseAdapter;
import cn.xports.baselib.adapter.XBaseHolder;
import cn.xports.qd2.R;
import cn.xports.qd2.entity.Option;
import cn.xports.qd2.entity.StudentInfo;
import java.util.Iterator;
import java.util.List;

public class ChooseStudentAdapter extends XBaseAdapter<Option<StudentInfo>> {
    public ChooseStudentAdapter(List<Option<StudentInfo>> list) {
        super(list);
    }

    public int getLayoutId() {
        return R.layout.item_choose_student;
    }

    public void onBindViewHolder(@NonNull XBaseHolder xBaseHolder, final int i) {
        final Option option = (Option) this.list.get(i);
        xBaseHolder.setImageResource(R.id.iv_check, option.isSelect() ? R.drawable.ic_checked : R.drawable.ic_uncheck);
        xBaseHolder.setText(R.id.tv_name, option.getName());
        xBaseHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!option.isSelect()) {
                    Iterator it = ChooseStudentAdapter.this.list.iterator();
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
                    ChooseStudentAdapter.this.notifyDataSetChanged();
                    ChooseStudentAdapter.this.performItemClick(option, i);
                }
            }
        });
    }
}

package cn.xports.qd2.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import cn.xports.baselib.adapter.XBaseAdapter;
import cn.xports.baselib.adapter.XBaseHolder;
import cn.xports.baselib.bean.DataMap;
import cn.xports.qd2.R;
import cn.xports.qd2.entity.K;
import com.blankj.utilcode.util.ColorUtils;
import java.util.List;

public class CourseAttendAdapter extends XBaseAdapter<DataMap> {
    public CourseAttendAdapter(List<DataMap> list) {
        super(list);
    }

    public int getLayoutId() {
        return R.layout.item_course_history;
    }

    public void onBindViewHolder(@NonNull XBaseHolder xBaseHolder, final int i) {
        final DataMap dataMap = (DataMap) this.list.get(i);
        xBaseHolder.setText(R.id.tv_lesson_date, dataMap.getString(K.lessonDate).replace("-", "/"));
        int i2 = R.id.ct_sign_time;
        xBaseHolder.setText(i2, "签到时间：" + dataMap.getString(K.signinTime));
        final String str = "";
        switch (dataMap.getIntValue(K.state).intValue()) {
            case 1:
                if (!dataMap.getString(K.cancelTag).equals("1")) {
                    if (!dataMap.getString(K.commentTag).equals("1")) {
                        str = "去评价";
                        break;
                    } else {
                        str = "已评价";
                        break;
                    }
                } else {
                    str = "已取消";
                    break;
                }
            case 2:
                if (!dataMap.getString(K.cancelTag).equals("1")) {
                    str = "已请假";
                    break;
                } else {
                    str = "已取消请假";
                    break;
                }
            case 3:
                str = "已取消";
                break;
        }
        if (!str.equals("去评价")) {
            xBaseHolder.setTextColor(R.id.tv_go_comment, ColorUtils.getColor(R.color.second_text_color));
            xBaseHolder.setText(R.id.tv_go_comment, str);
        } else {
            xBaseHolder.setText(R.id.tv_go_comment, str);
            xBaseHolder.setTextColor(R.id.tv_go_comment, ColorUtils.getColor(R.color.blue_2e6));
        }
        xBaseHolder.setOnClickListener(R.id.tv_go_comment, new View.OnClickListener() {
            public void onClick(View view) {
                if (str.equals("去评价")) {
                    CourseAttendAdapter.this.performItemClick(dataMap, i, 1);
                }
            }
        });
    }
}

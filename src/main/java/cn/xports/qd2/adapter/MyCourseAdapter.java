package cn.xports.qd2.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import cn.xports.baselib.adapter.XBaseAdapter;
import cn.xports.baselib.adapter.XBaseHolder;
import cn.xports.baselib.bean.DataMap;
import cn.xports.baselib.util.GlideUtil;
import cn.xports.qd2.R;
import cn.xports.qd2.entity.K;
import java.util.List;

public class MyCourseAdapter extends XBaseAdapter<DataMap> {
    public MyCourseAdapter(List<DataMap> list) {
        super(list);
    }

    public int getLayoutId() {
        return R.layout.item_course;
    }

    public void onBindViewHolder(@NonNull XBaseHolder xBaseHolder, final int i) {
        final DataMap dataMap = (DataMap) this.list.get(i);
        xBaseHolder.setText(R.id.tv_name, dataMap.getString(K.courseName));
        xBaseHolder.setText(R.id.tv_student_name, dataMap.getString(K.stuName));
        int i2 = R.id.tv_left_num;
        xBaseHolder.setText(i2, dataMap.getString(K.remainNum, K.k0) + "节");
        xBaseHolder.setVisible(R.id.ll_class, dataMap.getString(K.privateTag, "").equals(K.k0));
        xBaseHolder.setVisible(R.id.tv_appoint, dataMap.getString(K.privateTag, "").equals("1"));
        xBaseHolder.setText(R.id.tv_class, "未分班");
        String string = dataMap.getString(K.startDate);
        if (string.length() > 10) {
            string = string.substring(0, 10);
        }
        String replace = string.replace("-", "/");
        String string2 = dataMap.getString(K.endDate, "");
        if (string2.length() > 10) {
            string2 = string2.substring(0, 10);
        }
        String replace2 = string2.replace("-", "/");
        int i3 = R.id.tv_effect_date;
        xBaseHolder.setText(i3, replace + " - " + replace2);
        String string3 = dataMap.getString(K.coursePicUrl);
        ImageView imageView = (ImageView) xBaseHolder.getView(R.id.iv_course);
        GlideUtil.loadImage(imageView.getContext(), string3, R.drawable.bg_default).into(imageView);
        xBaseHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MyCourseAdapter.this.performItemClick(dataMap, i, 0);
            }
        });
        xBaseHolder.setOnClickListener(R.id.tv_appoint, new View.OnClickListener() {
            public void onClick(View view) {
                MyCourseAdapter.this.performItemClick(dataMap, i, 1);
            }
        });
        String string4 = dataMap.getString(K.signinTime, "暂无");
        if (string4 != null && string4.length() >= 10) {
            string4 = string4.substring(0, 10);
        }
        xBaseHolder.setText(R.id.tv_last_time, string4);
    }
}

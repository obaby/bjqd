package cn.xports.qd2.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import cn.xports.base.BaseBottomDialog;
import cn.xports.baselib.adapter.XBaseAdapter;
import cn.xports.qd2.R;
import cn.xports.qd2.adapter.CoursePromAdapter;
import cn.xports.qd2.entity.CoursePromInfo;
import java.util.ArrayList;
import java.util.List;

public class CoursePromDialog extends BaseBottomDialog {
    private CoursePromAdapter adapter;
    private List<CoursePromInfo> list;
    private RecyclerView rvProms;

    public CoursePromDialog(@NonNull Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public void onCreate() {
        this.list = new ArrayList();
        setContentView(R.layout.dialog_course_prom);
        this.adapter = new CoursePromAdapter(this.list);
        this.rvProms = (RecyclerView) findViewById(R.id.rv_proms);
        this.rvProms.setLayoutManager(new LinearLayoutManager(this.context));
        this.rvProms.setAdapter(this.adapter);
        this.adapter.setOnItemClickListener(new XBaseAdapter.OnItemClickListener<CoursePromInfo>() {
            public void onItemClick(CoursePromInfo coursePromInfo, int i, int i2) {
                CoursePromDialog.this.performClick(1, coursePromInfo, coursePromInfo.getPromName());
                CoursePromDialog.this.dismiss();
            }
        });
        findViewById(R.id.ivClose).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CoursePromDialog.this.dismiss();
            }
        });
    }

    public CoursePromDialog setList(List<CoursePromInfo> list2) {
        this.list.clear();
        if (list2 != null) {
            this.list.addAll(list2);
        }
        if (this.adapter != null) {
            this.adapter.notifyDataSetChanged();
        }
        return this;
    }
}

package cn.xports.qd2.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import cn.xports.base.BaseBottomDialog;
import cn.xports.baselib.adapter.XBaseAdapter;
import cn.xports.qd2.R;
import cn.xports.qd2.adapter.ChooseStudentAdapter;
import cn.xports.qd2.entity.Option;
import cn.xports.qd2.entity.StudentInfo;
import java.util.ArrayList;
import java.util.List;

public class ChooseStudentDialog extends BaseBottomDialog {
    private ChooseStudentAdapter adapter;
    private RecyclerView rvStudents;
    private List<Option<StudentInfo>> studentInfos;

    public ChooseStudentDialog(@NonNull Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public void onCreate() {
        this.studentInfos = new ArrayList();
        setContentView(R.layout.dialog_choose_student);
        findViewById(R.id.ivClose).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ChooseStudentDialog.this.dismiss();
            }
        });
        findViewById(R.id.rl_add).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ChooseStudentDialog.this.performClick(1, "添加培训人");
                ChooseStudentDialog.this.dismiss();
            }
        });
        this.adapter = new ChooseStudentAdapter(this.studentInfos);
        this.rvStudents = (RecyclerView) findViewById(R.id.rv_student_list);
        this.rvStudents.setLayoutManager(new LinearLayoutManager(this.context));
        this.rvStudents.setAdapter(this.adapter);
        this.adapter.setOnItemClickListener(new XBaseAdapter.OnItemClickListener<Option<StudentInfo>>() {
            public void onItemClick(Option<StudentInfo> option, int i, int i2) {
                ChooseStudentDialog.this.performClick(0, option.getData());
                ChooseStudentDialog.this.dismiss();
            }
        });
    }

    public ChooseStudentDialog setData(List<StudentInfo> list) {
        if (list != null) {
            for (StudentInfo next : list) {
                this.studentInfos.add(new Option().setName(next.getStuName()).setData(next));
            }
            if (this.adapter != null) {
                this.adapter.notifyDataSetChanged();
            }
        }
        return this;
    }
}

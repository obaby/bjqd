package cn.xports.qd2.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;
import cn.xports.base.BaseBottomDialog;
import cn.xports.qd2.R;
import cn.xports.qd2.entity.CourseChildren;
import cn.xports.qd2.entity.CourseOptions;
import com.zyyoona7.wheel.WheelView;
import java.util.List;

public class FollowChangeDialog extends BaseBottomDialog {
    /* access modifiers changed from: private */
    public CourseChildren children;
    /* access modifiers changed from: private */
    public CourseOptions courseOption;
    private List<CourseOptions> courseOptions;
    private WheelView<CourseOptions> pickerView;
    /* access modifiers changed from: private */
    public WheelView<CourseChildren> pickerView2;
    private TextView tvCancel;
    private TextView tvFinish;
    private TextView tvTitle;

    public FollowChangeDialog(@NonNull Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public void onCreate() {
        setContentView(R.layout.dialog_follow_change);
        this.tvTitle = (TextView) findViewById(R.id.tv_title);
        this.tvCancel = (TextView) findViewById(R.id.tv_cancel);
        this.tvFinish = (TextView) findViewById(R.id.tv_finish);
        this.tvCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                FollowChangeDialog.this.dismiss();
            }
        });
        this.tvFinish.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                FollowChangeDialog.this.performClick(0, FollowChangeDialog.this.children, "");
                FollowChangeDialog.this.dismiss();
            }
        });
        this.pickerView = (WheelView) findViewById(R.id.picker_view);
        this.pickerView2 = (WheelView) findViewById(R.id.picker_view2);
        setWheelView(this.pickerView);
        setWheelView(this.pickerView2);
        this.pickerView.setOnItemSelectedListener(new WheelView.OnItemSelectedListener<CourseOptions>() {
            public void onItemSelected(WheelView<CourseOptions> wheelView, CourseOptions courseOptions, int i) {
                FollowChangeDialog.this.pickerView2.setData(courseOptions.getChildrenList());
                CourseOptions unused = FollowChangeDialog.this.courseOption = courseOptions;
            }
        });
        this.pickerView2.setOnItemSelectedListener(new WheelView.OnItemSelectedListener<CourseChildren>() {
            public void onItemSelected(WheelView<CourseChildren> wheelView, CourseChildren courseChildren, int i) {
                CourseChildren unused = FollowChangeDialog.this.children = courseChildren;
            }
        });
    }

    private void setWheelView(WheelView wheelView) {
        wheelView.setTextSize(17.0f, true);
        wheelView.setShowDivider(true);
        wheelView.setDividerType(0);
        wheelView.setDividerHeight(1.0f, false);
        wheelView.setDividerColor(this.context.getResources().getColor(R.color.gray_e0e));
        wheelView.setLineSpacing(15.0f, true);
    }

    public FollowChangeDialog setTitle(String str) {
        this.tvTitle.setText(str);
        return this;
    }

    public FollowChangeDialog setData(List<CourseOptions> list) {
        this.courseOptions = list;
        if (this.pickerView != null) {
            this.pickerView.setData(list);
        }
        return this;
    }
}

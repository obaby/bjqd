package cn.xports.qd2.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.xports.base.BaseBottomDialog;
import cn.xports.qd2.R;
import cn.xports.qd2.dialog.FollowChangeDialog;
import cn.xports.qd2.entity.CourseChildren;
import cn.xports.qd2.entity.CourseElement;
import org.json.JSONException;
import org.json.JSONObject;

public class FollowChangeView extends LinearLayout {
    /* access modifiers changed from: private */
    public CourseChildren courseChildren;
    /* access modifiers changed from: private */
    public CourseElement element;
    private TextView etValue;
    private TextView tvTitle;

    public FollowChangeView(Context context) {
        this(context, (AttributeSet) null);
    }

    public FollowChangeView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FollowChangeView(final Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        inflate(context, R.layout.element_select_view, this);
        this.etValue = (TextView) findViewById(R.id.et_input_value);
        this.tvTitle = (TextView) findViewById(R.id.tv_title_tip);
        this.etValue.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (FollowChangeView.this.element != null) {
                    new FollowChangeDialog(context).setTitle(FollowChangeView.this.element.getElementName()).setData(FollowChangeView.this.element.getOptions()).setOnItemClickListener(new BaseBottomDialog.OnItemClickListener() {
                        public void onItemClick(int i, Object... objArr) {
                            CourseChildren unused = FollowChangeView.this.courseChildren = objArr[0];
                        }
                    }).show();
                }
            }
        });
    }

    public FollowChangeView setData(CourseElement courseElement) {
        this.element = courseElement;
        if (this.tvTitle != null) {
            this.tvTitle.setText(courseElement.getElementName());
        }
        return this;
    }

    public CourseChildren getSelectOption() {
        return this.courseChildren;
    }

    public String getSubmitJson() {
        if (this.element == null || this.courseChildren == null) {
            return "{}";
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("elementId", this.element.getElementId());
            jSONObject.put("value", this.courseChildren.getChildrenId());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }
}

package cn.xports.qd2.dialog;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import cn.xports.qd2.R;
import com.blankj.utilcode.util.ColorUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.blankj.utilcode.util.SpanUtils;

public class CourseTipDialog {
    /* access modifiers changed from: private */
    public OnButtonClick buttonClick;
    private Context context;
    /* access modifiers changed from: private */
    public AlertDialog dialog;
    private TextView tvClose;
    private TextView tvSubmit;
    private View vertical;

    public interface OnButtonClick {
        void onClick(int i);
    }

    public CourseTipDialog(Context context2, String str, String str2) {
        this.context = context2;
        View inflate = View.inflate(context2, R.layout.dialog_course_tips, (ViewGroup) null);
        this.tvClose = (TextView) inflate.findViewById(R.id.tv_close);
        this.tvSubmit = (TextView) inflate.findViewById(R.id.tv_submit);
        this.vertical = inflate.findViewById(R.id.v_vertical);
        SpanUtils.with((TextView) inflate.findViewById(R.id.tv_content)).append(str).append(str2).setForegroundColor(ColorUtils.getColor(R.color.red_fd4)).create();
        this.tvClose.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (CourseTipDialog.this.dialog != null) {
                    CourseTipDialog.this.dialog.dismiss();
                }
                if (CourseTipDialog.this.buttonClick != null) {
                    CourseTipDialog.this.buttonClick.onClick(0);
                }
            }
        });
        this.tvSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (CourseTipDialog.this.dialog != null) {
                    CourseTipDialog.this.dialog.dismiss();
                }
                if (CourseTipDialog.this.buttonClick != null) {
                    CourseTipDialog.this.buttonClick.onClick(1);
                }
            }
        });
        this.dialog = new AlertDialog.Builder(context2).setView(inflate).create();
        this.dialog.show();
        WindowManager.LayoutParams attributes = this.dialog.getWindow().getAttributes();
        this.dialog.getWindow().setBackgroundDrawableResource(17170445);
        attributes.width = SizeUtils.dp2px(250.0f);
        this.dialog.getWindow().setAttributes(attributes);
    }

    public CourseTipDialog setRightText(String str) {
        this.tvSubmit.setText(str);
        return this;
    }

    public CourseTipDialog setLeftText(String str) {
        this.tvClose.setText(str);
        return this;
    }

    public CourseTipDialog setButtonClick(OnButtonClick onButtonClick) {
        this.buttonClick = onButtonClick;
        return this;
    }

    public CourseTipDialog setCancelVisible(boolean z) {
        if (this.tvClose != null) {
            int i = 8;
            this.tvClose.setVisibility(z ? 0 : 8);
            View view = this.vertical;
            if (z) {
                i = 0;
            }
            view.setVisibility(i);
        }
        return this;
    }
}

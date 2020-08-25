package cn.xports.qd2.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import cn.xports.base.BaseBottomDialog;
import cn.xports.qd2.R;

public class ApplyJoinCircleDialog extends BaseBottomDialog {
    /* access modifiers changed from: private */
    public ApplyClickListener applyClickListener;
    /* access modifiers changed from: private */
    public EditText etContent;
    /* access modifiers changed from: private */
    public TextView tvApplyBtn;

    public interface ApplyClickListener {
        void submitApply(String str);
    }

    public ApplyJoinCircleDialog(@NonNull Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public void onCreate() {
        setContentView(R.layout.dialog_apply_join_circle);
        this.etContent = (EditText) findViewById(R.id.et_apply_reason);
        findViewById(R.id.iv_close).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ApplyJoinCircleDialog.this.dismiss();
            }
        });
        this.tvApplyBtn = (TextView) findViewById(R.id.tv_confirm_apply);
        this.tvApplyBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String trim = ApplyJoinCircleDialog.this.etContent.getText().toString().trim();
                if (!TextUtils.isEmpty(trim) && ApplyJoinCircleDialog.this.applyClickListener != null) {
                    ApplyJoinCircleDialog.this.applyClickListener.submitApply(trim);
                    ApplyJoinCircleDialog.this.dismiss();
                }
            }
        });
        this.etContent.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (!TextUtils.isEmpty(charSequence.toString().trim())) {
                    ApplyJoinCircleDialog.this.tvApplyBtn.setBackground(ApplyJoinCircleDialog.this.context.getResources().getDrawable(R.drawable.shape_raduis22_blue));
                    ApplyJoinCircleDialog.this.tvApplyBtn.setEnabled(true);
                    return;
                }
                ApplyJoinCircleDialog.this.tvApplyBtn.setBackground(ApplyJoinCircleDialog.this.context.getResources().getDrawable(R.drawable.shape_raduis22_gray));
                ApplyJoinCircleDialog.this.tvApplyBtn.setEnabled(false);
            }
        });
    }

    public void setApplyClickListener(ApplyClickListener applyClickListener2) {
        this.applyClickListener = applyClickListener2;
    }
}

package cn.xports.qd2.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import cn.xports.base.BaseBottomDialog;
import cn.xports.baselib.util.ToastUtil;
import cn.xports.qd2.R;
import cn.xports.qd2.util.ViewExt;
import com.blankj.utilcode.util.KeyboardUtils;

public class EditMsgDialog extends BaseBottomDialog {
    /* access modifiers changed from: private */
    public EditText etContent;
    /* access modifiers changed from: private */
    public boolean isEdit;
    private boolean isEditable = false;
    /* access modifiers changed from: private */
    public ImageView ivSend;
    /* access modifiers changed from: private */
    public SendClickListener sendClickListener;
    /* access modifiers changed from: private */
    public TextView tvContent;
    /* access modifiers changed from: private */
    public TextView tvEditBtn;
    /* access modifiers changed from: private */
    public TextView tvTextNum;
    private TextView tvTitle;

    public interface SendClickListener {
        void sendMsg(String str);
    }

    public EditMsgDialog(@NonNull Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public void onCreate() {
        setContentView(R.layout.dialog_edit_info);
        this.tvTitle = (TextView) findViewById(R.id.tv_title);
        this.tvTextNum = (TextView) findViewById(R.id.tv_num);
        this.tvContent = (TextView) findViewById(R.id.tv_content);
        this.etContent = (EditText) findViewById(R.id.et_content);
        this.tvEditBtn = (TextView) findViewById(R.id.tv_edit_btn);
        ViewExt.filterEmoji(this.etContent);
        ViewExt.filterBlank(this.etContent);
        ViewExt.setMaxLength(this.etContent, 500, "最多输入500字");
        this.ivSend = (ImageView) findViewById(R.id.iv_send);
        findViewById(R.id.iv_close).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                EditMsgDialog.this.dismiss();
            }
        });
        this.tvEditBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String charSequence = EditMsgDialog.this.tvContent.getText() == null ? "" : EditMsgDialog.this.tvContent.getText().toString();
                if (EditMsgDialog.this.isEdit) {
                    KeyboardUtils.hideSoftInput(EditMsgDialog.this.tvEditBtn);
                    EditMsgDialog.this.tvEditBtn.setText("编辑");
                    EditMsgDialog.this.ivSend.setVisibility(4);
                    EditMsgDialog.this.etContent.setVisibility(4);
                    EditMsgDialog.this.tvContent.setVisibility(0);
                    TextView access$500 = EditMsgDialog.this.tvTextNum;
                    access$500.setText(charSequence.length() + "/500字");
                } else {
                    EditMsgDialog.this.tvEditBtn.setText("取消");
                    EditMsgDialog.this.ivSend.setVisibility(0);
                    EditMsgDialog.this.etContent.setVisibility(0);
                    EditMsgDialog.this.etContent.setText(charSequence);
                    EditMsgDialog.this.tvContent.setVisibility(8);
                }
                boolean unused = EditMsgDialog.this.isEdit = !EditMsgDialog.this.isEdit;
            }
        });
        this.ivSend.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String trim = EditMsgDialog.this.etContent.getText().toString().trim();
                if (TextUtils.isEmpty(trim)) {
                    ToastUtil.showMsg("请输入你想要说的~");
                    return;
                }
                if (EditMsgDialog.this.sendClickListener != null) {
                    EditMsgDialog.this.sendClickListener.sendMsg(trim);
                }
                EditMsgDialog.this.dismiss();
            }
        });
        this.etContent.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                TextView access$500 = EditMsgDialog.this.tvTextNum;
                access$500.setText(editable.toString().length() + "/500字");
            }
        });
        ViewExt.verticalScroll(this.tvContent);
    }

    public void setSendClickListener(SendClickListener sendClickListener2) {
        this.sendClickListener = sendClickListener2;
    }

    public void setData(String str, String str2) {
        this.tvTitle.setText(str);
        this.tvContent.setText(str2);
        this.etContent.setText(str2);
        if (!TextUtils.isEmpty(str2) || !this.isEditable) {
            TextView textView = this.tvTextNum;
            textView.setText(str2.length() + "/500字");
            return;
        }
        this.isEdit = true;
        this.tvEditBtn.setVisibility(8);
        this.ivSend.setVisibility(0);
        this.etContent.setVisibility(0);
        this.tvContent.setVisibility(8);
        this.tvTextNum.setText("0/500字");
    }

    public void setEditAble(boolean z) {
        this.isEditable = z;
        this.tvEditBtn.setVisibility(z ? 0 : 8);
    }
}

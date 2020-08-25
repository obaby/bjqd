package cn.xports.qd2.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;
import cn.xports.base.BaseBottomDialog;
import cn.xports.qd2.R;
import cn.xports.qd2.util.ViewExt;

public class NoticeDialog extends BaseBottomDialog {
    private TextView tvContent;

    public NoticeDialog(@NonNull Context context, String str) {
        super(context);
        if (this.tvContent != null) {
            this.tvContent.setText(str);
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate() {
        setContentView(R.layout.dialog_noticel);
        this.tvContent = (TextView) findViewById(R.id.tv_notice_content);
        ViewExt.verticalScroll(this.tvContent);
        findViewById(R.id.iv_close).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                NoticeDialog.this.dismiss();
            }
        });
    }
}

package cn.xports.field;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import cn.xports.baselib.util.DensityUtil;
import cn.xports.qdplugin.R;

public class FieldDialog {
    private Context context;
    /* access modifiers changed from: private */
    public AlertDialog dialog;

    public FieldDialog(Context context2, String str) {
        this.context = context2;
        View inflate = View.inflate(context2, R.layout.dialog_field_know, (ViewGroup) null);
        ((TextView) inflate.findViewById(R.id.tv_content)).setText(str);
        ((TextView) inflate.findViewById(R.id.tv_close)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (FieldDialog.this.dialog != null) {
                    FieldDialog.this.dialog.dismiss();
                }
            }
        });
        this.dialog = new AlertDialog.Builder(context2).setView(inflate).create();
        this.dialog.show();
        WindowManager.LayoutParams attributes = this.dialog.getWindow().getAttributes();
        attributes.height = DensityUtil.dp2px(480.0f);
        this.dialog.getWindow().setAttributes(attributes);
    }
}

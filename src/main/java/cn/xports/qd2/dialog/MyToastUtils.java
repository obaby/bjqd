package cn.xports.qd2.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import cn.xports.qd2.R;

public class MyToastUtils {
    private View ll;
    private Toast toast;

    public void ToastShow(Context context, String str, int i) {
        this.ll = LayoutInflater.from(context).inflate(R.layout.mytoast, (ViewGroup) null);
        ((ImageView) this.ll.findViewById(R.id.mt_iv)).setImageResource(i);
        ((TextView) this.ll.findViewById(R.id.mt_tv)).setText(str);
        this.toast = new Toast(context);
        this.toast.setView(this.ll);
        this.toast.setDuration(0);
        this.toast.show();
        this.toast.setGravity(17, 0, 0);
    }
}

package cn.xports.qd2.circle;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;
import cn.xports.baselib.util.ToastUtil;
import cn.xports.qd2.circle.videoPlayer.MyBaseActivity;
import com.stub.StubApp;

public class CircleIntroduceEditActivity extends MyBaseActivity {
    private String content = "";
    private EditText etContent;
    /* access modifiers changed from: private */
    public TextView tvCount;

    static {
        StubApp.interface11(3742);
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    /* access modifiers changed from: private */
    public void complete() {
        String trim = this.etContent.getText().toString().trim();
        if (TextUtils.isEmpty(trim)) {
            ToastUtil.showMsg("请输入圈子介绍");
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("content", trim);
        setResult(99, intent);
        finish();
    }
}

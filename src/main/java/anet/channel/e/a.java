package anet.channel.e;

import anet.channel.util.ALog;
import com.taobao.tlog.adapter.AdapterForTLog;

/* compiled from: Taobao */
public class a implements ALog.ILog {
    private int a(char c2) {
        switch (c2) {
            case 'D':
                return 1;
            case 'E':
                return 4;
            case 'I':
                return 2;
            case 'V':
                return 0;
            case 'W':
                return 3;
            default:
                return 5;
        }
    }

    public void setLogLevel(int i) {
    }

    public void d(String str, String str2) {
        AdapterForTLog.logd(str, str2);
    }

    public void i(String str, String str2) {
        AdapterForTLog.logi(str, str2);
    }

    public void w(String str, String str2) {
        AdapterForTLog.logw(str, str2);
    }

    public void w(String str, String str2, Throwable th) {
        AdapterForTLog.logw(str, str2, th);
    }

    public void e(String str, String str2) {
        AdapterForTLog.loge(str, str2);
    }

    public void e(String str, String str2, Throwable th) {
        AdapterForTLog.loge(str, str2);
    }

    public boolean isPrintLog(int i) {
        return i >= a(AdapterForTLog.getLogLevel().charAt(0));
    }

    public boolean isValid() {
        return AdapterForTLog.isValid();
    }
}

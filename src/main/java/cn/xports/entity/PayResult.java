package cn.xports.entity;

import android.text.TextUtils;
import com.alipay.sdk.util.h;
import com.alipay.sdk.util.j;

public class PayResult {
    private String memo;
    private String result;
    private String resultStatus;

    public PayResult(String str) {
        if (!TextUtils.isEmpty(str)) {
            for (String str2 : str.split(h.f735b)) {
                if (str2.startsWith(j.f738a)) {
                    this.resultStatus = gatValue(str2, j.f738a);
                }
                if (str2.startsWith(j.f740c)) {
                    this.result = gatValue(str2, j.f740c);
                }
                if (str2.startsWith(j.f739b)) {
                    this.memo = gatValue(str2, j.f739b);
                }
            }
        }
    }

    public String toString() {
        return "resultStatus={" + this.resultStatus + "};memo={" + this.memo + "};result={" + this.result + h.d;
    }

    private String gatValue(String str, String str2) {
        String str3 = str2 + "={";
        return str.substring(str.indexOf(str3) + str3.length(), str.lastIndexOf(h.d));
    }

    public String getResultStatus() {
        return this.resultStatus;
    }

    public String getMemo() {
        return this.memo;
    }

    public String getResult() {
        return this.result;
    }
}

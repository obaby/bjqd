package cn.xports.baselib.http;

import android.util.Log;
import cn.xports.baselib.bean.DataMap;

public class ResponseThrowable extends RuntimeException {
    private int code;
    private DataMap dataMap;
    private boolean error;
    private String message;
    private String throwMessage;

    public DataMap getDataMap() {
        return this.dataMap;
    }

    public ResponseThrowable setDataMap(DataMap dataMap2) {
        this.dataMap = dataMap2;
        return this;
    }

    public ResponseThrowable(Throwable th, int i, String str) {
        super(th);
        this.code = i;
        this.message = str;
    }

    public ResponseThrowable(Throwable th, boolean z, String str) {
        super(th);
        this.error = z;
        this.message = str;
    }

    public ResponseThrowable(Throwable th, int i, String str, String str2) {
        super(th);
        this.code = i;
        this.message = str;
        this.throwMessage = str2;
        Log.e("THROW_ERROR", "" + str2);
    }

    public int getCode() {
        return this.code;
    }

    public boolean isError() {
        return this.error;
    }

    public String getThrowMessage() {
        return this.throwMessage;
    }

    public String getMessage() {
        return this.message;
    }
}

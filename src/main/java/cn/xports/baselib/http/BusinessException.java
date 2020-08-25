package cn.xports.baselib.http;

import cn.xports.baselib.bean.DataMap;

public class BusinessException extends RuntimeException {
    private DataMap dataMap;
    public int errorCode;
    public String message;

    public DataMap getDataMap() {
        return this.dataMap;
    }

    public BusinessException setDataMap(DataMap dataMap2) {
        this.dataMap = dataMap2;
        return this;
    }

    public BusinessException(int i, String str) {
        this.errorCode = i;
        this.message = str;
    }
}

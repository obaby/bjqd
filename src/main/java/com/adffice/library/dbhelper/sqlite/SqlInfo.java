package com.adffice.library.dbhelper.sqlite;

public class SqlInfo {
    private Object[] bindArgs;
    private String sql;

    public String getSql() {
        return this.sql;
    }

    public void setSql(String str) {
        this.sql = str;
    }

    public Object[] getBindArgs() {
        return this.bindArgs;
    }

    public void setBindArgs(Object[] objArr) {
        this.bindArgs = objArr;
    }
}

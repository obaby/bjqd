package anetwork.channel.entity;

import anetwork.channel.Param;

/* compiled from: Taobao */
public class StringParam implements Param {
    private String key;
    private String value;

    public String getKey() {
        return this.key;
    }

    public String getValue() {
        return this.value;
    }

    public StringParam(String str, String str2) {
        this.key = str;
        this.value = str2;
    }
}

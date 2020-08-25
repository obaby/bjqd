package anetwork.channel.entity;

import anetwork.channel.Header;

/* compiled from: Taobao */
public class BasicHeader implements Header {
    private final String name;
    private final String value;

    public BasicHeader(String str, String str2) {
        if (str != null) {
            this.name = str;
            this.value = str2;
            return;
        }
        throw new IllegalArgumentException("Name may not be null");
    }

    public String getName() {
        return this.name;
    }

    public String getValue() {
        return this.value;
    }
}

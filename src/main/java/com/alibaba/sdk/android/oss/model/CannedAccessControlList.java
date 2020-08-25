package com.alibaba.sdk.android.oss.model;

public enum CannedAccessControlList {
    Private("private"),
    PublicRead("public-read"),
    PublicReadWrite("public-read-write");
    
    private String ACLString;

    private CannedAccessControlList(String str) {
        this.ACLString = str;
    }

    public String toString() {
        return this.ACLString;
    }

    public static CannedAccessControlList parseACL(String str) {
        for (CannedAccessControlList cannedAccessControlList : values()) {
            if (cannedAccessControlList.toString().equals(str)) {
                return cannedAccessControlList;
            }
        }
        throw new IllegalArgumentException("Unable to parse the provided acl " + str);
    }
}

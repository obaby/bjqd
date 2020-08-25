package cn.xports.qd2.entity;

import android.net.Uri;
import java.io.Serializable;

public class Avatar implements Serializable {
    private String file;
    private Uri uri;
    private String url;

    public Avatar(String str, Uri uri2) {
        this.file = str;
        this.uri = uri2;
    }

    public String getFile() {
        return this.file;
    }

    public void setFile(String str) {
        this.file = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public Uri getUri() {
        return this.uri;
    }

    public void setUri(Uri uri2) {
        this.uri = uri2;
    }
}

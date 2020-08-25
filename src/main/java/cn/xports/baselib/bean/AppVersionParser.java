package cn.xports.baselib.bean;

import java.io.Serializable;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001c\u0010\n\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\tR\u001c\u0010\r\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u0007\"\u0004\b\u000f\u0010\tR\u001a\u0010\u0010\u001a\u00020\u0011X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006\u0016"}, d2 = {"Lcn/xports/baselib/bean/AppVersionParser;", "Lcn/xports/baselib/bean/Response;", "Ljava/io/Serializable;", "()V", "updateMessage", "", "getUpdateMessage", "()Ljava/lang/String;", "setUpdateMessage", "(Ljava/lang/String;)V", "url", "getUrl", "setUrl", "vensionName", "getVensionName", "setVensionName", "versionCode", "", "getVersionCode", "()I", "setVersionCode", "(I)V", "baselib_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: AppVersionParser.kt */
public final class AppVersionParser extends Response implements Serializable {
    @Nullable
    private String updateMessage;
    @Nullable
    private String url;
    @Nullable
    private String vensionName;
    private int versionCode;

    public final int getVersionCode() {
        return this.versionCode;
    }

    public final void setVersionCode(int i) {
        this.versionCode = i;
    }

    @Nullable
    public final String getVensionName() {
        return this.vensionName;
    }

    public final void setVensionName(@Nullable String str) {
        this.vensionName = str;
    }

    @Nullable
    public final String getUpdateMessage() {
        return this.updateMessage;
    }

    public final void setUpdateMessage(@Nullable String str) {
        this.updateMessage = str;
    }

    @Nullable
    public final String getUrl() {
        return this.url;
    }

    public final void setUrl(@Nullable String str) {
        this.url = str;
    }
}

package com.alibaba.sdk.android.mns.internal;

import anet.channel.util.HttpConstant;
import com.alibaba.sdk.android.common.HttpMethod;
import com.alibaba.sdk.android.common.auth.CredentialProvider;
import com.alibaba.sdk.android.common.utils.HttpdnsMini;
import com.alibaba.sdk.android.mns.common.MNSConstants;
import com.alibaba.sdk.android.mns.common.MNSLog;
import com.alibaba.sdk.android.mns.common.MNSUtils;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class RequestMessage {
    private String content;
    private long contentLength;
    private CredentialProvider credentialProvider;
    private URI endpoint;
    private Map<String, String> headers = new HashMap();
    private boolean isAuthorizationRequired = true;
    private boolean isHttpdnsEnable = true;
    private HttpMethod method;
    private Map<String, String> parameters = new LinkedHashMap();
    private String queueName;
    private String resourcePath;
    private MNSConstants.MNSType type;

    public HttpMethod getMethod() {
        return this.method;
    }

    public void setMethod(HttpMethod httpMethod) {
        this.method = httpMethod;
    }

    public URI getEndpoint() {
        return this.endpoint;
    }

    public CredentialProvider getCredentialProvider() {
        return this.credentialProvider;
    }

    public void setCredentialProvider(CredentialProvider credentialProvider2) {
        this.credentialProvider = credentialProvider2;
    }

    public void setEndpoint(URI uri) {
        this.endpoint = uri;
    }

    public boolean isHttpdnsEnable() {
        return this.isHttpdnsEnable;
    }

    public void setIsHttpdnsEnable(boolean z) {
        this.isHttpdnsEnable = z;
    }

    public String getQueueName() {
        return this.queueName;
    }

    public void setQueueName(String str) {
        this.queueName = str;
    }

    public Map<String, String> getHeaders() {
        return this.headers;
    }

    public void setHeaders(Map<String, String> map) {
        if (map != null) {
            this.headers = map;
        }
    }

    public void addHeaders(Map<String, String> map) {
        if (map != null) {
            this.headers.putAll(map);
        }
    }

    public boolean isAuthorizationRequired() {
        return this.isAuthorizationRequired;
    }

    public void setIsAuthorizationRequired(boolean z) {
        this.isAuthorizationRequired = z;
    }

    public String buildCanonicalURL() {
        MNSUtils.assertTrue(this.endpoint != null, "Endpoint haven't been set!");
        String scheme = this.endpoint.getScheme();
        String host = this.endpoint.getHost();
        String str = null;
        if (this.isHttpdnsEnable) {
            str = HttpdnsMini.getInstance().getIpByHostAsync(host);
        } else {
            MNSLog.logD("[buildCannonicalURL] - proxy exist, disable httpdns");
        }
        if (str == null) {
            str = host;
        }
        this.headers.put("Host", host);
        String str2 = scheme + HttpConstant.SCHEME_SPLIT + str;
        switch (this.type) {
            case QUEUE:
                if (this.queueName == null) {
                    str2 = str2 + "/queues";
                    this.resourcePath = "/queues";
                    break;
                } else {
                    str2 = str2 + "/queues/" + this.queueName;
                    this.resourcePath = "/queues/" + this.queueName;
                    break;
                }
            case MESSAGE:
                str2 = str2 + "/queues/" + this.queueName + "/messages";
                this.resourcePath = "/queues/" + this.queueName + "/messages";
                break;
        }
        String paramToQueryString = MNSUtils.paramToQueryString(this.parameters, "utf-8");
        if (MNSUtils.isEmptyString(paramToQueryString)) {
            return str2;
        }
        this.resourcePath += "?" + paramToQueryString;
        return str2 + "?" + paramToQueryString;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) throws IOException {
        this.content = str;
    }

    public long getContentLength() {
        return this.contentLength;
    }

    public void setResourcePath(String str) {
        this.resourcePath = str;
    }

    public String getResourcePath() {
        return this.resourcePath;
    }

    public void setType(MNSConstants.MNSType mNSType) {
        this.type = mNSType;
    }

    public MNSConstants.MNSType getType() {
        return this.type;
    }

    public Map<String, String> getParameters() {
        return this.parameters;
    }

    public void setParameters(Map<String, String> map) {
        this.parameters = map;
    }
}

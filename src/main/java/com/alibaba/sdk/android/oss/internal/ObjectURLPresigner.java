package com.alibaba.sdk.android.oss.internal;

import anet.channel.util.HttpConstant;
import com.alibaba.android.arouter.utils.Consts;
import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSCustomSignerCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSFederationCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSFederationToken;
import com.alibaba.sdk.android.oss.common.auth.OSSPlainTextAKSKCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSStsTokenCredentialProvider;
import com.alibaba.sdk.android.oss.common.utils.DateUtil;
import com.alibaba.sdk.android.oss.common.utils.HttpUtil;
import com.alibaba.sdk.android.oss.common.utils.OSSUtils;
import java.net.URI;

public class ObjectURLPresigner {
    private ClientConfiguration conf;
    private OSSCredentialProvider credentialProvider;
    private URI endpoint;

    public ObjectURLPresigner(URI uri, OSSCredentialProvider oSSCredentialProvider, ClientConfiguration clientConfiguration) {
        this.endpoint = uri;
        this.credentialProvider = oSSCredentialProvider;
        this.conf = clientConfiguration;
    }

    public String presignConstrainedURL(String str, String str2, long j) throws ClientException {
        OSSFederationToken oSSFederationToken;
        String str3;
        String str4 = "/" + str + "/" + str2;
        String valueOf = String.valueOf((DateUtil.getFixedSkewedTimeMillis() / 1000) + j);
        if (this.credentialProvider instanceof OSSFederationCredentialProvider) {
            oSSFederationToken = ((OSSFederationCredentialProvider) this.credentialProvider).getValidFederationToken();
            if (oSSFederationToken != null) {
                str4 = str4 + "?security-token=" + oSSFederationToken.getSecurityToken();
            } else {
                throw new ClientException("Can not get a federation token!");
            }
        } else if (this.credentialProvider instanceof OSSStsTokenCredentialProvider) {
            oSSFederationToken = ((OSSStsTokenCredentialProvider) this.credentialProvider).getFederationToken();
            str4 = str4 + "?security-token=" + oSSFederationToken.getSecurityToken();
        } else {
            oSSFederationToken = null;
        }
        String str5 = "GET\n\n\n" + valueOf + "\n" + str4;
        if ((this.credentialProvider instanceof OSSFederationCredentialProvider) || (this.credentialProvider instanceof OSSStsTokenCredentialProvider)) {
            str3 = OSSUtils.sign(oSSFederationToken.getTempAK(), oSSFederationToken.getTempSK(), str5);
        } else if (this.credentialProvider instanceof OSSPlainTextAKSKCredentialProvider) {
            str3 = OSSUtils.sign(((OSSPlainTextAKSKCredentialProvider) this.credentialProvider).getAccessKeyId(), ((OSSPlainTextAKSKCredentialProvider) this.credentialProvider).getAccessKeySecret(), str5);
        } else if (this.credentialProvider instanceof OSSCustomSignerCredentialProvider) {
            str3 = ((OSSCustomSignerCredentialProvider) this.credentialProvider).signContent(str5);
        } else {
            throw new ClientException("Unknown credentialProvider!");
        }
        String substring = str3.split(":")[0].substring(4);
        String str6 = str3.split(":")[1];
        String host = this.endpoint.getHost();
        if (!OSSUtils.isCname(host) || OSSUtils.isInCustomCnameExcludeList(host, this.conf.getCustomCnameExcludeList())) {
            host = str + Consts.DOT + host;
        }
        String str7 = this.endpoint.getScheme() + HttpConstant.SCHEME_SPLIT + host + "/" + HttpUtil.urlEncode(str2, "utf-8") + "?OSSAccessKeyId=" + HttpUtil.urlEncode(substring, "utf-8") + "&Expires=" + valueOf + "&Signature=" + HttpUtil.urlEncode(str6, "utf-8");
        if (!(this.credentialProvider instanceof OSSFederationCredentialProvider) && !(this.credentialProvider instanceof OSSStsTokenCredentialProvider)) {
            return str7;
        }
        return str7 + "&security-token=" + HttpUtil.urlEncode(oSSFederationToken.getSecurityToken(), "utf-8");
    }

    public String presignPublicURL(String str, String str2) {
        String host = this.endpoint.getHost();
        if (!OSSUtils.isCname(host) || OSSUtils.isInCustomCnameExcludeList(host, this.conf.getCustomCnameExcludeList())) {
            host = str + Consts.DOT + host;
        }
        return this.endpoint.getScheme() + HttpConstant.SCHEME_SPLIT + host + "/" + HttpUtil.urlEncode(str2, "utf-8");
    }
}

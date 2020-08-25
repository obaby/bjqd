package com.alibaba.sdk.android.mns.common;

import android.util.Pair;
import com.alibaba.sdk.android.common.auth.CredentialProvider;
import com.alibaba.sdk.android.common.auth.CustomSignerCredentialProvider;
import com.alibaba.sdk.android.common.auth.FederationCredentialProvider;
import com.alibaba.sdk.android.common.auth.FederationToken;
import com.alibaba.sdk.android.common.auth.HmacSHA1Signature;
import com.alibaba.sdk.android.common.auth.PlainTextAKSKCredentialProvider;
import com.alibaba.sdk.android.common.auth.StsTokenCredentialProvider;
import com.alibaba.sdk.android.common.utils.HttpUtil;
import com.alibaba.sdk.android.mns.internal.RequestMessage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

public class MNSUtils {
    public static void assertTrue(boolean z, String str) {
        if (!z) {
            throw new IllegalArgumentException(str);
        }
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isEmptyString(String str) {
        return str == null || str.length() == 0;
    }

    public static String paramToQueryString(Map<String, String> map, String str) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (Map.Entry next : map.entrySet()) {
            String str2 = (String) next.getKey();
            String str3 = (String) next.getValue();
            if (!z) {
                sb.append("&");
            }
            sb.append(HttpUtil.urlEncode(str2, str));
            if (!isEmptyString(str3)) {
                sb.append("=");
                sb.append(HttpUtil.urlEncode(str3, str));
            }
            z = false;
        }
        return sb.toString();
    }

    public static void signRequest(RequestMessage requestMessage) throws IOException {
        FederationToken federationToken;
        if (requestMessage.getCredentialProvider() != null) {
            CredentialProvider credentialProvider = requestMessage.getCredentialProvider();
            boolean z = credentialProvider instanceof FederationCredentialProvider;
            Pair pair = null;
            if (z) {
                federationToken = ((FederationCredentialProvider) credentialProvider).getValidFederationToken();
                if (federationToken != null) {
                    requestMessage.getHeaders().put(MNSHeaders.MNS_SECURITY_TOKEN, federationToken.getSecurityToken());
                } else {
                    MNSLog.logE("Can't get a federation token");
                    throw new IOException("Can't get a federation token");
                }
            } else if (credentialProvider instanceof StsTokenCredentialProvider) {
                federationToken = ((StsTokenCredentialProvider) credentialProvider).getFederationToken();
                requestMessage.getHeaders().put(MNSHeaders.MNS_SECURITY_TOKEN, federationToken.getSecurityToken());
            } else {
                federationToken = null;
            }
            String httpMethod = requestMessage.getMethod().toString();
            String str = requestMessage.getHeaders().get("Content-MD5");
            if (str == null) {
                str = "";
            }
            String str2 = requestMessage.getHeaders().get("Content-Type");
            if (str2 == null) {
                str2 = "";
            }
            String str3 = requestMessage.getHeaders().get("Date");
            ArrayList<Pair> arrayList = new ArrayList<>();
            for (String next : requestMessage.getHeaders().keySet()) {
                if (next.toLowerCase().startsWith(MNSHeaders.MNS_PREFIX)) {
                    arrayList.add(new Pair(next.toLowerCase(), requestMessage.getHeaders().get(next)));
                }
            }
            Collections.sort(arrayList, new Comparator<Pair<String, String>>() {
                public int compare(Pair<String, String> pair, Pair<String, String> pair2) {
                    return ((String) pair.first).compareTo((String) pair2.first);
                }
            });
            StringBuilder sb = new StringBuilder();
            for (Pair pair2 : arrayList) {
                if (pair == null) {
                    sb.append(((String) pair2.first) + ":" + ((String) pair2.second));
                } else if (((String) pair.first).equals(pair2.first)) {
                    sb.append("," + ((String) pair2.second));
                } else {
                    sb.append("\n" + ((String) pair2.first) + ":" + ((String) pair2.second));
                }
                pair = pair2;
            }
            String sb2 = sb.toString();
            if (!isEmptyString(sb2)) {
                sb2 = sb2.trim() + "\n";
            }
            String format = String.format("%s\n%s\n%s\n%s\n%s%s", new Object[]{httpMethod, str, str2, str3, sb2, requestMessage.getResourcePath()});
            MNSLog.logI(format);
            String str4 = "---initValue---";
            if (z || (credentialProvider instanceof StsTokenCredentialProvider)) {
                str4 = sign(federationToken.getTempAK(), federationToken.getTempSK(), format);
            } else if (credentialProvider instanceof PlainTextAKSKCredentialProvider) {
                PlainTextAKSKCredentialProvider plainTextAKSKCredentialProvider = (PlainTextAKSKCredentialProvider) credentialProvider;
                str4 = sign(plainTextAKSKCredentialProvider.getAccessKeyId(), plainTextAKSKCredentialProvider.getAccessKeySecret(), format);
            } else if (credentialProvider instanceof CustomSignerCredentialProvider) {
                str4 = ((CustomSignerCredentialProvider) credentialProvider).signContent(format);
            }
            MNSLog.logD("signed content: " + format.replaceAll("\n", "@") + "   ---------   signature: " + str4);
            requestMessage.getHeaders().put("Authorization", str4);
            return;
        }
        throw new IllegalStateException("当前CredentialProvider为空！！！");
    }

    public static String sign(String str, String str2, String str3) {
        try {
            String trim = new HmacSHA1Signature().computeSignature(str2, str3).trim();
            return "MNS " + str + ":" + trim;
        } catch (Exception e) {
            throw new IllegalStateException("Compute signature failed!", e);
        }
    }
}

package com.alibaba.sdk.android.mns.model.deserialize;

import com.alibaba.sdk.android.common.ServiceException;
import com.alibaba.sdk.android.mns.common.MNSConstants;
import com.alibaba.sdk.android.mns.common.MNSHeaders;
import java.io.StringReader;
import okhttp3.Response;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

public class ErrorMessageListDeserializer extends XMLDeserializer<ServiceException> {
    public ServiceException deserialize(Response response) throws Exception {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8;
        Exception e;
        String str9;
        int code = response.code();
        String header = response.header(MNSHeaders.MNS_HEADER_REQUEST_ID);
        String str10 = null;
        try {
            str6 = response.body().string();
            try {
                Element documentElement = getDocumentBuilder().parse(new InputSource(new StringReader(str6))).getDocumentElement();
                if (documentElement == null || !documentElement.getNodeName().equals(MNSConstants.ERROR_TAG)) {
                    str = str6;
                    str3 = header;
                    str5 = null;
                    str4 = null;
                    str2 = null;
                    return new ServiceException(code, str5, str4, str3, str2, str);
                }
                String safeGetElementContent = safeGetElementContent(documentElement, MNSConstants.ERROR_CODE_TAG, "");
                try {
                    str8 = safeGetElementContent(documentElement, "Message", "");
                    try {
                        String safeGetElementContent2 = safeGetElementContent(documentElement, MNSConstants.ERROR_REQUEST_ID_TAG, "");
                        try {
                            str7 = safeGetElementContent(documentElement, MNSConstants.ERROR_HOST_ID_TAG, "");
                            try {
                                return new ServiceException(code, str8, safeGetElementContent, safeGetElementContent2, str7, str6);
                            } catch (Exception e2) {
                                r2 = e2;
                                str10 = safeGetElementContent;
                                header = safeGetElementContent2;
                                r2.printStackTrace();
                                str = str6;
                                str3 = header;
                                str4 = str10;
                                str5 = str8;
                                str2 = str7;
                                return new ServiceException(code, str5, str4, str3, str2, str);
                            }
                        } catch (Exception e3) {
                            r2 = e3;
                            str7 = null;
                            str10 = safeGetElementContent;
                            header = safeGetElementContent2;
                            r2.printStackTrace();
                            str = str6;
                            str3 = header;
                            str4 = str10;
                            str5 = str8;
                            str2 = str7;
                            return new ServiceException(code, str5, str4, str3, str2, str);
                        }
                    } catch (Exception e4) {
                        e = e4;
                        str9 = null;
                        str10 = safeGetElementContent;
                        r2.printStackTrace();
                        str = str6;
                        str3 = header;
                        str4 = str10;
                        str5 = str8;
                        str2 = str7;
                        return new ServiceException(code, str5, str4, str3, str2, str);
                    }
                } catch (Exception e5) {
                    e = e5;
                    str8 = null;
                    str9 = null;
                    str10 = safeGetElementContent;
                    r2.printStackTrace();
                    str = str6;
                    str3 = header;
                    str4 = str10;
                    str5 = str8;
                    str2 = str7;
                    return new ServiceException(code, str5, str4, str3, str2, str);
                }
            } catch (Exception e6) {
                e = e6;
                str8 = null;
                str7 = str8;
                r2.printStackTrace();
                str = str6;
                str3 = header;
                str4 = str10;
                str5 = str8;
                str2 = str7;
                return new ServiceException(code, str5, str4, str3, str2, str);
            }
        } catch (Exception e7) {
            e = e7;
            str6 = null;
            str8 = null;
            str7 = str8;
            r2.printStackTrace();
            str = str6;
            str3 = header;
            str4 = str10;
            str5 = str8;
            str2 = str7;
            return new ServiceException(code, str5, str4, str3, str2, str);
        }
    }
}

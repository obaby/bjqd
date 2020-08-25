package com.alipay.sdk.util;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.cons.a;

public final class k {

    /* renamed from: a  reason: collision with root package name */
    private static final String f741a = "content://com.alipay.android.app.settings.data.ServerProvider/current_server";

    public static String a(Context context) {
        if (EnvUtils.isSandBox()) {
            return a.f665b;
        }
        if (context == null) {
            return a.f664a;
        }
        String str = a.f664a;
        return TextUtils.isEmpty(str) ? a.f664a : str;
    }

    private static String b(Context context) {
        Cursor query = context.getContentResolver().query(Uri.parse(f741a), (String[]) null, (String) null, (String[]) null, (String) null);
        String str = null;
        if (query != null && query.getCount() > 0) {
            if (query.moveToFirst()) {
                str = query.getString(query.getColumnIndex("url"));
            }
            query.close();
        }
        return str;
    }
}

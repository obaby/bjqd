package cn.xports.baselib.util;

import cn.xports.sportCoaching.WebViewDetailActivity;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001\u001a\u0006\u0010\u0003\u001a\u00020\u0001\u001a\u0006\u0010\u0004\u001a\u00020\u0001\u001a\u0006\u0010\u0005\u001a\u00020\u0001\u001a\u000e\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\b\u001a\u0016\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001\u001a\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001¨\u0006\f"}, d2 = {"dateFormat", "", "format", "getCurrentDateTime", "getCurrentTime", "getNowDayAndWeek", "getWeek", "date", "Ljava/util/Date;", "parse2Date", "parse2Time", "", "baselib_release"}, k = 2, mv = {1, 1, 15})
@JvmName(name = "DateUtil")
/* compiled from: DateUtil.kt */
public final class DateUtil {
    @NotNull
    public static final String getNowDayAndWeek() {
        String format = new SimpleDateFormat("yyyy/MM/dd EEEE").format(new Date(System.currentTimeMillis()));
        Intrinsics.checkExpressionValueIsNotNull(format, "sdf.format(Date(System.currentTimeMillis()))");
        return format;
    }

    @NotNull
    public static final String getCurrentTime() {
        String format = new SimpleDateFormat("HH:mm").format(new Date(System.currentTimeMillis()));
        Intrinsics.checkExpressionValueIsNotNull(format, "sdf.format(Date(System.currentTimeMillis()))");
        return format;
    }

    @NotNull
    public static final String getCurrentDateTime() {
        String format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
        Intrinsics.checkExpressionValueIsNotNull(format, "sdf.format(Date(System.currentTimeMillis()))");
        return format;
    }

    @NotNull
    public static final String dateFormat(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "format");
        String format = new SimpleDateFormat(str).format(new Date(System.currentTimeMillis()));
        Intrinsics.checkExpressionValueIsNotNull(format, "sdf.format(Date(System.currentTimeMillis()))");
        return format;
    }

    @NotNull
    public static final Date parse2Date(@NotNull String str, @NotNull String str2) {
        Intrinsics.checkParameterIsNotNull(str, WebViewDetailActivity.DATE);
        Intrinsics.checkParameterIsNotNull(str2, "format");
        try {
            Date parse = new SimpleDateFormat(str2).parse(str);
            Intrinsics.checkExpressionValueIsNotNull(parse, "sdf.parse(date)");
            return parse;
        } catch (Exception unused) {
            return new Date();
        }
    }

    public static final long parse2Time(@NotNull String str, @NotNull String str2) {
        Intrinsics.checkParameterIsNotNull(str, WebViewDetailActivity.DATE);
        Intrinsics.checkParameterIsNotNull(str2, "format");
        try {
            Date parse = new SimpleDateFormat(str2).parse(str);
            Intrinsics.checkExpressionValueIsNotNull(parse, "sdf.parse(date)");
            return parse.getTime();
        } catch (Exception unused) {
            return new Date().getTime();
        }
    }

    @NotNull
    public static final String getWeek(@NotNull Date date) {
        Intrinsics.checkParameterIsNotNull(date, WebViewDetailActivity.DATE);
        Calendar instance = Calendar.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(instance, "ca");
        instance.setTime(date);
        switch (instance.get(7)) {
            case 1:
                return "周日";
            case 2:
                return "周一";
            case 3:
                return "周二";
            case 4:
                return "周三";
            case 5:
                return "周四";
            case 6:
                return "周五";
            default:
                return "周六";
        }
    }
}

package cn.xports.field;

import android.widget.LinearLayout;
import cn.xports.baselib.mvp.IModel;
import cn.xports.baselib.mvp.IView;
import cn.xports.entity.BookingDay;
import cn.xports.entity.BookingRule;
import cn.xports.entity.FieldSegment;
import cn.xports.entity.FieldTypeBean;
import cn.xports.entity.OrderInfo;
import cn.xports.parser.BookDayParser;
import cn.xports.parser.BookRuleParser;
import cn.xports.parser.FieldParser;
import cn.xports.parser.FieldTypeParser;
import io.reactivex.Observable;
import java.util.List;
import kotlin.Metadata;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0005"}, d2 = {"Lcn/xports/field/FieldBookContract;", "", "()V", "Model", "View", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: FieldBookContract.kt */
public final class FieldBookContract {

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H&J\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\u0006\u0010\u0007\u001a\u00020\u0006H&J.\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00032\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H&J&\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u00032\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H&J\u001e\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u00032\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u0006H&J6\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u00032\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H&¨\u0006\u0017"}, d2 = {"Lcn/xports/field/FieldBookContract$Model;", "Lcn/xports/baselib/mvp/IModel;", "getBookDay", "Lio/reactivex/Observable;", "Lcn/xports/parser/BookDayParser;", "serviceId", "", "venueId", "getBookRule", "Lcn/xports/parser/BookRuleParser;", "getFieldList", "Lcn/xports/parser/FieldParser;", "date", "fieldType", "", "getFieldTypeList", "Lcn/xports/parser/FieldTypeParser;", "getVenuParam", "Lokhttp3/ResponseBody;", "paramCode", "orderField", "Lcn/xports/entity/OrderInfo;", "fieldInfo", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
    /* compiled from: FieldBookContract.kt */
    public interface Model extends IModel {
        @NotNull
        Observable<BookDayParser> getBookDay(@NotNull String str, @NotNull String str2);

        @NotNull
        Observable<BookRuleParser> getBookRule(@NotNull String str);

        @NotNull
        Observable<FieldParser> getFieldList(@NotNull String str, int i, @NotNull String str2, @NotNull String str3);

        @NotNull
        Observable<FieldTypeParser> getFieldTypeList(@NotNull String str, @NotNull String str2, @NotNull String str3);

        @NotNull
        Observable<ResponseBody> getVenuParam(@NotNull String str, @NotNull String str2);

        @NotNull
        Observable<OrderInfo> orderField(@NotNull String str, int i, @NotNull String str2, @NotNull String str3, @NotNull String str4);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&J&\u0010\n\u001a\u00020\u00032\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\tH&J\u0018\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0007H&J\u0010\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u0007H&J!\u0010\u0015\u001a\u00020\u00032\u0012\u0010\u0016\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00070\u0017\"\u00020\u0007H&¢\u0006\u0002\u0010\u0018J\u0016\u0010\u0019\u001a\u00020\u00032\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\fH&J\u0016\u0010\u001c\u001a\u00020\u00032\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001e0\fH&J\u001e\u0010\u001f\u001a\u00020\u00032\u0006\u0010 \u001a\u00020\u00072\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\"0\fH&J\u0010\u0010#\u001a\u00020\u00032\u0006\u0010$\u001a\u00020%H&J\u0012\u0010&\u001a\u00020\u00032\b\u0010'\u001a\u0004\u0018\u00010\u0007H&¨\u0006("}, d2 = {"Lcn/xports/field/FieldBookContract$View;", "Lcn/xports/baselib/mvp/IView;", "addFieldName", "", "layoutParams", "Landroid/widget/LinearLayout$LayoutParams;", "fieldName", "", "padding", "", "addLineField", "singleLineFieldList", "", "Lcn/xports/entity/FieldSegment;", "fieldLayoutParams", "paddingTop", "addTimeView", "timeLayoutParams", "startTime", "onGetTradeId", "tradeId", "saveParam", "params", "", "([Ljava/lang/String;)V", "saveRule", "rules", "Lcn/xports/entity/BookingRule;", "showBookDay", "bookDays", "Lcn/xports/entity/BookingDay;", "showFieldTypes", "hasFull", "fieldTypeList", "Lcn/xports/entity/FieldTypeBean;", "showFieldView", "hasField", "", "showTopTip", "manual", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
    /* compiled from: FieldBookContract.kt */
    public interface View extends IView {
        void addFieldName(@NotNull LinearLayout.LayoutParams layoutParams, @NotNull String str, int i);

        void addLineField(@NotNull List<? extends FieldSegment> list, @NotNull LinearLayout.LayoutParams layoutParams, int i);

        void addTimeView(@NotNull LinearLayout.LayoutParams layoutParams, @NotNull String str);

        void onGetTradeId(@NotNull String str);

        void saveParam(@NotNull String... strArr);

        void saveRule(@NotNull List<? extends BookingRule> list);

        void showBookDay(@NotNull List<? extends BookingDay> list);

        void showFieldTypes(@NotNull String str, @NotNull List<? extends FieldTypeBean> list);

        void showFieldView(boolean z);

        void showTopTip(@Nullable String str);
    }
}

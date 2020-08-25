package cn.xports.field;

import android.text.TextUtils;
import android.widget.LinearLayout;
import cn.xports.baselib.mvp.BasePresenter;
import cn.xports.baselib.util.DensityUtil;
import cn.xports.baselib.util.SegmentUtils;
import cn.xports.entity.BookingRule;
import cn.xports.entity.Field;
import cn.xports.entity.FieldSegment;
import cn.xports.field.FieldBookContract;
import cn.xports.parser.FieldParser;
import cn.xports.qd2.entity.K;
import cn.xports.sportCoaching.WebViewDetailActivity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\t\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J&\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\bJ:\u0010\u000f\u001a\u00020\b2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\b\u0010\f\u001a\u0004\u0018\u00010\rJ\u001e\u0010\u0013\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\b\u0010\f\u001a\u0004\u0018\u00010\rJ\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u0016\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001aJ\u000e\u0010\u001c\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u001aJ&\u0010\u001d\u001a\u00020\u00152\u0006\u0010\u001e\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001aJ\u001e\u0010!\u001a\u00020\u00152\u0006\u0010\u001e\u001a\u00020\u001a2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001aJ\u0016\u0010\"\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u0019\u001a\u00020\u001aJ\u001c\u0010#\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0002J\u001e\u0010%\u001a\u00020\b2\u0006\u0010&\u001a\u00020\u000b2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0002J4\u0010'\u001a\u00020\u00152\u0006\u0010\u001e\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020 2\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u001a0\n2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001a¨\u0006)"}, d2 = {"Lcn/xports/field/FieldBookPresenter;", "Lcn/xports/baselib/mvp/BasePresenter;", "Lcn/xports/field/FieldBookContract$Model;", "Lcn/xports/field/FieldBookContract$View;", "model", "view", "(Lcn/xports/field/FieldBookContract$Model;Lcn/xports/field/FieldBookContract$View;)V", "checkRule", "", "segments", "", "Lcn/xports/entity/FieldSegment;", "rules", "Lcn/xports/entity/BookingRule;", "isCheck", "checkRules", "fieldSegments", "effectiveFieldSegments", "defaultEffectiveFieldSegments", "checkSubmitRule", "drawFields", "", "fieldParser", "Lcn/xports/parser/FieldParser;", "getBookDays", "serviceId", "", "venueId", "getBookRule", "getFieldList", "date", "fieldType", "", "getFieldTypes", "getParams", "getSortField", "fieldList", "isPiecesDefault", "fieldSegment", "orderField", "fieldInfos", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: FieldBookPresenter.kt */
public final class FieldBookPresenter extends BasePresenter<FieldBookContract.Model, FieldBookContract.View> {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FieldBookPresenter(@NotNull FieldBookContract.Model model, @NotNull FieldBookContract.View view) {
        super(model, view);
        Intrinsics.checkParameterIsNotNull(model, "model");
        Intrinsics.checkParameterIsNotNull(view, "view");
    }

    public final void getParams(@NotNull String str, @NotNull String str2) {
        Intrinsics.checkParameterIsNotNull(str, K.venueId);
        Intrinsics.checkParameterIsNotNull(str2, K.serviceId);
        FieldBookContract.Model model = (FieldBookContract.Model) getModel();
        if (model != null) {
            model.getVenuParam(str, "wechat_default_field_full_tag,wechat_court_message_" + str2).subscribe(new FieldBookPresenter$getParams$$inlined$apply$lambda$1(getTAG(), this, str, str2));
        }
    }

    public final void orderField(@NotNull String str, int i, @NotNull List<String> list, @NotNull String str2, @NotNull String str3) {
        List<String> list2 = list;
        Intrinsics.checkParameterIsNotNull(str, WebViewDetailActivity.DATE);
        Intrinsics.checkParameterIsNotNull(list2, "fieldInfos");
        Intrinsics.checkParameterIsNotNull(str2, K.serviceId);
        Intrinsics.checkParameterIsNotNull(str3, K.venueId);
        String joinToString$default = CollectionsKt.joinToString$default(list2, ",", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
        FieldBookContract.Model model = (FieldBookContract.Model) getModel();
        if (model != null) {
            model.orderField(str, i, joinToString$default, str2, str3).subscribe(new FieldBookPresenter$orderField$$inlined$apply$lambda$1(getTAG(), this, str, i, joinToString$default, str2, str3));
        }
    }

    public final void getBookDays(@NotNull String str, @NotNull String str2) {
        Intrinsics.checkParameterIsNotNull(str, K.serviceId);
        Intrinsics.checkParameterIsNotNull(str2, K.venueId);
        FieldBookContract.Model model = (FieldBookContract.Model) getModel();
        if (model != null) {
            model.getBookDay(str, str2).subscribe(new FieldBookPresenter$getBookDays$$inlined$apply$lambda$1(getTAG(), this, str, str2));
        }
    }

    public final void getBookRule(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, K.venueId);
        FieldBookContract.Model model = (FieldBookContract.Model) getModel();
        if (model != null) {
            model.getBookRule(str).subscribe(new FieldBookPresenter$getBookRule$$inlined$apply$lambda$1(getTAG(), this, str));
        }
    }

    public final void getFieldTypes(@NotNull String str, @NotNull String str2, @NotNull String str3) {
        Intrinsics.checkParameterIsNotNull(str, WebViewDetailActivity.DATE);
        Intrinsics.checkParameterIsNotNull(str2, K.serviceId);
        Intrinsics.checkParameterIsNotNull(str3, K.venueId);
        FieldBookContract.Model model = (FieldBookContract.Model) getModel();
        if (model != null) {
            model.getFieldTypeList(str, str2, str3).subscribe(new FieldBookPresenter$getFieldTypes$$inlined$apply$lambda$1(getTAG(), this, str, str2, str3));
        }
    }

    public final void getFieldList(@NotNull String str, int i, @NotNull String str2, @NotNull String str3) {
        Intrinsics.checkParameterIsNotNull(str, WebViewDetailActivity.DATE);
        Intrinsics.checkParameterIsNotNull(str2, K.serviceId);
        Intrinsics.checkParameterIsNotNull(str3, K.venueId);
        FieldBookContract.Model model = (FieldBookContract.Model) getModel();
        if (model != null) {
            FieldBookContract.View view = (FieldBookContract.View) getRootView();
            if (view != null) {
                view.showLoading();
            }
            model.getFieldList(str, i, str2, str3).subscribe(new FieldBookPresenter$getFieldList$$inlined$apply$lambda$1(getTAG(), this, str, i, str2, str3));
        }
    }

    /* access modifiers changed from: private */
    public final void drawFields(FieldParser fieldParser) {
        FieldBookContract.View view;
        List<Field> list;
        FieldBookContract.View view2 = (FieldBookContract.View) getRootView();
        if (view2 != null) {
            view2.hideNoData();
        }
        int dp2px = DensityUtil.dp2px(48.0f);
        int dp2px2 = DensityUtil.dp2px(38.0f);
        int dp2px3 = DensityUtil.dp2px(4.0f);
        List<Field> fieldList = fieldParser.getFieldList();
        FieldBookContract.View view3 = (FieldBookContract.View) getRootView();
        int i = 0;
        if (view3 != null) {
            view3.showFieldView(fieldList.size() > 0);
        }
        if (fieldList.size() != 0) {
            int size = fieldList.size();
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(dp2px, dp2px2);
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(dp2px, dp2px2);
            layoutParams2.setMargins(dp2px3, dp2px3, 0, 0);
            int i2 = 0;
            boolean z = false;
            while (i2 < size) {
                Field field = fieldList.get(i2);
                Intrinsics.checkExpressionValueIsNotNull(field, "singleFieldLineInfo");
                int size2 = field.getFieldSegmentList().size();
                if (!z && size2 > 0) {
                    z = true;
                }
                ArrayList arrayList = new ArrayList();
                int i3 = 0;
                while (i3 < size2) {
                    FieldSegment fieldSegment = field.getFieldSegmentList().get(i3);
                    if (i2 == 0) {
                        layoutParams.setMargins(dp2px3, dp2px3, i, i);
                        FieldBookContract.View view4 = (FieldBookContract.View) getRootView();
                        if (view4 != null) {
                            String startTime = SegmentUtils.getStartTime(fieldSegment.getSegment());
                            list = fieldList;
                            Intrinsics.checkExpressionValueIsNotNull(startTime, "SegmentUtils.getStartTim…ieldSegment.getSegment())");
                            view4.addTimeView(layoutParams, startTime);
                            Intrinsics.checkExpressionValueIsNotNull(fieldSegment, "fieldSegment");
                            fieldSegment.setStartSegment(fieldSegment.getSegment());
                            fieldSegment.setEndSegment(fieldSegment.getSegment() + fieldSegment.getStep());
                            fieldSegment.setFieldId(field.getFieldId());
                            fieldSegment.setFieldName(field.getFieldName());
                            fieldSegment.setFieldNo(field.getFieldNo());
                            arrayList.add(fieldSegment);
                            i3++;
                            fieldList = list;
                            i = 0;
                        }
                    }
                    list = fieldList;
                    Intrinsics.checkExpressionValueIsNotNull(fieldSegment, "fieldSegment");
                    fieldSegment.setStartSegment(fieldSegment.getSegment());
                    fieldSegment.setEndSegment(fieldSegment.getSegment() + fieldSegment.getStep());
                    fieldSegment.setFieldId(field.getFieldId());
                    fieldSegment.setFieldName(field.getFieldName());
                    fieldSegment.setFieldNo(field.getFieldNo());
                    arrayList.add(fieldSegment);
                    i3++;
                    fieldList = list;
                    i = 0;
                }
                List<Field> list2 = fieldList;
                LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(dp2px, dp2px2);
                layoutParams3.setMargins(dp2px3, dp2px3, 0, dp2px3);
                String fieldName = field.getFieldName();
                if (!TextUtils.isEmpty(fieldName)) {
                    FieldBookContract.View view5 = (FieldBookContract.View) getRootView();
                    if (view5 != null) {
                        Intrinsics.checkExpressionValueIsNotNull(fieldName, "fieldName");
                        view5.addFieldName(layoutParams3, fieldName, dp2px3);
                    }
                } else {
                    FieldBookContract.View view6 = (FieldBookContract.View) getRootView();
                    if (view6 != null) {
                        view6.addFieldName(layoutParams3, field.getFieldNo() + "号场", dp2px3);
                    }
                }
                FieldBookContract.View view7 = (FieldBookContract.View) getRootView();
                if (view7 != null) {
                    view7.addLineField(arrayList, layoutParams2, dp2px3);
                }
                if (!z && (view = (FieldBookContract.View) getRootView()) != null) {
                    view.showNoData("暂无场地数据");
                }
                i2++;
                fieldList = list2;
                i = 0;
            }
        }
    }

    public final boolean checkRule(@NotNull List<? extends FieldSegment> list, @Nullable BookingRule bookingRule, boolean z) {
        List<? extends FieldSegment> list2 = list;
        Intrinsics.checkParameterIsNotNull(list2, "segments");
        if (bookingRule == null || list.size() <= 1) {
            return true;
        }
        FieldSegment fieldSegment = (FieldSegment) list2.get(CollectionsKt.getLastIndex(list));
        int maxField = bookingRule.getMaxField();
        int totalHour = bookingRule.getTotalHour() / fieldSegment.getStep();
        int maxHour = bookingRule.getMaxHour() / fieldSegment.getStep();
        int minSerialNum = bookingRule.getMinSerialNum() / fieldSegment.getStep();
        int piecesUnit = bookingRule.getPiecesUnit();
        if (z) {
            if (list.size() > maxField) {
                FieldBookContract.View view = (FieldBookContract.View) getRootView();
                if (view != null) {
                    view.showMsg("最多选" + maxField + "片场地");
                }
                return false;
            } else if (list.size() > totalHour) {
                FieldBookContract.View view2 = (FieldBookContract.View) getRootView();
                if (view2 != null) {
                    view2.showMsg("最多选" + (((float) bookingRule.getTotalHour()) / 2.0f) + "小时");
                }
                return false;
            }
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int fieldNo = fieldSegment.getFieldNo();
        int fieldNo2 = ((FieldSegment) list2.get(CollectionsKt.getLastIndex(list) - 1)).getFieldNo();
        for (FieldSegment fieldSegment2 : list2) {
            if (fieldSegment2.getFieldNo() == fieldNo) {
                arrayList.add(fieldSegment2);
            } else if (fieldSegment2.getFieldNo() == fieldNo2) {
                arrayList2.add(fieldSegment2);
            }
        }
        if (!z || arrayList.size() <= maxHour) {
            List list3 = arrayList;
            if (list3.size() > 1) {
                CollectionsKt.sortWith(list3, new FieldBookPresenter$$special$$inlined$sortBy$1());
            }
            if (!z) {
                arrayList.remove(fieldSegment);
                if (arrayList.size() < minSerialNum) {
                    FieldBookContract.View view3 = (FieldBookContract.View) getRootView();
                    if (view3 != null) {
                        view3.showMsg("最小连续订" + (((float) bookingRule.getMinSerialNum()) / 2.0f) + "小时");
                    }
                    return false;
                }
                ArrayList<ArrayList> arrayList3 = new ArrayList<>();
                ArrayList arrayList4 = new ArrayList();
                arrayList4.add(arrayList.get(0));
                arrayList3.add(arrayList4);
                int size = arrayList.size();
                ArrayList arrayList5 = arrayList4;
                for (int i = 0; i < size; i++) {
                    if (i < arrayList.size() - 1) {
                        int i2 = i + 1;
                        Object obj = arrayList.get(i2);
                        Intrinsics.checkExpressionValueIsNotNull(obj, "tempSegments[j + 1]");
                        int startSegment = ((FieldSegment) obj).getStartSegment();
                        Object obj2 = arrayList.get(i);
                        Intrinsics.checkExpressionValueIsNotNull(obj2, "tempSegments[j]");
                        int endSegment = startSegment - ((FieldSegment) obj2).getEndSegment();
                        if (endSegment == 0) {
                            arrayList5.add(arrayList.get(i2));
                        } else if (endSegment <= piecesUnit) {
                            FieldBookContract.View view4 = (FieldBookContract.View) getRootView();
                            if (view4 != null) {
                                view4.showMsg("时间间隔要大于" + (((float) piecesUnit) / 2.0f) + "小时");
                            }
                            return false;
                        } else {
                            arrayList5 = new ArrayList();
                            arrayList3.add(arrayList5);
                            arrayList5.add(arrayList.get(i2));
                        }
                    }
                }
                for (ArrayList size2 : arrayList3) {
                    if (size2.size() < minSerialNum) {
                        FieldBookContract.View view5 = (FieldBookContract.View) getRootView();
                        if (view5 != null) {
                            view5.showMsg("最小连续订" + (((float) bookingRule.getMinSerialNum()) / 2.0f) + "小时");
                        }
                        return false;
                    }
                }
                return true;
            }
            if (piecesUnit > 0 && arrayList.size() > 1) {
                Object obj3 = arrayList.get(CollectionsKt.getLastIndex(list3));
                Intrinsics.checkExpressionValueIsNotNull(obj3, "tempSegments[tempSegments.lastIndex]");
                int startSegment2 = ((FieldSegment) obj3).getStartSegment();
                Object obj4 = arrayList.get(CollectionsKt.getLastIndex(list3) - 1);
                Intrinsics.checkExpressionValueIsNotNull(obj4, "tempSegments[tempSegments.lastIndex - 1]");
                if (Math.abs(startSegment2 - ((FieldSegment) obj4).getEndSegment()) <= piecesUnit) {
                    FieldBookContract.View view6 = (FieldBookContract.View) getRootView();
                    if (view6 != null) {
                        view6.showMsg("时间间隔要大于" + (((float) piecesUnit) / 2.0f) + "小时");
                    }
                    return false;
                }
            }
            ArrayList arrayList6 = new ArrayList();
            ArrayList arrayList7 = new ArrayList();
            if (fieldNo == fieldNo2 || list.size() - 1 >= minSerialNum) {
                arrayList7.add(arrayList.get(0));
                arrayList6.add(arrayList7);
                int size3 = arrayList.size();
                ArrayList arrayList8 = arrayList7;
                for (int i3 = 0; i3 < size3; i3++) {
                    if (i3 < arrayList.size() - 1) {
                        int i4 = i3 + 1;
                        Object obj5 = arrayList.get(i4);
                        Intrinsics.checkExpressionValueIsNotNull(obj5, "tempSegments[j + 1]");
                        int startSegment3 = ((FieldSegment) obj5).getStartSegment();
                        Object obj6 = arrayList.get(i3);
                        Intrinsics.checkExpressionValueIsNotNull(obj6, "tempSegments[j]");
                        if (startSegment3 != ((FieldSegment) obj6).getEndSegment()) {
                            arrayList8 = new ArrayList();
                            arrayList6.add(arrayList8);
                            arrayList8.add(arrayList.get(i4));
                        } else {
                            arrayList8.add(arrayList.get(i4));
                        }
                    }
                }
                Iterable<ArrayList> iterable = arrayList6;
                for (ArrayList arrayList9 : iterable) {
                    if (arrayList9.size() < minSerialNum && !arrayList9.contains(fieldSegment)) {
                        FieldBookContract.View view7 = (FieldBookContract.View) getRootView();
                        if (view7 == null) {
                            return false;
                        }
                        view7.showMsg("最小连续订" + (((float) bookingRule.getMinSerialNum()) / 2.0f) + "小时");
                        return false;
                    }
                }
                if (fieldNo == fieldNo2) {
                    return true;
                }
                List list4 = arrayList2;
                if (list4.size() > 1) {
                    CollectionsKt.sortWith(list4, new FieldBookPresenter$$special$$inlined$sortBy$2());
                }
                arrayList6.clear();
                arrayList8.clear();
                arrayList8.add(arrayList2.get(0));
                arrayList6.add(arrayList8);
                int size4 = arrayList2.size();
                for (int i5 = 0; i5 < size4; i5++) {
                    if (i5 < arrayList2.size() - 1) {
                        int i6 = i5 + 1;
                        Object obj7 = arrayList2.get(i6);
                        Intrinsics.checkExpressionValueIsNotNull(obj7, "seTempSegments[j + 1]");
                        int startSegment4 = ((FieldSegment) obj7).getStartSegment();
                        Object obj8 = arrayList2.get(i5);
                        Intrinsics.checkExpressionValueIsNotNull(obj8, "seTempSegments[j]");
                        if (startSegment4 != ((FieldSegment) obj8).getEndSegment()) {
                            arrayList8 = new ArrayList();
                            arrayList6.add(arrayList8);
                            arrayList8.add(arrayList2.get(i6));
                        } else {
                            arrayList8.add(arrayList2.get(i6));
                        }
                    }
                }
                for (ArrayList size5 : iterable) {
                    if (size5.size() < minSerialNum) {
                        FieldBookContract.View view8 = (FieldBookContract.View) getRootView();
                        if (view8 == null) {
                            return false;
                        }
                        view8.showMsg("最小连续订" + (((float) bookingRule.getMinSerialNum()) / 2.0f) + "小时");
                        return false;
                    }
                }
                return true;
            }
            FieldBookContract.View view9 = (FieldBookContract.View) getRootView();
            if (view9 != null) {
                view9.showMsg("最小连续订" + (((float) bookingRule.getMinSerialNum()) / 2.0f) + "小时");
            }
            return false;
        }
        FieldBookContract.View view10 = (FieldBookContract.View) getRootView();
        if (view10 != null) {
            view10.showMsg("单片场最多选" + (((float) bookingRule.getMaxHour()) / 2.0f) + "小时");
        }
        return false;
    }

    public final boolean checkSubmitRule(@NotNull List<? extends FieldSegment> list, @Nullable BookingRule bookingRule) {
        Intrinsics.checkParameterIsNotNull(list, "segments");
        if (bookingRule != null) {
            if (list.isEmpty()) {
                FieldBookContract.View view = (FieldBookContract.View) getRootView();
                if (view != null) {
                    view.showMsg("请选择场地");
                }
                return false;
            }
            int minSerialNum = bookingRule.getMinSerialNum() / ((FieldSegment) list.get(0)).getStep();
            if (list.size() < minSerialNum) {
                FieldBookContract.View view2 = (FieldBookContract.View) getRootView();
                if (view2 != null) {
                    view2.showMsg("最小连续订" + (((float) bookingRule.getMinSerialNum()) / 2.0f) + "小时");
                }
                return false;
            }
            ArrayList arrayList = new ArrayList();
            int fieldNo = ((FieldSegment) list.get(CollectionsKt.getLastIndex(list))).getFieldNo();
            for (FieldSegment fieldSegment : list) {
                if (fieldSegment.getFieldNo() == fieldNo) {
                    arrayList.add(fieldSegment);
                }
            }
            List list2 = arrayList;
            if (list2.size() > 1) {
                CollectionsKt.sortWith(list2, new FieldBookPresenter$$special$$inlined$sortBy$3());
            }
            if (arrayList.size() < minSerialNum) {
                FieldBookContract.View view3 = (FieldBookContract.View) getRootView();
                if (view3 != null) {
                    view3.showMsg("最小连续订" + (((float) bookingRule.getMinSerialNum()) / 2.0f) + "小时");
                }
                return false;
            }
            ArrayList<ArrayList> arrayList2 = new ArrayList<>();
            ArrayList arrayList3 = new ArrayList();
            arrayList3.add(arrayList.get(0));
            arrayList2.add(arrayList3);
            int size = arrayList.size();
            ArrayList arrayList4 = arrayList3;
            for (int i = 0; i < size; i++) {
                if (i < arrayList.size() - 1) {
                    int i2 = i + 1;
                    Object obj = arrayList.get(i2);
                    Intrinsics.checkExpressionValueIsNotNull(obj, "tempSegments[j + 1]");
                    int startSegment = ((FieldSegment) obj).getStartSegment();
                    Object obj2 = arrayList.get(i);
                    Intrinsics.checkExpressionValueIsNotNull(obj2, "tempSegments[j]");
                    if (startSegment != ((FieldSegment) obj2).getEndSegment()) {
                        arrayList4 = new ArrayList();
                        arrayList2.add(arrayList4);
                        arrayList4.add(arrayList.get(i2));
                    } else {
                        arrayList4.add(arrayList.get(i2));
                    }
                }
            }
            for (ArrayList size2 : arrayList2) {
                if (size2.size() < minSerialNum) {
                    FieldBookContract.View view4 = (FieldBookContract.View) getRootView();
                    if (view4 != null) {
                        view4.showMsg("最小连续订" + (((float) bookingRule.getMinSerialNum()) / 2.0f) + "小时");
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public final boolean checkRules(@NotNull List<? extends FieldSegment> list, @NotNull List<? extends FieldSegment> list2, @NotNull List<? extends FieldSegment> list3, @Nullable BookingRule bookingRule) {
        Intrinsics.checkParameterIsNotNull(list, "fieldSegments");
        Intrinsics.checkParameterIsNotNull(list2, "effectiveFieldSegments");
        Intrinsics.checkParameterIsNotNull(list3, "defaultEffectiveFieldSegments");
        if (list.isEmpty()) {
            FieldBookContract.View view = (FieldBookContract.View) getRootView();
            if (view != null) {
                view.showMsg("请先选择场地");
            }
            return false;
        } else if (bookingRule == null) {
            FieldBookContract.View view2 = (FieldBookContract.View) getRootView();
            if (view2 != null) {
                view2.showMsg("未配置订场规则，请联系场馆");
            }
            return false;
        } else {
            int maxField = bookingRule.getMaxField();
            int totalHour = bookingRule.getTotalHour();
            int maxHour = bookingRule.getMaxHour();
            int minSerialNum = bookingRule.getMinSerialNum();
            int piecesUnit = bookingRule.getPiecesUnit();
            List<FieldSegment> sortField = getSortField(list);
            if (sortField.size() > maxField) {
                FieldBookContract.View view3 = (FieldBookContract.View) getRootView();
                if (view3 != null) {
                    view3.showMsg("预订场地不能超过" + maxField + "片");
                }
                return false;
            }
            Collection collection = sortField;
            int size = collection.size();
            int i = 0;
            for (int i2 = 0; i2 < size; i2++) {
                i += sortField.get(i2).getEndSegment() - sortField.get(i2).getStartSegment();
                if (i > totalHour) {
                    FieldBookContract.View view4 = (FieldBookContract.View) getRootView();
                    if (view4 != null) {
                        view4.showMsg("预定总时间不能超过" + (((float) totalHour) / 2.0f) + "小时");
                    }
                    return false;
                }
            }
            ArrayList arrayList = new ArrayList();
            Map linkedHashMap = new LinkedHashMap();
            for (Object next : sortField) {
                Long valueOf = Long.valueOf(((FieldSegment) next).getFieldId());
                Object obj = linkedHashMap.get(valueOf);
                if (obj == null) {
                    obj = new ArrayList();
                    linkedHashMap.put(valueOf, obj);
                }
                ((List) obj).add(next);
            }
            for (Map.Entry value : linkedHashMap.entrySet()) {
                int i3 = 0;
                for (FieldSegment fieldSegment : (List) value.getValue()) {
                    i3 += fieldSegment.getEndSegment() - fieldSegment.getStartSegment();
                }
                arrayList.add(Integer.valueOf(i3));
            }
            int size2 = arrayList.size();
            for (int i4 = 0; i4 < size2; i4++) {
                if (Intrinsics.compare(((Number) arrayList.get(i4)).intValue(), maxHour) > 0) {
                    FieldBookContract.View view5 = (FieldBookContract.View) getRootView();
                    if (view5 != null) {
                        view5.showMsg("每片场地不能预订超过" + (((float) maxHour) / 2.0f) + "个小时");
                    }
                    return false;
                }
            }
            int size3 = collection.size();
            for (int i5 = 0; i5 < size3; i5++) {
                if (sortField.get(i5).getEndSegment() - sortField.get(i5).getStartSegment() < minSerialNum) {
                    FieldBookContract.View view6 = (FieldBookContract.View) getRootView();
                    if (view6 != null) {
                        view6.showMsg("每片场至少预订" + (((float) minSerialNum) / 2.0f) + "小时的连续时间");
                    }
                    return false;
                }
            }
            List<FieldSegment> sortField2 = getSortField(list2);
            int size4 = sortField2.size();
            int i6 = 0;
            while (i6 < size4) {
                if (sortField2.get(i6).getEndSegment() - sortField2.get(i6).getStartSegment() > piecesUnit || isPiecesDefault(sortField2.get(i6), list3)) {
                    i6++;
                } else {
                    FieldBookContract.View view7 = (FieldBookContract.View) getRootView();
                    if (view7 != null) {
                        view7.showMsg("中间或旁边不要留" + (((float) piecesUnit) / 2.0f) + "小时的空隙");
                    }
                    return false;
                }
            }
            return true;
        }
    }

    private final List<FieldSegment> getSortField(List<? extends FieldSegment> list) {
        ArrayList arrayList = new ArrayList();
        List sortedWith = CollectionsKt.sortedWith(list, ComparisonsKt.compareBy(new Function1[]{(Function1) FieldBookPresenter$getSortField$checkFieldSegmentList$1.INSTANCE, (Function1) FieldBookPresenter$getSortField$checkFieldSegmentList$2.INSTANCE}));
        if (sortedWith.isEmpty()) {
            return arrayList;
        }
        FieldSegment fieldSegment = new FieldSegment((FieldSegment) sortedWith.get(0));
        int size = sortedWith.size();
        for (int i = 0; i < size; i++) {
            if (i < sortedWith.size() - 1) {
                FieldSegment fieldSegment2 = (FieldSegment) sortedWith.get(i);
                FieldSegment fieldSegment3 = (FieldSegment) sortedWith.get(i + 1);
                if (fieldSegment2.getFieldId() == fieldSegment3.getFieldId() && fieldSegment2.getEndSegment() == fieldSegment3.getStartSegment()) {
                    fieldSegment.setEndSegment(fieldSegment3.getEndSegment());
                    fieldSegment.setPrice(fieldSegment.getPrice() + fieldSegment3.getPrice());
                    fieldSegment.setStep(fieldSegment.getStep() + fieldSegment3.getStep());
                } else {
                    arrayList.add(fieldSegment);
                    fieldSegment = new FieldSegment();
                    fieldSegment.setFieldNo(fieldSegment3.getFieldNo());
                    fieldSegment.setFieldName(fieldSegment3.getFieldName());
                    fieldSegment.setFieldId(fieldSegment3.getFieldId());
                    fieldSegment.setPrice(fieldSegment3.getPrice());
                    fieldSegment.setState(fieldSegment3.getState());
                    fieldSegment.setStartSegment(fieldSegment3.getStartSegment());
                    fieldSegment.setEndSegment(fieldSegment3.getEndSegment());
                    fieldSegment.setStep(fieldSegment3.getStep());
                }
            } else {
                arrayList.add(fieldSegment);
            }
        }
        return arrayList;
    }

    private final boolean isPiecesDefault(FieldSegment fieldSegment, List<? extends FieldSegment> list) {
        return getSortField(list).contains(fieldSegment);
    }
}

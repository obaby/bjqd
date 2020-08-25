package cn.xports.qd2.fragment;

import cn.xports.baselib.http.ResponseJsonWrap;
import cn.xports.baselib.mvp.IView;
import cn.xports.qd2.entity.K;
import cn.xports.qd2.entity.Option;
import cn.xports.qd2.widget.DropDownView;
import com.alipay.sdk.util.j;
import com.blankj.utilcode.util.CollectionUtils;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.JsonUtils;
import com.blankj.utilcode.util.ObjectUtils;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"cn/xports/qd2/fragment/TrainingFragment$getFilter$1", "Lcn/xports/baselib/http/ResponseJsonWrap;", "onSuccess", "", "result", "Lorg/json/JSONObject;", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: TrainingFragment.kt */
public final class TrainingFragment$getFilter$1 extends ResponseJsonWrap {
    final /* synthetic */ TrainingFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    TrainingFragment$getFilter$1(TrainingFragment trainingFragment, IView iView) {
        super(iView);
        this.this$0 = trainingFragment;
    }

    public void onSuccess(@NotNull JSONObject jSONObject) throws Exception {
        Intrinsics.checkParameterIsNotNull(jSONObject, j.f740c);
        JSONObject jSONObject2 = JsonUtils.getJSONObject(jSONObject, "suitPersonList", (JSONObject) null);
        if (jSONObject2 != null) {
            Iterator<String> keys = jSONObject2.keys();
            this.this$0.suitPersonList.add(new Option().setName("全部").setValue(""));
            while (keys.hasNext()) {
                String next = keys.next();
                String string = JsonUtils.getString(jSONObject2, next, "");
                if (ObjectUtils.isNotEmpty(string)) {
                    this.this$0.suitPersonList.add(new Option().setName(string).setData(string).setValue(next));
                }
            }
            DropDownView access$getVPersonRange$p = this.this$0.vPersonRange;
            if (access$getVPersonRange$p != null) {
                access$getVPersonRange$p.setData(this.this$0.suitPersonList);
            }
        }
        JSONArray jSONArray = JsonUtils.getJSONArray(jSONObject, "centers", (JSONArray) null);
        if (ObjectUtils.isNotEmpty(jSONArray)) {
            JSONArray jSONArray2 = JsonUtils.getJSONArray(jSONArray.getJSONObject(0), "venueList", (JSONArray) null);
            if (ObjectUtils.isNotEmpty(jSONArray2)) {
                Collection collection = (List) GsonUtils.fromJson(jSONArray2.toString(), new TrainingFragment$getFilter$1$onSuccess$venues$1().getType());
                if (ObjectUtils.isNotEmpty(collection)) {
                    this.this$0.venueList.add(new Option().setName("全部").setValue(""));
                    CollectionUtils.forAllDo(collection, new TrainingFragment$getFilter$1$onSuccess$1(this));
                    DropDownView access$getVCourseLocation$p = this.this$0.vCourseLocation;
                    if (access$getVCourseLocation$p != null) {
                        access$getVCourseLocation$p.setData(this.this$0.venueList);
                    }
                }
            }
        }
        JSONArray jSONArray3 = JsonUtils.getJSONArray(jSONObject, K.serviceList, (JSONArray) null);
        if (ObjectUtils.isNotEmpty(jSONArray3)) {
            Collection collection2 = (List) GsonUtils.fromJson(jSONArray3.toString(), new TrainingFragment$getFilter$1$onSuccess$services1$1().getType());
            if (ObjectUtils.isNotEmpty(collection2)) {
                this.this$0.serviceList.add(new Option().setName("全部").setValue(""));
                CollectionUtils.forAllDo(collection2, new TrainingFragment$getFilter$1$onSuccess$2(this));
                DropDownView access$getVSportType$p = this.this$0.vSportType;
                if (access$getVSportType$p != null) {
                    access$getVSportType$p.setData(this.this$0.serviceList);
                }
            }
        }
    }
}

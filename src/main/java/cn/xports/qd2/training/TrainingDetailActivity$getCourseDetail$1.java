package cn.xports.qd2.training;

import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.xports.baselib.http.ProcessObserver;
import cn.xports.baselib.mvp.IView;
import cn.xports.baselib.util.GlideUtil;
import cn.xports.baselib.util.MoneyUtil;
import cn.xports.baselib.widget.FakeBoldText;
import cn.xports.entity.TrainCourse;
import cn.xports.qd2.R;
import cn.xports.qd2.entity.K;
import cn.xports.qd2.entity.TrainCourseDetailResult;
import cn.xports.qd2.util.ViewExt;
import cn.xports.widget.AddLessView;
import com.blankj.utilcode.util.SPUtils;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002H\u0016¨\u0006\u0006"}, d2 = {"cn/xports/qd2/training/TrainingDetailActivity$getCourseDetail$1", "Lcn/xports/baselib/http/ProcessObserver;", "Lcn/xports/qd2/entity/TrainCourseDetailResult;", "next", "", "value", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: TrainingDetailActivity.kt */
public final class TrainingDetailActivity$getCourseDetail$1 extends ProcessObserver<TrainCourseDetailResult> {
    final /* synthetic */ TrainingDetailActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    TrainingDetailActivity$getCourseDetail$1(TrainingDetailActivity trainingDetailActivity, IView iView) {
        super(iView);
        this.this$0 = trainingDetailActivity;
    }

    public void next(@Nullable TrainCourseDetailResult trainCourseDetailResult) {
        if (trainCourseDetailResult != null) {
            TrainCourse trainingCourseInfo = trainCourseDetailResult.getTrainingCourseInfo();
            boolean z = true;
            if (trainingCourseInfo != null) {
                TrainingDetailActivity trainingDetailActivity = this.this$0;
                trainingDetailActivity.centerVenueName = trainingCourseInfo.getCenterName() + trainingCourseInfo.getVenueName();
                this.this$0.baseLessonNum = trainingCourseInfo.getLessonNum();
                this.this$0.courseJson.set("courseId", trainingCourseInfo.getCourseId());
                TrainingDetailActivity trainingDetailActivity2 = this.this$0;
                String privateTag = trainingCourseInfo.getPrivateTag();
                Intrinsics.checkExpressionValueIsNotNull(privateTag, "it.privateTag");
                trainingDetailActivity2.privateTag = privateTag;
                if (Intrinsics.areEqual(this.this$0.privateTag, K.k0)) {
                    LinearLayout linearLayout = (LinearLayout) this.this$0._$_findCachedViewById(R.id.llCourseProp);
                    Intrinsics.checkExpressionValueIsNotNull(linearLayout, "llCourseProp");
                    linearLayout.setVisibility(0);
                } else {
                    this.this$0.courseJson.set(K.courseName, trainingCourseInfo.getCourseName()).set(K.serviceId, trainingCourseInfo.getServiceId()).set(K.serviceName, trainingCourseInfo.getServiceName()).set(K.price, Integer.valueOf(trainingCourseInfo.getPrice()));
                }
                WebView webView = (WebView) this.this$0._$_findCachedViewById(R.id.webDesc);
                Intrinsics.checkExpressionValueIsNotNull(webView, "webDesc");
                ViewExt.loadHtmlStr(webView, trainingCourseInfo.getCourseDesc(), 75);
                FakeBoldText fakeBoldText = (FakeBoldText) this.this$0._$_findCachedViewById(R.id.tvName);
                Intrinsics.checkExpressionValueIsNotNull(fakeBoldText, "tvName");
                fakeBoldText.setText(trainingCourseInfo.getCourseName());
                this.this$0.price = trainingCourseInfo.getPrice();
                TextView textView = (TextView) this.this$0._$_findCachedViewById(R.id.tvPrice);
                Intrinsics.checkExpressionValueIsNotNull(textView, "tvPrice");
                CharSequence text = textView.getText();
                if (text == null || text.length() == 0) {
                    TextView textView2 = (TextView) this.this$0._$_findCachedViewById(R.id.tvPrice);
                    Intrinsics.checkExpressionValueIsNotNull(textView2, "tvPrice");
                    textView2.setText("¥" + MoneyUtil.cent2Yuan(trainingCourseInfo.getPrice()));
                    TextView textView3 = (TextView) this.this$0._$_findCachedViewById(R.id.tvPercent);
                    Intrinsics.checkExpressionValueIsNotNull(textView3, "tvPercent");
                    textView3.setText("/" + trainingCourseInfo.getLessonNum() + "课时");
                }
                TextView textView4 = (TextView) this.this$0._$_findCachedViewById(R.id.tvSportLocation);
                Intrinsics.checkExpressionValueIsNotNull(textView4, "tvSportLocation");
                textView4.setText(trainingCourseInfo.getAddress());
                TrainingDetailActivity trainingDetailActivity3 = this.this$0;
                String latitude = trainingCourseInfo.getLatitude();
                Intrinsics.checkExpressionValueIsNotNull(latitude, "it.latitude");
                trainingDetailActivity3.lat = latitude;
                TrainingDetailActivity trainingDetailActivity4 = this.this$0;
                String longitude = trainingCourseInfo.getLongitude();
                Intrinsics.checkExpressionValueIsNotNull(longitude, "it.longitude");
                trainingDetailActivity4.lng = longitude;
                String courseEnrolled = trainingCourseInfo.getCourseEnrolled();
                if (trainingCourseInfo.isPrivate()) {
                    LinearLayout linearLayout2 = (LinearLayout) this.this$0._$_findCachedViewById(R.id.llNum);
                    Intrinsics.checkExpressionValueIsNotNull(linearLayout2, "llNum");
                    linearLayout2.setVisibility(0);
                    courseEnrolled = trainingCourseInfo.getPrivateCourseEnrolled();
                } else {
                    LinearLayout linearLayout3 = (LinearLayout) this.this$0._$_findCachedViewById(R.id.llNum);
                    Intrinsics.checkExpressionValueIsNotNull(linearLayout3, "llNum");
                    linearLayout3.setVisibility(8);
                }
                TextView textView5 = (TextView) this.this$0._$_findCachedViewById(R.id.tvHasSign);
                Intrinsics.checkExpressionValueIsNotNull(textView5, "tvHasSign");
                textView5.setText(courseEnrolled + 20154);
                GlideUtil.loadImage(this.this$0, trainingCourseInfo.getCoursePicUrl(), R.drawable.bg_default).into((ImageView) this.this$0._$_findCachedViewById(R.id.iv_head));
                SPUtils.getInstance().put("instId", trainingCourseInfo.getInstId());
                if (trainingCourseInfo.getMinPurchaseNum() > 0) {
                    ((AddLessView) this.this$0._$_findCachedViewById(R.id.addLessView)).setMinNum(trainingCourseInfo.getMinPurchaseNum());
                }
                if (trainingCourseInfo.getMaxPurchaseNum() > 0) {
                    ((AddLessView) this.this$0._$_findCachedViewById(R.id.addLessView)).setMax(trainingCourseInfo.getMaxPurchaseNum());
                }
            }
            Collection cardInfoList = trainCourseDetailResult.getCardInfoList();
            if (cardInfoList != null && !cardInfoList.isEmpty()) {
                z = false;
            }
            if (!z) {
                this.this$0.cardList.addAll(trainCourseDetailResult.getCardInfoList());
            }
        }
    }
}

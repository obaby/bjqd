package cn.xports.qd2.training;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;
import cn.xports.baselib.util.GlobalMapUtil;
import cn.xports.qd2.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 15})
/* compiled from: TrainingDetailActivity.kt */
final class TrainingDetailActivity$initView$1 implements View.OnClickListener {
    final /* synthetic */ TrainingDetailActivity this$0;

    TrainingDetailActivity$initView$1(TrainingDetailActivity trainingDetailActivity) {
        this.this$0 = trainingDetailActivity;
    }

    public final void onClick(View view) {
        CharSequence access$getLat$p = this.this$0.lat;
        boolean z = false;
        if (!(access$getLat$p == null || access$getLat$p.length() == 0)) {
            if (this.this$0.lng.length() == 0) {
                z = true;
            }
            if (!z) {
                if (!GlobalMapUtil.hasMap()) {
                    this.this$0.showMsg("请先安装地图客户端");
                } else {
                    new AlertDialog.Builder(this.this$0).setTitle((CharSequence) "选择地图").setSingleChoiceItems((CharSequence[]) new String[]{"百度地图", "高德地图"}, -1, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener(this) {
                        final /* synthetic */ TrainingDetailActivity$initView$1 this$0;

                        {
                            this.this$0 = r1;
                        }

                        public final void onClick(DialogInterface dialogInterface, int i) {
                            if (i == 0) {
                                Double valueOf = Double.valueOf(Double.parseDouble(this.this$0.this$0.lat));
                                Double valueOf2 = Double.valueOf(Double.parseDouble(this.this$0.this$0.lng));
                                TextView textView = (TextView) this.this$0.this$0._$_findCachedViewById(R.id.tvSportLocation);
                                Intrinsics.checkExpressionValueIsNotNull(textView, "tvSportLocation");
                                GlobalMapUtil.goToBaiduMap(valueOf, valueOf2, textView.getText().toString());
                            } else if (i == 1) {
                                Double valueOf3 = Double.valueOf(Double.parseDouble(this.this$0.this$0.lat));
                                Double valueOf4 = Double.valueOf(Double.parseDouble(this.this$0.this$0.lng));
                                TextView textView2 = (TextView) this.this$0.this$0._$_findCachedViewById(R.id.tvSportLocation);
                                Intrinsics.checkExpressionValueIsNotNull(textView2, "tvSportLocation");
                                GlobalMapUtil.goToGaodeMap(valueOf3, valueOf4, textView2.getText().toString());
                            }
                            dialogInterface.dismiss();
                        }
                    }).setNeutralButton((CharSequence) "取消", (DialogInterface.OnClickListener) AnonymousClass2.INSTANCE).show();
                }
            }
        }
    }
}

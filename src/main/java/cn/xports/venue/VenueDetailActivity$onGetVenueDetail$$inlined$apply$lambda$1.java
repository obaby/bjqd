package cn.xports.venue;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import cn.xports.baselib.util.GlobalMapUtil;
import cn.xports.entity.Venue;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005¨\u0006\u0006"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick", "cn/xports/venue/VenueDetailActivity$onGetVenueDetail$1$1"}, k = 3, mv = {1, 1, 15})
/* compiled from: VenueDetailActivity.kt */
final class VenueDetailActivity$onGetVenueDetail$$inlined$apply$lambda$1 implements View.OnClickListener {
    final /* synthetic */ Venue $this_apply;
    final /* synthetic */ VenueDetailActivity this$0;

    VenueDetailActivity$onGetVenueDetail$$inlined$apply$lambda$1(Venue venue, VenueDetailActivity venueDetailActivity) {
        this.$this_apply = venue;
        this.this$0 = venueDetailActivity;
    }

    public final void onClick(View view) {
        if (!GlobalMapUtil.hasMap()) {
            this.this$0.showMsg("请先安装地图客户端");
        } else {
            new AlertDialog.Builder(this.this$0).setTitle((CharSequence) "选择地图").setSingleChoiceItems((CharSequence[]) new String[]{"百度地图", "高德地图"}, -1, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener(this) {
                final /* synthetic */ VenueDetailActivity$onGetVenueDetail$$inlined$apply$lambda$1 this$0;

                {
                    this.this$0 = r1;
                }

                public final void onClick(DialogInterface dialogInterface, int i) {
                    Double d = null;
                    if (i == 0) {
                        String latitude = this.this$0.$this_apply.getLatitude();
                        Double valueOf = latitude != null ? Double.valueOf(Double.parseDouble(latitude)) : null;
                        String longitude = this.this$0.$this_apply.getLongitude();
                        if (longitude != null) {
                            d = Double.valueOf(Double.parseDouble(longitude));
                        }
                        GlobalMapUtil.goToBaiduMap(valueOf, d, this.this$0.$this_apply.getAddress());
                    } else if (i == 1) {
                        String latitude2 = this.this$0.$this_apply.getLatitude();
                        Double valueOf2 = latitude2 != null ? Double.valueOf(Double.parseDouble(latitude2)) : null;
                        String longitude2 = this.this$0.$this_apply.getLongitude();
                        if (longitude2 != null) {
                            d = Double.valueOf(Double.parseDouble(longitude2));
                        }
                        GlobalMapUtil.goToGaodeMap(valueOf2, d, this.this$0.$this_apply.getAddress());
                    }
                    dialogInterface.dismiss();
                }
            }).setNeutralButton((CharSequence) "取消", (DialogInterface.OnClickListener) VenueDetailActivity$onGetVenueDetail$1$1$2.INSTANCE).show();
        }
    }
}

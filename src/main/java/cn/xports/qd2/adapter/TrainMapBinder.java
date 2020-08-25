package cn.xports.qd2.adapter;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cn.xports.baselib.adapter.XBaseHolder;
import cn.xports.baselib.util.GlobalMapUtil;
import cn.xports.qd2.R;
import cn.xports.qd2.entity.VenueLocationItem;
import com.blankj.utilcode.util.ToastUtils;
import me.drakeet.multitype.ItemViewBinder;

public class TrainMapBinder extends ItemViewBinder<VenueLocationItem, XBaseHolder> {
    /* access modifiers changed from: protected */
    @NonNull
    public XBaseHolder onCreateViewHolder(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
        return new XBaseHolder(layoutInflater.inflate(R.layout.item_train_venue_map, viewGroup, false));
    }

    /* access modifiers changed from: protected */
    public void onBindViewHolder(@NonNull final XBaseHolder xBaseHolder, @NonNull final VenueLocationItem venueLocationItem) {
        xBaseHolder.setText(R.id.tv_name, venueLocationItem.getName());
        xBaseHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!GlobalMapUtil.hasMap()) {
                    ToastUtils.showShort("请先安装地图客户端");
                    return;
                }
                new AlertDialog.Builder(xBaseHolder.itemView.getContext()).setTitle((CharSequence) "选择地图").setSingleChoiceItems(new CharSequence[]{"百度地图", "高德地图"}, -1, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i == 0) {
                            GlobalMapUtil.goToBaiduMap(Double.valueOf(venueLocationItem.getLatitude()), Double.valueOf(venueLocationItem.getLongitude()), venueLocationItem.getName());
                        } else if (i == 1) {
                            GlobalMapUtil.goToGaodeMap(Double.valueOf(venueLocationItem.getLatitude()), Double.valueOf(venueLocationItem.getLongitude()), venueLocationItem.getName());
                        }
                        dialogInterface.dismiss();
                    }
                }).show();
            }
        });
    }
}

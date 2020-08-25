package cn.xports.venue;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import cn.xports.base.Constant;
import cn.xports.baselib.util.DensityUtil;
import cn.xports.entity.AdditionalService;
import cn.xports.qdplugin.R;
import java.util.List;

public class VenueServiceTagAdapter extends RecyclerView.Adapter<Holder> {
    private List<AdditionalService> additionalServices;

    public VenueServiceTagAdapter(List<AdditionalService> list) {
        this.additionalServices = list;
    }

    @NonNull
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new Holder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_service_grid, viewGroup, false));
    }

    public void onBindViewHolder(@NonNull Holder holder, int i) {
        AdditionalService additionalService = this.additionalServices.get(i);
        holder.tvServiceName.setText(additionalService.getName());
        holder.ivService.setImageResource(Constant.getAdditionalService(additionalService.getCode()));
        if (additionalService.getName().equals("器材租赁")) {
            ViewGroup.LayoutParams layoutParams = holder.ivService.getLayoutParams();
            layoutParams.width = DensityUtil.dp2px(14.0f);
            layoutParams.height = DensityUtil.dp2px(13.6f);
            holder.ivService.setLayoutParams(layoutParams);
        }
        ViewGroup.LayoutParams layoutParams2 = holder.itemView.getLayoutParams();
        layoutParams2.height = DensityUtil.dp2px(73.5f);
        layoutParams2.width = DensityUtil.dp2px(73.5f);
        holder.itemView.setLayoutParams(layoutParams2);
    }

    public int getItemCount() {
        if (this.additionalServices == null) {
            return 0;
        }
        return this.additionalServices.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        ImageView ivService;
        TextView tvServiceName;

        public Holder(@NonNull View view) {
            super(view);
            this.tvServiceName = (TextView) view.findViewById(R.id.tv_service_name);
            this.ivService = (ImageView) view.findViewById(R.id.iv_service_ic);
        }
    }
}

package cn.xports.venue;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.xports.baselib.http.ProcessObserver;
import cn.xports.baselib.http.ResponseThrowable;
import cn.xports.baselib.http.RetrofitUtil;
import cn.xports.baselib.http.RxUtil;
import cn.xports.baselib.mvp.BaseFragment;
import cn.xports.baselib.util.DensityUtil;
import cn.xports.baselib.util.GlideUtil;
import cn.xports.baselib.util.LoadingDialog;
import cn.xports.entity.Venue;
import cn.xports.export.EventHandler;
import cn.xports.http.SodaService;
import cn.xports.parser.VenueListParser;
import cn.xports.qd2.entity.K;
import cn.xports.qdplugin.R;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import java.util.ArrayList;

public class VenueListFragment extends BaseFragment {
    private boolean firstVisible = true;
    protected View llNoData;
    private LoadingDialog loadingDialog;
    private String serviceId = "-111";
    protected TextView tvTip;
    /* access modifiers changed from: private */
    public ArrayList<Venue> venues = new ArrayList<>();
    /* access modifiers changed from: private */
    public VenuesAdapter venuesAdapter = new VenuesAdapter();

    public static VenueListFragment newInstance(String str) {
        VenueListFragment venueListFragment = new VenueListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(K.serviceId, str);
        venueListFragment.setArguments(bundle);
        return venueListFragment;
    }

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.loadingDialog = new LoadingDialog(getContext());
        this.serviceId = getArguments().getString(K.serviceId, "-111");
    }

    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_venue_list, viewGroup, false);
        initView(inflate);
        if (this.firstVisible) {
            getVenueList();
            this.firstVisible = false;
        }
        return inflate;
    }

    private void initView(View view) {
        this.llNoData = view.findViewById(R.id.ll_no_data);
        this.tvTip = (TextView) view.findViewById(R.id.tv_tip);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_venues);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(this.venuesAdapter);
        if (this.venues.size() == 0) {
            showNoData("暂无数据");
        } else {
            hideNoData();
        }
    }

    public void getVenueList() {
        hideNoData();
        ((SodaService) RetrofitUtil.getInstance().create(SodaService.class)).getVenueList(7, "-111".equals(this.serviceId) ? null : this.serviceId, 1).compose(RxUtil.applyErrorsWithIO()).subscribe(new ProcessObserver<VenueListParser>(this) {
            public void next(VenueListParser venueListParser) {
                VenueListFragment.this.venues.clear();
                if (venueListParser == null || venueListParser.getVenueList() == null || venueListParser.getVenueList().size() <= 0) {
                    VenueListFragment.this.showNoData("暂无场馆数据");
                } else {
                    VenueListFragment.this.venues.addAll(venueListParser.getVenueList());
                }
                VenueListFragment.this.venuesAdapter.notifyDataSetChanged();
            }

            public void onError(ResponseThrowable responseThrowable) {
                super.onError(responseThrowable);
                VenueListFragment.this.venues.clear();
                VenueListFragment.this.venuesAdapter.notifyDataSetChanged();
                VenueListFragment.this.showNoData(responseThrowable.getMessage());
            }
        });
    }

    public void showNoData(String str) {
        if (this.llNoData != null) {
            this.llNoData.setVisibility(0);
            if (!TextUtils.isEmpty(str)) {
                this.tvTip.setText(str);
            } else {
                this.tvTip.setText("暂无数据");
            }
        }
    }

    public void hideNoData() {
        if (this.llNoData != null && this.llNoData.getVisibility() == 0) {
            this.llNoData.setVisibility(8);
        }
    }

    /* access modifiers changed from: private */
    public boolean notAddName(String str) {
        return TextUtils.isEmpty(str) || !"-111".equals(this.serviceId);
    }

    private class VenuesAdapter extends RecyclerView.Adapter<Holder> {
        private VenuesAdapter() {
        }

        @NonNull
        public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new Holder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_venue, viewGroup, false));
        }

        public void onBindViewHolder(@NonNull Holder holder, int i) {
            String str;
            final Venue venue = (Venue) VenueListFragment.this.venues.get(i);
            holder.tvAddress.setText(venue.getAddress());
            TextView textView = holder.tvVenueName;
            StringBuilder sb = new StringBuilder();
            sb.append(venue.getVenueName());
            if (VenueListFragment.this.notAddName(venue.getServiceName())) {
                str = "";
            } else {
                str = "·" + venue.getServiceName();
            }
            sb.append(str);
            textView.setText(sb.toString());
            if (!TextUtils.isEmpty(venue.getVenueImage())) {
                GlideUtil.loadRoundImage(holder.ivVenueImage.getContext(), venue.getVenueImage()).apply((BaseRequestOptions<?>) new RequestOptions().error(R.drawable.bg_default_corner).placeholder(R.drawable.bg_default_corner)).apply((BaseRequestOptions<?>) RequestOptions.bitmapTransform(new RoundedCorners(DensityUtil.dp2px(4.0f)))).into(holder.ivVenueImage);
            }
            if (venue.getAdditionalService() == null) {
                holder.llTags.setVisibility(8);
            } else {
                holder.llTags.removeAllViews();
                int size = venue.getAdditionalService().size() > 3 ? 4 : venue.getAdditionalService().size();
                for (int i2 = 0; i2 < size; i2++) {
                    TextView textView2 = new TextView(holder.itemView.getContext());
                    textView2.setBackgroundResource(R.drawable.bg_blue_stroke);
                    textView2.setText(venue.getAdditionalService().get(i2).getName());
                    textView2.setTextSize(10.0f);
                    textView2.setPadding(10, 2, 10, 2);
                    textView2.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.blue_2e6));
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
                    layoutParams.setMargins(0, 0, 20, 0);
                    textView2.setLayoutParams(layoutParams);
                    holder.llTags.addView(textView2);
                }
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (VenueListFragment.this.getActivity() != null) {
                        VenueListFragment.this.startActivity(new Intent(VenueListFragment.this.getActivity(), VenueDetailActivity.class).putExtra(K.serviceId, venue.getServiceId()).putExtra(K.venueId, venue.getVenueId()));
                    }
                }
            });
        }

        public int getItemCount() {
            if (VenueListFragment.this.venues == null) {
                return 0;
            }
            return VenueListFragment.this.venues.size();
        }

        public class Holder extends RecyclerView.ViewHolder {
            ImageView ivVenueImage;
            LinearLayout llTags;
            TextView tvAddress;
            TextView tvVenueName;

            public Holder(@NonNull View view) {
                super(view);
                this.llTags = (LinearLayout) view.findViewById(R.id.ll_tags);
                this.tvVenueName = (TextView) view.findViewById(R.id.tv_venue_name);
                this.tvAddress = (TextView) view.findViewById(R.id.tv_venue_address);
                this.ivVenueImage = (ImageView) view.findViewById(R.id.iv_venue_image);
            }
        }
    }

    public void showLoading() {
        super.showLoading();
        if (EventHandler.getInstance().getLoadingListener() != null) {
            EventHandler.getInstance().getLoadingListener().showLoading();
        } else if (this.loadingDialog != null) {
            this.loadingDialog.show();
        }
    }

    public void hideLoading() {
        if (EventHandler.getInstance().getLoadingListener() != null) {
            EventHandler.getInstance().getLoadingListener().hideLoading();
        } else if (this.loadingDialog != null) {
            this.loadingDialog.dismiss();
        }
    }
}

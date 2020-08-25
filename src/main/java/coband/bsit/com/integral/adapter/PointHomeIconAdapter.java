package coband.bsit.com.integral.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import coband.bsit.com.integral.R;
import com.convenient.qd.core.bean.MenuTab;
import com.convenient.qd.core.utils.ImageLoader;
import java.util.List;

public class PointHomeIconAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private Context context;
    private List<MenuTab> menuTabList;
    /* access modifiers changed from: private */
    public OnMenuClickListener onMenuClickListener;

    public interface OnMenuClickListener {
        void onMenuClick(MenuTab menuTab);
    }

    public void setOnMenuClickListener(OnMenuClickListener onMenuClickListener2) {
        this.onMenuClickListener = onMenuClickListener2;
    }

    public PointHomeIconAdapter(Context context2, List<MenuTab> list) {
        this.context = context2;
        this.menuTabList = list;
    }

    @NonNull
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(this.context).inflate(R.layout.integral_point_home_icon_layout, viewGroup, false));
    }

    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        final MenuTab menuTab = this.menuTabList.get(i);
        myViewHolder.tv_iconName.setText(menuTab.getAppName());
        ImageLoader.loadImg(this.context, menuTab.getAppIconUrl(), 0, myViewHolder.iv_icon);
        myViewHolder.ll_rootView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (PointHomeIconAdapter.this.onMenuClickListener != null) {
                    PointHomeIconAdapter.this.onMenuClickListener.onMenuClick(menuTab);
                }
            }
        });
    }

    public int getItemCount() {
        if (this.menuTabList == null) {
            return 0;
        }
        return this.menuTabList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        /* access modifiers changed from: private */
        public ImageView iv_icon;
        /* access modifiers changed from: private */
        public LinearLayout ll_rootView;
        /* access modifiers changed from: private */
        public TextView tv_iconName;

        public MyViewHolder(View view) {
            super(view);
            this.iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
            this.tv_iconName = (TextView) view.findViewById(R.id.tv_iconName);
            this.ll_rootView = (LinearLayout) view.findViewById(R.id.ll_rootView);
        }
    }
}

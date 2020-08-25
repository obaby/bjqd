package ad;

import a.e;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.linewell.licence.R;
import com.linewell.licence.entity.ServiceEntity;
import com.linewell.licence.glide.GlideImageView;
import java.util.ArrayList;
import java.util.List;

public class a extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /* renamed from: a  reason: collision with root package name */
    private List<ServiceEntity> f67a;

    /* renamed from: b  reason: collision with root package name */
    private boolean f68b;
    /* access modifiers changed from: private */

    /* renamed from: c  reason: collision with root package name */
    public e.c f69c;
    private List<ServiceEntity> d;

    public a(List<ServiceEntity> list) {
        this.f67a = list;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new C0003a(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_allapps, viewGroup, false));
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {
        if (viewHolder instanceof C0003a) {
            ServiceEntity serviceEntity = this.f67a.get(i);
            C0003a aVar = (C0003a) viewHolder;
            aVar.d.a(serviceEntity.serviceIconUrl);
            aVar.e.setText(serviceEntity.serviceName);
            boolean z = false;
            if (this.f68b) {
                aVar.f.setVisibility(0);
                aVar.f74c.setBackgroundResource(R.drawable.gengduo_bg);
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
                layoutParams.leftMargin = 10;
                layoutParams.rightMargin = 10;
                layoutParams.topMargin = 10;
                layoutParams.bottomMargin = 10;
                aVar.f73b.setLayoutParams(layoutParams);
                for (ServiceEntity next : this.d) {
                    if (!TextUtils.isEmpty(next.serviceName) && !TextUtils.isEmpty(serviceEntity.serviceName) && next.serviceName.equals(serviceEntity.serviceName) && !TextUtils.isEmpty(next.serviceId) && !TextUtils.isEmpty(serviceEntity.serviceId) && next.serviceId.equals(serviceEntity.serviceId)) {
                        z = true;
                    }
                }
                if (z) {
                    aVar.f.setBackgroundResource(R.drawable.gengduo_icon_dele);
                } else {
                    aVar.f.setBackgroundResource(R.drawable.gengduo_icon_add);
                }
            } else {
                aVar.f.setVisibility(8);
                aVar.f74c.setBackground((Drawable) null);
                RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
                layoutParams2.leftMargin = 0;
                layoutParams2.rightMargin = 0;
                layoutParams2.topMargin = 20;
                layoutParams2.bottomMargin = 0;
                aVar.f73b.setLayoutParams(layoutParams2);
                if (!TextUtils.isEmpty(serviceEntity.servicePopType)) {
                    int parseInt = Integer.parseInt(serviceEntity.servicePopType);
                    if (parseInt == 0) {
                        aVar.g.setVisibility(8);
                    } else if (parseInt == 1) {
                        aVar.g.setVisibility(0);
                        aVar.g.setImageResource(R.drawable.icon_new);
                    } else if (parseInt == 2) {
                        aVar.g.setVisibility(0);
                        aVar.g.setImageResource(R.drawable.hot);
                    }
                }
            }
            aVar.f73b.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (a.this.f69c != null) {
                        a.this.f69c.onItemChildClick((e) null, (View) null, i);
                    }
                }
            });
        }
    }

    public int getItemCount() {
        if (this.f67a == null) {
            return 0;
        }
        return this.f67a.size();
    }

    public void a(boolean z, List<ServiceEntity> list) {
        this.f68b = z;
        this.d = list;
        if (this.d == null) {
            this.d = new ArrayList();
        }
        notifyDataSetChanged();
    }

    public void a() {
        this.f68b = false;
        notifyDataSetChanged();
    }

    public void a(List<ServiceEntity> list) {
        if (list != null) {
            int size = this.f67a.size();
            this.f67a.clear();
            notifyItemRangeRemoved(0, size);
            this.f67a.addAll(list);
            notifyItemRangeInserted(0, list.size());
            notifyDataSetChanged();
        }
    }

    public void a(int i) {
        this.f67a.remove(i);
        notifyItemRemoved(i);
        notifyDataSetChanged();
    }

    public void a(ServiceEntity serviceEntity) {
        if (serviceEntity != null) {
            this.f67a.add(0, serviceEntity);
            notifyItemInserted(0);
            notifyItemRangeChanged(0, this.f67a.size());
            notifyDataSetChanged();
        }
    }

    public void b() {
        notifyItemRangeChanged(0, this.f67a.size());
    }

    public List<ServiceEntity> c() {
        return this.f67a;
    }

    /* renamed from: ad.a$a  reason: collision with other inner class name */
    class C0003a extends RecyclerView.ViewHolder {
        /* access modifiers changed from: private */

        /* renamed from: b  reason: collision with root package name */
        public RelativeLayout f73b;
        /* access modifiers changed from: private */

        /* renamed from: c  reason: collision with root package name */
        public LinearLayout f74c;
        /* access modifiers changed from: private */
        public GlideImageView d;
        /* access modifiers changed from: private */
        public TextView e;
        /* access modifiers changed from: private */
        public ImageView f;
        /* access modifiers changed from: private */
        public ImageView g;

        public C0003a(View view) {
            super(view);
            this.f73b = (RelativeLayout) view.findViewById(R.id.itemLayout);
            this.f74c = (LinearLayout) view.findViewById(R.id.tvLayout);
            this.d = view.findViewById(R.id.appIcon);
            this.e = (TextView) view.findViewById(R.id.appName);
            this.f = (ImageView) view.findViewById(R.id.img_edit);
            this.g = (ImageView) view.findViewById(R.id.appTag);
        }
    }

    public void a(e.c cVar) {
        this.f69c = cVar;
    }
}

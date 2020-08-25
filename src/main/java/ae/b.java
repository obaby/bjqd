package ae;

import a.f;
import ae.c;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.linewell.licence.R;
import com.linewell.licence.entity.FootprintEntity;
import com.linewell.licence.util.u;

public class b extends f {

    /* renamed from: c  reason: collision with root package name */
    private TextView f76c;
    private TextView d;
    private TextView e;
    private TextView f;
    private TextView g;
    private TextView h;
    private TextView i;
    private LinearLayout j;
    private LinearLayout k;
    private LinearLayout l;
    private ImageView m;
    private ImageView n;
    private c.a o;
    private LinearLayout p;
    private String q;
    private FootprintEntity r;

    public b(View view) {
        super(view);
        this.f76c = (TextView) view.findViewById(R.id.content);
        this.d = (TextView) view.findViewById(R.id.time);
        this.j = (LinearLayout) view.findViewById(R.id.timeSeek);
        this.m = (ImageView) view.findViewById(R.id.isRead);
        this.e = (TextView) view.findViewById(R.id.detailTv);
        this.k = (LinearLayout) view.findViewById(R.id.autho);
        this.l = (LinearLayout) view.findViewById(R.id.noAutho);
        this.p = (LinearLayout) view.findViewById(R.id.rootView);
        this.h = (TextView) view.findViewById(R.id.itemTitle);
        this.f = (TextView) view.findViewById(R.id.authoContent);
        this.g = (TextView) view.findViewById(R.id.authoTime);
        this.i = (TextView) view.findViewById(R.id.downLine);
        this.n = (ImageView) view.findViewById(R.id.moreArro);
    }

    public void a(final FootprintEntity footprintEntity, final String str, final c.a aVar, int i2) {
        this.p.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                aVar.a(str, footprintEntity);
            }
        });
        if (str.equals("2")) {
            this.k.setVisibility(0);
            this.l.setVisibility(8);
            this.n.setVisibility(8);
            this.h.setText(footprintEntity.manageStepTitle);
            this.h.setTextColor(this.h.getResources().getColor(R.color.black));
            this.h.setTextSize(14.0f);
            this.g.setVisibility(4);
            this.g.setText(footprintEntity.createTime);
            this.f.setText(footprintEntity.licenseDataBase);
        } else if (str.equals("1")) {
            this.k.setVisibility(0);
            this.l.setVisibility(8);
            this.n.setVisibility(0);
            this.h.setText(footprintEntity.title);
            this.g.setText(footprintEntity.authorizedTime);
            this.f.setText(footprintEntity.content);
        }
        f(i2);
    }

    public void f(int i2) {
        u.c("ssss-->" + b() + "," + i2);
        if (b() + 1 == i2) {
            this.i.setVisibility(4);
        } else {
            this.i.setVisibility(0);
        }
    }
}

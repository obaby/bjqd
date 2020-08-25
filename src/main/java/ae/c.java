package ae;

import a.f;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.xports.qd2.entity.K;
import com.linewell.licence.R;
import com.linewell.licence.entity.FootprintEntity;
import com.linewell.licence.view.img.ZZImageView;

public class c extends f {

    /* renamed from: c  reason: collision with root package name */
    private LinearLayout f80c;
    private ImageView d;
    private TextView e;
    private TextView f;
    private ZZImageView g;
    private TextView h;

    public interface a {
        void a(String str, FootprintEntity footprintEntity);
    }

    public c(View view) {
        super(view);
        this.f80c = (LinearLayout) view.findViewById(R.id.rootView);
        this.d = (ImageView) view.findViewById(R.id.msgIcon);
        this.e = (TextView) view.findViewById(R.id.msgTitle);
        this.f = (TextView) view.findViewById(R.id.msgTime);
        this.g = view.findViewById(R.id.msgStatus);
        this.h = (TextView) view.findViewById(R.id.msgContent);
    }

    public void a(final FootprintEntity footprintEntity, final a aVar) {
        if (footprintEntity != null) {
            this.f80c.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    aVar.a(footprintEntity.messageType, footprintEntity);
                }
            });
            String str = footprintEntity.messageType;
            char c2 = 65535;
            switch (str.hashCode()) {
                case 49:
                    if (str.equals("1")) {
                        c2 = 0;
                        break;
                    }
                    break;
                case 50:
                    if (str.equals("2")) {
                        c2 = 1;
                        break;
                    }
                    break;
                case 51:
                    if (str.equals("3")) {
                        c2 = 2;
                        break;
                    }
                    break;
            }
            switch (c2) {
                case 0:
                    this.d.setImageResource(R.drawable.massage_icon_huodong);
                    break;
                case 1:
                    this.d.setImageResource(R.drawable.massage_icon_shouquan);
                    break;
                case 2:
                    this.d.setImageResource(R.drawable.massage_icon_xitong);
                    break;
                default:
                    this.d.setImageResource(R.drawable.massage_icon_tongzhi);
                    break;
            }
            if (!TextUtils.isEmpty(footprintEntity.messageTypeTitle)) {
                this.e.setText(footprintEntity.messageTypeTitle);
            }
            if (!TextUtils.isEmpty(footprintEntity.createTime)) {
                this.f.setText(footprintEntity.createTime);
            }
            if (TextUtils.isEmpty(footprintEntity.isRead) || !K.k0.equals(footprintEntity.isRead)) {
                this.g.setVisibility(8);
            } else {
                this.g.setVisibility(0);
            }
            if (!TextUtils.isEmpty(footprintEntity.title)) {
                this.h.setText(footprintEntity.title);
            }
        }
    }
}

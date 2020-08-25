package ac;

import a.f;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.linewell.licence.R;
import com.linewell.licence.entity.LincenseEntity;

public class c extends f {

    /* renamed from: c  reason: collision with root package name */
    private TextView f66c;
    private TextView d;

    public c(View view) {
        super(view);
        this.d = (TextView) view.findViewById(R.id.licenDept);
        this.f66c = (TextView) view.findViewById(R.id.licenseName);
    }

    public void a(LincenseEntity lincenseEntity) {
        if (lincenseEntity != null) {
            if (TextUtils.isEmpty(lincenseEntity.dept)) {
                this.d.setVisibility(8);
            } else {
                this.d.setText(lincenseEntity.dept);
            }
            this.f66c.setText(lincenseEntity.licenseName);
        }
    }
}

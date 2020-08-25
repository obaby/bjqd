package ac;

import a.f;
import ac.b;
import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.View;
import android.widget.TextView;
import com.linewell.licence.R;
import com.linewell.licence.b;
import com.linewell.licence.entity.LincenseEntity;

public class b extends f {

    /* renamed from: c  reason: collision with root package name */
    private TextView f65c;
    private TextView d;
    private AppCompatCheckBox e;
    private a f;
    private boolean g = true;
    private ColorStateList h;

    public interface a {
        boolean a(LincenseEntity lincenseEntity);

        void b(LincenseEntity lincenseEntity);
    }

    public b(View view) {
        super(view);
        this.f65c = (TextView) view.findViewById(R.id.licenseName);
        this.d = (TextView) view.findViewById(R.id.licenseUnti);
        this.e = (AppCompatCheckBox) view.findViewById(R.id.check);
    }

    @SuppressLint({"RestrictedApi"})
    private void g() {
        if (this.e.isChecked()) {
            this.e.setSupportButtonTintList(ColorStateList.valueOf(Color.parseColor(b.c.b)));
            return;
        }
        if (this.g) {
            this.h = this.e.getSupportButtonTintList();
            this.g = false;
        }
        this.e.setSupportButtonTintList(this.h);
    }

    @SuppressLint({"RestrictedApi"})
    public void a(LincenseEntity lincenseEntity, a aVar) {
        if (lincenseEntity != null) {
            this.f = aVar;
            this.f65c.setText(lincenseEntity.licenseName != null ? lincenseEntity.licenseName : "");
            this.d.setText(lincenseEntity.dept != null ? lincenseEntity.dept : "");
            this.e.setChecked(lincenseEntity.isCheck);
            g();
            this.e.setOnClickListener(new View.OnClickListener(lincenseEntity, aVar) {
                private final /* synthetic */ LincenseEntity f$1;
                private final /* synthetic */ b.a f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void onClick(View view) {
                    b.this.a(this.f$1, this.f$2, view);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(LincenseEntity lincenseEntity, a aVar, View view) {
        if (lincenseEntity != null && aVar != null) {
            if (!this.e.isChecked()) {
                lincenseEntity.isCheck = false;
                this.f.b(lincenseEntity);
            } else if (this.f.a(lincenseEntity)) {
                lincenseEntity.isCheck = true;
            } else {
                lincenseEntity.isCheck = false;
                this.e.setChecked(false);
            }
            g();
        }
    }
}

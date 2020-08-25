package ab;

import a.f;
import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.linewell.licence.R;
import com.linewell.licence.entity.MaterialEntity;
import com.linewell.licence.ui.license.material.select.SelectMaterialAdapter;

public class b extends f {

    /* renamed from: c  reason: collision with root package name */
    private LinearLayout f63c;
    private TextView d;
    private ImageView e;
    private TextView f;
    private ImageView g;

    public b(View view) {
        super(view);
        this.f63c = (LinearLayout) view.findViewById(R.id.listItem);
        this.d = (TextView) view.findViewById(R.id.materialName);
        this.f = (TextView) view.findViewById(R.id.date);
        this.e = (ImageView) view.findViewById(R.id.materialStatus);
        this.g = (ImageView) view.findViewById(R.id.selectState);
    }

    @SuppressLint({"RestrictedApi"})
    private void b(MaterialEntity materialEntity, String str, String str2, SelectMaterialAdapter selectMaterialAdapter) {
        materialEntity.isCheckEntity = false;
        if (selectMaterialAdapter != null) {
            selectMaterialAdapter.b(str, str2, materialEntity);
        }
        this.g.setImageResource(R.drawable.nocheck);
    }

    @SuppressLint({"RestrictedApi"})
    private void c(MaterialEntity materialEntity, String str, String str2, SelectMaterialAdapter selectMaterialAdapter) {
        materialEntity.isCheckEntity = true;
        if (selectMaterialAdapter != null) {
            selectMaterialAdapter.a(str, str2, materialEntity);
        }
        this.g.setImageResource(R.drawable.select_check);
    }

    @SuppressLint({"RestrictedApi"})
    public void a(MaterialEntity materialEntity, String str, String str2, SelectMaterialAdapter selectMaterialAdapter) {
        if (materialEntity != null) {
            if (!TextUtils.isEmpty(materialEntity.materialName)) {
                this.d.setText(materialEntity.materialName);
            }
            if (!TextUtils.isEmpty(materialEntity.updateTime)) {
                this.f.setText(materialEntity.updateTime);
            }
            this.g.setOnClickListener(new View.OnClickListener(materialEntity, str, str2, selectMaterialAdapter) {
                private final /* synthetic */ MaterialEntity f$1;
                private final /* synthetic */ String f$2;
                private final /* synthetic */ String f$3;
                private final /* synthetic */ SelectMaterialAdapter f$4;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                    this.f$3 = r4;
                    this.f$4 = r5;
                }

                public final void onClick(View view) {
                    b.this.a(this.f$1, this.f$2, this.f$3, this.f$4, view);
                }
            });
            this.f63c.setBackgroundResource(R.drawable.winlicense);
            this.d.setAlpha(1.0f);
            this.f.setAlpha(1.0f);
            if ("1".equals(materialEntity.state)) {
                this.e.setVisibility(8);
                this.g.setImageResource(R.drawable.encheck);
                this.g.setClickable(false);
            } else if ("2".equals(materialEntity.state)) {
                this.e.setVisibility(8);
                this.g.setClickable(true);
                this.g.setImageResource(R.drawable.nocheck);
            } else if ("3".equals(materialEntity.state)) {
                this.e.setVisibility(0);
                this.e.setImageResource(R.drawable.sign_fail);
                this.g.setImageResource(R.drawable.encheck);
                this.g.setClickable(false);
            } else if ("4".equals(materialEntity.state)) {
                this.e.setVisibility(0);
                this.e.setImageResource(R.drawable.siging);
                this.f63c.setBackgroundResource(R.drawable.winlicense_gray);
                this.d.setAlpha(0.3f);
                this.f.setAlpha(0.3f);
                this.g.setClickable(false);
                this.g.setImageResource(R.drawable.encheck);
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(MaterialEntity materialEntity, String str, String str2, SelectMaterialAdapter selectMaterialAdapter, View view) {
        materialEntity.isCheckEntity = !materialEntity.isCheckEntity;
        if (materialEntity.isCheckEntity) {
            c(materialEntity, str, str2, selectMaterialAdapter);
        } else {
            b(materialEntity, str, str2, selectMaterialAdapter);
        }
    }
}

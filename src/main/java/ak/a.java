package ak;

import a.e;
import a.f;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.linewell.licence.R;
import com.linewell.licence.entity.SharePanelEntity;

public class a extends e<SharePanelEntity, f> {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public C0004a f98a;

    /* renamed from: ak.a$a  reason: collision with other inner class name */
    public interface C0004a {
        void a(String str);
    }

    public a() {
        super(R.layout.share_item);
    }

    /* access modifiers changed from: protected */
    public void a(f fVar, final SharePanelEntity sharePanelEntity) {
        fVar.e(R.id.root).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                a.this.f98a.a(sharePanelEntity.channelName);
            }
        });
        ((TextView) fVar.e(R.id.text)).setText(sharePanelEntity.channelName);
        ((ImageView) fVar.e(R.id.image)).setImageResource(sharePanelEntity.channelImage);
    }

    public void a(C0004a aVar) {
        this.f98a = aVar;
    }
}

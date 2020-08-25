package cn.xports.qd2.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import cn.xports.base.BaseBottomDialog;
import cn.xports.qd2.R;
import cn.xports.qd2.circle.CircleTypeBean;
import cn.xports.qd2.circle.SelectCircleTypeAdapter;
import java.util.List;

public class SelectCircleTypeDialog extends BaseBottomDialog {
    private SelectCircleTypeAdapter adapter;
    /* access modifiers changed from: private */
    public ClickListener clickListener;
    private List<CircleTypeBean> list;
    private RecyclerView recv;

    public interface ClickListener {
        void clickListener(int i);
    }

    public SelectCircleTypeDialog(@NonNull Context context) {
        super(context);
    }

    public void setData(List<CircleTypeBean> list2) {
        this.list = list2;
        this.adapter = new SelectCircleTypeAdapter(list2, getContext());
        this.recv.setAdapter(this.adapter);
        this.adapter.setListener(new SelectCircleTypeAdapter.SelectListener() {
            public void selected(int i) {
                if (SelectCircleTypeDialog.this.clickListener != null) {
                    SelectCircleTypeDialog.this.clickListener.clickListener(i);
                    SelectCircleTypeDialog.this.dismiss();
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onCreate() {
        setContentView(R.layout.dialog_select_circle_type);
        setCanceledOnTouchOutside(false);
        setCancelable(false);
        findViewById(R.id.iv_close).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SelectCircleTypeDialog.this.dismiss();
            }
        });
        this.recv = (RecyclerView) findViewById(R.id.recv_select_type);
        this.recv.setLayoutManager(new GridLayoutManager(getContext(), 4));
    }

    public void setItemClickListener(ClickListener clickListener2) {
        this.clickListener = clickListener2;
    }
}

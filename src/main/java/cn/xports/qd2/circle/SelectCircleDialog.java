package cn.xports.qd2.circle;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import cn.xports.base.BaseBottomDialog;
import cn.xports.baselib.adapter.XBaseAdapter;
import cn.xports.baselib.bean.DataMap;
import cn.xports.baselib.http.ResponseDataMap;
import cn.xports.baselib.http.RxUtil;
import cn.xports.baselib.mvp.IView;
import cn.xports.qd2.R;
import cn.xports.qd2.util.ApiUtil;
import com.alipay.sdk.cons.c;
import com.alipay.sdk.packet.d;
import org.jetbrains.annotations.NotNull;

public class SelectCircleDialog extends BaseBottomDialog {
    private Context context;
    /* access modifiers changed from: private */
    public SelectListener listener;
    private RecyclerView rvSelectCircle;

    public interface SelectListener {
        void onSelect(String str, String str2);
    }

    public SelectCircleDialog(@NonNull Context context2) {
        super(context2);
        this.context = context2;
        getCircleList();
    }

    /* access modifiers changed from: protected */
    public void onCreate() {
        setContentView(R.layout.dialog_select_circle);
        setCanceledOnTouchOutside(true);
        setCancelable(true);
        findViewById(R.id.iv_close).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SelectCircleDialog.this.dismiss();
            }
        });
        this.rvSelectCircle = (RecyclerView) findViewById(R.id.rv_select_circle);
    }

    private void getCircleList() {
        ApiUtil.getApi2().getMyJoinCircleInfo(1, 200).compose(RxUtil.applyDataMapIO()).subscribe(new ResponseDataMap((IView) this.context, true) {
            public void onSuccess(@NotNull DataMap dataMap) {
                SelectCircleDialog.this.refreshUi(dataMap);
            }

            public void onError(@NotNull DataMap dataMap) {
                super.onError(dataMap);
            }
        });
    }

    /* access modifiers changed from: private */
    public void refreshUi(DataMap dataMap) {
        if (isShowing()) {
            SelectCircleListAdapter selectCircleListAdapter = new SelectCircleListAdapter(dataMap.getDataMap(d.k).getDataList("list"), getContext());
            this.rvSelectCircle.setLayoutManager(new LinearLayoutManager(getContext()));
            this.rvSelectCircle.setAdapter(selectCircleListAdapter);
            selectCircleListAdapter.setOnItemClickListener(new XBaseAdapter.OnItemClickListener<DataMap>() {
                public void onItemClick(DataMap dataMap, int i, int i2) {
                    String num = dataMap.getInteger("id").toString();
                    String string = dataMap.getString(c.e);
                    if (SelectCircleDialog.this.listener != null) {
                        SelectCircleDialog.this.listener.onSelect(num, string);
                    }
                    SelectCircleDialog.this.dismiss();
                }
            });
        }
    }

    public void setSelectListener(SelectListener selectListener) {
        this.listener = selectListener;
    }
}

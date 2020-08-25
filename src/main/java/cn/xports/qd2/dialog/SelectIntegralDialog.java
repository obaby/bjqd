package cn.xports.qd2.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import cn.xports.base.BaseBottomDialog;
import cn.xports.baselib.util.ToastUtil;
import cn.xports.entity.CardPointInfo;
import cn.xports.qd2.R;
import cn.xports.qd2.adapter.SelectCardAdapter;
import java.util.List;

public class SelectIntegralDialog extends BaseBottomDialog {
    /* access modifiers changed from: private */
    public SelectCardAdapter adapter;
    private Context context;
    /* access modifiers changed from: private */
    public ExchangeClickListener exchangeClickListener;
    /* access modifiers changed from: private */
    public int index;
    /* access modifiers changed from: private */
    public List<CardPointInfo> list;
    private RecyclerView recv;

    public interface ExchangeClickListener {
        void clickListener(int i);
    }

    public SelectIntegralDialog(@NonNull Context context2) {
        super(context2);
        this.context = context2;
    }

    public void setData(final List<CardPointInfo> list2) {
        this.list = list2;
        for (int i = 0; i < list2.size(); i++) {
            list2.get(i).isSelect = false;
        }
        this.adapter = new SelectCardAdapter(list2);
        this.recv.setAdapter(this.adapter);
        this.adapter.setListener(new SelectCardAdapter.SelectListener() {
            public void selected(int i) {
                int unused = SelectIntegralDialog.this.index = i;
                int i2 = 0;
                while (i2 < list2.size()) {
                    ((CardPointInfo) list2.get(i2)).isSelect = i2 == i;
                    i2++;
                }
                SelectIntegralDialog.this.adapter.notifyDataSetChanged();
                SelectIntegralDialog.this.dismiss();
                if (SelectIntegralDialog.this.exchangeClickListener != null) {
                    SelectIntegralDialog.this.exchangeClickListener.clickListener(SelectIntegralDialog.this.index);
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onCreate() {
        setContentView(R.layout.dialog_select_integral);
        setCanceledOnTouchOutside(false);
        setCancelable(false);
        findViewById(R.id.iv_close).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SelectIntegralDialog.this.dismiss();
            }
        });
        this.recv = (RecyclerView) findViewById(R.id.recv_select_integral);
        this.recv.setLayoutManager(new LinearLayoutManager(this.context));
        findViewById(R.id.tv_confirm_exchange).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (SelectIntegralDialog.this.list.size() == 0) {
                    ToastUtil.showMsg("你还未绑定一卡通，请先绑定");
                } else if (SelectIntegralDialog.this.exchangeClickListener != null) {
                    SelectIntegralDialog.this.exchangeClickListener.clickListener(SelectIntegralDialog.this.index);
                }
            }
        });
    }

    public void setExchangeClickListener(ExchangeClickListener exchangeClickListener2) {
        this.exchangeClickListener = exchangeClickListener2;
    }
}

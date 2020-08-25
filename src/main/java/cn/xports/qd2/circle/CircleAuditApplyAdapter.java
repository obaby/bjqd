package cn.xports.qd2.circle;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import cn.xports.baselib.adapter.XBaseAdapter;
import cn.xports.baselib.adapter.XBaseHolder;
import cn.xports.baselib.bean.DataMap;
import cn.xports.baselib.util.GlideUtil;
import cn.xports.qd2.R;
import cn.xports.qd2.entity.K;
import cn.xports.sportCoaching.DateShowUtils;
import java.util.List;

public class CircleAuditApplyAdapter extends XBaseAdapter<DataMap> {
    private Context context;
    private List<DataMap> list;
    /* access modifiers changed from: private */
    public OnClickListener listener;

    public interface OnClickListener {
        void onAgreeClick(int i);
    }

    public CircleAuditApplyAdapter(List<DataMap> list2, Context context2) {
        super(list2);
        this.list = list2;
        this.context = context2;
    }

    public int getLayoutId() {
        return R.layout.item_audit_apply_msg;
    }

    public void onBindViewHolder(@NonNull XBaseHolder xBaseHolder, final int i) {
        DataMap dataMap = this.list.get(i);
        ImageView imageView = (ImageView) xBaseHolder.getView(R.id.iv_head);
        TextView textView = (TextView) xBaseHolder.getView(R.id.tv_time);
        TextView textView2 = (TextView) xBaseHolder.getView(R.id.tv_desc1);
        TextView textView3 = (TextView) xBaseHolder.getView(R.id.tv_agree);
        ((TextView) xBaseHolder.getView(R.id.tv_name)).setText(dataMap.getString("accountName"));
        String string = dataMap.getString(K.createTime);
        if (!TextUtils.isEmpty(string)) {
            textView.setText(DateShowUtils.getPostFriendlyTime(string));
        }
        GlideUtil.loadRoundImage(this.context, dataMap.getString("accountAvatar")).into(imageView);
        textView2.setText(dataMap.getString("applyMessage"));
        textView3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (CircleAuditApplyAdapter.this.listener != null) {
                    CircleAuditApplyAdapter.this.listener.onAgreeClick(i);
                }
            }
        });
    }

    public void setClickListener(OnClickListener onClickListener) {
        this.listener = onClickListener;
    }
}

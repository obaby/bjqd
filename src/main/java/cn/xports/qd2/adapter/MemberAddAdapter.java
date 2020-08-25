package cn.xports.qd2.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import cn.xports.baselib.adapter.XBaseAdapter;
import cn.xports.baselib.adapter.XBaseHolder;
import cn.xports.qd2.R;
import cn.xports.qd2.entity.MemberInfo;
import java.util.List;

public class MemberAddAdapter extends XBaseAdapter<MemberInfo> {
    /* access modifiers changed from: private */
    public OnEditMemberListener onEditMemberListener;

    public interface OnEditMemberListener {
        void OnEdit(MemberInfo memberInfo);
    }

    public MemberAddAdapter(List<MemberInfo> list) {
        super(list);
    }

    public int getLayoutId() {
        return R.layout.item_member;
    }

    public void onBindViewHolder(@NonNull XBaseHolder xBaseHolder, int i) {
        final MemberInfo memberInfo = (MemberInfo) this.list.get(i);
        if (memberInfo != null) {
            xBaseHolder.setText(R.id.tv_name, memberInfo.getName());
            xBaseHolder.setText(R.id.tv_phone, memberInfo.getMobileNum());
            xBaseHolder.setOnClickListener(R.id.iv_delete, new View.OnClickListener() {
                public void onClick(View view) {
                    MemberAddAdapter.this.list.remove(memberInfo);
                    MemberAddAdapter.this.notifyDataSetChanged();
                }
            });
            xBaseHolder.setOnClickListener(R.id.iv_edit, new View.OnClickListener() {
                public void onClick(View view) {
                    if (MemberAddAdapter.this.onEditMemberListener != null) {
                        MemberAddAdapter.this.onEditMemberListener.OnEdit(memberInfo);
                    }
                }
            });
            if (i == this.list.size() - 1) {
                xBaseHolder.setVisible(R.id.v_line, false);
            } else {
                xBaseHolder.setVisible(R.id.v_line, true);
            }
        }
    }

    public MemberAddAdapter setOnEditMemberListener(OnEditMemberListener onEditMemberListener2) {
        this.onEditMemberListener = onEditMemberListener2;
        return this;
    }
}

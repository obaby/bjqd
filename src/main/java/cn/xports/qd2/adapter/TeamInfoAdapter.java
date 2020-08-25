package cn.xports.qd2.adapter;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import cn.xports.baselib.adapter.XBaseAdapter;
import cn.xports.baselib.adapter.XBaseHolder;
import cn.xports.baselib.util.GlideUtil;
import cn.xports.qd2.R;
import cn.xports.qd2.entity.MemberInfo;
import java.util.List;

public class TeamInfoAdapter extends XBaseAdapter<MemberInfo> {
    public static final int DELETE = 20;
    public static final int EDIT = 10;
    public static final int SHOW_INFO = 30;
    private boolean editable = true;
    /* access modifiers changed from: private */
    public OnMemberOperationListener listener;

    public interface OnMemberOperationListener {
        void onItemOp(MemberInfo memberInfo, int i);
    }

    public TeamInfoAdapter(List<MemberInfo> list) {
        super(list);
    }

    public void setEditable(boolean z) {
        this.editable = z;
        notifyDataSetChanged();
    }

    public int getLayoutId() {
        return R.layout.item_team_member;
    }

    public void onBindViewHolder(@NonNull XBaseHolder xBaseHolder, int i) {
        final MemberInfo memberInfo = (MemberInfo) this.list.get(i);
        if (!"1".equals(memberInfo.getState()) || !this.editable) {
            xBaseHolder.setVisible(R.id.ll_edit, false);
        } else {
            xBaseHolder.setVisible(R.id.ll_edit, true);
        }
        xBaseHolder.setOnClickListener(R.id.iv_edit, new View.OnClickListener() {
            public void onClick(View view) {
                if (TeamInfoAdapter.this.listener != null) {
                    TeamInfoAdapter.this.listener.onItemOp(memberInfo, 10);
                }
            }
        });
        xBaseHolder.setOnClickListener(R.id.iv_delete, new View.OnClickListener() {
            public void onClick(View view) {
                if (TeamInfoAdapter.this.listener != null) {
                    TeamInfoAdapter.this.listener.onItemOp(memberInfo, 20);
                }
            }
        });
        xBaseHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (TeamInfoAdapter.this.listener != null) {
                    TeamInfoAdapter.this.listener.onItemOp(memberInfo, 30);
                }
            }
        });
        xBaseHolder.setText(R.id.tv_name, memberInfo.getName());
        ImageView imageView = (ImageView) xBaseHolder.getView(R.id.iv_header);
        GlideUtil.loadImage(imageView.getContext(), memberInfo.getAvatar(), R.drawable.ic_person_default_round).into(imageView);
        xBaseHolder.setVisible(R.id.iv_gender, true ^ TextUtils.isEmpty(memberInfo.getGender()));
        if ("1".equals(memberInfo.getGender())) {
            xBaseHolder.setImageResource(R.id.iv_gender, R.drawable.ic_nv);
        } else {
            xBaseHolder.setImageResource(R.id.iv_gender, R.drawable.ic_nan);
        }
    }

    public TeamInfoAdapter setListener(OnMemberOperationListener onMemberOperationListener) {
        this.listener = onMemberOperationListener;
        return this;
    }
}

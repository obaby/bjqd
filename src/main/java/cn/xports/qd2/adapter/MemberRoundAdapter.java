package cn.xports.qd2.adapter;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import cn.xports.baselib.adapter.XBaseAdapter;
import cn.xports.baselib.adapter.XBaseHolder;
import cn.xports.baselib.util.GlideUtil;
import cn.xports.qd2.R;
import cn.xports.qd2.entity.K;
import cn.xports.qd2.entity.MemberInfo;
import java.util.List;

public class MemberRoundAdapter extends XBaseAdapter<MemberInfo> {
    /* access modifiers changed from: private */
    public OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick();
    }

    public MemberRoundAdapter(List<MemberInfo> list) {
        super(list);
    }

    public int getLayoutId() {
        return R.layout.item_member_round;
    }

    public void onBindViewHolder(@NonNull XBaseHolder xBaseHolder, int i) {
        MemberInfo memberInfo = (MemberInfo) this.list.get(i);
        ImageView imageView = (ImageView) xBaseHolder.getView(R.id.iv_header);
        GlideUtil.loadImage(imageView.getContext(), memberInfo.getAvatar(), R.drawable.ic_person_default_round).into(imageView);
        if (TextUtils.isEmpty(memberInfo.getGender())) {
            xBaseHolder.setVisible(R.id.iv_gender, false);
        } else {
            xBaseHolder.setVisible(R.id.iv_gender, true);
            xBaseHolder.setImageResource(R.id.iv_gender, K.k0.equals(memberInfo.getGender()) ? R.drawable.ic_nan : R.drawable.ic_nv);
        }
        xBaseHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (MemberRoundAdapter.this.listener != null) {
                    MemberRoundAdapter.this.listener.onItemClick();
                }
            }
        });
    }

    public MemberRoundAdapter setListener(OnItemClickListener onItemClickListener) {
        this.listener = onItemClickListener;
        return this;
    }
}

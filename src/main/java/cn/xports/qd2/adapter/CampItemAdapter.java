package cn.xports.qd2.adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.View;
import cn.xports.baselib.adapter.XBaseAdapter;
import cn.xports.baselib.adapter.XBaseHolder;
import cn.xports.baselib.util.MoneyUtil;
import cn.xports.qd2.R;
import cn.xports.qd2.entity.CampItem;
import cn.xports.qd2.entity.K;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.TimeUtils;
import java.util.List;

public class CampItemAdapter extends XBaseAdapter<CampItem> {
    /* access modifiers changed from: private */
    public OnItemClick itemClick;

    public interface OnItemClick {
        void OnClick(CampItem campItem);
    }

    public CampItemAdapter(List<CampItem> list) {
        super(list);
    }

    public int getLayoutId() {
        return R.layout.item_camp_sign;
    }

    public void onBindViewHolder(@NonNull XBaseHolder xBaseHolder, int i) {
        String str;
        final CampItem campItem = (CampItem) this.list.get(i);
        xBaseHolder.setText(R.id.tv_name, campItem.getName());
        xBaseHolder.setText(R.id.tv_item_name, campItem.getDescription());
        xBaseHolder.setText(R.id.tv_end_time, campItem.getEnrollEndDate());
        xBaseHolder.setText(R.id.tv_person_num, campItem.getEnrolledNum());
        xBaseHolder.setText(R.id.tv_demand, campItem.getDemand());
        if ("1".equals(campItem.getEnrolledTag())) {
            xBaseHolder.setText(R.id.tv_state, "已报名");
            xBaseHolder.setTextColor(R.id.tv_state, Color.parseColor("#888888"));
            xBaseHolder.setVisible(R.id.rl_sign_up, false);
        } else if (TimeUtils.getTimeSpanByNow(campItem.getEnrollEndDate(), 60000) < 0) {
            xBaseHolder.setText(R.id.tv_state, "报名结束");
            xBaseHolder.setTextColor(R.id.tv_state, Color.parseColor("#888888"));
            xBaseHolder.setVisible(R.id.rl_sign_up, false);
        } else {
            xBaseHolder.setText(R.id.tv_state, "报名中");
            xBaseHolder.setTextColor(R.id.tv_state, Color.parseColor("#fd4772"));
            xBaseHolder.setVisible(R.id.rl_sign_up, true);
        }
        xBaseHolder.setOnClickListener(R.id.tv_sign_up, new View.OnClickListener() {
            public void onClick(View view) {
                if (CampItemAdapter.this.itemClick != null) {
                    CampItemAdapter.this.itemClick.OnClick(campItem);
                }
            }
        });
        if (campItem.getEnrollFee() == 0) {
            str = StringUtils.getString(R.string.yuan) + K.k0;
        } else {
            str = StringUtils.getString(R.string.yuan) + MoneyUtil.cent2Yuan(campItem.getEnrollFee());
        }
        xBaseHolder.setText(R.id.tv_sign_up, str + " 报名");
    }

    public CampItemAdapter setItemClick(OnItemClick onItemClick) {
        this.itemClick = onItemClick;
        return this;
    }
}

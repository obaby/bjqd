package cn.xports.qd2.adapter;

import android.support.annotation.NonNull;
import cn.xports.baselib.adapter.XBaseAdapter;
import cn.xports.baselib.adapter.XBaseHolder;
import cn.xports.baselib.bean.DataMap;
import cn.xports.qd2.R;
import java.util.List;

public class ScoreRankAdapter extends XBaseAdapter<DataMap> {
    public ScoreRankAdapter(List<DataMap> list) {
        super(list);
    }

    public int getLayoutId() {
        return R.layout.item_score_rank;
    }

    public void onBindViewHolder(@NonNull XBaseHolder xBaseHolder, int i) {
        DataMap dataMap = (DataMap) this.list.get(i);
        xBaseHolder.setText(R.id.tv_team_name, dataMap.getString("teamName"));
        xBaseHolder.setText(R.id.tv_score, dataMap.getString("score"));
        xBaseHolder.setText(R.id.tv_rank, dataMap.getString("rank"));
        switch (dataMap.getIntValue("rank").intValue()) {
            case 1:
                xBaseHolder.setVisible(R.id.tv_rank, false);
                xBaseHolder.setVisible(R.id.iv_rank, true);
                xBaseHolder.setImageResource(R.id.iv_rank, R.drawable.ic_rank_first);
                return;
            case 2:
                xBaseHolder.setVisible(R.id.tv_rank, false);
                xBaseHolder.setVisible(R.id.iv_rank, true);
                xBaseHolder.setImageResource(R.id.iv_rank, R.drawable.ic_rank_second);
                return;
            case 3:
                xBaseHolder.setVisible(R.id.tv_rank, false);
                xBaseHolder.setVisible(R.id.iv_rank, true);
                xBaseHolder.setImageResource(R.id.iv_rank, R.drawable.ic_rank_third);
                return;
            default:
                xBaseHolder.setVisible(R.id.tv_rank, true);
                xBaseHolder.setVisible(R.id.iv_rank, false);
                return;
        }
    }
}

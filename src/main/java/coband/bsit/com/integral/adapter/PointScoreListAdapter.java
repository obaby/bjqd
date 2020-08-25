package coband.bsit.com.integral.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import coband.bsit.com.integral.R;
import coband.bsit.com.integral.bean.CreditListBean;
import com.bumptech.glide.Glide;
import com.convenient.qd.core.utils.CommonUtils;
import java.util.List;

public class PointScoreListAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private Context context;
    private List<CreditListBean.ResultBean.DataGridBean.RowsBean> rowsBeans;

    public PointScoreListAdapter(Context context2, List<CreditListBean.ResultBean.DataGridBean.RowsBean> list) {
        this.context = context2;
        this.rowsBeans = list;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(this.context).inflate(R.layout.integral_item_point_score_list_layout, viewGroup, false));
    }

    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        CreditListBean.ResultBean.DataGridBean.RowsBean rowsBean = this.rowsBeans.get(i);
        myViewHolder.tv_scoreName.setText(rowsBean.getDescription());
        if (Long.valueOf(rowsBean.getOrderid()) == null || 0 == rowsBean.getOrderid()) {
            myViewHolder.tv_scoreOrderId.setVisibility(8);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) myViewHolder.tv_scoreDate.getLayoutParams();
            layoutParams.topMargin = CommonUtils.dp2px(this.context, 15);
            myViewHolder.tv_scoreDate.setLayoutParams(layoutParams);
        } else {
            myViewHolder.tv_scoreOrderId.setVisibility(0);
            TextView access$100 = myViewHolder.tv_scoreOrderId;
            access$100.setText("订单号:" + rowsBean.getOrderid());
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) myViewHolder.tv_scoreDate.getLayoutParams();
            layoutParams2.topMargin = CommonUtils.dp2px(this.context, 5);
            myViewHolder.tv_scoreDate.setLayoutParams(layoutParams2);
        }
        myViewHolder.tv_scoreDate.setText(rowsBean.getCredate());
        if (rowsBean.getCredit() > 0) {
            TextView access$300 = myViewHolder.tv_addScore;
            access$300.setText("+" + rowsBean.getCredit());
        } else {
            myViewHolder.tv_addScore.setText(String.valueOf(rowsBean.getCredit()));
        }
        int type = rowsBean.getType();
        if (type != -1) {
            switch (type) {
                case 1:
                    initImg(R.mipmap.integral_icon_zhuce, myViewHolder.iv_scoreType);
                    return;
                case 2:
                case 3:
                case 4:
                case 5:
                case 7:
                case 8:
                case 16:
                case 17:
                    initImg(R.mipmap.integral_icon_huoqujifen, myViewHolder.iv_scoreType);
                    return;
                case 6:
                case 19:
                    initImg(R.mipmap.integral_icon_jifenkoujian, myViewHolder.iv_scoreType);
                    return;
                case 9:
                    initImg(R.mipmap.integral_icon_qiandao, myViewHolder.iv_scoreType);
                    return;
                case 10:
                    initImg(R.mipmap.integral_icon_shiming, myViewHolder.iv_scoreType);
                    return;
                case 11:
                    initImg(R.mipmap.integral_icon_shiren, myViewHolder.iv_scoreType);
                    return;
                case 12:
                    initImg(R.mipmap.integral_icon_hsyc, myViewHolder.iv_scoreType);
                    return;
                case 13:
                    initImg(R.mipmap.integral_icon_jiaofei, myViewHolder.iv_scoreType);
                    return;
                case 14:
                    initImg(R.mipmap.integral_icon_jyjf, myViewHolder.iv_scoreType);
                    return;
                case 15:
                case 20:
                    initImg(R.mipmap.integral_icon_ljfl, myViewHolder.iv_scoreType);
                    return;
                case 18:
                    initImg(R.mipmap.integral_icon_duihuan, myViewHolder.iv_scoreType);
                    return;
                default:
                    initImg(R.mipmap.integral_icon_jifenqita, myViewHolder.iv_scoreType);
                    return;
            }
        } else {
            initImg(R.mipmap.integral_icon_huoqujifen, myViewHolder.iv_scoreType);
        }
    }

    public int getItemCount() {
        if (this.rowsBeans == null) {
            return 0;
        }
        return this.rowsBeans.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        /* access modifiers changed from: private */
        public ImageView iv_scoreType;
        /* access modifiers changed from: private */
        public TextView tv_addScore;
        /* access modifiers changed from: private */
        public TextView tv_scoreDate;
        /* access modifiers changed from: private */
        public TextView tv_scoreName;
        /* access modifiers changed from: private */
        public TextView tv_scoreOrderId;

        public MyViewHolder(View view) {
            super(view);
            this.iv_scoreType = (ImageView) view.findViewById(R.id.iv_scoreType);
            this.tv_scoreName = (TextView) view.findViewById(R.id.tv_scoreName);
            this.tv_scoreOrderId = (TextView) view.findViewById(R.id.tv_scoreOrderId);
            this.tv_scoreDate = (TextView) view.findViewById(R.id.tv_scoreDate);
            this.tv_addScore = (TextView) view.findViewById(R.id.tv_addScore);
        }
    }

    private void initImg(int i, ImageView imageView) {
        Glide.with(this.context).load(Integer.valueOf(i)).into(imageView);
    }
}

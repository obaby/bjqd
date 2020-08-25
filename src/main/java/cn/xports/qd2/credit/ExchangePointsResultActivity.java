package cn.xports.qd2.credit;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import cn.xports.base.BaseBussActivity;
import cn.xports.baselib.mvp.IPresenter;
import cn.xports.qd2.R;
import com.blankj.utilcode.util.ActivityUtils;

public class ExchangePointsResultActivity extends BaseBussActivity<IPresenter> {
    private ImageView ivState;
    private String result;
    /* access modifiers changed from: private */
    public int state;
    private TextView tvContinue;
    private TextView tvMyResv;
    private TextView tvResult;

    public void initData() {
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.activity_exchange_points_result;
    }

    /* access modifiers changed from: protected */
    public String getChildTitle() {
        Intent intent = getIntent();
        this.state = intent.getIntExtra(CouponExchangeDetailActivity.RESULT_STATE, 0);
        this.result = intent.getStringExtra(CouponExchangeDetailActivity.RESULT_MSG);
        return this.state == 0 ? "兑换成功" : "兑换失败";
    }

    /* access modifiers changed from: protected */
    public void initView() {
        this.ivState = (ImageView) findViewById(R.id.ivState);
        this.tvResult = (TextView) findViewById(R.id.tvState);
        this.tvContinue = (TextView) findViewById(R.id.tvContinueResv);
        this.tvMyResv = (TextView) findViewById(R.id.tvMyResv);
        if (this.state == 0) {
            this.tvContinue.setText("完成");
            this.tvResult.setText("恭喜您，兑换成功！");
        } else {
            this.ivState.setImageDrawable(getResources().getDrawable(R.drawable.bg_fail));
            this.tvMyResv.setVisibility(0);
            TextView textView = this.tvResult;
            textView.setText("兑换失败，" + this.result);
            this.tvMyResv.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ExchangePointsResultActivity.this.finish();
                }
            });
            this.tvContinue.setText("回到首页");
        }
        this.tvContinue.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (ExchangePointsResultActivity.this.state != 0) {
                    ActivityUtils.finishToActivity(CreditStoreActivity.class, false);
                }
                ExchangePointsResultActivity.this.finish();
            }
        });
    }
}

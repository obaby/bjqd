package coband.bsit.com.integral.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import coband.bsit.com.integral.R;
import com.convenient.qd.core.base.arouter.ARouterUtils;
import com.convenient.qd.core.base.mvp.BaseActivity;
import com.convenient.qd.core.base.mvp.BasePresenter;
import com.convenient.qd.core.user.UserDBHelper;
import com.convenient.qd.core.utils.ToastUtils;

public class PointTaskActivity extends BaseActivity implements View.OnClickListener {
    LinearLayout home_back;
    ImageView iv_share;
    TextView tv_shiMing;
    TextView tv_shiRen;
    TextView tv_spendMoney;
    TextView tv_zhuCe;
    View view_top;

    /* access modifiers changed from: protected */
    public BasePresenter createPresenter() {
        return null;
    }

    /* access modifiers changed from: protected */
    public int initLayout() {
        return R.layout.integral_activity_point_task_layout;
    }

    public void initView() {
        this.view_top = findViewById(R.id.view_top);
        this.home_back = (LinearLayout) findViewById(R.id.home_back);
        this.tv_zhuCe = (TextView) findViewById(R.id.tv_zhuCe);
        this.tv_shiMing = (TextView) findViewById(R.id.tv_shiMing);
        this.tv_shiRen = (TextView) findViewById(R.id.tv_shiRen);
        this.iv_share = (ImageView) findViewById(R.id.iv_share);
        this.tv_spendMoney = (TextView) findViewById(R.id.tv_spendMoney);
        if (this.mImmersionBar != null) {
            this.mImmersionBar.titleBarMarginTop(R.id.home_title).init();
        }
    }

    /* access modifiers changed from: protected */
    public void initImmersionBar() {
        PointTaskActivity.super.initImmersionBar();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        PointTaskActivity.super.onResume();
        if (UserDBHelper.getInstance().getNameAuthFlag() != 0) {
            this.tv_shiMing.setText("已认证");
            this.tv_shiMing.setEnabled(false);
            this.tv_shiMing.setBackgroundResource(R.drawable.integral_bg_point_task_btn_noclick);
        } else {
            this.tv_shiMing.setText("去查看");
            this.tv_shiMing.setEnabled(true);
            this.tv_shiMing.setBackgroundResource(R.drawable.integral_bg_point_task_btn_click);
        }
        if (UserDBHelper.getInstance().getFaceAuthFlag() != 0) {
            this.tv_shiRen.setText("已认证");
            this.tv_shiRen.setEnabled(false);
            this.tv_shiRen.setBackgroundResource(R.drawable.integral_bg_point_task_btn_noclick);
            return;
        }
        this.tv_shiRen.setText("去认证");
        this.tv_shiRen.setEnabled(true);
        this.tv_shiRen.setBackgroundResource(R.drawable.integral_bg_point_task_btn_click);
    }

    public void initData() {
        PointTaskActivity.super.initData();
    }

    public void initListener() {
        PointTaskActivity.super.initListener();
        this.home_back.setOnClickListener(this);
        this.tv_shiMing.setOnClickListener(this);
        this.tv_shiRen.setOnClickListener(this);
        this.iv_share.setOnClickListener(this);
        this.tv_spendMoney.setOnClickListener(this);
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.content.Context, coband.bsit.com.integral.activity.PointTaskActivity] */
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.home_back) {
            finish();
        } else if (id == R.id.tv_shiMing) {
            ARouterUtils.navigation("/idcard/IDCardActivity");
        } else if (id == R.id.tv_shiRen) {
            if (UserDBHelper.getInstance().getNameAuthFlag() == 0) {
                ToastUtils.showShort(this, "请先进行实名认证");
            } else {
                ARouterUtils.navigation("/face/FaceRecognitionActivity");
            }
        } else if (id == R.id.iv_share) {
            startActivity(PointInviteActivity.class);
        } else if (id == R.id.tv_spendMoney) {
            ARouterUtils.navigation("/menu/MenuActivity");
        }
    }
}

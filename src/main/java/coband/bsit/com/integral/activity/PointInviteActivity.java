package coband.bsit.com.integral.activity;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import coband.bsit.com.integral.R;
import coband.bsit.com.integral.api.DataManager;
import coband.bsit.com.integral.bean.ShareUrlBean;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.convenient.qd.core.base.mvp.BaseActivity;
import com.convenient.qd.core.base.mvp.BasePresenter;
import com.convenient.qd.core.bean.BaseResBean;
import com.convenient.qd.core.net.BaseHttpObserver;
import com.convenient.qd.core.utils.ShareUtil;
import com.convenient.qd.core.utils.ToastUtils;
import com.convenient.qd.core.widget.LoadingDiaLogUtils;
import net.arvin.socialhelper.callback.SocialShareCallback;

@Route(path = "/share/PointInviteActivity")
public class PointInviteActivity extends BaseActivity implements View.OnClickListener, SocialShareCallback {
    RelativeLayout back;
    private String shareDes = "市区扫码乘公交享八折；在线购药极速到家；垃圾分类兑换积分；水电气暖在线缴纳";
    private String shareTitle = "便捷青岛，生活更美好";
    /* access modifiers changed from: private */
    public String targetUrl;
    TextView title;
    TextView tv_invite;

    /* access modifiers changed from: protected */
    public BasePresenter createPresenter() {
        return null;
    }

    /* access modifiers changed from: protected */
    public int initLayout() {
        return R.layout.integral_activity_invite_layout;
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, coband.bsit.com.integral.activity.PointInviteActivity] */
    /* access modifiers changed from: protected */
    public void initView() {
        this.title = (TextView) findViewById(R.id.title);
        this.back = (RelativeLayout) findViewById(R.id.back);
        this.tv_invite = (TextView) findViewById(R.id.tv_invite);
        this.title.setText("邀请用户");
        LoadingDiaLogUtils.showLoadingDialog(this);
        DataManager.getInstance().getShareUrl(new BaseHttpObserver<BaseResBean<ShareUrlBean>>() {
            public void onSuccess(BaseResBean<ShareUrlBean> baseResBean) {
                LoadingDiaLogUtils.dismisDialog();
                if (baseResBean != null && baseResBean.getCode() == 200) {
                    String unused = PointInviteActivity.this.targetUrl = ((ShareUrlBean) baseResBean.getResult()).getUrl();
                }
            }

            public void onFailure(int i, String str) {
                LoadingDiaLogUtils.dismisDialog();
            }
        });
    }

    /* access modifiers changed from: protected */
    public void initListener() {
        PointInviteActivity.super.initListener();
        this.back.setOnClickListener(this);
        this.tv_invite.setOnClickListener(this);
    }

    /* access modifiers changed from: protected */
    public void initImmersionBar() {
        PointInviteActivity.super.initImmersionBar();
        setStatusBar(R.id.title_header);
    }

    /* JADX WARNING: type inference failed for: r8v0, types: [android.content.Context, coband.bsit.com.integral.activity.PointInviteActivity] */
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.back) {
            finish();
        } else if (id == R.id.tv_invite) {
            ShareUtil.getInstance().share(this, LayoutInflater.from(this).inflate(R.layout.integral_activity_invite_layout, (ViewGroup) null), this.shareTitle, this.targetUrl, this.shareDes, "");
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        PointInviteActivity.super.onActivityResult(i, i2, intent);
        if (ShareUtil.getInstance().socialHelper != null) {
            ShareUtil.getInstance().socialHelper.onActivityResult(i, i2, intent);
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        PointInviteActivity.super.onDestroy();
        if (ShareUtil.getInstance().socialHelper != null) {
            ShareUtil.getInstance().socialHelper.clear();
        }
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [android.content.Context, coband.bsit.com.integral.activity.PointInviteActivity] */
    public void shareSuccess(int i) {
        ToastUtils.showShort(this, "分享成功");
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [android.content.Context, coband.bsit.com.integral.activity.PointInviteActivity] */
    public void socialError(String str) {
        ToastUtils.showShort(this, str);
    }

    public void backgroundAlpha(float f) {
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.alpha = f;
        getWindow().setAttributes(attributes);
    }
}

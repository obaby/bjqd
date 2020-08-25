package coband.bsit.com.integral.activity;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import coband.bsit.com.integral.R;
import coband.bsit.com.integral.api.DataManager;
import coband.bsit.com.integral.bean.UserSign;
import coband.bsit.com.integral.bean.UserSignInfoToday;
import coband.bsit.com.integral.bean.UserSignListMonth;
import coband.bsit.com.integral.listener.OnPagerChangeListener;
import coband.bsit.com.integral.utils.CalendarUtil;
import coband.bsit.com.integral.widget.CalendarView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.utils.Consts;
import com.convenient.qd.core.base.mvp.BaseActivity;
import com.convenient.qd.core.base.mvp.BasePresenter;
import com.convenient.qd.core.utils.CommonUtils;
import com.convenient.qd.core.utils.ToastUtils;
import com.convenient.qd.core.widget.LoadingDiaLogUtils;
import io.reactivex.observers.DefaultObserver;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Route(path = "/point/PointCalendarActivity")
public class PointCalendarActivity extends BaseActivity implements View.OnClickListener {
    /* access modifiers changed from: private */
    public int[] cDate = CalendarUtil.getCurrentDate();
    CalendarView calendar;
    /* access modifiers changed from: private */
    public boolean isSignInfoFinish;
    /* access modifiers changed from: private */
    public boolean isSignInfoMonthFinish;
    ImageView iv_back;
    ImageView iv_beforeMonth;
    ImageView iv_nextMonth;
    /* access modifiers changed from: private */
    public List<String> list;
    TextView title;
    TextView tv_dayNum;
    TextView tv_sign;
    View view_top;

    /* access modifiers changed from: protected */
    public BasePresenter createPresenter() {
        return null;
    }

    /* access modifiers changed from: protected */
    public int initLayout() {
        return R.layout.integral_activity_point_calendar;
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, coband.bsit.com.integral.activity.PointCalendarActivity] */
    public void initView() {
        this.iv_back = (ImageView) findViewById(R.id.iv_back);
        this.view_top = findViewById(R.id.view_top);
        this.tv_sign = (TextView) findViewById(R.id.tv_sign);
        this.tv_dayNum = (TextView) findViewById(R.id.tv_dayNum);
        this.iv_beforeMonth = (ImageView) findViewById(R.id.iv_beforeMonth);
        this.title = (TextView) findViewById(R.id.title);
        this.iv_nextMonth = (ImageView) findViewById(R.id.iv_nextMonth);
        this.calendar = (CalendarView) findViewById(R.id.calendar);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.view_top.getLayoutParams();
        layoutParams.height = CommonUtils.getStatusBarHeight(this);
        this.view_top.setLayoutParams(layoutParams);
        this.list = new ArrayList();
    }

    public void initData() {
        PointCalendarActivity.super.initData();
        getSignInfo();
        checkSignInfo(this.cDate[0], this.cDate[1]);
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, coband.bsit.com.integral.activity.PointCalendarActivity] */
    /* access modifiers changed from: private */
    public void getSignInfo() {
        LoadingDiaLogUtils.showLoadingDialog(this);
        DataManager.getInstance().getSignInfo(new DefaultObserver<UserSignInfoToday>() {
            /* JADX WARNING: type inference failed for: r0v2, types: [android.content.Context, coband.bsit.com.integral.activity.PointCalendarActivity] */
            public void onNext(UserSignInfoToday userSignInfoToday) {
                if (userSignInfoToday != null) {
                    if (userSignInfoToday.getCode() == 200) {
                        boolean unused = PointCalendarActivity.this.isSignInfoFinish = true;
                        String todaySignFlg = userSignInfoToday.getResult().getTodaySignFlg();
                        PointCalendarActivity.this.tv_dayNum.setText(String.valueOf(userSignInfoToday.getResult().getSigncnt()));
                        if ("Y".equals(todaySignFlg)) {
                            PointCalendarActivity.this.tv_sign.setText("已签到+1信豆");
                            PointCalendarActivity.this.tv_sign.setEnabled(false);
                        } else {
                            PointCalendarActivity.this.tv_sign.setText("我要签到");
                            PointCalendarActivity.this.tv_sign.setEnabled(true);
                        }
                    } else {
                        boolean unused2 = PointCalendarActivity.this.isSignInfoFinish = false;
                        ToastUtils.showShort(PointCalendarActivity.this, userSignInfoToday.getMessage());
                    }
                    if (PointCalendarActivity.this.isSignInfoFinish && PointCalendarActivity.this.isSignInfoMonthFinish) {
                        LoadingDiaLogUtils.dismisDialog();
                    }
                }
            }

            public void onError(Throwable th) {
                LoadingDiaLogUtils.dismisDialog();
            }

            public void onComplete() {
                LoadingDiaLogUtils.dismisDialog();
            }
        });
    }

    public void initListener() {
        this.calendar.setOnPagerChangeListener(new OnPagerChangeListener() {
            public void onPagerChanged(int[] iArr) {
                TextView textView = PointCalendarActivity.this.title;
                textView.setText(iArr[0] + "年" + iArr[1] + "月");
            }
        });
        this.iv_back.setOnClickListener(this);
        this.tv_sign.setOnClickListener(this);
        this.iv_beforeMonth.setOnClickListener(this);
        this.iv_nextMonth.setOnClickListener(this);
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.content.Context, coband.bsit.com.integral.activity.PointCalendarActivity] */
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.iv_back) {
            finish();
        } else if (id == R.id.tv_sign) {
            LoadingDiaLogUtils.showLoadingDialog(this);
            DataManager.getInstance().userSign(new DefaultObserver<UserSign>() {
                /* JADX WARNING: type inference failed for: r0v1, types: [android.content.Context, coband.bsit.com.integral.activity.PointCalendarActivity] */
                /* JADX WARNING: type inference failed for: r0v4, types: [android.content.Context, coband.bsit.com.integral.activity.PointCalendarActivity] */
                public void onNext(UserSign userSign) {
                    LoadingDiaLogUtils.dismisDialog();
                    if (userSign != null) {
                        if (userSign.getCode() == 200) {
                            PointCalendarActivity.this.getSignInfo();
                            PointCalendarActivity.this.initCalendar();
                            LocalBroadcastManager.getInstance(PointCalendarActivity.this).sendBroadcast(new Intent("refresh_point"));
                        }
                        ToastUtils.showShort(PointCalendarActivity.this, userSign.getResult());
                    }
                }

                public void onError(Throwable th) {
                    LoadingDiaLogUtils.dismisDialog();
                }

                public void onComplete() {
                    LoadingDiaLogUtils.dismisDialog();
                }
            });
        } else if (id == R.id.iv_beforeMonth) {
            this.calendar.lastMonth();
        } else if (id == R.id.iv_nextMonth) {
            this.calendar.nextMonth();
        }
    }

    private void checkSignInfo(final int i, final int i2) {
        this.list.clear();
        DataManager.getInstance().getSignInfoMonth(new DefaultObserver<UserSignListMonth>() {
            /* JADX WARNING: type inference failed for: r0v1, types: [android.content.Context, coband.bsit.com.integral.activity.PointCalendarActivity] */
            public void onNext(UserSignListMonth userSignListMonth) {
                if (userSignListMonth != null) {
                    if (userSignListMonth.getCode() == 200) {
                        boolean unused = PointCalendarActivity.this.isSignInfoMonthFinish = true;
                        List<UserSignListMonth.ResultBean> result = userSignListMonth.getResult();
                        for (int i = 0; i < result.size(); i++) {
                            PointCalendarActivity.this.list.add(result.get(i).getSignTime().split(" ")[0].replace("-", Consts.DOT));
                        }
                        PointCalendarActivity.this.calendar.removeAllViews();
                        CalendarView calendarView = PointCalendarActivity.this.calendar;
                        calendarView.setInitDate(i + Consts.DOT + i2).setStartEndDate("2019.10", "2069.12").setMultiDate(PointCalendarActivity.this.list).init();
                        TextView textView = PointCalendarActivity.this.title;
                        textView.setText(PointCalendarActivity.this.cDate[0] + "年" + PointCalendarActivity.this.cDate[1] + "月");
                    } else {
                        ToastUtils.showShort(PointCalendarActivity.this, userSignListMonth.getMessage());
                    }
                    if (PointCalendarActivity.this.isSignInfoFinish && PointCalendarActivity.this.isSignInfoMonthFinish) {
                        LoadingDiaLogUtils.dismisDialog();
                    }
                }
            }

            public void onError(Throwable th) {
                LoadingDiaLogUtils.dismisDialog();
            }

            public void onComplete() {
                LoadingDiaLogUtils.dismisDialog();
            }
        });
    }

    /* access modifiers changed from: private */
    public void initCalendar() {
        this.list.add(new SimpleDateFormat("yyyy.MM.dd").format(new Date(System.currentTimeMillis())));
        this.calendar.removeAllViews();
        this.calendar.clearOnPageChangeListeners();
        CalendarView calendarView = this.calendar;
        calendarView.setInitDate(this.cDate[0] + Consts.DOT + this.cDate[1]).setStartEndDate("2019.10", "2069.12").setMultiDate(this.list).init();
        this.tv_dayNum.setText(String.valueOf(this.list.size()));
        this.tv_sign.setText("已签到+1信豆");
        this.tv_sign.setEnabled(false);
    }
}

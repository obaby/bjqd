package cn.xports.qd2.circle;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import cn.xports.baselib.adapter.XBaseAdapter;
import cn.xports.baselib.bean.DataMap;
import cn.xports.baselib.http.ResponseDataMap;
import cn.xports.baselib.http.RxUtil;
import cn.xports.qd2.R;
import cn.xports.qd2.circle.videoPlayer.MyBaseActivity;
import cn.xports.qd2.entity.K;
import cn.xports.qd2.util.ApiUtil;
import com.alipay.sdk.packet.d;
import com.stub.StubApp;
import java.util.ArrayList;
import org.jetbrains.annotations.NotNull;

public class MyCircleListActivity extends MyBaseActivity {
    private View rlEmpty;
    private RecyclerView rvMyCircle;
    private TextView tvEmptyDes;
    private TextView tvRemindMsgCount;

    static {
        StubApp.interface11(3833);
    }

    private void initData() {
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    private void initView() {
        this.tvRemindMsgCount = (TextView) findViewById(R.id.tv_msg_remind_count);
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MyCircleListActivity.this.finish();
            }
        });
        ((TextView) findViewById(R.id.tv_titles)).setText("我的圈子");
        this.rvMyCircle = (RecyclerView) findViewById(R.id.rv_my_circle_list);
        findViewById(R.id.iv_add_circle).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MyCircleListActivity.this.startActivity(new Intent(MyCircleListActivity.this, CreateCircleActivity.class));
            }
        });
        findViewById(R.id.rl_my_circle_center).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MyCircleListActivity.this.startActivity(new Intent(MyCircleListActivity.this, CircleMyCenterActivity.class));
            }
        });
        this.rlEmpty = findViewById(R.id.rl_circle_empty);
        this.tvEmptyDes = (TextView) findViewById(R.id.tv_empty_des);
        this.tvEmptyDes.setText("暂无圈子");
    }

    private final void getMyJoinCircle() {
        ApiUtil.getApi2().getMyJoinCircleInfo(1, 999).compose(RxUtil.applyDataMapIO()).subscribe(new ResponseDataMap(this, true) {
            public void onSuccess(@NotNull DataMap dataMap) {
                MyCircleListActivity.this.refreshUi(dataMap);
            }

            public void onError(@NotNull DataMap dataMap) {
                super.onError(dataMap);
            }
        });
    }

    /* access modifiers changed from: private */
    public void refreshUi(DataMap dataMap) {
        ArrayList<DataMap> dataList = dataMap.getDataMap(d.k).getDataList("list");
        if (dataList == null || dataList.isEmpty()) {
            this.rlEmpty.setVisibility(0);
            this.rvMyCircle.setVisibility(8);
        } else {
            this.rlEmpty.setVisibility(8);
            this.rvMyCircle.setVisibility(0);
            MyCircleListAdapter myCircleListAdapter = new MyCircleListAdapter(dataList, this);
            myCircleListAdapter.setOnItemClickListener(new XBaseAdapter.OnItemClickListener<DataMap>() {
                public void onItemClick(DataMap dataMap, int i, int i2) {
                    MyCircleListActivity.this.startActivity(new Intent(MyCircleListActivity.this, CircleDetailActivity.class).putExtra(CircleDetailActivity.CIRCLE_ID, dataMap.getString("id")));
                    if (dataMap.getIntValue("postNew").intValue() > 0) {
                        MyCircleListActivity.this.updateReadTime(dataMap.getString("id"));
                        MyCircleListActivity.this.sendBroadcast(new Intent(CircleHomeActivity.CIRCLE_CHANGE_ACTION));
                    }
                }
            });
            this.rvMyCircle.setLayoutManager(new LinearLayoutManager(this));
            this.rvMyCircle.setAdapter(myCircleListAdapter);
        }
        if ("1".equals(dataMap.getDataMap(d.k).getDataMap(K.extra).getString("createCircle"))) {
            findViewById(R.id.iv_add_circle).setVisibility(0);
        } else {
            findViewById(R.id.iv_add_circle).setVisibility(8);
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        getMyJoinCircle();
        getRemindMsg();
    }

    private void getRemindMsg() {
        ApiUtil.getApi2().getCircleMyCenterInfo().compose(RxUtil.applyDataMapIO()).subscribe(new ResponseDataMap(this, false) {
            public void onSuccess(@NotNull DataMap dataMap) {
                MyCircleListActivity.this.refreshMyCenterUi(dataMap);
            }

            public void onError(@NotNull DataMap dataMap) {
                super.onError(dataMap);
            }
        });
    }

    /* access modifiers changed from: private */
    public void refreshMyCenterUi(DataMap dataMap) {
        int intValue = dataMap.getDataMap(d.k).getInteger("unreadMessage").intValue();
        if (intValue > 0) {
            this.tvRemindMsgCount.setVisibility(0);
            TextView textView = this.tvRemindMsgCount;
            textView.setText(intValue + "");
            return;
        }
        this.tvRemindMsgCount.setVisibility(8);
    }

    /* access modifiers changed from: private */
    public void updateReadTime(String str) {
        ApiUtil.getApi2().updateRead("2", str).compose(RxUtil.applyDataMapIO()).subscribe(new ResponseDataMap(this, false) {
            public void onError(@NonNull DataMap dataMap) {
            }

            public void onSuccess(@NonNull DataMap dataMap) {
            }
        });
    }
}

package cn.xports.qd2.circle;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import cn.xports.base.GlobalDialog;
import cn.xports.baselib.bean.DataMap;
import cn.xports.baselib.http.ResponseDataMap;
import cn.xports.baselib.http.RxUtil;
import cn.xports.baselib.util.GlideUtil;
import cn.xports.baselib.util.SPUtil;
import cn.xports.baselib.util.ToastUtil;
import cn.xports.qd2.R;
import cn.xports.qd2.circle.videoPlayer.MyBaseActivity;
import cn.xports.qd2.dialog.EditMsgDialog;
import cn.xports.qd2.entity.K;
import cn.xports.qd2.util.ApiUtil;
import com.alipay.sdk.cons.c;
import com.alipay.sdk.packet.d;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.KeyboardUtils;
import com.google.gson.Gson;
import com.stub.StubApp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.jetbrains.annotations.NotNull;

public class CircleSettingsActivity extends MyBaseActivity implements View.OnClickListener {
    private String circleId;
    /* access modifiers changed from: private */
    public Map<String, String> circleInfoMap = new HashMap();
    private int curTag = 0;
    private boolean isFirst = true;
    private ImageView ivBack;
    private ImageView ivHead;
    private RelativeLayout rlCircleMember;
    private RelativeLayout rlIntroduce;
    private RelativeLayout rlNotice;
    private String role;
    private Switch sJoinAudit;
    private TextView tvCircleIntroduce;
    private TextView tvCircleNotice;
    private TextView tvConfirm;
    private TextView tvName;

    static {
        StubApp.interface11(3780);
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    private void initView() {
        this.ivBack = (ImageView) findViewById(R.id.iv_back);
        this.ivHead = (ImageView) findViewById(R.id.iv_head);
        this.tvName = (TextView) findViewById(R.id.tv_name);
        this.tvConfirm = (TextView) findViewById(R.id.tv_confirm);
        this.tvCircleNotice = (TextView) findViewById(R.id.tv_circle_notice);
        this.tvCircleIntroduce = (TextView) findViewById(R.id.tv_circle_introduces);
        this.rlNotice = (RelativeLayout) findViewById(R.id.rl_circle_notice);
        this.rlIntroduce = (RelativeLayout) findViewById(R.id.rl_circle_introduce);
        this.rlCircleMember = (RelativeLayout) findViewById(R.id.rl_circle_member);
        this.sJoinAudit = (Switch) findViewById(R.id.switch_join_check);
        this.ivBack.setOnClickListener(this);
        KeyboardUtils.hideSoftInput(this);
    }

    private void initData() {
        this.circleId = getIntent().getStringExtra(CircleDetailActivity.CIRCLE_ID);
        getCircleDetailInfo();
        switchRole();
    }

    /* access modifiers changed from: private */
    public void getCircleDetailInfo() {
        ApiUtil.getApi2().getCircleDetail(this.circleId).compose(RxUtil.applyDataMapIO()).subscribe(new ResponseDataMap(this, true) {
            public void onSuccess(@NotNull DataMap dataMap) {
                CircleSettingsActivity.this.refreshTitleInfo(dataMap);
            }

            public void onError(@NotNull DataMap dataMap) {
                super.onError(dataMap);
            }
        });
    }

    /* access modifiers changed from: private */
    public void refreshTitleInfo(DataMap dataMap) {
        GlideUtil.loadRoundImage((Context) this, dataMap.getDataMap(d.k).getString("avatar")).into(this.ivHead);
        this.tvName.setText(dataMap.getDataMap(d.k).getString(c.e));
        String string = dataMap.getDataMap(d.k).getString("announcement");
        if (TextUtils.isEmpty(string)) {
            this.tvCircleNotice.setVisibility(8);
        } else {
            this.tvCircleNotice.setVisibility(0);
            this.tvCircleNotice.setText(string);
        }
        String string2 = dataMap.getDataMap(d.k).getString("description");
        if (TextUtils.isEmpty(string2)) {
            this.tvCircleIntroduce.setVisibility(8);
        } else {
            this.tvCircleIntroduce.setVisibility(0);
            this.tvCircleIntroduce.setText(string2);
        }
        String string3 = dataMap.getDataMap(d.k).getString("auditTag");
        if (TextUtils.isEmpty(string3) || !string3.equals("1")) {
            this.sJoinAudit.setChecked(false);
        } else {
            this.sJoinAudit.setChecked(true);
        }
        this.circleInfoMap.put(c.e, dataMap.getDataMap(d.k).getString(c.e));
        this.circleInfoMap.put("boardId", dataMap.getDataMap(d.k).getString("boardId"));
        this.circleInfoMap.put("description", dataMap.getDataMap(d.k).getString("description"));
        this.circleInfoMap.put("auditTag", string3);
        this.circleInfoMap.put("avatar", dataMap.getDataMap(d.k).getString("avatar"));
        this.circleInfoMap.put("updateTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Long.valueOf(System.currentTimeMillis())));
        this.circleInfoMap.put("createAccountId", SPUtil.Companion.getINSTANCE().getStringValue("coAccountId"));
        this.circleInfoMap.put("announcement", dataMap.getDataMap(d.k).getString("announcement"));
        this.circleInfoMap.put("id", this.circleId);
        this.role = dataMap.getDataMap(d.k).getString("role");
        this.sJoinAudit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                CircleSettingsActivity.this.circleInfoMap.put("auditTag", z ? "1" : K.k0);
                CircleSettingsActivity.this.updateCircle();
            }
        });
    }

    private void switchRole() {
        String stringExtra = getIntent().getStringExtra("role");
        if (!TextUtils.isEmpty(stringExtra)) {
            this.tvConfirm.setOnClickListener(this);
            this.rlCircleMember.setOnClickListener(this);
            this.rlIntroduce.setOnClickListener(this);
            this.rlNotice.setOnClickListener(this);
            if (stringExtra.equals("1")) {
                this.tvConfirm.setText("解散圈子");
            } else if (stringExtra.equals("2")) {
                this.sJoinAudit.setEnabled(false);
                this.tvConfirm.setText("退出圈子");
            } else if (stringExtra.equals("3")) {
                this.tvConfirm.setText("退出圈子");
                this.sJoinAudit.setEnabled(false);
            }
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.rl_circle_notice) {
            showMsgDialog("公告", this.circleInfoMap.get("announcement"), 1);
        } else if (id == R.id.rl_circle_introduce) {
            showMsgDialog("圈子介绍", this.circleInfoMap.get("description"), 2);
        } else if (id == R.id.rl_circle_member) {
            startActivity(new Intent(this, CircleMemberActivity.class).putExtra("circleId", this.circleId));
        } else if (id == R.id.tv_confirm) {
            showConfirmDialog();
        } else if (id == R.id.iv_back) {
            finish();
        }
    }

    private void showConfirmDialog() {
        final String str = "您是圈子" + this.tvName.getText().toString() + "的圈主，您可以选择解散圈子或者转让圈主退出圈子，解散后，圈子信息将全部清除不可恢复。";
        String str2 = "确定退出圈子" + this.tvName.getText().toString();
        if (!this.role.equals("1")) {
            str = str2;
        }
        new GlobalDialog(this, str).setButtonClick(new GlobalDialog.OnButtonClick() {
            public void onClick(int i) {
                if (i != 1) {
                    return;
                }
                if (str.startsWith("您")) {
                    CircleSettingsActivity.this.deleteCircle();
                } else {
                    CircleSettingsActivity.this.exitCircle();
                }
            }
        });
    }

    private void showMsgDialog(String str, String str2, final int i) {
        EditMsgDialog editMsgDialog = new EditMsgDialog(this);
        editMsgDialog.show();
        editMsgDialog.setEditAble(!"2".equals(this.role));
        editMsgDialog.setData(str, str2);
        editMsgDialog.setSendClickListener(new EditMsgDialog.SendClickListener() {
            public void sendMsg(String str) {
                if (i == 1) {
                    CircleSettingsActivity.this.circleInfoMap.put("announcement", str);
                } else if (i == 2) {
                    CircleSettingsActivity.this.circleInfoMap.put("description", str);
                }
                CircleSettingsActivity.this.updateCircle();
            }
        });
    }

    /* access modifiers changed from: private */
    public void updateCircle() {
        if (this.circleInfoMap.size() != 0) {
            ApiUtil.getApi2().updateCircle(this.circleInfoMap).compose(RxUtil.applyDataMapIO()).subscribe(new ResponseDataMap(this, true) {
                public void onSuccess(@NotNull DataMap dataMap) {
                    CircleSettingsActivity.this.getCircleDetailInfo();
                    CircleSettingsActivity.this.setResult(100);
                }

                public void onError(@NotNull DataMap dataMap) {
                    super.onError(dataMap);
                    ToastUtil.showMsg("修改失败");
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void exitCircle() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(SPUtil.Companion.getINSTANCE().getStringValue("coAccountId"));
        ApiUtil.getApi2().removeMember(this.circleId, new Gson().toJson(arrayList)).compose(RxUtil.applyDataMapIO()).subscribe(new ResponseDataMap(this, true) {
            public void onSuccess(@NotNull DataMap dataMap) {
                new GlobalDialog(CircleSettingsActivity.this, "退出成功").setCancelVisible(false).setButtonClick(new GlobalDialog.OnButtonClick() {
                    public void onClick(int i) {
                        CircleSettingsActivity.this.sendBroadcast(new Intent(CircleHomeActivity.CIRCLE_CHANGE_ACTION));
                        ActivityUtils.finishToActivity(CircleHomeActivity.class, false);
                    }
                });
            }

            public void onError(@NotNull DataMap dataMap) {
                super.onError(dataMap);
                ToastUtil.showMsg("操作失败");
            }
        });
    }

    /* access modifiers changed from: private */
    public void deleteCircle() {
        ApiUtil.getApi2().deleteCircle(this.circleId).compose(RxUtil.applyDataMapIO()).subscribe(new ResponseDataMap(this, true) {
            public void onSuccess(@NotNull DataMap dataMap) {
                new GlobalDialog(CircleSettingsActivity.this, "解散成功").setCancelVisible(false).setButtonClick(new GlobalDialog.OnButtonClick() {
                    public void onClick(int i) {
                        CircleSettingsActivity.this.sendBroadcast(new Intent(CircleHomeActivity.CIRCLE_CHANGE_ACTION));
                        ActivityUtils.finishToActivity(CircleHomeActivity.class, false);
                    }
                });
            }

            public void onError(@NotNull DataMap dataMap) {
                super.onError(dataMap);
                ToastUtil.showMsg("操作失败");
            }
        });
    }
}

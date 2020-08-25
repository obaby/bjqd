package cn.xports.qd2.circle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import cn.xports.baselib.bean.DataMap;
import cn.xports.baselib.http.ResponseDataMap;
import cn.xports.baselib.http.RxUtil;
import cn.xports.baselib.util.SPUtil;
import cn.xports.baselib.util.ToastUtil;
import cn.xports.qd2.R;
import cn.xports.qd2.circle.imgSelect.GlideLoader;
import cn.xports.qd2.circle.videoPlayer.MyBaseActivity;
import cn.xports.qd2.dialog.SelectCircleTypeDialog;
import cn.xports.qd2.entity.K;
import cn.xports.qd2.util.ApiUtil;
import cn.xports.qd2.util.EmojiExcludeFilter;
import cn.xports.qd2.util.LengthFilter;
import com.alipay.sdk.cons.c;
import com.alipay.sdk.packet.d;
import com.bumptech.glide.Glide;
import com.lcw.library.imagepicker.ImagePicker;
import com.stub.StubApp;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import org.jetbrains.annotations.NotNull;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

public class CreateCircleActivity extends MyBaseActivity implements View.OnClickListener {
    private static final int REQUEST_INTRODUCE_CODE = 3;
    private static final int REQUEST_SELECT_IMAGES_CODE = 2;
    /* access modifiers changed from: private */
    public String boardId;
    private Switch checkSwitch;
    /* access modifiers changed from: private */
    public List<CircleTypeBean> circleTypeBeanList = new ArrayList();
    private EditText etCircleName;
    private ImageView ivCircleHead;
    private ImageView ivTakePhoto;
    private ArrayList<String> mImagePaths;
    private TextView tvCircleIntroduce;
    /* access modifiers changed from: private */
    public TextView tvCircleType;
    /* access modifiers changed from: private */
    public TextView tvConfirmCreate;
    private TextView tvTakePhoto;

    static {
        StubApp.interface11(3788);
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    private void initView() {
        this.etCircleName = (EditText) findViewById(R.id.tv_circle_name);
        this.etCircleName.setFilters(new InputFilter[]{new EmojiExcludeFilter(), new LengthFilter(12)});
        this.tvConfirmCreate = (TextView) findViewById(R.id.tv_confirm_create);
        this.tvTakePhoto = (TextView) findViewById(R.id.tv_take_photo);
        this.ivTakePhoto = (ImageView) findViewById(R.id.iv_take_photo);
        this.ivCircleHead = (ImageView) findViewById(R.id.ic_circle_head);
        this.tvCircleType = (TextView) findViewById(R.id.tv_circle_type);
        this.tvCircleIntroduce = (TextView) findViewById(R.id.tv_circle_introduce);
        this.checkSwitch = (Switch) findViewById(R.id.switch_join_check);
        this.tvConfirmCreate.setOnClickListener(this);
        findViewById(R.id.iv_back).setOnClickListener(this);
        findViewById(R.id.cv_take_photo).setOnClickListener(this);
        findViewById(R.id.ll_select_circle_type).setOnClickListener(this);
        findViewById(R.id.ll_circle_introduce).setOnClickListener(this);
        this.etCircleName.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                CreateCircleActivity.this.checkInputState();
            }
        });
    }

    private void initData() {
        getAllBoards();
    }

    private void getAllBoards() {
        ApiUtil.getApi2().getAllBoards().compose(RxUtil.applyDataMapIO()).subscribe(new ResponseDataMap(this, true) {
            public void onSuccess(@NotNull DataMap dataMap) {
                ArrayList<DataMap> dataList = dataMap.getDataList("boards");
                if (dataList != null) {
                    CreateCircleActivity.this.circleTypeBeanList.clear();
                    for (int i = 0; i < dataList.size(); i++) {
                        CircleTypeBean circleTypeBean = new CircleTypeBean();
                        circleTypeBean.boardName = dataList.get(i).getString("boardName");
                        circleTypeBean.boardId = (long) dataList.get(i).getInteger("boardId").intValue();
                        CreateCircleActivity.this.circleTypeBeanList.add(circleTypeBean);
                    }
                }
            }

            public void onError(@NotNull DataMap dataMap) {
                super.onError(dataMap);
            }
        });
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.iv_back) {
            finish();
        } else if (id == R.id.cv_take_photo) {
            startTakePhoto();
        } else if (id == R.id.ll_select_circle_type) {
            showSelectTypeDialog();
        } else if (id == R.id.ll_circle_introduce) {
            startActivityForResult(new Intent(this, CircleIntroduceEditActivity.class).putExtra("content", this.tvCircleIntroduce.getText()), 3);
        } else if (id == R.id.tv_confirm_create) {
            this.tvConfirmCreate.setClickable(false);
            createCircleRequest();
        }
    }

    private void showSelectTypeDialog() {
        SelectCircleTypeDialog selectCircleTypeDialog = new SelectCircleTypeDialog(this);
        selectCircleTypeDialog.show();
        selectCircleTypeDialog.setData(this.circleTypeBeanList);
        selectCircleTypeDialog.setItemClickListener(new SelectCircleTypeDialog.ClickListener() {
            public void clickListener(int i) {
                CreateCircleActivity.this.tvCircleType.setVisibility(0);
                CreateCircleActivity.this.tvCircleType.setText(((CircleTypeBean) CreateCircleActivity.this.circleTypeBeanList.get(i)).boardName);
                String unused = CreateCircleActivity.this.boardId = String.valueOf(((CircleTypeBean) CreateCircleActivity.this.circleTypeBeanList.get(i)).boardId);
                for (int i2 = 0; i2 < CreateCircleActivity.this.circleTypeBeanList.size(); i2++) {
                    if (i2 == i) {
                        ((CircleTypeBean) CreateCircleActivity.this.circleTypeBeanList.get(i2)).isSelected = true;
                    } else {
                        ((CircleTypeBean) CreateCircleActivity.this.circleTypeBeanList.get(i2)).isSelected = false;
                    }
                }
                CreateCircleActivity.this.checkInputState();
            }
        });
    }

    private void startTakePhoto() {
        ImagePicker.getInstance().setTitle("标题").showCamera(true).showImage(true).showVideo(false).filterGif(true).setMaxCount(1).setSingleType(true).setImagePaths((ArrayList) null).setImageLoader(new GlideLoader()).start(this, 2);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 2 && i2 == -1) {
            this.mImagePaths = intent.getStringArrayListExtra("selectItems");
            Glide.with((FragmentActivity) this).load(this.mImagePaths.get(0)).centerCrop().into(this.ivCircleHead);
            this.tvTakePhoto.setVisibility(4);
            this.ivTakePhoto.setVisibility(0);
            checkInputState();
        } else if (3 == i && i2 == 99) {
            String stringExtra = intent.getStringExtra("content");
            if (!TextUtils.isEmpty(stringExtra)) {
                this.tvCircleIntroduce.setText(stringExtra);
            }
            checkInputState();
        }
    }

    public void checkInputState() {
        if (this.mImagePaths == null || this.mImagePaths.size() == 0) {
            this.tvConfirmCreate.setBackground(getResources().getDrawable(R.drawable.shape_raduis22_gray));
        } else if (TextUtils.isEmpty(this.etCircleName.getText().toString().trim())) {
            this.tvConfirmCreate.setBackground(getResources().getDrawable(R.drawable.shape_raduis22_gray));
        } else if (TextUtils.isEmpty(this.tvCircleType.getText().toString().trim())) {
            this.tvConfirmCreate.setBackground(getResources().getDrawable(R.drawable.shape_raduis22_gray));
        } else {
            this.tvCircleIntroduce.getText().toString().trim();
            this.tvConfirmCreate.setBackground(getResources().getDrawable(R.drawable.shape_raduis22_blue));
        }
    }

    private void createCircleRequest() {
        if (this.mImagePaths == null || this.mImagePaths.size() == 0) {
            ToastUtil.showMsg("请添加头像");
            this.tvConfirmCreate.setClickable(true);
            return;
        }
        final String trim = this.etCircleName.getText().toString().trim();
        if (TextUtils.isEmpty(trim)) {
            ToastUtil.showMsg("请输入圈子名称");
            this.tvConfirmCreate.setClickable(true);
        } else if (trim.length() < 2) {
            ToastUtil.showMsg("圈子名称必须超过两个字符");
            this.tvConfirmCreate.setClickable(true);
        } else {
            final String trim2 = this.tvCircleType.getText().toString().trim();
            if (TextUtils.isEmpty(trim2)) {
                ToastUtil.showMsg("请选择圈子类型");
                this.tvConfirmCreate.setClickable(true);
                return;
            }
            final String trim3 = this.tvCircleIntroduce.getText().toString().trim();
            if (TextUtils.isEmpty(trim3)) {
                trim3 = "";
            }
            this.tvConfirmCreate.setClickable(false);
            Luban.with(this).load(this.mImagePaths.get(0)).ignoreBy(100).setCompressListener(new OnCompressListener() {
                public void onStart() {
                }

                public void onSuccess(File file) {
                    CreateCircleActivity.this.uploadImg(file, trim, trim2, trim3);
                }

                public void onError(Throwable th) {
                    if (CreateCircleActivity.this.tvConfirmCreate != null) {
                        CreateCircleActivity.this.tvConfirmCreate.setClickable(true);
                    }
                }
            }).launch();
        }
    }

    /* access modifiers changed from: private */
    public void uploadImg(File file, String str, String str2, String str3) {
        final String str4 = str;
        final String str5 = str3;
        ApiUtil.getApi2().uploadPostsFile(MultipartBody.Part.createFormData("file", file.getName(), RequestBody.create(MediaType.parse("image/jpg"), file))).compose(RxUtil.applyDataMapIO()).subscribe(new ResponseDataMap(this, true) {
            public void onSuccess(@NotNull DataMap dataMap) {
                Log.e(">>>>> 上传：onSuccess ", dataMap.toJson());
                CreateCircleActivity.this.uploadMsg(dataMap.getString("url"), str4, str5);
            }

            public void onError(@NotNull DataMap dataMap) {
                super.onError(dataMap);
                Log.e(">>>>> 上传：onError ", dataMap.toJson());
                if (CreateCircleActivity.this.tvConfirmCreate != null) {
                    CreateCircleActivity.this.tvConfirmCreate.setClickable(true);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void uploadMsg(String str, String str2, String str3) {
        String str4 = this.checkSwitch.isChecked() ? "1" : K.k0;
        HashMap hashMap = new HashMap();
        hashMap.put(c.e, str2);
        hashMap.put("boardId", this.boardId);
        hashMap.put("description", str3);
        hashMap.put("auditTag", str4);
        hashMap.put("avatar", str);
        hashMap.put("updateTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Long.valueOf(System.currentTimeMillis())));
        hashMap.put("createAccountId", SPUtil.Companion.getINSTANCE().getStringValue("coAccountId"));
        ApiUtil.getApi2().createCircle(hashMap).compose(RxUtil.applyDataMapIO()).subscribe(new ResponseDataMap(this, true) {
            public void onSuccess(@NotNull DataMap dataMap) {
                Log.e(">>>>> 创建：onSuccess ", dataMap.toJson());
                CreateCircleActivity.this.refreshUi(dataMap);
                if (CreateCircleActivity.this.tvConfirmCreate != null) {
                    CreateCircleActivity.this.tvConfirmCreate.setClickable(true);
                }
            }

            public void onError(@NotNull DataMap dataMap) {
                super.onError(dataMap);
                Log.e(">>>>> 创建失败：onError ", dataMap.toJson());
                ToastUtil.showMsg("发布失败");
                if (CreateCircleActivity.this.tvConfirmCreate != null) {
                    CreateCircleActivity.this.tvConfirmCreate.setClickable(true);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void refreshUi(DataMap dataMap) {
        startCircleDetailActivity(dataMap.getString(d.k));
        finish();
    }

    private void startCircleDetailActivity(String str) {
        Intent intent = new Intent(this, CircleDetailActivity.class);
        intent.putExtra(CircleDetailActivity.CIRCLE_ID, str);
        startActivity(intent);
    }
}

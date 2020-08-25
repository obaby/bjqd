package cn.xports.qd2.circle;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import cn.xports.baselib.bean.DataMap;
import cn.xports.baselib.http.ResponseDataMap;
import cn.xports.baselib.http.RxUtil;
import cn.xports.baselib.util.ToastUtil;
import cn.xports.qd2.R;
import cn.xports.qd2.circle.SelectCircleDialog;
import cn.xports.qd2.circle.imgSelect.GlideLoader;
import cn.xports.qd2.circle.imgSelect.GridImageAdapter;
import cn.xports.qd2.circle.imgSelect.LocalMedia;
import cn.xports.qd2.circle.videoPlayer.MyBaseActivity;
import cn.xports.qd2.dialog.MyToastUtils;
import cn.xports.qd2.entity.K;
import cn.xports.qd2.util.ApiUtil;
import cn.xports.qd2.util.ViewExt;
import com.google.gson.Gson;
import com.lcw.library.imagepicker.ImagePicker;
import com.stub.StubApp;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import org.jetbrains.annotations.NotNull;
import top.zibin.luban.Luban;

public class IssuePostsActivity extends MyBaseActivity implements View.OnClickListener {
    private static final int REQUEST_SELECT_IMAGES_CODE = 1;
    /* access modifiers changed from: private */
    public String circleId;
    /* access modifiers changed from: private */
    public String circleName;
    private EditText etContent;
    private GridImageAdapter gridImageAdapter;
    private boolean isVideo;
    /* access modifiers changed from: private */
    public ImageView ivIssuePosts;
    /* access modifiers changed from: private */
    public ArrayList<String> mImagePaths;
    private List<MediaBean> mediaBeanList = new ArrayList();
    private List<LocalMedia> mediaList = new ArrayList();
    private String mediaType;
    private GridImageAdapter.onAddPicClickListener myItemClickListener = new GridImageAdapter.onAddPicClickListener() {
        public void onAddPicClick() {
            ImagePicker.getInstance().setTitle("标题").showCamera(false).showImage(true).showVideo(true).filterGif(false).setMaxCount(9).setSingleType(true).setImagePaths(IssuePostsActivity.this.mImagePaths).setImageLoader(new GlideLoader()).start(IssuePostsActivity.this, 1);
        }

        public void onDeleteClick(int i) {
            IssuePostsActivity.this.mImagePaths.remove(i);
            IssuePostsActivity.this.setIssueButtonState();
        }
    };
    private RecyclerView rvImgList;
    /* access modifiers changed from: private */
    public TextView tvSelectCircle;

    static {
        StubApp.interface11(3822);
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    private void initView() {
        this.ivIssuePosts = (ImageView) findViewById(R.id.iv_issue_posts);
        this.tvSelectCircle = (TextView) findViewById(R.id.tv_select_circle);
        this.etContent = (EditText) findViewById(R.id.et_content);
        ViewExt.filterEmoji(this.etContent);
        ViewExt.filterBlank(this.etContent);
        ViewExt.setMaxLength(this.etContent, 200, "最多输入200字");
        this.rvImgList = (RecyclerView) findViewById(R.id.rv_add_img);
        ((ImageView) findViewById(R.id.iv_close)).setOnClickListener(this);
        this.ivIssuePosts.setOnClickListener(this);
        if (TextUtils.isEmpty(this.circleId) || TextUtils.isEmpty(this.circleName)) {
            this.tvSelectCircle.setOnClickListener(this);
        } else {
            this.tvSelectCircle.setText(this.circleName);
        }
        this.etContent.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                IssuePostsActivity.this.setIssueButtonState();
            }
        });
    }

    private void initData() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        this.gridImageAdapter = new GridImageAdapter(this, this.myItemClickListener);
        this.rvImgList.setLayoutManager(gridLayoutManager);
        this.rvImgList.setAdapter(this.gridImageAdapter);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.iv_close) {
            finish();
        } else if (view.getId() == R.id.iv_issue_posts) {
            checkInputParameter();
        } else if (view.getId() == R.id.tv_select_circle) {
            showSelectDialog();
        }
    }

    private void checkInputParameter() {
        if (TextUtils.isEmpty(this.etContent.getText().toString().trim())) {
            ToastUtil.showMsg("请输入帖子内容");
        } else if (this.mImagePaths == null || this.mImagePaths.size() == 0) {
            ToastUtil.showMsg("请选择图片或者视频");
        } else if (TextUtils.isEmpty(this.circleName)) {
            ToastUtil.showMsg("请选择圈子");
        } else {
            issuePosts();
            this.ivIssuePosts.setEnabled(false);
        }
    }

    private void showSelectDialog() {
        SelectCircleDialog selectCircleDialog = new SelectCircleDialog(this);
        selectCircleDialog.show();
        Display defaultDisplay = getWindowManager().getDefaultDisplay();
        WindowManager.LayoutParams attributes = selectCircleDialog.getWindow().getAttributes();
        attributes.height = defaultDisplay.getHeight();
        selectCircleDialog.getWindow().setAttributes(attributes);
        selectCircleDialog.setSelectListener(new SelectCircleDialog.SelectListener() {
            public void onSelect(String str, String str2) {
                IssuePostsActivity.this.tvSelectCircle.setText(str2);
                String unused = IssuePostsActivity.this.circleName = str2;
                String unused2 = IssuePostsActivity.this.circleId = str;
                IssuePostsActivity.this.setIssueButtonState();
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 1 && i2 == -1) {
            this.mImagePaths = intent.getStringArrayListExtra("selectItems");
            this.mediaList.clear();
            for (int i3 = 0; i3 < this.mImagePaths.size(); i3++) {
                LocalMedia localMedia = new LocalMedia();
                localMedia.setPath(this.mImagePaths.get(i3));
                localMedia.setChecked(false);
                this.mediaList.add(localMedia);
            }
            this.isVideo = intent.getBooleanExtra("isVideo", false);
            this.mediaType = this.isVideo ? "2" : "1";
            if (this.isVideo) {
                this.gridImageAdapter.setSelectMax(1);
            } else {
                this.gridImageAdapter.setSelectMax(9);
            }
            this.gridImageAdapter.setList(this.mediaList);
            this.gridImageAdapter.notifyDataSetChanged();
            setIssueButtonState();
        }
    }

    /* access modifiers changed from: private */
    public void setIssueButtonState() {
        if (TextUtils.isEmpty(this.etContent.getText().toString().trim())) {
            this.ivIssuePosts.setImageDrawable(getResources().getDrawable(R.drawable.ic_send_gray));
        } else if (this.mImagePaths == null || this.mImagePaths.size() == 0) {
            this.ivIssuePosts.setImageDrawable(getResources().getDrawable(R.drawable.ic_send_gray));
        } else if (TextUtils.isEmpty(this.circleName)) {
            this.ivIssuePosts.setImageDrawable(getResources().getDrawable(R.drawable.ic_send_gray));
        } else {
            this.ivIssuePosts.setImageDrawable(getResources().getDrawable(R.drawable.ic_send_blue));
        }
    }

    private void issuePosts() {
        if (this.isVideo) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new File(this.mImagePaths.get(0)));
            uploadCompressImg(arrayList);
            return;
        }
        Flowable.just(this.mImagePaths).observeOn(Schedulers.io()).map(new Function<List<String>, List<File>>() {
            public List<File> apply(@NonNull List<String> list) throws Exception {
                return Luban.with(IssuePostsActivity.this).ignoreBy(100).load(list).get();
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<File>>() {
            public void accept(List<File> list) throws Exception {
                IssuePostsActivity.this.uploadCompressImg(list);
            }
        });
    }

    /* access modifiers changed from: private */
    public void uploadCompressImg(List<File> list) {
        this.mediaBeanList.clear();
        Observable.zip(Observable.fromIterable(list), Observable.interval(300, TimeUnit.MILLISECONDS), new BiFunction<File, Long, File>() {
            public File apply(File file, Long l) throws Exception {
                return file;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).doOnNext(new Consumer<File>() {
            public void accept(File file) throws Exception {
                IssuePostsActivity.this.httpRequest(file);
            }
        }).subscribe();
    }

    /* access modifiers changed from: private */
    public void httpRequest(File file) {
        RequestBody requestBody;
        if (this.isVideo) {
            requestBody = RequestBody.create(MediaType.parse("video/mp4"), file);
        } else {
            requestBody = RequestBody.create(MediaType.parse("image/jpg"), file);
        }
        ApiUtil.getApi2().uploadPostsFile(MultipartBody.Part.createFormData("file", file.getName(), requestBody)).compose(RxUtil.applyDataMapIO()).subscribe(new ResponseDataMap(this, true) {
            public void onSuccess(@NotNull DataMap dataMap) {
                Log.e(">>>>> 上传：onSuccess ", dataMap.toJson());
                IssuePostsActivity.this.refreshUi(dataMap);
            }

            public void onError(@NotNull DataMap dataMap) {
                super.onError(dataMap);
                Log.e(">>>>> 上传：onError ", dataMap.toJson());
                ToastUtil.showMsg("发布失败");
                if (IssuePostsActivity.this.ivIssuePosts != null) {
                    IssuePostsActivity.this.ivIssuePosts.setEnabled(true);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void refreshUi(DataMap dataMap) {
        if (dataMap.getInteger(K.error).intValue() != 0) {
            ToastUtil.showMsg("上传失败");
            if (this.ivIssuePosts != null) {
                this.ivIssuePosts.setEnabled(true);
                return;
            }
            return;
        }
        String string = dataMap.getString("url");
        MediaBean mediaBean = new MediaBean();
        mediaBean.mediaUrl = string;
        mediaBean.mediaType = this.mediaType;
        mediaBean.sort = String.valueOf(this.mediaBeanList.size() + 1);
        mediaBean.thumbUrl = dataMap.getString("thumbUrl");
        this.mediaBeanList.add(mediaBean);
        if (this.mediaBeanList.size() == this.mImagePaths.size()) {
            sendAllMsg();
        }
    }

    private void sendAllMsg() {
        ApiUtil.getApi2().issuePosts(this.mediaType, this.etContent.getText().toString().trim(), new Gson().toJson(this.mediaBeanList), "1", "4", this.circleId, this.isVideo ? this.mediaBeanList.get(0).thumbUrl : this.mediaBeanList.get(0).mediaUrl).compose(RxUtil.applyDataMapIO()).subscribe(new ResponseDataMap(this, true) {
            public void onSuccess(@NotNull DataMap dataMap) {
                if (IssuePostsActivity.this.ivIssuePosts != null) {
                    IssuePostsActivity.this.ivIssuePosts.setEnabled(true);
                }
                Log.e(">>>>> 发布：onSuccess ", dataMap.toJson());
                new MyToastUtils().ToastShow(IssuePostsActivity.this, "发布成功", R.drawable.ic_right_white);
                IssuePostsActivity.this.setResult(100);
                IssuePostsActivity.this.finish();
            }

            public void onError(@NotNull DataMap dataMap) {
                super.onError(dataMap);
                if (IssuePostsActivity.this.ivIssuePosts != null) {
                    IssuePostsActivity.this.ivIssuePosts.setEnabled(true);
                }
                Log.e(">>>>> 发布：onError ", dataMap.toJson());
                ToastUtil.showMsg("发布失败");
            }
        });
    }
}

package cn.xports.qd2.circle;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.xports.baselib.adapter.XBaseAdapter;
import cn.xports.baselib.adapter.XBaseHolder;
import cn.xports.baselib.bean.DataMap;
import cn.xports.baselib.util.GlideUtil;
import cn.xports.qd2.R;
import cn.xports.qd2.circle.videoPlayer.PicUrlUtils;
import cn.xports.qd2.circle.videoPlayer.SurfaceActivity;
import cn.xports.qd2.circle.videoPlayer.entity.VideoInfo;
import cn.xports.qd2.entity.K;
import cn.xports.sportCoaching.DateShowUtils;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ColorUtils;
import com.bumptech.glide.Glide;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CircleHomePostsAdapter extends XBaseAdapter<DataMap> {
    /* access modifiers changed from: private */
    public Context context;
    /* access modifiers changed from: private */
    public HashMap<String, DataMap> imgMap = new HashMap<>();
    private boolean isInCircle = false;
    /* access modifiers changed from: private */
    public boolean isInOtherCenter = false;

    public long getItemId(int i) {
        return (long) i;
    }

    public CircleHomePostsAdapter(List<DataMap> list, Context context2) {
        super(list);
        this.context = context2;
    }

    public CircleHomePostsAdapter setInCircle(boolean z) {
        this.isInCircle = z;
        return this;
    }

    public CircleHomePostsAdapter setInOtherCenter(boolean z) {
        this.isInOtherCenter = z;
        return this;
    }

    public int getLayoutId() {
        return R.layout.item_circle_home;
    }

    public void onBindViewHolder(@NonNull XBaseHolder xBaseHolder, int i) {
        LinearLayoutManager linearLayoutManager;
        XBaseHolder xBaseHolder2 = xBaseHolder;
        int i2 = i;
        xBaseHolder2.setVisible(R.id.v_line, i2 != 0);
        final DataMap dataMap = (DataMap) this.list.get(i2);
        TextView textView = (TextView) xBaseHolder2.getView(R.id.tv_name);
        TextView textView2 = (TextView) xBaseHolder2.getView(R.id.tv_issue_time);
        TextView textView3 = (TextView) xBaseHolder2.getView(R.id.tv_circle_name);
        TextView textView4 = (TextView) xBaseHolder2.getView(R.id.tv_circle_msg);
        TextView textView5 = (TextView) xBaseHolder2.getView(R.id.tv_circle_care);
        final ExpandableTextView expandableTextView = (ExpandableTextView) xBaseHolder2.getView(R.id.tv_circle_content);
        ImageView imageView = (ImageView) xBaseHolder2.getView(R.id.iv_head_ic);
        final ImageView imageView2 = (ImageView) xBaseHolder2.getView(R.id.riv_content);
        ImageView imageView3 = (ImageView) xBaseHolder2.getView(R.id.iv_player_state);
        RecyclerView recyclerView = (RecyclerView) xBaseHolder2.getView(R.id.rv_circle_content);
        xBaseHolder2.setVisible(R.id.ivCircleTag, "1".equals(dataMap.getString("circleRole")));
        String string = dataMap.getString("accountName");
        if (!this.isInCircle && string.length() > 7) {
            string = string.substring(0, 7) + "...";
        }
        textView.setText(string);
        textView2.setText(DateShowUtils.getPostFriendlyTime(dataMap.getString(K.createTime)));
        textView3.setText(dataMap.getString("circleName"));
        expandableTextView.initWidth(((Activity) this.context).getWindowManager().getDefaultDisplay().getWidth() - dp2px(this.context, 30.0f));
        expandableTextView.setMaxLines(2);
        expandableTextView.setMargerdp(30);
        expandableTextView.setHasAnimation(false);
        expandableTextView.setCloseInNewLine(false);
        expandableTextView.setOpenSuffixColor(this.context.getResources().getColor(R.color.blue_2e6));
        expandableTextView.setCloseSuffixColor(this.context.getResources().getColor(R.color.blue_2e6));
        expandableTextView.setOriginalText(dataMap.getString("content"));
        String string2 = dataMap.getString("commentNum");
        if (!TextUtils.isEmpty(string2) && string2.equals(K.k0)) {
            string2 = "";
        }
        textView4.setText(string2);
        ArrayList<DataMap> dataList = dataMap.getDataList("likeList");
        if (dataList == null || dataList.size() == 0) {
            dataMap.set("hasLike", false);
            textView5.setCompoundDrawablesWithIntrinsicBounds(this.context.getResources().getDrawable(R.drawable.ic_care_black), (Drawable) null, (Drawable) null, (Drawable) null);
            textView5.setTextColor(ColorUtils.getColor(R.color.gray_333));
        } else {
            dataMap.set("hasLike", true);
            textView5.setCompoundDrawablesWithIntrinsicBounds(this.context.getResources().getDrawable(R.drawable.ic_care_red), (Drawable) null, (Drawable) null, (Drawable) null);
            textView5.setTextColor(ColorUtils.getColor(R.color.red_fd4));
        }
        String string3 = dataMap.getString("likeNum");
        if (!TextUtils.isEmpty(string3) && string3.equals(K.k0)) {
            string3 = "";
        }
        textView5.setText(string3);
        GlideUtil.loadRoundImage(this.context, dataMap.getString("accountAvatar"), R.drawable.ic_circle_default_head).into(imageView);
        final ArrayList<DataMap> dataList2 = dataMap.getDataList("media");
        if (dataList2 != null && dataList2.size() > 0) {
            if (dataList2.get(0).getString("mediaType").equals("1")) {
                imageView3.setVisibility(8);
                recyclerView.setVisibility(0);
                imageView2.setVisibility(8);
                if (dataList2.size() == 1) {
                    linearLayoutManager = new LinearLayoutManager(this.context);
                } else {
                    linearLayoutManager = new GridLayoutManager(this.context, 3);
                }
                PostsImgAdapter postsImgAdapter = new PostsImgAdapter(dataMap.getDataList("media"), this.context, linearLayoutManager, this.imgMap);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(postsImgAdapter);
            } else {
                imageView3.setVisibility(0);
                recyclerView.setVisibility(8);
                imageView2.setVisibility(0);
                DataMap dataMap2 = this.imgMap.get(dataMap.getString("thumbMediaUrl"));
                if (dataMap2 != null) {
                    imageView2.setLayoutParams(new RelativeLayout.LayoutParams(dataMap2.getIntValue("width").intValue(), dataMap2.getIntValue("height").intValue()));
                    Glide.with(this.context).load(PicUrlUtils.getPath(dataMap.getString("thumbMediaUrl"))).centerCrop().error(R.drawable.ic_circle_default).into(imageView2);
                } else {
                    final String string4 = dataMap.getString("thumbMediaUrl");
                    Observable.create(new ObservableOnSubscribe<Bitmap>() {
                        public void subscribe(@NonNull ObservableEmitter<Bitmap> observableEmitter) throws Exception {
                            observableEmitter.onNext((Bitmap) Glide.with(CircleHomePostsAdapter.this.context).asBitmap().load(PicUrlUtils.getPath(dataMap.getString("thumbMediaUrl"))).submit().get());
                        }
                    }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Observer<Bitmap>() {
                        public void onComplete() {
                        }

                        public void onSubscribe(Disposable disposable) {
                        }

                        public void onNext(Bitmap bitmap) {
                            float width = (((float) bitmap.getWidth()) * 1.0f) / ((float) bitmap.getHeight());
                            DataMap dataMap = new DataMap();
                            if (width > 1.0f) {
                                imageView2.setLayoutParams(new RelativeLayout.LayoutParams(ScreenDistUtils.dp2px(CircleHomePostsAdapter.this.context, 160.0f / width), ScreenDistUtils.dp2px(CircleHomePostsAdapter.this.context, 160.0f)));
                                dataMap.set("width", Integer.valueOf(ScreenDistUtils.dp2px(CircleHomePostsAdapter.this.context, width * 160.0f)));
                                dataMap.set("height", Integer.valueOf(ScreenDistUtils.dp2px(CircleHomePostsAdapter.this.context, 160.0f)));
                            } else {
                                float f = 160.0f / width;
                                imageView2.setLayoutParams(new RelativeLayout.LayoutParams(ScreenDistUtils.dp2px(CircleHomePostsAdapter.this.context, 160.0f), ScreenDistUtils.dp2px(CircleHomePostsAdapter.this.context, f)));
                                dataMap.set("height", Integer.valueOf(ScreenDistUtils.dp2px(CircleHomePostsAdapter.this.context, f)));
                                dataMap.set("width", Integer.valueOf(ScreenDistUtils.dp2px(CircleHomePostsAdapter.this.context, 160.0f)));
                            }
                            CircleHomePostsAdapter.this.imgMap.put(string4, dataMap);
                            imageView2.setLayoutParams(new RelativeLayout.LayoutParams(dataMap.getIntValue("width").intValue(), dataMap.getIntValue("height").intValue()));
                            Glide.with(CircleHomePostsAdapter.this.context).load(PicUrlUtils.getPath(dataMap.getString("thumbMediaUrl"))).centerCrop().error(R.drawable.ic_circle_default).into(imageView2);
                        }

                        public void onError(Throwable th) {
                            Glide.with(CircleHomePostsAdapter.this.context).load(PicUrlUtils.getPath(dataMap.getString("thumbMediaUrl"))).error(R.drawable.ic_circle_default).into(imageView2);
                            Log.e(">>>", "图片转换出错" + th.getMessage());
                        }
                    });
                }
                imageView2.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        VideoInfo videoInfo = new VideoInfo();
                        videoInfo.setMimeType("1");
                        videoInfo.setFilePath(((DataMap) dataList2.get(0)).getString("mediaUrl"));
                        Intent intent = new Intent(CircleHomePostsAdapter.this.context, SurfaceActivity.class);
                        intent.putExtra("VIDEO_INFO", videoInfo);
                        intent.putExtra("VIDEO_TYPE", 1);
                        intent.setFlags(268435456);
                        CircleHomePostsAdapter.this.context.startActivity(intent);
                    }
                });
            }
        }
        final int i3 = i;
        XBaseHolder xBaseHolder3 = xBaseHolder;
        xBaseHolder3.setOnClickListener(R.id.tv_circle_care, new View.OnClickListener() {
            public void onClick(View view) {
                CircleHomePostsAdapter.this.performItemClick(dataMap, i3, R.id.tv_circle_care);
            }
        });
        xBaseHolder3.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CircleHomePostsAdapter.this.performItemClick(dataMap, i3);
            }
        });
        xBaseHolder3.setOnClickListener(R.id.tv_circle_content, new View.OnClickListener() {
            public void onClick(View view) {
                if (expandableTextView.isHasOpened()) {
                    expandableTextView.setHasOpened(false);
                } else {
                    CircleHomePostsAdapter.this.performItemClick(dataMap, i3);
                }
            }
        });
        xBaseHolder3.setOnClickListener(R.id.cr_head, new View.OnClickListener() {
            public void onClick(View view) {
                if (!CircleHomePostsAdapter.this.isInOtherCenter) {
                    ActivityUtils.startActivity(new Intent(ActivityUtils.getTopActivity(), CircleMyCenterActivity.class).putExtra("circleName", dataMap.getString("circleName")).putExtra("circleId", dataMap.getString("circleId")).putExtra("accountId", dataMap.getString("accountId")));
                }
            }
        });
        xBaseHolder3.setVisible(R.id.ll_circle_name, !this.isInCircle);
        xBaseHolder3.setOnClickListener(R.id.ll_circle_name, new View.OnClickListener() {
            public void onClick(View view) {
                ActivityUtils.startActivity(new Intent(ActivityUtils.getTopActivity(), CircleDetailActivity.class).putExtra(CircleDetailActivity.CIRCLE_ID, dataMap.getString("circleId")));
            }
        });
    }

    public int dp2px(Context context2, float f) {
        float f2 = context2.getResources().getDisplayMetrics().density;
        return f < 0.0f ? -((int) (((-f) * f2) + 0.5f)) : (int) ((f * f2) + 0.5f);
    }
}

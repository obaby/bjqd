package cn.xports.qd2.circle;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import cn.xports.baselib.adapter.XBaseAdapter;
import cn.xports.baselib.adapter.XBaseHolder;
import cn.xports.baselib.bean.DataMap;
import cn.xports.qd2.R;
import cn.xports.qd2.circle.videoPlayer.PicUrlUtils;
import com.bumptech.glide.Glide;
import com.previewlibrary.GPreviewBuilder;
import com.previewlibrary.enitity.ThumbViewInfo;
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

public class PostsImgAdapter extends XBaseAdapter<DataMap> {
    /* access modifiers changed from: private */
    public Context context;
    /* access modifiers changed from: private */
    public HashMap<String, DataMap> imgMap = new HashMap<>();
    /* access modifiers changed from: private */
    public ThumbViewInfo item;
    /* access modifiers changed from: private */
    public LinearLayoutManager mGridLayoutManager;
    /* access modifiers changed from: private */
    public ArrayList<ThumbViewInfo> mThumbViewInfoList = new ArrayList<>();

    public PostsImgAdapter(List<DataMap> list, Context context2, LinearLayoutManager linearLayoutManager) {
        super(list);
        this.context = context2;
        this.list = list;
        this.mGridLayoutManager = linearLayoutManager;
    }

    public PostsImgAdapter(List<DataMap> list, Context context2, LinearLayoutManager linearLayoutManager, HashMap<String, DataMap> hashMap) {
        super(list);
        this.context = context2;
        this.list = list;
        this.mGridLayoutManager = linearLayoutManager;
        this.imgMap = hashMap;
    }

    public int getLayoutId() {
        return R.layout.item_post_img;
    }

    public void onBindViewHolder(@NonNull XBaseHolder xBaseHolder, final int i) {
        final DataMap dataMap = (DataMap) this.list.get(i);
        ImageView imageView = (ImageView) xBaseHolder.getView(R.id.iv_posts_img);
        final ImageView imageView2 = (ImageView) xBaseHolder.getView(R.id.iv_posts_img1);
        if (this.list.size() == 1) {
            DataMap dataMap2 = this.imgMap.get(dataMap.getString("mediaUrl"));
            if (dataMap2 != null) {
                imageView2.setLayoutParams(new RelativeLayout.LayoutParams(dataMap2.getIntValue("width").intValue(), dataMap2.getIntValue("height").intValue()));
                Glide.with(this.context).load(PicUrlUtils.getPath(dataMap.getString("mediaUrl"))).centerCrop().error(R.drawable.bg_default).into(imageView2);
            } else {
                final String string = dataMap.getString("mediaUrl");
                Observable.create(new ObservableOnSubscribe<Bitmap>() {
                    public void subscribe(@NonNull ObservableEmitter<Bitmap> observableEmitter) throws Exception {
                        observableEmitter.onNext((Bitmap) Glide.with(PostsImgAdapter.this.context).asBitmap().load(PicUrlUtils.getPath(dataMap.getString("mediaUrl"))).submit().get());
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
                            imageView2.setLayoutParams(new RelativeLayout.LayoutParams(ScreenDistUtils.dp2px(PostsImgAdapter.this.context, 160.0f / width), ScreenDistUtils.dp2px(PostsImgAdapter.this.context, 160.0f)));
                            dataMap.set("width", Integer.valueOf(ScreenDistUtils.dp2px(PostsImgAdapter.this.context, width * 160.0f)));
                            dataMap.set("height", Integer.valueOf(ScreenDistUtils.dp2px(PostsImgAdapter.this.context, 160.0f)));
                        } else {
                            float f = 160.0f / width;
                            imageView2.setLayoutParams(new RelativeLayout.LayoutParams(ScreenDistUtils.dp2px(PostsImgAdapter.this.context, 160.0f), ScreenDistUtils.dp2px(PostsImgAdapter.this.context, f)));
                            dataMap.set("height", Integer.valueOf(ScreenDistUtils.dp2px(PostsImgAdapter.this.context, f)));
                            dataMap.set("width", Integer.valueOf(ScreenDistUtils.dp2px(PostsImgAdapter.this.context, 160.0f)));
                        }
                        PostsImgAdapter.this.imgMap.put(string, dataMap);
                        imageView2.setLayoutParams(new RelativeLayout.LayoutParams(dataMap.getIntValue("width").intValue(), dataMap.getIntValue("height").intValue()));
                        Glide.with(PostsImgAdapter.this.context).load(PicUrlUtils.getPath(dataMap.getString("mediaUrl"))).centerCrop().error(R.drawable.bg_default).into(imageView2);
                        imageView2.post(new Runnable() {
                            public void run() {
                            }
                        });
                    }

                    public void onError(Throwable th) {
                        Glide.with(PostsImgAdapter.this.context).load(PicUrlUtils.getPath(dataMap.getString("mediaUrl"))).error(R.drawable.bg_default).into(imageView2);
                        Log.e(">>>", "图片转换出错" + th.getMessage());
                    }
                });
            }
        } else {
            Glide.with(this.context).load(PicUrlUtils.getPath(dataMap.getString("mediaUrl"))).centerCrop().error(R.drawable.bg_default).into(imageView);
            imageView2 = imageView;
        }
        imageView2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                PostsImgAdapter.this.mThumbViewInfoList.clear();
                for (int i = 0; i < PostsImgAdapter.this.list.size(); i++) {
                    Rect rect = new Rect();
                    ThumbViewInfo unused = PostsImgAdapter.this.item = new ThumbViewInfo(((DataMap) PostsImgAdapter.this.list.get(i)).getString("mediaUrl"));
                    PostsImgAdapter.this.item.setBounds(rect);
                    PostsImgAdapter.this.mThumbViewInfoList.add(PostsImgAdapter.this.item);
                }
                PostsImgAdapter.this.computeBoundsBackward(PostsImgAdapter.this.mGridLayoutManager.findFirstVisibleItemPosition());
                GPreviewBuilder.from((Activity) PostsImgAdapter.this.context).to(ImageLookActivity.class).setData(PostsImgAdapter.this.mThumbViewInfoList).setCurrentIndex(i).setSingleFling(true).setType(GPreviewBuilder.IndicatorType.Number).start();
            }
        });
    }

    /* access modifiers changed from: private */
    public void computeBoundsBackward(int i) {
        while (i < this.mThumbViewInfoList.size()) {
            View findViewByPosition = this.mGridLayoutManager.findViewByPosition(i);
            Rect rect = new Rect();
            if (findViewByPosition != null) {
                ((ImageView) findViewByPosition.findViewById(R.id.iv_posts_img)).getGlobalVisibleRect(rect);
            }
            this.mThumbViewInfoList.get(i).setBounds(rect);
            i++;
        }
    }
}

package cn.xports.qd2.circle.videoPlayer;

import android.content.Intent;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import cn.xports.qd2.R;
import cn.xports.qd2.circle.videoPlayer.entity.VideoInfo;
import com.stub.StubApp;
import java.io.IOException;

public class SurfaceActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener, MediaPlayer.OnErrorListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnVideoSizeChangedListener {
    /* access modifiers changed from: private */
    public static final String TAG = "SurfaceActivity";
    private LinearLayout bottomLayout;
    private SeekBar.OnSeekBarChangeListener change = new SeekBar.OnSeekBarChangeListener() {
        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
            int progress = seekBar.getProgress();
            if (SurfaceActivity.this.mediaPlayer != null && SurfaceActivity.this.mediaPlayer.isPlaying()) {
                SurfaceActivity.this.mediaPlayer.seekTo(progress);
                TextView access$000 = SurfaceActivity.this.tvPlayTime;
                access$000.setText("" + CommTools.LongToHms((long) progress));
            }
        }
    };
    Handler handler = new Handler() {
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 1) {
                int intValue = ((Integer) message.obj).intValue();
                TextView access$000 = SurfaceActivity.this.tvPlayTime;
                access$000.setText("" + CommTools.LongToHms((long) intValue));
            }
        }
    };
    /* access modifiers changed from: private */
    public SurfaceHolder holder;
    private Intent intent;
    /* access modifiers changed from: private */
    public boolean isPlay;
    private boolean isShowMenu;
    private ImageView ivBack;
    private ImageView ivDown;
    private ImageView ivFull;
    /* access modifiers changed from: private */
    public ImageView ivPlay;
    private ImageView ivShare;
    /* access modifiers changed from: private */
    public ImageView ivState;
    GestureDetector.OnGestureListener listener = new GestureDetector.OnGestureListener() {
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            return false;
        }

        public void onLongPress(MotionEvent motionEvent) {
        }

        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            return false;
        }

        public void onShowPress(MotionEvent motionEvent) {
        }

        public boolean onSingleTapUp(MotionEvent motionEvent) {
            return false;
        }

        public boolean onDown(MotionEvent motionEvent) {
            SurfaceActivity.this.finish();
            return false;
        }
    };
    private GestureDetector mGestureDetector;
    /* access modifiers changed from: private */
    public MediaPlayer mediaPlayer;
    private RelativeLayout rootLayout;
    Runnable runnable = new Runnable() {
        public void run() {
            SurfaceActivity.this.isShowHideTitle(false);
            SurfaceActivity.this.handler.postDelayed(this, 1000);
        }
    };
    private RelativeLayout rvLayout;
    private boolean screenDirection = true;
    /* access modifiers changed from: private */
    public SeekBar seekBar;
    private String sort;
    /* access modifiers changed from: private */
    public float surHeight;
    /* access modifiers changed from: private */
    public float surWidth;
    /* access modifiers changed from: private */
    public SurfaceView surfaceView;
    private float systemHeight;
    private float systemWidth;
    private LinearLayout topLayout;
    /* access modifiers changed from: private */
    public TextView tvPlayTime;
    private TextView tvSort;
    /* access modifiers changed from: private */
    public TextView tvTotalTime;
    /* access modifiers changed from: private */
    public int type;
    /* access modifiers changed from: private */
    public float videoHeight;
    private VideoInfo videoInfo;
    /* access modifiers changed from: private */
    public float videoWidth;

    static {
        StubApp.interface11(3907);
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    public void onVideoSizeChanged(MediaPlayer mediaPlayer2, int i, int i2) {
    }

    private void initEvent() {
        this.holder.addCallback(new MySurfaceHolderCallback());
        this.mediaPlayer.setOnErrorListener(this);
        this.mediaPlayer.setOnCompletionListener(this);
        this.mediaPlayer.setOnVideoSizeChangedListener(this);
        this.seekBar.setOnSeekBarChangeListener(this.change);
    }

    private void initData() {
        this.intent = getIntent();
        this.videoInfo = (VideoInfo) this.intent.getParcelableExtra("VIDEO_INFO");
        this.type = this.intent.getIntExtra("VIDEO_TYPE", -1);
        this.sort = this.intent.getStringExtra("VIDEO_SORT");
        this.tvSort.setText(this.sort);
        if (this.type == 0) {
            this.tvTotalTime.setText(this.videoInfo.getTime());
        }
        this.rootLayout.setOnTouchListener(this);
        this.mGestureDetector = new GestureDetector(this.listener);
        this.mediaPlayer = new MediaPlayer();
        this.holder = this.surfaceView.getHolder();
        this.holder.setType(3);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.systemWidth = (float) displayMetrics.widthPixels;
        this.systemHeight = (float) displayMetrics.heightPixels;
    }

    private void initView() {
        this.rootLayout = (RelativeLayout) findViewById(R.id.surface_root);
        this.surfaceView = (SurfaceView) findViewById(R.id.surface_view);
        this.topLayout = (LinearLayout) findViewById(R.id.surface_top_ll);
        this.bottomLayout = (LinearLayout) findViewById(R.id.surface_bottom_ll);
        this.ivState = (ImageView) findViewById(R.id.surface_iv_state);
        this.ivState.setOnClickListener(this);
        this.ivBack = (ImageView) findViewById(R.id.surface_iv_back);
        this.ivBack.setOnClickListener(this);
        this.ivDown = (ImageView) findViewById(R.id.surface_iv_download);
        this.ivDown.setOnClickListener(this);
        this.ivShare = (ImageView) findViewById(R.id.surface_iv_share);
        this.ivShare.setOnClickListener(this);
        this.ivPlay = (ImageView) findViewById(R.id.surface_iv_play);
        this.ivPlay.setOnClickListener(this);
        this.ivFull = (ImageView) findViewById(R.id.surface_iv_full);
        this.ivFull.setOnClickListener(this);
        this.tvPlayTime = (TextView) findViewById(R.id.surface_tv_start_time);
        this.tvTotalTime = (TextView) findViewById(R.id.surface_tv_total_time);
        this.tvSort = (TextView) findViewById(R.id.surface_tv_sort);
        this.seekBar = (SeekBar) findViewById(R.id.surface_seekbar);
        this.rvLayout = (RelativeLayout) findViewById(R.id.surface_rl_sv);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.surface_iv_state) {
            pause();
        } else if (id == R.id.surface_iv_back) {
            finish();
        } else if (id != R.id.surface_iv_download && id != R.id.surface_iv_share) {
            if (id == R.id.surface_iv_play) {
                pause();
            } else if (id == R.id.surface_iv_full) {
                screenCut();
            }
        }
    }

    public void screenCut() {
        if (this.screenDirection) {
            setRequestedOrientation(0);
            this.screenDirection = false;
            return;
        }
        setRequestedOrientation(1);
        this.screenDirection = true;
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (configuration.orientation == 1) {
            showNavigationBar();
            double min = Math.min((double) (this.surWidth / this.videoWidth), (double) (this.surHeight / this.videoHeight));
            double d = (double) this.videoWidth;
            Double.isNaN(d);
            double d2 = d * min;
            double d3 = (double) this.videoHeight;
            Double.isNaN(d3);
            double d4 = d3 * min;
            ViewGroup.LayoutParams layoutParams = this.surfaceView.getLayoutParams();
            layoutParams.width = (int) d2;
            layoutParams.height = (int) d4;
            this.surfaceView.setLayoutParams(layoutParams);
        }
        if (configuration.orientation == 2) {
            hideNavigationBar();
            double min2 = Math.min((double) (this.systemHeight / this.videoWidth), (double) (this.systemWidth / this.videoHeight));
            double d5 = (double) this.videoWidth;
            Double.isNaN(d5);
            double d6 = (double) this.videoHeight;
            Double.isNaN(d6);
            ViewGroup.LayoutParams layoutParams2 = this.surfaceView.getLayoutParams();
            layoutParams2.width = (int) (d5 * min2);
            layoutParams2.height = (int) (d6 * min2);
            this.surfaceView.setLayoutParams(layoutParams2);
            Toast.makeText(this, "现在是横屏", 0).show();
        }
    }

    private void showNavigationBar() {
        getWindow().getDecorView().setSystemUiVisibility(1280);
    }

    public void hideNavigationBar() {
        getWindow().getDecorView().setSystemUiVisibility(1284);
    }

    private void pause() {
        Log.i(TAG, "暂停和继续播放");
        if (this.mediaPlayer.isPlaying()) {
            this.mediaPlayer.pause();
            this.handler.removeCallbacks(this.runnable);
            this.ivState.setSelected(false);
            this.ivPlay.setSelected(false);
            return;
        }
        this.ivState.setSelected(true);
        this.ivPlay.setSelected(true);
        this.mediaPlayer.start();
        this.handler.postDelayed(this.runnable, 3000);
    }

    /* access modifiers changed from: private */
    public void isShowHideTitle(boolean z) {
        if (z) {
            this.topLayout.setVisibility(0);
            this.bottomLayout.setVisibility(0);
            this.ivState.setVisibility(0);
            this.isShowMenu = true;
            return;
        }
        this.topLayout.setVisibility(8);
        this.bottomLayout.setVisibility(8);
        this.ivState.setVisibility(8);
        this.isShowMenu = false;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        play();
        super.onResume();
    }

    public void finish() {
        stop();
        this.handler.removeCallbacks(this.runnable);
        super.finish();
        overridePendingTransition(0, R.anim.anim_zoom_out);
    }

    class MySurfaceHolderCallback implements SurfaceHolder.Callback {
        MySurfaceHolderCallback() {
        }

        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            Log.i(SurfaceActivity.TAG, "surfaceHolder被创建了");
            SurfaceActivity.this.mediaPlayer.setDisplay(SurfaceActivity.this.holder);
        }

        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
            Log.i(SurfaceActivity.TAG, "surfaceHolder被改变了");
        }

        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            Log.i(SurfaceActivity.TAG, "surfaceHolder被销毁了");
        }
    }

    private void play() {
        this.mediaPlayer.setAudioStreamType(3);
        try {
            this.mediaPlayer.setDataSource(this.videoInfo.getFilePath());
            this.mediaPlayer.prepareAsync();
            this.mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                public void onPrepared(final MediaPlayer mediaPlayer) {
                    float unused = SurfaceActivity.this.videoWidth = (float) mediaPlayer.getVideoWidth();
                    float unused2 = SurfaceActivity.this.videoHeight = (float) mediaPlayer.getVideoHeight();
                    float unused3 = SurfaceActivity.this.surWidth = (float) SurfaceActivity.this.surfaceView.getWidth();
                    float unused4 = SurfaceActivity.this.surHeight = (float) SurfaceActivity.this.surfaceView.getHeight();
                    double min = Math.min((double) (SurfaceActivity.this.surWidth / SurfaceActivity.this.videoWidth), (double) (SurfaceActivity.this.surHeight / SurfaceActivity.this.videoHeight));
                    double access$500 = (double) SurfaceActivity.this.videoWidth;
                    Double.isNaN(access$500);
                    double d = access$500 * min;
                    double access$600 = (double) SurfaceActivity.this.videoHeight;
                    Double.isNaN(access$600);
                    double d2 = access$600 * min;
                    ViewGroup.LayoutParams layoutParams = SurfaceActivity.this.surfaceView.getLayoutParams();
                    layoutParams.width = (int) d;
                    layoutParams.height = (int) d2;
                    SurfaceActivity.this.surfaceView.setLayoutParams(layoutParams);
                    mediaPlayer.start();
                    if (SurfaceActivity.this.type == 1) {
                        SurfaceActivity.this.tvTotalTime.setText(CommTools.LongToHms((long) mediaPlayer.getDuration()));
                    }
                    SurfaceActivity.this.seekBar.setMax(mediaPlayer.getDuration());
                    SurfaceActivity.this.ivState.setSelected(true);
                    SurfaceActivity.this.ivPlay.setSelected(true);
                    new Thread() {
                        public void run() {
                            try {
                                boolean unused = SurfaceActivity.this.isPlay = true;
                                while (SurfaceActivity.this.isPlay) {
                                    int currentPosition = mediaPlayer.getCurrentPosition();
                                    Message obtain = Message.obtain();
                                    obtain.what = 1;
                                    obtain.obj = Integer.valueOf(currentPosition);
                                    SurfaceActivity.this.handler.sendMessage(obtain);
                                    SurfaceActivity.this.seekBar.setProgress(currentPosition);
                                    sleep(500);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                    SurfaceActivity.this.handler.removeCallbacks(SurfaceActivity.this.runnable);
                    SurfaceActivity.this.handler.postDelayed(SurfaceActivity.this.runnable, 1000);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void stop() {
        Log.e(TAG, "停止播放");
        this.mediaPlayer.stop();
        this.mediaPlayer.release();
    }

    public boolean onError(MediaPlayer mediaPlayer2, int i, int i2) {
        Toast.makeText(this, "播放出错，请重新播放", 0).show();
        return false;
    }

    public void onCompletion(MediaPlayer mediaPlayer2) {
        Log.e(TAG, "播放结束时的监听");
        this.isPlay = false;
        this.ivState.setSelected(false);
        this.ivPlay.setSelected(false);
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        return this.mGestureDetector.onTouchEvent(motionEvent);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        this.handler.removeCallbacksAndMessages((Object) null);
        super.onDestroy();
    }
}

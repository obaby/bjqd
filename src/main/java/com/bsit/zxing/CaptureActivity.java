package com.bsit.zxing;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.bsit.zxing.camera.CameraManager;
import com.bsit.zxing.common.BitmapUtils;
import com.bsit.zxing.decode.BitmapDecoder;
import com.bsit.zxing.decode.CaptureActivityHandler;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.Result;
import com.google.zxing.client.result.ResultParser;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.Map;

public class CaptureActivity extends Activity implements SurfaceHolder.Callback, View.OnClickListener {
    private static final int PARSE_BARCODE_FAIL = 300;
    private static final int PARSE_BARCODE_SUC = 200;
    private static final int REQUEST_CODE = 100;
    private static final String TAG = "CaptureActivity";
    private AmbientLightManager ambientLightManager;
    private BeepManager beepManager;
    private CameraManager cameraManager;
    private String characterSet;
    private Collection<BarcodeFormat> decodeFormats;
    private Map<DecodeHintType, ?> decodeHints;
    private CaptureActivityHandler handler;
    private boolean hasSurface;
    private InactivityTimer inactivityTimer;
    private Rect mCropRect = null;
    /* access modifiers changed from: private */
    public Handler mHandler = new MyHandler(this);
    /* access modifiers changed from: private */
    public String photoPath;
    public RelativeLayout rlScanBottom;
    private Result savedResultToShow;
    private RelativeLayout scanContainer;
    private RelativeLayout scanCropView;

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
    }

    static class MyHandler extends Handler {
        private WeakReference<Activity> activityReference;

        public MyHandler(Activity activity) {
            this.activityReference = new WeakReference<>(activity);
        }

        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 200) {
                Toast.makeText((Context) this.activityReference.get(), "解析成功，结果为：" + message.obj, 0).show();
            } else if (i == CaptureActivity.PARSE_BARCODE_FAIL) {
                Toast.makeText((Context) this.activityReference.get(), "解析图片失败", 0).show();
            }
            super.handleMessage(message);
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().addFlags(128);
        setContentView(R.layout.activity_capture);
        this.rlScanBottom = (RelativeLayout) findViewById(R.id.rl_scan_bottom);
        this.scanContainer = (RelativeLayout) findViewById(R.id.capture_container);
        this.scanCropView = (RelativeLayout) findViewById(R.id.capture_crop_view);
        TranslateAnimation translateAnimation = new TranslateAnimation(2, 0.0f, 2, 0.0f, 2, 0.0f, 2, 0.9f);
        translateAnimation.setDuration(3500);
        translateAnimation.setRepeatCount(-1);
        translateAnimation.setRepeatMode(1);
        ((ImageView) findViewById(R.id.capture_scan_line)).startAnimation(translateAnimation);
        this.hasSurface = false;
        this.inactivityTimer = new InactivityTimer(this);
        this.beepManager = new BeepManager(this);
        this.ambientLightManager = new AmbientLightManager(this);
        findViewById(R.id.capture_back_btn).setOnClickListener(this);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.cameraManager = new CameraManager(this);
        this.handler = null;
        SurfaceHolder holder = ((SurfaceView) findViewById(R.id.capture_preview_view)).getHolder();
        if (this.hasSurface) {
            initCamera(holder);
        } else {
            holder.addCallback(this);
        }
        this.beepManager.updatePrefs();
        this.ambientLightManager.start(this.cameraManager);
        this.inactivityTimer.onResume();
        this.decodeFormats = null;
        this.characterSet = null;
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        if (this.handler != null) {
            this.handler.quitSynchronously();
            this.handler = null;
        }
        this.inactivityTimer.onPause();
        this.ambientLightManager.stop();
        this.beepManager.close();
        this.cameraManager.closeDriver();
        if (!this.hasSurface) {
            ((SurfaceView) findViewById(R.id.capture_preview_view)).getHolder().removeCallback(this);
        }
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        this.inactivityTimer.shutdown();
        super.onDestroy();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            finish();
        } else if (i == 27 || i == 80) {
            return true;
        } else {
            switch (i) {
                case 24:
                    return true;
                case 25:
                    return true;
            }
        }
        return super.onKeyDown(i, keyEvent);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i2 == -1 && i == 100) {
            Cursor query = getContentResolver().query(intent.getData(), new String[]{"_data"}, (String) null, (String[]) null, (String) null);
            if (query.moveToFirst()) {
                this.photoPath = query.getString(query.getColumnIndexOrThrow("_data"));
                if (this.photoPath == null) {
                    this.photoPath = UriUtils.getPath(this, intent.getData());
                }
            }
            query.close();
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("正在扫描...");
            progressDialog.setCancelable(false);
            progressDialog.show();
            new Thread(new Runnable() {
                public void run() {
                    Result rawResult = new BitmapDecoder(CaptureActivity.this).getRawResult(BitmapUtils.getCompressedBitmap(CaptureActivity.this.photoPath));
                    if (rawResult != null) {
                        Message obtainMessage = CaptureActivity.this.mHandler.obtainMessage();
                        obtainMessage.what = 200;
                        obtainMessage.obj = ResultParser.parseResult(rawResult).toString();
                        CaptureActivity.this.mHandler.sendMessage(obtainMessage);
                    } else {
                        Message obtainMessage2 = CaptureActivity.this.mHandler.obtainMessage();
                        obtainMessage2.what = CaptureActivity.PARSE_BARCODE_FAIL;
                        CaptureActivity.this.mHandler.sendMessage(obtainMessage2);
                    }
                    progressDialog.dismiss();
                }
            }).start();
        }
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        if (surfaceHolder == null) {
            Log.e(TAG, "*** WARNING *** surfaceCreated() gave us a null surface!");
        }
        if (!this.hasSurface) {
            this.hasSurface = true;
            initCamera(surfaceHolder);
        }
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        this.hasSurface = false;
    }

    public void handleDecode(Result result, Bitmap bitmap, float f) {
        this.inactivityTimer.onActivity();
        this.beepManager.playBeepSoundAndVibrate();
    }

    public void restartPreviewAfterDelay(long j) {
        if (this.handler != null) {
            this.handler.sendEmptyMessageDelayed(R.id.zxing_restart_preview, j);
        }
    }

    public Handler getHandler() {
        return this.handler;
    }

    public CameraManager getCameraManager() {
        return this.cameraManager;
    }

    private void initCamera(SurfaceHolder surfaceHolder) {
        if (surfaceHolder == null) {
            throw new IllegalStateException("No SurfaceHolder provided");
        } else if (this.cameraManager.isOpen()) {
            Log.w(TAG, "initCamera() while already open -- late SurfaceView callback?");
        } else {
            try {
                this.cameraManager.openDriver(surfaceHolder);
                if (this.handler == null) {
                    this.handler = new CaptureActivityHandler(this, this.decodeFormats, this.decodeHints, this.characterSet, this.cameraManager);
                }
                initCrop();
            } catch (IOException e) {
                Log.w(TAG, e);
                displayFrameworkBugMessageAndExit();
            } catch (RuntimeException e2) {
                Log.w(TAG, "Unexpected error initializing camera", e2);
                displayFrameworkBugMessageAndExit();
            }
        }
    }

    private void decodeOrStoreSavedBitmap(Bitmap bitmap, Result result) {
        if (this.handler == null) {
            this.savedResultToShow = result;
            return;
        }
        if (result != null) {
            this.savedResultToShow = result;
        }
        if (this.savedResultToShow != null) {
            this.handler.sendMessage(Message.obtain(this.handler, R.id.zxing_decode_succeeded, this.savedResultToShow));
        }
        this.savedResultToShow = null;
    }

    private void displayFrameworkBugMessageAndExit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.app_name));
        builder.setMessage(R.string.msg_camera_framework_bug);
        builder.setPositiveButton(R.string.button_ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                CaptureActivity.this.finish();
            }
        });
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
                CaptureActivity.this.finish();
            }
        });
        builder.show();
    }

    private void openAlbum() {
        Intent intent = new Intent();
        if (Build.VERSION.SDK_INT < 19) {
            intent.setAction("android.intent.action.GET_CONTENT");
        } else {
            intent.setAction("android.intent.action.OPEN_DOCUMENT");
        }
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "选择二维码图片"), 100);
    }

    private void initCrop() {
        int i = this.cameraManager.getCameraResolution().y;
        int i2 = this.cameraManager.getCameraResolution().x;
        int[] iArr = new int[2];
        this.scanCropView.getLocationInWindow(iArr);
        int i3 = iArr[0];
        int statusBarHeight = iArr[1] - getStatusBarHeight();
        int width = this.scanCropView.getWidth();
        int height = this.scanCropView.getHeight();
        int width2 = this.scanContainer.getWidth();
        int height2 = this.scanContainer.getHeight();
        int i4 = (i3 * i) / width2;
        int i5 = (statusBarHeight * i2) / height2;
        this.mCropRect = new Rect(i4, i5, ((width * i) / width2) + i4, ((height * i2) / height2) + i5);
    }

    private int getStatusBarHeight() {
        try {
            Class<?> cls = Class.forName("com.android.internal.R$dimen");
            return getResources().getDimensionPixelSize(Integer.parseInt(cls.getField("status_bar_height").get(cls.newInstance()).toString()));
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public Rect getCropRect() {
        return this.mCropRect;
    }

    public void onClick(View view) {
        if (view.getId() == R.id.capture_back_btn) {
            finish();
        }
    }
}

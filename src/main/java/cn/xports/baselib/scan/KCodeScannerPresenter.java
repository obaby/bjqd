package cn.xports.baselib.scan;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.SystemClock;
import android.util.Log;
import cn.xports.baselib.util.ByteUtils;
import com.alipay.sdk.util.h;

public class KCodeScannerPresenter {
    private static final String ACTION_RECEIVE_DATA = "com.sunmi.scanner.ACTION_DATA_CODE_RECEIVED";
    private static final String ACTION_SEND_DATA = "com.sunmi.scanner.Setting_cmd";
    private static final int CMD_WAIT_TIME = 500;
    private static final String RECEIVE_DATA = "data";
    private static final String SEND_DATA = "cmd_data";
    private static final String TAG = "KCodeScannerPresenter";
    /* access modifiers changed from: private */
    public boolean bCmdWaitting;
    private Context context;
    private IntentFilter filter;
    private Intent intent;
    /* access modifiers changed from: private */
    public OnDataReceiveListener onDataReceiveListener;
    private ScannerDataReceiver scannerDataReceiver;

    public interface OnDataReceiveListener {
        void onDataReceive(String str);
    }

    private KCodeScannerPresenter() {
        this.bCmdWaitting = false;
        this.context = null;
        this.intent = new Intent();
        this.filter = new IntentFilter();
        this.scannerDataReceiver = new ScannerDataReceiver();
        this.onDataReceiveListener = null;
    }

    public static KCodeScannerPresenter getInstance() {
        return KCodeScannerInstance.INSTANCE;
    }

    public void start(Context context2) {
        if (context2 == null) {
            Log.d(TAG, "start：context = null.");
            return;
        }
        this.context = context2;
        this.intent.setAction(ACTION_SEND_DATA);
        this.filter.addAction(ACTION_RECEIVE_DATA);
        context2.registerReceiver(this.scannerDataReceiver, this.filter);
    }

    public void stop() {
        if (this.context != null) {
            this.context.unregisterReceiver(this.scannerDataReceiver);
            this.context = null;
        }
    }

    public void setOnDataReceiveListener(OnDataReceiveListener onDataReceiveListener2) {
        this.onDataReceiveListener = onDataReceiveListener2;
    }

    /* access modifiers changed from: private */
    public void sendData(String str) {
        Log.d(TAG, "---sendstr:" + str + "---sendhex:" + ByteUtils.str2HexString(str));
        if (this.context != null) {
            byte[] bytes = str.getBytes();
            byte[] bArr = new byte[(bytes.length + 2)];
            System.arraycopy(bytes, 0, bArr, 0, bytes.length);
            lrcCheckSum(bArr);
            this.intent.putExtra(SEND_DATA, bArr);
            this.context.sendBroadcast(this.intent);
        }
    }

    private void lrcCheckSum(byte[] bArr) {
        int length = bArr.length;
        int i = 0;
        int i2 = 0;
        while (true) {
            int i3 = length - 2;
            if (i < i3) {
                i2 += bArr[i] & 255;
                i++;
            } else {
                int i4 = (i2 ^ -1) + 1;
                bArr[i3] = (byte) ((i4 >> 8) & 255);
                bArr[length - 1] = (byte) (i4 & 255);
                return;
            }
        }
    }

    public void setMode(int i, final int i2, int i3) {
        switch (i) {
            case 0:
                StringBuilder sb = new StringBuilder();
                sb.append("NLS0302000;NLS0313000=");
                if (i2 <= 0) {
                    i2 = 60000;
                }
                sb.append(i2);
                sb.append(h.f735b);
                sendData(sb.toString());
                return;
            case 1:
                this.bCmdWaitting = true;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("7E01303030304053454E495354");
                if (i3 < 200 || i3 > 3000) {
                    i3 = 1000;
                }
                sb2.append(ByteUtils.str2HexString(String.valueOf(i3)));
                sb2.append("3B03");
                sendData(ByteUtils.hexStr2Str(sb2.toString()));
                new Thread(new Runnable() {
                    public void run() {
                        long currentTimeMillis = System.currentTimeMillis();
                        while (true) {
                            if (!KCodeScannerPresenter.this.bCmdWaitting) {
                                break;
                            }
                            SystemClock.sleep(50);
                            if (System.currentTimeMillis() - currentTimeMillis > 3000) {
                                Log.e(KCodeScannerPresenter.TAG, "SendThread:等待返回超时");
                                break;
                            }
                        }
                        KCodeScannerPresenter kCodeScannerPresenter = KCodeScannerPresenter.this;
                        StringBuilder sb = new StringBuilder();
                        sb.append("NLS0302010;NLS0313000=");
                        sb.append(i2 > 0 ? i2 : 1000);
                        sb.append(";NLS0309010;");
                        kCodeScannerPresenter.sendData(sb.toString());
                    }
                }).start();
                return;
            default:
                return;
        }
    }

    public void setKeyDown() {
        sendData(ByteUtils.hexStr2Str("1B31"));
    }

    public void setKeyUp() {
        sendData(ByteUtils.hexStr2Str("1B30"));
    }

    private static class KCodeScannerInstance {
        /* access modifiers changed from: private */
        public static final KCodeScannerPresenter INSTANCE = new KCodeScannerPresenter();

        private KCodeScannerInstance() {
        }
    }

    private class ScannerDataReceiver extends BroadcastReceiver {
        private ScannerDataReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            String stringExtra = intent.getStringExtra("data");
            if (stringExtra.contains(ByteUtils.hexStr2Str("303030304053454E495354"))) {
                Log.d(KCodeScannerPresenter.TAG, "cmd return data：" + stringExtra + "[" + ByteUtils.str2HexString(stringExtra) + "]");
                boolean unused = KCodeScannerPresenter.this.bCmdWaitting = false;
                return;
            }
            Log.d(KCodeScannerPresenter.TAG, "scan data[hex]：" + stringExtra + "[" + ByteUtils.str2HexString(stringExtra) + "]");
            KCodeScannerPresenter.this.onDataReceiveListener.onDataReceive(stringExtra);
        }
    }
}

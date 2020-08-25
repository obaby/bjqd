package cn.xports.baselib.util;

import android.bluetooth.BluetoothAdapter;
import android.os.Handler;
import android.util.SparseArray;
import android.view.InputDevice;
import android.view.KeyEvent;
import com.alibaba.fastjson.asm.Opcodes;

public class ScanGunKeyEventHelper {
    private static final long MESSAGE_DELAY = 500;
    static SparseArray<Character> SHIFT_KEY_MAP = new SparseArray<>();
    private final BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    private boolean mCaps;
    private String mDeviceName;
    private final Handler mHandler = new Handler();
    private OnScanSuccessListener mOnScanSuccessListener;
    private final Runnable mScanningFishedRunnable = new Runnable() {
        public void run() {
            ScanGunKeyEventHelper.this.performScanSuccess();
        }
    };
    private StringBuffer mStringBufferResult = new StringBuffer();

    public interface OnScanSuccessListener {
        void onScanSuccess(String str);
    }

    public ScanGunKeyEventHelper(OnScanSuccessListener onScanSuccessListener) {
        this.mOnScanSuccessListener = onScanSuccessListener;
    }

    /* access modifiers changed from: private */
    public void performScanSuccess() {
        String stringBuffer = this.mStringBufferResult.toString();
        if (this.mOnScanSuccessListener != null) {
            this.mOnScanSuccessListener.onScanSuccess(stringBuffer);
        }
        this.mStringBufferResult.setLength(0);
    }

    public void analysisKeyEvent(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        checkLetterStatus(keyEvent);
        if (keyEvent.getAction() == 0) {
            char inputCode = getInputCode(keyEvent);
            if (inputCode != 0) {
                this.mStringBufferResult.append(inputCode);
            }
            if (keyCode == 66) {
                this.mHandler.removeCallbacks(this.mScanningFishedRunnable);
                this.mHandler.post(this.mScanningFishedRunnable);
                return;
            }
            this.mHandler.removeCallbacks(this.mScanningFishedRunnable);
            this.mHandler.postDelayed(this.mScanningFishedRunnable, MESSAGE_DELAY);
        }
    }

    private void checkLetterStatus(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        if (keyCode != 60 && keyCode != 59) {
            return;
        }
        if (keyEvent.getAction() == 0) {
            this.mCaps = true;
        } else {
            this.mCaps = false;
        }
    }

    private char getInputCode(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        if (keyCode >= 29 && keyCode <= 54) {
            return (char) (((this.mCaps ? 65 : 97) + keyCode) - 29);
        } else if (keyCode < 7 || keyCode > 16) {
            return SHIFT_KEY_MAP.get(keyCode, '0').charValue();
        } else {
            return (char) ((keyCode + 48) - 7);
        }
    }

    static {
        SHIFT_KEY_MAP.put(68, '~');
        SHIFT_KEY_MAP.put(Opcodes.IF_ICMPGE, '(');
        SHIFT_KEY_MAP.put(8, '!');
        SHIFT_KEY_MAP.put(77, '@');
        SHIFT_KEY_MAP.put(10, '#');
        SHIFT_KEY_MAP.put(11, '$');
        SHIFT_KEY_MAP.put(12, '%');
        SHIFT_KEY_MAP.put(13, '^');
        SHIFT_KEY_MAP.put(14, '&');
        SHIFT_KEY_MAP.put(15, '*');
        SHIFT_KEY_MAP.put(56, '.');
        SHIFT_KEY_MAP.put(Opcodes.IF_ICMPGT, ')');
        SHIFT_KEY_MAP.put(69, '-');
        SHIFT_KEY_MAP.put(70, '+');
        SHIFT_KEY_MAP.put(71, '{');
        SHIFT_KEY_MAP.put(72, '}');
        SHIFT_KEY_MAP.put(73, '|');
        SHIFT_KEY_MAP.put(74, ':');
        SHIFT_KEY_MAP.put(75, '\'');
        SHIFT_KEY_MAP.put(55, '<');
        SHIFT_KEY_MAP.put(56, '>');
        SHIFT_KEY_MAP.put(76, '?');
    }

    public void onDestroy() {
        this.mHandler.removeCallbacks(this.mScanningFishedRunnable);
        this.mOnScanSuccessListener = null;
    }

    private boolean isInputDeviceExist(String str) {
        for (int device : InputDevice.getDeviceIds()) {
            if (InputDevice.getDevice(device).getName().equals(str)) {
                return true;
            }
        }
        return false;
    }

    @Deprecated
    public boolean isScanGunEvent(KeyEvent keyEvent) {
        return keyEvent.getDevice().getName().equals(this.mDeviceName);
    }
}

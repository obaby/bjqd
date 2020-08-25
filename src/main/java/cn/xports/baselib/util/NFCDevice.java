package cn.xports.baselib.util;

import android.content.Intent;
import android.nfc.Tag;
import android.nfc.tech.MifareClassic;
import android.text.TextUtils;
import android.util.Log;
import java.io.IOException;

public class NFCDevice {
    public static final byte[] DEFAULT_KEY = MifareClassic.KEY_DEFAULT;
    public static final byte[] YXT_DEFAULT_KEY = {-119, -120, -124, 73, 80, 81};
    private static ReadListener mReadListener;

    public interface ReadListener {
        void onCardNum(String str);

        void onReadUid(String str);
    }

    public static void processYXTCard(Intent intent) {
        try {
            String action = intent.getAction();
            if ("android.nfc.action.TAG_DISCOVERED".equals(action) || "android.nfc.action.TECH_DISCOVERED".equals(action) || "android.nfc.action.NDEF_DISCOVERED".equals(action)) {
                MifareClassic mifareClassic = MifareClassic.get((Tag) intent.getParcelableExtra("android.nfc.extra.TAG"));
                try {
                    mifareClassic.connect();
                    if ((mifareClassic.getSectorCount() > 1 && mifareClassic.authenticateSectorWithKeyA(1, CardDecodeUtil.getDecodeKey())) || mifareClassic.authenticateSectorWithKeyA(1, DEFAULT_KEY)) {
                        int sectorToBlock = mifareClassic.sectorToBlock(1);
                        if (mifareClassic.getBlockCountInSector(1) > 1) {
                            String bytes2HexStr = ByteUtils.bytes2HexStr(mifareClassic.readBlock(sectorToBlock + 1));
                            Log.i("NFCDevice", "cardNum:" + bytes2HexStr);
                            int dataLength = CardDecodeUtil.getDataLength();
                            if (!TextUtils.isEmpty(bytes2HexStr) && bytes2HexStr.length() >= dataLength && mReadListener != null) {
                                mReadListener.onCardNum(bytes2HexStr.substring(0, dataLength));
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String bytes2HexStr2 = ByteUtils.bytes2HexStr(intent.getByteArrayExtra("android.nfc.extra.ID"));
                Log.i("NFCDevice", bytes2HexStr2);
                if (mReadListener != null && !TextUtils.isEmpty(bytes2HexStr2)) {
                    mReadListener.onReadUid(bytes2HexStr2);
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void setReadListener(ReadListener readListener) {
        mReadListener = readListener;
    }
}

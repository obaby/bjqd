package com.bsit.zxing.decode;

import android.content.Context;
import android.graphics.Bitmap;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import java.util.Hashtable;
import java.util.Vector;

public class BitmapDecoder {
    MultiFormatReader multiFormatReader = new MultiFormatReader();

    public BitmapDecoder(Context context) {
        Hashtable hashtable = new Hashtable(2);
        Vector vector = new Vector();
        if (vector.isEmpty()) {
            vector = new Vector();
            vector.addAll(DecodeFormatManager.ONE_D_FORMATS);
            vector.addAll(DecodeFormatManager.QR_CODE_FORMATS);
            vector.addAll(DecodeFormatManager.DATA_MATRIX_FORMATS);
        }
        hashtable.put(DecodeHintType.POSSIBLE_FORMATS, vector);
        hashtable.put(DecodeHintType.CHARACTER_SET, "UTF8");
        this.multiFormatReader.setHints(hashtable);
    }

    public Result getRawResult(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        try {
            return this.multiFormatReader.decodeWithState(new BinaryBitmap(new HybridBinarizer(new BitmapLuminanceSource(bitmap))));
        } catch (NotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}

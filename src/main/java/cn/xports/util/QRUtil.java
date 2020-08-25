package cn.xports.util;

import android.graphics.Bitmap;
import android.widget.ImageView;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.util.Hashtable;

public class QRUtil {
    public static Bitmap createQRCode(String str, int i) {
        new Hashtable().put(EncodeHintType.CHARACTER_SET, "utf-8");
        try {
            BitMatrix encode = new MultiFormatWriter().encode(str, BarcodeFormat.QR_CODE, i, i);
            int width = encode.getWidth();
            int height = encode.getHeight();
            int[] iArr = new int[(width * height)];
            for (int i2 = 0; i2 < height; i2++) {
                for (int i3 = 0; i3 < width; i3++) {
                    if (encode.get(i3, i2)) {
                        iArr[(i2 * width) + i3] = -16777216;
                    } else {
                        iArr[(i2 * width) + i3] = -1;
                    }
                }
            }
            Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            createBitmap.setPixels(iArr, 0, width, 0, 0, width, height);
            return createBitmap;
        } catch (WriterException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Bitmap createQRImage(String str, int i) {
        if (str != null) {
            try {
                if (!"".equals(str)) {
                    if (str.length() >= 1) {
                        Hashtable hashtable = new Hashtable();
                        hashtable.put(EncodeHintType.CHARACTER_SET, "utf-8");
                        hashtable.put(EncodeHintType.MARGIN, 1);
                        hashtable.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
                        BitMatrix encode = new MultiFormatWriter().encode(str, BarcodeFormat.QR_CODE, i, i, hashtable);
                        deleteWhite(encode);
                        int width = encode.getWidth();
                        int[] iArr = new int[(width * width)];
                        for (int i2 = 0; i2 < width; i2++) {
                            for (int i3 = 0; i3 < width; i3++) {
                                if (encode.get(i3, i2)) {
                                    iArr[(i2 * width) + i3] = -16777216;
                                } else {
                                    iArr[(i2 * width) + i3] = -1;
                                }
                            }
                        }
                        Bitmap createBitmap = Bitmap.createBitmap(width, width, Bitmap.Config.ARGB_8888);
                        createBitmap.setPixels(iArr, 0, width, 0, 0, width, width);
                        return createBitmap;
                    }
                }
            } catch (WriterException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    public static void createQRImage(String str, ImageView imageView) {
        Bitmap createQRCode = createQRCode(str, imageView.getWidth());
        if (createQRCode != null) {
            imageView.setImageBitmap(createQRCode);
        }
    }

    private static BitMatrix deleteWhite(BitMatrix bitMatrix) {
        int[] enclosingRectangle = bitMatrix.getEnclosingRectangle();
        int i = enclosingRectangle[2] + 1;
        int i2 = enclosingRectangle[3] + 1;
        BitMatrix bitMatrix2 = new BitMatrix(i, i2);
        bitMatrix2.clear();
        for (int i3 = 0; i3 < i; i3++) {
            for (int i4 = 0; i4 < i2; i4++) {
                if (bitMatrix.get(enclosingRectangle[0] + i3, enclosingRectangle[1] + i4)) {
                    bitMatrix2.set(i3, i4);
                }
            }
        }
        return bitMatrix2;
    }
}

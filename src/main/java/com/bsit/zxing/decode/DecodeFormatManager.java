package com.bsit.zxing.decode;

import com.google.zxing.BarcodeFormat;
import java.util.EnumSet;
import java.util.Set;
import java.util.regex.Pattern;

final class DecodeFormatManager {
    static final Set<BarcodeFormat> AZTEC_FORMATS = EnumSet.of(BarcodeFormat.AZTEC);
    private static final Pattern COMMA_PATTERN = Pattern.compile(",");
    static final Set<BarcodeFormat> DATA_MATRIX_FORMATS = EnumSet.of(BarcodeFormat.DATA_MATRIX);
    static final Set<BarcodeFormat> INDUSTRIAL_FORMATS = EnumSet.of(BarcodeFormat.CODE_39, BarcodeFormat.CODE_93, BarcodeFormat.CODE_128, BarcodeFormat.ITF, BarcodeFormat.CODABAR);
    static final Set<BarcodeFormat> ONE_D_FORMATS = EnumSet.copyOf(PRODUCT_FORMATS);
    static final Set<BarcodeFormat> PDF417_FORMATS = EnumSet.of(BarcodeFormat.PDF_417);
    static final Set<BarcodeFormat> PRODUCT_FORMATS = EnumSet.of(BarcodeFormat.UPC_A, new BarcodeFormat[]{BarcodeFormat.UPC_E, BarcodeFormat.EAN_13, BarcodeFormat.EAN_8, BarcodeFormat.RSS_14, BarcodeFormat.RSS_EXPANDED});
    static final Set<BarcodeFormat> QR_CODE_FORMATS = EnumSet.of(BarcodeFormat.QR_CODE);

    static {
        ONE_D_FORMATS.addAll(INDUSTRIAL_FORMATS);
    }

    private DecodeFormatManager() {
    }
}

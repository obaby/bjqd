package com.alibaba.fastjson.asm;

import com.alibaba.sdk.android.mns.common.MNSConstants;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ClassReader {

    /* renamed from: b  reason: collision with root package name */
    public final byte[] f454b;
    public final int header;
    private final int[] items;
    private final int maxStringLength;
    private final String[] strings;

    public ClassReader(InputStream inputStream) throws IOException {
        int i;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            i = 0;
            if (read == -1) {
                break;
            } else if (read > 0) {
                byteArrayOutputStream.write(bArr, 0, read);
            }
        }
        inputStream.close();
        this.f454b = byteArrayOutputStream.toByteArray();
        this.items = new int[readUnsignedShort(8)];
        int length = this.items.length;
        this.strings = new String[length];
        int i2 = 1;
        int i3 = 10;
        while (i2 < length) {
            int i4 = i3 + 1;
            this.items[i2] = i4;
            byte b2 = this.f454b[i3];
            int i5 = 3;
            if (b2 == 1) {
                i5 = 3 + readUnsignedShort(i4);
                if (i5 > i) {
                    i = i5;
                }
            } else if (b2 != 15) {
                if (b2 != 18) {
                    switch (b2) {
                        case 3:
                        case 4:
                            break;
                        case 5:
                        case 6:
                            i5 = 9;
                            i2++;
                            continue;
                        default:
                            switch (b2) {
                                case 9:
                                case 10:
                                case 11:
                                case 12:
                                    break;
                                default:
                                    continue;
                            }
                    }
                }
                i5 = 5;
            } else {
                i5 = 4;
            }
            i3 += i5;
            i2++;
        }
        this.maxStringLength = i;
        this.header = i3;
    }

    public void accept(TypeCollector typeCollector) {
        char[] cArr = new char[this.maxStringLength];
        int i = this.header;
        int i2 = this.items[readUnsignedShort(i + 4)];
        int readUnsignedShort = readUnsignedShort(i + 6);
        int i3 = i + 8;
        for (int i4 = 0; i4 < readUnsignedShort; i4++) {
            i3 += 2;
        }
        int i5 = i3 + 2;
        int i6 = i5;
        for (int readUnsignedShort2 = readUnsignedShort(i3); readUnsignedShort2 > 0; readUnsignedShort2--) {
            i6 += 8;
            for (int readUnsignedShort3 = readUnsignedShort(i6 + 6); readUnsignedShort3 > 0; readUnsignedShort3--) {
                i6 += readInt(i6 + 2) + 6;
            }
        }
        int i7 = i6 + 2;
        for (int readUnsignedShort4 = readUnsignedShort(i6); readUnsignedShort4 > 0; readUnsignedShort4--) {
            i7 += 8;
            for (int readUnsignedShort5 = readUnsignedShort(i7 + 6); readUnsignedShort5 > 0; readUnsignedShort5--) {
                i7 += readInt(i7 + 2) + 6;
            }
        }
        int i8 = i7 + 2;
        for (int readUnsignedShort6 = readUnsignedShort(i7); readUnsignedShort6 > 0; readUnsignedShort6--) {
            i8 += readInt(i8 + 2) + 6;
        }
        for (int readUnsignedShort7 = readUnsignedShort(i3); readUnsignedShort7 > 0; readUnsignedShort7--) {
            i5 += 8;
            for (int readUnsignedShort8 = readUnsignedShort(i5 + 6); readUnsignedShort8 > 0; readUnsignedShort8--) {
                i5 += readInt(i5 + 2) + 6;
            }
        }
        int i9 = i5 + 2;
        for (int readUnsignedShort9 = readUnsignedShort(i5); readUnsignedShort9 > 0; readUnsignedShort9--) {
            i9 = readMethod(typeCollector, cArr, i9);
        }
    }

    private int readMethod(TypeCollector typeCollector, char[] cArr, int i) {
        int readUnsignedShort = readUnsignedShort(i);
        String readUTF8 = readUTF8(i + 2, cArr);
        String readUTF82 = readUTF8(i + 4, cArr);
        int i2 = i + 8;
        int i3 = 0;
        int i4 = 0;
        for (int readUnsignedShort2 = readUnsignedShort(i + 6); readUnsignedShort2 > 0; readUnsignedShort2--) {
            String readUTF83 = readUTF8(i2, cArr);
            int readInt = readInt(i2 + 2);
            int i5 = i2 + 6;
            if (readUTF83.equals(MNSConstants.ERROR_CODE_TAG)) {
                i4 = i5;
            }
            i2 = i5 + readInt;
        }
        MethodCollector visitMethod = typeCollector.visitMethod(readUnsignedShort, readUTF8, readUTF82);
        if (!(visitMethod == null || i4 == 0)) {
            int readInt2 = i4 + 8 + readInt(i4 + 4);
            int i6 = readInt2 + 2;
            for (int readUnsignedShort3 = readUnsignedShort(readInt2); readUnsignedShort3 > 0; readUnsignedShort3--) {
                i6 += 8;
            }
            int i7 = i6 + 2;
            int i8 = 0;
            for (int readUnsignedShort4 = readUnsignedShort(i6); readUnsignedShort4 > 0; readUnsignedShort4--) {
                String readUTF84 = readUTF8(i7, cArr);
                if (readUTF84.equals("LocalVariableTable")) {
                    i3 = i7 + 6;
                } else if (readUTF84.equals("LocalVariableTypeTable")) {
                    i8 = i7 + 6;
                }
                i7 += readInt(i7 + 2) + 6;
            }
            if (i3 != 0) {
                if (i8 != 0) {
                    int readUnsignedShort5 = readUnsignedShort(i8) * 3;
                    int i9 = i8 + 2;
                    int[] iArr = new int[readUnsignedShort5];
                    while (readUnsignedShort5 > 0) {
                        int i10 = readUnsignedShort5 - 1;
                        iArr[i10] = i9 + 6;
                        int i11 = i10 - 1;
                        iArr[i11] = readUnsignedShort(i9 + 8);
                        readUnsignedShort5 = i11 - 1;
                        iArr[readUnsignedShort5] = readUnsignedShort(i9);
                        i9 += 10;
                    }
                }
                int i12 = i3 + 2;
                for (int readUnsignedShort6 = readUnsignedShort(i3); readUnsignedShort6 > 0; readUnsignedShort6--) {
                    visitMethod.visitLocalVariable(readUTF8(i12 + 4, cArr), readUnsignedShort(i12 + 8));
                    i12 += 10;
                }
            }
        }
        return i2;
    }

    private int readUnsignedShort(int i) {
        byte[] bArr = this.f454b;
        return (bArr[i + 1] & 255) | ((bArr[i] & 255) << 8);
    }

    private int readInt(int i) {
        byte[] bArr = this.f454b;
        return (bArr[i + 3] & 255) | ((bArr[i] & 255) << 24) | ((bArr[i + 1] & 255) << 16) | ((bArr[i + 2] & 255) << 8);
    }

    private String readUTF8(int i, char[] cArr) {
        int readUnsignedShort = readUnsignedShort(i);
        String str = this.strings[readUnsignedShort];
        if (str != null) {
            return str;
        }
        int i2 = this.items[readUnsignedShort];
        String[] strArr = this.strings;
        String readUTF = readUTF(i2 + 2, readUnsignedShort(i2), cArr);
        strArr[readUnsignedShort] = readUTF;
        return readUTF;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String readUTF(int r9, int r10, char[] r11) {
        /*
            r8 = this;
            int r10 = r10 + r9
            byte[] r0 = r8.f454b
            r1 = 0
            r2 = 0
            r3 = 0
            r4 = 0
        L_0x0007:
            if (r9 >= r10) goto L_0x0048
            int r5 = r9 + 1
            byte r9 = r0[r9]
            r6 = 1
            switch(r2) {
                case 0: goto L_0x0028;
                case 1: goto L_0x001b;
                case 2: goto L_0x0012;
                default: goto L_0x0011;
            }
        L_0x0011:
            goto L_0x0046
        L_0x0012:
            int r2 = r4 << 6
            r9 = r9 & 63
            r9 = r9 | r2
            char r9 = (char) r9
        L_0x0018:
            r4 = r9
            r2 = 1
            goto L_0x0046
        L_0x001b:
            int r2 = r3 + 1
            int r6 = r4 << 6
            r9 = r9 & 63
            r9 = r9 | r6
            char r9 = (char) r9
            r11[r3] = r9
            r3 = r2
            r2 = 0
            goto L_0x0046
        L_0x0028:
            r9 = r9 & 255(0xff, float:3.57E-43)
            r7 = 128(0x80, float:1.794E-43)
            if (r9 >= r7) goto L_0x0035
            int r6 = r3 + 1
            char r9 = (char) r9
            r11[r3] = r9
            r3 = r6
            goto L_0x0046
        L_0x0035:
            r2 = 224(0xe0, float:3.14E-43)
            if (r9 >= r2) goto L_0x0041
            r2 = 191(0xbf, float:2.68E-43)
            if (r9 <= r2) goto L_0x0041
            r9 = r9 & 31
            char r9 = (char) r9
            goto L_0x0018
        L_0x0041:
            r9 = r9 & 15
            char r9 = (char) r9
            r2 = 2
            r4 = r9
        L_0x0046:
            r9 = r5
            goto L_0x0007
        L_0x0048:
            java.lang.String r9 = new java.lang.String
            r9.<init>(r11, r1, r3)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.asm.ClassReader.readUTF(int, int, char[]):java.lang.String");
    }
}

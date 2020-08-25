package com.alibaba.fastjson.asm;

public class Label {
    int inputStackTop;
    Label next;
    int outputStackMax;
    int position;
    private int referenceCount;
    private int[] srcAndRefPositions;
    int status;
    Label successor;

    /* access modifiers changed from: package-private */
    public void put(MethodWriter methodWriter, ByteVector byteVector, int i) {
        if ((this.status & 2) == 0) {
            addReference(i, byteVector.length);
            byteVector.putShort(-1);
            return;
        }
        byteVector.putShort(this.position - i);
    }

    private void addReference(int i, int i2) {
        if (this.srcAndRefPositions == null) {
            this.srcAndRefPositions = new int[6];
        }
        if (this.referenceCount >= this.srcAndRefPositions.length) {
            int[] iArr = new int[(this.srcAndRefPositions.length + 6)];
            System.arraycopy(this.srcAndRefPositions, 0, iArr, 0, this.srcAndRefPositions.length);
            this.srcAndRefPositions = iArr;
        }
        int[] iArr2 = this.srcAndRefPositions;
        int i3 = this.referenceCount;
        this.referenceCount = i3 + 1;
        iArr2[i3] = i;
        int[] iArr3 = this.srcAndRefPositions;
        int i4 = this.referenceCount;
        this.referenceCount = i4 + 1;
        iArr3[i4] = i2;
    }

    /* access modifiers changed from: package-private */
    public void resolve(MethodWriter methodWriter, int i, byte[] bArr) {
        this.status |= 2;
        this.position = i;
        int i2 = 0;
        while (i2 < this.referenceCount) {
            int i3 = i2 + 1;
            int i4 = this.srcAndRefPositions[i2];
            int i5 = this.srcAndRefPositions[i3];
            int i6 = i - i4;
            bArr[i5] = (byte) (i6 >>> 8);
            bArr[i5 + 1] = (byte) i6;
            i2 = i3 + 1;
        }
    }
}

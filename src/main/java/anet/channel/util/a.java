package anet.channel.util;

import java.io.IOException;
import java.io.InputStream;

/* compiled from: Taobao */
public class a extends InputStream {

    /* renamed from: a  reason: collision with root package name */
    private InputStream f337a = null;

    /* renamed from: b  reason: collision with root package name */
    private long f338b = 0;

    public a(InputStream inputStream) {
        if (inputStream != null) {
            this.f337a = inputStream;
            return;
        }
        throw new NullPointerException("input stream cannot be null");
    }

    public long a() {
        return this.f338b;
    }

    public int read() throws IOException {
        this.f338b++;
        return this.f337a.read();
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        int read = this.f337a.read(bArr, i, i2);
        if (read != -1) {
            this.f338b += (long) read;
        }
        return read;
    }
}

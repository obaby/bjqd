package aa;

import java.io.File;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

public class a<T> extends RequestBody {

    /* renamed from: a  reason: collision with root package name */
    private RequestBody f56a;
    /* access modifiers changed from: private */

    /* renamed from: b  reason: collision with root package name */
    public c<T> f57b;
    /* access modifiers changed from: private */

    /* renamed from: c  reason: collision with root package name */
    public File f58c;
    private BufferedSink d;

    public a(RequestBody requestBody, c<T> cVar, File file) {
        this.f56a = requestBody;
        this.f57b = cVar;
        this.f58c = file;
    }

    public long contentLength() throws IOException {
        return this.f56a.contentLength();
    }

    public MediaType contentType() {
        return this.f56a.contentType();
    }

    public void writeTo(BufferedSink bufferedSink) throws IOException {
        if (this.d == null) {
            this.d = Okio.buffer(a((Sink) bufferedSink));
        }
        this.f56a.writeTo(this.d);
        this.d.flush();
    }

    private Sink a(Sink sink) {
        return new ForwardingSink(sink) {

            /* renamed from: a  reason: collision with root package name */
            long f59a = 0;

            /* renamed from: b  reason: collision with root package name */
            long f60b = 0;

            public void write(Buffer buffer, long j) throws IOException {
                a.super.write(buffer, j);
                if (this.f60b == 0) {
                    this.f60b = a.this.contentLength();
                }
                this.f59a += j;
                a.this.f57b.a(a.this.f58c, this.f60b, this.f59a);
            }
        };
    }
}

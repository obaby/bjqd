package cn.xports.baselib.http;

import cn.xports.baselib.event.DownloadProgressEvent;
import cn.xports.baselib.util.RxBus;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;

public class FileResponseBody extends ResponseBody {
    private Response originalResponse;

    public FileResponseBody(Response response) {
        this.originalResponse = response;
    }

    public MediaType contentType() {
        return this.originalResponse.body().contentType();
    }

    public long contentLength() {
        return this.originalResponse.body().contentLength();
    }

    public BufferedSource source() {
        return Okio.buffer(new ForwardingSource(this.originalResponse.body().source()) {
            long bytesReaded = 0;

            public long read(Buffer buffer, long j) throws IOException {
                long read = FileResponseBody.super.read(buffer, j);
                this.bytesReaded += read == -1 ? 0 : read;
                RxBus.get().post(new DownloadProgressEvent(FileResponseBody.this.contentLength(), this.bytesReaded));
                return read;
            }
        });
    }
}

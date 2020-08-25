package com.alibaba.sdk.android.oss.network;

import android.support.v4.media.session.PlaybackStateCompat;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.internal.OSSRetryHandler;
import com.alibaba.sdk.android.oss.internal.RequestMessage;
import com.alibaba.sdk.android.oss.internal.ResponseParser;
import com.alibaba.sdk.android.oss.model.OSSResult;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Callable;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okio.BufferedSink;
import okio.Okio;
import okio.Source;

public class OSSRequestTask<T extends OSSResult> implements Callable<T> {
    private OkHttpClient client;
    /* access modifiers changed from: private */
    public ExecutionContext context;
    private int currentRetryCount = 0;
    private RequestMessage message;
    private ResponseParser<T> responseParser;
    private OSSRetryHandler retryHandler;

    class ProgressTouchableRequestBody extends RequestBody {
        private static final int SEGMENT_SIZE = 2048;
        private BufferedSink bufferedSink;
        private OSSProgressCallback callback;
        private long contentLength;
        private String contentType;
        private byte[] data;
        private File file;
        private InputStream inputStream;

        public ProgressTouchableRequestBody(File file2, String str, OSSProgressCallback oSSProgressCallback) {
            this.file = file2;
            this.contentType = str;
            this.contentLength = file2.length();
            this.callback = oSSProgressCallback;
        }

        public ProgressTouchableRequestBody(byte[] bArr, String str, OSSProgressCallback oSSProgressCallback) {
            this.data = bArr;
            this.contentType = str;
            this.contentLength = (long) bArr.length;
            this.callback = oSSProgressCallback;
        }

        public ProgressTouchableRequestBody(InputStream inputStream2, long j, String str, OSSProgressCallback oSSProgressCallback) {
            this.inputStream = inputStream2;
            this.contentType = str;
            this.contentLength = j;
            this.callback = oSSProgressCallback;
        }

        public MediaType contentType() {
            return MediaType.parse(this.contentType);
        }

        public long contentLength() throws IOException {
            return this.contentLength;
        }

        public void writeTo(BufferedSink bufferedSink2) throws IOException {
            Source source;
            if (this.file != null) {
                source = Okio.source(this.file);
            } else if (this.data != null) {
                source = Okio.source(new ByteArrayInputStream(this.data));
            } else {
                source = this.inputStream != null ? Okio.source(this.inputStream) : null;
            }
            long j = 0;
            while (j < this.contentLength) {
                long read = source.read(bufferedSink2.buffer(), Math.min(this.contentLength - j, PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH));
                if (read == -1) {
                    break;
                }
                j += read;
                bufferedSink2.flush();
                if (this.callback != null) {
                    this.callback.onProgress(OSSRequestTask.this.context.getRequest(), j, this.contentLength);
                }
            }
            if (source != null) {
                source.close();
            }
        }
    }

    public OSSRequestTask(RequestMessage requestMessage, ResponseParser responseParser2, ExecutionContext executionContext, int i) {
        this.responseParser = responseParser2;
        this.message = requestMessage;
        this.context = executionContext;
        this.client = executionContext.getClient();
        this.retryHandler = new OSSRetryHandler(i);
    }

    /* JADX WARNING: Removed duplicated region for block: B:59:0x025d  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x026d  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x0292 A[SYNTHETIC, Splitter:B:71:0x0292] */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x02ac  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x02ec  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x02fc  */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x031d  */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x0327  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public T call() throws java.lang.Exception {
        /*
            r14 = this;
            r0 = 1
            r1 = 0
            java.lang.String r2 = "[call] - "
            com.alibaba.sdk.android.oss.common.OSSLog.logD(r2)     // Catch:{ Exception -> 0x023b }
            com.alibaba.sdk.android.oss.network.ExecutionContext r2 = r14.context     // Catch:{ Exception -> 0x023b }
            com.alibaba.sdk.android.oss.model.OSSRequest r2 = r2.getRequest()     // Catch:{ Exception -> 0x023b }
            com.alibaba.sdk.android.oss.internal.RequestMessage r3 = r14.message     // Catch:{ Exception -> 0x023b }
            com.alibaba.sdk.android.oss.common.utils.OSSUtils.ensureRequestValid(r2, r3)     // Catch:{ Exception -> 0x023b }
            com.alibaba.sdk.android.oss.internal.RequestMessage r2 = r14.message     // Catch:{ Exception -> 0x023b }
            com.alibaba.sdk.android.oss.common.utils.OSSUtils.signRequest(r2)     // Catch:{ Exception -> 0x023b }
            com.alibaba.sdk.android.oss.network.ExecutionContext r2 = r14.context     // Catch:{ Exception -> 0x023b }
            com.alibaba.sdk.android.oss.network.CancellationHandler r2 = r2.getCancellationHandler()     // Catch:{ Exception -> 0x023b }
            boolean r2 = r2.isCancelled()     // Catch:{ Exception -> 0x023b }
            if (r2 != 0) goto L_0x0233
            okhttp3.Request$Builder r2 = new okhttp3.Request$Builder     // Catch:{ Exception -> 0x023b }
            r2.<init>()     // Catch:{ Exception -> 0x023b }
            com.alibaba.sdk.android.oss.internal.RequestMessage r3 = r14.message     // Catch:{ Exception -> 0x023b }
            java.lang.String r3 = r3.buildCanonicalURL()     // Catch:{ Exception -> 0x023b }
            okhttp3.Request$Builder r2 = r2.url(r3)     // Catch:{ Exception -> 0x023b }
            com.alibaba.sdk.android.oss.internal.RequestMessage r3 = r14.message     // Catch:{ Exception -> 0x023b }
            java.util.Map r3 = r3.getHeaders()     // Catch:{ Exception -> 0x023b }
            java.util.Set r3 = r3.keySet()     // Catch:{ Exception -> 0x023b }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ Exception -> 0x023b }
        L_0x0040:
            boolean r4 = r3.hasNext()     // Catch:{ Exception -> 0x023b }
            if (r4 == 0) goto L_0x005d
            java.lang.Object r4 = r3.next()     // Catch:{ Exception -> 0x023b }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ Exception -> 0x023b }
            com.alibaba.sdk.android.oss.internal.RequestMessage r5 = r14.message     // Catch:{ Exception -> 0x023b }
            java.util.Map r5 = r5.getHeaders()     // Catch:{ Exception -> 0x023b }
            java.lang.Object r5 = r5.get(r4)     // Catch:{ Exception -> 0x023b }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ Exception -> 0x023b }
            okhttp3.Request$Builder r2 = r2.addHeader(r4, r5)     // Catch:{ Exception -> 0x023b }
            goto L_0x0040
        L_0x005d:
            com.alibaba.sdk.android.oss.internal.RequestMessage r3 = r14.message     // Catch:{ Exception -> 0x023b }
            java.util.Map r3 = r3.getHeaders()     // Catch:{ Exception -> 0x023b }
            java.lang.String r4 = "Content-Type"
            java.lang.Object r3 = r3.get(r4)     // Catch:{ Exception -> 0x023b }
            r9 = r3
            java.lang.String r9 = (java.lang.String) r9     // Catch:{ Exception -> 0x023b }
            int[] r3 = com.alibaba.sdk.android.oss.network.OSSRequestTask.AnonymousClass1.$SwitchMap$com$alibaba$sdk$android$oss$common$HttpMethod     // Catch:{ Exception -> 0x023b }
            com.alibaba.sdk.android.oss.internal.RequestMessage r4 = r14.message     // Catch:{ Exception -> 0x023b }
            com.alibaba.sdk.android.oss.common.HttpMethod r4 = r4.getMethod()     // Catch:{ Exception -> 0x023b }
            int r4 = r4.ordinal()     // Catch:{ Exception -> 0x023b }
            r3 = r3[r4]     // Catch:{ Exception -> 0x023b }
            r11 = 0
            switch(r3) {
                case 1: goto L_0x0092;
                case 2: goto L_0x0092;
                case 3: goto L_0x008c;
                case 4: goto L_0x0086;
                case 5: goto L_0x0080;
                default: goto L_0x007e;
            }     // Catch:{ Exception -> 0x023b }
        L_0x007e:
            goto L_0x0135
        L_0x0080:
            okhttp3.Request$Builder r2 = r2.delete()     // Catch:{ Exception -> 0x023b }
            goto L_0x0135
        L_0x0086:
            okhttp3.Request$Builder r2 = r2.head()     // Catch:{ Exception -> 0x023b }
            goto L_0x0135
        L_0x008c:
            okhttp3.Request$Builder r2 = r2.get()     // Catch:{ Exception -> 0x023b }
            goto L_0x0135
        L_0x0092:
            if (r9 == 0) goto L_0x0096
            r3 = 1
            goto L_0x0097
        L_0x0096:
            r3 = 0
        L_0x0097:
            java.lang.String r4 = "Content type can't be null when upload!"
            com.alibaba.sdk.android.oss.common.utils.OSSUtils.assertTrue(r3, r4)     // Catch:{ Exception -> 0x023b }
            com.alibaba.sdk.android.oss.internal.RequestMessage r3 = r14.message     // Catch:{ Exception -> 0x023b }
            byte[] r3 = r3.getUploadData()     // Catch:{ Exception -> 0x023b }
            if (r3 == 0) goto L_0x00c4
            com.alibaba.sdk.android.oss.internal.RequestMessage r3 = r14.message     // Catch:{ Exception -> 0x023b }
            com.alibaba.sdk.android.oss.common.HttpMethod r3 = r3.getMethod()     // Catch:{ Exception -> 0x023b }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x023b }
            com.alibaba.sdk.android.oss.network.OSSRequestTask$ProgressTouchableRequestBody r4 = new com.alibaba.sdk.android.oss.network.OSSRequestTask$ProgressTouchableRequestBody     // Catch:{ Exception -> 0x023b }
            com.alibaba.sdk.android.oss.internal.RequestMessage r5 = r14.message     // Catch:{ Exception -> 0x023b }
            byte[] r5 = r5.getUploadData()     // Catch:{ Exception -> 0x023b }
            com.alibaba.sdk.android.oss.network.ExecutionContext r6 = r14.context     // Catch:{ Exception -> 0x023b }
            com.alibaba.sdk.android.oss.callback.OSSProgressCallback r6 = r6.getProgressCallback()     // Catch:{ Exception -> 0x023b }
            r4.<init>((byte[]) r5, (java.lang.String) r9, (com.alibaba.sdk.android.oss.callback.OSSProgressCallback) r6)     // Catch:{ Exception -> 0x023b }
            okhttp3.Request$Builder r2 = r2.method(r3, r4)     // Catch:{ Exception -> 0x023b }
            goto L_0x0135
        L_0x00c4:
            com.alibaba.sdk.android.oss.internal.RequestMessage r3 = r14.message     // Catch:{ Exception -> 0x023b }
            java.lang.String r3 = r3.getUploadFilePath()     // Catch:{ Exception -> 0x023b }
            if (r3 == 0) goto L_0x00f1
            com.alibaba.sdk.android.oss.internal.RequestMessage r3 = r14.message     // Catch:{ Exception -> 0x023b }
            com.alibaba.sdk.android.oss.common.HttpMethod r3 = r3.getMethod()     // Catch:{ Exception -> 0x023b }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x023b }
            com.alibaba.sdk.android.oss.network.OSSRequestTask$ProgressTouchableRequestBody r4 = new com.alibaba.sdk.android.oss.network.OSSRequestTask$ProgressTouchableRequestBody     // Catch:{ Exception -> 0x023b }
            java.io.File r5 = new java.io.File     // Catch:{ Exception -> 0x023b }
            com.alibaba.sdk.android.oss.internal.RequestMessage r6 = r14.message     // Catch:{ Exception -> 0x023b }
            java.lang.String r6 = r6.getUploadFilePath()     // Catch:{ Exception -> 0x023b }
            r5.<init>(r6)     // Catch:{ Exception -> 0x023b }
            com.alibaba.sdk.android.oss.network.ExecutionContext r6 = r14.context     // Catch:{ Exception -> 0x023b }
            com.alibaba.sdk.android.oss.callback.OSSProgressCallback r6 = r6.getProgressCallback()     // Catch:{ Exception -> 0x023b }
            r4.<init>((java.io.File) r5, (java.lang.String) r9, (com.alibaba.sdk.android.oss.callback.OSSProgressCallback) r6)     // Catch:{ Exception -> 0x023b }
            okhttp3.Request$Builder r2 = r2.method(r3, r4)     // Catch:{ Exception -> 0x023b }
            goto L_0x0135
        L_0x00f1:
            com.alibaba.sdk.android.oss.internal.RequestMessage r3 = r14.message     // Catch:{ Exception -> 0x023b }
            java.io.InputStream r3 = r3.getUploadInputStream()     // Catch:{ Exception -> 0x023b }
            if (r3 == 0) goto L_0x0121
            com.alibaba.sdk.android.oss.internal.RequestMessage r3 = r14.message     // Catch:{ Exception -> 0x023b }
            com.alibaba.sdk.android.oss.common.HttpMethod r3 = r3.getMethod()     // Catch:{ Exception -> 0x023b }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x023b }
            com.alibaba.sdk.android.oss.network.OSSRequestTask$ProgressTouchableRequestBody r12 = new com.alibaba.sdk.android.oss.network.OSSRequestTask$ProgressTouchableRequestBody     // Catch:{ Exception -> 0x023b }
            com.alibaba.sdk.android.oss.internal.RequestMessage r4 = r14.message     // Catch:{ Exception -> 0x023b }
            java.io.InputStream r6 = r4.getUploadInputStream()     // Catch:{ Exception -> 0x023b }
            com.alibaba.sdk.android.oss.internal.RequestMessage r4 = r14.message     // Catch:{ Exception -> 0x023b }
            long r7 = r4.getReadStreamLength()     // Catch:{ Exception -> 0x023b }
            com.alibaba.sdk.android.oss.network.ExecutionContext r4 = r14.context     // Catch:{ Exception -> 0x023b }
            com.alibaba.sdk.android.oss.callback.OSSProgressCallback r10 = r4.getProgressCallback()     // Catch:{ Exception -> 0x023b }
            r4 = r12
            r5 = r14
            r4.<init>(r6, r7, r9, r10)     // Catch:{ Exception -> 0x023b }
            okhttp3.Request$Builder r2 = r2.method(r3, r12)     // Catch:{ Exception -> 0x023b }
            goto L_0x0135
        L_0x0121:
            com.alibaba.sdk.android.oss.internal.RequestMessage r3 = r14.message     // Catch:{ Exception -> 0x023b }
            com.alibaba.sdk.android.oss.common.HttpMethod r3 = r3.getMethod()     // Catch:{ Exception -> 0x023b }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x023b }
            byte[] r4 = new byte[r11]     // Catch:{ Exception -> 0x023b }
            okhttp3.RequestBody r4 = okhttp3.RequestBody.create(r1, r4)     // Catch:{ Exception -> 0x023b }
            okhttp3.Request$Builder r2 = r2.method(r3, r4)     // Catch:{ Exception -> 0x023b }
        L_0x0135:
            okhttp3.Request r2 = r2.build()     // Catch:{ Exception -> 0x023b }
            boolean r3 = com.alibaba.sdk.android.oss.common.OSSLog.isEnableLog()     // Catch:{ Exception -> 0x022c }
            if (r3 == 0) goto L_0x019c
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x022c }
            r3.<init>()     // Catch:{ Exception -> 0x022c }
            java.lang.String r4 = "request url: "
            r3.append(r4)     // Catch:{ Exception -> 0x022c }
            okhttp3.HttpUrl r4 = r2.url()     // Catch:{ Exception -> 0x022c }
            r3.append(r4)     // Catch:{ Exception -> 0x022c }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x022c }
            com.alibaba.sdk.android.oss.common.OSSLog.logD(r3)     // Catch:{ Exception -> 0x022c }
            okhttp3.Headers r3 = r2.headers()     // Catch:{ Exception -> 0x022c }
            java.util.Map r3 = r3.toMultimap()     // Catch:{ Exception -> 0x022c }
            java.util.Set r4 = r3.keySet()     // Catch:{ Exception -> 0x022c }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ Exception -> 0x022c }
        L_0x0167:
            boolean r5 = r4.hasNext()     // Catch:{ Exception -> 0x022c }
            if (r5 == 0) goto L_0x019c
            java.lang.Object r5 = r4.next()     // Catch:{ Exception -> 0x022c }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ Exception -> 0x022c }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x022c }
            r6.<init>()     // Catch:{ Exception -> 0x022c }
            java.lang.String r7 = "requestHeader "
            r6.append(r7)     // Catch:{ Exception -> 0x022c }
            r6.append(r5)     // Catch:{ Exception -> 0x022c }
            java.lang.String r7 = ": "
            r6.append(r7)     // Catch:{ Exception -> 0x022c }
            java.lang.Object r5 = r3.get(r5)     // Catch:{ Exception -> 0x022c }
            java.util.List r5 = (java.util.List) r5     // Catch:{ Exception -> 0x022c }
            java.lang.Object r5 = r5.get(r11)     // Catch:{ Exception -> 0x022c }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ Exception -> 0x022c }
            r6.append(r5)     // Catch:{ Exception -> 0x022c }
            java.lang.String r5 = r6.toString()     // Catch:{ Exception -> 0x022c }
            com.alibaba.sdk.android.oss.common.OSSLog.logD(r5)     // Catch:{ Exception -> 0x022c }
            goto L_0x0167
        L_0x019c:
            okhttp3.OkHttpClient r3 = r14.client     // Catch:{ Exception -> 0x022c }
            okhttp3.Call r3 = r3.newCall(r2)     // Catch:{ Exception -> 0x022c }
            com.alibaba.sdk.android.oss.network.ExecutionContext r4 = r14.context     // Catch:{ Exception -> 0x0226 }
            com.alibaba.sdk.android.oss.network.CancellationHandler r4 = r4.getCancellationHandler()     // Catch:{ Exception -> 0x0226 }
            r4.setCall(r3)     // Catch:{ Exception -> 0x0226 }
            okhttp3.Response r4 = r3.execute()     // Catch:{ Exception -> 0x0226 }
            boolean r5 = com.alibaba.sdk.android.oss.common.OSSLog.isEnableLog()     // Catch:{ Exception -> 0x0220 }
            if (r5 == 0) goto L_0x021e
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0220 }
            r5.<init>()     // Catch:{ Exception -> 0x0220 }
            java.lang.String r6 = "response code: "
            r5.append(r6)     // Catch:{ Exception -> 0x0220 }
            int r6 = r4.code()     // Catch:{ Exception -> 0x0220 }
            r5.append(r6)     // Catch:{ Exception -> 0x0220 }
            java.lang.String r6 = " for url: "
            r5.append(r6)     // Catch:{ Exception -> 0x0220 }
            okhttp3.HttpUrl r6 = r2.url()     // Catch:{ Exception -> 0x0220 }
            r5.append(r6)     // Catch:{ Exception -> 0x0220 }
            java.lang.String r5 = r5.toString()     // Catch:{ Exception -> 0x0220 }
            com.alibaba.sdk.android.oss.common.OSSLog.logD(r5)     // Catch:{ Exception -> 0x0220 }
            okhttp3.Headers r5 = r4.headers()     // Catch:{ Exception -> 0x0220 }
            java.util.Map r5 = r5.toMultimap()     // Catch:{ Exception -> 0x0220 }
            java.util.Set r6 = r5.keySet()     // Catch:{ Exception -> 0x0220 }
            java.util.Iterator r6 = r6.iterator()     // Catch:{ Exception -> 0x0220 }
        L_0x01e9:
            boolean r7 = r6.hasNext()     // Catch:{ Exception -> 0x0220 }
            if (r7 == 0) goto L_0x021e
            java.lang.Object r7 = r6.next()     // Catch:{ Exception -> 0x0220 }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ Exception -> 0x0220 }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0220 }
            r8.<init>()     // Catch:{ Exception -> 0x0220 }
            java.lang.String r9 = "responseHeader "
            r8.append(r9)     // Catch:{ Exception -> 0x0220 }
            r8.append(r7)     // Catch:{ Exception -> 0x0220 }
            java.lang.String r9 = ": "
            r8.append(r9)     // Catch:{ Exception -> 0x0220 }
            java.lang.Object r7 = r5.get(r7)     // Catch:{ Exception -> 0x0220 }
            java.util.List r7 = (java.util.List) r7     // Catch:{ Exception -> 0x0220 }
            java.lang.Object r7 = r7.get(r11)     // Catch:{ Exception -> 0x0220 }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ Exception -> 0x0220 }
            r8.append(r7)     // Catch:{ Exception -> 0x0220 }
            java.lang.String r7 = r8.toString()     // Catch:{ Exception -> 0x0220 }
            com.alibaba.sdk.android.oss.common.OSSLog.logD(r7)     // Catch:{ Exception -> 0x0220 }
            goto L_0x01e9
        L_0x021e:
            r6 = r1
            goto L_0x026b
        L_0x0220:
            r5 = move-exception
            r13 = r3
            r3 = r2
            r2 = r5
            r5 = r13
            goto L_0x023f
        L_0x0226:
            r4 = move-exception
            r5 = r3
            r3 = r2
            r2 = r4
            r4 = r1
            goto L_0x023f
        L_0x022c:
            r3 = move-exception
            r4 = r1
            r5 = r4
            r13 = r3
            r3 = r2
            r2 = r13
            goto L_0x023f
        L_0x0233:
            java.io.InterruptedIOException r2 = new java.io.InterruptedIOException     // Catch:{ Exception -> 0x023b }
            java.lang.String r3 = "This task is cancelled!"
            r2.<init>(r3)     // Catch:{ Exception -> 0x023b }
            throw r2     // Catch:{ Exception -> 0x023b }
        L_0x023b:
            r2 = move-exception
            r3 = r1
            r4 = r3
            r5 = r4
        L_0x023f:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "Encounter local execpiton: "
            r6.append(r7)
            java.lang.String r7 = r2.toString()
            r6.append(r7)
            java.lang.String r6 = r6.toString()
            com.alibaba.sdk.android.oss.common.OSSLog.logE(r6)
            boolean r6 = com.alibaba.sdk.android.oss.common.OSSLog.isEnableLog()
            if (r6 == 0) goto L_0x0260
            r2.printStackTrace()
        L_0x0260:
            com.alibaba.sdk.android.oss.ClientException r6 = new com.alibaba.sdk.android.oss.ClientException
            java.lang.String r7 = r2.getMessage()
            r6.<init>(r7, r2)
            r2 = r3
            r3 = r5
        L_0x026b:
            if (r4 == 0) goto L_0x0280
            java.lang.String r5 = "Date"
            java.lang.String r5 = r4.header(r5)
            java.util.Date r5 = com.alibaba.sdk.android.oss.common.utils.DateUtil.parseRfc822Date(r5)     // Catch:{ Exception -> 0x027f }
            long r7 = r5.getTime()     // Catch:{ Exception -> 0x027f }
            com.alibaba.sdk.android.oss.common.utils.DateUtil.setCurrentServerTime(r7)     // Catch:{ Exception -> 0x027f }
            goto L_0x0280
        L_0x027f:
        L_0x0280:
            if (r6 != 0) goto L_0x02ac
            int r5 = r4.code()
            r7 = 203(0xcb, float:2.84E-43)
            if (r5 == r7) goto L_0x0292
            int r5 = r4.code()
            r7 = 300(0x12c, float:4.2E-43)
            if (r5 < r7) goto L_0x02ac
        L_0x0292:
            java.lang.String r2 = r2.method()     // Catch:{ IOException -> 0x02a1 }
            java.lang.String r5 = "HEAD"
            boolean r2 = r2.equals(r5)     // Catch:{ IOException -> 0x02a1 }
            com.alibaba.sdk.android.oss.ServiceException r6 = com.alibaba.sdk.android.oss.internal.ResponseParsers.parseResponseErrorXML(r4, r2)     // Catch:{ IOException -> 0x02a1 }
            goto L_0x02d8
        L_0x02a1:
            r2 = move-exception
            com.alibaba.sdk.android.oss.ClientException r6 = new com.alibaba.sdk.android.oss.ClientException
            java.lang.String r5 = r2.getMessage()
            r6.<init>(r5, r2)
            goto L_0x02d8
        L_0x02ac:
            if (r6 != 0) goto L_0x02d8
            com.alibaba.sdk.android.oss.internal.ResponseParser<T> r2 = r14.responseParser     // Catch:{ IOException -> 0x02ce }
            java.lang.Object r2 = r2.parse(r4)     // Catch:{ IOException -> 0x02ce }
            com.alibaba.sdk.android.oss.model.OSSResult r2 = (com.alibaba.sdk.android.oss.model.OSSResult) r2     // Catch:{ IOException -> 0x02ce }
            com.alibaba.sdk.android.oss.network.ExecutionContext r5 = r14.context     // Catch:{ IOException -> 0x02ce }
            com.alibaba.sdk.android.oss.callback.OSSCompletedCallback r5 = r5.getCompletedCallback()     // Catch:{ IOException -> 0x02ce }
            if (r5 == 0) goto L_0x02cd
            com.alibaba.sdk.android.oss.network.ExecutionContext r5 = r14.context     // Catch:{ Exception -> 0x02cd }
            com.alibaba.sdk.android.oss.callback.OSSCompletedCallback r5 = r5.getCompletedCallback()     // Catch:{ Exception -> 0x02cd }
            com.alibaba.sdk.android.oss.network.ExecutionContext r6 = r14.context     // Catch:{ Exception -> 0x02cd }
            com.alibaba.sdk.android.oss.model.OSSRequest r6 = r6.getRequest()     // Catch:{ Exception -> 0x02cd }
            r5.onSuccess(r6, r2)     // Catch:{ Exception -> 0x02cd }
        L_0x02cd:
            return r2
        L_0x02ce:
            r2 = move-exception
            com.alibaba.sdk.android.oss.ClientException r6 = new com.alibaba.sdk.android.oss.ClientException
            java.lang.String r5 = r2.getMessage()
            r6.<init>(r5, r2)
        L_0x02d8:
            if (r3 == 0) goto L_0x02e0
            boolean r2 = r3.isCanceled()
            if (r2 != 0) goto L_0x02ec
        L_0x02e0:
            com.alibaba.sdk.android.oss.network.ExecutionContext r2 = r14.context
            com.alibaba.sdk.android.oss.network.CancellationHandler r2 = r2.getCancellationHandler()
            boolean r2 = r2.isCancelled()
            if (r2 == 0) goto L_0x02fc
        L_0x02ec:
            com.alibaba.sdk.android.oss.ClientException r2 = new com.alibaba.sdk.android.oss.ClientException
            java.lang.String r3 = "Task is cancelled!"
            java.lang.Throwable r5 = r6.getCause()
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r0)
            r2.<init>(r3, r5, r6)
            goto L_0x02fd
        L_0x02fc:
            r2 = r6
        L_0x02fd:
            com.alibaba.sdk.android.oss.internal.OSSRetryHandler r3 = r14.retryHandler
            int r5 = r14.currentRetryCount
            com.alibaba.sdk.android.oss.internal.OSSRetryType r3 = r3.shouldRetry(r2, r5)
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "[run] - retry, retry type: "
            r5.append(r6)
            r5.append(r3)
            java.lang.String r5 = r5.toString()
            com.alibaba.sdk.android.oss.common.OSSLog.logE(r5)
            com.alibaba.sdk.android.oss.internal.OSSRetryType r5 = com.alibaba.sdk.android.oss.internal.OSSRetryType.OSSRetryTypeShouldRetry
            if (r3 != r5) goto L_0x0327
            int r1 = r14.currentRetryCount
            int r1 = r1 + r0
            r14.currentRetryCount = r1
            com.alibaba.sdk.android.oss.model.OSSResult r0 = r14.call()
            return r0
        L_0x0327:
            com.alibaba.sdk.android.oss.internal.OSSRetryType r5 = com.alibaba.sdk.android.oss.internal.OSSRetryType.OSSRetryTypeShouldFixedTimeSkewedAndRetry
            if (r3 != r5) goto L_0x0348
            if (r4 == 0) goto L_0x033e
            com.alibaba.sdk.android.oss.internal.RequestMessage r1 = r14.message
            java.util.Map r1 = r1.getHeaders()
            java.lang.String r2 = "Date"
            java.lang.String r3 = "Date"
            java.lang.String r3 = r4.header(r3)
            r1.put(r2, r3)
        L_0x033e:
            int r1 = r14.currentRetryCount
            int r1 = r1 + r0
            r14.currentRetryCount = r1
            com.alibaba.sdk.android.oss.model.OSSResult r0 = r14.call()
            return r0
        L_0x0348:
            boolean r0 = r2 instanceof com.alibaba.sdk.android.oss.ClientException
            if (r0 == 0) goto L_0x0367
            com.alibaba.sdk.android.oss.network.ExecutionContext r0 = r14.context
            com.alibaba.sdk.android.oss.callback.OSSCompletedCallback r0 = r0.getCompletedCallback()
            if (r0 == 0) goto L_0x0381
            com.alibaba.sdk.android.oss.network.ExecutionContext r0 = r14.context
            com.alibaba.sdk.android.oss.callback.OSSCompletedCallback r0 = r0.getCompletedCallback()
            com.alibaba.sdk.android.oss.network.ExecutionContext r3 = r14.context
            com.alibaba.sdk.android.oss.model.OSSRequest r3 = r3.getRequest()
            r4 = r2
            com.alibaba.sdk.android.oss.ClientException r4 = (com.alibaba.sdk.android.oss.ClientException) r4
            r0.onFailure(r3, r4, r1)
            goto L_0x0381
        L_0x0367:
            com.alibaba.sdk.android.oss.network.ExecutionContext r0 = r14.context
            com.alibaba.sdk.android.oss.callback.OSSCompletedCallback r0 = r0.getCompletedCallback()
            if (r0 == 0) goto L_0x0381
            com.alibaba.sdk.android.oss.network.ExecutionContext r0 = r14.context
            com.alibaba.sdk.android.oss.callback.OSSCompletedCallback r0 = r0.getCompletedCallback()
            com.alibaba.sdk.android.oss.network.ExecutionContext r3 = r14.context
            com.alibaba.sdk.android.oss.model.OSSRequest r3 = r3.getRequest()
            r4 = r2
            com.alibaba.sdk.android.oss.ServiceException r4 = (com.alibaba.sdk.android.oss.ServiceException) r4
            r0.onFailure(r3, r1, r4)
        L_0x0381:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.sdk.android.oss.network.OSSRequestTask.call():com.alibaba.sdk.android.oss.model.OSSResult");
    }
}

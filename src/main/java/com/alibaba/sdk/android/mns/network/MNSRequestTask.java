package com.alibaba.sdk.android.mns.network;

import android.support.v4.media.session.PlaybackStateCompat;
import com.alibaba.sdk.android.mns.callback.MNSProgressCallback;
import com.alibaba.sdk.android.mns.internal.RequestMessage;
import com.alibaba.sdk.android.mns.internal.ResponseParser;
import com.alibaba.sdk.android.mns.model.MNSResult;
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

public class MNSRequestTask<T extends MNSResult> implements Callable<T> {
    private OkHttpClient client;
    /* access modifiers changed from: private */
    public ExecutionContext context;
    private RequestMessage message;
    private ResponseParser<T> responseParser;

    class ProgressTouchableRequestBody extends RequestBody {
        private static final int SEGMENT_SIZE = 2048;
        private MNSProgressCallback callback;
        private long contentLength;
        private String contentType;
        private byte[] data;
        private File file;
        private InputStream inputStream;

        public ProgressTouchableRequestBody(File file2, String str, MNSProgressCallback mNSProgressCallback) {
            this.file = file2;
            this.contentType = str;
            this.contentLength = file2.length();
            this.callback = mNSProgressCallback;
        }

        public ProgressTouchableRequestBody(byte[] bArr, String str, MNSProgressCallback mNSProgressCallback) {
            this.data = bArr;
            this.contentType = str;
            this.contentLength = (long) bArr.length;
            this.callback = mNSProgressCallback;
        }

        public ProgressTouchableRequestBody(InputStream inputStream2, long j, String str, MNSProgressCallback mNSProgressCallback) {
            this.inputStream = inputStream2;
            this.contentType = str;
            this.contentLength = j;
            this.callback = mNSProgressCallback;
        }

        public MediaType contentType() {
            return MediaType.parse(this.contentType);
        }

        public long contentLength() throws IOException {
            return this.contentLength;
        }

        public void writeTo(BufferedSink bufferedSink) throws IOException {
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
                long read = source.read(bufferedSink.buffer(), Math.min(this.contentLength - j, PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH));
                if (read == -1) {
                    break;
                }
                j += read;
                bufferedSink.flush();
                if (this.callback != null) {
                    this.callback.onProgress(MNSRequestTask.this.context.getRequest(), j, this.contentLength);
                }
            }
            if (source != null) {
                source.close();
            }
        }
    }

    public MNSRequestTask(RequestMessage requestMessage, ResponseParser responseParser2, ExecutionContext executionContext) {
        this.responseParser = responseParser2;
        this.message = requestMessage;
        this.context = executionContext;
        this.client = executionContext.getClient();
    }

    /* JADX WARNING: Removed duplicated region for block: B:48:0x01ea  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x0208 A[SYNTHETIC, Splitter:B:55:0x0208] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0218  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x0258  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x0268  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x026d  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x0288  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public T call() throws java.lang.Exception {
        /*
            r10 = this;
            r0 = 1
            r1 = 0
            java.lang.String r2 = "[call] - "
            com.alibaba.sdk.android.mns.common.MNSLog.logD(r2)     // Catch:{ Exception -> 0x01c9 }
            com.alibaba.sdk.android.mns.network.ExecutionContext r2 = r10.context     // Catch:{ Exception -> 0x01c9 }
            com.alibaba.sdk.android.common.CancellationHandler r2 = r2.getCancellationHandler()     // Catch:{ Exception -> 0x01c9 }
            boolean r2 = r2.isCancelled()     // Catch:{ Exception -> 0x01c9 }
            if (r2 != 0) goto L_0x01c1
            okhttp3.Request$Builder r2 = new okhttp3.Request$Builder     // Catch:{ Exception -> 0x01c9 }
            r2.<init>()     // Catch:{ Exception -> 0x01c9 }
            com.alibaba.sdk.android.mns.internal.RequestMessage r3 = r10.message     // Catch:{ Exception -> 0x01c9 }
            java.lang.String r3 = r3.buildCanonicalURL()     // Catch:{ Exception -> 0x01c9 }
            com.alibaba.sdk.android.mns.internal.RequestMessage r4 = r10.message     // Catch:{ Exception -> 0x01c9 }
            com.alibaba.sdk.android.mns.common.MNSUtils.signRequest(r4)     // Catch:{ Exception -> 0x01c9 }
            okhttp3.Request$Builder r2 = r2.url(r3)     // Catch:{ Exception -> 0x01c9 }
            com.alibaba.sdk.android.mns.internal.RequestMessage r3 = r10.message     // Catch:{ Exception -> 0x01c9 }
            java.util.Map r3 = r3.getHeaders()     // Catch:{ Exception -> 0x01c9 }
            java.util.Set r3 = r3.keySet()     // Catch:{ Exception -> 0x01c9 }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ Exception -> 0x01c9 }
        L_0x0035:
            boolean r4 = r3.hasNext()     // Catch:{ Exception -> 0x01c9 }
            if (r4 == 0) goto L_0x0052
            java.lang.Object r4 = r3.next()     // Catch:{ Exception -> 0x01c9 }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ Exception -> 0x01c9 }
            com.alibaba.sdk.android.mns.internal.RequestMessage r5 = r10.message     // Catch:{ Exception -> 0x01c9 }
            java.util.Map r5 = r5.getHeaders()     // Catch:{ Exception -> 0x01c9 }
            java.lang.Object r5 = r5.get(r4)     // Catch:{ Exception -> 0x01c9 }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ Exception -> 0x01c9 }
            okhttp3.Request$Builder r2 = r2.addHeader(r4, r5)     // Catch:{ Exception -> 0x01c9 }
            goto L_0x0035
        L_0x0052:
            com.alibaba.sdk.android.mns.internal.RequestMessage r3 = r10.message     // Catch:{ Exception -> 0x01c9 }
            java.util.Map r3 = r3.getHeaders()     // Catch:{ Exception -> 0x01c9 }
            java.lang.String r4 = "Content-Type"
            java.lang.Object r3 = r3.get(r4)     // Catch:{ Exception -> 0x01c9 }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ Exception -> 0x01c9 }
            com.alibaba.sdk.android.mns.internal.RequestMessage r4 = r10.message     // Catch:{ Exception -> 0x01c9 }
            r4.getContent()     // Catch:{ Exception -> 0x01c9 }
            com.alibaba.sdk.android.mns.internal.RequestMessage r4 = r10.message     // Catch:{ Exception -> 0x01c9 }
            java.lang.String r4 = r4.getContent()     // Catch:{ Exception -> 0x01c9 }
            r5 = 0
            if (r4 == 0) goto L_0x009c
            if (r3 == 0) goto L_0x0072
            r4 = 1
            goto L_0x0073
        L_0x0072:
            r4 = 0
        L_0x0073:
            java.lang.String r6 = "Content type can't be null when send data!"
            com.alibaba.sdk.android.mns.common.MNSUtils.assertTrue(r4, r6)     // Catch:{ Exception -> 0x01c9 }
            com.alibaba.sdk.android.mns.internal.RequestMessage r4 = r10.message     // Catch:{ Exception -> 0x01c9 }
            com.alibaba.sdk.android.common.HttpMethod r4 = r4.getMethod()     // Catch:{ Exception -> 0x01c9 }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x01c9 }
            com.alibaba.sdk.android.mns.network.MNSRequestTask$ProgressTouchableRequestBody r6 = new com.alibaba.sdk.android.mns.network.MNSRequestTask$ProgressTouchableRequestBody     // Catch:{ Exception -> 0x01c9 }
            com.alibaba.sdk.android.mns.internal.RequestMessage r7 = r10.message     // Catch:{ Exception -> 0x01c9 }
            java.lang.String r7 = r7.getContent()     // Catch:{ Exception -> 0x01c9 }
            byte[] r7 = r7.getBytes()     // Catch:{ Exception -> 0x01c9 }
            com.alibaba.sdk.android.mns.network.ExecutionContext r8 = r10.context     // Catch:{ Exception -> 0x01c9 }
            com.alibaba.sdk.android.mns.callback.MNSProgressCallback r8 = r8.getProgressCallback()     // Catch:{ Exception -> 0x01c9 }
            r6.<init>((byte[]) r7, (java.lang.String) r3, (com.alibaba.sdk.android.mns.callback.MNSProgressCallback) r8)     // Catch:{ Exception -> 0x01c9 }
            okhttp3.Request$Builder r2 = r2.method(r4, r6)     // Catch:{ Exception -> 0x01c9 }
            goto L_0x00d1
        L_0x009c:
            int[] r3 = com.alibaba.sdk.android.mns.network.MNSRequestTask.AnonymousClass1.$SwitchMap$com$alibaba$sdk$android$common$HttpMethod     // Catch:{ Exception -> 0x01c9 }
            com.alibaba.sdk.android.mns.internal.RequestMessage r4 = r10.message     // Catch:{ Exception -> 0x01c9 }
            com.alibaba.sdk.android.common.HttpMethod r4 = r4.getMethod()     // Catch:{ Exception -> 0x01c9 }
            int r4 = r4.ordinal()     // Catch:{ Exception -> 0x01c9 }
            r3 = r3[r4]     // Catch:{ Exception -> 0x01c9 }
            switch(r3) {
                case 1: goto L_0x00bd;
                case 2: goto L_0x00b8;
                case 3: goto L_0x00b3;
                case 4: goto L_0x00ae;
                default: goto L_0x00ad;
            }     // Catch:{ Exception -> 0x01c9 }
        L_0x00ad:
            goto L_0x00d1
        L_0x00ae:
            okhttp3.Request$Builder r2 = r2.delete()     // Catch:{ Exception -> 0x01c9 }
            goto L_0x00d1
        L_0x00b3:
            okhttp3.Request$Builder r2 = r2.head()     // Catch:{ Exception -> 0x01c9 }
            goto L_0x00d1
        L_0x00b8:
            okhttp3.Request$Builder r2 = r2.get()     // Catch:{ Exception -> 0x01c9 }
            goto L_0x00d1
        L_0x00bd:
            com.alibaba.sdk.android.mns.internal.RequestMessage r3 = r10.message     // Catch:{ Exception -> 0x01c9 }
            com.alibaba.sdk.android.common.HttpMethod r3 = r3.getMethod()     // Catch:{ Exception -> 0x01c9 }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x01c9 }
            byte[] r4 = new byte[r5]     // Catch:{ Exception -> 0x01c9 }
            okhttp3.RequestBody r4 = okhttp3.RequestBody.create(r1, r4)     // Catch:{ Exception -> 0x01c9 }
            okhttp3.Request$Builder r2 = r2.method(r3, r4)     // Catch:{ Exception -> 0x01c9 }
        L_0x00d1:
            okhttp3.Request r2 = r2.build()     // Catch:{ Exception -> 0x01c9 }
            boolean r3 = com.alibaba.sdk.android.mns.common.MNSLog.isEnableLog()     // Catch:{ Exception -> 0x01c9 }
            if (r3 == 0) goto L_0x0138
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01c9 }
            r3.<init>()     // Catch:{ Exception -> 0x01c9 }
            java.lang.String r4 = "request url: "
            r3.append(r4)     // Catch:{ Exception -> 0x01c9 }
            okhttp3.HttpUrl r4 = r2.url()     // Catch:{ Exception -> 0x01c9 }
            r3.append(r4)     // Catch:{ Exception -> 0x01c9 }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x01c9 }
            com.alibaba.sdk.android.mns.common.MNSLog.logD(r3)     // Catch:{ Exception -> 0x01c9 }
            okhttp3.Headers r3 = r2.headers()     // Catch:{ Exception -> 0x01c9 }
            java.util.Map r3 = r3.toMultimap()     // Catch:{ Exception -> 0x01c9 }
            java.util.Set r4 = r3.keySet()     // Catch:{ Exception -> 0x01c9 }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ Exception -> 0x01c9 }
        L_0x0103:
            boolean r6 = r4.hasNext()     // Catch:{ Exception -> 0x01c9 }
            if (r6 == 0) goto L_0x0138
            java.lang.Object r6 = r4.next()     // Catch:{ Exception -> 0x01c9 }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ Exception -> 0x01c9 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01c9 }
            r7.<init>()     // Catch:{ Exception -> 0x01c9 }
            java.lang.String r8 = "requestHeader "
            r7.append(r8)     // Catch:{ Exception -> 0x01c9 }
            r7.append(r6)     // Catch:{ Exception -> 0x01c9 }
            java.lang.String r8 = ": "
            r7.append(r8)     // Catch:{ Exception -> 0x01c9 }
            java.lang.Object r6 = r3.get(r6)     // Catch:{ Exception -> 0x01c9 }
            java.util.List r6 = (java.util.List) r6     // Catch:{ Exception -> 0x01c9 }
            java.lang.Object r6 = r6.get(r5)     // Catch:{ Exception -> 0x01c9 }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ Exception -> 0x01c9 }
            r7.append(r6)     // Catch:{ Exception -> 0x01c9 }
            java.lang.String r6 = r7.toString()     // Catch:{ Exception -> 0x01c9 }
            com.alibaba.sdk.android.mns.common.MNSLog.logD(r6)     // Catch:{ Exception -> 0x01c9 }
            goto L_0x0103
        L_0x0138:
            okhttp3.OkHttpClient r3 = r10.client     // Catch:{ Exception -> 0x01c9 }
            okhttp3.Call r3 = r3.newCall(r2)     // Catch:{ Exception -> 0x01c9 }
            com.alibaba.sdk.android.mns.network.ExecutionContext r4 = r10.context     // Catch:{ Exception -> 0x01be }
            com.alibaba.sdk.android.common.CancellationHandler r4 = r4.getCancellationHandler()     // Catch:{ Exception -> 0x01be }
            r4.setCall(r3)     // Catch:{ Exception -> 0x01be }
            okhttp3.Response r4 = r3.execute()     // Catch:{ Exception -> 0x01be }
            boolean r6 = com.alibaba.sdk.android.mns.common.MNSLog.isEnableLog()     // Catch:{ Exception -> 0x01bc }
            if (r6 == 0) goto L_0x01ba
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01bc }
            r6.<init>()     // Catch:{ Exception -> 0x01bc }
            java.lang.String r7 = "response code: "
            r6.append(r7)     // Catch:{ Exception -> 0x01bc }
            int r7 = r4.code()     // Catch:{ Exception -> 0x01bc }
            r6.append(r7)     // Catch:{ Exception -> 0x01bc }
            java.lang.String r7 = " for url: "
            r6.append(r7)     // Catch:{ Exception -> 0x01bc }
            okhttp3.HttpUrl r2 = r2.url()     // Catch:{ Exception -> 0x01bc }
            r6.append(r2)     // Catch:{ Exception -> 0x01bc }
            java.lang.String r2 = r6.toString()     // Catch:{ Exception -> 0x01bc }
            com.alibaba.sdk.android.mns.common.MNSLog.logD(r2)     // Catch:{ Exception -> 0x01bc }
            okhttp3.Headers r2 = r4.headers()     // Catch:{ Exception -> 0x01bc }
            java.util.Map r2 = r2.toMultimap()     // Catch:{ Exception -> 0x01bc }
            java.util.Set r6 = r2.keySet()     // Catch:{ Exception -> 0x01bc }
            java.util.Iterator r6 = r6.iterator()     // Catch:{ Exception -> 0x01bc }
        L_0x0185:
            boolean r7 = r6.hasNext()     // Catch:{ Exception -> 0x01bc }
            if (r7 == 0) goto L_0x01ba
            java.lang.Object r7 = r6.next()     // Catch:{ Exception -> 0x01bc }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ Exception -> 0x01bc }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01bc }
            r8.<init>()     // Catch:{ Exception -> 0x01bc }
            java.lang.String r9 = "responseHeader "
            r8.append(r9)     // Catch:{ Exception -> 0x01bc }
            r8.append(r7)     // Catch:{ Exception -> 0x01bc }
            java.lang.String r9 = ": "
            r8.append(r9)     // Catch:{ Exception -> 0x01bc }
            java.lang.Object r7 = r2.get(r7)     // Catch:{ Exception -> 0x01bc }
            java.util.List r7 = (java.util.List) r7     // Catch:{ Exception -> 0x01bc }
            java.lang.Object r7 = r7.get(r5)     // Catch:{ Exception -> 0x01bc }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ Exception -> 0x01bc }
            r8.append(r7)     // Catch:{ Exception -> 0x01bc }
            java.lang.String r7 = r8.toString()     // Catch:{ Exception -> 0x01bc }
            com.alibaba.sdk.android.mns.common.MNSLog.logD(r7)     // Catch:{ Exception -> 0x01bc }
            goto L_0x0185
        L_0x01ba:
            r5 = r1
            goto L_0x01f6
        L_0x01bc:
            r2 = move-exception
            goto L_0x01cc
        L_0x01be:
            r2 = move-exception
            r4 = r1
            goto L_0x01cc
        L_0x01c1:
            java.io.InterruptedIOException r2 = new java.io.InterruptedIOException     // Catch:{ Exception -> 0x01c9 }
            java.lang.String r3 = "This task is cancelled!"
            r2.<init>(r3)     // Catch:{ Exception -> 0x01c9 }
            throw r2     // Catch:{ Exception -> 0x01c9 }
        L_0x01c9:
            r2 = move-exception
            r3 = r1
            r4 = r3
        L_0x01cc:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "Encounter local execpiton: "
            r5.append(r6)
            java.lang.String r6 = r2.toString()
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            com.alibaba.sdk.android.mns.common.MNSLog.logE(r5)
            boolean r5 = com.alibaba.sdk.android.mns.common.MNSLog.isEnableLog()
            if (r5 == 0) goto L_0x01ed
            r2.printStackTrace()
        L_0x01ed:
            com.alibaba.sdk.android.common.ClientException r5 = new com.alibaba.sdk.android.common.ClientException
            java.lang.String r6 = r2.getMessage()
            r5.<init>(r6, r2)
        L_0x01f6:
            if (r5 != 0) goto L_0x0218
            int r2 = r4.code()
            r6 = 203(0xcb, float:2.84E-43)
            if (r2 == r6) goto L_0x0208
            int r2 = r4.code()
            r6 = 300(0x12c, float:4.2E-43)
            if (r2 < r6) goto L_0x0218
        L_0x0208:
            com.alibaba.sdk.android.common.ServiceException r5 = com.alibaba.sdk.android.mns.internal.ResponseParsers.parseResponseErrorXML(r4)     // Catch:{ IOException -> 0x020d }
            goto L_0x0244
        L_0x020d:
            r2 = move-exception
            com.alibaba.sdk.android.common.ClientException r5 = new com.alibaba.sdk.android.common.ClientException
            java.lang.String r4 = r2.getMessage()
            r5.<init>(r4, r2)
            goto L_0x0244
        L_0x0218:
            if (r5 != 0) goto L_0x0244
            com.alibaba.sdk.android.mns.internal.ResponseParser<T> r2 = r10.responseParser     // Catch:{ IOException -> 0x023a }
            java.lang.Object r2 = r2.parse(r4)     // Catch:{ IOException -> 0x023a }
            com.alibaba.sdk.android.mns.model.MNSResult r2 = (com.alibaba.sdk.android.mns.model.MNSResult) r2     // Catch:{ IOException -> 0x023a }
            com.alibaba.sdk.android.mns.network.ExecutionContext r4 = r10.context     // Catch:{ IOException -> 0x023a }
            com.alibaba.sdk.android.mns.callback.MNSCompletedCallback r4 = r4.getCompletedCallback()     // Catch:{ IOException -> 0x023a }
            if (r4 == 0) goto L_0x0239
            com.alibaba.sdk.android.mns.network.ExecutionContext r4 = r10.context     // Catch:{ IOException -> 0x023a }
            com.alibaba.sdk.android.mns.callback.MNSCompletedCallback r4 = r4.getCompletedCallback()     // Catch:{ IOException -> 0x023a }
            com.alibaba.sdk.android.mns.network.ExecutionContext r5 = r10.context     // Catch:{ IOException -> 0x023a }
            com.alibaba.sdk.android.mns.model.MNSRequest r5 = r5.getRequest()     // Catch:{ IOException -> 0x023a }
            r4.onSuccess(r5, r2)     // Catch:{ IOException -> 0x023a }
        L_0x0239:
            return r2
        L_0x023a:
            r2 = move-exception
            com.alibaba.sdk.android.common.ClientException r5 = new com.alibaba.sdk.android.common.ClientException
            java.lang.String r4 = r2.getMessage()
            r5.<init>(r4, r2)
        L_0x0244:
            if (r3 == 0) goto L_0x024c
            boolean r2 = r3.isCanceled()
            if (r2 != 0) goto L_0x0258
        L_0x024c:
            com.alibaba.sdk.android.mns.network.ExecutionContext r2 = r10.context
            com.alibaba.sdk.android.common.CancellationHandler r2 = r2.getCancellationHandler()
            boolean r2 = r2.isCancelled()
            if (r2 == 0) goto L_0x0268
        L_0x0258:
            com.alibaba.sdk.android.common.ClientException r2 = new com.alibaba.sdk.android.common.ClientException
            java.lang.String r3 = "Task is cancelled!"
            java.lang.Throwable r4 = r5.getCause()
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            r2.<init>(r3, r4, r0)
            goto L_0x0269
        L_0x0268:
            r2 = r5
        L_0x0269:
            boolean r0 = r2 instanceof com.alibaba.sdk.android.common.ClientException
            if (r0 == 0) goto L_0x0288
            com.alibaba.sdk.android.mns.network.ExecutionContext r0 = r10.context
            com.alibaba.sdk.android.mns.callback.MNSCompletedCallback r0 = r0.getCompletedCallback()
            if (r0 == 0) goto L_0x02a2
            com.alibaba.sdk.android.mns.network.ExecutionContext r0 = r10.context
            com.alibaba.sdk.android.mns.callback.MNSCompletedCallback r0 = r0.getCompletedCallback()
            com.alibaba.sdk.android.mns.network.ExecutionContext r3 = r10.context
            com.alibaba.sdk.android.mns.model.MNSRequest r3 = r3.getRequest()
            r4 = r2
            com.alibaba.sdk.android.common.ClientException r4 = (com.alibaba.sdk.android.common.ClientException) r4
            r0.onFailure(r3, r4, r1)
            goto L_0x02a2
        L_0x0288:
            com.alibaba.sdk.android.mns.network.ExecutionContext r0 = r10.context
            com.alibaba.sdk.android.mns.callback.MNSCompletedCallback r0 = r0.getCompletedCallback()
            if (r0 == 0) goto L_0x02a2
            com.alibaba.sdk.android.mns.network.ExecutionContext r0 = r10.context
            com.alibaba.sdk.android.mns.callback.MNSCompletedCallback r0 = r0.getCompletedCallback()
            com.alibaba.sdk.android.mns.network.ExecutionContext r3 = r10.context
            com.alibaba.sdk.android.mns.model.MNSRequest r3 = r3.getRequest()
            r4 = r2
            com.alibaba.sdk.android.common.ServiceException r4 = (com.alibaba.sdk.android.common.ServiceException) r4
            r0.onFailure(r3, r1, r4)
        L_0x02a2:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.sdk.android.mns.network.MNSRequestTask.call():com.alibaba.sdk.android.mns.model.MNSResult");
    }
}

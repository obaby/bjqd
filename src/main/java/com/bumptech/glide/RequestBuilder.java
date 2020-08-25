package com.bumptech.glide;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.CheckResult;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RawRes;
import android.widget.ImageView;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.ErrorRequestCoordinator;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.RequestCoordinator;
import com.bumptech.glide.request.RequestFutureTarget;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.SingleRequest;
import com.bumptech.glide.request.ThumbnailRequestCoordinator;
import com.bumptech.glide.request.target.PreloadTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.target.ViewTarget;
import com.bumptech.glide.signature.ApplicationVersionSignature;
import com.bumptech.glide.util.Executors;
import com.bumptech.glide.util.Preconditions;
import com.bumptech.glide.util.Util;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;

public class RequestBuilder<TranscodeType> extends BaseRequestOptions<RequestBuilder<TranscodeType>> implements Cloneable, ModelTypes<RequestBuilder<TranscodeType>> {
    protected static final RequestOptions DOWNLOAD_ONLY_OPTIONS = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.DATA).priority(Priority.LOW).skipMemoryCache(true);
    private final Context context;
    @Nullable
    private RequestBuilder<TranscodeType> errorBuilder;
    private final Glide glide;
    private final GlideContext glideContext;
    private boolean isDefaultTransitionOptionsSet;
    private boolean isModelSet;
    private boolean isThumbnailBuilt;
    @Nullable
    private Object model;
    @Nullable
    private List<RequestListener<TranscodeType>> requestListeners;
    private final RequestManager requestManager;
    @Nullable
    private Float thumbSizeMultiplier;
    @Nullable
    private RequestBuilder<TranscodeType> thumbnailBuilder;
    private final Class<TranscodeType> transcodeClass;
    @NonNull
    private TransitionOptions<?, ? super TranscodeType> transitionOptions;

    @SuppressLint({"CheckResult"})
    protected RequestBuilder(@NonNull Glide glide2, RequestManager requestManager2, Class<TranscodeType> cls, Context context2) {
        this.isDefaultTransitionOptionsSet = true;
        this.glide = glide2;
        this.requestManager = requestManager2;
        this.transcodeClass = cls;
        this.context = context2;
        this.transitionOptions = requestManager2.getDefaultTransitionOptions(cls);
        this.glideContext = glide2.getGlideContext();
        initRequestListeners(requestManager2.getDefaultRequestListeners());
        apply((BaseRequestOptions<?>) requestManager2.getDefaultRequestOptions());
    }

    @SuppressLint({"CheckResult"})
    protected RequestBuilder(Class<TranscodeType> cls, RequestBuilder<?> requestBuilder) {
        this(requestBuilder.glide, requestBuilder.requestManager, cls, requestBuilder.context);
        this.model = requestBuilder.model;
        this.isModelSet = requestBuilder.isModelSet;
        apply((BaseRequestOptions<?>) requestBuilder);
    }

    @SuppressLint({"CheckResult"})
    private void initRequestListeners(List<RequestListener<Object>> list) {
        Iterator<RequestListener<Object>> it = list.iterator();
        while (it.hasNext()) {
            addListener((RequestListener) it.next());
        }
    }

    @CheckResult
    @NonNull
    public RequestBuilder<TranscodeType> apply(@NonNull BaseRequestOptions<?> baseRequestOptions) {
        Preconditions.checkNotNull(baseRequestOptions);
        return RequestBuilder.super.apply(baseRequestOptions);
    }

    @CheckResult
    @NonNull
    public RequestBuilder<TranscodeType> transition(@NonNull TransitionOptions<?, ? super TranscodeType> transitionOptions2) {
        this.transitionOptions = (TransitionOptions) Preconditions.checkNotNull(transitionOptions2);
        this.isDefaultTransitionOptionsSet = false;
        return this;
    }

    @CheckResult
    @NonNull
    public RequestBuilder<TranscodeType> listener(@Nullable RequestListener<TranscodeType> requestListener) {
        this.requestListeners = null;
        return addListener(requestListener);
    }

    @CheckResult
    @NonNull
    public RequestBuilder<TranscodeType> addListener(@Nullable RequestListener<TranscodeType> requestListener) {
        if (requestListener != null) {
            if (this.requestListeners == null) {
                this.requestListeners = new ArrayList();
            }
            this.requestListeners.add(requestListener);
        }
        return this;
    }

    @NonNull
    public RequestBuilder<TranscodeType> error(@Nullable RequestBuilder<TranscodeType> requestBuilder) {
        this.errorBuilder = requestBuilder;
        return this;
    }

    @CheckResult
    @NonNull
    public RequestBuilder<TranscodeType> thumbnail(@Nullable RequestBuilder<TranscodeType> requestBuilder) {
        this.thumbnailBuilder = requestBuilder;
        return this;
    }

    @CheckResult
    @NonNull
    public RequestBuilder<TranscodeType> thumbnail(@Nullable RequestBuilder<TranscodeType>... requestBuilderArr) {
        RequestBuilder<TranscodeType> requestBuilder = null;
        if (requestBuilderArr == null || requestBuilderArr.length == 0) {
            return thumbnail((RequestBuilder) null);
        }
        for (int length = requestBuilderArr.length - 1; length >= 0; length--) {
            RequestBuilder<TranscodeType> requestBuilder2 = requestBuilderArr[length];
            if (requestBuilder2 != null) {
                requestBuilder = requestBuilder == null ? requestBuilder2 : requestBuilder2.thumbnail(requestBuilder);
            }
        }
        return thumbnail(requestBuilder);
    }

    @CheckResult
    @NonNull
    public RequestBuilder<TranscodeType> thumbnail(float f) {
        if (f < 0.0f || f > 1.0f) {
            throw new IllegalArgumentException("sizeMultiplier must be between 0 and 1");
        }
        this.thumbSizeMultiplier = Float.valueOf(f);
        return this;
    }

    @CheckResult
    @NonNull
    public RequestBuilder<TranscodeType> load(@Nullable Object obj) {
        return loadGeneric(obj);
    }

    @NonNull
    private RequestBuilder<TranscodeType> loadGeneric(@Nullable Object obj) {
        this.model = obj;
        this.isModelSet = true;
        return this;
    }

    @CheckResult
    @NonNull
    public RequestBuilder<TranscodeType> load(@Nullable Bitmap bitmap) {
        return loadGeneric(bitmap).apply((BaseRequestOptions<?>) RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE));
    }

    @CheckResult
    @NonNull
    public RequestBuilder<TranscodeType> load(@Nullable Drawable drawable) {
        return loadGeneric(drawable).apply((BaseRequestOptions<?>) RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE));
    }

    @CheckResult
    @NonNull
    public RequestBuilder<TranscodeType> load(@Nullable String str) {
        return loadGeneric(str);
    }

    @CheckResult
    @NonNull
    public RequestBuilder<TranscodeType> load(@Nullable Uri uri) {
        return loadGeneric(uri);
    }

    @CheckResult
    @NonNull
    public RequestBuilder<TranscodeType> load(@Nullable File file) {
        return loadGeneric(file);
    }

    @CheckResult
    @NonNull
    public RequestBuilder<TranscodeType> load(@Nullable @RawRes @DrawableRes Integer num) {
        return loadGeneric(num).apply((BaseRequestOptions<?>) RequestOptions.signatureOf(ApplicationVersionSignature.obtain(this.context)));
    }

    @Deprecated
    @CheckResult
    public RequestBuilder<TranscodeType> load(@Nullable URL url) {
        return loadGeneric(url);
    }

    @CheckResult
    @NonNull
    public RequestBuilder<TranscodeType> load(@Nullable byte[] bArr) {
        RequestBuilder<TranscodeType> loadGeneric = loadGeneric(bArr);
        if (!loadGeneric.isDiskCacheStrategySet()) {
            loadGeneric = loadGeneric.apply((BaseRequestOptions<?>) RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE));
        }
        return !loadGeneric.isSkipMemoryCacheSet() ? loadGeneric.apply((BaseRequestOptions<?>) RequestOptions.skipMemoryCacheOf(true)) : loadGeneric;
    }

    @CheckResult
    public RequestBuilder<TranscodeType> clone() {
        RequestBuilder<TranscodeType> clone = RequestBuilder.super.clone();
        clone.transitionOptions = clone.transitionOptions.clone();
        return clone;
    }

    @NonNull
    public <Y extends Target<TranscodeType>> Y into(@NonNull Y y) {
        return into(y, (RequestListener) null, Executors.mainThreadExecutor());
    }

    /* access modifiers changed from: package-private */
    @NonNull
    public <Y extends Target<TranscodeType>> Y into(@NonNull Y y, @Nullable RequestListener<TranscodeType> requestListener, Executor executor) {
        return into(y, requestListener, this, executor);
    }

    private <Y extends Target<TranscodeType>> Y into(@NonNull Y y, @Nullable RequestListener<TranscodeType> requestListener, BaseRequestOptions<?> baseRequestOptions, Executor executor) {
        Preconditions.checkNotNull(y);
        if (this.isModelSet) {
            Request buildRequest = buildRequest(y, requestListener, baseRequestOptions, executor);
            Request request = y.getRequest();
            if (!buildRequest.isEquivalentTo(request) || isSkipMemoryCacheWithCompletePreviousRequest(baseRequestOptions, request)) {
                this.requestManager.clear((Target<?>) y);
                y.setRequest(buildRequest);
                this.requestManager.track(y, buildRequest);
                return y;
            }
            buildRequest.recycle();
            if (!((Request) Preconditions.checkNotNull(request)).isRunning()) {
                request.begin();
            }
            return y;
        }
        throw new IllegalArgumentException("You must call #load() before calling #into()");
    }

    private boolean isSkipMemoryCacheWithCompletePreviousRequest(BaseRequestOptions<?> baseRequestOptions, Request request) {
        return !baseRequestOptions.isMemoryCacheable() && request.isComplete();
    }

    @NonNull
    public ViewTarget<ImageView, TranscodeType> into(@NonNull ImageView imageView) {
        RequestBuilder requestBuilder;
        Util.assertMainThread();
        Preconditions.checkNotNull(imageView);
        if (!isTransformationSet() && isTransformationAllowed() && imageView.getScaleType() != null) {
            switch (AnonymousClass1.$SwitchMap$android$widget$ImageView$ScaleType[imageView.getScaleType().ordinal()]) {
                case 1:
                    requestBuilder = clone().optionalCenterCrop();
                    break;
                case 2:
                    requestBuilder = clone().optionalCenterInside();
                    break;
                case 3:
                case 4:
                case 5:
                    requestBuilder = clone().optionalFitCenter();
                    break;
                case 6:
                    requestBuilder = clone().optionalCenterInside();
                    break;
            }
        }
        requestBuilder = this;
        return (ViewTarget) into(this.glideContext.buildImageViewTarget(imageView, this.transcodeClass), (RequestListener) null, requestBuilder, Executors.mainThreadExecutor());
    }

    @Deprecated
    public FutureTarget<TranscodeType> into(int i, int i2) {
        return submit(i, i2);
    }

    @NonNull
    public FutureTarget<TranscodeType> submit() {
        return submit(Integer.MIN_VALUE, Integer.MIN_VALUE);
    }

    @NonNull
    public FutureTarget<TranscodeType> submit(int i, int i2) {
        RequestFutureTarget requestFutureTarget = new RequestFutureTarget(i, i2);
        return (FutureTarget) into(requestFutureTarget, requestFutureTarget, Executors.directExecutor());
    }

    @NonNull
    public Target<TranscodeType> preload(int i, int i2) {
        return into(PreloadTarget.obtain(this.requestManager, i, i2));
    }

    @NonNull
    public Target<TranscodeType> preload() {
        return preload(Integer.MIN_VALUE, Integer.MIN_VALUE);
    }

    @Deprecated
    @CheckResult
    public <Y extends Target<File>> Y downloadOnly(@NonNull Y y) {
        return getDownloadOnlyRequest().into(y);
    }

    @Deprecated
    @CheckResult
    public FutureTarget<File> downloadOnly(int i, int i2) {
        return getDownloadOnlyRequest().submit(i, i2);
    }

    /* access modifiers changed from: protected */
    @CheckResult
    @NonNull
    public RequestBuilder<File> getDownloadOnlyRequest() {
        return new RequestBuilder(File.class, this).apply((BaseRequestOptions<?>) DOWNLOAD_ONLY_OPTIONS);
    }

    /* renamed from: com.bumptech.glide.RequestBuilder$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$widget$ImageView$ScaleType = new int[ImageView.ScaleType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(24:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|17|18|19|20|21|22|23|24|25|26|27|28|29|30|(3:31|32|34)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(26:0|1|2|3|(2:5|6)|7|(2:9|10)|11|13|14|15|17|18|19|20|21|22|23|24|25|26|27|28|29|30|(3:31|32|34)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(28:0|1|2|3|(2:5|6)|7|(2:9|10)|11|13|14|15|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|34) */
        /* JADX WARNING: Can't wrap try/catch for region: R(29:0|1|2|3|5|6|7|(2:9|10)|11|13|14|15|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|34) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0048 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0052 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x005c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0066 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x0071 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x007c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x0087 */
        static {
            /*
                com.bumptech.glide.Priority[] r0 = com.bumptech.glide.Priority.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$bumptech$glide$Priority = r0
                r0 = 1
                int[] r1 = $SwitchMap$com$bumptech$glide$Priority     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.bumptech.glide.Priority r2 = com.bumptech.glide.Priority.LOW     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                r1 = 2
                int[] r2 = $SwitchMap$com$bumptech$glide$Priority     // Catch:{ NoSuchFieldError -> 0x001f }
                com.bumptech.glide.Priority r3 = com.bumptech.glide.Priority.NORMAL     // Catch:{ NoSuchFieldError -> 0x001f }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2[r3] = r1     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                r2 = 3
                int[] r3 = $SwitchMap$com$bumptech$glide$Priority     // Catch:{ NoSuchFieldError -> 0x002a }
                com.bumptech.glide.Priority r4 = com.bumptech.glide.Priority.HIGH     // Catch:{ NoSuchFieldError -> 0x002a }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                r3 = 4
                int[] r4 = $SwitchMap$com$bumptech$glide$Priority     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.bumptech.glide.Priority r5 = com.bumptech.glide.Priority.IMMEDIATE     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r4[r5] = r3     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                android.widget.ImageView$ScaleType[] r4 = android.widget.ImageView.ScaleType.values()
                int r4 = r4.length
                int[] r4 = new int[r4]
                $SwitchMap$android$widget$ImageView$ScaleType = r4
                int[] r4 = $SwitchMap$android$widget$ImageView$ScaleType     // Catch:{ NoSuchFieldError -> 0x0048 }
                android.widget.ImageView$ScaleType r5 = android.widget.ImageView.ScaleType.CENTER_CROP     // Catch:{ NoSuchFieldError -> 0x0048 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0048 }
                r4[r5] = r0     // Catch:{ NoSuchFieldError -> 0x0048 }
            L_0x0048:
                int[] r0 = $SwitchMap$android$widget$ImageView$ScaleType     // Catch:{ NoSuchFieldError -> 0x0052 }
                android.widget.ImageView$ScaleType r4 = android.widget.ImageView.ScaleType.CENTER_INSIDE     // Catch:{ NoSuchFieldError -> 0x0052 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0052 }
                r0[r4] = r1     // Catch:{ NoSuchFieldError -> 0x0052 }
            L_0x0052:
                int[] r0 = $SwitchMap$android$widget$ImageView$ScaleType     // Catch:{ NoSuchFieldError -> 0x005c }
                android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.FIT_CENTER     // Catch:{ NoSuchFieldError -> 0x005c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x005c }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x005c }
            L_0x005c:
                int[] r0 = $SwitchMap$android$widget$ImageView$ScaleType     // Catch:{ NoSuchFieldError -> 0x0066 }
                android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.FIT_START     // Catch:{ NoSuchFieldError -> 0x0066 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0066 }
                r0[r1] = r3     // Catch:{ NoSuchFieldError -> 0x0066 }
            L_0x0066:
                int[] r0 = $SwitchMap$android$widget$ImageView$ScaleType     // Catch:{ NoSuchFieldError -> 0x0071 }
                android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.FIT_END     // Catch:{ NoSuchFieldError -> 0x0071 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0071 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0071 }
            L_0x0071:
                int[] r0 = $SwitchMap$android$widget$ImageView$ScaleType     // Catch:{ NoSuchFieldError -> 0x007c }
                android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.FIT_XY     // Catch:{ NoSuchFieldError -> 0x007c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x007c }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x007c }
            L_0x007c:
                int[] r0 = $SwitchMap$android$widget$ImageView$ScaleType     // Catch:{ NoSuchFieldError -> 0x0087 }
                android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.CENTER     // Catch:{ NoSuchFieldError -> 0x0087 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0087 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0087 }
            L_0x0087:
                int[] r0 = $SwitchMap$android$widget$ImageView$ScaleType     // Catch:{ NoSuchFieldError -> 0x0093 }
                android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.MATRIX     // Catch:{ NoSuchFieldError -> 0x0093 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0093 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0093 }
            L_0x0093:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.RequestBuilder.AnonymousClass1.<clinit>():void");
        }
    }

    @NonNull
    private Priority getThumbnailPriority(@NonNull Priority priority) {
        switch (priority) {
            case LOW:
                return Priority.NORMAL;
            case NORMAL:
                return Priority.HIGH;
            case HIGH:
            case IMMEDIATE:
                return Priority.IMMEDIATE;
            default:
                throw new IllegalArgumentException("unknown priority: " + getPriority());
        }
    }

    private Request buildRequest(Target<TranscodeType> target, @Nullable RequestListener<TranscodeType> requestListener, BaseRequestOptions<?> baseRequestOptions, Executor executor) {
        return buildRequestRecursive(target, requestListener, (RequestCoordinator) null, this.transitionOptions, baseRequestOptions.getPriority(), baseRequestOptions.getOverrideWidth(), baseRequestOptions.getOverrideHeight(), baseRequestOptions, executor);
    }

    private Request buildRequestRecursive(Target<TranscodeType> target, @Nullable RequestListener<TranscodeType> requestListener, @Nullable RequestCoordinator requestCoordinator, TransitionOptions<?, ? super TranscodeType> transitionOptions2, Priority priority, int i, int i2, BaseRequestOptions<?> baseRequestOptions, Executor executor) {
        RequestCoordinator requestCoordinator2;
        RequestCoordinator requestCoordinator3;
        if (this.errorBuilder != null) {
            requestCoordinator3 = new ErrorRequestCoordinator(requestCoordinator);
            requestCoordinator2 = requestCoordinator3;
        } else {
            requestCoordinator2 = null;
            requestCoordinator3 = requestCoordinator;
        }
        Request buildThumbnailRequestRecursive = buildThumbnailRequestRecursive(target, requestListener, requestCoordinator3, transitionOptions2, priority, i, i2, baseRequestOptions, executor);
        if (requestCoordinator2 == null) {
            return buildThumbnailRequestRecursive;
        }
        int overrideWidth = this.errorBuilder.getOverrideWidth();
        int overrideHeight = this.errorBuilder.getOverrideHeight();
        if (Util.isValidDimensions(i, i2) && !this.errorBuilder.isValidOverride()) {
            overrideWidth = baseRequestOptions.getOverrideWidth();
            overrideHeight = baseRequestOptions.getOverrideHeight();
        }
        RequestCoordinator requestCoordinator4 = requestCoordinator2;
        requestCoordinator4.setRequests(buildThumbnailRequestRecursive, this.errorBuilder.buildRequestRecursive(target, requestListener, requestCoordinator2, this.errorBuilder.transitionOptions, this.errorBuilder.getPriority(), overrideWidth, overrideHeight, this.errorBuilder, executor));
        return requestCoordinator4;
    }

    private Request buildThumbnailRequestRecursive(Target<TranscodeType> target, RequestListener<TranscodeType> requestListener, @Nullable RequestCoordinator requestCoordinator, TransitionOptions<?, ? super TranscodeType> transitionOptions2, Priority priority, int i, int i2, BaseRequestOptions<?> baseRequestOptions, Executor executor) {
        RequestCoordinator requestCoordinator2 = requestCoordinator;
        Priority priority2 = priority;
        if (this.thumbnailBuilder != null) {
            if (!this.isThumbnailBuilt) {
                TransitionOptions<?, ? super TranscodeType> transitionOptions3 = this.thumbnailBuilder.isDefaultTransitionOptionsSet ? transitionOptions2 : this.thumbnailBuilder.transitionOptions;
                Priority priority3 = this.thumbnailBuilder.isPrioritySet() ? this.thumbnailBuilder.getPriority() : getThumbnailPriority(priority2);
                int overrideWidth = this.thumbnailBuilder.getOverrideWidth();
                int overrideHeight = this.thumbnailBuilder.getOverrideHeight();
                if (Util.isValidDimensions(i, i2) && !this.thumbnailBuilder.isValidOverride()) {
                    overrideWidth = baseRequestOptions.getOverrideWidth();
                    overrideHeight = baseRequestOptions.getOverrideHeight();
                }
                ThumbnailRequestCoordinator thumbnailRequestCoordinator = new ThumbnailRequestCoordinator(requestCoordinator2);
                Request obtainRequest = obtainRequest(target, requestListener, baseRequestOptions, thumbnailRequestCoordinator, transitionOptions2, priority, i, i2, executor);
                this.isThumbnailBuilt = true;
                ThumbnailRequestCoordinator thumbnailRequestCoordinator2 = thumbnailRequestCoordinator;
                Request buildRequestRecursive = this.thumbnailBuilder.buildRequestRecursive(target, requestListener, thumbnailRequestCoordinator, transitionOptions3, priority3, overrideWidth, overrideHeight, this.thumbnailBuilder, executor);
                this.isThumbnailBuilt = false;
                thumbnailRequestCoordinator2.setRequests(obtainRequest, buildRequestRecursive);
                return thumbnailRequestCoordinator2;
            }
            throw new IllegalStateException("You cannot use a request as both the main request and a thumbnail, consider using clone() on the request(s) passed to thumbnail()");
        } else if (this.thumbSizeMultiplier == null) {
            return obtainRequest(target, requestListener, baseRequestOptions, requestCoordinator, transitionOptions2, priority, i, i2, executor);
        } else {
            ThumbnailRequestCoordinator thumbnailRequestCoordinator3 = new ThumbnailRequestCoordinator(requestCoordinator2);
            RequestListener<TranscodeType> requestListener2 = requestListener;
            ThumbnailRequestCoordinator thumbnailRequestCoordinator4 = thumbnailRequestCoordinator3;
            TransitionOptions<?, ? super TranscodeType> transitionOptions4 = transitionOptions2;
            int i3 = i;
            int i4 = i2;
            Executor executor2 = executor;
            thumbnailRequestCoordinator3.setRequests(obtainRequest(target, requestListener2, baseRequestOptions, thumbnailRequestCoordinator4, transitionOptions4, priority, i3, i4, executor2), obtainRequest(target, requestListener2, baseRequestOptions.clone().sizeMultiplier(this.thumbSizeMultiplier.floatValue()), thumbnailRequestCoordinator4, transitionOptions4, getThumbnailPriority(priority2), i3, i4, executor2));
            return thumbnailRequestCoordinator3;
        }
    }

    private Request obtainRequest(Target<TranscodeType> target, RequestListener<TranscodeType> requestListener, BaseRequestOptions<?> baseRequestOptions, RequestCoordinator requestCoordinator, TransitionOptions<?, ? super TranscodeType> transitionOptions2, Priority priority, int i, int i2, Executor executor) {
        return SingleRequest.obtain(this.context, this.glideContext, this.model, this.transcodeClass, baseRequestOptions, i, i2, priority, target, requestListener, this.requestListeners, requestCoordinator, this.glideContext.getEngine(), transitionOptions2.getTransitionFactory(), executor);
    }
}

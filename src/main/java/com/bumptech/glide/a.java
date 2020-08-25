package com.bumptech.glide;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import com.linewell.licence.glide.progress.ProgressAppGlideModule;
import java.util.Collections;
import java.util.Set;

final class a extends GeneratedAppGlideModule {

    /* renamed from: a  reason: collision with root package name */
    private final ProgressAppGlideModule f831a = new ProgressAppGlideModule();

    a() {
        if (Log.isLoggable("Glide", 3)) {
            Log.d("Glide", "Discovered AppGlideModule from annotation: com.linewell.licence.glide.progress.ProgressAppGlideModule");
        }
    }

    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder glideBuilder) {
        this.f831a.applyOptions(context, glideBuilder);
    }

    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
        this.f831a.registerComponents(context, glide, registry);
    }

    public boolean isManifestParsingEnabled() {
        return this.f831a.isManifestParsingEnabled();
    }

    @NonNull
    public Set<Class<?>> getExcludedModuleClasses() {
        return Collections.emptySet();
    }

    /* access modifiers changed from: package-private */
    @NonNull
    /* renamed from: a */
    public b getRequestManagerFactory() {
        return new b();
    }
}

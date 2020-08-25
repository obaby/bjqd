package com.bumptech.glide;

import android.content.Context;
import android.support.annotation.NonNull;
import com.bumptech.glide.manager.Lifecycle;
import com.bumptech.glide.manager.RequestManagerRetriever;
import com.bumptech.glide.manager.RequestManagerTreeNode;
import com.linewell.licence.glide.progress.d;

final class b implements RequestManagerRetriever.RequestManagerFactory {
    b() {
    }

    @NonNull
    public RequestManager build(@NonNull Glide glide, @NonNull Lifecycle lifecycle, @NonNull RequestManagerTreeNode requestManagerTreeNode, @NonNull Context context) {
        return new d(glide, lifecycle, requestManagerTreeNode, context);
    }
}

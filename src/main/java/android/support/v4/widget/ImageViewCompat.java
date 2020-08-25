package android.support.v4.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.widget.ImageView;

public class ImageViewCompat {
    static final ImageViewCompatImpl IMPL;

    interface ImageViewCompatImpl {
        ColorStateList getImageTintList(ImageView imageView);

        PorterDuff.Mode getImageTintMode(ImageView imageView);

        void setImageTintList(ImageView imageView, ColorStateList colorStateList);

        void setImageTintMode(ImageView imageView, PorterDuff.Mode mode);
    }

    static class BaseViewCompatImpl implements ImageViewCompatImpl {
        BaseViewCompatImpl() {
        }

        public ColorStateList getImageTintList(ImageView imageView) {
            if (imageView instanceof TintableImageSourceView) {
                return ((TintableImageSourceView) imageView).getSupportImageTintList();
            }
            return null;
        }

        public void setImageTintList(ImageView imageView, ColorStateList colorStateList) {
            if (imageView instanceof TintableImageSourceView) {
                ((TintableImageSourceView) imageView).setSupportImageTintList(colorStateList);
            }
        }

        public void setImageTintMode(ImageView imageView, PorterDuff.Mode mode) {
            if (imageView instanceof TintableImageSourceView) {
                ((TintableImageSourceView) imageView).setSupportImageTintMode(mode);
            }
        }

        public PorterDuff.Mode getImageTintMode(ImageView imageView) {
            if (imageView instanceof TintableImageSourceView) {
                return ((TintableImageSourceView) imageView).getSupportImageTintMode();
            }
            return null;
        }
    }

    @RequiresApi(21)
    static class LollipopViewCompatImpl extends BaseViewCompatImpl {
        LollipopViewCompatImpl() {
        }

        public ColorStateList getImageTintList(ImageView imageView) {
            return imageView.getImageTintList();
        }

        public void setImageTintList(ImageView imageView, ColorStateList colorStateList) {
            imageView.setImageTintList(colorStateList);
            if (Build.VERSION.SDK_INT == 21) {
                Drawable drawable = imageView.getDrawable();
                boolean z = (imageView.getImageTintList() == null || imageView.getImageTintMode() == null) ? false : true;
                if (drawable != null && z) {
                    if (drawable.isStateful()) {
                        drawable.setState(imageView.getDrawableState());
                    }
                    imageView.setImageDrawable(drawable);
                }
            }
        }

        public void setImageTintMode(ImageView imageView, PorterDuff.Mode mode) {
            imageView.setImageTintMode(mode);
            if (Build.VERSION.SDK_INT == 21) {
                Drawable drawable = imageView.getDrawable();
                boolean z = (imageView.getImageTintList() == null || imageView.getImageTintMode() == null) ? false : true;
                if (drawable != null && z) {
                    if (drawable.isStateful()) {
                        drawable.setState(imageView.getDrawableState());
                    }
                    imageView.setImageDrawable(drawable);
                }
            }
        }

        public PorterDuff.Mode getImageTintMode(ImageView imageView) {
            return imageView.getImageTintMode();
        }
    }

    static {
        if (Build.VERSION.SDK_INT >= 21) {
            IMPL = new LollipopViewCompatImpl();
        } else {
            IMPL = new BaseViewCompatImpl();
        }
    }

    @Nullable
    public static ColorStateList getImageTintList(@NonNull ImageView imageView) {
        return IMPL.getImageTintList(imageView);
    }

    public static void setImageTintList(@NonNull ImageView imageView, @Nullable ColorStateList colorStateList) {
        IMPL.setImageTintList(imageView, colorStateList);
    }

    @Nullable
    public static PorterDuff.Mode getImageTintMode(@NonNull ImageView imageView) {
        return IMPL.getImageTintMode(imageView);
    }

    public static void setImageTintMode(@NonNull ImageView imageView, @Nullable PorterDuff.Mode mode) {
        IMPL.setImageTintMode(imageView, mode);
    }

    private ImageViewCompat() {
    }
}

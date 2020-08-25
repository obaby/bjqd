package android.support.transition;

import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.ViewGroup;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@RequiresApi(18)
class ViewGroupUtilsApi18 extends ViewGroupUtilsApi14 {
    private static final String TAG = "ViewUtilsApi18";
    private static Method sSuppressLayoutMethod;
    private static boolean sSuppressLayoutMethodFetched;

    ViewGroupUtilsApi18() {
    }

    public ViewGroupOverlayImpl getOverlay(@NonNull ViewGroup viewGroup) {
        return new ViewGroupOverlayApi18(viewGroup);
    }

    public void suppressLayout(@NonNull ViewGroup viewGroup, boolean z) {
        fetchSuppressLayoutMethod();
        if (sSuppressLayoutMethod != null) {
            try {
                sSuppressLayoutMethod.invoke(viewGroup, new Object[]{Boolean.valueOf(z)});
            } catch (IllegalAccessException e) {
                Log.i(TAG, "Failed to invoke suppressLayout method", e);
            } catch (InvocationTargetException e2) {
                Log.i(TAG, "Error invoking suppressLayout method", e2);
            }
        }
    }

    private void fetchSuppressLayoutMethod() {
        if (!sSuppressLayoutMethodFetched) {
            Class<ViewGroup> cls = ViewGroup.class;
            try {
                sSuppressLayoutMethod = cls.getDeclaredMethod("suppressLayout", new Class[]{Boolean.TYPE});
                sSuppressLayoutMethod.setAccessible(true);
            } catch (NoSuchMethodException e) {
                Log.i(TAG, "Failed to retrieve suppressLayout method", e);
            }
            sSuppressLayoutMethodFetched = true;
        }
    }
}

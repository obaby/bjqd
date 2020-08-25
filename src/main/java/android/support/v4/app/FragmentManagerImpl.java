package android.support.v4.app;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.arch.lifecycle.ViewModelStore;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.util.ArraySet;
import android.support.v4.util.DebugUtils;
import android.support.v4.util.LogWriter;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.Transformation;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: FragmentManager */
final class FragmentManagerImpl extends FragmentManager implements LayoutInflater.Factory2 {
    static final Interpolator ACCELERATE_CUBIC = new AccelerateInterpolator(1.5f);
    static final Interpolator ACCELERATE_QUINT = new AccelerateInterpolator(2.5f);
    static final int ANIM_DUR = 220;
    public static final int ANIM_STYLE_CLOSE_ENTER = 3;
    public static final int ANIM_STYLE_CLOSE_EXIT = 4;
    public static final int ANIM_STYLE_FADE_ENTER = 5;
    public static final int ANIM_STYLE_FADE_EXIT = 6;
    public static final int ANIM_STYLE_OPEN_ENTER = 1;
    public static final int ANIM_STYLE_OPEN_EXIT = 2;
    static boolean DEBUG = false;
    static final Interpolator DECELERATE_CUBIC = new DecelerateInterpolator(1.5f);
    static final Interpolator DECELERATE_QUINT = new DecelerateInterpolator(2.5f);
    static final String TAG = "FragmentManager";
    static final String TARGET_REQUEST_CODE_STATE_TAG = "android:target_req_state";
    static final String TARGET_STATE_TAG = "android:target_state";
    static final String USER_VISIBLE_HINT_TAG = "android:user_visible_hint";
    static final String VIEW_STATE_TAG = "android:view_state";
    static Field sAnimationListenerField;
    SparseArray<Fragment> mActive;
    final ArrayList<Fragment> mAdded = new ArrayList<>();
    ArrayList<Integer> mAvailBackStackIndices;
    ArrayList<BackStackRecord> mBackStack;
    ArrayList<FragmentManager.OnBackStackChangedListener> mBackStackChangeListeners;
    ArrayList<BackStackRecord> mBackStackIndices;
    FragmentContainer mContainer;
    ArrayList<Fragment> mCreatedMenus;
    int mCurState = 0;
    boolean mDestroyed;
    Runnable mExecCommit = new Runnable() {
        public void run() {
            FragmentManagerImpl.this.execPendingActions();
        }
    };
    boolean mExecutingActions;
    boolean mHavePendingDeferredStart;
    FragmentHostCallback mHost;
    private final CopyOnWriteArrayList<Pair<FragmentManager.FragmentLifecycleCallbacks, Boolean>> mLifecycleCallbacks = new CopyOnWriteArrayList<>();
    boolean mNeedMenuInvalidate;
    int mNextFragmentIndex = 0;
    String mNoTransactionsBecause;
    Fragment mParent;
    ArrayList<OpGenerator> mPendingActions;
    ArrayList<StartEnterTransitionListener> mPostponedTransactions;
    Fragment mPrimaryNav;
    FragmentManagerNonConfig mSavedNonConfig;
    SparseArray<Parcelable> mStateArray = null;
    Bundle mStateBundle = null;
    boolean mStateSaved;
    boolean mStopped;
    ArrayList<Fragment> mTmpAddedFragments;
    ArrayList<Boolean> mTmpIsPop;
    ArrayList<BackStackRecord> mTmpRecords;

    /* compiled from: FragmentManager */
    interface OpGenerator {
        boolean generateOps(ArrayList<BackStackRecord> arrayList, ArrayList<Boolean> arrayList2);
    }

    public static int reverseTransit(int i) {
        if (i == 4097) {
            return 8194;
        }
        if (i == 4099) {
            return FragmentTransaction.TRANSIT_FRAGMENT_FADE;
        }
        if (i != 8194) {
            return 0;
        }
        return FragmentTransaction.TRANSIT_FRAGMENT_OPEN;
    }

    public static int transitToStyleIndex(int i, boolean z) {
        if (i == 4097) {
            return z ? 1 : 2;
        }
        if (i == 4099) {
            return z ? 5 : 6;
        }
        if (i != 8194) {
            return -1;
        }
        return z ? 3 : 4;
    }

    /* access modifiers changed from: package-private */
    public LayoutInflater.Factory2 getLayoutInflaterFactory() {
        return this;
    }

    FragmentManagerImpl() {
    }

    static boolean modifiesAlpha(AnimationOrAnimator animationOrAnimator) {
        if (animationOrAnimator.animation instanceof AlphaAnimation) {
            return true;
        }
        if (!(animationOrAnimator.animation instanceof AnimationSet)) {
            return modifiesAlpha(animationOrAnimator.animator);
        }
        List<Animation> animations = ((AnimationSet) animationOrAnimator.animation).getAnimations();
        for (int i = 0; i < animations.size(); i++) {
            if (animations.get(i) instanceof AlphaAnimation) {
                return true;
            }
        }
        return false;
    }

    static boolean modifiesAlpha(Animator animator) {
        if (animator == null) {
            return false;
        }
        if (animator instanceof ValueAnimator) {
            PropertyValuesHolder[] values = ((ValueAnimator) animator).getValues();
            for (PropertyValuesHolder propertyName : values) {
                if ("alpha".equals(propertyName.getPropertyName())) {
                    return true;
                }
            }
        } else if (animator instanceof AnimatorSet) {
            ArrayList<Animator> childAnimations = ((AnimatorSet) animator).getChildAnimations();
            for (int i = 0; i < childAnimations.size(); i++) {
                if (modifiesAlpha(childAnimations.get(i))) {
                    return true;
                }
            }
        }
        return false;
    }

    static boolean shouldRunOnHWLayer(View view, AnimationOrAnimator animationOrAnimator) {
        if (view == null || animationOrAnimator == null || Build.VERSION.SDK_INT < 19 || view.getLayerType() != 0 || !ViewCompat.hasOverlappingRendering(view) || !modifiesAlpha(animationOrAnimator)) {
            return false;
        }
        return true;
    }

    private void throwException(RuntimeException runtimeException) {
        Log.e(TAG, runtimeException.getMessage());
        Log.e(TAG, "Activity state:");
        PrintWriter printWriter = new PrintWriter(new LogWriter(TAG));
        if (this.mHost != null) {
            try {
                this.mHost.onDump("  ", (FileDescriptor) null, printWriter, new String[0]);
            } catch (Exception e) {
                Log.e(TAG, "Failed dumping state", e);
            }
        } else {
            try {
                dump("  ", (FileDescriptor) null, printWriter, new String[0]);
            } catch (Exception e2) {
                Log.e(TAG, "Failed dumping state", e2);
            }
        }
        throw runtimeException;
    }

    public FragmentTransaction beginTransaction() {
        return new BackStackRecord(this);
    }

    public boolean executePendingTransactions() {
        boolean execPendingActions = execPendingActions();
        forcePostponedTransactions();
        return execPendingActions;
    }

    public void popBackStack() {
        enqueueAction(new PopBackStackState((String) null, -1, 0), false);
    }

    public boolean popBackStackImmediate() {
        checkStateLoss();
        return popBackStackImmediate((String) null, -1, 0);
    }

    public void popBackStack(String str, int i) {
        enqueueAction(new PopBackStackState(str, -1, i), false);
    }

    public boolean popBackStackImmediate(String str, int i) {
        checkStateLoss();
        return popBackStackImmediate(str, -1, i);
    }

    public void popBackStack(int i, int i2) {
        if (i >= 0) {
            enqueueAction(new PopBackStackState((String) null, i, i2), false);
            return;
        }
        throw new IllegalArgumentException("Bad id: " + i);
    }

    public boolean popBackStackImmediate(int i, int i2) {
        checkStateLoss();
        execPendingActions();
        if (i >= 0) {
            return popBackStackImmediate((String) null, i, i2);
        }
        throw new IllegalArgumentException("Bad id: " + i);
    }

    private boolean popBackStackImmediate(String str, int i, int i2) {
        FragmentManager peekChildFragmentManager;
        execPendingActions();
        ensureExecReady(true);
        if (this.mPrimaryNav != null && i < 0 && str == null && (peekChildFragmentManager = this.mPrimaryNav.peekChildFragmentManager()) != null && peekChildFragmentManager.popBackStackImmediate()) {
            return true;
        }
        boolean popBackStackState = popBackStackState(this.mTmpRecords, this.mTmpIsPop, str, i, i2);
        if (popBackStackState) {
            this.mExecutingActions = true;
            try {
                removeRedundantOperationsAndExecute(this.mTmpRecords, this.mTmpIsPop);
            } finally {
                cleanupExec();
            }
        }
        doPendingDeferredStart();
        burpActive();
        return popBackStackState;
    }

    public int getBackStackEntryCount() {
        if (this.mBackStack != null) {
            return this.mBackStack.size();
        }
        return 0;
    }

    public FragmentManager.BackStackEntry getBackStackEntryAt(int i) {
        return this.mBackStack.get(i);
    }

    public void addOnBackStackChangedListener(FragmentManager.OnBackStackChangedListener onBackStackChangedListener) {
        if (this.mBackStackChangeListeners == null) {
            this.mBackStackChangeListeners = new ArrayList<>();
        }
        this.mBackStackChangeListeners.add(onBackStackChangedListener);
    }

    public void removeOnBackStackChangedListener(FragmentManager.OnBackStackChangedListener onBackStackChangedListener) {
        if (this.mBackStackChangeListeners != null) {
            this.mBackStackChangeListeners.remove(onBackStackChangedListener);
        }
    }

    public void putFragment(Bundle bundle, String str, Fragment fragment) {
        if (fragment.mIndex < 0) {
            throwException(new IllegalStateException("Fragment " + fragment + " is not currently in the FragmentManager"));
        }
        bundle.putInt(str, fragment.mIndex);
    }

    public Fragment getFragment(Bundle bundle, String str) {
        int i = bundle.getInt(str, -1);
        if (i == -1) {
            return null;
        }
        Fragment fragment = this.mActive.get(i);
        if (fragment == null) {
            throwException(new IllegalStateException("Fragment no longer exists for key " + str + ": index " + i));
        }
        return fragment;
    }

    public List<Fragment> getFragments() {
        List<Fragment> list;
        if (this.mAdded.isEmpty()) {
            return Collections.EMPTY_LIST;
        }
        synchronized (this.mAdded) {
            list = (List) this.mAdded.clone();
        }
        return list;
    }

    /* access modifiers changed from: package-private */
    public List<Fragment> getActiveFragments() {
        if (this.mActive == null) {
            return null;
        }
        int size = this.mActive.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            arrayList.add(this.mActive.valueAt(i));
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public int getActiveFragmentCount() {
        if (this.mActive == null) {
            return 0;
        }
        return this.mActive.size();
    }

    public Fragment.SavedState saveFragmentInstanceState(Fragment fragment) {
        Bundle saveFragmentBasicState;
        if (fragment.mIndex < 0) {
            throwException(new IllegalStateException("Fragment " + fragment + " is not currently in the FragmentManager"));
        }
        if (fragment.mState <= 0 || (saveFragmentBasicState = saveFragmentBasicState(fragment)) == null) {
            return null;
        }
        return new Fragment.SavedState(saveFragmentBasicState);
    }

    public boolean isDestroyed() {
        return this.mDestroyed;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("FragmentManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        if (this.mParent != null) {
            DebugUtils.buildShortClassTag(this.mParent, sb);
        } else {
            DebugUtils.buildShortClassTag(this.mHost, sb);
        }
        sb.append("}}");
        return sb.toString();
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        int size;
        int size2;
        int size3;
        int size4;
        int size5;
        String str2 = str + "    ";
        if (this.mActive != null && (size5 = this.mActive.size()) > 0) {
            printWriter.print(str);
            printWriter.print("Active Fragments in ");
            printWriter.print(Integer.toHexString(System.identityHashCode(this)));
            printWriter.println(":");
            for (int i = 0; i < size5; i++) {
                Fragment valueAt = this.mActive.valueAt(i);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i);
                printWriter.print(": ");
                printWriter.println(valueAt);
                if (valueAt != null) {
                    valueAt.dump(str2, fileDescriptor, printWriter, strArr);
                }
            }
        }
        int size6 = this.mAdded.size();
        if (size6 > 0) {
            printWriter.print(str);
            printWriter.println("Added Fragments:");
            for (int i2 = 0; i2 < size6; i2++) {
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i2);
                printWriter.print(": ");
                printWriter.println(this.mAdded.get(i2).toString());
            }
        }
        if (this.mCreatedMenus != null && (size4 = this.mCreatedMenus.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Fragments Created Menus:");
            for (int i3 = 0; i3 < size4; i3++) {
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i3);
                printWriter.print(": ");
                printWriter.println(this.mCreatedMenus.get(i3).toString());
            }
        }
        if (this.mBackStack != null && (size3 = this.mBackStack.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Back Stack:");
            for (int i4 = 0; i4 < size3; i4++) {
                BackStackRecord backStackRecord = this.mBackStack.get(i4);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i4);
                printWriter.print(": ");
                printWriter.println(backStackRecord.toString());
                backStackRecord.dump(str2, fileDescriptor, printWriter, strArr);
            }
        }
        synchronized (this) {
            if (this.mBackStackIndices != null && (size2 = this.mBackStackIndices.size()) > 0) {
                printWriter.print(str);
                printWriter.println("Back Stack Indices:");
                for (int i5 = 0; i5 < size2; i5++) {
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(i5);
                    printWriter.print(": ");
                    printWriter.println(this.mBackStackIndices.get(i5));
                }
            }
            if (this.mAvailBackStackIndices != null && this.mAvailBackStackIndices.size() > 0) {
                printWriter.print(str);
                printWriter.print("mAvailBackStackIndices: ");
                printWriter.println(Arrays.toString(this.mAvailBackStackIndices.toArray()));
            }
        }
        if (this.mPendingActions != null && (size = this.mPendingActions.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Pending Actions:");
            for (int i6 = 0; i6 < size; i6++) {
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i6);
                printWriter.print(": ");
                printWriter.println(this.mPendingActions.get(i6));
            }
        }
        printWriter.print(str);
        printWriter.println("FragmentManager misc state:");
        printWriter.print(str);
        printWriter.print("  mHost=");
        printWriter.println(this.mHost);
        printWriter.print(str);
        printWriter.print("  mContainer=");
        printWriter.println(this.mContainer);
        if (this.mParent != null) {
            printWriter.print(str);
            printWriter.print("  mParent=");
            printWriter.println(this.mParent);
        }
        printWriter.print(str);
        printWriter.print("  mCurState=");
        printWriter.print(this.mCurState);
        printWriter.print(" mStateSaved=");
        printWriter.print(this.mStateSaved);
        printWriter.print(" mStopped=");
        printWriter.print(this.mStopped);
        printWriter.print(" mDestroyed=");
        printWriter.println(this.mDestroyed);
        if (this.mNeedMenuInvalidate) {
            printWriter.print(str);
            printWriter.print("  mNeedMenuInvalidate=");
            printWriter.println(this.mNeedMenuInvalidate);
        }
        if (this.mNoTransactionsBecause != null) {
            printWriter.print(str);
            printWriter.print("  mNoTransactionsBecause=");
            printWriter.println(this.mNoTransactionsBecause);
        }
    }

    static AnimationOrAnimator makeOpenCloseAnimation(Context context, float f, float f2, float f3, float f4) {
        AnimationSet animationSet = new AnimationSet(false);
        ScaleAnimation scaleAnimation = new ScaleAnimation(f, f2, f, f2, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setInterpolator(DECELERATE_QUINT);
        scaleAnimation.setDuration(220);
        animationSet.addAnimation(scaleAnimation);
        AlphaAnimation alphaAnimation = new AlphaAnimation(f3, f4);
        alphaAnimation.setInterpolator(DECELERATE_CUBIC);
        alphaAnimation.setDuration(220);
        animationSet.addAnimation(alphaAnimation);
        return new AnimationOrAnimator((Animation) animationSet);
    }

    static AnimationOrAnimator makeFadeAnimation(Context context, float f, float f2) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(f, f2);
        alphaAnimation.setInterpolator(DECELERATE_CUBIC);
        alphaAnimation.setDuration(220);
        return new AnimationOrAnimator((Animation) alphaAnimation);
    }

    /* access modifiers changed from: package-private */
    public AnimationOrAnimator loadAnimation(Fragment fragment, int i, boolean z, int i2) {
        int transitToStyleIndex;
        int nextAnim = fragment.getNextAnim();
        Animation onCreateAnimation = fragment.onCreateAnimation(i, z, nextAnim);
        if (onCreateAnimation != null) {
            return new AnimationOrAnimator(onCreateAnimation);
        }
        Animator onCreateAnimator = fragment.onCreateAnimator(i, z, nextAnim);
        if (onCreateAnimator != null) {
            return new AnimationOrAnimator(onCreateAnimator);
        }
        if (nextAnim != 0) {
            boolean equals = "anim".equals(this.mHost.getContext().getResources().getResourceTypeName(nextAnim));
            boolean z2 = false;
            if (equals) {
                try {
                    Animation loadAnimation = AnimationUtils.loadAnimation(this.mHost.getContext(), nextAnim);
                    if (loadAnimation != null) {
                        return new AnimationOrAnimator(loadAnimation);
                    }
                    z2 = true;
                } catch (Resources.NotFoundException e) {
                    throw e;
                } catch (RuntimeException unused) {
                }
            }
            if (!z2) {
                try {
                    Animator loadAnimator = AnimatorInflater.loadAnimator(this.mHost.getContext(), nextAnim);
                    if (loadAnimator != null) {
                        return new AnimationOrAnimator(loadAnimator);
                    }
                } catch (RuntimeException e2) {
                    if (!equals) {
                        Animation loadAnimation2 = AnimationUtils.loadAnimation(this.mHost.getContext(), nextAnim);
                        if (loadAnimation2 != null) {
                            return new AnimationOrAnimator(loadAnimation2);
                        }
                    } else {
                        throw e2;
                    }
                }
            }
        }
        if (i == 0 || (transitToStyleIndex = transitToStyleIndex(i, z)) < 0) {
            return null;
        }
        switch (transitToStyleIndex) {
            case 1:
                return makeOpenCloseAnimation(this.mHost.getContext(), 1.125f, 1.0f, 0.0f, 1.0f);
            case 2:
                return makeOpenCloseAnimation(this.mHost.getContext(), 1.0f, 0.975f, 1.0f, 0.0f);
            case 3:
                return makeOpenCloseAnimation(this.mHost.getContext(), 0.975f, 1.0f, 0.0f, 1.0f);
            case 4:
                return makeOpenCloseAnimation(this.mHost.getContext(), 1.0f, 1.075f, 1.0f, 0.0f);
            case 5:
                return makeFadeAnimation(this.mHost.getContext(), 0.0f, 1.0f);
            case 6:
                return makeFadeAnimation(this.mHost.getContext(), 1.0f, 0.0f);
            default:
                if (i2 == 0 && this.mHost.onHasWindowAnimations()) {
                    i2 = this.mHost.onGetWindowAnimations();
                }
                return i2 == 0 ? null : null;
        }
    }

    public void performPendingDeferredStart(Fragment fragment) {
        if (!fragment.mDeferStart) {
            return;
        }
        if (this.mExecutingActions) {
            this.mHavePendingDeferredStart = true;
            return;
        }
        fragment.mDeferStart = false;
        moveToState(fragment, this.mCurState, 0, 0, false);
    }

    private static void setHWLayerAnimListenerIfAlpha(View view, AnimationOrAnimator animationOrAnimator) {
        if (view != null && animationOrAnimator != null && shouldRunOnHWLayer(view, animationOrAnimator)) {
            if (animationOrAnimator.animator != null) {
                animationOrAnimator.animator.addListener(new AnimatorOnHWLayerIfNeededListener(view));
                return;
            }
            Animation.AnimationListener animationListener = getAnimationListener(animationOrAnimator.animation);
            view.setLayerType(2, (Paint) null);
            animationOrAnimator.animation.setAnimationListener(new AnimateOnHWLayerIfNeededListener(view, animationListener));
        }
    }

    private static Animation.AnimationListener getAnimationListener(Animation animation) {
        try {
            if (sAnimationListenerField == null) {
                sAnimationListenerField = Animation.class.getDeclaredField("mListener");
                sAnimationListenerField.setAccessible(true);
            }
            return (Animation.AnimationListener) sAnimationListenerField.get(animation);
        } catch (NoSuchFieldException e) {
            Log.e(TAG, "No field with the name mListener is found in Animation class", e);
            return null;
        } catch (IllegalAccessException e2) {
            Log.e(TAG, "Cannot access Animation's mListener field", e2);
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isStateAtLeast(int i) {
        return this.mCurState >= i;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v0, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v0, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v1, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v1, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v2, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v2, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v3, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v4, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v5, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v3, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v4, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v6, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v7, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v8, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v9, resolved type: int} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:100:?, code lost:
        r1 = r16.getResources().getResourceName(r7.mContainerId);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x0214, code lost:
        r1 = android.support.v4.os.EnvironmentCompat.MEDIA_UNKNOWN;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x0246, code lost:
        r0 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:146:0x0328, code lost:
        if (r11 >= 4) goto L_0x034a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:148:0x032c, code lost:
        if (DEBUG == false) goto L_0x0344;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:149:0x032e, code lost:
        android.util.Log.v(TAG, "movefrom STARTED: " + r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:150:0x0344, code lost:
        r16.performStop();
        dispatchOnFragmentStopped(r7, false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:151:0x034a, code lost:
        if (r11 >= 3) goto L_0x0369;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:153:0x034e, code lost:
        if (DEBUG == false) goto L_0x0366;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:154:0x0350, code lost:
        android.util.Log.v(TAG, "movefrom STOPPED: " + r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:155:0x0366, code lost:
        r16.performReallyStop();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:156:0x0369, code lost:
        if (r11 >= 2) goto L_0x03e9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:158:0x036d, code lost:
        if (DEBUG == false) goto L_0x0385;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:159:0x036f, code lost:
        android.util.Log.v(TAG, "movefrom ACTIVITY_CREATED: " + r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:161:0x0387, code lost:
        if (r7.mView == null) goto L_0x0398;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:163:0x038f, code lost:
        if (r6.mHost.onShouldSaveFragmentState(r7) == false) goto L_0x0398;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:165:0x0393, code lost:
        if (r7.mSavedViewState != null) goto L_0x0398;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:166:0x0395, code lost:
        saveFragmentViewState(r16);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:167:0x0398, code lost:
        r16.performDestroyView();
        dispatchOnFragmentViewDestroyed(r7, false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:168:0x03a0, code lost:
        if (r7.mView == null) goto L_0x03e1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:170:0x03a4, code lost:
        if (r7.mContainer == null) goto L_0x03e1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:171:0x03a6, code lost:
        r7.mContainer.endViewTransition(r7.mView);
        r7.mView.clearAnimation();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:172:0x03b5, code lost:
        if (r6.mCurState <= 0) goto L_0x03d2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:174:0x03b9, code lost:
        if (r6.mDestroyed != false) goto L_0x03d2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:176:0x03c1, code lost:
        if (r7.mView.getVisibility() != 0) goto L_0x03d2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:178:0x03c7, code lost:
        if (r7.mPostponedAlpha < 0.0f) goto L_0x03d2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:179:0x03c9, code lost:
        r0 = loadAnimation(r7, r18, false, r19);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:180:0x03d2, code lost:
        r0 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:181:0x03d3, code lost:
        r7.mPostponedAlpha = 0.0f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:182:0x03d5, code lost:
        if (r0 == null) goto L_0x03da;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:183:0x03d7, code lost:
        animateRemoveFragment(r7, r0, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:184:0x03da, code lost:
        r7.mContainer.removeView(r7.mView);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:185:0x03e1, code lost:
        r7.mContainer = null;
        r7.mView = null;
        r7.mInnerView = null;
        r7.mInLayout = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:186:0x03e9, code lost:
        if (r11 >= 1) goto L_0x045f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:188:0x03ed, code lost:
        if (r6.mDestroyed == false) goto L_0x0410;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:190:0x03f3, code lost:
        if (r16.getAnimatingAway() == null) goto L_0x0400;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:191:0x03f5, code lost:
        r0 = r16.getAnimatingAway();
        r7.setAnimatingAway((android.view.View) null);
        r0.clearAnimation();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:193:0x0404, code lost:
        if (r16.getAnimator() == null) goto L_0x0410;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:194:0x0406, code lost:
        r0 = r16.getAnimator();
        r7.setAnimator((android.animation.Animator) null);
        r0.cancel();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:196:0x0414, code lost:
        if (r16.getAnimatingAway() != null) goto L_0x045b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:198:0x041a, code lost:
        if (r16.getAnimator() == null) goto L_0x041d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:200:0x041f, code lost:
        if (DEBUG == false) goto L_0x0437;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:201:0x0421, code lost:
        android.util.Log.v(TAG, "movefrom CREATED: " + r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:203:0x0439, code lost:
        if (r7.mRetaining != false) goto L_0x0442;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:204:0x043b, code lost:
        r16.performDestroy();
        dispatchOnFragmentDestroyed(r7, false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:205:0x0442, code lost:
        r7.mState = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:206:0x0444, code lost:
        r16.performDetach();
        dispatchOnFragmentDetached(r7, false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:207:0x044a, code lost:
        if (r20 != false) goto L_0x045f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:209:0x044e, code lost:
        if (r7.mRetaining != false) goto L_0x0454;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:210:0x0450, code lost:
        makeInactive(r16);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:211:0x0454, code lost:
        r7.mHost = null;
        r7.mParentFragment = null;
        r7.mFragmentManager = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:212:0x045b, code lost:
        r7.setStateAfterAnimating(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x01af, code lost:
        ensureInflatedFragmentView(r16);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x01b2, code lost:
        if (r11 <= 1) goto L_0x02ac;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x01b6, code lost:
        if (DEBUG == false) goto L_0x01ce;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x01b8, code lost:
        android.util.Log.v(TAG, "moveto ACTIVITY_CREATED: " + r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x01d0, code lost:
        if (r7.mFromLayout != false) goto L_0x0297;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x01d4, code lost:
        if (r7.mContainerId == 0) goto L_0x0246;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x01d9, code lost:
        if (r7.mContainerId != -1) goto L_0x01f9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x01db, code lost:
        throwException(new java.lang.IllegalArgumentException("Cannot create fragment " + r7 + " for a container view with no id"));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x01f9, code lost:
        r0 = (android.view.ViewGroup) r6.mContainer.onFindViewById(r7.mContainerId);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x0203, code lost:
        if (r0 != null) goto L_0x0247;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x0207, code lost:
        if (r7.mRestored != false) goto L_0x0247;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:216:0x0464  */
    /* JADX WARNING: Removed duplicated region for block: B:218:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void moveToState(android.support.v4.app.Fragment r16, int r17, int r18, int r19, boolean r20) {
        /*
            r15 = this;
            r6 = r15
            r7 = r16
            boolean r0 = r7.mAdded
            r8 = 1
            if (r0 == 0) goto L_0x0010
            boolean r0 = r7.mDetached
            if (r0 == 0) goto L_0x000d
            goto L_0x0010
        L_0x000d:
            r0 = r17
            goto L_0x0015
        L_0x0010:
            r0 = r17
            if (r0 <= r8) goto L_0x0015
            r0 = 1
        L_0x0015:
            boolean r1 = r7.mRemoving
            if (r1 == 0) goto L_0x002b
            int r1 = r7.mState
            if (r0 <= r1) goto L_0x002b
            int r0 = r7.mState
            if (r0 != 0) goto L_0x0029
            boolean r0 = r16.isInBackStack()
            if (r0 == 0) goto L_0x0029
            r0 = 1
            goto L_0x002b
        L_0x0029:
            int r0 = r7.mState
        L_0x002b:
            boolean r1 = r7.mDeferStart
            r9 = 4
            r10 = 3
            if (r1 == 0) goto L_0x0039
            int r1 = r7.mState
            if (r1 >= r9) goto L_0x0039
            if (r0 <= r10) goto L_0x0039
            r11 = 3
            goto L_0x003a
        L_0x0039:
            r11 = r0
        L_0x003a:
            int r0 = r7.mState
            r12 = 2
            r13 = 0
            r14 = 0
            if (r0 > r11) goto L_0x02fa
            boolean r0 = r7.mFromLayout
            if (r0 == 0) goto L_0x004a
            boolean r0 = r7.mInLayout
            if (r0 != 0) goto L_0x004a
            return
        L_0x004a:
            android.view.View r0 = r16.getAnimatingAway()
            if (r0 != 0) goto L_0x0056
            android.animation.Animator r0 = r16.getAnimator()
            if (r0 == 0) goto L_0x0069
        L_0x0056:
            r7.setAnimatingAway(r13)
            r7.setAnimator(r13)
            int r2 = r16.getStateAfterAnimating()
            r3 = 0
            r4 = 0
            r5 = 1
            r0 = r15
            r1 = r16
            r0.moveToState(r1, r2, r3, r4, r5)
        L_0x0069:
            int r0 = r7.mState
            switch(r0) {
                case 0: goto L_0x0070;
                case 1: goto L_0x01af;
                case 2: goto L_0x02ac;
                case 3: goto L_0x02b0;
                case 4: goto L_0x02d2;
                default: goto L_0x006e;
            }
        L_0x006e:
            goto L_0x045f
        L_0x0070:
            if (r11 <= 0) goto L_0x01af
            boolean r0 = DEBUG
            if (r0 == 0) goto L_0x008c
            java.lang.String r0 = "FragmentManager"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "moveto CREATED: "
            r1.append(r2)
            r1.append(r7)
            java.lang.String r1 = r1.toString()
            android.util.Log.v(r0, r1)
        L_0x008c:
            android.os.Bundle r0 = r7.mSavedFragmentState
            if (r0 == 0) goto L_0x00e3
            android.os.Bundle r0 = r7.mSavedFragmentState
            android.support.v4.app.FragmentHostCallback r1 = r6.mHost
            android.content.Context r1 = r1.getContext()
            java.lang.ClassLoader r1 = r1.getClassLoader()
            r0.setClassLoader(r1)
            android.os.Bundle r0 = r7.mSavedFragmentState
            java.lang.String r1 = "android:view_state"
            android.util.SparseArray r0 = r0.getSparseParcelableArray(r1)
            r7.mSavedViewState = r0
            android.os.Bundle r0 = r7.mSavedFragmentState
            java.lang.String r1 = "android:target_state"
            android.support.v4.app.Fragment r0 = r15.getFragment(r0, r1)
            r7.mTarget = r0
            android.support.v4.app.Fragment r0 = r7.mTarget
            if (r0 == 0) goto L_0x00c1
            android.os.Bundle r0 = r7.mSavedFragmentState
            java.lang.String r1 = "android:target_req_state"
            int r0 = r0.getInt(r1, r14)
            r7.mTargetRequestCode = r0
        L_0x00c1:
            java.lang.Boolean r0 = r7.mSavedUserVisibleHint
            if (r0 == 0) goto L_0x00d0
            java.lang.Boolean r0 = r7.mSavedUserVisibleHint
            boolean r0 = r0.booleanValue()
            r7.mUserVisibleHint = r0
            r7.mSavedUserVisibleHint = r13
            goto L_0x00da
        L_0x00d0:
            android.os.Bundle r0 = r7.mSavedFragmentState
            java.lang.String r1 = "android:user_visible_hint"
            boolean r0 = r0.getBoolean(r1, r8)
            r7.mUserVisibleHint = r0
        L_0x00da:
            boolean r0 = r7.mUserVisibleHint
            if (r0 != 0) goto L_0x00e3
            r7.mDeferStart = r8
            if (r11 <= r10) goto L_0x00e3
            r11 = 3
        L_0x00e3:
            android.support.v4.app.FragmentHostCallback r0 = r6.mHost
            r7.mHost = r0
            android.support.v4.app.Fragment r0 = r6.mParent
            r7.mParentFragment = r0
            android.support.v4.app.Fragment r0 = r6.mParent
            if (r0 == 0) goto L_0x00f4
            android.support.v4.app.Fragment r0 = r6.mParent
            android.support.v4.app.FragmentManagerImpl r0 = r0.mChildFragmentManager
            goto L_0x00fa
        L_0x00f4:
            android.support.v4.app.FragmentHostCallback r0 = r6.mHost
            android.support.v4.app.FragmentManagerImpl r0 = r0.getFragmentManagerImpl()
        L_0x00fa:
            r7.mFragmentManager = r0
            android.support.v4.app.Fragment r0 = r7.mTarget
            if (r0 == 0) goto L_0x0145
            android.util.SparseArray<android.support.v4.app.Fragment> r0 = r6.mActive
            android.support.v4.app.Fragment r1 = r7.mTarget
            int r1 = r1.mIndex
            java.lang.Object r0 = r0.get(r1)
            android.support.v4.app.Fragment r1 = r7.mTarget
            if (r0 != r1) goto L_0x011f
            android.support.v4.app.Fragment r0 = r7.mTarget
            int r0 = r0.mState
            if (r0 >= r8) goto L_0x0145
            android.support.v4.app.Fragment r1 = r7.mTarget
            r2 = 1
            r3 = 0
            r4 = 0
            r5 = 1
            r0 = r15
            r0.moveToState(r1, r2, r3, r4, r5)
            goto L_0x0145
        L_0x011f:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Fragment "
            r1.append(r2)
            r1.append(r7)
            java.lang.String r2 = " declared target fragment "
            r1.append(r2)
            android.support.v4.app.Fragment r2 = r7.mTarget
            r1.append(r2)
            java.lang.String r2 = " that does not belong to this FragmentManager!"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x0145:
            android.support.v4.app.FragmentHostCallback r0 = r6.mHost
            android.content.Context r0 = r0.getContext()
            r15.dispatchOnFragmentPreAttached(r7, r0, r14)
            r7.mCalled = r14
            android.support.v4.app.FragmentHostCallback r0 = r6.mHost
            android.content.Context r0 = r0.getContext()
            r7.onAttach((android.content.Context) r0)
            boolean r0 = r7.mCalled
            if (r0 == 0) goto L_0x0193
            android.support.v4.app.Fragment r0 = r7.mParentFragment
            if (r0 != 0) goto L_0x0167
            android.support.v4.app.FragmentHostCallback r0 = r6.mHost
            r0.onAttachFragment(r7)
            goto L_0x016c
        L_0x0167:
            android.support.v4.app.Fragment r0 = r7.mParentFragment
            r0.onAttachFragment(r7)
        L_0x016c:
            android.support.v4.app.FragmentHostCallback r0 = r6.mHost
            android.content.Context r0 = r0.getContext()
            r15.dispatchOnFragmentAttached(r7, r0, r14)
            boolean r0 = r7.mIsCreated
            if (r0 != 0) goto L_0x0189
            android.os.Bundle r0 = r7.mSavedFragmentState
            r15.dispatchOnFragmentPreCreated(r7, r0, r14)
            android.os.Bundle r0 = r7.mSavedFragmentState
            r7.performCreate(r0)
            android.os.Bundle r0 = r7.mSavedFragmentState
            r15.dispatchOnFragmentCreated(r7, r0, r14)
            goto L_0x0190
        L_0x0189:
            android.os.Bundle r0 = r7.mSavedFragmentState
            r7.restoreChildFragmentState(r0)
            r7.mState = r8
        L_0x0190:
            r7.mRetaining = r14
            goto L_0x01af
        L_0x0193:
            android.support.v4.app.SuperNotCalledException r0 = new android.support.v4.app.SuperNotCalledException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Fragment "
            r1.append(r2)
            r1.append(r7)
            java.lang.String r2 = " did not call through to super.onAttach()"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x01af:
            r15.ensureInflatedFragmentView(r16)
            if (r11 <= r8) goto L_0x02ac
            boolean r0 = DEBUG
            if (r0 == 0) goto L_0x01ce
            java.lang.String r0 = "FragmentManager"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "moveto ACTIVITY_CREATED: "
            r1.append(r2)
            r1.append(r7)
            java.lang.String r1 = r1.toString()
            android.util.Log.v(r0, r1)
        L_0x01ce:
            boolean r0 = r7.mFromLayout
            if (r0 != 0) goto L_0x0297
            int r0 = r7.mContainerId
            if (r0 == 0) goto L_0x0246
            int r0 = r7.mContainerId
            r1 = -1
            if (r0 != r1) goto L_0x01f9
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Cannot create fragment "
            r1.append(r2)
            r1.append(r7)
            java.lang.String r2 = " for a container view with no id"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            r15.throwException(r0)
        L_0x01f9:
            android.support.v4.app.FragmentContainer r0 = r6.mContainer
            int r1 = r7.mContainerId
            android.view.View r0 = r0.onFindViewById(r1)
            android.view.ViewGroup r0 = (android.view.ViewGroup) r0
            if (r0 != 0) goto L_0x0247
            boolean r1 = r7.mRestored
            if (r1 != 0) goto L_0x0247
            android.content.res.Resources r1 = r16.getResources()     // Catch:{ NotFoundException -> 0x0214 }
            int r2 = r7.mContainerId     // Catch:{ NotFoundException -> 0x0214 }
            java.lang.String r1 = r1.getResourceName(r2)     // Catch:{ NotFoundException -> 0x0214 }
            goto L_0x0216
        L_0x0214:
            java.lang.String r1 = "unknown"
        L_0x0216:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "No view found for id 0x"
            r3.append(r4)
            int r4 = r7.mContainerId
            java.lang.String r4 = java.lang.Integer.toHexString(r4)
            r3.append(r4)
            java.lang.String r4 = " ("
            r3.append(r4)
            r3.append(r1)
            java.lang.String r1 = ") for fragment "
            r3.append(r1)
            r3.append(r7)
            java.lang.String r1 = r3.toString()
            r2.<init>(r1)
            r15.throwException(r2)
            goto L_0x0247
        L_0x0246:
            r0 = r13
        L_0x0247:
            r7.mContainer = r0
            android.os.Bundle r1 = r7.mSavedFragmentState
            android.view.LayoutInflater r1 = r7.performGetLayoutInflater(r1)
            android.os.Bundle r2 = r7.mSavedFragmentState
            android.view.View r1 = r7.performCreateView(r1, r0, r2)
            r7.mView = r1
            android.view.View r1 = r7.mView
            if (r1 == 0) goto L_0x0295
            android.view.View r1 = r7.mView
            r7.mInnerView = r1
            android.view.View r1 = r7.mView
            r1.setSaveFromParentEnabled(r14)
            if (r0 == 0) goto L_0x026b
            android.view.View r1 = r7.mView
            r0.addView(r1)
        L_0x026b:
            boolean r0 = r7.mHidden
            if (r0 == 0) goto L_0x0276
            android.view.View r0 = r7.mView
            r1 = 8
            r0.setVisibility(r1)
        L_0x0276:
            android.view.View r0 = r7.mView
            android.os.Bundle r1 = r7.mSavedFragmentState
            r7.onViewCreated(r0, r1)
            android.view.View r0 = r7.mView
            android.os.Bundle r1 = r7.mSavedFragmentState
            r15.dispatchOnFragmentViewCreated(r7, r0, r1, r14)
            android.view.View r0 = r7.mView
            int r0 = r0.getVisibility()
            if (r0 != 0) goto L_0x0291
            android.view.ViewGroup r0 = r7.mContainer
            if (r0 == 0) goto L_0x0291
            goto L_0x0292
        L_0x0291:
            r8 = 0
        L_0x0292:
            r7.mIsNewlyAdded = r8
            goto L_0x0297
        L_0x0295:
            r7.mInnerView = r13
        L_0x0297:
            android.os.Bundle r0 = r7.mSavedFragmentState
            r7.performActivityCreated(r0)
            android.os.Bundle r0 = r7.mSavedFragmentState
            r15.dispatchOnFragmentActivityCreated(r7, r0, r14)
            android.view.View r0 = r7.mView
            if (r0 == 0) goto L_0x02aa
            android.os.Bundle r0 = r7.mSavedFragmentState
            r7.restoreViewState(r0)
        L_0x02aa:
            r7.mSavedFragmentState = r13
        L_0x02ac:
            if (r11 <= r12) goto L_0x02b0
            r7.mState = r10
        L_0x02b0:
            if (r11 <= r10) goto L_0x02d2
            boolean r0 = DEBUG
            if (r0 == 0) goto L_0x02cc
            java.lang.String r0 = "FragmentManager"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "moveto STARTED: "
            r1.append(r2)
            r1.append(r7)
            java.lang.String r1 = r1.toString()
            android.util.Log.v(r0, r1)
        L_0x02cc:
            r16.performStart()
            r15.dispatchOnFragmentStarted(r7, r14)
        L_0x02d2:
            if (r11 <= r9) goto L_0x045f
            boolean r0 = DEBUG
            if (r0 == 0) goto L_0x02ee
            java.lang.String r0 = "FragmentManager"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "moveto RESUMED: "
            r1.append(r2)
            r1.append(r7)
            java.lang.String r1 = r1.toString()
            android.util.Log.v(r0, r1)
        L_0x02ee:
            r16.performResume()
            r15.dispatchOnFragmentResumed(r7, r14)
            r7.mSavedFragmentState = r13
            r7.mSavedViewState = r13
            goto L_0x045f
        L_0x02fa:
            int r0 = r7.mState
            if (r0 <= r11) goto L_0x045f
            int r0 = r7.mState
            switch(r0) {
                case 1: goto L_0x03e9;
                case 2: goto L_0x0369;
                case 3: goto L_0x034a;
                case 4: goto L_0x0328;
                case 5: goto L_0x0305;
                default: goto L_0x0303;
            }
        L_0x0303:
            goto L_0x045f
        L_0x0305:
            r0 = 5
            if (r11 >= r0) goto L_0x0328
            boolean r0 = DEBUG
            if (r0 == 0) goto L_0x0322
            java.lang.String r0 = "FragmentManager"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "movefrom RESUMED: "
            r1.append(r2)
            r1.append(r7)
            java.lang.String r1 = r1.toString()
            android.util.Log.v(r0, r1)
        L_0x0322:
            r16.performPause()
            r15.dispatchOnFragmentPaused(r7, r14)
        L_0x0328:
            if (r11 >= r9) goto L_0x034a
            boolean r0 = DEBUG
            if (r0 == 0) goto L_0x0344
            java.lang.String r0 = "FragmentManager"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "movefrom STARTED: "
            r1.append(r2)
            r1.append(r7)
            java.lang.String r1 = r1.toString()
            android.util.Log.v(r0, r1)
        L_0x0344:
            r16.performStop()
            r15.dispatchOnFragmentStopped(r7, r14)
        L_0x034a:
            if (r11 >= r10) goto L_0x0369
            boolean r0 = DEBUG
            if (r0 == 0) goto L_0x0366
            java.lang.String r0 = "FragmentManager"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "movefrom STOPPED: "
            r1.append(r2)
            r1.append(r7)
            java.lang.String r1 = r1.toString()
            android.util.Log.v(r0, r1)
        L_0x0366:
            r16.performReallyStop()
        L_0x0369:
            if (r11 >= r12) goto L_0x03e9
            boolean r0 = DEBUG
            if (r0 == 0) goto L_0x0385
            java.lang.String r0 = "FragmentManager"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "movefrom ACTIVITY_CREATED: "
            r1.append(r2)
            r1.append(r7)
            java.lang.String r1 = r1.toString()
            android.util.Log.v(r0, r1)
        L_0x0385:
            android.view.View r0 = r7.mView
            if (r0 == 0) goto L_0x0398
            android.support.v4.app.FragmentHostCallback r0 = r6.mHost
            boolean r0 = r0.onShouldSaveFragmentState(r7)
            if (r0 == 0) goto L_0x0398
            android.util.SparseArray<android.os.Parcelable> r0 = r7.mSavedViewState
            if (r0 != 0) goto L_0x0398
            r15.saveFragmentViewState(r16)
        L_0x0398:
            r16.performDestroyView()
            r15.dispatchOnFragmentViewDestroyed(r7, r14)
            android.view.View r0 = r7.mView
            if (r0 == 0) goto L_0x03e1
            android.view.ViewGroup r0 = r7.mContainer
            if (r0 == 0) goto L_0x03e1
            android.view.ViewGroup r0 = r7.mContainer
            android.view.View r1 = r7.mView
            r0.endViewTransition(r1)
            android.view.View r0 = r7.mView
            r0.clearAnimation()
            int r0 = r6.mCurState
            r1 = 0
            if (r0 <= 0) goto L_0x03d2
            boolean r0 = r6.mDestroyed
            if (r0 != 0) goto L_0x03d2
            android.view.View r0 = r7.mView
            int r0 = r0.getVisibility()
            if (r0 != 0) goto L_0x03d2
            float r0 = r7.mPostponedAlpha
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 < 0) goto L_0x03d2
            r0 = r18
            r2 = r19
            android.support.v4.app.FragmentManagerImpl$AnimationOrAnimator r0 = r15.loadAnimation(r7, r0, r14, r2)
            goto L_0x03d3
        L_0x03d2:
            r0 = r13
        L_0x03d3:
            r7.mPostponedAlpha = r1
            if (r0 == 0) goto L_0x03da
            r15.animateRemoveFragment(r7, r0, r11)
        L_0x03da:
            android.view.ViewGroup r0 = r7.mContainer
            android.view.View r1 = r7.mView
            r0.removeView(r1)
        L_0x03e1:
            r7.mContainer = r13
            r7.mView = r13
            r7.mInnerView = r13
            r7.mInLayout = r14
        L_0x03e9:
            if (r11 >= r8) goto L_0x045f
            boolean r0 = r6.mDestroyed
            if (r0 == 0) goto L_0x0410
            android.view.View r0 = r16.getAnimatingAway()
            if (r0 == 0) goto L_0x0400
            android.view.View r0 = r16.getAnimatingAway()
            r7.setAnimatingAway(r13)
            r0.clearAnimation()
            goto L_0x0410
        L_0x0400:
            android.animation.Animator r0 = r16.getAnimator()
            if (r0 == 0) goto L_0x0410
            android.animation.Animator r0 = r16.getAnimator()
            r7.setAnimator(r13)
            r0.cancel()
        L_0x0410:
            android.view.View r0 = r16.getAnimatingAway()
            if (r0 != 0) goto L_0x045b
            android.animation.Animator r0 = r16.getAnimator()
            if (r0 == 0) goto L_0x041d
            goto L_0x045b
        L_0x041d:
            boolean r0 = DEBUG
            if (r0 == 0) goto L_0x0437
            java.lang.String r0 = "FragmentManager"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "movefrom CREATED: "
            r1.append(r2)
            r1.append(r7)
            java.lang.String r1 = r1.toString()
            android.util.Log.v(r0, r1)
        L_0x0437:
            boolean r0 = r7.mRetaining
            if (r0 != 0) goto L_0x0442
            r16.performDestroy()
            r15.dispatchOnFragmentDestroyed(r7, r14)
            goto L_0x0444
        L_0x0442:
            r7.mState = r14
        L_0x0444:
            r16.performDetach()
            r15.dispatchOnFragmentDetached(r7, r14)
            if (r20 != 0) goto L_0x045f
            boolean r0 = r7.mRetaining
            if (r0 != 0) goto L_0x0454
            r15.makeInactive(r16)
            goto L_0x045f
        L_0x0454:
            r7.mHost = r13
            r7.mParentFragment = r13
            r7.mFragmentManager = r13
            goto L_0x045f
        L_0x045b:
            r7.setStateAfterAnimating(r11)
            goto L_0x0460
        L_0x045f:
            r8 = r11
        L_0x0460:
            int r0 = r7.mState
            if (r0 == r8) goto L_0x0493
            java.lang.String r0 = "FragmentManager"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "moveToState: Fragment state for "
            r1.append(r2)
            r1.append(r7)
            java.lang.String r2 = " not updated inline; "
            r1.append(r2)
            java.lang.String r2 = "expected state "
            r1.append(r2)
            r1.append(r8)
            java.lang.String r2 = " found "
            r1.append(r2)
            int r2 = r7.mState
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            android.util.Log.w(r0, r1)
            r7.mState = r8
        L_0x0493:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.app.FragmentManagerImpl.moveToState(android.support.v4.app.Fragment, int, int, int, boolean):void");
    }

    private void animateRemoveFragment(@NonNull final Fragment fragment, @NonNull AnimationOrAnimator animationOrAnimator, int i) {
        final View view = fragment.mView;
        final ViewGroup viewGroup = fragment.mContainer;
        viewGroup.startViewTransition(view);
        fragment.setStateAfterAnimating(i);
        if (animationOrAnimator.animation != null) {
            EndViewTransitionAnimator endViewTransitionAnimator = new EndViewTransitionAnimator(animationOrAnimator.animation, viewGroup, view);
            fragment.setAnimatingAway(fragment.mView);
            endViewTransitionAnimator.setAnimationListener(new AnimationListenerWrapper(getAnimationListener(endViewTransitionAnimator)) {
                public void onAnimationEnd(Animation animation) {
                    super.onAnimationEnd(animation);
                    viewGroup.post(new Runnable() {
                        public void run() {
                            if (fragment.getAnimatingAway() != null) {
                                fragment.setAnimatingAway((View) null);
                                FragmentManagerImpl.this.moveToState(fragment, fragment.getStateAfterAnimating(), 0, 0, false);
                            }
                        }
                    });
                }
            });
            setHWLayerAnimListenerIfAlpha(view, animationOrAnimator);
            fragment.mView.startAnimation(endViewTransitionAnimator);
            return;
        }
        Animator animator = animationOrAnimator.animator;
        fragment.setAnimator(animationOrAnimator.animator);
        animator.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                viewGroup.endViewTransition(view);
                Animator animator2 = fragment.getAnimator();
                fragment.setAnimator((Animator) null);
                if (animator2 != null && viewGroup.indexOfChild(view) < 0) {
                    FragmentManagerImpl.this.moveToState(fragment, fragment.getStateAfterAnimating(), 0, 0, false);
                }
            }
        });
        animator.setTarget(fragment.mView);
        setHWLayerAnimListenerIfAlpha(fragment.mView, animationOrAnimator);
        animator.start();
    }

    /* access modifiers changed from: package-private */
    public void moveToState(Fragment fragment) {
        moveToState(fragment, this.mCurState, 0, 0, false);
    }

    /* access modifiers changed from: package-private */
    public void ensureInflatedFragmentView(Fragment fragment) {
        if (fragment.mFromLayout && !fragment.mPerformedCreateView) {
            fragment.mView = fragment.performCreateView(fragment.performGetLayoutInflater(fragment.mSavedFragmentState), (ViewGroup) null, fragment.mSavedFragmentState);
            if (fragment.mView != null) {
                fragment.mInnerView = fragment.mView;
                fragment.mView.setSaveFromParentEnabled(false);
                if (fragment.mHidden) {
                    fragment.mView.setVisibility(8);
                }
                fragment.onViewCreated(fragment.mView, fragment.mSavedFragmentState);
                dispatchOnFragmentViewCreated(fragment, fragment.mView, fragment.mSavedFragmentState, false);
                return;
            }
            fragment.mInnerView = null;
        }
    }

    /* access modifiers changed from: package-private */
    public void completeShowHideFragment(final Fragment fragment) {
        if (fragment.mView != null) {
            AnimationOrAnimator loadAnimation = loadAnimation(fragment, fragment.getNextTransition(), !fragment.mHidden, fragment.getNextTransitionStyle());
            if (loadAnimation == null || loadAnimation.animator == null) {
                if (loadAnimation != null) {
                    setHWLayerAnimListenerIfAlpha(fragment.mView, loadAnimation);
                    fragment.mView.startAnimation(loadAnimation.animation);
                    loadAnimation.animation.start();
                }
                fragment.mView.setVisibility((!fragment.mHidden || fragment.isHideReplaced()) ? 0 : 8);
                if (fragment.isHideReplaced()) {
                    fragment.setHideReplaced(false);
                }
            } else {
                loadAnimation.animator.setTarget(fragment.mView);
                if (!fragment.mHidden) {
                    fragment.mView.setVisibility(0);
                } else if (fragment.isHideReplaced()) {
                    fragment.setHideReplaced(false);
                } else {
                    final ViewGroup viewGroup = fragment.mContainer;
                    final View view = fragment.mView;
                    viewGroup.startViewTransition(view);
                    loadAnimation.animator.addListener(new AnimatorListenerAdapter() {
                        public void onAnimationEnd(Animator animator) {
                            viewGroup.endViewTransition(view);
                            animator.removeListener(this);
                            if (fragment.mView != null) {
                                fragment.mView.setVisibility(8);
                            }
                        }
                    });
                }
                setHWLayerAnimListenerIfAlpha(fragment.mView, loadAnimation);
                loadAnimation.animator.start();
            }
        }
        if (fragment.mAdded && fragment.mHasMenu && fragment.mMenuVisible) {
            this.mNeedMenuInvalidate = true;
        }
        fragment.mHiddenChanged = false;
        fragment.onHiddenChanged(fragment.mHidden);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0033, code lost:
        r0 = r0.mView;
        r1 = r11.mContainer;
        r0 = r1.indexOfChild(r0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void moveFragmentToExpectedState(android.support.v4.app.Fragment r11) {
        /*
            r10 = this;
            if (r11 != 0) goto L_0x0003
            return
        L_0x0003:
            int r0 = r10.mCurState
            boolean r1 = r11.mRemoving
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L_0x001a
            boolean r1 = r11.isInBackStack()
            if (r1 == 0) goto L_0x0016
            int r0 = java.lang.Math.min(r0, r3)
            goto L_0x001a
        L_0x0016:
            int r0 = java.lang.Math.min(r0, r2)
        L_0x001a:
            r6 = r0
            int r7 = r11.getNextTransition()
            int r8 = r11.getNextTransitionStyle()
            r9 = 0
            r4 = r10
            r5 = r11
            r4.moveToState(r5, r6, r7, r8, r9)
            android.view.View r0 = r11.mView
            if (r0 == 0) goto L_0x0090
            android.support.v4.app.Fragment r0 = r10.findFragmentUnder(r11)
            if (r0 == 0) goto L_0x004b
            android.view.View r0 = r0.mView
            android.view.ViewGroup r1 = r11.mContainer
            int r0 = r1.indexOfChild(r0)
            android.view.View r4 = r11.mView
            int r4 = r1.indexOfChild(r4)
            if (r4 >= r0) goto L_0x004b
            r1.removeViewAt(r4)
            android.view.View r4 = r11.mView
            r1.addView(r4, r0)
        L_0x004b:
            boolean r0 = r11.mIsNewlyAdded
            if (r0 == 0) goto L_0x0090
            android.view.ViewGroup r0 = r11.mContainer
            if (r0 == 0) goto L_0x0090
            float r0 = r11.mPostponedAlpha
            r1 = 0
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 <= 0) goto L_0x0061
            android.view.View r0 = r11.mView
            float r4 = r11.mPostponedAlpha
            r0.setAlpha(r4)
        L_0x0061:
            r11.mPostponedAlpha = r1
            r11.mIsNewlyAdded = r2
            int r0 = r11.getNextTransition()
            int r1 = r11.getNextTransitionStyle()
            android.support.v4.app.FragmentManagerImpl$AnimationOrAnimator r0 = r10.loadAnimation(r11, r0, r3, r1)
            if (r0 == 0) goto L_0x0090
            android.view.View r1 = r11.mView
            setHWLayerAnimListenerIfAlpha(r1, r0)
            android.view.animation.Animation r1 = r0.animation
            if (r1 == 0) goto L_0x0084
            android.view.View r1 = r11.mView
            android.view.animation.Animation r0 = r0.animation
            r1.startAnimation(r0)
            goto L_0x0090
        L_0x0084:
            android.animation.Animator r1 = r0.animator
            android.view.View r2 = r11.mView
            r1.setTarget(r2)
            android.animation.Animator r0 = r0.animator
            r0.start()
        L_0x0090:
            boolean r0 = r11.mHiddenChanged
            if (r0 == 0) goto L_0x0097
            r10.completeShowHideFragment(r11)
        L_0x0097:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.app.FragmentManagerImpl.moveFragmentToExpectedState(android.support.v4.app.Fragment):void");
    }

    /* access modifiers changed from: package-private */
    public void moveToState(int i, boolean z) {
        if (this.mHost == null && i != 0) {
            throw new IllegalStateException("No activity");
        } else if (z || i != this.mCurState) {
            this.mCurState = i;
            if (this.mActive != null) {
                int size = this.mAdded.size();
                for (int i2 = 0; i2 < size; i2++) {
                    moveFragmentToExpectedState(this.mAdded.get(i2));
                }
                int size2 = this.mActive.size();
                for (int i3 = 0; i3 < size2; i3++) {
                    Fragment valueAt = this.mActive.valueAt(i3);
                    if (valueAt != null && ((valueAt.mRemoving || valueAt.mDetached) && !valueAt.mIsNewlyAdded)) {
                        moveFragmentToExpectedState(valueAt);
                    }
                }
                startPendingDeferredFragments();
                if (this.mNeedMenuInvalidate && this.mHost != null && this.mCurState == 5) {
                    this.mHost.onSupportInvalidateOptionsMenu();
                    this.mNeedMenuInvalidate = false;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void startPendingDeferredFragments() {
        if (this.mActive != null) {
            for (int i = 0; i < this.mActive.size(); i++) {
                Fragment valueAt = this.mActive.valueAt(i);
                if (valueAt != null) {
                    performPendingDeferredStart(valueAt);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void makeActive(Fragment fragment) {
        if (fragment.mIndex < 0) {
            int i = this.mNextFragmentIndex;
            this.mNextFragmentIndex = i + 1;
            fragment.setIndex(i, this.mParent);
            if (this.mActive == null) {
                this.mActive = new SparseArray<>();
            }
            this.mActive.put(fragment.mIndex, fragment);
            if (DEBUG) {
                Log.v(TAG, "Allocated fragment index " + fragment);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void makeInactive(Fragment fragment) {
        if (fragment.mIndex >= 0) {
            if (DEBUG) {
                Log.v(TAG, "Freeing fragment index " + fragment);
            }
            this.mActive.put(fragment.mIndex, (Object) null);
            fragment.initState();
        }
    }

    public void addFragment(Fragment fragment, boolean z) {
        if (DEBUG) {
            Log.v(TAG, "add: " + fragment);
        }
        makeActive(fragment);
        if (fragment.mDetached) {
            return;
        }
        if (!this.mAdded.contains(fragment)) {
            synchronized (this.mAdded) {
                this.mAdded.add(fragment);
            }
            fragment.mAdded = true;
            fragment.mRemoving = false;
            if (fragment.mView == null) {
                fragment.mHiddenChanged = false;
            }
            if (fragment.mHasMenu && fragment.mMenuVisible) {
                this.mNeedMenuInvalidate = true;
            }
            if (z) {
                moveToState(fragment);
                return;
            }
            return;
        }
        throw new IllegalStateException("Fragment already added: " + fragment);
    }

    public void removeFragment(Fragment fragment) {
        if (DEBUG) {
            Log.v(TAG, "remove: " + fragment + " nesting=" + fragment.mBackStackNesting);
        }
        boolean z = !fragment.isInBackStack();
        if (!fragment.mDetached || z) {
            synchronized (this.mAdded) {
                this.mAdded.remove(fragment);
            }
            if (fragment.mHasMenu && fragment.mMenuVisible) {
                this.mNeedMenuInvalidate = true;
            }
            fragment.mAdded = false;
            fragment.mRemoving = true;
        }
    }

    public void hideFragment(Fragment fragment) {
        if (DEBUG) {
            Log.v(TAG, "hide: " + fragment);
        }
        if (!fragment.mHidden) {
            fragment.mHidden = true;
            fragment.mHiddenChanged = true ^ fragment.mHiddenChanged;
        }
    }

    public void showFragment(Fragment fragment) {
        if (DEBUG) {
            Log.v(TAG, "show: " + fragment);
        }
        if (fragment.mHidden) {
            fragment.mHidden = false;
            fragment.mHiddenChanged = !fragment.mHiddenChanged;
        }
    }

    public void detachFragment(Fragment fragment) {
        if (DEBUG) {
            Log.v(TAG, "detach: " + fragment);
        }
        if (!fragment.mDetached) {
            fragment.mDetached = true;
            if (fragment.mAdded) {
                if (DEBUG) {
                    Log.v(TAG, "remove from detach: " + fragment);
                }
                synchronized (this.mAdded) {
                    this.mAdded.remove(fragment);
                }
                if (fragment.mHasMenu && fragment.mMenuVisible) {
                    this.mNeedMenuInvalidate = true;
                }
                fragment.mAdded = false;
            }
        }
    }

    public void attachFragment(Fragment fragment) {
        if (DEBUG) {
            Log.v(TAG, "attach: " + fragment);
        }
        if (fragment.mDetached) {
            fragment.mDetached = false;
            if (fragment.mAdded) {
                return;
            }
            if (!this.mAdded.contains(fragment)) {
                if (DEBUG) {
                    Log.v(TAG, "add from attach: " + fragment);
                }
                synchronized (this.mAdded) {
                    this.mAdded.add(fragment);
                }
                fragment.mAdded = true;
                if (fragment.mHasMenu && fragment.mMenuVisible) {
                    this.mNeedMenuInvalidate = true;
                    return;
                }
                return;
            }
            throw new IllegalStateException("Fragment already added: " + fragment);
        }
    }

    public Fragment findFragmentById(int i) {
        for (int size = this.mAdded.size() - 1; size >= 0; size--) {
            Fragment fragment = this.mAdded.get(size);
            if (fragment != null && fragment.mFragmentId == i) {
                return fragment;
            }
        }
        if (this.mActive == null) {
            return null;
        }
        for (int size2 = this.mActive.size() - 1; size2 >= 0; size2--) {
            Fragment valueAt = this.mActive.valueAt(size2);
            if (valueAt != null && valueAt.mFragmentId == i) {
                return valueAt;
            }
        }
        return null;
    }

    public Fragment findFragmentByTag(String str) {
        if (str != null) {
            for (int size = this.mAdded.size() - 1; size >= 0; size--) {
                Fragment fragment = this.mAdded.get(size);
                if (fragment != null && str.equals(fragment.mTag)) {
                    return fragment;
                }
            }
        }
        if (this.mActive == null || str == null) {
            return null;
        }
        for (int size2 = this.mActive.size() - 1; size2 >= 0; size2--) {
            Fragment valueAt = this.mActive.valueAt(size2);
            if (valueAt != null && str.equals(valueAt.mTag)) {
                return valueAt;
            }
        }
        return null;
    }

    public Fragment findFragmentByWho(String str) {
        Fragment findFragmentByWho;
        if (this.mActive == null || str == null) {
            return null;
        }
        for (int size = this.mActive.size() - 1; size >= 0; size--) {
            Fragment valueAt = this.mActive.valueAt(size);
            if (valueAt != null && (findFragmentByWho = valueAt.findFragmentByWho(str)) != null) {
                return findFragmentByWho;
            }
        }
        return null;
    }

    private void checkStateLoss() {
        if (isStateSaved()) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        } else if (this.mNoTransactionsBecause != null) {
            throw new IllegalStateException("Can not perform this action inside of " + this.mNoTransactionsBecause);
        }
    }

    public boolean isStateSaved() {
        return this.mStateSaved || this.mStopped;
    }

    public void enqueueAction(OpGenerator opGenerator, boolean z) {
        if (!z) {
            checkStateLoss();
        }
        synchronized (this) {
            if (!this.mDestroyed) {
                if (this.mHost != null) {
                    if (this.mPendingActions == null) {
                        this.mPendingActions = new ArrayList<>();
                    }
                    this.mPendingActions.add(opGenerator);
                    scheduleCommit();
                    return;
                }
            }
            if (!z) {
                throw new IllegalStateException("Activity has been destroyed");
            }
        }
    }

    /* access modifiers changed from: private */
    public void scheduleCommit() {
        synchronized (this) {
            boolean z = false;
            boolean z2 = this.mPostponedTransactions != null && !this.mPostponedTransactions.isEmpty();
            if (this.mPendingActions != null && this.mPendingActions.size() == 1) {
                z = true;
            }
            if (z2 || z) {
                this.mHost.getHandler().removeCallbacks(this.mExecCommit);
                this.mHost.getHandler().post(this.mExecCommit);
            }
        }
    }

    public int allocBackStackIndex(BackStackRecord backStackRecord) {
        synchronized (this) {
            if (this.mAvailBackStackIndices != null) {
                if (this.mAvailBackStackIndices.size() > 0) {
                    int intValue = this.mAvailBackStackIndices.remove(this.mAvailBackStackIndices.size() - 1).intValue();
                    if (DEBUG) {
                        Log.v(TAG, "Adding back stack index " + intValue + " with " + backStackRecord);
                    }
                    this.mBackStackIndices.set(intValue, backStackRecord);
                    return intValue;
                }
            }
            if (this.mBackStackIndices == null) {
                this.mBackStackIndices = new ArrayList<>();
            }
            int size = this.mBackStackIndices.size();
            if (DEBUG) {
                Log.v(TAG, "Setting back stack index " + size + " to " + backStackRecord);
            }
            this.mBackStackIndices.add(backStackRecord);
            return size;
        }
    }

    public void setBackStackIndex(int i, BackStackRecord backStackRecord) {
        synchronized (this) {
            if (this.mBackStackIndices == null) {
                this.mBackStackIndices = new ArrayList<>();
            }
            int size = this.mBackStackIndices.size();
            if (i < size) {
                if (DEBUG) {
                    Log.v(TAG, "Setting back stack index " + i + " to " + backStackRecord);
                }
                this.mBackStackIndices.set(i, backStackRecord);
            } else {
                while (size < i) {
                    this.mBackStackIndices.add((Object) null);
                    if (this.mAvailBackStackIndices == null) {
                        this.mAvailBackStackIndices = new ArrayList<>();
                    }
                    if (DEBUG) {
                        Log.v(TAG, "Adding available back stack index " + size);
                    }
                    this.mAvailBackStackIndices.add(Integer.valueOf(size));
                    size++;
                }
                if (DEBUG) {
                    Log.v(TAG, "Adding back stack index " + i + " with " + backStackRecord);
                }
                this.mBackStackIndices.add(backStackRecord);
            }
        }
    }

    public void freeBackStackIndex(int i) {
        synchronized (this) {
            this.mBackStackIndices.set(i, (Object) null);
            if (this.mAvailBackStackIndices == null) {
                this.mAvailBackStackIndices = new ArrayList<>();
            }
            if (DEBUG) {
                Log.v(TAG, "Freeing back stack index " + i);
            }
            this.mAvailBackStackIndices.add(Integer.valueOf(i));
        }
    }

    private void ensureExecReady(boolean z) {
        if (this.mExecutingActions) {
            throw new IllegalStateException("FragmentManager is already executing transactions");
        } else if (this.mHost == null) {
            throw new IllegalStateException("Fragment host has been destroyed");
        } else if (Looper.myLooper() == this.mHost.getHandler().getLooper()) {
            if (!z) {
                checkStateLoss();
            }
            if (this.mTmpRecords == null) {
                this.mTmpRecords = new ArrayList<>();
                this.mTmpIsPop = new ArrayList<>();
            }
            this.mExecutingActions = true;
            try {
                executePostponedTransaction((ArrayList<BackStackRecord>) null, (ArrayList<Boolean>) null);
            } finally {
                this.mExecutingActions = false;
            }
        } else {
            throw new IllegalStateException("Must be called from main thread of fragment host");
        }
    }

    public void execSingleAction(OpGenerator opGenerator, boolean z) {
        if (!z || (this.mHost != null && !this.mDestroyed)) {
            ensureExecReady(z);
            if (opGenerator.generateOps(this.mTmpRecords, this.mTmpIsPop)) {
                this.mExecutingActions = true;
                try {
                    removeRedundantOperationsAndExecute(this.mTmpRecords, this.mTmpIsPop);
                } finally {
                    cleanupExec();
                }
            }
            doPendingDeferredStart();
            burpActive();
        }
    }

    private void cleanupExec() {
        this.mExecutingActions = false;
        this.mTmpIsPop.clear();
        this.mTmpRecords.clear();
    }

    /* JADX INFO: finally extract failed */
    public boolean execPendingActions() {
        ensureExecReady(true);
        boolean z = false;
        while (generateOpsForPendingActions(this.mTmpRecords, this.mTmpIsPop)) {
            this.mExecutingActions = true;
            try {
                removeRedundantOperationsAndExecute(this.mTmpRecords, this.mTmpIsPop);
                cleanupExec();
                z = true;
            } catch (Throwable th) {
                cleanupExec();
                throw th;
            }
        }
        doPendingDeferredStart();
        burpActive();
        return z;
    }

    private void executePostponedTransaction(ArrayList<BackStackRecord> arrayList, ArrayList<Boolean> arrayList2) {
        int indexOf;
        int indexOf2;
        int size = this.mPostponedTransactions == null ? 0 : this.mPostponedTransactions.size();
        int i = 0;
        while (i < size) {
            StartEnterTransitionListener startEnterTransitionListener = this.mPostponedTransactions.get(i);
            if (arrayList != null && !startEnterTransitionListener.mIsBack && (indexOf2 = arrayList.indexOf(startEnterTransitionListener.mRecord)) != -1 && arrayList2.get(indexOf2).booleanValue()) {
                startEnterTransitionListener.cancelTransaction();
            } else if (startEnterTransitionListener.isReady() || (arrayList != null && startEnterTransitionListener.mRecord.interactsWith(arrayList, 0, arrayList.size()))) {
                this.mPostponedTransactions.remove(i);
                i--;
                size--;
                if (arrayList == null || startEnterTransitionListener.mIsBack || (indexOf = arrayList.indexOf(startEnterTransitionListener.mRecord)) == -1 || !arrayList2.get(indexOf).booleanValue()) {
                    startEnterTransitionListener.completeTransaction();
                } else {
                    startEnterTransitionListener.cancelTransaction();
                }
            }
            i++;
        }
    }

    private void removeRedundantOperationsAndExecute(ArrayList<BackStackRecord> arrayList, ArrayList<Boolean> arrayList2) {
        if (arrayList != null && !arrayList.isEmpty()) {
            if (arrayList2 == null || arrayList.size() != arrayList2.size()) {
                throw new IllegalStateException("Internal error with the back stack records");
            }
            executePostponedTransaction(arrayList, arrayList2);
            int size = arrayList.size();
            int i = 0;
            int i2 = 0;
            while (i < size) {
                if (!arrayList.get(i).mReorderingAllowed) {
                    if (i2 != i) {
                        executeOpsTogether(arrayList, arrayList2, i2, i);
                    }
                    i2 = i + 1;
                    if (arrayList2.get(i).booleanValue()) {
                        while (i2 < size && arrayList2.get(i2).booleanValue() && !arrayList.get(i2).mReorderingAllowed) {
                            i2++;
                        }
                    }
                    executeOpsTogether(arrayList, arrayList2, i, i2);
                    i = i2 - 1;
                }
                i++;
            }
            if (i2 != size) {
                executeOpsTogether(arrayList, arrayList2, i2, size);
            }
        }
    }

    private void executeOpsTogether(ArrayList<BackStackRecord> arrayList, ArrayList<Boolean> arrayList2, int i, int i2) {
        int i3;
        ArrayList<BackStackRecord> arrayList3 = arrayList;
        ArrayList<Boolean> arrayList4 = arrayList2;
        int i4 = i;
        int i5 = i2;
        boolean z = arrayList3.get(i4).mReorderingAllowed;
        if (this.mTmpAddedFragments == null) {
            this.mTmpAddedFragments = new ArrayList<>();
        } else {
            this.mTmpAddedFragments.clear();
        }
        this.mTmpAddedFragments.addAll(this.mAdded);
        Fragment primaryNavigationFragment = getPrimaryNavigationFragment();
        boolean z2 = false;
        for (int i6 = i4; i6 < i5; i6++) {
            BackStackRecord backStackRecord = arrayList3.get(i6);
            if (!arrayList4.get(i6).booleanValue()) {
                primaryNavigationFragment = backStackRecord.expandOps(this.mTmpAddedFragments, primaryNavigationFragment);
            } else {
                primaryNavigationFragment = backStackRecord.trackAddedFragmentsInPop(this.mTmpAddedFragments, primaryNavigationFragment);
            }
            z2 = z2 || backStackRecord.mAddToBackStack;
        }
        this.mTmpAddedFragments.clear();
        if (!z) {
            FragmentTransition.startTransitions(this, arrayList, arrayList2, i, i2, false);
        }
        executeOps(arrayList, arrayList2, i, i2);
        if (z) {
            ArraySet arraySet = new ArraySet();
            addAddedFragments(arraySet);
            int postponePostponableTransactions = postponePostponableTransactions(arrayList, arrayList2, i, i2, arraySet);
            makeRemovedFragmentsInvisible(arraySet);
            i3 = postponePostponableTransactions;
        } else {
            i3 = i5;
        }
        if (i3 != i4 && z) {
            FragmentTransition.startTransitions(this, arrayList, arrayList2, i, i3, true);
            moveToState(this.mCurState, true);
        }
        while (i4 < i5) {
            BackStackRecord backStackRecord2 = arrayList3.get(i4);
            if (arrayList4.get(i4).booleanValue() && backStackRecord2.mIndex >= 0) {
                freeBackStackIndex(backStackRecord2.mIndex);
                backStackRecord2.mIndex = -1;
            }
            backStackRecord2.runOnCommitRunnables();
            i4++;
        }
        if (z2) {
            reportBackStackChanged();
        }
    }

    private void makeRemovedFragmentsInvisible(ArraySet<Fragment> arraySet) {
        int size = arraySet.size();
        for (int i = 0; i < size; i++) {
            Fragment valueAt = arraySet.valueAt(i);
            if (!valueAt.mAdded) {
                View view = valueAt.getView();
                valueAt.mPostponedAlpha = view.getAlpha();
                view.setAlpha(0.0f);
            }
        }
    }

    private int postponePostponableTransactions(ArrayList<BackStackRecord> arrayList, ArrayList<Boolean> arrayList2, int i, int i2, ArraySet<Fragment> arraySet) {
        int i3 = i2;
        for (int i4 = i2 - 1; i4 >= i; i4--) {
            BackStackRecord backStackRecord = arrayList.get(i4);
            boolean booleanValue = arrayList2.get(i4).booleanValue();
            if (backStackRecord.isPostponed() && !backStackRecord.interactsWith(arrayList, i4 + 1, i2)) {
                if (this.mPostponedTransactions == null) {
                    this.mPostponedTransactions = new ArrayList<>();
                }
                StartEnterTransitionListener startEnterTransitionListener = new StartEnterTransitionListener(backStackRecord, booleanValue);
                this.mPostponedTransactions.add(startEnterTransitionListener);
                backStackRecord.setOnStartPostponedListener(startEnterTransitionListener);
                if (booleanValue) {
                    backStackRecord.executeOps();
                } else {
                    backStackRecord.executePopOps(false);
                }
                i3--;
                if (i4 != i3) {
                    arrayList.remove(i4);
                    arrayList.add(i3, backStackRecord);
                }
                addAddedFragments(arraySet);
            }
        }
        return i3;
    }

    /* access modifiers changed from: private */
    public void completeExecute(BackStackRecord backStackRecord, boolean z, boolean z2, boolean z3) {
        if (z) {
            backStackRecord.executePopOps(z3);
        } else {
            backStackRecord.executeOps();
        }
        ArrayList arrayList = new ArrayList(1);
        ArrayList arrayList2 = new ArrayList(1);
        arrayList.add(backStackRecord);
        arrayList2.add(Boolean.valueOf(z));
        if (z2) {
            FragmentTransition.startTransitions(this, arrayList, arrayList2, 0, 1, true);
        }
        if (z3) {
            moveToState(this.mCurState, true);
        }
        if (this.mActive != null) {
            int size = this.mActive.size();
            for (int i = 0; i < size; i++) {
                Fragment valueAt = this.mActive.valueAt(i);
                if (valueAt != null && valueAt.mView != null && valueAt.mIsNewlyAdded && backStackRecord.interactsWith(valueAt.mContainerId)) {
                    if (valueAt.mPostponedAlpha > 0.0f) {
                        valueAt.mView.setAlpha(valueAt.mPostponedAlpha);
                    }
                    if (z3) {
                        valueAt.mPostponedAlpha = 0.0f;
                    } else {
                        valueAt.mPostponedAlpha = -1.0f;
                        valueAt.mIsNewlyAdded = false;
                    }
                }
            }
        }
    }

    private Fragment findFragmentUnder(Fragment fragment) {
        ViewGroup viewGroup = fragment.mContainer;
        View view = fragment.mView;
        if (viewGroup == null || view == null) {
            return null;
        }
        for (int indexOf = this.mAdded.indexOf(fragment) - 1; indexOf >= 0; indexOf--) {
            Fragment fragment2 = this.mAdded.get(indexOf);
            if (fragment2.mContainer == viewGroup && fragment2.mView != null) {
                return fragment2;
            }
        }
        return null;
    }

    private static void executeOps(ArrayList<BackStackRecord> arrayList, ArrayList<Boolean> arrayList2, int i, int i2) {
        while (i < i2) {
            BackStackRecord backStackRecord = arrayList.get(i);
            boolean z = true;
            if (arrayList2.get(i).booleanValue()) {
                backStackRecord.bumpBackStackNesting(-1);
                if (i != i2 - 1) {
                    z = false;
                }
                backStackRecord.executePopOps(z);
            } else {
                backStackRecord.bumpBackStackNesting(1);
                backStackRecord.executeOps();
            }
            i++;
        }
    }

    private void addAddedFragments(ArraySet<Fragment> arraySet) {
        if (this.mCurState >= 1) {
            int min = Math.min(this.mCurState, 4);
            int size = this.mAdded.size();
            for (int i = 0; i < size; i++) {
                Fragment fragment = this.mAdded.get(i);
                if (fragment.mState < min) {
                    moveToState(fragment, min, fragment.getNextAnim(), fragment.getNextTransition(), false);
                    if (fragment.mView != null && !fragment.mHidden && fragment.mIsNewlyAdded) {
                        arraySet.add(fragment);
                    }
                }
            }
        }
    }

    private void forcePostponedTransactions() {
        if (this.mPostponedTransactions != null) {
            while (!this.mPostponedTransactions.isEmpty()) {
                this.mPostponedTransactions.remove(0).completeTransaction();
            }
        }
    }

    private void endAnimatingAwayFragments() {
        int size = this.mActive == null ? 0 : this.mActive.size();
        for (int i = 0; i < size; i++) {
            Fragment valueAt = this.mActive.valueAt(i);
            if (valueAt != null) {
                if (valueAt.getAnimatingAway() != null) {
                    int stateAfterAnimating = valueAt.getStateAfterAnimating();
                    View animatingAway = valueAt.getAnimatingAway();
                    Animation animation = animatingAway.getAnimation();
                    if (animation != null) {
                        animation.cancel();
                        animatingAway.clearAnimation();
                    }
                    valueAt.setAnimatingAway((View) null);
                    moveToState(valueAt, stateAfterAnimating, 0, 0, false);
                } else if (valueAt.getAnimator() != null) {
                    valueAt.getAnimator().end();
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003b, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean generateOpsForPendingActions(java.util.ArrayList<android.support.v4.app.BackStackRecord> r5, java.util.ArrayList<java.lang.Boolean> r6) {
        /*
            r4 = this;
            monitor-enter(r4)
            java.util.ArrayList<android.support.v4.app.FragmentManagerImpl$OpGenerator> r0 = r4.mPendingActions     // Catch:{ all -> 0x003c }
            r1 = 0
            if (r0 == 0) goto L_0x003a
            java.util.ArrayList<android.support.v4.app.FragmentManagerImpl$OpGenerator> r0 = r4.mPendingActions     // Catch:{ all -> 0x003c }
            int r0 = r0.size()     // Catch:{ all -> 0x003c }
            if (r0 != 0) goto L_0x000f
            goto L_0x003a
        L_0x000f:
            java.util.ArrayList<android.support.v4.app.FragmentManagerImpl$OpGenerator> r0 = r4.mPendingActions     // Catch:{ all -> 0x003c }
            int r0 = r0.size()     // Catch:{ all -> 0x003c }
            r2 = 0
        L_0x0016:
            if (r1 >= r0) goto L_0x0028
            java.util.ArrayList<android.support.v4.app.FragmentManagerImpl$OpGenerator> r3 = r4.mPendingActions     // Catch:{ all -> 0x003c }
            java.lang.Object r3 = r3.get(r1)     // Catch:{ all -> 0x003c }
            android.support.v4.app.FragmentManagerImpl$OpGenerator r3 = (android.support.v4.app.FragmentManagerImpl.OpGenerator) r3     // Catch:{ all -> 0x003c }
            boolean r3 = r3.generateOps(r5, r6)     // Catch:{ all -> 0x003c }
            r2 = r2 | r3
            int r1 = r1 + 1
            goto L_0x0016
        L_0x0028:
            java.util.ArrayList<android.support.v4.app.FragmentManagerImpl$OpGenerator> r5 = r4.mPendingActions     // Catch:{ all -> 0x003c }
            r5.clear()     // Catch:{ all -> 0x003c }
            android.support.v4.app.FragmentHostCallback r5 = r4.mHost     // Catch:{ all -> 0x003c }
            android.os.Handler r5 = r5.getHandler()     // Catch:{ all -> 0x003c }
            java.lang.Runnable r6 = r4.mExecCommit     // Catch:{ all -> 0x003c }
            r5.removeCallbacks(r6)     // Catch:{ all -> 0x003c }
            monitor-exit(r4)     // Catch:{ all -> 0x003c }
            return r2
        L_0x003a:
            monitor-exit(r4)     // Catch:{ all -> 0x003c }
            return r1
        L_0x003c:
            r5 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x003c }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.app.FragmentManagerImpl.generateOpsForPendingActions(java.util.ArrayList, java.util.ArrayList):boolean");
    }

    /* access modifiers changed from: package-private */
    public void doPendingDeferredStart() {
        if (this.mHavePendingDeferredStart) {
            this.mHavePendingDeferredStart = false;
            startPendingDeferredFragments();
        }
    }

    /* access modifiers changed from: package-private */
    public void reportBackStackChanged() {
        if (this.mBackStackChangeListeners != null) {
            for (int i = 0; i < this.mBackStackChangeListeners.size(); i++) {
                this.mBackStackChangeListeners.get(i).onBackStackChanged();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void addBackStackState(BackStackRecord backStackRecord) {
        if (this.mBackStack == null) {
            this.mBackStack = new ArrayList<>();
        }
        this.mBackStack.add(backStackRecord);
    }

    /* access modifiers changed from: package-private */
    public boolean popBackStackState(ArrayList<BackStackRecord> arrayList, ArrayList<Boolean> arrayList2, String str, int i, int i2) {
        int i3;
        if (this.mBackStack == null) {
            return false;
        }
        if (str == null && i < 0 && (i2 & 1) == 0) {
            int size = this.mBackStack.size() - 1;
            if (size < 0) {
                return false;
            }
            arrayList.add(this.mBackStack.remove(size));
            arrayList2.add(true);
        } else {
            if (str != null || i >= 0) {
                int size2 = this.mBackStack.size() - 1;
                while (i3 >= 0) {
                    BackStackRecord backStackRecord = this.mBackStack.get(i3);
                    if ((str != null && str.equals(backStackRecord.getName())) || (i >= 0 && i == backStackRecord.mIndex)) {
                        break;
                    }
                    size2 = i3 - 1;
                }
                if (i3 < 0) {
                    return false;
                }
                if ((i2 & 1) != 0) {
                    i3--;
                    while (i3 >= 0) {
                        BackStackRecord backStackRecord2 = this.mBackStack.get(i3);
                        if ((str == null || !str.equals(backStackRecord2.getName())) && (i < 0 || i != backStackRecord2.mIndex)) {
                            break;
                        }
                        i3--;
                    }
                }
            } else {
                i3 = -1;
            }
            if (i3 == this.mBackStack.size() - 1) {
                return false;
            }
            for (int size3 = this.mBackStack.size() - 1; size3 > i3; size3--) {
                arrayList.add(this.mBackStack.remove(size3));
                arrayList2.add(true);
            }
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public FragmentManagerNonConfig retainNonConfig() {
        setRetaining(this.mSavedNonConfig);
        return this.mSavedNonConfig;
    }

    private static void setRetaining(FragmentManagerNonConfig fragmentManagerNonConfig) {
        if (fragmentManagerNonConfig != null) {
            List<Fragment> fragments = fragmentManagerNonConfig.getFragments();
            if (fragments != null) {
                for (Fragment fragment : fragments) {
                    fragment.mRetaining = true;
                }
            }
            List<FragmentManagerNonConfig> childNonConfigs = fragmentManagerNonConfig.getChildNonConfigs();
            if (childNonConfigs != null) {
                for (FragmentManagerNonConfig retaining : childNonConfigs) {
                    setRetaining(retaining);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void saveNonConfig() {
        ArrayList arrayList;
        ArrayList arrayList2;
        ArrayList arrayList3;
        FragmentManagerNonConfig fragmentManagerNonConfig;
        if (this.mActive != null) {
            arrayList3 = null;
            arrayList2 = null;
            arrayList = null;
            for (int i = 0; i < this.mActive.size(); i++) {
                Fragment valueAt = this.mActive.valueAt(i);
                if (valueAt != null) {
                    if (valueAt.mRetainInstance) {
                        if (arrayList3 == null) {
                            arrayList3 = new ArrayList();
                        }
                        arrayList3.add(valueAt);
                        valueAt.mTargetIndex = valueAt.mTarget != null ? valueAt.mTarget.mIndex : -1;
                        if (DEBUG) {
                            Log.v(TAG, "retainNonConfig: keeping retained " + valueAt);
                        }
                    }
                    if (valueAt.mChildFragmentManager != null) {
                        valueAt.mChildFragmentManager.saveNonConfig();
                        fragmentManagerNonConfig = valueAt.mChildFragmentManager.mSavedNonConfig;
                    } else {
                        fragmentManagerNonConfig = valueAt.mChildNonConfig;
                    }
                    if (arrayList2 == null && fragmentManagerNonConfig != null) {
                        arrayList2 = new ArrayList(this.mActive.size());
                        for (int i2 = 0; i2 < i; i2++) {
                            arrayList2.add((Object) null);
                        }
                    }
                    if (arrayList2 != null) {
                        arrayList2.add(fragmentManagerNonConfig);
                    }
                    if (arrayList == null && valueAt.mViewModelStore != null) {
                        arrayList = new ArrayList(this.mActive.size());
                        for (int i3 = 0; i3 < i; i3++) {
                            arrayList.add((Object) null);
                        }
                    }
                    if (arrayList != null) {
                        arrayList.add(valueAt.mViewModelStore);
                    }
                }
            }
        } else {
            arrayList3 = null;
            arrayList2 = null;
            arrayList = null;
        }
        if (arrayList3 == null && arrayList2 == null && arrayList == null) {
            this.mSavedNonConfig = null;
        } else {
            this.mSavedNonConfig = new FragmentManagerNonConfig(arrayList3, arrayList2, arrayList);
        }
    }

    /* access modifiers changed from: package-private */
    public void saveFragmentViewState(Fragment fragment) {
        if (fragment.mInnerView != null) {
            if (this.mStateArray == null) {
                this.mStateArray = new SparseArray<>();
            } else {
                this.mStateArray.clear();
            }
            fragment.mInnerView.saveHierarchyState(this.mStateArray);
            if (this.mStateArray.size() > 0) {
                fragment.mSavedViewState = this.mStateArray;
                this.mStateArray = null;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public Bundle saveFragmentBasicState(Fragment fragment) {
        Bundle bundle;
        if (this.mStateBundle == null) {
            this.mStateBundle = new Bundle();
        }
        fragment.performSaveInstanceState(this.mStateBundle);
        dispatchOnFragmentSaveInstanceState(fragment, this.mStateBundle, false);
        if (!this.mStateBundle.isEmpty()) {
            bundle = this.mStateBundle;
            this.mStateBundle = null;
        } else {
            bundle = null;
        }
        if (fragment.mView != null) {
            saveFragmentViewState(fragment);
        }
        if (fragment.mSavedViewState != null) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putSparseParcelableArray(VIEW_STATE_TAG, fragment.mSavedViewState);
        }
        if (!fragment.mUserVisibleHint) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putBoolean(USER_VISIBLE_HINT_TAG, fragment.mUserVisibleHint);
        }
        return bundle;
    }

    /* access modifiers changed from: package-private */
    public Parcelable saveAllState() {
        int[] iArr;
        int size;
        forcePostponedTransactions();
        endAnimatingAwayFragments();
        execPendingActions();
        this.mStateSaved = true;
        BackStackState[] backStackStateArr = null;
        this.mSavedNonConfig = null;
        if (this.mActive == null || this.mActive.size() <= 0) {
            return null;
        }
        int size2 = this.mActive.size();
        FragmentState[] fragmentStateArr = new FragmentState[size2];
        boolean z = false;
        for (int i = 0; i < size2; i++) {
            Fragment valueAt = this.mActive.valueAt(i);
            if (valueAt != null) {
                if (valueAt.mIndex < 0) {
                    throwException(new IllegalStateException("Failure saving state: active " + valueAt + " has cleared index: " + valueAt.mIndex));
                }
                FragmentState fragmentState = new FragmentState(valueAt);
                fragmentStateArr[i] = fragmentState;
                if (valueAt.mState <= 0 || fragmentState.mSavedFragmentState != null) {
                    fragmentState.mSavedFragmentState = valueAt.mSavedFragmentState;
                } else {
                    fragmentState.mSavedFragmentState = saveFragmentBasicState(valueAt);
                    if (valueAt.mTarget != null) {
                        if (valueAt.mTarget.mIndex < 0) {
                            throwException(new IllegalStateException("Failure saving state: " + valueAt + " has target not in fragment manager: " + valueAt.mTarget));
                        }
                        if (fragmentState.mSavedFragmentState == null) {
                            fragmentState.mSavedFragmentState = new Bundle();
                        }
                        putFragment(fragmentState.mSavedFragmentState, TARGET_STATE_TAG, valueAt.mTarget);
                        if (valueAt.mTargetRequestCode != 0) {
                            fragmentState.mSavedFragmentState.putInt(TARGET_REQUEST_CODE_STATE_TAG, valueAt.mTargetRequestCode);
                        }
                    }
                }
                if (DEBUG) {
                    Log.v(TAG, "Saved state of " + valueAt + ": " + fragmentState.mSavedFragmentState);
                }
                z = true;
            }
        }
        if (!z) {
            if (DEBUG) {
                Log.v(TAG, "saveAllState: no fragments!");
            }
            return null;
        }
        int size3 = this.mAdded.size();
        if (size3 > 0) {
            iArr = new int[size3];
            for (int i2 = 0; i2 < size3; i2++) {
                iArr[i2] = this.mAdded.get(i2).mIndex;
                if (iArr[i2] < 0) {
                    throwException(new IllegalStateException("Failure saving state: active " + this.mAdded.get(i2) + " has cleared index: " + iArr[i2]));
                }
                if (DEBUG) {
                    Log.v(TAG, "saveAllState: adding fragment #" + i2 + ": " + this.mAdded.get(i2));
                }
            }
        } else {
            iArr = null;
        }
        if (this.mBackStack != null && (size = this.mBackStack.size()) > 0) {
            backStackStateArr = new BackStackState[size];
            for (int i3 = 0; i3 < size; i3++) {
                backStackStateArr[i3] = new BackStackState(this.mBackStack.get(i3));
                if (DEBUG) {
                    Log.v(TAG, "saveAllState: adding back stack #" + i3 + ": " + this.mBackStack.get(i3));
                }
            }
        }
        FragmentManagerState fragmentManagerState = new FragmentManagerState();
        fragmentManagerState.mActive = fragmentStateArr;
        fragmentManagerState.mAdded = iArr;
        fragmentManagerState.mBackStack = backStackStateArr;
        if (this.mPrimaryNav != null) {
            fragmentManagerState.mPrimaryNavActiveIndex = this.mPrimaryNav.mIndex;
        }
        fragmentManagerState.mNextFragmentIndex = this.mNextFragmentIndex;
        saveNonConfig();
        return fragmentManagerState;
    }

    /* access modifiers changed from: package-private */
    public void restoreAllState(Parcelable parcelable, FragmentManagerNonConfig fragmentManagerNonConfig) {
        List<ViewModelStore> list;
        List<FragmentManagerNonConfig> list2;
        if (parcelable != null) {
            FragmentManagerState fragmentManagerState = (FragmentManagerState) parcelable;
            if (fragmentManagerState.mActive != null) {
                if (fragmentManagerNonConfig != null) {
                    List<Fragment> fragments = fragmentManagerNonConfig.getFragments();
                    list2 = fragmentManagerNonConfig.getChildNonConfigs();
                    list = fragmentManagerNonConfig.getViewModelStores();
                    int size = fragments != null ? fragments.size() : 0;
                    for (int i = 0; i < size; i++) {
                        Fragment fragment = fragments.get(i);
                        if (DEBUG) {
                            Log.v(TAG, "restoreAllState: re-attaching retained " + fragment);
                        }
                        int i2 = 0;
                        while (i2 < fragmentManagerState.mActive.length && fragmentManagerState.mActive[i2].mIndex != fragment.mIndex) {
                            i2++;
                        }
                        if (i2 == fragmentManagerState.mActive.length) {
                            throwException(new IllegalStateException("Could not find active fragment with index " + fragment.mIndex));
                        }
                        FragmentState fragmentState = fragmentManagerState.mActive[i2];
                        fragmentState.mInstance = fragment;
                        fragment.mSavedViewState = null;
                        fragment.mBackStackNesting = 0;
                        fragment.mInLayout = false;
                        fragment.mAdded = false;
                        fragment.mTarget = null;
                        if (fragmentState.mSavedFragmentState != null) {
                            fragmentState.mSavedFragmentState.setClassLoader(this.mHost.getContext().getClassLoader());
                            fragment.mSavedViewState = fragmentState.mSavedFragmentState.getSparseParcelableArray(VIEW_STATE_TAG);
                            fragment.mSavedFragmentState = fragmentState.mSavedFragmentState;
                        }
                    }
                } else {
                    list2 = null;
                    list = null;
                }
                this.mActive = new SparseArray<>(fragmentManagerState.mActive.length);
                int i3 = 0;
                while (i3 < fragmentManagerState.mActive.length) {
                    FragmentState fragmentState2 = fragmentManagerState.mActive[i3];
                    if (fragmentState2 != null) {
                        Fragment instantiate = fragmentState2.instantiate(this.mHost, this.mContainer, this.mParent, (list2 == null || i3 >= list2.size()) ? null : list2.get(i3), (list == null || i3 >= list.size()) ? null : list.get(i3));
                        if (DEBUG) {
                            Log.v(TAG, "restoreAllState: active #" + i3 + ": " + instantiate);
                        }
                        this.mActive.put(instantiate.mIndex, instantiate);
                        fragmentState2.mInstance = null;
                    }
                    i3++;
                }
                if (fragmentManagerNonConfig != null) {
                    List<Fragment> fragments2 = fragmentManagerNonConfig.getFragments();
                    int size2 = fragments2 != null ? fragments2.size() : 0;
                    for (int i4 = 0; i4 < size2; i4++) {
                        Fragment fragment2 = fragments2.get(i4);
                        if (fragment2.mTargetIndex >= 0) {
                            fragment2.mTarget = this.mActive.get(fragment2.mTargetIndex);
                            if (fragment2.mTarget == null) {
                                Log.w(TAG, "Re-attaching retained fragment " + fragment2 + " target no longer exists: " + fragment2.mTargetIndex);
                            }
                        }
                    }
                }
                this.mAdded.clear();
                if (fragmentManagerState.mAdded != null) {
                    int i5 = 0;
                    while (i5 < fragmentManagerState.mAdded.length) {
                        Fragment fragment3 = this.mActive.get(fragmentManagerState.mAdded[i5]);
                        if (fragment3 == null) {
                            throwException(new IllegalStateException("No instantiated fragment for index #" + fragmentManagerState.mAdded[i5]));
                        }
                        fragment3.mAdded = true;
                        if (DEBUG) {
                            Log.v(TAG, "restoreAllState: added #" + i5 + ": " + fragment3);
                        }
                        if (!this.mAdded.contains(fragment3)) {
                            synchronized (this.mAdded) {
                                this.mAdded.add(fragment3);
                            }
                            i5++;
                        } else {
                            throw new IllegalStateException("Already added!");
                        }
                    }
                }
                if (fragmentManagerState.mBackStack != null) {
                    this.mBackStack = new ArrayList<>(fragmentManagerState.mBackStack.length);
                    for (int i6 = 0; i6 < fragmentManagerState.mBackStack.length; i6++) {
                        BackStackRecord instantiate2 = fragmentManagerState.mBackStack[i6].instantiate(this);
                        if (DEBUG) {
                            Log.v(TAG, "restoreAllState: back stack #" + i6 + " (index " + instantiate2.mIndex + "): " + instantiate2);
                            PrintWriter printWriter = new PrintWriter(new LogWriter(TAG));
                            instantiate2.dump("  ", printWriter, false);
                            printWriter.close();
                        }
                        this.mBackStack.add(instantiate2);
                        if (instantiate2.mIndex >= 0) {
                            setBackStackIndex(instantiate2.mIndex, instantiate2);
                        }
                    }
                } else {
                    this.mBackStack = null;
                }
                if (fragmentManagerState.mPrimaryNavActiveIndex >= 0) {
                    this.mPrimaryNav = this.mActive.get(fragmentManagerState.mPrimaryNavActiveIndex);
                }
                this.mNextFragmentIndex = fragmentManagerState.mNextFragmentIndex;
            }
        }
    }

    private void burpActive() {
        if (this.mActive != null) {
            for (int size = this.mActive.size() - 1; size >= 0; size--) {
                if (this.mActive.valueAt(size) == null) {
                    this.mActive.delete(this.mActive.keyAt(size));
                }
            }
        }
    }

    public void attachController(FragmentHostCallback fragmentHostCallback, FragmentContainer fragmentContainer, Fragment fragment) {
        if (this.mHost == null) {
            this.mHost = fragmentHostCallback;
            this.mContainer = fragmentContainer;
            this.mParent = fragment;
            return;
        }
        throw new IllegalStateException("Already attached");
    }

    public void noteStateNotSaved() {
        this.mSavedNonConfig = null;
        this.mStateSaved = false;
        this.mStopped = false;
        int size = this.mAdded.size();
        for (int i = 0; i < size; i++) {
            Fragment fragment = this.mAdded.get(i);
            if (fragment != null) {
                fragment.noteStateNotSaved();
            }
        }
    }

    public void dispatchCreate() {
        this.mStateSaved = false;
        this.mStopped = false;
        dispatchStateChange(1);
    }

    public void dispatchActivityCreated() {
        this.mStateSaved = false;
        this.mStopped = false;
        dispatchStateChange(2);
    }

    public void dispatchStart() {
        this.mStateSaved = false;
        this.mStopped = false;
        dispatchStateChange(4);
    }

    public void dispatchResume() {
        this.mStateSaved = false;
        this.mStopped = false;
        dispatchStateChange(5);
    }

    public void dispatchPause() {
        dispatchStateChange(4);
    }

    public void dispatchStop() {
        this.mStopped = true;
        dispatchStateChange(3);
    }

    public void dispatchReallyStop() {
        dispatchStateChange(2);
    }

    public void dispatchDestroyView() {
        dispatchStateChange(1);
    }

    public void dispatchDestroy() {
        this.mDestroyed = true;
        execPendingActions();
        dispatchStateChange(0);
        this.mHost = null;
        this.mContainer = null;
        this.mParent = null;
    }

    /* JADX INFO: finally extract failed */
    private void dispatchStateChange(int i) {
        try {
            this.mExecutingActions = true;
            moveToState(i, false);
            this.mExecutingActions = false;
            execPendingActions();
        } catch (Throwable th) {
            this.mExecutingActions = false;
            throw th;
        }
    }

    public void dispatchMultiWindowModeChanged(boolean z) {
        for (int size = this.mAdded.size() - 1; size >= 0; size--) {
            Fragment fragment = this.mAdded.get(size);
            if (fragment != null) {
                fragment.performMultiWindowModeChanged(z);
            }
        }
    }

    public void dispatchPictureInPictureModeChanged(boolean z) {
        for (int size = this.mAdded.size() - 1; size >= 0; size--) {
            Fragment fragment = this.mAdded.get(size);
            if (fragment != null) {
                fragment.performPictureInPictureModeChanged(z);
            }
        }
    }

    public void dispatchConfigurationChanged(Configuration configuration) {
        for (int i = 0; i < this.mAdded.size(); i++) {
            Fragment fragment = this.mAdded.get(i);
            if (fragment != null) {
                fragment.performConfigurationChanged(configuration);
            }
        }
    }

    public void dispatchLowMemory() {
        for (int i = 0; i < this.mAdded.size(); i++) {
            Fragment fragment = this.mAdded.get(i);
            if (fragment != null) {
                fragment.performLowMemory();
            }
        }
    }

    public boolean dispatchCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        if (this.mCurState < 1) {
            return false;
        }
        ArrayList<Fragment> arrayList = null;
        boolean z = false;
        for (int i = 0; i < this.mAdded.size(); i++) {
            Fragment fragment = this.mAdded.get(i);
            if (fragment != null && fragment.performCreateOptionsMenu(menu, menuInflater)) {
                if (arrayList == null) {
                    arrayList = new ArrayList<>();
                }
                arrayList.add(fragment);
                z = true;
            }
        }
        if (this.mCreatedMenus != null) {
            for (int i2 = 0; i2 < this.mCreatedMenus.size(); i2++) {
                Fragment fragment2 = this.mCreatedMenus.get(i2);
                if (arrayList == null || !arrayList.contains(fragment2)) {
                    fragment2.onDestroyOptionsMenu();
                }
            }
        }
        this.mCreatedMenus = arrayList;
        return z;
    }

    public boolean dispatchPrepareOptionsMenu(Menu menu) {
        if (this.mCurState < 1) {
            return false;
        }
        boolean z = false;
        for (int i = 0; i < this.mAdded.size(); i++) {
            Fragment fragment = this.mAdded.get(i);
            if (fragment != null && fragment.performPrepareOptionsMenu(menu)) {
                z = true;
            }
        }
        return z;
    }

    public boolean dispatchOptionsItemSelected(MenuItem menuItem) {
        if (this.mCurState < 1) {
            return false;
        }
        for (int i = 0; i < this.mAdded.size(); i++) {
            Fragment fragment = this.mAdded.get(i);
            if (fragment != null && fragment.performOptionsItemSelected(menuItem)) {
                return true;
            }
        }
        return false;
    }

    public boolean dispatchContextItemSelected(MenuItem menuItem) {
        if (this.mCurState < 1) {
            return false;
        }
        for (int i = 0; i < this.mAdded.size(); i++) {
            Fragment fragment = this.mAdded.get(i);
            if (fragment != null && fragment.performContextItemSelected(menuItem)) {
                return true;
            }
        }
        return false;
    }

    public void dispatchOptionsMenuClosed(Menu menu) {
        if (this.mCurState >= 1) {
            for (int i = 0; i < this.mAdded.size(); i++) {
                Fragment fragment = this.mAdded.get(i);
                if (fragment != null) {
                    fragment.performOptionsMenuClosed(menu);
                }
            }
        }
    }

    public void setPrimaryNavigationFragment(Fragment fragment) {
        if (fragment == null || (this.mActive.get(fragment.mIndex) == fragment && (fragment.mHost == null || fragment.getFragmentManager() == this))) {
            this.mPrimaryNav = fragment;
            return;
        }
        throw new IllegalArgumentException("Fragment " + fragment + " is not an active fragment of FragmentManager " + this);
    }

    public Fragment getPrimaryNavigationFragment() {
        return this.mPrimaryNav;
    }

    public void registerFragmentLifecycleCallbacks(FragmentManager.FragmentLifecycleCallbacks fragmentLifecycleCallbacks, boolean z) {
        this.mLifecycleCallbacks.add(new Pair(fragmentLifecycleCallbacks, Boolean.valueOf(z)));
    }

    public void unregisterFragmentLifecycleCallbacks(FragmentManager.FragmentLifecycleCallbacks fragmentLifecycleCallbacks) {
        synchronized (this.mLifecycleCallbacks) {
            int i = 0;
            int size = this.mLifecycleCallbacks.size();
            while (true) {
                if (i >= size) {
                    break;
                } else if (this.mLifecycleCallbacks.get(i).first == fragmentLifecycleCallbacks) {
                    this.mLifecycleCallbacks.remove(i);
                    break;
                } else {
                    i++;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void dispatchOnFragmentPreAttached(Fragment fragment, Context context, boolean z) {
        if (this.mParent != null) {
            FragmentManager fragmentManager = this.mParent.getFragmentManager();
            if (fragmentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) fragmentManager).dispatchOnFragmentPreAttached(fragment, context, true);
            }
        }
        Iterator<Pair<FragmentManager.FragmentLifecycleCallbacks, Boolean>> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            Pair next = it.next();
            if (!z || ((Boolean) next.second).booleanValue()) {
                ((FragmentManager.FragmentLifecycleCallbacks) next.first).onFragmentPreAttached(this, fragment, context);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void dispatchOnFragmentAttached(Fragment fragment, Context context, boolean z) {
        if (this.mParent != null) {
            FragmentManager fragmentManager = this.mParent.getFragmentManager();
            if (fragmentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) fragmentManager).dispatchOnFragmentAttached(fragment, context, true);
            }
        }
        Iterator<Pair<FragmentManager.FragmentLifecycleCallbacks, Boolean>> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            Pair next = it.next();
            if (!z || ((Boolean) next.second).booleanValue()) {
                ((FragmentManager.FragmentLifecycleCallbacks) next.first).onFragmentAttached(this, fragment, context);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void dispatchOnFragmentPreCreated(Fragment fragment, Bundle bundle, boolean z) {
        if (this.mParent != null) {
            FragmentManager fragmentManager = this.mParent.getFragmentManager();
            if (fragmentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) fragmentManager).dispatchOnFragmentPreCreated(fragment, bundle, true);
            }
        }
        Iterator<Pair<FragmentManager.FragmentLifecycleCallbacks, Boolean>> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            Pair next = it.next();
            if (!z || ((Boolean) next.second).booleanValue()) {
                ((FragmentManager.FragmentLifecycleCallbacks) next.first).onFragmentPreCreated(this, fragment, bundle);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void dispatchOnFragmentCreated(Fragment fragment, Bundle bundle, boolean z) {
        if (this.mParent != null) {
            FragmentManager fragmentManager = this.mParent.getFragmentManager();
            if (fragmentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) fragmentManager).dispatchOnFragmentCreated(fragment, bundle, true);
            }
        }
        Iterator<Pair<FragmentManager.FragmentLifecycleCallbacks, Boolean>> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            Pair next = it.next();
            if (!z || ((Boolean) next.second).booleanValue()) {
                ((FragmentManager.FragmentLifecycleCallbacks) next.first).onFragmentCreated(this, fragment, bundle);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void dispatchOnFragmentActivityCreated(Fragment fragment, Bundle bundle, boolean z) {
        if (this.mParent != null) {
            FragmentManager fragmentManager = this.mParent.getFragmentManager();
            if (fragmentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) fragmentManager).dispatchOnFragmentActivityCreated(fragment, bundle, true);
            }
        }
        Iterator<Pair<FragmentManager.FragmentLifecycleCallbacks, Boolean>> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            Pair next = it.next();
            if (!z || ((Boolean) next.second).booleanValue()) {
                ((FragmentManager.FragmentLifecycleCallbacks) next.first).onFragmentActivityCreated(this, fragment, bundle);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void dispatchOnFragmentViewCreated(Fragment fragment, View view, Bundle bundle, boolean z) {
        if (this.mParent != null) {
            FragmentManager fragmentManager = this.mParent.getFragmentManager();
            if (fragmentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) fragmentManager).dispatchOnFragmentViewCreated(fragment, view, bundle, true);
            }
        }
        Iterator<Pair<FragmentManager.FragmentLifecycleCallbacks, Boolean>> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            Pair next = it.next();
            if (!z || ((Boolean) next.second).booleanValue()) {
                ((FragmentManager.FragmentLifecycleCallbacks) next.first).onFragmentViewCreated(this, fragment, view, bundle);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void dispatchOnFragmentStarted(Fragment fragment, boolean z) {
        if (this.mParent != null) {
            FragmentManager fragmentManager = this.mParent.getFragmentManager();
            if (fragmentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) fragmentManager).dispatchOnFragmentStarted(fragment, true);
            }
        }
        Iterator<Pair<FragmentManager.FragmentLifecycleCallbacks, Boolean>> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            Pair next = it.next();
            if (!z || ((Boolean) next.second).booleanValue()) {
                ((FragmentManager.FragmentLifecycleCallbacks) next.first).onFragmentStarted(this, fragment);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void dispatchOnFragmentResumed(Fragment fragment, boolean z) {
        if (this.mParent != null) {
            FragmentManager fragmentManager = this.mParent.getFragmentManager();
            if (fragmentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) fragmentManager).dispatchOnFragmentResumed(fragment, true);
            }
        }
        Iterator<Pair<FragmentManager.FragmentLifecycleCallbacks, Boolean>> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            Pair next = it.next();
            if (!z || ((Boolean) next.second).booleanValue()) {
                ((FragmentManager.FragmentLifecycleCallbacks) next.first).onFragmentResumed(this, fragment);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void dispatchOnFragmentPaused(Fragment fragment, boolean z) {
        if (this.mParent != null) {
            FragmentManager fragmentManager = this.mParent.getFragmentManager();
            if (fragmentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) fragmentManager).dispatchOnFragmentPaused(fragment, true);
            }
        }
        Iterator<Pair<FragmentManager.FragmentLifecycleCallbacks, Boolean>> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            Pair next = it.next();
            if (!z || ((Boolean) next.second).booleanValue()) {
                ((FragmentManager.FragmentLifecycleCallbacks) next.first).onFragmentPaused(this, fragment);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void dispatchOnFragmentStopped(Fragment fragment, boolean z) {
        if (this.mParent != null) {
            FragmentManager fragmentManager = this.mParent.getFragmentManager();
            if (fragmentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) fragmentManager).dispatchOnFragmentStopped(fragment, true);
            }
        }
        Iterator<Pair<FragmentManager.FragmentLifecycleCallbacks, Boolean>> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            Pair next = it.next();
            if (!z || ((Boolean) next.second).booleanValue()) {
                ((FragmentManager.FragmentLifecycleCallbacks) next.first).onFragmentStopped(this, fragment);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void dispatchOnFragmentSaveInstanceState(Fragment fragment, Bundle bundle, boolean z) {
        if (this.mParent != null) {
            FragmentManager fragmentManager = this.mParent.getFragmentManager();
            if (fragmentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) fragmentManager).dispatchOnFragmentSaveInstanceState(fragment, bundle, true);
            }
        }
        Iterator<Pair<FragmentManager.FragmentLifecycleCallbacks, Boolean>> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            Pair next = it.next();
            if (!z || ((Boolean) next.second).booleanValue()) {
                ((FragmentManager.FragmentLifecycleCallbacks) next.first).onFragmentSaveInstanceState(this, fragment, bundle);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void dispatchOnFragmentViewDestroyed(Fragment fragment, boolean z) {
        if (this.mParent != null) {
            FragmentManager fragmentManager = this.mParent.getFragmentManager();
            if (fragmentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) fragmentManager).dispatchOnFragmentViewDestroyed(fragment, true);
            }
        }
        Iterator<Pair<FragmentManager.FragmentLifecycleCallbacks, Boolean>> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            Pair next = it.next();
            if (!z || ((Boolean) next.second).booleanValue()) {
                ((FragmentManager.FragmentLifecycleCallbacks) next.first).onFragmentViewDestroyed(this, fragment);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void dispatchOnFragmentDestroyed(Fragment fragment, boolean z) {
        if (this.mParent != null) {
            FragmentManager fragmentManager = this.mParent.getFragmentManager();
            if (fragmentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) fragmentManager).dispatchOnFragmentDestroyed(fragment, true);
            }
        }
        Iterator<Pair<FragmentManager.FragmentLifecycleCallbacks, Boolean>> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            Pair next = it.next();
            if (!z || ((Boolean) next.second).booleanValue()) {
                ((FragmentManager.FragmentLifecycleCallbacks) next.first).onFragmentDestroyed(this, fragment);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void dispatchOnFragmentDetached(Fragment fragment, boolean z) {
        if (this.mParent != null) {
            FragmentManager fragmentManager = this.mParent.getFragmentManager();
            if (fragmentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) fragmentManager).dispatchOnFragmentDetached(fragment, true);
            }
        }
        Iterator<Pair<FragmentManager.FragmentLifecycleCallbacks, Boolean>> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            Pair next = it.next();
            if (!z || ((Boolean) next.second).booleanValue()) {
                ((FragmentManager.FragmentLifecycleCallbacks) next.first).onFragmentDetached(this, fragment);
            }
        }
    }

    public View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        Fragment fragment;
        Context context2 = context;
        AttributeSet attributeSet2 = attributeSet;
        String str2 = str;
        if (!"fragment".equals(str)) {
            return null;
        }
        String attributeValue = attributeSet2.getAttributeValue((String) null, "class");
        TypedArray obtainStyledAttributes = context2.obtainStyledAttributes(attributeSet2, FragmentTag.Fragment);
        int i = 0;
        if (attributeValue == null) {
            attributeValue = obtainStyledAttributes.getString(0);
        }
        String str3 = attributeValue;
        int resourceId = obtainStyledAttributes.getResourceId(1, -1);
        String string = obtainStyledAttributes.getString(2);
        obtainStyledAttributes.recycle();
        if (!Fragment.isSupportFragmentClass(this.mHost.getContext(), str3)) {
            return null;
        }
        if (view != null) {
            i = view.getId();
        }
        if (i == -1 && resourceId == -1 && string == null) {
            throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Must specify unique android:id, android:tag, or have a parent with an id for " + str3);
        }
        Fragment findFragmentById = resourceId != -1 ? findFragmentById(resourceId) : null;
        if (findFragmentById == null && string != null) {
            findFragmentById = findFragmentByTag(string);
        }
        if (findFragmentById == null && i != -1) {
            findFragmentById = findFragmentById(i);
        }
        if (DEBUG) {
            Log.v(TAG, "onCreateView: id=0x" + Integer.toHexString(resourceId) + " fname=" + str3 + " existing=" + findFragmentById);
        }
        if (findFragmentById == null) {
            Fragment instantiate = this.mContainer.instantiate(context2, str3, (Bundle) null);
            instantiate.mFromLayout = true;
            instantiate.mFragmentId = resourceId != 0 ? resourceId : i;
            instantiate.mContainerId = i;
            instantiate.mTag = string;
            instantiate.mInLayout = true;
            instantiate.mFragmentManager = this;
            instantiate.mHost = this.mHost;
            instantiate.onInflate(this.mHost.getContext(), attributeSet2, instantiate.mSavedFragmentState);
            addFragment(instantiate, true);
            fragment = instantiate;
        } else if (!findFragmentById.mInLayout) {
            findFragmentById.mInLayout = true;
            findFragmentById.mHost = this.mHost;
            if (!findFragmentById.mRetaining) {
                findFragmentById.onInflate(this.mHost.getContext(), attributeSet2, findFragmentById.mSavedFragmentState);
            }
            fragment = findFragmentById;
        } else {
            throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Duplicate id 0x" + Integer.toHexString(resourceId) + ", tag " + string + ", or parent id 0x" + Integer.toHexString(i) + " with another fragment for " + str3);
        }
        if (this.mCurState >= 1 || !fragment.mFromLayout) {
            moveToState(fragment);
        } else {
            moveToState(fragment, 1, 0, 0, false);
        }
        if (fragment.mView != null) {
            if (resourceId != 0) {
                fragment.mView.setId(resourceId);
            }
            if (fragment.mView.getTag() == null) {
                fragment.mView.setTag(string);
            }
            return fragment.mView;
        }
        throw new IllegalStateException("Fragment " + str3 + " did not create a view.");
    }

    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView((View) null, str, context, attributeSet);
    }

    /* compiled from: FragmentManager */
    static class FragmentTag {
        public static final int[] Fragment = {16842755, 16842960, 16842961};
        public static final int Fragment_id = 1;
        public static final int Fragment_name = 0;
        public static final int Fragment_tag = 2;

        FragmentTag() {
        }
    }

    /* compiled from: FragmentManager */
    private class PopBackStackState implements OpGenerator {
        final int mFlags;
        final int mId;
        final String mName;

        PopBackStackState(String str, int i, int i2) {
            this.mName = str;
            this.mId = i;
            this.mFlags = i2;
        }

        public boolean generateOps(ArrayList<BackStackRecord> arrayList, ArrayList<Boolean> arrayList2) {
            FragmentManager peekChildFragmentManager;
            if (FragmentManagerImpl.this.mPrimaryNav != null && this.mId < 0 && this.mName == null && (peekChildFragmentManager = FragmentManagerImpl.this.mPrimaryNav.peekChildFragmentManager()) != null && peekChildFragmentManager.popBackStackImmediate()) {
                return false;
            }
            return FragmentManagerImpl.this.popBackStackState(arrayList, arrayList2, this.mName, this.mId, this.mFlags);
        }
    }

    /* compiled from: FragmentManager */
    static class StartEnterTransitionListener implements Fragment.OnStartEnterTransitionListener {
        /* access modifiers changed from: private */
        public final boolean mIsBack;
        private int mNumPostponed;
        /* access modifiers changed from: private */
        public final BackStackRecord mRecord;

        StartEnterTransitionListener(BackStackRecord backStackRecord, boolean z) {
            this.mIsBack = z;
            this.mRecord = backStackRecord;
        }

        public void onStartEnterTransition() {
            this.mNumPostponed--;
            if (this.mNumPostponed == 0) {
                this.mRecord.mManager.scheduleCommit();
            }
        }

        public void startListening() {
            this.mNumPostponed++;
        }

        public boolean isReady() {
            return this.mNumPostponed == 0;
        }

        public void completeTransaction() {
            boolean z = this.mNumPostponed > 0;
            FragmentManagerImpl fragmentManagerImpl = this.mRecord.mManager;
            int size = fragmentManagerImpl.mAdded.size();
            for (int i = 0; i < size; i++) {
                Fragment fragment = fragmentManagerImpl.mAdded.get(i);
                fragment.setOnStartEnterTransitionListener((Fragment.OnStartEnterTransitionListener) null);
                if (z && fragment.isPostponed()) {
                    fragment.startPostponedEnterTransition();
                }
            }
            this.mRecord.mManager.completeExecute(this.mRecord, this.mIsBack, !z, true);
        }

        public void cancelTransaction() {
            this.mRecord.mManager.completeExecute(this.mRecord, this.mIsBack, false, false);
        }
    }

    /* compiled from: FragmentManager */
    private static class AnimationOrAnimator {
        public final Animation animation;
        public final Animator animator;

        private AnimationOrAnimator(Animation animation2) {
            this.animation = animation2;
            this.animator = null;
            if (animation2 == null) {
                throw new IllegalStateException("Animation cannot be null");
            }
        }

        private AnimationOrAnimator(Animator animator2) {
            this.animation = null;
            this.animator = animator2;
            if (animator2 == null) {
                throw new IllegalStateException("Animator cannot be null");
            }
        }
    }

    /* compiled from: FragmentManager */
    private static class AnimationListenerWrapper implements Animation.AnimationListener {
        private final Animation.AnimationListener mWrapped;

        private AnimationListenerWrapper(Animation.AnimationListener animationListener) {
            this.mWrapped = animationListener;
        }

        @CallSuper
        public void onAnimationStart(Animation animation) {
            if (this.mWrapped != null) {
                this.mWrapped.onAnimationStart(animation);
            }
        }

        @CallSuper
        public void onAnimationEnd(Animation animation) {
            if (this.mWrapped != null) {
                this.mWrapped.onAnimationEnd(animation);
            }
        }

        @CallSuper
        public void onAnimationRepeat(Animation animation) {
            if (this.mWrapped != null) {
                this.mWrapped.onAnimationRepeat(animation);
            }
        }
    }

    /* compiled from: FragmentManager */
    private static class AnimateOnHWLayerIfNeededListener extends AnimationListenerWrapper {
        View mView;

        AnimateOnHWLayerIfNeededListener(View view, Animation.AnimationListener animationListener) {
            super(animationListener);
            this.mView = view;
        }

        @CallSuper
        public void onAnimationEnd(Animation animation) {
            if (ViewCompat.isAttachedToWindow(this.mView) || Build.VERSION.SDK_INT >= 24) {
                this.mView.post(new Runnable() {
                    public void run() {
                        AnimateOnHWLayerIfNeededListener.this.mView.setLayerType(0, (Paint) null);
                    }
                });
            } else {
                this.mView.setLayerType(0, (Paint) null);
            }
            super.onAnimationEnd(animation);
        }
    }

    /* compiled from: FragmentManager */
    private static class AnimatorOnHWLayerIfNeededListener extends AnimatorListenerAdapter {
        View mView;

        AnimatorOnHWLayerIfNeededListener(View view) {
            this.mView = view;
        }

        public void onAnimationStart(Animator animator) {
            this.mView.setLayerType(2, (Paint) null);
        }

        public void onAnimationEnd(Animator animator) {
            this.mView.setLayerType(0, (Paint) null);
            animator.removeListener(this);
        }
    }

    /* compiled from: FragmentManager */
    private static class EndViewTransitionAnimator extends AnimationSet implements Runnable {
        private final View mChild;
        private boolean mEnded;
        private final ViewGroup mParent;
        private boolean mTransitionEnded;

        EndViewTransitionAnimator(@NonNull Animation animation, @NonNull ViewGroup viewGroup, @NonNull View view) {
            super(false);
            this.mParent = viewGroup;
            this.mChild = view;
            addAnimation(animation);
        }

        public boolean getTransformation(long j, Transformation transformation) {
            if (this.mEnded) {
                return !this.mTransitionEnded;
            }
            if (!super.getTransformation(j, transformation)) {
                this.mEnded = true;
                OneShotPreDrawListener.add(this.mParent, this);
            }
            return true;
        }

        public boolean getTransformation(long j, Transformation transformation, float f) {
            if (this.mEnded) {
                return !this.mTransitionEnded;
            }
            if (!super.getTransformation(j, transformation, f)) {
                this.mEnded = true;
                OneShotPreDrawListener.add(this.mParent, this);
            }
            return true;
        }

        public void run() {
            this.mParent.endViewTransition(this.mChild);
            this.mTransitionEnded = true;
        }
    }
}

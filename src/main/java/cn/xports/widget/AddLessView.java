package cn.xports.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.xports.qd2.entity.K;
import cn.xports.qdplugin.R;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0001\u001dB%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0006\u0010\u0014\u001a\u00020\u0007J\b\u0010\u0015\u001a\u00020\u0016H\u0002J\u000e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0007J\u000e\u0010\u001a\u001a\u00020\u00182\u0006\u0010\u0011\u001a\u00020\u0012J\u000e\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u0007R\u001a\u0010\t\u001a\u00020\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u000b\"\u0004\b\u0010\u0010\rR\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcn/xports/widget/AddLessView;", "Landroid/widget/LinearLayout;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "max", "getMax", "()I", "setMax", "(I)V", "min", "getMin", "setMin", "onValueListener", "Lcn/xports/widget/AddLessView$OnValueListener;", "value", "getValue", "hasMax", "", "setMinNum", "", "num", "setOnValueListener", "setValue", "v", "OnValueListener", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: AddLessView.kt */
public final class AddLessView extends LinearLayout {
    private HashMap _$_findViewCache;
    private int max;
    private int min;
    /* access modifiers changed from: private */
    public OnValueListener onValueListener;
    /* access modifiers changed from: private */
    public int value;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcn/xports/widget/AddLessView$OnValueListener;", "", "onValueChange", "", "value", "", "xports_productRelease"}, k = 1, mv = {1, 1, 15})
    /* compiled from: AddLessView.kt */
    public interface OnValueListener {
        void onValueChange(int i);
    }

    @JvmOverloads
    public AddLessView(@NotNull Context context) {
        this(context, (AttributeSet) null, 0, 6, (DefaultConstructorMarker) null);
    }

    @JvmOverloads
    public AddLessView(@NotNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, (DefaultConstructorMarker) null);
    }

    public void _$_clearFindViewByIdCache() {
        if (this._$_findViewCache != null) {
            this._$_findViewCache.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ AddLessView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public AddLessView(@NotNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.max = -1;
        View.inflate(context, R.layout.view_add_less, this);
        TextView textView = (TextView) _$_findCachedViewById(R.id.tvValue);
        Intrinsics.checkExpressionValueIsNotNull(textView, "tvValue");
        textView.setText(K.k0);
        ((ImageView) _$_findCachedViewById(R.id.ivAdd)).setOnClickListener(new View.OnClickListener(this) {
            final /* synthetic */ AddLessView this$0;

            {
                this.this$0 = r1;
            }

            public final void onClick(View view) {
                AddLessView addLessView = this.this$0;
                addLessView.value = addLessView.value + 1;
                if (this.this$0.getMax() != -1 && this.this$0.value > this.this$0.getMax()) {
                    this.this$0.value = this.this$0.getMax();
                }
                TextView textView = (TextView) this.this$0._$_findCachedViewById(R.id.tvValue);
                Intrinsics.checkExpressionValueIsNotNull(textView, "tvValue");
                textView.setText(String.valueOf(this.this$0.value));
                OnValueListener access$getOnValueListener$p = this.this$0.onValueListener;
                if (access$getOnValueListener$p != null) {
                    access$getOnValueListener$p.onValueChange(this.this$0.value);
                }
            }
        });
        ((ImageView) _$_findCachedViewById(R.id.ivLess)).setOnClickListener(new View.OnClickListener(this) {
            final /* synthetic */ AddLessView this$0;

            {
                this.this$0 = r1;
            }

            public final void onClick(View view) {
                AddLessView addLessView = this.this$0;
                addLessView.value = addLessView.value - 1;
                if (this.this$0.value < this.this$0.getMin()) {
                    this.this$0.value = this.this$0.getMin();
                }
                TextView textView = (TextView) this.this$0._$_findCachedViewById(R.id.tvValue);
                Intrinsics.checkExpressionValueIsNotNull(textView, "tvValue");
                textView.setText(String.valueOf(this.this$0.value));
                OnValueListener access$getOnValueListener$p = this.this$0.onValueListener;
                if (access$getOnValueListener$p != null) {
                    access$getOnValueListener$p.onValueChange(this.this$0.value);
                }
            }
        });
    }

    public final int getMax() {
        return this.max;
    }

    public final void setMax(int i) {
        this.max = i;
    }

    public final int getMin() {
        return this.min;
    }

    public final void setMin(int i) {
        this.min = i;
    }

    public final int getValue() {
        return this.value;
    }

    public final void setValue(int i) {
        if ((!hasMax() || i <= this.max) && i >= this.min) {
            this.value = i;
            TextView textView = (TextView) _$_findCachedViewById(R.id.tvValue);
            if (textView != null) {
                textView.setText(String.valueOf(this.value));
            }
        }
    }

    public final void setOnValueListener(@NotNull OnValueListener onValueListener2) {
        Intrinsics.checkParameterIsNotNull(onValueListener2, "onValueListener");
        this.onValueListener = onValueListener2;
    }

    private final boolean hasMax() {
        return this.max != -1;
    }

    public final void setMinNum(int i) {
        if (i > 0) {
            this.min = i;
            setValue(i);
        }
    }
}

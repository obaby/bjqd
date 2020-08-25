package cn.xports.baselib.rxbind.widget;

import android.text.Editable;
import android.widget.TextView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, d2 = {"Lcn/xports/baselib/rxbind/widget/TextViewAfterTextChangeEvent;", "", "view", "Landroid/widget/TextView;", "editable", "Landroid/text/Editable;", "(Landroid/widget/TextView;Landroid/text/Editable;)V", "getEditable", "()Landroid/text/Editable;", "getView", "()Landroid/widget/TextView;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "baselib_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: TextViewAfterTextChangeEventObservable.kt */
public final class TextViewAfterTextChangeEvent {
    @Nullable
    private final Editable editable;
    @NotNull
    private final TextView view;

    public static /* synthetic */ TextViewAfterTextChangeEvent copy$default(TextViewAfterTextChangeEvent textViewAfterTextChangeEvent, TextView textView, Editable editable2, int i, Object obj) {
        if ((i & 1) != 0) {
            textView = textViewAfterTextChangeEvent.view;
        }
        if ((i & 2) != 0) {
            editable2 = textViewAfterTextChangeEvent.editable;
        }
        return textViewAfterTextChangeEvent.copy(textView, editable2);
    }

    @NotNull
    public final TextView component1() {
        return this.view;
    }

    @Nullable
    public final Editable component2() {
        return this.editable;
    }

    @NotNull
    public final TextViewAfterTextChangeEvent copy(@NotNull TextView textView, @Nullable Editable editable2) {
        Intrinsics.checkParameterIsNotNull(textView, "view");
        return new TextViewAfterTextChangeEvent(textView, editable2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TextViewAfterTextChangeEvent)) {
            return false;
        }
        TextViewAfterTextChangeEvent textViewAfterTextChangeEvent = (TextViewAfterTextChangeEvent) obj;
        return Intrinsics.areEqual(this.view, textViewAfterTextChangeEvent.view) && Intrinsics.areEqual(this.editable, textViewAfterTextChangeEvent.editable);
    }

    public int hashCode() {
        TextView textView = this.view;
        int i = 0;
        int hashCode = (textView != null ? textView.hashCode() : 0) * 31;
        Editable editable2 = this.editable;
        if (editable2 != null) {
            i = editable2.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        return "TextViewAfterTextChangeEvent(view=" + this.view + ", editable=" + this.editable + ")";
    }

    public TextViewAfterTextChangeEvent(@NotNull TextView textView, @Nullable Editable editable2) {
        Intrinsics.checkParameterIsNotNull(textView, "view");
        this.view = textView;
        this.editable = editable2;
    }

    @NotNull
    public final TextView getView() {
        return this.view;
    }

    @Nullable
    public final Editable getEditable() {
        return this.editable;
    }
}

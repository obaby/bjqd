package cn.xports.baselib.rxbind.widget;

import android.widget.TextView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007¢\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0007HÆ\u0003J;\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\u0007HÖ\u0001J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\t\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001f"}, d2 = {"Lcn/xports/baselib/rxbind/widget/TextViewTextChangeEvent;", "", "view", "Landroid/widget/TextView;", "text", "", "start", "", "before", "count", "(Landroid/widget/TextView;Ljava/lang/CharSequence;III)V", "getBefore", "()I", "getCount", "getStart", "getText", "()Ljava/lang/CharSequence;", "getView", "()Landroid/widget/TextView;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "", "baselib_release"}, k = 1, mv = {1, 1, 15})
/* compiled from: TextViewTextChangeEventObservable.kt */
public final class TextViewTextChangeEvent {
    private final int before;
    private final int count;
    private final int start;
    @NotNull
    private final CharSequence text;
    @NotNull
    private final TextView view;

    public static /* synthetic */ TextViewTextChangeEvent copy$default(TextViewTextChangeEvent textViewTextChangeEvent, TextView textView, CharSequence charSequence, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            textView = textViewTextChangeEvent.view;
        }
        if ((i4 & 2) != 0) {
            charSequence = textViewTextChangeEvent.text;
        }
        CharSequence charSequence2 = charSequence;
        if ((i4 & 4) != 0) {
            i = textViewTextChangeEvent.start;
        }
        int i5 = i;
        if ((i4 & 8) != 0) {
            i2 = textViewTextChangeEvent.before;
        }
        int i6 = i2;
        if ((i4 & 16) != 0) {
            i3 = textViewTextChangeEvent.count;
        }
        return textViewTextChangeEvent.copy(textView, charSequence2, i5, i6, i3);
    }

    @NotNull
    public final TextView component1() {
        return this.view;
    }

    @NotNull
    public final CharSequence component2() {
        return this.text;
    }

    public final int component3() {
        return this.start;
    }

    public final int component4() {
        return this.before;
    }

    public final int component5() {
        return this.count;
    }

    @NotNull
    public final TextViewTextChangeEvent copy(@NotNull TextView textView, @NotNull CharSequence charSequence, int i, int i2, int i3) {
        Intrinsics.checkParameterIsNotNull(textView, "view");
        Intrinsics.checkParameterIsNotNull(charSequence, "text");
        return new TextViewTextChangeEvent(textView, charSequence, i, i2, i3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof TextViewTextChangeEvent) {
                TextViewTextChangeEvent textViewTextChangeEvent = (TextViewTextChangeEvent) obj;
                if (Intrinsics.areEqual(this.view, textViewTextChangeEvent.view) && Intrinsics.areEqual(this.text, textViewTextChangeEvent.text)) {
                    if (this.start == textViewTextChangeEvent.start) {
                        if (this.before == textViewTextChangeEvent.before) {
                            if (this.count == textViewTextChangeEvent.count) {
                                return true;
                            }
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        TextView textView = this.view;
        int i = 0;
        int hashCode = (textView != null ? textView.hashCode() : 0) * 31;
        CharSequence charSequence = this.text;
        if (charSequence != null) {
            i = charSequence.hashCode();
        }
        return ((((((hashCode + i) * 31) + this.start) * 31) + this.before) * 31) + this.count;
    }

    @NotNull
    public String toString() {
        return "TextViewTextChangeEvent(view=" + this.view + ", text=" + this.text + ", start=" + this.start + ", before=" + this.before + ", count=" + this.count + ")";
    }

    public TextViewTextChangeEvent(@NotNull TextView textView, @NotNull CharSequence charSequence, int i, int i2, int i3) {
        Intrinsics.checkParameterIsNotNull(textView, "view");
        Intrinsics.checkParameterIsNotNull(charSequence, "text");
        this.view = textView;
        this.text = charSequence;
        this.start = i;
        this.before = i2;
        this.count = i3;
    }

    @NotNull
    public final TextView getView() {
        return this.view;
    }

    @NotNull
    public final CharSequence getText() {
        return this.text;
    }

    public final int getStart() {
        return this.start;
    }

    public final int getBefore() {
        return this.before;
    }

    public final int getCount() {
        return this.count;
    }
}

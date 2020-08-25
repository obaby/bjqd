package com.alibaba.fastjson.asm;

import android.support.v7.widget.ActivityChooserView;

final class Item {
    int hashCode;
    int index;
    int intVal;
    long longVal;
    Item next;
    String strVal1;
    String strVal2;
    String strVal3;
    int type;

    Item() {
    }

    Item(int i, Item item) {
        this.index = i;
        this.type = item.type;
        this.intVal = item.intVal;
        this.longVal = item.longVal;
        this.strVal1 = item.strVal1;
        this.strVal2 = item.strVal2;
        this.strVal3 = item.strVal3;
        this.hashCode = item.hashCode;
    }

    /* access modifiers changed from: package-private */
    public void set(int i, String str, String str2, String str3) {
        this.type = i;
        this.strVal1 = str;
        this.strVal2 = str2;
        this.strVal3 = str3;
        switch (i) {
            case 1:
            case 7:
            case 8:
            case 13:
                this.hashCode = (i + str.hashCode()) & ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
                return;
            case 12:
                this.hashCode = (i + (str.hashCode() * str2.hashCode())) & ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
                return;
            default:
                this.hashCode = (i + (str.hashCode() * str2.hashCode() * str3.hashCode())) & ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
                return;
        }
    }

    /* access modifiers changed from: package-private */
    public void set(int i) {
        this.type = 3;
        this.intVal = i;
        this.hashCode = Integer.MAX_VALUE & (this.type + i);
    }

    /* access modifiers changed from: package-private */
    public boolean isEqualTo(Item item) {
        switch (this.type) {
            case 1:
            case 7:
            case 8:
            case 13:
                return item.strVal1.equals(this.strVal1);
            case 3:
            case 4:
                if (item.intVal == this.intVal) {
                    return true;
                }
                return false;
            case 5:
            case 6:
            case 15:
                if (item.longVal == this.longVal) {
                    return true;
                }
                return false;
            case 12:
                if (!item.strVal1.equals(this.strVal1) || !item.strVal2.equals(this.strVal2)) {
                    return false;
                }
                return true;
            default:
                if (!item.strVal1.equals(this.strVal1) || !item.strVal2.equals(this.strVal2) || !item.strVal3.equals(this.strVal3)) {
                    return false;
                }
                return true;
        }
    }
}

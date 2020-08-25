package cn.xports.qd2;

import cn.xports.qd2.adapter.RechargeGiftBinder;
import cn.xports.qd2.adapter.RechargeValueBinder;
import cn.xports.qd2.entity.RechargeValue;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0007H\u0016¨\u0006\n"}, d2 = {"cn/xports/qd2/CardRechargeActivity$initData$1", "Lcn/xports/qd2/adapter/RechargeValueBinder$OnGiftClickListener;", "editInputListener", "", "s", "", "type", "", "haveGiftClick", "pos", "xports2_productRelease"}, k = 1, mv = {1, 1, 15})
/* compiled from: CardRechargeActivity.kt */
public final class CardRechargeActivity$initData$1 implements RechargeValueBinder.OnGiftClickListener {
    final /* synthetic */ RechargeGiftBinder $rechargeGiftBinder;
    final /* synthetic */ CardRechargeActivity this$0;

    CardRechargeActivity$initData$1(CardRechargeActivity cardRechargeActivity, RechargeGiftBinder rechargeGiftBinder) {
        this.this$0 = cardRechargeActivity;
        this.$rechargeGiftBinder = rechargeGiftBinder;
    }

    public void haveGiftClick(int i) {
        Object obj = this.this$0.valueList.get(i);
        Intrinsics.checkExpressionValueIsNotNull(obj, "valueList[pos]");
        RechargeValue rechargeValue = (RechargeValue) obj;
        String rechargeCampInfo = rechargeValue.getRechargeCampInfo();
        if (rechargeCampInfo == null) {
            rechargeCampInfo = rechargeValue.getPromInfo();
        }
        if (rechargeCampInfo == null) {
            rechargeCampInfo = "";
        }
        Iterator it = this.this$0.mItems.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            if (next instanceof String) {
                this.this$0.mItems.remove(next);
                break;
            }
        }
        if (!Intrinsics.areEqual(rechargeValue.getValue(), "其他金额")) {
            if (!(rechargeCampInfo.length() == 0)) {
                this.this$0.curSelectValue = rechargeValue;
                int i2 = i + 1;
                int i3 = i2 % 3;
                if (i3 == 0) {
                    this.this$0.mItems.add(i2, rechargeCampInfo);
                    this.$rechargeGiftBinder.setAlign(RechargeGiftBinder.Align.RIGHT);
                } else {
                    int i4 = ((i2 / 3) + 1) * 3;
                    if (i4 > this.this$0.mItems.size()) {
                        i4 = this.this$0.mItems.size();
                    }
                    this.this$0.mItems.add(i4, rechargeCampInfo);
                    if (i3 == 1) {
                        this.$rechargeGiftBinder.setAlign(RechargeGiftBinder.Align.LEFT);
                    } else {
                        this.$rechargeGiftBinder.setAlign(RechargeGiftBinder.Align.MID);
                    }
                }
                this.this$0.multiTypeAdapter.notifyDataSetChanged();
                return;
            }
        }
        this.this$0.multiTypeAdapter.notifyDataSetChanged();
    }

    public void editInputListener(@Nullable String str, int i) {
        if (str != null) {
            try {
                boolean z = true;
                this.this$0.isInputMoney = i != 1;
                if (i == 1) {
                    this.this$0.setTotalPrice(Integer.parseInt(str));
                } else {
                    if (str.length() != 0) {
                        z = false;
                    }
                    if (z) {
                        this.this$0.setTotalPrice(0);
                    } else if (Integer.parseInt(str) <= 21474836) {
                        this.this$0.setTotalPrice(Integer.parseInt(str) * 100);
                    }
                }
            } catch (Exception unused) {
                this.this$0.setTotalPrice(0);
            }
            if (str != null) {
                return;
            }
        }
        this.this$0.setTotalPrice(0);
        Unit unit = Unit.INSTANCE;
    }
}

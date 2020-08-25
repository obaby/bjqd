package cn.xports.qd2.dialog;

import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;
import cn.xports.baselib.adapter.XBaseAdapter;
import cn.xports.baselib.bean.DataMap;
import cn.xports.qd2.R;
import cn.xports.qd2.adapter.CouponTagAdapter;
import cn.xports.qd2.entity.Option;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.ScreenUtils;
import java.util.ArrayList;
import java.util.Iterator;

public class CouponDropWindow {
    /* access modifiers changed from: private */
    public boolean canShow = true;
    /* access modifiers changed from: private */
    public CouponTagAdapter couponClassAdapter = new CouponTagAdapter(this.couponClassList);
    /* access modifiers changed from: private */
    public ArrayList<Option<DataMap>> couponClassList = new ArrayList<>();
    private View dialogView = LayoutInflater.from(ActivityUtils.getTopActivity()).inflate(R.layout.dialog_drop_coupon, (ViewGroup) null);
    /* access modifiers changed from: private */
    public PopupWindow.OnDismissListener dismissListener;
    /* access modifiers changed from: private */
    public OnPopOptListener optListener;
    /* access modifiers changed from: private */
    public PopupWindow popupWindow;
    private RecyclerView rvCouponTypes;
    private RecyclerView rvServices;
    private RecyclerView rvVenues;
    /* access modifiers changed from: private */
    public Option<DataMap> selectCoupon;
    /* access modifiers changed from: private */
    public Option<DataMap> selectService;
    /* access modifiers changed from: private */
    public Option<DataMap> selectVenue;
    /* access modifiers changed from: private */
    public CouponTagAdapter serviceAdapter = new CouponTagAdapter(this.serviceList);
    /* access modifiers changed from: private */
    public ArrayList<Option<DataMap>> serviceList = new ArrayList<>();
    private View topView;
    private TextView tvConfirm;
    private TextView tvRest;
    private View vCouponTypes;
    private View vServices;
    /* access modifiers changed from: private */
    public CouponTagAdapter venueAdapter = new CouponTagAdapter(this.venueList);
    /* access modifiers changed from: private */
    public ArrayList<Option<DataMap>> venueList = new ArrayList<>();

    public interface OnPopOptListener {
        void onConfirm(Option<DataMap> option, Option<DataMap> option2, Option<DataMap> option3);

        void onReset();
    }

    public CouponDropWindow() {
        initView(this.dialogView);
    }

    private void initView(View view) {
        view.findViewById(R.id.v_empty).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (CouponDropWindow.this.popupWindow != null) {
                    CouponDropWindow.this.popupWindow.dismiss();
                }
            }
        });
        this.topView = view.findViewById(R.id.v_top);
        this.topView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (CouponDropWindow.this.popupWindow != null) {
                    CouponDropWindow.this.popupWindow.dismiss();
                }
            }
        });
        this.vServices = view.findViewById(R.id.ll_services);
        this.vCouponTypes = view.findViewById(R.id.ll_coupon_type);
        this.rvServices = (RecyclerView) view.findViewById(R.id.rv_services);
        this.rvServices.setAdapter(this.serviceAdapter);
        this.rvServices.setLayoutManager(new GridLayoutManager(this.dialogView.getContext(), 3));
        this.rvVenues = (RecyclerView) view.findViewById(R.id.rv_venues);
        this.rvVenues.setAdapter(this.venueAdapter);
        this.rvVenues.setLayoutManager(new GridLayoutManager(this.dialogView.getContext(), 3));
        this.rvCouponTypes = (RecyclerView) view.findViewById(R.id.rv_coupon_type);
        this.rvCouponTypes.setAdapter(this.couponClassAdapter);
        this.rvCouponTypes.setLayoutManager(new GridLayoutManager(this.dialogView.getContext(), 3));
        this.couponClassAdapter.setOnItemClickListener(new XBaseAdapter.OnItemClickListener<Option<DataMap>>() {
            public void onItemClick(Option<DataMap> option, int i, int i2) {
                Option unused = CouponDropWindow.this.selectCoupon = option;
            }
        });
        this.serviceAdapter.setOnItemClickListener(new XBaseAdapter.OnItemClickListener<Option<DataMap>>() {
            public void onItemClick(Option<DataMap> option, int i, int i2) {
                Option unused = CouponDropWindow.this.selectService = option;
            }
        });
        this.venueAdapter.setOnItemClickListener(new XBaseAdapter.OnItemClickListener<Option<DataMap>>() {
            public void onItemClick(Option<DataMap> option, int i, int i2) {
                Option unused = CouponDropWindow.this.selectVenue = option;
            }
        });
        this.tvConfirm = (TextView) view.findViewById(R.id.tv_confirm);
        this.tvRest = (TextView) view.findViewById(R.id.tv_reset);
        this.tvRest.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Iterator it = CouponDropWindow.this.serviceList.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Option option = (Option) it.next();
                    if (option.isSelect()) {
                        option.setSelect(false);
                        break;
                    }
                }
                Iterator it2 = CouponDropWindow.this.couponClassList.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    }
                    Option option2 = (Option) it2.next();
                    if (option2.isSelect()) {
                        option2.setSelect(false);
                        break;
                    }
                }
                Iterator it3 = CouponDropWindow.this.venueList.iterator();
                while (true) {
                    if (!it3.hasNext()) {
                        break;
                    }
                    Option option3 = (Option) it3.next();
                    if (option3.isSelect()) {
                        option3.setSelect(false);
                        break;
                    }
                }
                CouponDropWindow.this.serviceAdapter.notifyDataSetChanged();
                CouponDropWindow.this.couponClassAdapter.notifyDataSetChanged();
                CouponDropWindow.this.venueAdapter.notifyDataSetChanged();
                CouponDropWindow.this.popupWindow.dismiss();
                if (CouponDropWindow.this.optListener != null) {
                    CouponDropWindow.this.optListener.onReset();
                }
            }
        });
        this.tvConfirm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CouponDropWindow.this.popupWindow.dismiss();
                if (CouponDropWindow.this.optListener != null) {
                    CouponDropWindow.this.optListener.onConfirm(CouponDropWindow.this.selectCoupon, CouponDropWindow.this.selectVenue, CouponDropWindow.this.selectService);
                }
            }
        });
    }

    public CouponDropWindow setServiceList(ArrayList<Option<DataMap>> arrayList) {
        this.serviceList.clear();
        this.serviceList.addAll(arrayList);
        Iterator<Option<DataMap>> it = this.serviceList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Option<DataMap> next = it.next();
            if (next.isSelect()) {
                this.selectService = next;
                break;
            }
        }
        return this;
    }

    public CouponDropWindow setVenueList(ArrayList<Option<DataMap>> arrayList) {
        this.venueList.clear();
        this.venueList.addAll(arrayList);
        Iterator<Option<DataMap>> it = this.venueList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Option<DataMap> next = it.next();
            if (next.isSelect()) {
                this.selectVenue = next;
                break;
            }
        }
        return this;
    }

    public CouponDropWindow setCouponClassList(ArrayList<Option<DataMap>> arrayList) {
        this.couponClassList.clear();
        this.couponClassList.addAll(arrayList);
        Iterator<Option<DataMap>> it = this.couponClassList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Option<DataMap> next = it.next();
            if (next.isSelect()) {
                this.selectCoupon = next;
                break;
            }
        }
        return this;
    }

    public void show(final View view) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        ViewGroup.LayoutParams layoutParams = this.topView.getLayoutParams();
        layoutParams.height = (iArr[1] + view.getHeight()) - BarUtils.getStatusBarHeight();
        this.topView.setLayoutParams(layoutParams);
        if (this.popupWindow != null && this.popupWindow.isShowing()) {
            this.popupWindow.dismiss();
        } else if (!this.canShow) {
            this.canShow = true;
        } else {
            if (this.popupWindow == null) {
                this.popupWindow = new PopupWindow(this.dialogView, ScreenUtils.getScreenWidth(), ScreenUtils.getScreenHeight());
                this.popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    public void onDismiss() {
                        if (CouponDropWindow.this.dismissListener != null) {
                            CouponDropWindow.this.dismissListener.onDismiss();
                        }
                        boolean unused = CouponDropWindow.this.canShow = false;
                        view.postDelayed(new Runnable() {
                            public void run() {
                                boolean unused = CouponDropWindow.this.canShow = true;
                            }
                        }, 200);
                    }
                });
            }
            this.couponClassAdapter.notifyDataSetChanged();
            this.serviceAdapter.notifyDataSetChanged();
            this.venueAdapter.notifyDataSetChanged();
            this.popupWindow.setBackgroundDrawable(new BitmapDrawable());
            this.popupWindow.setOutsideTouchable(true);
            this.popupWindow.showAsDropDown(view);
        }
    }

    public CouponDropWindow setOptListener(OnPopOptListener onPopOptListener) {
        this.optListener = onPopOptListener;
        return this;
    }

    public void dismiss() {
        if (this.popupWindow != null) {
            this.popupWindow.dismiss();
        }
    }

    public boolean isShowing() {
        return this.popupWindow != null && this.popupWindow.isShowing();
    }

    public PopupWindow getPopupWindow() {
        return this.popupWindow;
    }

    public CouponDropWindow setOnDismissListener(PopupWindow.OnDismissListener onDismissListener) {
        this.dismissListener = onDismissListener;
        return this;
    }
}

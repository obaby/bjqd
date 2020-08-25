package cn.xports.qd2.widget;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import cn.xports.baselib.adapter.XBaseAdapter;
import cn.xports.qd2.R;
import cn.xports.qd2.adapter.DropDownAdapter;
import cn.xports.qd2.entity.Option;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.ScreenUtils;
import java.util.ArrayList;
import java.util.List;

public class DropDownView extends LinearLayout {
    private DropDownAdapter adapter;
    /* access modifiers changed from: private */
    public boolean canShow;
    /* access modifiers changed from: private */
    public ImageView ivDrop;
    private List<Option> options;
    /* access modifiers changed from: private */
    public View popTopView;
    /* access modifiers changed from: private */
    public PopupWindow popupWindow;
    private RecyclerView rvList;
    /* access modifiers changed from: private */
    public OnItemSelectListener selectListener;
    /* access modifiers changed from: private */
    public TextView tvTitle;

    public interface OnItemSelectListener {
        void OnItemSelect(Option option, int i);
    }

    public DropDownView(Context context) {
        this(context, (AttributeSet) null);
    }

    public DropDownView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DropDownView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.canShow = true;
        this.options = new ArrayList();
        this.adapter = new DropDownAdapter(this.options);
        inflate(context, R.layout.view_drop_down, this);
        this.ivDrop = (ImageView) findViewById(R.id.iv_arrow);
        this.tvTitle = (TextView) findViewById(R.id.tv_title);
        final View inflate = inflate(context, R.layout.dialog_drop_down, (ViewGroup) null);
        initDialogView(inflate);
        setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (DropDownView.this.popupWindow != null && DropDownView.this.popupWindow.isShowing()) {
                    DropDownView.this.popupWindow.dismiss();
                } else if (!DropDownView.this.canShow) {
                    boolean unused = DropDownView.this.canShow = true;
                } else {
                    if (DropDownView.this.popupWindow == null) {
                        int[] iArr = new int[2];
                        DropDownView.this.getLocationOnScreen(iArr);
                        ViewGroup.LayoutParams layoutParams = DropDownView.this.popTopView.getLayoutParams();
                        layoutParams.height = (iArr[1] + DropDownView.this.getHeight()) - BarUtils.getStatusBarHeight();
                        DropDownView.this.popTopView.setLayoutParams(layoutParams);
                        PopupWindow unused2 = DropDownView.this.popupWindow = new PopupWindow(inflate, ScreenUtils.getScreenWidth(), ScreenUtils.getScreenHeight());
                        DropDownView.this.popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                            public void onDismiss() {
                                DropDownView.this.ivDrop.setImageResource(R.drawable.ic_arrow_down);
                                boolean unused = DropDownView.this.canShow = false;
                                DropDownView.this.postDelayed(new Runnable() {
                                    public void run() {
                                        boolean unused = DropDownView.this.canShow = true;
                                    }
                                }, 200);
                            }
                        });
                    }
                    DropDownView.this.popupWindow.setBackgroundDrawable(new BitmapDrawable());
                    DropDownView.this.popupWindow.setOutsideTouchable(true);
                    DropDownView.this.popupWindow.showAsDropDown(DropDownView.this);
                    DropDownView.this.ivDrop.setImageResource(R.drawable.ic_arrow_up);
                }
            }
        });
    }

    public DropDownView setTitle(String str) {
        if (this.tvTitle != null) {
            this.tvTitle.setText(str);
        }
        return this;
    }

    public <T> DropDownView setData(List<Option<T>> list) {
        if (list != null) {
            this.options.clear();
            this.options.addAll(list);
            if (this.adapter != null) {
                this.adapter.notifyDataSetChanged();
            }
        }
        return this;
    }

    private void initDialogView(View view) {
        view.findViewById(R.id.v_empty).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (DropDownView.this.popupWindow != null) {
                    DropDownView.this.popupWindow.dismiss();
                }
            }
        });
        this.popTopView = view.findViewById(R.id.v_top);
        this.popTopView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (DropDownView.this.popupWindow != null) {
                    DropDownView.this.popupWindow.dismiss();
                }
            }
        });
        this.rvList = (RecyclerView) view.findViewById(R.id.recycler_view);
        this.rvList.setLayoutManager(new LinearLayoutManager(view.getContext()));
        this.rvList.setAdapter(this.adapter);
        this.adapter.setOnItemClickListener(new XBaseAdapter.OnItemClickListener<Option>() {
            public void onItemClick(Option option, int i, int i2) {
                if (DropDownView.this.popupWindow != null) {
                    DropDownView.this.popupWindow.dismiss();
                    if (DropDownView.this.tvTitle != null) {
                        DropDownView.this.tvTitle.setText(option.getName());
                    }
                    if (DropDownView.this.selectListener != null) {
                        DropDownView.this.selectListener.OnItemSelect(option, i);
                    }
                }
            }
        });
    }

    public void setOnItemSelectListener(OnItemSelectListener onItemSelectListener) {
        this.selectListener = onItemSelectListener;
    }
}

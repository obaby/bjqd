package cn.xports.qd2.adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;
import cn.xports.baselib.adapter.XBaseAdapter;
import cn.xports.baselib.adapter.XBaseHolder;
import cn.xports.baselib.util.MoneyUtil;
import cn.xports.qd2.R;
import cn.xports.qd2.entity.CampItemListResult;
import java.util.Iterator;
import java.util.List;

public class CampPackageAdapter extends XBaseAdapter<CampItemListResult.CampPackage> {
    /* access modifiers changed from: private */
    public OnItemClick onClickListener;

    public interface OnItemClick {
        void onClick(CampItemListResult.CampPackage campPackage);
    }

    public CampPackageAdapter(List<CampItemListResult.CampPackage> list) {
        super(list);
        if (list != null) {
            for (CampItemListResult.CampPackage checked : list) {
                checked.setChecked(false);
            }
        }
    }

    public int getLayoutId() {
        return R.layout.item_sport_package;
    }

    public void onBindViewHolder(@NonNull XBaseHolder xBaseHolder, int i) {
        final CampItemListResult.CampPackage campPackage = (CampItemListResult.CampPackage) this.list.get(i);
        TextView textView = (TextView) xBaseHolder.getView(R.id.tv_name);
        if (campPackage.getPrice() != 0) {
            String str = "  ¥" + MoneyUtil.cent2Yuan(campPackage.getPrice());
            SpannableString spannableString = new SpannableString(campPackage.getName() + str);
            ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.parseColor("#fd4772"));
            int indexOf = spannableString.toString().indexOf("¥");
            spannableString.setSpan(foregroundColorSpan, indexOf, str.length() + indexOf + -2, 33);
            textView.setText(spannableString);
        } else {
            textView.setText(campPackage.getName());
        }
        xBaseHolder.setText(R.id.tv_desc, campPackage.getDescription());
        if (campPackage.isChecked()) {
            xBaseHolder.setImageResource(R.id.iv_check, R.drawable.ic_checked);
        } else {
            xBaseHolder.setImageResource(R.id.iv_check, R.drawable.ic_uncheck);
        }
        xBaseHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Iterator it = CampPackageAdapter.this.list.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    CampItemListResult.CampPackage campPackage = (CampItemListResult.CampPackage) it.next();
                    if (campPackage.isChecked()) {
                        campPackage.setChecked(false);
                        break;
                    }
                }
                campPackage.setChecked(!campPackage.isChecked());
                CampPackageAdapter.this.notifyDataSetChanged();
                if (CampPackageAdapter.this.onClickListener != null) {
                    CampPackageAdapter.this.onClickListener.onClick(campPackage);
                }
            }
        });
    }

    public void setItemClick(OnItemClick onItemClick) {
        this.onClickListener = onItemClick;
    }
}

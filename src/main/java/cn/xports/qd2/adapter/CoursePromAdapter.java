package cn.xports.qd2.adapter;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import cn.xports.baselib.adapter.XBaseAdapter;
import cn.xports.baselib.adapter.XBaseHolder;
import cn.xports.baselib.util.MoneyUtil;
import cn.xports.qd2.R;
import cn.xports.qd2.entity.CoursePromInfo;
import com.alipay.sdk.util.h;
import com.blankj.utilcode.util.CollectionUtils;
import com.blankj.utilcode.util.StringUtils;
import java.util.Iterator;
import java.util.List;

public class CoursePromAdapter extends XBaseAdapter<CoursePromInfo> {
    public CoursePromAdapter(List<CoursePromInfo> list) {
        super(list);
    }

    public int getLayoutId() {
        return R.layout.item_card_prom;
    }

    public void onBindViewHolder(@NonNull XBaseHolder xBaseHolder, final int i) {
        final CoursePromInfo coursePromInfo = (CoursePromInfo) this.list.get(i);
        xBaseHolder.setText(R.id.tv_name, coursePromInfo.getPromName());
        final String[] strArr = {""};
        final String[] strArr2 = {""};
        final String[] strArr3 = {""};
        boolean z = true;
        if (coursePromInfo.getPromDetail() != null) {
            CollectionUtils.forAllDo(coursePromInfo.getPromDetail(), new CollectionUtils.Closure<CoursePromInfo.PromDetail>() {
                public void execute(int i, CoursePromInfo.PromDetail promDetail) {
                    if ("1".equals(promDetail.getElementType())) {
                        if (MoneyUtil.str2Int(promDetail.getElementValue()) != 0) {
                            String[] strArr = strArr3;
                            strArr[0] = "优惠价格" + StringUtils.getString(R.string.yuan) + MoneyUtil.cent2Yuan(promDetail.getElementValue()) + "元";
                        }
                    } else if ("2".equals(promDetail.getElementType())) {
                        StringBuilder sb = new StringBuilder();
                        String[] strArr2 = strArr;
                        sb.append(strArr2[0]);
                        sb.append(promDetail.getElementName());
                        sb.append(h.f735b);
                        strArr2[0] = sb.toString();
                    } else {
                        StringBuilder sb2 = new StringBuilder();
                        String[] strArr3 = strArr2;
                        sb2.append(strArr3[0]);
                        sb2.append(promDetail.getElementName());
                        sb2.append(promDetail.getElementNum());
                        sb2.append("张;");
                        strArr3[0] = sb2.toString();
                    }
                }
            });
            if (strArr[0].length() > 0) {
                strArr[0] = strArr[0].substring(0, strArr[0].length() - 1);
            }
            if (strArr2[0].length() > 0) {
                strArr2[0] = strArr2[0].substring(0, strArr2[0].length() - 1);
            }
        }
        xBaseHolder.setText(R.id.tv_give_content, strArr[0]);
        xBaseHolder.setText(R.id.tv_camp_content, strArr2[0]);
        xBaseHolder.setText(R.id.tv_money_content, strArr3[0]);
        xBaseHolder.setVisible(R.id.tv_give_content, !TextUtils.isEmpty(strArr[0]));
        xBaseHolder.setVisible(R.id.tv_give, !TextUtils.isEmpty(strArr[0]));
        xBaseHolder.setVisible(R.id.tv_camp_content, !TextUtils.isEmpty(strArr2[0]));
        xBaseHolder.setVisible(R.id.tv_camp, !TextUtils.isEmpty(strArr2[0]));
        xBaseHolder.setVisible(R.id.tv_money_content, !TextUtils.isEmpty(strArr3[0]));
        xBaseHolder.setVisible(R.id.tv_money, !TextUtils.isEmpty(strArr3[0]));
        xBaseHolder.setImageResource(R.id.iv_select, coursePromInfo.isSelect() ? R.drawable.ic_checked : R.drawable.ic_uncheck);
        xBaseHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Iterator it = CoursePromAdapter.this.list.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    CoursePromInfo coursePromInfo = (CoursePromInfo) it.next();
                    if (coursePromInfo.isSelect()) {
                        coursePromInfo.setSelect(false);
                        break;
                    }
                }
                coursePromInfo.setSelect(true);
                CoursePromAdapter.this.notifyDataSetChanged();
                CoursePromAdapter.this.performItemClick(coursePromInfo, i);
            }
        });
        int i2 = R.id.v_empty;
        if (i == getItemCount() - 1) {
            z = false;
        }
        xBaseHolder.setVisible(i2, z);
    }
}

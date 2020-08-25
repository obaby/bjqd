package cn.xports.qd2.adapter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import cn.xports.baselib.adapter.XBaseHolder;
import cn.xports.baselib.util.GlideUtil;
import cn.xports.baselib.util.MoneyUtil;
import cn.xports.qd2.R;
import cn.xports.qd2.entity.K;
import cn.xports.qd2.entity.TrainItem;
import cn.xports.qd2.training.TrainingDetailActivity;
import com.blankj.utilcode.util.ActivityUtils;
import me.drakeet.multitype.ItemViewBinder;

public class PersonalTrainBinder extends ItemViewBinder<TrainItem, XBaseHolder> {
    /* access modifiers changed from: protected */
    @NonNull
    public XBaseHolder onCreateViewHolder(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
        return new XBaseHolder(layoutInflater.inflate(R.layout.item_personal_train, viewGroup, false));
    }

    /* access modifiers changed from: protected */
    public void onBindViewHolder(@NonNull XBaseHolder xBaseHolder, @NonNull final TrainItem trainItem) {
        ImageView imageView = (ImageView) xBaseHolder.getView(R.id.iv_header);
        GlideUtil.loadImage(imageView.getContext(), trainItem.getCoursePicUrl(), R.drawable.bg_default).into(imageView);
        xBaseHolder.setText(R.id.ct_suit, trainItem.getSuitPerson());
        xBaseHolder.setText(R.id.tv_name, trainItem.getCourseName());
        TextView textView = (TextView) xBaseHolder.getView(R.id.ct_suit);
        TextView textView2 = (TextView) xBaseHolder.getView(R.id.ct_prom);
        int i = 4;
        boolean z = false;
        textView.setVisibility(!TextUtils.isEmpty(trainItem.getSuitPerson()) ? 0 : 4);
        if (MoneyUtil.str2Int(trainItem.getOriginalPrice()) > trainItem.getPrice()) {
            i = 0;
        }
        textView2.setVisibility(i);
        if (trainItem.getSubjectPrice() != null) {
            int i2 = R.id.tv_real_money;
            xBaseHolder.setText(i2, "¥" + MoneyUtil.simpleYuan(trainItem.getSubjectPrice().intValue()) + "起");
        } else {
            int i3 = R.id.tv_real_money;
            xBaseHolder.setText(i3, "¥" + MoneyUtil.simpleYuan(trainItem.getPrice()));
        }
        TextView textView3 = (TextView) xBaseHolder.getView(R.id.tv_old_money);
        textView3.getPaint().setFlags(16);
        textView3.setText("¥" + MoneyUtil.simpleYuan(trainItem.getOriginalPrice()));
        int i4 = R.id.tv_old_money;
        if (MoneyUtil.str2Int(trainItem.getOriginalPrice()) > trainItem.getPrice()) {
            z = true;
        }
        xBaseHolder.setVisible(i4, z);
        String courseEnrolled = trainItem.getCourseEnrolled();
        if (trainItem.isPrivate()) {
            courseEnrolled = trainItem.getPrivateCourseEnrolled();
        }
        if (TextUtils.isEmpty(courseEnrolled)) {
            courseEnrolled = K.k0;
        }
        int i5 = R.id.tv_has_sign;
        xBaseHolder.setText(i5, courseEnrolled + "人已报名");
        xBaseHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("courseId", trainItem.getCourseId());
                ActivityUtils.startActivity(bundle, TrainingDetailActivity.class);
            }
        });
    }
}

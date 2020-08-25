package cn.xports.qd2.widget;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.xports.baselib.bean.DataMap;
import cn.xports.baselib.util.DataMapUtils;
import cn.xports.qd2.R;
import cn.xports.qd2.entity.K;
import cn.xports.widget.RatingBar;
import com.blankj.utilcode.util.SizeUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GradeLabelView extends LinearLayout {
    private String gradeType = "";
    private RatingBar ratingBar;
    /* access modifiers changed from: private */
    public int star = 0;
    /* access modifiers changed from: private */
    public TextView tvLabel;
    private TextView tvName;

    public GradeLabelView(Context context, DataMap dataMap) {
        super(context);
        inflate(context, R.layout.v_grade_label, this);
        String string = dataMap.getString(K.extra);
        this.gradeType = dataMap.getString(K.paramValue);
        List<DataMap> fromJsonArray = DataMapUtils.fromJsonArray(string);
        final HashMap hashMap = new HashMap();
        if (fromJsonArray != null) {
            for (DataMap next : fromJsonArray) {
                hashMap.put(next.getString(K.grade), next.getString(K.label));
            }
        }
        this.ratingBar = (RatingBar) findViewById(R.id.rating_bar);
        this.tvName = (TextView) findViewById(R.id.tv_grade_name);
        this.tvLabel = (TextView) findViewById(R.id.tv_grade_label);
        this.ratingBar.showStars(SizeUtils.dp2px(8.0f)).setChangeListener(new RatingBar.OnStarChangeListener() {
            public void onChange(int i) {
                int unused = GradeLabelView.this.star = i;
                TextView access$100 = GradeLabelView.this.tvLabel;
                Map map = hashMap;
                access$100.setText((CharSequence) map.get("" + i));
            }
        });
        this.tvName.setText(dataMap.getString(K.valueName));
    }

    public DataMap getGradeSubmit() {
        return new DataMap().set(K.gradeType, this.gradeType).set(K.grade, Integer.valueOf(this.star));
    }

    public int getStar() {
        return this.star;
    }
}

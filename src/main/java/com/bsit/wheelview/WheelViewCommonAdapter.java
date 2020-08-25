package com.bsit.wheelview;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.bsit.wheelview.TosGallery;
import java.util.List;

public class WheelViewCommonAdapter extends BaseAdapter {
    private int checkColor;
    private Context context;
    private List<String> listData;
    private int mHeight = -2;
    private int mWidth = -1;
    private int nomalColor;
    private int selectPosition;

    public long getItemId(int i) {
        return (long) i;
    }

    public void setSelectPosition(int i) {
        this.selectPosition = i;
        notifyDataSetChanged();
    }

    public WheelViewCommonAdapter(Context context2, List<String> list) {
        this.context = context2;
        this.listData = list;
        this.nomalColor = context2.getResources().getColor(R.color.sum_text2);
        this.checkColor = context2.getResources().getColor(R.color.color_3e84cc);
    }

    public static float pixelToDp(Context context2, float f) {
        return f * context2.getResources().getDisplayMetrics().density;
    }

    public int getCount() {
        return this.listData.size();
    }

    public Object getItem(int i) {
        return this.listData.get(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView textView;
        if (view == null) {
            view = new TextView(this.context);
            view.setLayoutParams(new TosGallery.LayoutParams(this.mWidth, this.mHeight));
            textView = (TextView) view;
            textView.setGravity(17);
            textView.setSingleLine();
            textView.setEllipsize(TextUtils.TruncateAt.valueOf("END"));
            textView.setTextSize(1, 20.0f);
            textView.setTextColor(this.nomalColor);
        } else {
            textView = null;
        }
        if (textView == null) {
            textView = (TextView) view;
        }
        textView.setText(this.listData.get(i));
        if (this.selectPosition == i) {
            textView.setTextColor(this.checkColor);
        } else {
            textView.setTextColor(this.nomalColor);
        }
        return view;
    }
}

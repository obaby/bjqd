package cn.xports.qd2.adapter;

import android.support.annotation.NonNull;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.xports.baselib.adapter.XBaseHolder;
import cn.xports.baselib.adapter.XViewBinder;
import cn.xports.qd2.R;
import com.blankj.utilcode.util.ColorUtils;

public class TextServiceBinder extends XViewBinder<String> {
    private int backgroundColor = 0;
    private int fontSize = 14;
    private int height = 0;
    private int layoutId = 0;
    private int textColor = ColorUtils.getColor(R.color.main_text_color);

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        if (this.layoutId != 0) {
            return this.layoutId;
        }
        return R.layout.item_single_text;
    }

    public TextServiceBinder setLayoutId(int i) {
        this.layoutId = i;
        return this;
    }

    /* access modifiers changed from: protected */
    public void onBindViewHolder(@NonNull XBaseHolder xBaseHolder, @NonNull String str) {
        TextView textView = (TextView) xBaseHolder.getView(R.id.tv_title);
        textView.setText(str);
        textView.setTextSize((float) this.fontSize);
        textView.setTextColor(this.textColor);
        textView.setBackgroundColor(this.backgroundColor);
        if (this.height != 0) {
            ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
            layoutParams.height = this.height;
            textView.setLayoutParams(layoutParams);
        }
    }

    public TextServiceBinder setFontSize(int i) {
        this.fontSize = i;
        return this;
    }

    public TextServiceBinder setTextColor(int i) {
        this.textColor = i;
        return this;
    }

    public TextServiceBinder setHeight(int i) {
        this.height = i;
        return this;
    }

    public TextServiceBinder setBackgroundColor(int i) {
        this.backgroundColor = i;
        return this;
    }
}

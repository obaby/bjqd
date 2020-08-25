package cn.xports.qd2.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import cn.xports.baselib.adapter.XBaseAdapter;
import cn.xports.baselib.adapter.XBaseHolder;
import cn.xports.baselib.util.GlideUtil;
import cn.xports.qd2.R;
import java.util.List;

public class ImagePickerShowAdapter extends XBaseAdapter<String> {
    public static final String ADD = "239487ADD09kjdk";
    /* access modifiers changed from: private */
    public OnImageClickListener clickListener;
    /* access modifiers changed from: private */
    public boolean itemClickable = true;

    public interface OnImageClickListener {
        void onAddClick();

        void onImageClick(int i);
    }

    public ImagePickerShowAdapter setItemClickable(boolean z) {
        this.itemClickable = z;
        return this;
    }

    public ImagePickerShowAdapter(List<String> list) {
        super(list);
    }

    public int getLayoutId() {
        return R.layout.item_image_add;
    }

    public void onBindViewHolder(@NonNull XBaseHolder xBaseHolder, final int i) {
        final String str = (String) this.list.get(i);
        if (ADD.equals(str)) {
            xBaseHolder.setImageResource(R.id.iv_img, R.drawable.ic_add_img);
        } else {
            ImageView imageView = (ImageView) xBaseHolder.getView(R.id.iv_img);
            GlideUtil.loadImage(imageView.getContext(), str).into(imageView);
        }
        xBaseHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (ImagePickerShowAdapter.this.clickListener != null && ImagePickerShowAdapter.this.itemClickable) {
                    if (ImagePickerShowAdapter.ADD.equals(str)) {
                        ImagePickerShowAdapter.this.clickListener.onAddClick();
                    } else {
                        ImagePickerShowAdapter.this.clickListener.onImageClick(i);
                    }
                }
            }
        });
    }

    public ImagePickerShowAdapter setOnImageClickListener(OnImageClickListener onImageClickListener) {
        this.clickListener = onImageClickListener;
        return this;
    }
}

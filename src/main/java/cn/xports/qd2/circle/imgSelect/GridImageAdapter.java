package cn.xports.qd2.circle.imgSelect;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import cn.xports.qd2.R;
import com.bumptech.glide.Glide;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GridImageAdapter extends RecyclerView.Adapter<ViewHolder> {
    public static final int TYPE_CAMERA = 1;
    public static final int TYPE_PICTURE = 2;
    /* access modifiers changed from: private */
    public List<LocalMedia> list = new ArrayList();
    private LayoutInflater mInflater;
    protected OnItemClickListener mItemClickListener;
    /* access modifiers changed from: private */
    public onAddPicClickListener mOnAddPicClickListener;
    private int selectMax = 9;

    public interface OnItemClickListener {
        void onItemClick(int i, View view);
    }

    public interface onAddPicClickListener {
        void onAddPicClick();

        void onDeleteClick(int i);
    }

    public GridImageAdapter(Context context, onAddPicClickListener onaddpicclicklistener) {
        this.mInflater = LayoutInflater.from(context);
        this.mOnAddPicClickListener = onaddpicclicklistener;
    }

    public void setSelectMax(int i) {
        this.selectMax = i;
    }

    public void setList(List<LocalMedia> list2) {
        this.list = list2;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout ll_del;
        ImageView mImg;

        public ViewHolder(View view) {
            super(view);
            this.mImg = (ImageView) view.findViewById(R.id.fiv);
            this.ll_del = (RelativeLayout) view.findViewById(R.id.ll_del);
        }
    }

    public int getItemCount() {
        if (this.list.size() < this.selectMax) {
            return this.list.size() + 1;
        }
        return this.list.size();
    }

    public int getItemViewType(int i) {
        return isShowAddItem(i) ? 1 : 2;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(this.mInflater.inflate(R.layout.gv_filter_image, viewGroup, false));
    }

    private boolean isShowAddItem(int i) {
        return i == (this.list.size() == 0 ? 0 : this.list.size());
    }

    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        String str;
        if (getItemViewType(i) == 1) {
            viewHolder.mImg.setImageResource(R.drawable.ic_select_img);
            viewHolder.mImg.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    GridImageAdapter.this.mOnAddPicClickListener.onAddPicClick();
                }
            });
            viewHolder.ll_del.setVisibility(4);
            return;
        }
        viewHolder.ll_del.setVisibility(0);
        viewHolder.ll_del.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int adapterPosition = viewHolder.getAdapterPosition();
                GridImageAdapter.this.list.remove(adapterPosition);
                GridImageAdapter.this.notifyItemRemoved(adapterPosition);
                GridImageAdapter.this.notifyItemRangeChanged(adapterPosition, GridImageAdapter.this.list.size());
                if (GridImageAdapter.this.mOnAddPicClickListener != null) {
                    GridImageAdapter.this.mOnAddPicClickListener.onDeleteClick(adapterPosition);
                }
            }
        });
        LocalMedia localMedia = this.list.get(i);
        if (localMedia.isCut() && !localMedia.isCompressed()) {
            str = localMedia.getCutPath();
        } else if (localMedia.isCompressed() || (localMedia.isCut() && localMedia.isCompressed())) {
            str = localMedia.getCompressPath();
        } else {
            str = localMedia.getPath();
        }
        if (localMedia.isCompressed()) {
            Log.i("compress image result:", (new File(localMedia.getCompressPath()).length() / 1024) + "k");
            Log.i("压缩地址::", localMedia.getCompressPath());
        }
        Log.i("原图地址::", localMedia.getPath());
        if (localMedia.isCut()) {
            Log.i("裁剪地址::", localMedia.getCutPath());
        }
        Glide.with(viewHolder.itemView.getContext()).load(str).centerCrop().placeholder(R.drawable.bg_default).into(viewHolder.mImg);
        if (this.mItemClickListener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    GridImageAdapter.this.mItemClickListener.onItemClick(viewHolder.getAdapterPosition(), view);
                }
            });
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mItemClickListener = onItemClickListener;
    }
}

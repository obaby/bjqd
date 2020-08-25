package cn.xports.qd2.widget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.xports.qd2.R;
import cn.xports.qd2.adapter.ImagePickerShowAdapter;
import cn.xports.qd2.dialog.ImagePickDialog;
import cn.xports.qd2.entity.ViewElementData;
import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ImagePickElementView extends ElementView {
    private int maxCount = 0;
    private RecyclerView rvImages;
    private List<String> urls;

    public ImagePickElementView(ViewElementData viewElementData, Context context) {
        super(viewElementData, context);
    }

    /* access modifiers changed from: protected */
    public void init() {
        this.urls = new ArrayList();
        this.urls.add(ImagePickerShowAdapter.ADD);
        inflate(this.context, R.layout.element_iamge_pick_view, this);
        ((TextView) findViewById(R.id.tv_title_tip)).setText(this.elementData.getName());
        this.rvImages = (RecyclerView) findViewById(R.id.rv_images);
        this.rvImages.setLayoutManager(new LinearLayoutManager(this.context, 0, false));
        this.rvImages.setAdapter(new ImagePickerShowAdapter(this.urls).setOnImageClickListener(new ImagePickerShowAdapter.OnImageClickListener() {
            public void onImageClick(int i) {
            }

            public void onAddClick() {
                PermissionUtils.permission(new String[]{"android.permission-group.STORAGE"}).rationale(new PermissionUtils.OnRationaleListener() {
                    public void rationale(PermissionUtils.OnRationaleListener.ShouldRequest shouldRequest) {
                    }
                }).callback(new PermissionUtils.SimpleCallback() {
                    public void onGranted() {
                        LinearLayout linearLayout = (LinearLayout) ImagePickElementView.this.getParent();
                        int i = 0;
                        while (true) {
                            if (i >= linearLayout.getChildCount()) {
                                i = -1;
                                break;
                            } else if (linearLayout.getChildAt(i) == ImagePickElementView.this) {
                                break;
                            } else {
                                i++;
                            }
                        }
                        SPUtils.getInstance().put("image_location", i);
                        new ImagePickDialog(ImagePickElementView.this.context).show();
                    }

                    public void onDenied() {
                        ToastUtils.showShort("权限被禁止，无法继续操作");
                    }
                }).request();
            }
        }));
    }

    public void setEnable(boolean z) {
        this.rvImages.setEnabled(z);
        if (this.rvImages.getAdapter() != null) {
            ((ImagePickerShowAdapter) this.rvImages.getAdapter()).setItemClickable(z);
        }
    }

    public void clear() {
        this.urls.clear();
        this.urls.add(ImagePickerShowAdapter.ADD);
        this.rvImages.getAdapter().notifyDataSetChanged();
    }

    public void addUrl(String str) {
        if (this.urls.size() > 1) {
            this.urls.remove(0);
        }
        this.urls.add(0, str);
        this.rvImages.getAdapter().notifyDataSetChanged();
    }

    public String getValue() {
        StringBuilder sb = new StringBuilder();
        for (String next : this.urls) {
            if (next != null && !Objects.equals(next, ImagePickerShowAdapter.ADD)) {
                sb.append(next);
                sb.append(",");
            }
        }
        return !TextUtils.isEmpty(sb.toString()) ? sb.substring(0, sb.length() - 1) : "";
    }

    public void setShowValue(String str) {
        if (str != null) {
            List asList = Arrays.asList(str.split(","));
            this.urls.clear();
            if (asList != null && asList.size() > 0) {
                this.urls.addAll(asList);
            }
            this.urls.add(ImagePickerShowAdapter.ADD);
            if (this.rvImages != null && this.rvImages.getAdapter() != null) {
                this.rvImages.getAdapter().notifyDataSetChanged();
            }
        }
    }

    public boolean canSubmit() {
        if (super.canSubmit() || !this.urls.contains(ImagePickerShowAdapter.ADD) || this.urls.size() > 1) {
            return true;
        }
        ToastUtils.showShort("请完成" + this.elementData.getName() + "上传");
        return false;
    }

    public void showLine(boolean z) {
        findViewById(R.id.v_line).setVisibility(z ? 0 : 8);
    }

    public ImagePickElementView setMaxCount(int i) {
        this.maxCount = i;
        return this;
    }
}

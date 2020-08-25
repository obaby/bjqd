package cn.xports.qd2.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import cn.xports.base.BaseBottomDialog;
import cn.xports.qd2.R;
import cn.xports.qd2.adapter.CampPackageAdapter;
import cn.xports.qd2.entity.CampItemListResult;
import java.util.ArrayList;
import java.util.List;

public class SelectSportPackage extends BaseBottomDialog implements View.OnClickListener {
    CampPackageAdapter adapter;
    /* access modifiers changed from: private */
    public CampPackageAdapter.OnItemClick onItemClick;
    private List<CampItemListResult.CampPackage> packages;
    private RecyclerView rvPackages;

    public SelectSportPackage(@NonNull Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public void onCreate() {
        this.packages = new ArrayList();
        setContentView(R.layout.dialog_select_sport_package);
        findViewById(R.id.ivClose).setOnClickListener(this);
        this.rvPackages = (RecyclerView) findViewById(R.id.rv_packages);
        this.rvPackages.setLayoutManager(new LinearLayoutManager(this.context));
        this.adapter = new CampPackageAdapter(this.packages);
        this.rvPackages.setAdapter(this.adapter);
        this.adapter.setItemClick(new CampPackageAdapter.OnItemClick() {
            public void onClick(CampItemListResult.CampPackage campPackage) {
                if (SelectSportPackage.this.onItemClick != null) {
                    SelectSportPackage.this.onItemClick.onClick(campPackage);
                }
                SelectSportPackage.this.dismiss();
            }
        });
    }

    public SelectSportPackage setPackages(List<CampItemListResult.CampPackage> list) {
        if (list != null) {
            this.packages.clear();
            this.packages.addAll(list);
            if (!(this.rvPackages == null || this.adapter == null)) {
                this.adapter.notifyDataSetChanged();
            }
        }
        return this;
    }

    public SelectSportPackage setOnItemClick(CampPackageAdapter.OnItemClick onItemClick2) {
        this.onItemClick = onItemClick2;
        return this;
    }

    public void onClick(View view) {
        if (view.getId() == R.id.ivClose) {
            dismiss();
        }
    }
}

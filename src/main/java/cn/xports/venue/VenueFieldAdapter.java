package cn.xports.venue;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.xports.baselib.util.DateUtil;
import cn.xports.baselib.util.MoneyUtil;
import cn.xports.entity.SaleField;
import cn.xports.field.FieldBookActivity;
import cn.xports.parser.FieldSaleParser;
import cn.xports.qdplugin.R;
import java.util.List;

public class VenueFieldAdapter extends RecyclerView.Adapter<Holder> {
    private List<SaleField.FieldCal> fieldCals;
    /* access modifiers changed from: private */
    public FieldSaleParser fieldSaleParser;
    private List<SaleField.FieldType> fieldTypes;
    private boolean isDay = false;

    public VenueFieldAdapter(FieldSaleParser fieldSaleParser2) {
        this.fieldSaleParser = fieldSaleParser2;
    }

    @NonNull
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new Holder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_field, viewGroup, false));
    }

    public void onBindViewHolder(@NonNull final Holder holder, int i) {
        String str = "";
        final int i2 = 0;
        if (this.isDay) {
            holder.extra.setVisibility(0);
            SaleField.FieldCal fieldCal = this.fieldCals.get(i);
            TextView textView = holder.tvFieldCount;
            textView.setText(fieldCal.getRemainNum() + "");
            str = fieldCal.getDate();
            String week = DateUtil.getWeek(DateUtil.parse2Date(str, "yyyy-MM-dd"));
            TextView textView2 = holder.tvFieldTitle;
            textView2.setText(week + "(" + str.substring(5) + ")");
            holder.tvFieldMoney.setText(MoneyUtil.cent2Yuan(fieldCal.getLowestPrice()));
        } else {
            holder.extra.setVisibility(8);
            SaleField.FieldType fieldType = this.fieldTypes.get(i);
            i2 = fieldType.getFieldType();
            holder.tvFieldTitle.setText(fieldType.getFieldTypeName());
            holder.tvFieldMoney.setText(MoneyUtil.cent2Yuan(fieldType.getLowestPrice()));
        }
        final String replace = str.replace("-", "");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                FieldBookActivity.start(holder.itemView.getContext(), VenueFieldAdapter.this.fieldSaleParser.getVenueId(), VenueFieldAdapter.this.fieldSaleParser.getServiceId(), i2, replace);
            }
        });
    }

    public int getItemCount() {
        if (this.fieldSaleParser != null) {
            if (this.fieldSaleParser.getFieldCal() != null && this.fieldSaleParser.getFieldCal().size() > 0) {
                this.fieldCals = this.fieldSaleParser.getFieldCal();
                this.isDay = true;
                return this.fieldCals.size();
            } else if (this.fieldSaleParser.getFieldTypeList() != null) {
                this.fieldTypes = this.fieldSaleParser.getFieldTypeList();
                this.isDay = false;
                return this.fieldTypes.size();
            }
        }
        return 0;
    }

    public class Holder extends RecyclerView.ViewHolder {
        View extra;
        TextView tvFieldCount;
        TextView tvFieldMoney;
        TextView tvFieldTitle;

        public Holder(@NonNull View view) {
            super(view);
            this.tvFieldTitle = (TextView) view.findViewById(R.id.tv_field_title);
            this.tvFieldMoney = (TextView) view.findViewById(R.id.tv_field_money);
            this.tvFieldCount = (TextView) view.findViewById(R.id.tv_field_left);
            this.extra = view.findViewById(R.id.ll_field_extra);
        }
    }
}

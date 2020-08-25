package cn.qqtheme.framework.picker;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.LinearLayout;
import cn.qqtheme.framework.picker.LinkagePicker;
import cn.qqtheme.framework.widget.WheelView;
import java.util.ArrayList;
import java.util.List;

public class AddressPicker extends LinkagePicker {
    private boolean hideCounty = false;
    private boolean hideProvince = false;
    private OnAddressPickListener onAddressPickListener;
    private List<Province> provinceList = new ArrayList();

    public static class County extends Area {
    }

    public interface OnAddressPickListener {
        void onAddressPicked(Province province, City city, County county);
    }

    public AddressPicker(Activity activity, ArrayList<Province> arrayList) {
        super(activity);
        parseData(arrayList);
    }

    private void parseData(ArrayList<Province> arrayList) {
        int size = arrayList.size();
        this.provinceList.clear();
        this.provinceList.addAll(arrayList);
        for (int i = 0; i < size; i++) {
            Province province = arrayList.get(i);
            this.firstList.add(province.getAreaName());
            ArrayList<City> cities = province.getCities();
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            int size2 = cities.size();
            for (int i2 = 0; i2 < size2; i2++) {
                City city = cities.get(i2);
                arrayList2.add(city.getAreaName());
                ArrayList<County> counties = city.getCounties();
                ArrayList arrayList4 = new ArrayList();
                int size3 = counties.size();
                if (size3 == 0) {
                    arrayList4.add(city.getAreaName());
                } else {
                    for (int i3 = 0; i3 < size3; i3++) {
                        arrayList4.add(counties.get(i3).getAreaName());
                    }
                }
                arrayList3.add(arrayList4);
            }
            this.secondList.add(arrayList2);
            this.thirdList.add(arrayList3);
        }
    }

    public void setSelectedItem(String str, String str2, String str3) {
        super.setSelectedItem(str, str2, str3);
    }

    public void setHideProvince(boolean z) {
        this.hideProvince = z;
    }

    public void setHideCounty(boolean z) {
        this.hideCounty = z;
    }

    public void setOnAddressPickListener(OnAddressPickListener onAddressPickListener2) {
        this.onAddressPickListener = onAddressPickListener2;
    }

    @Deprecated
    public void setOnLinkageListener(LinkagePicker.OnLinkageListener onLinkageListener) {
        throw new UnsupportedOperationException("Please use setOnAddressPickListener instead.");
    }

    /* access modifiers changed from: protected */
    @NonNull
    public View makeCenterView() {
        if (this.hideCounty) {
            this.hideProvince = false;
        }
        if (this.firstList.size() != 0) {
            LinearLayout linearLayout = new LinearLayout(this.activity);
            linearLayout.setOrientation(0);
            linearLayout.setGravity(17);
            WheelView wheelView = new WheelView(this.activity);
            int i = this.screenWidthPixels / 3;
            wheelView.setLayoutParams(new LinearLayout.LayoutParams(i, -2));
            wheelView.setTextSize(this.textSize);
            wheelView.setTextColor(this.textColorNormal, this.textColorFocus);
            wheelView.setLineVisible(this.lineVisible);
            wheelView.setLineColor(this.lineColor);
            wheelView.setOffset(this.offset);
            linearLayout.addView(wheelView);
            if (this.hideProvince) {
                wheelView.setVisibility(8);
            }
            final WheelView wheelView2 = new WheelView(this.activity);
            wheelView2.setLayoutParams(new LinearLayout.LayoutParams(i, -2));
            wheelView2.setTextSize(this.textSize);
            wheelView2.setTextColor(this.textColorNormal, this.textColorFocus);
            wheelView2.setLineVisible(this.lineVisible);
            wheelView2.setLineColor(this.lineColor);
            wheelView2.setOffset(this.offset);
            linearLayout.addView(wheelView2);
            final WheelView wheelView3 = new WheelView(this.activity);
            wheelView3.setLayoutParams(new LinearLayout.LayoutParams(i, -2));
            wheelView3.setTextSize(this.textSize);
            wheelView3.setTextColor(this.textColorNormal, this.textColorFocus);
            wheelView3.setLineVisible(this.lineVisible);
            wheelView3.setLineColor(this.lineColor);
            wheelView3.setOffset(this.offset);
            linearLayout.addView(wheelView3);
            if (this.hideCounty) {
                wheelView3.setVisibility(8);
            }
            wheelView.setItems((List<String>) this.firstList, this.selectedFirstIndex);
            wheelView.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
                public void onSelected(boolean z, int i, String str) {
                    AddressPicker.this.selectedFirstText = str;
                    AddressPicker.this.selectedFirstIndex = i;
                    int i2 = 0;
                    AddressPicker.this.selectedThirdIndex = 0;
                    wheelView2.setItems((List<String>) (List) AddressPicker.this.secondList.get(AddressPicker.this.selectedFirstIndex), z ? 0 : AddressPicker.this.selectedSecondIndex);
                    ArrayList arrayList = (ArrayList) AddressPicker.this.thirdList.get(AddressPicker.this.selectedFirstIndex);
                    if (arrayList.size() > 0) {
                        WheelView wheelView = wheelView3;
                        List list = (List) arrayList.get(0);
                        if (!z) {
                            i2 = AddressPicker.this.selectedThirdIndex;
                        }
                        wheelView.setItems((List<String>) list, i2);
                        return;
                    }
                    wheelView3.setItems(new ArrayList());
                }
            });
            wheelView2.setItems((List<String>) (List) this.secondList.get(this.selectedFirstIndex), this.selectedSecondIndex);
            wheelView2.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
                public void onSelected(boolean z, int i, String str) {
                    AddressPicker.this.selectedSecondText = str;
                    AddressPicker.this.selectedSecondIndex = i;
                    ArrayList arrayList = (ArrayList) ((ArrayList) AddressPicker.this.thirdList.get(AddressPicker.this.selectedFirstIndex)).get(AddressPicker.this.selectedSecondIndex);
                    if (arrayList.size() > 0) {
                        wheelView3.setItems((List<String>) arrayList, z ? 0 : AddressPicker.this.selectedThirdIndex);
                    } else {
                        wheelView3.setItems(new ArrayList());
                    }
                }
            });
            wheelView3.setItems((List<String>) (List) ((ArrayList) this.thirdList.get(this.selectedFirstIndex)).get(this.selectedSecondIndex), this.selectedThirdIndex);
            wheelView3.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
                public void onSelected(boolean z, int i, String str) {
                    AddressPicker.this.selectedThirdText = str;
                    AddressPicker.this.selectedThirdIndex = i;
                }
            });
            return linearLayout;
        }
        throw new IllegalArgumentException("please initial data at first, can't be empty");
    }

    public void onSubmit() {
        if (this.onAddressPickListener != null) {
            Province province = this.provinceList.get(this.selectedFirstIndex);
            City city = this.provinceList.get(this.selectedFirstIndex).getCities().get(this.selectedSecondIndex);
            County county = null;
            if (!this.hideCounty) {
                county = this.provinceList.get(this.selectedFirstIndex).getCities().get(this.selectedSecondIndex).getCounties().get(this.selectedThirdIndex);
            }
            this.onAddressPickListener.onAddressPicked(province, city, county);
        }
    }

    public static abstract class Area {
        private String areaId;
        private String areaName;

        public String getAreaId() {
            return this.areaId;
        }

        public void setAreaId(String str) {
            this.areaId = str;
        }

        public String getAreaName() {
            return this.areaName;
        }

        public void setAreaName(String str) {
            this.areaName = str;
        }

        public String toString() {
            return "areaId=" + this.areaId + ",areaName=" + this.areaName;
        }
    }

    public static class Province extends Area {
        private ArrayList<City> cities = new ArrayList<>();

        public ArrayList<City> getCities() {
            return this.cities;
        }

        public void setCities(ArrayList<City> arrayList) {
            this.cities = arrayList;
        }
    }

    public static class City extends Area {
        private ArrayList<County> counties = new ArrayList<>();

        public ArrayList<County> getCounties() {
            return this.counties;
        }

        public void setCounties(ArrayList<County> arrayList) {
            this.counties = arrayList;
        }
    }
}

package cn.xports.entity;

import java.io.Serializable;

public class SaleField implements Serializable {

    public static class FieldCal implements Serializable {
        private int bookingState;
        private String date;
        private int lowestPrice;
        private int remainNum;

        public String getDate() {
            return this.date;
        }

        public FieldCal setDate(String str) {
            this.date = str;
            return this;
        }

        public int getRemainNum() {
            return this.remainNum;
        }

        public FieldCal setRemainNum(int i) {
            this.remainNum = i;
            return this;
        }

        public int getLowestPrice() {
            return this.lowestPrice;
        }

        public FieldCal setLowestPrice(int i) {
            this.lowestPrice = i;
            return this;
        }

        public int getBookingState() {
            return this.bookingState;
        }

        public FieldCal setBookingState(int i) {
            this.bookingState = i;
            return this;
        }
    }

    public static class FieldType implements Serializable {
        private int bookHour;
        private int fieldCnt;
        private int fieldType;
        private String fieldTypeName;
        private String fullTag;
        private int lowestPrice;
        private String remark;

        public String getFullTag() {
            return this.fullTag;
        }

        public FieldType setFullTag(String str) {
            this.fullTag = str;
            return this;
        }

        public int getFieldType() {
            return this.fieldType;
        }

        public FieldType setFieldType(int i) {
            this.fieldType = i;
            return this;
        }

        public String getFieldTypeName() {
            return this.fieldTypeName;
        }

        public FieldType setFieldTypeName(String str) {
            this.fieldTypeName = str;
            return this;
        }

        public int getFieldCnt() {
            return this.fieldCnt;
        }

        public FieldType setFieldCnt(int i) {
            this.fieldCnt = i;
            return this;
        }

        public String getRemark() {
            return this.remark;
        }

        public FieldType setRemark(String str) {
            this.remark = str;
            return this;
        }

        public int getBookHour() {
            return this.bookHour;
        }

        public FieldType setBookHour(int i) {
            this.bookHour = i;
            return this;
        }

        public int getLowestPrice() {
            return this.lowestPrice;
        }

        public FieldType setLowestPrice(int i) {
            this.lowestPrice = i;
            return this;
        }
    }
}

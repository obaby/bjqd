package coband.bsit.com.integral.bean;

import java.io.Serializable;
import java.util.List;

public class CreditListBean implements Serializable {
    private int code;
    private String message;
    private ResultBean result;

    public int getCode() {
        return this.code;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public ResultBean getResult() {
        return this.result;
    }

    public void setResult(ResultBean resultBean) {
        this.result = resultBean;
    }

    public static class ResultBean {
        private int credit;
        private DataGridBean dataGrid;

        public int getCredit() {
            return this.credit;
        }

        public void setCredit(int i) {
            this.credit = i;
        }

        public DataGridBean getDataGrid() {
            return this.dataGrid;
        }

        public void setDataGrid(DataGridBean dataGridBean) {
            this.dataGrid = dataGridBean;
        }

        public static class DataGridBean {
            private int endRow;
            private int pageNum;
            private int pageSize;
            private int pages;
            private List<RowsBean> rows;
            private int size;
            private int startRow;
            private int total;

            public int getTotal() {
                return this.total;
            }

            public void setTotal(int i) {
                this.total = i;
            }

            public int getPageNum() {
                return this.pageNum;
            }

            public void setPageNum(int i) {
                this.pageNum = i;
            }

            public int getPageSize() {
                return this.pageSize;
            }

            public void setPageSize(int i) {
                this.pageSize = i;
            }

            public int getPages() {
                return this.pages;
            }

            public void setPages(int i) {
                this.pages = i;
            }

            public int getSize() {
                return this.size;
            }

            public void setSize(int i) {
                this.size = i;
            }

            public int getStartRow() {
                return this.startRow;
            }

            public void setStartRow(int i) {
                this.startRow = i;
            }

            public int getEndRow() {
                return this.endRow;
            }

            public void setEndRow(int i) {
                this.endRow = i;
            }

            public List<RowsBean> getRows() {
                return this.rows;
            }

            public void setRows(List<RowsBean> list) {
                this.rows = list;
            }

            public static class RowsBean {
                private String credate;
                private int credit;
                private String description;
                private int id;
                private Object listType;
                private Object moddate;
                private long orderid;
                private Object today;
                private int type;
                private Object types;
                private int userid;

                public int getId() {
                    return this.id;
                }

                public void setId(int i) {
                    this.id = i;
                }

                public int getUserid() {
                    return this.userid;
                }

                public void setUserid(int i) {
                    this.userid = i;
                }

                public int getType() {
                    return this.type;
                }

                public void setType(int i) {
                    this.type = i;
                }

                public Object getToday() {
                    return this.today;
                }

                public void setToday(Object obj) {
                    this.today = obj;
                }

                public long getOrderid() {
                    return this.orderid;
                }

                public void setOrderid(long j) {
                    this.orderid = j;
                }

                public String getCredate() {
                    return this.credate;
                }

                public void setCredate(String str) {
                    this.credate = str;
                }

                public Object getModdate() {
                    return this.moddate;
                }

                public void setModdate(Object obj) {
                    this.moddate = obj;
                }

                public int getCredit() {
                    return this.credit;
                }

                public void setCredit(int i) {
                    this.credit = i;
                }

                public String getDescription() {
                    return this.description;
                }

                public void setDescription(String str) {
                    this.description = str;
                }

                public Object getTypes() {
                    return this.types;
                }

                public void setTypes(Object obj) {
                    this.types = obj;
                }

                public Object getListType() {
                    return this.listType;
                }

                public void setListType(Object obj) {
                    this.listType = obj;
                }
            }
        }
    }
}

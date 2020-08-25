package cn.xports.sportCoaching;

import cn.xports.baselib.bean.Response;
import java.io.Serializable;
import java.util.List;

public class SportCoachingBean extends Response implements Serializable {
    public PageInfo pageInfo;

    public class PageInfo {
        public long endRow;
        public long firstPage;
        public boolean hasNextPage;
        public boolean hasPreviousPage;
        public boolean isFirstPage;
        public boolean isLastPage;
        public long lastPage;
        public List<DateList> list;
        public long navigatePages;
        public List<String> navigatepageNums;
        public long nextPage;
        public long pageNum;
        public long pageSize;
        public long pages;
        public long prePage;
        public long size;
        public long startRow;
        public long total;

        public PageInfo() {
        }
    }
}

package cn.xports.entity;

import java.io.Serializable;
import java.util.List;

public class OrderPageInfo implements Serializable {
    private int endRow;
    private int firstPage;
    private boolean hasNextPage;
    private boolean hasPreviousPage;
    private boolean isFirstPage;
    private boolean isLastPage;
    private int lastPage;
    private List<OrderInfo> list;
    private int navigatePages;
    private List<Integer> navigatepageNums;
    private int nextPage;
    private int pageNum;
    private int pageSize;
    private int pages;
    private int prePage;
    private int size;
    private int startRow;
    private int total;

    public int getPageNum() {
        return this.pageNum;
    }

    public OrderPageInfo setPageNum(int i) {
        this.pageNum = i;
        return this;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public OrderPageInfo setPageSize(int i) {
        this.pageSize = i;
        return this;
    }

    public int getSize() {
        return this.size;
    }

    public OrderPageInfo setSize(int i) {
        this.size = i;
        return this;
    }

    public int getStartRow() {
        return this.startRow;
    }

    public OrderPageInfo setStartRow(int i) {
        this.startRow = i;
        return this;
    }

    public int getEndRow() {
        return this.endRow;
    }

    public OrderPageInfo setEndRow(int i) {
        this.endRow = i;
        return this;
    }

    public int getTotal() {
        return this.total;
    }

    public OrderPageInfo setTotal(int i) {
        this.total = i;
        return this;
    }

    public int getPages() {
        return this.pages;
    }

    public OrderPageInfo setPages(int i) {
        this.pages = i;
        return this;
    }

    public int getFirstPage() {
        return this.firstPage;
    }

    public OrderPageInfo setFirstPage(int i) {
        this.firstPage = i;
        return this;
    }

    public int getPrePage() {
        return this.prePage;
    }

    public OrderPageInfo setPrePage(int i) {
        this.prePage = i;
        return this;
    }

    public int getNextPage() {
        return this.nextPage;
    }

    public OrderPageInfo setNextPage(int i) {
        this.nextPage = i;
        return this;
    }

    public int getLastPage() {
        return this.lastPage;
    }

    public OrderPageInfo setLastPage(int i) {
        this.lastPage = i;
        return this;
    }

    public boolean isFirstPage() {
        return this.isFirstPage;
    }

    public OrderPageInfo setFirstPage(boolean z) {
        this.isFirstPage = z;
        return this;
    }

    public boolean isLastPage() {
        return this.isLastPage;
    }

    public OrderPageInfo setLastPage(boolean z) {
        this.isLastPage = z;
        return this;
    }

    public boolean isHasPreviousPage() {
        return this.hasPreviousPage;
    }

    public OrderPageInfo setHasPreviousPage(boolean z) {
        this.hasPreviousPage = z;
        return this;
    }

    public boolean isHasNextPage() {
        return this.hasNextPage;
    }

    public OrderPageInfo setHasNextPage(boolean z) {
        this.hasNextPage = z;
        return this;
    }

    public int getNavigatePages() {
        return this.navigatePages;
    }

    public OrderPageInfo setNavigatePages(int i) {
        this.navigatePages = i;
        return this;
    }

    public List<Integer> getNavigatepageNums() {
        return this.navigatepageNums;
    }

    public OrderPageInfo setNavigatepageNums(List<Integer> list2) {
        this.navigatepageNums = list2;
        return this;
    }

    public List<OrderInfo> getList() {
        return this.list;
    }

    public OrderPageInfo setList(List<OrderInfo> list2) {
        this.list = list2;
        return this;
    }
}

package cn.xports.entity;

import java.io.Serializable;
import java.util.List;

public class PageInfo<T> implements Serializable {
    private int endRow;
    private int firstPage;
    private boolean hasNextPage;
    private boolean hasPreviousPage;
    private boolean isFirstPage;
    private boolean isLastPage;
    private int lastPage;
    private List<T> list;
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

    public void setPageNum(int i) {
        this.pageNum = i;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int i) {
        this.pageSize = i;
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

    public int getTotal() {
        return this.total;
    }

    public void setTotal(int i) {
        this.total = i;
    }

    public int getPages() {
        return this.pages;
    }

    public void setPages(int i) {
        this.pages = i;
    }

    public int getFirstPage() {
        return this.firstPage;
    }

    public void setFirstPage(int i) {
        this.firstPage = i;
    }

    public int getPrePage() {
        return this.prePage;
    }

    public void setPrePage(int i) {
        this.prePage = i;
    }

    public int getNextPage() {
        return this.nextPage;
    }

    public void setNextPage(int i) {
        this.nextPage = i;
    }

    public int getLastPage() {
        return this.lastPage;
    }

    public void setLastPage(int i) {
        this.lastPage = i;
    }

    public boolean isIsFirstPage() {
        return this.isFirstPage;
    }

    public void setIsFirstPage(boolean z) {
        this.isFirstPage = z;
    }

    public boolean isIsLastPage() {
        return this.isLastPage;
    }

    public void setIsLastPage(boolean z) {
        this.isLastPage = z;
    }

    public boolean isHasPreviousPage() {
        return this.hasPreviousPage;
    }

    public void setHasPreviousPage(boolean z) {
        this.hasPreviousPage = z;
    }

    public boolean isHasNextPage() {
        return this.hasNextPage;
    }

    public void setHasNextPage(boolean z) {
        this.hasNextPage = z;
    }

    public int getNavigatePages() {
        return this.navigatePages;
    }

    public void setNavigatePages(int i) {
        this.navigatePages = i;
    }

    public List<T> getList() {
        return this.list;
    }

    public void setList(List<T> list2) {
        this.list = list2;
    }

    public List<Integer> getNavigatepageNums() {
        return this.navigatepageNums;
    }

    public void setNavigatepageNums(List<Integer> list2) {
        this.navigatepageNums = list2;
    }
}

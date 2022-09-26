package com.smile.untils;

import java.util.List;

public class PageTool {
    private int totalCount;//总数据量
    private int currentPage;//当前页码
    private int pageCount;//总页码
    private int lastPage;//上一页页码
    private int nextPage;//下一页页码
    private int startIndex;//起始下标
    private int pageSize;//每页数据量
    private List list;//储存分页得到的数据

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public PageTool() {
    }

    /*
            构造方法，只需要传递总数据量，当前页码，每页数据量
            因为只要有了这三个值，其余的值可以推算出来！
        */
    public PageTool(int totalCount, String currentPage, int pageSize) {
        this.totalCount = totalCount;
        initialCurrentPage(currentPage);
        this.pageSize = pageSize;
        initialPageCount();
        initialLastPage();
        initialNextPage();
        initialStartIndex();
    }

    //初始化起始下标的方法
    private void initialStartIndex() {
        startIndex = (currentPage - 1) * pageSize;
    }

    //初始化下一页页码的方法
    private void initialNextPage() {
        if (currentPage == pageCount) {
            nextPage = pageCount;
        } else {
            nextPage = currentPage + 1;
        }
    }

    //初始化上一页页码的方法
    private void initialLastPage() {
        if (currentPage == 1) {
            lastPage = 1;
        } else {
            lastPage = currentPage - 1;
        }
    }

    //初始化总页码的方法
    private void initialPageCount() {
        pageCount = totalCount / pageSize + (totalCount % pageSize == 0 ? 0 : 1);
    }

    //初始化当前页码的方法
    private void initialCurrentPage(String currentPage) {
        if (currentPage == null) {
            this.currentPage = 1;
        } else {
            this.currentPage = Integer.valueOf(currentPage);
        }
    }

    @Override
    public String toString() {
        return "PageTool{" +
                "totalCount=" + totalCount +
                ", currentPage=" + currentPage +
                ", pageCount=" + pageCount +
                ", lastPage=" + lastPage +
                ", nextPage=" + nextPage +
                ", startIndex=" + startIndex +
                ", pageSize=" + pageSize +
                '}';
    }
}

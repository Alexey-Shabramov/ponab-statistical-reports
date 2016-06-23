package com.uz.laboratory.statistical.util;


public class HibernatePaginationUtil {
    private Long firstResult;
    private Long maxResult;
    private Long lastIndex;
    private Long tableViewPageIndex;

    public Long getTableViewPageIndex() {
        return tableViewPageIndex;
    }

    public void setTableViewPageIndex(Long tableViewPageIndex) {
        this.tableViewPageIndex = tableViewPageIndex;
    }

    public Long getFirstResult() {
        return firstResult;
    }

    public void setFirstResult(Long firstResult) {
        this.firstResult = firstResult;
    }

    public Long getMaxResult() {
        return maxResult;
    }

    public void setMaxResult(Long maxResult) {
        this.maxResult = maxResult;
    }

    public Long getLastIndex() {
        return lastIndex;
    }

    public void setLastIndex(Long lastIndex) {
        this.lastIndex = lastIndex;
    }
}

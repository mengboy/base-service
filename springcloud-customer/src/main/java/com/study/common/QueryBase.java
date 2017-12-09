package com.study.common;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 封装的一个数据查询小工具
 * 按多种条件查询
 * 分页查询
 * Created by admin on 2016/11/5.
 */
public class QueryBase implements Serializable {

    private final static long serialVersionUID = -977211858753607189L;

    private final static long defaultFirstRow = 0;
    private final static long defaultTotalRow = 0;
    private final static long firstPage = 1;
    private final static long defaultPageSize = 10;
    private final static long defaultTotalPage = 0;

    private Long firstRow = defaultFirstRow;
    private Long maxRow = defaultPageSize;
    private Long totalRow = defaultTotalRow;

    private Long currentPage = firstPage;
    private Long pageSize = defaultPageSize;
    private Long totalPage = defaultTotalPage;


    private Long limit_start;

    public int geteId() {
        return eId;
    }

    public void seteId(int eId) {
        this.eId = eId;
    }

    private int eId;


    private String sidx;
    private String sord;


    public QueryBase() {
    }

    public QueryBase(Long currentPage, Long pageSize) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.limit_start = (this.currentPage - 1) * this.pageSize;
    }

    public QueryBase(Long currentPage, Long pageSize, String password) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.limit_start = (this.currentPage - 1) * this.pageSize;
    }

    public QueryBase(Long currentPage, Long pageSize, Long year, Long mounth, String depart) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.limit_start = (this.currentPage - 1) * this.pageSize;
    }




    public Long getLimit_start() {
        return limit_start;
    }

    private Map<String, Object> parameters = new HashMap<String, Object>();

    private List<? extends Object> results;

    public boolean isFirstPage() {
        return this.currentPage == firstPage;
    }

    public boolean isLastPage() {
        return this.currentPage == this.totalPage;
    }

    public boolean nextPage() {
        if (this.totalRow == defaultTotalRow
                || this.currentPage == this.totalPage)
            return false;
        this.setCurrentPage(this.getNextPage());
        return true;
    }

    public boolean previousPage() {
        if (this.totalRow == defaultTotalRow || this.currentPage == firstPage)
            return false;
        this.setCurrentPage(this.getPreviousPage());
        return true;
    }

    public Long getNextPage() {
        Long next = this.currentPage + 1;
        return next > this.totalPage ? this.totalPage : next;
    }

    public Long getPreviousPage() {
        Long previous = this.currentPage - 1;
        return previous < firstPage ? firstPage : previous;
    }

    public void setTotalRow(Long totalRow) {
        if (totalRow > 0 && totalRow != this.totalRow) {
            this.totalRow = totalRow;
            this.totalPage = totalRow / this.pageSize;
            if (totalRow % this.pageSize != 0)
                this.totalPage++;
        }
    }

    public void setTotalRow() {
        this.setTotalRow(defaultTotalRow);
    }

    public Long getTotalRow() {
        return totalRow;
    }

    public void setCurrentPage(Long currentPage) {
        if (currentPage > 0) {
            this.currentPage = currentPage;
            this.firstRow = (currentPage - 1) * this.pageSize;
        }
    }

    public void updatePages() {
        setCurrentPage(this.getCurrentPage());
    }

    public void setCurrentPage() {
        this.currentPage = firstPage;
        this.firstRow = (this.currentPage - 1) * this.pageSize;
    }

    public Long getCurrentPage() {
        return currentPage;
    }

    public void setPageSize(Long pageSize) {
        if (pageSize > 0) {
            this.pageSize = pageSize;
            this.maxRow = pageSize;
            this.setCurrentPage(this.currentPage);
        }
    }

    public void setPageSize() {
        this.pageSize = defaultPageSize;
        this.maxRow = defaultPageSize;
        this.setCurrentPage(this.currentPage);
    }

    public Long getPageSize() {
        return pageSize;
    }

    public Long getDefaultPageSize() {
        return defaultPageSize;
    }

    public Long getTotalPage() {
        return totalPage;
    }

    public void setFirstRow(Long firstRow) {
        if (firstRow >= 0) {
            this.firstRow = firstRow;
            this.currentPage = 1 + (Long) (firstRow / this.pageSize);
        }
    }

    public Long getFirstRow() {
        return firstRow;
    }

    public void setMaxRow(Long maxRow) {
        if (maxRow > 0) {
            this.pageSize = maxRow;
            this.maxRow = maxRow;
        }
    }

    public Long getMaxRow() {
        return maxRow;
    }

    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public void addParameter(String key, Object value) {
        parameters.put(key, value);
    }

    public void removeParameter(String key) {
        parameters.remove(key);
    }

    public Object getParameter(String key) {
        return parameters.get(key);
    }

    public void setResults(List<? extends Object> results) {
        this.results = results;
    }

    public List<? extends Object> getResults() {
        return results;
    }

    public void setSidx(String sidx) {
        this.sidx = sidx;
    }

    public String getSidx() {
        return sidx;
    }

    public void setSord(String sord) {
        this.sord = sord;
    }

    public String getSord() {
        return sord;
    }

}
package com.study.model;

public class Request {
    private Long pageSize;

    private Long requestPage;

    private String serviceName;

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public Long getRequestPage() {
        return requestPage;
    }

    public void setRequestPage(Long requestPage) {
        this.requestPage = requestPage;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}

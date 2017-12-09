package com.study.common;

import java.util.Map;

public class QueryBase {
    private Map<String, Object> parameters;

    public QueryBase() {
    }

    public QueryBase(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }
}

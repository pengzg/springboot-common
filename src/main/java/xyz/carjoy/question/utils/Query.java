package xyz.carjoy.question.utils;

import java.util.Map;

public class Query {
    private Map<String, Object> queryParams;
    private Pager pager;

    public Query() {
    }

    public Map<String, Object> getQueryParams() {
        return this.queryParams;
    }

    public void setQueryParams(Map<String, Object> queryParams) {
        this.queryParams = queryParams;
    }

    public Pager getPager() {
        return this.pager;
    }

    public void setPager(Pager pager) {
        this.pager = pager;
    }
}

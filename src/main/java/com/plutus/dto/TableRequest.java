package com.plutus.dto;

/**
 * Created by Administrator on 2017/5/19.
 */
public class TableRequest {
    private Integer limit;
    private Integer offset;
    private String order;
    private String search;

    public void setSearch(String search) {
        this.search = search;
    }

    public String getSearch() {

        return search;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Integer getLimit() {

        return limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public String getOrder() {
        return order;
    }
}

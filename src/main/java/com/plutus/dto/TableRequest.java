package com.plutus.dto;

/**
 * Created by Administrator on 2017/5/19.
 */
public class TableRequest {
    private Integer limit;
    private Integer offset;
    private String order;

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

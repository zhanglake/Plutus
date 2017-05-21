package com.plutus.dto;

/**
 * Created by Administrator on 2017/5/20.
 */
public class CustomerOrderRequest {
    private Integer limit;
    private Integer offset;
    private String order;
    private Long customerId;

    public Integer getLimit() {
        return limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public String getOrder() {
        return order;
    }

    public Long getCustomerId() {
        return customerId;
    }
}

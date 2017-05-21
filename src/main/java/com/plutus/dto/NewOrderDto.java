package com.plutus.dto;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/5/20.
 */
public class NewOrderDto {
    private Long customerId;
    private String description;
    private Date deliveryDate;
    private List<NewOrderDetailDto> details;

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Date getDeliveryDate() {

        return deliveryDate;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDetails(List<NewOrderDetailDto> details) {
        this.details = details;
    }

    public Long getCustomerId() {

        return customerId;
    }

    public String getDescription() {
        return description;
    }

    public List<NewOrderDetailDto> getDetails() {
        return details;
    }
}
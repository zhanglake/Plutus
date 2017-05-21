package com.plutus.dto;

import java.util.Date;

/**
 * Created by Administrator on 2017/5/19.
 */
public class OrderTableResponse {
    private Long id;
    private String createdDate;
    private String deliveryDate;
    private String description;
    private String totalPrice;
    private String customerName;
    private String customerPhone;

    public void setId(Long id) {
        this.id = id;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public Long getId() {

        return id;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public String getDescription() {
        return description;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }
}

package com.plutus.dto;

import java.util.Date;

/**
 * Created by Administrator on 2017/5/19.
 */
public class OrderDto {
    private Long id;
    private Date createdDate;
    private Date deliveryDate;
    private String description;
    private Double totalPrice;
    private String customerName;
    private String customerPhone;

    public void setId(Long id) {
        this.id = id;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTotalPrice(Double totalPrice) {
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public String getDescription() {
        return description;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }
}

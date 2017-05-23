package com.plutus.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2017/5/18.
 */
public class Order implements Serializable {
    private Long id;
    private Date createdDate;
    private Long customerId;
    private Date deliveryDate;
    private String Description;
    private Double totalPrice;
    private String files;

    public void setFiles(String files) {
        this.files = files;
    }

    public String getFiles() {

        return files;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getId() {

        return id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public String getDescription() {
        return Description;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }
}

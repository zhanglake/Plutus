package com.plutus.dto;

import com.plutus.entity.MyFile;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/5/19.
 */
public class OrderTableResponse {
    private Long id;
    private String code;
    private String createdDate;
    private String deliveryDate;
    private String description;
    private String totalPrice;
    private String customerName;
    private String customerPhone;
    private String files;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {

        return code;
    }

    public void setFiles(String files) {
        this.files = files;
    }

    public String getFiles() {
        return files;
    }

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

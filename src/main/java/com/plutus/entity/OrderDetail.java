package com.plutus.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/19.
 */
public class OrderDetail implements Serializable {
    private Long id;
    private Long orderId;
    private String name;
    private Double price;
    private Integer number;
    private String description;

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {

        return id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getNumber() {
        return number;
    }

    public String getDescription() {
        return description;
    }
}

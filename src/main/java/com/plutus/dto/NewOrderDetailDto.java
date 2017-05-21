package com.plutus.dto;

/**
 * Created by Administrator on 2017/5/20.
 */
public class NewOrderDetailDto {
    private String name;
    private String number;
    private String price;
    private String description;

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {

        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
}

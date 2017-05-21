package com.plutus.dto;

/**
 * Created by Administrator on 2017/5/20.
 */
public class CustomerDto {
    private String customerName;
    private String customerPhone;
    private String address;

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerName() {

        return customerName;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public String getAddress() {
        return address;
    }
}

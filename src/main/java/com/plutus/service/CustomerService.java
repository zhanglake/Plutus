package com.plutus.service;

import com.plutus.dto.CustomerDto;
import com.plutus.dto.TableRequest;
import com.plutus.entity.Customer;

import java.util.List;

/**
 * Created by Administrator on 2017/5/20.
 */
public interface CustomerService {

    public Integer findAllCount();

    public List<Customer> findAllPageable(TableRequest request);

    public Integer addCustomer(CustomerDto dto);

    public Customer findById(Long customerId);

}

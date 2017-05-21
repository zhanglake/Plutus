package com.plutus.dao;

import com.plutus.dto.CustomerDto;
import com.plutus.dto.TableRequest;
import com.plutus.entity.Customer;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/5/20.
 */
@Repository("customerDao")
public interface CustomerDao {

    public List<Customer> findAll();

    public Integer findAllCount();

    public List<Customer> findAllpageable(TableRequest request);

    public Integer addCustomer(CustomerDto dto);

    public Customer findById(Long customerId);
}

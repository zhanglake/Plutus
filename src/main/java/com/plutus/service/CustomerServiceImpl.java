package com.plutus.service;

import com.plutus.dao.CustomerDao;
import com.plutus.dto.CustomerDto;
import com.plutus.dto.TableRequest;
import com.plutus.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/5/20.
 */
@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Override
    public Integer findAllCount() {
        return customerDao.findAllCount();
    }

    @Override
    public List<Customer> findAllPageable(TableRequest request) {
        return customerDao.findAllpageable(request);
    }

    @Override
    public Integer addCustomer(CustomerDto dto) {
        return customerDao.addCustomer(dto);
    }

    @Override
    public Customer findById(Long customerId) {
        return customerDao.findById(customerId);
    }
}

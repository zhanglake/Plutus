package com.plutus.controller;

import com.plutus.dto.CustomerDto;
import com.plutus.dto.Result;
import com.plutus.dto.TableRequest;
import com.plutus.dto.TableResponse;
import com.plutus.entity.Customer;
import com.plutus.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2017/5/20.
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping("")
    public String orderPage() {
        return "customer";
    }

    @RequestMapping("/table")
    @ResponseBody
    public TableResponse queryCustomerTable(@RequestBody TableRequest request) {
        List<Customer> customers = customerService.findAllPageable(request);
        Integer count = customerService.findAllCount();
        TableResponse response = new TableResponse();
        response.setTotal(count);
        response.setRows(customers);
        return response;
    }

    @RequestMapping("/add")
    @ResponseBody
    public Result addCustomer(@RequestBody CustomerDto dto) {
        int count = customerService.addCustomer(dto);
        if (count > 0) {
            return new Result(200, Result.SUCCESS);
        }
        return new Result(500, Result.FAIL);
    }

}

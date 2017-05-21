package com.plutus.service;

import com.plutus.dto.CustomerOrderRequest;
import com.plutus.dto.OrderDto;
import com.plutus.dto.TableRequest;
import com.plutus.entity.Order;
import com.plutus.entity.OrderDetail;

import java.util.List;

/**
 * Created by Administrator on 2017/5/18.
 */
public interface OrderService {
    public List<Order> findAll();

    public Integer findAllCount();

    public List<Order> findAllPageable(TableRequest request);

    public List<OrderDto> findAllWithCustomerPageable(TableRequest request);

    public List<OrderDetail> findOrderDetailByOrderId(Long orderId);

    public List<Order> findAllWithCustomerPageableByCustomerId(CustomerOrderRequest request);

    public Integer findCountByCustomerId(CustomerOrderRequest request);

    public Integer addOrder(Order order);

    public Integer addOrderDetail(OrderDetail orderDetail);
}

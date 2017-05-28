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
    List<Order> findAll();

    Integer findAllCount();

    Integer findAllCountSearch(TableRequest request);

    List<Order> findAllPageable(TableRequest request);

    List<OrderDto> findAllWithCustomerPageable(TableRequest request);

    List<OrderDto> findAllWithCustomerPageableSearch(TableRequest request);

    List<OrderDetail> findOrderDetailByOrderId(Long orderId);

    List<Order> findAllWithCustomerPageableByCustomerId(CustomerOrderRequest request);

    Integer findCountByCustomerId(CustomerOrderRequest request);

    Integer addOrder(Order order);

    Integer addOrderDetail(OrderDetail orderDetail);
}

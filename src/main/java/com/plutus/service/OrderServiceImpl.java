package com.plutus.service;

import com.plutus.dao.OrderDao;
import com.plutus.dto.CustomerOrderRequest;
import com.plutus.dto.OrderDto;
import com.plutus.dto.TableRequest;
import com.plutus.entity.Order;
import com.plutus.entity.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/5/18.
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired(required = false)
    private OrderDao orderDao;

    @Override
    public List<Order> findAll() {
        return orderDao.findAll();
    }

    @Override
    public Integer findAllCount() {
        return orderDao.findAllCount();
    }

    @Override
    public List<Order> findAllPageable(TableRequest request) {
        return orderDao.findAllPageable(request);
    }

    @Override
    public List<OrderDto> findAllWithCustomerPageable(TableRequest request) {
        return orderDao.findAllWithCustomerPageable(request);
    }

    @Override
    public List<OrderDetail> findOrderDetailByOrderId(Long orderId) {
        return orderDao.findOrderDetailByOrderId(orderId);
    }

    @Override
    public List<Order> findAllWithCustomerPageableByCustomerId(CustomerOrderRequest request) {
        return orderDao.findAllWithCustomerPageableByCustomerId(request);
    }

    @Override
    public Integer findCountByCustomerId(CustomerOrderRequest request) {
        return orderDao.findCountByCustomerId(request);
    }

    @Override
    public Integer addOrder(Order order) {
        return orderDao.addOrder(order);
    }

    @Override
    public Integer addOrderDetail(OrderDetail orderDetail) {
        return orderDao.addOrderDetail(orderDetail);
    }
}

package com.plutus.controller;

import com.plutus.dto.*;
import com.plutus.entity.Customer;
import com.plutus.entity.Order;
import com.plutus.entity.OrderDetail;
import com.plutus.service.CustomerService;
import com.plutus.service.OrderService;
import com.plutus.utils.CommonUtils;
import com.plutus.utils.DateUtils;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/5/16.
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    private static final String FORMAT_DATE = "yyyy-MM-dd";

    private static final String FORMAT_DATE_TO_HOUR = "yyyy-MM-dd hh:mm:ss";

    @Autowired
    private OrderService orderService;
    @Autowired
    private CustomerService customerService;

    @RequestMapping("")
    public String orderPage() {
        return "order";
    }

    @RequestMapping("/table")
    @ResponseBody
    public TableResponse queryOrderTable(@RequestBody TableRequest request) {
        Integer totalNumbers = 0;
        List<OrderDto> dtos = new ArrayList<OrderDto>();
        if (null != request.getSearch()) {
            request.setSearch("%" + request.getSearch() + "%");
            totalNumbers = orderService.findAllCountSearch(request);
            dtos = orderService.findAllWithCustomerPageableSearch(request);
        } else {
            totalNumbers = orderService.findAllCount();
            dtos = orderService.findAllWithCustomerPageable(request);
        }
        List<OrderTableResponse> orderTableResponses = new ArrayList<OrderTableResponse>();
        for (OrderDto dto : dtos) {
            OrderTableResponse orderTableResponse = new OrderTableResponse();
            orderTableResponse.setId(dto.getId());
            orderTableResponse.setCreatedDate(DateUtils.formatDateToString(dto.getCreatedDate(), FORMAT_DATE));
            orderTableResponse.setDeliveryDate(DateUtils.formatDateToString(dto.getDeliveryDate(), FORMAT_DATE));
            orderTableResponse.setCustomerName(dto.getCustomerName());
            orderTableResponse.setCustomerPhone(dto.getCustomerPhone());
            orderTableResponse.setDescription(dto.getDescription());
            orderTableResponse.setTotalPrice(dto.getTotalPrice().toString());
            orderTableResponse.setFiles(dto.getFiles());
            orderTableResponse.setCode(dto.getCode());
            orderTableResponses.add(orderTableResponse);
        }
        TableResponse response = new TableResponse();
        response.setRows(orderTableResponses);
        response.setTotal(totalNumbers);
        return response;
    }

    @RequestMapping("/details")
    @ResponseBody
    public List<OrderDetail> queryOrderDetails(@RequestParam("orderId" )Long orderId) {
        List<OrderDetail> orderDetails = orderService.findOrderDetailByOrderId(orderId);
        return orderDetails;
    }

    @RequestMapping("/customer/table")
    @ResponseBody
    public TableResponse queryCustomerOrderTable(@RequestBody CustomerOrderRequest request) {
        Integer count = orderService.findCountByCustomerId(request);
        List<Order> orders = orderService.findAllWithCustomerPageableByCustomerId(request);
        List<OrderTableResponse> orderTableResponses = new ArrayList<OrderTableResponse>();
        for (Order order : orders) {
            OrderTableResponse orderTableResponse = new OrderTableResponse();
            orderTableResponse.setId(order.getId());
            orderTableResponse.setCreatedDate(DateUtils.formatDateToString(order.getCreatedDate(), FORMAT_DATE));
            orderTableResponse.setDeliveryDate(DateUtils.formatDateToString(order.getDeliveryDate(), FORMAT_DATE));
            orderTableResponse.setDescription(order.getDescription());
            orderTableResponse.setTotalPrice(order.getTotalPrice().toString());
            orderTableResponse.setCode(order.getCode());
            orderTableResponse.setFiles(order.getFiles());
            orderTableResponses.add(orderTableResponse);
        }
        TableResponse response = new TableResponse();
        response.setTotal(count);
        response.setRows(orderTableResponses);
        return response;
    }

    @RequestMapping("/add")
    @ResponseBody
    public Result addOrder(@RequestBody NewOrderDto dto) {
        Customer customer = customerService.findById(dto.getCustomerId());
        if (null == customer) {
            return new Result(500, Result.FAIL, "Can not find this customer !");
        }
        Order order = new Order();
        order.setCustomerId(customer.getId());
        order.setCreatedDate(new Date());
        order.setDeliveryDate(dto.getDeliveryDate());
        order.setDescription(dto.getDescription());
        order.setCode(CommonUtils.genOrderCode(CommonUtils.ORDER_CODE_HEAD_NORMAL));
        Double totalPrice = 0d;
        for (NewOrderDetailDto detail : dto.getDetails()) {
            totalPrice += Double.valueOf(detail.getPrice()) * Double.valueOf(detail.getNumber());
        }
        order.setTotalPrice(totalPrice);
        String fileIdsStr = "";
        for (int i = 0; i < dto.getFiles().size(); i++) {
            fileIdsStr += dto.getFiles().get(i);
            if (i != dto.getFiles().size() - 1) {
                fileIdsStr += ",";
            }
        }
        order.setFiles(fileIdsStr);
        Integer ins = orderService.addOrder(order);
        if (null == ins || ins <= 0) {
            return new Result(500, Result.FAIL, "Add order record failed !");
        }
        Integer res = 0;
        for (NewOrderDetailDto detail : dto.getDetails()) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setName(detail.getName());
            orderDetail.setNumber(Integer.valueOf(detail.getNumber()));
            orderDetail.setPrice(Double.valueOf(detail.getPrice()));
            orderDetail.setOrderId(order.getId());
            orderDetail.setDescription(detail.getDescription());
            res += orderService.addOrderDetail(orderDetail);
        }
        if (null == res || res <= 0) {
            return new Result(500, Result.FAIL, "Add order details failed !");
        }
        return new Result(200, Result.SUCCESS);
    }
}

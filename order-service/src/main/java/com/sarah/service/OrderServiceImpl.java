package com.sarah.service;

import com.sarah.domain.Order;
import com.sarah.domain.OrderDetail;
import com.sarah.domain.OrderDetailRepository;
import com.sarah.domain.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by vagrant on 5/4/17.
 */

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,
                            OrderDetailRepository orderDetailRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Page<Order> findAll(Pageable pageable) {

        return orderRepository.findAll(pageable);
    }

    @Override
    public List<OrderDetail> createOrderDetailByOrderId(int orderId, List<OrderDetail> orderDetails) {

        for (OrderDetail od : orderDetails) {
            od.setOrderId(orderId);
        }
        return orderDetailRepository.save(orderDetails);
    }

    @Override
    public Order findOrderByOrderId(int orderId) {
        return orderRepository.findByOrderId(orderId);
    }

    @Override
    public List<OrderDetail> findOrderDetailByOrderId(int orderId) {
        return orderDetailRepository.findByOrderId(orderId);
    }

    @Override
    public void updateAmountById(double amount, int orderId) {
        orderRepository.updateAmountById(amount, orderId);
    }
}
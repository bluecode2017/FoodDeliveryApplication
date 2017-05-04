package com.sarah.service;

import com.sarah.domain.Order;
import com.sarah.domain.OrderDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by vagrant on 5/4/17.
 */
public interface OrderService {
    public Order createOrder(Order order);

    public Page<Order> findAll(Pageable pageable);

    public Order findOrderByOrderId(int orderId);

    public List<OrderDetail> createOrderDetailByOrderId(int orderId, List<OrderDetail> orderDetails);

    public List<OrderDetail> findOrderDetailByOrderId(int orderId);

    public void updateAmountById(double amount, int orderId);
}

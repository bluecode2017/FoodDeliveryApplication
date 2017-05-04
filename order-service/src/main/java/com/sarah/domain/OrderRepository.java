package com.sarah.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by vagrant on 5/4/17.
 */

public interface OrderRepository extends JpaRepository<Order, Long> {
    //see all the Order
    public Page<Order> findAll(Pageable pageable);

    public Order findByOrderId(int orderId);

    @Modifying
    @Query("update orders o set o.amount = ?1  where o.order_id = ?2")
    public void updateAmountById(double amount, int orderId);
}



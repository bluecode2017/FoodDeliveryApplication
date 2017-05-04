package com.sarah.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by vagrant on 5/4/17.
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    //see all the Order
    public Page<OrderDetail> findAll(Pageable pageable);

    public List<OrderDetail> findByOrderId(int orderId);

}

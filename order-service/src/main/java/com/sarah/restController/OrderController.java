package com.sarah.restController;

import com.sarah.domain.Order;
import com.sarah.domain.OrderDetail;
import com.sarah.service.OrderService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by vagrant on 5/4/17.
 */


@EnableAutoConfiguration
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;
    final String kDefaultPage = "0";
    final String kDefaultItemPerPage = "10";

    //list all orders order by orderId
    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public Page<Order> findAllInfo(@RequestParam(name = "page", defaultValue = kDefaultPage) Integer page,
                                   @RequestParam(name = "size", defaultValue = kDefaultItemPerPage) Integer size) {
        Sort sort = new Sort(Sort.Direction.ASC, "orderId");
        Pageable pageable = new PageRequest(page, size, sort);
        return orderService.findAll(pageable);
    }


    //search order information & orderdetail by orderid
    @RequestMapping(value = "/orders/{id}", method = RequestMethod.GET)
    public ResponseEntity<JSONObject> findByRestaurantName(@PathVariable("id") int id) {
        Order order = orderService.findOrderByOrderId(id);
        List<OrderDetail> orderDetails = orderService.findOrderDetailByOrderId(id);

        JSONObject info = new JSONObject();

        if (order != null) {
            info.put("orderId", order.getOrderId());
            info.put("restaurantname", order.getRestaurantname());
            info.put("note", order.getNote());
            info.put("customer fullname", order.getUser().getFullName());
            info.put("orderCreatedDate", order.getOrderCreatedDate());
            info.put("amount", order.getAmount());
        }
        for (OrderDetail od : orderDetails) {

            info.put("foodName", od.getFoodName());
            info.put("price", od.getPrice());
            info.put("quantity", od.getQuantity());
        }
        return new ResponseEntity<JSONObject>(info, HttpStatus.OK);

    }


    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createRestaurant(@RequestBody Order order) {
        orderService.createOrder(order);
    }


    @RequestMapping(value = "/orders/{orderid}/orderdetail", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createMenuItem(@PathVariable("orderid") int orderId,
                               @RequestBody List<OrderDetail> orderDetails) {
        orderService.createOrderDetailByOrderId(orderId, orderDetails);
        int amount = 0;
        for (OrderDetail od : orderDetails) {
            amount += od.getPrice() * od.getQuantity();
        }
        orderService.updateAmountById(amount, orderId);
    }
}

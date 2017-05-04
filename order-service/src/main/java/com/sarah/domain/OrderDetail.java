package com.sarah.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by vagrant on 5/4/17.
 */
@Table(name = "orderdetail")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Entity
public class OrderDetail {
    private int orderId;
    private String foodName;
    private double price;
    private int quantity;


    public OrderDetail() {

    }

    @JsonCreator
    public OrderDetail(@JsonProperty("foodname") String foodName,
                       @JsonProperty("quantity") int quantity,
                       @JsonProperty("price") double foodPrice,
                       @JsonProperty("orderId") int orderId) {
        this.foodName = foodName;
        this.price = price;
        this.quantity = quantity;
        this.orderId = orderId;
    }
}
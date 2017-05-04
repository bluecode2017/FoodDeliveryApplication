package com.sarah.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by vagrant on 5/4/17.
 */
@Table(name = "orders")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    private String restaurantname = " ";
    private double amount = 0;
    private String note = " ";
    private Date orderCreatedDate = new Date();
    private Date estimatedDeliveryTime;
    private boolean isCompleted = false;
    private int paymentId;
    private Date payTimeStamp;

    @Embedded
    private User user;


    public Order() {
        this.user = null;
    }

    public Order(User user) {
        this.user = user;
    }

    @JsonCreator
    public Order(@JsonProperty("note") String note,
                 @JsonProperty("restaurantname") String restaurantname,
                 @JsonProperty("user") User user) {
        this.note = note;
        this.user = user;
        this.restaurantname = restaurantname;
    }

}

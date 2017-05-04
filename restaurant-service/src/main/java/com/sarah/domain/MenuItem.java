package com.sarah.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by vagrant on 5/2/17.
 */
@Table(name = "menuitems")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
@Entity
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int foodId;

    String foodName;
    String foodDesc;
    double foodPrice;
    int restaurantId;


    public MenuItem() {

    }

    @JsonCreator
    public MenuItem(@JsonProperty("name") String foodName,
                    @JsonProperty("description") String foodDesc,
                    @JsonProperty("price") double foodPrice,
                    @JsonProperty("restaurantId") int restaurantId) {
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.foodDesc = foodDesc;
        this.restaurantId = restaurantId;
    }

}

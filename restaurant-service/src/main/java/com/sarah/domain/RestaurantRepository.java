package com.sarah.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by vagrant on 5/2/17.
 */
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    //see all the restaurent
    Page<Restaurant> findAll(Pageable pageable);

    //search a restaurant based on restaurant name
    Restaurant findByRestaurantName(@Param("restaurant_name") String restaurant_name);

    public List<MenuItem> findAllByRestaurantId(String restaurantId);

}

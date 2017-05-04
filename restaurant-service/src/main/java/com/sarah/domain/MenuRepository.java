package com.sarah.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Sarah Sun on 5/4/17.
 */
public interface MenuRepository extends JpaRepository<MenuItem, Long> {
    //search the menu based on restaurant id
    public List<MenuItem> findAllByRestaurantId(@Param("restaurant_id") int restaurantId);
}
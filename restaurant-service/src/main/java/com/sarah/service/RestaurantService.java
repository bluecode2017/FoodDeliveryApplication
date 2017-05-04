package com.sarah.service;


import com.sarah.domain.MenuItem;
import com.sarah.domain.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by Sarah Sun on 5/2/17.
 */
public interface RestaurantService {
    public List<Restaurant> createRestaurant(List<Restaurant> restaurants);

    public Page<Restaurant> findAll(Pageable pageable);

    public Restaurant findByRestaurantName(String restaurantName);

    public int findRestaurantIdByRestaurantName(String restaurantName);

    public List<MenuItem> createMenuByRestaurantName(String restaurantName, List<MenuItem> menuItems);

    public List<MenuItem> getMenuByRestaurantName(String restaurantName);
}

package com.sarah.service;

import com.sarah.domain.MenuItem;
import com.sarah.domain.MenuRepository;
import com.sarah.domain.Restaurant;
import com.sarah.domain.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by vagrant on 5/2/17.
 */
@Service
public class RestaurantServiceImpl implements RestaurantService {

    private RestaurantRepository restaurantRepository;
    private MenuRepository menuRepository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository,
                                 MenuRepository menuRepository) {
        this.restaurantRepository = restaurantRepository;
        this.menuRepository = menuRepository;
    }

    @Override
    public List<Restaurant> createRestaurant(List<Restaurant> restaurants) {
        return restaurantRepository.save(restaurants);
    }

    @Override
    public Page<Restaurant> findAll(Pageable pageable) {
        return restaurantRepository.findAll(pageable);
    }

    @Override
    public Restaurant findByRestaurantName(String restaurantName) {
        return restaurantRepository.findByRestaurantName(restaurantName);
    }

    @Override
    public int findRestaurantIdByRestaurantName(String restaurantName) {
        return restaurantRepository.findByRestaurantName(restaurantName).getRestaurantId();
    }

    @Override
    public List<MenuItem> createMenuByRestaurantName(String restaurantName, List<MenuItem> menuItems) {

        int restaurantId = findRestaurantIdByRestaurantName(restaurantName);
        for (MenuItem menuItem : menuItems) {
            menuItem.setRestaurantId(restaurantId);
        }
        return menuRepository.save(menuItems);
    }

    @Override
    public List<MenuItem> getMenuByRestaurantName(String restaurantName) {
        int restaurantId = findRestaurantIdByRestaurantName(restaurantName);
        return menuRepository.findAllByRestaurantId(restaurantId);
    }
}

package com.sarah.restController;

import com.sarah.domain.MenuItem;
import com.sarah.domain.Restaurant;
import com.sarah.service.RestaurantService;
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
 * Created by Sarah Sun on 5/2/17.
 */
@EnableAutoConfiguration
@RestController
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;
    final String kDefaultPage = "0";
    final String kDefaultItemPerPage = "10";

    String RestaurantId = "restaurant_id";
    String RestaurantName = "restaurant_name";
    String RestaurantAddress = "restaurant_address";
    String RestaurantDes = "restaurant_description";
    String foodId = "menuitem_id";
    String foodName = "menuitem_name";
    String foodDesc = "fmenuitem_description";
    String foodPrice = "menuitem_price";

    // creat restaurants
    @RequestMapping(value = "/restaurants", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createRestaurant(@RequestBody List<Restaurant> restaurants) {

        restaurantService.createRestaurant(restaurants);
    }

    //create menu for specified  restaurant name
    @RequestMapping(value = "/restaurants/{name}/menuItems", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createMenuItem(@PathVariable("name") String restaurantName,
                               @RequestBody List<MenuItem> menuItems) {
        restaurantService.createMenuByRestaurantName(restaurantName, menuItems);
    }

    //list all restaurants order by restaurantName
    @RequestMapping(value = "/restaurants", method = RequestMethod.GET)
    public Page<Restaurant> findAllInfo(@RequestParam(name = "page", defaultValue = kDefaultPage) Integer page,
                                        @RequestParam(name = "size", defaultValue = kDefaultItemPerPage) Integer size) {
        Sort sort = new Sort(Sort.Direction.ASC, "restaurantName");
        Pageable pageable = new PageRequest(page, size, sort);
        return restaurantService.findAll(pageable);
    }

    //search restaurant information by name and show the menu at the same time
    @RequestMapping(value = "/restaurants/{name}", method = RequestMethod.GET)
    public ResponseEntity<JSONObject> findByRestaurantName(@PathVariable("name") String name) {
        Restaurant result = restaurantService.findByRestaurantName(name);
        List<MenuItem> menuItems = restaurantService.getMenuByRestaurantName(name);
        JSONObject info = new JSONObject();
        if (result != null) {
            info.put(RestaurantId, result.getRestaurantId());
            info.put(RestaurantName, result.getRestaurantName());
            info.put(RestaurantAddress, result.getRestaurantAddress());
            info.put(RestaurantDes, result.getRestaurantDesc());
        }

        for (MenuItem mi : menuItems) {
            info.put(foodId, mi.getFoodId());
            info.put(foodName, mi.getFoodName());
            info.put(foodPrice, mi.getFoodPrice());
            info.put(foodDesc, mi.getFoodDesc());
        }
        return new ResponseEntity<JSONObject>(info, HttpStatus.OK);

    }
/*
    for restaurant has more than one menu
    //get menu for specified restaurant name
    @RequestMapping(value = "/restaurants/{name}/menuItems", method = RequestMethod.GET)
    public List<MenuItem> getMenuItems(@PathVariable("name") String restaurantName) {
        return restaurantService.getMenuByRestaurantName(restaurantName);
    }
    */
}


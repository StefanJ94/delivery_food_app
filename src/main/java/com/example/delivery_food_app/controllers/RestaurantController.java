package com.example.delivery_food_app.controllers;

import com.example.delivery_food_app.model.Restaurant;
import com.example.delivery_food_app.repository.RestaurantJpaRepository;
import com.example.delivery_food_app.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestaurantController {

    private RestaurantRepository restaurantRepository;
    private RestaurantJpaRepository restaurantJpaRepository;

    private static int resId = 1;

    @Autowired
    public RestaurantController(RestaurantRepository restaurantRepository, RestaurantJpaRepository restaurantJpaRepository) {
        this.restaurantRepository = restaurantRepository;
        this.restaurantJpaRepository = restaurantJpaRepository;
    }

    //ENDPOINT -> REGISTER RESTAURANT
    @PostMapping("/api/restaurants")
    public ResponseEntity<Void> register(@RequestBody Restaurant restaurant) {

        // Check if there is already a restaurant with the forwarded name
        if (restaurantJpaRepository.isDuplicateRestaurant(restaurant.getName()) != 0 ) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        restaurant.setRestaurant_id(resId++);
        restaurantRepository.insertRestaurant(restaurant);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
    }
    //ENDPOINT -> GET ALL RESTAURANTS
    @GetMapping("/api/getres")
    public List<Restaurant> gettAllRes() {
       return restaurantRepository.getAllRestaurants();
    }
}

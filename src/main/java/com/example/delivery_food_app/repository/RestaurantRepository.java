package com.example.delivery_food_app.repository;

import com.example.delivery_food_app.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {

    void insertRestaurant(Restaurant restaurant);

    List<Restaurant> getAllRestaurants();
}

package com.example.delivery_food_app.repository;

import com.example.delivery_food_app.model.Restaurant;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository {

    void insertRestaurant(Restaurant restaurant);

    List<Restaurant> getAllRestaurants();
}

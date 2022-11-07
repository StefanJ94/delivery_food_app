package com.example.delivery_food_app.repository;

import com.example.delivery_food_app.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SqlRestaurantRepository implements RestaurantRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public void insertRestaurant(Restaurant restaurant) {
        String action = "INSERT INTO Restaurants ([name], [location]) VALUES ('" + restaurant.getName() + "','"
                + restaurant.getLocation() + "')";

        jdbcTemplate.execute(action);
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        String query = "SELECT restaurant_id, [name], [location] FROM Restaurants";

        return jdbcTemplate.query(
                query,
                BeanPropertyRowMapper.newInstance(Restaurant.class)
        );
    }
}

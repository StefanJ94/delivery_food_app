package com.example.delivery_food_app.repository;

import com.example.delivery_food_app.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class SqlOrderRepository implements OrderRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void insertOrder(Order order) {
        String action = "INSERT INTO Orders (order_status,price,restaurant_id,clients_id) VALUES ('" + order.getOrder_status() + "','"
                + order.getPrice() + "','" + order.getRestaurant().getRestaurant_id() + "','" + order.getClient().getId() + "')";

        jdbcTemplate.execute(action);
    }

    @Override
    public List<Order> getAllOrdersByClientId(Order order) {
        String query = "SELECT order_status,price,restaurant_id,clients_id FROM Orders WHERE clients_id = '" + order.getClient().getId() + "'";

        return jdbcTemplate.query(
                query,
                BeanPropertyRowMapper.newInstance(Order.class)
        );
    }
}

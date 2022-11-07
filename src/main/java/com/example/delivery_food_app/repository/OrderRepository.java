package com.example.delivery_food_app.repository;

import com.example.delivery_food_app.model.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository {

    void insertOrder(Order order);

    List<Order> getAllOrders();
}

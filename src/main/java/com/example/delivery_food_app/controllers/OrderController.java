package com.example.delivery_food_app.controllers;

import com.example.delivery_food_app.model.Order;
import com.example.delivery_food_app.repository.OrderJpaRepository;
import com.example.delivery_food_app.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    private OrderJpaRepository orderJpaRepository;
    private OrderRepository orderRepository;

    public static int num = 1;

    @Autowired
    public OrderController(OrderJpaRepository orderJpaRepository, OrderRepository orderRepository) {
        this.orderJpaRepository = orderJpaRepository;
        this.orderRepository = orderRepository;
    }

    //ENDPOINT -> MAKE ORDER
    @PostMapping("/api/order")
    public ResponseEntity<Void> registerOrder(@RequestBody Order order) {

        order.setOrders_id(num++);
        orderRepository.insertOrder(order);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
    }
}

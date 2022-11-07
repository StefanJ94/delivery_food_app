package com.example.delivery_food_app.repository;

import com.example.delivery_food_app.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderJpaRepository extends JpaRepository<Order,Integer> {

    @Query(value = "INSERT INTO Orders (order_status,price,restaurant_id,clients_id) values (:order_status, :price, :restaurant_id, :clients_id)", nativeQuery = true)
    Void InsertOrder(@Param("order_status") String order_status, @Param("price") Integer price, @Param("restaurant_id") Integer restaurant_id, @Param("clients_id") UUID clients_id);
}

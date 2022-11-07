package com.example.delivery_food_app.repository;

import com.example.delivery_food_app.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantJpaRepository extends JpaRepository<Restaurant,Integer> {

    @Query(value = "INSERT INTO Restaurants ([name], [location]) values (:name, :location)", nativeQuery = true)
    Void InsertRestaurant(@Param("name") String name, @Param("location") String location);

    @Query(value = "SELECT COUNT(*) FROM Restaurants WHERE name=:name", nativeQuery = true) Integer
    isDuplicateRestaurant(@Param("name") String name);

    @Query(value = "SELECT id FROM Restaurants WHERE name=:name", nativeQuery = true) Integer
    getRestaurantByName(@Param("name") String name);
}

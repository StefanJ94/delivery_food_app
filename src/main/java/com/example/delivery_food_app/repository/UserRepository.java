package com.example.delivery_food_app.repository;


import com.example.delivery_food_app.model.Client;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {

    void insertClient(Client client);

    List<Client> getAllClients();
}

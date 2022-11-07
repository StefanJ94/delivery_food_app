package com.example.delivery_food_app.repository;

import com.example.delivery_food_app.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SqlUserRepository implements UserRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public void insertClient(Client client) {

        String action = "INSERT INTO Clients ([id], username, [password], email, userType) values ('" + client.getId() + "','"
                + client.getUsername() + "','" + client.getPassword() + "','" + client.getEmail() + "','" + client.getUserType()+ "')";

        jdbcTemplate.execute(action);

    }

    @Override
    public List<Client> getAllClients() {
        String action= "SELECT * FROM CLIENTS WHERE userType like 'user'";
        return jdbcTemplate.query(
                action,
                BeanPropertyRowMapper.newInstance(Client.class));
    }
}

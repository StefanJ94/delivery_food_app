package com.example.delivery_food_app.controllers;


import com.example.delivery_food_app.model.Client;
import com.example.delivery_food_app.repository.UserJpaRepository;
import com.example.delivery_food_app.repository.UserRepository;
import com.example.delivery_food_app.validation.RegexPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class ClientController {

    private UserRepository userRepository;
    private UserJpaRepository userJpaRepository;

    @Autowired
    public ClientController(UserJpaRepository userJpaRepository, UserRepository userRepository) {
        this.userJpaRepository = userJpaRepository;
        this.userRepository = userRepository;
    }
    //ENDPOINT -> REGISTER CLIENT
    @PostMapping("/api/clients/register")
    public ResponseEntity<Void> register(@RequestBody Client client) {
        Client newClient = new Client(client.getUsername(), client.getPassword(), client.getEmail(), "user");

        // Username at least 3 characters
        if (newClient.getUsername().length() < 3) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        // Password must contain at least one digit [0-9], at least one lowercase Latin character [a-z],
        // at least one uppercase Latin character [A-Z], and at least one special character like ! (JAVA REGEX)
        if (!RegexPassword.isValidPassword(client.getPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        // Email must be valid
        if (!client.getEmail().contains("@") || client.getEmail().length() < 6 || !client.getEmail().contains("."))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        // Check if there is already a user with the forwarded username or email
        if (userJpaRepository.isDuplicateName(newClient.getUsername()) != 0 || userJpaRepository.isDuplicateEmail(newClient.getEmail()) != 0) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        userRepository.insertClient(newClient);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
    }
    // ENDPOINT -> LOGIN CLIENT
    @PostMapping("/api/clients/login")
    public ResponseEntity<String> login(HttpServletRequest request, HttpServletResponse response, @RequestBody Client client) {
        String account = client.getAccount();

        if (userJpaRepository.isDuplicateName(account) > 0) {
            String token = userJpaRepository.getClientByName(account);
            String password = userJpaRepository.getPasswordById(token);

            if (password.equals(client.getPassword())) {
                HttpSession session = request.getSession();
                session.setAttribute("token", token);
                return ResponseEntity.status(HttpStatus.OK).body(token);

            } else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } else if (userJpaRepository.isDuplicateEmail(account) > 0) {
            String token = userJpaRepository.getClientByEmail(account);
            String password = userJpaRepository.getPasswordById(token);
            if (password.equals(client.getPassword())) {
                response.addHeader("token", token);
                return ResponseEntity.status(HttpStatus.OK).body(token);
            } else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    // ENDPOINT -> GET ALL CLIENTS (CUSTOMERS)
    @GetMapping("/api/clients")
    public List<Client> getAllUsers() {
        return userRepository.getAllClients();
    }
}

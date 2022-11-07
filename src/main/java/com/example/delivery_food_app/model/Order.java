package com.example.delivery_food_app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name= "Orders")
public class Order {

    @Id
    private int orders_id;
    private String order_status;
    private int price;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;


    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<MenuItem> items;

    public void addItem(MenuItem menuItem) {
        this.items.add(menuItem);
    }

    public List<MenuItem> getItems() {
        return this.items;
    }

    public Order() {
    }

    public int getOrders_id() {
        return orders_id;
    }

    public void setOrders_id(int orders_id) {
        this.orders_id = orders_id;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

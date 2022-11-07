package com.example.delivery_food_app.model;


import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name= "Menu")
public class Menu {

    @Id
    private int menu_id;
    private String info;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<MenuItem> items;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @JsonSetter
    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @JsonGetter
    public Restaurant getRestaurant() {
        return restaurant;
    }

    public Menu(String info, Restaurant restaurant) {
        this.info = info;
        this.restaurant = restaurant;
    }

    public Menu() {
    }

    public int getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(int menu_id) {
        this.menu_id = menu_id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @JsonCreator
    public Menu(@JsonProperty("menu_id") Integer menu_id, @JsonProperty("info") String info, @JsonProperty("items") List<MenuItem> items) {
        this.menu_id = menu_id;
        this.info = info;
        if (items != null) {
            this.items = items;
            for (MenuItem item : items)
                item.setMenu(this);
        }
    }
}

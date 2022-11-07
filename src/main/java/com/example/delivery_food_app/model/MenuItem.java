package com.example.delivery_food_app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name= "MenuItem")
public class MenuItem {

    @Id
    private int menu_item_id;
    private String name;
    private String info;
    private int price;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "menu_id")
    private Menu menu;


    public MenuItem(int menu_item_id, String name, String info, int price, Menu menu) {
        this.menu_item_id = menu_item_id;
        this.name = name;
        this.info = info;
        this.price = price;
        this.menu = menu;
    }

    public MenuItem() {
    }

    public MenuItem(String name, String info, int price) {
        this.name = name;
        this.info = info;
        this.price = price;
    }

    public MenuItem(@JsonProperty("menu_item_id") Integer menu_item_id, @JsonProperty("name") String name,
                    @JsonProperty("info") String info, @JsonProperty("price") Integer price) {
        this.menu_item_id = menu_item_id;
        this.name = name;
        this.info = info;
        this.price = price;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "id=" + menu_item_id +
                ", name='" + name + '\'' +
                ", info='" + info + '\'' +
                ", price=" + price +
                '}';
    }
}

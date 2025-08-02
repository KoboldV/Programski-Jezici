package com.example.RestStorage.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor


@Entity
@Getter
@Setter

public class Inventory {


    @Id

    @GeneratedValue(strategy =  GenerationType.IDENTITY)

    private Long id;

    private String name;

    private String category;

    private int count;

    private double price;

    private double totalPrice;

    private Date timestamp;




@Enumerated(EnumType.STRING)
    private Location location;




    @PrePersist
    @PreUpdate

    private void calculateTotalPrice() {

        this.totalPrice=this.price * this.count;
    }



    public Inventory(String name, String category , int count , double price, Location location, Date timestamp) {
        this.name = name;

        this.category = category;

        this.count = count;

        this.price=price;

        this.location = location;

        this.totalPrice = price*count;

        this.timestamp = timestamp;

    }






}



package com.example.RestStorage.model;


import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Inventory {


    @Id

    @GeneratedValue(strategy =  GenerationType.IDENTITY)

    private Long id;

    private String name;

    private String category;

    private int count;

    private double price;

    private double totalPrice;


@Enumerated(EnumType.STRING)
    private Location location;




    @PrePersist
    @PreUpdate

    private void calculateTotalPrice() {

        this.totalPrice=this.price * this.count;
    }



    public Inventory(String name, String category , int count , double price, Location location) {
        this.name = name;

        this.category = category;

        this.count = count;

        this.price=price;

        this.location = location;

        this.totalPrice = price*count;

    }



}
//    @Column(updatable = false)
//    private java.time.LocalDateTime createdAt;


//    @PrePersist
//    protected void onCreate() {
//        this.createdAt = java.time.LocalDateTime.now();
//    }
package com.example.RestStorage.repository;


import com.example.RestStorage.model.Inventory;
import com.example.RestStorage.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory,Long> {

//    List<Inventory> findTop10ByOrderCreatedAtDesc();

    List<Inventory> findByNameContainingIgnoreCase(String name);
    List<Inventory> findByCategoryContainingIgnoreCase(String category);


}
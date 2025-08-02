package com.example.RestStorage.repository;
import com.example.RestStorage.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface InventoryRepository extends JpaRepository<Inventory,Long> {



    List<Inventory> findByNameContainingIgnoreCase(String name);
    List<Inventory> findByCategoryContainingIgnoreCase(String category);
    List<Inventory> findTop10ByOrderByTimestampDesc();


}
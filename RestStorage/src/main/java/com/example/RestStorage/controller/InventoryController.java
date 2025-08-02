package com.example.RestStorage.controller;


import com.example.RestStorage.InventoryService.InventoryService;
import com.example.RestStorage.model.Inventory;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/api/inventory")

public class InventoryController {

    private static final Logger logger =LoggerFactory.getLogger(InventoryController.class);

    private final InventoryService service;

    @GetMapping("/latest")
    public List<Inventory> getLatestInventories() {
        return service.getLast10Inventories();
    }


    public InventoryController(InventoryService service) {
        this.service = service;
    }

    @GetMapping
    public List<Inventory> getAllInventory() {
        return service.findAll();
    }

    @GetMapping("/{id}")

    public ResponseEntity getInventoryById(@PathVariable Long id) {
        Optional<Inventory> optionalInventory = service.findById(id);
        if (optionalInventory.isPresent()) {
            Inventory inventory = optionalInventory.get();
            return ResponseEntity.ok(inventory);
        }else {
            return ResponseEntity.notFound().build();
        }

    }



    @GetMapping("/search")
    public List<Inventory> searchInventory(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category)
    {
        if (name != null) {
            return service.searchByName(name);
        } else if (category != null) {
            return service.searchByCategory(category);

        } else {
            return service.findAll();
        }

    }



    @PostMapping
    public Inventory createInventory(@RequestBody Inventory inventory) {
        inventory.setTimestamp(Date.from(Instant.now()));
        return service.save(inventory);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Inventory> updateInventory(@PathVariable Long id, @RequestBody Inventory updated){

        Optional<Inventory> optionalInventory = service.findById(id);

        if (optionalInventory.isPresent()) {
            Inventory existing = optionalInventory.get();
            existing.setName(updated.getName());
            existing.setCategory(updated.getCategory());
            existing.setCount(updated.getCount());
            existing.setPrice(updated.getPrice());
            existing.setLocation(updated.getLocation());
            existing.setTimestamp(Date.from(Instant.now()));

            logger.info("Updated item with ID  : {}" , existing.getId());
            Inventory saved = service.save(existing);



            return ResponseEntity.ok(saved);
        }else {
            return ResponseEntity.notFound().build();
        }

        }

    @DeleteMapping("/{id}")

    public ResponseEntity<Void> deleteInventory(@PathVariable Long id)   {

        if (service.findById(id).isPresent()) {

            service.deleteID(id);
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/all")

    public ResponseEntity<String> deleteAll() {
        service.deleteAll();
        return ResponseEntity.ok("Svi inventari su obrisani.");
    }


}








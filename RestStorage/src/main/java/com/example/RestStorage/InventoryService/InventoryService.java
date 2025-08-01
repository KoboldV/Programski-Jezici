package com.example.RestStorage.InventoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ch.qos.logback.core.util.StringUtil;
import com.example.RestStorage.model.Inventory;
import com.example.RestStorage.model.Location;
import com.example.RestStorage.repository.InventoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service

public class InventoryService {


    private static final Logger logger =LoggerFactory.getLogger(InventoryService.class);


    private final InventoryRepository repository;


    public InventoryService(InventoryRepository repository) {
        this.repository = repository;
    }

    public Inventory save(Inventory inventory) {


        logger.info("Creating new item : {} {} ", inventory.getName(),  inventory.getCategory()) ;


        if (inventory.getCount() < 0) {
            logger.warn("Tried to enter negativ number for count {}", inventory.getName());
            throw new IllegalArgumentException("Count must be positive");
        }

        Validator.validatorInt(inventory.getCount(), "Count");

        Validator.validatorDouble(inventory.getPrice(), "Price");

        Validator.validatorString(inventory.getName(), "Name");

        Validator.validatorString(inventory.getCategory(), "Category");

        Inventory saved = repository.save(inventory);
        logger.info("Item saved under ID : {}" , saved.getId());

        return saved;
    }


//    private void validatorString(String name, String category){
//        if (StringUtils.isEmpty(string)) {
//            throw new RuntimeException("String must not be null");
//        }
//
//    }
//

//    private void validator1000(int count){
//
//    }
//
//    private void validator11000(double price) {
//
//    }


    public List<Inventory> searchByName(String name) {
        logger.info("Searching for an item : {}" , name);
        return repository.findByNameContainingIgnoreCase(name);
    }

    public List<Inventory> searchByCategory(String category) {
        logger.info("Searching for all items that belong to: {}" , category);
        return repository.findByCategoryContainingIgnoreCase(category);
    }





    public List<Inventory> findAll() {
        logger.info("Displaying all items in the database ");
        return repository.findAll();
    }


    public Optional<Inventory> findById(Long id) {

        logger.info("Searching for an item under ID: {}" ,id);
        return repository.findById(id);
    }




//    public  Optional<Inventory> findByName(String name) {
//        return repository.findBy()
//    }





    public void deleteAll(){

        logger.info("Database cleared");
        repository.deleteAll();
    }

    public void deleteID(Long id) {

        logger.info("Deleted an item: {}" , id);

        repository.deleteById(id);
    }


//    public List<Inventory> findLast10Added() {
//        return repository.findTop10ByOrderCreatedAtDesc();
//    }








}





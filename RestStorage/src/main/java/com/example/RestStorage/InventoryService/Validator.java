package com.example.RestStorage.InventoryService;

import com.example.RestStorage.model.Location;

public class Validator {


    public static void validatorString(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " can not be empty");
        }
    }


    public static void validatorInt(int value , String fieldName) {
        if (value < 0 ) {
            throw new IllegalArgumentException(fieldName + " must be positive");
        }
    }


    public static void validatorDouble(double value , String fieldName) {
        if (value < 0) {
            throw new IllegalArgumentException(fieldName + " must be positive");
        }
    }

//    public static void validatorLocation(Location location, String fieldName) {
//
//        if (!(location == Location.MAGACIN_E ||
//                location == Location.MAGACIN_P ||
//                location == Location.MAGACIN_G ||
//                location== Location.MAGACIN_H)) {
//
//            throw new IllegalArgumentException("""
//                    Invalid location
//                    Available locations:
//                    MAGACIN_E
//                    MAGACIN_G
//                    MAGACIN_H
//                    MAGACIN_P
//                    """);
//        }
//    }

    }


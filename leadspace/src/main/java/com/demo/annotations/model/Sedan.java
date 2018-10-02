package com.demo.annotations.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Sedan extends Car {

    @JsonCreator
    public Sedan(@JsonProperty("make")String make, @JsonProperty("modell")String modell,
                 @JsonProperty("seatingCapacity")int seatingCapacity, @JsonProperty("topSpeed")double topSpeed,
                 @JsonProperty("yearManufacturing")int yearManufacturing,
                 @JsonProperty("engine")String engine
                 ) {
        super(make, modell, seatingCapacity, topSpeed, yearManufacturing, engine);
    }



}

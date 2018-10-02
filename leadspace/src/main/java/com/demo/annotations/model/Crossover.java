package com.demo.annotations.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Crossover extends Car {
    private double towingCapacity;

    @JsonCreator
    public Crossover(@JsonProperty("make")String make,
                     @JsonProperty("modell")String modell,
                     @JsonProperty("seatingCapacity")int seatingCapacity,
                     @JsonProperty("topSpeed")double topSpeed,
                     @JsonProperty("yearManufacturing")int yearManufacturing,
                     @JsonProperty("engine")String engine,
                     @JsonProperty("towingCapacity")double towingCapacity) {
        super(make, modell, seatingCapacity, topSpeed, yearManufacturing, engine);
        this.towingCapacity = towingCapacity;
    }

    public double getTowingCapacity() {
        return towingCapacity;
    }

    public void setTowingCapacity(double towingCapacity) {
        this.towingCapacity = towingCapacity;
    }
}

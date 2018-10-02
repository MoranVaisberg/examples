package com.demo.annotations.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "modell", "seatingCapacity" })
public abstract class Car extends Vehicle {
    private int seatingCapacity;

    private double topSpeed;

    private String engine;
    private int yearManufacturing;

    protected Car(String make, String modell, int seatingCapacity, double topSpeed, int yearManufacturing, String engine) {
        super(make, modell);
        this.seatingCapacity = seatingCapacity;
        this.topSpeed = topSpeed;
        this.yearManufacturing = yearManufacturing;
    }

    public int getSeatingCapacity() {
        return seatingCapacity;
    }

    public void setSeatingCapacity(int seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }
    @JsonIgnore
    public double getTopSpeed() {
        return topSpeed;
    }

    public void setTopSpeed(double topSpeed) {
        this.topSpeed = topSpeed;
    }

    public int getYearManufacturing() {
        return yearManufacturing;
    }

    public void setYearManufacturing(int yearManufacturing) {
        this.yearManufacturing = yearManufacturing;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }
}
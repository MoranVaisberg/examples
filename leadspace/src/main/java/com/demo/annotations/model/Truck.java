package com.demo.annotations.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Truck extends Vehicle {
    private double payloadCapacity;

    @JsonCreator
    public Truck(@JsonProperty("make")String make, @JsonProperty("modell")String modell,
                 @JsonProperty("payloadCapacity")double payloadCapacity) {
        super(make, modell);
        this.payloadCapacity = payloadCapacity;
    }

    public double getPayloadCapacity() {
        return payloadCapacity;
    }

    public void setPayloadCapacity(double payloadCapacity) {
        this.payloadCapacity = payloadCapacity;
    }

}
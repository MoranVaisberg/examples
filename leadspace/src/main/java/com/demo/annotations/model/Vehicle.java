package com.demo.annotations.model;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Car.class, name = "car"),
        @JsonSubTypes.Type(value = Sedan.class, name = "sedan"),
        @JsonSubTypes.Type(value = Crossover.class, name = "crossover"),
        @JsonSubTypes.Type(value = Truck.class, name = "truck")
})
public abstract class Vehicle {
    private String make;
    private String modell;


    protected Vehicle(String make, String modell) {
        this.make = make;
        this.modell = modell;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return modell;
    }

    public void setModel(String modell) {
        this.modell = modell;
    }
}

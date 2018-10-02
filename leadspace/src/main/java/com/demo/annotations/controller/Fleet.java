package com.demo.annotations.controller;


import com.demo.annotations.model.Vehicle;

import java.util.List;

public class Fleet {
    private List<Vehicle> vehicles;

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }


}
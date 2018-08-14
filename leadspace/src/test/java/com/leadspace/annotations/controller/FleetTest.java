package com.leadspace.annotations.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leadspace.annotations.model.*;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.*;

public class FleetTest {


    @Test
    public void testFleet() throws IOException {
        System.out.println("testFleet");
        Sedan sedan = new Sedan("Mercedes-Benz", "S500", 5, 250.0, 2010, "Strong");
        Truck truck = new Truck("Isuzu", "NQR", 7500.0);

        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(sedan);
        vehicles.add(truck);

        Fleet serializedFleet = new Fleet();
        serializedFleet.setVehicles(vehicles);

        ObjectMapper mapper = new ObjectMapper();
        mapper.enableDefaultTyping();
        String jsonDataString = mapper.writeValueAsString(serializedFleet);
        System.out.println("jsonDataString:" + jsonDataString);
        Fleet deserializedFleet = mapper.readValue(jsonDataString, Fleet.class);

        assertThat(deserializedFleet.getVehicles().get(0), instanceOf(Car.class));
        assertThat(deserializedFleet.getVehicles().get(1), instanceOf(Truck.class));

        assertEquals(deserializedFleet.getVehicles().get(0).getModel(), "S500");
        System.out.println(deserializedFleet.getVehicles().get(0).getModel());
    }

    @Test
    public void testIgnorance() throws IOException {
        System.out.println("testIgnorance");
        ObjectMapper mapper = new ObjectMapper();
        mapper.enableDefaultTyping();

        Sedan sedan = new Sedan("Mercedes-Benz", "S500", 5, 250.0, 2010, "Strong");
        Crossover crossover = new Crossover("BMW", "X6", 5, 250.0, 2017,"Weak",6000.0);

        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(sedan);
        vehicles.add(crossover);

        String jsonDataString = mapper.writeValueAsString(vehicles);
        System.out.println(jsonDataString);

        assertThat(jsonDataString, containsString("make"));
        assertThat(jsonDataString, not(containsString("modell")));
        assertThat(jsonDataString, not(containsString("seatingCapacity")));
        assertThat(jsonDataString, not(containsString("topSpeed")));
        assertThat(jsonDataString, containsString("towingCapacity"));
    }

    @Test
    public void testMixIns() throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.addMixIn(Car.class, CarMixIn.class);

        Sedan sedan = new Sedan("Mercedes-Benz", "S500", 5, 250.0, 2010, "Strong");
        Crossover crossover = new Crossover("BMW", "X6", 5, 250.0, 2017,"Weak",6000.0);

        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(sedan);
        vehicles.add(crossover);

        String jsonDataString = mapper.writeValueAsString(vehicles);
        System.out.println(jsonDataString);

        assertThat(jsonDataString, containsString("make"));
        assertThat(jsonDataString, not(containsString("modell")));
        assertThat(jsonDataString, not(containsString("seatingCapacity")));
        assertThat(jsonDataString, not(containsString("topSpeed")));
        assertThat(jsonDataString, not(containsString("yearManufacturing")));
        assertThat(jsonDataString, containsString("towingCapacity"));
    }

    @Test
    public void testIntrospector() throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.setAnnotationIntrospector(new IgnoranceIntrospector());

        Sedan sedan = new Sedan("Mercedes-Benz", "S500", 5, 250.0, 2010, "Strong");
        Crossover crossover = new Crossover("BMW", "X6", 5, 250.0, 2017,"Weak",6000.0);

        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(sedan);
        vehicles.add(crossover);

        String jsonDataString = mapper.writeValueAsString(vehicles);
        System.out.println(jsonDataString);

        assertThat(jsonDataString, containsString("make"));
        assertThat(jsonDataString, not(containsString("modell")));
        assertThat(jsonDataString, not(containsString("seatingCapacity")));
        assertThat(jsonDataString, not(containsString("topSpeed")));
        assertThat(jsonDataString, not(containsString("yearManufacturing")));
        assertThat(jsonDataString, not(containsString("engine")));
        assertThat(jsonDataString, containsString("towingCapacity"));
    }
}
package com.leadspace.annotations.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class CarMixIn {
    @JsonIgnore
    public int yearManufacturing;

}
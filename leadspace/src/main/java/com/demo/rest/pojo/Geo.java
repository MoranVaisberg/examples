package com.demo.rest.pojo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Geo {
	double latitude;
	double longitude;

	@JsonCreator
	public Geo(@JsonProperty("latitude")double latitude, @JsonProperty("longitude")double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public double getLongitude() {
		return longitude;
	}

}

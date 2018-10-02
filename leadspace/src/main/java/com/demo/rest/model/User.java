package com.demo.rest.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.demo.rest.pojo.Address;
import com.demo.rest.pojo.Company;

public class User {

	
	int id;
	String name;
	String userId;
	String email;
	Address address;
	String phone;
	String website;
	Company company;

	@JsonCreator
	public User(@JsonProperty("id")int id, @JsonProperty("name")String name, @JsonProperty("userId")String userId,
				@JsonProperty("email")String email, @JsonProperty("address")Address address,
				@JsonProperty("phone")String phone, @JsonProperty("website")String website,
				@JsonProperty("company")Company company
			) {
		this.id = id;
		this.name = name;
		this.userId = userId;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.website = website;
		this.company = company;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getUserId() {
		return userId;
	}

	public String getEmail() {
		return email;
	}

	public Address getAddress() {
		return address;
	}

	public String getPhone() {
		return phone;
	}

	public String getWebsite() {
		return website;
	}

	public Company getCompany() {
		return company;
	}
	
	
	
}

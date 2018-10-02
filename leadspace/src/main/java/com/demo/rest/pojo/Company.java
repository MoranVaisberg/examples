package com.demo.rest.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;

public class Company {

	private String name;
	private String catchPhrase;
	private String bs;

	@JsonCreator
	public Company(@JsonProperty("name") String name, @JsonProperty("catchPhrase") String catchPhrase, @JsonProperty("bs") String bs) {
		this.name = name;
		this.catchPhrase = catchPhrase;
		this.bs = bs;
	}

	public String getName() {
		return name;
	}
	public String getCatchPhrase() {
		return catchPhrase;
	}
	public String getBs() {
		return bs;
	}
	
	
}

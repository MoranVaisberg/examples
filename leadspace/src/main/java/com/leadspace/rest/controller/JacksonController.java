package com.leadspace.rest.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leadspace.rest.model.User;

public class JacksonController{

	ObjectMapper mapper = new ObjectMapper();
	TypeReference<List<User>> listType = new TypeReference<List<User>>() { };
	TypeReference<Map<String,List<String>>> mapType = new TypeReference<Map<String, List<String>>>() {};
	//MyClass[] myObjects = mapper.readValue(json, MyClass[].class);
	//List<MyClass> myObjects = mapper.readValue(jsonInput, new TypeReference<List<MyClass>>(){});
	//List<MyClass> myObjects = mapper.readValue(jsonInput, mapper.getTypeFactory().constructCollectionType(List.class, MyClass.class));

	public void writeToFile(List<User> users) throws Exception {
	     mapper.writeValue(new File("student.json"), users);
	}
	
	public List<User> readFromFile() throws Exception {
		return mapper.readValue(new File("student.json"), listType);
	}

	public List<User> getUsers(String jsonUser) throws Exception{
		JavaType type = mapper.getTypeFactory().constructCollectionType(List.class, User.class);
		return mapper.readValue(jsonUser, type);
	}

	public String serialize(List<User> users) throws Exception {
		return mapper.writeValueAsString(users);
	}
	public List<User> deserialize(String usersString) throws Exception {
		return mapper.readValue(usersString,listType);
	}
}

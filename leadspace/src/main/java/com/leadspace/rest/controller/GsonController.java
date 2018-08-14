package com.leadspace.rest.controller;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.leadspace.rest.model.User;

public class GsonController  {

	Gson gson = new Gson();
	Type userListType = new TypeToken<ArrayList<User>>(){}.getType();

	
	public List<User> fromJson2List(String responseEntityBody) {
	    return gson.fromJson(responseEntityBody, userListType);  
	}

	public User fromJson(String responseEntityBody) {
		return gson.fromJson(responseEntityBody, User.class);
	}
	
	public String serialize(List<User> users) {
		return gson.toJson(users, userListType);		
	}
	
	public List<User> deserialize(String str) {
		return gson.fromJson(str, userListType);
	}
}

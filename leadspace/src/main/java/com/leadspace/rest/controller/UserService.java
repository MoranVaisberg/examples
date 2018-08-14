package com.leadspace.rest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.JavaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.leadspace.rest.model.User;

/**
 * Handles requests for the User service.
 */
@Controller
@RequestMapping(value = "/api")
public class UserService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	UserController userController = new UserController();
	List<User> users = userController.get("/users");
	
	public UserService () throws Exception {
		System.out.println(users);
	}
	
	
	@RequestMapping(value = "/users/{id}")
	public @ResponseBody Optional<User> getUser(@PathVariable("id") int id) {
		logger.info("Start getUser. ID="+id);		
		return users.parallelStream().filter(e->e.getId() == id).findAny();
	}
	
	@RequestMapping(value = "/users/map")
	public @ResponseBody Map<Integer, List<User>> getUsersGroupedById() {
		logger.info("Start getUsersGroupedById. " );		
		return users.stream().collect(Collectors.groupingBy(User::getId));
	}
	
		
	@RequestMapping(value = "/users/byName")
	public @ResponseBody List<User> getUserByName(@RequestParam(value="name", required=true) String name) {
		logger.info("Start getUser Name="+name);
		return users.stream().filter(e->e.getName().equals(name)).collect(Collectors.toList());
	}
	
	@RequestMapping(value = "/users")
	public @ResponseBody List<User> getAllUsers() {
		logger.info("Start getAllUser.");
		return users; 		
	}
	
	@RequestMapping(value = "/users/jackson")
	public @ResponseBody List<User> getJacksonSerializedUsers() throws Exception {
		logger.info("Start getSerializedUsers.");		
		JacksonController jackson = new JacksonController();
		return jackson.deserialize(jackson.serialize(users));
	}
	
	@RequestMapping(value = "/users/gson")
	public @ResponseBody List<User> getGsonSerializedUsers() throws Exception {
		logger.info("Start getSerializedUsers.");

		GsonController gson = new GsonController();
		return  gson.deserialize(gson.serialize(users));
	}
	
	@RequestMapping(value = "users/reload/id", method = RequestMethod.POST)
	public @ResponseBody User reloadUser(@RequestBody int id) throws Exception {
		logger.info("Start reloadUser.");

		users.remove(users.parallelStream().filter(e->e.getId() == id).findAny());
		User user = userController.get("/users/", id);
		users.add(user);

		return user;
	}

	@RequestMapping(value = "users/reload/user", method = RequestMethod.POST)
	public @ResponseBody List<User> reloadUser(@RequestBody String jsonUser) throws Exception {
		logger.info("Start reloadUser.");

		return getReloadedUsers(new JacksonController().getUsers(jsonUser));
	}

	private List<User> getReloadedUsers(List<User> usersToReload) throws Exception {
		List<User> newUsers = new ArrayList<>();
		users.removeAll(usersToReload);
		for (User user: usersToReload ) {
			newUsers.add(userController.get("/users/", user.getId()));
		}

		users.addAll(newUsers);
		return newUsers;
	}

	@RequestMapping(value = "users/delete/{id}", method = RequestMethod.DELETE)
	public @ResponseBody List<User> deleteUser(@PathVariable("id") int id) {
		logger.info("Start deleteUser.");
		List<User> usersToRemove = users.stream().filter(e->e.getId() == id).collect(Collectors.toList());
		users.removeAll(usersToRemove);
		return usersToRemove;
	}
	
}

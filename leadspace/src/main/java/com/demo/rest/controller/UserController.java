package com.demo.rest.controller;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.demo.rest.model.User;


public class UserController {

	private static String server = "https://jsonplaceholder.typicode.com";
	private RestTemplate rest;
	private HttpHeaders headers;
	private HttpStatus status;
	
	
	public UserController() {
		this.rest = new RestTemplate();
	    this.headers = new HttpHeaders();
	    headers.add("Content-Type", "application/json");
	    headers.add("Accept", "*/*");
	}
	
	public List<User> get(String uri) throws Exception {
	    return new GsonController().fromJson2List(getUsersFromServer(server + uri));
	}

	public User get(String uri, int id) throws Exception {
		return new GsonController().fromJson(getUsersFromServer(server + uri + "/" + id));
	}

	private String getUsersFromServer(String uri){
		/*ResponseEntity<String> response = rest.getForEntity("https://jsonplaceholder.typicode.com/todos", String.class);*/
		HttpEntity<String> requestEntity = new HttpEntity<String>("", headers);
		ResponseEntity<String> responseEntity = rest.exchange(uri, HttpMethod.GET, requestEntity, String.class);
		this.setStatus(responseEntity.getStatusCode());
		return responseEntity.getBody();
	}
	
	  public String post(String uri, String json) {   
		    HttpEntity<String> requestEntity = new HttpEntity<String>(json, headers);
		    ResponseEntity<String> responseEntity = rest.exchange(server + uri, HttpMethod.POST, requestEntity, String.class);
		    this.setStatus(responseEntity.getStatusCode());
		    return responseEntity.getBody();
	  }

	  public void put(String uri, String json) {
		    HttpEntity<String> requestEntity = new HttpEntity<String>(json, headers);
		    ResponseEntity<String> responseEntity = rest.exchange(server + uri, HttpMethod.PUT, requestEntity, String.class);
		    this.setStatus(responseEntity.getStatusCode());   
	  }

	  public void delete(String uri) {
		    HttpEntity<String> requestEntity = new HttpEntity<String>("", headers);
		    ResponseEntity<String> responseEntity = rest.exchange(server + uri, HttpMethod.DELETE, requestEntity, String.class);
		    this.setStatus(responseEntity.getStatusCode());
	  }

	  public HttpStatus getStatus() {
		    return status;
	  }

	  public void setStatus(HttpStatus status) {
		    this.status = status;
	  } 
}

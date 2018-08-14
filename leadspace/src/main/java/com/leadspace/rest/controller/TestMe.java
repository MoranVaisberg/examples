package com.leadspace.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leadspace.rest.model.Company;
import com.leadspace.rest.model.User;


public class TestMe {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = "{\n\"id\": 2,\n\"name\": \"Ervin Howell\",\n\"userId\": null,\n\"email\": \"Shanna@melissa.tv\",\n\"address\":{\"street\": \"Victor Plains\", \"suite\": \"Suite 879\", \"city\": \"Wisokyburgh\", \"zipcode\": \"90566-7771\"},\n\"phone\": \"010-692-6593 x09125\",\n\"website\": \"anastasia.net\",\n\"company\":{\n\"name\": \"Deckow-Crist\",\n\"catchPhrase\": \"Proactive didactic contingency\",\n\"bs\": \"synergize scalable supply-chains\"\n}\n}";
		User user1 = mapper.readValue(jsonInString, User.class);
		//String compString = "{\"name\": \"Deckow-Crist\",\"catchPhrase\": \"Proactive didactic contingency\",\"bs\": \"synergize scalable supply-chains\"}";

		//Company company = mapper.readValue(compString, Company.class);

		System.out.println(user1.getName());
	}

}

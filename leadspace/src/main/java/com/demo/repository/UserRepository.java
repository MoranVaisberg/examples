package com.demo.repository;

import com.demo.rest.model.User;
import org.springframework.data.couchbase.repository.CouchbaseRepository;

public interface UserRepository extends CouchbaseRepository<User, String> {


    User findByCustomerNameAndProjectName(String customerName, String projectName);

}
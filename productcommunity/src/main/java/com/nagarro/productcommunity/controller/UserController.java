package com.nagarro.productcommunity.controller;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityNotFoundException;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.productcommunity.model.Login;
import com.nagarro.productcommunity.model.User;
import com.nagarro.productcommunity.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController
{
	@Autowired
	UserService userService;
	
	
	@PostMapping(value = "/register",consumes = "application/json")
	public ResponseEntity<?> createUser(@RequestBody User user)
	{
		try 
		{
			//User created and saved in database successfully 
			userService.registerUser(user);
		    return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(SQLIntegrityConstraintViolationException e)
		{
			// User with existing email want to register
		    return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	
	
	@PostMapping(value = "/login",consumes = "application/json")
	public ResponseEntity<JSONObject> loginUser(@RequestBody Login loginData)
	{
		User loggedInUser=null;
		try
		{
			loggedInUser=userService.userAuth(loginData);
		}
		catch(EntityNotFoundException e)
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		JSONObject userDetails=new JSONObject();
		userDetails.put("userEmail", loggedInUser.getEmail());
		userDetails.put("firstName", loggedInUser.getFirstName());
		userDetails.put("lastName", loggedInUser.getLastName());
		return new ResponseEntity<>(userDetails,HttpStatus.OK);
		
	}
	
	@GetMapping(value="/users")
	public ResponseEntity<?> getAllUsers()
	{
	//	List<String> userNames=userService.getAllUserNames();
		Set<String> userNames = new LinkedHashSet<String>(userService.getAllUserNames());
		return new ResponseEntity<>(userNames,HttpStatus.OK);
	}
	
	
	
	
	
	
	
}
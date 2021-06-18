package com.nagarro.productcommunity.service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.productcommunity.dao.UserDao;
import com.nagarro.productcommunity.model.Login;
import com.nagarro.productcommunity.model.User;


@Service
public class UserService
{
	@Autowired
	UserDao userDao;
	
	
	public List<User> getAllUsers()
	{
		List<User> allUsers=userDao.findAll();
		return allUsers;
	}
	
	public User registerUser(User user) throws SQLIntegrityConstraintViolationException
	{
		List<User> allUsers=getAllUsers();
		for(User tempUser:allUsers)
		{
			if(user.getEmail().equals(tempUser.getEmail()))
			{
				throw new SQLIntegrityConstraintViolationException();
			}
		}
		return userDao.save(user);
	}
	
	public User userAuth(Login loginData)throws EntityNotFoundException
	{
		try
		{
			User gotUser=userDao.getById(loginData.getEmail());
			if(gotUser.getPassword().equals(loginData.getPassword()))
			{
				return gotUser;
			}
			else 
			{
				throw new EntityNotFoundException("Password Incorrect");
			}
		}
		catch(EntityNotFoundException e)
		{
			throw new EntityNotFoundException("No user exist with this email id");
		}	
	}
	
	public User getUserByEmail(String email)throws EntityNotFoundException
	{
		return userDao.getById(email);
	}
	
	public int getUsersCount()
	{
		int usersCount=(int) userDao.count();
		return usersCount;
	}
	
	public List<String> getAllUserNames()
	{
		return userDao.findUserNames();
	}
	
	
	
	
	
}


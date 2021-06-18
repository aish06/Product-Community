package com.nagarro.productcommunity.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.productcommunity.model.User;
import org.springframework.data.jpa.repository.Query;


public interface UserDao extends JpaRepository<User,String>
{

	
	@Query("SELECT firstName FROM User")
	List<String> findUserNames();
}

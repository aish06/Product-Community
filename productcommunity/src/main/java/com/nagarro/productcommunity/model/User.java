package com.nagarro.productcommunity.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "User_Details") //Name of table in database
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User implements Serializable
{
//	@Id
//  	@Column(name = "User_Id")
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	int userId;
	
	@Id
	@Column(name="Email",unique = true)
	@Size(max = 30)
	@Email
	String email; //Primary key in database
	
  	@Column(name = "First_Name")
	String firstName;
  	
  	@Column(name = "Last_Name")
	String lastName;
  	
  	@Column(name = "Password")
	String password;
	
  	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    Set<Question> questions = new HashSet<>();
  	
  	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    Set<Comment> comments = new HashSet<>();
  	
	public User() {}

	
	
	public User(String email, String firstName, String lastName, String password) {
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
	}



	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



	public Set<Question> getQuestions() {
		return questions;
	}



	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}



	public Set<Comment> getComments() {
		return comments;
	}



	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}



	
	
	
}
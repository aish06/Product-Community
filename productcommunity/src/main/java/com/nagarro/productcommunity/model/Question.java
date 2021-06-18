package com.nagarro.productcommunity.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="Question_Details") //Name of table in database
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Question implements Serializable
{
	@Id
  	@Column(name = "Question_Id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	int questionId;

	@ManyToOne
    @JoinColumn(name = "Username", nullable = false)
	User user;
  	
	@Column
	String userEmail;
	
	@Column
	String userFirstName;

	@Column(name = "Question_Text",columnDefinition = "LONGTEXT")
	String questionText;
  	
  	@Column(name = "Product_Code",unique = true)
	String productCode;
  	
  	@Column(name="Product_label")
  	String productLabel;
  	
  	@Column(name = "Created_Date")
	String createdDate;
  	
  	@OneToMany(mappedBy = "question", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    Set<Comment> comments = new HashSet<>();
  	
  	@Column(name="Answered",columnDefinition = "boolean default false")
  	boolean answer;
  	
  	@Column(name="Likes")
  	@ElementCollection
    Set<String> likes = new HashSet<>();
  	
  	public Question() {}

	public Question(int questionId, User user, String questionText, String productCode, String createdDate,Set<Comment> comments, boolean answer, Set<String> likes,String productLabel) {
		this.questionId = questionId;
		this.user = user;
		this.questionText = questionText;
		this.productCode = productCode;
		this.createdDate = createdDate;
		this.comments = comments;
		this.answer = answer;
		this.likes = likes;
		this.productLabel=productLabel;
		this.userEmail=user.getEmail();
	}


	public String getUserFirstName() {
		return userFirstName;
	}





	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}



	public String getUserEmail() {
		return userEmail;
	}





	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}





	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String string) {
		this.createdDate = string;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	
	
	public boolean getAnswer() {
		return answer;
	}


	public void setAnswer(boolean answer) {
		this.answer = answer;
	}


	
	public Set<String> getLikes() {
		return likes;
	}


	


	public String getProductLabel() {
		return productLabel;
	}





	public void setProductLabel(String productLabel) {
		this.productLabel = productLabel;
	}





	public void setLikes(Set<String> likes) {
		this.likes = likes;
	}

}

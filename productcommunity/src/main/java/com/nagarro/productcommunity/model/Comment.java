package com.nagarro.productcommunity.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="Comment_Details")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Comment implements Serializable
{
	@Id
  	@Column(name = "Comment_Id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	int commentId;
	
	
	@Column(name="Comment_Text")
	String commentText;
	
	@ManyToOne
    @JoinColumn(name = "Username", nullable = false)
	User user;
	
	@ManyToOne
    @JoinColumn(name = "Question", nullable = false)
	Question question;
	
	@Column(name="Answer",columnDefinition = "boolean default false")
  	boolean answer;
	
	@Column(name="Created_Date")
	String createdDate;
	
	public Comment() {}


	

	public Comment(int commentId, String commentText, User user, Question question, boolean answer) 
	{
		this.commentId = commentId;
		this.commentText = commentText;
		this.user = user;
		this.question = question;
		this.answer = answer;
	}




	public boolean isAnswer() {
		return answer;
	}



	
	
	
	

	public String getCreatedDate() {
		return createdDate;
	}




	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}




	public void setAnswer(boolean answer) {
		this.answer = answer;
	}




	public int getCommentId() {
		return commentId;
	}


	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	
	

	public String getCommentText() {
		return commentText;
	}


	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Question getQuestion() {
		return question;
	}


	public void setQuestion(Question question) {
		this.question = question;
	}
	
	
	
}

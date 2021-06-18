package com.nagarro.productcommunity.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityNotFoundException;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.productcommunity.model.Comment;
import com.nagarro.productcommunity.model.Question;
import com.nagarro.productcommunity.model.User;
import com.nagarro.productcommunity.service.CommentService;
import com.nagarro.productcommunity.service.QuestionService;
import com.nagarro.productcommunity.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")

public class CommentController 
{
	

	@Autowired
	UserService userService;
	
	@Autowired
	QuestionService questionService;
	
	@Autowired
	CommentService commentService;
	
	@PostMapping(value = "/postComment",consumes = "application/json")
	public ResponseEntity<?> postNewComment(@RequestParam("userEmail") String userEmail, @RequestParam("questionId") int questionId,@RequestBody Comment comment)
	{
		 Date currentDate = new Date();  
		 SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");  
		 String createdDate= formatter.format(currentDate);  
		 System.out.println(createdDate);

		User loggedInUser=null;
		try
		{
			loggedInUser=userService.getUserByEmail(userEmail);
		}
		//Email id is not registered
		catch(EntityNotFoundException e)
		{
			return new ResponseEntity<>("Email id not registered",HttpStatus.NOT_FOUND);
		}
		
		Question question=null;
		try 
		{
			question=questionService.getQuestionById(questionId);
		}
		catch(EntityNotFoundException e)
		{
			return new ResponseEntity<>("No such Question exist",HttpStatus.NOT_FOUND);
		}
		
		comment.setUser(loggedInUser);
		comment.setQuestion(question);
		comment.setCreatedDate(createdDate);
		Comment addedComment=commentService.addNewComment(comment);
		System.out.println(addedComment);
		JSONObject commentJson=commentService.objectToJsonObj(addedComment);
		System.out.println(commentJson);
		
		return new ResponseEntity<>(commentJson, HttpStatus.OK);
	}
}

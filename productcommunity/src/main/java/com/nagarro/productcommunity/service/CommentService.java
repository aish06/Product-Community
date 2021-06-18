package com.nagarro.productcommunity.service;

import javax.persistence.EntityNotFoundException;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.productcommunity.dao.CommentDao;
import com.nagarro.productcommunity.model.Comment;

@Service
public class CommentService
{
	@Autowired
	CommentDao commentDao;
	
	public Comment addNewComment(Comment newComment)
	{
		return commentDao.save(newComment);
	}
	
	public Comment getCommentById(int commentId)throws EntityNotFoundException
	{
		return commentDao.getById(commentId);
	}
	
	public Comment updateComment(Comment newComment)
	{
		return commentDao.save(newComment);
	}
	
	public JSONObject objectToJsonObj(Comment addedComment)
	{
	    JSONObject commentObj = new JSONObject();
	    commentObj.put("commentUserEmail",addedComment.getUser().getEmail());
	    commentObj.put("commentUserName",addedComment.getUser().getFirstName()+" "+addedComment.getUser().getLastName());
	    commentObj.put("commentQuestionId",addedComment.getQuestion().getQuestionId());
	    commentObj.put("commentId",addedComment.getCommentId());
	    commentObj.put("commentText",addedComment.getCommentText());

	    return commentObj;

	}
}

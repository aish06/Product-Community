package com.nagarro.productcommunity.service;


import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.nagarro.productcommunity.dao.QuestionDao;
import com.nagarro.productcommunity.model.Comment;
import com.nagarro.productcommunity.model.Question;


@Service
public class QuestionService 
{
	@Autowired
	QuestionDao questionDao;
	
	public Question postNewQuestion(Question newQuestion) throws SQLIntegrityConstraintViolationException
	{
		List<Question> allQuestions=questionDao.findAll();
		for(Question question:allQuestions)
		{
			if(newQuestion.getProductCode().equals(question.getProductCode()))
			{
				throw new SQLIntegrityConstraintViolationException();
			}
		}
		return questionDao.save(newQuestion);
	}
	
	
	public Question updateQuesion(Question question)
	{
		return questionDao.save(question);
	}
	
	@SuppressWarnings("unchecked")
	public JSONArray listToJsonArray(List<Question> questions)
	{
		JSONArray questionsJsonArray = new JSONArray();
		for(Question ques:questions)
		{
		    JSONObject questionObj = new JSONObject();
		    questionObj.put("questionId",ques.getQuestionId());
		    questionObj.put("questionText",ques.getQuestionText());
		    questionObj.put("productCode",ques.getProductCode());
		    questionObj.put("dateCreated",ques.getCreatedDate());
		    questionObj.put("productLabel",ques.getProductLabel());
		    questionObj.put("author",ques.getUser().getFirstName());
		    questionObj.put("userEmail",ques.getUserEmail());
		    questionObj.put("showComments",false);
		    questionObj.put("readMore",true);

//			userQuestions.sort((Question ques1,Question ques2)->ques2.getCreatedDate().compareTo(ques1.getCreatedDate()));

			List<Comment> questionComment=new ArrayList<>(ques.getComments());
			questionComment.sort((Comment c1,Comment c2)->c2.getCreatedDate().compareTo(c1.getCreatedDate()));

			JSONArray comments=new JSONArray();
		    for(Comment comment:questionComment)
		    {
			    JSONObject commentObj = new JSONObject();
			    commentObj=new JSONObject();
			    commentObj.put("commentId",comment.getCommentId());
			    commentObj.put("commentText",comment.getCommentText());
			    commentObj.put("commentUserEmail", comment.getUser().getEmail());
			    commentObj.put("commentUserName", comment.getUser().getFirstName()+" "+comment.getUser().getLastName());
			    commentObj.put("commentQuestionId",comment.getQuestion().getQuestionId());
			    commentObj.put("answer", comment.isAnswer());
			    commentObj.put("createdDate", comment.getCreatedDate());
			    comments.add(commentObj);
		    }
		  questionObj.put("comments",comments);
		  questionObj.put("commentsCount",comments.size());
		    
		  questionObj.put("answered",ques.getAnswer());
		   
		    List<String> likesUserEmail=new ArrayList<>();
		    
		    for(String userEmail:ques.getLikes())
		    {
		    	likesUserEmail.add(userEmail);
		    }
		    questionObj.put("likes",likesUserEmail);
		    questionObj.put("likesCount",likesUserEmail.size());
		    questionsJsonArray.add(questionObj);
		}
		return questionsJsonArray;
	}
	
	
	public Question getQuestionById(int questionId)throws EntityNotFoundException
	{
		return questionDao.getById(questionId);
	}
	
	
	public List<Question> getQuestionsBySearchParameters(Specification<Question> spec)
	{
		return questionDao.findAll(spec,Sort.by(Sort.Direction.DESC, "createdDate"));
	}
	
	public int getQuestionsCount()
	{
		return (int) questionDao.count();
	}
	
	public int solvedQuestionsCount()
	{
		return (int) questionDao.countByAnswer(true);
	}
	
	public List<Question> getAllQuestions()
	{
		return questionDao.findAll(Sort.by(Sort.Direction.DESC, "createdDate"));
	}
	
	
	public List<String> getAllProductLabels()
	{
		return questionDao.findAllProductLabels();
	}
	
	
	public void deleteQuestion(Question question)
	{
		questionDao.delete(question);
	}
	
	
//	public List<Question> getUserQuestions(String userEmail)
//	{
//		return questionDao.userQuestion(userEmail);
//	}
}

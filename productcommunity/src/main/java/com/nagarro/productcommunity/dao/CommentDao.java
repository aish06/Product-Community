package com.nagarro.productcommunity.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.productcommunity.model.Comment;

public interface CommentDao extends JpaRepository<Comment, Integer>
{

}

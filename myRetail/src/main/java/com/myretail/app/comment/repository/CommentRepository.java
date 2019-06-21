package com.myretail.app.comment.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myretail.app.comment.entity.Comment;
import com.myretail.app.products.entity.ProductDetailsEntity;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {

}

package com.myretail.app.comment.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myretail.app.comment.model.CommentEntityObjectionWordRecord;
import com.myretail.app.comment.model.CommentModel;
import com.myretail.app.comment.model.CommentPostResponse;
import com.myretail.app.comment.service.CommentService;

@RestController
@RequestMapping("/api/comment/")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@RequestMapping(value = "/v1/create", method = RequestMethod.POST)
	public CommentPostResponse createNewProduct(
			@RequestBody CommentModel comment) {
		return commentService.postComment(comment);
	}
	
	@RequestMapping(value = "/v1/obbjectionslist", method = RequestMethod.GET)
	public Set<CommentEntityObjectionWordRecord> getProductDescription() {
		return commentService.getCommentsWithObjectionWords();
	}
	
	
}

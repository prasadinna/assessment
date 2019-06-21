package com.myretail.app.comment.model;

import java.util.LinkedList;
import java.util.List;

import com.myretail.app.comment.entity.Comment;

public class CommentEntityObjectionWordRecord {
	private Comment comment;
	private List<String> objectionWords = new LinkedList<String>();

	public CommentEntityObjectionWordRecord(Comment comment){
		this.comment = comment;
	}
	public Comment getComment() {
		return comment;
	}

	public List<String> getObjectionWords() {
		return objectionWords;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public void setObjectionWords(List<String> objectionWords) {
		this.objectionWords = objectionWords;
	}
}

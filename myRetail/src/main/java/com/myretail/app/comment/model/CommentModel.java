package com.myretail.app.comment.model;

import java.util.List;

public class CommentModel {
	String userId;
	long creationDate;
	String documentReference;
	String commentContent;
	String commentStatus;// enum - SUBMITTED, EDIT, APPROVED, DELETED
	String parentId;
	List<String> childIds;

	public String getUserId() {
		return userId;
	}

	public long getCreationDate() {
		return creationDate;
	}

	public String getDocumentReference() {
		return documentReference;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public String getCommentStatus() {
		return commentStatus;
	}

	public String getParentId() {
		return parentId;
	}

	public List<String> getChildIds() {
		return childIds;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setCreationDate(long creationDate) {
		this.creationDate = creationDate;
	}

	public void setDocumentReference(String documentReference) {
		this.documentReference = documentReference;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public void setCommentStatus(String commentStatus) {
		this.commentStatus = commentStatus;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public void setChildIds(List<String> childIds) {
		this.childIds = childIds;
	}
}

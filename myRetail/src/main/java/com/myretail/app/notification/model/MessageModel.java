package com.myretail.app.notification.model;

import java.util.List;

public class MessageModel {
	private String from;
	private List<String> clientList;
	private String subject;
	private String body;
	private String document_type;
	private String documentReference;
	private String parentMsgId;
	private String msgKey;
	private String topic;
	private String partitionKey;
	public String getFrom() {
		return from;
	}

	public String getSubject() {
		return subject;
	}
	public String getBody() {
		return body;
	}
	public String getDocument_type() {
		return document_type;
	}
	public String getDocumentReference() {
		return documentReference;
	}
	public String getParentMsgId() {
		return parentMsgId;
	}
	public String getMsgKey() {
		return msgKey;
	}
	public void setFrom(String from) {
		this.from = from;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public void setDocument_type(String document_type) {
		this.document_type = document_type;
	}
	public void setDocumentReference(String documentReference) {
		this.documentReference = documentReference;
	}
	public void setParentMsgId(String parentMsgId) {
		this.parentMsgId = parentMsgId;
	}
	public void setMsgKey(String msgKey) {
		this.msgKey = msgKey;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getPartitionKey() {
		return partitionKey;
	}
	public void setPartitionKey(String partitionKey) {
		this.partitionKey = partitionKey;
	}

	public List<String> getClientList() {
		return clientList;
	}

	public void setClientList(List<String> clientList) {
		this.clientList = clientList;
	}
	
}

package com.myretail.app.notification.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailMessageChannelProcessor extends AbstractMessageProcessorChannel {
	
	private MessageCenter msgcenter;
	@Autowired
	public void setMsgcenter(MessageCenter msgcenter){
		this.msgcenter = msgcenter;
		this.msgcenter.registerClient(this);
	}
	
	public String getChannelName() {
		// TODO Auto-generated method stub
		return "EMail";
	}
}

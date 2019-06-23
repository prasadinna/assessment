package com.myretail.app.notification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SlackMessageChannelProcessor extends AbstractMessageProcessorChannel {

	private MessageCenter msgcenter;
	@Autowired
	public void setMsgcenter(MessageCenter msgcenter){
		this.msgcenter = msgcenter;
		this.msgcenter.registerClient(this);
	}
	
	public String getChannelName() {
		return "Slack";
	}

}

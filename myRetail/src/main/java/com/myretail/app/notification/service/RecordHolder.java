package com.myretail.app.notification.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import com.myretail.app.notification.model.MessageModel;

class RecordHolder{
    protected List<MessageModel> msgList = new ArrayList<MessageModel>();
	protected ReentrantLock lock = new ReentrantLock();
	protected String channelName;
	public List<MessageModel> getMsgList() {
		return msgList;
	}
	public ReentrantLock getLock() {
		return lock;
	}
	public void setMsgList(List<MessageModel> msgList) {
		this.msgList = msgList;
	}
	public void setLock(ReentrantLock lock) {
		this.lock = lock;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	
	public List<MessageModel>  drainToProcessingList(){
		List<MessageModel>  processingList = msgList;
		msgList = new ArrayList<MessageModel>();
		return processingList;
	}
}
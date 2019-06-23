package com.myretail.app.notification.service;

import java.util.List;

import com.myretail.app.notification.model.MessageModel;

public interface IMessageProcessorChannel {
  public String getChannelName();
  public void enqueueMessage(MessageModel msg);
}

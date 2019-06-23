package com.myretail.app.notification.service;

import java.util.List;

import com.myretail.app.notification.model.MessageModel;

public interface IPartitionProcessor {
   public void addMessageToPartition(MessageModel msg);
   public void processPartitionMessages();
   public void sendToclient(List<MessageModel> msgList);
   public void sendToclient(MessageModel msg);
}

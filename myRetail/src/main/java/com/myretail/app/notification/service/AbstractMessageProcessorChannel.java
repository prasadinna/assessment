package com.myretail.app.notification.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

import com.myretail.app.notification.model.MessageModel;

public abstract class AbstractMessageProcessorChannel implements IMessageProcessorChannel {

	private Map<String, IPartitionProcessor> topicVsPartitions = new ConcurrentHashMap<String, IPartitionProcessor>();
	public void enqueueMessage(MessageModel msg) {
		String topicName = msg.getTopic() != null?msg.getTopic():"defaultTopic";
		IPartitionProcessor partitionProcessor = 
				topicVsPartitions.putIfAbsent(topicName, new PartitionProcessor(this.getChannelName()));
		if(partitionProcessor == null ){
			partitionProcessor = topicVsPartitions.get(topicName);
		}
		partitionProcessor.addMessageToPartition(msg);
		

	}

}

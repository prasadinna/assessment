package com.myretail.app.notification.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.Lock;

import com.myretail.app.notification.model.MessageModel;

public class AbstractPartitionProcessor implements IPartitionProcessor {
	private String channelName;
	private static PartitionPublisher publisher = new PartitionPublisher();
    protected Map<String, RecordHolder> partitionVsmsgList = 
    		new ConcurrentHashMap<String, RecordHolder>();
    public AbstractPartitionProcessor(String channelName){
    	this.channelName = channelName;
    }
	public void addMessageToPartition(MessageModel msg) {
		String partitionKey = msg.getPartitionKey() != null?msg.getPartitionKey():"defaultPartition";
		RecordHolder recHolder = partitionVsmsgList.putIfAbsent(partitionKey, new RecordHolder());
		if(recHolder == null){
			recHolder = partitionVsmsgList.get(partitionKey);
		}
		Lock lock = recHolder.getLock();
		try{
			lock.lock();
			recHolder.getMsgList().add(msg);
		}catch(Exception e){
			
		}finally {
			lock.unlock();
		}
		publisher.submitTask(this,msg);
		
	}

	public void processPartitionMessages() {
		// TODO Auto-generated method stub

	}

	public void sendToclient(List<MessageModel> msgList) {
		for(MessageModel msg:msgList){
			sendToclient(msg);
		}
		
	}

	public void sendToclient(MessageModel msg) {
		System.out.println("Processing the message:channel:"+this.channelName+"-msg:"+msg.getMsgKey());
		
	}

}

class PartitionPublisher{
	private static ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
	public void submitTask(AbstractPartitionProcessor partition,MessageModel msg){
		PublishTask task = new PublishTask(partition,msg);
		String partitionKey = msg.getPartitionKey() != null?msg.getPartitionKey():"defaultPartition";
		executor.submit(task);
	}
	
	
}

class PublishTask implements Runnable{
    private AbstractPartitionProcessor partitionProcessor;
    private MessageModel msg;
    public PublishTask(AbstractPartitionProcessor partitionProcessor, MessageModel msg){
    	this.partitionProcessor = partitionProcessor;
    	this.msg = msg;

    }
	public void run() {
		String partitionKey = msg.getPartitionKey() != null?msg.getPartitionKey():"defaultPartition";
		RecordHolder recHolder = partitionProcessor.partitionVsmsgList.get(partitionKey);
		Lock lock = recHolder.getLock();
		List<MessageModel> msgList = null;
		try{
			lock.lock();
			msgList = recHolder.drainToProcessingList();
			if(msgList.size() > 0){
				partitionProcessor.sendToclient(msgList);
			}
		}catch(Exception e){
			
		}finally {
			lock.unlock();
		}
		
		
		
	}
	
}

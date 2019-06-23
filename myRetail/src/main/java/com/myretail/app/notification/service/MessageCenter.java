package com.myretail.app.notification.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.stereotype.Service;

import com.myretail.app.notification.model.MessageModel;

@Service
public class MessageCenter {
	//DB backed msg queue
	private BlockingQueue<MessageModel> messageQ = new LinkedBlockingQueue(100000);
	private Map<String,IMessageProcessorChannel> clintTypeVschannleProcessor =
			new HashMap<String,IMessageProcessorChannel>();
	public MessageCenter(){
		Runnable task = new Runnable(){

			public void run() {
				while(true){
					try {
						MessageModel message = messageQ.take();
						List<String> clientList = message.getClientList();
						for(String client:clientList){
							//handle no handler scenario
							IMessageProcessorChannel channel = clintTypeVschannleProcessor.get(client);
							channel.enqueueMessage(message);
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		};
		Thread pubMsg = new Thread(task);
		pubMsg.start();
	}
	public String postMessage(MessageModel msg){
		boolean success =  messageQ.offer(msg);
		if(!success){
			//there should be fall back or block
			return "failure";
		}
		return "success";
	}
	
	public void registerClient(IMessageProcessorChannel channelProcessor){
		clintTypeVschannleProcessor.put(channelProcessor.getChannelName(), channelProcessor);
	}
}

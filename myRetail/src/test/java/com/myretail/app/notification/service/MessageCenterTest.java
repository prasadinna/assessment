package com.myretail.app.notification.service;

import java.util.ArrayList;
import java.util.List;

import com.myretail.app.notification.model.MessageModel;

public class MessageCenterTest {

	public static void main(String[] args) {
		MessageCenter msgCenter = new MessageCenter();
		EmailMessageChannelProcessor emailCP = new EmailMessageChannelProcessor();
		emailCP.setMsgcenter(msgCenter);
		SlackMessageChannelProcessor slackCP = new SlackMessageChannelProcessor();
		slackCP.setMsgcenter(msgCenter);
		String methodToinvoke = "getEmailNSlackMsgNoPartition";
		for (int i = 1; i < 100; i++) {
			MessageModel model = null;
			switch (methodToinvoke) {
			case "getEmailNSlackMsgNoPartition":
				model = getEmailNSlackMsgNoPartition(i);
				methodToinvoke = "getEmailMsgNoPartition";
				break;
			case "getEmailMsgNoPartition":
				model = getEmailMsgNoPartition(i);
				methodToinvoke = "getEmailNSlackMsgPartitionNTopic";
				break;
			case "getEmailNSlackMsgPartitionNTopic":
				model = getEmailNSlackMsgPartitionNTopic(i);
				methodToinvoke = "getEmailMsgNoPartitionNTopic";
				break;
			case "getEmailMsgNoPartitionNTopic":
				model = getEmailNSlackMsgNoPartition(i);
				methodToinvoke = "getEmailNSlackMsgNoPartition";
				break;
			}
			msgCenter.postMessage(model);
		}

	}

	private static MessageModel getEmailNSlackMsgNoPartition(int index) {
		List<String> emailNslkClientList = new ArrayList();
		emailNslkClientList.add("Slack");
		emailNslkClientList.add("EMail");
		MessageModel model = new MessageModel();
		model.setClientList(emailNslkClientList);
		model.setMsgKey("EmailNslack-" + index);
		return model;
	}

	private static MessageModel getEmailMsgNoPartition(int index) {
		List<String> emailNslkClientList = new ArrayList();
		emailNslkClientList.add("EMail");
		MessageModel model = new MessageModel();
		model.setClientList(emailNslkClientList);
		model.setMsgKey("Email-" + index);
		return model;
	}

	private static MessageModel getEmailNSlackMsgPartitionNTopic(int partition) {
		List<String> emailNslkClientList = new ArrayList();
		emailNslkClientList.add("Slack");
		emailNslkClientList.add("EMail");
		MessageModel model = new MessageModel();
		model.setClientList(emailNslkClientList);
		model.setMsgKey("EmailNslack-" + partition);
		model.setTopic("mailslacktoppic-"+partition);
		return model;
	}

	private static MessageModel getEmailMsgNoPartitionNTopic(int partition) {
		List<String> emailNslkClientList = new ArrayList();
		emailNslkClientList.add("EMail");
		MessageModel model = new MessageModel();
		model.setClientList(emailNslkClientList);
		model.setMsgKey("Email-" + partition);
		model.setTopic("mailtoppic-"+partition);
		return model;
	}

}

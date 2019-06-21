package com.myretail.app.comment.service;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myretail.app.comment.entity.Comment;
import com.myretail.app.comment.model.CommentEntityObjectionWordRecord;
import com.myretail.app.comment.model.CommentModel;
import com.myretail.app.comment.model.CommentPostResponse;
import com.myretail.app.comment.repository.CommentRepository;

@Service
public class CommentService {
	@Autowired
	private CommentRepository commentRepo;

	@Autowired
	private InMemoryIndexer indexer;
	
	private Set<CommentEntityObjectionWordRecord> objectionableComment = 
			new HashSet<CommentEntityObjectionWordRecord>();

	private BlockingQueue<Comment> commentQ = new LinkedBlockingQueue<Comment>(10000);

	public CommentService() {
		Runnable task = new Runnable() {
			public void run() {
				while (true) {
					try {
						Comment comment = commentQ.take();
						if (comment != null) {
							indexer.processComment(comment);
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		};
		
		Thread publisher = new Thread(task);
		publisher.start();
	}

	public CommentPostResponse postComment(CommentModel comment) {

		Comment commentEntity = getCommentEntityFromModel(comment);
		//TODO:write the repository
		//commentEntity = commentRepo.save(commentEntity);
		try {
			commentQ.put(commentEntity);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CommentPostResponse response = new CommentPostResponse();
		return response;
	}

	private Comment getCommentEntityFromModel(CommentModel model) {
		Comment cmt = new Comment();
		cmt.setCommentContent(model.getCommentContent());
		cmt.setCommentStatus(model.getCommentStatus());
		cmt.setCreationDate(model.getCreationDate());
		cmt.setDocumentReference(model.getDocumentReference());
		cmt.setParentId(model.getParentId());
		cmt.setUserId(model.getUserId());
		return cmt;
	}
	
	public Set<String> getObjectionKeyWords(){
		Set<String> objectionKeys = new HashSet<String>();
		//TODO:load from config file
		objectionKeys.add("infamous");
		objectionKeys.add("thief");
		objectionKeys.add("murder");
		objectionKeys.add("kill");
		objectionKeys.add("miser");
		objectionKeys.add("imprison");
		return objectionKeys;
	}
	
	public void saveCommentWithObjectionWords(CommentEntityObjectionWordRecord record){
		objectionableComment.add(record);
	}
	
	public Set<CommentEntityObjectionWordRecord> getCommentsWithObjectionWords(){
		Set<CommentEntityObjectionWordRecord> currentSnappShot = objectionableComment;
		objectionableComment = new HashSet<CommentEntityObjectionWordRecord>();
		return currentSnappShot;
	}
}

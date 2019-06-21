package com.myretail.app.comment.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import com.myretail.app.comment.entity.Comment;
import com.myretail.app.comment.model.CommentEntityObjectionWordRecord;

public class ObjectionContentListener extends  TokenFilter {
    private Set<String> ObjectionContentSet;
    private final CharTermAttribute termAtt = addAttribute(CharTermAttribute.class);
    private TokenStream input;
    private CommentEntityObjectionWordRecord comment;
    private CommentService service;
	protected ObjectionContentListener(TokenStream input, CommentEntityObjectionWordRecord comment, CommentService commentService) {
		super(input);
		this.input = input;
		this.comment = comment;
		this.service = commentService;
	}

	protected ObjectionContentListener(TokenStream input, Set<String> ObjectionContentSet,CommentEntityObjectionWordRecord comment,
			CommentService commentService) {
		super(input);
		this.ObjectionContentSet = ObjectionContentSet;
		this.comment = comment;
		this.service = commentService;
	}

	@Override
	public boolean incrementToken() throws IOException {
		char []buffer = termAtt.buffer();
		if(termAtt.length() < 1){
			return input.incrementToken();
		}
		char []bufferSub = Arrays.copyOfRange(buffer, 0, termAtt.length());
		String currentString = new String(bufferSub);
		if(currentString != null && !currentString.trim().equals("")){
			if(ObjectionContentSet == null){
				ObjectionContentSet = service.getObjectionKeyWords();
			}
			List<String> commentObjectionString = new LinkedList<String>();
			for(String objectionStr:ObjectionContentSet){
				if(currentString.startsWith(objectionStr)){
					comment.getObjectionWords().add(currentString);
				}
			}
			if(comment.getObjectionWords().size() > 0){
				service.saveCommentWithObjectionWords(comment);
			}
			
		}
		
		return input.incrementToken();
	}



}

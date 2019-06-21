package com.myretail.app.comment.service;

import java.io.IOException;
import java.io.Reader;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.LowerCaseFilter;
import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.en.PorterStemFilter;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.standard.StandardFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.util.CharArraySet;
import org.apache.lucene.analysis.util.StopwordAnalyzerBase;
import org.apache.lucene.util.Version;

import com.myretail.app.comment.entity.Comment;
import com.myretail.app.comment.model.CommentEntityObjectionWordRecord;

public class CustomAnalyzer extends StopwordAnalyzerBase{
	  public static final int DEFAULT_MAX_TOKEN_LENGTH = 255;

	  private int maxTokenLength = DEFAULT_MAX_TOKEN_LENGTH;
	  public static final CharArraySet STOP_WORDS_SET = StopAnalyzer.ENGLISH_STOP_WORDS_SET; 
	  private CommentEntityObjectionWordRecord comment;
	  private CommentService commentService;
	  protected CustomAnalyzer(Version version,  CommentEntityObjectionWordRecord comment, CommentService commentService) {
		 super(version, STOP_WORDS_SET);
		 this.comment = comment;
		 this.commentService = commentService;
	  }

	  @Override
	  protected TokenStreamComponents createComponents(final String fieldName, final Reader reader) {
	    final StandardTokenizer src = new StandardTokenizer(matchVersion, reader);
	    src.setMaxTokenLength(maxTokenLength);
	    TokenStream tok = new StandardFilter(matchVersion, src);
	    tok = new LowerCaseFilter(matchVersion, tok);
	    tok = new StopFilter(matchVersion, tok, stopwords);
	    tok = new PorterStemFilter(tok);
	    tok = new ObjectionContentListener(tok, comment,commentService);
	    return new TokenStreamComponents(src, tok) {
	      @Override
	      protected void setReader(final Reader reader) throws IOException {
	        src.setMaxTokenLength(CustomAnalyzer.this.maxTokenLength);
	        super.setReader(reader);
	      }
	    };
	  }

}

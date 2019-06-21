package com.myretail.app.comment.service;

import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.document.Document;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.myretail.app.comment.entity.Comment;
import com.myretail.app.comment.model.CommentEntityObjectionWordRecord;

import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.queryparser.simple.*;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.analysis.Analyzer;

@Component
public class InMemoryIndexer {
	@Autowired
	private CommentService commentService;
	
	public void processComment(Comment comment) {

		RAMDirectory idx = new RAMDirectory();

		try {
			CustomAnalyzer analyzer = new CustomAnalyzer(Version.LUCENE_42,
					new CommentEntityObjectionWordRecord(comment),commentService);

			IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_42, analyzer);
			IndexWriter writer = new IndexWriter(idx, indexWriterConfig);

			// Add some Document objects containing quotes
			writer.addDocument(createDocument(comment));

			// Optimize and close the writer to finish building the index
			writer.close();

			// Build an IndexSearcher using the in-memory index
			IndexReader reader = DirectoryReader.open(idx);
			IndexSearcher searcher = new IndexSearcher(reader);

		} catch (IOException ioe) {
			// In this example we aren't really doing an I/O, so this
			// exception should never actually be thrown.
			ioe.printStackTrace();
		}

	}

	private static Document createDocument(Comment comment) {
		Document doc = new Document();

		doc.add(new StringField("id", comment.getId() + "", Field.Store.YES));
		doc.add(new TextField("content", comment.getCommentContent(), Field.Store.YES));
		return doc;
	}

	/**
	 * Searches for the given string in the "content" field
	 */
	private void search(IndexSearcher searcher, String queryString, Comment comment) throws ParseException, IOException {

		Query query = new QueryParser(Version.LUCENE_42, "content", new CustomAnalyzer(Version.LUCENE_42,
				new CommentEntityObjectionWordRecord(comment),commentService))
				.parse(queryString);
		int hitsPerPage = 10;
		TopScoreDocCollector collector = TopScoreDocCollector.create(hitsPerPage, true);
		searcher.search(query, collector);
		ScoreDoc[] hits = collector.topDocs().scoreDocs;

		for (int i = 0; i < hits.length; ++i) {
			int docId = hits[i].doc;
			Document d = searcher.doc(docId);
			System.out.println((i + 1) + ". " + d.get("title"));
		}
		System.out.println();
	}
}

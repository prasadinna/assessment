package com.rom.app.solr.search.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.solr.repository.Query;
import org.springframework.stereotype.Repository;

import com.rom.app.order.management.model.OrderDetailSolrModel;

@Repository
public interface ModelSolrRepository extends PagingAndSortingRepository<OrderDetailSolrModel, String> {
	@Query("id:*?0* OR name:*?0* OR description:*?0*")
	public List<OrderDetailSolrModel> queryByContent(String value);
}     
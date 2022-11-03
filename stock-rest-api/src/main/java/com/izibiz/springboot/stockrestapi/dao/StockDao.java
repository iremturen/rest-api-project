package com.izibiz.springboot.stockrestapi.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import com.izibiz.springboot.stockrestapi.model.Stock;

@Repository
public interface StockDao extends JpaRepository<Stock, Long>, CrudRepository<Stock, Long>,
		PagingAndSortingRepository<Stock, Long>, JpaSpecificationExecutor<Stock>, QueryByExampleExecutor<Stock> {

	Page<Stock> findAll(Specification<Stock> specification, Pageable page);

}

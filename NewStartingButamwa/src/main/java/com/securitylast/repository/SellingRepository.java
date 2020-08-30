package com.securitylast.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.securitylast.domain.Selling;

@Repository
public interface SellingRepository extends PagingAndSortingRepository<Selling, String> {
	@Query(value="select a from Selling a where a.recorderon <=?2 AND a.recorderon >=?1")
	Iterable<Selling> findByRecorderonInBetwwen(Date value2, Date value1);
     
	
	Selling findByIdnum(String id);
	// Iterable<Selling> findRecorderonBetween(String value1,String value2);
}

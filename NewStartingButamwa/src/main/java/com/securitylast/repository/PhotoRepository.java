package com.securitylast.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.securitylast.domain.Photos;
import com.securitylast.domain.User;
@Repository
public interface PhotoRepository extends PagingAndSortingRepository<Photos, String> {
	Photos findByPhotoId(String id);
}

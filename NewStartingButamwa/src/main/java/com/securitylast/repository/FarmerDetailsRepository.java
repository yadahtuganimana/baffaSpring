package com.securitylast.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.securitylast.domain.FarmerDetails;
import com.securitylast.domain.User;

@Repository
public interface FarmerDetailsRepository extends PagingAndSortingRepository<FarmerDetails, String> {
	Iterable<FarmerDetails> findByUserRolesEqualsOrUserRolesEquals(String farmer, String fam);

	FarmerDetails findByIdnum(String id);

	Iterable<FarmerDetails> findAllByIdnum(String id);

	FarmerDetails findByUserRoles(String role);
}

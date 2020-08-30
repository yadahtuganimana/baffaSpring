package com.securitylast.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.securitylast.domain.UserEmails;
@Repository
public interface UseremailsRepo extends PagingAndSortingRepository<UserEmails, String>{
UserEmails findByUsername(String username);

}

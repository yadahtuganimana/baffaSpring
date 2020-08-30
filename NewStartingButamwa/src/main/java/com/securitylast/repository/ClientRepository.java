package com.securitylast.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.securitylast.domain.Clients;
@Repository
public interface ClientRepository extends PagingAndSortingRepository<Clients, String> {
	Clients findByContact(String contact);

	Clients findByFullname(String fullname);

	Clients findByIdnum(String id);
    
}

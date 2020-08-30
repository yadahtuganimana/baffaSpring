package com.securitylast.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.securitylast.domain.Location;
import com.securitylast.domain.LocationType;

@Repository
public interface LocationRepository extends PagingAndSortingRepository<Location, String> {
	Location findByLocname(String id);
   List<Location> findByLocationtype(LocationType loctype);
}

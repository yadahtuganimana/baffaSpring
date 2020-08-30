package com.securitylast.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.securitylast.domain.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, String> {
	User findByUsername(String username);

	User findByIdnum(String id);

	User findByActive(User active);

	Iterable<User> findByNewuserEqualsAndRolesEqualsOrRolesEqualsOrRolesEquals(String neww, String visitor,
			String admin, String farmer);
	// Iterable<User> findByRolesEquals(String visitor);

	Iterable<User> findByRolesEquals(String farmer);
  
	long countByNewuserAndRolesEquals(String newuser, String roletype);
	long countByNewuser(String newuser);
//	@Modifying(clearAutomatically = true)
//	@Query("update User u set u.roles = FARMERR where u.username = :email")
//	void updateRoles(@Param("email") String email);

}

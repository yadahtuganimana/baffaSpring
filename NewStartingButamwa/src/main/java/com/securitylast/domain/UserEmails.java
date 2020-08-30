package com.securitylast.domain;


import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class UserEmails {
@Column(unique=true)	
@Id	
private String idnumber=UUID.randomUUID().toString();

@NotBlank(message = "Username is mandatory")
private String username;

public String getId() {
	return idnumber;
}

public void setId(String id) {
	this.idnumber = id;
}

public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}



}

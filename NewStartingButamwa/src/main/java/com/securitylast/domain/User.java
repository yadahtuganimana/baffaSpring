package com.securitylast.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class User {
	@Column(unique = true)
	@Id
	private String idnum = UUID.randomUUID().toString();
	@NotBlank(message = "Firstname is mandatory")
	private String userfirstname;
	@NotBlank(message = "LastName is mandatory")
	private String userlastname;
	@NotBlank(message = "Username is mandatory")
	@Size(min = 4, max = 30)
	private String username;
    private String newuser="New";
	@NotBlank(message = "passowrd is mandatory")
	private String passwordd;
	private String active = "BLOCK";
	@NotBlank(message = "UserType is mandatory")
	private String roles = "";
	@OneToOne(mappedBy = "user")
    private FarmerDetails farmerdetails;    

	public User(String username, String passwordd, String roles) {
		this.username = username;
		this.passwordd = passwordd;
		this.roles = roles;
		this.active = "ACTIVE";
	}

	public User() {

	}

	public String getIdnum() {
		return idnum;
	}

	public void setIdnum(String idnum) {
		this.idnum = idnum;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswordd() {
		return passwordd;
	}

	public void setPasswordd(String passwordd) {
		this.passwordd = passwordd;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public List<String> getRoleList() {
		if (this.roles.length() > 0) {
			return Arrays.asList(this.roles.split(","));
		}
		return new ArrayList<String>();
	}

	public String getUserfirstname() {
		return userfirstname;
	}

	public void setUserfirstname(String userfirstname) {
		this.userfirstname = userfirstname;
	}

	public String getUserlastname() {
		return userlastname;
	}

	public void setUserlastname(String userlastname) {
		this.userlastname = userlastname;
	}

	public FarmerDetails getFarmerdetails() {
		return farmerdetails;
	}

	public void setFarmerdetails(FarmerDetails farmerdetails) {
		this.farmerdetails = farmerdetails;
	}

	public String getNewuser() {
		return newuser;
	}

	public void setNewuser(String newuser) {
		this.newuser = newuser;
	}

	
//public List<String> getPermissionss(){
//	if(this.permissions.length() > 0) {
//		return Arrays.asList(this.permissions.split(","))  ;
//	}
//	return new ArrayList<String>();
//}

}

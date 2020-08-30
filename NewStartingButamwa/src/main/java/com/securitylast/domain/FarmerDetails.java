package com.securitylast.domain;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
public class FarmerDetails {
	@Id
	private String idnum = UUID.randomUUID().toString();
	@NotBlank(message = "phone contact  is mandatory")
	@Pattern(regexp = "^[0-9]{10}", message = "length must be 10 and must be numbers only ")
	private String contactnumber;
	@NotBlank(message = "nationalidnumber is mandatory")
	@Pattern(regexp = "^[0-9]{16}", message = "length must be 16 and must be numbers only ")
	private String nationalidnumber;
	@NotBlank(message = "your dealy is mandatory")
	private String dailactivity;
	// @NotBlank(message = "provide your National id image")
	private String idcardimage;
	// @NotBlank(message = "provide your image")
	private String yourpictureimage;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "famr-dtls", referencedColumnName = "idnum")
	private User user;

	public String getIdnum() {
		return idnum;
	}

	public void setIdnum(String idnum) {
		this.idnum = idnum;
	}

	public String getContactnumber() {
		return contactnumber;
	}

	public void setContactnumber(String contactnumber) {
		this.contactnumber = contactnumber;
	}

	public String getNationalidnumber() {
		return nationalidnumber;
	}

	public void setNationalidnumber(String nationalidnumber) {
		this.nationalidnumber = nationalidnumber;
	}

	public String getIdcardimage() {
		return idcardimage;
	}

	public void setIdcardimage(String idcardimage) {
		this.idcardimage = idcardimage;
	}

	public String getYourpictureimage() {
		return yourpictureimage;
	}

	public void setYourpictureimage(String yourpictureimage) {
		this.yourpictureimage = yourpictureimage;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getDailactivity() {
		return dailactivity;
	}

	public void setDailactivity(String dailactivity) {
		this.dailactivity = dailactivity;
	}
}

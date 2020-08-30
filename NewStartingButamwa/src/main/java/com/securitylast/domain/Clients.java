package com.securitylast.domain;

import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "contact"))
public class Clients {
	@Column(unique = true)
	@Id
	private String idnum = UUID.randomUUID().toString();
	@NotBlank(message = "phone contact  is mandatory")
	@Pattern(regexp = "^[0-9]{10}", message = "length must be 10 and must be numbers only ")
	private String contact;
	@NotBlank(message = "Client fullname  is mandatory")
	private String fullname;
	@NotBlank(message = "location  is mandatory")
	private String ahoatuye;
	@JsonManagedReference
	@OneToMany(mappedBy = "clients")
	private List<Selling> selling;

	public String getIdnum() {
		return idnum;
	}

	public void setIdnum(String idnum) {
		this.idnum = idnum;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getAhoatuye() {
		return ahoatuye;
	}

	public void setAhoatuye(String ahoatuye) {
		this.ahoatuye = ahoatuye;
	}

	public List<Selling> getSelling() {
		return selling;
	}

	public void setSelling(List<Selling> selling) {
		this.selling = selling;
	}

	@Override
	public String toString() {
		return contact +fullname;
	}

}

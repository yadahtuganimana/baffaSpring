package com.securitylast.domain;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.OnDelete;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
public class Selling {
	@Id
	private String idnum = UUID.randomUUID().toString();
	@NotBlank(message = "productname  is mandatory")
	private String productname;
	@NotBlank(message = "time  is mandatory")
	private String saangahe;
	@NotNull(message = "quantiy is  mandatory")
	private Double quatyty;
	private String recordedby;
	@Temporal(TemporalType.DATE)
	Date recorderon=new Date();
	@JoinColumn(name = "fk-clients")
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Clients clients;
   
	public String getIdnum() {
		return idnum;
	}

	public void setIdnum(String idnum) {
		this.idnum = idnum;
	}


	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public Double getQuatyty() {
		return quatyty;
	}

	public void setQuatyty(Double quatyty) {
		this.quatyty = quatyty;
	}

	public String getRecordedby() {
		return recordedby;
	}

	public void setRecordedby(String recordedby) {
		this.recordedby = recordedby;
	}

	public Date getRecorderon() {
		return recorderon;
	}

	public void setRecorderon(Date recorderon) {
		
		this.recorderon =recorderon;
	}

	public String getSaangahe() {
		return saangahe;
	}

	public void setSaangahe(String saangahe) {
		
		this.saangahe = saangahe;
	}
	
		public Clients getClients() {
		return clients;
	}

	public void setClients(Clients clients) {
		this.clients = clients;
	}

	


  
}
